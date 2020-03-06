(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__30171]
 (let*
  [ret__9971__auto__
   (clojure.core/letfn
    [(CATA__FN__30216
      [input__30171]
      (clojure.core/letfn
       [(state__31145
         []
         (if
          (clojure.core/vector? input__30171)
          (if
           (clojure.core/= (clojure.core/count input__30171) 3)
           (clojure.core/let
            [input__30171_nth_0__
             (clojure.core/nth input__30171 0)
             input__30171_nth_1__
             (clojure.core/nth input__30171 1)
             input__30171_nth_2__
             (clojure.core/nth input__30171 2)]
            (if
             (clojure.core/let
              [x__8851__auto__ input__30171_nth_0__]
              (clojure.core/case
               x__8851__auto__
               (meander.dev.parse.zeta/parse-seq
                meander.dev.parse.zeta/parse-string)
               true
               false))
             (clojure.core/letfn
              [(state__31166
                []
                (if
                 (clojure.core/vector? input__30171_nth_1__)
                 (clojure.core/case
                  input__30171_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__30171_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__12034__auto__
                     (if
                      (meander.runtime.zeta/fail? e__12034__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__12034__auto__)))))
                  (state__31167))
                 (state__31167)))
               (state__31167
                []
                (clojure.core/let
                 [?rule-name input__30171_nth_0__]
                 (if
                  (clojure.core/vector? input__30171_nth_1__)
                  (clojure.core/let
                   [n__30224
                    (clojure.core/count input__30171_nth_1__)
                    m__30225
                    (clojure.core/max 0 (clojure.core/- n__30224 2))
                    input__30171_nth_1___l__
                    (clojure.core/subvec
                     input__30171_nth_1__
                     0
                     (clojure.core/min
                      (clojure.core/count input__30171_nth_1__)
                      m__30225))
                    input__30171_nth_1___r__
                    (clojure.core/subvec
                     input__30171_nth_1__
                     m__30225)]
                   (if
                    (clojure.core/=
                     (clojure.core/count input__30171_nth_1___r__)
                     2)
                    (clojure.core/let
                     [!xs (clojure.core/vec input__30171_nth_1___l__)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__30171_nth_1___r__)
                       2)
                      (clojure.core/let
                       [input__30171_nth_1___r___nth_0__
                        (clojure.core/nth input__30171_nth_1___r__ 0)
                        input__30171_nth_1___r___nth_1__
                        (clojure.core/nth input__30171_nth_1___r__ 1)]
                       (clojure.core/case
                        input__30171_nth_1___r___nth_0__
                        (:meander.zeta/as)
                        (clojure.core/let
                         [?pattern input__30171_nth_1___r___nth_1__]
                         (clojure.core/let
                          [?env input__30171_nth_2__]
                          (try
                           [(clojure.core/let
                             [!xs__counter
                              (meander.runtime.zeta/iterator !xs)]
                             {:tag :as,
                              :pattern
                              (clojure.core/let
                               [CATA_RESULT__11094__auto__
                                (CATA__FN__30216 [?pattern ?env])]
                               (if
                                (meander.runtime.zeta/fail?
                                 CATA_RESULT__11094__auto__)
                                (throw (meander.runtime.zeta/fail))
                                (clojure.core/nth
                                 CATA_RESULT__11094__auto__
                                 0))),
                              :next
                              (clojure.core/let
                               [CATA_RESULT__11094__auto__
                                (CATA__FN__30216
                                 [?rule-name
                                  (clojure.core/into
                                   []
                                   (clojure.core/vec
                                    (clojure.core/iterator-seq
                                     !xs__counter)))
                                  ?env])]
                               (if
                                (meander.runtime.zeta/fail?
                                 CATA_RESULT__11094__auto__)
                                (throw (meander.runtime.zeta/fail))
                                (clojure.core/nth
                                 CATA_RESULT__11094__auto__
                                 0)))})]
                           (catch
                            java.lang.Exception
                            e__12034__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__12034__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__12034__auto__))))))
                        (state__31146)))
                      (state__31146)))
                    (state__31146)))
                  (state__31146))))]
              (state__31166))
             (state__31146)))
           (state__31146))
          (state__31146)))
        (state__31146
         []
         (clojure.core/letfn
          [(def__30230
            [arg__30265 ?ns]
            (clojure.core/letfn
             [(state__31168
               []
               (clojure.core/let
                [x__30266 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__30266)
                 (clojure.core/let [?env arg__30265] [?env ?ns])
                 (state__31169))))
              (state__31169
               []
               (if
                (clojure.core/map? arg__30265)
                (clojure.core/let
                 [VAL__30267 (.valAt arg__30265 :aliases)]
                 (if
                  (clojure.core/map? VAL__30267)
                  (clojure.core/let
                   [X__30269 (clojure.core/set VAL__30267)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__30269))
                    (clojure.core/loop
                     [search_space__31170 (clojure.core/seq X__30269)]
                     (if
                      (clojure.core/seq search_space__31170)
                      (clojure.core/let
                       [elem__30270
                        (clojure.core/first search_space__31170)
                        result__31171
                        (clojure.core/let
                         [elem__30270_nth_0__
                          (clojure.core/nth elem__30270 0)
                          elem__30270_nth_1__
                          (clojure.core/nth elem__30270 1)]
                         (if
                          (clojure.core/symbol? elem__30270_nth_0__)
                          (clojure.core/let
                           [X__30272
                            (clojure.core/name elem__30270_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__30272)
                            (if
                             (clojure.core/symbol? elem__30270_nth_1__)
                             (clojure.core/let
                              [X__30274
                               (clojure.core/name elem__30270_nth_1__)]
                              (clojure.core/case
                               X__30274
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__30265]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__31171)
                        (recur (clojure.core/next search_space__31170))
                        result__31171))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__31168)))]
          (if
           (clojure.core/vector? input__30171)
           (if
            (clojure.core/= (clojure.core/count input__30171) 3)
            (clojure.core/let
             [input__30171_nth_0__
              (clojure.core/nth input__30171 0)
              input__30171_nth_1__
              (clojure.core/nth input__30171 1)
              input__30171_nth_2__
              (clojure.core/nth input__30171 2)]
             (if
              (clojure.core/let
               [x__8851__auto__ input__30171_nth_0__]
               (clojure.core/case
                x__8851__auto__
                (meander.dev.parse.zeta/parse-seq
                 meander.dev.parse.zeta/parse-string)
                true
                false))
              (clojure.core/let
               [?rule-name input__30171_nth_0__]
               (if
                (clojure.core/vector? input__30171_nth_1__)
                (clojure.core/loop
                 [search_space__31173
                  (meander.match.runtime.epsilon/partitions
                   2
                   input__30171_nth_1__)]
                 (if
                  (clojure.core/seq search_space__31173)
                  (clojure.core/let
                   [input__30171_nth_1___parts__
                    (clojure.core/first search_space__31173)
                    result__31174
                    (clojure.core/let
                     [input__30171_nth_1___l__
                      (clojure.core/nth input__30171_nth_1___parts__ 0)
                      input__30171_nth_1___r__
                      (clojure.core/nth
                       input__30171_nth_1___parts__
                       1)]
                     (clojure.core/let
                      [!init
                       (clojure.core/vec input__30171_nth_1___l__)]
                      (clojure.core/let
                       [input__30171_nth_1___r___l__
                        (clojure.core/subvec
                         input__30171_nth_1___r__
                         0
                         (clojure.core/min
                          (clojure.core/count input__30171_nth_1___r__)
                          2))]
                       (if
                        (clojure.core/=
                         (clojure.core/count
                          input__30171_nth_1___r___l__)
                         2)
                        (clojure.core/let
                         [input__30171_nth_1___r___r__
                          (clojure.core/subvec
                           input__30171_nth_1___r__
                           2)]
                         (clojure.core/let
                          [input__30171_nth_1___r___l___nth_0__
                           (clojure.core/nth
                            input__30171_nth_1___r___l__
                            0)
                           input__30171_nth_1___r___l___nth_1__
                           (clojure.core/nth
                            input__30171_nth_1___r___l__
                            1)]
                          (if
                           (clojure.core/symbol?
                            input__30171_nth_1___r___l___nth_0__)
                           (clojure.core/let
                            [X__30239
                             (clojure.core/namespace
                              input__30171_nth_1___r___l___nth_0__)]
                            (clojure.core/let
                             [?ns X__30239]
                             (clojure.core/let
                              [X__30241
                               (clojure.core/name
                                input__30171_nth_1___r___l___nth_0__)]
                              (if
                               (clojure.core/string? X__30241)
                               (clojure.core/let
                                [ret__30242
                                 (clojure.core/re-matches
                                  #"&(\d+)"
                                  X__30241)]
                                (if
                                 (clojure.core/some? ret__30242)
                                 (if
                                  (clojure.core/vector? ret__30242)
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count ret__30242)
                                    2)
                                   (clojure.core/let
                                    [ret__30242_nth_1__
                                     (clojure.core/nth ret__30242 1)]
                                    (clojure.core/let
                                     [?n ret__30242_nth_1__]
                                     (clojure.core/let
                                      [?pattern
                                       input__30171_nth_1___r___l___nth_1__]
                                      (clojure.core/let
                                       [?rest
                                        input__30171_nth_1___r___r__]
                                       (clojure.core/let
                                        [x__9791__auto__
                                         (def__30230
                                          input__30171_nth_2__
                                          ?ns)]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          x__9791__auto__)
                                         (meander.runtime.zeta/fail)
                                         (clojure.core/let
                                          [[?env ?ns] x__9791__auto__]
                                          (try
                                           [(clojure.core/let
                                             [!init__counter
                                              (meander.runtime.zeta/iterator
                                               !init)]
                                             (clojure.core/let
                                              [CATA_RESULT__11094__auto__
                                               (CATA__FN__30216
                                                ['meander.dev.parse.zeta/join-args
                                                 (clojure.core/let
                                                  [CATA_RESULT__11094__auto__
                                                   (CATA__FN__30216
                                                    [?rule-name
                                                     (clojure.core/into
                                                      []
                                                      (clojure.core/vec
                                                       (clojure.core/iterator-seq
                                                        !init__counter)))
                                                     ?env])]
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    CATA_RESULT__11094__auto__)
                                                   (throw
                                                    (meander.runtime.zeta/fail))
                                                   (clojure.core/nth
                                                    CATA_RESULT__11094__auto__
                                                    0)))
                                                 (clojure.core/let
                                                  [CATA_RESULT__11094__auto__
                                                   (CATA__FN__30216
                                                    ['meander.dev.parse.zeta/join-args
                                                     {:tag :slice,
                                                      :size
                                                      (Integer. ?n),
                                                      :pattern
                                                      (clojure.core/let
                                                       [CATA_RESULT__11094__auto__
                                                        (CATA__FN__30216
                                                         [?pattern
                                                          ?env])]
                                                       (if
                                                        (meander.runtime.zeta/fail?
                                                         CATA_RESULT__11094__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__11094__auto__
                                                         0)))}
                                                     (clojure.core/let
                                                      [CATA_RESULT__11094__auto__
                                                       (CATA__FN__30216
                                                        [?rule-name
                                                         (clojure.core/into
                                                          []
                                                          ?rest)
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        CATA_RESULT__11094__auto__)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        CATA_RESULT__11094__auto__
                                                        0)))])]
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    CATA_RESULT__11094__auto__)
                                                   (throw
                                                    (meander.runtime.zeta/fail))
                                                   (clojure.core/nth
                                                    CATA_RESULT__11094__auto__
                                                    0)))])]
                                              (if
                                               (meander.runtime.zeta/fail?
                                                CATA_RESULT__11094__auto__)
                                               (throw
                                                (meander.runtime.zeta/fail))
                                               (clojure.core/nth
                                                CATA_RESULT__11094__auto__
                                                0))))]
                                           (catch
                                            java.lang.Exception
                                            e__12034__auto__
                                            (if
                                             (meander.runtime.zeta/fail?
                                              e__12034__auto__)
                                             (meander.runtime.zeta/fail)
                                             (throw
                                              e__12034__auto__)))))))))))
                                   (meander.runtime.zeta/fail))
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail)))
                               (meander.runtime.zeta/fail)))))
                           (meander.runtime.zeta/fail))))
                        (meander.runtime.zeta/fail)))))]
                   (if
                    (meander.runtime.zeta/fail? result__31174)
                    (recur (clojure.core/next search_space__31173))
                    result__31174))
                  (state__31147)))
                (state__31147)))
              (state__31147)))
            (state__31147))
           (state__31147))))
        (state__31147
         []
         (clojure.core/letfn
          [(def__30287
            [arg__30319 ?ns]
            (clojure.core/letfn
             [(state__31176
               []
               (clojure.core/let
                [x__30320 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__30320)
                 (clojure.core/let [?env arg__30319] [?env ?ns])
                 (state__31177))))
              (state__31177
               []
               (if
                (clojure.core/map? arg__30319)
                (clojure.core/let
                 [VAL__30321 (.valAt arg__30319 :aliases)]
                 (if
                  (clojure.core/map? VAL__30321)
                  (clojure.core/let
                   [X__30323 (clojure.core/set VAL__30321)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__30323))
                    (clojure.core/loop
                     [search_space__31178 (clojure.core/seq X__30323)]
                     (if
                      (clojure.core/seq search_space__31178)
                      (clojure.core/let
                       [elem__30324
                        (clojure.core/first search_space__31178)
                        result__31179
                        (clojure.core/let
                         [elem__30324_nth_0__
                          (clojure.core/nth elem__30324 0)
                          elem__30324_nth_1__
                          (clojure.core/nth elem__30324 1)]
                         (if
                          (clojure.core/symbol? elem__30324_nth_0__)
                          (clojure.core/let
                           [X__30326
                            (clojure.core/name elem__30324_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__30326)
                            (if
                             (clojure.core/symbol? elem__30324_nth_1__)
                             (clojure.core/let
                              [X__30328
                               (clojure.core/name elem__30324_nth_1__)]
                              (clojure.core/case
                               X__30328
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__30319]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__31179)
                        (recur (clojure.core/next search_space__31178))
                        result__31179))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__31176)))]
          (if
           (clojure.core/vector? input__30171)
           (if
            (clojure.core/= (clojure.core/count input__30171) 3)
            (clojure.core/let
             [input__30171_nth_0__
              (clojure.core/nth input__30171 0)
              input__30171_nth_1__
              (clojure.core/nth input__30171 1)
              input__30171_nth_2__
              (clojure.core/nth input__30171 2)]
             (if
              (clojure.core/=
               input__30171_nth_0__
               'meander.dev.parse.zeta/parse-seq)
              (if
               (clojure.core/vector? input__30171_nth_1__)
               (clojure.core/loop
                [search_space__31181
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__30171_nth_1__)]
                (if
                 (clojure.core/seq search_space__31181)
                 (clojure.core/let
                  [input__30171_nth_1___parts__
                   (clojure.core/first search_space__31181)
                   result__31182
                   (clojure.core/let
                    [input__30171_nth_1___l__
                     (clojure.core/nth input__30171_nth_1___parts__ 0)
                     input__30171_nth_1___r__
                     (clojure.core/nth input__30171_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__30171_nth_1___l__)]
                     (clojure.core/let
                      [input__30171_nth_1___r___l__
                       (clojure.core/subvec
                        input__30171_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__30171_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__30171_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__30171_nth_1___r___r__
                         (clojure.core/subvec
                          input__30171_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__30171_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__30171_nth_1___r___l__
                           0)
                          input__30171_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__30171_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__30171_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__30296
                            (clojure.core/namespace
                             input__30171_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__30296]
                            (clojure.core/let
                             [X__30298
                              (clojure.core/name
                               input__30171_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__30298)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__30298)
                               (clojure.core/let
                                [?pattern
                                 input__30171_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__30171_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__9791__auto__
                                   (def__30287
                                    input__30171_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__9791__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__9791__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__11094__auto__
                                         (CATA__FN__30216
                                          ['meander.dev.parse.zeta/join-args
                                           (clojure.core/let
                                            [CATA_RESULT__11094__auto__
                                             (CATA__FN__30216
                                              ['meander.dev.parse.zeta/parse-seq
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__11094__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__11094__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__11094__auto__
                                             (CATA__FN__30216
                                              ['meander.dev.parse.zeta/join-args
                                               (clojure.core/let
                                                [CATA_RESULT__11094__auto__
                                                 (CATA__FN__30216
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__11094__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__11094__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__11094__auto__
                                                 (CATA__FN__30216
                                                  ['meander.dev.parse.zeta/parse-string
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__11094__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__11094__auto__
                                                  0)))])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__11094__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__11094__auto__
                                              0)))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__11094__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__11094__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__12034__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__12034__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__12034__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__31182)
                   (recur (clojure.core/next search_space__31181))
                   result__31182))
                 (state__31148)))
               (state__31148))
              (state__31148)))
            (state__31148))
           (state__31148))))
        (state__31148
         []
         (clojure.core/letfn
          [(def__30341
            [arg__30373 ?ns]
            (clojure.core/letfn
             [(state__31184
               []
               (clojure.core/let
                [x__30374 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__30374)
                 (clojure.core/let [?env arg__30373] [?env ?ns])
                 (state__31185))))
              (state__31185
               []
               (if
                (clojure.core/map? arg__30373)
                (clojure.core/let
                 [VAL__30375 (.valAt arg__30373 :aliases)]
                 (if
                  (clojure.core/map? VAL__30375)
                  (clojure.core/let
                   [X__30377 (clojure.core/set VAL__30375)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__30377))
                    (clojure.core/loop
                     [search_space__31186 (clojure.core/seq X__30377)]
                     (if
                      (clojure.core/seq search_space__31186)
                      (clojure.core/let
                       [elem__30378
                        (clojure.core/first search_space__31186)
                        result__31187
                        (clojure.core/let
                         [elem__30378_nth_0__
                          (clojure.core/nth elem__30378 0)
                          elem__30378_nth_1__
                          (clojure.core/nth elem__30378 1)]
                         (if
                          (clojure.core/symbol? elem__30378_nth_0__)
                          (clojure.core/let
                           [X__30380
                            (clojure.core/name elem__30378_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__30380)
                            (if
                             (clojure.core/symbol? elem__30378_nth_1__)
                             (clojure.core/let
                              [X__30382
                               (clojure.core/name elem__30378_nth_1__)]
                              (clojure.core/case
                               X__30382
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__30373]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__31187)
                        (recur (clojure.core/next search_space__31186))
                        result__31187))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__31184)))]
          (if
           (clojure.core/vector? input__30171)
           (if
            (clojure.core/= (clojure.core/count input__30171) 3)
            (clojure.core/let
             [input__30171_nth_0__
              (clojure.core/nth input__30171 0)
              input__30171_nth_1__
              (clojure.core/nth input__30171 1)
              input__30171_nth_2__
              (clojure.core/nth input__30171 2)]
             (if
              (clojure.core/=
               input__30171_nth_0__
               'meander.dev.parse.zeta/parse-string)
              (if
               (clojure.core/vector? input__30171_nth_1__)
               (clojure.core/loop
                [search_space__31189
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__30171_nth_1__)]
                (if
                 (clojure.core/seq search_space__31189)
                 (clojure.core/let
                  [input__30171_nth_1___parts__
                   (clojure.core/first search_space__31189)
                   result__31190
                   (clojure.core/let
                    [input__30171_nth_1___l__
                     (clojure.core/nth input__30171_nth_1___parts__ 0)
                     input__30171_nth_1___r__
                     (clojure.core/nth input__30171_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__30171_nth_1___l__)]
                     (clojure.core/let
                      [input__30171_nth_1___r___l__
                       (clojure.core/subvec
                        input__30171_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__30171_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__30171_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__30171_nth_1___r___r__
                         (clojure.core/subvec
                          input__30171_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__30171_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__30171_nth_1___r___l__
                           0)
                          input__30171_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__30171_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__30171_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__30350
                            (clojure.core/namespace
                             input__30171_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__30350]
                            (clojure.core/let
                             [X__30352
                              (clojure.core/name
                               input__30171_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__30352)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__30352)
                               (clojure.core/let
                                [?pattern
                                 input__30171_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__30171_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__9791__auto__
                                   (def__30341
                                    input__30171_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__9791__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__9791__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__11094__auto__
                                         (CATA__FN__30216
                                          ['meander.dev.parse.zeta/string-join-args
                                           (clojure.core/let
                                            [CATA_RESULT__11094__auto__
                                             (CATA__FN__30216
                                              ['meander.dev.parse.zeta/parse-string
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__11094__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__11094__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__11094__auto__
                                             (CATA__FN__30216
                                              ['meander.dev.parse.zeta/string-join-args
                                               (clojure.core/let
                                                [CATA_RESULT__11094__auto__
                                                 (CATA__FN__30216
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__11094__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__11094__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__11094__auto__
                                                 (CATA__FN__30216
                                                  ['meander.dev.parse.zeta/parse-string
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__11094__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__11094__auto__
                                                  0)))])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__11094__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__11094__auto__
                                              0)))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__11094__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__11094__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__12034__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__12034__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__12034__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__31190)
                   (recur (clojure.core/next search_space__31189))
                   result__31190))
                 (state__31149)))
               (state__31149))
              (state__31149)))
            (state__31149))
           (state__31149))))
        (state__31149
         []
         (if
          (clojure.core/vector? input__30171)
          (if
           (clojure.core/= (clojure.core/count input__30171) 3)
           (clojure.core/let
            [input__30171_nth_0__
             (clojure.core/nth input__30171 0)
             input__30171_nth_1__
             (clojure.core/nth input__30171 1)
             input__30171_nth_2__
             (clojure.core/nth input__30171 2)]
            (clojure.core/letfn
             [(state__31192
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/parse-seq)
                (if
                 (clojure.core/vector? input__30171_nth_1__)
                 (clojure.core/let
                  [n__30403
                   (clojure.core/count input__30171_nth_1__)
                   m__30404
                   (clojure.core/max 0 (clojure.core/- n__30403 2))
                   input__30171_nth_1___l__
                   (clojure.core/subvec
                    input__30171_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__30171_nth_1__)
                     m__30404))
                   input__30171_nth_1___r__
                   (clojure.core/subvec input__30171_nth_1__ m__30404)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__30171_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__30171_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__30171_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__30171_nth_1___r___nth_0__
                       (clojure.core/nth input__30171_nth_1___r__ 0)
                       input__30171_nth_1___r___nth_1__
                       (clojure.core/nth input__30171_nth_1___r__ 1)]
                      (if
                       (clojure.core/symbol?
                        input__30171_nth_1___r___nth_0__)
                       (clojure.core/let
                        [X__30408
                         (clojure.core/namespace
                          input__30171_nth_1___r___nth_0__)]
                        (clojure.core/let
                         [?ns X__30408]
                         (clojure.core/let
                          [X__30410
                           (clojure.core/name
                            input__30171_nth_1___r___nth_0__)]
                          (if
                           (clojure.core/string? X__30410)
                           (clojure.core/let
                            [ret__30411
                             (clojure.core/re-matches #"&.*" X__30410)]
                            (if
                             (clojure.core/some? ret__30411)
                             (clojure.core/let
                              [?name ret__30411]
                              (clojure.core/let
                               [?pattern
                                input__30171_nth_1___r___nth_1__]
                               (if
                                (clojure.core/map?
                                 input__30171_nth_2__)
                                (clojure.core/let
                                 [VAL__30395
                                  (.valAt
                                   input__30171_nth_2__
                                   :aliases)]
                                 (if
                                  (clojure.core/map? VAL__30395)
                                  (clojure.core/let
                                   [X__30397
                                    (clojure.core/set VAL__30395)]
                                   (if
                                    (clojure.core/<=
                                     1
                                     (clojure.core/count X__30397))
                                    (clojure.core/loop
                                     [search_space__31197
                                      (clojure.core/seq X__30397)]
                                     (if
                                      (clojure.core/seq
                                       search_space__31197)
                                      (clojure.core/let
                                       [elem__30398
                                        (clojure.core/first
                                         search_space__31197)
                                        result__31198
                                        (clojure.core/let
                                         [elem__30398_nth_0__
                                          (clojure.core/nth
                                           elem__30398
                                           0)
                                          elem__30398_nth_1__
                                          (clojure.core/nth
                                           elem__30398
                                           1)]
                                         (if
                                          (clojure.core/symbol?
                                           elem__30398_nth_0__)
                                          (clojure.core/let
                                           [X__30400
                                            (clojure.core/name
                                             elem__30398_nth_0__)]
                                           (if
                                            (clojure.core/=
                                             ?ns
                                             X__30400)
                                            (if
                                             (clojure.core/symbol?
                                              elem__30398_nth_1__)
                                             (clojure.core/let
                                              [X__30402
                                               (clojure.core/name
                                                elem__30398_nth_1__)]
                                              (clojure.core/case
                                               X__30402
                                               ("meander.zeta")
                                               (clojure.core/let
                                                [?env
                                                 input__30171_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__11094__auto__
                                                     (CATA__FN__30216
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
                                                      CATA_RESULT__11094__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__11094__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__12034__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__12034__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__12034__auto__)))))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail))
                                            (meander.runtime.zeta/fail)))
                                          (meander.runtime.zeta/fail)))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         result__31198)
                                        (recur
                                         (clojure.core/next
                                          search_space__31197))
                                        result__31198))
                                      (state__31193)))
                                    (state__31193)))
                                  (state__31193)))
                                (state__31193))))
                             (state__31193)))
                           (state__31193)))))
                       (state__31193)))
                     (state__31193)))
                   (state__31193)))
                 (state__31193))
                (state__31193)))
              (state__31193
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__30171_nth_1__)
                 (clojure.core/let
                  [n__30422
                   (clojure.core/count input__30171_nth_1__)
                   m__30423
                   (clojure.core/max 0 (clojure.core/- n__30422 2))
                   input__30171_nth_1___l__
                   (clojure.core/subvec
                    input__30171_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__30171_nth_1__)
                     m__30423))
                   input__30171_nth_1___r__
                   (clojure.core/subvec input__30171_nth_1__ m__30423)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__30171_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__30171_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__30171_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__30171_nth_1___r___nth_0__
                       (clojure.core/nth input__30171_nth_1___r__ 0)
                       input__30171_nth_1___r___nth_1__
                       (clojure.core/nth input__30171_nth_1___r__ 1)]
                      (if
                       (clojure.core/symbol?
                        input__30171_nth_1___r___nth_0__)
                       (clojure.core/let
                        [X__30427
                         (clojure.core/namespace
                          input__30171_nth_1___r___nth_0__)]
                        (clojure.core/let
                         [?ns X__30427]
                         (clojure.core/let
                          [X__30429
                           (clojure.core/name
                            input__30171_nth_1___r___nth_0__)]
                          (if
                           (clojure.core/string? X__30429)
                           (clojure.core/let
                            [ret__30430
                             (clojure.core/re-matches #"&.*" X__30429)]
                            (if
                             (clojure.core/some? ret__30430)
                             (clojure.core/let
                              [?name ret__30430]
                              (clojure.core/let
                               [?pattern
                                input__30171_nth_1___r___nth_1__]
                               (if
                                (clojure.core/map?
                                 input__30171_nth_2__)
                                (clojure.core/let
                                 [VAL__30414
                                  (.valAt
                                   input__30171_nth_2__
                                   :aliases)]
                                 (if
                                  (clojure.core/map? VAL__30414)
                                  (clojure.core/let
                                   [X__30416
                                    (clojure.core/set VAL__30414)]
                                   (if
                                    (clojure.core/<=
                                     1
                                     (clojure.core/count X__30416))
                                    (clojure.core/loop
                                     [search_space__31200
                                      (clojure.core/seq X__30416)]
                                     (if
                                      (clojure.core/seq
                                       search_space__31200)
                                      (clojure.core/let
                                       [elem__30417
                                        (clojure.core/first
                                         search_space__31200)
                                        result__31201
                                        (clojure.core/let
                                         [elem__30417_nth_0__
                                          (clojure.core/nth
                                           elem__30417
                                           0)
                                          elem__30417_nth_1__
                                          (clojure.core/nth
                                           elem__30417
                                           1)]
                                         (if
                                          (clojure.core/symbol?
                                           elem__30417_nth_0__)
                                          (clojure.core/let
                                           [X__30419
                                            (clojure.core/name
                                             elem__30417_nth_0__)]
                                           (if
                                            (clojure.core/=
                                             ?ns
                                             X__30419)
                                            (if
                                             (clojure.core/symbol?
                                              elem__30417_nth_1__)
                                             (clojure.core/let
                                              [X__30421
                                               (clojure.core/name
                                                elem__30417_nth_1__)]
                                              (clojure.core/case
                                               X__30421
                                               ("meander.zeta")
                                               (clojure.core/let
                                                [?env
                                                 input__30171_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__11094__auto__
                                                     (CATA__FN__30216
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
                                                      CATA_RESULT__11094__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__11094__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__12034__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__12034__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__12034__auto__)))))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail))
                                            (meander.runtime.zeta/fail)))
                                          (meander.runtime.zeta/fail)))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         result__31201)
                                        (recur
                                         (clojure.core/next
                                          search_space__31200))
                                        result__31201))
                                      (state__31194)))
                                    (state__31194)))
                                  (state__31194)))
                                (state__31194))))
                             (state__31194)))
                           (state__31194)))))
                       (state__31194)))
                     (state__31194)))
                   (state__31194)))
                 (state__31194))
                (state__31194)))
              (state__31194
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/parse-seq)
                (if
                 (clojure.core/vector? input__30171_nth_1__)
                 (clojure.core/loop
                  [search_space__31203
                   (meander.match.runtime.epsilon/partitions
                    2
                    input__30171_nth_1__)]
                  (if
                   (clojure.core/seq search_space__31203)
                   (clojure.core/let
                    [input__30171_nth_1___parts__
                     (clojure.core/first search_space__31203)
                     result__31204
                     (clojure.core/let
                      [input__30171_nth_1___l__
                       (clojure.core/nth
                        input__30171_nth_1___parts__
                        0)
                       input__30171_nth_1___r__
                       (clojure.core/nth
                        input__30171_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__30171_nth_1___l__)]
                       (clojure.core/let
                        [input__30171_nth_1___r___l__
                         (clojure.core/subvec
                          input__30171_nth_1___r__
                          0
                          (clojure.core/min
                           (clojure.core/count
                            input__30171_nth_1___r__)
                           1))]
                        (if
                         (clojure.core/=
                          (clojure.core/count
                           input__30171_nth_1___r___l__)
                          1)
                         (clojure.core/let
                          [input__30171_nth_1___r___r__
                           (clojure.core/subvec
                            input__30171_nth_1___r__
                            1)]
                          (if
                           (clojure.core/=
                            input__30171_nth_1___r___l__
                            ['.])
                           (clojure.core/let
                            [?rest input__30171_nth_1___r___r__]
                            (clojure.core/let
                             [?env input__30171_nth_2__]
                             (try
                              [(clojure.core/let
                                [!xs__counter
                                 (meander.runtime.zeta/iterator !xs)]
                                (clojure.core/let
                                 [CATA_RESULT__11094__auto__
                                  (CATA__FN__30216
                                   ['meander.dev.parse.zeta/join-args
                                    (clojure.core/let
                                     [CATA_RESULT__11094__auto__
                                      (CATA__FN__30216
                                       ['meander.dev.parse.zeta/parse-seq
                                        (clojure.core/into
                                         []
                                         (clojure.core/vec
                                          (clojure.core/iterator-seq
                                           !xs__counter)))
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__11094__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__11094__auto__
                                       0)))
                                    (clojure.core/let
                                     [CATA_RESULT__11094__auto__
                                      (CATA__FN__30216
                                       ['meander.dev.parse.zeta/parse-seq
                                        ?rest
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__11094__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__11094__auto__
                                       0)))])]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   CATA_RESULT__11094__auto__)
                                  (throw (meander.runtime.zeta/fail))
                                  (clojure.core/nth
                                   CATA_RESULT__11094__auto__
                                   0))))]
                              (catch
                               java.lang.Exception
                               e__12034__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__12034__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__12034__auto__))))))
                           (meander.runtime.zeta/fail)))
                         (meander.runtime.zeta/fail)))))]
                    (if
                     (meander.runtime.zeta/fail? result__31204)
                     (recur (clojure.core/next search_space__31203))
                     result__31204))
                   (state__31195)))
                 (state__31195))
                (state__31195)))
              (state__31195
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__30171_nth_1__)
                 (clojure.core/loop
                  [search_space__31206
                   (meander.match.runtime.epsilon/partitions
                    2
                    input__30171_nth_1__)]
                  (if
                   (clojure.core/seq search_space__31206)
                   (clojure.core/let
                    [input__30171_nth_1___parts__
                     (clojure.core/first search_space__31206)
                     result__31207
                     (clojure.core/let
                      [input__30171_nth_1___l__
                       (clojure.core/nth
                        input__30171_nth_1___parts__
                        0)
                       input__30171_nth_1___r__
                       (clojure.core/nth
                        input__30171_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__30171_nth_1___l__)]
                       (clojure.core/let
                        [input__30171_nth_1___r___l__
                         (clojure.core/subvec
                          input__30171_nth_1___r__
                          0
                          (clojure.core/min
                           (clojure.core/count
                            input__30171_nth_1___r__)
                           1))]
                        (if
                         (clojure.core/=
                          (clojure.core/count
                           input__30171_nth_1___r___l__)
                          1)
                         (clojure.core/let
                          [input__30171_nth_1___r___r__
                           (clojure.core/subvec
                            input__30171_nth_1___r__
                            1)]
                          (if
                           (clojure.core/=
                            input__30171_nth_1___r___l__
                            ['.])
                           (clojure.core/let
                            [?rest input__30171_nth_1___r___r__]
                            (clojure.core/let
                             [?env input__30171_nth_2__]
                             (try
                              [(clojure.core/let
                                [!xs__counter
                                 (meander.runtime.zeta/iterator !xs)]
                                (clojure.core/let
                                 [CATA_RESULT__11094__auto__
                                  (CATA__FN__30216
                                   ['meander.dev.parse.zeta/string-join-args
                                    (clojure.core/let
                                     [CATA_RESULT__11094__auto__
                                      (CATA__FN__30216
                                       ['meander.dev.parse.zeta/parse-string
                                        (clojure.core/into
                                         []
                                         (clojure.core/vec
                                          (clojure.core/iterator-seq
                                           !xs__counter)))
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__11094__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__11094__auto__
                                       0)))
                                    (clojure.core/let
                                     [CATA_RESULT__11094__auto__
                                      (CATA__FN__30216
                                       ['meander.dev.parse.zeta/parse-string
                                        ?rest
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__11094__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__11094__auto__
                                       0)))])]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   CATA_RESULT__11094__auto__)
                                  (throw (meander.runtime.zeta/fail))
                                  (clojure.core/nth
                                   CATA_RESULT__11094__auto__
                                   0))))]
                              (catch
                               java.lang.Exception
                               e__12034__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__12034__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__12034__auto__))))))
                           (meander.runtime.zeta/fail)))
                         (meander.runtime.zeta/fail)))))]
                    (if
                     (meander.runtime.zeta/fail? result__31207)
                     (recur (clojure.core/next search_space__31206))
                     result__31207))
                   (state__31196)))
                 (state__31196))
                (state__31196)))
              (state__31196
               []
               (if
                (clojure.core/let
                 [x__8851__auto__ input__30171_nth_0__]
                 (clojure.core/case
                  x__8851__auto__
                  (meander.dev.parse.zeta/parse-seq
                   meander.dev.parse.zeta/parse-string)
                  true
                  false))
                (clojure.core/let
                 [?rule-name input__30171_nth_0__]
                 (if
                  (clojure.core/vector? input__30171_nth_1__)
                  (clojure.core/let
                   [input__30171_nth_1___l__
                    (clojure.core/subvec
                     input__30171_nth_1__
                     0
                     (clojure.core/min
                      (clojure.core/count input__30171_nth_1__)
                      1))]
                   (if
                    (clojure.core/=
                     (clojure.core/count input__30171_nth_1___l__)
                     1)
                    (clojure.core/let
                     [input__30171_nth_1___r__
                      (clojure.core/subvec input__30171_nth_1__ 1)]
                     (if
                      (clojure.core/= input__30171_nth_1___l__ ['...])
                      (clojure.core/let
                       [?rest input__30171_nth_1___r__]
                       (clojure.core/let
                        [?env input__30171_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__11094__auto__
                            (CATA__FN__30216 [?rule-name ?rest ?env])]
                           (if
                            (meander.runtime.zeta/fail?
                             CATA_RESULT__11094__auto__)
                            (throw (meander.runtime.zeta/fail))
                            (clojure.core/nth
                             CATA_RESULT__11094__auto__
                             0)))]
                         (catch
                          java.lang.Exception
                          e__12034__auto__
                          (if
                           (meander.runtime.zeta/fail?
                            e__12034__auto__)
                           (meander.runtime.zeta/fail)
                           (throw e__12034__auto__))))))
                      (state__31150)))
                    (state__31150)))
                  (state__31150)))
                (state__31150)))]
             (state__31192)))
           (state__31150))
          (state__31150)))
        (state__31150
         []
         (clojure.core/letfn
          [(def__30447
            [arg__30464]
            (clojure.core/letfn
             [(state__31209
               []
               (if
                (clojure.core/=
                 arg__30464
                 'meander.dev.parse.zeta/parse-string)
                (clojure.core/let
                 [?rule-name arg__30464]
                 (clojure.core/let
                  [x__30465 'meander.dev.parse.zeta/string-star-args]
                  (clojure.core/let
                   [?star-name x__30465]
                   [?star-name ?rule-name])))
                (state__31210)))
              (state__31210
               []
               (if
                (clojure.core/=
                 arg__30464
                 'meander.dev.parse.zeta/parse-seq)
                (clojure.core/let
                 [?rule-name arg__30464]
                 (clojure.core/let
                  [x__30466 'meander.dev.parse.zeta/star-args]
                  (clojure.core/let
                   [?star-name x__30466]
                   [?star-name ?rule-name])))
                (meander.runtime.zeta/fail)))]
             (state__31209)))]
          (if
           (clojure.core/vector? input__30171)
           (if
            (clojure.core/= (clojure.core/count input__30171) 3)
            (clojure.core/let
             [input__30171_nth_0__
              (clojure.core/nth input__30171 0)
              input__30171_nth_1__
              (clojure.core/nth input__30171 1)
              input__30171_nth_2__
              (clojure.core/nth input__30171 2)]
             (clojure.core/let
              [x__9791__auto__ (def__30447 input__30171_nth_0__)]
              (if
               (meander.runtime.zeta/fail? x__9791__auto__)
               (state__31151)
               (clojure.core/let
                [[?star-name ?rule-name] x__9791__auto__]
                (if
                 (clojure.core/vector? input__30171_nth_1__)
                 (clojure.core/loop
                  [search_space__31211
                   (meander.match.runtime.epsilon/partitions
                    2
                    input__30171_nth_1__)]
                  (if
                   (clojure.core/seq search_space__31211)
                   (clojure.core/let
                    [input__30171_nth_1___parts__
                     (clojure.core/first search_space__31211)
                     result__31212
                     (clojure.core/let
                      [input__30171_nth_1___l__
                       (clojure.core/nth
                        input__30171_nth_1___parts__
                        0)
                       input__30171_nth_1___r__
                       (clojure.core/nth
                        input__30171_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs []]
                       (clojure.core/let
                        [ret__9955__auto__
                         (meander.runtime.zeta/epsilon-run-star-1
                          input__30171_nth_1___l__
                          [!xs]
                          (clojure.core/fn
                           [[!xs] input__30457]
                           (clojure.core/let
                            [input__30457_nth_0__
                             (clojure.core/nth input__30457 0)]
                            (clojure.core/letfn
                             [(save__30458
                               []
                               (meander.runtime.zeta/fail))
                              (f__31215
                               []
                               (clojure.core/let
                                [!xs
                                 (clojure.core/conj
                                  !xs
                                  input__30457_nth_0__)]
                                [!xs]))]
                             (if
                              (clojure.core/symbol?
                               input__30457_nth_0__)
                              (clojure.core/let
                               [X__30460
                                (clojure.core/namespace
                                 input__30457_nth_0__)]
                               (clojure.core/case
                                X__30460
                                (nil)
                                (clojure.core/let
                                 [X__30462
                                  (clojure.core/name
                                   input__30457_nth_0__)]
                                 (if
                                  (clojure.core/string? X__30462)
                                  (if
                                   (clojure.core/re-matches
                                    #"\.\.(?:\.|\d+)"
                                    X__30462)
                                   (save__30458)
                                   (f__31215))
                                  (f__31215)))
                                (f__31215)))
                              (f__31215)))))
                          (clojure.core/fn
                           [[!xs]]
                           (clojure.core/let
                            [input__30171_nth_1___r___l__
                             (clojure.core/subvec
                              input__30171_nth_1___r__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__30171_nth_1___r__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__30171_nth_1___r___l__)
                              1)
                             (clojure.core/let
                              [input__30171_nth_1___r___r__
                               (clojure.core/subvec
                                input__30171_nth_1___r__
                                1)]
                              (if
                               (clojure.core/=
                                input__30171_nth_1___r___l__
                                ['...])
                               (clojure.core/let
                                [?rest input__30171_nth_1___r___r__]
                                (clojure.core/let
                                 [?env input__30171_nth_2__]
                                 (try
                                  [(clojure.core/let
                                    [!xs__counter
                                     (meander.runtime.zeta/iterator
                                      !xs)]
                                    (clojure.core/let
                                     [CATA_RESULT__11094__auto__
                                      (CATA__FN__30216
                                       [?star-name
                                        (clojure.core/let
                                         [CATA_RESULT__11094__auto__
                                          (CATA__FN__30216
                                           [?rule-name
                                            (clojure.core/into
                                             []
                                             (clojure.core/vec
                                              (clojure.core/iterator-seq
                                               !xs__counter)))
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__11094__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__11094__auto__
                                           0)))
                                        (clojure.core/let
                                         [CATA_RESULT__11094__auto__
                                          (CATA__FN__30216
                                           [?rule-name ?rest ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__11094__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__11094__auto__
                                           0)))])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__11094__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__11094__auto__
                                       0))))]
                                  (catch
                                   java.lang.Exception
                                   e__12034__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__12034__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__12034__auto__))))))
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail)))))]
                        (if
                         (meander.runtime.zeta/fail? ret__9955__auto__)
                         (meander.runtime.zeta/fail)
                         ret__9955__auto__))))]
                    (if
                     (meander.runtime.zeta/fail? result__31212)
                     (recur (clojure.core/next search_space__31211))
                     result__31212))
                   (state__31151)))
                 (state__31151))))))
            (state__31151))
           (state__31151))))
        (state__31151
         []
         (if
          (clojure.core/vector? input__30171)
          (if
           (clojure.core/= (clojure.core/count input__30171) 3)
           (clojure.core/let
            [input__30171_nth_0__
             (clojure.core/nth input__30171 0)
             input__30171_nth_1__
             (clojure.core/nth input__30171 1)
             input__30171_nth_2__
             (clojure.core/nth input__30171 2)]
            (clojure.core/letfn
             [(state__31216
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/star-args)
                (if
                 (clojure.core/map? input__30171_nth_1__)
                 (clojure.core/let
                  [VAL__30478 (.valAt input__30171_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__30478
                   (:cat)
                   (clojure.core/let
                    [VAL__30479
                     (.valAt input__30171_nth_1__ :sequence)]
                    (if
                     (clojure.core/vector? VAL__30479)
                     (if
                      (clojure.core/=
                       (clojure.core/count VAL__30479)
                       1)
                      (clojure.core/let
                       [VAL__30479_nth_0__
                        (clojure.core/nth VAL__30479 0)]
                       (if
                        (clojure.core/map? VAL__30479_nth_0__)
                        (clojure.core/let
                         [VAL__30484 (.valAt VAL__30479_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__30484
                          (:memory-variable)
                          (clojure.core/let
                           [?memory-variable VAL__30479_nth_0__]
                           (clojure.core/let
                            [VAL__30480
                             (.valAt input__30171_nth_1__ :next)]
                            (if
                             (clojure.core/map? VAL__30480)
                             (clojure.core/let
                              [VAL__30481 (.valAt VAL__30480 :tag)]
                              (clojure.core/case
                               VAL__30481
                               (:empty)
                               (clojure.core/let
                                [?next input__30171_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [CATA_RESULT__11094__auto__
                                    (CATA__FN__30216
                                     ['meander.dev.parse.zeta/join-args
                                      {:tag :into,
                                       :memory-variable
                                       ?memory-variable}
                                      ?next])]
                                   (if
                                    (meander.runtime.zeta/fail?
                                     CATA_RESULT__11094__auto__)
                                    (throw (meander.runtime.zeta/fail))
                                    (clojure.core/nth
                                     CATA_RESULT__11094__auto__
                                     0)))]
                                 (catch
                                  java.lang.Exception
                                  e__12034__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__12034__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__12034__auto__)))))
                               (state__31217)))
                             (state__31217))))
                          (state__31217)))
                        (state__31217)))
                      (state__31217))
                     (state__31217)))
                   (state__31217)))
                 (state__31217))
                (state__31217)))
              (state__31217
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/star-args)
                (clojure.core/let
                 [?pattern input__30171_nth_1__]
                 (clojure.core/let
                  [?next input__30171_nth_2__]
                  (try
                   [{:tag :star, :pattern ?pattern, :next ?next}]
                   (catch
                    java.lang.Exception
                    e__12034__auto__
                    (if
                     (meander.runtime.zeta/fail? e__12034__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__12034__auto__))))))
                (state__31218)))
              (state__31218
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/string-star-args)
                (if
                 (clojure.core/map? input__30171_nth_1__)
                 (clojure.core/let
                  [VAL__30489 (.valAt input__30171_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__30489
                   (:string-cat)
                   (clojure.core/let
                    [VAL__30490
                     (.valAt input__30171_nth_1__ :sequence)]
                    (if
                     (clojure.core/vector? VAL__30490)
                     (if
                      (clojure.core/=
                       (clojure.core/count VAL__30490)
                       1)
                      (clojure.core/let
                       [VAL__30490_nth_0__
                        (clojure.core/nth VAL__30490 0)]
                       (if
                        (clojure.core/map? VAL__30490_nth_0__)
                        (clojure.core/let
                         [VAL__30495 (.valAt VAL__30490_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__30495
                          (:memory-variable)
                          (clojure.core/let
                           [?memory-variable VAL__30490_nth_0__]
                           (clojure.core/let
                            [VAL__30491
                             (.valAt input__30171_nth_1__ :next)]
                            (if
                             (clojure.core/map? VAL__30491)
                             (clojure.core/let
                              [VAL__30492 (.valAt VAL__30491 :tag)]
                              (clojure.core/case
                               VAL__30492
                               (:empty)
                               (clojure.core/let
                                [?next input__30171_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [CATA_RESULT__11094__auto__
                                    (CATA__FN__30216
                                     ['meander.dev.parse.zeta/string-join-args
                                      {:tag :into,
                                       :memory-variable
                                       ?memory-variable}
                                      ?next])]
                                   (if
                                    (meander.runtime.zeta/fail?
                                     CATA_RESULT__11094__auto__)
                                    (throw (meander.runtime.zeta/fail))
                                    (clojure.core/nth
                                     CATA_RESULT__11094__auto__
                                     0)))]
                                 (catch
                                  java.lang.Exception
                                  e__12034__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__12034__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__12034__auto__)))))
                               (state__31219)))
                             (state__31219))))
                          (state__31219)))
                        (state__31219)))
                      (state__31219))
                     (state__31219)))
                   (state__31219)))
                 (state__31219))
                (state__31219)))
              (state__31219
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/string-star-args)
                (clojure.core/let
                 [?pattern input__30171_nth_1__]
                 (clojure.core/let
                  [?next input__30171_nth_2__]
                  (try
                   [{:tag :string-star,
                     :pattern ?pattern,
                     :next ?next}]
                   (catch
                    java.lang.Exception
                    e__12034__auto__
                    (if
                     (meander.runtime.zeta/fail? e__12034__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__12034__auto__))))))
                (state__31220)))
              (state__31220
               []
               (if
                (clojure.core/let
                 [x__8851__auto__ input__30171_nth_0__]
                 (clojure.core/case
                  x__8851__auto__
                  (meander.dev.parse.zeta/parse-seq
                   meander.dev.parse.zeta/parse-string)
                  true
                  false))
                (clojure.core/letfn
                 [(state__31241
                   []
                   (if
                    (clojure.core/vector? input__30171_nth_1__)
                    (clojure.core/let
                     [input__30171_nth_1___l__
                      (clojure.core/subvec
                       input__30171_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__30171_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__30171_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__30171_nth_1___r__
                        (clojure.core/subvec input__30171_nth_1__ 1)]
                       (clojure.core/let
                        [input__30171_nth_1___l___nth_0__
                         (clojure.core/nth input__30171_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__30171_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__30503
                           (clojure.core/namespace
                            input__30171_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__30503
                           (nil)
                           (clojure.core/let
                            [X__30505
                             (clojure.core/name
                              input__30171_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__30505)
                             (clojure.core/let
                              [ret__30506
                               (clojure.core/re-matches
                                #"\.\.(\d+)"
                                X__30505)]
                              (if
                               (clojure.core/some? ret__30506)
                               (if
                                (clojure.core/vector? ret__30506)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__30506)
                                  2)
                                 (clojure.core/let
                                  [ret__30506_nth_1__
                                   (clojure.core/nth ret__30506 1)]
                                  (clojure.core/let
                                   [?n ret__30506_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__30171_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__30171_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__30171_nth_2__]
                                      (try
                                       [{:tag :syntax-error,
                                         :message
                                         "The n or more operator ..N must be preceeded by at least one pattern"}]
                                       (catch
                                        java.lang.Exception
                                        e__12034__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__12034__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__12034__auto__)))))))))
                                 (state__31242))
                                (state__31242))
                               (state__31242)))
                             (state__31242)))
                           (state__31242)))
                         (state__31242))))
                      (state__31242)))
                    (state__31242)))
                  (state__31242
                   []
                   (clojure.core/let
                    [?rule-name input__30171_nth_0__]
                    (if
                     (clojure.core/vector? input__30171_nth_1__)
                     (clojure.core/loop
                      [search_space__31247
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__30171_nth_1__)]
                      (if
                       (clojure.core/seq search_space__31247)
                       (clojure.core/let
                        [input__30171_nth_1___parts__
                         (clojure.core/first search_space__31247)
                         result__31248
                         (clojure.core/let
                          [input__30171_nth_1___l__
                           (clojure.core/nth
                            input__30171_nth_1___parts__
                            0)
                           input__30171_nth_1___r__
                           (clojure.core/nth
                            input__30171_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__9955__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__30171_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__30522]
                               (clojure.core/let
                                [input__30522_nth_0__
                                 (clojure.core/nth input__30522 0)]
                                (clojure.core/letfn
                                 [(save__30523
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__31251
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__30522_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__30522_nth_0__)
                                  (clojure.core/let
                                   [X__30525
                                    (clojure.core/namespace
                                     input__30522_nth_0__)]
                                   (clojure.core/case
                                    X__30525
                                    (nil)
                                    (clojure.core/let
                                     [X__30527
                                      (clojure.core/name
                                       input__30522_nth_0__)]
                                     (if
                                      (clojure.core/string? X__30527)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__30527)
                                       (save__30523)
                                       (f__31251))
                                      (f__31251)))
                                    (f__31251)))
                                  (f__31251)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__30171_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__30171_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__30171_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__30171_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__30171_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__30171_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__30171_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__30171_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__30171_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__30516
                                      (clojure.core/namespace
                                       input__30171_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__30516
                                      (nil)
                                      (clojure.core/let
                                       [X__30518
                                        (clojure.core/name
                                         input__30171_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string? X__30518)
                                        (clojure.core/let
                                         [ret__30519
                                          (clojure.core/re-matches
                                           #"\.\.(\d+)"
                                           X__30518)]
                                         (if
                                          (clojure.core/some?
                                           ret__30519)
                                          (if
                                           (clojure.core/vector?
                                            ret__30519)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__30519)
                                             2)
                                            (clojure.core/let
                                             [ret__30519_nth_1__
                                              (clojure.core/nth
                                               ret__30519
                                               1)]
                                             (clojure.core/let
                                              [?n ret__30519_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__30171_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__30171_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   {:tag :plus,
                                                    :n (Integer. ?n),
                                                    :pattern
                                                    (clojure.core/let
                                                     [CATA_RESULT__11094__auto__
                                                      (CATA__FN__30216
                                                       [?rule-name
                                                        (clojure.core/into
                                                         []
                                                         (clojure.core/vec
                                                          (clojure.core/iterator-seq
                                                           !xs__counter)))
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__11094__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__11094__auto__
                                                       0))),
                                                    :next
                                                    (clojure.core/let
                                                     [CATA_RESULT__11094__auto__
                                                      (CATA__FN__30216
                                                       [?rule-name
                                                        ?rest
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__11094__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__11094__auto__
                                                       0)))})]
                                                 (catch
                                                  java.lang.Exception
                                                  e__12034__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__12034__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__12034__auto__))))))))
                                            (meander.runtime.zeta/fail))
                                           (meander.runtime.zeta/fail))
                                          (meander.runtime.zeta/fail)))
                                        (meander.runtime.zeta/fail)))
                                      (meander.runtime.zeta/fail)))
                                    (meander.runtime.zeta/fail))))
                                 (meander.runtime.zeta/fail)))))]
                            (if
                             (meander.runtime.zeta/fail?
                              ret__9955__auto__)
                             (meander.runtime.zeta/fail)
                             ret__9955__auto__))))]
                        (if
                         (meander.runtime.zeta/fail? result__31248)
                         (recur
                          (clojure.core/next search_space__31247))
                         result__31248))
                       (state__31243)))
                     (state__31243))))
                  (state__31243
                   []
                   (if
                    (clojure.core/vector? input__30171_nth_1__)
                    (clojure.core/let
                     [input__30171_nth_1___l__
                      (clojure.core/subvec
                       input__30171_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__30171_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__30171_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__30171_nth_1___r__
                        (clojure.core/subvec input__30171_nth_1__ 1)]
                       (clojure.core/let
                        [input__30171_nth_1___l___nth_0__
                         (clojure.core/nth input__30171_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__30171_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__30534
                           (clojure.core/namespace
                            input__30171_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__30534
                           (nil)
                           (clojure.core/let
                            [X__30536
                             (clojure.core/name
                              input__30171_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__30536)
                             (clojure.core/let
                              [ret__30537
                               (clojure.core/re-matches
                                #"\.\.(\?.+)"
                                X__30536)]
                              (if
                               (clojure.core/some? ret__30537)
                               (if
                                (clojure.core/vector? ret__30537)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__30537)
                                  2)
                                 (clojure.core/let
                                  [ret__30537_nth_1__
                                   (clojure.core/nth ret__30537 1)]
                                  (clojure.core/let
                                   [?n ret__30537_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__30171_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__30171_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__30171_nth_2__]
                                      (try
                                       [{:tag :syntax-error,
                                         :message
                                         "The ?n or more operator ..?n must be preceeded by at least one pattern"}]
                                       (catch
                                        java.lang.Exception
                                        e__12034__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__12034__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__12034__auto__)))))))))
                                 (state__31244))
                                (state__31244))
                               (state__31244)))
                             (state__31244)))
                           (state__31244)))
                         (state__31244))))
                      (state__31244)))
                    (state__31244)))
                  (state__31244
                   []
                   (clojure.core/let
                    [?rule-name input__30171_nth_0__]
                    (if
                     (clojure.core/vector? input__30171_nth_1__)
                     (clojure.core/loop
                      [search_space__31252
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__30171_nth_1__)]
                      (if
                       (clojure.core/seq search_space__31252)
                       (clojure.core/let
                        [input__30171_nth_1___parts__
                         (clojure.core/first search_space__31252)
                         result__31253
                         (clojure.core/let
                          [input__30171_nth_1___l__
                           (clojure.core/nth
                            input__30171_nth_1___parts__
                            0)
                           input__30171_nth_1___r__
                           (clojure.core/nth
                            input__30171_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__9955__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__30171_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__30553]
                               (clojure.core/let
                                [input__30553_nth_0__
                                 (clojure.core/nth input__30553 0)]
                                (clojure.core/letfn
                                 [(save__30554
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__31256
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__30553_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__30553_nth_0__)
                                  (clojure.core/let
                                   [X__30556
                                    (clojure.core/namespace
                                     input__30553_nth_0__)]
                                   (clojure.core/case
                                    X__30556
                                    (nil)
                                    (clojure.core/let
                                     [X__30558
                                      (clojure.core/name
                                       input__30553_nth_0__)]
                                     (if
                                      (clojure.core/string? X__30558)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__30558)
                                       (save__30554)
                                       (f__31256))
                                      (f__31256)))
                                    (f__31256)))
                                  (f__31256)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__30171_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__30171_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__30171_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__30171_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__30171_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__30171_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__30171_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__30171_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__30171_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__30547
                                      (clojure.core/namespace
                                       input__30171_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__30547
                                      (nil)
                                      (clojure.core/let
                                       [X__30549
                                        (clojure.core/name
                                         input__30171_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string? X__30549)
                                        (clojure.core/let
                                         [ret__30550
                                          (clojure.core/re-matches
                                           #"\.\.(\?.+)"
                                           X__30549)]
                                         (if
                                          (clojure.core/some?
                                           ret__30550)
                                          (if
                                           (clojure.core/vector?
                                            ret__30550)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__30550)
                                             2)
                                            (clojure.core/let
                                             [ret__30550_nth_1__
                                              (clojure.core/nth
                                               ret__30550
                                               1)]
                                             (clojure.core/let
                                              [?n ret__30550_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__30171_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__30171_nth_2__]
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
                                                     [CATA_RESULT__11094__auto__
                                                      (CATA__FN__30216
                                                       [?rule-name
                                                        (clojure.core/into
                                                         []
                                                         (clojure.core/vec
                                                          (clojure.core/iterator-seq
                                                           !xs__counter)))
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__11094__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__11094__auto__
                                                       0))),
                                                    :next
                                                    (clojure.core/let
                                                     [CATA_RESULT__11094__auto__
                                                      (CATA__FN__30216
                                                       [?rule-name
                                                        ?rest
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__11094__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__11094__auto__
                                                       0)))})]
                                                 (catch
                                                  java.lang.Exception
                                                  e__12034__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__12034__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__12034__auto__))))))))
                                            (meander.runtime.zeta/fail))
                                           (meander.runtime.zeta/fail))
                                          (meander.runtime.zeta/fail)))
                                        (meander.runtime.zeta/fail)))
                                      (meander.runtime.zeta/fail)))
                                    (meander.runtime.zeta/fail))))
                                 (meander.runtime.zeta/fail)))))]
                            (if
                             (meander.runtime.zeta/fail?
                              ret__9955__auto__)
                             (meander.runtime.zeta/fail)
                             ret__9955__auto__))))]
                        (if
                         (meander.runtime.zeta/fail? result__31253)
                         (recur
                          (clojure.core/next search_space__31252))
                         result__31253))
                       (state__31245)))
                     (state__31245))))
                  (state__31245
                   []
                   (if
                    (clojure.core/vector? input__30171_nth_1__)
                    (clojure.core/let
                     [input__30171_nth_1___l__
                      (clojure.core/subvec
                       input__30171_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__30171_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__30171_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__30171_nth_1___r__
                        (clojure.core/subvec input__30171_nth_1__ 1)]
                       (clojure.core/let
                        [input__30171_nth_1___l___nth_0__
                         (clojure.core/nth input__30171_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__30171_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__30565
                           (clojure.core/namespace
                            input__30171_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__30565
                           (nil)
                           (clojure.core/let
                            [X__30567
                             (clojure.core/name
                              input__30171_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__30567)
                             (clojure.core/let
                              [ret__30568
                               (clojure.core/re-matches
                                #"\.\.(!.+)"
                                X__30567)]
                              (if
                               (clojure.core/some? ret__30568)
                               (if
                                (clojure.core/vector? ret__30568)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__30568)
                                  2)
                                 (clojure.core/let
                                  [ret__30568_nth_1__
                                   (clojure.core/nth ret__30568 1)]
                                  (clojure.core/let
                                   [?n ret__30568_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__30171_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__30171_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__30171_nth_2__]
                                      (try
                                       [{:tag :syntax-error,
                                         :message
                                         "The operator ..!n must be preceeded by at least one pattern"}]
                                       (catch
                                        java.lang.Exception
                                        e__12034__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__12034__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__12034__auto__)))))))))
                                 (state__31246))
                                (state__31246))
                               (state__31246)))
                             (state__31246)))
                           (state__31246)))
                         (state__31246))))
                      (state__31246)))
                    (state__31246)))
                  (state__31246
                   []
                   (clojure.core/let
                    [?rule-name input__30171_nth_0__]
                    (if
                     (clojure.core/vector? input__30171_nth_1__)
                     (clojure.core/loop
                      [search_space__31257
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__30171_nth_1__)]
                      (if
                       (clojure.core/seq search_space__31257)
                       (clojure.core/let
                        [input__30171_nth_1___parts__
                         (clojure.core/first search_space__31257)
                         result__31258
                         (clojure.core/let
                          [input__30171_nth_1___l__
                           (clojure.core/nth
                            input__30171_nth_1___parts__
                            0)
                           input__30171_nth_1___r__
                           (clojure.core/nth
                            input__30171_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__9955__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__30171_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__30584]
                               (clojure.core/let
                                [input__30584_nth_0__
                                 (clojure.core/nth input__30584 0)]
                                (clojure.core/letfn
                                 [(save__30585
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__31261
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__30584_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__30584_nth_0__)
                                  (clojure.core/let
                                   [X__30587
                                    (clojure.core/namespace
                                     input__30584_nth_0__)]
                                   (clojure.core/case
                                    X__30587
                                    (nil)
                                    (clojure.core/let
                                     [X__30589
                                      (clojure.core/name
                                       input__30584_nth_0__)]
                                     (if
                                      (clojure.core/string? X__30589)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__30589)
                                       (save__30585)
                                       (f__31261))
                                      (f__31261)))
                                    (f__31261)))
                                  (f__31261)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__30171_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__30171_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__30171_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__30171_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__30171_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__30171_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__30171_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__30171_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__30171_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__30578
                                      (clojure.core/namespace
                                       input__30171_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__30578
                                      (nil)
                                      (clojure.core/let
                                       [X__30580
                                        (clojure.core/name
                                         input__30171_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string? X__30580)
                                        (clojure.core/let
                                         [ret__30581
                                          (clojure.core/re-matches
                                           #"\.\.(\!.+)"
                                           X__30580)]
                                         (if
                                          (clojure.core/some?
                                           ret__30581)
                                          (if
                                           (clojure.core/vector?
                                            ret__30581)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__30581)
                                             2)
                                            (clojure.core/let
                                             [ret__30581_nth_1__
                                              (clojure.core/nth
                                               ret__30581
                                               1)]
                                             (clojure.core/let
                                              [?n ret__30581_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__30171_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__30171_nth_2__]
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
                                                     [CATA_RESULT__11094__auto__
                                                      (CATA__FN__30216
                                                       [?rule-name
                                                        (clojure.core/into
                                                         []
                                                         (clojure.core/vec
                                                          (clojure.core/iterator-seq
                                                           !xs__counter)))
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__11094__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__11094__auto__
                                                       0))),
                                                    :next
                                                    (clojure.core/let
                                                     [CATA_RESULT__11094__auto__
                                                      (CATA__FN__30216
                                                       [?rule-name
                                                        ?rest
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__11094__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__11094__auto__
                                                       0)))})]
                                                 (catch
                                                  java.lang.Exception
                                                  e__12034__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__12034__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__12034__auto__))))))))
                                            (meander.runtime.zeta/fail))
                                           (meander.runtime.zeta/fail))
                                          (meander.runtime.zeta/fail)))
                                        (meander.runtime.zeta/fail)))
                                      (meander.runtime.zeta/fail)))
                                    (meander.runtime.zeta/fail))))
                                 (meander.runtime.zeta/fail)))))]
                            (if
                             (meander.runtime.zeta/fail?
                              ret__9955__auto__)
                             (meander.runtime.zeta/fail)
                             ret__9955__auto__))))]
                        (if
                         (meander.runtime.zeta/fail? result__31258)
                         (recur
                          (clojure.core/next search_space__31257))
                         result__31258))
                       (state__31221)))
                     (state__31221))))]
                 (state__31241))
                (state__31221)))
              (state__31221
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/parse-seq)
                (if
                 (clojure.core/vector? input__30171_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__30171_nth_1__)]
                  (clojure.core/let
                   [?env input__30171_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__11094__auto__
                        (CATA__FN__30216
                         ['meander.dev.parse.zeta/cat-args
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__30217 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__30217
                               (clojure.core/let
                                [CATA_RESULT__11094__auto__
                                 (CATA__FN__30216
                                  [(if
                                    (.hasNext !xs__counter)
                                    (.next !xs__counter))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__11094__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__11094__auto__
                                  0)))))
                             (clojure.core/persistent!
                              return__30217))))
                          {:tag :empty}])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__11094__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__11094__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__12034__auto__
                     (if
                      (meander.runtime.zeta/fail? e__12034__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__12034__auto__))))))
                 (state__31222))
                (state__31222)))
              (state__31222
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__30171_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__30171_nth_1__)]
                  (clojure.core/let
                   [?env input__30171_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__11094__auto__
                        (CATA__FN__30216
                         ['meander.dev.parse.zeta/string-cat-args
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__30218 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__30218
                               (clojure.core/let
                                [CATA_RESULT__11094__auto__
                                 (CATA__FN__30216
                                  [(if
                                    (.hasNext !xs__counter)
                                    (.next !xs__counter))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__11094__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__11094__auto__
                                  0)))))
                             (clojure.core/persistent!
                              return__30218))))
                          {:tag :empty}])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__11094__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__11094__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__12034__auto__
                     (if
                      (meander.runtime.zeta/fail? e__12034__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__12034__auto__))))))
                 (state__31223))
                (state__31223)))
              (state__31223
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/cat-args)
                (if
                 (clojure.core/vector? input__30171_nth_1__)
                 (clojure.core/let
                  [!forms []]
                  (clojure.core/let
                   [ret__9955__auto__
                    (meander.runtime.zeta/epsilon-run-star-1
                     input__30171_nth_1__
                     [!forms]
                     (clojure.core/fn
                      [[!forms] input__30604]
                      (clojure.core/let
                       [input__30604_nth_0__
                        (clojure.core/nth input__30604 0)]
                       (if
                        (clojure.core/map? input__30604_nth_0__)
                        (clojure.core/let
                         [VAL__30605
                          (.valAt input__30604_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__30605
                          (:literal)
                          (clojure.core/let
                           [VAL__30606
                            (.valAt input__30604_nth_0__ :form)]
                           (clojure.core/let
                            [!forms
                             (clojure.core/conj !forms VAL__30606)]
                            [!forms]))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail))))
                     (clojure.core/fn
                      [[!forms]]
                      (if
                       (clojure.core/map? input__30171_nth_2__)
                       (clojure.core/let
                        [VAL__30601 (.valAt input__30171_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__30601
                         (:empty)
                         (try
                          [{:tag :literal,
                            :form (clojure.core/into [] !forms)}]
                          (catch
                           java.lang.Exception
                           e__12034__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__12034__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__12034__auto__))))
                         (state__31224)))
                       (state__31224))))]
                   (if
                    (meander.runtime.zeta/fail? ret__9955__auto__)
                    (state__31224)
                    ret__9955__auto__)))
                 (state__31224))
                (state__31224)))
              (state__31224
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/cat-args)
                (clojure.core/let
                 [?sequence input__30171_nth_1__]
                 (clojure.core/let
                  [?next input__30171_nth_2__]
                  (try
                   [{:tag :cat, :sequence ?sequence, :next ?next}]
                   (catch
                    java.lang.Exception
                    e__12034__auto__
                    (if
                     (meander.runtime.zeta/fail? e__12034__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__12034__auto__))))))
                (state__31225)))
              (state__31225
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (if
                 (clojure.core/map? input__30171_nth_1__)
                 (clojure.core/let
                  [VAL__30612 (.valAt input__30171_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__30612
                   (:cat)
                   (clojure.core/let
                    [VAL__30613
                     (.valAt input__30171_nth_1__ :sequence)]
                    (clojure.core/let
                     [?sequence VAL__30613]
                     (clojure.core/let
                      [VAL__30614 (.valAt input__30171_nth_1__ :next)]
                      (if
                       (clojure.core/map? VAL__30614)
                       (clojure.core/let
                        [VAL__30615 (.valAt VAL__30614 :tag)]
                        (clojure.core/case
                         VAL__30615
                         (:empty)
                         (clojure.core/let
                          [?right input__30171_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__11094__auto__
                              (CATA__FN__30216
                               ['meander.dev.parse.zeta/cat-args
                                ?sequence
                                ?right])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__11094__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__11094__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__12034__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__12034__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__12034__auto__)))))
                         (state__31226)))
                       (state__31226)))))
                   (state__31226)))
                 (state__31226))
                (state__31226)))
              (state__31226
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (clojure.core/let
                 [?left input__30171_nth_1__]
                 (if
                  (clojure.core/map? input__30171_nth_2__)
                  (clojure.core/let
                   [VAL__30618 (.valAt input__30171_nth_2__ :tag)]
                   (clojure.core/case
                    VAL__30618
                    (:empty)
                    (try
                     [?left]
                     (catch
                      java.lang.Exception
                      e__12034__auto__
                      (if
                       (meander.runtime.zeta/fail? e__12034__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__12034__auto__))))
                    (state__31227)))
                  (state__31227)))
                (state__31227)))
              (state__31227
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (if
                 (clojure.core/map? input__30171_nth_1__)
                 (clojure.core/let
                  [VAL__30621 (.valAt input__30171_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__30621
                   (:empty)
                   (clojure.core/let
                    [?right input__30171_nth_2__]
                    (try
                     [?right]
                     (catch
                      java.lang.Exception
                      e__12034__auto__
                      (if
                       (meander.runtime.zeta/fail? e__12034__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__12034__auto__)))))
                   (state__31228)))
                 (state__31228))
                (state__31228)))
              (state__31228
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (clojure.core/let
                 [?left input__30171_nth_1__]
                 (clojure.core/let
                  [?right input__30171_nth_2__]
                  (try
                   [{:tag :join, :left ?left, :right ?right}]
                   (catch
                    java.lang.Exception
                    e__12034__auto__
                    (if
                     (meander.runtime.zeta/fail? e__12034__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__12034__auto__))))))
                (state__31229)))
              (state__31229
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (if
                 (clojure.core/vector? input__30171_nth_1__)
                 (clojure.core/let
                  [!forms []]
                  (clojure.core/let
                   [ret__9955__auto__
                    (meander.runtime.zeta/epsilon-run-star-1
                     input__30171_nth_1__
                     [!forms]
                     (clojure.core/fn
                      [[!forms] input__30629]
                      (clojure.core/let
                       [input__30629_nth_0__
                        (clojure.core/nth input__30629 0)]
                       (if
                        (clojure.core/map? input__30629_nth_0__)
                        (clojure.core/let
                         [VAL__30630
                          (.valAt input__30629_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__30630
                          (:literal)
                          (clojure.core/let
                           [VAL__30631
                            (.valAt input__30629_nth_0__ :type)]
                           (if
                            (clojure.core/let
                             [x__8851__auto__ VAL__30631]
                             (clojure.core/case
                              x__8851__auto__
                              (:string :char)
                              true
                              false))
                            (clojure.core/let
                             [VAL__30632
                              (.valAt input__30629_nth_0__ :form)]
                             (clojure.core/let
                              [!forms
                               (clojure.core/conj !forms VAL__30632)]
                              [!forms]))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail))))
                     (clojure.core/fn
                      [[!forms]]
                      (if
                       (clojure.core/map? input__30171_nth_2__)
                       (clojure.core/let
                        [VAL__30626 (.valAt input__30171_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__30626
                         (:empty)
                         (try
                          [{:tag :literal,
                            :type :string,
                            :form
                            (clojure.string/join
                             (clojure.core/into [] !forms))}]
                          (catch
                           java.lang.Exception
                           e__12034__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__12034__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__12034__auto__))))
                         (state__31230)))
                       (state__31230))))]
                   (if
                    (meander.runtime.zeta/fail? ret__9955__auto__)
                    (state__31230)
                    ret__9955__auto__)))
                 (state__31230))
                (state__31230)))
              (state__31230
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (if
                 (clojure.core/vector? input__30171_nth_1__)
                 (clojure.core/let
                  [input__30171_nth_1___l__
                   (clojure.core/subvec
                    input__30171_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__30171_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__30171_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__30171_nth_1___r__
                     (clojure.core/subvec input__30171_nth_1__ 1)]
                    (clojure.core/let
                     [input__30171_nth_1___l___nth_0__
                      (clojure.core/nth input__30171_nth_1___l__ 0)]
                     (if
                      (clojure.core/map?
                       input__30171_nth_1___l___nth_0__)
                      (clojure.core/let
                       [VAL__30638
                        (.valAt input__30171_nth_1___l___nth_0__ :tag)]
                       (clojure.core/case
                        VAL__30638
                        (:literal)
                        (clojure.core/let
                         [VAL__30639
                          (.valAt
                           input__30171_nth_1___l___nth_0__
                           :type)]
                         (clojure.core/case
                          VAL__30639
                          (:string)
                          (clojure.core/let
                           [?ast input__30171_nth_1___l___nth_0__]
                           (clojure.core/let
                            [?rest input__30171_nth_1___r__]
                            (clojure.core/let
                             [?next input__30171_nth_2__]
                             (try
                              [(clojure.core/let
                                [CATA_RESULT__11094__auto__
                                 (CATA__FN__30216
                                  ['meander.dev.parse.zeta/string-join-args
                                   ?ast
                                   (clojure.core/let
                                    [CATA_RESULT__11094__auto__
                                     (CATA__FN__30216
                                      ['meander.dev.parse.zeta/string-cat-args
                                       ?rest
                                       ?next])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__11094__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__11094__auto__
                                      0)))])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__11094__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__11094__auto__
                                  0)))]
                              (catch
                               java.lang.Exception
                               e__12034__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__12034__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__12034__auto__)))))))
                          (state__31231)))
                        (state__31231)))
                      (state__31231))))
                   (state__31231)))
                 (state__31231))
                (state__31231)))
              (state__31231
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (clojure.core/let
                 [?sequence input__30171_nth_1__]
                 (clojure.core/let
                  [?next input__30171_nth_2__]
                  (try
                   [{:tag :string-cat,
                     :sequence ?sequence,
                     :next ?next}]
                   (catch
                    java.lang.Exception
                    e__12034__auto__
                    (if
                     (meander.runtime.zeta/fail? e__12034__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__12034__auto__))))))
                (state__31232)))
              (state__31232
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__30171_nth_1__)
                 (clojure.core/let
                  [VAL__30644 (.valAt input__30171_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__30644
                   (:literal)
                   (clojure.core/let
                    [VAL__30645 (.valAt input__30171_nth_1__ :type)]
                    (clojure.core/case
                     VAL__30645
                     (:string)
                     (clojure.core/let
                      [VAL__30646 (.valAt input__30171_nth_1__ :form)]
                      (clojure.core/let
                       [?form-1 VAL__30646]
                       (if
                        (clojure.core/map? input__30171_nth_2__)
                        (clojure.core/let
                         [VAL__30647
                          (.valAt input__30171_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__30647
                          (:string-join)
                          (clojure.core/let
                           [VAL__30648
                            (.valAt input__30171_nth_2__ :left)]
                           (if
                            (clojure.core/map? VAL__30648)
                            (clojure.core/let
                             [VAL__30649 (.valAt VAL__30648 :tag)]
                             (clojure.core/case
                              VAL__30649
                              (:literal)
                              (clojure.core/let
                               [VAL__30650 (.valAt VAL__30648 :type)]
                               (clojure.core/case
                                VAL__30650
                                (:string)
                                (clojure.core/let
                                 [VAL__30651 (.valAt VAL__30648 :form)]
                                 (clojure.core/let
                                  [?form-2 VAL__30651]
                                  (clojure.core/let
                                   [VAL__30652
                                    (.valAt
                                     input__30171_nth_2__
                                     :right)]
                                   (clojure.core/let
                                    [?right VAL__30652]
                                    (try
                                     [(clojure.core/let
                                       [CATA_RESULT__11094__auto__
                                        (CATA__FN__30216
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
                                         CATA_RESULT__11094__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__11094__auto__
                                         0)))]
                                     (catch
                                      java.lang.Exception
                                      e__12034__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__12034__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__12034__auto__))))))))
                                (state__31233)))
                              (state__31233)))
                            (state__31233)))
                          (state__31233)))
                        (state__31233))))
                     (state__31233)))
                   (state__31233)))
                 (state__31233))
                (state__31233)))
              (state__31233
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__30171_nth_1__)
                 (clojure.core/let
                  [VAL__30655 (.valAt input__30171_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__30655
                   (:literal)
                   (clojure.core/let
                    [VAL__30656 (.valAt input__30171_nth_1__ :type)]
                    (clojure.core/case
                     VAL__30656
                     (:string)
                     (clojure.core/let
                      [?ast input__30171_nth_1__]
                      (if
                       (clojure.core/map? input__30171_nth_2__)
                       (clojure.core/let
                        [VAL__30657 (.valAt input__30171_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__30657
                         (:string-cat)
                         (clojure.core/let
                          [VAL__30658
                           (.valAt input__30171_nth_2__ :sequence)]
                          (clojure.core/let
                           [?sequence VAL__30658]
                           (clojure.core/let
                            [VAL__30659
                             (.valAt input__30171_nth_2__ :next)]
                            (clojure.core/let
                             [?next VAL__30659]
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
                               e__12034__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__12034__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__12034__auto__))))))))
                         (state__31234)))
                       (state__31234)))
                     (state__31234)))
                   (state__31234)))
                 (state__31234))
                (state__31234)))
              (state__31234
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__30171_nth_1__)
                 (clojure.core/let
                  [VAL__30662 (.valAt input__30171_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__30662
                   (:string-cat)
                   (clojure.core/let
                    [VAL__30663
                     (.valAt input__30171_nth_1__ :sequence)]
                    (clojure.core/let
                     [?sequence VAL__30663]
                     (clojure.core/let
                      [VAL__30664 (.valAt input__30171_nth_1__ :next)]
                      (if
                       (clojure.core/map? VAL__30664)
                       (clojure.core/let
                        [VAL__30665 (.valAt VAL__30664 :tag)]
                        (clojure.core/case
                         VAL__30665
                         (:empty)
                         (clojure.core/let
                          [?right input__30171_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__11094__auto__
                              (CATA__FN__30216
                               ['meander.dev.parse.zeta/string-cat-args
                                ?sequence
                                ?right])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__11094__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__11094__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__12034__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__12034__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__12034__auto__)))))
                         (state__31235)))
                       (state__31235)))))
                   (state__31235)))
                 (state__31235))
                (state__31235)))
              (state__31235
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__30171_nth_1__)
                 (clojure.core/let
                  [VAL__30668 (.valAt input__30171_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__30668
                   (:string-star)
                   (clojure.core/let
                    [VAL__30669 (.valAt input__30171_nth_1__ :pattern)]
                    (clojure.core/let
                     [?pattern VAL__30669]
                     (clojure.core/let
                      [VAL__30670 (.valAt input__30171_nth_1__ :next)]
                      (if
                       (clojure.core/map? VAL__30670)
                       (clojure.core/let
                        [VAL__30671 (.valAt VAL__30670 :tag)]
                        (clojure.core/case
                         VAL__30671
                         (:empty)
                         (clojure.core/let
                          [?right input__30171_nth_2__]
                          (try
                           [{:tag :string-star,
                             :pattern ?pattern,
                             :next ?right}]
                           (catch
                            java.lang.Exception
                            e__12034__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__12034__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__12034__auto__)))))
                         (state__31236)))
                       (state__31236)))))
                   (state__31236)))
                 (state__31236))
                (state__31236)))
              (state__31236
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__30171_nth_1__)
                 (clojure.core/let
                  [VAL__30674 (.valAt input__30171_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__30674
                   (:string-join)
                   (clojure.core/let
                    [VAL__30675 (.valAt input__30171_nth_1__ :left)]
                    (clojure.core/let
                     [?left VAL__30675]
                     (clojure.core/let
                      [VAL__30676 (.valAt input__30171_nth_1__ :right)]
                      (clojure.core/let
                       [?right-1 VAL__30676]
                       (clojure.core/let
                        [?right-2 input__30171_nth_2__]
                        (try
                         [{:tag :string-join,
                           :left ?left,
                           :right
                           (clojure.core/let
                            [CATA_RESULT__11094__auto__
                             (CATA__FN__30216
                              ['meander.dev.parse.zeta/string-join-args
                               ?right-1
                               ?right-2])]
                            (if
                             (meander.runtime.zeta/fail?
                              CATA_RESULT__11094__auto__)
                             (throw (meander.runtime.zeta/fail))
                             (clojure.core/nth
                              CATA_RESULT__11094__auto__
                              0)))}]
                         (catch
                          java.lang.Exception
                          e__12034__auto__
                          (if
                           (meander.runtime.zeta/fail?
                            e__12034__auto__)
                           (meander.runtime.zeta/fail)
                           (throw e__12034__auto__)))))))))
                   (state__31237)))
                 (state__31237))
                (state__31237)))
              (state__31237
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (clojure.core/let
                 [?left input__30171_nth_1__]
                 (if
                  (clojure.core/map? input__30171_nth_2__)
                  (clojure.core/let
                   [VAL__30679 (.valAt input__30171_nth_2__ :tag)]
                   (clojure.core/case
                    VAL__30679
                    (:empty)
                    (try
                     [?left]
                     (catch
                      java.lang.Exception
                      e__12034__auto__
                      (if
                       (meander.runtime.zeta/fail? e__12034__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__12034__auto__))))
                    (state__31238)))
                  (state__31238)))
                (state__31238)))
              (state__31238
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__30171_nth_1__)
                 (clojure.core/let
                  [VAL__30682 (.valAt input__30171_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__30682
                   (:empty)
                   (clojure.core/let
                    [?right input__30171_nth_2__]
                    (try
                     [?right]
                     (catch
                      java.lang.Exception
                      e__12034__auto__
                      (if
                       (meander.runtime.zeta/fail? e__12034__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__12034__auto__)))))
                   (state__31239)))
                 (state__31239))
                (state__31239)))
              (state__31239
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (clojure.core/let
                 [?left input__30171_nth_1__]
                 (clojure.core/let
                  [?right input__30171_nth_2__]
                  (try
                   [{:tag :string-join, :left ?left, :right ?right}]
                   (catch
                    java.lang.Exception
                    e__12034__auto__
                    (if
                     (meander.runtime.zeta/fail? e__12034__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__12034__auto__))))))
                (state__31240)))
              (state__31240
               []
               (if
                (clojure.core/=
                 input__30171_nth_0__
                 'meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__30171_nth_1__)
                 (clojure.core/let
                  [VAL__30687
                   (.valAt input__30171_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__30687]
                   (clojure.core/let
                    [X__30689
                     ((clojure.core/fn
                       [m__8858__auto__]
                       (clojure.core/dissoc
                        m__8858__auto__
                        :meander.zeta/as))
                      input__30171_nth_1__)]
                    (clojure.core/let
                     [?rest X__30689]
                     (clojure.core/letfn
                      [(save__30690 [] (state__31152))
                       (f__31264
                        []
                        (clojure.core/let
                         [?env input__30171_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern ?pattern,
                            :next
                            (clojure.core/let
                             [CATA_RESULT__11094__auto__
                              (CATA__FN__30216 [?rest ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__11094__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__11094__auto__
                               0)))}]
                          (catch
                           java.lang.Exception
                           e__12034__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__12034__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__12034__auto__))))))]
                      (if
                       (clojure.core/= ?rest input__30171_nth_1__)
                       (save__30690)
                       (f__31264)))))))
                 (state__31152))
                (state__31152)))]
             (state__31216)))
           (state__31152))
          (state__31152)))
        (state__31152
         []
         (clojure.core/letfn
          [(def__30693
            [arg__30726 ?ns]
            (clojure.core/letfn
             [(state__31265
               []
               (clojure.core/let
                [x__30727 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__30727)
                 (clojure.core/let [?env arg__30726] [?env ?ns])
                 (state__31266))))
              (state__31266
               []
               (if
                (clojure.core/map? arg__30726)
                (clojure.core/let
                 [VAL__30728 (.valAt arg__30726 :aliases)]
                 (if
                  (clojure.core/map? VAL__30728)
                  (clojure.core/let
                   [X__30730 (clojure.core/set VAL__30728)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__30730))
                    (clojure.core/loop
                     [search_space__31267 (clojure.core/seq X__30730)]
                     (if
                      (clojure.core/seq search_space__31267)
                      (clojure.core/let
                       [elem__30731
                        (clojure.core/first search_space__31267)
                        result__31268
                        (clojure.core/let
                         [elem__30731_nth_0__
                          (clojure.core/nth elem__30731 0)
                          elem__30731_nth_1__
                          (clojure.core/nth elem__30731 1)]
                         (if
                          (clojure.core/symbol? elem__30731_nth_0__)
                          (clojure.core/let
                           [X__30733
                            (clojure.core/name elem__30731_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__30733)
                            (if
                             (clojure.core/symbol? elem__30731_nth_1__)
                             (clojure.core/let
                              [X__30735
                               (clojure.core/name elem__30731_nth_1__)]
                              (clojure.core/case
                               X__30735
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__30726]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__31268)
                        (recur (clojure.core/next search_space__31267))
                        result__31268))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__31265)))]
          (if
           (clojure.core/vector? input__30171)
           (if
            (clojure.core/= (clojure.core/count input__30171) 3)
            (clojure.core/let
             [input__30171_nth_0__
              (clojure.core/nth input__30171 0)
              input__30171_nth_1__
              (clojure.core/nth input__30171 1)
              input__30171_nth_2__
              (clojure.core/nth input__30171 2)]
             (if
              (clojure.core/=
               input__30171_nth_0__
               'meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__30171_nth_1__)
               (clojure.core/let
                [X__30698 (clojure.core/set input__30171_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__30698))
                 (clojure.core/loop
                  [search_space__31270 (clojure.core/seq X__30698)]
                  (if
                   (clojure.core/seq search_space__31270)
                   (clojure.core/let
                    [elem__30699
                     (clojure.core/first search_space__31270)
                     result__31271
                     (clojure.core/let
                      [elem__30699_nth_0__
                       (clojure.core/nth elem__30699 0)
                       elem__30699_nth_1__
                       (clojure.core/nth elem__30699 1)]
                      (clojure.core/let
                       [*m__30195 elem__30699_nth_0__]
                       (if
                        (clojure.core/symbol? elem__30699_nth_0__)
                        (clojure.core/let
                         [X__30701
                          (clojure.core/namespace elem__30699_nth_0__)]
                         (clojure.core/let
                          [?ns X__30701]
                          (clojure.core/let
                           [X__30703
                            (clojure.core/name elem__30699_nth_0__)]
                           (if
                            (clojure.core/string? X__30703)
                            (if
                             (clojure.core/re-matches #"&.*" X__30703)
                             (clojure.core/let
                              [?pattern elem__30699_nth_1__]
                              (clojure.core/let
                               [X__30705
                                ((clojure.core/fn
                                  [m__8858__auto__]
                                  (clojure.core/dissoc
                                   m__8858__auto__
                                   *m__30195))
                                 input__30171_nth_1__)]
                               (clojure.core/let
                                [?rest X__30705]
                                (clojure.core/let
                                 [x__9791__auto__
                                  (def__30693
                                   input__30171_nth_2__
                                   ?ns)]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   x__9791__auto__)
                                  (meander.runtime.zeta/fail)
                                  (clojure.core/let
                                   [[?env ?ns] x__9791__auto__]
                                   (try
                                    [{:tag :rest-map,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__11094__auto__
                                        (CATA__FN__30216
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__11094__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__11094__auto__
                                         0))),
                                      :next
                                      (clojure.core/let
                                       [CATA_RESULT__11094__auto__
                                        (CATA__FN__30216
                                         ['meander.dev.parse.zeta/parse-entries
                                          ?rest
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__11094__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__11094__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__12034__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__12034__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__12034__auto__))))))))))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? result__31271)
                     (recur (clojure.core/next search_space__31270))
                     result__31271))
                   (state__31153)))
                 (state__31153)))
               (state__31153))
              (state__31153)))
            (state__31153))
           (state__31153))))
        (state__31153
         []
         (if
          (clojure.core/vector? input__30171)
          (clojure.core/letfn
           [(state__31273
             []
             (if
              (clojure.core/= (clojure.core/count input__30171) 3)
              (clojure.core/let
               [input__30171_nth_0__
                (clojure.core/nth input__30171 0)
                input__30171_nth_1__
                (clojure.core/nth input__30171 1)
                input__30171_nth_2__
                (clojure.core/nth input__30171 2)]
               (clojure.core/letfn
                [(state__31276
                  []
                  (if
                   (clojure.core/=
                    input__30171_nth_0__
                    'meander.dev.parse.zeta/parse-entries)
                   (if
                    (clojure.core/map? input__30171_nth_1__)
                    (clojure.core/let
                     [X__30749 (clojure.core/set input__30171_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__30749))
                      (clojure.core/loop
                       [search_space__31282
                        (clojure.core/seq X__30749)]
                       (if
                        (clojure.core/seq search_space__31282)
                        (clojure.core/let
                         [elem__30750
                          (clojure.core/first search_space__31282)
                          result__31283
                          (clojure.core/let
                           [elem__30750_nth_0__
                            (clojure.core/nth elem__30750 0)
                            elem__30750_nth_1__
                            (clojure.core/nth elem__30750 1)]
                           (clojure.core/let
                            [?key-pattern elem__30750_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__30750_nth_1__]
                             (clojure.core/let
                              [X__30752
                               ((clojure.core/fn
                                 [m__8858__auto__]
                                 (clojure.core/dissoc
                                  m__8858__auto__
                                  ?key-pattern))
                                input__30171_nth_1__)]
                              (clojure.core/let
                               [?rest X__30752]
                               (clojure.core/let
                                [?env input__30171_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__11094__auto__
                                     (CATA__FN__30216
                                      [?key-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__11094__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__11094__auto__
                                      0))),
                                   :val-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__11094__auto__
                                     (CATA__FN__30216
                                      [?val-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__11094__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__11094__auto__
                                      0))),
                                   :next
                                   (clojure.core/let
                                    [CATA_RESULT__11094__auto__
                                     (CATA__FN__30216
                                      ['meander.dev.parse.zeta/parse-entries
                                       ?rest
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__11094__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__11094__auto__
                                      0)))}]
                                 (catch
                                  java.lang.Exception
                                  e__12034__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__12034__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__12034__auto__))))))))))]
                         (if
                          (meander.runtime.zeta/fail? result__31283)
                          (recur
                           (clojure.core/next search_space__31282))
                          result__31283))
                        (state__31277)))
                      (state__31277)))
                    (state__31277))
                   (state__31277)))
                 (state__31277
                  []
                  (if
                   (clojure.core/=
                    input__30171_nth_0__
                    'meander.dev.parse.zeta/parse-entries)
                   (if
                    (clojure.core/map? input__30171_nth_1__)
                    (clojure.core/let
                     [?env input__30171_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__12034__auto__
                       (if
                        (meander.runtime.zeta/fail? e__12034__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__12034__auto__)))))
                    (state__31278))
                   (state__31278)))
                 (state__31278
                  []
                  (if
                   (clojure.core/=
                    input__30171_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__30171_nth_1__)
                    (clojure.core/case
                     input__30171_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__30171_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__12034__auto__
                        (if
                         (meander.runtime.zeta/fail? e__12034__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__12034__auto__)))))
                     (state__31279))
                    (state__31279))
                   (state__31279)))
                 (state__31279
                  []
                  (if
                   (clojure.core/=
                    input__30171_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__30171_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__30171_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__30171_nth_2__]
                      (try
                       [[{:tag :error,
                          :message
                          "meander.zeta/with expects an even number of bindings"}]]
                       (catch
                        java.lang.Exception
                        e__12034__auto__
                        (if
                         (meander.runtime.zeta/fail? e__12034__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__12034__auto__)))))
                     (state__31280))
                    (state__31280))
                   (state__31280)))
                 (state__31280
                  []
                  (if
                   (clojure.core/=
                    input__30171_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__30171_nth_1__)
                    (clojure.core/let
                     [input__30171_nth_1___l__
                      (clojure.core/subvec
                       input__30171_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__30171_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__30171_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__30171_nth_1___r__
                        (clojure.core/subvec input__30171_nth_1__ 2)]
                       (clojure.core/let
                        [input__30171_nth_1___l___nth_0__
                         (clojure.core/nth input__30171_nth_1___l__ 0)
                         input__30171_nth_1___l___nth_1__
                         (clojure.core/nth input__30171_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__30171_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__30766
                           (clojure.core/namespace
                            input__30171_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__30768
                            (clojure.core/name
                             input__30171_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__30768)
                            (if
                             (clojure.core/re-matches #"%.+" X__30768)
                             (clojure.core/let
                              [?symbol
                               input__30171_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__30171_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__30171_nth_1___r__]
                                (clojure.core/let
                                 [?env input__30171_nth_2__]
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
                                        [CATA_RESULT__11094__auto__
                                         (CATA__FN__30216
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__11094__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__11094__auto__
                                          0)))})
                                     (clojure.core/let
                                      [CATA_RESULT__11094__auto__
                                       (CATA__FN__30216
                                        ['meander.dev.parse.zeta/parse-with-bindings
                                         ?rest
                                         ?env])]
                                      (if
                                       (meander.runtime.zeta/fail?
                                        CATA_RESULT__11094__auto__)
                                       (throw
                                        (meander.runtime.zeta/fail))
                                       (clojure.core/nth
                                        CATA_RESULT__11094__auto__
                                        0)))))]
                                  (catch
                                   java.lang.Exception
                                   e__12034__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__12034__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__12034__auto__))))))))
                             (state__31281))
                            (state__31281))))
                         (state__31281))))
                      (state__31281)))
                    (state__31281))
                   (state__31281)))
                 (state__31281
                  []
                  (if
                   (clojure.core/=
                    input__30171_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__30171_nth_1__)
                    (clojure.core/let
                     [input__30171_nth_1___l__
                      (clojure.core/subvec
                       input__30171_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__30171_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__30171_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__30171_nth_1___r__
                        (clojure.core/subvec input__30171_nth_1__ 2)]
                       (clojure.core/let
                        [input__30171_nth_1___l___nth_0__
                         (clojure.core/nth input__30171_nth_1___l__ 0)
                         input__30171_nth_1___l___nth_1__
                         (clojure.core/nth input__30171_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__30171_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__30171_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__30171_nth_1___r__]
                           (clojure.core/let
                            [?env input__30171_nth_2__]
                            (try
                             [[{:tag :error,
                                :message
                                "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]
                             (catch
                              java.lang.Exception
                              e__12034__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__12034__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__12034__auto__))))))))))
                      (state__31274)))
                    (state__31274))
                   (state__31274)))]
                (state__31276)))
              (state__31274)))
            (state__31274
             []
             (if
              (clojure.core/= (clojure.core/count input__30171) 2)
              (clojure.core/let
               [input__30171_nth_0__
                (clojure.core/nth input__30171 0)
                input__30171_nth_1__
                (clojure.core/nth input__30171 1)]
               (if
                (clojure.core/vector? input__30171_nth_0__)
                (clojure.core/let
                 [?sequence input__30171_nth_0__]
                 (clojure.core/let
                  [?env input__30171_nth_1__]
                  (try
                   [(clojure.core/let
                     [CATA_RESULT__11094__auto__
                      (CATA__FN__30216
                       ['meander.dev.parse.zeta/vector-args
                        (clojure.core/let
                         [CATA_RESULT__11094__auto__
                          (CATA__FN__30216
                           ['meander.dev.parse.zeta/parse-seq
                            ?sequence
                            ?env])]
                         (if
                          (meander.runtime.zeta/fail?
                           CATA_RESULT__11094__auto__)
                          (throw (meander.runtime.zeta/fail))
                          (clojure.core/nth
                           CATA_RESULT__11094__auto__
                           0)))
                        ?sequence])]
                     (if
                      (meander.runtime.zeta/fail?
                       CATA_RESULT__11094__auto__)
                      (throw (meander.runtime.zeta/fail))
                      (clojure.core/nth
                       CATA_RESULT__11094__auto__
                       0)))]
                   (catch
                    java.lang.Exception
                    e__12034__auto__
                    (if
                     (meander.runtime.zeta/fail? e__12034__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__12034__auto__))))))
                (state__31275)))
              (state__31275)))
            (state__31275
             []
             (if
              (clojure.core/= (clojure.core/count input__30171) 3)
              (clojure.core/let
               [input__30171_nth_0__
                (clojure.core/nth input__30171 0)
                input__30171_nth_1__
                (clojure.core/nth input__30171 1)]
               (clojure.core/letfn
                [(state__31285
                  []
                  (if
                   (clojure.core/=
                    input__30171_nth_0__
                    'meander.dev.parse.zeta/vector-args)
                   (if
                    (clojure.core/map? input__30171_nth_1__)
                    (clojure.core/let
                     [VAL__30779 (.valAt input__30171_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__30779
                      (:literal)
                      (clojure.core/let
                       [?literal input__30171_nth_1__]
                       (try
                        [?literal]
                        (catch
                         java.lang.Exception
                         e__12034__auto__
                         (if
                          (meander.runtime.zeta/fail? e__12034__auto__)
                          (meander.runtime.zeta/fail)
                          (throw e__12034__auto__)))))
                      (state__31286)))
                    (state__31286))
                   (state__31286)))
                 (state__31286
                  []
                  (clojure.core/let
                   [input__30171_nth_2__
                    (clojure.core/nth input__30171 2)]
                   (if
                    (clojure.core/=
                     input__30171_nth_0__
                     'meander.dev.parse.zeta/vector-args)
                    (clojure.core/let
                     [?next input__30171_nth_1__]
                     (clojure.core/let
                      [?sequence input__30171_nth_2__]
                      (try
                       [{:tag :vector, :next ?next, :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__12034__auto__
                        (if
                         (meander.runtime.zeta/fail? e__12034__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__12034__auto__))))))
                    (state__31154))))]
                (state__31285)))
              (state__31154)))]
           (state__31273))
          (state__31154)))
        (state__31154
         []
         (clojure.core/letfn
          [(def__30783
            [arg__30806 ?__30173]
            (clojure.core/letfn
             [(state__31287
               []
               (clojure.core/let
                [x__30807 "meander.zeta"]
                (if
                 (clojure.core/= ?__30173 x__30807)
                 [?__30173]
                 (state__31288))))
              (state__31288
               []
               (if
                (clojure.core/map? arg__30806)
                (clojure.core/let
                 [VAL__30808 (.valAt arg__30806 :aliases)]
                 (if
                  (clojure.core/map? VAL__30808)
                  (clojure.core/let
                   [X__30810 (clojure.core/set VAL__30808)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__30810))
                    (clojure.core/loop
                     [search_space__31289 (clojure.core/seq X__30810)]
                     (if
                      (clojure.core/seq search_space__31289)
                      (clojure.core/let
                       [elem__30811
                        (clojure.core/first search_space__31289)
                        result__31290
                        (clojure.core/let
                         [elem__30811_nth_0__
                          (clojure.core/nth elem__30811 0)
                          elem__30811_nth_1__
                          (clojure.core/nth elem__30811 1)]
                         (if
                          (clojure.core/symbol? elem__30811_nth_0__)
                          (clojure.core/let
                           [X__30813
                            (clojure.core/name elem__30811_nth_0__)]
                           (if
                            (clojure.core/= ?__30173 X__30813)
                            (if
                             (clojure.core/symbol? elem__30811_nth_1__)
                             (clojure.core/let
                              [X__30815
                               (clojure.core/name elem__30811_nth_1__)]
                              (clojure.core/case
                               X__30815
                               ("meander.zeta")
                               [?__30173]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__31290)
                        (recur (clojure.core/next search_space__31289))
                        result__31290))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__31287)))]
          (if
           (clojure.core/vector? input__30171)
           (if
            (clojure.core/= (clojure.core/count input__30171) 2)
            (clojure.core/let
             [input__30171_nth_0__
              (clojure.core/nth input__30171 0)
              input__30171_nth_1__
              (clojure.core/nth input__30171 1)]
             (if
              (clojure.core/seq? input__30171_nth_0__)
              (clojure.core/let
               [input__30171_nth_0___l__
                (clojure.core/take 1 input__30171_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__30171_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__30171_nth_0___r__
                  (clojure.core/drop 1 input__30171_nth_0__)]
                 (clojure.core/let
                  [input__30171_nth_0___l___nth_0__
                   (clojure.core/nth input__30171_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__30171_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__30793
                     (clojure.core/namespace
                      input__30171_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__30173 X__30793]
                     (clojure.core/let
                      [X__30795
                       (clojure.core/name
                        input__30171_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__30795
                       ("with")
                       (clojure.core/let
                        [x__9791__auto__
                         (def__30783 input__30171_nth_1__ ?__30173)]
                        (if
                         (meander.runtime.zeta/fail? x__9791__auto__)
                         (state__31155)
                         (clojure.core/let
                          [[?__30173] x__9791__auto__]
                          (if
                           (clojure.core/vector? input__30171)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__30171)
                             2)
                            (clojure.core/let
                             [input__30171_nth_0__
                              (clojure.core/nth input__30171 0)
                              input__30171_nth_1__
                              (clojure.core/nth input__30171 1)]
                             (if
                              (clojure.core/seq? input__30171_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__30171_nth_0__)
                                3)
                               (clojure.core/let
                                [input__30171_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  1)
                                 input__30171_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__30171_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__30171_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__30171_nth_0__]
                                   (clojure.core/let
                                    [?env input__30171_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__11094__auto__
                                          (CATA__FN__30216
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?bindings
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__11094__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__11094__auto__
                                           0)))},
                                       :body
                                       (clojure.core/let
                                        [CATA_RESULT__11094__auto__
                                         (CATA__FN__30216
                                          [?body ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__11094__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__11094__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__12034__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__12034__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__12034__auto__)))))))))
                               (state__31155))
                              (state__31155)))
                            (state__31155))
                           (state__31155)))))
                       (state__31155)))))
                   (state__31155))))
                (state__31155)))
              (state__31155)))
            (state__31155))
           (state__31155))))
        (state__31155
         []
         (clojure.core/letfn
          [(def__30817
            [arg__30840 ?__30174]
            (clojure.core/letfn
             [(state__31292
               []
               (clojure.core/let
                [x__30841 "meander.zeta"]
                (if
                 (clojure.core/= ?__30174 x__30841)
                 [?__30174]
                 (state__31293))))
              (state__31293
               []
               (if
                (clojure.core/map? arg__30840)
                (clojure.core/let
                 [VAL__30842 (.valAt arg__30840 :aliases)]
                 (if
                  (clojure.core/map? VAL__30842)
                  (clojure.core/let
                   [X__30844 (clojure.core/set VAL__30842)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__30844))
                    (clojure.core/loop
                     [search_space__31294 (clojure.core/seq X__30844)]
                     (if
                      (clojure.core/seq search_space__31294)
                      (clojure.core/let
                       [elem__30845
                        (clojure.core/first search_space__31294)
                        result__31295
                        (clojure.core/let
                         [elem__30845_nth_0__
                          (clojure.core/nth elem__30845 0)
                          elem__30845_nth_1__
                          (clojure.core/nth elem__30845 1)]
                         (if
                          (clojure.core/symbol? elem__30845_nth_0__)
                          (clojure.core/let
                           [X__30847
                            (clojure.core/name elem__30845_nth_0__)]
                           (if
                            (clojure.core/= ?__30174 X__30847)
                            (if
                             (clojure.core/symbol? elem__30845_nth_1__)
                             (clojure.core/let
                              [X__30849
                               (clojure.core/name elem__30845_nth_1__)]
                              (clojure.core/case
                               X__30849
                               ("meander.zeta")
                               [?__30174]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__31295)
                        (recur (clojure.core/next search_space__31294))
                        result__31295))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__31292)))]
          (if
           (clojure.core/vector? input__30171)
           (if
            (clojure.core/= (clojure.core/count input__30171) 2)
            (clojure.core/let
             [input__30171_nth_0__
              (clojure.core/nth input__30171 0)
              input__30171_nth_1__
              (clojure.core/nth input__30171 1)]
             (if
              (clojure.core/seq? input__30171_nth_0__)
              (clojure.core/let
               [input__30171_nth_0___l__
                (clojure.core/take 1 input__30171_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__30171_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__30171_nth_0___r__
                  (clojure.core/drop 1 input__30171_nth_0__)]
                 (clojure.core/let
                  [input__30171_nth_0___l___nth_0__
                   (clojure.core/nth input__30171_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__30171_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__30827
                     (clojure.core/namespace
                      input__30171_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__30174 X__30827]
                     (clojure.core/let
                      [X__30829
                       (clojure.core/name
                        input__30171_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__30829
                       ("apply")
                       (clojure.core/let
                        [x__9791__auto__
                         (def__30817 input__30171_nth_1__ ?__30174)]
                        (if
                         (meander.runtime.zeta/fail? x__9791__auto__)
                         (state__31156)
                         (clojure.core/let
                          [[?__30174] x__9791__auto__]
                          (if
                           (clojure.core/vector? input__30171)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__30171)
                             2)
                            (clojure.core/let
                             [input__30171_nth_0__
                              (clojure.core/nth input__30171 0)
                              input__30171_nth_1__
                              (clojure.core/nth input__30171 1)]
                             (if
                              (clojure.core/seq? input__30171_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__30171_nth_0__)
                                3)
                               (clojure.core/let
                                [input__30171_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  1)
                                 input__30171_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__30171_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__30171_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__30171_nth_0__]
                                   (clojure.core/let
                                    [?env input__30171_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__11094__auto__
                                         (CATA__FN__30216
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__11094__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__11094__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__12034__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__12034__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__12034__auto__)))))))))
                               (state__31156))
                              (state__31156)))
                            (state__31156))
                           (state__31156)))))
                       (state__31156)))))
                   (state__31156))))
                (state__31156)))
              (state__31156)))
            (state__31156))
           (state__31156))))
        (state__31156
         []
         (clojure.core/letfn
          [(def__30851
            [arg__30874 ?__30175]
            (clojure.core/letfn
             [(state__31297
               []
               (clojure.core/let
                [x__30875 "meander.zeta"]
                (if
                 (clojure.core/= ?__30175 x__30875)
                 [?__30175]
                 (state__31298))))
              (state__31298
               []
               (if
                (clojure.core/map? arg__30874)
                (clojure.core/let
                 [VAL__30876 (.valAt arg__30874 :aliases)]
                 (if
                  (clojure.core/map? VAL__30876)
                  (clojure.core/let
                   [X__30878 (clojure.core/set VAL__30876)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__30878))
                    (clojure.core/loop
                     [search_space__31299 (clojure.core/seq X__30878)]
                     (if
                      (clojure.core/seq search_space__31299)
                      (clojure.core/let
                       [elem__30879
                        (clojure.core/first search_space__31299)
                        result__31300
                        (clojure.core/let
                         [elem__30879_nth_0__
                          (clojure.core/nth elem__30879 0)
                          elem__30879_nth_1__
                          (clojure.core/nth elem__30879 1)]
                         (if
                          (clojure.core/symbol? elem__30879_nth_0__)
                          (clojure.core/let
                           [X__30881
                            (clojure.core/name elem__30879_nth_0__)]
                           (if
                            (clojure.core/= ?__30175 X__30881)
                            (if
                             (clojure.core/symbol? elem__30879_nth_1__)
                             (clojure.core/let
                              [X__30883
                               (clojure.core/name elem__30879_nth_1__)]
                              (clojure.core/case
                               X__30883
                               ("meander.zeta")
                               [?__30175]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__31300)
                        (recur (clojure.core/next search_space__31299))
                        result__31300))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__31297)))]
          (if
           (clojure.core/vector? input__30171)
           (if
            (clojure.core/= (clojure.core/count input__30171) 2)
            (clojure.core/let
             [input__30171_nth_0__
              (clojure.core/nth input__30171 0)
              input__30171_nth_1__
              (clojure.core/nth input__30171 1)]
             (if
              (clojure.core/seq? input__30171_nth_0__)
              (clojure.core/let
               [input__30171_nth_0___l__
                (clojure.core/take 1 input__30171_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__30171_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__30171_nth_0___r__
                  (clojure.core/drop 1 input__30171_nth_0__)]
                 (clojure.core/let
                  [input__30171_nth_0___l___nth_0__
                   (clojure.core/nth input__30171_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__30171_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__30861
                     (clojure.core/namespace
                      input__30171_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__30175 X__30861]
                     (clojure.core/let
                      [X__30863
                       (clojure.core/name
                        input__30171_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__30863
                       ("and")
                       (clojure.core/let
                        [x__9791__auto__
                         (def__30851 input__30171_nth_1__ ?__30175)]
                        (if
                         (meander.runtime.zeta/fail? x__9791__auto__)
                         (state__31157)
                         (clojure.core/let
                          [[?__30175] x__9791__auto__]
                          (if
                           (clojure.core/vector? input__30171)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__30171)
                             2)
                            (clojure.core/let
                             [input__30171_nth_0__
                              (clojure.core/nth input__30171 0)
                              input__30171_nth_1__
                              (clojure.core/nth input__30171 1)]
                             (if
                              (clojure.core/seq? input__30171_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__30171_nth_0__)
                                3)
                               (clojure.core/let
                                [input__30171_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  1)
                                 input__30171_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__30171_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__30171_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__30171_nth_0__]
                                   (clojure.core/let
                                    [?env input__30171_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__11094__auto__
                                         (CATA__FN__30216
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__11094__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__11094__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__11094__auto__
                                         (CATA__FN__30216
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__11094__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__11094__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__12034__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__12034__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__12034__auto__)))))))))
                               (state__31157))
                              (state__31157)))
                            (state__31157))
                           (state__31157)))))
                       (state__31157)))))
                   (state__31157))))
                (state__31157)))
              (state__31157)))
            (state__31157))
           (state__31157))))
        (state__31157
         []
         (clojure.core/letfn
          [(def__30885
            [arg__30908 ?__30176]
            (clojure.core/letfn
             [(state__31302
               []
               (clojure.core/let
                [x__30909 "meander.zeta"]
                (if
                 (clojure.core/= ?__30176 x__30909)
                 [?__30176]
                 (state__31303))))
              (state__31303
               []
               (if
                (clojure.core/map? arg__30908)
                (clojure.core/let
                 [VAL__30910 (.valAt arg__30908 :aliases)]
                 (if
                  (clojure.core/map? VAL__30910)
                  (clojure.core/let
                   [X__30912 (clojure.core/set VAL__30910)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__30912))
                    (clojure.core/loop
                     [search_space__31304 (clojure.core/seq X__30912)]
                     (if
                      (clojure.core/seq search_space__31304)
                      (clojure.core/let
                       [elem__30913
                        (clojure.core/first search_space__31304)
                        result__31305
                        (clojure.core/let
                         [elem__30913_nth_0__
                          (clojure.core/nth elem__30913 0)
                          elem__30913_nth_1__
                          (clojure.core/nth elem__30913 1)]
                         (if
                          (clojure.core/symbol? elem__30913_nth_0__)
                          (clojure.core/let
                           [X__30915
                            (clojure.core/name elem__30913_nth_0__)]
                           (if
                            (clojure.core/= ?__30176 X__30915)
                            (if
                             (clojure.core/symbol? elem__30913_nth_1__)
                             (clojure.core/let
                              [X__30917
                               (clojure.core/name elem__30913_nth_1__)]
                              (clojure.core/case
                               X__30917
                               ("meander.zeta")
                               [?__30176]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__31305)
                        (recur (clojure.core/next search_space__31304))
                        result__31305))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__31302)))]
          (if
           (clojure.core/vector? input__30171)
           (if
            (clojure.core/= (clojure.core/count input__30171) 2)
            (clojure.core/let
             [input__30171_nth_0__
              (clojure.core/nth input__30171 0)
              input__30171_nth_1__
              (clojure.core/nth input__30171 1)]
             (if
              (clojure.core/seq? input__30171_nth_0__)
              (clojure.core/let
               [input__30171_nth_0___l__
                (clojure.core/take 1 input__30171_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__30171_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__30171_nth_0___r__
                  (clojure.core/drop 1 input__30171_nth_0__)]
                 (clojure.core/let
                  [input__30171_nth_0___l___nth_0__
                   (clojure.core/nth input__30171_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__30171_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__30895
                     (clojure.core/namespace
                      input__30171_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__30176 X__30895]
                     (clojure.core/let
                      [X__30897
                       (clojure.core/name
                        input__30171_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__30897
                       ("cata")
                       (clojure.core/let
                        [x__9791__auto__
                         (def__30885 input__30171_nth_1__ ?__30176)]
                        (if
                         (meander.runtime.zeta/fail? x__9791__auto__)
                         (state__31158)
                         (clojure.core/let
                          [[?__30176] x__9791__auto__]
                          (if
                           (clojure.core/vector? input__30171)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__30171)
                             2)
                            (clojure.core/let
                             [input__30171_nth_0__
                              (clojure.core/nth input__30171 0)
                              input__30171_nth_1__
                              (clojure.core/nth input__30171 1)]
                             (if
                              (clojure.core/seq? input__30171_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__30171_nth_0__)
                                2)
                               (clojure.core/let
                                [input__30171_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__30171_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__30171_nth_0__]
                                  (clojure.core/let
                                   [?env input__30171_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__11094__auto__
                                        (CATA__FN__30216
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__11094__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__11094__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__12034__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__12034__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__12034__auto__))))))))
                               (state__31158))
                              (state__31158)))
                            (state__31158))
                           (state__31158)))))
                       (state__31158)))))
                   (state__31158))))
                (state__31158)))
              (state__31158)))
            (state__31158))
           (state__31158))))
        (state__31158
         []
         (clojure.core/letfn
          [(def__30919
            [arg__30942 ?__30177]
            (clojure.core/letfn
             [(state__31307
               []
               (clojure.core/let
                [x__30943 "meander.zeta"]
                (if
                 (clojure.core/= ?__30177 x__30943)
                 [?__30177]
                 (state__31308))))
              (state__31308
               []
               (if
                (clojure.core/map? arg__30942)
                (clojure.core/let
                 [VAL__30944 (.valAt arg__30942 :aliases)]
                 (if
                  (clojure.core/map? VAL__30944)
                  (clojure.core/let
                   [X__30946 (clojure.core/set VAL__30944)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__30946))
                    (clojure.core/loop
                     [search_space__31309 (clojure.core/seq X__30946)]
                     (if
                      (clojure.core/seq search_space__31309)
                      (clojure.core/let
                       [elem__30947
                        (clojure.core/first search_space__31309)
                        result__31310
                        (clojure.core/let
                         [elem__30947_nth_0__
                          (clojure.core/nth elem__30947 0)
                          elem__30947_nth_1__
                          (clojure.core/nth elem__30947 1)]
                         (if
                          (clojure.core/symbol? elem__30947_nth_0__)
                          (clojure.core/let
                           [X__30949
                            (clojure.core/name elem__30947_nth_0__)]
                           (if
                            (clojure.core/= ?__30177 X__30949)
                            (if
                             (clojure.core/symbol? elem__30947_nth_1__)
                             (clojure.core/let
                              [X__30951
                               (clojure.core/name elem__30947_nth_1__)]
                              (clojure.core/case
                               X__30951
                               ("meander.zeta")
                               [?__30177]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__31310)
                        (recur (clojure.core/next search_space__31309))
                        result__31310))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__31307)))]
          (if
           (clojure.core/vector? input__30171)
           (if
            (clojure.core/= (clojure.core/count input__30171) 2)
            (clojure.core/let
             [input__30171_nth_0__
              (clojure.core/nth input__30171 0)
              input__30171_nth_1__
              (clojure.core/nth input__30171 1)]
             (if
              (clojure.core/seq? input__30171_nth_0__)
              (clojure.core/let
               [input__30171_nth_0___l__
                (clojure.core/take 1 input__30171_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__30171_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__30171_nth_0___r__
                  (clojure.core/drop 1 input__30171_nth_0__)]
                 (clojure.core/let
                  [input__30171_nth_0___l___nth_0__
                   (clojure.core/nth input__30171_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__30171_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__30929
                     (clojure.core/namespace
                      input__30171_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__30177 X__30929]
                     (clojure.core/let
                      [X__30931
                       (clojure.core/name
                        input__30171_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__30931
                       ("fold")
                       (clojure.core/let
                        [x__9791__auto__
                         (def__30919 input__30171_nth_1__ ?__30177)]
                        (if
                         (meander.runtime.zeta/fail? x__9791__auto__)
                         (state__31159)
                         (clojure.core/let
                          [[?__30177] x__9791__auto__]
                          (if
                           (clojure.core/vector? input__30171)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__30171)
                             2)
                            (clojure.core/let
                             [input__30171_nth_0__
                              (clojure.core/nth input__30171 0)
                              input__30171_nth_1__
                              (clojure.core/nth input__30171 1)]
                             (if
                              (clojure.core/seq? input__30171_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__30171_nth_0__)
                                4)
                               (clojure.core/let
                                [input__30171_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  1)
                                 input__30171_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  2)
                                 input__30171_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__30171_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__30171_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__30171_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__30171_nth_0__]
                                    (clojure.core/let
                                     [?env input__30171_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__11094__auto__
                                         (CATA__FN__30216
                                          ['meander.dev.parse.zeta/fold-args
                                           (clojure.core/let
                                            [CATA_RESULT__11094__auto__
                                             (CATA__FN__30216
                                              [?mutable-variable
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__11094__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__11094__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__11094__auto__
                                             (CATA__FN__30216
                                              [?initial-value ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__11094__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__11094__auto__
                                              0)))
                                           ?fold-function
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__11094__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__11094__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__12034__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__12034__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__12034__auto__))))))))))
                               (state__31159))
                              (state__31159)))
                            (state__31159))
                           (state__31159)))))
                       (state__31159)))))
                   (state__31159))))
                (state__31159)))
              (state__31159)))
            (state__31159))
           (state__31159))))
        (state__31159
         []
         (if
          (clojure.core/vector? input__30171)
          (if
           (clojure.core/= (clojure.core/count input__30171) 5)
           (clojure.core/let
            [input__30171_nth_0__
             (clojure.core/nth input__30171 0)
             input__30171_nth_1__
             (clojure.core/nth input__30171 1)
             input__30171_nth_2__
             (clojure.core/nth input__30171 2)
             input__30171_nth_3__
             (clojure.core/nth input__30171 3)
             input__30171_nth_4__
             (clojure.core/nth input__30171 4)]
            (if
             (clojure.core/=
              input__30171_nth_0__
              'meander.dev.parse.zeta/fold-args)
             (if
              (clojure.core/map? input__30171_nth_1__)
              (clojure.core/let
               [VAL__30954 (.valAt input__30171_nth_1__ :tag)]
               (clojure.core/case
                VAL__30954
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__30171_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__30171_nth_2__]
                  (clojure.core/let
                   [?fold-function input__30171_nth_3__]
                   (clojure.core/let
                    [?form input__30171_nth_4__]
                    (try
                     [{:tag :fold,
                       :variable ?variable-ast,
                       :initial-value ?initial-value-ast,
                       :fold-function
                       {:tag :host-expression, :form ?fold-function},
                       :form ?form}]
                     (catch
                      java.lang.Exception
                      e__12034__auto__
                      (if
                       (meander.runtime.zeta/fail? e__12034__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__12034__auto__))))))))
                (state__31160)))
              (state__31160))
             (state__31160)))
           (state__31160))
          (state__31160)))
        (state__31160
         []
         (clojure.core/letfn
          [(def__30956
            [arg__30979 ?__30178]
            (clojure.core/letfn
             [(state__31312
               []
               (clojure.core/let
                [x__30980 "meander.zeta"]
                (if
                 (clojure.core/= ?__30178 x__30980)
                 [?__30178]
                 (state__31313))))
              (state__31313
               []
               (if
                (clojure.core/map? arg__30979)
                (clojure.core/let
                 [VAL__30981 (.valAt arg__30979 :aliases)]
                 (if
                  (clojure.core/map? VAL__30981)
                  (clojure.core/let
                   [X__30983 (clojure.core/set VAL__30981)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__30983))
                    (clojure.core/loop
                     [search_space__31314 (clojure.core/seq X__30983)]
                     (if
                      (clojure.core/seq search_space__31314)
                      (clojure.core/let
                       [elem__30984
                        (clojure.core/first search_space__31314)
                        result__31315
                        (clojure.core/let
                         [elem__30984_nth_0__
                          (clojure.core/nth elem__30984 0)
                          elem__30984_nth_1__
                          (clojure.core/nth elem__30984 1)]
                         (if
                          (clojure.core/symbol? elem__30984_nth_0__)
                          (clojure.core/let
                           [X__30986
                            (clojure.core/name elem__30984_nth_0__)]
                           (if
                            (clojure.core/= ?__30178 X__30986)
                            (if
                             (clojure.core/symbol? elem__30984_nth_1__)
                             (clojure.core/let
                              [X__30988
                               (clojure.core/name elem__30984_nth_1__)]
                              (clojure.core/case
                               X__30988
                               ("meander.zeta")
                               [?__30178]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__31315)
                        (recur (clojure.core/next search_space__31314))
                        result__31315))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__31312)))]
          (if
           (clojure.core/vector? input__30171)
           (if
            (clojure.core/= (clojure.core/count input__30171) 2)
            (clojure.core/let
             [input__30171_nth_0__
              (clojure.core/nth input__30171 0)
              input__30171_nth_1__
              (clojure.core/nth input__30171 1)]
             (if
              (clojure.core/seq? input__30171_nth_0__)
              (clojure.core/let
               [input__30171_nth_0___l__
                (clojure.core/take 1 input__30171_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__30171_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__30171_nth_0___r__
                  (clojure.core/drop 1 input__30171_nth_0__)]
                 (clojure.core/let
                  [input__30171_nth_0___l___nth_0__
                   (clojure.core/nth input__30171_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__30171_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__30966
                     (clojure.core/namespace
                      input__30171_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__30178 X__30966]
                     (clojure.core/let
                      [X__30968
                       (clojure.core/name
                        input__30171_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__30968
                       ("let")
                       (clojure.core/let
                        [x__9791__auto__
                         (def__30956 input__30171_nth_1__ ?__30178)]
                        (if
                         (meander.runtime.zeta/fail? x__9791__auto__)
                         (state__31161)
                         (clojure.core/let
                          [[?__30178] x__9791__auto__]
                          (if
                           (clojure.core/vector? input__30171)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__30171)
                             2)
                            (clojure.core/let
                             [input__30171_nth_0__
                              (clojure.core/nth input__30171 0)
                              input__30171_nth_1__
                              (clojure.core/nth input__30171 1)]
                             (if
                              (clojure.core/seq? input__30171_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__30171_nth_0__)
                                3)
                               (clojure.core/let
                                [input__30171_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  0)
                                 input__30171_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  1)
                                 input__30171_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__30171_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__30171_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next input__30171_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__30171_nth_0__]
                                    (clojure.core/let
                                     [?env input__30171_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__11094__auto__
                                          (CATA__FN__30216
                                           [?pattern ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__11094__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__11094__auto__
                                           0))),
                                        :expression
                                        {:tag :host-expression,
                                         :form ?expression},
                                        :next
                                        (clojure.core/let
                                         [CATA_RESULT__11094__auto__
                                          (CATA__FN__30216
                                           [?next ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__11094__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__11094__auto__
                                           0)))}]
                                      (catch
                                       java.lang.Exception
                                       e__12034__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__12034__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__12034__auto__))))))))))
                               (state__31161))
                              (state__31161)))
                            (state__31161))
                           (state__31161)))))
                       (state__31161)))))
                   (state__31161))))
                (state__31161)))
              (state__31161)))
            (state__31161))
           (state__31161))))
        (state__31161
         []
         (clojure.core/letfn
          [(def__30990
            [arg__31013 ?__30179]
            (clojure.core/letfn
             [(state__31317
               []
               (clojure.core/let
                [x__31014 "meander.zeta"]
                (if
                 (clojure.core/= ?__30179 x__31014)
                 [?__30179]
                 (state__31318))))
              (state__31318
               []
               (if
                (clojure.core/map? arg__31013)
                (clojure.core/let
                 [VAL__31015 (.valAt arg__31013 :aliases)]
                 (if
                  (clojure.core/map? VAL__31015)
                  (clojure.core/let
                   [X__31017 (clojure.core/set VAL__31015)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__31017))
                    (clojure.core/loop
                     [search_space__31319 (clojure.core/seq X__31017)]
                     (if
                      (clojure.core/seq search_space__31319)
                      (clojure.core/let
                       [elem__31018
                        (clojure.core/first search_space__31319)
                        result__31320
                        (clojure.core/let
                         [elem__31018_nth_0__
                          (clojure.core/nth elem__31018 0)
                          elem__31018_nth_1__
                          (clojure.core/nth elem__31018 1)]
                         (if
                          (clojure.core/symbol? elem__31018_nth_0__)
                          (clojure.core/let
                           [X__31020
                            (clojure.core/name elem__31018_nth_0__)]
                           (if
                            (clojure.core/= ?__30179 X__31020)
                            (if
                             (clojure.core/symbol? elem__31018_nth_1__)
                             (clojure.core/let
                              [X__31022
                               (clojure.core/name elem__31018_nth_1__)]
                              (clojure.core/case
                               X__31022
                               ("meander.zeta")
                               [?__30179]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__31320)
                        (recur (clojure.core/next search_space__31319))
                        result__31320))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__31317)))]
          (if
           (clojure.core/vector? input__30171)
           (if
            (clojure.core/= (clojure.core/count input__30171) 2)
            (clojure.core/let
             [input__30171_nth_0__
              (clojure.core/nth input__30171 0)
              input__30171_nth_1__
              (clojure.core/nth input__30171 1)]
             (if
              (clojure.core/seq? input__30171_nth_0__)
              (clojure.core/let
               [input__30171_nth_0___l__
                (clojure.core/take 1 input__30171_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__30171_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__30171_nth_0___r__
                  (clojure.core/drop 1 input__30171_nth_0__)]
                 (clojure.core/let
                  [input__30171_nth_0___l___nth_0__
                   (clojure.core/nth input__30171_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__30171_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__31000
                     (clojure.core/namespace
                      input__30171_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__30179 X__31000]
                     (clojure.core/let
                      [X__31002
                       (clojure.core/name
                        input__30171_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__31002
                       ("not")
                       (clojure.core/let
                        [x__9791__auto__
                         (def__30990 input__30171_nth_1__ ?__30179)]
                        (if
                         (meander.runtime.zeta/fail? x__9791__auto__)
                         (state__31162)
                         (clojure.core/let
                          [[?__30179] x__9791__auto__]
                          (if
                           (clojure.core/vector? input__30171)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__30171)
                             2)
                            (clojure.core/let
                             [input__30171_nth_0__
                              (clojure.core/nth input__30171 0)
                              input__30171_nth_1__
                              (clojure.core/nth input__30171 1)]
                             (if
                              (clojure.core/seq? input__30171_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__30171_nth_0__)
                                2)
                               (clojure.core/let
                                [input__30171_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__30171_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__30171_nth_0__]
                                  (clojure.core/let
                                   [?env input__30171_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__11094__auto__
                                        (CATA__FN__30216
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__11094__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__11094__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__12034__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__12034__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__12034__auto__))))))))
                               (state__31162))
                              (state__31162)))
                            (state__31162))
                           (state__31162)))))
                       (state__31162)))))
                   (state__31162))))
                (state__31162)))
              (state__31162)))
            (state__31162))
           (state__31162))))
        (state__31162
         []
         (clojure.core/letfn
          [(def__31024
            [arg__31047 ?__30180]
            (clojure.core/letfn
             [(state__31322
               []
               (clojure.core/let
                [x__31048 "meander.zeta"]
                (if
                 (clojure.core/= ?__30180 x__31048)
                 [?__30180]
                 (state__31323))))
              (state__31323
               []
               (if
                (clojure.core/map? arg__31047)
                (clojure.core/let
                 [VAL__31049 (.valAt arg__31047 :aliases)]
                 (if
                  (clojure.core/map? VAL__31049)
                  (clojure.core/let
                   [X__31051 (clojure.core/set VAL__31049)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__31051))
                    (clojure.core/loop
                     [search_space__31324 (clojure.core/seq X__31051)]
                     (if
                      (clojure.core/seq search_space__31324)
                      (clojure.core/let
                       [elem__31052
                        (clojure.core/first search_space__31324)
                        result__31325
                        (clojure.core/let
                         [elem__31052_nth_0__
                          (clojure.core/nth elem__31052 0)
                          elem__31052_nth_1__
                          (clojure.core/nth elem__31052 1)]
                         (if
                          (clojure.core/symbol? elem__31052_nth_0__)
                          (clojure.core/let
                           [X__31054
                            (clojure.core/name elem__31052_nth_0__)]
                           (if
                            (clojure.core/= ?__30180 X__31054)
                            (if
                             (clojure.core/symbol? elem__31052_nth_1__)
                             (clojure.core/let
                              [X__31056
                               (clojure.core/name elem__31052_nth_1__)]
                              (clojure.core/case
                               X__31056
                               ("meander.zeta")
                               [?__30180]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__31325)
                        (recur (clojure.core/next search_space__31324))
                        result__31325))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__31322)))]
          (if
           (clojure.core/vector? input__30171)
           (if
            (clojure.core/= (clojure.core/count input__30171) 2)
            (clojure.core/let
             [input__30171_nth_0__
              (clojure.core/nth input__30171 0)
              input__30171_nth_1__
              (clojure.core/nth input__30171 1)]
             (if
              (clojure.core/seq? input__30171_nth_0__)
              (clojure.core/let
               [input__30171_nth_0___l__
                (clojure.core/take 1 input__30171_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__30171_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__30171_nth_0___r__
                  (clojure.core/drop 1 input__30171_nth_0__)]
                 (clojure.core/let
                  [input__30171_nth_0___l___nth_0__
                   (clojure.core/nth input__30171_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__30171_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__31034
                     (clojure.core/namespace
                      input__30171_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__30180 X__31034]
                     (clojure.core/let
                      [X__31036
                       (clojure.core/name
                        input__30171_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__31036
                       ("or")
                       (clojure.core/let
                        [x__9791__auto__
                         (def__31024 input__30171_nth_1__ ?__30180)]
                        (if
                         (meander.runtime.zeta/fail? x__9791__auto__)
                         (state__31163)
                         (clojure.core/let
                          [[?__30180] x__9791__auto__]
                          (if
                           (clojure.core/vector? input__30171)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__30171)
                             2)
                            (clojure.core/let
                             [input__30171_nth_0__
                              (clojure.core/nth input__30171 0)
                              input__30171_nth_1__
                              (clojure.core/nth input__30171 1)]
                             (if
                              (clojure.core/seq? input__30171_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__30171_nth_0__)
                                3)
                               (clojure.core/let
                                [input__30171_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  1)
                                 input__30171_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__30171_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__30171_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__30171_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__30171_nth_0__]
                                   (clojure.core/let
                                    [?env input__30171_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__11094__auto__
                                         (CATA__FN__30216
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__11094__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__11094__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__11094__auto__
                                         (CATA__FN__30216
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__11094__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__11094__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__12034__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__12034__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__12034__auto__)))))))))
                               (state__31163))
                              (state__31163)))
                            (state__31163))
                           (state__31163)))))
                       (state__31163)))))
                   (state__31163))))
                (state__31163)))
              (state__31163)))
            (state__31163))
           (state__31163))))
        (state__31163
         []
         (clojure.core/letfn
          [(def__31058
            [arg__31081 ?__30181]
            (clojure.core/letfn
             [(state__31327
               []
               (clojure.core/let
                [x__31082 "meander.zeta"]
                (if
                 (clojure.core/= ?__30181 x__31082)
                 [?__30181]
                 (state__31328))))
              (state__31328
               []
               (if
                (clojure.core/map? arg__31081)
                (clojure.core/let
                 [VAL__31083 (.valAt arg__31081 :aliases)]
                 (if
                  (clojure.core/map? VAL__31083)
                  (clojure.core/let
                   [X__31085 (clojure.core/set VAL__31083)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__31085))
                    (clojure.core/loop
                     [search_space__31329 (clojure.core/seq X__31085)]
                     (if
                      (clojure.core/seq search_space__31329)
                      (clojure.core/let
                       [elem__31086
                        (clojure.core/first search_space__31329)
                        result__31330
                        (clojure.core/let
                         [elem__31086_nth_0__
                          (clojure.core/nth elem__31086 0)
                          elem__31086_nth_1__
                          (clojure.core/nth elem__31086 1)]
                         (if
                          (clojure.core/symbol? elem__31086_nth_0__)
                          (clojure.core/let
                           [X__31088
                            (clojure.core/name elem__31086_nth_0__)]
                           (if
                            (clojure.core/= ?__30181 X__31088)
                            (if
                             (clojure.core/symbol? elem__31086_nth_1__)
                             (clojure.core/let
                              [X__31090
                               (clojure.core/name elem__31086_nth_1__)]
                              (clojure.core/case
                               X__31090
                               ("meander.zeta")
                               [?__30181]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__31330)
                        (recur (clojure.core/next search_space__31329))
                        result__31330))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__31327)))]
          (if
           (clojure.core/vector? input__30171)
           (if
            (clojure.core/= (clojure.core/count input__30171) 2)
            (clojure.core/let
             [input__30171_nth_0__
              (clojure.core/nth input__30171 0)
              input__30171_nth_1__
              (clojure.core/nth input__30171 1)]
             (if
              (clojure.core/seq? input__30171_nth_0__)
              (clojure.core/let
               [input__30171_nth_0___l__
                (clojure.core/take 1 input__30171_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__30171_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__30171_nth_0___r__
                  (clojure.core/drop 1 input__30171_nth_0__)]
                 (clojure.core/let
                  [input__30171_nth_0___l___nth_0__
                   (clojure.core/nth input__30171_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__30171_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__31068
                     (clojure.core/namespace
                      input__30171_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__30181 X__31068]
                     (clojure.core/let
                      [X__31070
                       (clojure.core/name
                        input__30171_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__31070
                       ("string")
                       (clojure.core/let
                        [x__9791__auto__
                         (def__31058 input__30171_nth_1__ ?__30181)]
                        (if
                         (meander.runtime.zeta/fail? x__9791__auto__)
                         (state__31164)
                         (clojure.core/let
                          [[?__30181] x__9791__auto__]
                          (if
                           (clojure.core/vector? input__30171)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__30171)
                             2)
                            (clojure.core/let
                             [input__30171_nth_0__
                              (clojure.core/nth input__30171 0)
                              input__30171_nth_1__
                              (clojure.core/nth input__30171 1)]
                             (if
                              (clojure.core/seq? input__30171_nth_0__)
                              (clojure.core/let
                               [input__30171_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__30171_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__30171_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__30171_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__30171_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__30171_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__30171_nth_0__]
                                   (clojure.core/let
                                    [?env input__30171_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__11094__auto__
                                         (CATA__FN__30216
                                          ['meander.dev.parse.zeta/parse-string
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__11094__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__11094__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__12034__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__12034__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__12034__auto__))))))))
                                (state__31164)))
                              (state__31164)))
                            (state__31164))
                           (state__31164)))))
                       (state__31164)))))
                   (state__31164))))
                (state__31164)))
              (state__31164)))
            (state__31164))
           (state__31164))))
        (state__31164
         []
         (if
          (clojure.core/vector? input__30171)
          (if
           (clojure.core/= (clojure.core/count input__30171) 2)
           (clojure.core/let
            [input__30171_nth_0__ (clojure.core/nth input__30171 0)]
            (clojure.core/letfn
             [(state__31332
               []
               (clojure.core/let
                [input__30171_nth_1__
                 (clojure.core/nth input__30171 1)]
                (clojure.core/letfn
                 [(state__31337
                   []
                   (if
                    (clojure.core/seq? input__30171_nth_0__)
                    (clojure.core/let
                     [?sequence input__30171_nth_0__]
                     (clojure.core/let
                      [?env input__30171_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__11094__auto__
                           (CATA__FN__30216
                            ['meander.dev.parse.zeta/parse-seq
                             (clojure.core/into [] ?sequence)
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__11094__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__11094__auto__
                            0))),
                         :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__12034__auto__
                        (if
                         (meander.runtime.zeta/fail? e__12034__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__12034__auto__))))))
                    (state__31338)))
                  (state__31338
                   []
                   (if
                    (clojure.core/map? input__30171_nth_0__)
                    (clojure.core/let
                     [?map input__30171_nth_0__]
                     (clojure.core/let
                      [?env input__30171_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__11094__auto__
                           (CATA__FN__30216
                            ['meander.dev.parse.zeta/parse-entries
                             ?map
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__11094__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__11094__auto__
                            0))),
                         :form ?map}]
                       (catch
                        java.lang.Exception
                        e__12034__auto__
                        (if
                         (meander.runtime.zeta/fail? e__12034__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__12034__auto__))))))
                    (state__31333)))]
                 (state__31337))))
              (state__31333
               []
               (if
                (clojure.core/symbol? input__30171_nth_0__)
                (clojure.core/let
                 [X__31100
                  (clojure.core/namespace input__30171_nth_0__)]
                 (clojure.core/let
                  [X__31102 (clojure.core/name input__30171_nth_0__)]
                  (if
                   (clojure.core/string? X__31102)
                   (clojure.core/letfn
                    [(state__31339
                      []
                      (clojure.core/let
                       [ret__31103
                        (clojure.core/re-matches #"_.*" X__31102)]
                       (if
                        (clojure.core/some? ret__31103)
                        (clojure.core/let
                         [?name ret__31103]
                         (clojure.core/let
                          [?symbol input__30171_nth_0__]
                          (try
                           [{:tag :wildcard,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__12034__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__12034__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__12034__auto__))))))
                        (state__31340))))
                     (state__31340
                      []
                      (clojure.core/let
                       [ret__31110
                        (clojure.core/re-matches #".+#" X__31102)]
                       (if
                        (clojure.core/some? ret__31110)
                        (clojure.core/let
                         [?name ret__31110]
                         (clojure.core/let
                          [?symbol input__30171_nth_0__]
                          (try
                           [{:tag :random-symbol,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__12034__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__12034__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__12034__auto__))))))
                        (state__31341))))
                     (state__31341
                      []
                      (clojure.core/let
                       [ret__31117
                        (clojure.core/re-matches #"%.+" X__31102)]
                       (if
                        (clojure.core/some? ret__31117)
                        (clojure.core/let
                         [?name ret__31117]
                         (clojure.core/let
                          [?symbol input__30171_nth_0__]
                          (try
                           [{:tag :reference,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__12034__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__12034__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__12034__auto__))))))
                        (state__31342))))
                     (state__31342
                      []
                      (clojure.core/let
                       [ret__31124
                        (clojure.core/re-matches #"\*.+" X__31102)]
                       (if
                        (clojure.core/some? ret__31124)
                        (clojure.core/let
                         [?name ret__31124]
                         (clojure.core/let
                          [?symbol input__30171_nth_0__]
                          (try
                           [{:tag :mutable-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__12034__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__12034__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__12034__auto__))))))
                        (state__31343))))
                     (state__31343
                      []
                      (clojure.core/let
                       [ret__31131
                        (clojure.core/re-matches #"\!.+" X__31102)]
                       (if
                        (clojure.core/some? ret__31131)
                        (clojure.core/let
                         [?name ret__31131]
                         (clojure.core/let
                          [?symbol input__30171_nth_0__]
                          (try
                           [{:tag :memory-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__12034__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__12034__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__12034__auto__))))))
                        (state__31344))))
                     (state__31344
                      []
                      (clojure.core/let
                       [ret__31138
                        (clojure.core/re-matches #"\?.+" X__31102)]
                       (if
                        (clojure.core/some? ret__31138)
                        (clojure.core/let
                         [?name ret__31138]
                         (clojure.core/let
                          [?symbol input__30171_nth_0__]
                          (try
                           [{:tag :logic-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__12034__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__12034__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__12034__auto__))))))
                        (state__31334))))]
                    (state__31339))
                   (state__31334))))
                (state__31334)))
              (state__31334
               []
               (if
                (string? input__30171_nth_0__)
                (clojure.core/let
                 [?x input__30171_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__12034__auto__
                   (if
                    (meander.runtime.zeta/fail? e__12034__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__12034__auto__)))))
                (state__31335)))
              (state__31335
               []
               (if
                (char? input__30171_nth_0__)
                (clojure.core/let
                 [?x input__30171_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__12034__auto__
                   (if
                    (meander.runtime.zeta/fail? e__12034__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__12034__auto__)))))
                (state__31336)))
              (state__31336
               []
               (clojure.core/let
                [?x input__30171_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__12034__auto__
                  (if
                   (meander.runtime.zeta/fail? e__12034__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__12034__auto__))))))]
             (state__31332)))
           (state__31165))
          (state__31165)))
        (state__31165
         []
         (clojure.core/let
          [?x input__30171]
          (try
           [?x]
           (catch
            java.lang.Exception
            e__12034__auto__
            (if
             (meander.runtime.zeta/fail? e__12034__auto__)
             (meander.runtime.zeta/fail)
             (throw e__12034__auto__))))))]
       (state__31145)))]
    (clojure.core/let
     [x__9791__auto__ (CATA__FN__30216 input__30171)]
     (if
      (meander.runtime.zeta/fail? x__9791__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__30219] x__9791__auto__]
       CATA_RETURN__30219))))]
  (if
   (meander.runtime.zeta/fail? ret__9971__auto__)
   nil
   ret__9971__auto__)))
