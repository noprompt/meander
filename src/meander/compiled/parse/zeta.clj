(ns
 meander.compiled.parse.zeta
 (:require [meander.runtime.zeta] [meander.util.zeta]))
(clojure.core/defn
 parse
 [input__77076]
 (let*
  [ret__8139__auto__
   (clojure.core/letfn
    [(CATA__FN__77153
      [input__77076]
      (clojure.core/letfn
       [(state__78572
         []
         (if
          (clojure.core/vector? input__77076)
          (if
           (clojure.core/= (clojure.core/count input__77076) 3)
           (clojure.core/let
            [input__77076_nth_0__
             (clojure.core/nth input__77076 0)
             input__77076_nth_1__
             (clojure.core/nth input__77076 1)
             input__77076_nth_2__
             (clojure.core/nth input__77076 2)]
            (clojure.core/case
             input__77076_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__77076_nth_1__)
              (clojure.core/letfn
               [(state__78611
                 []
                 (clojure.core/case
                  input__77076_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__77076_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__10202__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10202__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10202__auto__)))))
                  (state__78612)))
                (state__78612
                 []
                 (clojure.core/let
                  [n__77162
                   (clojure.core/count input__77076_nth_1__)
                   m__77163
                   (clojure.core/max 0 (clojure.core/- n__77162 2))
                   input__77076_nth_1___l__
                   (clojure.core/subvec
                    input__77076_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__77076_nth_1__)
                     m__77163))
                   input__77076_nth_1___r__
                   (clojure.core/subvec input__77076_nth_1__ m__77163)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__77076_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__77076_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__77076_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__77076_nth_1___r___nth_0__
                       (clojure.core/nth input__77076_nth_1___r__ 0)
                       input__77076_nth_1___r___nth_1__
                       (clojure.core/nth input__77076_nth_1___r__ 1)]
                      (clojure.core/case
                       input__77076_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__77076_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__77076_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__9262__auto__
                               (CATA__FN__77153 [?pattern ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__9262__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__9262__auto__
                                0))),
                             :next
                             (clojure.core/let
                              [CATA_RESULT__9262__auto__
                               (CATA__FN__77153
                                ['meander.dev.parse.zeta/parse-sequential
                                 (clojure.core/into
                                  []
                                  (clojure.core/vec
                                   (clojure.core/iterator-seq
                                    !xs__counter)))
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__9262__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__9262__auto__
                                0)))})]
                          (catch
                           java.lang.Exception
                           e__10202__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10202__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10202__auto__))))))
                       (state__78573)))
                     (state__78573)))
                   (state__78573))))]
               (state__78611))
              (state__78573))
             (state__78573)))
           (state__78573))
          (state__78573)))
        (state__78573
         []
         (clojure.core/letfn
          [(def__77168
            [arg__77203 ?ns]
            (clojure.core/letfn
             [(state__78613
               []
               (clojure.core/let
                [x__77204 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__77204)
                 (clojure.core/let [?env arg__77203] [?env ?ns])
                 (state__78614))))
              (state__78614
               []
               (if
                (clojure.core/map? arg__77203)
                (clojure.core/let
                 [VAL__77205 (.valAt arg__77203 :aliases)]
                 (if
                  (clojure.core/map? VAL__77205)
                  (clojure.core/let
                   [X__77207 (clojure.core/set VAL__77205)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__77207))
                    (clojure.core/loop
                     [search_space__78615 (clojure.core/seq X__77207)]
                     (if
                      (clojure.core/seq search_space__78615)
                      (clojure.core/let
                       [elem__77208
                        (clojure.core/first search_space__78615)
                        result__78616
                        (clojure.core/let
                         [elem__77208_nth_0__
                          (clojure.core/nth elem__77208 0)
                          elem__77208_nth_1__
                          (clojure.core/nth elem__77208 1)]
                         (if
                          (clojure.core/symbol? elem__77208_nth_0__)
                          (clojure.core/let
                           [X__77210
                            (clojure.core/name elem__77208_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__77210)
                            (if
                             (clojure.core/symbol? elem__77208_nth_1__)
                             (clojure.core/let
                              [X__77212
                               (clojure.core/name elem__77208_nth_1__)]
                              (clojure.core/case
                               X__77212
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__77203]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78616)
                        (recur (clojure.core/next search_space__78615))
                        result__78616))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78613)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 3)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)
              input__77076_nth_2__
              (clojure.core/nth input__77076 2)]
             (clojure.core/case
              input__77076_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__77076_nth_1__)
               (clojure.core/loop
                [search_space__78618
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__77076_nth_1__)]
                (if
                 (clojure.core/seq search_space__78618)
                 (clojure.core/let
                  [input__77076_nth_1___parts__
                   (clojure.core/first search_space__78618)
                   result__78619
                   (clojure.core/let
                    [input__77076_nth_1___l__
                     (clojure.core/nth input__77076_nth_1___parts__ 0)
                     input__77076_nth_1___r__
                     (clojure.core/nth input__77076_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__77076_nth_1___l__)]
                     (clojure.core/let
                      [input__77076_nth_1___r___l__
                       (clojure.core/subvec
                        input__77076_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__77076_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__77076_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__77076_nth_1___r___r__
                         (clojure.core/subvec
                          input__77076_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__77076_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__77076_nth_1___r___l__
                           0)
                          input__77076_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__77076_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__77076_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__77177
                            (clojure.core/namespace
                             input__77076_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__77177]
                            (clojure.core/let
                             [X__77179
                              (clojure.core/name
                               input__77076_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__77179)
                              (clojure.core/let
                               [ret__77180
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__77179)]
                               (if
                                (clojure.core/some? ret__77180)
                                (if
                                 (clojure.core/vector? ret__77180)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__77180)
                                   2)
                                  (clojure.core/let
                                   [ret__77180_nth_1__
                                    (clojure.core/nth ret__77180 1)]
                                   (clojure.core/let
                                    [?n ret__77180_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__77076_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__77076_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__7959__auto__
                                        (def__77168
                                         input__77076_nth_2__
                                         ?ns)]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         x__7959__auto__)
                                        (meander.runtime.zeta/fail)
                                        (clojure.core/let
                                         [[?env ?ns] x__7959__auto__]
                                         (try
                                          [(clojure.core/let
                                            [!init__counter
                                             (meander.runtime.zeta/iterator
                                              !init)]
                                            (clojure.core/let
                                             [CATA_RESULT__9262__auto__
                                              (CATA__FN__77153
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__9262__auto__
                                                  (CATA__FN__77153
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !init__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9262__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9262__auto__
                                                   0)))
                                                (clojure.core/let
                                                 [CATA_RESULT__9262__auto__
                                                  (CATA__FN__77153
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__9262__auto__
                                                       (CATA__FN__77153
                                                        [?pattern
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        CATA_RESULT__9262__auto__)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        CATA_RESULT__9262__auto__
                                                        0)))}
                                                    (clojure.core/let
                                                     [CATA_RESULT__9262__auto__
                                                      (CATA__FN__77153
                                                       ['meander.dev.parse.zeta/parse-sequential
                                                        (clojure.core/into
                                                         []
                                                         ?rest)
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__9262__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__9262__auto__
                                                       0)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9262__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9262__auto__
                                                   0)))
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__9262__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__9262__auto__
                                               0))))]
                                          (catch
                                           java.lang.Exception
                                           e__10202__auto__
                                           (if
                                            (meander.runtime.zeta/fail?
                                             e__10202__auto__)
                                            (meander.runtime.zeta/fail)
                                            (throw
                                             e__10202__auto__)))))))))))
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__78619)
                   (recur (clojure.core/next search_space__78618))
                   result__78619))
                 (state__78574)))
               (state__78574))
              (state__78574)))
            (state__78574))
           (state__78574))))
        (state__78574
         []
         (clojure.core/letfn
          [(def__77225
            [arg__77257 ?ns]
            (clojure.core/letfn
             [(state__78621
               []
               (clojure.core/let
                [x__77258 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__77258)
                 (clojure.core/let [?env arg__77257] [?env ?ns])
                 (state__78622))))
              (state__78622
               []
               (if
                (clojure.core/map? arg__77257)
                (clojure.core/let
                 [VAL__77259 (.valAt arg__77257 :aliases)]
                 (if
                  (clojure.core/map? VAL__77259)
                  (clojure.core/let
                   [X__77261 (clojure.core/set VAL__77259)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__77261))
                    (clojure.core/loop
                     [search_space__78623 (clojure.core/seq X__77261)]
                     (if
                      (clojure.core/seq search_space__78623)
                      (clojure.core/let
                       [elem__77262
                        (clojure.core/first search_space__78623)
                        result__78624
                        (clojure.core/let
                         [elem__77262_nth_0__
                          (clojure.core/nth elem__77262 0)
                          elem__77262_nth_1__
                          (clojure.core/nth elem__77262 1)]
                         (if
                          (clojure.core/symbol? elem__77262_nth_0__)
                          (clojure.core/let
                           [X__77264
                            (clojure.core/name elem__77262_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__77264)
                            (if
                             (clojure.core/symbol? elem__77262_nth_1__)
                             (clojure.core/let
                              [X__77266
                               (clojure.core/name elem__77262_nth_1__)]
                              (clojure.core/case
                               X__77266
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__77257]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78624)
                        (recur (clojure.core/next search_space__78623))
                        result__78624))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78621)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 3)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)
              input__77076_nth_2__
              (clojure.core/nth input__77076 2)]
             (clojure.core/case
              input__77076_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__77076_nth_1__)
               (clojure.core/loop
                [search_space__78626
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__77076_nth_1__)]
                (if
                 (clojure.core/seq search_space__78626)
                 (clojure.core/let
                  [input__77076_nth_1___parts__
                   (clojure.core/first search_space__78626)
                   result__78627
                   (clojure.core/let
                    [input__77076_nth_1___l__
                     (clojure.core/nth input__77076_nth_1___parts__ 0)
                     input__77076_nth_1___r__
                     (clojure.core/nth input__77076_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__77076_nth_1___l__)]
                     (clojure.core/let
                      [input__77076_nth_1___r___l__
                       (clojure.core/subvec
                        input__77076_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__77076_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__77076_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__77076_nth_1___r___r__
                         (clojure.core/subvec
                          input__77076_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__77076_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__77076_nth_1___r___l__
                           0)
                          input__77076_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__77076_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__77076_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__77234
                            (clojure.core/namespace
                             input__77076_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__77234]
                            (clojure.core/let
                             [X__77236
                              (clojure.core/name
                               input__77076_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__77236)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__77236)
                               (clojure.core/let
                                [?pattern
                                 input__77076_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__77076_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__7959__auto__
                                   (def__77225
                                    input__77076_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__7959__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__7959__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__9262__auto__
                                             (CATA__FN__77153
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9262__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9262__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__9262__auto__
                                             (CATA__FN__77153
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__9262__auto__
                                                 (CATA__FN__77153
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__9262__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__9262__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__9262__auto__
                                                 (CATA__FN__77153
                                                  ['meander.dev.parse.zeta/parse-sequential
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__9262__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__9262__auto__
                                                  0)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9262__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9262__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__78627)
                   (recur (clojure.core/next search_space__78626))
                   result__78627))
                 (state__78575)))
               (state__78575))
              (state__78575)))
            (state__78575))
           (state__78575))))
        (state__78575
         []
         (if
          (clojure.core/vector? input__77076)
          (clojure.core/letfn
           [(state__78629
             []
             (if
              (clojure.core/= (clojure.core/count input__77076) 3)
              (clojure.core/let
               [input__77076_nth_0__
                (clojure.core/nth input__77076 0)
                input__77076_nth_1__
                (clojure.core/nth input__77076 1)
                input__77076_nth_2__
                (clojure.core/nth input__77076 2)]
               (clojure.core/case
                input__77076_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__77076_nth_1__)
                 (clojure.core/letfn
                  [(state__78632
                    []
                    (clojure.core/let
                     [n__77287
                      (clojure.core/count input__77076_nth_1__)
                      m__77288
                      (clojure.core/max 0 (clojure.core/- n__77287 2))
                      input__77076_nth_1___l__
                      (clojure.core/subvec
                       input__77076_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__77076_nth_1__)
                        m__77288))
                      input__77076_nth_1___r__
                      (clojure.core/subvec
                       input__77076_nth_1__
                       m__77288)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__77076_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__77076_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__77076_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__77076_nth_1___r___nth_0__
                          (clojure.core/nth input__77076_nth_1___r__ 0)
                          input__77076_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__77076_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__77076_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__77292
                            (clojure.core/namespace
                             input__77076_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__77292]
                            (clojure.core/let
                             [X__77294
                              (clojure.core/name
                               input__77076_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__77294)
                              (clojure.core/let
                               [ret__77295
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__77294)]
                               (if
                                (clojure.core/some? ret__77295)
                                (clojure.core/let
                                 [?name ret__77295]
                                 (clojure.core/let
                                  [?pattern
                                   input__77076_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__77076_nth_2__)
                                   (clojure.core/let
                                    [VAL__77279
                                     (.valAt
                                      input__77076_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__77279)
                                     (clojure.core/let
                                      [X__77281
                                       (clojure.core/set VAL__77279)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__77281))
                                       (clojure.core/loop
                                        [search_space__78636
                                         (clojure.core/seq X__77281)]
                                        (if
                                         (clojure.core/seq
                                          search_space__78636)
                                         (clojure.core/let
                                          [elem__77282
                                           (clojure.core/first
                                            search_space__78636)
                                           result__78637
                                           (clojure.core/let
                                            [elem__77282_nth_0__
                                             (clojure.core/nth
                                              elem__77282
                                              0)
                                             elem__77282_nth_1__
                                             (clojure.core/nth
                                              elem__77282
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__77282_nth_0__)
                                             (clojure.core/let
                                              [X__77284
                                               (clojure.core/name
                                                elem__77282_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__77284)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__77282_nth_1__)
                                                (clojure.core/let
                                                 [X__77286
                                                  (clojure.core/name
                                                   elem__77282_nth_1__)]
                                                 (clojure.core/case
                                                  X__77286
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__77076_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__9262__auto__
                                                        (CATA__FN__77153
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
                                                         CATA_RESULT__9262__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__9262__auto__
                                                         0))))]
                                                    (catch
                                                     java.lang.Exception
                                                     e__10202__auto__
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       e__10202__auto__)
                                                      (meander.runtime.zeta/fail)
                                                      (throw
                                                       e__10202__auto__)))))
                                                  (meander.runtime.zeta/fail)))
                                                (meander.runtime.zeta/fail))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail)))]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            result__78637)
                                           (recur
                                            (clojure.core/next
                                             search_space__78636))
                                           result__78637))
                                         (state__78633)))
                                       (state__78633)))
                                     (state__78633)))
                                   (state__78633))))
                                (state__78633)))
                              (state__78633)))))
                          (state__78633)))
                        (state__78633)))
                      (state__78633))))
                   (state__78633
                    []
                    (clojure.core/loop
                     [search_space__78639
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__77076_nth_1__)]
                     (if
                      (clojure.core/seq search_space__78639)
                      (clojure.core/let
                       [input__77076_nth_1___parts__
                        (clojure.core/first search_space__78639)
                        result__78640
                        (clojure.core/let
                         [input__77076_nth_1___l__
                          (clojure.core/nth
                           input__77076_nth_1___parts__
                           0)
                          input__77076_nth_1___r__
                          (clojure.core/nth
                           input__77076_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__77076_nth_1___l__)]
                          (clojure.core/let
                           [input__77076_nth_1___r___l__
                            (clojure.core/subvec
                             input__77076_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__77076_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__77076_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__77076_nth_1___r___r__
                              (clojure.core/subvec
                               input__77076_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__77076_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__77076_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__77076_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__9262__auto__
                                     (CATA__FN__77153
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            (clojure.core/vec
                                             (clojure.core/iterator-seq
                                              !xs__counter)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0)))
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          ['meander.dev.parse.zeta/parse-sequential
                                           ?rest
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0)))
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9262__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9262__auto__
                                      0))))]
                                 (catch
                                  java.lang.Exception
                                  e__10202__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10202__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10202__auto__))))))
                              (meander.runtime.zeta/fail)))
                            (meander.runtime.zeta/fail)))))]
                       (if
                        (meander.runtime.zeta/fail? result__78640)
                        (recur (clojure.core/next search_space__78639))
                        result__78640))
                      (state__78634))))
                   (state__78634
                    []
                    (clojure.core/let
                     [input__77076_nth_1___l__
                      (clojure.core/subvec
                       input__77076_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__77076_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__77076_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__77076_nth_1___r__
                        (clojure.core/subvec input__77076_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__77076_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__77076_nth_1___r__]
                         (clojure.core/let
                          [?env input__77076_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__9262__auto__
                              (CATA__FN__77153
                               ['meander.dev.parse.zeta/parse-sequential
                                ?rest
                                ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9262__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9262__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__10202__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10202__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10202__auto__))))))
                        (state__78635)))
                      (state__78635))))
                   (state__78635
                    []
                    (clojure.core/loop
                     [search_space__78642
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__77076_nth_1__)]
                     (if
                      (clojure.core/seq search_space__78642)
                      (clojure.core/let
                       [input__77076_nth_1___parts__
                        (clojure.core/first search_space__78642)
                        result__78643
                        (clojure.core/let
                         [input__77076_nth_1___l__
                          (clojure.core/nth
                           input__77076_nth_1___parts__
                           0)
                          input__77076_nth_1___r__
                          (clojure.core/nth
                           input__77076_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8123__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__77076_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__77312]
                              (clojure.core/let
                               [input__77312_nth_0__
                                (clojure.core/nth input__77312 0)]
                               (clojure.core/letfn
                                [(save__77313
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__78646
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__77312_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__77312_nth_0__)
                                 (clojure.core/let
                                  [X__77315
                                   (clojure.core/namespace
                                    input__77312_nth_0__)]
                                  (clojure.core/case
                                   X__77315
                                   (nil)
                                   (clojure.core/let
                                    [X__77317
                                     (clojure.core/name
                                      input__77312_nth_0__)]
                                    (if
                                     (clojure.core/string? X__77317)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__77317)
                                      (save__77313)
                                      (f__78646))
                                     (f__78646)))
                                   (f__78646)))
                                 (f__78646)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__77076_nth_1___r___l__
                                (clojure.core/subvec
                                 input__77076_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__77076_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__77076_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__77076_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__77076_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__77076_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__77076_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__77076_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__9262__auto__
                                             (CATA__FN__77153
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !xs__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9262__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9262__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__9262__auto__
                                             (CATA__FN__77153
                                              ['meander.dev.parse.zeta/parse-sequential
                                               ?rest
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9262__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9262__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__))))))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))))]
                           (if
                            (meander.runtime.zeta/fail?
                             ret__8123__auto__)
                            (meander.runtime.zeta/fail)
                            ret__8123__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__78643)
                        (recur (clojure.core/next search_space__78642))
                        result__78643))
                      (state__78630))))]
                  (state__78632))
                 (state__78630))
                (state__78630)))
              (state__78630)))
            (state__78630
             []
             (if
              (clojure.core/= (clojure.core/count input__77076) 4)
              (clojure.core/let
               [input__77076_nth_0__
                (clojure.core/nth input__77076 0)
                input__77076_nth_1__
                (clojure.core/nth input__77076 1)
                input__77076_nth_2__
                (clojure.core/nth input__77076 2)]
               (clojure.core/letfn
                [(state__78647
                  []
                  (clojure.core/let
                   [input__77076_nth_3__
                    (clojure.core/nth input__77076 3)]
                   (clojure.core/case
                    input__77076_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__78649
                       []
                       (if
                        (clojure.core/map? input__77076_nth_1__)
                        (clojure.core/let
                         [VAL__77321
                          (.valAt input__77076_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__77321
                          (:cat)
                          (clojure.core/let
                           [VAL__77322
                            (.valAt input__77076_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__77322)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__77322)
                              1)
                             (clojure.core/let
                              [VAL__77322_nth_0__
                               (clojure.core/nth VAL__77322 0)]
                              (if
                               (clojure.core/map? VAL__77322_nth_0__)
                               (clojure.core/let
                                [VAL__77327
                                 (.valAt VAL__77322_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__77327
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__77322_nth_0__]
                                  (clojure.core/let
                                   [VAL__77323
                                    (.valAt
                                     input__77076_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__77323)
                                    (clojure.core/let
                                     [VAL__77324
                                      (.valAt VAL__77323 :tag)]
                                     (clojure.core/case
                                      VAL__77324
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__77076_nth_2__]
                                       (clojure.core/let
                                        [?env input__77076_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9262__auto__
                                            (CATA__FN__77153
                                             ['meander.dev.parse.zeta/make-join
                                              {:tag :into,
                                               :memory-variable
                                               ?memory-variable}
                                              ?next
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__9262__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__9262__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__10202__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__10202__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__10202__auto__))))))
                                      (state__78650)))
                                    (state__78650))))
                                 (state__78650)))
                               (state__78650)))
                             (state__78650))
                            (state__78650)))
                          (state__78650)))
                        (state__78650)))
                      (state__78650
                       []
                       (clojure.core/let
                        [?pattern input__77076_nth_1__]
                        (clojure.core/let
                         [?next input__77076_nth_2__]
                         (if
                          (clojure.core/map? input__77076_nth_3__)
                          (clojure.core/let
                           [VAL__77330
                            (.valAt input__77076_nth_3__ :context)]
                           (clojure.core/case
                            VAL__77330
                            (:string)
                            (try
                             [{:tag :string-star,
                               :greedy? false,
                               :pattern ?pattern,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__10202__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10202__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10202__auto__))))
                            (state__78648)))
                          (state__78648)))))]
                     (state__78649))
                    (state__78648))))
                 (state__78648
                  []
                  (clojure.core/case
                   input__77076_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__77076_nth_1__]
                    (clojure.core/let
                     [?next input__77076_nth_2__]
                     (try
                      [{:tag :star,
                        :greedy? false,
                        :pattern ?pattern,
                        :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10202__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10202__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10202__auto__))))))
                   (state__78631)))]
                (state__78647)))
              (state__78631)))
            (state__78631
             []
             (if
              (clojure.core/= (clojure.core/count input__77076) 3)
              (clojure.core/let
               [input__77076_nth_0__
                (clojure.core/nth input__77076 0)
                input__77076_nth_1__
                (clojure.core/nth input__77076 1)
                input__77076_nth_2__
                (clojure.core/nth input__77076 2)]
               (clojure.core/case
                input__77076_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__77076_nth_1__)
                 (clojure.core/let
                  [input__77076_nth_1___l__
                   (clojure.core/subvec
                    input__77076_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__77076_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__77076_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__77076_nth_1___r__
                     (clojure.core/subvec input__77076_nth_1__ 1)]
                    (clojure.core/let
                     [input__77076_nth_1___l___nth_0__
                      (clojure.core/nth input__77076_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__77076_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__77338
                        (clojure.core/namespace
                         input__77076_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__77338
                        (nil)
                        (clojure.core/let
                         [X__77340
                          (clojure.core/name
                           input__77076_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__77340)
                          (clojure.core/let
                           [ret__77341
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__77340)]
                           (if
                            (clojure.core/some? ret__77341)
                            (if
                             (clojure.core/vector? ret__77341)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__77341)
                               2)
                              (clojure.core/let
                               [ret__77341_nth_1__
                                (clojure.core/nth ret__77341 1)]
                               (clojure.core/let
                                [?n ret__77341_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__77076_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__77076_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__77076_nth_2__]
                                   (try
                                    [{:tag :syntax-error,
                                      :message
                                      "The n or more operator ..N must be preceeded by at least one pattern"}]
                                    (catch
                                     java.lang.Exception
                                     e__10202__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10202__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10202__auto__)))))))))
                              (state__78576))
                             (state__78576))
                            (state__78576)))
                          (state__78576)))
                        (state__78576)))
                      (state__78576))))
                   (state__78576)))
                 (state__78576))
                (state__78576)))
              (state__78576)))]
           (state__78629))
          (state__78576)))
        (state__78576
         []
         (clojure.core/letfn
          [(def__77344
            [arg__77368]
            (clojure.core/letfn
             [(state__78651
               []
               (clojure.core/let
                [x__77369 :string-plus]
                (clojure.core/let
                 [?tag x__77369]
                 (if
                  (clojure.core/map? arg__77368)
                  (clojure.core/let
                   [VAL__77370 (.valAt arg__77368 :context)]
                   (clojure.core/case
                    VAL__77370
                    (:string)
                    (clojure.core/let [?env arg__77368] [?tag ?env])
                    (state__78652)))
                  (state__78652)))))
              (state__78652
               []
               (clojure.core/let
                [x__77371 :plus]
                (clojure.core/let
                 [?tag x__77371]
                 (clojure.core/let [?env arg__77368] [?tag ?env]))))]
             (state__78651)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 3)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)
              input__77076_nth_2__
              (clojure.core/nth input__77076 2)]
             (clojure.core/case
              input__77076_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__77076_nth_1__)
               (clojure.core/loop
                [search_space__78653
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__77076_nth_1__)]
                (if
                 (clojure.core/seq search_space__78653)
                 (clojure.core/let
                  [input__77076_nth_1___parts__
                   (clojure.core/first search_space__78653)
                   result__78654
                   (clojure.core/let
                    [input__77076_nth_1___l__
                     (clojure.core/nth input__77076_nth_1___parts__ 0)
                     input__77076_nth_1___r__
                     (clojure.core/nth input__77076_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8123__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__77076_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__77361]
                         (clojure.core/let
                          [input__77361_nth_0__
                           (clojure.core/nth input__77361 0)]
                          (clojure.core/letfn
                           [(save__77362
                             []
                             (meander.runtime.zeta/fail))
                            (f__78657
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__77361_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__77361_nth_0__)
                            (clojure.core/let
                             [X__77364
                              (clojure.core/namespace
                               input__77361_nth_0__)]
                             (clojure.core/case
                              X__77364
                              (nil)
                              (clojure.core/let
                               [X__77366
                                (clojure.core/name
                                 input__77361_nth_0__)]
                               (if
                                (clojure.core/string? X__77366)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__77366)
                                 (save__77362)
                                 (f__78657))
                                (f__78657)))
                              (f__78657)))
                            (f__78657)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__77076_nth_1___r___l__
                           (clojure.core/subvec
                            input__77076_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__77076_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__77076_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__77076_nth_1___r___r__
                             (clojure.core/subvec
                              input__77076_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__77076_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__77076_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__77076_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__77355
                                (clojure.core/namespace
                                 input__77076_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__77355
                                (nil)
                                (clojure.core/let
                                 [X__77357
                                  (clojure.core/name
                                   input__77076_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__77357)
                                  (clojure.core/let
                                   [ret__77358
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__77357)]
                                   (if
                                    (clojure.core/some? ret__77358)
                                    (if
                                     (clojure.core/vector? ret__77358)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__77358)
                                       2)
                                      (clojure.core/let
                                       [ret__77358_nth_1__
                                        (clojure.core/nth
                                         ret__77358
                                         1)]
                                       (clojure.core/let
                                        [?n ret__77358_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__77076_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7959__auto__
                                           (def__77344
                                            input__77076_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__7959__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__7959__auto__]
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
                                                 [CATA_RESULT__9262__auto__
                                                  (CATA__FN__77153
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9262__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9262__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__9262__auto__
                                                  (CATA__FN__77153
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9262__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9262__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__10202__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10202__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10202__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8123__auto__)
                       (meander.runtime.zeta/fail)
                       ret__8123__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__78654)
                   (recur (clojure.core/next search_space__78653))
                   result__78654))
                 (state__78577)))
               (state__78577))
              (state__78577)))
            (state__78577))
           (state__78577))))
        (state__78577
         []
         (if
          (clojure.core/vector? input__77076)
          (if
           (clojure.core/= (clojure.core/count input__77076) 3)
           (clojure.core/let
            [input__77076_nth_0__
             (clojure.core/nth input__77076 0)
             input__77076_nth_1__
             (clojure.core/nth input__77076 1)
             input__77076_nth_2__
             (clojure.core/nth input__77076 2)]
            (clojure.core/case
             input__77076_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__77076_nth_1__)
              (clojure.core/let
               [input__77076_nth_1___l__
                (clojure.core/subvec
                 input__77076_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__77076_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__77076_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_1___r__
                  (clojure.core/subvec input__77076_nth_1__ 1)]
                 (clojure.core/let
                  [input__77076_nth_1___l___nth_0__
                   (clojure.core/nth input__77076_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__77389
                     (clojure.core/namespace
                      input__77076_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__77389
                     (nil)
                     (clojure.core/let
                      [X__77391
                       (clojure.core/name
                        input__77076_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__77391)
                       (clojure.core/let
                        [ret__77392
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__77391)]
                        (if
                         (clojure.core/some? ret__77392)
                         (if
                          (clojure.core/vector? ret__77392)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__77392)
                            2)
                           (clojure.core/let
                            [ret__77392_nth_1__
                             (clojure.core/nth ret__77392 1)]
                            (clojure.core/let
                             [?n ret__77392_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__77076_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__77076_nth_1___r__]
                               (clojure.core/let
                                [?env input__77076_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The ?n or more operator ..?n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__10202__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10202__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10202__auto__)))))))))
                           (state__78578))
                          (state__78578))
                         (state__78578)))
                       (state__78578)))
                     (state__78578)))
                   (state__78578))))
                (state__78578)))
              (state__78578))
             (state__78578)))
           (state__78578))
          (state__78578)))
        (state__78578
         []
         (clojure.core/letfn
          [(def__77395
            [arg__77419]
            (clojure.core/letfn
             [(state__78658
               []
               (clojure.core/let
                [x__77420 :string-logical-plus]
                (clojure.core/let
                 [?tag x__77420]
                 (if
                  (clojure.core/map? arg__77419)
                  (clojure.core/let
                   [VAL__77421 (.valAt arg__77419 :context)]
                   (clojure.core/case
                    VAL__77421
                    (:string)
                    (clojure.core/let [?env arg__77419] [?tag ?env])
                    (state__78659)))
                  (state__78659)))))
              (state__78659
               []
               (clojure.core/let
                [x__77422 :logical-plus]
                (clojure.core/let
                 [?tag x__77422]
                 (clojure.core/let [?env arg__77419] [?tag ?env]))))]
             (state__78658)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 3)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)
              input__77076_nth_2__
              (clojure.core/nth input__77076 2)]
             (clojure.core/case
              input__77076_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__77076_nth_1__)
               (clojure.core/loop
                [search_space__78660
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__77076_nth_1__)]
                (if
                 (clojure.core/seq search_space__78660)
                 (clojure.core/let
                  [input__77076_nth_1___parts__
                   (clojure.core/first search_space__78660)
                   result__78661
                   (clojure.core/let
                    [input__77076_nth_1___l__
                     (clojure.core/nth input__77076_nth_1___parts__ 0)
                     input__77076_nth_1___r__
                     (clojure.core/nth input__77076_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8123__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__77076_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__77412]
                         (clojure.core/let
                          [input__77412_nth_0__
                           (clojure.core/nth input__77412 0)]
                          (clojure.core/letfn
                           [(save__77413
                             []
                             (meander.runtime.zeta/fail))
                            (f__78664
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__77412_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__77412_nth_0__)
                            (clojure.core/let
                             [X__77415
                              (clojure.core/namespace
                               input__77412_nth_0__)]
                             (clojure.core/case
                              X__77415
                              (nil)
                              (clojure.core/let
                               [X__77417
                                (clojure.core/name
                                 input__77412_nth_0__)]
                               (if
                                (clojure.core/string? X__77417)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__77417)
                                 (save__77413)
                                 (f__78664))
                                (f__78664)))
                              (f__78664)))
                            (f__78664)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__77076_nth_1___r___l__
                           (clojure.core/subvec
                            input__77076_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__77076_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__77076_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__77076_nth_1___r___r__
                             (clojure.core/subvec
                              input__77076_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__77076_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__77076_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__77076_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__77406
                                (clojure.core/namespace
                                 input__77076_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__77406
                                (nil)
                                (clojure.core/let
                                 [X__77408
                                  (clojure.core/name
                                   input__77076_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__77408)
                                  (clojure.core/let
                                   [ret__77409
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__77408)]
                                   (if
                                    (clojure.core/some? ret__77409)
                                    (if
                                     (clojure.core/vector? ret__77409)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__77409)
                                       2)
                                      (clojure.core/let
                                       [ret__77409_nth_1__
                                        (clojure.core/nth
                                         ret__77409
                                         1)]
                                       (clojure.core/let
                                        [?n ret__77409_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__77076_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7959__auto__
                                           (def__77395
                                            input__77076_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__7959__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__7959__auto__]
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
                                                 [CATA_RESULT__9262__auto__
                                                  (CATA__FN__77153
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9262__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9262__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__9262__auto__
                                                  (CATA__FN__77153
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9262__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9262__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__10202__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10202__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10202__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8123__auto__)
                       (meander.runtime.zeta/fail)
                       ret__8123__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__78661)
                   (recur (clojure.core/next search_space__78660))
                   result__78661))
                 (state__78579)))
               (state__78579))
              (state__78579)))
            (state__78579))
           (state__78579))))
        (state__78579
         []
         (if
          (clojure.core/vector? input__77076)
          (if
           (clojure.core/= (clojure.core/count input__77076) 3)
           (clojure.core/let
            [input__77076_nth_0__
             (clojure.core/nth input__77076 0)
             input__77076_nth_1__
             (clojure.core/nth input__77076 1)
             input__77076_nth_2__
             (clojure.core/nth input__77076 2)]
            (clojure.core/case
             input__77076_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__77076_nth_1__)
              (clojure.core/let
               [input__77076_nth_1___l__
                (clojure.core/subvec
                 input__77076_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__77076_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__77076_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_1___r__
                  (clojure.core/subvec input__77076_nth_1__ 1)]
                 (clojure.core/let
                  [input__77076_nth_1___l___nth_0__
                   (clojure.core/nth input__77076_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__77440
                     (clojure.core/namespace
                      input__77076_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__77440
                     (nil)
                     (clojure.core/let
                      [X__77442
                       (clojure.core/name
                        input__77076_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__77442)
                       (clojure.core/let
                        [ret__77443
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__77442)]
                        (if
                         (clojure.core/some? ret__77443)
                         (if
                          (clojure.core/vector? ret__77443)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__77443)
                            2)
                           (clojure.core/let
                            [ret__77443_nth_1__
                             (clojure.core/nth ret__77443 1)]
                            (clojure.core/let
                             [?n ret__77443_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__77076_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__77076_nth_1___r__]
                               (clojure.core/let
                                [?env input__77076_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The operator ..!n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__10202__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10202__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10202__auto__)))))))))
                           (state__78580))
                          (state__78580))
                         (state__78580)))
                       (state__78580)))
                     (state__78580)))
                   (state__78580))))
                (state__78580)))
              (state__78580))
             (state__78580)))
           (state__78580))
          (state__78580)))
        (state__78580
         []
         (clojure.core/letfn
          [(def__77446
            [arg__77470]
            (clojure.core/letfn
             [(state__78665
               []
               (clojure.core/let
                [x__77471 :string-memory-plus]
                (clojure.core/let
                 [?tag x__77471]
                 (if
                  (clojure.core/map? arg__77470)
                  (clojure.core/let
                   [VAL__77472 (.valAt arg__77470 :context)]
                   (clojure.core/case
                    VAL__77472
                    (:string)
                    (clojure.core/let [?env arg__77470] [?tag ?env])
                    (state__78666)))
                  (state__78666)))))
              (state__78666
               []
               (clojure.core/let
                [x__77473 :memory-plus]
                (clojure.core/let
                 [?tag x__77473]
                 (clojure.core/let [?env arg__77470] [?tag ?env]))))]
             (state__78665)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 3)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)
              input__77076_nth_2__
              (clojure.core/nth input__77076 2)]
             (clojure.core/case
              input__77076_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__77076_nth_1__)
               (clojure.core/loop
                [search_space__78667
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__77076_nth_1__)]
                (if
                 (clojure.core/seq search_space__78667)
                 (clojure.core/let
                  [input__77076_nth_1___parts__
                   (clojure.core/first search_space__78667)
                   result__78668
                   (clojure.core/let
                    [input__77076_nth_1___l__
                     (clojure.core/nth input__77076_nth_1___parts__ 0)
                     input__77076_nth_1___r__
                     (clojure.core/nth input__77076_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8123__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__77076_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__77463]
                         (clojure.core/let
                          [input__77463_nth_0__
                           (clojure.core/nth input__77463 0)]
                          (clojure.core/letfn
                           [(save__77464
                             []
                             (meander.runtime.zeta/fail))
                            (f__78671
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__77463_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__77463_nth_0__)
                            (clojure.core/let
                             [X__77466
                              (clojure.core/namespace
                               input__77463_nth_0__)]
                             (clojure.core/case
                              X__77466
                              (nil)
                              (clojure.core/let
                               [X__77468
                                (clojure.core/name
                                 input__77463_nth_0__)]
                               (if
                                (clojure.core/string? X__77468)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__77468)
                                 (save__77464)
                                 (f__78671))
                                (f__78671)))
                              (f__78671)))
                            (f__78671)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__77076_nth_1___r___l__
                           (clojure.core/subvec
                            input__77076_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__77076_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__77076_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__77076_nth_1___r___r__
                             (clojure.core/subvec
                              input__77076_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__77076_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__77076_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__77076_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__77457
                                (clojure.core/namespace
                                 input__77076_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__77457
                                (nil)
                                (clojure.core/let
                                 [X__77459
                                  (clojure.core/name
                                   input__77076_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__77459)
                                  (clojure.core/let
                                   [ret__77460
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__77459)]
                                   (if
                                    (clojure.core/some? ret__77460)
                                    (if
                                     (clojure.core/vector? ret__77460)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__77460)
                                       2)
                                      (clojure.core/let
                                       [ret__77460_nth_1__
                                        (clojure.core/nth
                                         ret__77460
                                         1)]
                                       (clojure.core/let
                                        [?n ret__77460_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__77076_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7959__auto__
                                           (def__77446
                                            input__77076_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__7959__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__7959__auto__]
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
                                                 [CATA_RESULT__9262__auto__
                                                  (CATA__FN__77153
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9262__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9262__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__9262__auto__
                                                  (CATA__FN__77153
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9262__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9262__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__10202__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10202__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10202__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8123__auto__)
                       (meander.runtime.zeta/fail)
                       ret__8123__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__78668)
                   (recur (clojure.core/next search_space__78667))
                   result__78668))
                 (state__78581)))
               (state__78581))
              (state__78581)))
            (state__78581))
           (state__78581))))
        (state__78581
         []
         (if
          (clojure.core/vector? input__77076)
          (clojure.core/letfn
           [(state__78672
             []
             (if
              (clojure.core/= (clojure.core/count input__77076) 3)
              (clojure.core/let
               [input__77076_nth_0__
                (clojure.core/nth input__77076 0)
                input__77076_nth_1__
                (clojure.core/nth input__77076 1)
                input__77076_nth_2__
                (clojure.core/nth input__77076 2)]
               (clojure.core/case
                input__77076_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__77076_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__77076_nth_1__)]
                  (clojure.core/let
                   [?env input__77076_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__9262__auto__
                        (CATA__FN__77153
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__77154 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__77154
                               (clojure.core/let
                                [CATA_RESULT__9262__auto__
                                 (CATA__FN__77153
                                  [(if
                                    (.hasNext !xs__counter)
                                    (.next !xs__counter))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__9262__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__9262__auto__
                                  0)))))
                             (clojure.core/persistent!
                              return__77154))))
                          {:tag :empty}
                          ?env])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__9262__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__9262__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__10202__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10202__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10202__auto__))))))
                 (state__78673))
                (state__78673)))
              (state__78673)))
            (state__78673
             []
             (if
              (clojure.core/= (clojure.core/count input__77076) 4)
              (clojure.core/let
               [input__77076_nth_0__
                (clojure.core/nth input__77076 0)
                input__77076_nth_1__
                (clojure.core/nth input__77076 1)
                input__77076_nth_2__
                (clojure.core/nth input__77076 2)]
               (clojure.core/letfn
                [(state__78675
                  []
                  (clojure.core/let
                   [input__77076_nth_3__
                    (clojure.core/nth input__77076 3)]
                   (clojure.core/case
                    input__77076_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__78680
                       []
                       (if
                        (clojure.core/vector? input__77076_nth_1__)
                        (clojure.core/letfn
                         [(state__78682
                           []
                           (clojure.core/case
                            input__77076_nth_1__
                            ([])
                            (clojure.core/let
                             [?next input__77076_nth_2__]
                             (clojure.core/let
                              [?env input__77076_nth_3__]
                              (try
                               [?next]
                               (catch
                                java.lang.Exception
                                e__10202__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__10202__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__10202__auto__))))))
                            (state__78683)))
                          (state__78683
                           []
                           (clojure.core/loop
                            [search_space__78685
                             (meander.runtime.zeta/epsilon-partitions
                              2
                              input__77076_nth_1__)]
                            (if
                             (clojure.core/seq search_space__78685)
                             (clojure.core/let
                              [input__77076_nth_1___parts__
                               (clojure.core/first search_space__78685)
                               result__78686
                               (clojure.core/let
                                [input__77076_nth_1___l__
                                 (clojure.core/nth
                                  input__77076_nth_1___parts__
                                  0)
                                 input__77076_nth_1___r__
                                 (clojure.core/nth
                                  input__77076_nth_1___parts__
                                  1)]
                                (clojure.core/letfn
                                 [(state__78688
                                   []
                                   (clojure.core/let
                                    [!xs []]
                                    (clojure.core/let
                                     [ret__8123__auto__
                                      (meander.runtime.zeta/epsilon-run-star-1
                                       input__77076_nth_1___l__
                                       [!xs]
                                       (clojure.core/fn
                                        [[!xs] input__77499]
                                        (clojure.core/let
                                         [input__77499_nth_0__
                                          (clojure.core/nth
                                           input__77499
                                           0)]
                                         (clojure.core/letfn
                                          [(save__77500
                                            []
                                            (meander.runtime.zeta/fail))
                                           (f__78692
                                            []
                                            (clojure.core/let
                                             [!xs
                                              (clojure.core/conj
                                               !xs
                                               input__77499_nth_0__)]
                                             [!xs]))]
                                          (if
                                           (clojure.core/map?
                                            input__77499_nth_0__)
                                           (clojure.core/let
                                            [VAL__77501
                                             (.valAt
                                              input__77499_nth_0__
                                              :tag)]
                                            (clojure.core/case
                                             VAL__77501
                                             (:group)
                                             (save__77500)
                                             (f__78692)))
                                           (f__78692)))))
                                       (clojure.core/fn
                                        [[!xs]]
                                        (clojure.core/let
                                         [input__77076_nth_1___r___l__
                                          (clojure.core/subvec
                                           input__77076_nth_1___r__
                                           0
                                           (clojure.core/min
                                            (clojure.core/count
                                             input__77076_nth_1___r__)
                                            1))]
                                         (if
                                          (clojure.core/=
                                           (clojure.core/count
                                            input__77076_nth_1___r___l__)
                                           1)
                                          (clojure.core/let
                                           [input__77076_nth_1___r___r__
                                            (clojure.core/subvec
                                             input__77076_nth_1___r__
                                             1)]
                                           (clojure.core/let
                                            [input__77076_nth_1___r___l___nth_0__
                                             (clojure.core/nth
                                              input__77076_nth_1___r___l__
                                              0)]
                                            (if
                                             (clojure.core/map?
                                              input__77076_nth_1___r___l___nth_0__)
                                             (clojure.core/let
                                              [VAL__77498
                                               (.valAt
                                                input__77076_nth_1___r___l___nth_0__
                                                :tag)]
                                              (clojure.core/case
                                               VAL__77498
                                               (:group)
                                               (clojure.core/let
                                                [?group
                                                 input__77076_nth_1___r___l___nth_0__]
                                                (clojure.core/let
                                                 [?rest
                                                  input__77076_nth_1___r___r__]
                                                 (clojure.core/let
                                                  [?next
                                                   input__77076_nth_2__]
                                                  (clojure.core/let
                                                   [?env
                                                    input__77076_nth_3__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__9262__auto__
                                                        (CATA__FN__77153
                                                         ['meander.dev.parse.zeta/make-join
                                                          (clojure.core/let
                                                           [CATA_RESULT__9262__auto__
                                                            (CATA__FN__77153
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
                                                             CATA_RESULT__9262__auto__)
                                                            (throw
                                                             (meander.runtime.zeta/fail))
                                                            (clojure.core/nth
                                                             CATA_RESULT__9262__auto__
                                                             0)))
                                                          (clojure.core/let
                                                           [CATA_RESULT__9262__auto__
                                                            (CATA__FN__77153
                                                             ['meander.dev.parse.zeta/make-join
                                                              ?group
                                                              (clojure.core/let
                                                               [CATA_RESULT__9262__auto__
                                                                (CATA__FN__77153
                                                                 ['meander.dev.parse.zeta/make-cat
                                                                  ?rest
                                                                  ?next
                                                                  ?env])]
                                                               (if
                                                                (meander.runtime.zeta/fail?
                                                                 CATA_RESULT__9262__auto__)
                                                                (throw
                                                                 (meander.runtime.zeta/fail))
                                                                (clojure.core/nth
                                                                 CATA_RESULT__9262__auto__
                                                                 0)))
                                                              ?env])]
                                                           (if
                                                            (meander.runtime.zeta/fail?
                                                             CATA_RESULT__9262__auto__)
                                                            (throw
                                                             (meander.runtime.zeta/fail))
                                                            (clojure.core/nth
                                                             CATA_RESULT__9262__auto__
                                                             0)))
                                                          ?env])]
                                                       (if
                                                        (meander.runtime.zeta/fail?
                                                         CATA_RESULT__9262__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__9262__auto__
                                                         0))))]
                                                    (catch
                                                     java.lang.Exception
                                                     e__10202__auto__
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       e__10202__auto__)
                                                      (meander.runtime.zeta/fail)
                                                      (throw
                                                       e__10202__auto__))))))))
                                               (state__78689)))
                                             (state__78689))))
                                          (state__78689)))))]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       ret__8123__auto__)
                                      (state__78689)
                                      ret__8123__auto__))))
                                  (state__78689
                                   []
                                   (clojure.core/let
                                    [!xs []]
                                    (clojure.core/let
                                     [ret__8123__auto__
                                      (meander.runtime.zeta/epsilon-run-star-1
                                       input__77076_nth_1___l__
                                       [!xs]
                                       (clojure.core/fn
                                        [[!xs] input__77510]
                                        (clojure.core/let
                                         [input__77510_nth_0__
                                          (clojure.core/nth
                                           input__77510
                                           0)]
                                         (clojure.core/letfn
                                          [(save__77511
                                            []
                                            (meander.runtime.zeta/fail))
                                           (f__78694
                                            []
                                            (clojure.core/let
                                             [!xs
                                              (clojure.core/conj
                                               !xs
                                               input__77510_nth_0__)]
                                             [!xs]))]
                                          (if
                                           (clojure.core/map?
                                            input__77510_nth_0__)
                                           (clojure.core/let
                                            [VAL__77512
                                             (.valAt
                                              input__77510_nth_0__
                                              :tag)]
                                            (clojure.core/case
                                             VAL__77512
                                             (:star)
                                             (save__77511)
                                             (f__78694)))
                                           (f__78694)))))
                                       (clojure.core/fn
                                        [[!xs]]
                                        (clojure.core/let
                                         [input__77076_nth_1___r___l__
                                          (clojure.core/subvec
                                           input__77076_nth_1___r__
                                           0
                                           (clojure.core/min
                                            (clojure.core/count
                                             input__77076_nth_1___r__)
                                            1))]
                                         (if
                                          (clojure.core/=
                                           (clojure.core/count
                                            input__77076_nth_1___r___l__)
                                           1)
                                          (clojure.core/let
                                           [input__77076_nth_1___r___r__
                                            (clojure.core/subvec
                                             input__77076_nth_1___r__
                                             1)]
                                           (clojure.core/let
                                            [input__77076_nth_1___r___l___nth_0__
                                             (clojure.core/nth
                                              input__77076_nth_1___r___l__
                                              0)]
                                            (if
                                             (clojure.core/map?
                                              input__77076_nth_1___r___l___nth_0__)
                                             (clojure.core/let
                                              [VAL__77509
                                               (.valAt
                                                input__77076_nth_1___r___l___nth_0__
                                                :tag)]
                                              (clojure.core/case
                                               VAL__77509
                                               (:star)
                                               (clojure.core/let
                                                [?star
                                                 input__77076_nth_1___r___l___nth_0__]
                                                (clojure.core/let
                                                 [?rest
                                                  input__77076_nth_1___r___r__]
                                                 (clojure.core/let
                                                  [?next
                                                   input__77076_nth_2__]
                                                  (clojure.core/let
                                                   [?env
                                                    input__77076_nth_3__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__9262__auto__
                                                        (CATA__FN__77153
                                                         ['meander.dev.parse.zeta/make-join
                                                          (clojure.core/let
                                                           [CATA_RESULT__9262__auto__
                                                            (CATA__FN__77153
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
                                                             CATA_RESULT__9262__auto__)
                                                            (throw
                                                             (meander.runtime.zeta/fail))
                                                            (clojure.core/nth
                                                             CATA_RESULT__9262__auto__
                                                             0)))
                                                          (clojure.core/let
                                                           [CATA_RESULT__9262__auto__
                                                            (CATA__FN__77153
                                                             ['meander.dev.parse.zeta/make-join
                                                              ?star
                                                              (clojure.core/let
                                                               [CATA_RESULT__9262__auto__
                                                                (CATA__FN__77153
                                                                 ['meander.dev.parse.zeta/make-cat
                                                                  ?rest
                                                                  ?next
                                                                  ?env])]
                                                               (if
                                                                (meander.runtime.zeta/fail?
                                                                 CATA_RESULT__9262__auto__)
                                                                (throw
                                                                 (meander.runtime.zeta/fail))
                                                                (clojure.core/nth
                                                                 CATA_RESULT__9262__auto__
                                                                 0)))
                                                              ?env])]
                                                           (if
                                                            (meander.runtime.zeta/fail?
                                                             CATA_RESULT__9262__auto__)
                                                            (throw
                                                             (meander.runtime.zeta/fail))
                                                            (clojure.core/nth
                                                             CATA_RESULT__9262__auto__
                                                             0)))
                                                          ?env])]
                                                       (if
                                                        (meander.runtime.zeta/fail?
                                                         CATA_RESULT__9262__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__9262__auto__
                                                         0))))]
                                                    (catch
                                                     java.lang.Exception
                                                     e__10202__auto__
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       e__10202__auto__)
                                                      (meander.runtime.zeta/fail)
                                                      (throw
                                                       e__10202__auto__))))))))
                                               (state__78690)))
                                             (state__78690))))
                                          (state__78690)))))]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       ret__8123__auto__)
                                      (state__78690)
                                      ret__8123__auto__))))
                                  (state__78690
                                   []
                                   (clojure.core/let
                                    [input__77076_nth_1___l___l__
                                     (clojure.core/subvec
                                      input__77076_nth_1___l__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__77076_nth_1___l__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__77076_nth_1___l___l__)
                                      1)
                                     (clojure.core/let
                                      [input__77076_nth_1___l___r__
                                       (clojure.core/subvec
                                        input__77076_nth_1___l__
                                        1)]
                                      (clojure.core/let
                                       [input__77076_nth_1___l___l___nth_0__
                                        (clojure.core/nth
                                         input__77076_nth_1___l___l__
                                         0)]
                                       (clojure.core/letfn
                                        [(save__77520
                                          []
                                          (meander.runtime.zeta/fail))
                                         (f__78695
                                          []
                                          (clojure.core/let
                                           [!xs []]
                                           (clojure.core/let
                                            [!xs
                                             (clojure.core/conj
                                              !xs
                                              input__77076_nth_1___l___l___nth_0__)]
                                            (clojure.core/loop
                                             [i__8096__auto__
                                              0
                                              coll__78696
                                              input__77076_nth_1___l___r__
                                              [!xs]
                                              [!xs]]
                                             (clojure.core/let
                                              [input__77525
                                               (clojure.core/subvec
                                                coll__78696
                                                0
                                                (clojure.core/min
                                                 (clojure.core/count
                                                  coll__78696)
                                                 1))]
                                              (if
                                               (clojure.core/=
                                                (clojure.core/count
                                                 input__77525)
                                                1)
                                               (clojure.core/let
                                                [result__8097__auto__
                                                 (clojure.core/let
                                                  [input__77525_nth_0__
                                                   (clojure.core/nth
                                                    input__77525
                                                    0)]
                                                  (clojure.core/letfn
                                                   [(save__77526
                                                     []
                                                     (meander.runtime.zeta/fail))
                                                    (f__78697
                                                     []
                                                     (clojure.core/let
                                                      [!xs
                                                       (clojure.core/conj
                                                        !xs
                                                        input__77525_nth_0__)]
                                                      [!xs]))]
                                                   (if
                                                    (clojure.core/map?
                                                     input__77525_nth_0__)
                                                    (clojure.core/let
                                                     [VAL__77527
                                                      (.valAt
                                                       input__77525_nth_0__
                                                       :tag)]
                                                     (clojure.core/case
                                                      VAL__77527
                                                      (:reference)
                                                      (save__77526)
                                                      (f__78697)))
                                                    (f__78697))))]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  result__8097__auto__)
                                                 (meander.runtime.zeta/fail)
                                                 (recur
                                                  (clojure.core/inc
                                                   i__8096__auto__)
                                                  (clojure.core/subvec
                                                   coll__78696
                                                   1)
                                                  result__8097__auto__)))
                                               (if
                                                (clojure.core/or
                                                 (clojure.core/seq
                                                  coll__78696)
                                                 (clojure.core/<
                                                  i__8096__auto__
                                                  0))
                                                (meander.runtime.zeta/fail)
                                                (clojure.core/let
                                                 [input__77076_nth_1___r___l__
                                                  (clojure.core/subvec
                                                   input__77076_nth_1___r__
                                                   0
                                                   (clojure.core/min
                                                    (clojure.core/count
                                                     input__77076_nth_1___r__)
                                                    1))]
                                                 (if
                                                  (clojure.core/=
                                                   (clojure.core/count
                                                    input__77076_nth_1___r___l__)
                                                   1)
                                                  (clojure.core/let
                                                   [input__77076_nth_1___r___r__
                                                    (clojure.core/subvec
                                                     input__77076_nth_1___r__
                                                     1)]
                                                   (clojure.core/let
                                                    [input__77076_nth_1___r___l___nth_0__
                                                     (clojure.core/nth
                                                      input__77076_nth_1___r___l__
                                                      0)]
                                                    (if
                                                     (clojure.core/map?
                                                      input__77076_nth_1___r___l___nth_0__)
                                                     (clojure.core/let
                                                      [VAL__77524
                                                       (.valAt
                                                        input__77076_nth_1___r___l___nth_0__
                                                        :tag)]
                                                      (clojure.core/case
                                                       VAL__77524
                                                       (:reference)
                                                       (clojure.core/let
                                                        [?reference
                                                         input__77076_nth_1___r___l___nth_0__]
                                                        (clojure.core/let
                                                         [?rest
                                                          input__77076_nth_1___r___r__]
                                                         (clojure.core/let
                                                          [?next
                                                           input__77076_nth_2__]
                                                          (clojure.core/let
                                                           [?env
                                                            input__77076_nth_3__]
                                                           (try
                                                            [(clojure.core/let
                                                              [!xs__counter
                                                               (meander.runtime.zeta/iterator
                                                                !xs)]
                                                              (clojure.core/let
                                                               [CATA_RESULT__9262__auto__
                                                                (CATA__FN__77153
                                                                 ['meander.dev.parse.zeta/make-join
                                                                  (clojure.core/let
                                                                   [CATA_RESULT__9262__auto__
                                                                    (CATA__FN__77153
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
                                                                     CATA_RESULT__9262__auto__)
                                                                    (throw
                                                                     (meander.runtime.zeta/fail))
                                                                    (clojure.core/nth
                                                                     CATA_RESULT__9262__auto__
                                                                     0)))
                                                                  (clojure.core/let
                                                                   [CATA_RESULT__9262__auto__
                                                                    (CATA__FN__77153
                                                                     ['meander.dev.parse.zeta/make-join
                                                                      (clojure.core/let
                                                                       [CATA_RESULT__9262__auto__
                                                                        (CATA__FN__77153
                                                                         ['meander.dev.parse.zeta/make-cat
                                                                          [?reference]
                                                                          {:tag
                                                                           :empty}
                                                                          ?env])]
                                                                       (if
                                                                        (meander.runtime.zeta/fail?
                                                                         CATA_RESULT__9262__auto__)
                                                                        (throw
                                                                         (meander.runtime.zeta/fail))
                                                                        (clojure.core/nth
                                                                         CATA_RESULT__9262__auto__
                                                                         0)))
                                                                      (clojure.core/let
                                                                       [CATA_RESULT__9262__auto__
                                                                        (CATA__FN__77153
                                                                         ['meander.dev.parse.zeta/make-cat
                                                                          ?rest
                                                                          ?next
                                                                          ?env])]
                                                                       (if
                                                                        (meander.runtime.zeta/fail?
                                                                         CATA_RESULT__9262__auto__)
                                                                        (throw
                                                                         (meander.runtime.zeta/fail))
                                                                        (clojure.core/nth
                                                                         CATA_RESULT__9262__auto__
                                                                         0)))
                                                                      ?env])]
                                                                   (if
                                                                    (meander.runtime.zeta/fail?
                                                                     CATA_RESULT__9262__auto__)
                                                                    (throw
                                                                     (meander.runtime.zeta/fail))
                                                                    (clojure.core/nth
                                                                     CATA_RESULT__9262__auto__
                                                                     0)))
                                                                  ?env])]
                                                               (if
                                                                (meander.runtime.zeta/fail?
                                                                 CATA_RESULT__9262__auto__)
                                                                (throw
                                                                 (meander.runtime.zeta/fail))
                                                                (clojure.core/nth
                                                                 CATA_RESULT__9262__auto__
                                                                 0))))]
                                                            (catch
                                                             java.lang.Exception
                                                             e__10202__auto__
                                                             (if
                                                              (meander.runtime.zeta/fail?
                                                               e__10202__auto__)
                                                              (meander.runtime.zeta/fail)
                                                              (throw
                                                               e__10202__auto__))))))))
                                                       (meander.runtime.zeta/fail)))
                                                     (meander.runtime.zeta/fail))))
                                                  (meander.runtime.zeta/fail))))))))))]
                                        (if
                                         (clojure.core/map?
                                          input__77076_nth_1___l___l___nth_0__)
                                         (clojure.core/let
                                          [VAL__77521
                                           (.valAt
                                            input__77076_nth_1___l___l___nth_0__
                                            :tag)]
                                          (clojure.core/case
                                           VAL__77521
                                           (:reference)
                                           (save__77520)
                                           (f__78695)))
                                         (f__78695)))))
                                     (meander.runtime.zeta/fail))))]
                                 (state__78688)))]
                              (if
                               (meander.runtime.zeta/fail?
                                result__78686)
                               (recur
                                (clojure.core/next
                                 search_space__78685))
                               result__78686))
                             (state__78684))))
                          (state__78684
                           []
                           (clojure.core/let
                            [input__77076_nth_1___l__
                             (clojure.core/subvec
                              input__77076_nth_1__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__77076_nth_1__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__77076_nth_1___l__)
                              1)
                             (clojure.core/let
                              [input__77076_nth_1___r__
                               (clojure.core/subvec
                                input__77076_nth_1__
                                1)]
                              (clojure.core/let
                               [input__77076_nth_1___l___nth_0__
                                (clojure.core/nth
                                 input__77076_nth_1___l__
                                 0)]
                               (if
                                (clojure.core/map?
                                 input__77076_nth_1___l___nth_0__)
                                (clojure.core/let
                                 [VAL__77533
                                  (.valAt
                                   input__77076_nth_1___l___nth_0__
                                   :tag)]
                                 (clojure.core/case
                                  VAL__77533
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__77534
                                    (.valAt
                                     input__77076_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__77534
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__77076_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__77076_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__77076_nth_2__]
                                       (clojure.core/let
                                        [?env input__77076_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9262__auto__
                                            (CATA__FN__77153
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__9262__auto__
                                                (CATA__FN__77153
                                                 ['meander.dev.parse.zeta/make-cat
                                                  ?rest
                                                  ?next
                                                  ?env])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__9262__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__9262__auto__
                                                 0)))
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__9262__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__9262__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__10202__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__10202__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__10202__auto__))))))))
                                    (state__78681)))
                                  (state__78681)))
                                (state__78681))))
                             (state__78681))))]
                         (state__78682))
                        (state__78681)))
                      (state__78681
                       []
                       (clojure.core/let
                        [?sequence input__77076_nth_1__]
                        (clojure.core/let
                         [?next input__77076_nth_2__]
                         (if
                          (clojure.core/map? input__77076_nth_3__)
                          (clojure.core/let
                           [VAL__77537
                            (.valAt input__77076_nth_3__ :context)]
                           (clojure.core/case
                            VAL__77537
                            (:string)
                            (try
                             [{:tag :string-cat,
                               :sequence ?sequence,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__10202__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10202__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10202__auto__))))
                            (state__78676)))
                          (state__78676)))))]
                     (state__78680))
                    (state__78676))))
                 (state__78676
                  []
                  (clojure.core/case
                   input__77076_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__77076_nth_1__]
                    (clojure.core/let
                     [?next input__77076_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10202__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10202__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10202__auto__))))))
                   (state__78677)))
                 (state__78677
                  []
                  (clojure.core/let
                   [input__77076_nth_3__
                    (clojure.core/nth input__77076 3)]
                   (clojure.core/case
                    input__77076_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__77076_nth_1__)
                     (clojure.core/let
                      [VAL__77542 (.valAt input__77076_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__77542
                       (:cat)
                       (clojure.core/let
                        [VAL__77543
                         (.valAt input__77076_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__77543]
                         (clojure.core/let
                          [VAL__77544
                           (.valAt input__77076_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__77544)
                           (clojure.core/let
                            [VAL__77545 (.valAt VAL__77544 :tag)]
                            (clojure.core/case
                             VAL__77545
                             (:empty)
                             (clojure.core/let
                              [?right input__77076_nth_2__]
                              (clojure.core/let
                               [?env input__77076_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__9262__auto__
                                   (CATA__FN__77153
                                    ['meander.dev.parse.zeta/make-cat
                                     ?sequence
                                     ?right
                                     ?env])]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    CATA_RESULT__9262__auto__)
                                   (throw (meander.runtime.zeta/fail))
                                   (clojure.core/nth
                                    CATA_RESULT__9262__auto__
                                    0)))]
                                (catch
                                 java.lang.Exception
                                 e__10202__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__10202__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__10202__auto__))))))
                             (state__78678)))
                           (state__78678)))))
                       (state__78678)))
                     (state__78678))
                    (state__78678))))
                 (state__78678
                  []
                  (clojure.core/case
                   input__77076_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__77076_nth_1__)
                    (clojure.core/let
                     [VAL__78568 (.valAt input__77076_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__78568
                      (:cat)
                      (clojure.core/let
                       [?ast input__77076_nth_1__]
                       (if
                        (clojure.core/map? input__77076_nth_2__)
                        (clojure.core/let
                         [VAL__77549
                          (.valAt input__77076_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__77549
                          (:cat)
                          (clojure.core/let
                           [VAL__77550
                            (.valAt input__77076_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__77550]
                            (clojure.core/let
                             [VAL__77551
                              (.valAt input__77076_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__77551]
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
                                e__10202__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__10202__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__10202__auto__))))))))
                          (state__78679)))
                        (state__78679)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__77076_nth_1__]
                       (if
                        (clojure.core/map? input__77076_nth_2__)
                        (clojure.core/let
                         [VAL__77555
                          (.valAt input__77076_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__77555
                          (:string-cat)
                          (clojure.core/let
                           [VAL__77556
                            (.valAt input__77076_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__77556]
                            (clojure.core/let
                             [VAL__77557
                              (.valAt input__77076_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__77557]
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
                                e__10202__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__10202__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__10202__auto__))))))))
                          (state__78679)))
                        (state__78679)))
                      (state__78679)))
                    (state__78679))
                   (state__78679)))
                 (state__78679
                  []
                  (clojure.core/let
                   [input__77076_nth_3__
                    (clojure.core/nth input__77076 3)]
                   (clojure.core/case
                    input__77076_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__78698
                       []
                       (if
                        (clojure.core/map? input__77076_nth_1__)
                        (clojure.core/let
                         [VAL__78571
                          (.valAt input__77076_nth_1__ :next)
                          VAL__78570
                          (.valAt input__77076_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__78570
                          (:string-cat)
                          (clojure.core/let
                           [VAL__77561
                            (.valAt input__77076_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__77561]
                            (if
                             (clojure.core/map? VAL__78571)
                             (clojure.core/let
                              [VAL__77563 (.valAt VAL__78571 :tag)]
                              (clojure.core/case
                               VAL__77563
                               (:empty)
                               (clojure.core/let
                                [?right input__77076_nth_2__]
                                (clojure.core/let
                                 [?env input__77076_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [CATA_RESULT__9262__auto__
                                     (CATA__FN__77153
                                      ['meander.dev.parse.zeta/make-join
                                       ?sequence
                                       ?right
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9262__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9262__auto__
                                      0)))]
                                  (catch
                                   java.lang.Exception
                                   e__10202__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__10202__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__10202__auto__))))))
                               (state__78699)))
                             (state__78699))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__77567
                            (.valAt input__77076_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__77567]
                            (if
                             (clojure.core/map? VAL__78571)
                             (clojure.core/let
                              [VAL__77569 (.valAt VAL__78571 :tag)]
                              (clojure.core/case
                               VAL__77569
                               (:empty)
                               (clojure.core/let
                                [?right input__77076_nth_2__]
                                (if
                                 (clojure.core/map?
                                  input__77076_nth_3__)
                                 (clojure.core/let
                                  [VAL__77570
                                   (.valAt
                                    input__77076_nth_3__
                                    :context)]
                                  (clojure.core/case
                                   VAL__77570
                                   (:string)
                                   (try
                                    [{:tag :string-star,
                                      :pattern ?pattern,
                                      :next ?right}]
                                    (catch
                                     java.lang.Exception
                                     e__10202__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10202__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10202__auto__))))
                                   (state__78699)))
                                 (state__78699)))
                               (state__78699)))
                             (state__78699))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__77574
                            (.valAt input__77076_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__77574]
                            (clojure.core/let
                             [VAL__77575
                              (.valAt input__77076_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__77575]
                              (clojure.core/let
                               [?right-2 input__77076_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__77076_nth_3__)
                                (clojure.core/let
                                 [VAL__77576
                                  (.valAt
                                   input__77076_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__77576
                                  (:string)
                                  (clojure.core/let
                                   [?env input__77076_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__9262__auto__
                                        (CATA__FN__77153
                                         ['meander.dev.parse.zeta/make-join
                                          ?right-1
                                          ?right-2
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9262__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9262__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__10202__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10202__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10202__auto__)))))
                                  (state__78699)))
                                (state__78699)))))))
                          (state__78699)))
                        (state__78699)))
                      (state__78699
                       []
                       (clojure.core/let
                        [?left input__77076_nth_1__]
                        (if
                         (clojure.core/map? input__77076_nth_2__)
                         (clojure.core/let
                          [VAL__77579
                           (.valAt input__77076_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__77579
                           (:empty)
                           (clojure.core/let
                            [?env input__77076_nth_3__]
                            (try
                             [?left]
                             (catch
                              java.lang.Exception
                              e__10202__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10202__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10202__auto__)))))
                           (state__78700)))
                         (state__78700))))
                      (state__78700
                       []
                       (if
                        (clojure.core/map? input__77076_nth_1__)
                        (clojure.core/let
                         [VAL__78569
                          (.valAt input__77076_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__78569
                          (:empty)
                          (clojure.core/let
                           [?right input__77076_nth_2__]
                           (clojure.core/let
                            [?env input__77076_nth_3__]
                            (try
                             [?right]
                             (catch
                              java.lang.Exception
                              e__10202__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10202__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10202__auto__))))))
                          (:star)
                          (clojure.core/let
                           [VAL__77586
                            (.valAt input__77076_nth_1__ :next)]
                           (if
                            (clojure.core/map? VAL__77586)
                            (clojure.core/let
                             [VAL__77587 (.valAt VAL__77586 :tag)]
                             (clojure.core/case
                              VAL__77587
                              (:empty)
                              (clojure.core/let
                               [?left input__77076_nth_1__]
                               (clojure.core/let
                                [?right input__77076_nth_2__]
                                (clojure.core/let
                                 [?env input__77076_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [form__9361__auto__
                                     {:tag :star, :next ?right}]
                                    (clojure.core/merge
                                     (clojure.core/into {} ?left)
                                     form__9361__auto__))]
                                  (catch
                                   java.lang.Exception
                                   e__10202__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__10202__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__10202__auto__)))))))
                              (state__78701)))
                            (state__78701)))
                          (state__78701)))
                        (state__78701)))
                      (state__78701
                       []
                       (clojure.core/let
                        [?left input__77076_nth_1__]
                        (clojure.core/let
                         [?right input__77076_nth_2__]
                         (clojure.core/letfn
                          [(state__78702
                            []
                            (if
                             (clojure.core/map? input__77076_nth_3__)
                             (clojure.core/let
                              [VAL__77590
                               (.valAt input__77076_nth_3__ :context)]
                              (clojure.core/case
                               VAL__77590
                               (:string)
                               (try
                                [{:tag :string-join,
                                  :left ?left,
                                  :right ?right}]
                                (catch
                                 java.lang.Exception
                                 e__10202__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__10202__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__10202__auto__))))
                               (state__78703)))
                             (state__78703)))
                           (state__78703
                            []
                            (clojure.core/let
                             [?env input__77076_nth_3__]
                             (try
                              [{:tag :join,
                                :left ?left,
                                :right ?right}]
                              (catch
                               java.lang.Exception
                               e__10202__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10202__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10202__auto__))))))]
                          (state__78702)))))]
                     (state__78698))
                    (state__78674))))]
                (state__78675)))
              (state__78674)))
            (state__78674
             []
             (if
              (clojure.core/= (clojure.core/count input__77076) 3)
              (clojure.core/let
               [input__77076_nth_0__
                (clojure.core/nth input__77076 0)
                input__77076_nth_1__
                (clojure.core/nth input__77076 1)
                input__77076_nth_2__
                (clojure.core/nth input__77076 2)]
               (clojure.core/case
                input__77076_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__77076_nth_1__)
                 (clojure.core/let
                  [VAL__77595
                   (.valAt input__77076_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__77595]
                   (clojure.core/let
                    [X__77597
                     ((clojure.core/fn
                       [m__7026__auto__]
                       (clojure.core/dissoc
                        m__7026__auto__
                        :meander.zeta/as))
                      input__77076_nth_1__)]
                    (clojure.core/let
                     [?rest X__77597]
                     (clojure.core/letfn
                      [(save__77598 [] (state__78582))
                       (f__78704
                        []
                        (clojure.core/let
                         [?env input__77076_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__9262__auto__
                              (CATA__FN__77153 [?pattern ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9262__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9262__auto__
                               0))),
                            :next
                            (clojure.core/let
                             [CATA_RESULT__9262__auto__
                              (CATA__FN__77153 [?rest ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9262__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9262__auto__
                               0)))}]
                          (catch
                           java.lang.Exception
                           e__10202__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10202__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10202__auto__))))))]
                      (if
                       (clojure.core/= ?rest input__77076_nth_1__)
                       (save__77598)
                       (f__78704)))))))
                 (state__78582))
                (state__78582)))
              (state__78582)))]
           (state__78672))
          (state__78582)))
        (state__78582
         []
         (clojure.core/letfn
          [(def__77601
            [arg__77634 ?ns]
            (clojure.core/letfn
             [(state__78705
               []
               (clojure.core/let
                [x__77635 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__77635)
                 (clojure.core/let [?env arg__77634] [?env ?ns])
                 (state__78706))))
              (state__78706
               []
               (if
                (clojure.core/map? arg__77634)
                (clojure.core/let
                 [VAL__77636 (.valAt arg__77634 :aliases)]
                 (if
                  (clojure.core/map? VAL__77636)
                  (clojure.core/let
                   [X__77638 (clojure.core/set VAL__77636)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__77638))
                    (clojure.core/loop
                     [search_space__78707 (clojure.core/seq X__77638)]
                     (if
                      (clojure.core/seq search_space__78707)
                      (clojure.core/let
                       [elem__77639
                        (clojure.core/first search_space__78707)
                        result__78708
                        (clojure.core/let
                         [elem__77639_nth_0__
                          (clojure.core/nth elem__77639 0)
                          elem__77639_nth_1__
                          (clojure.core/nth elem__77639 1)]
                         (if
                          (clojure.core/symbol? elem__77639_nth_0__)
                          (clojure.core/let
                           [X__77641
                            (clojure.core/name elem__77639_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__77641)
                            (if
                             (clojure.core/symbol? elem__77639_nth_1__)
                             (clojure.core/let
                              [X__77643
                               (clojure.core/name elem__77639_nth_1__)]
                              (clojure.core/case
                               X__77643
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__77634]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78708)
                        (recur (clojure.core/next search_space__78707))
                        result__78708))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78705)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 3)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)
              input__77076_nth_2__
              (clojure.core/nth input__77076 2)]
             (clojure.core/case
              input__77076_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__77076_nth_1__)
               (clojure.core/let
                [X__77606 (clojure.core/set input__77076_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__77606))
                 (clojure.core/loop
                  [search_space__78710 (clojure.core/seq X__77606)]
                  (if
                   (clojure.core/seq search_space__78710)
                   (clojure.core/let
                    [elem__77607
                     (clojure.core/first search_space__78710)
                     result__78711
                     (clojure.core/let
                      [elem__77607_nth_0__
                       (clojure.core/nth elem__77607 0)
                       elem__77607_nth_1__
                       (clojure.core/nth elem__77607 1)]
                      (clojure.core/let
                       [*m__77108 elem__77607_nth_0__]
                       (if
                        (clojure.core/symbol? elem__77607_nth_0__)
                        (clojure.core/let
                         [X__77609
                          (clojure.core/namespace elem__77607_nth_0__)]
                         (clojure.core/let
                          [?ns X__77609]
                          (clojure.core/let
                           [X__77611
                            (clojure.core/name elem__77607_nth_0__)]
                           (if
                            (clojure.core/string? X__77611)
                            (if
                             (clojure.core/re-matches #"&.*" X__77611)
                             (clojure.core/let
                              [?pattern elem__77607_nth_1__]
                              (clojure.core/let
                               [X__77613
                                ((clojure.core/fn
                                  [m__7026__auto__]
                                  (clojure.core/dissoc
                                   m__7026__auto__
                                   *m__77108))
                                 input__77076_nth_1__)]
                               (clojure.core/let
                                [?rest X__77613]
                                (clojure.core/let
                                 [x__7959__auto__
                                  (def__77601
                                   input__77076_nth_2__
                                   ?ns)]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   x__7959__auto__)
                                  (meander.runtime.zeta/fail)
                                  (clojure.core/let
                                   [[?env ?ns] x__7959__auto__]
                                   (try
                                    [{:tag :rest-map,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9262__auto__
                                        (CATA__FN__77153
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9262__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9262__auto__
                                         0))),
                                      :next
                                      (clojure.core/let
                                       [CATA_RESULT__9262__auto__
                                        (CATA__FN__77153
                                         ['meander.dev.parse.zeta/parse-entries
                                          ?rest
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9262__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9262__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__10202__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10202__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10202__auto__))))))))))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? result__78711)
                     (recur (clojure.core/next search_space__78710))
                     result__78711))
                   (state__78583)))
                 (state__78583)))
               (state__78583))
              (state__78583)))
            (state__78583))
           (state__78583))))
        (state__78583
         []
         (if
          (clojure.core/vector? input__77076)
          (clojure.core/letfn
           [(state__78713
             []
             (if
              (clojure.core/= (clojure.core/count input__77076) 3)
              (clojure.core/let
               [input__77076_nth_0__
                (clojure.core/nth input__77076 0)
                input__77076_nth_1__
                (clojure.core/nth input__77076 1)
                input__77076_nth_2__
                (clojure.core/nth input__77076 2)]
               (clojure.core/case
                input__77076_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__78715
                   []
                   (if
                    (clojure.core/map? input__77076_nth_1__)
                    (clojure.core/let
                     [X__77657 (clojure.core/set input__77076_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__77657))
                      (clojure.core/loop
                       [search_space__78717
                        (clojure.core/seq X__77657)]
                       (if
                        (clojure.core/seq search_space__78717)
                        (clojure.core/let
                         [elem__77658
                          (clojure.core/first search_space__78717)
                          result__78718
                          (clojure.core/let
                           [elem__77658_nth_0__
                            (clojure.core/nth elem__77658 0)
                            elem__77658_nth_1__
                            (clojure.core/nth elem__77658 1)]
                           (clojure.core/let
                            [?key-pattern elem__77658_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__77658_nth_1__]
                             (clojure.core/let
                              [X__77660
                               ((clojure.core/fn
                                 [m__7026__auto__]
                                 (clojure.core/dissoc
                                  m__7026__auto__
                                  ?key-pattern))
                                input__77076_nth_1__)]
                              (clojure.core/let
                               [?rest X__77660]
                               (clojure.core/let
                                [?env input__77076_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9262__auto__
                                     (CATA__FN__77153
                                      [?key-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9262__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9262__auto__
                                      0))),
                                   :val-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9262__auto__
                                     (CATA__FN__77153
                                      [?val-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9262__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9262__auto__
                                      0))),
                                   :next
                                   (clojure.core/let
                                    [CATA_RESULT__9262__auto__
                                     (CATA__FN__77153
                                      ['meander.dev.parse.zeta/parse-entries
                                       ?rest
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9262__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9262__auto__
                                      0)))}]
                                 (catch
                                  java.lang.Exception
                                  e__10202__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10202__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10202__auto__))))))))))]
                         (if
                          (meander.runtime.zeta/fail? result__78718)
                          (recur
                           (clojure.core/next search_space__78717))
                          result__78718))
                        (state__78716)))
                      (state__78716)))
                    (state__78716)))
                  (state__78716
                   []
                   (if
                    (clojure.core/map? input__77076_nth_1__)
                    (clojure.core/let
                     [?env input__77076_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__10202__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10202__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10202__auto__)))))
                    (state__78714)))]
                 (state__78715))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__78720
                   []
                   (if
                    (clojure.core/vector? input__77076_nth_1__)
                    (clojure.core/case
                     input__77076_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__77076_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__10202__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10202__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10202__auto__)))))
                     (state__78721))
                    (state__78721)))
                  (state__78721
                   []
                   (if
                    (clojure.core/vector? input__77076_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__77076_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__77076_nth_2__]
                      (try
                       [[{:tag :error,
                          :message
                          "meander.zeta/with expects an even number of bindings"}]]
                       (catch
                        java.lang.Exception
                        e__10202__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10202__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10202__auto__)))))
                     (state__78722))
                    (state__78722)))
                  (state__78722
                   []
                   (if
                    (clojure.core/vector? input__77076_nth_1__)
                    (clojure.core/let
                     [input__77076_nth_1___l__
                      (clojure.core/subvec
                       input__77076_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__77076_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__77076_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__77076_nth_1___r__
                        (clojure.core/subvec input__77076_nth_1__ 2)]
                       (clojure.core/let
                        [input__77076_nth_1___l___nth_0__
                         (clojure.core/nth input__77076_nth_1___l__ 0)
                         input__77076_nth_1___l___nth_1__
                         (clojure.core/nth input__77076_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__77076_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__77674
                           (clojure.core/namespace
                            input__77076_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__77676
                            (clojure.core/name
                             input__77076_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__77676)
                            (if
                             (clojure.core/re-matches #"%.+" X__77676)
                             (clojure.core/let
                              [?symbol
                               input__77076_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__77076_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__77076_nth_1___r__]
                                (clojure.core/let
                                 [?env input__77076_nth_2__]
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
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0)))})
                                     (clojure.core/let
                                      [CATA_RESULT__9262__auto__
                                       (CATA__FN__77153
                                        ['meander.dev.parse.zeta/parse-with-bindings
                                         ?rest
                                         ?env])]
                                      (if
                                       (meander.runtime.zeta/fail?
                                        CATA_RESULT__9262__auto__)
                                       (throw
                                        (meander.runtime.zeta/fail))
                                       (clojure.core/nth
                                        CATA_RESULT__9262__auto__
                                        0)))))]
                                  (catch
                                   java.lang.Exception
                                   e__10202__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__10202__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__10202__auto__))))))))
                             (state__78723))
                            (state__78723))))
                         (state__78723))))
                      (state__78723)))
                    (state__78723)))
                  (state__78723
                   []
                   (if
                    (clojure.core/vector? input__77076_nth_1__)
                    (clojure.core/let
                     [input__77076_nth_1___l__
                      (clojure.core/subvec
                       input__77076_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__77076_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__77076_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__77076_nth_1___r__
                        (clojure.core/subvec input__77076_nth_1__ 2)]
                       (clojure.core/let
                        [input__77076_nth_1___l___nth_0__
                         (clojure.core/nth input__77076_nth_1___l__ 0)
                         input__77076_nth_1___l___nth_1__
                         (clojure.core/nth input__77076_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__77076_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__77076_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__77076_nth_1___r__]
                           (clojure.core/let
                            [?env input__77076_nth_2__]
                            (try
                             [[{:tag :error,
                                :message
                                "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]
                             (catch
                              java.lang.Exception
                              e__10202__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10202__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10202__auto__))))))))))
                      (state__78714)))
                    (state__78714)))]
                 (state__78720))
                (state__78714)))
              (state__78714)))
            (state__78714
             []
             (if
              (clojure.core/= (clojure.core/count input__77076) 2)
              (clojure.core/let
               [input__77076_nth_0__
                (clojure.core/nth input__77076 0)
                input__77076_nth_1__
                (clojure.core/nth input__77076 1)]
               (if
                (clojure.core/vector? input__77076_nth_0__)
                (clojure.core/let
                 [?sequence input__77076_nth_0__]
                 (clojure.core/let
                  [?form input__77076_nth_0__]
                  (clojure.core/let
                   [?env input__77076_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__9262__auto__
                        (CATA__FN__77153
                         ['meander.dev.parse.zeta/parse-sequential
                          ?sequence
                          (clojure.core/let
                           [form__9361__auto__ {:context :vector}]
                           (clojure.core/merge
                            (clojure.core/into {} ?env)
                            form__9361__auto__))])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__9262__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__9262__auto__
                         0))),
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__10202__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10202__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10202__auto__)))))))
                (state__78584)))
              (state__78584)))]
           (state__78713))
          (state__78584)))
        (state__78584
         []
         (clojure.core/letfn
          [(def__77686
            [arg__77709 ?__77077]
            (clojure.core/letfn
             [(state__78724
               []
               (clojure.core/let
                [x__77710 "clojure.core"]
                (if
                 (clojure.core/= ?__77077 x__77710)
                 [?__77077]
                 (state__78725))))
              (state__78725
               []
               (if
                (clojure.core/map? arg__77709)
                (clojure.core/let
                 [VAL__77711 (.valAt arg__77709 :aliases)]
                 (if
                  (clojure.core/map? VAL__77711)
                  (clojure.core/let
                   [X__77713 (clojure.core/set VAL__77711)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__77713))
                    (clojure.core/loop
                     [search_space__78726 (clojure.core/seq X__77713)]
                     (if
                      (clojure.core/seq search_space__78726)
                      (clojure.core/let
                       [elem__77714
                        (clojure.core/first search_space__78726)
                        result__78727
                        (clojure.core/let
                         [elem__77714_nth_0__
                          (clojure.core/nth elem__77714 0)
                          elem__77714_nth_1__
                          (clojure.core/nth elem__77714 1)]
                         (if
                          (clojure.core/symbol? elem__77714_nth_0__)
                          (clojure.core/let
                           [X__77716
                            (clojure.core/name elem__77714_nth_0__)]
                           (if
                            (clojure.core/= ?__77077 X__77716)
                            (if
                             (clojure.core/symbol? elem__77714_nth_1__)
                             (clojure.core/let
                              [X__77718
                               (clojure.core/name elem__77714_nth_1__)]
                              (clojure.core/case
                               X__77718
                               ("clojure.core")
                               [?__77077]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78727)
                        (recur (clojure.core/next search_space__78726))
                        result__78727))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78724)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__77696
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77077 X__77696]
                     (clojure.core/let
                      [X__77698
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__77698
                       ("unquote")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__77686 input__77076_nth_1__ ?__77077)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78585)
                         (clojure.core/let
                          [[?__77077] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__77076_nth_0__)
                                2)
                               (clojure.core/let
                                [input__77076_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?x input__77076_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__77076_nth_0__]
                                  (clojure.core/let
                                   [?env input__77076_nth_1__]
                                   (try
                                    [{:tag :host-expression,
                                      :expression ?x,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10202__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10202__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10202__auto__))))))))
                               (state__78585))
                              (state__78585)))
                            (state__78585))
                           (state__78585)))))
                       (state__78585)))))
                   (state__78585))))
                (state__78585)))
              (state__78585)))
            (state__78585))
           (state__78585))))
        (state__78585
         []
         (clojure.core/letfn
          [(def__77720
            [arg__77743 ?__77078]
            (clojure.core/letfn
             [(state__78729
               []
               (clojure.core/let
                [x__77744 "meander.zeta"]
                (if
                 (clojure.core/= ?__77078 x__77744)
                 [?__77078]
                 (state__78730))))
              (state__78730
               []
               (if
                (clojure.core/map? arg__77743)
                (clojure.core/let
                 [VAL__77745 (.valAt arg__77743 :aliases)]
                 (if
                  (clojure.core/map? VAL__77745)
                  (clojure.core/let
                   [X__77747 (clojure.core/set VAL__77745)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__77747))
                    (clojure.core/loop
                     [search_space__78731 (clojure.core/seq X__77747)]
                     (if
                      (clojure.core/seq search_space__78731)
                      (clojure.core/let
                       [elem__77748
                        (clojure.core/first search_space__78731)
                        result__78732
                        (clojure.core/let
                         [elem__77748_nth_0__
                          (clojure.core/nth elem__77748 0)
                          elem__77748_nth_1__
                          (clojure.core/nth elem__77748 1)]
                         (if
                          (clojure.core/symbol? elem__77748_nth_0__)
                          (clojure.core/let
                           [X__77750
                            (clojure.core/name elem__77748_nth_0__)]
                           (if
                            (clojure.core/= ?__77078 X__77750)
                            (if
                             (clojure.core/symbol? elem__77748_nth_1__)
                             (clojure.core/let
                              [X__77752
                               (clojure.core/name elem__77748_nth_1__)]
                              (clojure.core/case
                               X__77752
                               ("meander.zeta")
                               [?__77078]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78732)
                        (recur (clojure.core/next search_space__78731))
                        result__78732))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78729)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__77730
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77078 X__77730]
                     (clojure.core/let
                      [X__77732
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__77732
                       ("*")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__77720 input__77076_nth_1__ ?__77078)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78586)
                         (clojure.core/let
                          [[?__77078] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (clojure.core/let
                               [input__77076_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__77076_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__77076_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__77076_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__77076_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__77076_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__77076_nth_0__]
                                   (clojure.core/let
                                    [?env input__77076_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0))),
                                       :next {:tag :empty}}]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__))))))))
                                (state__78586)))
                              (state__78586)))
                            (state__78586))
                           (state__78586)))))
                       (state__78586)))))
                   (state__78586))))
                (state__78586)))
              (state__78586)))
            (state__78586))
           (state__78586))))
        (state__78586
         []
         (clojure.core/letfn
          [(def__77754
            [arg__77777 ?__77079]
            (clojure.core/letfn
             [(state__78734
               []
               (clojure.core/let
                [x__77778 "meander.zeta"]
                (if
                 (clojure.core/= ?__77079 x__77778)
                 [?__77079]
                 (state__78735))))
              (state__78735
               []
               (if
                (clojure.core/map? arg__77777)
                (clojure.core/let
                 [VAL__77779 (.valAt arg__77777 :aliases)]
                 (if
                  (clojure.core/map? VAL__77779)
                  (clojure.core/let
                   [X__77781 (clojure.core/set VAL__77779)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__77781))
                    (clojure.core/loop
                     [search_space__78736 (clojure.core/seq X__77781)]
                     (if
                      (clojure.core/seq search_space__78736)
                      (clojure.core/let
                       [elem__77782
                        (clojure.core/first search_space__78736)
                        result__78737
                        (clojure.core/let
                         [elem__77782_nth_0__
                          (clojure.core/nth elem__77782 0)
                          elem__77782_nth_1__
                          (clojure.core/nth elem__77782 1)]
                         (if
                          (clojure.core/symbol? elem__77782_nth_0__)
                          (clojure.core/let
                           [X__77784
                            (clojure.core/name elem__77782_nth_0__)]
                           (if
                            (clojure.core/= ?__77079 X__77784)
                            (if
                             (clojure.core/symbol? elem__77782_nth_1__)
                             (clojure.core/let
                              [X__77786
                               (clojure.core/name elem__77782_nth_1__)]
                              (clojure.core/case
                               X__77786
                               ("meander.zeta")
                               [?__77079]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78737)
                        (recur (clojure.core/next search_space__78736))
                        result__78737))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78734)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__77764
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77079 X__77764]
                     (clojure.core/let
                      [X__77766
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__77766
                       ("<>")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__77754 input__77076_nth_1__ ?__77079)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78587)
                         (clojure.core/let
                          [[?__77079] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (clojure.core/let
                               [input__77076_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__77076_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__77076_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__77076_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__77076_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__77076_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__77076_nth_0__]
                                   (clojure.core/let
                                    [?env input__77076_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__))))))))
                                (state__78587)))
                              (state__78587)))
                            (state__78587))
                           (state__78587)))))
                       (state__78587)))))
                   (state__78587))))
                (state__78587)))
              (state__78587)))
            (state__78587))
           (state__78587))))
        (state__78587
         []
         (clojure.core/letfn
          [(def__77788
            [arg__77811 ?__77080]
            (clojure.core/letfn
             [(state__78739
               []
               (clojure.core/let
                [x__77812 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__77080 x__77812)
                 [?__77080]
                 (state__78740))))
              (state__78740
               []
               (if
                (clojure.core/map? arg__77811)
                (clojure.core/let
                 [VAL__77813 (.valAt arg__77811 :aliases)]
                 (if
                  (clojure.core/map? VAL__77813)
                  (clojure.core/let
                   [X__77815 (clojure.core/set VAL__77813)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__77815))
                    (clojure.core/loop
                     [search_space__78741 (clojure.core/seq X__77815)]
                     (if
                      (clojure.core/seq search_space__78741)
                      (clojure.core/let
                       [elem__77816
                        (clojure.core/first search_space__78741)
                        result__78742
                        (clojure.core/let
                         [elem__77816_nth_0__
                          (clojure.core/nth elem__77816 0)
                          elem__77816_nth_1__
                          (clojure.core/nth elem__77816 1)]
                         (if
                          (clojure.core/symbol? elem__77816_nth_0__)
                          (clojure.core/let
                           [X__77818
                            (clojure.core/name elem__77816_nth_0__)]
                           (if
                            (clojure.core/= ?__77080 X__77818)
                            (if
                             (clojure.core/symbol? elem__77816_nth_1__)
                             (clojure.core/let
                              [X__77820
                               (clojure.core/name elem__77816_nth_1__)]
                              (clojure.core/case
                               X__77820
                               ("meander.math.zeta")
                               [?__77080]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78742)
                        (recur (clojure.core/next search_space__78741))
                        result__78742))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78739)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__77798
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77080 X__77798]
                     (clojure.core/let
                      [X__77800
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__77800
                       ("+")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__77788 input__77076_nth_1__ ?__77080)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78588)
                         (clojure.core/let
                          [[?__77080] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__77076_nth_0__)
                                3)
                               (clojure.core/let
                                [input__77076_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  1)
                                 input__77076_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__77076_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__77076_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__77076_nth_0__]
                                   (clojure.core/let
                                    [?env input__77076_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/+,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153 [?a ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153 [?b ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__)))))))))
                               (state__78588))
                              (state__78588)))
                            (state__78588))
                           (state__78588)))))
                       (state__78588)))))
                   (state__78588))))
                (state__78588)))
              (state__78588)))
            (state__78588))
           (state__78588))))
        (state__78588
         []
         (clojure.core/letfn
          [(def__77822
            [arg__77845 ?__77081]
            (clojure.core/letfn
             [(state__78744
               []
               (clojure.core/let
                [x__77846 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__77081 x__77846)
                 [?__77081]
                 (state__78745))))
              (state__78745
               []
               (if
                (clojure.core/map? arg__77845)
                (clojure.core/let
                 [VAL__77847 (.valAt arg__77845 :aliases)]
                 (if
                  (clojure.core/map? VAL__77847)
                  (clojure.core/let
                   [X__77849 (clojure.core/set VAL__77847)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__77849))
                    (clojure.core/loop
                     [search_space__78746 (clojure.core/seq X__77849)]
                     (if
                      (clojure.core/seq search_space__78746)
                      (clojure.core/let
                       [elem__77850
                        (clojure.core/first search_space__78746)
                        result__78747
                        (clojure.core/let
                         [elem__77850_nth_0__
                          (clojure.core/nth elem__77850 0)
                          elem__77850_nth_1__
                          (clojure.core/nth elem__77850 1)]
                         (if
                          (clojure.core/symbol? elem__77850_nth_0__)
                          (clojure.core/let
                           [X__77852
                            (clojure.core/name elem__77850_nth_0__)]
                           (if
                            (clojure.core/= ?__77081 X__77852)
                            (if
                             (clojure.core/symbol? elem__77850_nth_1__)
                             (clojure.core/let
                              [X__77854
                               (clojure.core/name elem__77850_nth_1__)]
                              (clojure.core/case
                               X__77854
                               ("meander.math.zeta")
                               [?__77081]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78747)
                        (recur (clojure.core/next search_space__78746))
                        result__78747))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78744)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__77832
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77081 X__77832]
                     (clojure.core/let
                      [X__77834
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__77834
                       ("-")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__77822 input__77076_nth_1__ ?__77081)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78589)
                         (clojure.core/let
                          [[?__77081] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__77076_nth_0__)
                                3)
                               (clojure.core/let
                                [input__77076_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  1)
                                 input__77076_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__77076_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__77076_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__77076_nth_0__]
                                   (clojure.core/let
                                    [?env input__77076_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/-,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153 [?a ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153 [?b ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__)))))))))
                               (state__78589))
                              (state__78589)))
                            (state__78589))
                           (state__78589)))))
                       (state__78589)))))
                   (state__78589))))
                (state__78589)))
              (state__78589)))
            (state__78589))
           (state__78589))))
        (state__78589
         []
         (clojure.core/letfn
          [(def__77856
            [arg__77879 ?__77082]
            (clojure.core/letfn
             [(state__78749
               []
               (clojure.core/let
                [x__77880 "meander.zeta"]
                (if
                 (clojure.core/= ?__77082 x__77880)
                 [?__77082]
                 (state__78750))))
              (state__78750
               []
               (if
                (clojure.core/map? arg__77879)
                (clojure.core/let
                 [VAL__77881 (.valAt arg__77879 :aliases)]
                 (if
                  (clojure.core/map? VAL__77881)
                  (clojure.core/let
                   [X__77883 (clojure.core/set VAL__77881)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__77883))
                    (clojure.core/loop
                     [search_space__78751 (clojure.core/seq X__77883)]
                     (if
                      (clojure.core/seq search_space__78751)
                      (clojure.core/let
                       [elem__77884
                        (clojure.core/first search_space__78751)
                        result__78752
                        (clojure.core/let
                         [elem__77884_nth_0__
                          (clojure.core/nth elem__77884 0)
                          elem__77884_nth_1__
                          (clojure.core/nth elem__77884 1)]
                         (if
                          (clojure.core/symbol? elem__77884_nth_0__)
                          (clojure.core/let
                           [X__77886
                            (clojure.core/name elem__77884_nth_0__)]
                           (if
                            (clojure.core/= ?__77082 X__77886)
                            (if
                             (clojure.core/symbol? elem__77884_nth_1__)
                             (clojure.core/let
                              [X__77888
                               (clojure.core/name elem__77884_nth_1__)]
                              (clojure.core/case
                               X__77888
                               ("meander.zeta")
                               [?__77082]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78752)
                        (recur (clojure.core/next search_space__78751))
                        result__78752))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78749)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__77866
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77082 X__77866]
                     (clojure.core/let
                      [X__77868
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__77868
                       ("with")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__77856 input__77076_nth_1__ ?__77082)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78590)
                         (clojure.core/let
                          [[?__77082] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__77076_nth_0__)
                                3)
                               (clojure.core/let
                                [input__77076_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  1)
                                 input__77076_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__77076_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__77076_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__77076_nth_0__]
                                   (clojure.core/let
                                    [?env input__77076_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__9262__auto__
                                          (CATA__FN__77153
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?bindings
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9262__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9262__auto__
                                           0)))},
                                       :body
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          [?body ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__)))))))))
                               (state__78590))
                              (state__78590)))
                            (state__78590))
                           (state__78590)))))
                       (state__78590)))))
                   (state__78590))))
                (state__78590)))
              (state__78590)))
            (state__78590))
           (state__78590))))
        (state__78590
         []
         (clojure.core/letfn
          [(def__77890
            [arg__77913 ?__77083]
            (clojure.core/letfn
             [(state__78754
               []
               (clojure.core/let
                [x__77914 "meander.zeta"]
                (if
                 (clojure.core/= ?__77083 x__77914)
                 [?__77083]
                 (state__78755))))
              (state__78755
               []
               (if
                (clojure.core/map? arg__77913)
                (clojure.core/let
                 [VAL__77915 (.valAt arg__77913 :aliases)]
                 (if
                  (clojure.core/map? VAL__77915)
                  (clojure.core/let
                   [X__77917 (clojure.core/set VAL__77915)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__77917))
                    (clojure.core/loop
                     [search_space__78756 (clojure.core/seq X__77917)]
                     (if
                      (clojure.core/seq search_space__78756)
                      (clojure.core/let
                       [elem__77918
                        (clojure.core/first search_space__78756)
                        result__78757
                        (clojure.core/let
                         [elem__77918_nth_0__
                          (clojure.core/nth elem__77918 0)
                          elem__77918_nth_1__
                          (clojure.core/nth elem__77918 1)]
                         (if
                          (clojure.core/symbol? elem__77918_nth_0__)
                          (clojure.core/let
                           [X__77920
                            (clojure.core/name elem__77918_nth_0__)]
                           (if
                            (clojure.core/= ?__77083 X__77920)
                            (if
                             (clojure.core/symbol? elem__77918_nth_1__)
                             (clojure.core/let
                              [X__77922
                               (clojure.core/name elem__77918_nth_1__)]
                              (clojure.core/case
                               X__77922
                               ("meander.zeta")
                               [?__77083]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78757)
                        (recur (clojure.core/next search_space__78756))
                        result__78757))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78754)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__77900
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77083 X__77900]
                     (clojure.core/let
                      [X__77902
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__77902
                       ("apply")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__77890 input__77076_nth_1__ ?__77083)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78591)
                         (clojure.core/let
                          [[?__77083] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__77076_nth_0__)
                                3)
                               (clojure.core/let
                                [input__77076_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  1)
                                 input__77076_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__77076_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__77076_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__77076_nth_0__]
                                   (clojure.core/let
                                    [?env input__77076_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__)))))))))
                               (state__78591))
                              (state__78591)))
                            (state__78591))
                           (state__78591)))))
                       (state__78591)))))
                   (state__78591))))
                (state__78591)))
              (state__78591)))
            (state__78591))
           (state__78591))))
        (state__78591
         []
         (clojure.core/letfn
          [(def__77924
            [arg__77949 ?__77084]
            (clojure.core/letfn
             [(state__78759
               []
               (clojure.core/let
                [x__77950 "meander.zeta"]
                (if
                 (clojure.core/= ?__77084 x__77950)
                 [?__77084]
                 (state__78760))))
              (state__78760
               []
               (if
                (clojure.core/map? arg__77949)
                (clojure.core/let
                 [VAL__77951 (.valAt arg__77949 :aliases)]
                 (if
                  (clojure.core/map? VAL__77951)
                  (clojure.core/let
                   [X__77953 (clojure.core/set VAL__77951)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__77953))
                    (clojure.core/loop
                     [search_space__78761 (clojure.core/seq X__77953)]
                     (if
                      (clojure.core/seq search_space__78761)
                      (clojure.core/let
                       [elem__77954
                        (clojure.core/first search_space__78761)
                        result__78762
                        (clojure.core/let
                         [elem__77954_nth_0__
                          (clojure.core/nth elem__77954 0)
                          elem__77954_nth_1__
                          (clojure.core/nth elem__77954 1)]
                         (if
                          (clojure.core/symbol? elem__77954_nth_0__)
                          (clojure.core/let
                           [X__77956
                            (clojure.core/name elem__77954_nth_0__)]
                           (if
                            (clojure.core/= ?__77084 X__77956)
                            (if
                             (clojure.core/symbol? elem__77954_nth_1__)
                             (clojure.core/let
                              [X__77958
                               (clojure.core/name elem__77954_nth_1__)]
                              (clojure.core/case
                               X__77958
                               ("meander.zeta")
                               [?__77084]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78762)
                        (recur (clojure.core/next search_space__78761))
                        result__78762))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78759)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__77936
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77084 X__77936]
                     (clojure.core/let
                      [X__77938
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__77938
                       ("and")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__77924 input__77076_nth_1__ ?__77084)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78592)
                         (clojure.core/let
                          [[?__77084] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (clojure.core/let
                               [input__77076_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__77076_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__77076_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__77076_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__77076_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__77076_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__77076_nth_0__]
                                   (clojure.core/let
                                    [?env input__77076_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          ['meander.dev.parse.zeta/make-and
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__77155
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__77155
                                                (clojure.core/let
                                                 [CATA_RESULT__9262__auto__
                                                  (CATA__FN__77153
                                                   [(if
                                                     (.hasNext
                                                      !forms__counter)
                                                     (.next
                                                      !forms__counter))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9262__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9262__auto__
                                                   0)))))
                                              (clojure.core/persistent!
                                               return__77155))))
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__))))))))
                                (state__78592)))
                              (state__78592)))
                            (state__78592))
                           (state__78592)))))
                       (state__78592)))))
                   (state__78592))))
                (state__78592)))
              (state__78592)))
            (state__78592))
           (state__78592))))
        (state__78592
         []
         (if
          (clojure.core/vector? input__77076)
          (if
           (clojure.core/= (clojure.core/count input__77076) 3)
           (clojure.core/let
            [input__77076_nth_0__
             (clojure.core/nth input__77076 0)
             input__77076_nth_1__
             (clojure.core/nth input__77076 1)
             input__77076_nth_2__
             (clojure.core/nth input__77076 2)]
            (clojure.core/case
             input__77076_nth_0__
             (meander.dev.parse.zeta/make-and)
             (clojure.core/letfn
              [(state__78764
                []
                (if
                 (clojure.core/vector? input__77076_nth_1__)
                 (clojure.core/case
                  input__77076_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__77076_nth_2__]
                   (try
                    [{:tag :error,
                      :message
                      "meander.zeta/and requires 1 or more arguments",
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__10202__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10202__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10202__auto__)))))
                  (state__78765))
                 (state__78765)))
               (state__78765
                []
                (clojure.core/case
                 input__77076_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__77076_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__77076_nth_1__)
                    1)
                   (clojure.core/let
                    [input__77076_nth_1___nth_0__
                     (clojure.core/nth input__77076_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__77076_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__10202__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10202__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10202__auto__))))))
                   (state__78766))
                  (state__78766))
                 (state__78766)))
               (state__78766
                []
                (if
                 (clojure.core/vector? input__77076_nth_1__)
                 (clojure.core/letfn
                  [(state__78767
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__77076_nth_1__)
                      1)
                     (clojure.core/let
                      [input__77076_nth_1___nth_0__
                       (clojure.core/nth input__77076_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__77076_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__77076_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__9262__auto__
                            (CATA__FN__77153
                             ['meander.dev.parse.zeta/make-and
                              [?ast-a {:tag :pass}]
                              ?form])]
                           (if
                            (meander.runtime.zeta/fail?
                             CATA_RESULT__9262__auto__)
                            (throw (meander.runtime.zeta/fail))
                            (clojure.core/nth
                             CATA_RESULT__9262__auto__
                             0)))]
                         (catch
                          java.lang.Exception
                          e__10202__auto__
                          (if
                           (meander.runtime.zeta/fail?
                            e__10202__auto__)
                           (meander.runtime.zeta/fail)
                           (throw e__10202__auto__)))))))
                     (state__78768)))
                   (state__78768
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__77076_nth_1__)
                      2)
                     (clojure.core/let
                      [input__77076_nth_1___nth_0__
                       (clojure.core/nth input__77076_nth_1__ 0)
                       input__77076_nth_1___nth_1__
                       (clojure.core/nth input__77076_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__77076_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__77076_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__77076_nth_2__]
                         (try
                          [{:tag :and,
                            :left ?ast-a,
                            :right ?ast-b,
                            :form ?form}]
                          (catch
                           java.lang.Exception
                           e__10202__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10202__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10202__auto__))))))))
                     (state__78769)))
                   (state__78769
                    []
                    (clojure.core/loop
                     [search_space__78770
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__77076_nth_1__)]
                     (if
                      (clojure.core/seq search_space__78770)
                      (clojure.core/let
                       [input__77076_nth_1___parts__
                        (clojure.core/first search_space__78770)
                        result__78771
                        (clojure.core/let
                         [input__77076_nth_1___l__
                          (clojure.core/nth
                           input__77076_nth_1___parts__
                           0)
                          input__77076_nth_1___r__
                          (clojure.core/nth
                           input__77076_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__78773
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__8123__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__77076_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__77985]
                                 (clojure.core/let
                                  [input__77985_nth_0__
                                   (clojure.core/nth input__77985 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__77985_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__77978
                                   (clojure.core/count
                                    input__77076_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__77978]
                                   (clojure.core/let
                                    [X__77982
                                     (clojure.core/count
                                      input__77076_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__77982)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__8123__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__77076_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__77983]
                                          (clojure.core/let
                                           [input__77983_nth_0__
                                            (clojure.core/nth
                                             input__77983
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__77983_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form input__77076_nth_2__]
                                           (try
                                            [(clojure.core/let
                                              [!asts-1__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-1)
                                               !asts-2__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-2)]
                                              (clojure.core/let
                                               [CATA_RESULT__9262__auto__
                                                (CATA__FN__77153
                                                 ['meander.dev.parse.zeta/make-and
                                                  [(clojure.core/let
                                                    [CATA_RESULT__9262__auto__
                                                     (CATA__FN__77153
                                                      ['meander.dev.parse.zeta/make-and
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-1__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9262__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9262__auto__
                                                      0)))
                                                   (clojure.core/let
                                                    [CATA_RESULT__9262__auto__
                                                     (CATA__FN__77153
                                                      ['meander.dev.parse.zeta/make-and
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-2__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9262__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9262__auto__
                                                      0)))]
                                                  ?form])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__9262__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__9262__auto__
                                                 0))))]
                                            (catch
                                             java.lang.Exception
                                             e__10202__auto__
                                             (if
                                              (meander.runtime.zeta/fail?
                                               e__10202__auto__)
                                              (meander.runtime.zeta/fail)
                                              (throw
                                               e__10202__auto__)))))))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         ret__8123__auto__)
                                        (state__78774)
                                        ret__8123__auto__)))
                                     (state__78774)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__8123__auto__)
                               (state__78774)
                               ret__8123__auto__))))
                           (state__78774
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__8123__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__77076_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__78001]
                                 (clojure.core/let
                                  [input__78001_nth_0__
                                   (clojure.core/nth input__78001 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__78001_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__77992
                                   (clojure.core/count
                                    input__77076_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__77992]
                                   (clojure.core/let
                                    [input__77076_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__77076_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__77076_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__77076_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__77076_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__77076_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__77076_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__77076_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__77076_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__77998
                                          (clojure.core/count
                                           input__77076_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__77998)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__8123__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__77076_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__77999]
                                               (clojure.core/let
                                                [input__77999_nth_0__
                                                 (clojure.core/nth
                                                  input__77999
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__77999_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__77076_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!asts-1__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-1)
                                                    !asts-2__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-2)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__9262__auto__
                                                     (CATA__FN__77153
                                                      ['meander.dev.parse.zeta/make-and
                                                       [(clojure.core/let
                                                         [CATA_RESULT__9262__auto__
                                                          (CATA__FN__77153
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
                                                           CATA_RESULT__9262__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__9262__auto__
                                                           0)))
                                                        (clojure.core/let
                                                         [CATA_RESULT__9262__auto__
                                                          (CATA__FN__77153
                                                           ['meander.dev.parse.zeta/make-and
                                                            (clojure.core/into
                                                             []
                                                             (clojure.core/vec
                                                              (clojure.core/iterator-seq
                                                               !asts-2__counter)))
                                                            nil])]
                                                         (if
                                                          (meander.runtime.zeta/fail?
                                                           CATA_RESULT__9262__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__9262__auto__
                                                           0)))]
                                                       ?form])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9262__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9262__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__10202__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__10202__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__10202__auto__)))))))]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              ret__8123__auto__)
                                             (meander.runtime.zeta/fail)
                                             ret__8123__auto__)))
                                          (meander.runtime.zeta/fail))))))
                                     (meander.runtime.zeta/fail)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__8123__auto__)
                               (meander.runtime.zeta/fail)
                               ret__8123__auto__))))]
                          (state__78773)))]
                       (if
                        (meander.runtime.zeta/fail? result__78771)
                        (recur (clojure.core/next search_space__78770))
                        result__78771))
                      (state__78593))))]
                  (state__78767))
                 (state__78593)))]
              (state__78764))
             (state__78593)))
           (state__78593))
          (state__78593)))
        (state__78593
         []
         (clojure.core/letfn
          [(def__78004
            [arg__78027 ?__77085]
            (clojure.core/letfn
             [(state__78779
               []
               (clojure.core/let
                [x__78028 "meander.zeta"]
                (if
                 (clojure.core/= ?__77085 x__78028)
                 [?__77085]
                 (state__78780))))
              (state__78780
               []
               (if
                (clojure.core/map? arg__78027)
                (clojure.core/let
                 [VAL__78029 (.valAt arg__78027 :aliases)]
                 (if
                  (clojure.core/map? VAL__78029)
                  (clojure.core/let
                   [X__78031 (clojure.core/set VAL__78029)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__78031))
                    (clojure.core/loop
                     [search_space__78781 (clojure.core/seq X__78031)]
                     (if
                      (clojure.core/seq search_space__78781)
                      (clojure.core/let
                       [elem__78032
                        (clojure.core/first search_space__78781)
                        result__78782
                        (clojure.core/let
                         [elem__78032_nth_0__
                          (clojure.core/nth elem__78032 0)
                          elem__78032_nth_1__
                          (clojure.core/nth elem__78032 1)]
                         (if
                          (clojure.core/symbol? elem__78032_nth_0__)
                          (clojure.core/let
                           [X__78034
                            (clojure.core/name elem__78032_nth_0__)]
                           (if
                            (clojure.core/= ?__77085 X__78034)
                            (if
                             (clojure.core/symbol? elem__78032_nth_1__)
                             (clojure.core/let
                              [X__78036
                               (clojure.core/name elem__78032_nth_1__)]
                              (clojure.core/case
                               X__78036
                               ("meander.zeta")
                               [?__77085]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78782)
                        (recur (clojure.core/next search_space__78781))
                        result__78782))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78779)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__78014
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77085 X__78014]
                     (clojure.core/let
                      [X__78016
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__78016
                       ("cata")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__78004 input__77076_nth_1__ ?__77085)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78594)
                         (clojure.core/let
                          [[?__77085] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__77076_nth_0__)
                                2)
                               (clojure.core/let
                                [input__77076_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__77076_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__77076_nth_0__]
                                  (clojure.core/let
                                   [?env input__77076_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9262__auto__
                                        (CATA__FN__77153
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9262__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9262__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10202__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10202__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10202__auto__))))))))
                               (state__78594))
                              (state__78594)))
                            (state__78594))
                           (state__78594)))))
                       (state__78594)))))
                   (state__78594))))
                (state__78594)))
              (state__78594)))
            (state__78594))
           (state__78594))))
        (state__78594
         []
         (clojure.core/letfn
          [(def__78038
            [arg__78061 ?__77086]
            (clojure.core/letfn
             [(state__78784
               []
               (clojure.core/let
                [x__78062 "meander.zeta"]
                (if
                 (clojure.core/= ?__77086 x__78062)
                 [?__77086]
                 (state__78785))))
              (state__78785
               []
               (if
                (clojure.core/map? arg__78061)
                (clojure.core/let
                 [VAL__78063 (.valAt arg__78061 :aliases)]
                 (if
                  (clojure.core/map? VAL__78063)
                  (clojure.core/let
                   [X__78065 (clojure.core/set VAL__78063)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__78065))
                    (clojure.core/loop
                     [search_space__78786 (clojure.core/seq X__78065)]
                     (if
                      (clojure.core/seq search_space__78786)
                      (clojure.core/let
                       [elem__78066
                        (clojure.core/first search_space__78786)
                        result__78787
                        (clojure.core/let
                         [elem__78066_nth_0__
                          (clojure.core/nth elem__78066 0)
                          elem__78066_nth_1__
                          (clojure.core/nth elem__78066 1)]
                         (if
                          (clojure.core/symbol? elem__78066_nth_0__)
                          (clojure.core/let
                           [X__78068
                            (clojure.core/name elem__78066_nth_0__)]
                           (if
                            (clojure.core/= ?__77086 X__78068)
                            (if
                             (clojure.core/symbol? elem__78066_nth_1__)
                             (clojure.core/let
                              [X__78070
                               (clojure.core/name elem__78066_nth_1__)]
                              (clojure.core/case
                               X__78070
                               ("meander.zeta")
                               [?__77086]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78787)
                        (recur (clojure.core/next search_space__78786))
                        result__78787))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78784)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__78048
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77086 X__78048]
                     (clojure.core/let
                      [X__78050
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__78050
                       ("fold")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__78038 input__77076_nth_1__ ?__77086)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78595)
                         (clojure.core/let
                          [[?__77086] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__77076_nth_0__)
                                4)
                               (clojure.core/let
                                [input__77076_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  1)
                                 input__77076_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  2)
                                 input__77076_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__77076_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__77076_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__77076_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__77076_nth_0__]
                                    (clojure.core/let
                                     [?env input__77076_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__9262__auto__
                                             (CATA__FN__77153
                                              [?mutable-variable
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9262__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9262__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__9262__auto__
                                             (CATA__FN__77153
                                              [?initial-value ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9262__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9262__auto__
                                              0)))
                                           ?fold-function
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__10202__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10202__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__10202__auto__))))))))))
                               (state__78595))
                              (state__78595)))
                            (state__78595))
                           (state__78595)))))
                       (state__78595)))))
                   (state__78595))))
                (state__78595)))
              (state__78595)))
            (state__78595))
           (state__78595))))
        (state__78595
         []
         (if
          (clojure.core/vector? input__77076)
          (if
           (clojure.core/= (clojure.core/count input__77076) 5)
           (clojure.core/let
            [input__77076_nth_0__
             (clojure.core/nth input__77076 0)
             input__77076_nth_1__
             (clojure.core/nth input__77076 1)
             input__77076_nth_2__
             (clojure.core/nth input__77076 2)
             input__77076_nth_3__
             (clojure.core/nth input__77076 3)
             input__77076_nth_4__
             (clojure.core/nth input__77076 4)]
            (clojure.core/case
             input__77076_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__77076_nth_1__)
              (clojure.core/let
               [VAL__78073 (.valAt input__77076_nth_1__ :tag)]
               (clojure.core/case
                VAL__78073
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__77076_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__77076_nth_2__]
                  (clojure.core/let
                   [?fold-function input__77076_nth_3__]
                   (clojure.core/let
                    [?form input__77076_nth_4__]
                    (try
                     [{:tag :fold,
                       :variable ?variable-ast,
                       :initial-value ?initial-value-ast,
                       :fold-function
                       {:tag :host-expression, :form ?fold-function},
                       :form ?form}]
                     (catch
                      java.lang.Exception
                      e__10202__auto__
                      (if
                       (meander.runtime.zeta/fail? e__10202__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__10202__auto__))))))))
                (state__78596)))
              (state__78596))
             (state__78596)))
           (state__78596))
          (state__78596)))
        (state__78596
         []
         (clojure.core/letfn
          [(def__78075
            [arg__78098 ?__77087]
            (clojure.core/letfn
             [(state__78789
               []
               (clojure.core/let
                [x__78099 "meander.zeta"]
                (if
                 (clojure.core/= ?__77087 x__78099)
                 [?__77087]
                 (state__78790))))
              (state__78790
               []
               (if
                (clojure.core/map? arg__78098)
                (clojure.core/let
                 [VAL__78100 (.valAt arg__78098 :aliases)]
                 (if
                  (clojure.core/map? VAL__78100)
                  (clojure.core/let
                   [X__78102 (clojure.core/set VAL__78100)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__78102))
                    (clojure.core/loop
                     [search_space__78791 (clojure.core/seq X__78102)]
                     (if
                      (clojure.core/seq search_space__78791)
                      (clojure.core/let
                       [elem__78103
                        (clojure.core/first search_space__78791)
                        result__78792
                        (clojure.core/let
                         [elem__78103_nth_0__
                          (clojure.core/nth elem__78103 0)
                          elem__78103_nth_1__
                          (clojure.core/nth elem__78103 1)]
                         (if
                          (clojure.core/symbol? elem__78103_nth_0__)
                          (clojure.core/let
                           [X__78105
                            (clojure.core/name elem__78103_nth_0__)]
                           (if
                            (clojure.core/= ?__77087 X__78105)
                            (if
                             (clojure.core/symbol? elem__78103_nth_1__)
                             (clojure.core/let
                              [X__78107
                               (clojure.core/name elem__78103_nth_1__)]
                              (clojure.core/case
                               X__78107
                               ("meander.zeta")
                               [?__77087]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78792)
                        (recur (clojure.core/next search_space__78791))
                        result__78792))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78789)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__78085
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77087 X__78085]
                     (clojure.core/let
                      [X__78087
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__78087
                       ("keyword")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__78075 input__77076_nth_1__ ?__77087)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78597)
                         (clojure.core/let
                          [[?__77087] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (clojure.core/let
                               [input__77076_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__77076_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__77076_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__77076_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__77076_nth_0__)]
                                 (clojure.core/let
                                  [?keyword-args
                                   input__77076_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__77076_nth_0__]
                                   (clojure.core/let
                                    [?env input__77076_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [CATA_RESULT__9262__auto__
                                        (CATA__FN__77153
                                         ['meander.dev.parse.zeta/make-keyword
                                          (clojure.core/into
                                           []
                                           ?keyword-args)
                                          ?form
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9262__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9262__auto__
                                         0)))]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__))))))))
                                (state__78597)))
                              (state__78597)))
                            (state__78597))
                           (state__78597)))))
                       (state__78597)))))
                   (state__78597))))
                (state__78597)))
              (state__78597)))
            (state__78597))
           (state__78597))))
        (state__78597
         []
         (if
          (clojure.core/vector? input__77076)
          (if
           (clojure.core/= (clojure.core/count input__77076) 4)
           (clojure.core/let
            [input__77076_nth_0__
             (clojure.core/nth input__77076 0)
             input__77076_nth_1__
             (clojure.core/nth input__77076 1)
             input__77076_nth_2__
             (clojure.core/nth input__77076 2)]
            (clojure.core/letfn
             [(state__78794
               []
               (clojure.core/case
                input__77076_nth_0__
                (meander.dev.parse.zeta/make-keyword)
                (if
                 (clojure.core/vector? input__77076_nth_1__)
                 (clojure.core/case
                  input__77076_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__77076_nth_2__]
                   (try
                    [{:tag :keyword,
                      :namespace {:tag :wildcard},
                      :name {:tag :wildcard},
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__10202__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10202__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10202__auto__)))))
                  (state__78795))
                 (state__78795))
                (state__78795)))
              (state__78795
               []
               (clojure.core/let
                [input__77076_nth_3__
                 (clojure.core/nth input__77076 3)]
                (clojure.core/case
                 input__77076_nth_0__
                 (meander.dev.parse.zeta/make-keyword)
                 (if
                  (clojure.core/vector? input__77076_nth_1__)
                  (clojure.core/letfn
                   [(state__78796
                     []
                     (if
                      (clojure.core/=
                       (clojure.core/count input__77076_nth_1__)
                       1)
                      (clojure.core/let
                       [input__77076_nth_1___nth_0__
                        (clojure.core/nth input__77076_nth_1__ 0)]
                       (clojure.core/let
                        [?name input__77076_nth_1___nth_0__]
                        (clojure.core/let
                         [?form input__77076_nth_2__]
                         (clojure.core/let
                          [?env input__77076_nth_3__]
                          (try
                           [{:tag :keyword,
                             :namespace {:tag :wildcard},
                             :name
                             (clojure.core/let
                              [CATA_RESULT__9262__auto__
                               (CATA__FN__77153 [?name ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__9262__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__9262__auto__
                                0))),
                             :form ?form}]
                           (catch
                            java.lang.Exception
                            e__10202__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10202__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10202__auto__))))))))
                      (state__78797)))
                    (state__78797
                     []
                     (if
                      (clojure.core/=
                       (clojure.core/count input__77076_nth_1__)
                       2)
                      (clojure.core/let
                       [input__77076_nth_1___nth_0__
                        (clojure.core/nth input__77076_nth_1__ 0)
                        input__77076_nth_1___nth_1__
                        (clojure.core/nth input__77076_nth_1__ 1)]
                       (clojure.core/let
                        [?namespace input__77076_nth_1___nth_0__]
                        (clojure.core/let
                         [?name input__77076_nth_1___nth_1__]
                         (clojure.core/let
                          [?form input__77076_nth_2__]
                          (clojure.core/let
                           [?env input__77076_nth_3__]
                           (try
                            [{:tag :keyword,
                              :namespace
                              (clojure.core/let
                               [CATA_RESULT__9262__auto__
                                (CATA__FN__77153 [?namespace ?env])]
                               (if
                                (meander.runtime.zeta/fail?
                                 CATA_RESULT__9262__auto__)
                                (throw (meander.runtime.zeta/fail))
                                (clojure.core/nth
                                 CATA_RESULT__9262__auto__
                                 0))),
                              :name
                              (clojure.core/let
                               [CATA_RESULT__9262__auto__
                                (CATA__FN__77153 [?name ?env])]
                               (if
                                (meander.runtime.zeta/fail?
                                 CATA_RESULT__9262__auto__)
                                (throw (meander.runtime.zeta/fail))
                                (clojure.core/nth
                                 CATA_RESULT__9262__auto__
                                 0))),
                              :form ?form}]
                            (catch
                             java.lang.Exception
                             e__10202__auto__
                             (if
                              (meander.runtime.zeta/fail?
                               e__10202__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__10202__auto__)))))))))
                      (state__78598)))]
                   (state__78796))
                  (state__78598))
                 (state__78598))))]
             (state__78794)))
           (state__78598))
          (state__78598)))
        (state__78598
         []
         (clojure.core/letfn
          [(def__78119
            [arg__78142 ?__77088]
            (clojure.core/letfn
             [(state__78798
               []
               (clojure.core/let
                [x__78143 "meander.zeta"]
                (if
                 (clojure.core/= ?__77088 x__78143)
                 [?__77088]
                 (state__78799))))
              (state__78799
               []
               (if
                (clojure.core/map? arg__78142)
                (clojure.core/let
                 [VAL__78144 (.valAt arg__78142 :aliases)]
                 (if
                  (clojure.core/map? VAL__78144)
                  (clojure.core/let
                   [X__78146 (clojure.core/set VAL__78144)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__78146))
                    (clojure.core/loop
                     [search_space__78800 (clojure.core/seq X__78146)]
                     (if
                      (clojure.core/seq search_space__78800)
                      (clojure.core/let
                       [elem__78147
                        (clojure.core/first search_space__78800)
                        result__78801
                        (clojure.core/let
                         [elem__78147_nth_0__
                          (clojure.core/nth elem__78147 0)
                          elem__78147_nth_1__
                          (clojure.core/nth elem__78147 1)]
                         (if
                          (clojure.core/symbol? elem__78147_nth_0__)
                          (clojure.core/let
                           [X__78149
                            (clojure.core/name elem__78147_nth_0__)]
                           (if
                            (clojure.core/= ?__77088 X__78149)
                            (if
                             (clojure.core/symbol? elem__78147_nth_1__)
                             (clojure.core/let
                              [X__78151
                               (clojure.core/name elem__78147_nth_1__)]
                              (clojure.core/case
                               X__78151
                               ("meander.zeta")
                               [?__77088]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78801)
                        (recur (clojure.core/next search_space__78800))
                        result__78801))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78798)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__78129
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77088 X__78129]
                     (clojure.core/let
                      [X__78131
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__78131
                       ("let")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__78119 input__77076_nth_1__ ?__77088)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78599)
                         (clojure.core/let
                          [[?__77088] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__77076_nth_0__)
                                3)
                               (clojure.core/let
                                [input__77076_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  1)
                                 input__77076_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__77076_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__77076_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__77076_nth_0__]
                                   (clojure.core/let
                                    [?env input__77076_nth_1__]
                                    (try
                                     [{:tag :let,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0))),
                                       :expression
                                       {:tag :host-expression,
                                        :form ?expression},
                                       :next {:tag :pass}}]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__)))))))))
                               (state__78599))
                              (state__78599)))
                            (state__78599))
                           (state__78599)))))
                       (state__78599)))))
                   (state__78599))))
                (state__78599)))
              (state__78599)))
            (state__78599))
           (state__78599))))
        (state__78599
         []
         (clojure.core/letfn
          [(def__78153
            [arg__78176 ?__77089]
            (clojure.core/letfn
             [(state__78803
               []
               (clojure.core/let
                [x__78177 "meander.zeta"]
                (if
                 (clojure.core/= ?__77089 x__78177)
                 [?__77089]
                 (state__78804))))
              (state__78804
               []
               (if
                (clojure.core/map? arg__78176)
                (clojure.core/let
                 [VAL__78178 (.valAt arg__78176 :aliases)]
                 (if
                  (clojure.core/map? VAL__78178)
                  (clojure.core/let
                   [X__78180 (clojure.core/set VAL__78178)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__78180))
                    (clojure.core/loop
                     [search_space__78805 (clojure.core/seq X__78180)]
                     (if
                      (clojure.core/seq search_space__78805)
                      (clojure.core/let
                       [elem__78181
                        (clojure.core/first search_space__78805)
                        result__78806
                        (clojure.core/let
                         [elem__78181_nth_0__
                          (clojure.core/nth elem__78181 0)
                          elem__78181_nth_1__
                          (clojure.core/nth elem__78181 1)]
                         (if
                          (clojure.core/symbol? elem__78181_nth_0__)
                          (clojure.core/let
                           [X__78183
                            (clojure.core/name elem__78181_nth_0__)]
                           (if
                            (clojure.core/= ?__77089 X__78183)
                            (if
                             (clojure.core/symbol? elem__78181_nth_1__)
                             (clojure.core/let
                              [X__78185
                               (clojure.core/name elem__78181_nth_1__)]
                              (clojure.core/case
                               X__78185
                               ("meander.zeta")
                               [?__77089]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78806)
                        (recur (clojure.core/next search_space__78805))
                        result__78806))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78803)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__78163
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77089 X__78163]
                     (clojure.core/let
                      [X__78165
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__78165
                       ("let")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__78153 input__77076_nth_1__ ?__77089)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78600)
                         (clojure.core/let
                          [[?__77089] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__77076_nth_0__)
                                4)
                               (clojure.core/let
                                [input__77076_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  1)
                                 input__77076_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  2)
                                 input__77076_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?pattern
                                  input__77076_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__77076_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?next input__77076_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__77076_nth_0__]
                                    (clojure.core/let
                                     [?env input__77076_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__9262__auto__
                                          (CATA__FN__77153
                                           [?pattern ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9262__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9262__auto__
                                           0))),
                                        :expression
                                        {:tag :host-expression,
                                         :form ?expression},
                                        :next
                                        (clojure.core/let
                                         [CATA_RESULT__9262__auto__
                                          (CATA__FN__77153
                                           [?next ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9262__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9262__auto__
                                           0)))}]
                                      (catch
                                       java.lang.Exception
                                       e__10202__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10202__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__10202__auto__))))))))))
                               (state__78600))
                              (state__78600)))
                            (state__78600))
                           (state__78600)))))
                       (state__78600)))))
                   (state__78600))))
                (state__78600)))
              (state__78600)))
            (state__78600))
           (state__78600))))
        (state__78600
         []
         (clojure.core/letfn
          [(def__78187
            [arg__78210 ?__77090]
            (clojure.core/letfn
             [(state__78808
               []
               (clojure.core/let
                [x__78211 "meander.zeta"]
                (if
                 (clojure.core/= ?__77090 x__78211)
                 [?__77090]
                 (state__78809))))
              (state__78809
               []
               (if
                (clojure.core/map? arg__78210)
                (clojure.core/let
                 [VAL__78212 (.valAt arg__78210 :aliases)]
                 (if
                  (clojure.core/map? VAL__78212)
                  (clojure.core/let
                   [X__78214 (clojure.core/set VAL__78212)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__78214))
                    (clojure.core/loop
                     [search_space__78810 (clojure.core/seq X__78214)]
                     (if
                      (clojure.core/seq search_space__78810)
                      (clojure.core/let
                       [elem__78215
                        (clojure.core/first search_space__78810)
                        result__78811
                        (clojure.core/let
                         [elem__78215_nth_0__
                          (clojure.core/nth elem__78215 0)
                          elem__78215_nth_1__
                          (clojure.core/nth elem__78215 1)]
                         (if
                          (clojure.core/symbol? elem__78215_nth_0__)
                          (clojure.core/let
                           [X__78217
                            (clojure.core/name elem__78215_nth_0__)]
                           (if
                            (clojure.core/= ?__77090 X__78217)
                            (if
                             (clojure.core/symbol? elem__78215_nth_1__)
                             (clojure.core/let
                              [X__78219
                               (clojure.core/name elem__78215_nth_1__)]
                              (clojure.core/case
                               X__78219
                               ("meander.zeta")
                               [?__77090]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78811)
                        (recur (clojure.core/next search_space__78810))
                        result__78811))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78808)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__78197
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77090 X__78197]
                     (clojure.core/let
                      [X__78199
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__78199
                       ("not")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__78187 input__77076_nth_1__ ?__77090)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78601)
                         (clojure.core/let
                          [[?__77090] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__77076_nth_0__)
                                2)
                               (clojure.core/let
                                [input__77076_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__77076_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__77076_nth_0__]
                                  (clojure.core/let
                                   [?env input__77076_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9262__auto__
                                        (CATA__FN__77153
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9262__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9262__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10202__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10202__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10202__auto__))))))))
                               (state__78601))
                              (state__78601)))
                            (state__78601))
                           (state__78601)))))
                       (state__78601)))))
                   (state__78601))))
                (state__78601)))
              (state__78601)))
            (state__78601))
           (state__78601))))
        (state__78601
         []
         (clojure.core/letfn
          [(def__78221
            [arg__78246 ?__77091]
            (clojure.core/letfn
             [(state__78813
               []
               (clojure.core/let
                [x__78247 "meander.zeta"]
                (if
                 (clojure.core/= ?__77091 x__78247)
                 [?__77091]
                 (state__78814))))
              (state__78814
               []
               (if
                (clojure.core/map? arg__78246)
                (clojure.core/let
                 [VAL__78248 (.valAt arg__78246 :aliases)]
                 (if
                  (clojure.core/map? VAL__78248)
                  (clojure.core/let
                   [X__78250 (clojure.core/set VAL__78248)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__78250))
                    (clojure.core/loop
                     [search_space__78815 (clojure.core/seq X__78250)]
                     (if
                      (clojure.core/seq search_space__78815)
                      (clojure.core/let
                       [elem__78251
                        (clojure.core/first search_space__78815)
                        result__78816
                        (clojure.core/let
                         [elem__78251_nth_0__
                          (clojure.core/nth elem__78251 0)
                          elem__78251_nth_1__
                          (clojure.core/nth elem__78251 1)]
                         (if
                          (clojure.core/symbol? elem__78251_nth_0__)
                          (clojure.core/let
                           [X__78253
                            (clojure.core/name elem__78251_nth_0__)]
                           (if
                            (clojure.core/= ?__77091 X__78253)
                            (if
                             (clojure.core/symbol? elem__78251_nth_1__)
                             (clojure.core/let
                              [X__78255
                               (clojure.core/name elem__78251_nth_1__)]
                              (clojure.core/case
                               X__78255
                               ("meander.zeta")
                               [?__77091]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78816)
                        (recur (clojure.core/next search_space__78815))
                        result__78816))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78813)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__78233
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77091 X__78233]
                     (clojure.core/let
                      [X__78235
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__78235
                       ("or")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__78221 input__77076_nth_1__ ?__77091)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78602)
                         (clojure.core/let
                          [[?__77091] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (clojure.core/let
                               [input__77076_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__77076_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__77076_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__77076_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__77076_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__77076_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__77076_nth_0__]
                                   (clojure.core/let
                                    [?env input__77076_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          ['meander.dev.parse.zeta/make-or
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__77156
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__77156
                                                (clojure.core/let
                                                 [CATA_RESULT__9262__auto__
                                                  (CATA__FN__77153
                                                   [(if
                                                     (.hasNext
                                                      !forms__counter)
                                                     (.next
                                                      !forms__counter))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9262__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9262__auto__
                                                   0)))))
                                              (clojure.core/persistent!
                                               return__77156))))
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__))))))))
                                (state__78602)))
                              (state__78602)))
                            (state__78602))
                           (state__78602)))))
                       (state__78602)))))
                   (state__78602))))
                (state__78602)))
              (state__78602)))
            (state__78602))
           (state__78602))))
        (state__78602
         []
         (if
          (clojure.core/vector? input__77076)
          (if
           (clojure.core/= (clojure.core/count input__77076) 3)
           (clojure.core/let
            [input__77076_nth_0__
             (clojure.core/nth input__77076 0)
             input__77076_nth_1__
             (clojure.core/nth input__77076 1)
             input__77076_nth_2__
             (clojure.core/nth input__77076 2)]
            (clojure.core/case
             input__77076_nth_0__
             (meander.dev.parse.zeta/make-or)
             (clojure.core/letfn
              [(state__78818
                []
                (if
                 (clojure.core/vector? input__77076_nth_1__)
                 (clojure.core/case
                  input__77076_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__77076_nth_2__]
                   (try
                    [{:tag :error,
                      :message
                      "meander.zeta/or requires 1 or more arguments",
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__10202__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10202__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10202__auto__)))))
                  (state__78819))
                 (state__78819)))
               (state__78819
                []
                (clojure.core/case
                 input__77076_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__77076_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__77076_nth_1__)
                    1)
                   (clojure.core/let
                    [input__77076_nth_1___nth_0__
                     (clojure.core/nth input__77076_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__77076_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__10202__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10202__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10202__auto__))))))
                   (state__78820))
                  (state__78820))
                 (state__78820)))
               (state__78820
                []
                (if
                 (clojure.core/vector? input__77076_nth_1__)
                 (clojure.core/letfn
                  [(state__78821
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__77076_nth_1__)
                      1)
                     (clojure.core/let
                      [input__77076_nth_1___nth_0__
                       (clojure.core/nth input__77076_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__77076_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__77076_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__9262__auto__
                            (CATA__FN__77153
                             ['meander.dev.parse.zeta/make-or
                              [?ast-a {:tag :pass}]
                              ?form])]
                           (if
                            (meander.runtime.zeta/fail?
                             CATA_RESULT__9262__auto__)
                            (throw (meander.runtime.zeta/fail))
                            (clojure.core/nth
                             CATA_RESULT__9262__auto__
                             0)))]
                         (catch
                          java.lang.Exception
                          e__10202__auto__
                          (if
                           (meander.runtime.zeta/fail?
                            e__10202__auto__)
                           (meander.runtime.zeta/fail)
                           (throw e__10202__auto__)))))))
                     (state__78822)))
                   (state__78822
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__77076_nth_1__)
                      2)
                     (clojure.core/let
                      [input__77076_nth_1___nth_0__
                       (clojure.core/nth input__77076_nth_1__ 0)
                       input__77076_nth_1___nth_1__
                       (clojure.core/nth input__77076_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__77076_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__77076_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__77076_nth_2__]
                         (try
                          [{:tag :or,
                            :left ?ast-a,
                            :right ?ast-b,
                            :form ?form}]
                          (catch
                           java.lang.Exception
                           e__10202__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10202__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10202__auto__))))))))
                     (state__78823)))
                   (state__78823
                    []
                    (clojure.core/loop
                     [search_space__78824
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__77076_nth_1__)]
                     (if
                      (clojure.core/seq search_space__78824)
                      (clojure.core/let
                       [input__77076_nth_1___parts__
                        (clojure.core/first search_space__78824)
                        result__78825
                        (clojure.core/let
                         [input__77076_nth_1___l__
                          (clojure.core/nth
                           input__77076_nth_1___parts__
                           0)
                          input__77076_nth_1___r__
                          (clojure.core/nth
                           input__77076_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__78827
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__8123__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__77076_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__78282]
                                 (clojure.core/let
                                  [input__78282_nth_0__
                                   (clojure.core/nth input__78282 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__78282_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__78275
                                   (clojure.core/count
                                    input__77076_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__78275]
                                   (clojure.core/let
                                    [X__78279
                                     (clojure.core/count
                                      input__77076_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__78279)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__8123__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__77076_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__78280]
                                          (clojure.core/let
                                           [input__78280_nth_0__
                                            (clojure.core/nth
                                             input__78280
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__78280_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form input__77076_nth_2__]
                                           (try
                                            [(clojure.core/let
                                              [!asts-1__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-1)
                                               !asts-2__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-2)]
                                              (clojure.core/let
                                               [CATA_RESULT__9262__auto__
                                                (CATA__FN__77153
                                                 ['meander.dev.parse.zeta/make-or
                                                  [(clojure.core/let
                                                    [CATA_RESULT__9262__auto__
                                                     (CATA__FN__77153
                                                      ['meander.dev.parse.zeta/make-or
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-1__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9262__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9262__auto__
                                                      0)))
                                                   (clojure.core/let
                                                    [CATA_RESULT__9262__auto__
                                                     (CATA__FN__77153
                                                      ['meander.dev.parse.zeta/make-or
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-2__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9262__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9262__auto__
                                                      0)))]
                                                  ?form])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__9262__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__9262__auto__
                                                 0))))]
                                            (catch
                                             java.lang.Exception
                                             e__10202__auto__
                                             (if
                                              (meander.runtime.zeta/fail?
                                               e__10202__auto__)
                                              (meander.runtime.zeta/fail)
                                              (throw
                                               e__10202__auto__)))))))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         ret__8123__auto__)
                                        (state__78828)
                                        ret__8123__auto__)))
                                     (state__78828)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__8123__auto__)
                               (state__78828)
                               ret__8123__auto__))))
                           (state__78828
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__8123__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__77076_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__78298]
                                 (clojure.core/let
                                  [input__78298_nth_0__
                                   (clojure.core/nth input__78298 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__78298_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__78289
                                   (clojure.core/count
                                    input__77076_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__78289]
                                   (clojure.core/let
                                    [input__77076_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__77076_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__77076_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__77076_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__77076_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__77076_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__77076_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__77076_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__77076_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__78295
                                          (clojure.core/count
                                           input__77076_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__78295)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__8123__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__77076_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__78296]
                                               (clojure.core/let
                                                [input__78296_nth_0__
                                                 (clojure.core/nth
                                                  input__78296
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__78296_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__77076_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!asts-1__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-1)
                                                    !asts-2__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-2)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__9262__auto__
                                                     (CATA__FN__77153
                                                      ['meander.dev.parse.zeta/make-or
                                                       [(clojure.core/let
                                                         [CATA_RESULT__9262__auto__
                                                          (CATA__FN__77153
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
                                                           CATA_RESULT__9262__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__9262__auto__
                                                           0)))
                                                        (clojure.core/let
                                                         [CATA_RESULT__9262__auto__
                                                          (CATA__FN__77153
                                                           ['meander.dev.parse.zeta/make-or
                                                            (clojure.core/into
                                                             []
                                                             (clojure.core/vec
                                                              (clojure.core/iterator-seq
                                                               !asts-2__counter)))
                                                            nil])]
                                                         (if
                                                          (meander.runtime.zeta/fail?
                                                           CATA_RESULT__9262__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__9262__auto__
                                                           0)))]
                                                       ?form])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9262__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9262__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__10202__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__10202__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__10202__auto__)))))))]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              ret__8123__auto__)
                                             (meander.runtime.zeta/fail)
                                             ret__8123__auto__)))
                                          (meander.runtime.zeta/fail))))))
                                     (meander.runtime.zeta/fail)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__8123__auto__)
                               (meander.runtime.zeta/fail)
                               ret__8123__auto__))))]
                          (state__78827)))]
                       (if
                        (meander.runtime.zeta/fail? result__78825)
                        (recur (clojure.core/next search_space__78824))
                        result__78825))
                      (state__78603))))]
                  (state__78821))
                 (state__78603)))]
              (state__78818))
             (state__78603)))
           (state__78603))
          (state__78603)))
        (state__78603
         []
         (clojure.core/letfn
          [(def__78301
            [arg__78324 ?__77092]
            (clojure.core/letfn
             [(state__78833
               []
               (clojure.core/let
                [x__78325 "meander.zeta"]
                (if
                 (clojure.core/= ?__77092 x__78325)
                 [?__77092]
                 (state__78834))))
              (state__78834
               []
               (if
                (clojure.core/map? arg__78324)
                (clojure.core/let
                 [VAL__78326 (.valAt arg__78324 :aliases)]
                 (if
                  (clojure.core/map? VAL__78326)
                  (clojure.core/let
                   [X__78328 (clojure.core/set VAL__78326)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__78328))
                    (clojure.core/loop
                     [search_space__78835 (clojure.core/seq X__78328)]
                     (if
                      (clojure.core/seq search_space__78835)
                      (clojure.core/let
                       [elem__78329
                        (clojure.core/first search_space__78835)
                        result__78836
                        (clojure.core/let
                         [elem__78329_nth_0__
                          (clojure.core/nth elem__78329 0)
                          elem__78329_nth_1__
                          (clojure.core/nth elem__78329 1)]
                         (if
                          (clojure.core/symbol? elem__78329_nth_0__)
                          (clojure.core/let
                           [X__78331
                            (clojure.core/name elem__78329_nth_0__)]
                           (if
                            (clojure.core/= ?__77092 X__78331)
                            (if
                             (clojure.core/symbol? elem__78329_nth_1__)
                             (clojure.core/let
                              [X__78333
                               (clojure.core/name elem__78329_nth_1__)]
                              (clojure.core/case
                               X__78333
                               ("meander.zeta")
                               [?__77092]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78836)
                        (recur (clojure.core/next search_space__78835))
                        result__78836))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78833)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__78311
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77092 X__78311]
                     (clojure.core/let
                      [X__78313
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__78313
                       ("pred")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__78301 input__77076_nth_1__ ?__77092)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78604)
                         (clojure.core/let
                          [[?__77092] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__77076_nth_0__)
                                2)
                               (clojure.core/let
                                [input__77076_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?expression
                                  input__77076_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__77076_nth_0__]
                                  (clojure.core/let
                                   [?env input__77076_nth_1__]
                                   (try
                                    [{:tag :pred,
                                      :expression
                                      {:tag :host-expression,
                                       :form ?expression},
                                      :pattern {:tag :wildcard},
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10202__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10202__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10202__auto__))))))))
                               (state__78604))
                              (state__78604)))
                            (state__78604))
                           (state__78604)))))
                       (state__78604)))))
                   (state__78604))))
                (state__78604)))
              (state__78604)))
            (state__78604))
           (state__78604))))
        (state__78604
         []
         (clojure.core/letfn
          [(def__78335
            [arg__78358 ?__77093]
            (clojure.core/letfn
             [(state__78838
               []
               (clojure.core/let
                [x__78359 "meander.zeta"]
                (if
                 (clojure.core/= ?__77093 x__78359)
                 [?__77093]
                 (state__78839))))
              (state__78839
               []
               (if
                (clojure.core/map? arg__78358)
                (clojure.core/let
                 [VAL__78360 (.valAt arg__78358 :aliases)]
                 (if
                  (clojure.core/map? VAL__78360)
                  (clojure.core/let
                   [X__78362 (clojure.core/set VAL__78360)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__78362))
                    (clojure.core/loop
                     [search_space__78840 (clojure.core/seq X__78362)]
                     (if
                      (clojure.core/seq search_space__78840)
                      (clojure.core/let
                       [elem__78363
                        (clojure.core/first search_space__78840)
                        result__78841
                        (clojure.core/let
                         [elem__78363_nth_0__
                          (clojure.core/nth elem__78363 0)
                          elem__78363_nth_1__
                          (clojure.core/nth elem__78363 1)]
                         (if
                          (clojure.core/symbol? elem__78363_nth_0__)
                          (clojure.core/let
                           [X__78365
                            (clojure.core/name elem__78363_nth_0__)]
                           (if
                            (clojure.core/= ?__77093 X__78365)
                            (if
                             (clojure.core/symbol? elem__78363_nth_1__)
                             (clojure.core/let
                              [X__78367
                               (clojure.core/name elem__78363_nth_1__)]
                              (clojure.core/case
                               X__78367
                               ("meander.zeta")
                               [?__77093]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78841)
                        (recur (clojure.core/next search_space__78840))
                        result__78841))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78838)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__78345
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77093 X__78345]
                     (clojure.core/let
                      [X__78347
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__78347
                       ("pred")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__78335 input__77076_nth_1__ ?__77093)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78605)
                         (clojure.core/let
                          [[?__77093] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__77076_nth_0__)
                                3)
                               (clojure.core/let
                                [input__77076_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  1)
                                 input__77076_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?expression
                                  input__77076_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__77076_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__77076_nth_0__]
                                   (clojure.core/let
                                    [?env input__77076_nth_1__]
                                    (try
                                     [{:tag :pred,
                                       :expression
                                       {:tag :host-expression,
                                        :form ?expression},
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__)))))))))
                               (state__78605))
                              (state__78605)))
                            (state__78605))
                           (state__78605)))))
                       (state__78605)))))
                   (state__78605))))
                (state__78605)))
              (state__78605)))
            (state__78605))
           (state__78605))))
        (state__78605
         []
         (clojure.core/letfn
          [(def__78369
            [arg__78392 ?__77094]
            (clojure.core/letfn
             [(state__78843
               []
               (clojure.core/let
                [x__78393 "meander.zeta"]
                (if
                 (clojure.core/= ?__77094 x__78393)
                 [?__77094]
                 (state__78844))))
              (state__78844
               []
               (if
                (clojure.core/map? arg__78392)
                (clojure.core/let
                 [VAL__78394 (.valAt arg__78392 :aliases)]
                 (if
                  (clojure.core/map? VAL__78394)
                  (clojure.core/let
                   [X__78396 (clojure.core/set VAL__78394)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__78396))
                    (clojure.core/loop
                     [search_space__78845 (clojure.core/seq X__78396)]
                     (if
                      (clojure.core/seq search_space__78845)
                      (clojure.core/let
                       [elem__78397
                        (clojure.core/first search_space__78845)
                        result__78846
                        (clojure.core/let
                         [elem__78397_nth_0__
                          (clojure.core/nth elem__78397 0)
                          elem__78397_nth_1__
                          (clojure.core/nth elem__78397 1)]
                         (if
                          (clojure.core/symbol? elem__78397_nth_0__)
                          (clojure.core/let
                           [X__78399
                            (clojure.core/name elem__78397_nth_0__)]
                           (if
                            (clojure.core/= ?__77094 X__78399)
                            (if
                             (clojure.core/symbol? elem__78397_nth_1__)
                             (clojure.core/let
                              [X__78401
                               (clojure.core/name elem__78397_nth_1__)]
                              (clojure.core/case
                               X__78401
                               ("meander.zeta")
                               [?__77094]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78846)
                        (recur (clojure.core/next search_space__78845))
                        result__78846))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78843)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__78379
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77094 X__78379]
                     (clojure.core/let
                      [X__78381
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__78381
                       ("re")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__78369 input__77076_nth_1__ ?__77094)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78606)
                         (clojure.core/let
                          [[?__77094] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__77076_nth_0__)
                                2)
                               (clojure.core/let
                                [input__77076_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__77076_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__77076_nth_0__]
                                  (clojure.core/let
                                   [?env input__77076_nth_1__]
                                   (try
                                    [{:tag :regex,
                                      :regex ?regex,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10202__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10202__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10202__auto__))))))))
                               (state__78606))
                              (state__78606)))
                            (state__78606))
                           (state__78606)))))
                       (state__78606)))))
                   (state__78606))))
                (state__78606)))
              (state__78606)))
            (state__78606))
           (state__78606))))
        (state__78606
         []
         (clojure.core/letfn
          [(def__78403
            [arg__78426 ?__77095]
            (clojure.core/letfn
             [(state__78848
               []
               (clojure.core/let
                [x__78427 "meander.zeta"]
                (if
                 (clojure.core/= ?__77095 x__78427)
                 [?__77095]
                 (state__78849))))
              (state__78849
               []
               (if
                (clojure.core/map? arg__78426)
                (clojure.core/let
                 [VAL__78428 (.valAt arg__78426 :aliases)]
                 (if
                  (clojure.core/map? VAL__78428)
                  (clojure.core/let
                   [X__78430 (clojure.core/set VAL__78428)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__78430))
                    (clojure.core/loop
                     [search_space__78850 (clojure.core/seq X__78430)]
                     (if
                      (clojure.core/seq search_space__78850)
                      (clojure.core/let
                       [elem__78431
                        (clojure.core/first search_space__78850)
                        result__78851
                        (clojure.core/let
                         [elem__78431_nth_0__
                          (clojure.core/nth elem__78431 0)
                          elem__78431_nth_1__
                          (clojure.core/nth elem__78431 1)]
                         (if
                          (clojure.core/symbol? elem__78431_nth_0__)
                          (clojure.core/let
                           [X__78433
                            (clojure.core/name elem__78431_nth_0__)]
                           (if
                            (clojure.core/= ?__77095 X__78433)
                            (if
                             (clojure.core/symbol? elem__78431_nth_1__)
                             (clojure.core/let
                              [X__78435
                               (clojure.core/name elem__78431_nth_1__)]
                              (clojure.core/case
                               X__78435
                               ("meander.zeta")
                               [?__77095]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78851)
                        (recur (clojure.core/next search_space__78850))
                        result__78851))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78848)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__78413
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77095 X__78413]
                     (clojure.core/let
                      [X__78415
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__78415
                       ("re")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__78403 input__77076_nth_1__ ?__77095)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78607)
                         (clojure.core/let
                          [[?__77095] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__77076_nth_0__)
                                3)
                               (clojure.core/let
                                [input__77076_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  1)
                                 input__77076_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__77076_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__77076_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__77076_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__77076_nth_0__]
                                   (clojure.core/let
                                    [?env input__77076_nth_1__]
                                    (try
                                     [{:tag :regex-with-capture,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          [?capture ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__)))))))))
                               (state__78607))
                              (state__78607)))
                            (state__78607))
                           (state__78607)))))
                       (state__78607)))))
                   (state__78607))))
                (state__78607)))
              (state__78607)))
            (state__78607))
           (state__78607))))
        (state__78607
         []
         (clojure.core/letfn
          [(def__78437
            [arg__78460 ?__77096]
            (clojure.core/letfn
             [(state__78853
               []
               (clojure.core/let
                [x__78461 "meander.zeta"]
                (if
                 (clojure.core/= ?__77096 x__78461)
                 [?__77096]
                 (state__78854))))
              (state__78854
               []
               (if
                (clojure.core/map? arg__78460)
                (clojure.core/let
                 [VAL__78462 (.valAt arg__78460 :aliases)]
                 (if
                  (clojure.core/map? VAL__78462)
                  (clojure.core/let
                   [X__78464 (clojure.core/set VAL__78462)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__78464))
                    (clojure.core/loop
                     [search_space__78855 (clojure.core/seq X__78464)]
                     (if
                      (clojure.core/seq search_space__78855)
                      (clojure.core/let
                       [elem__78465
                        (clojure.core/first search_space__78855)
                        result__78856
                        (clojure.core/let
                         [elem__78465_nth_0__
                          (clojure.core/nth elem__78465 0)
                          elem__78465_nth_1__
                          (clojure.core/nth elem__78465 1)]
                         (if
                          (clojure.core/symbol? elem__78465_nth_0__)
                          (clojure.core/let
                           [X__78467
                            (clojure.core/name elem__78465_nth_0__)]
                           (if
                            (clojure.core/= ?__77096 X__78467)
                            (if
                             (clojure.core/symbol? elem__78465_nth_1__)
                             (clojure.core/let
                              [X__78469
                               (clojure.core/name elem__78465_nth_1__)]
                              (clojure.core/case
                               X__78469
                               ("meander.zeta")
                               [?__77096]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78856)
                        (recur (clojure.core/next search_space__78855))
                        result__78856))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78853)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__78447
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77096 X__78447]
                     (clojure.core/let
                      [X__78449
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__78449
                       ("string")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__78437 input__77076_nth_1__ ?__77096)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78608)
                         (clojure.core/let
                          [[?__77096] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (clojure.core/let
                               [input__77076_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__77076_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__77076_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__77076_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__77076_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__77076_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__77076_nth_0__]
                                   (clojure.core/let
                                    [?env input__77076_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__9262__auto__
                                         (CATA__FN__77153
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           (clojure.core/let
                                            [form__9361__auto__
                                             {:context :string}]
                                            (clojure.core/merge
                                             (clojure.core/into
                                              {}
                                              ?env)
                                             form__9361__auto__))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9262__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9262__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__))))))))
                                (state__78608)))
                              (state__78608)))
                            (state__78608))
                           (state__78608)))))
                       (state__78608)))))
                   (state__78608))))
                (state__78608)))
              (state__78608)))
            (state__78608))
           (state__78608))))
        (state__78608
         []
         (clojure.core/letfn
          [(def__78471
            [arg__78494 ?__77097]
            (clojure.core/letfn
             [(state__78858
               []
               (clojure.core/let
                [x__78495 "meander.zeta"]
                (if
                 (clojure.core/= ?__77097 x__78495)
                 [?__77097]
                 (state__78859))))
              (state__78859
               []
               (if
                (clojure.core/map? arg__78494)
                (clojure.core/let
                 [VAL__78496 (.valAt arg__78494 :aliases)]
                 (if
                  (clojure.core/map? VAL__78496)
                  (clojure.core/let
                   [X__78498 (clojure.core/set VAL__78496)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__78498))
                    (clojure.core/loop
                     [search_space__78860 (clojure.core/seq X__78498)]
                     (if
                      (clojure.core/seq search_space__78860)
                      (clojure.core/let
                       [elem__78499
                        (clojure.core/first search_space__78860)
                        result__78861
                        (clojure.core/let
                         [elem__78499_nth_0__
                          (clojure.core/nth elem__78499 0)
                          elem__78499_nth_1__
                          (clojure.core/nth elem__78499 1)]
                         (if
                          (clojure.core/symbol? elem__78499_nth_0__)
                          (clojure.core/let
                           [X__78501
                            (clojure.core/name elem__78499_nth_0__)]
                           (if
                            (clojure.core/= ?__77097 X__78501)
                            (if
                             (clojure.core/symbol? elem__78499_nth_1__)
                             (clojure.core/let
                              [X__78503
                               (clojure.core/name elem__78499_nth_1__)]
                              (clojure.core/case
                               X__78503
                               ("meander.zeta")
                               [?__77097]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__78861)
                        (recur (clojure.core/next search_space__78860))
                        result__78861))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__78858)))]
          (if
           (clojure.core/vector? input__77076)
           (if
            (clojure.core/= (clojure.core/count input__77076) 2)
            (clojure.core/let
             [input__77076_nth_0__
              (clojure.core/nth input__77076 0)
              input__77076_nth_1__
              (clojure.core/nth input__77076 1)]
             (if
              (clojure.core/seq? input__77076_nth_0__)
              (clojure.core/let
               [input__77076_nth_0___l__
                (clojure.core/take 1 input__77076_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__77076_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__77076_nth_0___r__
                  (clojure.core/drop 1 input__77076_nth_0__)]
                 (clojure.core/let
                  [input__77076_nth_0___l___nth_0__
                   (clojure.core/nth input__77076_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__77076_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__78481
                     (clojure.core/namespace
                      input__77076_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__77097 X__78481]
                     (clojure.core/let
                      [X__78483
                       (clojure.core/name
                        input__77076_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__78483
                       ("symbol")
                       (clojure.core/let
                        [x__7959__auto__
                         (def__78471 input__77076_nth_1__ ?__77097)]
                        (if
                         (meander.runtime.zeta/fail? x__7959__auto__)
                         (state__78609)
                         (clojure.core/let
                          [[?__77097] x__7959__auto__]
                          (if
                           (clojure.core/vector? input__77076)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__77076)
                             2)
                            (clojure.core/let
                             [input__77076_nth_0__
                              (clojure.core/nth input__77076 0)
                              input__77076_nth_1__
                              (clojure.core/nth input__77076 1)]
                             (if
                              (clojure.core/seq? input__77076_nth_0__)
                              (clojure.core/let
                               [input__77076_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__77076_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__77076_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__77076_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__77076_nth_0__)]
                                 (clojure.core/let
                                  [?symbol-args
                                   input__77076_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__77076_nth_0__]
                                   (clojure.core/let
                                    [?env input__77076_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [CATA_RESULT__9262__auto__
                                        (CATA__FN__77153
                                         ['meander.dev.parse.zeta/make-symbol
                                          (clojure.core/into
                                           []
                                           ?symbol-args)
                                          ?form
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9262__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9262__auto__
                                         0)))]
                                     (catch
                                      java.lang.Exception
                                      e__10202__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10202__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10202__auto__))))))))
                                (state__78609)))
                              (state__78609)))
                            (state__78609))
                           (state__78609)))))
                       (state__78609)))))
                   (state__78609))))
                (state__78609)))
              (state__78609)))
            (state__78609))
           (state__78609))))
        (state__78609
         []
         (if
          (clojure.core/vector? input__77076)
          (clojure.core/letfn
           [(state__78863
             []
             (if
              (clojure.core/= (clojure.core/count input__77076) 4)
              (clojure.core/let
               [input__77076_nth_0__
                (clojure.core/nth input__77076 0)
                input__77076_nth_1__
                (clojure.core/nth input__77076 1)
                input__77076_nth_2__
                (clojure.core/nth input__77076 2)]
               (clojure.core/letfn
                [(state__78865
                  []
                  (clojure.core/case
                   input__77076_nth_0__
                   (meander.dev.parse.zeta/make-symbol)
                   (if
                    (clojure.core/vector? input__77076_nth_1__)
                    (clojure.core/case
                     input__77076_nth_1__
                     ([])
                     (clojure.core/let
                      [?form input__77076_nth_2__]
                      (try
                       [{:tag :symbol,
                         :namespace {:tag :wildcard},
                         :name {:tag :wildcard},
                         :form ?form}]
                       (catch
                        java.lang.Exception
                        e__10202__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10202__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10202__auto__)))))
                     (state__78866))
                    (state__78866))
                   (state__78866)))
                 (state__78866
                  []
                  (clojure.core/let
                   [input__77076_nth_3__
                    (clojure.core/nth input__77076 3)]
                   (clojure.core/case
                    input__77076_nth_0__
                    (meander.dev.parse.zeta/make-symbol)
                    (if
                     (clojure.core/vector? input__77076_nth_1__)
                     (clojure.core/letfn
                      [(state__78867
                        []
                        (if
                         (clojure.core/=
                          (clojure.core/count input__77076_nth_1__)
                          1)
                         (clojure.core/let
                          [input__77076_nth_1___nth_0__
                           (clojure.core/nth input__77076_nth_1__ 0)]
                          (clojure.core/let
                           [?name input__77076_nth_1___nth_0__]
                           (clojure.core/let
                            [?form input__77076_nth_2__]
                            (clojure.core/let
                             [?env input__77076_nth_3__]
                             (try
                              [{:tag :symbol,
                                :namespace {:tag :wildcard},
                                :name
                                (clojure.core/let
                                 [CATA_RESULT__9262__auto__
                                  (CATA__FN__77153 [?name ?env])]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   CATA_RESULT__9262__auto__)
                                  (throw (meander.runtime.zeta/fail))
                                  (clojure.core/nth
                                   CATA_RESULT__9262__auto__
                                   0))),
                                :form ?form}]
                              (catch
                               java.lang.Exception
                               e__10202__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10202__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10202__auto__))))))))
                         (state__78868)))
                       (state__78868
                        []
                        (if
                         (clojure.core/=
                          (clojure.core/count input__77076_nth_1__)
                          2)
                         (clojure.core/let
                          [input__77076_nth_1___nth_0__
                           (clojure.core/nth input__77076_nth_1__ 0)
                           input__77076_nth_1___nth_1__
                           (clojure.core/nth input__77076_nth_1__ 1)]
                          (clojure.core/let
                           [?namespace input__77076_nth_1___nth_0__]
                           (clojure.core/let
                            [?name input__77076_nth_1___nth_1__]
                            (clojure.core/let
                             [?form input__77076_nth_2__]
                             (clojure.core/let
                              [?env input__77076_nth_3__]
                              (try
                               [{:tag :symbol,
                                 :namespace
                                 (clojure.core/let
                                  [CATA_RESULT__9262__auto__
                                   (CATA__FN__77153 [?namespace ?env])]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    CATA_RESULT__9262__auto__)
                                   (throw (meander.runtime.zeta/fail))
                                   (clojure.core/nth
                                    CATA_RESULT__9262__auto__
                                    0))),
                                 :name
                                 (clojure.core/let
                                  [CATA_RESULT__9262__auto__
                                   (CATA__FN__77153 [?name ?env])]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    CATA_RESULT__9262__auto__)
                                   (throw (meander.runtime.zeta/fail))
                                   (clojure.core/nth
                                    CATA_RESULT__9262__auto__
                                    0))),
                                 :form ?form}]
                               (catch
                                java.lang.Exception
                                e__10202__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__10202__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__10202__auto__)))))))))
                         (state__78864)))]
                      (state__78867))
                     (state__78864))
                    (state__78864))))]
                (state__78865)))
              (state__78864)))
            (state__78864
             []
             (if
              (clojure.core/= (clojure.core/count input__77076) 2)
              (clojure.core/let
               [input__77076_nth_0__ (clojure.core/nth input__77076 0)]
               (clojure.core/letfn
                [(state__78869
                  []
                  (clojure.core/let
                   [input__77076_nth_1__
                    (clojure.core/nth input__77076 1)]
                   (clojure.core/letfn
                    [(state__78874
                      []
                      (if
                       (clojure.core/seq? input__77076_nth_0__)
                       (clojure.core/let
                        [?sequence input__77076_nth_0__]
                        (clojure.core/let
                         [?env input__77076_nth_1__]
                         (try
                          [{:tag :seq,
                            :next
                            (clojure.core/let
                             [CATA_RESULT__9262__auto__
                              (CATA__FN__77153
                               ['meander.dev.parse.zeta/parse-sequential
                                (clojure.core/into [] ?sequence)
                                (clojure.core/let
                                 [form__9361__auto__ {:context :seq}]
                                 (clojure.core/merge
                                  (clojure.core/into {} ?env)
                                  form__9361__auto__))])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9262__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9262__auto__
                               0))),
                            :form ?sequence}]
                          (catch
                           java.lang.Exception
                           e__10202__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10202__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10202__auto__))))))
                       (state__78875)))
                     (state__78875
                      []
                      (if
                       (clojure.core/map? input__77076_nth_0__)
                       (clojure.core/let
                        [?map input__77076_nth_0__]
                        (clojure.core/let
                         [?env input__77076_nth_1__]
                         (try
                          [{:tag :map,
                            :next
                            (clojure.core/let
                             [CATA_RESULT__9262__auto__
                              (CATA__FN__77153
                               ['meander.dev.parse.zeta/parse-entries
                                ?map
                                ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9262__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9262__auto__
                               0))),
                            :form ?map}]
                          (catch
                           java.lang.Exception
                           e__10202__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10202__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10202__auto__))))))
                       (state__78870)))]
                    (state__78874))))
                 (state__78870
                  []
                  (if
                   (clojure.core/symbol? input__77076_nth_0__)
                   (clojure.core/let
                    [X__78523
                     (clojure.core/namespace input__77076_nth_0__)]
                    (clojure.core/let
                     [X__78525
                      (clojure.core/name input__77076_nth_0__)]
                     (if
                      (clojure.core/string? X__78525)
                      (clojure.core/letfn
                       [(state__78876
                         []
                         (clojure.core/let
                          [ret__78526
                           (clojure.core/re-matches #"_.*" X__78525)]
                          (if
                           (clojure.core/some? ret__78526)
                           (clojure.core/let
                            [?name ret__78526]
                            (clojure.core/let
                             [?symbol input__77076_nth_0__]
                             (try
                              [{:tag :wildcard,
                                :name ?name,
                                :form ?symbol,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__10202__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10202__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10202__auto__))))))
                           (state__78877))))
                        (state__78877
                         []
                         (clojure.core/let
                          [ret__78533
                           (clojure.core/re-matches #".+#" X__78525)]
                          (if
                           (clojure.core/some? ret__78533)
                           (clojure.core/let
                            [?name ret__78533]
                            (clojure.core/let
                             [?symbol input__77076_nth_0__]
                             (try
                              [{:tag :random-symbol,
                                :name ?name,
                                :form ?symbol,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__10202__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10202__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10202__auto__))))))
                           (state__78878))))
                        (state__78878
                         []
                         (clojure.core/let
                          [ret__78540
                           (clojure.core/re-matches #"%.+" X__78525)]
                          (if
                           (clojure.core/some? ret__78540)
                           (clojure.core/let
                            [?name ret__78540]
                            (clojure.core/let
                             [?symbol input__77076_nth_0__]
                             (try
                              [{:tag :reference,
                                :name ?name,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__10202__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10202__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10202__auto__))))))
                           (state__78879))))
                        (state__78879
                         []
                         (clojure.core/let
                          [ret__78547
                           (clojure.core/re-matches #"\*.+" X__78525)]
                          (if
                           (clojure.core/some? ret__78547)
                           (clojure.core/let
                            [?name ret__78547]
                            (clojure.core/let
                             [?symbol input__77076_nth_0__]
                             (try
                              [{:tag :mutable-variable,
                                :name ?name,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__10202__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10202__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10202__auto__))))))
                           (state__78880))))
                        (state__78880
                         []
                         (clojure.core/let
                          [ret__78554
                           (clojure.core/re-matches #"\!.+" X__78525)]
                          (if
                           (clojure.core/some? ret__78554)
                           (clojure.core/let
                            [?name ret__78554]
                            (clojure.core/let
                             [?symbol input__77076_nth_0__]
                             (try
                              [{:tag :memory-variable,
                                :name ?name,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__10202__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10202__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10202__auto__))))))
                           (state__78881))))
                        (state__78881
                         []
                         (clojure.core/let
                          [ret__78561
                           (clojure.core/re-matches #"\?.+" X__78525)]
                          (if
                           (clojure.core/some? ret__78561)
                           (clojure.core/let
                            [?name ret__78561]
                            (clojure.core/let
                             [?symbol input__77076_nth_0__]
                             (try
                              [{:tag :logic-variable,
                                :name ?name,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__10202__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10202__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10202__auto__))))))
                           (state__78871))))]
                       (state__78876))
                      (state__78871))))
                   (state__78871)))
                 (state__78871
                  []
                  (if
                   (string? input__77076_nth_0__)
                   (clojure.core/let
                    [?x input__77076_nth_0__]
                    (try
                     [{:tag :literal, :type :string, :form ?x}]
                     (catch
                      java.lang.Exception
                      e__10202__auto__
                      (if
                       (meander.runtime.zeta/fail? e__10202__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__10202__auto__)))))
                   (state__78872)))
                 (state__78872
                  []
                  (if
                   (char? input__77076_nth_0__)
                   (clojure.core/let
                    [?x input__77076_nth_0__]
                    (try
                     [{:tag :literal, :type :char, :form ?x}]
                     (catch
                      java.lang.Exception
                      e__10202__auto__
                      (if
                       (meander.runtime.zeta/fail? e__10202__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__10202__auto__)))))
                   (state__78873)))
                 (state__78873
                  []
                  (clojure.core/let
                   [?x input__77076_nth_0__]
                   (try
                    [{:tag :literal, :form ?x}]
                    (catch
                     java.lang.Exception
                     e__10202__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10202__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10202__auto__))))))]
                (state__78869)))
              (state__78610)))]
           (state__78863))
          (state__78610)))
        (state__78610
         []
         (clojure.core/let
          [?x input__77076]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__10202__auto__
            (if
             (meander.runtime.zeta/fail? e__10202__auto__)
             (meander.runtime.zeta/fail)
             (throw e__10202__auto__))))))]
       (state__78572)))]
    (clojure.core/let
     [x__7959__auto__ (CATA__FN__77153 input__77076)]
     (if
      (meander.runtime.zeta/fail? x__7959__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__77157] x__7959__auto__]
       CATA_RETURN__77157))))]
  (if
   (meander.runtime.zeta/fail? ret__8139__auto__)
   nil
   ret__8139__auto__)))
