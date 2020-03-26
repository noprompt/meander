(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__103668]
 (let*
  [ret__12776__auto__
   (clojure.core/letfn
    [(CATA__FN__103733
      [input__103668]
      (clojure.core/letfn
       [(state__104930
         []
         (if
          (clojure.core/vector? input__103668)
          (if
           (clojure.core/= (clojure.core/count input__103668) 3)
           (clojure.core/let
            [input__103668_nth_0__
             (clojure.core/nth input__103668 0)
             input__103668_nth_1__
             (clojure.core/nth input__103668 1)
             input__103668_nth_2__
             (clojure.core/nth input__103668 2)]
            (clojure.core/case
             input__103668_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__103668_nth_1__)
              (clojure.core/letfn
               [(state__104962
                 []
                 (clojure.core/case
                  input__103668_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__103668_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__14839__auto__
                     (if
                      (meander.runtime.zeta/fail? e__14839__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__14839__auto__)))))
                  (state__104963)))
                (state__104963
                 []
                 (clojure.core/let
                  [n__103740
                   (clojure.core/count input__103668_nth_1__)
                   m__103741
                   (clojure.core/max 0 (clojure.core/- n__103740 2))
                   input__103668_nth_1___l__
                   (clojure.core/subvec
                    input__103668_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__103668_nth_1__)
                     m__103741))
                   input__103668_nth_1___r__
                   (clojure.core/subvec
                    input__103668_nth_1__
                    m__103741)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__103668_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__103668_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__103668_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__103668_nth_1___r___nth_0__
                       (clojure.core/nth input__103668_nth_1___r__ 0)
                       input__103668_nth_1___r___nth_1__
                       (clojure.core/nth input__103668_nth_1___r__ 1)]
                      (clojure.core/case
                       input__103668_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__103668_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__103668_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__13899__auto__
                               (CATA__FN__103733 [?pattern ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__13899__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__13899__auto__
                                0))),
                             :next
                             (clojure.core/let
                              [CATA_RESULT__13899__auto__
                               (CATA__FN__103733
                                ['meander.dev.parse.zeta/parse-sequential
                                 (clojure.core/into
                                  []
                                  (clojure.core/vec
                                   (clojure.core/iterator-seq
                                    !xs__counter)))
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__13899__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__13899__auto__
                                0)))})]
                          (catch
                           java.lang.Exception
                           e__14839__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__14839__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__14839__auto__))))))
                       (state__104931)))
                     (state__104931)))
                   (state__104931))))]
               (state__104962))
              (state__104931))
             (state__104931)))
           (state__104931))
          (state__104931)))
        (state__104931
         []
         (clojure.core/letfn
          [(def__103746
            [arg__103781 ?ns]
            (clojure.core/letfn
             [(state__104964
               []
               (clojure.core/let
                [x__103782 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__103782)
                 (clojure.core/let [?env arg__103781] [?env ?ns])
                 (state__104965))))
              (state__104965
               []
               (if
                (clojure.core/map? arg__103781)
                (clojure.core/let
                 [VAL__103783 (.valAt arg__103781 :aliases)]
                 (if
                  (clojure.core/map? VAL__103783)
                  (clojure.core/let
                   [X__103785 (clojure.core/set VAL__103783)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__103785))
                    (clojure.core/loop
                     [search_space__104966
                      (clojure.core/seq X__103785)]
                     (if
                      (clojure.core/seq search_space__104966)
                      (clojure.core/let
                       [elem__103786
                        (clojure.core/first search_space__104966)
                        result__104967
                        (clojure.core/let
                         [elem__103786_nth_0__
                          (clojure.core/nth elem__103786 0)
                          elem__103786_nth_1__
                          (clojure.core/nth elem__103786 1)]
                         (if
                          (clojure.core/symbol? elem__103786_nth_0__)
                          (clojure.core/let
                           [X__103788
                            (clojure.core/name elem__103786_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__103788)
                            (if
                             (clojure.core/symbol?
                              elem__103786_nth_1__)
                             (clojure.core/let
                              [X__103790
                               (clojure.core/name
                                elem__103786_nth_1__)]
                              (clojure.core/case
                               X__103790
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__103781]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__104967)
                        (recur
                         (clojure.core/next search_space__104966))
                        result__104967))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__104964)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 3)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)
              input__103668_nth_2__
              (clojure.core/nth input__103668 2)]
             (clojure.core/case
              input__103668_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__103668_nth_1__)
               (clojure.core/loop
                [search_space__104969
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__103668_nth_1__)]
                (if
                 (clojure.core/seq search_space__104969)
                 (clojure.core/let
                  [input__103668_nth_1___parts__
                   (clojure.core/first search_space__104969)
                   result__104970
                   (clojure.core/let
                    [input__103668_nth_1___l__
                     (clojure.core/nth input__103668_nth_1___parts__ 0)
                     input__103668_nth_1___r__
                     (clojure.core/nth
                      input__103668_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__103668_nth_1___l__)]
                     (clojure.core/let
                      [input__103668_nth_1___r___l__
                       (clojure.core/subvec
                        input__103668_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__103668_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__103668_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__103668_nth_1___r___r__
                         (clojure.core/subvec
                          input__103668_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__103668_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__103668_nth_1___r___l__
                           0)
                          input__103668_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__103668_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__103668_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__103755
                            (clojure.core/namespace
                             input__103668_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__103755]
                            (clojure.core/let
                             [X__103757
                              (clojure.core/name
                               input__103668_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__103757)
                              (clojure.core/let
                               [ret__103758
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__103757)]
                               (if
                                (clojure.core/some? ret__103758)
                                (if
                                 (clojure.core/vector? ret__103758)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__103758)
                                   2)
                                  (clojure.core/let
                                   [ret__103758_nth_1__
                                    (clojure.core/nth ret__103758 1)]
                                   (clojure.core/let
                                    [?n ret__103758_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__103668_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__103668_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__12596__auto__
                                        (def__103746
                                         input__103668_nth_2__
                                         ?ns)]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         x__12596__auto__)
                                        (meander.runtime.zeta/fail)
                                        (clojure.core/let
                                         [[?env ?ns] x__12596__auto__]
                                         (try
                                          [(clojure.core/let
                                            [!init__counter
                                             (meander.runtime.zeta/iterator
                                              !init)]
                                            (clojure.core/let
                                             [CATA_RESULT__13899__auto__
                                              (CATA__FN__103733
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__103733
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !init__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0)))
                                                (clojure.core/let
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__103733
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__13899__auto__
                                                       (CATA__FN__103733
                                                        [?pattern
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        CATA_RESULT__13899__auto__)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        CATA_RESULT__13899__auto__
                                                        0)))}
                                                    (clojure.core/let
                                                     [CATA_RESULT__13899__auto__
                                                      (CATA__FN__103733
                                                       ['meander.dev.parse.zeta/parse-sequential
                                                        (clojure.core/into
                                                         []
                                                         ?rest)
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__13899__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__13899__auto__
                                                       0)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0)))
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__13899__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__13899__auto__
                                               0))))]
                                          (catch
                                           java.lang.Exception
                                           e__14839__auto__
                                           (if
                                            (meander.runtime.zeta/fail?
                                             e__14839__auto__)
                                            (meander.runtime.zeta/fail)
                                            (throw
                                             e__14839__auto__)))))))))))
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__104970)
                   (recur (clojure.core/next search_space__104969))
                   result__104970))
                 (state__104932)))
               (state__104932))
              (state__104932)))
            (state__104932))
           (state__104932))))
        (state__104932
         []
         (clojure.core/letfn
          [(def__103803
            [arg__103835 ?ns]
            (clojure.core/letfn
             [(state__104972
               []
               (clojure.core/let
                [x__103836 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__103836)
                 (clojure.core/let [?env arg__103835] [?env ?ns])
                 (state__104973))))
              (state__104973
               []
               (if
                (clojure.core/map? arg__103835)
                (clojure.core/let
                 [VAL__103837 (.valAt arg__103835 :aliases)]
                 (if
                  (clojure.core/map? VAL__103837)
                  (clojure.core/let
                   [X__103839 (clojure.core/set VAL__103837)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__103839))
                    (clojure.core/loop
                     [search_space__104974
                      (clojure.core/seq X__103839)]
                     (if
                      (clojure.core/seq search_space__104974)
                      (clojure.core/let
                       [elem__103840
                        (clojure.core/first search_space__104974)
                        result__104975
                        (clojure.core/let
                         [elem__103840_nth_0__
                          (clojure.core/nth elem__103840 0)
                          elem__103840_nth_1__
                          (clojure.core/nth elem__103840 1)]
                         (if
                          (clojure.core/symbol? elem__103840_nth_0__)
                          (clojure.core/let
                           [X__103842
                            (clojure.core/name elem__103840_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__103842)
                            (if
                             (clojure.core/symbol?
                              elem__103840_nth_1__)
                             (clojure.core/let
                              [X__103844
                               (clojure.core/name
                                elem__103840_nth_1__)]
                              (clojure.core/case
                               X__103844
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__103835]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__104975)
                        (recur
                         (clojure.core/next search_space__104974))
                        result__104975))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__104972)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 3)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)
              input__103668_nth_2__
              (clojure.core/nth input__103668 2)]
             (clojure.core/case
              input__103668_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__103668_nth_1__)
               (clojure.core/loop
                [search_space__104977
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__103668_nth_1__)]
                (if
                 (clojure.core/seq search_space__104977)
                 (clojure.core/let
                  [input__103668_nth_1___parts__
                   (clojure.core/first search_space__104977)
                   result__104978
                   (clojure.core/let
                    [input__103668_nth_1___l__
                     (clojure.core/nth input__103668_nth_1___parts__ 0)
                     input__103668_nth_1___r__
                     (clojure.core/nth
                      input__103668_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__103668_nth_1___l__)]
                     (clojure.core/let
                      [input__103668_nth_1___r___l__
                       (clojure.core/subvec
                        input__103668_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__103668_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__103668_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__103668_nth_1___r___r__
                         (clojure.core/subvec
                          input__103668_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__103668_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__103668_nth_1___r___l__
                           0)
                          input__103668_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__103668_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__103668_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__103812
                            (clojure.core/namespace
                             input__103668_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__103812]
                            (clojure.core/let
                             [X__103814
                              (clojure.core/name
                               input__103668_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__103814)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__103814)
                               (clojure.core/let
                                [?pattern
                                 input__103668_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__103668_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__12596__auto__
                                   (def__103803
                                    input__103668_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__12596__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__12596__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__13899__auto__
                                             (CATA__FN__103733
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__13899__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__13899__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__13899__auto__
                                             (CATA__FN__103733
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__13899__auto__
                                                 (CATA__FN__103733
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__13899__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__13899__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__13899__auto__
                                                 (CATA__FN__103733
                                                  ['meander.dev.parse.zeta/parse-sequential
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__13899__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__13899__auto__
                                                  0)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__13899__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__13899__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__104978)
                   (recur (clojure.core/next search_space__104977))
                   result__104978))
                 (state__104933)))
               (state__104933))
              (state__104933)))
            (state__104933))
           (state__104933))))
        (state__104933
         []
         (if
          (clojure.core/vector? input__103668)
          (clojure.core/letfn
           [(state__104980
             []
             (if
              (clojure.core/= (clojure.core/count input__103668) 3)
              (clojure.core/let
               [input__103668_nth_0__
                (clojure.core/nth input__103668 0)
                input__103668_nth_1__
                (clojure.core/nth input__103668 1)
                input__103668_nth_2__
                (clojure.core/nth input__103668 2)]
               (clojure.core/case
                input__103668_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__103668_nth_1__)
                 (clojure.core/letfn
                  [(state__104983
                    []
                    (clojure.core/let
                     [n__103865
                      (clojure.core/count input__103668_nth_1__)
                      m__103866
                      (clojure.core/max 0 (clojure.core/- n__103865 2))
                      input__103668_nth_1___l__
                      (clojure.core/subvec
                       input__103668_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__103668_nth_1__)
                        m__103866))
                      input__103668_nth_1___r__
                      (clojure.core/subvec
                       input__103668_nth_1__
                       m__103866)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__103668_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__103668_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__103668_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__103668_nth_1___r___nth_0__
                          (clojure.core/nth
                           input__103668_nth_1___r__
                           0)
                          input__103668_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__103668_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__103668_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__103870
                            (clojure.core/namespace
                             input__103668_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__103870]
                            (clojure.core/let
                             [X__103872
                              (clojure.core/name
                               input__103668_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__103872)
                              (clojure.core/let
                               [ret__103873
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__103872)]
                               (if
                                (clojure.core/some? ret__103873)
                                (clojure.core/let
                                 [?name ret__103873]
                                 (clojure.core/let
                                  [?pattern
                                   input__103668_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__103668_nth_2__)
                                   (clojure.core/let
                                    [VAL__103857
                                     (.valAt
                                      input__103668_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__103857)
                                     (clojure.core/let
                                      [X__103859
                                       (clojure.core/set VAL__103857)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__103859))
                                       (clojure.core/loop
                                        [search_space__104987
                                         (clojure.core/seq X__103859)]
                                        (if
                                         (clojure.core/seq
                                          search_space__104987)
                                         (clojure.core/let
                                          [elem__103860
                                           (clojure.core/first
                                            search_space__104987)
                                           result__104988
                                           (clojure.core/let
                                            [elem__103860_nth_0__
                                             (clojure.core/nth
                                              elem__103860
                                              0)
                                             elem__103860_nth_1__
                                             (clojure.core/nth
                                              elem__103860
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__103860_nth_0__)
                                             (clojure.core/let
                                              [X__103862
                                               (clojure.core/name
                                                elem__103860_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__103862)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__103860_nth_1__)
                                                (clojure.core/let
                                                 [X__103864
                                                  (clojure.core/name
                                                   elem__103860_nth_1__)]
                                                 (clojure.core/case
                                                  X__103864
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__103668_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__13899__auto__
                                                        (CATA__FN__103733
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
                                                         CATA_RESULT__13899__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__13899__auto__
                                                         0))))]
                                                    (catch
                                                     java.lang.Exception
                                                     e__14839__auto__
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       e__14839__auto__)
                                                      (meander.runtime.zeta/fail)
                                                      (throw
                                                       e__14839__auto__)))))
                                                  (meander.runtime.zeta/fail)))
                                                (meander.runtime.zeta/fail))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail)))]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            result__104988)
                                           (recur
                                            (clojure.core/next
                                             search_space__104987))
                                           result__104988))
                                         (state__104984)))
                                       (state__104984)))
                                     (state__104984)))
                                   (state__104984))))
                                (state__104984)))
                              (state__104984)))))
                          (state__104984)))
                        (state__104984)))
                      (state__104984))))
                   (state__104984
                    []
                    (clojure.core/loop
                     [search_space__104990
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__103668_nth_1__)]
                     (if
                      (clojure.core/seq search_space__104990)
                      (clojure.core/let
                       [input__103668_nth_1___parts__
                        (clojure.core/first search_space__104990)
                        result__104991
                        (clojure.core/let
                         [input__103668_nth_1___l__
                          (clojure.core/nth
                           input__103668_nth_1___parts__
                           0)
                          input__103668_nth_1___r__
                          (clojure.core/nth
                           input__103668_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec
                            input__103668_nth_1___l__)]
                          (clojure.core/let
                           [input__103668_nth_1___r___l__
                            (clojure.core/subvec
                             input__103668_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__103668_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__103668_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__103668_nth_1___r___r__
                              (clojure.core/subvec
                               input__103668_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__103668_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__103668_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__103668_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__13899__auto__
                                     (CATA__FN__103733
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            (clojure.core/vec
                                             (clojure.core/iterator-seq
                                              !xs__counter)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0)))
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          ['meander.dev.parse.zeta/parse-sequential
                                           ?rest
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0)))
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__13899__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__13899__auto__
                                      0))))]
                                 (catch
                                  java.lang.Exception
                                  e__14839__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__14839__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__14839__auto__))))))
                              (meander.runtime.zeta/fail)))
                            (meander.runtime.zeta/fail)))))]
                       (if
                        (meander.runtime.zeta/fail? result__104991)
                        (recur
                         (clojure.core/next search_space__104990))
                        result__104991))
                      (state__104985))))
                   (state__104985
                    []
                    (clojure.core/let
                     [input__103668_nth_1___l__
                      (clojure.core/subvec
                       input__103668_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__103668_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__103668_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__103668_nth_1___r__
                        (clojure.core/subvec input__103668_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__103668_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__103668_nth_1___r__]
                         (clojure.core/let
                          [?env input__103668_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__13899__auto__
                              (CATA__FN__103733
                               ['meander.dev.parse.zeta/parse-sequential
                                ?rest
                                ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__13899__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__13899__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__14839__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__14839__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__14839__auto__))))))
                        (state__104986)))
                      (state__104986))))
                   (state__104986
                    []
                    (clojure.core/loop
                     [search_space__104993
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__103668_nth_1__)]
                     (if
                      (clojure.core/seq search_space__104993)
                      (clojure.core/let
                       [input__103668_nth_1___parts__
                        (clojure.core/first search_space__104993)
                        result__104994
                        (clojure.core/let
                         [input__103668_nth_1___l__
                          (clojure.core/nth
                           input__103668_nth_1___parts__
                           0)
                          input__103668_nth_1___r__
                          (clojure.core/nth
                           input__103668_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__12760__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__103668_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__103890]
                              (clojure.core/let
                               [input__103890_nth_0__
                                (clojure.core/nth input__103890 0)]
                               (clojure.core/letfn
                                [(save__103891
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__104997
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__103890_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__103890_nth_0__)
                                 (clojure.core/let
                                  [X__103893
                                   (clojure.core/namespace
                                    input__103890_nth_0__)]
                                  (clojure.core/case
                                   X__103893
                                   (nil)
                                   (clojure.core/let
                                    [X__103895
                                     (clojure.core/name
                                      input__103890_nth_0__)]
                                    (if
                                     (clojure.core/string? X__103895)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__103895)
                                      (save__103891)
                                      (f__104997))
                                     (f__104997)))
                                   (f__104997)))
                                 (f__104997)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__103668_nth_1___r___l__
                                (clojure.core/subvec
                                 input__103668_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__103668_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__103668_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__103668_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__103668_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__103668_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest
                                    input__103668_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__103668_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__13899__auto__
                                             (CATA__FN__103733
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !xs__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__13899__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__13899__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__13899__auto__
                                             (CATA__FN__103733
                                              ['meander.dev.parse.zeta/parse-sequential
                                               ?rest
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__13899__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__13899__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__))))))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))))]
                           (if
                            (meander.runtime.zeta/fail?
                             ret__12760__auto__)
                            (meander.runtime.zeta/fail)
                            ret__12760__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__104994)
                        (recur
                         (clojure.core/next search_space__104993))
                        result__104994))
                      (state__104981))))]
                  (state__104983))
                 (state__104981))
                (state__104981)))
              (state__104981)))
            (state__104981
             []
             (if
              (clojure.core/= (clojure.core/count input__103668) 4)
              (clojure.core/let
               [input__103668_nth_0__
                (clojure.core/nth input__103668 0)
                input__103668_nth_1__
                (clojure.core/nth input__103668 1)
                input__103668_nth_2__
                (clojure.core/nth input__103668 2)]
               (clojure.core/letfn
                [(state__104998
                  []
                  (clojure.core/let
                   [input__103668_nth_3__
                    (clojure.core/nth input__103668 3)]
                   (clojure.core/case
                    input__103668_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__105000
                       []
                       (if
                        (clojure.core/map? input__103668_nth_1__)
                        (clojure.core/let
                         [VAL__103899
                          (.valAt input__103668_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__103899
                          (:cat)
                          (clojure.core/let
                           [VAL__103900
                            (.valAt input__103668_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__103900)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__103900)
                              1)
                             (clojure.core/let
                              [VAL__103900_nth_0__
                               (clojure.core/nth VAL__103900 0)]
                              (if
                               (clojure.core/map? VAL__103900_nth_0__)
                               (clojure.core/let
                                [VAL__103905
                                 (.valAt VAL__103900_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__103905
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable
                                   VAL__103900_nth_0__]
                                  (clojure.core/let
                                   [VAL__103901
                                    (.valAt
                                     input__103668_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__103901)
                                    (clojure.core/let
                                     [VAL__103902
                                      (.valAt VAL__103901 :tag)]
                                     (clojure.core/case
                                      VAL__103902
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__103668_nth_2__]
                                       (clojure.core/let
                                        [?env input__103668_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__13899__auto__
                                            (CATA__FN__103733
                                             ['meander.dev.parse.zeta/make-join
                                              {:tag :into,
                                               :memory-variable
                                               ?memory-variable}
                                              ?next
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__13899__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__13899__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__14839__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__14839__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__14839__auto__))))))
                                      (state__105001)))
                                    (state__105001))))
                                 (state__105001)))
                               (state__105001)))
                             (state__105001))
                            (state__105001)))
                          (state__105001)))
                        (state__105001)))
                      (state__105001
                       []
                       (clojure.core/let
                        [?pattern input__103668_nth_1__]
                        (clojure.core/let
                         [?next input__103668_nth_2__]
                         (if
                          (clojure.core/map? input__103668_nth_3__)
                          (clojure.core/let
                           [VAL__103908
                            (.valAt input__103668_nth_3__ :context)]
                           (clojure.core/case
                            VAL__103908
                            (:string)
                            (try
                             [{:tag :string-star,
                               :greedy? false,
                               :pattern ?pattern,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__14839__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__14839__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__14839__auto__))))
                            (state__104999)))
                          (state__104999)))))]
                     (state__105000))
                    (state__104999))))
                 (state__104999
                  []
                  (clojure.core/case
                   input__103668_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__103668_nth_1__]
                    (clojure.core/let
                     [?next input__103668_nth_2__]
                     (try
                      [{:tag :star,
                        :greedy? false,
                        :pattern ?pattern,
                        :next ?next}]
                      (catch
                       java.lang.Exception
                       e__14839__auto__
                       (if
                        (meander.runtime.zeta/fail? e__14839__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__14839__auto__))))))
                   (state__104982)))]
                (state__104998)))
              (state__104982)))
            (state__104982
             []
             (if
              (clojure.core/= (clojure.core/count input__103668) 3)
              (clojure.core/let
               [input__103668_nth_0__
                (clojure.core/nth input__103668 0)
                input__103668_nth_1__
                (clojure.core/nth input__103668 1)
                input__103668_nth_2__
                (clojure.core/nth input__103668 2)]
               (clojure.core/case
                input__103668_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__103668_nth_1__)
                 (clojure.core/let
                  [input__103668_nth_1___l__
                   (clojure.core/subvec
                    input__103668_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__103668_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__103668_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__103668_nth_1___r__
                     (clojure.core/subvec input__103668_nth_1__ 1)]
                    (clojure.core/let
                     [input__103668_nth_1___l___nth_0__
                      (clojure.core/nth input__103668_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__103668_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__103916
                        (clojure.core/namespace
                         input__103668_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__103916
                        (nil)
                        (clojure.core/let
                         [X__103918
                          (clojure.core/name
                           input__103668_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__103918)
                          (clojure.core/let
                           [ret__103919
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__103918)]
                           (if
                            (clojure.core/some? ret__103919)
                            (if
                             (clojure.core/vector? ret__103919)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__103919)
                               2)
                              (clojure.core/let
                               [ret__103919_nth_1__
                                (clojure.core/nth ret__103919 1)]
                               (clojure.core/let
                                [?n ret__103919_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__103668_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__103668_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__103668_nth_2__]
                                   (try
                                    [{:tag :syntax-error,
                                      :message
                                      "The n or more operator ..N must be preceeded by at least one pattern"}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__)))))))))
                              (state__104934))
                             (state__104934))
                            (state__104934)))
                          (state__104934)))
                        (state__104934)))
                      (state__104934))))
                   (state__104934)))
                 (state__104934))
                (state__104934)))
              (state__104934)))]
           (state__104980))
          (state__104934)))
        (state__104934
         []
         (clojure.core/letfn
          [(def__103922
            [arg__103946]
            (clojure.core/letfn
             [(state__105002
               []
               (clojure.core/let
                [x__103947 :string-plus]
                (clojure.core/let
                 [?tag x__103947]
                 (if
                  (clojure.core/map? arg__103946)
                  (clojure.core/let
                   [VAL__103948 (.valAt arg__103946 :context)]
                   (clojure.core/case
                    VAL__103948
                    (:string)
                    (clojure.core/let [?env arg__103946] [?tag ?env])
                    (state__105003)))
                  (state__105003)))))
              (state__105003
               []
               (clojure.core/let
                [x__103949 :plus]
                (clojure.core/let
                 [?tag x__103949]
                 (clojure.core/let [?env arg__103946] [?tag ?env]))))]
             (state__105002)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 3)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)
              input__103668_nth_2__
              (clojure.core/nth input__103668 2)]
             (clojure.core/case
              input__103668_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__103668_nth_1__)
               (clojure.core/loop
                [search_space__105004
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__103668_nth_1__)]
                (if
                 (clojure.core/seq search_space__105004)
                 (clojure.core/let
                  [input__103668_nth_1___parts__
                   (clojure.core/first search_space__105004)
                   result__105005
                   (clojure.core/let
                    [input__103668_nth_1___l__
                     (clojure.core/nth input__103668_nth_1___parts__ 0)
                     input__103668_nth_1___r__
                     (clojure.core/nth
                      input__103668_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__12760__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__103668_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__103939]
                         (clojure.core/let
                          [input__103939_nth_0__
                           (clojure.core/nth input__103939 0)]
                          (clojure.core/letfn
                           [(save__103940
                             []
                             (meander.runtime.zeta/fail))
                            (f__105008
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__103939_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__103939_nth_0__)
                            (clojure.core/let
                             [X__103942
                              (clojure.core/namespace
                               input__103939_nth_0__)]
                             (clojure.core/case
                              X__103942
                              (nil)
                              (clojure.core/let
                               [X__103944
                                (clojure.core/name
                                 input__103939_nth_0__)]
                               (if
                                (clojure.core/string? X__103944)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__103944)
                                 (save__103940)
                                 (f__105008))
                                (f__105008)))
                              (f__105008)))
                            (f__105008)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__103668_nth_1___r___l__
                           (clojure.core/subvec
                            input__103668_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__103668_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__103668_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__103668_nth_1___r___r__
                             (clojure.core/subvec
                              input__103668_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__103668_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__103668_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__103668_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__103933
                                (clojure.core/namespace
                                 input__103668_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__103933
                                (nil)
                                (clojure.core/let
                                 [X__103935
                                  (clojure.core/name
                                   input__103668_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__103935)
                                  (clojure.core/let
                                   [ret__103936
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__103935)]
                                   (if
                                    (clojure.core/some? ret__103936)
                                    (if
                                     (clojure.core/vector? ret__103936)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__103936)
                                       2)
                                      (clojure.core/let
                                       [ret__103936_nth_1__
                                        (clojure.core/nth
                                         ret__103936
                                         1)]
                                       (clojure.core/let
                                        [?n ret__103936_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__103668_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__12596__auto__
                                           (def__103922
                                            input__103668_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__12596__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__12596__auto__]
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
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__103733
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__103733
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__14839__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__14839__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__14839__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__12760__auto__)
                       (meander.runtime.zeta/fail)
                       ret__12760__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__105005)
                   (recur (clojure.core/next search_space__105004))
                   result__105005))
                 (state__104935)))
               (state__104935))
              (state__104935)))
            (state__104935))
           (state__104935))))
        (state__104935
         []
         (if
          (clojure.core/vector? input__103668)
          (if
           (clojure.core/= (clojure.core/count input__103668) 3)
           (clojure.core/let
            [input__103668_nth_0__
             (clojure.core/nth input__103668 0)
             input__103668_nth_1__
             (clojure.core/nth input__103668 1)
             input__103668_nth_2__
             (clojure.core/nth input__103668 2)]
            (clojure.core/case
             input__103668_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__103668_nth_1__)
              (clojure.core/let
               [input__103668_nth_1___l__
                (clojure.core/subvec
                 input__103668_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__103668_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__103668_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_1___r__
                  (clojure.core/subvec input__103668_nth_1__ 1)]
                 (clojure.core/let
                  [input__103668_nth_1___l___nth_0__
                   (clojure.core/nth input__103668_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__103967
                     (clojure.core/namespace
                      input__103668_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__103967
                     (nil)
                     (clojure.core/let
                      [X__103969
                       (clojure.core/name
                        input__103668_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__103969)
                       (clojure.core/let
                        [ret__103970
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__103969)]
                        (if
                         (clojure.core/some? ret__103970)
                         (if
                          (clojure.core/vector? ret__103970)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__103970)
                            2)
                           (clojure.core/let
                            [ret__103970_nth_1__
                             (clojure.core/nth ret__103970 1)]
                            (clojure.core/let
                             [?n ret__103970_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__103668_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__103668_nth_1___r__]
                               (clojure.core/let
                                [?env input__103668_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The ?n or more operator ..?n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__14839__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__14839__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__14839__auto__)))))))))
                           (state__104936))
                          (state__104936))
                         (state__104936)))
                       (state__104936)))
                     (state__104936)))
                   (state__104936))))
                (state__104936)))
              (state__104936))
             (state__104936)))
           (state__104936))
          (state__104936)))
        (state__104936
         []
         (clojure.core/letfn
          [(def__103973
            [arg__103997]
            (clojure.core/letfn
             [(state__105009
               []
               (clojure.core/let
                [x__103998 :string-logical-plus]
                (clojure.core/let
                 [?tag x__103998]
                 (if
                  (clojure.core/map? arg__103997)
                  (clojure.core/let
                   [VAL__103999 (.valAt arg__103997 :context)]
                   (clojure.core/case
                    VAL__103999
                    (:string)
                    (clojure.core/let [?env arg__103997] [?tag ?env])
                    (state__105010)))
                  (state__105010)))))
              (state__105010
               []
               (clojure.core/let
                [x__104000 :logical-plus]
                (clojure.core/let
                 [?tag x__104000]
                 (clojure.core/let [?env arg__103997] [?tag ?env]))))]
             (state__105009)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 3)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)
              input__103668_nth_2__
              (clojure.core/nth input__103668 2)]
             (clojure.core/case
              input__103668_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__103668_nth_1__)
               (clojure.core/loop
                [search_space__105011
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__103668_nth_1__)]
                (if
                 (clojure.core/seq search_space__105011)
                 (clojure.core/let
                  [input__103668_nth_1___parts__
                   (clojure.core/first search_space__105011)
                   result__105012
                   (clojure.core/let
                    [input__103668_nth_1___l__
                     (clojure.core/nth input__103668_nth_1___parts__ 0)
                     input__103668_nth_1___r__
                     (clojure.core/nth
                      input__103668_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__12760__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__103668_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__103990]
                         (clojure.core/let
                          [input__103990_nth_0__
                           (clojure.core/nth input__103990 0)]
                          (clojure.core/letfn
                           [(save__103991
                             []
                             (meander.runtime.zeta/fail))
                            (f__105015
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__103990_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__103990_nth_0__)
                            (clojure.core/let
                             [X__103993
                              (clojure.core/namespace
                               input__103990_nth_0__)]
                             (clojure.core/case
                              X__103993
                              (nil)
                              (clojure.core/let
                               [X__103995
                                (clojure.core/name
                                 input__103990_nth_0__)]
                               (if
                                (clojure.core/string? X__103995)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__103995)
                                 (save__103991)
                                 (f__105015))
                                (f__105015)))
                              (f__105015)))
                            (f__105015)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__103668_nth_1___r___l__
                           (clojure.core/subvec
                            input__103668_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__103668_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__103668_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__103668_nth_1___r___r__
                             (clojure.core/subvec
                              input__103668_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__103668_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__103668_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__103668_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__103984
                                (clojure.core/namespace
                                 input__103668_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__103984
                                (nil)
                                (clojure.core/let
                                 [X__103986
                                  (clojure.core/name
                                   input__103668_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__103986)
                                  (clojure.core/let
                                   [ret__103987
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__103986)]
                                   (if
                                    (clojure.core/some? ret__103987)
                                    (if
                                     (clojure.core/vector? ret__103987)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__103987)
                                       2)
                                      (clojure.core/let
                                       [ret__103987_nth_1__
                                        (clojure.core/nth
                                         ret__103987
                                         1)]
                                       (clojure.core/let
                                        [?n ret__103987_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__103668_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__12596__auto__
                                           (def__103973
                                            input__103668_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__12596__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__12596__auto__]
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
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__103733
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__103733
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__14839__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__14839__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__14839__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__12760__auto__)
                       (meander.runtime.zeta/fail)
                       ret__12760__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__105012)
                   (recur (clojure.core/next search_space__105011))
                   result__105012))
                 (state__104937)))
               (state__104937))
              (state__104937)))
            (state__104937))
           (state__104937))))
        (state__104937
         []
         (if
          (clojure.core/vector? input__103668)
          (if
           (clojure.core/= (clojure.core/count input__103668) 3)
           (clojure.core/let
            [input__103668_nth_0__
             (clojure.core/nth input__103668 0)
             input__103668_nth_1__
             (clojure.core/nth input__103668 1)
             input__103668_nth_2__
             (clojure.core/nth input__103668 2)]
            (clojure.core/case
             input__103668_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__103668_nth_1__)
              (clojure.core/let
               [input__103668_nth_1___l__
                (clojure.core/subvec
                 input__103668_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__103668_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__103668_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_1___r__
                  (clojure.core/subvec input__103668_nth_1__ 1)]
                 (clojure.core/let
                  [input__103668_nth_1___l___nth_0__
                   (clojure.core/nth input__103668_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__104018
                     (clojure.core/namespace
                      input__103668_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__104018
                     (nil)
                     (clojure.core/let
                      [X__104020
                       (clojure.core/name
                        input__103668_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__104020)
                       (clojure.core/let
                        [ret__104021
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__104020)]
                        (if
                         (clojure.core/some? ret__104021)
                         (if
                          (clojure.core/vector? ret__104021)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__104021)
                            2)
                           (clojure.core/let
                            [ret__104021_nth_1__
                             (clojure.core/nth ret__104021 1)]
                            (clojure.core/let
                             [?n ret__104021_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__103668_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__103668_nth_1___r__]
                               (clojure.core/let
                                [?env input__103668_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The operator ..!n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__14839__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__14839__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__14839__auto__)))))))))
                           (state__104938))
                          (state__104938))
                         (state__104938)))
                       (state__104938)))
                     (state__104938)))
                   (state__104938))))
                (state__104938)))
              (state__104938))
             (state__104938)))
           (state__104938))
          (state__104938)))
        (state__104938
         []
         (clojure.core/letfn
          [(def__104024
            [arg__104048]
            (clojure.core/letfn
             [(state__105016
               []
               (clojure.core/let
                [x__104049 :string-memory-plus]
                (clojure.core/let
                 [?tag x__104049]
                 (if
                  (clojure.core/map? arg__104048)
                  (clojure.core/let
                   [VAL__104050 (.valAt arg__104048 :context)]
                   (clojure.core/case
                    VAL__104050
                    (:string)
                    (clojure.core/let [?env arg__104048] [?tag ?env])
                    (state__105017)))
                  (state__105017)))))
              (state__105017
               []
               (clojure.core/let
                [x__104051 :memory-plus]
                (clojure.core/let
                 [?tag x__104051]
                 (clojure.core/let [?env arg__104048] [?tag ?env]))))]
             (state__105016)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 3)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)
              input__103668_nth_2__
              (clojure.core/nth input__103668 2)]
             (clojure.core/case
              input__103668_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__103668_nth_1__)
               (clojure.core/loop
                [search_space__105018
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__103668_nth_1__)]
                (if
                 (clojure.core/seq search_space__105018)
                 (clojure.core/let
                  [input__103668_nth_1___parts__
                   (clojure.core/first search_space__105018)
                   result__105019
                   (clojure.core/let
                    [input__103668_nth_1___l__
                     (clojure.core/nth input__103668_nth_1___parts__ 0)
                     input__103668_nth_1___r__
                     (clojure.core/nth
                      input__103668_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__12760__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__103668_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__104041]
                         (clojure.core/let
                          [input__104041_nth_0__
                           (clojure.core/nth input__104041 0)]
                          (clojure.core/letfn
                           [(save__104042
                             []
                             (meander.runtime.zeta/fail))
                            (f__105022
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__104041_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__104041_nth_0__)
                            (clojure.core/let
                             [X__104044
                              (clojure.core/namespace
                               input__104041_nth_0__)]
                             (clojure.core/case
                              X__104044
                              (nil)
                              (clojure.core/let
                               [X__104046
                                (clojure.core/name
                                 input__104041_nth_0__)]
                               (if
                                (clojure.core/string? X__104046)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__104046)
                                 (save__104042)
                                 (f__105022))
                                (f__105022)))
                              (f__105022)))
                            (f__105022)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__103668_nth_1___r___l__
                           (clojure.core/subvec
                            input__103668_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__103668_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__103668_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__103668_nth_1___r___r__
                             (clojure.core/subvec
                              input__103668_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__103668_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__103668_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__103668_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__104035
                                (clojure.core/namespace
                                 input__103668_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__104035
                                (nil)
                                (clojure.core/let
                                 [X__104037
                                  (clojure.core/name
                                   input__103668_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__104037)
                                  (clojure.core/let
                                   [ret__104038
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__104037)]
                                   (if
                                    (clojure.core/some? ret__104038)
                                    (if
                                     (clojure.core/vector? ret__104038)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__104038)
                                       2)
                                      (clojure.core/let
                                       [ret__104038_nth_1__
                                        (clojure.core/nth
                                         ret__104038
                                         1)]
                                       (clojure.core/let
                                        [?n ret__104038_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__103668_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__12596__auto__
                                           (def__104024
                                            input__103668_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__12596__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__12596__auto__]
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
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__103733
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__103733
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__14839__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__14839__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__14839__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__12760__auto__)
                       (meander.runtime.zeta/fail)
                       ret__12760__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__105019)
                   (recur (clojure.core/next search_space__105018))
                   result__105019))
                 (state__104939)))
               (state__104939))
              (state__104939)))
            (state__104939))
           (state__104939))))
        (state__104939
         []
         (if
          (clojure.core/vector? input__103668)
          (clojure.core/letfn
           [(state__105023
             []
             (if
              (clojure.core/= (clojure.core/count input__103668) 3)
              (clojure.core/let
               [input__103668_nth_0__
                (clojure.core/nth input__103668 0)
                input__103668_nth_1__
                (clojure.core/nth input__103668 1)
                input__103668_nth_2__
                (clojure.core/nth input__103668 2)]
               (clojure.core/case
                input__103668_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__103668_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__103668_nth_1__)]
                  (clojure.core/let
                   [?env input__103668_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__13899__auto__
                        (CATA__FN__103733
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__103734
                             (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__103734
                               (clojure.core/let
                                [CATA_RESULT__13899__auto__
                                 (CATA__FN__103733
                                  [(if
                                    (.hasNext !xs__counter)
                                    (.next !xs__counter))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__13899__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__13899__auto__
                                  0)))))
                             (clojure.core/persistent!
                              return__103734))))
                          {:tag :empty}
                          ?env])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__13899__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__13899__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__14839__auto__
                     (if
                      (meander.runtime.zeta/fail? e__14839__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__14839__auto__))))))
                 (state__105024))
                (state__105024)))
              (state__105024)))
            (state__105024
             []
             (if
              (clojure.core/= (clojure.core/count input__103668) 4)
              (clojure.core/let
               [input__103668_nth_0__
                (clojure.core/nth input__103668 0)
                input__103668_nth_1__
                (clojure.core/nth input__103668 1)
                input__103668_nth_2__
                (clojure.core/nth input__103668 2)]
               (clojure.core/letfn
                [(state__105026
                  []
                  (clojure.core/let
                   [input__103668_nth_3__
                    (clojure.core/nth input__103668 3)]
                   (clojure.core/case
                    input__103668_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__103668_nth_1__)
                     (clojure.core/letfn
                      [(state__105033
                        []
                        (clojure.core/case
                         input__103668_nth_1__
                         ([])
                         (clojure.core/let
                          [?next input__103668_nth_2__]
                          (clojure.core/let
                           [?env input__103668_nth_3__]
                           (try
                            [?next]
                            (catch
                             java.lang.Exception
                             e__14839__auto__
                             (if
                              (meander.runtime.zeta/fail?
                               e__14839__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__14839__auto__))))))
                         (state__105034)))
                       (state__105034
                        []
                        (clojure.core/loop
                         [search_space__105035
                          (meander.runtime.zeta/epsilon-partitions
                           2
                           input__103668_nth_1__)]
                         (if
                          (clojure.core/seq search_space__105035)
                          (clojure.core/let
                           [input__103668_nth_1___parts__
                            (clojure.core/first search_space__105035)
                            result__105036
                            (clojure.core/let
                             [input__103668_nth_1___l__
                              (clojure.core/nth
                               input__103668_nth_1___parts__
                               0)
                              input__103668_nth_1___r__
                              (clojure.core/nth
                               input__103668_nth_1___parts__
                               1)]
                             (clojure.core/letfn
                              [(state__105038
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__12760__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__103668_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__104077]
                                     (clojure.core/let
                                      [input__104077_nth_0__
                                       (clojure.core/nth
                                        input__104077
                                        0)]
                                      (clojure.core/letfn
                                       [(save__104078
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__105041
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__104077_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__104077_nth_0__)
                                        (clojure.core/let
                                         [VAL__104079
                                          (.valAt
                                           input__104077_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__104079
                                          (:group)
                                          (save__104078)
                                          (f__105041)))
                                        (f__105041)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__103668_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__103668_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__103668_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__103668_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__103668_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__103668_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__103668_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__103668_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__103668_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__104076
                                            (.valAt
                                             input__103668_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__104076
                                            (:group)
                                            (clojure.core/let
                                             [?group
                                              input__103668_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__103668_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__103668_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__103668_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__13899__auto__
                                                     (CATA__FN__103733
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__13899__auto__
                                                         (CATA__FN__103733
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
                                                          CATA_RESULT__13899__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__13899__auto__
                                                          0)))
                                                       (clojure.core/let
                                                        [CATA_RESULT__13899__auto__
                                                         (CATA__FN__103733
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__13899__auto__
                                                             (CATA__FN__103733
                                                              ['meander.dev.parse.zeta/make-cat
                                                               ?rest
                                                               ?next
                                                               ?env])]
                                                            (if
                                                             (meander.runtime.zeta/fail?
                                                              CATA_RESULT__13899__auto__)
                                                             (throw
                                                              (meander.runtime.zeta/fail))
                                                             (clojure.core/nth
                                                              CATA_RESULT__13899__auto__
                                                              0)))
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__13899__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__13899__auto__
                                                          0)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__13899__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__13899__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__14839__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__14839__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__14839__auto__))))))))
                                            (state__105039)))
                                          (state__105039))))
                                       (state__105039)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__12760__auto__)
                                   (state__105039)
                                   ret__12760__auto__))))
                               (state__105039
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__12760__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__103668_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__104088]
                                     (clojure.core/let
                                      [input__104088_nth_0__
                                       (clojure.core/nth
                                        input__104088
                                        0)]
                                      (clojure.core/letfn
                                       [(save__104089
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__105043
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__104088_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__104088_nth_0__)
                                        (clojure.core/let
                                         [VAL__104090
                                          (.valAt
                                           input__104088_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__104090
                                          (:star)
                                          (save__104089)
                                          (f__105043)))
                                        (f__105043)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__103668_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__103668_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__103668_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__103668_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__103668_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__103668_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__103668_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__103668_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__103668_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__104087
                                            (.valAt
                                             input__103668_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__104087
                                            (:star)
                                            (clojure.core/let
                                             [?group
                                              input__103668_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__103668_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__103668_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__103668_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__13899__auto__
                                                     (CATA__FN__103733
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__13899__auto__
                                                         (CATA__FN__103733
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
                                                          CATA_RESULT__13899__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__13899__auto__
                                                          0)))
                                                       (clojure.core/let
                                                        [CATA_RESULT__13899__auto__
                                                         (CATA__FN__103733
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__13899__auto__
                                                             (CATA__FN__103733
                                                              ['meander.dev.parse.zeta/make-cat
                                                               ?rest
                                                               ?next
                                                               ?env])]
                                                            (if
                                                             (meander.runtime.zeta/fail?
                                                              CATA_RESULT__13899__auto__)
                                                             (throw
                                                              (meander.runtime.zeta/fail))
                                                             (clojure.core/nth
                                                              CATA_RESULT__13899__auto__
                                                              0)))
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__13899__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__13899__auto__
                                                          0)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__13899__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__13899__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__14839__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__14839__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__14839__auto__))))))))
                                            (meander.runtime.zeta/fail)))
                                          (meander.runtime.zeta/fail))))
                                       (meander.runtime.zeta/fail)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__12760__auto__)
                                   (meander.runtime.zeta/fail)
                                   ret__12760__auto__))))]
                              (state__105038)))]
                           (if
                            (meander.runtime.zeta/fail? result__105036)
                            (recur
                             (clojure.core/next search_space__105035))
                            result__105036))
                          (state__105027))))]
                      (state__105033))
                     (state__105027))
                    (state__105027))))
                 (state__105027
                  []
                  (clojure.core/case
                   input__103668_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__103668_nth_1__)
                    (clojure.core/let
                     [input__103668_nth_1___l__
                      (clojure.core/subvec
                       input__103668_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__103668_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__103668_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__103668_nth_1___r__
                        (clojure.core/subvec input__103668_nth_1__ 1)]
                       (clojure.core/let
                        [input__103668_nth_1___l___nth_0__
                         (clojure.core/nth
                          input__103668_nth_1___l__
                          0)]
                        (if
                         (clojure.core/map?
                          input__103668_nth_1___l___nth_0__)
                         (clojure.core/let
                          [VAL__104099
                           (.valAt
                            input__103668_nth_1___l___nth_0__
                            :tag)]
                          (clojure.core/case
                           VAL__104099
                           (:literal)
                           (clojure.core/let
                            [VAL__104100
                             (.valAt
                              input__103668_nth_1___l___nth_0__
                              :type)]
                            (if
                             (clojure.core/let
                              [x__11656__auto__ VAL__104100]
                              (clojure.core/case
                               x__11656__auto__
                               (:string :char)
                               true
                               false))
                             (clojure.core/let
                              [VAL__104101
                               (.valAt
                                input__103668_nth_1___l___nth_0__
                                :form)]
                              (clojure.core/let
                               [!forms []]
                               (clojure.core/let
                                [!forms
                                 (clojure.core/conj
                                  !forms
                                  VAL__104101)]
                                (clojure.core/loop
                                 [i__12733__auto__
                                  0
                                  coll__105044
                                  input__103668_nth_1___r__
                                  [!forms]
                                  [!forms]]
                                 (clojure.core/let
                                  [input__104102
                                   (clojure.core/subvec
                                    coll__105044
                                    0
                                    (clojure.core/min
                                     (clojure.core/count coll__105044)
                                     1))]
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count input__104102)
                                    1)
                                   (clojure.core/let
                                    [result__12734__auto__
                                     (clojure.core/let
                                      [input__104102_nth_0__
                                       (clojure.core/nth
                                        input__104102
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__104102_nth_0__)
                                       (clojure.core/let
                                        [VAL__104103
                                         (.valAt
                                          input__104102_nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__104103
                                         (:literal)
                                         (clojure.core/let
                                          [VAL__104104
                                           (.valAt
                                            input__104102_nth_0__
                                            :type)]
                                          (if
                                           (clojure.core/let
                                            [x__11656__auto__
                                             VAL__104104]
                                            (clojure.core/case
                                             x__11656__auto__
                                             (:string :char)
                                             true
                                             false))
                                           (clojure.core/let
                                            [VAL__104105
                                             (.valAt
                                              input__104102_nth_0__
                                              :form)]
                                            (clojure.core/let
                                             [!forms
                                              (clojure.core/conj
                                               !forms
                                               VAL__104105)]
                                             [!forms]))
                                           (meander.runtime.zeta/fail)))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      result__12734__auto__)
                                     (state__105028)
                                     (recur
                                      (clojure.core/inc
                                       i__12733__auto__)
                                      (clojure.core/subvec
                                       coll__105044
                                       1)
                                      result__12734__auto__)))
                                   (if
                                    (clojure.core/or
                                     (clojure.core/seq coll__105044)
                                     (clojure.core/<
                                      i__12733__auto__
                                      0))
                                    (state__105028)
                                    (if
                                     (clojure.core/map?
                                      input__103668_nth_2__)
                                     (clojure.core/let
                                      [VAL__104094
                                       (.valAt
                                        input__103668_nth_2__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__104094
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
                                         e__14839__auto__
                                         (if
                                          (meander.runtime.zeta/fail?
                                           e__14839__auto__)
                                          (meander.runtime.zeta/fail)
                                          (throw e__14839__auto__))))
                                       (state__105028)))
                                     (state__105028)))))))))
                             (state__105028)))
                           (state__105028)))
                         (state__105028))))
                      (state__105028)))
                    (state__105028))
                   (state__105028)))
                 (state__105028
                  []
                  (clojure.core/let
                   [input__103668_nth_3__
                    (clojure.core/nth input__103668 3)]
                   (clojure.core/case
                    input__103668_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__105045
                       []
                       (if
                        (clojure.core/vector? input__103668_nth_1__)
                        (clojure.core/let
                         [input__103668_nth_1___l__
                          (clojure.core/subvec
                           input__103668_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__103668_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__103668_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__103668_nth_1___r__
                            (clojure.core/subvec
                             input__103668_nth_1__
                             1)]
                           (clojure.core/let
                            [input__103668_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__103668_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__103668_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__104926
                               (.valAt
                                input__103668_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__104926
                               (:literal)
                               (clojure.core/letfn
                                [(state__105047
                                  []
                                  (clojure.core/let
                                   [VAL__104112
                                    (.valAt
                                     input__103668_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__104112
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__103668_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__103668_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__103668_nth_2__]
                                       (clojure.core/let
                                        [?env input__103668_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__13899__auto__
                                            (CATA__FN__103733
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__13899__auto__
                                                (CATA__FN__103733
                                                 ['meander.dev.parse.zeta/make-cat
                                                  ?rest
                                                  ?next
                                                  ?env])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__13899__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__13899__auto__
                                                 0)))
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__13899__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__13899__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__14839__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__14839__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__14839__auto__))))))))
                                    (state__105048))))
                                 (state__105048
                                  []
                                  (clojure.core/let
                                   [VAL__104122
                                    (.valAt
                                     input__103668_nth_1___l___nth_0__
                                     :form)]
                                   (clojure.core/let
                                    [!forms []]
                                    (clojure.core/let
                                     [!forms
                                      (clojure.core/conj
                                       !forms
                                       VAL__104122)]
                                     (clojure.core/loop
                                      [i__12733__auto__
                                       0
                                       coll__105049
                                       input__103668_nth_1___r__
                                       [!forms]
                                       [!forms]]
                                      (clojure.core/let
                                       [input__104123
                                        (clojure.core/subvec
                                         coll__105049
                                         0
                                         (clojure.core/min
                                          (clojure.core/count
                                           coll__105049)
                                          1))]
                                       (if
                                        (clojure.core/=
                                         (clojure.core/count
                                          input__104123)
                                         1)
                                        (clojure.core/let
                                         [result__12734__auto__
                                          (clojure.core/let
                                           [input__104123_nth_0__
                                            (clojure.core/nth
                                             input__104123
                                             0)]
                                           (if
                                            (clojure.core/map?
                                             input__104123_nth_0__)
                                            (clojure.core/let
                                             [VAL__104124
                                              (.valAt
                                               input__104123_nth_0__
                                               :tag)]
                                             (clojure.core/case
                                              VAL__104124
                                              (:literal)
                                              (clojure.core/let
                                               [VAL__104125
                                                (.valAt
                                                 input__104123_nth_0__
                                                 :form)]
                                               (clojure.core/let
                                                [!forms
                                                 (clojure.core/conj
                                                  !forms
                                                  VAL__104125)]
                                                [!forms]))
                                              (meander.runtime.zeta/fail)))
                                            (meander.runtime.zeta/fail)))]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           result__12734__auto__)
                                          (state__105046)
                                          (recur
                                           (clojure.core/inc
                                            i__12733__auto__)
                                           (clojure.core/subvec
                                            coll__105049
                                            1)
                                           result__12734__auto__)))
                                        (if
                                         (clojure.core/or
                                          (clojure.core/seq
                                           coll__105049)
                                          (clojure.core/<
                                           i__12733__auto__
                                           0))
                                         (state__105046)
                                         (if
                                          (clojure.core/map?
                                           input__103668_nth_2__)
                                          (clojure.core/let
                                           [VAL__104115
                                            (.valAt
                                             input__103668_nth_2__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__104115
                                            (:empty)
                                            (if
                                             (clojure.core/map?
                                              input__103668_nth_3__)
                                             (clojure.core/let
                                              [VAL__104116
                                               (.valAt
                                                input__103668_nth_3__
                                                :context)]
                                              (clojure.core/let
                                               [?context VAL__104116]
                                               (clojure.core/let
                                                [?env
                                                 input__103668_nth_3__]
                                                (try
                                                 [{:tag :literal,
                                                   :type ?context,
                                                   :form
                                                   (clojure.core/into
                                                    []
                                                    !forms)}]
                                                 (catch
                                                  java.lang.Exception
                                                  e__14839__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__14839__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__14839__auto__)))))))
                                             (state__105046))
                                            (state__105046)))
                                          (state__105046))))))))))]
                                (state__105047))
                               (state__105046)))
                             (state__105046))))
                          (state__105046)))
                        (state__105046)))
                      (state__105046
                       []
                       (clojure.core/let
                        [?sequence input__103668_nth_1__]
                        (clojure.core/let
                         [?next input__103668_nth_2__]
                         (if
                          (clojure.core/map? input__103668_nth_3__)
                          (clojure.core/let
                           [VAL__104129
                            (.valAt input__103668_nth_3__ :context)]
                           (clojure.core/case
                            VAL__104129
                            (:string)
                            (try
                             [{:tag :string-cat,
                               :sequence ?sequence,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__14839__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__14839__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__14839__auto__))))
                            (state__105029)))
                          (state__105029)))))]
                     (state__105045))
                    (state__105029))))
                 (state__105029
                  []
                  (clojure.core/case
                   input__103668_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__103668_nth_1__]
                    (clojure.core/let
                     [?next input__103668_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__14839__auto__
                       (if
                        (meander.runtime.zeta/fail? e__14839__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__14839__auto__))))))
                   (state__105030)))
                 (state__105030
                  []
                  (clojure.core/let
                   [input__103668_nth_3__
                    (clojure.core/nth input__103668 3)]
                   (clojure.core/case
                    input__103668_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__103668_nth_1__)
                     (clojure.core/let
                      [VAL__104924 (.valAt input__103668_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__104924
                       (:cat)
                       (clojure.core/let
                        [VAL__104135
                         (.valAt input__103668_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__104135]
                         (clojure.core/let
                          [VAL__104136
                           (.valAt input__103668_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__104136)
                           (clojure.core/let
                            [VAL__104137 (.valAt VAL__104136 :tag)]
                            (clojure.core/case
                             VAL__104137
                             (:empty)
                             (clojure.core/let
                              [?right input__103668_nth_2__]
                              (clojure.core/let
                               [?env input__103668_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__13899__auto__
                                   (CATA__FN__103733
                                    ['meander.dev.parse.zeta/make-cat
                                     ?sequence
                                     ?right
                                     ?env])]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    CATA_RESULT__13899__auto__)
                                   (throw (meander.runtime.zeta/fail))
                                   (clojure.core/nth
                                    CATA_RESULT__13899__auto__
                                    0)))]
                                (catch
                                 java.lang.Exception
                                 e__14839__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__14839__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__14839__auto__))))))
                             (state__105031)))
                           (state__105031)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__104141
                         (.valAt input__103668_nth_1__ :type)]
                        (clojure.core/case
                         VAL__104141
                         (:string)
                         (clojure.core/let
                          [VAL__104142
                           (.valAt input__103668_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__104142]
                           (if
                            (clojure.core/map? input__103668_nth_2__)
                            (clojure.core/let
                             [VAL__104143
                              (.valAt input__103668_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__104143
                              (:string-join)
                              (clojure.core/let
                               [VAL__104144
                                (.valAt input__103668_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__104144)
                                (clojure.core/let
                                 [VAL__104145
                                  (.valAt VAL__104144 :tag)]
                                 (clojure.core/case
                                  VAL__104145
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__104146
                                    (.valAt VAL__104144 :type)]
                                   (clojure.core/case
                                    VAL__104146
                                    (:string)
                                    (clojure.core/let
                                     [VAL__104147
                                      (.valAt VAL__104144 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__104147]
                                      (clojure.core/let
                                       [VAL__104148
                                        (.valAt
                                         input__103668_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__104148]
                                        (if
                                         (clojure.core/map?
                                          input__103668_nth_3__)
                                         (clojure.core/let
                                          [VAL__104149
                                           (.valAt
                                            input__103668_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__104149
                                           (:string)
                                           (clojure.core/let
                                            [?env
                                             input__103668_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__13899__auto__
                                                (CATA__FN__103733
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
                                                 CATA_RESULT__13899__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__13899__auto__
                                                 0)))]
                                             (catch
                                              java.lang.Exception
                                              e__14839__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__14839__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__14839__auto__)))))
                                           (state__105031)))
                                         (state__105031))))))
                                    (state__105031)))
                                  (state__105031)))
                                (state__105031)))
                              (state__105031)))
                            (state__105031))))
                         (state__105031)))
                       (state__105031)))
                     (state__105031))
                    (state__105031))))
                 (state__105031
                  []
                  (clojure.core/case
                   input__103668_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__103668_nth_1__)
                    (clojure.core/let
                     [VAL__104925 (.valAt input__103668_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__104925
                      (:cat)
                      (clojure.core/let
                       [?ast input__103668_nth_1__]
                       (if
                        (clojure.core/map? input__103668_nth_2__)
                        (clojure.core/let
                         [VAL__104153
                          (.valAt input__103668_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__104153
                          (:cat)
                          (clojure.core/let
                           [VAL__104154
                            (.valAt input__103668_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__104154]
                            (clojure.core/let
                             [VAL__104155
                              (.valAt input__103668_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__104155]
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
                                e__14839__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__14839__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__14839__auto__))))))))
                          (state__105032)))
                        (state__105032)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__103668_nth_1__]
                       (if
                        (clojure.core/map? input__103668_nth_2__)
                        (clojure.core/let
                         [VAL__104159
                          (.valAt input__103668_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__104159
                          (:string-cat)
                          (clojure.core/let
                           [VAL__104160
                            (.valAt input__103668_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__104160]
                            (clojure.core/let
                             [VAL__104161
                              (.valAt input__103668_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__104161]
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
                                e__14839__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__14839__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__14839__auto__))))))))
                          (state__105032)))
                        (state__105032)))
                      (state__105032)))
                    (state__105032))
                   (state__105032)))
                 (state__105032
                  []
                  (clojure.core/let
                   [input__103668_nth_3__
                    (clojure.core/nth input__103668 3)]
                   (clojure.core/case
                    input__103668_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__105050
                       []
                       (if
                        (clojure.core/map? input__103668_nth_1__)
                        (clojure.core/let
                         [VAL__104929
                          (.valAt input__103668_nth_1__ :next)
                          VAL__104928
                          (.valAt input__103668_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__104928
                          (:string-cat)
                          (clojure.core/let
                           [VAL__104165
                            (.valAt input__103668_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__104165]
                            (if
                             (clojure.core/map? VAL__104929)
                             (clojure.core/let
                              [VAL__104167 (.valAt VAL__104929 :tag)]
                              (clojure.core/case
                               VAL__104167
                               (:empty)
                               (clojure.core/let
                                [?right input__103668_nth_2__]
                                (clojure.core/let
                                 [?env input__103668_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [CATA_RESULT__13899__auto__
                                     (CATA__FN__103733
                                      ['meander.dev.parse.zeta/make-join
                                       ?sequence
                                       ?right
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__13899__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__13899__auto__
                                      0)))]
                                  (catch
                                   java.lang.Exception
                                   e__14839__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__14839__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__14839__auto__))))))
                               (state__105051)))
                             (state__105051))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__104171
                            (.valAt input__103668_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__104171]
                            (if
                             (clojure.core/map? VAL__104929)
                             (clojure.core/let
                              [VAL__104173 (.valAt VAL__104929 :tag)]
                              (clojure.core/case
                               VAL__104173
                               (:empty)
                               (clojure.core/let
                                [?right input__103668_nth_2__]
                                (if
                                 (clojure.core/map?
                                  input__103668_nth_3__)
                                 (clojure.core/let
                                  [VAL__104174
                                   (.valAt
                                    input__103668_nth_3__
                                    :context)]
                                  (clojure.core/case
                                   VAL__104174
                                   (:string)
                                   (try
                                    [{:tag :string-star,
                                      :pattern ?pattern,
                                      :next ?right}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__))))
                                   (state__105051)))
                                 (state__105051)))
                               (state__105051)))
                             (state__105051))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__104178
                            (.valAt input__103668_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__104178]
                            (clojure.core/let
                             [VAL__104179
                              (.valAt input__103668_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__104179]
                              (clojure.core/let
                               [?right-2 input__103668_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__103668_nth_3__)
                                (clojure.core/let
                                 [VAL__104180
                                  (.valAt
                                   input__103668_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__104180
                                  (:string)
                                  (clojure.core/let
                                   [?env input__103668_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__13899__auto__
                                        (CATA__FN__103733
                                         ['meander.dev.parse.zeta/make-join
                                          ?right-1
                                          ?right-2
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__13899__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__13899__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__)))))
                                  (state__105051)))
                                (state__105051)))))))
                          (state__105051)))
                        (state__105051)))
                      (state__105051
                       []
                       (clojure.core/let
                        [?left input__103668_nth_1__]
                        (if
                         (clojure.core/map? input__103668_nth_2__)
                         (clojure.core/let
                          [VAL__104183
                           (.valAt input__103668_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__104183
                           (:empty)
                           (clojure.core/let
                            [?env input__103668_nth_3__]
                            (try
                             [?left]
                             (catch
                              java.lang.Exception
                              e__14839__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__14839__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__14839__auto__)))))
                           (state__105052)))
                         (state__105052))))
                      (state__105052
                       []
                       (if
                        (clojure.core/map? input__103668_nth_1__)
                        (clojure.core/let
                         [VAL__104927
                          (.valAt input__103668_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__104927
                          (:empty)
                          (clojure.core/let
                           [?right input__103668_nth_2__]
                           (clojure.core/let
                            [?env input__103668_nth_3__]
                            (try
                             [?right]
                             (catch
                              java.lang.Exception
                              e__14839__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__14839__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__14839__auto__))))))
                          (:star)
                          (clojure.core/let
                           [VAL__104190
                            (.valAt input__103668_nth_1__ :next)]
                           (if
                            (clojure.core/map? VAL__104190)
                            (clojure.core/let
                             [VAL__104191 (.valAt VAL__104190 :tag)]
                             (clojure.core/case
                              VAL__104191
                              (:empty)
                              (clojure.core/let
                               [?left input__103668_nth_1__]
                               (clojure.core/let
                                [?right input__103668_nth_2__]
                                (clojure.core/let
                                 [?env input__103668_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [form__13998__auto__
                                     {:tag :star, :next ?right}]
                                    (clojure.core/merge
                                     (clojure.core/into {} ?left)
                                     form__13998__auto__))]
                                  (catch
                                   java.lang.Exception
                                   e__14839__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__14839__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__14839__auto__)))))))
                              (state__105053)))
                            (state__105053)))
                          (state__105053)))
                        (state__105053)))
                      (state__105053
                       []
                       (clojure.core/let
                        [?left input__103668_nth_1__]
                        (clojure.core/let
                         [?right input__103668_nth_2__]
                         (clojure.core/letfn
                          [(state__105054
                            []
                            (if
                             (clojure.core/map? input__103668_nth_3__)
                             (clojure.core/let
                              [VAL__104194
                               (.valAt input__103668_nth_3__ :context)]
                              (clojure.core/case
                               VAL__104194
                               (:string)
                               (try
                                [{:tag :string-join,
                                  :left ?left,
                                  :right ?right}]
                                (catch
                                 java.lang.Exception
                                 e__14839__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__14839__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__14839__auto__))))
                               (state__105055)))
                             (state__105055)))
                           (state__105055
                            []
                            (clojure.core/let
                             [?env input__103668_nth_3__]
                             (try
                              [{:tag :join,
                                :left ?left,
                                :right ?right}]
                              (catch
                               java.lang.Exception
                               e__14839__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__14839__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__14839__auto__))))))]
                          (state__105054)))))]
                     (state__105050))
                    (state__105025))))]
                (state__105026)))
              (state__105025)))
            (state__105025
             []
             (if
              (clojure.core/= (clojure.core/count input__103668) 3)
              (clojure.core/let
               [input__103668_nth_0__
                (clojure.core/nth input__103668 0)
                input__103668_nth_1__
                (clojure.core/nth input__103668 1)
                input__103668_nth_2__
                (clojure.core/nth input__103668 2)]
               (clojure.core/case
                input__103668_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__103668_nth_1__)
                 (clojure.core/let
                  [VAL__104199
                   (.valAt input__103668_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__104199]
                   (clojure.core/let
                    [X__104201
                     ((clojure.core/fn
                       [m__11663__auto__]
                       (clojure.core/dissoc
                        m__11663__auto__
                        :meander.zeta/as))
                      input__103668_nth_1__)]
                    (clojure.core/let
                     [?rest X__104201]
                     (clojure.core/letfn
                      [(save__104202 [] (state__104940))
                       (f__105056
                        []
                        (clojure.core/let
                         [?env input__103668_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__13899__auto__
                              (CATA__FN__103733 [?pattern ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__13899__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__13899__auto__
                               0))),
                            :next
                            (clojure.core/let
                             [CATA_RESULT__13899__auto__
                              (CATA__FN__103733 [?rest ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__13899__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__13899__auto__
                               0)))}]
                          (catch
                           java.lang.Exception
                           e__14839__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__14839__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__14839__auto__))))))]
                      (if
                       (clojure.core/= ?rest input__103668_nth_1__)
                       (save__104202)
                       (f__105056)))))))
                 (state__104940))
                (state__104940)))
              (state__104940)))]
           (state__105023))
          (state__104940)))
        (state__104940
         []
         (clojure.core/letfn
          [(def__104205
            [arg__104238 ?ns]
            (clojure.core/letfn
             [(state__105057
               []
               (clojure.core/let
                [x__104239 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__104239)
                 (clojure.core/let [?env arg__104238] [?env ?ns])
                 (state__105058))))
              (state__105058
               []
               (if
                (clojure.core/map? arg__104238)
                (clojure.core/let
                 [VAL__104240 (.valAt arg__104238 :aliases)]
                 (if
                  (clojure.core/map? VAL__104240)
                  (clojure.core/let
                   [X__104242 (clojure.core/set VAL__104240)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104242))
                    (clojure.core/loop
                     [search_space__105059
                      (clojure.core/seq X__104242)]
                     (if
                      (clojure.core/seq search_space__105059)
                      (clojure.core/let
                       [elem__104243
                        (clojure.core/first search_space__105059)
                        result__105060
                        (clojure.core/let
                         [elem__104243_nth_0__
                          (clojure.core/nth elem__104243 0)
                          elem__104243_nth_1__
                          (clojure.core/nth elem__104243 1)]
                         (if
                          (clojure.core/symbol? elem__104243_nth_0__)
                          (clojure.core/let
                           [X__104245
                            (clojure.core/name elem__104243_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__104245)
                            (if
                             (clojure.core/symbol?
                              elem__104243_nth_1__)
                             (clojure.core/let
                              [X__104247
                               (clojure.core/name
                                elem__104243_nth_1__)]
                              (clojure.core/case
                               X__104247
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__104238]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105060)
                        (recur
                         (clojure.core/next search_space__105059))
                        result__105060))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105057)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 3)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)
              input__103668_nth_2__
              (clojure.core/nth input__103668 2)]
             (clojure.core/case
              input__103668_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__103668_nth_1__)
               (clojure.core/let
                [X__104210 (clojure.core/set input__103668_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__104210))
                 (clojure.core/loop
                  [search_space__105062 (clojure.core/seq X__104210)]
                  (if
                   (clojure.core/seq search_space__105062)
                   (clojure.core/let
                    [elem__104211
                     (clojure.core/first search_space__105062)
                     result__105063
                     (clojure.core/let
                      [elem__104211_nth_0__
                       (clojure.core/nth elem__104211 0)
                       elem__104211_nth_1__
                       (clojure.core/nth elem__104211 1)]
                      (clojure.core/let
                       [*m__103696 elem__104211_nth_0__]
                       (if
                        (clojure.core/symbol? elem__104211_nth_0__)
                        (clojure.core/let
                         [X__104213
                          (clojure.core/namespace
                           elem__104211_nth_0__)]
                         (clojure.core/let
                          [?ns X__104213]
                          (clojure.core/let
                           [X__104215
                            (clojure.core/name elem__104211_nth_0__)]
                           (if
                            (clojure.core/string? X__104215)
                            (if
                             (clojure.core/re-matches #"&.*" X__104215)
                             (clojure.core/let
                              [?pattern elem__104211_nth_1__]
                              (clojure.core/let
                               [X__104217
                                ((clojure.core/fn
                                  [m__11663__auto__]
                                  (clojure.core/dissoc
                                   m__11663__auto__
                                   *m__103696))
                                 input__103668_nth_1__)]
                               (clojure.core/let
                                [?rest X__104217]
                                (clojure.core/let
                                 [x__12596__auto__
                                  (def__104205
                                   input__103668_nth_2__
                                   ?ns)]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   x__12596__auto__)
                                  (meander.runtime.zeta/fail)
                                  (clojure.core/let
                                   [[?env ?ns] x__12596__auto__]
                                   (try
                                    [{:tag :rest-map,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__13899__auto__
                                        (CATA__FN__103733
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__13899__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__13899__auto__
                                         0))),
                                      :next
                                      (clojure.core/let
                                       [CATA_RESULT__13899__auto__
                                        (CATA__FN__103733
                                         ['meander.dev.parse.zeta/parse-entries
                                          ?rest
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__13899__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__13899__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__))))))))))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? result__105063)
                     (recur (clojure.core/next search_space__105062))
                     result__105063))
                   (state__104941)))
                 (state__104941)))
               (state__104941))
              (state__104941)))
            (state__104941))
           (state__104941))))
        (state__104941
         []
         (if
          (clojure.core/vector? input__103668)
          (clojure.core/letfn
           [(state__105065
             []
             (if
              (clojure.core/= (clojure.core/count input__103668) 3)
              (clojure.core/let
               [input__103668_nth_0__
                (clojure.core/nth input__103668 0)
                input__103668_nth_1__
                (clojure.core/nth input__103668 1)
                input__103668_nth_2__
                (clojure.core/nth input__103668 2)]
               (clojure.core/case
                input__103668_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__105067
                   []
                   (if
                    (clojure.core/map? input__103668_nth_1__)
                    (clojure.core/let
                     [X__104261
                      (clojure.core/set input__103668_nth_1__)]
                     (if
                      (clojure.core/<=
                       1
                       (clojure.core/count X__104261))
                      (clojure.core/loop
                       [search_space__105069
                        (clojure.core/seq X__104261)]
                       (if
                        (clojure.core/seq search_space__105069)
                        (clojure.core/let
                         [elem__104262
                          (clojure.core/first search_space__105069)
                          result__105070
                          (clojure.core/let
                           [elem__104262_nth_0__
                            (clojure.core/nth elem__104262 0)
                            elem__104262_nth_1__
                            (clojure.core/nth elem__104262 1)]
                           (clojure.core/let
                            [?key-pattern elem__104262_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__104262_nth_1__]
                             (clojure.core/let
                              [X__104264
                               ((clojure.core/fn
                                 [m__11663__auto__]
                                 (clojure.core/dissoc
                                  m__11663__auto__
                                  ?key-pattern))
                                input__103668_nth_1__)]
                              (clojure.core/let
                               [?rest X__104264]
                               (clojure.core/let
                                [?env input__103668_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__13899__auto__
                                     (CATA__FN__103733
                                      [?key-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__13899__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__13899__auto__
                                      0))),
                                   :val-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__13899__auto__
                                     (CATA__FN__103733
                                      [?val-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__13899__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__13899__auto__
                                      0))),
                                   :next
                                   (clojure.core/let
                                    [CATA_RESULT__13899__auto__
                                     (CATA__FN__103733
                                      ['meander.dev.parse.zeta/parse-entries
                                       ?rest
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__13899__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__13899__auto__
                                      0)))}]
                                 (catch
                                  java.lang.Exception
                                  e__14839__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__14839__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__14839__auto__))))))))))]
                         (if
                          (meander.runtime.zeta/fail? result__105070)
                          (recur
                           (clojure.core/next search_space__105069))
                          result__105070))
                        (state__105068)))
                      (state__105068)))
                    (state__105068)))
                  (state__105068
                   []
                   (if
                    (clojure.core/map? input__103668_nth_1__)
                    (clojure.core/let
                     [?env input__103668_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__14839__auto__
                       (if
                        (meander.runtime.zeta/fail? e__14839__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__14839__auto__)))))
                    (state__105066)))]
                 (state__105067))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__105072
                   []
                   (if
                    (clojure.core/vector? input__103668_nth_1__)
                    (clojure.core/case
                     input__103668_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__103668_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__14839__auto__
                        (if
                         (meander.runtime.zeta/fail? e__14839__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__14839__auto__)))))
                     (state__105073))
                    (state__105073)))
                  (state__105073
                   []
                   (if
                    (clojure.core/vector? input__103668_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__103668_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__103668_nth_2__]
                      (try
                       [[{:tag :error,
                          :message
                          "meander.zeta/with expects an even number of bindings"}]]
                       (catch
                        java.lang.Exception
                        e__14839__auto__
                        (if
                         (meander.runtime.zeta/fail? e__14839__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__14839__auto__)))))
                     (state__105074))
                    (state__105074)))
                  (state__105074
                   []
                   (if
                    (clojure.core/vector? input__103668_nth_1__)
                    (clojure.core/let
                     [input__103668_nth_1___l__
                      (clojure.core/subvec
                       input__103668_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__103668_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__103668_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__103668_nth_1___r__
                        (clojure.core/subvec input__103668_nth_1__ 2)]
                       (clojure.core/let
                        [input__103668_nth_1___l___nth_0__
                         (clojure.core/nth input__103668_nth_1___l__ 0)
                         input__103668_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__103668_nth_1___l__
                          1)]
                        (if
                         (clojure.core/symbol?
                          input__103668_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__104278
                           (clojure.core/namespace
                            input__103668_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__104280
                            (clojure.core/name
                             input__103668_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__104280)
                            (if
                             (clojure.core/re-matches #"%.+" X__104280)
                             (clojure.core/let
                              [?symbol
                               input__103668_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__103668_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__103668_nth_1___r__]
                                (clojure.core/let
                                 [?env input__103668_nth_2__]
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
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0)))})
                                     (clojure.core/let
                                      [CATA_RESULT__13899__auto__
                                       (CATA__FN__103733
                                        ['meander.dev.parse.zeta/parse-with-bindings
                                         ?rest
                                         ?env])]
                                      (if
                                       (meander.runtime.zeta/fail?
                                        CATA_RESULT__13899__auto__)
                                       (throw
                                        (meander.runtime.zeta/fail))
                                       (clojure.core/nth
                                        CATA_RESULT__13899__auto__
                                        0)))))]
                                  (catch
                                   java.lang.Exception
                                   e__14839__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__14839__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__14839__auto__))))))))
                             (state__105075))
                            (state__105075))))
                         (state__105075))))
                      (state__105075)))
                    (state__105075)))
                  (state__105075
                   []
                   (if
                    (clojure.core/vector? input__103668_nth_1__)
                    (clojure.core/let
                     [input__103668_nth_1___l__
                      (clojure.core/subvec
                       input__103668_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__103668_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__103668_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__103668_nth_1___r__
                        (clojure.core/subvec input__103668_nth_1__ 2)]
                       (clojure.core/let
                        [input__103668_nth_1___l___nth_0__
                         (clojure.core/nth input__103668_nth_1___l__ 0)
                         input__103668_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__103668_nth_1___l__
                          1)]
                        (clojure.core/let
                         [?x input__103668_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__103668_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__103668_nth_1___r__]
                           (clojure.core/let
                            [?env input__103668_nth_2__]
                            (try
                             [[{:tag :error,
                                :message
                                "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]
                             (catch
                              java.lang.Exception
                              e__14839__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__14839__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__14839__auto__))))))))))
                      (state__105066)))
                    (state__105066)))]
                 (state__105072))
                (state__105066)))
              (state__105066)))
            (state__105066
             []
             (if
              (clojure.core/= (clojure.core/count input__103668) 2)
              (clojure.core/let
               [input__103668_nth_0__
                (clojure.core/nth input__103668 0)
                input__103668_nth_1__
                (clojure.core/nth input__103668 1)]
               (if
                (clojure.core/vector? input__103668_nth_0__)
                (clojure.core/let
                 [?sequence input__103668_nth_0__]
                 (clojure.core/let
                  [?form input__103668_nth_0__]
                  (clojure.core/let
                   [?env input__103668_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__13899__auto__
                        (CATA__FN__103733
                         ['meander.dev.parse.zeta/parse-sequential
                          ?sequence
                          (clojure.core/let
                           [form__13998__auto__ {:context :vector}]
                           (clojure.core/merge
                            (clojure.core/into {} ?env)
                            form__13998__auto__))])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__13899__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__13899__auto__
                         0))),
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__14839__auto__
                     (if
                      (meander.runtime.zeta/fail? e__14839__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__14839__auto__)))))))
                (state__104942)))
              (state__104942)))]
           (state__105065))
          (state__104942)))
        (state__104942
         []
         (clojure.core/letfn
          [(def__104290
            [arg__104313 ?__103669]
            (clojure.core/letfn
             [(state__105076
               []
               (clojure.core/let
                [x__104314 "meander.zeta"]
                (if
                 (clojure.core/= ?__103669 x__104314)
                 [?__103669]
                 (state__105077))))
              (state__105077
               []
               (if
                (clojure.core/map? arg__104313)
                (clojure.core/let
                 [VAL__104315 (.valAt arg__104313 :aliases)]
                 (if
                  (clojure.core/map? VAL__104315)
                  (clojure.core/let
                   [X__104317 (clojure.core/set VAL__104315)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104317))
                    (clojure.core/loop
                     [search_space__105078
                      (clojure.core/seq X__104317)]
                     (if
                      (clojure.core/seq search_space__105078)
                      (clojure.core/let
                       [elem__104318
                        (clojure.core/first search_space__105078)
                        result__105079
                        (clojure.core/let
                         [elem__104318_nth_0__
                          (clojure.core/nth elem__104318 0)
                          elem__104318_nth_1__
                          (clojure.core/nth elem__104318 1)]
                         (if
                          (clojure.core/symbol? elem__104318_nth_0__)
                          (clojure.core/let
                           [X__104320
                            (clojure.core/name elem__104318_nth_0__)]
                           (if
                            (clojure.core/= ?__103669 X__104320)
                            (if
                             (clojure.core/symbol?
                              elem__104318_nth_1__)
                             (clojure.core/let
                              [X__104322
                               (clojure.core/name
                                elem__104318_nth_1__)]
                              (clojure.core/case
                               X__104322
                               ("meander.zeta")
                               [?__103669]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105079)
                        (recur
                         (clojure.core/next search_space__105078))
                        result__105079))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105076)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104300
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103669 X__104300]
                     (clojure.core/let
                      [X__104302
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104302
                       ("*")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104290 input__103668_nth_1__ ?__103669)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104943)
                         (clojure.core/let
                          [[?__103669] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (clojure.core/let
                               [input__103668_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__103668_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__103668_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__103668_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__103668_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__103668_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__103668_nth_0__]
                                   (clojure.core/let
                                    [?env input__103668_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :next {:tag :empty}}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__))))))))
                                (state__104943)))
                              (state__104943)))
                            (state__104943))
                           (state__104943)))))
                       (state__104943)))))
                   (state__104943))))
                (state__104943)))
              (state__104943)))
            (state__104943))
           (state__104943))))
        (state__104943
         []
         (clojure.core/letfn
          [(def__104324
            [arg__104347 ?__103670]
            (clojure.core/letfn
             [(state__105081
               []
               (clojure.core/let
                [x__104348 "meander.zeta"]
                (if
                 (clojure.core/= ?__103670 x__104348)
                 [?__103670]
                 (state__105082))))
              (state__105082
               []
               (if
                (clojure.core/map? arg__104347)
                (clojure.core/let
                 [VAL__104349 (.valAt arg__104347 :aliases)]
                 (if
                  (clojure.core/map? VAL__104349)
                  (clojure.core/let
                   [X__104351 (clojure.core/set VAL__104349)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104351))
                    (clojure.core/loop
                     [search_space__105083
                      (clojure.core/seq X__104351)]
                     (if
                      (clojure.core/seq search_space__105083)
                      (clojure.core/let
                       [elem__104352
                        (clojure.core/first search_space__105083)
                        result__105084
                        (clojure.core/let
                         [elem__104352_nth_0__
                          (clojure.core/nth elem__104352 0)
                          elem__104352_nth_1__
                          (clojure.core/nth elem__104352 1)]
                         (if
                          (clojure.core/symbol? elem__104352_nth_0__)
                          (clojure.core/let
                           [X__104354
                            (clojure.core/name elem__104352_nth_0__)]
                           (if
                            (clojure.core/= ?__103670 X__104354)
                            (if
                             (clojure.core/symbol?
                              elem__104352_nth_1__)
                             (clojure.core/let
                              [X__104356
                               (clojure.core/name
                                elem__104352_nth_1__)]
                              (clojure.core/case
                               X__104356
                               ("meander.zeta")
                               [?__103670]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105084)
                        (recur
                         (clojure.core/next search_space__105083))
                        result__105084))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105081)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104334
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103670 X__104334]
                     (clojure.core/let
                      [X__104336
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104336
                       ("<>")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104324 input__103668_nth_1__ ?__103670)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104944)
                         (clojure.core/let
                          [[?__103670] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (clojure.core/let
                               [input__103668_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__103668_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__103668_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__103668_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__103668_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__103668_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__103668_nth_0__]
                                   (clojure.core/let
                                    [?env input__103668_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__))))))))
                                (state__104944)))
                              (state__104944)))
                            (state__104944))
                           (state__104944)))))
                       (state__104944)))))
                   (state__104944))))
                (state__104944)))
              (state__104944)))
            (state__104944))
           (state__104944))))
        (state__104944
         []
         (clojure.core/letfn
          [(def__104358
            [arg__104381 ?__103671]
            (clojure.core/letfn
             [(state__105086
               []
               (clojure.core/let
                [x__104382 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__103671 x__104382)
                 [?__103671]
                 (state__105087))))
              (state__105087
               []
               (if
                (clojure.core/map? arg__104381)
                (clojure.core/let
                 [VAL__104383 (.valAt arg__104381 :aliases)]
                 (if
                  (clojure.core/map? VAL__104383)
                  (clojure.core/let
                   [X__104385 (clojure.core/set VAL__104383)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104385))
                    (clojure.core/loop
                     [search_space__105088
                      (clojure.core/seq X__104385)]
                     (if
                      (clojure.core/seq search_space__105088)
                      (clojure.core/let
                       [elem__104386
                        (clojure.core/first search_space__105088)
                        result__105089
                        (clojure.core/let
                         [elem__104386_nth_0__
                          (clojure.core/nth elem__104386 0)
                          elem__104386_nth_1__
                          (clojure.core/nth elem__104386 1)]
                         (if
                          (clojure.core/symbol? elem__104386_nth_0__)
                          (clojure.core/let
                           [X__104388
                            (clojure.core/name elem__104386_nth_0__)]
                           (if
                            (clojure.core/= ?__103671 X__104388)
                            (if
                             (clojure.core/symbol?
                              elem__104386_nth_1__)
                             (clojure.core/let
                              [X__104390
                               (clojure.core/name
                                elem__104386_nth_1__)]
                              (clojure.core/case
                               X__104390
                               ("meander.math.zeta")
                               [?__103671]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105089)
                        (recur
                         (clojure.core/next search_space__105088))
                        result__105089))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105086)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104368
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103671 X__104368]
                     (clojure.core/let
                      [X__104370
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104370
                       ("+")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104358 input__103668_nth_1__ ?__103671)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104945)
                         (clojure.core/let
                          [[?__103671] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__103668_nth_0__)
                                3)
                               (clojure.core/let
                                [input__103668_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  1)
                                 input__103668_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__103668_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__103668_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__103668_nth_0__]
                                   (clojure.core/let
                                    [?env input__103668_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/+,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733 [?a ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733 [?b ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__)))))))))
                               (state__104945))
                              (state__104945)))
                            (state__104945))
                           (state__104945)))))
                       (state__104945)))))
                   (state__104945))))
                (state__104945)))
              (state__104945)))
            (state__104945))
           (state__104945))))
        (state__104945
         []
         (clojure.core/letfn
          [(def__104392
            [arg__104415 ?__103672]
            (clojure.core/letfn
             [(state__105091
               []
               (clojure.core/let
                [x__104416 "meander.zeta"]
                (if
                 (clojure.core/= ?__103672 x__104416)
                 [?__103672]
                 (state__105092))))
              (state__105092
               []
               (if
                (clojure.core/map? arg__104415)
                (clojure.core/let
                 [VAL__104417 (.valAt arg__104415 :aliases)]
                 (if
                  (clojure.core/map? VAL__104417)
                  (clojure.core/let
                   [X__104419 (clojure.core/set VAL__104417)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104419))
                    (clojure.core/loop
                     [search_space__105093
                      (clojure.core/seq X__104419)]
                     (if
                      (clojure.core/seq search_space__105093)
                      (clojure.core/let
                       [elem__104420
                        (clojure.core/first search_space__105093)
                        result__105094
                        (clojure.core/let
                         [elem__104420_nth_0__
                          (clojure.core/nth elem__104420 0)
                          elem__104420_nth_1__
                          (clojure.core/nth elem__104420 1)]
                         (if
                          (clojure.core/symbol? elem__104420_nth_0__)
                          (clojure.core/let
                           [X__104422
                            (clojure.core/name elem__104420_nth_0__)]
                           (if
                            (clojure.core/= ?__103672 X__104422)
                            (if
                             (clojure.core/symbol?
                              elem__104420_nth_1__)
                             (clojure.core/let
                              [X__104424
                               (clojure.core/name
                                elem__104420_nth_1__)]
                              (clojure.core/case
                               X__104424
                               ("meander.zeta")
                               [?__103672]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105094)
                        (recur
                         (clojure.core/next search_space__105093))
                        result__105094))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105091)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104402
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103672 X__104402]
                     (clojure.core/let
                      [X__104404
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104404
                       ("with")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104392 input__103668_nth_1__ ?__103672)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104946)
                         (clojure.core/let
                          [[?__103672] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__103668_nth_0__)
                                3)
                               (clojure.core/let
                                [input__103668_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  1)
                                 input__103668_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__103668_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__103668_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__103668_nth_0__]
                                   (clojure.core/let
                                    [?env input__103668_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__13899__auto__
                                          (CATA__FN__103733
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?bindings
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__13899__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__13899__auto__
                                           0)))},
                                       :body
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          [?body ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__)))))))))
                               (state__104946))
                              (state__104946)))
                            (state__104946))
                           (state__104946)))))
                       (state__104946)))))
                   (state__104946))))
                (state__104946)))
              (state__104946)))
            (state__104946))
           (state__104946))))
        (state__104946
         []
         (clojure.core/letfn
          [(def__104426
            [arg__104449 ?__103673]
            (clojure.core/letfn
             [(state__105096
               []
               (clojure.core/let
                [x__104450 "meander.zeta"]
                (if
                 (clojure.core/= ?__103673 x__104450)
                 [?__103673]
                 (state__105097))))
              (state__105097
               []
               (if
                (clojure.core/map? arg__104449)
                (clojure.core/let
                 [VAL__104451 (.valAt arg__104449 :aliases)]
                 (if
                  (clojure.core/map? VAL__104451)
                  (clojure.core/let
                   [X__104453 (clojure.core/set VAL__104451)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104453))
                    (clojure.core/loop
                     [search_space__105098
                      (clojure.core/seq X__104453)]
                     (if
                      (clojure.core/seq search_space__105098)
                      (clojure.core/let
                       [elem__104454
                        (clojure.core/first search_space__105098)
                        result__105099
                        (clojure.core/let
                         [elem__104454_nth_0__
                          (clojure.core/nth elem__104454 0)
                          elem__104454_nth_1__
                          (clojure.core/nth elem__104454 1)]
                         (if
                          (clojure.core/symbol? elem__104454_nth_0__)
                          (clojure.core/let
                           [X__104456
                            (clojure.core/name elem__104454_nth_0__)]
                           (if
                            (clojure.core/= ?__103673 X__104456)
                            (if
                             (clojure.core/symbol?
                              elem__104454_nth_1__)
                             (clojure.core/let
                              [X__104458
                               (clojure.core/name
                                elem__104454_nth_1__)]
                              (clojure.core/case
                               X__104458
                               ("meander.zeta")
                               [?__103673]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105099)
                        (recur
                         (clojure.core/next search_space__105098))
                        result__105099))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105096)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104436
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103673 X__104436]
                     (clojure.core/let
                      [X__104438
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104438
                       ("apply")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104426 input__103668_nth_1__ ?__103673)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104947)
                         (clojure.core/let
                          [[?__103673] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__103668_nth_0__)
                                3)
                               (clojure.core/let
                                [input__103668_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  1)
                                 input__103668_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__103668_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__103668_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__103668_nth_0__]
                                   (clojure.core/let
                                    [?env input__103668_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__)))))))))
                               (state__104947))
                              (state__104947)))
                            (state__104947))
                           (state__104947)))))
                       (state__104947)))))
                   (state__104947))))
                (state__104947)))
              (state__104947)))
            (state__104947))
           (state__104947))))
        (state__104947
         []
         (clojure.core/letfn
          [(def__104460
            [arg__104483 ?__103674]
            (clojure.core/letfn
             [(state__105101
               []
               (clojure.core/let
                [x__104484 "meander.zeta"]
                (if
                 (clojure.core/= ?__103674 x__104484)
                 [?__103674]
                 (state__105102))))
              (state__105102
               []
               (if
                (clojure.core/map? arg__104483)
                (clojure.core/let
                 [VAL__104485 (.valAt arg__104483 :aliases)]
                 (if
                  (clojure.core/map? VAL__104485)
                  (clojure.core/let
                   [X__104487 (clojure.core/set VAL__104485)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104487))
                    (clojure.core/loop
                     [search_space__105103
                      (clojure.core/seq X__104487)]
                     (if
                      (clojure.core/seq search_space__105103)
                      (clojure.core/let
                       [elem__104488
                        (clojure.core/first search_space__105103)
                        result__105104
                        (clojure.core/let
                         [elem__104488_nth_0__
                          (clojure.core/nth elem__104488 0)
                          elem__104488_nth_1__
                          (clojure.core/nth elem__104488 1)]
                         (if
                          (clojure.core/symbol? elem__104488_nth_0__)
                          (clojure.core/let
                           [X__104490
                            (clojure.core/name elem__104488_nth_0__)]
                           (if
                            (clojure.core/= ?__103674 X__104490)
                            (if
                             (clojure.core/symbol?
                              elem__104488_nth_1__)
                             (clojure.core/let
                              [X__104492
                               (clojure.core/name
                                elem__104488_nth_1__)]
                              (clojure.core/case
                               X__104492
                               ("meander.zeta")
                               [?__103674]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105104)
                        (recur
                         (clojure.core/next search_space__105103))
                        result__105104))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105101)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104470
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103674 X__104470]
                     (clojure.core/let
                      [X__104472
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104472
                       ("and")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104460 input__103668_nth_1__ ?__103674)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104948)
                         (clojure.core/let
                          [[?__103674] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__103668_nth_0__)
                                3)
                               (clojure.core/let
                                [input__103668_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  1)
                                 input__103668_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__103668_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right
                                   input__103668_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__103668_nth_0__]
                                   (clojure.core/let
                                    [?env input__103668_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__)))))))))
                               (state__104948))
                              (state__104948)))
                            (state__104948))
                           (state__104948)))))
                       (state__104948)))))
                   (state__104948))))
                (state__104948)))
              (state__104948)))
            (state__104948))
           (state__104948))))
        (state__104948
         []
         (clojure.core/letfn
          [(def__104494
            [arg__104517 ?__103675]
            (clojure.core/letfn
             [(state__105106
               []
               (clojure.core/let
                [x__104518 "meander.zeta"]
                (if
                 (clojure.core/= ?__103675 x__104518)
                 [?__103675]
                 (state__105107))))
              (state__105107
               []
               (if
                (clojure.core/map? arg__104517)
                (clojure.core/let
                 [VAL__104519 (.valAt arg__104517 :aliases)]
                 (if
                  (clojure.core/map? VAL__104519)
                  (clojure.core/let
                   [X__104521 (clojure.core/set VAL__104519)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104521))
                    (clojure.core/loop
                     [search_space__105108
                      (clojure.core/seq X__104521)]
                     (if
                      (clojure.core/seq search_space__105108)
                      (clojure.core/let
                       [elem__104522
                        (clojure.core/first search_space__105108)
                        result__105109
                        (clojure.core/let
                         [elem__104522_nth_0__
                          (clojure.core/nth elem__104522 0)
                          elem__104522_nth_1__
                          (clojure.core/nth elem__104522 1)]
                         (if
                          (clojure.core/symbol? elem__104522_nth_0__)
                          (clojure.core/let
                           [X__104524
                            (clojure.core/name elem__104522_nth_0__)]
                           (if
                            (clojure.core/= ?__103675 X__104524)
                            (if
                             (clojure.core/symbol?
                              elem__104522_nth_1__)
                             (clojure.core/let
                              [X__104526
                               (clojure.core/name
                                elem__104522_nth_1__)]
                              (clojure.core/case
                               X__104526
                               ("meander.zeta")
                               [?__103675]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105109)
                        (recur
                         (clojure.core/next search_space__105108))
                        result__105109))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105106)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104504
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103675 X__104504]
                     (clojure.core/let
                      [X__104506
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104506
                       ("cata")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104494 input__103668_nth_1__ ?__103675)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104949)
                         (clojure.core/let
                          [[?__103675] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__103668_nth_0__)
                                2)
                               (clojure.core/let
                                [input__103668_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__103668_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__103668_nth_0__]
                                  (clojure.core/let
                                   [?env input__103668_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__13899__auto__
                                        (CATA__FN__103733
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__13899__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__13899__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__))))))))
                               (state__104949))
                              (state__104949)))
                            (state__104949))
                           (state__104949)))))
                       (state__104949)))))
                   (state__104949))))
                (state__104949)))
              (state__104949)))
            (state__104949))
           (state__104949))))
        (state__104949
         []
         (clojure.core/letfn
          [(def__104528
            [arg__104551 ?__103676]
            (clojure.core/letfn
             [(state__105111
               []
               (clojure.core/let
                [x__104552 "meander.zeta"]
                (if
                 (clojure.core/= ?__103676 x__104552)
                 [?__103676]
                 (state__105112))))
              (state__105112
               []
               (if
                (clojure.core/map? arg__104551)
                (clojure.core/let
                 [VAL__104553 (.valAt arg__104551 :aliases)]
                 (if
                  (clojure.core/map? VAL__104553)
                  (clojure.core/let
                   [X__104555 (clojure.core/set VAL__104553)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104555))
                    (clojure.core/loop
                     [search_space__105113
                      (clojure.core/seq X__104555)]
                     (if
                      (clojure.core/seq search_space__105113)
                      (clojure.core/let
                       [elem__104556
                        (clojure.core/first search_space__105113)
                        result__105114
                        (clojure.core/let
                         [elem__104556_nth_0__
                          (clojure.core/nth elem__104556 0)
                          elem__104556_nth_1__
                          (clojure.core/nth elem__104556 1)]
                         (if
                          (clojure.core/symbol? elem__104556_nth_0__)
                          (clojure.core/let
                           [X__104558
                            (clojure.core/name elem__104556_nth_0__)]
                           (if
                            (clojure.core/= ?__103676 X__104558)
                            (if
                             (clojure.core/symbol?
                              elem__104556_nth_1__)
                             (clojure.core/let
                              [X__104560
                               (clojure.core/name
                                elem__104556_nth_1__)]
                              (clojure.core/case
                               X__104560
                               ("meander.zeta")
                               [?__103676]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105114)
                        (recur
                         (clojure.core/next search_space__105113))
                        result__105114))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105111)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104538
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103676 X__104538]
                     (clojure.core/let
                      [X__104540
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104540
                       ("fold")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104528 input__103668_nth_1__ ?__103676)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104950)
                         (clojure.core/let
                          [[?__103676] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__103668_nth_0__)
                                4)
                               (clojure.core/let
                                [input__103668_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  1)
                                 input__103668_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  2)
                                 input__103668_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__103668_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__103668_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__103668_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__103668_nth_0__]
                                    (clojure.core/let
                                     [?env input__103668_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__13899__auto__
                                             (CATA__FN__103733
                                              [?mutable-variable
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__13899__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__13899__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__13899__auto__
                                             (CATA__FN__103733
                                              [?initial-value ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__13899__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__13899__auto__
                                              0)))
                                           ?fold-function
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__14839__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__14839__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__14839__auto__))))))))))
                               (state__104950))
                              (state__104950)))
                            (state__104950))
                           (state__104950)))))
                       (state__104950)))))
                   (state__104950))))
                (state__104950)))
              (state__104950)))
            (state__104950))
           (state__104950))))
        (state__104950
         []
         (if
          (clojure.core/vector? input__103668)
          (if
           (clojure.core/= (clojure.core/count input__103668) 5)
           (clojure.core/let
            [input__103668_nth_0__
             (clojure.core/nth input__103668 0)
             input__103668_nth_1__
             (clojure.core/nth input__103668 1)
             input__103668_nth_2__
             (clojure.core/nth input__103668 2)
             input__103668_nth_3__
             (clojure.core/nth input__103668 3)
             input__103668_nth_4__
             (clojure.core/nth input__103668 4)]
            (clojure.core/case
             input__103668_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__103668_nth_1__)
              (clojure.core/let
               [VAL__104563 (.valAt input__103668_nth_1__ :tag)]
               (clojure.core/case
                VAL__104563
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__103668_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__103668_nth_2__]
                  (clojure.core/let
                   [?fold-function input__103668_nth_3__]
                   (clojure.core/let
                    [?form input__103668_nth_4__]
                    (try
                     [{:tag :fold,
                       :variable ?variable-ast,
                       :initial-value ?initial-value-ast,
                       :fold-function
                       {:tag :host-expression, :form ?fold-function},
                       :form ?form}]
                     (catch
                      java.lang.Exception
                      e__14839__auto__
                      (if
                       (meander.runtime.zeta/fail? e__14839__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__14839__auto__))))))))
                (state__104951)))
              (state__104951))
             (state__104951)))
           (state__104951))
          (state__104951)))
        (state__104951
         []
         (clojure.core/letfn
          [(def__104565
            [arg__104588 ?__103677]
            (clojure.core/letfn
             [(state__105116
               []
               (clojure.core/let
                [x__104589 "meander.zeta"]
                (if
                 (clojure.core/= ?__103677 x__104589)
                 [?__103677]
                 (state__105117))))
              (state__105117
               []
               (if
                (clojure.core/map? arg__104588)
                (clojure.core/let
                 [VAL__104590 (.valAt arg__104588 :aliases)]
                 (if
                  (clojure.core/map? VAL__104590)
                  (clojure.core/let
                   [X__104592 (clojure.core/set VAL__104590)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104592))
                    (clojure.core/loop
                     [search_space__105118
                      (clojure.core/seq X__104592)]
                     (if
                      (clojure.core/seq search_space__105118)
                      (clojure.core/let
                       [elem__104593
                        (clojure.core/first search_space__105118)
                        result__105119
                        (clojure.core/let
                         [elem__104593_nth_0__
                          (clojure.core/nth elem__104593 0)
                          elem__104593_nth_1__
                          (clojure.core/nth elem__104593 1)]
                         (if
                          (clojure.core/symbol? elem__104593_nth_0__)
                          (clojure.core/let
                           [X__104595
                            (clojure.core/name elem__104593_nth_0__)]
                           (if
                            (clojure.core/= ?__103677 X__104595)
                            (if
                             (clojure.core/symbol?
                              elem__104593_nth_1__)
                             (clojure.core/let
                              [X__104597
                               (clojure.core/name
                                elem__104593_nth_1__)]
                              (clojure.core/case
                               X__104597
                               ("meander.zeta")
                               [?__103677]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105119)
                        (recur
                         (clojure.core/next search_space__105118))
                        result__105119))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105116)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104575
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103677 X__104575]
                     (clojure.core/let
                      [X__104577
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104577
                       ("let")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104565 input__103668_nth_1__ ?__103677)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104952)
                         (clojure.core/let
                          [[?__103677] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__103668_nth_0__)
                                3)
                               (clojure.core/let
                                [input__103668_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  0)
                                 input__103668_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  1)
                                 input__103668_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__103668_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__103668_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next
                                    input__103668_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__103668_nth_0__]
                                    (clojure.core/let
                                     [?env input__103668_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__13899__auto__
                                          (CATA__FN__103733
                                           [?pattern ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__13899__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__13899__auto__
                                           0))),
                                        :expression
                                        {:tag :host-expression,
                                         :form ?expression},
                                        :next
                                        (clojure.core/let
                                         [CATA_RESULT__13899__auto__
                                          (CATA__FN__103733
                                           [?next ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__13899__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__13899__auto__
                                           0)))}]
                                      (catch
                                       java.lang.Exception
                                       e__14839__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__14839__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__14839__auto__))))))))))
                               (state__104952))
                              (state__104952)))
                            (state__104952))
                           (state__104952)))))
                       (state__104952)))))
                   (state__104952))))
                (state__104952)))
              (state__104952)))
            (state__104952))
           (state__104952))))
        (state__104952
         []
         (clojure.core/letfn
          [(def__104599
            [arg__104622 ?__103678]
            (clojure.core/letfn
             [(state__105121
               []
               (clojure.core/let
                [x__104623 "meander.zeta"]
                (if
                 (clojure.core/= ?__103678 x__104623)
                 [?__103678]
                 (state__105122))))
              (state__105122
               []
               (if
                (clojure.core/map? arg__104622)
                (clojure.core/let
                 [VAL__104624 (.valAt arg__104622 :aliases)]
                 (if
                  (clojure.core/map? VAL__104624)
                  (clojure.core/let
                   [X__104626 (clojure.core/set VAL__104624)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104626))
                    (clojure.core/loop
                     [search_space__105123
                      (clojure.core/seq X__104626)]
                     (if
                      (clojure.core/seq search_space__105123)
                      (clojure.core/let
                       [elem__104627
                        (clojure.core/first search_space__105123)
                        result__105124
                        (clojure.core/let
                         [elem__104627_nth_0__
                          (clojure.core/nth elem__104627 0)
                          elem__104627_nth_1__
                          (clojure.core/nth elem__104627 1)]
                         (if
                          (clojure.core/symbol? elem__104627_nth_0__)
                          (clojure.core/let
                           [X__104629
                            (clojure.core/name elem__104627_nth_0__)]
                           (if
                            (clojure.core/= ?__103678 X__104629)
                            (if
                             (clojure.core/symbol?
                              elem__104627_nth_1__)
                             (clojure.core/let
                              [X__104631
                               (clojure.core/name
                                elem__104627_nth_1__)]
                              (clojure.core/case
                               X__104631
                               ("meander.zeta")
                               [?__103678]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105124)
                        (recur
                         (clojure.core/next search_space__105123))
                        result__105124))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105121)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104609
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103678 X__104609]
                     (clojure.core/let
                      [X__104611
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104611
                       ("not")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104599 input__103668_nth_1__ ?__103678)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104953)
                         (clojure.core/let
                          [[?__103678] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__103668_nth_0__)
                                2)
                               (clojure.core/let
                                [input__103668_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__103668_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__103668_nth_0__]
                                  (clojure.core/let
                                   [?env input__103668_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__13899__auto__
                                        (CATA__FN__103733
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__13899__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__13899__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__))))))))
                               (state__104953))
                              (state__104953)))
                            (state__104953))
                           (state__104953)))))
                       (state__104953)))))
                   (state__104953))))
                (state__104953)))
              (state__104953)))
            (state__104953))
           (state__104953))))
        (state__104953
         []
         (clojure.core/letfn
          [(def__104633
            [arg__104656 ?__103679]
            (clojure.core/letfn
             [(state__105126
               []
               (clojure.core/let
                [x__104657 "meander.zeta"]
                (if
                 (clojure.core/= ?__103679 x__104657)
                 [?__103679]
                 (state__105127))))
              (state__105127
               []
               (if
                (clojure.core/map? arg__104656)
                (clojure.core/let
                 [VAL__104658 (.valAt arg__104656 :aliases)]
                 (if
                  (clojure.core/map? VAL__104658)
                  (clojure.core/let
                   [X__104660 (clojure.core/set VAL__104658)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104660))
                    (clojure.core/loop
                     [search_space__105128
                      (clojure.core/seq X__104660)]
                     (if
                      (clojure.core/seq search_space__105128)
                      (clojure.core/let
                       [elem__104661
                        (clojure.core/first search_space__105128)
                        result__105129
                        (clojure.core/let
                         [elem__104661_nth_0__
                          (clojure.core/nth elem__104661 0)
                          elem__104661_nth_1__
                          (clojure.core/nth elem__104661 1)]
                         (if
                          (clojure.core/symbol? elem__104661_nth_0__)
                          (clojure.core/let
                           [X__104663
                            (clojure.core/name elem__104661_nth_0__)]
                           (if
                            (clojure.core/= ?__103679 X__104663)
                            (if
                             (clojure.core/symbol?
                              elem__104661_nth_1__)
                             (clojure.core/let
                              [X__104665
                               (clojure.core/name
                                elem__104661_nth_1__)]
                              (clojure.core/case
                               X__104665
                               ("meander.zeta")
                               [?__103679]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105129)
                        (recur
                         (clojure.core/next search_space__105128))
                        result__105129))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105126)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104643
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103679 X__104643]
                     (clojure.core/let
                      [X__104645
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104645
                       ("or")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104633 input__103668_nth_1__ ?__103679)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104954)
                         (clojure.core/let
                          [[?__103679] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__103668_nth_0__)
                                3)
                               (clojure.core/let
                                [input__103668_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  1)
                                 input__103668_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__103668_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right
                                   input__103668_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__103668_nth_0__]
                                   (clojure.core/let
                                    [?env input__103668_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__)))))))))
                               (state__104954))
                              (state__104954)))
                            (state__104954))
                           (state__104954)))))
                       (state__104954)))))
                   (state__104954))))
                (state__104954)))
              (state__104954)))
            (state__104954))
           (state__104954))))
        (state__104954
         []
         (clojure.core/letfn
          [(def__104667
            [arg__104690 ?__103680]
            (clojure.core/letfn
             [(state__105131
               []
               (clojure.core/let
                [x__104691 "meander.zeta"]
                (if
                 (clojure.core/= ?__103680 x__104691)
                 [?__103680]
                 (state__105132))))
              (state__105132
               []
               (if
                (clojure.core/map? arg__104690)
                (clojure.core/let
                 [VAL__104692 (.valAt arg__104690 :aliases)]
                 (if
                  (clojure.core/map? VAL__104692)
                  (clojure.core/let
                   [X__104694 (clojure.core/set VAL__104692)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104694))
                    (clojure.core/loop
                     [search_space__105133
                      (clojure.core/seq X__104694)]
                     (if
                      (clojure.core/seq search_space__105133)
                      (clojure.core/let
                       [elem__104695
                        (clojure.core/first search_space__105133)
                        result__105134
                        (clojure.core/let
                         [elem__104695_nth_0__
                          (clojure.core/nth elem__104695 0)
                          elem__104695_nth_1__
                          (clojure.core/nth elem__104695 1)]
                         (if
                          (clojure.core/symbol? elem__104695_nth_0__)
                          (clojure.core/let
                           [X__104697
                            (clojure.core/name elem__104695_nth_0__)]
                           (if
                            (clojure.core/= ?__103680 X__104697)
                            (if
                             (clojure.core/symbol?
                              elem__104695_nth_1__)
                             (clojure.core/let
                              [X__104699
                               (clojure.core/name
                                elem__104695_nth_1__)]
                              (clojure.core/case
                               X__104699
                               ("meander.zeta")
                               [?__103680]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105134)
                        (recur
                         (clojure.core/next search_space__105133))
                        result__105134))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105131)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104677
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103680 X__104677]
                     (clojure.core/let
                      [X__104679
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104679
                       ("re")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104667 input__103668_nth_1__ ?__103680)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104955)
                         (clojure.core/let
                          [[?__103680] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__103668_nth_0__)
                                2)
                               (clojure.core/let
                                [input__103668_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__103668_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__103668_nth_0__]
                                  (clojure.core/let
                                   [?env input__103668_nth_1__]
                                   (try
                                    [{:tag :regex,
                                      :regex ?regex,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__))))))))
                               (state__104955))
                              (state__104955)))
                            (state__104955))
                           (state__104955)))))
                       (state__104955)))))
                   (state__104955))))
                (state__104955)))
              (state__104955)))
            (state__104955))
           (state__104955))))
        (state__104955
         []
         (clojure.core/letfn
          [(def__104701
            [arg__104724 ?__103681]
            (clojure.core/letfn
             [(state__105136
               []
               (clojure.core/let
                [x__104725 "meander.zeta"]
                (if
                 (clojure.core/= ?__103681 x__104725)
                 [?__103681]
                 (state__105137))))
              (state__105137
               []
               (if
                (clojure.core/map? arg__104724)
                (clojure.core/let
                 [VAL__104726 (.valAt arg__104724 :aliases)]
                 (if
                  (clojure.core/map? VAL__104726)
                  (clojure.core/let
                   [X__104728 (clojure.core/set VAL__104726)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104728))
                    (clojure.core/loop
                     [search_space__105138
                      (clojure.core/seq X__104728)]
                     (if
                      (clojure.core/seq search_space__105138)
                      (clojure.core/let
                       [elem__104729
                        (clojure.core/first search_space__105138)
                        result__105139
                        (clojure.core/let
                         [elem__104729_nth_0__
                          (clojure.core/nth elem__104729 0)
                          elem__104729_nth_1__
                          (clojure.core/nth elem__104729 1)]
                         (if
                          (clojure.core/symbol? elem__104729_nth_0__)
                          (clojure.core/let
                           [X__104731
                            (clojure.core/name elem__104729_nth_0__)]
                           (if
                            (clojure.core/= ?__103681 X__104731)
                            (if
                             (clojure.core/symbol?
                              elem__104729_nth_1__)
                             (clojure.core/let
                              [X__104733
                               (clojure.core/name
                                elem__104729_nth_1__)]
                              (clojure.core/case
                               X__104733
                               ("meander.zeta")
                               [?__103681]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105139)
                        (recur
                         (clojure.core/next search_space__105138))
                        result__105139))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105136)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104711
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103681 X__104711]
                     (clojure.core/let
                      [X__104713
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104713
                       ("re")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104701 input__103668_nth_1__ ?__103681)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104956)
                         (clojure.core/let
                          [[?__103681] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__103668_nth_0__)
                                3)
                               (clojure.core/let
                                [input__103668_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  1)
                                 input__103668_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__103668_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__103668_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__103668_nth_0__]
                                   (clojure.core/let
                                    [?env input__103668_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          [?capture ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__)))))))))
                               (state__104956))
                              (state__104956)))
                            (state__104956))
                           (state__104956)))))
                       (state__104956)))))
                   (state__104956))))
                (state__104956)))
              (state__104956)))
            (state__104956))
           (state__104956))))
        (state__104956
         []
         (clojure.core/letfn
          [(def__104735
            [arg__104758 ?__103682]
            (clojure.core/letfn
             [(state__105141
               []
               (clojure.core/let
                [x__104759 "meander.zeta"]
                (if
                 (clojure.core/= ?__103682 x__104759)
                 [?__103682]
                 (state__105142))))
              (state__105142
               []
               (if
                (clojure.core/map? arg__104758)
                (clojure.core/let
                 [VAL__104760 (.valAt arg__104758 :aliases)]
                 (if
                  (clojure.core/map? VAL__104760)
                  (clojure.core/let
                   [X__104762 (clojure.core/set VAL__104760)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104762))
                    (clojure.core/loop
                     [search_space__105143
                      (clojure.core/seq X__104762)]
                     (if
                      (clojure.core/seq search_space__105143)
                      (clojure.core/let
                       [elem__104763
                        (clojure.core/first search_space__105143)
                        result__105144
                        (clojure.core/let
                         [elem__104763_nth_0__
                          (clojure.core/nth elem__104763 0)
                          elem__104763_nth_1__
                          (clojure.core/nth elem__104763 1)]
                         (if
                          (clojure.core/symbol? elem__104763_nth_0__)
                          (clojure.core/let
                           [X__104765
                            (clojure.core/name elem__104763_nth_0__)]
                           (if
                            (clojure.core/= ?__103682 X__104765)
                            (if
                             (clojure.core/symbol?
                              elem__104763_nth_1__)
                             (clojure.core/let
                              [X__104767
                               (clojure.core/name
                                elem__104763_nth_1__)]
                              (clojure.core/case
                               X__104767
                               ("meander.zeta")
                               [?__103682]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105144)
                        (recur
                         (clojure.core/next search_space__105143))
                        result__105144))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105141)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104745
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103682 X__104745]
                     (clojure.core/let
                      [X__104747
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104747
                       ("string")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104735 input__103668_nth_1__ ?__103682)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104957)
                         (clojure.core/let
                          [[?__103682] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (clojure.core/let
                               [input__103668_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__103668_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__103668_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__103668_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__103668_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__103668_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__103668_nth_0__]
                                   (clojure.core/let
                                    [?env input__103668_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           (clojure.core/let
                                            [form__13998__auto__
                                             {:context :string}]
                                            (clojure.core/merge
                                             (clojure.core/into
                                              {}
                                              ?env)
                                             form__13998__auto__))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__))))))))
                                (state__104957)))
                              (state__104957)))
                            (state__104957))
                           (state__104957)))))
                       (state__104957)))))
                   (state__104957))))
                (state__104957)))
              (state__104957)))
            (state__104957))
           (state__104957))))
        (state__104957
         []
         (clojure.core/letfn
          [(def__104769
            [arg__104792 ?__103683]
            (clojure.core/letfn
             [(state__105146
               []
               (clojure.core/let
                [x__104793 "meander.zeta"]
                (if
                 (clojure.core/= ?__103683 x__104793)
                 [?__103683]
                 (state__105147))))
              (state__105147
               []
               (if
                (clojure.core/map? arg__104792)
                (clojure.core/let
                 [VAL__104794 (.valAt arg__104792 :aliases)]
                 (if
                  (clojure.core/map? VAL__104794)
                  (clojure.core/let
                   [X__104796 (clojure.core/set VAL__104794)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104796))
                    (clojure.core/loop
                     [search_space__105148
                      (clojure.core/seq X__104796)]
                     (if
                      (clojure.core/seq search_space__105148)
                      (clojure.core/let
                       [elem__104797
                        (clojure.core/first search_space__105148)
                        result__105149
                        (clojure.core/let
                         [elem__104797_nth_0__
                          (clojure.core/nth elem__104797 0)
                          elem__104797_nth_1__
                          (clojure.core/nth elem__104797 1)]
                         (if
                          (clojure.core/symbol? elem__104797_nth_0__)
                          (clojure.core/let
                           [X__104799
                            (clojure.core/name elem__104797_nth_0__)]
                           (if
                            (clojure.core/= ?__103683 X__104799)
                            (if
                             (clojure.core/symbol?
                              elem__104797_nth_1__)
                             (clojure.core/let
                              [X__104801
                               (clojure.core/name
                                elem__104797_nth_1__)]
                              (clojure.core/case
                               X__104801
                               ("meander.zeta")
                               [?__103683]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105149)
                        (recur
                         (clojure.core/next search_space__105148))
                        result__105149))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105146)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104779
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103683 X__104779]
                     (clojure.core/let
                      [X__104781
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104781
                       ("symbol")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104769 input__103668_nth_1__ ?__103683)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104958)
                         (clojure.core/let
                          [[?__103683] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__103668_nth_0__)
                                2)
                               (clojure.core/let
                                [input__103668_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__103668_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__103668_nth_0__]
                                  (clojure.core/let
                                   [?env input__103668_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__13899__auto__
                                        (CATA__FN__103733
                                         [?name ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__13899__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__13899__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__))))))))
                               (state__104958))
                              (state__104958)))
                            (state__104958))
                           (state__104958)))))
                       (state__104958)))))
                   (state__104958))))
                (state__104958)))
              (state__104958)))
            (state__104958))
           (state__104958))))
        (state__104958
         []
         (clojure.core/letfn
          [(def__104803
            [arg__104826 ?__103684]
            (clojure.core/letfn
             [(state__105151
               []
               (clojure.core/let
                [x__104827 "meander.zeta"]
                (if
                 (clojure.core/= ?__103684 x__104827)
                 [?__103684]
                 (state__105152))))
              (state__105152
               []
               (if
                (clojure.core/map? arg__104826)
                (clojure.core/let
                 [VAL__104828 (.valAt arg__104826 :aliases)]
                 (if
                  (clojure.core/map? VAL__104828)
                  (clojure.core/let
                   [X__104830 (clojure.core/set VAL__104828)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104830))
                    (clojure.core/loop
                     [search_space__105153
                      (clojure.core/seq X__104830)]
                     (if
                      (clojure.core/seq search_space__105153)
                      (clojure.core/let
                       [elem__104831
                        (clojure.core/first search_space__105153)
                        result__105154
                        (clojure.core/let
                         [elem__104831_nth_0__
                          (clojure.core/nth elem__104831 0)
                          elem__104831_nth_1__
                          (clojure.core/nth elem__104831 1)]
                         (if
                          (clojure.core/symbol? elem__104831_nth_0__)
                          (clojure.core/let
                           [X__104833
                            (clojure.core/name elem__104831_nth_0__)]
                           (if
                            (clojure.core/= ?__103684 X__104833)
                            (if
                             (clojure.core/symbol?
                              elem__104831_nth_1__)
                             (clojure.core/let
                              [X__104835
                               (clojure.core/name
                                elem__104831_nth_1__)]
                              (clojure.core/case
                               X__104835
                               ("meander.zeta")
                               [?__103684]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105154)
                        (recur
                         (clojure.core/next search_space__105153))
                        result__105154))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105151)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104813
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103684 X__104813]
                     (clojure.core/let
                      [X__104815
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104815
                       ("symbol")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104803 input__103668_nth_1__ ?__103684)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104959)
                         (clojure.core/let
                          [[?__103684] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__103668_nth_0__)
                                3)
                               (clojure.core/let
                                [input__103668_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  1)
                                 input__103668_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__103668_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__103668_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__103668_nth_0__]
                                   (clojure.core/let
                                    [?env input__103668_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          [?name ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :namespace
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__103733
                                          [?namespace ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__)))))))))
                               (state__104959))
                              (state__104959)))
                            (state__104959))
                           (state__104959)))))
                       (state__104959)))))
                   (state__104959))))
                (state__104959)))
              (state__104959)))
            (state__104959))
           (state__104959))))
        (state__104959
         []
         (clojure.core/letfn
          [(def__104837
            [arg__104860 ?__103685]
            (clojure.core/letfn
             [(state__105156
               []
               (clojure.core/let
                [x__104861 "meander.zeta"]
                (if
                 (clojure.core/= ?__103685 x__104861)
                 [?__103685]
                 (state__105157))))
              (state__105157
               []
               (if
                (clojure.core/map? arg__104860)
                (clojure.core/let
                 [VAL__104862 (.valAt arg__104860 :aliases)]
                 (if
                  (clojure.core/map? VAL__104862)
                  (clojure.core/let
                   [X__104864 (clojure.core/set VAL__104862)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__104864))
                    (clojure.core/loop
                     [search_space__105158
                      (clojure.core/seq X__104864)]
                     (if
                      (clojure.core/seq search_space__105158)
                      (clojure.core/let
                       [elem__104865
                        (clojure.core/first search_space__105158)
                        result__105159
                        (clojure.core/let
                         [elem__104865_nth_0__
                          (clojure.core/nth elem__104865 0)
                          elem__104865_nth_1__
                          (clojure.core/nth elem__104865 1)]
                         (if
                          (clojure.core/symbol? elem__104865_nth_0__)
                          (clojure.core/let
                           [X__104867
                            (clojure.core/name elem__104865_nth_0__)]
                           (if
                            (clojure.core/= ?__103685 X__104867)
                            (if
                             (clojure.core/symbol?
                              elem__104865_nth_1__)
                             (clojure.core/let
                              [X__104869
                               (clojure.core/name
                                elem__104865_nth_1__)]
                              (clojure.core/case
                               X__104869
                               ("meander.zeta")
                               [?__103685]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__105159)
                        (recur
                         (clojure.core/next search_space__105158))
                        result__105159))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__105156)))]
          (if
           (clojure.core/vector? input__103668)
           (if
            (clojure.core/= (clojure.core/count input__103668) 2)
            (clojure.core/let
             [input__103668_nth_0__
              (clojure.core/nth input__103668 0)
              input__103668_nth_1__
              (clojure.core/nth input__103668 1)]
             (if
              (clojure.core/seq? input__103668_nth_0__)
              (clojure.core/let
               [input__103668_nth_0___l__
                (clojure.core/take 1 input__103668_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__103668_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__103668_nth_0___r__
                  (clojure.core/drop 1 input__103668_nth_0__)]
                 (clojure.core/let
                  [input__103668_nth_0___l___nth_0__
                   (clojure.core/nth input__103668_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__103668_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__104847
                     (clojure.core/namespace
                      input__103668_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__103685 X__104847]
                     (clojure.core/let
                      [X__104849
                       (clojure.core/name
                        input__103668_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__104849
                       ("symbol")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__104837 input__103668_nth_1__ ?__103685)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__104960)
                         (clojure.core/let
                          [[?__103685] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__103668)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__103668)
                             2)
                            (clojure.core/let
                             [input__103668_nth_0__
                              (clojure.core/nth input__103668 0)
                              input__103668_nth_1__
                              (clojure.core/nth input__103668 1)]
                             (if
                              (clojure.core/seq? input__103668_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__103668_nth_0__)
                                5)
                               (clojure.core/let
                                [input__103668_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  1)
                                 input__103668_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  2)
                                 input__103668_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  3)
                                 input__103668_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__103668_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__103668_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__103668_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name
                                    input__103668_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__103668_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__103668_nth_0__]
                                     (clojure.core/let
                                      [?env input__103668_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__13899__auto__
                                           (CATA__FN__103733
                                            [?name ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__13899__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__13899__auto__
                                            0))),
                                         :namespace
                                         (clojure.core/let
                                          [CATA_RESULT__13899__auto__
                                           (CATA__FN__103733
                                            [?namespace ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__13899__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__13899__auto__
                                            0))),
                                         :as-pattern
                                         (clojure.core/let
                                          [CATA_RESULT__13899__auto__
                                           (CATA__FN__103733
                                            [?pattern ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__13899__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__13899__auto__
                                            0))),
                                         :form ?form}]
                                       (catch
                                        java.lang.Exception
                                        e__14839__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__14839__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__14839__auto__)))))))))
                                 (state__104960)))
                               (state__104960))
                              (state__104960)))
                            (state__104960))
                           (state__104960)))))
                       (state__104960)))))
                   (state__104960))))
                (state__104960)))
              (state__104960)))
            (state__104960))
           (state__104960))))
        (state__104960
         []
         (if
          (clojure.core/vector? input__103668)
          (if
           (clojure.core/= (clojure.core/count input__103668) 2)
           (clojure.core/let
            [input__103668_nth_0__ (clojure.core/nth input__103668 0)]
            (clojure.core/letfn
             [(state__105161
               []
               (clojure.core/let
                [input__103668_nth_1__
                 (clojure.core/nth input__103668 1)]
                (clojure.core/letfn
                 [(state__105166
                   []
                   (if
                    (clojure.core/seq? input__103668_nth_0__)
                    (clojure.core/let
                     [?sequence input__103668_nth_0__]
                     (clojure.core/let
                      [?env input__103668_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__13899__auto__
                           (CATA__FN__103733
                            ['meander.dev.parse.zeta/parse-sequential
                             (clojure.core/into [] ?sequence)
                             (clojure.core/let
                              [form__13998__auto__ {:context :seq}]
                              (clojure.core/merge
                               (clojure.core/into {} ?env)
                               form__13998__auto__))])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__13899__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__13899__auto__
                            0))),
                         :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__14839__auto__
                        (if
                         (meander.runtime.zeta/fail? e__14839__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__14839__auto__))))))
                    (state__105167)))
                  (state__105167
                   []
                   (if
                    (clojure.core/map? input__103668_nth_0__)
                    (clojure.core/let
                     [?map input__103668_nth_0__]
                     (clojure.core/let
                      [?env input__103668_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__13899__auto__
                           (CATA__FN__103733
                            ['meander.dev.parse.zeta/parse-entries
                             ?map
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__13899__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__13899__auto__
                            0))),
                         :form ?map}]
                       (catch
                        java.lang.Exception
                        e__14839__auto__
                        (if
                         (meander.runtime.zeta/fail? e__14839__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__14839__auto__))))))
                    (state__105162)))]
                 (state__105166))))
              (state__105162
               []
               (if
                (clojure.core/symbol? input__103668_nth_0__)
                (clojure.core/let
                 [X__104879
                  (clojure.core/namespace input__103668_nth_0__)]
                 (clojure.core/let
                  [X__104881 (clojure.core/name input__103668_nth_0__)]
                  (if
                   (clojure.core/string? X__104881)
                   (clojure.core/letfn
                    [(state__105168
                      []
                      (clojure.core/let
                       [ret__104882
                        (clojure.core/re-matches #"_.*" X__104881)]
                       (if
                        (clojure.core/some? ret__104882)
                        (clojure.core/let
                         [?name ret__104882]
                         (clojure.core/let
                          [?symbol input__103668_nth_0__]
                          (try
                           [{:tag :wildcard,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__14839__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__14839__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__14839__auto__))))))
                        (state__105169))))
                     (state__105169
                      []
                      (clojure.core/let
                       [ret__104889
                        (clojure.core/re-matches #".+#" X__104881)]
                       (if
                        (clojure.core/some? ret__104889)
                        (clojure.core/let
                         [?name ret__104889]
                         (clojure.core/let
                          [?symbol input__103668_nth_0__]
                          (try
                           [{:tag :random-symbol,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__14839__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__14839__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__14839__auto__))))))
                        (state__105170))))
                     (state__105170
                      []
                      (clojure.core/let
                       [ret__104896
                        (clojure.core/re-matches #"%.+" X__104881)]
                       (if
                        (clojure.core/some? ret__104896)
                        (clojure.core/let
                         [?name ret__104896]
                         (clojure.core/let
                          [?symbol input__103668_nth_0__]
                          (try
                           [{:tag :reference,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__14839__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__14839__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__14839__auto__))))))
                        (state__105171))))
                     (state__105171
                      []
                      (clojure.core/let
                       [ret__104903
                        (clojure.core/re-matches #"\*.+" X__104881)]
                       (if
                        (clojure.core/some? ret__104903)
                        (clojure.core/let
                         [?name ret__104903]
                         (clojure.core/let
                          [?symbol input__103668_nth_0__]
                          (try
                           [{:tag :mutable-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__14839__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__14839__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__14839__auto__))))))
                        (state__105172))))
                     (state__105172
                      []
                      (clojure.core/let
                       [ret__104910
                        (clojure.core/re-matches #"\!.+" X__104881)]
                       (if
                        (clojure.core/some? ret__104910)
                        (clojure.core/let
                         [?name ret__104910]
                         (clojure.core/let
                          [?symbol input__103668_nth_0__]
                          (try
                           [{:tag :memory-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__14839__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__14839__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__14839__auto__))))))
                        (state__105173))))
                     (state__105173
                      []
                      (clojure.core/let
                       [ret__104917
                        (clojure.core/re-matches #"\?.+" X__104881)]
                       (if
                        (clojure.core/some? ret__104917)
                        (clojure.core/let
                         [?name ret__104917]
                         (clojure.core/let
                          [?symbol input__103668_nth_0__]
                          (try
                           [{:tag :logic-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__14839__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__14839__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__14839__auto__))))))
                        (state__105163))))]
                    (state__105168))
                   (state__105163))))
                (state__105163)))
              (state__105163
               []
               (if
                (string? input__103668_nth_0__)
                (clojure.core/let
                 [?x input__103668_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__14839__auto__
                   (if
                    (meander.runtime.zeta/fail? e__14839__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__14839__auto__)))))
                (state__105164)))
              (state__105164
               []
               (if
                (char? input__103668_nth_0__)
                (clojure.core/let
                 [?x input__103668_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__14839__auto__
                   (if
                    (meander.runtime.zeta/fail? e__14839__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__14839__auto__)))))
                (state__105165)))
              (state__105165
               []
               (clojure.core/let
                [?x input__103668_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__14839__auto__
                  (if
                   (meander.runtime.zeta/fail? e__14839__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__14839__auto__))))))]
             (state__105161)))
           (state__104961))
          (state__104961)))
        (state__104961
         []
         (clojure.core/let
          [?x input__103668]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__14839__auto__
            (if
             (meander.runtime.zeta/fail? e__14839__auto__)
             (meander.runtime.zeta/fail)
             (throw e__14839__auto__))))))]
       (state__104930)))]
    (clojure.core/let
     [x__12596__auto__ (CATA__FN__103733 input__103668)]
     (if
      (meander.runtime.zeta/fail? x__12596__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__103735] x__12596__auto__]
       CATA_RETURN__103735))))]
  (if
   (meander.runtime.zeta/fail? ret__12776__auto__)
   nil
   ret__12776__auto__)))
