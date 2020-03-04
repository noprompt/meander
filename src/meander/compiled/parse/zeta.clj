(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__14536]
 (let*
  [ret__8121__auto__
   (clojure.core/letfn
    [(CATA__FN__14560
      [input__14536]
      (clojure.core/letfn
       [(state__15395
         []
         (if
          (clojure.core/vector? input__14536)
          (if
           (clojure.core/= (clojure.core/count input__14536) 3)
           (clojure.core/let
            [input__14536_nth_0__
             (clojure.core/nth input__14536 0)
             input__14536_nth_1__
             (clojure.core/nth input__14536 1)
             input__14536_nth_2__
             (clojure.core/nth input__14536 2)]
            (if
             (clojure.core/let
              [x__7000__auto__ input__14536_nth_0__]
              (clojure.core/case
               x__7000__auto__
               (meander.dev.parse.zeta/parse-seq
                meander.dev.parse.zeta/parse-string)
               true
               false))
             (clojure.core/letfn
              [(state__15412
                []
                (if
                 (clojure.core/vector? input__14536_nth_1__)
                 (clojure.core/case
                  input__14536_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__14536_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__10169__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10169__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10169__auto__)))))
                  (state__15413))
                 (state__15413)))
               (state__15413
                []
                (clojure.core/let
                 [?rule-name input__14536_nth_0__]
                 (if
                  (clojure.core/vector? input__14536_nth_1__)
                  (clojure.core/let
                   [n__14568
                    (clojure.core/count input__14536_nth_1__)
                    m__14569
                    (clojure.core/max 0 (clojure.core/- n__14568 2))
                    input__14536_nth_1___l__
                    (clojure.core/subvec
                     input__14536_nth_1__
                     0
                     (clojure.core/min
                      (clojure.core/count input__14536_nth_1__)
                      m__14569))
                    input__14536_nth_1___r__
                    (clojure.core/subvec
                     input__14536_nth_1__
                     m__14569)]
                   (if
                    (clojure.core/=
                     (clojure.core/count input__14536_nth_1___r__)
                     2)
                    (clojure.core/let
                     [!xs (clojure.core/vec input__14536_nth_1___l__)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14536_nth_1___r__)
                       2)
                      (clojure.core/let
                       [input__14536_nth_1___r___nth_0__
                        (clojure.core/nth input__14536_nth_1___r__ 0)
                        input__14536_nth_1___r___nth_1__
                        (clojure.core/nth input__14536_nth_1___r__ 1)]
                       (clojure.core/case
                        input__14536_nth_1___r___nth_0__
                        (:meander.zeta/as)
                        (clojure.core/let
                         [?pattern input__14536_nth_1___r___nth_1__]
                         (clojure.core/let
                          [?env input__14536_nth_2__]
                          (try
                           [(clojure.core/let
                             [!xs__counter
                              (meander.runtime.zeta/iterator !xs)]
                             {:tag :as,
                              :pattern
                              (clojure.core/let
                               [CATA_RESULT__9229__auto__
                                (CATA__FN__14560 ?pattern)]
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
                                (CATA__FN__14560
                                 [?rule-name
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
                        (state__15396)))
                      (state__15396)))
                    (state__15396)))
                  (state__15396))))]
              (state__15412))
             (state__15396)))
           (state__15396))
          (state__15396)))
        (state__15396
         []
         (clojure.core/letfn
          [(def__14574
            [arg__14609 ?ns]
            (clojure.core/letfn
             [(state__15414
               []
               (clojure.core/let
                [x__14610 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__14610)
                 (clojure.core/let [?env arg__14609] [?env ?ns])
                 (state__15415))))
              (state__15415
               []
               (if
                (clojure.core/map? arg__14609)
                (clojure.core/let
                 [VAL__14611 (.valAt arg__14609 :aliases)]
                 (if
                  (clojure.core/map? VAL__14611)
                  (clojure.core/let
                   [X__14613 (clojure.core/set VAL__14611)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14613))
                    (clojure.core/loop
                     [search_space__15416 (clojure.core/seq X__14613)]
                     (if
                      (clojure.core/seq search_space__15416)
                      (clojure.core/let
                       [elem__14614
                        (clojure.core/first search_space__15416)
                        result__15417
                        (clojure.core/let
                         [elem__14614_nth_0__
                          (clojure.core/nth elem__14614 0)
                          elem__14614_nth_1__
                          (clojure.core/nth elem__14614 1)]
                         (if
                          (clojure.core/symbol? elem__14614_nth_0__)
                          (clojure.core/let
                           [X__14616
                            (clojure.core/name elem__14614_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__14616)
                            (if
                             (clojure.core/symbol? elem__14614_nth_1__)
                             (clojure.core/let
                              [X__14618
                               (clojure.core/name elem__14614_nth_1__)]
                              (clojure.core/case
                               X__14618
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__14609]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15417)
                        (recur (clojure.core/next search_space__15416))
                        result__15417))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15414)))]
          (if
           (clojure.core/vector? input__14536)
           (if
            (clojure.core/= (clojure.core/count input__14536) 3)
            (clojure.core/let
             [input__14536_nth_0__
              (clojure.core/nth input__14536 0)
              input__14536_nth_1__
              (clojure.core/nth input__14536 1)
              input__14536_nth_2__
              (clojure.core/nth input__14536 2)]
             (if
              (clojure.core/let
               [x__7000__auto__ input__14536_nth_0__]
               (clojure.core/case
                x__7000__auto__
                (meander.dev.parse.zeta/parse-seq
                 meander.dev.parse.zeta/parse-string)
                true
                false))
              (clojure.core/let
               [?rule-name input__14536_nth_0__]
               (if
                (clojure.core/vector? input__14536_nth_1__)
                (clojure.core/loop
                 [search_space__15419
                  (meander.match.runtime.epsilon/partitions
                   2
                   input__14536_nth_1__)]
                 (if
                  (clojure.core/seq search_space__15419)
                  (clojure.core/let
                   [input__14536_nth_1___parts__
                    (clojure.core/first search_space__15419)
                    result__15420
                    (clojure.core/let
                     [input__14536_nth_1___l__
                      (clojure.core/nth input__14536_nth_1___parts__ 0)
                      input__14536_nth_1___r__
                      (clojure.core/nth
                       input__14536_nth_1___parts__
                       1)]
                     (clojure.core/let
                      [!init
                       (clojure.core/vec input__14536_nth_1___l__)]
                      (clojure.core/let
                       [input__14536_nth_1___r___l__
                        (clojure.core/subvec
                         input__14536_nth_1___r__
                         0
                         (clojure.core/min
                          (clojure.core/count input__14536_nth_1___r__)
                          2))]
                       (if
                        (clojure.core/=
                         (clojure.core/count
                          input__14536_nth_1___r___l__)
                         2)
                        (clojure.core/let
                         [input__14536_nth_1___r___r__
                          (clojure.core/subvec
                           input__14536_nth_1___r__
                           2)]
                         (clojure.core/let
                          [input__14536_nth_1___r___l___nth_0__
                           (clojure.core/nth
                            input__14536_nth_1___r___l__
                            0)
                           input__14536_nth_1___r___l___nth_1__
                           (clojure.core/nth
                            input__14536_nth_1___r___l__
                            1)]
                          (if
                           (clojure.core/symbol?
                            input__14536_nth_1___r___l___nth_0__)
                           (clojure.core/let
                            [X__14583
                             (clojure.core/namespace
                              input__14536_nth_1___r___l___nth_0__)]
                            (clojure.core/let
                             [?ns X__14583]
                             (clojure.core/let
                              [X__14585
                               (clojure.core/name
                                input__14536_nth_1___r___l___nth_0__)]
                              (if
                               (clojure.core/string? X__14585)
                               (clojure.core/let
                                [ret__14586
                                 (clojure.core/re-matches
                                  #"&(\d+)"
                                  X__14585)]
                                (if
                                 (clojure.core/some? ret__14586)
                                 (if
                                  (clojure.core/vector? ret__14586)
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count ret__14586)
                                    2)
                                   (clojure.core/let
                                    [ret__14586_nth_1__
                                     (clojure.core/nth ret__14586 1)]
                                    (clojure.core/let
                                     [?n ret__14586_nth_1__]
                                     (clojure.core/let
                                      [?pattern
                                       input__14536_nth_1___r___l___nth_1__]
                                      (clojure.core/let
                                       [?rest
                                        input__14536_nth_1___r___r__]
                                       (clojure.core/let
                                        [x__7941__auto__
                                         (def__14574
                                          input__14536_nth_2__
                                          ?ns)]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          x__7941__auto__)
                                         (meander.runtime.zeta/fail)
                                         (clojure.core/let
                                          [[?env ?ns] x__7941__auto__]
                                          (try
                                           [(clojure.core/let
                                             [!init__counter
                                              (meander.runtime.zeta/iterator
                                               !init)]
                                             (clojure.core/let
                                              [CATA_RESULT__9229__auto__
                                               (CATA__FN__14560
                                                ['meander.dev.parse.zeta/join-args
                                                 (clojure.core/let
                                                  [CATA_RESULT__9229__auto__
                                                   (CATA__FN__14560
                                                    [?rule-name
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
                                                   (CATA__FN__14560
                                                    ['meander.dev.parse.zeta/join-args
                                                     {:tag :slice,
                                                      :size
                                                      (Integer. ?n),
                                                      :pattern
                                                      (clojure.core/let
                                                       [CATA_RESULT__9229__auto__
                                                        (CATA__FN__14560
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
                                                       (CATA__FN__14560
                                                        [?rule-name
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
                                                        0)))])]
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    CATA_RESULT__9229__auto__)
                                                   (throw
                                                    (meander.runtime.zeta/fail))
                                                   (clojure.core/nth
                                                    CATA_RESULT__9229__auto__
                                                    0)))])]
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
                    (meander.runtime.zeta/fail? result__15420)
                    (recur (clojure.core/next search_space__15419))
                    result__15420))
                  (state__15397)))
                (state__15397)))
              (state__15397)))
            (state__15397))
           (state__15397))))
        (state__15397
         []
         (clojure.core/letfn
          [(def__14631
            [arg__14663 ?ns]
            (clojure.core/letfn
             [(state__15422
               []
               (clojure.core/let
                [x__14664 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__14664)
                 (clojure.core/let [?env arg__14663] [?env ?ns])
                 (state__15423))))
              (state__15423
               []
               (if
                (clojure.core/map? arg__14663)
                (clojure.core/let
                 [VAL__14665 (.valAt arg__14663 :aliases)]
                 (if
                  (clojure.core/map? VAL__14665)
                  (clojure.core/let
                   [X__14667 (clojure.core/set VAL__14665)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14667))
                    (clojure.core/loop
                     [search_space__15424 (clojure.core/seq X__14667)]
                     (if
                      (clojure.core/seq search_space__15424)
                      (clojure.core/let
                       [elem__14668
                        (clojure.core/first search_space__15424)
                        result__15425
                        (clojure.core/let
                         [elem__14668_nth_0__
                          (clojure.core/nth elem__14668 0)
                          elem__14668_nth_1__
                          (clojure.core/nth elem__14668 1)]
                         (if
                          (clojure.core/symbol? elem__14668_nth_0__)
                          (clojure.core/let
                           [X__14670
                            (clojure.core/name elem__14668_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__14670)
                            (if
                             (clojure.core/symbol? elem__14668_nth_1__)
                             (clojure.core/let
                              [X__14672
                               (clojure.core/name elem__14668_nth_1__)]
                              (clojure.core/case
                               X__14672
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__14663]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15425)
                        (recur (clojure.core/next search_space__15424))
                        result__15425))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15422)))]
          (if
           (clojure.core/vector? input__14536)
           (if
            (clojure.core/= (clojure.core/count input__14536) 3)
            (clojure.core/let
             [input__14536_nth_0__
              (clojure.core/nth input__14536 0)
              input__14536_nth_1__
              (clojure.core/nth input__14536 1)
              input__14536_nth_2__
              (clojure.core/nth input__14536 2)]
             (if
              (clojure.core/let
               [x__7000__auto__ input__14536_nth_0__]
               (clojure.core/case
                x__7000__auto__
                (meander.dev.parse.zeta/parse-seq
                 meander.dev.parse.zeta/parse-string)
                true
                false))
              (clojure.core/let
               [?rule-name input__14536_nth_0__]
               (if
                (clojure.core/vector? input__14536_nth_1__)
                (clojure.core/loop
                 [search_space__15427
                  (meander.match.runtime.epsilon/partitions
                   2
                   input__14536_nth_1__)]
                 (if
                  (clojure.core/seq search_space__15427)
                  (clojure.core/let
                   [input__14536_nth_1___parts__
                    (clojure.core/first search_space__15427)
                    result__15428
                    (clojure.core/let
                     [input__14536_nth_1___l__
                      (clojure.core/nth input__14536_nth_1___parts__ 0)
                      input__14536_nth_1___r__
                      (clojure.core/nth
                       input__14536_nth_1___parts__
                       1)]
                     (clojure.core/let
                      [!init
                       (clojure.core/vec input__14536_nth_1___l__)]
                      (clojure.core/let
                       [input__14536_nth_1___r___l__
                        (clojure.core/subvec
                         input__14536_nth_1___r__
                         0
                         (clojure.core/min
                          (clojure.core/count input__14536_nth_1___r__)
                          2))]
                       (if
                        (clojure.core/=
                         (clojure.core/count
                          input__14536_nth_1___r___l__)
                         2)
                        (clojure.core/let
                         [input__14536_nth_1___r___r__
                          (clojure.core/subvec
                           input__14536_nth_1___r__
                           2)]
                         (clojure.core/let
                          [input__14536_nth_1___r___l___nth_0__
                           (clojure.core/nth
                            input__14536_nth_1___r___l__
                            0)
                           input__14536_nth_1___r___l___nth_1__
                           (clojure.core/nth
                            input__14536_nth_1___r___l__
                            1)]
                          (if
                           (clojure.core/symbol?
                            input__14536_nth_1___r___l___nth_0__)
                           (clojure.core/let
                            [X__14640
                             (clojure.core/namespace
                              input__14536_nth_1___r___l___nth_0__)]
                            (clojure.core/let
                             [?ns X__14640]
                             (clojure.core/let
                              [X__14642
                               (clojure.core/name
                                input__14536_nth_1___r___l___nth_0__)]
                              (if
                               (clojure.core/string? X__14642)
                               (if
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__14642)
                                (clojure.core/let
                                 [?pattern
                                  input__14536_nth_1___r___l___nth_1__]
                                 (clojure.core/let
                                  [?rest input__14536_nth_1___r___r__]
                                  (clojure.core/let
                                   [x__7941__auto__
                                    (def__14631
                                     input__14536_nth_2__
                                     ?ns)]
                                   (if
                                    (meander.runtime.zeta/fail?
                                     x__7941__auto__)
                                    (meander.runtime.zeta/fail)
                                    (clojure.core/let
                                     [[?env ?ns] x__7941__auto__]
                                     (try
                                      [(clojure.core/let
                                        [!init__counter
                                         (meander.runtime.zeta/iterator
                                          !init)]
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__14560
                                           ['meander.dev.parse.zeta/join-args
                                            (clojure.core/let
                                             [CATA_RESULT__9229__auto__
                                              (CATA__FN__14560
                                               [?rule-name
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
                                              (CATA__FN__14560
                                               ['meander.dev.parse.zeta/join-args
                                                (clojure.core/let
                                                 [CATA_RESULT__9229__auto__
                                                  (CATA__FN__14560
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
                                                  (CATA__FN__14560
                                                   [?rule-name
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
                                                   0)))])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__9229__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__9229__auto__
                                               0)))])]
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
                                         e__10169__auto__)))))))))
                                (meander.runtime.zeta/fail))
                               (meander.runtime.zeta/fail)))))
                           (meander.runtime.zeta/fail))))
                        (meander.runtime.zeta/fail)))))]
                   (if
                    (meander.runtime.zeta/fail? result__15428)
                    (recur (clojure.core/next search_space__15427))
                    result__15428))
                  (state__15398)))
                (state__15398)))
              (state__15398)))
            (state__15398))
           (state__15398))))
        (state__15398
         []
         (if
          (clojure.core/vector? input__14536)
          (if
           (clojure.core/= (clojure.core/count input__14536) 3)
           (clojure.core/let
            [input__14536_nth_0__
             (clojure.core/nth input__14536 0)
             input__14536_nth_1__
             (clojure.core/nth input__14536 1)
             input__14536_nth_2__
             (clojure.core/nth input__14536 2)]
            (clojure.core/letfn
             [(state__15430
               []
               (if
                (clojure.core/let
                 [x__7000__auto__ input__14536_nth_0__]
                 (clojure.core/case
                  x__7000__auto__
                  (meander.dev.parse.zeta/parse-seq
                   meander.dev.parse.zeta/parse-string)
                  true
                  false))
                (clojure.core/let
                 [?rule-name input__14536_nth_0__]
                 (if
                  (clojure.core/vector? input__14536_nth_1__)
                  (clojure.core/letfn
                   [(state__15446
                     []
                     (clojure.core/let
                      [n__14693
                       (clojure.core/count input__14536_nth_1__)
                       m__14694
                       (clojure.core/max 0 (clojure.core/- n__14693 2))
                       input__14536_nth_1___l__
                       (clojure.core/subvec
                        input__14536_nth_1__
                        0
                        (clojure.core/min
                         (clojure.core/count input__14536_nth_1__)
                         m__14694))
                       input__14536_nth_1___r__
                       (clojure.core/subvec
                        input__14536_nth_1__
                        m__14694)]
                      (if
                       (clojure.core/=
                        (clojure.core/count input__14536_nth_1___r__)
                        2)
                       (clojure.core/let
                        [!xs
                         (clojure.core/vec input__14536_nth_1___l__)]
                        (if
                         (clojure.core/=
                          (clojure.core/count input__14536_nth_1___r__)
                          2)
                         (clojure.core/let
                          [input__14536_nth_1___r___nth_0__
                           (clojure.core/nth
                            input__14536_nth_1___r__
                            0)
                           input__14536_nth_1___r___nth_1__
                           (clojure.core/nth
                            input__14536_nth_1___r__
                            1)]
                          (if
                           (clojure.core/symbol?
                            input__14536_nth_1___r___nth_0__)
                           (clojure.core/let
                            [X__14698
                             (clojure.core/namespace
                              input__14536_nth_1___r___nth_0__)]
                            (clojure.core/let
                             [?ns X__14698]
                             (clojure.core/let
                              [X__14700
                               (clojure.core/name
                                input__14536_nth_1___r___nth_0__)]
                              (if
                               (clojure.core/string? X__14700)
                               (clojure.core/let
                                [ret__14701
                                 (clojure.core/re-matches
                                  #"&.*"
                                  X__14700)]
                                (if
                                 (clojure.core/some? ret__14701)
                                 (clojure.core/let
                                  [?name ret__14701]
                                  (clojure.core/let
                                   [?pattern
                                    input__14536_nth_1___r___nth_1__]
                                   (if
                                    (clojure.core/map?
                                     input__14536_nth_2__)
                                    (clojure.core/let
                                     [VAL__14685
                                      (.valAt
                                       input__14536_nth_2__
                                       :aliases)]
                                     (if
                                      (clojure.core/map? VAL__14685)
                                      (clojure.core/let
                                       [X__14687
                                        (clojure.core/set VAL__14685)]
                                       (if
                                        (clojure.core/<=
                                         1
                                         (clojure.core/count X__14687))
                                        (clojure.core/loop
                                         [search_space__15450
                                          (clojure.core/seq X__14687)]
                                         (if
                                          (clojure.core/seq
                                           search_space__15450)
                                          (clojure.core/let
                                           [elem__14688
                                            (clojure.core/first
                                             search_space__15450)
                                            result__15451
                                            (clojure.core/let
                                             [elem__14688_nth_0__
                                              (clojure.core/nth
                                               elem__14688
                                               0)
                                              elem__14688_nth_1__
                                              (clojure.core/nth
                                               elem__14688
                                               1)]
                                             (if
                                              (clojure.core/symbol?
                                               elem__14688_nth_0__)
                                              (clojure.core/let
                                               [X__14690
                                                (clojure.core/name
                                                 elem__14688_nth_0__)]
                                               (if
                                                (clojure.core/=
                                                 ?ns
                                                 X__14690)
                                                (if
                                                 (clojure.core/symbol?
                                                  elem__14688_nth_1__)
                                                 (clojure.core/let
                                                  [X__14692
                                                   (clojure.core/name
                                                    elem__14688_nth_1__)]
                                                  (clojure.core/case
                                                   X__14692
                                                   ("meander.zeta")
                                                   (clojure.core/let
                                                    [?env
                                                     input__14536_nth_2__]
                                                    (try
                                                     [(clojure.core/let
                                                       [!xs__counter
                                                        (meander.runtime.zeta/iterator
                                                         !xs)]
                                                       (clojure.core/let
                                                        [CATA_RESULT__9229__auto__
                                                         (CATA__FN__14560
                                                          [?rule-name
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
                                             result__15451)
                                            (recur
                                             (clojure.core/next
                                              search_space__15450))
                                            result__15451))
                                          (state__15447)))
                                        (state__15447)))
                                      (state__15447)))
                                    (state__15447))))
                                 (state__15447)))
                               (state__15447)))))
                           (state__15447)))
                         (state__15447)))
                       (state__15447))))
                    (state__15447
                     []
                     (clojure.core/loop
                      [search_space__15453
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__14536_nth_1__)]
                      (if
                       (clojure.core/seq search_space__15453)
                       (clojure.core/let
                        [input__14536_nth_1___parts__
                         (clojure.core/first search_space__15453)
                         result__15454
                         (clojure.core/let
                          [input__14536_nth_1___l__
                           (clojure.core/nth
                            input__14536_nth_1___parts__
                            0)
                           input__14536_nth_1___r__
                           (clojure.core/nth
                            input__14536_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs
                            (clojure.core/vec
                             input__14536_nth_1___l__)]
                           (clojure.core/let
                            [input__14536_nth_1___r___l__
                             (clojure.core/subvec
                              input__14536_nth_1___r__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__14536_nth_1___r__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__14536_nth_1___r___l__)
                              1)
                             (clojure.core/let
                              [input__14536_nth_1___r___r__
                               (clojure.core/subvec
                                input__14536_nth_1___r__
                                1)]
                              (if
                               (clojure.core/=
                                input__14536_nth_1___r___l__
                                ['.])
                               (clojure.core/let
                                [?rest input__14536_nth_1___r___r__]
                                (clojure.core/let
                                 [?env input__14536_nth_2__]
                                 (try
                                  [(clojure.core/let
                                    [!xs__counter
                                     (meander.runtime.zeta/iterator
                                      !xs)]
                                    (clojure.core/let
                                     [CATA_RESULT__9229__auto__
                                      (CATA__FN__14560
                                       ['meander.dev.parse.zeta/join-args
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__14560
                                           [?rule-name
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
                                          (CATA__FN__14560
                                           [?rule-name ?rest ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9229__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9229__auto__
                                           0)))])]
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
                         (meander.runtime.zeta/fail? result__15454)
                         (recur
                          (clojure.core/next search_space__15453))
                         result__15454))
                       (state__15448))))
                    (state__15448
                     []
                     (clojure.core/let
                      [input__14536_nth_1___l__
                       (clojure.core/subvec
                        input__14536_nth_1__
                        0
                        (clojure.core/min
                         (clojure.core/count input__14536_nth_1__)
                         1))]
                      (if
                       (clojure.core/=
                        (clojure.core/count input__14536_nth_1___l__)
                        1)
                       (clojure.core/let
                        [input__14536_nth_1___r__
                         (clojure.core/subvec input__14536_nth_1__ 1)]
                        (if
                         (clojure.core/=
                          input__14536_nth_1___l__
                          ['...])
                         (clojure.core/let
                          [?rest input__14536_nth_1___r__]
                          (clojure.core/let
                           [?env input__14536_nth_2__]
                           (try
                            [(clojure.core/let
                              [CATA_RESULT__9229__auto__
                               (CATA__FN__14560
                                [?rule-name ?rest ?env])]
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
                         (state__15449)))
                       (state__15449))))
                    (state__15449
                     []
                     (clojure.core/loop
                      [search_space__15456
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__14536_nth_1__)]
                      (if
                       (clojure.core/seq search_space__15456)
                       (clojure.core/let
                        [input__14536_nth_1___parts__
                         (clojure.core/first search_space__15456)
                         result__15457
                         (clojure.core/let
                          [input__14536_nth_1___l__
                           (clojure.core/nth
                            input__14536_nth_1___parts__
                            0)
                           input__14536_nth_1___r__
                           (clojure.core/nth
                            input__14536_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__8105__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__14536_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__14718]
                               (clojure.core/let
                                [input__14718_nth_0__
                                 (clojure.core/nth input__14718 0)]
                                (clojure.core/letfn
                                 [(save__14719
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__15460
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__14718_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__14718_nth_0__)
                                  (clojure.core/let
                                   [X__14721
                                    (clojure.core/namespace
                                     input__14718_nth_0__)]
                                   (clojure.core/case
                                    X__14721
                                    (nil)
                                    (clojure.core/let
                                     [X__14723
                                      (clojure.core/name
                                       input__14718_nth_0__)]
                                     (if
                                      (clojure.core/string? X__14723)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__14723)
                                       (save__14719)
                                       (f__15460))
                                      (f__15460)))
                                    (f__15460)))
                                  (f__15460)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__14536_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__14536_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__14536_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__14536_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__14536_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__14536_nth_1___r__
                                    1)]
                                  (if
                                   (clojure.core/=
                                    input__14536_nth_1___r___l__
                                    ['...])
                                   (clojure.core/let
                                    [?rest
                                     input__14536_nth_1___r___r__]
                                    (clojure.core/let
                                     [?env input__14536_nth_2__]
                                     (try
                                      [(clojure.core/let
                                        [!xs__counter
                                         (meander.runtime.zeta/iterator
                                          !xs)]
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__14560
                                           ['meander.dev.parse.zeta/star-args
                                            (clojure.core/let
                                             [CATA_RESULT__9229__auto__
                                              (CATA__FN__14560
                                               [?rule-name
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
                                              (CATA__FN__14560
                                               [?rule-name
                                                ?rest
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__9229__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__9229__auto__
                                               0)))])]
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
                              ret__8105__auto__)
                             (meander.runtime.zeta/fail)
                             ret__8105__auto__))))]
                        (if
                         (meander.runtime.zeta/fail? result__15457)
                         (recur
                          (clojure.core/next search_space__15456))
                         result__15457))
                       (state__15431))))]
                   (state__15446))
                  (state__15431)))
                (state__15431)))
              (state__15431
               []
               (if
                (clojure.core/=
                 input__14536_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__14536_nth_1__)
                 (clojure.core/loop
                  [search_space__15461
                   (meander.match.runtime.epsilon/partitions
                    2
                    input__14536_nth_1__)]
                  (if
                   (clojure.core/seq search_space__15461)
                   (clojure.core/let
                    [input__14536_nth_1___parts__
                     (clojure.core/first search_space__15461)
                     result__15462
                     (clojure.core/let
                      [input__14536_nth_1___l__
                       (clojure.core/nth
                        input__14536_nth_1___parts__
                        0)
                       input__14536_nth_1___r__
                       (clojure.core/nth
                        input__14536_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs []]
                       (clojure.core/let
                        [ret__8105__auto__
                         (meander.runtime.zeta/epsilon-run-star-1
                          input__14536_nth_1___l__
                          [!xs]
                          (clojure.core/fn
                           [[!xs] input__14731]
                           (clojure.core/let
                            [input__14731_nth_0__
                             (clojure.core/nth input__14731 0)]
                            (clojure.core/letfn
                             [(save__14732
                               []
                               (meander.runtime.zeta/fail))
                              (f__15465
                               []
                               (clojure.core/let
                                [!xs
                                 (clojure.core/conj
                                  !xs
                                  input__14731_nth_0__)]
                                [!xs]))]
                             (if
                              (clojure.core/symbol?
                               input__14731_nth_0__)
                              (clojure.core/let
                               [X__14734
                                (clojure.core/namespace
                                 input__14731_nth_0__)]
                               (clojure.core/case
                                X__14734
                                (nil)
                                (clojure.core/let
                                 [X__14736
                                  (clojure.core/name
                                   input__14731_nth_0__)]
                                 (if
                                  (clojure.core/string? X__14736)
                                  (if
                                   (clojure.core/re-matches
                                    #"\.\.(?:\.|\d+)"
                                    X__14736)
                                   (save__14732)
                                   (f__15465))
                                  (f__15465)))
                                (f__15465)))
                              (f__15465)))))
                          (clojure.core/fn
                           [[!xs]]
                           (clojure.core/let
                            [input__14536_nth_1___r___l__
                             (clojure.core/subvec
                              input__14536_nth_1___r__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__14536_nth_1___r__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__14536_nth_1___r___l__)
                              1)
                             (clojure.core/let
                              [input__14536_nth_1___r___r__
                               (clojure.core/subvec
                                input__14536_nth_1___r__
                                1)]
                              (if
                               (clojure.core/=
                                input__14536_nth_1___r___l__
                                ['...])
                               (clojure.core/let
                                [?rest input__14536_nth_1___r___r__]
                                (clojure.core/let
                                 [?env input__14536_nth_2__]
                                 (try
                                  [(clojure.core/let
                                    [!xs__counter
                                     (meander.runtime.zeta/iterator
                                      !xs)]
                                    (clojure.core/let
                                     [CATA_RESULT__9229__auto__
                                      (CATA__FN__14560
                                       ['meander.dev.parse.zeta/star-args
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__14560
                                           ['meander.dev.parse.zeta/parse-string
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
                                          (CATA__FN__14560
                                           ['meander.dev.parse.zeta/parse-string
                                            ?rest
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9229__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9229__auto__
                                           0)))])]
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
                         (meander.runtime.zeta/fail? ret__8105__auto__)
                         (meander.runtime.zeta/fail)
                         ret__8105__auto__))))]
                    (if
                     (meander.runtime.zeta/fail? result__15462)
                     (recur (clojure.core/next search_space__15461))
                     result__15462))
                   (state__15432)))
                 (state__15432))
                (state__15432)))
              (state__15432
               []
               (if
                (clojure.core/=
                 input__14536_nth_0__
                 'meander.dev.parse.zeta/star-args)
                (if
                 (clojure.core/map? input__14536_nth_1__)
                 (clojure.core/let
                  [VAL__14740 (.valAt input__14536_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__14740
                   (:cat)
                   (clojure.core/let
                    [VAL__14741
                     (.valAt input__14536_nth_1__ :sequence)]
                    (if
                     (clojure.core/vector? VAL__14741)
                     (if
                      (clojure.core/=
                       (clojure.core/count VAL__14741)
                       1)
                      (clojure.core/let
                       [VAL__14741_nth_0__
                        (clojure.core/nth VAL__14741 0)]
                       (if
                        (clojure.core/map? VAL__14741_nth_0__)
                        (clojure.core/let
                         [VAL__14746 (.valAt VAL__14741_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__14746
                          (:memory-variable)
                          (clojure.core/let
                           [?memory-variable VAL__14741_nth_0__]
                           (clojure.core/let
                            [VAL__14742
                             (.valAt input__14536_nth_1__ :next)]
                            (if
                             (clojure.core/map? VAL__14742)
                             (clojure.core/let
                              [VAL__14743 (.valAt VAL__14742 :tag)]
                              (clojure.core/case
                               VAL__14743
                               (:empty)
                               (clojure.core/let
                                [?next input__14536_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [CATA_RESULT__9229__auto__
                                    (CATA__FN__14560
                                     ['meander.dev.parse.zeta/join-args
                                      {:tag :into,
                                       :memory-variable
                                       ?memory-variable}
                                      ?next])]
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
                                   (throw e__10169__auto__)))))
                               (state__15433)))
                             (state__15433))))
                          (state__15433)))
                        (state__15433)))
                      (state__15433))
                     (state__15433)))
                   (state__15433)))
                 (state__15433))
                (state__15433)))
              (state__15433
               []
               (if
                (clojure.core/=
                 input__14536_nth_0__
                 'meander.dev.parse.zeta/star-args)
                (clojure.core/let
                 [?pattern input__14536_nth_1__]
                 (clojure.core/let
                  [?next input__14536_nth_2__]
                  (try
                   [{:tag :star, :pattern ?pattern, :next ?next}]
                   (catch
                    java.lang.Exception
                    e__10169__auto__
                    (if
                     (meander.runtime.zeta/fail? e__10169__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__10169__auto__))))))
                (state__15434)))
              (state__15434
               []
               (if
                (clojure.core/let
                 [x__7000__auto__ input__14536_nth_0__]
                 (clojure.core/case
                  x__7000__auto__
                  (meander.dev.parse.zeta/parse-seq
                   meander.dev.parse.zeta/parse-string)
                  true
                  false))
                (clojure.core/letfn
                 [(state__15466
                   []
                   (if
                    (clojure.core/vector? input__14536_nth_1__)
                    (clojure.core/let
                     [input__14536_nth_1___l__
                      (clojure.core/subvec
                       input__14536_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14536_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14536_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__14536_nth_1___r__
                        (clojure.core/subvec input__14536_nth_1__ 1)]
                       (clojure.core/let
                        [input__14536_nth_1___l___nth_0__
                         (clojure.core/nth input__14536_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__14536_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__14754
                           (clojure.core/namespace
                            input__14536_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__14754
                           (nil)
                           (clojure.core/let
                            [X__14756
                             (clojure.core/name
                              input__14536_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__14756)
                             (clojure.core/let
                              [ret__14757
                               (clojure.core/re-matches
                                #"\.\.(\d+)"
                                X__14756)]
                              (if
                               (clojure.core/some? ret__14757)
                               (if
                                (clojure.core/vector? ret__14757)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__14757)
                                  2)
                                 (clojure.core/let
                                  [ret__14757_nth_1__
                                   (clojure.core/nth ret__14757 1)]
                                  (clojure.core/let
                                   [?n ret__14757_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__14536_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__14536_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__14536_nth_2__]
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
                                         (throw
                                          e__10169__auto__)))))))))
                                 (state__15467))
                                (state__15467))
                               (state__15467)))
                             (state__15467)))
                           (state__15467)))
                         (state__15467))))
                      (state__15467)))
                    (state__15467)))
                  (state__15467
                   []
                   (clojure.core/let
                    [?rule-name input__14536_nth_0__]
                    (if
                     (clojure.core/vector? input__14536_nth_1__)
                     (clojure.core/loop
                      [search_space__15468
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__14536_nth_1__)]
                      (if
                       (clojure.core/seq search_space__15468)
                       (clojure.core/let
                        [input__14536_nth_1___parts__
                         (clojure.core/first search_space__15468)
                         result__15469
                         (clojure.core/let
                          [input__14536_nth_1___l__
                           (clojure.core/nth
                            input__14536_nth_1___parts__
                            0)
                           input__14536_nth_1___r__
                           (clojure.core/nth
                            input__14536_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__8105__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__14536_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__14773]
                               (clojure.core/let
                                [input__14773_nth_0__
                                 (clojure.core/nth input__14773 0)]
                                (clojure.core/letfn
                                 [(save__14774
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__15472
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__14773_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__14773_nth_0__)
                                  (clojure.core/let
                                   [X__14776
                                    (clojure.core/namespace
                                     input__14773_nth_0__)]
                                   (clojure.core/case
                                    X__14776
                                    (nil)
                                    (clojure.core/let
                                     [X__14778
                                      (clojure.core/name
                                       input__14773_nth_0__)]
                                     (if
                                      (clojure.core/string? X__14778)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__14778)
                                       (save__14774)
                                       (f__15472))
                                      (f__15472)))
                                    (f__15472)))
                                  (f__15472)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__14536_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__14536_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__14536_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__14536_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__14536_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__14536_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__14536_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__14536_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__14536_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__14767
                                      (clojure.core/namespace
                                       input__14536_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__14767
                                      (nil)
                                      (clojure.core/let
                                       [X__14769
                                        (clojure.core/name
                                         input__14536_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string? X__14769)
                                        (clojure.core/let
                                         [ret__14770
                                          (clojure.core/re-matches
                                           #"\.\.(\d+)"
                                           X__14769)]
                                         (if
                                          (clojure.core/some?
                                           ret__14770)
                                          (if
                                           (clojure.core/vector?
                                            ret__14770)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__14770)
                                             2)
                                            (clojure.core/let
                                             [ret__14770_nth_1__
                                              (clojure.core/nth
                                               ret__14770
                                               1)]
                                             (clojure.core/let
                                              [?n ret__14770_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__14536_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__14536_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   {:tag :plus,
                                                    :n (Integer. ?n),
                                                    :pattern
                                                    (clojure.core/let
                                                     [CATA_RESULT__9229__auto__
                                                      (CATA__FN__14560
                                                       [?rule-name
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
                                                      (CATA__FN__14560
                                                       [?rule-name
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
                                                    e__10169__auto__))))))))
                                            (meander.runtime.zeta/fail))
                                           (meander.runtime.zeta/fail))
                                          (meander.runtime.zeta/fail)))
                                        (meander.runtime.zeta/fail)))
                                      (meander.runtime.zeta/fail)))
                                    (meander.runtime.zeta/fail))))
                                 (meander.runtime.zeta/fail)))))]
                            (if
                             (meander.runtime.zeta/fail?
                              ret__8105__auto__)
                             (meander.runtime.zeta/fail)
                             ret__8105__auto__))))]
                        (if
                         (meander.runtime.zeta/fail? result__15469)
                         (recur
                          (clojure.core/next search_space__15468))
                         result__15469))
                       (state__15435)))
                     (state__15435))))]
                 (state__15466))
                (state__15435)))
              (state__15435
               []
               (if
                (clojure.core/=
                 input__14536_nth_0__
                 'meander.dev.parse.zeta/parse-seq)
                (if
                 (clojure.core/vector? input__14536_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__14536_nth_1__)]
                  (clojure.core/let
                   [?env input__14536_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__9229__auto__
                        (CATA__FN__14560
                         ['meander.dev.parse.zeta/cat-args
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__14561 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (do
                              (clojure.core/conj!
                               return__14561
                               (clojure.core/let
                                [CATA_RESULT__9229__auto__
                                 (CATA__FN__14560
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
                                  0))))
                              (recur return__14561))
                             (clojure.core/persistent!
                              return__14561))))
                          {:tag :empty}])]
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
                 (state__15436))
                (state__15436)))
              (state__15436
               []
               (if
                (clojure.core/=
                 input__14536_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__14536_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__14536_nth_1__)]
                  (clojure.core/let
                   [?env input__14536_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__9229__auto__
                        (CATA__FN__14560
                         ['meander.dev.parse.zeta/string-cat-args
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__14562 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (do
                              (clojure.core/conj!
                               return__14562
                               (clojure.core/let
                                [CATA_RESULT__9229__auto__
                                 (CATA__FN__14560
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
                                  0))))
                              (recur return__14562))
                             (clojure.core/persistent!
                              return__14562))))
                          {:tag :empty}])]
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
                 (state__15437))
                (state__15437)))
              (state__15437
               []
               (if
                (clojure.core/=
                 input__14536_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (if
                 (clojure.core/vector? input__14536_nth_1__)
                 (clojure.core/let
                  [!forms []]
                  (clojure.core/let
                   [ret__8105__auto__
                    (meander.runtime.zeta/epsilon-run-star-1
                     input__14536_nth_1__
                     [!forms]
                     (clojure.core/fn
                      [[!forms] input__14793]
                      (clojure.core/let
                       [input__14793_nth_0__
                        (clojure.core/nth input__14793 0)]
                       (if
                        (clojure.core/map? input__14793_nth_0__)
                        (clojure.core/let
                         [VAL__14794
                          (.valAt input__14793_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__14794
                          (:literal)
                          (clojure.core/let
                           [VAL__14795
                            (.valAt input__14793_nth_0__ :type)]
                           (if
                            (clojure.core/let
                             [x__7000__auto__ VAL__14795]
                             (clojure.core/case
                              x__7000__auto__
                              (:string :char)
                              true
                              false))
                            (clojure.core/let
                             [VAL__14796
                              (.valAt input__14793_nth_0__ :form)]
                             (clojure.core/let
                              [!forms
                               (clojure.core/conj !forms VAL__14796)]
                              [!forms]))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail))))
                     (clojure.core/fn
                      [[!forms]]
                      (if
                       (clojure.core/map? input__14536_nth_2__)
                       (clojure.core/let
                        [VAL__14790 (.valAt input__14536_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__14790
                         (:empty)
                         (try
                          [{:tag :literal,
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
                         (state__15438)))
                       (state__15438))))]
                   (if
                    (meander.runtime.zeta/fail? ret__8105__auto__)
                    (state__15438)
                    ret__8105__auto__)))
                 (state__15438))
                (state__15438)))
              (state__15438
               []
               (if
                (clojure.core/=
                 input__14536_nth_0__
                 'meander.dev.parse.zeta/cat-args)
                (if
                 (clojure.core/vector? input__14536_nth_1__)
                 (clojure.core/let
                  [!forms []]
                  (clojure.core/let
                   [ret__8105__auto__
                    (meander.runtime.zeta/epsilon-run-star-1
                     input__14536_nth_1__
                     [!forms]
                     (clojure.core/fn
                      [[!forms] input__14803]
                      (clojure.core/let
                       [input__14803_nth_0__
                        (clojure.core/nth input__14803 0)]
                       (if
                        (clojure.core/map? input__14803_nth_0__)
                        (clojure.core/let
                         [VAL__14804
                          (.valAt input__14803_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__14804
                          (:literal)
                          (clojure.core/let
                           [VAL__14805
                            (.valAt input__14803_nth_0__ :form)]
                           (clojure.core/let
                            [!forms
                             (clojure.core/conj !forms VAL__14805)]
                            [!forms]))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail))))
                     (clojure.core/fn
                      [[!forms]]
                      (if
                       (clojure.core/map? input__14536_nth_2__)
                       (clojure.core/let
                        [VAL__14800 (.valAt input__14536_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__14800
                         (:empty)
                         (try
                          [{:tag :literal,
                            :form (clojure.core/into [] !forms)}]
                          (catch
                           java.lang.Exception
                           e__10169__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10169__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10169__auto__))))
                         (state__15439)))
                       (state__15439))))]
                   (if
                    (meander.runtime.zeta/fail? ret__8105__auto__)
                    (state__15439)
                    ret__8105__auto__)))
                 (state__15439))
                (state__15439)))
              (state__15439
               []
               (if
                (clojure.core/=
                 input__14536_nth_0__
                 'meander.dev.parse.zeta/cat-args)
                (clojure.core/let
                 [?sequence input__14536_nth_1__]
                 (clojure.core/let
                  [?next input__14536_nth_2__]
                  (try
                   [{:tag :cat, :sequence ?sequence, :next ?next}]
                   (catch
                    java.lang.Exception
                    e__10169__auto__
                    (if
                     (meander.runtime.zeta/fail? e__10169__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__10169__auto__))))))
                (state__15440)))
              (state__15440
               []
               (if
                (clojure.core/=
                 input__14536_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (clojure.core/let
                 [?sequence input__14536_nth_1__]
                 (clojure.core/let
                  [?next input__14536_nth_2__]
                  (try
                   [{:tag :string-cat,
                     :sequence ?sequence,
                     :next ?next}]
                   (catch
                    java.lang.Exception
                    e__10169__auto__
                    (if
                     (meander.runtime.zeta/fail? e__10169__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__10169__auto__))))))
                (state__15441)))
              (state__15441
               []
               (if
                (clojure.core/=
                 input__14536_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (if
                 (clojure.core/map? input__14536_nth_1__)
                 (clojure.core/let
                  [VAL__14813 (.valAt input__14536_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__14813
                   (:cat)
                   (clojure.core/let
                    [VAL__14814
                     (.valAt input__14536_nth_1__ :sequence)]
                    (clojure.core/let
                     [?sequence VAL__14814]
                     (clojure.core/let
                      [VAL__14815 (.valAt input__14536_nth_1__ :next)]
                      (if
                       (clojure.core/map? VAL__14815)
                       (clojure.core/let
                        [VAL__14816 (.valAt VAL__14815 :tag)]
                        (clojure.core/case
                         VAL__14816
                         (:empty)
                         (clojure.core/let
                          [?right input__14536_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__9229__auto__
                              (CATA__FN__14560
                               ['meander.dev.parse.zeta/cat-args
                                ?sequence
                                ?right])]
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
                             (throw e__10169__auto__)))))
                         (state__15442)))
                       (state__15442)))))
                   (state__15442)))
                 (state__15442))
                (state__15442)))
              (state__15442
               []
               (if
                (clojure.core/=
                 input__14536_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (clojure.core/let
                 [?left input__14536_nth_1__]
                 (if
                  (clojure.core/map? input__14536_nth_2__)
                  (clojure.core/let
                   [VAL__14819 (.valAt input__14536_nth_2__ :tag)]
                   (clojure.core/case
                    VAL__14819
                    (:empty)
                    (try
                     [?left]
                     (catch
                      java.lang.Exception
                      e__10169__auto__
                      (if
                       (meander.runtime.zeta/fail? e__10169__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__10169__auto__))))
                    (state__15443)))
                  (state__15443)))
                (state__15443)))
              (state__15443
               []
               (if
                (clojure.core/=
                 input__14536_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (if
                 (clojure.core/map? input__14536_nth_1__)
                 (clojure.core/let
                  [VAL__14822 (.valAt input__14536_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__14822
                   (:empty)
                   (clojure.core/let
                    [?right input__14536_nth_2__]
                    (try
                     [?right]
                     (catch
                      java.lang.Exception
                      e__10169__auto__
                      (if
                       (meander.runtime.zeta/fail? e__10169__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__10169__auto__)))))
                   (state__15444)))
                 (state__15444))
                (state__15444)))
              (state__15444
               []
               (if
                (clojure.core/=
                 input__14536_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (clojure.core/let
                 [?left input__14536_nth_1__]
                 (clojure.core/let
                  [?right input__14536_nth_2__]
                  (try
                   [{:tag :join, :left ?left, :right ?right}]
                   (catch
                    java.lang.Exception
                    e__10169__auto__
                    (if
                     (meander.runtime.zeta/fail? e__10169__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__10169__auto__))))))
                (state__15445)))
              (state__15445
               []
               (if
                (clojure.core/=
                 input__14536_nth_0__
                 'meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__14536_nth_1__)
                 (clojure.core/let
                  [VAL__14827
                   (.valAt input__14536_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__14827]
                   (clojure.core/let
                    [X__14829
                     ((clojure.core/fn
                       [m__7007__auto__]
                       (clojure.core/dissoc
                        m__7007__auto__
                        :meander.zeta/as))
                      input__14536_nth_1__)]
                    (clojure.core/let
                     [?rest X__14829]
                     (clojure.core/letfn
                      [(save__14830 [] (state__15399))
                       (f__15475
                        []
                        (clojure.core/let
                         [?env input__14536_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern ?pattern,
                            :next
                            (clojure.core/let
                             [CATA_RESULT__9229__auto__
                              (CATA__FN__14560 [?rest ?env])]
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
                       (clojure.core/= ?rest input__14536_nth_1__)
                       (save__14830)
                       (f__15475)))))))
                 (state__15399))
                (state__15399)))]
             (state__15430)))
           (state__15399))
          (state__15399)))
        (state__15399
         []
         (clojure.core/letfn
          [(def__14833
            [arg__14866 ?ns]
            (clojure.core/letfn
             [(state__15476
               []
               (clojure.core/let
                [x__14867 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__14867)
                 (clojure.core/let [?env arg__14866] [?env ?ns])
                 (state__15477))))
              (state__15477
               []
               (if
                (clojure.core/map? arg__14866)
                (clojure.core/let
                 [VAL__14868 (.valAt arg__14866 :aliases)]
                 (if
                  (clojure.core/map? VAL__14868)
                  (clojure.core/let
                   [X__14870 (clojure.core/set VAL__14868)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14870))
                    (clojure.core/loop
                     [search_space__15478 (clojure.core/seq X__14870)]
                     (if
                      (clojure.core/seq search_space__15478)
                      (clojure.core/let
                       [elem__14871
                        (clojure.core/first search_space__15478)
                        result__15479
                        (clojure.core/let
                         [elem__14871_nth_0__
                          (clojure.core/nth elem__14871 0)
                          elem__14871_nth_1__
                          (clojure.core/nth elem__14871 1)]
                         (if
                          (clojure.core/symbol? elem__14871_nth_0__)
                          (clojure.core/let
                           [X__14873
                            (clojure.core/name elem__14871_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__14873)
                            (if
                             (clojure.core/symbol? elem__14871_nth_1__)
                             (clojure.core/let
                              [X__14875
                               (clojure.core/name elem__14871_nth_1__)]
                              (clojure.core/case
                               X__14875
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__14866]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15479)
                        (recur (clojure.core/next search_space__15478))
                        result__15479))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15476)))]
          (if
           (clojure.core/vector? input__14536)
           (if
            (clojure.core/= (clojure.core/count input__14536) 3)
            (clojure.core/let
             [input__14536_nth_0__
              (clojure.core/nth input__14536 0)
              input__14536_nth_1__
              (clojure.core/nth input__14536 1)
              input__14536_nth_2__
              (clojure.core/nth input__14536 2)]
             (if
              (clojure.core/=
               input__14536_nth_0__
               'meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__14536_nth_1__)
               (clojure.core/let
                [X__14838 (clojure.core/set input__14536_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__14838))
                 (clojure.core/loop
                  [search_space__15481 (clojure.core/seq X__14838)]
                  (if
                   (clojure.core/seq search_space__15481)
                   (clojure.core/let
                    [elem__14839
                     (clojure.core/first search_space__15481)
                     result__15482
                     (clojure.core/let
                      [elem__14839_nth_0__
                       (clojure.core/nth elem__14839 0)
                       elem__14839_nth_1__
                       (clojure.core/nth elem__14839 1)]
                      (clojure.core/let
                       [*m__14541 elem__14839_nth_0__]
                       (if
                        (clojure.core/symbol? elem__14839_nth_0__)
                        (clojure.core/let
                         [X__14841
                          (clojure.core/namespace elem__14839_nth_0__)]
                         (clojure.core/let
                          [?ns X__14841]
                          (clojure.core/let
                           [X__14843
                            (clojure.core/name elem__14839_nth_0__)]
                           (if
                            (clojure.core/string? X__14843)
                            (if
                             (clojure.core/re-matches #"&.*" X__14843)
                             (clojure.core/let
                              [?pattern elem__14839_nth_1__]
                              (clojure.core/let
                               [X__14845
                                ((clojure.core/fn
                                  [m__7007__auto__]
                                  (clojure.core/dissoc
                                   m__7007__auto__
                                   *m__14541))
                                 input__14536_nth_1__)]
                               (clojure.core/let
                                [?rest X__14845]
                                (clojure.core/let
                                 [x__7941__auto__
                                  (def__14833
                                   input__14536_nth_2__
                                   ?ns)]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   x__7941__auto__)
                                  (meander.runtime.zeta/fail)
                                  (clojure.core/let
                                   [[?env ?ns] x__7941__auto__]
                                   (try
                                    [{:tag :rest-map,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__14560
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
                                        (CATA__FN__14560
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
                     (meander.runtime.zeta/fail? result__15482)
                     (recur (clojure.core/next search_space__15481))
                     result__15482))
                   (state__15400)))
                 (state__15400)))
               (state__15400))
              (state__15400)))
            (state__15400))
           (state__15400))))
        (state__15400
         []
         (if
          (clojure.core/vector? input__14536)
          (clojure.core/letfn
           [(state__15484
             []
             (if
              (clojure.core/= (clojure.core/count input__14536) 3)
              (clojure.core/let
               [input__14536_nth_0__
                (clojure.core/nth input__14536 0)
                input__14536_nth_1__
                (clojure.core/nth input__14536 1)
                input__14536_nth_2__
                (clojure.core/nth input__14536 2)]
               (clojure.core/letfn
                [(state__15487
                  []
                  (if
                   (clojure.core/=
                    input__14536_nth_0__
                    'meander.dev.parse.zeta/parse-entries)
                   (if
                    (clojure.core/map? input__14536_nth_1__)
                    (clojure.core/let
                     [X__14889 (clojure.core/set input__14536_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__14889))
                      (clojure.core/loop
                       [search_space__15493
                        (clojure.core/seq X__14889)]
                       (if
                        (clojure.core/seq search_space__15493)
                        (clojure.core/let
                         [elem__14890
                          (clojure.core/first search_space__15493)
                          result__15494
                          (clojure.core/let
                           [elem__14890_nth_0__
                            (clojure.core/nth elem__14890 0)
                            elem__14890_nth_1__
                            (clojure.core/nth elem__14890 1)]
                           (clojure.core/let
                            [?key-pattern elem__14890_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__14890_nth_1__]
                             (clojure.core/let
                              [X__14892
                               ((clojure.core/fn
                                 [m__7007__auto__]
                                 (clojure.core/dissoc
                                  m__7007__auto__
                                  ?key-pattern))
                                input__14536_nth_1__)]
                              (clojure.core/let
                               [?rest X__14892]
                               (clojure.core/let
                                [?env input__14536_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9229__auto__
                                     (CATA__FN__14560
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
                                     (CATA__FN__14560
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
                                     (CATA__FN__14560
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
                          (meander.runtime.zeta/fail? result__15494)
                          (recur
                           (clojure.core/next search_space__15493))
                          result__15494))
                        (state__15488)))
                      (state__15488)))
                    (state__15488))
                   (state__15488)))
                 (state__15488
                  []
                  (if
                   (clojure.core/=
                    input__14536_nth_0__
                    'meander.dev.parse.zeta/parse-entries)
                   (if
                    (clojure.core/map? input__14536_nth_1__)
                    (clojure.core/let
                     [?env input__14536_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__)))))
                    (state__15489))
                   (state__15489)))
                 (state__15489
                  []
                  (if
                   (clojure.core/=
                    input__14536_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__14536_nth_1__)
                    (clojure.core/case
                     input__14536_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__14536_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__10169__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10169__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10169__auto__)))))
                     (state__15490))
                    (state__15490))
                   (state__15490)))
                 (state__15490
                  []
                  (if
                   (clojure.core/=
                    input__14536_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__14536_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14536_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__14536_nth_2__]
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
                     (state__15491))
                    (state__15491))
                   (state__15491)))
                 (state__15491
                  []
                  (if
                   (clojure.core/=
                    input__14536_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__14536_nth_1__)
                    (clojure.core/let
                     [input__14536_nth_1___l__
                      (clojure.core/subvec
                       input__14536_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14536_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14536_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__14536_nth_1___r__
                        (clojure.core/subvec input__14536_nth_1__ 2)]
                       (clojure.core/let
                        [input__14536_nth_1___l___nth_0__
                         (clojure.core/nth input__14536_nth_1___l__ 0)
                         input__14536_nth_1___l___nth_1__
                         (clojure.core/nth input__14536_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__14536_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__14906
                           (clojure.core/namespace
                            input__14536_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__14908
                            (clojure.core/name
                             input__14536_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__14908)
                            (if
                             (clojure.core/re-matches #"%.+" X__14908)
                             (clojure.core/let
                              [?symbol
                               input__14536_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__14536_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__14536_nth_1___r__]
                                (clojure.core/let
                                 [?env input__14536_nth_2__]
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
                                         (CATA__FN__14560
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
                                       (CATA__FN__14560
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
                             (state__15492))
                            (state__15492))))
                         (state__15492))))
                      (state__15492)))
                    (state__15492))
                   (state__15492)))
                 (state__15492
                  []
                  (if
                   (clojure.core/=
                    input__14536_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__14536_nth_1__)
                    (clojure.core/let
                     [input__14536_nth_1___l__
                      (clojure.core/subvec
                       input__14536_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14536_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14536_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__14536_nth_1___r__
                        (clojure.core/subvec input__14536_nth_1__ 2)]
                       (clojure.core/let
                        [input__14536_nth_1___l___nth_0__
                         (clojure.core/nth input__14536_nth_1___l__ 0)
                         input__14536_nth_1___l___nth_1__
                         (clojure.core/nth input__14536_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__14536_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__14536_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__14536_nth_1___r__]
                           (clojure.core/let
                            [?env input__14536_nth_2__]
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
                      (state__15485)))
                    (state__15485))
                   (state__15485)))]
                (state__15487)))
              (state__15485)))
            (state__15485
             []
             (if
              (clojure.core/= (clojure.core/count input__14536) 2)
              (clojure.core/let
               [input__14536_nth_0__
                (clojure.core/nth input__14536 0)
                input__14536_nth_1__
                (clojure.core/nth input__14536 1)]
               (if
                (clojure.core/vector? input__14536_nth_0__)
                (clojure.core/let
                 [?sequence input__14536_nth_0__]
                 (clojure.core/let
                  [?env input__14536_nth_1__]
                  (try
                   [(clojure.core/let
                     [CATA_RESULT__9229__auto__
                      (CATA__FN__14560
                       ['meander.dev.parse.zeta/vector-args
                        (clojure.core/let
                         [CATA_RESULT__9229__auto__
                          (CATA__FN__14560
                           ['meander.dev.parse.zeta/parse-seq
                            ?sequence
                            ?env])]
                         (if
                          (meander.runtime.zeta/fail?
                           CATA_RESULT__9229__auto__)
                          (throw (meander.runtime.zeta/fail))
                          (clojure.core/nth
                           CATA_RESULT__9229__auto__
                           0)))
                        ?sequence])]
                     (if
                      (meander.runtime.zeta/fail?
                       CATA_RESULT__9229__auto__)
                      (throw (meander.runtime.zeta/fail))
                      (clojure.core/nth CATA_RESULT__9229__auto__ 0)))]
                   (catch
                    java.lang.Exception
                    e__10169__auto__
                    (if
                     (meander.runtime.zeta/fail? e__10169__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__10169__auto__))))))
                (state__15486)))
              (state__15486)))
            (state__15486
             []
             (if
              (clojure.core/= (clojure.core/count input__14536) 3)
              (clojure.core/let
               [input__14536_nth_0__
                (clojure.core/nth input__14536 0)
                input__14536_nth_1__
                (clojure.core/nth input__14536 1)]
               (clojure.core/letfn
                [(state__15496
                  []
                  (if
                   (clojure.core/=
                    input__14536_nth_0__
                    'meander.dev.parse.zeta/vector-args)
                   (if
                    (clojure.core/map? input__14536_nth_1__)
                    (clojure.core/let
                     [VAL__14919 (.valAt input__14536_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__14919
                      (:literal)
                      (clojure.core/let
                       [?literal input__14536_nth_1__]
                       (try
                        [?literal]
                        (catch
                         java.lang.Exception
                         e__10169__auto__
                         (if
                          (meander.runtime.zeta/fail? e__10169__auto__)
                          (meander.runtime.zeta/fail)
                          (throw e__10169__auto__)))))
                      (state__15497)))
                    (state__15497))
                   (state__15497)))
                 (state__15497
                  []
                  (clojure.core/let
                   [input__14536_nth_2__
                    (clojure.core/nth input__14536 2)]
                   (if
                    (clojure.core/=
                     input__14536_nth_0__
                     'meander.dev.parse.zeta/vector-args)
                    (clojure.core/let
                     [?next input__14536_nth_1__]
                     (clojure.core/let
                      [?sequence input__14536_nth_2__]
                      (try
                       [{:tag :vector, :next ?next, :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__10169__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10169__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10169__auto__))))))
                    (state__15401))))]
                (state__15496)))
              (state__15401)))]
           (state__15484))
          (state__15401)))
        (state__15401
         []
         (clojure.core/letfn
          [(def__14924
            [arg__14954 ?ns]
            (clojure.core/letfn
             [(state__15498
               []
               (clojure.core/let
                [x__14955 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__14955)
                 (clojure.core/let [?env arg__14954] [?env ?ns])
                 (state__15499))))
              (state__15499
               []
               (if
                (clojure.core/map? arg__14954)
                (clojure.core/let
                 [VAL__14956 (.valAt arg__14954 :aliases)]
                 (if
                  (clojure.core/map? VAL__14956)
                  (clojure.core/let
                   [X__14958 (clojure.core/set VAL__14956)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14958))
                    (clojure.core/loop
                     [search_space__15500 (clojure.core/seq X__14958)]
                     (if
                      (clojure.core/seq search_space__15500)
                      (clojure.core/let
                       [elem__14959
                        (clojure.core/first search_space__15500)
                        result__15501
                        (clojure.core/let
                         [elem__14959_nth_0__
                          (clojure.core/nth elem__14959 0)
                          elem__14959_nth_1__
                          (clojure.core/nth elem__14959 1)]
                         (if
                          (clojure.core/symbol? elem__14959_nth_0__)
                          (clojure.core/let
                           [X__14961
                            (clojure.core/name elem__14959_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__14961)
                            (if
                             (clojure.core/symbol? elem__14959_nth_1__)
                             (clojure.core/let
                              [X__14963
                               (clojure.core/name elem__14959_nth_1__)]
                              (clojure.core/case
                               X__14963
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__14954]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15501)
                        (recur (clojure.core/next search_space__15500))
                        result__15501))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15498)))]
          (if
           (clojure.core/vector? input__14536)
           (if
            (clojure.core/= (clojure.core/count input__14536) 2)
            (clojure.core/let
             [input__14536_nth_0__
              (clojure.core/nth input__14536 0)
              input__14536_nth_1__
              (clojure.core/nth input__14536 1)]
             (if
              (clojure.core/seq? input__14536_nth_0__)
              (if
               (clojure.core/=
                (clojure.core/bounded-count
                 (clojure.core/inc 3)
                 input__14536_nth_0__)
                3)
               (clojure.core/let
                [input__14536_nth_0___nth_0__
                 (clojure.core/nth input__14536_nth_0__ 0)
                 input__14536_nth_0___nth_1__
                 (clojure.core/nth input__14536_nth_0__ 1)
                 input__14536_nth_0___nth_2__
                 (clojure.core/nth input__14536_nth_0__ 2)]
                (if
                 (clojure.core/symbol? input__14536_nth_0___nth_0__)
                 (clojure.core/let
                  [X__14931
                   (clojure.core/namespace
                    input__14536_nth_0___nth_0__)]
                  (clojure.core/let
                   [?ns X__14931]
                   (clojure.core/let
                    [X__14933
                     (clojure.core/name input__14536_nth_0___nth_0__)]
                    (clojure.core/case
                     X__14933
                     ("with")
                     (clojure.core/let
                      [?bindings input__14536_nth_0___nth_1__]
                      (clojure.core/let
                       [?body input__14536_nth_0___nth_2__]
                       (clojure.core/let
                        [?form input__14536_nth_0__]
                        (clojure.core/let
                         [x__7941__auto__
                          (def__14924 input__14536_nth_1__ ?ns)]
                         (if
                          (meander.runtime.zeta/fail? x__7941__auto__)
                          (state__15402)
                          (clojure.core/let
                           [[?env ?ns] x__7941__auto__]
                           (try
                            [{:tag :with,
                              :bindings
                              {:tag :with-bindings,
                               :bindings
                               (clojure.core/let
                                [CATA_RESULT__9229__auto__
                                 (CATA__FN__14560
                                  ['meander.dev.parse.zeta/parse-with-bindings
                                   ?bindings
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__9229__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__9229__auto__
                                  0)))},
                              :body
                              (clojure.core/let
                               [CATA_RESULT__9229__auto__
                                (CATA__FN__14560 [?body ?env])]
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
                              (meander.runtime.zeta/fail?
                               e__10169__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__10169__auto__))))))))))
                     (state__15402)))))
                 (state__15402)))
               (state__15402))
              (state__15402)))
            (state__15402))
           (state__15402))))
        (state__15402
         []
         (clojure.core/letfn
          [(def__14976
            [arg__15006 ?ns]
            (clojure.core/letfn
             [(state__15503
               []
               (clojure.core/let
                [x__15007 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__15007)
                 (clojure.core/let [?env arg__15006] [?env ?ns])
                 (state__15504))))
              (state__15504
               []
               (if
                (clojure.core/map? arg__15006)
                (clojure.core/let
                 [VAL__15008 (.valAt arg__15006 :aliases)]
                 (if
                  (clojure.core/map? VAL__15008)
                  (clojure.core/let
                   [X__15010 (clojure.core/set VAL__15008)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15010))
                    (clojure.core/loop
                     [search_space__15505 (clojure.core/seq X__15010)]
                     (if
                      (clojure.core/seq search_space__15505)
                      (clojure.core/let
                       [elem__15011
                        (clojure.core/first search_space__15505)
                        result__15506
                        (clojure.core/let
                         [elem__15011_nth_0__
                          (clojure.core/nth elem__15011 0)
                          elem__15011_nth_1__
                          (clojure.core/nth elem__15011 1)]
                         (if
                          (clojure.core/symbol? elem__15011_nth_0__)
                          (clojure.core/let
                           [X__15013
                            (clojure.core/name elem__15011_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__15013)
                            (if
                             (clojure.core/symbol? elem__15011_nth_1__)
                             (clojure.core/let
                              [X__15015
                               (clojure.core/name elem__15011_nth_1__)]
                              (clojure.core/case
                               X__15015
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__15006]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15506)
                        (recur (clojure.core/next search_space__15505))
                        result__15506))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15503)))]
          (if
           (clojure.core/vector? input__14536)
           (if
            (clojure.core/= (clojure.core/count input__14536) 2)
            (clojure.core/let
             [input__14536_nth_0__
              (clojure.core/nth input__14536 0)
              input__14536_nth_1__
              (clojure.core/nth input__14536 1)]
             (if
              (clojure.core/seq? input__14536_nth_0__)
              (if
               (clojure.core/=
                (clojure.core/bounded-count
                 (clojure.core/inc 3)
                 input__14536_nth_0__)
                3)
               (clojure.core/let
                [input__14536_nth_0___nth_0__
                 (clojure.core/nth input__14536_nth_0__ 0)
                 input__14536_nth_0___nth_1__
                 (clojure.core/nth input__14536_nth_0__ 1)
                 input__14536_nth_0___nth_2__
                 (clojure.core/nth input__14536_nth_0__ 2)]
                (if
                 (clojure.core/symbol? input__14536_nth_0___nth_0__)
                 (clojure.core/let
                  [X__14983
                   (clojure.core/namespace
                    input__14536_nth_0___nth_0__)]
                  (clojure.core/let
                   [?ns X__14983]
                   (clojure.core/let
                    [X__14985
                     (clojure.core/name input__14536_nth_0___nth_0__)]
                    (clojure.core/case
                     X__14985
                     ("and")
                     (clojure.core/let
                      [?left input__14536_nth_0___nth_1__]
                      (clojure.core/let
                       [?right input__14536_nth_0___nth_2__]
                       (clojure.core/let
                        [?form input__14536_nth_0__]
                        (clojure.core/let
                         [x__7941__auto__
                          (def__14976 input__14536_nth_1__ ?ns)]
                         (if
                          (meander.runtime.zeta/fail? x__7941__auto__)
                          (state__15403)
                          (clojure.core/let
                           [[?env ?ns] x__7941__auto__]
                           (try
                            [{:tag :and,
                              :left
                              (clojure.core/let
                               [CATA_RESULT__9229__auto__
                                (CATA__FN__14560 [?left ?env])]
                               (if
                                (meander.runtime.zeta/fail?
                                 CATA_RESULT__9229__auto__)
                                (throw (meander.runtime.zeta/fail))
                                (clojure.core/nth
                                 CATA_RESULT__9229__auto__
                                 0))),
                              :right
                              (clojure.core/let
                               [CATA_RESULT__9229__auto__
                                (CATA__FN__14560 [?right ?env])]
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
                              (meander.runtime.zeta/fail?
                               e__10169__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__10169__auto__))))))))))
                     (state__15403)))))
                 (state__15403)))
               (state__15403))
              (state__15403)))
            (state__15403))
           (state__15403))))
        (state__15403
         []
         (clojure.core/letfn
          [(def__15028
            [arg__15058 ?ns]
            (clojure.core/letfn
             [(state__15508
               []
               (clojure.core/let
                [x__15059 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__15059)
                 (clojure.core/let [?env arg__15058] [?env ?ns])
                 (state__15509))))
              (state__15509
               []
               (if
                (clojure.core/map? arg__15058)
                (clojure.core/let
                 [VAL__15060 (.valAt arg__15058 :aliases)]
                 (if
                  (clojure.core/map? VAL__15060)
                  (clojure.core/let
                   [X__15062 (clojure.core/set VAL__15060)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15062))
                    (clojure.core/loop
                     [search_space__15510 (clojure.core/seq X__15062)]
                     (if
                      (clojure.core/seq search_space__15510)
                      (clojure.core/let
                       [elem__15063
                        (clojure.core/first search_space__15510)
                        result__15511
                        (clojure.core/let
                         [elem__15063_nth_0__
                          (clojure.core/nth elem__15063 0)
                          elem__15063_nth_1__
                          (clojure.core/nth elem__15063 1)]
                         (if
                          (clojure.core/symbol? elem__15063_nth_0__)
                          (clojure.core/let
                           [X__15065
                            (clojure.core/name elem__15063_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__15065)
                            (if
                             (clojure.core/symbol? elem__15063_nth_1__)
                             (clojure.core/let
                              [X__15067
                               (clojure.core/name elem__15063_nth_1__)]
                              (clojure.core/case
                               X__15067
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__15058]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15511)
                        (recur (clojure.core/next search_space__15510))
                        result__15511))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15508)))]
          (if
           (clojure.core/vector? input__14536)
           (if
            (clojure.core/= (clojure.core/count input__14536) 2)
            (clojure.core/let
             [input__14536_nth_0__
              (clojure.core/nth input__14536 0)
              input__14536_nth_1__
              (clojure.core/nth input__14536 1)]
             (if
              (clojure.core/seq? input__14536_nth_0__)
              (if
               (clojure.core/=
                (clojure.core/bounded-count
                 (clojure.core/inc 2)
                 input__14536_nth_0__)
                2)
               (clojure.core/let
                [input__14536_nth_0___nth_0__
                 (clojure.core/nth input__14536_nth_0__ 0)
                 input__14536_nth_0___nth_1__
                 (clojure.core/nth input__14536_nth_0__ 1)]
                (if
                 (clojure.core/symbol? input__14536_nth_0___nth_0__)
                 (clojure.core/let
                  [X__15035
                   (clojure.core/namespace
                    input__14536_nth_0___nth_0__)]
                  (clojure.core/let
                   [?ns X__15035]
                   (clojure.core/let
                    [X__15037
                     (clojure.core/name input__14536_nth_0___nth_0__)]
                    (clojure.core/case
                     X__15037
                     ("cata")
                     (clojure.core/let
                      [?pattern input__14536_nth_0___nth_1__]
                      (clojure.core/let
                       [?form input__14536_nth_0__]
                       (clojure.core/let
                        [x__7941__auto__
                         (def__15028 input__14536_nth_1__ ?ns)]
                        (if
                         (meander.runtime.zeta/fail? x__7941__auto__)
                         (state__15404)
                         (clojure.core/let
                          [[?env ?ns] x__7941__auto__]
                          (try
                           [{:tag :cata,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__9229__auto__
                               (CATA__FN__14560 [?pattern ?env])]
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
                             (meander.runtime.zeta/fail?
                              e__10169__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10169__auto__)))))))))
                     (state__15404)))))
                 (state__15404)))
               (state__15404))
              (state__15404)))
            (state__15404))
           (state__15404))))
        (state__15404
         []
         (clojure.core/letfn
          [(def__15080
            [arg__15110 ?ns]
            (clojure.core/letfn
             [(state__15513
               []
               (clojure.core/let
                [x__15111 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__15111)
                 (clojure.core/let [?env arg__15110] [?env ?ns])
                 (state__15514))))
              (state__15514
               []
               (if
                (clojure.core/map? arg__15110)
                (clojure.core/let
                 [VAL__15112 (.valAt arg__15110 :aliases)]
                 (if
                  (clojure.core/map? VAL__15112)
                  (clojure.core/let
                   [X__15114 (clojure.core/set VAL__15112)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15114))
                    (clojure.core/loop
                     [search_space__15515 (clojure.core/seq X__15114)]
                     (if
                      (clojure.core/seq search_space__15515)
                      (clojure.core/let
                       [elem__15115
                        (clojure.core/first search_space__15515)
                        result__15516
                        (clojure.core/let
                         [elem__15115_nth_0__
                          (clojure.core/nth elem__15115 0)
                          elem__15115_nth_1__
                          (clojure.core/nth elem__15115 1)]
                         (if
                          (clojure.core/symbol? elem__15115_nth_0__)
                          (clojure.core/let
                           [X__15117
                            (clojure.core/name elem__15115_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__15117)
                            (if
                             (clojure.core/symbol? elem__15115_nth_1__)
                             (clojure.core/let
                              [X__15119
                               (clojure.core/name elem__15115_nth_1__)]
                              (clojure.core/case
                               X__15119
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__15110]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15516)
                        (recur (clojure.core/next search_space__15515))
                        result__15516))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15513)))]
          (if
           (clojure.core/vector? input__14536)
           (if
            (clojure.core/= (clojure.core/count input__14536) 2)
            (clojure.core/let
             [input__14536_nth_0__
              (clojure.core/nth input__14536 0)
              input__14536_nth_1__
              (clojure.core/nth input__14536 1)]
             (if
              (clojure.core/seq? input__14536_nth_0__)
              (if
               (clojure.core/=
                (clojure.core/bounded-count
                 (clojure.core/inc 4)
                 input__14536_nth_0__)
                4)
               (clojure.core/let
                [input__14536_nth_0___nth_0__
                 (clojure.core/nth input__14536_nth_0__ 0)
                 input__14536_nth_0___nth_1__
                 (clojure.core/nth input__14536_nth_0__ 1)
                 input__14536_nth_0___nth_2__
                 (clojure.core/nth input__14536_nth_0__ 2)
                 input__14536_nth_0___nth_3__
                 (clojure.core/nth input__14536_nth_0__ 3)]
                (if
                 (clojure.core/symbol? input__14536_nth_0___nth_0__)
                 (clojure.core/let
                  [X__15087
                   (clojure.core/namespace
                    input__14536_nth_0___nth_0__)]
                  (clojure.core/let
                   [?ns X__15087]
                   (clojure.core/let
                    [X__15089
                     (clojure.core/name input__14536_nth_0___nth_0__)]
                    (clojure.core/case
                     X__15089
                     ("fold")
                     (clojure.core/let
                      [?mutable-variable input__14536_nth_0___nth_1__]
                      (clojure.core/let
                       [?initial-value input__14536_nth_0___nth_2__]
                       (clojure.core/let
                        [?fold-function input__14536_nth_0___nth_3__]
                        (clojure.core/let
                         [?form input__14536_nth_0__]
                         (clojure.core/let
                          [x__7941__auto__
                           (def__15080 input__14536_nth_1__ ?ns)]
                          (if
                           (meander.runtime.zeta/fail? x__7941__auto__)
                           (state__15405)
                           (clojure.core/let
                            [[?env ?ns] x__7941__auto__]
                            (try
                             [(clojure.core/let
                               [CATA_RESULT__9229__auto__
                                (CATA__FN__14560
                                 ['meander.dev.parse.zeta/fold-args
                                  (clojure.core/let
                                   [CATA_RESULT__9229__auto__
                                    (CATA__FN__14560
                                     [?mutable-variable ?env])]
                                   (if
                                    (meander.runtime.zeta/fail?
                                     CATA_RESULT__9229__auto__)
                                    (throw (meander.runtime.zeta/fail))
                                    (clojure.core/nth
                                     CATA_RESULT__9229__auto__
                                     0)))
                                  (clojure.core/let
                                   [CATA_RESULT__9229__auto__
                                    (CATA__FN__14560
                                     [?initial-value ?env])]
                                   (if
                                    (meander.runtime.zeta/fail?
                                     CATA_RESULT__9229__auto__)
                                    (throw (meander.runtime.zeta/fail))
                                    (clojure.core/nth
                                     CATA_RESULT__9229__auto__
                                     0)))
                                  ?fold-function
                                  ?form])]
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
                               (throw e__10169__auto__)))))))))))
                     (state__15405)))))
                 (state__15405)))
               (state__15405))
              (state__15405)))
            (state__15405))
           (state__15405))))
        (state__15405
         []
         (if
          (clojure.core/vector? input__14536)
          (if
           (clojure.core/= (clojure.core/count input__14536) 5)
           (clojure.core/let
            [input__14536_nth_0__
             (clojure.core/nth input__14536 0)
             input__14536_nth_1__
             (clojure.core/nth input__14536 1)
             input__14536_nth_2__
             (clojure.core/nth input__14536 2)
             input__14536_nth_3__
             (clojure.core/nth input__14536 3)
             input__14536_nth_4__
             (clojure.core/nth input__14536 4)]
            (if
             (clojure.core/=
              input__14536_nth_0__
              'meander.dev.parse.zeta/fold-args)
             (if
              (clojure.core/map? input__14536_nth_1__)
              (clojure.core/let
               [VAL__15132 (.valAt input__14536_nth_1__ :tag)]
               (clojure.core/case
                VAL__15132
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__14536_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__14536_nth_2__]
                  (clojure.core/let
                   [?fold-function input__14536_nth_3__]
                   (clojure.core/let
                    [?form input__14536_nth_4__]
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
                (state__15406)))
              (state__15406))
             (state__15406)))
           (state__15406))
          (state__15406)))
        (state__15406
         []
         (clojure.core/letfn
          [(def__15135
            [arg__15165 ?ns]
            (clojure.core/letfn
             [(state__15518
               []
               (clojure.core/let
                [x__15166 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__15166)
                 (clojure.core/let [?env arg__15165] [?env ?ns])
                 (state__15519))))
              (state__15519
               []
               (if
                (clojure.core/map? arg__15165)
                (clojure.core/let
                 [VAL__15167 (.valAt arg__15165 :aliases)]
                 (if
                  (clojure.core/map? VAL__15167)
                  (clojure.core/let
                   [X__15169 (clojure.core/set VAL__15167)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15169))
                    (clojure.core/loop
                     [search_space__15520 (clojure.core/seq X__15169)]
                     (if
                      (clojure.core/seq search_space__15520)
                      (clojure.core/let
                       [elem__15170
                        (clojure.core/first search_space__15520)
                        result__15521
                        (clojure.core/let
                         [elem__15170_nth_0__
                          (clojure.core/nth elem__15170 0)
                          elem__15170_nth_1__
                          (clojure.core/nth elem__15170 1)]
                         (if
                          (clojure.core/symbol? elem__15170_nth_0__)
                          (clojure.core/let
                           [X__15172
                            (clojure.core/name elem__15170_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__15172)
                            (if
                             (clojure.core/symbol? elem__15170_nth_1__)
                             (clojure.core/let
                              [X__15174
                               (clojure.core/name elem__15170_nth_1__)]
                              (clojure.core/case
                               X__15174
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__15165]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15521)
                        (recur (clojure.core/next search_space__15520))
                        result__15521))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15518)))]
          (if
           (clojure.core/vector? input__14536)
           (if
            (clojure.core/= (clojure.core/count input__14536) 2)
            (clojure.core/let
             [input__14536_nth_0__
              (clojure.core/nth input__14536 0)
              input__14536_nth_1__
              (clojure.core/nth input__14536 1)]
             (if
              (clojure.core/seq? input__14536_nth_0__)
              (if
               (clojure.core/=
                (clojure.core/bounded-count
                 (clojure.core/inc 4)
                 input__14536_nth_0__)
                4)
               (clojure.core/let
                [input__14536_nth_0___nth_0__
                 (clojure.core/nth input__14536_nth_0__ 0)
                 input__14536_nth_0___nth_1__
                 (clojure.core/nth input__14536_nth_0__ 1)
                 input__14536_nth_0___nth_2__
                 (clojure.core/nth input__14536_nth_0__ 2)
                 input__14536_nth_0___nth_3__
                 (clojure.core/nth input__14536_nth_0__ 3)]
                (if
                 (clojure.core/symbol? input__14536_nth_0___nth_0__)
                 (clojure.core/let
                  [X__15142
                   (clojure.core/namespace
                    input__14536_nth_0___nth_0__)]
                  (clojure.core/let
                   [?ns X__15142]
                   (clojure.core/let
                    [X__15144
                     (clojure.core/name input__14536_nth_0___nth_0__)]
                    (clojure.core/case
                     X__15144
                     ("let")
                     (clojure.core/let
                      [?pattern input__14536_nth_0___nth_1__]
                      (clojure.core/let
                       [?expression input__14536_nth_0___nth_2__]
                       (clojure.core/let
                        [?next input__14536_nth_0___nth_3__]
                        (clojure.core/let
                         [?form input__14536_nth_0__]
                         (clojure.core/let
                          [x__7941__auto__
                           (def__15135 input__14536_nth_1__ ?ns)]
                          (if
                           (meander.runtime.zeta/fail? x__7941__auto__)
                           (state__15407)
                           (clojure.core/let
                            [[?env ?ns] x__7941__auto__]
                            (try
                             [{:tag :let,
                               :pattern
                               (clojure.core/let
                                [CATA_RESULT__9229__auto__
                                 (CATA__FN__14560 [?pattern ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__9229__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__9229__auto__
                                  0))),
                               :expression
                               {:tag :host-expression,
                                :form ?expression},
                               :next
                               (clojure.core/let
                                [CATA_RESULT__9229__auto__
                                 (CATA__FN__14560 [?next ?env])]
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
                               (throw e__10169__auto__)))))))))))
                     (state__15407)))))
                 (state__15407)))
               (state__15407))
              (state__15407)))
            (state__15407))
           (state__15407))))
        (state__15407
         []
         (clojure.core/letfn
          [(def__15187
            [arg__15217 ?ns]
            (clojure.core/letfn
             [(state__15523
               []
               (clojure.core/let
                [x__15218 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__15218)
                 (clojure.core/let [?env arg__15217] [?env ?ns])
                 (state__15524))))
              (state__15524
               []
               (if
                (clojure.core/map? arg__15217)
                (clojure.core/let
                 [VAL__15219 (.valAt arg__15217 :aliases)]
                 (if
                  (clojure.core/map? VAL__15219)
                  (clojure.core/let
                   [X__15221 (clojure.core/set VAL__15219)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15221))
                    (clojure.core/loop
                     [search_space__15525 (clojure.core/seq X__15221)]
                     (if
                      (clojure.core/seq search_space__15525)
                      (clojure.core/let
                       [elem__15222
                        (clojure.core/first search_space__15525)
                        result__15526
                        (clojure.core/let
                         [elem__15222_nth_0__
                          (clojure.core/nth elem__15222 0)
                          elem__15222_nth_1__
                          (clojure.core/nth elem__15222 1)]
                         (if
                          (clojure.core/symbol? elem__15222_nth_0__)
                          (clojure.core/let
                           [X__15224
                            (clojure.core/name elem__15222_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__15224)
                            (if
                             (clojure.core/symbol? elem__15222_nth_1__)
                             (clojure.core/let
                              [X__15226
                               (clojure.core/name elem__15222_nth_1__)]
                              (clojure.core/case
                               X__15226
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__15217]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15526)
                        (recur (clojure.core/next search_space__15525))
                        result__15526))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15523)))]
          (if
           (clojure.core/vector? input__14536)
           (if
            (clojure.core/= (clojure.core/count input__14536) 2)
            (clojure.core/let
             [input__14536_nth_0__
              (clojure.core/nth input__14536 0)
              input__14536_nth_1__
              (clojure.core/nth input__14536 1)]
             (if
              (clojure.core/seq? input__14536_nth_0__)
              (if
               (clojure.core/=
                (clojure.core/bounded-count
                 (clojure.core/inc 2)
                 input__14536_nth_0__)
                2)
               (clojure.core/let
                [input__14536_nth_0___nth_0__
                 (clojure.core/nth input__14536_nth_0__ 0)
                 input__14536_nth_0___nth_1__
                 (clojure.core/nth input__14536_nth_0__ 1)]
                (if
                 (clojure.core/symbol? input__14536_nth_0___nth_0__)
                 (clojure.core/let
                  [X__15194
                   (clojure.core/namespace
                    input__14536_nth_0___nth_0__)]
                  (clojure.core/let
                   [?ns X__15194]
                   (clojure.core/let
                    [X__15196
                     (clojure.core/name input__14536_nth_0___nth_0__)]
                    (clojure.core/case
                     X__15196
                     ("not")
                     (clojure.core/let
                      [?pattern input__14536_nth_0___nth_1__]
                      (clojure.core/let
                       [?form input__14536_nth_0__]
                       (clojure.core/let
                        [x__7941__auto__
                         (def__15187 input__14536_nth_1__ ?ns)]
                        (if
                         (meander.runtime.zeta/fail? x__7941__auto__)
                         (state__15408)
                         (clojure.core/let
                          [[?env ?ns] x__7941__auto__]
                          (try
                           [{:tag :not,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__9229__auto__
                               (CATA__FN__14560 [?pattern ?env])]
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
                             (meander.runtime.zeta/fail?
                              e__10169__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10169__auto__)))))))))
                     (state__15408)))))
                 (state__15408)))
               (state__15408))
              (state__15408)))
            (state__15408))
           (state__15408))))
        (state__15408
         []
         (clojure.core/letfn
          [(def__15239
            [arg__15269 ?ns]
            (clojure.core/letfn
             [(state__15528
               []
               (clojure.core/let
                [x__15270 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__15270)
                 (clojure.core/let [?env arg__15269] [?env ?ns])
                 (state__15529))))
              (state__15529
               []
               (if
                (clojure.core/map? arg__15269)
                (clojure.core/let
                 [VAL__15271 (.valAt arg__15269 :aliases)]
                 (if
                  (clojure.core/map? VAL__15271)
                  (clojure.core/let
                   [X__15273 (clojure.core/set VAL__15271)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15273))
                    (clojure.core/loop
                     [search_space__15530 (clojure.core/seq X__15273)]
                     (if
                      (clojure.core/seq search_space__15530)
                      (clojure.core/let
                       [elem__15274
                        (clojure.core/first search_space__15530)
                        result__15531
                        (clojure.core/let
                         [elem__15274_nth_0__
                          (clojure.core/nth elem__15274 0)
                          elem__15274_nth_1__
                          (clojure.core/nth elem__15274 1)]
                         (if
                          (clojure.core/symbol? elem__15274_nth_0__)
                          (clojure.core/let
                           [X__15276
                            (clojure.core/name elem__15274_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__15276)
                            (if
                             (clojure.core/symbol? elem__15274_nth_1__)
                             (clojure.core/let
                              [X__15278
                               (clojure.core/name elem__15274_nth_1__)]
                              (clojure.core/case
                               X__15278
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__15269]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15531)
                        (recur (clojure.core/next search_space__15530))
                        result__15531))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15528)))]
          (if
           (clojure.core/vector? input__14536)
           (if
            (clojure.core/= (clojure.core/count input__14536) 2)
            (clojure.core/let
             [input__14536_nth_0__
              (clojure.core/nth input__14536 0)
              input__14536_nth_1__
              (clojure.core/nth input__14536 1)]
             (if
              (clojure.core/seq? input__14536_nth_0__)
              (if
               (clojure.core/=
                (clojure.core/bounded-count
                 (clojure.core/inc 3)
                 input__14536_nth_0__)
                3)
               (clojure.core/let
                [input__14536_nth_0___nth_0__
                 (clojure.core/nth input__14536_nth_0__ 0)
                 input__14536_nth_0___nth_1__
                 (clojure.core/nth input__14536_nth_0__ 1)
                 input__14536_nth_0___nth_2__
                 (clojure.core/nth input__14536_nth_0__ 2)]
                (if
                 (clojure.core/symbol? input__14536_nth_0___nth_0__)
                 (clojure.core/let
                  [X__15246
                   (clojure.core/namespace
                    input__14536_nth_0___nth_0__)]
                  (clojure.core/let
                   [?ns X__15246]
                   (clojure.core/let
                    [X__15248
                     (clojure.core/name input__14536_nth_0___nth_0__)]
                    (clojure.core/case
                     X__15248
                     ("or")
                     (clojure.core/let
                      [?left input__14536_nth_0___nth_1__]
                      (clojure.core/let
                       [?right input__14536_nth_0___nth_2__]
                       (clojure.core/let
                        [?form input__14536_nth_0__]
                        (clojure.core/let
                         [x__7941__auto__
                          (def__15239 input__14536_nth_1__ ?ns)]
                         (if
                          (meander.runtime.zeta/fail? x__7941__auto__)
                          (state__15409)
                          (clojure.core/let
                           [[?env ?ns] x__7941__auto__]
                           (try
                            [{:tag :or,
                              :left
                              (clojure.core/let
                               [CATA_RESULT__9229__auto__
                                (CATA__FN__14560 [?left ?env])]
                               (if
                                (meander.runtime.zeta/fail?
                                 CATA_RESULT__9229__auto__)
                                (throw (meander.runtime.zeta/fail))
                                (clojure.core/nth
                                 CATA_RESULT__9229__auto__
                                 0))),
                              :right
                              (clojure.core/let
                               [CATA_RESULT__9229__auto__
                                (CATA__FN__14560 [?right ?env])]
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
                              (meander.runtime.zeta/fail?
                               e__10169__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__10169__auto__))))))))))
                     (state__15409)))))
                 (state__15409)))
               (state__15409))
              (state__15409)))
            (state__15409))
           (state__15409))))
        (state__15409
         []
         (clojure.core/letfn
          [(def__15291
            [arg__15321 ?ns]
            (clojure.core/letfn
             [(state__15533
               []
               (clojure.core/let
                [x__15322 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__15322)
                 (clojure.core/let [?env arg__15321] [?env ?ns])
                 (state__15534))))
              (state__15534
               []
               (if
                (clojure.core/map? arg__15321)
                (clojure.core/let
                 [VAL__15323 (.valAt arg__15321 :aliases)]
                 (if
                  (clojure.core/map? VAL__15323)
                  (clojure.core/let
                   [X__15325 (clojure.core/set VAL__15323)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15325))
                    (clojure.core/loop
                     [search_space__15535 (clojure.core/seq X__15325)]
                     (if
                      (clojure.core/seq search_space__15535)
                      (clojure.core/let
                       [elem__15326
                        (clojure.core/first search_space__15535)
                        result__15536
                        (clojure.core/let
                         [elem__15326_nth_0__
                          (clojure.core/nth elem__15326 0)
                          elem__15326_nth_1__
                          (clojure.core/nth elem__15326 1)]
                         (if
                          (clojure.core/symbol? elem__15326_nth_0__)
                          (clojure.core/let
                           [X__15328
                            (clojure.core/name elem__15326_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__15328)
                            (if
                             (clojure.core/symbol? elem__15326_nth_1__)
                             (clojure.core/let
                              [X__15330
                               (clojure.core/name elem__15326_nth_1__)]
                              (clojure.core/case
                               X__15330
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__15321]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15536)
                        (recur (clojure.core/next search_space__15535))
                        result__15536))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15533)))]
          (if
           (clojure.core/vector? input__14536)
           (if
            (clojure.core/= (clojure.core/count input__14536) 2)
            (clojure.core/let
             [input__14536_nth_0__
              (clojure.core/nth input__14536 0)
              input__14536_nth_1__
              (clojure.core/nth input__14536 1)]
             (if
              (clojure.core/seq? input__14536_nth_0__)
              (clojure.core/let
               [input__14536_nth_0___l__
                (clojure.core/take 1 input__14536_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14536_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14536_nth_0___r__
                  (clojure.core/drop 1 input__14536_nth_0__)]
                 (clojure.core/let
                  [input__14536_nth_0___l___nth_0__
                   (clojure.core/nth input__14536_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14536_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15298
                     (clojure.core/namespace
                      input__14536_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?ns X__15298]
                     (clojure.core/let
                      [X__15300
                       (clojure.core/name
                        input__14536_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15300
                       ("string")
                       (clojure.core/let
                        [?sequence input__14536_nth_0___r__]
                        (clojure.core/let
                         [?form input__14536_nth_0__]
                         (clojure.core/let
                          [x__7941__auto__
                           (def__15291 input__14536_nth_1__ ?ns)]
                          (if
                           (meander.runtime.zeta/fail? x__7941__auto__)
                           (state__15410)
                           (clojure.core/let
                            [[?env ?ns] x__7941__auto__]
                            (try
                             [{:tag :string,
                               :next
                               (clojure.core/let
                                [CATA_RESULT__9229__auto__
                                 (CATA__FN__14560
                                  ['meander.dev.parse.zeta/parse-string
                                   (clojure.core/into [] ?sequence)
                                   ?env])]
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
                               (meander.runtime.zeta/fail?
                                e__10169__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10169__auto__)))))))))
                       (state__15410)))))
                   (state__15410))))
                (state__15410)))
              (state__15410)))
            (state__15410))
           (state__15410))))
        (state__15410
         []
         (if
          (clojure.core/vector? input__14536)
          (if
           (clojure.core/= (clojure.core/count input__14536) 2)
           (clojure.core/let
            [input__14536_nth_0__ (clojure.core/nth input__14536 0)]
            (clojure.core/letfn
             [(state__15538
               []
               (clojure.core/let
                [input__14536_nth_1__
                 (clojure.core/nth input__14536 1)]
                (clojure.core/letfn
                 [(state__15543
                   []
                   (if
                    (clojure.core/seq? input__14536_nth_0__)
                    (clojure.core/let
                     [?sequence input__14536_nth_0__]
                     (clojure.core/let
                      [?env input__14536_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__14560
                            ['meander.dev.parse.zeta/parse-seq
                             (clojure.core/into [] ?sequence)
                             ?env])]
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
                    (state__15544)))
                  (state__15544
                   []
                   (if
                    (clojure.core/map? input__14536_nth_0__)
                    (clojure.core/let
                     [?map input__14536_nth_0__]
                     (clojure.core/let
                      [?env input__14536_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__14560
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
                    (state__15539)))]
                 (state__15543))))
              (state__15539
               []
               (if
                (clojure.core/symbol? input__14536_nth_0__)
                (clojure.core/let
                 [X__15350
                  (clojure.core/namespace input__14536_nth_0__)]
                 (clojure.core/let
                  [X__15352 (clojure.core/name input__14536_nth_0__)]
                  (if
                   (clojure.core/string? X__15352)
                   (clojure.core/letfn
                    [(state__15545
                      []
                      (clojure.core/let
                       [ret__15353
                        (clojure.core/re-matches #"_.*" X__15352)]
                       (if
                        (clojure.core/some? ret__15353)
                        (clojure.core/let
                         [?name ret__15353]
                         (clojure.core/let
                          [?symbol input__14536_nth_0__]
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
                        (state__15546))))
                     (state__15546
                      []
                      (clojure.core/let
                       [ret__15360
                        (clojure.core/re-matches #".+#" X__15352)]
                       (if
                        (clojure.core/some? ret__15360)
                        (clojure.core/let
                         [?name ret__15360]
                         (clojure.core/let
                          [?symbol input__14536_nth_0__]
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
                        (state__15547))))
                     (state__15547
                      []
                      (clojure.core/let
                       [ret__15367
                        (clojure.core/re-matches #"%.+" X__15352)]
                       (if
                        (clojure.core/some? ret__15367)
                        (clojure.core/let
                         [?name ret__15367]
                         (clojure.core/let
                          [?symbol input__14536_nth_0__]
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
                        (state__15548))))
                     (state__15548
                      []
                      (clojure.core/let
                       [ret__15374
                        (clojure.core/re-matches #"\*.+" X__15352)]
                       (if
                        (clojure.core/some? ret__15374)
                        (clojure.core/let
                         [?name ret__15374]
                         (clojure.core/let
                          [?symbol input__14536_nth_0__]
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
                        (state__15549))))
                     (state__15549
                      []
                      (clojure.core/let
                       [ret__15381
                        (clojure.core/re-matches #"\!.+" X__15352)]
                       (if
                        (clojure.core/some? ret__15381)
                        (clojure.core/let
                         [?name ret__15381]
                         (clojure.core/let
                          [?symbol input__14536_nth_0__]
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
                        (state__15550))))
                     (state__15550
                      []
                      (clojure.core/let
                       [ret__15388
                        (clojure.core/re-matches #"\?.+" X__15352)]
                       (if
                        (clojure.core/some? ret__15388)
                        (clojure.core/let
                         [?name ret__15388]
                         (clojure.core/let
                          [?symbol input__14536_nth_0__]
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
                        (state__15540))))]
                    (state__15545))
                   (state__15540))))
                (state__15540)))
              (state__15540
               []
               (if
                (string? input__14536_nth_0__)
                (clojure.core/let
                 [?x input__14536_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10169__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10169__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10169__auto__)))))
                (state__15541)))
              (state__15541
               []
               (if
                (char? input__14536_nth_0__)
                (clojure.core/let
                 [?x input__14536_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10169__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10169__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10169__auto__)))))
                (state__15542)))
              (state__15542
               []
               (clojure.core/let
                [?x input__14536_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__10169__auto__
                  (if
                   (meander.runtime.zeta/fail? e__10169__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__10169__auto__))))))]
             (state__15538)))
           (state__15411))
          (state__15411)))
        (state__15411
         []
         (clojure.core/let
          [?x input__14536]
          (try
           [?x]
           (catch
            java.lang.Exception
            e__10169__auto__
            (if
             (meander.runtime.zeta/fail? e__10169__auto__)
             (meander.runtime.zeta/fail)
             (throw e__10169__auto__))))))]
       (state__15395)))]
    (clojure.core/let
     [x__7941__auto__ (CATA__FN__14560 input__14536)]
     (if
      (meander.runtime.zeta/fail? x__7941__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__14563] x__7941__auto__]
       CATA_RETURN__14563))))]
  (if
   (meander.runtime.zeta/fail? ret__8121__auto__)
   nil
   ret__8121__auto__)))
