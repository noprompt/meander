(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__174111]
 (let*
  [ret__14429__auto__
   (clojure.core/letfn
    [(CATA__FN__174165
      [input__174111]
      (clojure.core/letfn
       [(state__175196
         []
         (if
          (clojure.core/vector? input__174111)
          (if
           (clojure.core/= (clojure.core/count input__174111) 3)
           (clojure.core/let
            [input__174111_nth_0__
             (clojure.core/nth input__174111 0)
             input__174111_nth_1__
             (clojure.core/nth input__174111 1)
             input__174111_nth_2__
             (clojure.core/nth input__174111 2)]
            (if
             (clojure.core/let
              [x__13309__auto__ input__174111_nth_0__]
              (clojure.core/case
               x__13309__auto__
               (meander.dev.parse.zeta/parse-seq
                meander.dev.parse.zeta/parse-string)
               true
               false))
             (clojure.core/letfn
              [(state__175220
                []
                (if
                 (clojure.core/vector? input__174111_nth_1__)
                 (clojure.core/case
                  input__174111_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__174111_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__16492__auto__
                     (if
                      (meander.runtime.zeta/fail? e__16492__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__16492__auto__)))))
                  (state__175221))
                 (state__175221)))
               (state__175221
                []
                (clojure.core/let
                 [?rule-name input__174111_nth_0__]
                 (if
                  (clojure.core/vector? input__174111_nth_1__)
                  (clojure.core/let
                   [n__174173
                    (clojure.core/count input__174111_nth_1__)
                    m__174174
                    (clojure.core/max 0 (clojure.core/- n__174173 2))
                    input__174111_nth_1___l__
                    (clojure.core/subvec
                     input__174111_nth_1__
                     0
                     (clojure.core/min
                      (clojure.core/count input__174111_nth_1__)
                      m__174174))
                    input__174111_nth_1___r__
                    (clojure.core/subvec
                     input__174111_nth_1__
                     m__174174)]
                   (if
                    (clojure.core/=
                     (clojure.core/count input__174111_nth_1___r__)
                     2)
                    (clojure.core/let
                     [!xs (clojure.core/vec input__174111_nth_1___l__)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__174111_nth_1___r__)
                       2)
                      (clojure.core/let
                       [input__174111_nth_1___r___nth_0__
                        (clojure.core/nth input__174111_nth_1___r__ 0)
                        input__174111_nth_1___r___nth_1__
                        (clojure.core/nth input__174111_nth_1___r__ 1)]
                       (clojure.core/case
                        input__174111_nth_1___r___nth_0__
                        (:meander.zeta/as)
                        (clojure.core/let
                         [?pattern input__174111_nth_1___r___nth_1__]
                         (clojure.core/let
                          [?env input__174111_nth_2__]
                          (try
                           [(clojure.core/let
                             [!xs__counter
                              (meander.runtime.zeta/iterator !xs)]
                             {:tag :as,
                              :pattern
                              (clojure.core/let
                               [CATA_RESULT__15552__auto__
                                (CATA__FN__174165 [?pattern ?env])]
                               (if
                                (meander.runtime.zeta/fail?
                                 CATA_RESULT__15552__auto__)
                                (throw (meander.runtime.zeta/fail))
                                (clojure.core/nth
                                 CATA_RESULT__15552__auto__
                                 0))),
                              :next
                              (clojure.core/let
                               [CATA_RESULT__15552__auto__
                                (CATA__FN__174165
                                 [?rule-name
                                  (clojure.core/into
                                   []
                                   (clojure.core/vec
                                    (clojure.core/iterator-seq
                                     !xs__counter)))
                                  ?env])]
                               (if
                                (meander.runtime.zeta/fail?
                                 CATA_RESULT__15552__auto__)
                                (throw (meander.runtime.zeta/fail))
                                (clojure.core/nth
                                 CATA_RESULT__15552__auto__
                                 0)))})]
                           (catch
                            java.lang.Exception
                            e__16492__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16492__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16492__auto__))))))
                        (state__175197)))
                      (state__175197)))
                    (state__175197)))
                  (state__175197))))]
              (state__175220))
             (state__175197)))
           (state__175197))
          (state__175197)))
        (state__175197
         []
         (clojure.core/letfn
          [(def__174179
            [arg__174214 ?ns]
            (clojure.core/letfn
             [(state__175222
               []
               (clojure.core/let
                [x__174215 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__174215)
                 (clojure.core/let [?env arg__174214] [?env ?ns])
                 (state__175223))))
              (state__175223
               []
               (if
                (clojure.core/map? arg__174214)
                (clojure.core/let
                 [VAL__174216 (.valAt arg__174214 :aliases)]
                 (if
                  (clojure.core/map? VAL__174216)
                  (clojure.core/let
                   [X__174218 (clojure.core/set VAL__174216)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__174218))
                    (clojure.core/loop
                     [search_space__175224
                      (clojure.core/seq X__174218)]
                     (if
                      (clojure.core/seq search_space__175224)
                      (clojure.core/let
                       [elem__174219
                        (clojure.core/first search_space__175224)
                        result__175225
                        (clojure.core/let
                         [elem__174219_nth_0__
                          (clojure.core/nth elem__174219 0)
                          elem__174219_nth_1__
                          (clojure.core/nth elem__174219 1)]
                         (if
                          (clojure.core/symbol? elem__174219_nth_0__)
                          (clojure.core/let
                           [X__174221
                            (clojure.core/name elem__174219_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__174221)
                            (if
                             (clojure.core/symbol?
                              elem__174219_nth_1__)
                             (clojure.core/let
                              [X__174223
                               (clojure.core/name
                                elem__174219_nth_1__)]
                              (clojure.core/case
                               X__174223
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__174214]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175225)
                        (recur
                         (clojure.core/next search_space__175224))
                        result__175225))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175222)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 3)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)
              input__174111_nth_2__
              (clojure.core/nth input__174111 2)]
             (if
              (clojure.core/let
               [x__13309__auto__ input__174111_nth_0__]
               (clojure.core/case
                x__13309__auto__
                (meander.dev.parse.zeta/parse-seq
                 meander.dev.parse.zeta/parse-string)
                true
                false))
              (clojure.core/let
               [?rule-name input__174111_nth_0__]
               (if
                (clojure.core/vector? input__174111_nth_1__)
                (clojure.core/loop
                 [search_space__175227
                  (meander.match.runtime.epsilon/partitions
                   2
                   input__174111_nth_1__)]
                 (if
                  (clojure.core/seq search_space__175227)
                  (clojure.core/let
                   [input__174111_nth_1___parts__
                    (clojure.core/first search_space__175227)
                    result__175228
                    (clojure.core/let
                     [input__174111_nth_1___l__
                      (clojure.core/nth
                       input__174111_nth_1___parts__
                       0)
                      input__174111_nth_1___r__
                      (clojure.core/nth
                       input__174111_nth_1___parts__
                       1)]
                     (clojure.core/let
                      [!init
                       (clojure.core/vec input__174111_nth_1___l__)]
                      (clojure.core/let
                       [input__174111_nth_1___r___l__
                        (clojure.core/subvec
                         input__174111_nth_1___r__
                         0
                         (clojure.core/min
                          (clojure.core/count
                           input__174111_nth_1___r__)
                          2))]
                       (if
                        (clojure.core/=
                         (clojure.core/count
                          input__174111_nth_1___r___l__)
                         2)
                        (clojure.core/let
                         [input__174111_nth_1___r___r__
                          (clojure.core/subvec
                           input__174111_nth_1___r__
                           2)]
                         (clojure.core/let
                          [input__174111_nth_1___r___l___nth_0__
                           (clojure.core/nth
                            input__174111_nth_1___r___l__
                            0)
                           input__174111_nth_1___r___l___nth_1__
                           (clojure.core/nth
                            input__174111_nth_1___r___l__
                            1)]
                          (if
                           (clojure.core/symbol?
                            input__174111_nth_1___r___l___nth_0__)
                           (clojure.core/let
                            [X__174188
                             (clojure.core/namespace
                              input__174111_nth_1___r___l___nth_0__)]
                            (clojure.core/let
                             [?ns X__174188]
                             (clojure.core/let
                              [X__174190
                               (clojure.core/name
                                input__174111_nth_1___r___l___nth_0__)]
                              (if
                               (clojure.core/string? X__174190)
                               (clojure.core/let
                                [ret__174191
                                 (clojure.core/re-matches
                                  #"&(\d+)"
                                  X__174190)]
                                (if
                                 (clojure.core/some? ret__174191)
                                 (if
                                  (clojure.core/vector? ret__174191)
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count ret__174191)
                                    2)
                                   (clojure.core/let
                                    [ret__174191_nth_1__
                                     (clojure.core/nth ret__174191 1)]
                                    (clojure.core/let
                                     [?n ret__174191_nth_1__]
                                     (clojure.core/let
                                      [?pattern
                                       input__174111_nth_1___r___l___nth_1__]
                                      (clojure.core/let
                                       [?rest
                                        input__174111_nth_1___r___r__]
                                       (clojure.core/let
                                        [x__14249__auto__
                                         (def__174179
                                          input__174111_nth_2__
                                          ?ns)]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          x__14249__auto__)
                                         (meander.runtime.zeta/fail)
                                         (clojure.core/let
                                          [[?env ?ns] x__14249__auto__]
                                          (try
                                           [(clojure.core/let
                                             [!init__counter
                                              (meander.runtime.zeta/iterator
                                               !init)]
                                             (clojure.core/let
                                              [CATA_RESULT__15552__auto__
                                               (CATA__FN__174165
                                                ['meander.dev.parse.zeta/join-args
                                                 (clojure.core/let
                                                  [CATA_RESULT__15552__auto__
                                                   (CATA__FN__174165
                                                    [?rule-name
                                                     (clojure.core/into
                                                      []
                                                      (clojure.core/vec
                                                       (clojure.core/iterator-seq
                                                        !init__counter)))
                                                     ?env])]
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    CATA_RESULT__15552__auto__)
                                                   (throw
                                                    (meander.runtime.zeta/fail))
                                                   (clojure.core/nth
                                                    CATA_RESULT__15552__auto__
                                                    0)))
                                                 (clojure.core/let
                                                  [CATA_RESULT__15552__auto__
                                                   (CATA__FN__174165
                                                    ['meander.dev.parse.zeta/join-args
                                                     {:tag :slice,
                                                      :size
                                                      (Integer. ?n),
                                                      :pattern
                                                      (clojure.core/let
                                                       [CATA_RESULT__15552__auto__
                                                        (CATA__FN__174165
                                                         [?pattern
                                                          ?env])]
                                                       (if
                                                        (meander.runtime.zeta/fail?
                                                         CATA_RESULT__15552__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__15552__auto__
                                                         0)))}
                                                     (clojure.core/let
                                                      [CATA_RESULT__15552__auto__
                                                       (CATA__FN__174165
                                                        [?rule-name
                                                         (clojure.core/into
                                                          []
                                                          ?rest)
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        CATA_RESULT__15552__auto__)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        CATA_RESULT__15552__auto__
                                                        0)))])]
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    CATA_RESULT__15552__auto__)
                                                   (throw
                                                    (meander.runtime.zeta/fail))
                                                   (clojure.core/nth
                                                    CATA_RESULT__15552__auto__
                                                    0)))])]
                                              (if
                                               (meander.runtime.zeta/fail?
                                                CATA_RESULT__15552__auto__)
                                               (throw
                                                (meander.runtime.zeta/fail))
                                               (clojure.core/nth
                                                CATA_RESULT__15552__auto__
                                                0))))]
                                           (catch
                                            java.lang.Exception
                                            e__16492__auto__
                                            (if
                                             (meander.runtime.zeta/fail?
                                              e__16492__auto__)
                                             (meander.runtime.zeta/fail)
                                             (throw
                                              e__16492__auto__)))))))))))
                                   (meander.runtime.zeta/fail))
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail)))
                               (meander.runtime.zeta/fail)))))
                           (meander.runtime.zeta/fail))))
                        (meander.runtime.zeta/fail)))))]
                   (if
                    (meander.runtime.zeta/fail? result__175228)
                    (recur (clojure.core/next search_space__175227))
                    result__175228))
                  (state__175198)))
                (state__175198)))
              (state__175198)))
            (state__175198))
           (state__175198))))
        (state__175198
         []
         (clojure.core/letfn
          [(def__174236
            [arg__174268 ?ns]
            (clojure.core/letfn
             [(state__175230
               []
               (clojure.core/let
                [x__174269 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__174269)
                 (clojure.core/let [?env arg__174268] [?env ?ns])
                 (state__175231))))
              (state__175231
               []
               (if
                (clojure.core/map? arg__174268)
                (clojure.core/let
                 [VAL__174270 (.valAt arg__174268 :aliases)]
                 (if
                  (clojure.core/map? VAL__174270)
                  (clojure.core/let
                   [X__174272 (clojure.core/set VAL__174270)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__174272))
                    (clojure.core/loop
                     [search_space__175232
                      (clojure.core/seq X__174272)]
                     (if
                      (clojure.core/seq search_space__175232)
                      (clojure.core/let
                       [elem__174273
                        (clojure.core/first search_space__175232)
                        result__175233
                        (clojure.core/let
                         [elem__174273_nth_0__
                          (clojure.core/nth elem__174273 0)
                          elem__174273_nth_1__
                          (clojure.core/nth elem__174273 1)]
                         (if
                          (clojure.core/symbol? elem__174273_nth_0__)
                          (clojure.core/let
                           [X__174275
                            (clojure.core/name elem__174273_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__174275)
                            (if
                             (clojure.core/symbol?
                              elem__174273_nth_1__)
                             (clojure.core/let
                              [X__174277
                               (clojure.core/name
                                elem__174273_nth_1__)]
                              (clojure.core/case
                               X__174277
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__174268]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175233)
                        (recur
                         (clojure.core/next search_space__175232))
                        result__175233))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175230)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 3)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)
              input__174111_nth_2__
              (clojure.core/nth input__174111 2)]
             (if
              (clojure.core/=
               input__174111_nth_0__
               'meander.dev.parse.zeta/parse-seq)
              (if
               (clojure.core/vector? input__174111_nth_1__)
               (clojure.core/loop
                [search_space__175235
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__174111_nth_1__)]
                (if
                 (clojure.core/seq search_space__175235)
                 (clojure.core/let
                  [input__174111_nth_1___parts__
                   (clojure.core/first search_space__175235)
                   result__175236
                   (clojure.core/let
                    [input__174111_nth_1___l__
                     (clojure.core/nth input__174111_nth_1___parts__ 0)
                     input__174111_nth_1___r__
                     (clojure.core/nth
                      input__174111_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__174111_nth_1___l__)]
                     (clojure.core/let
                      [input__174111_nth_1___r___l__
                       (clojure.core/subvec
                        input__174111_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__174111_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__174111_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__174111_nth_1___r___r__
                         (clojure.core/subvec
                          input__174111_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__174111_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__174111_nth_1___r___l__
                           0)
                          input__174111_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__174111_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__174111_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__174245
                            (clojure.core/namespace
                             input__174111_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__174245]
                            (clojure.core/let
                             [X__174247
                              (clojure.core/name
                               input__174111_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__174247)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__174247)
                               (clojure.core/let
                                [?pattern
                                 input__174111_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__174111_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__14249__auto__
                                   (def__174236
                                    input__174111_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__14249__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__14249__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__174165
                                          ['meander.dev.parse.zeta/join-args
                                           (clojure.core/let
                                            [CATA_RESULT__15552__auto__
                                             (CATA__FN__174165
                                              ['meander.dev.parse.zeta/parse-seq
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__15552__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__15552__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__15552__auto__
                                             (CATA__FN__174165
                                              ['meander.dev.parse.zeta/join-args
                                               (clojure.core/let
                                                [CATA_RESULT__15552__auto__
                                                 (CATA__FN__174165
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__15552__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__15552__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__15552__auto__
                                                 (CATA__FN__174165
                                                  ['meander.dev.parse.zeta/parse-string
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__15552__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__15552__auto__
                                                  0)))])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__15552__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__15552__auto__
                                              0)))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15552__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15552__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__16492__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16492__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16492__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__175236)
                   (recur (clojure.core/next search_space__175235))
                   result__175236))
                 (state__175199)))
               (state__175199))
              (state__175199)))
            (state__175199))
           (state__175199))))
        (state__175199
         []
         (clojure.core/letfn
          [(def__174290
            [arg__174322 ?ns]
            (clojure.core/letfn
             [(state__175238
               []
               (clojure.core/let
                [x__174323 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__174323)
                 (clojure.core/let [?env arg__174322] [?env ?ns])
                 (state__175239))))
              (state__175239
               []
               (if
                (clojure.core/map? arg__174322)
                (clojure.core/let
                 [VAL__174324 (.valAt arg__174322 :aliases)]
                 (if
                  (clojure.core/map? VAL__174324)
                  (clojure.core/let
                   [X__174326 (clojure.core/set VAL__174324)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__174326))
                    (clojure.core/loop
                     [search_space__175240
                      (clojure.core/seq X__174326)]
                     (if
                      (clojure.core/seq search_space__175240)
                      (clojure.core/let
                       [elem__174327
                        (clojure.core/first search_space__175240)
                        result__175241
                        (clojure.core/let
                         [elem__174327_nth_0__
                          (clojure.core/nth elem__174327 0)
                          elem__174327_nth_1__
                          (clojure.core/nth elem__174327 1)]
                         (if
                          (clojure.core/symbol? elem__174327_nth_0__)
                          (clojure.core/let
                           [X__174329
                            (clojure.core/name elem__174327_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__174329)
                            (if
                             (clojure.core/symbol?
                              elem__174327_nth_1__)
                             (clojure.core/let
                              [X__174331
                               (clojure.core/name
                                elem__174327_nth_1__)]
                              (clojure.core/case
                               X__174331
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__174322]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175241)
                        (recur
                         (clojure.core/next search_space__175240))
                        result__175241))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175238)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 3)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)
              input__174111_nth_2__
              (clojure.core/nth input__174111 2)]
             (if
              (clojure.core/=
               input__174111_nth_0__
               'meander.dev.parse.zeta/parse-string)
              (if
               (clojure.core/vector? input__174111_nth_1__)
               (clojure.core/loop
                [search_space__175243
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__174111_nth_1__)]
                (if
                 (clojure.core/seq search_space__175243)
                 (clojure.core/let
                  [input__174111_nth_1___parts__
                   (clojure.core/first search_space__175243)
                   result__175244
                   (clojure.core/let
                    [input__174111_nth_1___l__
                     (clojure.core/nth input__174111_nth_1___parts__ 0)
                     input__174111_nth_1___r__
                     (clojure.core/nth
                      input__174111_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__174111_nth_1___l__)]
                     (clojure.core/let
                      [input__174111_nth_1___r___l__
                       (clojure.core/subvec
                        input__174111_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__174111_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__174111_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__174111_nth_1___r___r__
                         (clojure.core/subvec
                          input__174111_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__174111_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__174111_nth_1___r___l__
                           0)
                          input__174111_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__174111_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__174111_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__174299
                            (clojure.core/namespace
                             input__174111_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__174299]
                            (clojure.core/let
                             [X__174301
                              (clojure.core/name
                               input__174111_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__174301)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__174301)
                               (clojure.core/let
                                [?pattern
                                 input__174111_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__174111_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__14249__auto__
                                   (def__174290
                                    input__174111_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__14249__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__14249__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__174165
                                          ['meander.dev.parse.zeta/string-join-args
                                           (clojure.core/let
                                            [CATA_RESULT__15552__auto__
                                             (CATA__FN__174165
                                              ['meander.dev.parse.zeta/parse-string
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__15552__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__15552__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__15552__auto__
                                             (CATA__FN__174165
                                              ['meander.dev.parse.zeta/string-join-args
                                               (clojure.core/let
                                                [CATA_RESULT__15552__auto__
                                                 (CATA__FN__174165
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__15552__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__15552__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__15552__auto__
                                                 (CATA__FN__174165
                                                  ['meander.dev.parse.zeta/parse-string
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__15552__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__15552__auto__
                                                  0)))])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__15552__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__15552__auto__
                                              0)))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15552__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15552__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__16492__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16492__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16492__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__175244)
                   (recur (clojure.core/next search_space__175243))
                   result__175244))
                 (state__175200)))
               (state__175200))
              (state__175200)))
            (state__175200))
           (state__175200))))
        (state__175200
         []
         (if
          (clojure.core/vector? input__174111)
          (if
           (clojure.core/= (clojure.core/count input__174111) 3)
           (clojure.core/let
            [input__174111_nth_0__
             (clojure.core/nth input__174111 0)
             input__174111_nth_1__
             (clojure.core/nth input__174111 1)
             input__174111_nth_2__
             (clojure.core/nth input__174111 2)]
            (clojure.core/letfn
             [(state__175246
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/parse-seq)
                (if
                 (clojure.core/vector? input__174111_nth_1__)
                 (clojure.core/let
                  [n__174352
                   (clojure.core/count input__174111_nth_1__)
                   m__174353
                   (clojure.core/max 0 (clojure.core/- n__174352 2))
                   input__174111_nth_1___l__
                   (clojure.core/subvec
                    input__174111_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__174111_nth_1__)
                     m__174353))
                   input__174111_nth_1___r__
                   (clojure.core/subvec
                    input__174111_nth_1__
                    m__174353)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__174111_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__174111_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__174111_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__174111_nth_1___r___nth_0__
                       (clojure.core/nth input__174111_nth_1___r__ 0)
                       input__174111_nth_1___r___nth_1__
                       (clojure.core/nth input__174111_nth_1___r__ 1)]
                      (if
                       (clojure.core/symbol?
                        input__174111_nth_1___r___nth_0__)
                       (clojure.core/let
                        [X__174357
                         (clojure.core/namespace
                          input__174111_nth_1___r___nth_0__)]
                        (clojure.core/let
                         [?ns X__174357]
                         (clojure.core/let
                          [X__174359
                           (clojure.core/name
                            input__174111_nth_1___r___nth_0__)]
                          (if
                           (clojure.core/string? X__174359)
                           (clojure.core/let
                            [ret__174360
                             (clojure.core/re-matches
                              #"&.*"
                              X__174359)]
                            (if
                             (clojure.core/some? ret__174360)
                             (clojure.core/let
                              [?name ret__174360]
                              (clojure.core/let
                               [?pattern
                                input__174111_nth_1___r___nth_1__]
                               (if
                                (clojure.core/map?
                                 input__174111_nth_2__)
                                (clojure.core/let
                                 [VAL__174344
                                  (.valAt
                                   input__174111_nth_2__
                                   :aliases)]
                                 (if
                                  (clojure.core/map? VAL__174344)
                                  (clojure.core/let
                                   [X__174346
                                    (clojure.core/set VAL__174344)]
                                   (if
                                    (clojure.core/<=
                                     1
                                     (clojure.core/count X__174346))
                                    (clojure.core/loop
                                     [search_space__175251
                                      (clojure.core/seq X__174346)]
                                     (if
                                      (clojure.core/seq
                                       search_space__175251)
                                      (clojure.core/let
                                       [elem__174347
                                        (clojure.core/first
                                         search_space__175251)
                                        result__175252
                                        (clojure.core/let
                                         [elem__174347_nth_0__
                                          (clojure.core/nth
                                           elem__174347
                                           0)
                                          elem__174347_nth_1__
                                          (clojure.core/nth
                                           elem__174347
                                           1)]
                                         (if
                                          (clojure.core/symbol?
                                           elem__174347_nth_0__)
                                          (clojure.core/let
                                           [X__174349
                                            (clojure.core/name
                                             elem__174347_nth_0__)]
                                           (if
                                            (clojure.core/=
                                             ?ns
                                             X__174349)
                                            (if
                                             (clojure.core/symbol?
                                              elem__174347_nth_1__)
                                             (clojure.core/let
                                              [X__174351
                                               (clojure.core/name
                                                elem__174347_nth_1__)]
                                              (clojure.core/case
                                               X__174351
                                               ("meander.zeta")
                                               (clojure.core/let
                                                [?env
                                                 input__174111_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15552__auto__
                                                     (CATA__FN__174165
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
                                                      CATA_RESULT__15552__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__15552__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__16492__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__16492__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__16492__auto__)))))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail))
                                            (meander.runtime.zeta/fail)))
                                          (meander.runtime.zeta/fail)))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         result__175252)
                                        (recur
                                         (clojure.core/next
                                          search_space__175251))
                                        result__175252))
                                      (state__175247)))
                                    (state__175247)))
                                  (state__175247)))
                                (state__175247))))
                             (state__175247)))
                           (state__175247)))))
                       (state__175247)))
                     (state__175247)))
                   (state__175247)))
                 (state__175247))
                (state__175247)))
              (state__175247
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__174111_nth_1__)
                 (clojure.core/let
                  [n__174371
                   (clojure.core/count input__174111_nth_1__)
                   m__174372
                   (clojure.core/max 0 (clojure.core/- n__174371 2))
                   input__174111_nth_1___l__
                   (clojure.core/subvec
                    input__174111_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__174111_nth_1__)
                     m__174372))
                   input__174111_nth_1___r__
                   (clojure.core/subvec
                    input__174111_nth_1__
                    m__174372)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__174111_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__174111_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__174111_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__174111_nth_1___r___nth_0__
                       (clojure.core/nth input__174111_nth_1___r__ 0)
                       input__174111_nth_1___r___nth_1__
                       (clojure.core/nth input__174111_nth_1___r__ 1)]
                      (if
                       (clojure.core/symbol?
                        input__174111_nth_1___r___nth_0__)
                       (clojure.core/let
                        [X__174376
                         (clojure.core/namespace
                          input__174111_nth_1___r___nth_0__)]
                        (clojure.core/let
                         [?ns X__174376]
                         (clojure.core/let
                          [X__174378
                           (clojure.core/name
                            input__174111_nth_1___r___nth_0__)]
                          (if
                           (clojure.core/string? X__174378)
                           (clojure.core/let
                            [ret__174379
                             (clojure.core/re-matches
                              #"&.*"
                              X__174378)]
                            (if
                             (clojure.core/some? ret__174379)
                             (clojure.core/let
                              [?name ret__174379]
                              (clojure.core/let
                               [?pattern
                                input__174111_nth_1___r___nth_1__]
                               (if
                                (clojure.core/map?
                                 input__174111_nth_2__)
                                (clojure.core/let
                                 [VAL__174363
                                  (.valAt
                                   input__174111_nth_2__
                                   :aliases)]
                                 (if
                                  (clojure.core/map? VAL__174363)
                                  (clojure.core/let
                                   [X__174365
                                    (clojure.core/set VAL__174363)]
                                   (if
                                    (clojure.core/<=
                                     1
                                     (clojure.core/count X__174365))
                                    (clojure.core/loop
                                     [search_space__175254
                                      (clojure.core/seq X__174365)]
                                     (if
                                      (clojure.core/seq
                                       search_space__175254)
                                      (clojure.core/let
                                       [elem__174366
                                        (clojure.core/first
                                         search_space__175254)
                                        result__175255
                                        (clojure.core/let
                                         [elem__174366_nth_0__
                                          (clojure.core/nth
                                           elem__174366
                                           0)
                                          elem__174366_nth_1__
                                          (clojure.core/nth
                                           elem__174366
                                           1)]
                                         (if
                                          (clojure.core/symbol?
                                           elem__174366_nth_0__)
                                          (clojure.core/let
                                           [X__174368
                                            (clojure.core/name
                                             elem__174366_nth_0__)]
                                           (if
                                            (clojure.core/=
                                             ?ns
                                             X__174368)
                                            (if
                                             (clojure.core/symbol?
                                              elem__174366_nth_1__)
                                             (clojure.core/let
                                              [X__174370
                                               (clojure.core/name
                                                elem__174366_nth_1__)]
                                              (clojure.core/case
                                               X__174370
                                               ("meander.zeta")
                                               (clojure.core/let
                                                [?env
                                                 input__174111_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15552__auto__
                                                     (CATA__FN__174165
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
                                                      CATA_RESULT__15552__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__15552__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__16492__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__16492__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__16492__auto__)))))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail))
                                            (meander.runtime.zeta/fail)))
                                          (meander.runtime.zeta/fail)))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         result__175255)
                                        (recur
                                         (clojure.core/next
                                          search_space__175254))
                                        result__175255))
                                      (state__175248)))
                                    (state__175248)))
                                  (state__175248)))
                                (state__175248))))
                             (state__175248)))
                           (state__175248)))))
                       (state__175248)))
                     (state__175248)))
                   (state__175248)))
                 (state__175248))
                (state__175248)))
              (state__175248
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/parse-seq)
                (if
                 (clojure.core/vector? input__174111_nth_1__)
                 (clojure.core/loop
                  [search_space__175257
                   (meander.match.runtime.epsilon/partitions
                    2
                    input__174111_nth_1__)]
                  (if
                   (clojure.core/seq search_space__175257)
                   (clojure.core/let
                    [input__174111_nth_1___parts__
                     (clojure.core/first search_space__175257)
                     result__175258
                     (clojure.core/let
                      [input__174111_nth_1___l__
                       (clojure.core/nth
                        input__174111_nth_1___parts__
                        0)
                       input__174111_nth_1___r__
                       (clojure.core/nth
                        input__174111_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__174111_nth_1___l__)]
                       (clojure.core/let
                        [input__174111_nth_1___r___l__
                         (clojure.core/subvec
                          input__174111_nth_1___r__
                          0
                          (clojure.core/min
                           (clojure.core/count
                            input__174111_nth_1___r__)
                           1))]
                        (if
                         (clojure.core/=
                          (clojure.core/count
                           input__174111_nth_1___r___l__)
                          1)
                         (clojure.core/let
                          [input__174111_nth_1___r___r__
                           (clojure.core/subvec
                            input__174111_nth_1___r__
                            1)]
                          (if
                           (clojure.core/=
                            input__174111_nth_1___r___l__
                            ['.])
                           (clojure.core/let
                            [?rest input__174111_nth_1___r___r__]
                            (clojure.core/let
                             [?env input__174111_nth_2__]
                             (try
                              [(clojure.core/let
                                [!xs__counter
                                 (meander.runtime.zeta/iterator !xs)]
                                (clojure.core/let
                                 [CATA_RESULT__15552__auto__
                                  (CATA__FN__174165
                                   ['meander.dev.parse.zeta/join-args
                                    (clojure.core/let
                                     [CATA_RESULT__15552__auto__
                                      (CATA__FN__174165
                                       ['meander.dev.parse.zeta/parse-seq
                                        (clojure.core/into
                                         []
                                         (clojure.core/vec
                                          (clojure.core/iterator-seq
                                           !xs__counter)))
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__15552__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__15552__auto__
                                       0)))
                                    (clojure.core/let
                                     [CATA_RESULT__15552__auto__
                                      (CATA__FN__174165
                                       ['meander.dev.parse.zeta/parse-seq
                                        ?rest
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__15552__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__15552__auto__
                                       0)))])]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   CATA_RESULT__15552__auto__)
                                  (throw (meander.runtime.zeta/fail))
                                  (clojure.core/nth
                                   CATA_RESULT__15552__auto__
                                   0))))]
                              (catch
                               java.lang.Exception
                               e__16492__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__16492__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__16492__auto__))))))
                           (meander.runtime.zeta/fail)))
                         (meander.runtime.zeta/fail)))))]
                    (if
                     (meander.runtime.zeta/fail? result__175258)
                     (recur (clojure.core/next search_space__175257))
                     result__175258))
                   (state__175249)))
                 (state__175249))
                (state__175249)))
              (state__175249
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__174111_nth_1__)
                 (clojure.core/loop
                  [search_space__175260
                   (meander.match.runtime.epsilon/partitions
                    2
                    input__174111_nth_1__)]
                  (if
                   (clojure.core/seq search_space__175260)
                   (clojure.core/let
                    [input__174111_nth_1___parts__
                     (clojure.core/first search_space__175260)
                     result__175261
                     (clojure.core/let
                      [input__174111_nth_1___l__
                       (clojure.core/nth
                        input__174111_nth_1___parts__
                        0)
                       input__174111_nth_1___r__
                       (clojure.core/nth
                        input__174111_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__174111_nth_1___l__)]
                       (clojure.core/let
                        [input__174111_nth_1___r___l__
                         (clojure.core/subvec
                          input__174111_nth_1___r__
                          0
                          (clojure.core/min
                           (clojure.core/count
                            input__174111_nth_1___r__)
                           1))]
                        (if
                         (clojure.core/=
                          (clojure.core/count
                           input__174111_nth_1___r___l__)
                          1)
                         (clojure.core/let
                          [input__174111_nth_1___r___r__
                           (clojure.core/subvec
                            input__174111_nth_1___r__
                            1)]
                          (if
                           (clojure.core/=
                            input__174111_nth_1___r___l__
                            ['.])
                           (clojure.core/let
                            [?rest input__174111_nth_1___r___r__]
                            (clojure.core/let
                             [?env input__174111_nth_2__]
                             (try
                              [(clojure.core/let
                                [!xs__counter
                                 (meander.runtime.zeta/iterator !xs)]
                                (clojure.core/let
                                 [CATA_RESULT__15552__auto__
                                  (CATA__FN__174165
                                   ['meander.dev.parse.zeta/string-join-args
                                    (clojure.core/let
                                     [CATA_RESULT__15552__auto__
                                      (CATA__FN__174165
                                       ['meander.dev.parse.zeta/parse-string
                                        (clojure.core/into
                                         []
                                         (clojure.core/vec
                                          (clojure.core/iterator-seq
                                           !xs__counter)))
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__15552__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__15552__auto__
                                       0)))
                                    (clojure.core/let
                                     [CATA_RESULT__15552__auto__
                                      (CATA__FN__174165
                                       ['meander.dev.parse.zeta/parse-string
                                        ?rest
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__15552__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__15552__auto__
                                       0)))])]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   CATA_RESULT__15552__auto__)
                                  (throw (meander.runtime.zeta/fail))
                                  (clojure.core/nth
                                   CATA_RESULT__15552__auto__
                                   0))))]
                              (catch
                               java.lang.Exception
                               e__16492__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__16492__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__16492__auto__))))))
                           (meander.runtime.zeta/fail)))
                         (meander.runtime.zeta/fail)))))]
                    (if
                     (meander.runtime.zeta/fail? result__175261)
                     (recur (clojure.core/next search_space__175260))
                     result__175261))
                   (state__175250)))
                 (state__175250))
                (state__175250)))
              (state__175250
               []
               (if
                (clojure.core/let
                 [x__13309__auto__ input__174111_nth_0__]
                 (clojure.core/case
                  x__13309__auto__
                  (meander.dev.parse.zeta/parse-seq
                   meander.dev.parse.zeta/parse-string)
                  true
                  false))
                (clojure.core/let
                 [?rule-name input__174111_nth_0__]
                 (if
                  (clojure.core/vector? input__174111_nth_1__)
                  (clojure.core/let
                   [input__174111_nth_1___l__
                    (clojure.core/subvec
                     input__174111_nth_1__
                     0
                     (clojure.core/min
                      (clojure.core/count input__174111_nth_1__)
                      1))]
                   (if
                    (clojure.core/=
                     (clojure.core/count input__174111_nth_1___l__)
                     1)
                    (clojure.core/let
                     [input__174111_nth_1___r__
                      (clojure.core/subvec input__174111_nth_1__ 1)]
                     (if
                      (clojure.core/= input__174111_nth_1___l__ ['...])
                      (clojure.core/let
                       [?rest input__174111_nth_1___r__]
                       (clojure.core/let
                        [?env input__174111_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__15552__auto__
                            (CATA__FN__174165 [?rule-name ?rest ?env])]
                           (if
                            (meander.runtime.zeta/fail?
                             CATA_RESULT__15552__auto__)
                            (throw (meander.runtime.zeta/fail))
                            (clojure.core/nth
                             CATA_RESULT__15552__auto__
                             0)))]
                         (catch
                          java.lang.Exception
                          e__16492__auto__
                          (if
                           (meander.runtime.zeta/fail?
                            e__16492__auto__)
                           (meander.runtime.zeta/fail)
                           (throw e__16492__auto__))))))
                      (state__175201)))
                    (state__175201)))
                  (state__175201)))
                (state__175201)))]
             (state__175246)))
           (state__175201))
          (state__175201)))
        (state__175201
         []
         (clojure.core/letfn
          [(def__174396
            [arg__174413]
            (clojure.core/letfn
             [(state__175263
               []
               (if
                (clojure.core/=
                 arg__174413
                 'meander.dev.parse.zeta/parse-string)
                (clojure.core/let
                 [?rule-name arg__174413]
                 (clojure.core/let
                  [x__174414 'meander.dev.parse.zeta/string-star-args]
                  (clojure.core/let
                   [?star-name x__174414]
                   [?star-name ?rule-name])))
                (state__175264)))
              (state__175264
               []
               (if
                (clojure.core/=
                 arg__174413
                 'meander.dev.parse.zeta/parse-seq)
                (clojure.core/let
                 [?rule-name arg__174413]
                 (clojure.core/let
                  [x__174415 'meander.dev.parse.zeta/star-args]
                  (clojure.core/let
                   [?star-name x__174415]
                   [?star-name ?rule-name])))
                (meander.runtime.zeta/fail)))]
             (state__175263)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 3)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)
              input__174111_nth_2__
              (clojure.core/nth input__174111 2)]
             (clojure.core/let
              [x__14249__auto__ (def__174396 input__174111_nth_0__)]
              (if
               (meander.runtime.zeta/fail? x__14249__auto__)
               (state__175202)
               (clojure.core/let
                [[?star-name ?rule-name] x__14249__auto__]
                (if
                 (clojure.core/vector? input__174111_nth_1__)
                 (clojure.core/loop
                  [search_space__175265
                   (meander.match.runtime.epsilon/partitions
                    2
                    input__174111_nth_1__)]
                  (if
                   (clojure.core/seq search_space__175265)
                   (clojure.core/let
                    [input__174111_nth_1___parts__
                     (clojure.core/first search_space__175265)
                     result__175266
                     (clojure.core/let
                      [input__174111_nth_1___l__
                       (clojure.core/nth
                        input__174111_nth_1___parts__
                        0)
                       input__174111_nth_1___r__
                       (clojure.core/nth
                        input__174111_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs []]
                       (clojure.core/let
                        [ret__14413__auto__
                         (meander.runtime.zeta/epsilon-run-star-1
                          input__174111_nth_1___l__
                          [!xs]
                          (clojure.core/fn
                           [[!xs] input__174406]
                           (clojure.core/let
                            [input__174406_nth_0__
                             (clojure.core/nth input__174406 0)]
                            (clojure.core/letfn
                             [(save__174407
                               []
                               (meander.runtime.zeta/fail))
                              (f__175269
                               []
                               (clojure.core/let
                                [!xs
                                 (clojure.core/conj
                                  !xs
                                  input__174406_nth_0__)]
                                [!xs]))]
                             (if
                              (clojure.core/symbol?
                               input__174406_nth_0__)
                              (clojure.core/let
                               [X__174409
                                (clojure.core/namespace
                                 input__174406_nth_0__)]
                               (clojure.core/case
                                X__174409
                                (nil)
                                (clojure.core/let
                                 [X__174411
                                  (clojure.core/name
                                   input__174406_nth_0__)]
                                 (if
                                  (clojure.core/string? X__174411)
                                  (if
                                   (clojure.core/re-matches
                                    #"\.\.(?:\.|\d+)"
                                    X__174411)
                                   (save__174407)
                                   (f__175269))
                                  (f__175269)))
                                (f__175269)))
                              (f__175269)))))
                          (clojure.core/fn
                           [[!xs]]
                           (clojure.core/let
                            [input__174111_nth_1___r___l__
                             (clojure.core/subvec
                              input__174111_nth_1___r__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__174111_nth_1___r__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__174111_nth_1___r___l__)
                              1)
                             (clojure.core/let
                              [input__174111_nth_1___r___r__
                               (clojure.core/subvec
                                input__174111_nth_1___r__
                                1)]
                              (if
                               (clojure.core/=
                                input__174111_nth_1___r___l__
                                ['...])
                               (clojure.core/let
                                [?rest input__174111_nth_1___r___r__]
                                (clojure.core/let
                                 [?env input__174111_nth_2__]
                                 (try
                                  [(clojure.core/let
                                    [!xs__counter
                                     (meander.runtime.zeta/iterator
                                      !xs)]
                                    (clojure.core/let
                                     [CATA_RESULT__15552__auto__
                                      (CATA__FN__174165
                                       [?star-name
                                        (clojure.core/let
                                         [CATA_RESULT__15552__auto__
                                          (CATA__FN__174165
                                           [?rule-name
                                            (clojure.core/into
                                             []
                                             (clojure.core/vec
                                              (clojure.core/iterator-seq
                                               !xs__counter)))
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__15552__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__15552__auto__
                                           0)))
                                        (clojure.core/let
                                         [CATA_RESULT__15552__auto__
                                          (CATA__FN__174165
                                           [?rule-name ?rest ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__15552__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__15552__auto__
                                           0)))])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__15552__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__15552__auto__
                                       0))))]
                                  (catch
                                   java.lang.Exception
                                   e__16492__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__16492__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__16492__auto__))))))
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail)))))]
                        (if
                         (meander.runtime.zeta/fail?
                          ret__14413__auto__)
                         (meander.runtime.zeta/fail)
                         ret__14413__auto__))))]
                    (if
                     (meander.runtime.zeta/fail? result__175266)
                     (recur (clojure.core/next search_space__175265))
                     result__175266))
                   (state__175202)))
                 (state__175202))))))
            (state__175202))
           (state__175202))))
        (state__175202
         []
         (if
          (clojure.core/vector? input__174111)
          (if
           (clojure.core/= (clojure.core/count input__174111) 3)
           (clojure.core/let
            [input__174111_nth_0__
             (clojure.core/nth input__174111 0)
             input__174111_nth_1__
             (clojure.core/nth input__174111 1)
             input__174111_nth_2__
             (clojure.core/nth input__174111 2)]
            (clojure.core/letfn
             [(state__175270
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/star-args)
                (if
                 (clojure.core/map? input__174111_nth_1__)
                 (clojure.core/let
                  [VAL__174427 (.valAt input__174111_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__174427
                   (:cat)
                   (clojure.core/let
                    [VAL__174428
                     (.valAt input__174111_nth_1__ :sequence)]
                    (if
                     (clojure.core/vector? VAL__174428)
                     (if
                      (clojure.core/=
                       (clojure.core/count VAL__174428)
                       1)
                      (clojure.core/let
                       [VAL__174428_nth_0__
                        (clojure.core/nth VAL__174428 0)]
                       (if
                        (clojure.core/map? VAL__174428_nth_0__)
                        (clojure.core/let
                         [VAL__174433
                          (.valAt VAL__174428_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__174433
                          (:memory-variable)
                          (clojure.core/let
                           [?memory-variable VAL__174428_nth_0__]
                           (clojure.core/let
                            [VAL__174429
                             (.valAt input__174111_nth_1__ :next)]
                            (if
                             (clojure.core/map? VAL__174429)
                             (clojure.core/let
                              [VAL__174430 (.valAt VAL__174429 :tag)]
                              (clojure.core/case
                               VAL__174430
                               (:empty)
                               (clojure.core/let
                                [?next input__174111_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [CATA_RESULT__15552__auto__
                                    (CATA__FN__174165
                                     ['meander.dev.parse.zeta/join-args
                                      {:tag :into,
                                       :memory-variable
                                       ?memory-variable}
                                      ?next])]
                                   (if
                                    (meander.runtime.zeta/fail?
                                     CATA_RESULT__15552__auto__)
                                    (throw (meander.runtime.zeta/fail))
                                    (clojure.core/nth
                                     CATA_RESULT__15552__auto__
                                     0)))]
                                 (catch
                                  java.lang.Exception
                                  e__16492__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__16492__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__16492__auto__)))))
                               (state__175271)))
                             (state__175271))))
                          (state__175271)))
                        (state__175271)))
                      (state__175271))
                     (state__175271)))
                   (state__175271)))
                 (state__175271))
                (state__175271)))
              (state__175271
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/star-args)
                (clojure.core/let
                 [?pattern input__174111_nth_1__]
                 (clojure.core/let
                  [?next input__174111_nth_2__]
                  (try
                   [{:tag :star, :pattern ?pattern, :next ?next}]
                   (catch
                    java.lang.Exception
                    e__16492__auto__
                    (if
                     (meander.runtime.zeta/fail? e__16492__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__16492__auto__))))))
                (state__175272)))
              (state__175272
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/string-star-args)
                (if
                 (clojure.core/map? input__174111_nth_1__)
                 (clojure.core/let
                  [VAL__174438 (.valAt input__174111_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__174438
                   (:string-cat)
                   (clojure.core/let
                    [VAL__174439
                     (.valAt input__174111_nth_1__ :sequence)]
                    (if
                     (clojure.core/vector? VAL__174439)
                     (if
                      (clojure.core/=
                       (clojure.core/count VAL__174439)
                       1)
                      (clojure.core/let
                       [VAL__174439_nth_0__
                        (clojure.core/nth VAL__174439 0)]
                       (if
                        (clojure.core/map? VAL__174439_nth_0__)
                        (clojure.core/let
                         [VAL__174444
                          (.valAt VAL__174439_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__174444
                          (:memory-variable)
                          (clojure.core/let
                           [?memory-variable VAL__174439_nth_0__]
                           (clojure.core/let
                            [VAL__174440
                             (.valAt input__174111_nth_1__ :next)]
                            (if
                             (clojure.core/map? VAL__174440)
                             (clojure.core/let
                              [VAL__174441 (.valAt VAL__174440 :tag)]
                              (clojure.core/case
                               VAL__174441
                               (:empty)
                               (clojure.core/let
                                [?next input__174111_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [CATA_RESULT__15552__auto__
                                    (CATA__FN__174165
                                     ['meander.dev.parse.zeta/string-join-args
                                      {:tag :into,
                                       :memory-variable
                                       ?memory-variable}
                                      ?next])]
                                   (if
                                    (meander.runtime.zeta/fail?
                                     CATA_RESULT__15552__auto__)
                                    (throw (meander.runtime.zeta/fail))
                                    (clojure.core/nth
                                     CATA_RESULT__15552__auto__
                                     0)))]
                                 (catch
                                  java.lang.Exception
                                  e__16492__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__16492__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__16492__auto__)))))
                               (state__175273)))
                             (state__175273))))
                          (state__175273)))
                        (state__175273)))
                      (state__175273))
                     (state__175273)))
                   (state__175273)))
                 (state__175273))
                (state__175273)))
              (state__175273
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/string-star-args)
                (clojure.core/let
                 [?pattern input__174111_nth_1__]
                 (clojure.core/let
                  [?next input__174111_nth_2__]
                  (try
                   [{:tag :string-star,
                     :pattern ?pattern,
                     :next ?next}]
                   (catch
                    java.lang.Exception
                    e__16492__auto__
                    (if
                     (meander.runtime.zeta/fail? e__16492__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__16492__auto__))))))
                (state__175274)))
              (state__175274
               []
               (if
                (clojure.core/let
                 [x__13309__auto__ input__174111_nth_0__]
                 (clojure.core/case
                  x__13309__auto__
                  (meander.dev.parse.zeta/parse-seq
                   meander.dev.parse.zeta/parse-string)
                  true
                  false))
                (clojure.core/letfn
                 [(state__175295
                   []
                   (if
                    (clojure.core/vector? input__174111_nth_1__)
                    (clojure.core/let
                     [input__174111_nth_1___l__
                      (clojure.core/subvec
                       input__174111_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__174111_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__174111_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__174111_nth_1___r__
                        (clojure.core/subvec input__174111_nth_1__ 1)]
                       (clojure.core/let
                        [input__174111_nth_1___l___nth_0__
                         (clojure.core/nth
                          input__174111_nth_1___l__
                          0)]
                        (if
                         (clojure.core/symbol?
                          input__174111_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__174452
                           (clojure.core/namespace
                            input__174111_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__174452
                           (nil)
                           (clojure.core/let
                            [X__174454
                             (clojure.core/name
                              input__174111_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__174454)
                             (clojure.core/let
                              [ret__174455
                               (clojure.core/re-matches
                                #"\.\.(\d+)"
                                X__174454)]
                              (if
                               (clojure.core/some? ret__174455)
                               (if
                                (clojure.core/vector? ret__174455)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__174455)
                                  2)
                                 (clojure.core/let
                                  [ret__174455_nth_1__
                                   (clojure.core/nth ret__174455 1)]
                                  (clojure.core/let
                                   [?n ret__174455_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__174111_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__174111_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__174111_nth_2__]
                                      (try
                                       [{:tag :syntax-error,
                                         :message
                                         "The n or more operator ..N must be preceeded by at least one pattern"}]
                                       (catch
                                        java.lang.Exception
                                        e__16492__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__16492__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__16492__auto__)))))))))
                                 (state__175296))
                                (state__175296))
                               (state__175296)))
                             (state__175296)))
                           (state__175296)))
                         (state__175296))))
                      (state__175296)))
                    (state__175296)))
                  (state__175296
                   []
                   (clojure.core/let
                    [?rule-name input__174111_nth_0__]
                    (if
                     (clojure.core/vector? input__174111_nth_1__)
                     (clojure.core/loop
                      [search_space__175301
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__174111_nth_1__)]
                      (if
                       (clojure.core/seq search_space__175301)
                       (clojure.core/let
                        [input__174111_nth_1___parts__
                         (clojure.core/first search_space__175301)
                         result__175302
                         (clojure.core/let
                          [input__174111_nth_1___l__
                           (clojure.core/nth
                            input__174111_nth_1___parts__
                            0)
                           input__174111_nth_1___r__
                           (clojure.core/nth
                            input__174111_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__14413__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__174111_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__174471]
                               (clojure.core/let
                                [input__174471_nth_0__
                                 (clojure.core/nth input__174471 0)]
                                (clojure.core/letfn
                                 [(save__174472
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__175305
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__174471_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__174471_nth_0__)
                                  (clojure.core/let
                                   [X__174474
                                    (clojure.core/namespace
                                     input__174471_nth_0__)]
                                   (clojure.core/case
                                    X__174474
                                    (nil)
                                    (clojure.core/let
                                     [X__174476
                                      (clojure.core/name
                                       input__174471_nth_0__)]
                                     (if
                                      (clojure.core/string? X__174476)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__174476)
                                       (save__174472)
                                       (f__175305))
                                      (f__175305)))
                                    (f__175305)))
                                  (f__175305)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__174111_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__174111_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__174111_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__174111_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__174111_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__174111_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__174111_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__174111_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__174111_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__174465
                                      (clojure.core/namespace
                                       input__174111_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__174465
                                      (nil)
                                      (clojure.core/let
                                       [X__174467
                                        (clojure.core/name
                                         input__174111_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string?
                                         X__174467)
                                        (clojure.core/let
                                         [ret__174468
                                          (clojure.core/re-matches
                                           #"\.\.(\d+)"
                                           X__174467)]
                                         (if
                                          (clojure.core/some?
                                           ret__174468)
                                          (if
                                           (clojure.core/vector?
                                            ret__174468)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__174468)
                                             2)
                                            (clojure.core/let
                                             [ret__174468_nth_1__
                                              (clojure.core/nth
                                               ret__174468
                                               1)]
                                             (clojure.core/let
                                              [?n ret__174468_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__174111_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__174111_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   {:tag :plus,
                                                    :n (Integer. ?n),
                                                    :pattern
                                                    (clojure.core/let
                                                     [CATA_RESULT__15552__auto__
                                                      (CATA__FN__174165
                                                       [?rule-name
                                                        (clojure.core/into
                                                         []
                                                         (clojure.core/vec
                                                          (clojure.core/iterator-seq
                                                           !xs__counter)))
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__15552__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__15552__auto__
                                                       0))),
                                                    :next
                                                    (clojure.core/let
                                                     [CATA_RESULT__15552__auto__
                                                      (CATA__FN__174165
                                                       [?rule-name
                                                        ?rest
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__15552__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__15552__auto__
                                                       0)))})]
                                                 (catch
                                                  java.lang.Exception
                                                  e__16492__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__16492__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__16492__auto__))))))))
                                            (meander.runtime.zeta/fail))
                                           (meander.runtime.zeta/fail))
                                          (meander.runtime.zeta/fail)))
                                        (meander.runtime.zeta/fail)))
                                      (meander.runtime.zeta/fail)))
                                    (meander.runtime.zeta/fail))))
                                 (meander.runtime.zeta/fail)))))]
                            (if
                             (meander.runtime.zeta/fail?
                              ret__14413__auto__)
                             (meander.runtime.zeta/fail)
                             ret__14413__auto__))))]
                        (if
                         (meander.runtime.zeta/fail? result__175302)
                         (recur
                          (clojure.core/next search_space__175301))
                         result__175302))
                       (state__175297)))
                     (state__175297))))
                  (state__175297
                   []
                   (if
                    (clojure.core/vector? input__174111_nth_1__)
                    (clojure.core/let
                     [input__174111_nth_1___l__
                      (clojure.core/subvec
                       input__174111_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__174111_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__174111_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__174111_nth_1___r__
                        (clojure.core/subvec input__174111_nth_1__ 1)]
                       (clojure.core/let
                        [input__174111_nth_1___l___nth_0__
                         (clojure.core/nth
                          input__174111_nth_1___l__
                          0)]
                        (if
                         (clojure.core/symbol?
                          input__174111_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__174483
                           (clojure.core/namespace
                            input__174111_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__174483
                           (nil)
                           (clojure.core/let
                            [X__174485
                             (clojure.core/name
                              input__174111_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__174485)
                             (clojure.core/let
                              [ret__174486
                               (clojure.core/re-matches
                                #"\.\.(\?.+)"
                                X__174485)]
                              (if
                               (clojure.core/some? ret__174486)
                               (if
                                (clojure.core/vector? ret__174486)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__174486)
                                  2)
                                 (clojure.core/let
                                  [ret__174486_nth_1__
                                   (clojure.core/nth ret__174486 1)]
                                  (clojure.core/let
                                   [?n ret__174486_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__174111_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__174111_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__174111_nth_2__]
                                      (try
                                       [{:tag :syntax-error,
                                         :message
                                         "The ?n or more operator ..?n must be preceeded by at least one pattern"}]
                                       (catch
                                        java.lang.Exception
                                        e__16492__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__16492__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__16492__auto__)))))))))
                                 (state__175298))
                                (state__175298))
                               (state__175298)))
                             (state__175298)))
                           (state__175298)))
                         (state__175298))))
                      (state__175298)))
                    (state__175298)))
                  (state__175298
                   []
                   (clojure.core/let
                    [?rule-name input__174111_nth_0__]
                    (if
                     (clojure.core/vector? input__174111_nth_1__)
                     (clojure.core/loop
                      [search_space__175306
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__174111_nth_1__)]
                      (if
                       (clojure.core/seq search_space__175306)
                       (clojure.core/let
                        [input__174111_nth_1___parts__
                         (clojure.core/first search_space__175306)
                         result__175307
                         (clojure.core/let
                          [input__174111_nth_1___l__
                           (clojure.core/nth
                            input__174111_nth_1___parts__
                            0)
                           input__174111_nth_1___r__
                           (clojure.core/nth
                            input__174111_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__14413__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__174111_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__174502]
                               (clojure.core/let
                                [input__174502_nth_0__
                                 (clojure.core/nth input__174502 0)]
                                (clojure.core/letfn
                                 [(save__174503
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__175310
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__174502_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__174502_nth_0__)
                                  (clojure.core/let
                                   [X__174505
                                    (clojure.core/namespace
                                     input__174502_nth_0__)]
                                   (clojure.core/case
                                    X__174505
                                    (nil)
                                    (clojure.core/let
                                     [X__174507
                                      (clojure.core/name
                                       input__174502_nth_0__)]
                                     (if
                                      (clojure.core/string? X__174507)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__174507)
                                       (save__174503)
                                       (f__175310))
                                      (f__175310)))
                                    (f__175310)))
                                  (f__175310)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__174111_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__174111_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__174111_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__174111_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__174111_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__174111_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__174111_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__174111_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__174111_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__174496
                                      (clojure.core/namespace
                                       input__174111_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__174496
                                      (nil)
                                      (clojure.core/let
                                       [X__174498
                                        (clojure.core/name
                                         input__174111_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string?
                                         X__174498)
                                        (clojure.core/let
                                         [ret__174499
                                          (clojure.core/re-matches
                                           #"\.\.(\?.+)"
                                           X__174498)]
                                         (if
                                          (clojure.core/some?
                                           ret__174499)
                                          (if
                                           (clojure.core/vector?
                                            ret__174499)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__174499)
                                             2)
                                            (clojure.core/let
                                             [ret__174499_nth_1__
                                              (clojure.core/nth
                                               ret__174499
                                               1)]
                                             (clojure.core/let
                                              [?n ret__174499_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__174111_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__174111_nth_2__]
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
                                                     [CATA_RESULT__15552__auto__
                                                      (CATA__FN__174165
                                                       [?rule-name
                                                        (clojure.core/into
                                                         []
                                                         (clojure.core/vec
                                                          (clojure.core/iterator-seq
                                                           !xs__counter)))
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__15552__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__15552__auto__
                                                       0))),
                                                    :next
                                                    (clojure.core/let
                                                     [CATA_RESULT__15552__auto__
                                                      (CATA__FN__174165
                                                       [?rule-name
                                                        ?rest
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__15552__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__15552__auto__
                                                       0)))})]
                                                 (catch
                                                  java.lang.Exception
                                                  e__16492__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__16492__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__16492__auto__))))))))
                                            (meander.runtime.zeta/fail))
                                           (meander.runtime.zeta/fail))
                                          (meander.runtime.zeta/fail)))
                                        (meander.runtime.zeta/fail)))
                                      (meander.runtime.zeta/fail)))
                                    (meander.runtime.zeta/fail))))
                                 (meander.runtime.zeta/fail)))))]
                            (if
                             (meander.runtime.zeta/fail?
                              ret__14413__auto__)
                             (meander.runtime.zeta/fail)
                             ret__14413__auto__))))]
                        (if
                         (meander.runtime.zeta/fail? result__175307)
                         (recur
                          (clojure.core/next search_space__175306))
                         result__175307))
                       (state__175299)))
                     (state__175299))))
                  (state__175299
                   []
                   (if
                    (clojure.core/vector? input__174111_nth_1__)
                    (clojure.core/let
                     [input__174111_nth_1___l__
                      (clojure.core/subvec
                       input__174111_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__174111_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__174111_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__174111_nth_1___r__
                        (clojure.core/subvec input__174111_nth_1__ 1)]
                       (clojure.core/let
                        [input__174111_nth_1___l___nth_0__
                         (clojure.core/nth
                          input__174111_nth_1___l__
                          0)]
                        (if
                         (clojure.core/symbol?
                          input__174111_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__174514
                           (clojure.core/namespace
                            input__174111_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__174514
                           (nil)
                           (clojure.core/let
                            [X__174516
                             (clojure.core/name
                              input__174111_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__174516)
                             (clojure.core/let
                              [ret__174517
                               (clojure.core/re-matches
                                #"\.\.(!.+)"
                                X__174516)]
                              (if
                               (clojure.core/some? ret__174517)
                               (if
                                (clojure.core/vector? ret__174517)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__174517)
                                  2)
                                 (clojure.core/let
                                  [ret__174517_nth_1__
                                   (clojure.core/nth ret__174517 1)]
                                  (clojure.core/let
                                   [?n ret__174517_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__174111_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__174111_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__174111_nth_2__]
                                      (try
                                       [{:tag :syntax-error,
                                         :message
                                         "The operator ..!n must be preceeded by at least one pattern"}]
                                       (catch
                                        java.lang.Exception
                                        e__16492__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__16492__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__16492__auto__)))))))))
                                 (state__175300))
                                (state__175300))
                               (state__175300)))
                             (state__175300)))
                           (state__175300)))
                         (state__175300))))
                      (state__175300)))
                    (state__175300)))
                  (state__175300
                   []
                   (clojure.core/let
                    [?rule-name input__174111_nth_0__]
                    (if
                     (clojure.core/vector? input__174111_nth_1__)
                     (clojure.core/loop
                      [search_space__175311
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__174111_nth_1__)]
                      (if
                       (clojure.core/seq search_space__175311)
                       (clojure.core/let
                        [input__174111_nth_1___parts__
                         (clojure.core/first search_space__175311)
                         result__175312
                         (clojure.core/let
                          [input__174111_nth_1___l__
                           (clojure.core/nth
                            input__174111_nth_1___parts__
                            0)
                           input__174111_nth_1___r__
                           (clojure.core/nth
                            input__174111_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__14413__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__174111_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__174533]
                               (clojure.core/let
                                [input__174533_nth_0__
                                 (clojure.core/nth input__174533 0)]
                                (clojure.core/letfn
                                 [(save__174534
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__175315
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__174533_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__174533_nth_0__)
                                  (clojure.core/let
                                   [X__174536
                                    (clojure.core/namespace
                                     input__174533_nth_0__)]
                                   (clojure.core/case
                                    X__174536
                                    (nil)
                                    (clojure.core/let
                                     [X__174538
                                      (clojure.core/name
                                       input__174533_nth_0__)]
                                     (if
                                      (clojure.core/string? X__174538)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__174538)
                                       (save__174534)
                                       (f__175315))
                                      (f__175315)))
                                    (f__175315)))
                                  (f__175315)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__174111_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__174111_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__174111_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__174111_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__174111_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__174111_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__174111_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__174111_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__174111_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__174527
                                      (clojure.core/namespace
                                       input__174111_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__174527
                                      (nil)
                                      (clojure.core/let
                                       [X__174529
                                        (clojure.core/name
                                         input__174111_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string?
                                         X__174529)
                                        (clojure.core/let
                                         [ret__174530
                                          (clojure.core/re-matches
                                           #"\.\.(\!.+)"
                                           X__174529)]
                                         (if
                                          (clojure.core/some?
                                           ret__174530)
                                          (if
                                           (clojure.core/vector?
                                            ret__174530)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__174530)
                                             2)
                                            (clojure.core/let
                                             [ret__174530_nth_1__
                                              (clojure.core/nth
                                               ret__174530
                                               1)]
                                             (clojure.core/let
                                              [?n ret__174530_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__174111_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__174111_nth_2__]
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
                                                     [CATA_RESULT__15552__auto__
                                                      (CATA__FN__174165
                                                       [?rule-name
                                                        (clojure.core/into
                                                         []
                                                         (clojure.core/vec
                                                          (clojure.core/iterator-seq
                                                           !xs__counter)))
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__15552__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__15552__auto__
                                                       0))),
                                                    :next
                                                    (clojure.core/let
                                                     [CATA_RESULT__15552__auto__
                                                      (CATA__FN__174165
                                                       [?rule-name
                                                        ?rest
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__15552__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__15552__auto__
                                                       0)))})]
                                                 (catch
                                                  java.lang.Exception
                                                  e__16492__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__16492__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__16492__auto__))))))))
                                            (meander.runtime.zeta/fail))
                                           (meander.runtime.zeta/fail))
                                          (meander.runtime.zeta/fail)))
                                        (meander.runtime.zeta/fail)))
                                      (meander.runtime.zeta/fail)))
                                    (meander.runtime.zeta/fail))))
                                 (meander.runtime.zeta/fail)))))]
                            (if
                             (meander.runtime.zeta/fail?
                              ret__14413__auto__)
                             (meander.runtime.zeta/fail)
                             ret__14413__auto__))))]
                        (if
                         (meander.runtime.zeta/fail? result__175312)
                         (recur
                          (clojure.core/next search_space__175311))
                         result__175312))
                       (state__175275)))
                     (state__175275))))]
                 (state__175295))
                (state__175275)))
              (state__175275
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/parse-seq)
                (if
                 (clojure.core/vector? input__174111_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__174111_nth_1__)]
                  (clojure.core/let
                   [?env input__174111_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__15552__auto__
                        (CATA__FN__174165
                         ['meander.dev.parse.zeta/cat-args
                          (clojure.core/let
                           [return__174166 (clojure.core/transient [])]
                           (clojure.core/while
                            (clojure.core/and (.hasNext !xs__counter))
                            (clojure.core/conj!
                             return__174166
                             (clojure.core/let
                              [CATA_RESULT__15552__auto__
                               (CATA__FN__174165
                                [(if
                                  (.hasNext !xs__counter)
                                  (.next !xs__counter))
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__15552__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__15552__auto__
                                0)))))
                           (clojure.core/persistent! return__174166))
                          {:tag :empty}])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__15552__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__15552__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__16492__auto__
                     (if
                      (meander.runtime.zeta/fail? e__16492__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__16492__auto__))))))
                 (state__175276))
                (state__175276)))
              (state__175276
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__174111_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__174111_nth_1__)]
                  (clojure.core/let
                   [?env input__174111_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__15552__auto__
                        (CATA__FN__174165
                         ['meander.dev.parse.zeta/string-cat-args
                          (clojure.core/let
                           [return__174167 (clojure.core/transient [])]
                           (clojure.core/while
                            (clojure.core/and (.hasNext !xs__counter))
                            (clojure.core/conj!
                             return__174167
                             (clojure.core/let
                              [CATA_RESULT__15552__auto__
                               (CATA__FN__174165
                                [(if
                                  (.hasNext !xs__counter)
                                  (.next !xs__counter))
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__15552__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__15552__auto__
                                0)))))
                           (clojure.core/persistent! return__174167))
                          {:tag :empty}])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__15552__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__15552__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__16492__auto__
                     (if
                      (meander.runtime.zeta/fail? e__16492__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__16492__auto__))))))
                 (state__175277))
                (state__175277)))
              (state__175277
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/cat-args)
                (if
                 (clojure.core/vector? input__174111_nth_1__)
                 (clojure.core/let
                  [!forms []]
                  (clojure.core/let
                   [ret__14413__auto__
                    (meander.runtime.zeta/epsilon-run-star-1
                     input__174111_nth_1__
                     [!forms]
                     (clojure.core/fn
                      [[!forms] input__174553]
                      (clojure.core/let
                       [input__174553_nth_0__
                        (clojure.core/nth input__174553 0)]
                       (if
                        (clojure.core/map? input__174553_nth_0__)
                        (clojure.core/let
                         [VAL__174554
                          (.valAt input__174553_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__174554
                          (:literal)
                          (clojure.core/let
                           [VAL__174555
                            (.valAt input__174553_nth_0__ :form)]
                           (clojure.core/let
                            [!forms
                             (clojure.core/conj !forms VAL__174555)]
                            [!forms]))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail))))
                     (clojure.core/fn
                      [[!forms]]
                      (if
                       (clojure.core/map? input__174111_nth_2__)
                       (clojure.core/let
                        [VAL__174550
                         (.valAt input__174111_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__174550
                         (:empty)
                         (try
                          [{:tag :literal,
                            :form (clojure.core/into [] !forms)}]
                          (catch
                           java.lang.Exception
                           e__16492__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__16492__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__16492__auto__))))
                         (state__175278)))
                       (state__175278))))]
                   (if
                    (meander.runtime.zeta/fail? ret__14413__auto__)
                    (state__175278)
                    ret__14413__auto__)))
                 (state__175278))
                (state__175278)))
              (state__175278
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/cat-args)
                (clojure.core/let
                 [?sequence input__174111_nth_1__]
                 (clojure.core/let
                  [?next input__174111_nth_2__]
                  (try
                   [{:tag :cat, :sequence ?sequence, :next ?next}]
                   (catch
                    java.lang.Exception
                    e__16492__auto__
                    (if
                     (meander.runtime.zeta/fail? e__16492__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__16492__auto__))))))
                (state__175279)))
              (state__175279
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (if
                 (clojure.core/map? input__174111_nth_1__)
                 (clojure.core/let
                  [VAL__174561 (.valAt input__174111_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__174561
                   (:cat)
                   (clojure.core/let
                    [VAL__174562
                     (.valAt input__174111_nth_1__ :sequence)]
                    (clojure.core/let
                     [?sequence VAL__174562]
                     (clojure.core/let
                      [VAL__174563
                       (.valAt input__174111_nth_1__ :next)]
                      (if
                       (clojure.core/map? VAL__174563)
                       (clojure.core/let
                        [VAL__174564 (.valAt VAL__174563 :tag)]
                        (clojure.core/case
                         VAL__174564
                         (:empty)
                         (clojure.core/let
                          [?right input__174111_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__15552__auto__
                              (CATA__FN__174165
                               ['meander.dev.parse.zeta/cat-args
                                ?sequence
                                ?right])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__15552__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__15552__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__16492__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16492__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16492__auto__)))))
                         (state__175280)))
                       (state__175280)))))
                   (state__175280)))
                 (state__175280))
                (state__175280)))
              (state__175280
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (clojure.core/let
                 [?left input__174111_nth_1__]
                 (if
                  (clojure.core/map? input__174111_nth_2__)
                  (clojure.core/let
                   [VAL__174567 (.valAt input__174111_nth_2__ :tag)]
                   (clojure.core/case
                    VAL__174567
                    (:empty)
                    (try
                     [?left]
                     (catch
                      java.lang.Exception
                      e__16492__auto__
                      (if
                       (meander.runtime.zeta/fail? e__16492__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__16492__auto__))))
                    (state__175281)))
                  (state__175281)))
                (state__175281)))
              (state__175281
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (if
                 (clojure.core/map? input__174111_nth_1__)
                 (clojure.core/let
                  [VAL__174570 (.valAt input__174111_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__174570
                   (:empty)
                   (clojure.core/let
                    [?right input__174111_nth_2__]
                    (try
                     [?right]
                     (catch
                      java.lang.Exception
                      e__16492__auto__
                      (if
                       (meander.runtime.zeta/fail? e__16492__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__16492__auto__)))))
                   (state__175282)))
                 (state__175282))
                (state__175282)))
              (state__175282
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (clojure.core/let
                 [?left input__174111_nth_1__]
                 (clojure.core/let
                  [?right input__174111_nth_2__]
                  (try
                   [{:tag :join, :left ?left, :right ?right}]
                   (catch
                    java.lang.Exception
                    e__16492__auto__
                    (if
                     (meander.runtime.zeta/fail? e__16492__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__16492__auto__))))))
                (state__175283)))
              (state__175283
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (if
                 (clojure.core/vector? input__174111_nth_1__)
                 (clojure.core/let
                  [!forms []]
                  (clojure.core/let
                   [ret__14413__auto__
                    (meander.runtime.zeta/epsilon-run-star-1
                     input__174111_nth_1__
                     [!forms]
                     (clojure.core/fn
                      [[!forms] input__174578]
                      (clojure.core/let
                       [input__174578_nth_0__
                        (clojure.core/nth input__174578 0)]
                       (if
                        (clojure.core/map? input__174578_nth_0__)
                        (clojure.core/let
                         [VAL__174579
                          (.valAt input__174578_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__174579
                          (:literal)
                          (clojure.core/let
                           [VAL__174580
                            (.valAt input__174578_nth_0__ :type)]
                           (if
                            (clojure.core/let
                             [x__13309__auto__ VAL__174580]
                             (clojure.core/case
                              x__13309__auto__
                              (:string :char)
                              true
                              false))
                            (clojure.core/let
                             [VAL__174581
                              (.valAt input__174578_nth_0__ :form)]
                             (clojure.core/let
                              [!forms
                               (clojure.core/conj !forms VAL__174581)]
                              [!forms]))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail))))
                     (clojure.core/fn
                      [[!forms]]
                      (if
                       (clojure.core/map? input__174111_nth_2__)
                       (clojure.core/let
                        [VAL__174575
                         (.valAt input__174111_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__174575
                         (:empty)
                         (try
                          [{:tag :literal,
                            :type :string,
                            :form
                            (clojure.string/join
                             (clojure.core/into [] !forms))}]
                          (catch
                           java.lang.Exception
                           e__16492__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__16492__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__16492__auto__))))
                         (state__175284)))
                       (state__175284))))]
                   (if
                    (meander.runtime.zeta/fail? ret__14413__auto__)
                    (state__175284)
                    ret__14413__auto__)))
                 (state__175284))
                (state__175284)))
              (state__175284
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (if
                 (clojure.core/vector? input__174111_nth_1__)
                 (clojure.core/let
                  [input__174111_nth_1___l__
                   (clojure.core/subvec
                    input__174111_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__174111_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__174111_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__174111_nth_1___r__
                     (clojure.core/subvec input__174111_nth_1__ 1)]
                    (clojure.core/let
                     [input__174111_nth_1___l___nth_0__
                      (clojure.core/nth input__174111_nth_1___l__ 0)]
                     (if
                      (clojure.core/map?
                       input__174111_nth_1___l___nth_0__)
                      (clojure.core/let
                       [VAL__174587
                        (.valAt
                         input__174111_nth_1___l___nth_0__
                         :tag)]
                       (clojure.core/case
                        VAL__174587
                        (:literal)
                        (clojure.core/let
                         [VAL__174588
                          (.valAt
                           input__174111_nth_1___l___nth_0__
                           :type)]
                         (clojure.core/case
                          VAL__174588
                          (:string)
                          (clojure.core/let
                           [?ast input__174111_nth_1___l___nth_0__]
                           (clojure.core/let
                            [?rest input__174111_nth_1___r__]
                            (clojure.core/let
                             [?next input__174111_nth_2__]
                             (try
                              [(clojure.core/let
                                [CATA_RESULT__15552__auto__
                                 (CATA__FN__174165
                                  ['meander.dev.parse.zeta/string-join-args
                                   ?ast
                                   (clojure.core/let
                                    [CATA_RESULT__15552__auto__
                                     (CATA__FN__174165
                                      ['meander.dev.parse.zeta/string-cat-args
                                       ?rest
                                       ?next])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__15552__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__15552__auto__
                                      0)))])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__15552__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__15552__auto__
                                  0)))]
                              (catch
                               java.lang.Exception
                               e__16492__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__16492__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__16492__auto__)))))))
                          (state__175285)))
                        (state__175285)))
                      (state__175285))))
                   (state__175285)))
                 (state__175285))
                (state__175285)))
              (state__175285
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (clojure.core/let
                 [?sequence input__174111_nth_1__]
                 (clojure.core/let
                  [?next input__174111_nth_2__]
                  (try
                   [{:tag :string-cat,
                     :sequence ?sequence,
                     :next ?next}]
                   (catch
                    java.lang.Exception
                    e__16492__auto__
                    (if
                     (meander.runtime.zeta/fail? e__16492__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__16492__auto__))))))
                (state__175286)))
              (state__175286
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__174111_nth_1__)
                 (clojure.core/let
                  [VAL__174593 (.valAt input__174111_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__174593
                   (:literal)
                   (clojure.core/let
                    [VAL__174594 (.valAt input__174111_nth_1__ :type)]
                    (clojure.core/case
                     VAL__174594
                     (:string)
                     (clojure.core/let
                      [VAL__174595
                       (.valAt input__174111_nth_1__ :form)]
                      (clojure.core/let
                       [?form-1 VAL__174595]
                       (if
                        (clojure.core/map? input__174111_nth_2__)
                        (clojure.core/let
                         [VAL__174596
                          (.valAt input__174111_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__174596
                          (:string-join)
                          (clojure.core/let
                           [VAL__174597
                            (.valAt input__174111_nth_2__ :left)]
                           (if
                            (clojure.core/map? VAL__174597)
                            (clojure.core/let
                             [VAL__174598 (.valAt VAL__174597 :tag)]
                             (clojure.core/case
                              VAL__174598
                              (:literal)
                              (clojure.core/let
                               [VAL__174599 (.valAt VAL__174597 :type)]
                               (clojure.core/case
                                VAL__174599
                                (:string)
                                (clojure.core/let
                                 [VAL__174600
                                  (.valAt VAL__174597 :form)]
                                 (clojure.core/let
                                  [?form-2 VAL__174600]
                                  (clojure.core/let
                                   [VAL__174601
                                    (.valAt
                                     input__174111_nth_2__
                                     :right)]
                                   (clojure.core/let
                                    [?right VAL__174601]
                                    (try
                                     [(clojure.core/let
                                       [CATA_RESULT__15552__auto__
                                        (CATA__FN__174165
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
                                         CATA_RESULT__15552__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__15552__auto__
                                         0)))]
                                     (catch
                                      java.lang.Exception
                                      e__16492__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16492__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16492__auto__))))))))
                                (state__175287)))
                              (state__175287)))
                            (state__175287)))
                          (state__175287)))
                        (state__175287))))
                     (state__175287)))
                   (state__175287)))
                 (state__175287))
                (state__175287)))
              (state__175287
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__174111_nth_1__)
                 (clojure.core/let
                  [VAL__174604 (.valAt input__174111_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__174604
                   (:literal)
                   (clojure.core/let
                    [VAL__174605 (.valAt input__174111_nth_1__ :type)]
                    (clojure.core/case
                     VAL__174605
                     (:string)
                     (clojure.core/let
                      [?ast input__174111_nth_1__]
                      (if
                       (clojure.core/map? input__174111_nth_2__)
                       (clojure.core/let
                        [VAL__174606
                         (.valAt input__174111_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__174606
                         (:string-cat)
                         (clojure.core/let
                          [VAL__174607
                           (.valAt input__174111_nth_2__ :sequence)]
                          (clojure.core/let
                           [?sequence VAL__174607]
                           (clojure.core/let
                            [VAL__174608
                             (.valAt input__174111_nth_2__ :next)]
                            (clojure.core/let
                             [?next VAL__174608]
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
                               e__16492__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__16492__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__16492__auto__))))))))
                         (state__175288)))
                       (state__175288)))
                     (state__175288)))
                   (state__175288)))
                 (state__175288))
                (state__175288)))
              (state__175288
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__174111_nth_1__)
                 (clojure.core/let
                  [VAL__174611 (.valAt input__174111_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__174611
                   (:string-cat)
                   (clojure.core/let
                    [VAL__174612
                     (.valAt input__174111_nth_1__ :sequence)]
                    (clojure.core/let
                     [?sequence VAL__174612]
                     (clojure.core/let
                      [VAL__174613
                       (.valAt input__174111_nth_1__ :next)]
                      (if
                       (clojure.core/map? VAL__174613)
                       (clojure.core/let
                        [VAL__174614 (.valAt VAL__174613 :tag)]
                        (clojure.core/case
                         VAL__174614
                         (:empty)
                         (clojure.core/let
                          [?right input__174111_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__15552__auto__
                              (CATA__FN__174165
                               ['meander.dev.parse.zeta/string-cat-args
                                ?sequence
                                ?right])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__15552__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__15552__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__16492__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16492__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16492__auto__)))))
                         (state__175289)))
                       (state__175289)))))
                   (state__175289)))
                 (state__175289))
                (state__175289)))
              (state__175289
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__174111_nth_1__)
                 (clojure.core/let
                  [VAL__174617 (.valAt input__174111_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__174617
                   (:string-star)
                   (clojure.core/let
                    [VAL__174618
                     (.valAt input__174111_nth_1__ :pattern)]
                    (clojure.core/let
                     [?pattern VAL__174618]
                     (clojure.core/let
                      [VAL__174619
                       (.valAt input__174111_nth_1__ :next)]
                      (if
                       (clojure.core/map? VAL__174619)
                       (clojure.core/let
                        [VAL__174620 (.valAt VAL__174619 :tag)]
                        (clojure.core/case
                         VAL__174620
                         (:empty)
                         (clojure.core/let
                          [?right input__174111_nth_2__]
                          (try
                           [{:tag :string-star,
                             :pattern ?pattern,
                             :next ?right}]
                           (catch
                            java.lang.Exception
                            e__16492__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16492__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16492__auto__)))))
                         (state__175290)))
                       (state__175290)))))
                   (state__175290)))
                 (state__175290))
                (state__175290)))
              (state__175290
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__174111_nth_1__)
                 (clojure.core/let
                  [VAL__174623 (.valAt input__174111_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__174623
                   (:string-join)
                   (clojure.core/let
                    [VAL__174624 (.valAt input__174111_nth_1__ :left)]
                    (clojure.core/let
                     [?left VAL__174624]
                     (clojure.core/let
                      [VAL__174625
                       (.valAt input__174111_nth_1__ :right)]
                      (clojure.core/let
                       [?right-1 VAL__174625]
                       (clojure.core/let
                        [?right-2 input__174111_nth_2__]
                        (try
                         [{:tag :string-join,
                           :left ?left,
                           :right
                           (clojure.core/let
                            [CATA_RESULT__15552__auto__
                             (CATA__FN__174165
                              ['meander.dev.parse.zeta/string-join-args
                               ?right-1
                               ?right-2])]
                            (if
                             (meander.runtime.zeta/fail?
                              CATA_RESULT__15552__auto__)
                             (throw (meander.runtime.zeta/fail))
                             (clojure.core/nth
                              CATA_RESULT__15552__auto__
                              0)))}]
                         (catch
                          java.lang.Exception
                          e__16492__auto__
                          (if
                           (meander.runtime.zeta/fail?
                            e__16492__auto__)
                           (meander.runtime.zeta/fail)
                           (throw e__16492__auto__)))))))))
                   (state__175291)))
                 (state__175291))
                (state__175291)))
              (state__175291
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (clojure.core/let
                 [?left input__174111_nth_1__]
                 (if
                  (clojure.core/map? input__174111_nth_2__)
                  (clojure.core/let
                   [VAL__174628 (.valAt input__174111_nth_2__ :tag)]
                   (clojure.core/case
                    VAL__174628
                    (:empty)
                    (try
                     [?left]
                     (catch
                      java.lang.Exception
                      e__16492__auto__
                      (if
                       (meander.runtime.zeta/fail? e__16492__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__16492__auto__))))
                    (state__175292)))
                  (state__175292)))
                (state__175292)))
              (state__175292
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__174111_nth_1__)
                 (clojure.core/let
                  [VAL__174631 (.valAt input__174111_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__174631
                   (:empty)
                   (clojure.core/let
                    [?right input__174111_nth_2__]
                    (try
                     [?right]
                     (catch
                      java.lang.Exception
                      e__16492__auto__
                      (if
                       (meander.runtime.zeta/fail? e__16492__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__16492__auto__)))))
                   (state__175293)))
                 (state__175293))
                (state__175293)))
              (state__175293
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (clojure.core/let
                 [?left input__174111_nth_1__]
                 (clojure.core/let
                  [?right input__174111_nth_2__]
                  (try
                   [{:tag :string-join, :left ?left, :right ?right}]
                   (catch
                    java.lang.Exception
                    e__16492__auto__
                    (if
                     (meander.runtime.zeta/fail? e__16492__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__16492__auto__))))))
                (state__175294)))
              (state__175294
               []
               (if
                (clojure.core/=
                 input__174111_nth_0__
                 'meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__174111_nth_1__)
                 (clojure.core/let
                  [VAL__174636
                   (.valAt input__174111_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__174636]
                   (clojure.core/let
                    [X__174638
                     ((clojure.core/fn
                       [m__13316__auto__]
                       (clojure.core/dissoc
                        m__13316__auto__
                        :meander.zeta/as))
                      input__174111_nth_1__)]
                    (clojure.core/let
                     [?rest X__174638]
                     (clojure.core/letfn
                      [(save__174639 [] (state__175203))
                       (f__175318
                        []
                        (clojure.core/let
                         [?env input__174111_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__15552__auto__
                              (CATA__FN__174165 [?pattern ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__15552__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__15552__auto__
                               0))),
                            :next
                            (clojure.core/let
                             [CATA_RESULT__15552__auto__
                              (CATA__FN__174165 [?rest ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__15552__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__15552__auto__
                               0)))}]
                          (catch
                           java.lang.Exception
                           e__16492__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__16492__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__16492__auto__))))))]
                      (if
                       (clojure.core/= ?rest input__174111_nth_1__)
                       (save__174639)
                       (f__175318)))))))
                 (state__175203))
                (state__175203)))]
             (state__175270)))
           (state__175203))
          (state__175203)))
        (state__175203
         []
         (clojure.core/letfn
          [(def__174642
            [arg__174675 ?ns]
            (clojure.core/letfn
             [(state__175319
               []
               (clojure.core/let
                [x__174676 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__174676)
                 (clojure.core/let [?env arg__174675] [?env ?ns])
                 (state__175320))))
              (state__175320
               []
               (if
                (clojure.core/map? arg__174675)
                (clojure.core/let
                 [VAL__174677 (.valAt arg__174675 :aliases)]
                 (if
                  (clojure.core/map? VAL__174677)
                  (clojure.core/let
                   [X__174679 (clojure.core/set VAL__174677)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__174679))
                    (clojure.core/loop
                     [search_space__175321
                      (clojure.core/seq X__174679)]
                     (if
                      (clojure.core/seq search_space__175321)
                      (clojure.core/let
                       [elem__174680
                        (clojure.core/first search_space__175321)
                        result__175322
                        (clojure.core/let
                         [elem__174680_nth_0__
                          (clojure.core/nth elem__174680 0)
                          elem__174680_nth_1__
                          (clojure.core/nth elem__174680 1)]
                         (if
                          (clojure.core/symbol? elem__174680_nth_0__)
                          (clojure.core/let
                           [X__174682
                            (clojure.core/name elem__174680_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__174682)
                            (if
                             (clojure.core/symbol?
                              elem__174680_nth_1__)
                             (clojure.core/let
                              [X__174684
                               (clojure.core/name
                                elem__174680_nth_1__)]
                              (clojure.core/case
                               X__174684
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__174675]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175322)
                        (recur
                         (clojure.core/next search_space__175321))
                        result__175322))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175319)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 3)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)
              input__174111_nth_2__
              (clojure.core/nth input__174111 2)]
             (if
              (clojure.core/=
               input__174111_nth_0__
               'meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__174111_nth_1__)
               (clojure.core/let
                [X__174647 (clojure.core/set input__174111_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__174647))
                 (clojure.core/loop
                  [search_space__175324 (clojure.core/seq X__174647)]
                  (if
                   (clojure.core/seq search_space__175324)
                   (clojure.core/let
                    [elem__174648
                     (clojure.core/first search_space__175324)
                     result__175325
                     (clojure.core/let
                      [elem__174648_nth_0__
                       (clojure.core/nth elem__174648 0)
                       elem__174648_nth_1__
                       (clojure.core/nth elem__174648 1)]
                      (clojure.core/let
                       [*m__174138 elem__174648_nth_0__]
                       (if
                        (clojure.core/symbol? elem__174648_nth_0__)
                        (clojure.core/let
                         [X__174650
                          (clojure.core/namespace
                           elem__174648_nth_0__)]
                         (clojure.core/let
                          [?ns X__174650]
                          (clojure.core/let
                           [X__174652
                            (clojure.core/name elem__174648_nth_0__)]
                           (if
                            (clojure.core/string? X__174652)
                            (if
                             (clojure.core/re-matches #"&.*" X__174652)
                             (clojure.core/let
                              [?pattern elem__174648_nth_1__]
                              (clojure.core/let
                               [X__174654
                                ((clojure.core/fn
                                  [m__13316__auto__]
                                  (clojure.core/dissoc
                                   m__13316__auto__
                                   *m__174138))
                                 input__174111_nth_1__)]
                               (clojure.core/let
                                [?rest X__174654]
                                (clojure.core/let
                                 [x__14249__auto__
                                  (def__174642
                                   input__174111_nth_2__
                                   ?ns)]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   x__14249__auto__)
                                  (meander.runtime.zeta/fail)
                                  (clojure.core/let
                                   [[?env ?ns] x__14249__auto__]
                                   (try
                                    [{:tag :rest-map,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__15552__auto__
                                        (CATA__FN__174165
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__15552__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__15552__auto__
                                         0))),
                                      :next
                                      (clojure.core/let
                                       [CATA_RESULT__15552__auto__
                                        (CATA__FN__174165
                                         ['meander.dev.parse.zeta/parse-entries
                                          ?rest
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__15552__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__15552__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__16492__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__16492__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__16492__auto__))))))))))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? result__175325)
                     (recur (clojure.core/next search_space__175324))
                     result__175325))
                   (state__175204)))
                 (state__175204)))
               (state__175204))
              (state__175204)))
            (state__175204))
           (state__175204))))
        (state__175204
         []
         (if
          (clojure.core/vector? input__174111)
          (clojure.core/letfn
           [(state__175327
             []
             (if
              (clojure.core/= (clojure.core/count input__174111) 3)
              (clojure.core/let
               [input__174111_nth_0__
                (clojure.core/nth input__174111 0)
                input__174111_nth_1__
                (clojure.core/nth input__174111 1)
                input__174111_nth_2__
                (clojure.core/nth input__174111 2)]
               (clojure.core/letfn
                [(state__175330
                  []
                  (if
                   (clojure.core/=
                    input__174111_nth_0__
                    'meander.dev.parse.zeta/parse-entries)
                   (if
                    (clojure.core/map? input__174111_nth_1__)
                    (clojure.core/let
                     [X__174698
                      (clojure.core/set input__174111_nth_1__)]
                     (if
                      (clojure.core/<=
                       1
                       (clojure.core/count X__174698))
                      (clojure.core/loop
                       [search_space__175336
                        (clojure.core/seq X__174698)]
                       (if
                        (clojure.core/seq search_space__175336)
                        (clojure.core/let
                         [elem__174699
                          (clojure.core/first search_space__175336)
                          result__175337
                          (clojure.core/let
                           [elem__174699_nth_0__
                            (clojure.core/nth elem__174699 0)
                            elem__174699_nth_1__
                            (clojure.core/nth elem__174699 1)]
                           (clojure.core/let
                            [?key-pattern elem__174699_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__174699_nth_1__]
                             (clojure.core/let
                              [X__174701
                               ((clojure.core/fn
                                 [m__13316__auto__]
                                 (clojure.core/dissoc
                                  m__13316__auto__
                                  ?key-pattern))
                                input__174111_nth_1__)]
                              (clojure.core/let
                               [?rest X__174701]
                               (clojure.core/let
                                [?env input__174111_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__15552__auto__
                                     (CATA__FN__174165
                                      [?key-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__15552__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__15552__auto__
                                      0))),
                                   :val-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__15552__auto__
                                     (CATA__FN__174165
                                      [?val-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__15552__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__15552__auto__
                                      0))),
                                   :next
                                   (clojure.core/let
                                    [CATA_RESULT__15552__auto__
                                     (CATA__FN__174165
                                      ['meander.dev.parse.zeta/parse-entries
                                       ?rest
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__15552__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__15552__auto__
                                      0)))}]
                                 (catch
                                  java.lang.Exception
                                  e__16492__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__16492__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__16492__auto__))))))))))]
                         (if
                          (meander.runtime.zeta/fail? result__175337)
                          (recur
                           (clojure.core/next search_space__175336))
                          result__175337))
                        (state__175331)))
                      (state__175331)))
                    (state__175331))
                   (state__175331)))
                 (state__175331
                  []
                  (if
                   (clojure.core/=
                    input__174111_nth_0__
                    'meander.dev.parse.zeta/parse-entries)
                   (if
                    (clojure.core/map? input__174111_nth_1__)
                    (clojure.core/let
                     [?env input__174111_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__16492__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16492__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16492__auto__)))))
                    (state__175332))
                   (state__175332)))
                 (state__175332
                  []
                  (if
                   (clojure.core/=
                    input__174111_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__174111_nth_1__)
                    (clojure.core/case
                     input__174111_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__174111_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__16492__auto__
                        (if
                         (meander.runtime.zeta/fail? e__16492__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__16492__auto__)))))
                     (state__175333))
                    (state__175333))
                   (state__175333)))
                 (state__175333
                  []
                  (if
                   (clojure.core/=
                    input__174111_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__174111_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__174111_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__174111_nth_2__]
                      (try
                       [[{:tag :error,
                          :message
                          "meander.zeta/with expects an even number of bindings"}]]
                       (catch
                        java.lang.Exception
                        e__16492__auto__
                        (if
                         (meander.runtime.zeta/fail? e__16492__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__16492__auto__)))))
                     (state__175334))
                    (state__175334))
                   (state__175334)))
                 (state__175334
                  []
                  (if
                   (clojure.core/=
                    input__174111_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__174111_nth_1__)
                    (clojure.core/let
                     [input__174111_nth_1___l__
                      (clojure.core/subvec
                       input__174111_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__174111_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__174111_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__174111_nth_1___r__
                        (clojure.core/subvec input__174111_nth_1__ 2)]
                       (clojure.core/let
                        [input__174111_nth_1___l___nth_0__
                         (clojure.core/nth input__174111_nth_1___l__ 0)
                         input__174111_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__174111_nth_1___l__
                          1)]
                        (if
                         (clojure.core/symbol?
                          input__174111_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__174715
                           (clojure.core/namespace
                            input__174111_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__174717
                            (clojure.core/name
                             input__174111_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__174717)
                            (if
                             (clojure.core/re-matches #"%.+" X__174717)
                             (clojure.core/let
                              [?symbol
                               input__174111_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__174111_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__174111_nth_1___r__]
                                (clojure.core/let
                                 [?env input__174111_nth_2__]
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
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__174165
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15552__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15552__auto__
                                          0)))})
                                     (clojure.core/let
                                      [CATA_RESULT__15552__auto__
                                       (CATA__FN__174165
                                        ['meander.dev.parse.zeta/parse-with-bindings
                                         ?rest
                                         ?env])]
                                      (if
                                       (meander.runtime.zeta/fail?
                                        CATA_RESULT__15552__auto__)
                                       (throw
                                        (meander.runtime.zeta/fail))
                                       (clojure.core/nth
                                        CATA_RESULT__15552__auto__
                                        0)))))]
                                  (catch
                                   java.lang.Exception
                                   e__16492__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__16492__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__16492__auto__))))))))
                             (state__175335))
                            (state__175335))))
                         (state__175335))))
                      (state__175335)))
                    (state__175335))
                   (state__175335)))
                 (state__175335
                  []
                  (if
                   (clojure.core/=
                    input__174111_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__174111_nth_1__)
                    (clojure.core/let
                     [input__174111_nth_1___l__
                      (clojure.core/subvec
                       input__174111_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__174111_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__174111_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__174111_nth_1___r__
                        (clojure.core/subvec input__174111_nth_1__ 2)]
                       (clojure.core/let
                        [input__174111_nth_1___l___nth_0__
                         (clojure.core/nth input__174111_nth_1___l__ 0)
                         input__174111_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__174111_nth_1___l__
                          1)]
                        (clojure.core/let
                         [?x input__174111_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__174111_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__174111_nth_1___r__]
                           (clojure.core/let
                            [?env input__174111_nth_2__]
                            (try
                             [[{:tag :error,
                                :message
                                "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]
                             (catch
                              java.lang.Exception
                              e__16492__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__16492__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__16492__auto__))))))))))
                      (state__175328)))
                    (state__175328))
                   (state__175328)))]
                (state__175330)))
              (state__175328)))
            (state__175328
             []
             (if
              (clojure.core/= (clojure.core/count input__174111) 2)
              (clojure.core/let
               [input__174111_nth_0__
                (clojure.core/nth input__174111 0)
                input__174111_nth_1__
                (clojure.core/nth input__174111 1)]
               (if
                (clojure.core/vector? input__174111_nth_0__)
                (clojure.core/let
                 [?sequence input__174111_nth_0__]
                 (clojure.core/let
                  [?env input__174111_nth_1__]
                  (try
                   [(clojure.core/let
                     [CATA_RESULT__15552__auto__
                      (CATA__FN__174165
                       ['meander.dev.parse.zeta/vector-args
                        (clojure.core/let
                         [CATA_RESULT__15552__auto__
                          (CATA__FN__174165
                           ['meander.dev.parse.zeta/parse-seq
                            ?sequence
                            ?env])]
                         (if
                          (meander.runtime.zeta/fail?
                           CATA_RESULT__15552__auto__)
                          (throw (meander.runtime.zeta/fail))
                          (clojure.core/nth
                           CATA_RESULT__15552__auto__
                           0)))
                        ?sequence])]
                     (if
                      (meander.runtime.zeta/fail?
                       CATA_RESULT__15552__auto__)
                      (throw (meander.runtime.zeta/fail))
                      (clojure.core/nth
                       CATA_RESULT__15552__auto__
                       0)))]
                   (catch
                    java.lang.Exception
                    e__16492__auto__
                    (if
                     (meander.runtime.zeta/fail? e__16492__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__16492__auto__))))))
                (state__175329)))
              (state__175329)))
            (state__175329
             []
             (if
              (clojure.core/= (clojure.core/count input__174111) 3)
              (clojure.core/let
               [input__174111_nth_0__
                (clojure.core/nth input__174111 0)
                input__174111_nth_1__
                (clojure.core/nth input__174111 1)]
               (clojure.core/letfn
                [(state__175339
                  []
                  (if
                   (clojure.core/=
                    input__174111_nth_0__
                    'meander.dev.parse.zeta/vector-args)
                   (if
                    (clojure.core/map? input__174111_nth_1__)
                    (clojure.core/let
                     [VAL__174728 (.valAt input__174111_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__174728
                      (:literal)
                      (clojure.core/let
                       [?literal input__174111_nth_1__]
                       (try
                        [?literal]
                        (catch
                         java.lang.Exception
                         e__16492__auto__
                         (if
                          (meander.runtime.zeta/fail? e__16492__auto__)
                          (meander.runtime.zeta/fail)
                          (throw e__16492__auto__)))))
                      (state__175340)))
                    (state__175340))
                   (state__175340)))
                 (state__175340
                  []
                  (clojure.core/let
                   [input__174111_nth_2__
                    (clojure.core/nth input__174111 2)]
                   (if
                    (clojure.core/=
                     input__174111_nth_0__
                     'meander.dev.parse.zeta/vector-args)
                    (clojure.core/let
                     [?next input__174111_nth_1__]
                     (clojure.core/let
                      [?sequence input__174111_nth_2__]
                      (try
                       [{:tag :vector, :next ?next, :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__16492__auto__
                        (if
                         (meander.runtime.zeta/fail? e__16492__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__16492__auto__))))))
                    (state__175205))))]
                (state__175339)))
              (state__175205)))]
           (state__175327))
          (state__175205)))
        (state__175205
         []
         (clojure.core/letfn
          [(def__174732
            [arg__174755 ?__174113]
            (clojure.core/letfn
             [(state__175341
               []
               (clojure.core/let
                [x__174756 "meander.zeta"]
                (if
                 (clojure.core/= ?__174113 x__174756)
                 [?__174113]
                 (state__175342))))
              (state__175342
               []
               (if
                (clojure.core/map? arg__174755)
                (clojure.core/let
                 [VAL__174757 (.valAt arg__174755 :aliases)]
                 (if
                  (clojure.core/map? VAL__174757)
                  (clojure.core/let
                   [X__174759 (clojure.core/set VAL__174757)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__174759))
                    (clojure.core/loop
                     [search_space__175343
                      (clojure.core/seq X__174759)]
                     (if
                      (clojure.core/seq search_space__175343)
                      (clojure.core/let
                       [elem__174760
                        (clojure.core/first search_space__175343)
                        result__175344
                        (clojure.core/let
                         [elem__174760_nth_0__
                          (clojure.core/nth elem__174760 0)
                          elem__174760_nth_1__
                          (clojure.core/nth elem__174760 1)]
                         (if
                          (clojure.core/symbol? elem__174760_nth_0__)
                          (clojure.core/let
                           [X__174762
                            (clojure.core/name elem__174760_nth_0__)]
                           (if
                            (clojure.core/= ?__174113 X__174762)
                            (if
                             (clojure.core/symbol?
                              elem__174760_nth_1__)
                             (clojure.core/let
                              [X__174764
                               (clojure.core/name
                                elem__174760_nth_1__)]
                              (clojure.core/case
                               X__174764
                               ("meander.zeta")
                               [?__174113]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175344)
                        (recur
                         (clojure.core/next search_space__175343))
                        result__175344))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175341)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 2)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)]
             (if
              (clojure.core/seq? input__174111_nth_0__)
              (clojure.core/let
               [input__174111_nth_0___l__
                (clojure.core/take 1 input__174111_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__174111_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__174111_nth_0___r__
                  (clojure.core/drop 1 input__174111_nth_0__)]
                 (clojure.core/let
                  [input__174111_nth_0___l___nth_0__
                   (clojure.core/nth input__174111_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__174111_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__174742
                     (clojure.core/namespace
                      input__174111_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__174113 X__174742]
                     (clojure.core/let
                      [X__174744
                       (clojure.core/name
                        input__174111_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__174744
                       ("with")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__174732 input__174111_nth_1__ ?__174113)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__175206)
                         (clojure.core/let
                          [[?__174113] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__174111)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__174111)
                             2)
                            (clojure.core/let
                             [input__174111_nth_0__
                              (clojure.core/nth input__174111 0)
                              input__174111_nth_1__
                              (clojure.core/nth input__174111 1)]
                             (if
                              (clojure.core/seq? input__174111_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__174111_nth_0__)
                                3)
                               (clojure.core/let
                                [input__174111_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  1)
                                 input__174111_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__174111_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__174111_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__174111_nth_0__]
                                   (clojure.core/let
                                    [?env input__174111_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__15552__auto__
                                          (CATA__FN__174165
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?bindings
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__15552__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__15552__auto__
                                           0)))},
                                       :body
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__174165
                                          [?body ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15552__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15552__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__16492__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16492__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16492__auto__)))))))))
                               (state__175206))
                              (state__175206)))
                            (state__175206))
                           (state__175206)))))
                       (state__175206)))))
                   (state__175206))))
                (state__175206)))
              (state__175206)))
            (state__175206))
           (state__175206))))
        (state__175206
         []
         (clojure.core/letfn
          [(def__174766
            [arg__174789 ?__174114]
            (clojure.core/letfn
             [(state__175346
               []
               (clojure.core/let
                [x__174790 "meander.zeta"]
                (if
                 (clojure.core/= ?__174114 x__174790)
                 [?__174114]
                 (state__175347))))
              (state__175347
               []
               (if
                (clojure.core/map? arg__174789)
                (clojure.core/let
                 [VAL__174791 (.valAt arg__174789 :aliases)]
                 (if
                  (clojure.core/map? VAL__174791)
                  (clojure.core/let
                   [X__174793 (clojure.core/set VAL__174791)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__174793))
                    (clojure.core/loop
                     [search_space__175348
                      (clojure.core/seq X__174793)]
                     (if
                      (clojure.core/seq search_space__175348)
                      (clojure.core/let
                       [elem__174794
                        (clojure.core/first search_space__175348)
                        result__175349
                        (clojure.core/let
                         [elem__174794_nth_0__
                          (clojure.core/nth elem__174794 0)
                          elem__174794_nth_1__
                          (clojure.core/nth elem__174794 1)]
                         (if
                          (clojure.core/symbol? elem__174794_nth_0__)
                          (clojure.core/let
                           [X__174796
                            (clojure.core/name elem__174794_nth_0__)]
                           (if
                            (clojure.core/= ?__174114 X__174796)
                            (if
                             (clojure.core/symbol?
                              elem__174794_nth_1__)
                             (clojure.core/let
                              [X__174798
                               (clojure.core/name
                                elem__174794_nth_1__)]
                              (clojure.core/case
                               X__174798
                               ("meander.zeta")
                               [?__174114]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175349)
                        (recur
                         (clojure.core/next search_space__175348))
                        result__175349))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175346)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 2)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)]
             (if
              (clojure.core/seq? input__174111_nth_0__)
              (clojure.core/let
               [input__174111_nth_0___l__
                (clojure.core/take 1 input__174111_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__174111_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__174111_nth_0___r__
                  (clojure.core/drop 1 input__174111_nth_0__)]
                 (clojure.core/let
                  [input__174111_nth_0___l___nth_0__
                   (clojure.core/nth input__174111_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__174111_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__174776
                     (clojure.core/namespace
                      input__174111_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__174114 X__174776]
                     (clojure.core/let
                      [X__174778
                       (clojure.core/name
                        input__174111_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__174778
                       ("apply")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__174766 input__174111_nth_1__ ?__174114)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__175207)
                         (clojure.core/let
                          [[?__174114] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__174111)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__174111)
                             2)
                            (clojure.core/let
                             [input__174111_nth_0__
                              (clojure.core/nth input__174111 0)
                              input__174111_nth_1__
                              (clojure.core/nth input__174111 1)]
                             (if
                              (clojure.core/seq? input__174111_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__174111_nth_0__)
                                3)
                               (clojure.core/let
                                [input__174111_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  1)
                                 input__174111_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__174111_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__174111_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__174111_nth_0__]
                                   (clojure.core/let
                                    [?env input__174111_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__174165
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15552__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15552__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__16492__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16492__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16492__auto__)))))))))
                               (state__175207))
                              (state__175207)))
                            (state__175207))
                           (state__175207)))))
                       (state__175207)))))
                   (state__175207))))
                (state__175207)))
              (state__175207)))
            (state__175207))
           (state__175207))))
        (state__175207
         []
         (clojure.core/letfn
          [(def__174800
            [arg__174823 ?__174115]
            (clojure.core/letfn
             [(state__175351
               []
               (clojure.core/let
                [x__174824 "meander.zeta"]
                (if
                 (clojure.core/= ?__174115 x__174824)
                 [?__174115]
                 (state__175352))))
              (state__175352
               []
               (if
                (clojure.core/map? arg__174823)
                (clojure.core/let
                 [VAL__174825 (.valAt arg__174823 :aliases)]
                 (if
                  (clojure.core/map? VAL__174825)
                  (clojure.core/let
                   [X__174827 (clojure.core/set VAL__174825)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__174827))
                    (clojure.core/loop
                     [search_space__175353
                      (clojure.core/seq X__174827)]
                     (if
                      (clojure.core/seq search_space__175353)
                      (clojure.core/let
                       [elem__174828
                        (clojure.core/first search_space__175353)
                        result__175354
                        (clojure.core/let
                         [elem__174828_nth_0__
                          (clojure.core/nth elem__174828 0)
                          elem__174828_nth_1__
                          (clojure.core/nth elem__174828 1)]
                         (if
                          (clojure.core/symbol? elem__174828_nth_0__)
                          (clojure.core/let
                           [X__174830
                            (clojure.core/name elem__174828_nth_0__)]
                           (if
                            (clojure.core/= ?__174115 X__174830)
                            (if
                             (clojure.core/symbol?
                              elem__174828_nth_1__)
                             (clojure.core/let
                              [X__174832
                               (clojure.core/name
                                elem__174828_nth_1__)]
                              (clojure.core/case
                               X__174832
                               ("meander.zeta")
                               [?__174115]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175354)
                        (recur
                         (clojure.core/next search_space__175353))
                        result__175354))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175351)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 2)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)]
             (if
              (clojure.core/seq? input__174111_nth_0__)
              (clojure.core/let
               [input__174111_nth_0___l__
                (clojure.core/take 1 input__174111_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__174111_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__174111_nth_0___r__
                  (clojure.core/drop 1 input__174111_nth_0__)]
                 (clojure.core/let
                  [input__174111_nth_0___l___nth_0__
                   (clojure.core/nth input__174111_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__174111_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__174810
                     (clojure.core/namespace
                      input__174111_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__174115 X__174810]
                     (clojure.core/let
                      [X__174812
                       (clojure.core/name
                        input__174111_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__174812
                       ("and")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__174800 input__174111_nth_1__ ?__174115)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__175208)
                         (clojure.core/let
                          [[?__174115] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__174111)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__174111)
                             2)
                            (clojure.core/let
                             [input__174111_nth_0__
                              (clojure.core/nth input__174111 0)
                              input__174111_nth_1__
                              (clojure.core/nth input__174111 1)]
                             (if
                              (clojure.core/seq? input__174111_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__174111_nth_0__)
                                3)
                               (clojure.core/let
                                [input__174111_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  1)
                                 input__174111_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__174111_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right
                                   input__174111_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__174111_nth_0__]
                                   (clojure.core/let
                                    [?env input__174111_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__174165
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15552__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15552__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__174165
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15552__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15552__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__16492__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16492__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16492__auto__)))))))))
                               (state__175208))
                              (state__175208)))
                            (state__175208))
                           (state__175208)))))
                       (state__175208)))))
                   (state__175208))))
                (state__175208)))
              (state__175208)))
            (state__175208))
           (state__175208))))
        (state__175208
         []
         (clojure.core/letfn
          [(def__174834
            [arg__174857 ?__174116]
            (clojure.core/letfn
             [(state__175356
               []
               (clojure.core/let
                [x__174858 "meander.zeta"]
                (if
                 (clojure.core/= ?__174116 x__174858)
                 [?__174116]
                 (state__175357))))
              (state__175357
               []
               (if
                (clojure.core/map? arg__174857)
                (clojure.core/let
                 [VAL__174859 (.valAt arg__174857 :aliases)]
                 (if
                  (clojure.core/map? VAL__174859)
                  (clojure.core/let
                   [X__174861 (clojure.core/set VAL__174859)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__174861))
                    (clojure.core/loop
                     [search_space__175358
                      (clojure.core/seq X__174861)]
                     (if
                      (clojure.core/seq search_space__175358)
                      (clojure.core/let
                       [elem__174862
                        (clojure.core/first search_space__175358)
                        result__175359
                        (clojure.core/let
                         [elem__174862_nth_0__
                          (clojure.core/nth elem__174862 0)
                          elem__174862_nth_1__
                          (clojure.core/nth elem__174862 1)]
                         (if
                          (clojure.core/symbol? elem__174862_nth_0__)
                          (clojure.core/let
                           [X__174864
                            (clojure.core/name elem__174862_nth_0__)]
                           (if
                            (clojure.core/= ?__174116 X__174864)
                            (if
                             (clojure.core/symbol?
                              elem__174862_nth_1__)
                             (clojure.core/let
                              [X__174866
                               (clojure.core/name
                                elem__174862_nth_1__)]
                              (clojure.core/case
                               X__174866
                               ("meander.zeta")
                               [?__174116]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175359)
                        (recur
                         (clojure.core/next search_space__175358))
                        result__175359))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175356)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 2)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)]
             (if
              (clojure.core/seq? input__174111_nth_0__)
              (clojure.core/let
               [input__174111_nth_0___l__
                (clojure.core/take 1 input__174111_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__174111_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__174111_nth_0___r__
                  (clojure.core/drop 1 input__174111_nth_0__)]
                 (clojure.core/let
                  [input__174111_nth_0___l___nth_0__
                   (clojure.core/nth input__174111_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__174111_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__174844
                     (clojure.core/namespace
                      input__174111_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__174116 X__174844]
                     (clojure.core/let
                      [X__174846
                       (clojure.core/name
                        input__174111_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__174846
                       ("cata")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__174834 input__174111_nth_1__ ?__174116)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__175209)
                         (clojure.core/let
                          [[?__174116] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__174111)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__174111)
                             2)
                            (clojure.core/let
                             [input__174111_nth_0__
                              (clojure.core/nth input__174111 0)
                              input__174111_nth_1__
                              (clojure.core/nth input__174111 1)]
                             (if
                              (clojure.core/seq? input__174111_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__174111_nth_0__)
                                2)
                               (clojure.core/let
                                [input__174111_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__174111_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__174111_nth_0__]
                                  (clojure.core/let
                                   [?env input__174111_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__15552__auto__
                                        (CATA__FN__174165
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__15552__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__15552__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__16492__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__16492__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__16492__auto__))))))))
                               (state__175209))
                              (state__175209)))
                            (state__175209))
                           (state__175209)))))
                       (state__175209)))))
                   (state__175209))))
                (state__175209)))
              (state__175209)))
            (state__175209))
           (state__175209))))
        (state__175209
         []
         (clojure.core/letfn
          [(def__174868
            [arg__174891 ?__174117]
            (clojure.core/letfn
             [(state__175361
               []
               (clojure.core/let
                [x__174892 "meander.zeta"]
                (if
                 (clojure.core/= ?__174117 x__174892)
                 [?__174117]
                 (state__175362))))
              (state__175362
               []
               (if
                (clojure.core/map? arg__174891)
                (clojure.core/let
                 [VAL__174893 (.valAt arg__174891 :aliases)]
                 (if
                  (clojure.core/map? VAL__174893)
                  (clojure.core/let
                   [X__174895 (clojure.core/set VAL__174893)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__174895))
                    (clojure.core/loop
                     [search_space__175363
                      (clojure.core/seq X__174895)]
                     (if
                      (clojure.core/seq search_space__175363)
                      (clojure.core/let
                       [elem__174896
                        (clojure.core/first search_space__175363)
                        result__175364
                        (clojure.core/let
                         [elem__174896_nth_0__
                          (clojure.core/nth elem__174896 0)
                          elem__174896_nth_1__
                          (clojure.core/nth elem__174896 1)]
                         (if
                          (clojure.core/symbol? elem__174896_nth_0__)
                          (clojure.core/let
                           [X__174898
                            (clojure.core/name elem__174896_nth_0__)]
                           (if
                            (clojure.core/= ?__174117 X__174898)
                            (if
                             (clojure.core/symbol?
                              elem__174896_nth_1__)
                             (clojure.core/let
                              [X__174900
                               (clojure.core/name
                                elem__174896_nth_1__)]
                              (clojure.core/case
                               X__174900
                               ("meander.zeta")
                               [?__174117]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175364)
                        (recur
                         (clojure.core/next search_space__175363))
                        result__175364))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175361)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 2)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)]
             (if
              (clojure.core/seq? input__174111_nth_0__)
              (clojure.core/let
               [input__174111_nth_0___l__
                (clojure.core/take 1 input__174111_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__174111_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__174111_nth_0___r__
                  (clojure.core/drop 1 input__174111_nth_0__)]
                 (clojure.core/let
                  [input__174111_nth_0___l___nth_0__
                   (clojure.core/nth input__174111_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__174111_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__174878
                     (clojure.core/namespace
                      input__174111_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__174117 X__174878]
                     (clojure.core/let
                      [X__174880
                       (clojure.core/name
                        input__174111_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__174880
                       ("fold")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__174868 input__174111_nth_1__ ?__174117)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__175210)
                         (clojure.core/let
                          [[?__174117] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__174111)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__174111)
                             2)
                            (clojure.core/let
                             [input__174111_nth_0__
                              (clojure.core/nth input__174111 0)
                              input__174111_nth_1__
                              (clojure.core/nth input__174111 1)]
                             (if
                              (clojure.core/seq? input__174111_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__174111_nth_0__)
                                4)
                               (clojure.core/let
                                [input__174111_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  1)
                                 input__174111_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  2)
                                 input__174111_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__174111_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__174111_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__174111_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__174111_nth_0__]
                                    (clojure.core/let
                                     [?env input__174111_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__174165
                                          ['meander.dev.parse.zeta/fold-args
                                           (clojure.core/let
                                            [CATA_RESULT__15552__auto__
                                             (CATA__FN__174165
                                              [?mutable-variable
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__15552__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__15552__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__15552__auto__
                                             (CATA__FN__174165
                                              [?initial-value ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__15552__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__15552__auto__
                                              0)))
                                           ?fold-function
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15552__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15552__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__16492__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__16492__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__16492__auto__))))))))))
                               (state__175210))
                              (state__175210)))
                            (state__175210))
                           (state__175210)))))
                       (state__175210)))))
                   (state__175210))))
                (state__175210)))
              (state__175210)))
            (state__175210))
           (state__175210))))
        (state__175210
         []
         (if
          (clojure.core/vector? input__174111)
          (if
           (clojure.core/= (clojure.core/count input__174111) 5)
           (clojure.core/let
            [input__174111_nth_0__
             (clojure.core/nth input__174111 0)
             input__174111_nth_1__
             (clojure.core/nth input__174111 1)
             input__174111_nth_2__
             (clojure.core/nth input__174111 2)
             input__174111_nth_3__
             (clojure.core/nth input__174111 3)
             input__174111_nth_4__
             (clojure.core/nth input__174111 4)]
            (if
             (clojure.core/=
              input__174111_nth_0__
              'meander.dev.parse.zeta/fold-args)
             (if
              (clojure.core/map? input__174111_nth_1__)
              (clojure.core/let
               [VAL__174903 (.valAt input__174111_nth_1__ :tag)]
               (clojure.core/case
                VAL__174903
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__174111_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__174111_nth_2__]
                  (clojure.core/let
                   [?fold-function input__174111_nth_3__]
                   (clojure.core/let
                    [?form input__174111_nth_4__]
                    (try
                     [{:tag :fold,
                       :variable ?variable-ast,
                       :initial-value ?initial-value-ast,
                       :fold-function
                       {:tag :host-expression, :form ?fold-function},
                       :form ?form}]
                     (catch
                      java.lang.Exception
                      e__16492__auto__
                      (if
                       (meander.runtime.zeta/fail? e__16492__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__16492__auto__))))))))
                (state__175211)))
              (state__175211))
             (state__175211)))
           (state__175211))
          (state__175211)))
        (state__175211
         []
         (clojure.core/letfn
          [(def__174905
            [arg__174928 ?__174118]
            (clojure.core/letfn
             [(state__175366
               []
               (clojure.core/let
                [x__174929 "meander.zeta"]
                (if
                 (clojure.core/= ?__174118 x__174929)
                 [?__174118]
                 (state__175367))))
              (state__175367
               []
               (if
                (clojure.core/map? arg__174928)
                (clojure.core/let
                 [VAL__174930 (.valAt arg__174928 :aliases)]
                 (if
                  (clojure.core/map? VAL__174930)
                  (clojure.core/let
                   [X__174932 (clojure.core/set VAL__174930)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__174932))
                    (clojure.core/loop
                     [search_space__175368
                      (clojure.core/seq X__174932)]
                     (if
                      (clojure.core/seq search_space__175368)
                      (clojure.core/let
                       [elem__174933
                        (clojure.core/first search_space__175368)
                        result__175369
                        (clojure.core/let
                         [elem__174933_nth_0__
                          (clojure.core/nth elem__174933 0)
                          elem__174933_nth_1__
                          (clojure.core/nth elem__174933 1)]
                         (if
                          (clojure.core/symbol? elem__174933_nth_0__)
                          (clojure.core/let
                           [X__174935
                            (clojure.core/name elem__174933_nth_0__)]
                           (if
                            (clojure.core/= ?__174118 X__174935)
                            (if
                             (clojure.core/symbol?
                              elem__174933_nth_1__)
                             (clojure.core/let
                              [X__174937
                               (clojure.core/name
                                elem__174933_nth_1__)]
                              (clojure.core/case
                               X__174937
                               ("meander.zeta")
                               [?__174118]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175369)
                        (recur
                         (clojure.core/next search_space__175368))
                        result__175369))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175366)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 2)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)]
             (if
              (clojure.core/seq? input__174111_nth_0__)
              (clojure.core/let
               [input__174111_nth_0___l__
                (clojure.core/take 1 input__174111_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__174111_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__174111_nth_0___r__
                  (clojure.core/drop 1 input__174111_nth_0__)]
                 (clojure.core/let
                  [input__174111_nth_0___l___nth_0__
                   (clojure.core/nth input__174111_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__174111_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__174915
                     (clojure.core/namespace
                      input__174111_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__174118 X__174915]
                     (clojure.core/let
                      [X__174917
                       (clojure.core/name
                        input__174111_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__174917
                       ("let")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__174905 input__174111_nth_1__ ?__174118)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__175212)
                         (clojure.core/let
                          [[?__174118] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__174111)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__174111)
                             2)
                            (clojure.core/let
                             [input__174111_nth_0__
                              (clojure.core/nth input__174111 0)
                              input__174111_nth_1__
                              (clojure.core/nth input__174111 1)]
                             (if
                              (clojure.core/seq? input__174111_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__174111_nth_0__)
                                3)
                               (clojure.core/let
                                [input__174111_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  0)
                                 input__174111_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  1)
                                 input__174111_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__174111_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__174111_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next
                                    input__174111_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__174111_nth_0__]
                                    (clojure.core/let
                                     [?env input__174111_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__15552__auto__
                                          (CATA__FN__174165
                                           [?pattern ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__15552__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__15552__auto__
                                           0))),
                                        :expression
                                        {:tag :host-expression,
                                         :form ?expression},
                                        :next
                                        (clojure.core/let
                                         [CATA_RESULT__15552__auto__
                                          (CATA__FN__174165
                                           [?next ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__15552__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__15552__auto__
                                           0)))}]
                                      (catch
                                       java.lang.Exception
                                       e__16492__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__16492__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__16492__auto__))))))))))
                               (state__175212))
                              (state__175212)))
                            (state__175212))
                           (state__175212)))))
                       (state__175212)))))
                   (state__175212))))
                (state__175212)))
              (state__175212)))
            (state__175212))
           (state__175212))))
        (state__175212
         []
         (clojure.core/letfn
          [(def__174939
            [arg__174962 ?__174119]
            (clojure.core/letfn
             [(state__175371
               []
               (clojure.core/let
                [x__174963 "meander.zeta"]
                (if
                 (clojure.core/= ?__174119 x__174963)
                 [?__174119]
                 (state__175372))))
              (state__175372
               []
               (if
                (clojure.core/map? arg__174962)
                (clojure.core/let
                 [VAL__174964 (.valAt arg__174962 :aliases)]
                 (if
                  (clojure.core/map? VAL__174964)
                  (clojure.core/let
                   [X__174966 (clojure.core/set VAL__174964)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__174966))
                    (clojure.core/loop
                     [search_space__175373
                      (clojure.core/seq X__174966)]
                     (if
                      (clojure.core/seq search_space__175373)
                      (clojure.core/let
                       [elem__174967
                        (clojure.core/first search_space__175373)
                        result__175374
                        (clojure.core/let
                         [elem__174967_nth_0__
                          (clojure.core/nth elem__174967 0)
                          elem__174967_nth_1__
                          (clojure.core/nth elem__174967 1)]
                         (if
                          (clojure.core/symbol? elem__174967_nth_0__)
                          (clojure.core/let
                           [X__174969
                            (clojure.core/name elem__174967_nth_0__)]
                           (if
                            (clojure.core/= ?__174119 X__174969)
                            (if
                             (clojure.core/symbol?
                              elem__174967_nth_1__)
                             (clojure.core/let
                              [X__174971
                               (clojure.core/name
                                elem__174967_nth_1__)]
                              (clojure.core/case
                               X__174971
                               ("meander.zeta")
                               [?__174119]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175374)
                        (recur
                         (clojure.core/next search_space__175373))
                        result__175374))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175371)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 2)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)]
             (if
              (clojure.core/seq? input__174111_nth_0__)
              (clojure.core/let
               [input__174111_nth_0___l__
                (clojure.core/take 1 input__174111_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__174111_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__174111_nth_0___r__
                  (clojure.core/drop 1 input__174111_nth_0__)]
                 (clojure.core/let
                  [input__174111_nth_0___l___nth_0__
                   (clojure.core/nth input__174111_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__174111_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__174949
                     (clojure.core/namespace
                      input__174111_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__174119 X__174949]
                     (clojure.core/let
                      [X__174951
                       (clojure.core/name
                        input__174111_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__174951
                       ("not")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__174939 input__174111_nth_1__ ?__174119)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__175213)
                         (clojure.core/let
                          [[?__174119] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__174111)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__174111)
                             2)
                            (clojure.core/let
                             [input__174111_nth_0__
                              (clojure.core/nth input__174111 0)
                              input__174111_nth_1__
                              (clojure.core/nth input__174111 1)]
                             (if
                              (clojure.core/seq? input__174111_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__174111_nth_0__)
                                2)
                               (clojure.core/let
                                [input__174111_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__174111_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__174111_nth_0__]
                                  (clojure.core/let
                                   [?env input__174111_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__15552__auto__
                                        (CATA__FN__174165
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__15552__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__15552__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__16492__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__16492__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__16492__auto__))))))))
                               (state__175213))
                              (state__175213)))
                            (state__175213))
                           (state__175213)))))
                       (state__175213)))))
                   (state__175213))))
                (state__175213)))
              (state__175213)))
            (state__175213))
           (state__175213))))
        (state__175213
         []
         (clojure.core/letfn
          [(def__174973
            [arg__174996 ?__174120]
            (clojure.core/letfn
             [(state__175376
               []
               (clojure.core/let
                [x__174997 "meander.zeta"]
                (if
                 (clojure.core/= ?__174120 x__174997)
                 [?__174120]
                 (state__175377))))
              (state__175377
               []
               (if
                (clojure.core/map? arg__174996)
                (clojure.core/let
                 [VAL__174998 (.valAt arg__174996 :aliases)]
                 (if
                  (clojure.core/map? VAL__174998)
                  (clojure.core/let
                   [X__175000 (clojure.core/set VAL__174998)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__175000))
                    (clojure.core/loop
                     [search_space__175378
                      (clojure.core/seq X__175000)]
                     (if
                      (clojure.core/seq search_space__175378)
                      (clojure.core/let
                       [elem__175001
                        (clojure.core/first search_space__175378)
                        result__175379
                        (clojure.core/let
                         [elem__175001_nth_0__
                          (clojure.core/nth elem__175001 0)
                          elem__175001_nth_1__
                          (clojure.core/nth elem__175001 1)]
                         (if
                          (clojure.core/symbol? elem__175001_nth_0__)
                          (clojure.core/let
                           [X__175003
                            (clojure.core/name elem__175001_nth_0__)]
                           (if
                            (clojure.core/= ?__174120 X__175003)
                            (if
                             (clojure.core/symbol?
                              elem__175001_nth_1__)
                             (clojure.core/let
                              [X__175005
                               (clojure.core/name
                                elem__175001_nth_1__)]
                              (clojure.core/case
                               X__175005
                               ("meander.zeta")
                               [?__174120]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175379)
                        (recur
                         (clojure.core/next search_space__175378))
                        result__175379))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175376)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 2)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)]
             (if
              (clojure.core/seq? input__174111_nth_0__)
              (clojure.core/let
               [input__174111_nth_0___l__
                (clojure.core/take 1 input__174111_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__174111_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__174111_nth_0___r__
                  (clojure.core/drop 1 input__174111_nth_0__)]
                 (clojure.core/let
                  [input__174111_nth_0___l___nth_0__
                   (clojure.core/nth input__174111_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__174111_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__174983
                     (clojure.core/namespace
                      input__174111_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__174120 X__174983]
                     (clojure.core/let
                      [X__174985
                       (clojure.core/name
                        input__174111_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__174985
                       ("or")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__174973 input__174111_nth_1__ ?__174120)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__175214)
                         (clojure.core/let
                          [[?__174120] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__174111)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__174111)
                             2)
                            (clojure.core/let
                             [input__174111_nth_0__
                              (clojure.core/nth input__174111 0)
                              input__174111_nth_1__
                              (clojure.core/nth input__174111 1)]
                             (if
                              (clojure.core/seq? input__174111_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__174111_nth_0__)
                                3)
                               (clojure.core/let
                                [input__174111_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  1)
                                 input__174111_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__174111_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right
                                   input__174111_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__174111_nth_0__]
                                   (clojure.core/let
                                    [?env input__174111_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__174165
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15552__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15552__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__174165
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15552__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15552__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__16492__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16492__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16492__auto__)))))))))
                               (state__175214))
                              (state__175214)))
                            (state__175214))
                           (state__175214)))))
                       (state__175214)))))
                   (state__175214))))
                (state__175214)))
              (state__175214)))
            (state__175214))
           (state__175214))))
        (state__175214
         []
         (clojure.core/letfn
          [(def__175007
            [arg__175030 ?__174121]
            (clojure.core/letfn
             [(state__175381
               []
               (clojure.core/let
                [x__175031 "meander.zeta"]
                (if
                 (clojure.core/= ?__174121 x__175031)
                 [?__174121]
                 (state__175382))))
              (state__175382
               []
               (if
                (clojure.core/map? arg__175030)
                (clojure.core/let
                 [VAL__175032 (.valAt arg__175030 :aliases)]
                 (if
                  (clojure.core/map? VAL__175032)
                  (clojure.core/let
                   [X__175034 (clojure.core/set VAL__175032)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__175034))
                    (clojure.core/loop
                     [search_space__175383
                      (clojure.core/seq X__175034)]
                     (if
                      (clojure.core/seq search_space__175383)
                      (clojure.core/let
                       [elem__175035
                        (clojure.core/first search_space__175383)
                        result__175384
                        (clojure.core/let
                         [elem__175035_nth_0__
                          (clojure.core/nth elem__175035 0)
                          elem__175035_nth_1__
                          (clojure.core/nth elem__175035 1)]
                         (if
                          (clojure.core/symbol? elem__175035_nth_0__)
                          (clojure.core/let
                           [X__175037
                            (clojure.core/name elem__175035_nth_0__)]
                           (if
                            (clojure.core/= ?__174121 X__175037)
                            (if
                             (clojure.core/symbol?
                              elem__175035_nth_1__)
                             (clojure.core/let
                              [X__175039
                               (clojure.core/name
                                elem__175035_nth_1__)]
                              (clojure.core/case
                               X__175039
                               ("meander.zeta")
                               [?__174121]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175384)
                        (recur
                         (clojure.core/next search_space__175383))
                        result__175384))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175381)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 2)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)]
             (if
              (clojure.core/seq? input__174111_nth_0__)
              (clojure.core/let
               [input__174111_nth_0___l__
                (clojure.core/take 1 input__174111_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__174111_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__174111_nth_0___r__
                  (clojure.core/drop 1 input__174111_nth_0__)]
                 (clojure.core/let
                  [input__174111_nth_0___l___nth_0__
                   (clojure.core/nth input__174111_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__174111_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__175017
                     (clojure.core/namespace
                      input__174111_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__174121 X__175017]
                     (clojure.core/let
                      [X__175019
                       (clojure.core/name
                        input__174111_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__175019
                       ("string")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__175007 input__174111_nth_1__ ?__174121)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__175215)
                         (clojure.core/let
                          [[?__174121] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__174111)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__174111)
                             2)
                            (clojure.core/let
                             [input__174111_nth_0__
                              (clojure.core/nth input__174111 0)
                              input__174111_nth_1__
                              (clojure.core/nth input__174111 1)]
                             (if
                              (clojure.core/seq? input__174111_nth_0__)
                              (clojure.core/let
                               [input__174111_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__174111_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__174111_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__174111_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__174111_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__174111_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__174111_nth_0__]
                                   (clojure.core/let
                                    [?env input__174111_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__174165
                                          ['meander.dev.parse.zeta/parse-string
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15552__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15552__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__16492__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16492__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16492__auto__))))))))
                                (state__175215)))
                              (state__175215)))
                            (state__175215))
                           (state__175215)))))
                       (state__175215)))))
                   (state__175215))))
                (state__175215)))
              (state__175215)))
            (state__175215))
           (state__175215))))
        (state__175215
         []
         (clojure.core/letfn
          [(def__175041
            [arg__175064 ?__174122]
            (clojure.core/letfn
             [(state__175386
               []
               (clojure.core/let
                [x__175065 "meander.zeta"]
                (if
                 (clojure.core/= ?__174122 x__175065)
                 [?__174122]
                 (state__175387))))
              (state__175387
               []
               (if
                (clojure.core/map? arg__175064)
                (clojure.core/let
                 [VAL__175066 (.valAt arg__175064 :aliases)]
                 (if
                  (clojure.core/map? VAL__175066)
                  (clojure.core/let
                   [X__175068 (clojure.core/set VAL__175066)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__175068))
                    (clojure.core/loop
                     [search_space__175388
                      (clojure.core/seq X__175068)]
                     (if
                      (clojure.core/seq search_space__175388)
                      (clojure.core/let
                       [elem__175069
                        (clojure.core/first search_space__175388)
                        result__175389
                        (clojure.core/let
                         [elem__175069_nth_0__
                          (clojure.core/nth elem__175069 0)
                          elem__175069_nth_1__
                          (clojure.core/nth elem__175069 1)]
                         (if
                          (clojure.core/symbol? elem__175069_nth_0__)
                          (clojure.core/let
                           [X__175071
                            (clojure.core/name elem__175069_nth_0__)]
                           (if
                            (clojure.core/= ?__174122 X__175071)
                            (if
                             (clojure.core/symbol?
                              elem__175069_nth_1__)
                             (clojure.core/let
                              [X__175073
                               (clojure.core/name
                                elem__175069_nth_1__)]
                              (clojure.core/case
                               X__175073
                               ("meander.zeta")
                               [?__174122]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175389)
                        (recur
                         (clojure.core/next search_space__175388))
                        result__175389))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175386)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 2)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)]
             (if
              (clojure.core/seq? input__174111_nth_0__)
              (clojure.core/let
               [input__174111_nth_0___l__
                (clojure.core/take 1 input__174111_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__174111_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__174111_nth_0___r__
                  (clojure.core/drop 1 input__174111_nth_0__)]
                 (clojure.core/let
                  [input__174111_nth_0___l___nth_0__
                   (clojure.core/nth input__174111_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__174111_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__175051
                     (clojure.core/namespace
                      input__174111_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__174122 X__175051]
                     (clojure.core/let
                      [X__175053
                       (clojure.core/name
                        input__174111_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__175053
                       ("symbol")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__175041 input__174111_nth_1__ ?__174122)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__175216)
                         (clojure.core/let
                          [[?__174122] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__174111)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__174111)
                             2)
                            (clojure.core/let
                             [input__174111_nth_0__
                              (clojure.core/nth input__174111 0)
                              input__174111_nth_1__
                              (clojure.core/nth input__174111 1)]
                             (if
                              (clojure.core/seq? input__174111_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__174111_nth_0__)
                                2)
                               (clojure.core/let
                                [input__174111_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__174111_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__174111_nth_0__]
                                  (clojure.core/let
                                   [?env input__174111_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__15552__auto__
                                        (CATA__FN__174165
                                         [?name ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__15552__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__15552__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__16492__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__16492__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__16492__auto__))))))))
                               (state__175216))
                              (state__175216)))
                            (state__175216))
                           (state__175216)))))
                       (state__175216)))))
                   (state__175216))))
                (state__175216)))
              (state__175216)))
            (state__175216))
           (state__175216))))
        (state__175216
         []
         (clojure.core/letfn
          [(def__175075
            [arg__175098 ?__174123]
            (clojure.core/letfn
             [(state__175391
               []
               (clojure.core/let
                [x__175099 "meander.zeta"]
                (if
                 (clojure.core/= ?__174123 x__175099)
                 [?__174123]
                 (state__175392))))
              (state__175392
               []
               (if
                (clojure.core/map? arg__175098)
                (clojure.core/let
                 [VAL__175100 (.valAt arg__175098 :aliases)]
                 (if
                  (clojure.core/map? VAL__175100)
                  (clojure.core/let
                   [X__175102 (clojure.core/set VAL__175100)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__175102))
                    (clojure.core/loop
                     [search_space__175393
                      (clojure.core/seq X__175102)]
                     (if
                      (clojure.core/seq search_space__175393)
                      (clojure.core/let
                       [elem__175103
                        (clojure.core/first search_space__175393)
                        result__175394
                        (clojure.core/let
                         [elem__175103_nth_0__
                          (clojure.core/nth elem__175103 0)
                          elem__175103_nth_1__
                          (clojure.core/nth elem__175103 1)]
                         (if
                          (clojure.core/symbol? elem__175103_nth_0__)
                          (clojure.core/let
                           [X__175105
                            (clojure.core/name elem__175103_nth_0__)]
                           (if
                            (clojure.core/= ?__174123 X__175105)
                            (if
                             (clojure.core/symbol?
                              elem__175103_nth_1__)
                             (clojure.core/let
                              [X__175107
                               (clojure.core/name
                                elem__175103_nth_1__)]
                              (clojure.core/case
                               X__175107
                               ("meander.zeta")
                               [?__174123]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175394)
                        (recur
                         (clojure.core/next search_space__175393))
                        result__175394))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175391)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 2)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)]
             (if
              (clojure.core/seq? input__174111_nth_0__)
              (clojure.core/let
               [input__174111_nth_0___l__
                (clojure.core/take 1 input__174111_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__174111_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__174111_nth_0___r__
                  (clojure.core/drop 1 input__174111_nth_0__)]
                 (clojure.core/let
                  [input__174111_nth_0___l___nth_0__
                   (clojure.core/nth input__174111_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__174111_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__175085
                     (clojure.core/namespace
                      input__174111_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__174123 X__175085]
                     (clojure.core/let
                      [X__175087
                       (clojure.core/name
                        input__174111_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__175087
                       ("symbol")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__175075 input__174111_nth_1__ ?__174123)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__175217)
                         (clojure.core/let
                          [[?__174123] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__174111)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__174111)
                             2)
                            (clojure.core/let
                             [input__174111_nth_0__
                              (clojure.core/nth input__174111 0)
                              input__174111_nth_1__
                              (clojure.core/nth input__174111 1)]
                             (if
                              (clojure.core/seq? input__174111_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__174111_nth_0__)
                                3)
                               (clojure.core/let
                                [input__174111_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  1)
                                 input__174111_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?name input__174111_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?namespace
                                   input__174111_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__174111_nth_0__]
                                   (clojure.core/let
                                    [?env input__174111_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__174165
                                          [?name ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15552__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15552__auto__
                                          0))),
                                       :namespace
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__174165
                                          [?namespace ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15552__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15552__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__16492__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16492__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16492__auto__)))))))))
                               (state__175217))
                              (state__175217)))
                            (state__175217))
                           (state__175217)))))
                       (state__175217)))))
                   (state__175217))))
                (state__175217)))
              (state__175217)))
            (state__175217))
           (state__175217))))
        (state__175217
         []
         (clojure.core/letfn
          [(def__175109
            [arg__175132 ?__174124]
            (clojure.core/letfn
             [(state__175396
               []
               (clojure.core/let
                [x__175133 "meander.zeta"]
                (if
                 (clojure.core/= ?__174124 x__175133)
                 [?__174124]
                 (state__175397))))
              (state__175397
               []
               (if
                (clojure.core/map? arg__175132)
                (clojure.core/let
                 [VAL__175134 (.valAt arg__175132 :aliases)]
                 (if
                  (clojure.core/map? VAL__175134)
                  (clojure.core/let
                   [X__175136 (clojure.core/set VAL__175134)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__175136))
                    (clojure.core/loop
                     [search_space__175398
                      (clojure.core/seq X__175136)]
                     (if
                      (clojure.core/seq search_space__175398)
                      (clojure.core/let
                       [elem__175137
                        (clojure.core/first search_space__175398)
                        result__175399
                        (clojure.core/let
                         [elem__175137_nth_0__
                          (clojure.core/nth elem__175137 0)
                          elem__175137_nth_1__
                          (clojure.core/nth elem__175137 1)]
                         (if
                          (clojure.core/symbol? elem__175137_nth_0__)
                          (clojure.core/let
                           [X__175139
                            (clojure.core/name elem__175137_nth_0__)]
                           (if
                            (clojure.core/= ?__174124 X__175139)
                            (if
                             (clojure.core/symbol?
                              elem__175137_nth_1__)
                             (clojure.core/let
                              [X__175141
                               (clojure.core/name
                                elem__175137_nth_1__)]
                              (clojure.core/case
                               X__175141
                               ("meander.zeta")
                               [?__174124]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__175399)
                        (recur
                         (clojure.core/next search_space__175398))
                        result__175399))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__175396)))]
          (if
           (clojure.core/vector? input__174111)
           (if
            (clojure.core/= (clojure.core/count input__174111) 2)
            (clojure.core/let
             [input__174111_nth_0__
              (clojure.core/nth input__174111 0)
              input__174111_nth_1__
              (clojure.core/nth input__174111 1)]
             (if
              (clojure.core/seq? input__174111_nth_0__)
              (clojure.core/let
               [input__174111_nth_0___l__
                (clojure.core/take 1 input__174111_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__174111_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__174111_nth_0___r__
                  (clojure.core/drop 1 input__174111_nth_0__)]
                 (clojure.core/let
                  [input__174111_nth_0___l___nth_0__
                   (clojure.core/nth input__174111_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__174111_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__175119
                     (clojure.core/namespace
                      input__174111_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__174124 X__175119]
                     (clojure.core/let
                      [X__175121
                       (clojure.core/name
                        input__174111_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__175121
                       ("symbol")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__175109 input__174111_nth_1__ ?__174124)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__175218)
                         (clojure.core/let
                          [[?__174124] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__174111)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__174111)
                             2)
                            (clojure.core/let
                             [input__174111_nth_0__
                              (clojure.core/nth input__174111 0)
                              input__174111_nth_1__
                              (clojure.core/nth input__174111 1)]
                             (if
                              (clojure.core/seq? input__174111_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 6)
                                 input__174111_nth_0__)
                                6)
                               (clojure.core/let
                                [input__174111_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  1)
                                 input__174111_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  2)
                                 input__174111_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  3)
                                 input__174111_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  4)
                                 input__174111_nth_0___nth_5__
                                 (clojure.core/nth
                                  input__174111_nth_0__
                                  5)]
                                (clojure.core/case
                                 input__174111_nth_0___nth_1__
                                 (&)
                                 (clojure.core/case
                                  input__174111_nth_0___nth_4__
                                  (:meander.zeta/as)
                                  (clojure.core/let
                                   [?name
                                    input__174111_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?namespace
                                     input__174111_nth_0___nth_3__]
                                    (clojure.core/let
                                     [?pattern
                                      input__174111_nth_0___nth_5__]
                                     (clojure.core/let
                                      [?form input__174111_nth_0__]
                                      (clojure.core/let
                                       [?env input__174111_nth_1__]
                                       (try
                                        [{:tag :symbol,
                                          :name
                                          (clojure.core/let
                                           [CATA_RESULT__15552__auto__
                                            (CATA__FN__174165
                                             [?name ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__15552__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__15552__auto__
                                             0))),
                                          :namespace
                                          (clojure.core/let
                                           [CATA_RESULT__15552__auto__
                                            (CATA__FN__174165
                                             [?namespace ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__15552__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__15552__auto__
                                             0))),
                                          :next
                                          {:tag :as,
                                           :pattern
                                           (clojure.core/let
                                            [CATA_RESULT__15552__auto__
                                             (CATA__FN__174165
                                              [?pattern ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__15552__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__15552__auto__
                                              0)))},
                                          :form ?form}]
                                        (catch
                                         java.lang.Exception
                                         e__16492__auto__
                                         (if
                                          (meander.runtime.zeta/fail?
                                           e__16492__auto__)
                                          (meander.runtime.zeta/fail)
                                          (throw
                                           e__16492__auto__)))))))))
                                  (state__175218))
                                 (state__175218)))
                               (state__175218))
                              (state__175218)))
                            (state__175218))
                           (state__175218)))))
                       (state__175218)))))
                   (state__175218))))
                (state__175218)))
              (state__175218)))
            (state__175218))
           (state__175218))))
        (state__175218
         []
         (if
          (clojure.core/vector? input__174111)
          (if
           (clojure.core/= (clojure.core/count input__174111) 2)
           (clojure.core/let
            [input__174111_nth_0__ (clojure.core/nth input__174111 0)]
            (clojure.core/letfn
             [(state__175401
               []
               (clojure.core/let
                [input__174111_nth_1__
                 (clojure.core/nth input__174111 1)]
                (clojure.core/letfn
                 [(state__175406
                   []
                   (if
                    (clojure.core/seq? input__174111_nth_0__)
                    (clojure.core/let
                     [?sequence input__174111_nth_0__]
                     (clojure.core/let
                      [?env input__174111_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__15552__auto__
                           (CATA__FN__174165
                            ['meander.dev.parse.zeta/parse-seq
                             (clojure.core/into [] ?sequence)
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__15552__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__15552__auto__
                            0))),
                         :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__16492__auto__
                        (if
                         (meander.runtime.zeta/fail? e__16492__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__16492__auto__))))))
                    (state__175407)))
                  (state__175407
                   []
                   (if
                    (clojure.core/map? input__174111_nth_0__)
                    (clojure.core/let
                     [?map input__174111_nth_0__]
                     (clojure.core/let
                      [?env input__174111_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__15552__auto__
                           (CATA__FN__174165
                            ['meander.dev.parse.zeta/parse-entries
                             ?map
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__15552__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__15552__auto__
                            0))),
                         :form ?map}]
                       (catch
                        java.lang.Exception
                        e__16492__auto__
                        (if
                         (meander.runtime.zeta/fail? e__16492__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__16492__auto__))))))
                    (state__175402)))]
                 (state__175406))))
              (state__175402
               []
               (if
                (clojure.core/symbol? input__174111_nth_0__)
                (clojure.core/let
                 [X__175151
                  (clojure.core/namespace input__174111_nth_0__)]
                 (clojure.core/let
                  [X__175153 (clojure.core/name input__174111_nth_0__)]
                  (if
                   (clojure.core/string? X__175153)
                   (clojure.core/letfn
                    [(state__175408
                      []
                      (clojure.core/let
                       [ret__175154
                        (clojure.core/re-matches #"_.*" X__175153)]
                       (if
                        (clojure.core/some? ret__175154)
                        (clojure.core/let
                         [?name ret__175154]
                         (clojure.core/let
                          [?symbol input__174111_nth_0__]
                          (try
                           [{:tag :wildcard,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__16492__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16492__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16492__auto__))))))
                        (state__175409))))
                     (state__175409
                      []
                      (clojure.core/let
                       [ret__175161
                        (clojure.core/re-matches #".+#" X__175153)]
                       (if
                        (clojure.core/some? ret__175161)
                        (clojure.core/let
                         [?name ret__175161]
                         (clojure.core/let
                          [?symbol input__174111_nth_0__]
                          (try
                           [{:tag :random-symbol,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__16492__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16492__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16492__auto__))))))
                        (state__175410))))
                     (state__175410
                      []
                      (clojure.core/let
                       [ret__175168
                        (clojure.core/re-matches #"%.+" X__175153)]
                       (if
                        (clojure.core/some? ret__175168)
                        (clojure.core/let
                         [?name ret__175168]
                         (clojure.core/let
                          [?symbol input__174111_nth_0__]
                          (try
                           [{:tag :reference,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__16492__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16492__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16492__auto__))))))
                        (state__175411))))
                     (state__175411
                      []
                      (clojure.core/let
                       [ret__175175
                        (clojure.core/re-matches #"\*.+" X__175153)]
                       (if
                        (clojure.core/some? ret__175175)
                        (clojure.core/let
                         [?name ret__175175]
                         (clojure.core/let
                          [?symbol input__174111_nth_0__]
                          (try
                           [{:tag :mutable-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__16492__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16492__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16492__auto__))))))
                        (state__175412))))
                     (state__175412
                      []
                      (clojure.core/let
                       [ret__175182
                        (clojure.core/re-matches #"\!.+" X__175153)]
                       (if
                        (clojure.core/some? ret__175182)
                        (clojure.core/let
                         [?name ret__175182]
                         (clojure.core/let
                          [?symbol input__174111_nth_0__]
                          (try
                           [{:tag :memory-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__16492__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16492__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16492__auto__))))))
                        (state__175413))))
                     (state__175413
                      []
                      (clojure.core/let
                       [ret__175189
                        (clojure.core/re-matches #"\?.+" X__175153)]
                       (if
                        (clojure.core/some? ret__175189)
                        (clojure.core/let
                         [?name ret__175189]
                         (clojure.core/let
                          [?symbol input__174111_nth_0__]
                          (try
                           [{:tag :logic-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__16492__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16492__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16492__auto__))))))
                        (state__175403))))]
                    (state__175408))
                   (state__175403))))
                (state__175403)))
              (state__175403
               []
               (if
                (string? input__174111_nth_0__)
                (clojure.core/let
                 [?x input__174111_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__16492__auto__
                   (if
                    (meander.runtime.zeta/fail? e__16492__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__16492__auto__)))))
                (state__175404)))
              (state__175404
               []
               (if
                (char? input__174111_nth_0__)
                (clojure.core/let
                 [?x input__174111_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__16492__auto__
                   (if
                    (meander.runtime.zeta/fail? e__16492__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__16492__auto__)))))
                (state__175405)))
              (state__175405
               []
               (clojure.core/let
                [?x input__174111_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__16492__auto__
                  (if
                   (meander.runtime.zeta/fail? e__16492__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__16492__auto__))))))]
             (state__175401)))
           (state__175219))
          (state__175219)))
        (state__175219
         []
         (clojure.core/let
          [?x input__174111]
          (try
           [?x]
           (catch
            java.lang.Exception
            e__16492__auto__
            (if
             (meander.runtime.zeta/fail? e__16492__auto__)
             (meander.runtime.zeta/fail)
             (throw e__16492__auto__))))))]
       (state__175196)))]
    (clojure.core/let
     [x__14249__auto__ (CATA__FN__174165 input__174111)]
     (if
      (meander.runtime.zeta/fail? x__14249__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__174168] x__14249__auto__]
       CATA_RETURN__174168))))]
  (if
   (meander.runtime.zeta/fail? ret__14429__auto__)
   nil
   ret__14429__auto__)))
