(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__15599]
 (let*
  [ret__9760__auto__
   (clojure.core/letfn
    [(CATA__FN__15664
      [input__15599]
      (clojure.core/letfn
       [(state__16861
         []
         (if
          (clojure.core/vector? input__15599)
          (if
           (clojure.core/= (clojure.core/count input__15599) 3)
           (clojure.core/let
            [input__15599_nth_0__
             (clojure.core/nth input__15599 0)
             input__15599_nth_1__
             (clojure.core/nth input__15599 1)
             input__15599_nth_2__
             (clojure.core/nth input__15599 2)]
            (clojure.core/case
             input__15599_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__15599_nth_1__)
              (clojure.core/letfn
               [(state__16893
                 []
                 (clojure.core/case
                  input__15599_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__15599_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__11823__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11823__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11823__auto__)))))
                  (state__16894)))
                (state__16894
                 []
                 (clojure.core/let
                  [n__15671
                   (clojure.core/count input__15599_nth_1__)
                   m__15672
                   (clojure.core/max 0 (clojure.core/- n__15671 2))
                   input__15599_nth_1___l__
                   (clojure.core/subvec
                    input__15599_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__15599_nth_1__)
                     m__15672))
                   input__15599_nth_1___r__
                   (clojure.core/subvec input__15599_nth_1__ m__15672)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__15599_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__15599_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__15599_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__15599_nth_1___r___nth_0__
                       (clojure.core/nth input__15599_nth_1___r__ 0)
                       input__15599_nth_1___r___nth_1__
                       (clojure.core/nth input__15599_nth_1___r__ 1)]
                      (clojure.core/case
                       input__15599_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__15599_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__15599_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__10883__auto__
                               (CATA__FN__15664 [?pattern ?env])]
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
                               (CATA__FN__15664
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
                       (state__16862)))
                     (state__16862)))
                   (state__16862))))]
               (state__16893))
              (state__16862))
             (state__16862)))
           (state__16862))
          (state__16862)))
        (state__16862
         []
         (clojure.core/letfn
          [(def__15677
            [arg__15712 ?ns]
            (clojure.core/letfn
             [(state__16895
               []
               (clojure.core/let
                [x__15713 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__15713)
                 (clojure.core/let [?env arg__15712] [?env ?ns])
                 (state__16896))))
              (state__16896
               []
               (if
                (clojure.core/map? arg__15712)
                (clojure.core/let
                 [VAL__15714 (.valAt arg__15712 :aliases)]
                 (if
                  (clojure.core/map? VAL__15714)
                  (clojure.core/let
                   [X__15716 (clojure.core/set VAL__15714)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15716))
                    (clojure.core/loop
                     [search_space__16897 (clojure.core/seq X__15716)]
                     (if
                      (clojure.core/seq search_space__16897)
                      (clojure.core/let
                       [elem__15717
                        (clojure.core/first search_space__16897)
                        result__16898
                        (clojure.core/let
                         [elem__15717_nth_0__
                          (clojure.core/nth elem__15717 0)
                          elem__15717_nth_1__
                          (clojure.core/nth elem__15717 1)]
                         (if
                          (clojure.core/symbol? elem__15717_nth_0__)
                          (clojure.core/let
                           [X__15719
                            (clojure.core/name elem__15717_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__15719)
                            (if
                             (clojure.core/symbol? elem__15717_nth_1__)
                             (clojure.core/let
                              [X__15721
                               (clojure.core/name elem__15717_nth_1__)]
                              (clojure.core/case
                               X__15721
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__15712]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16898)
                        (recur (clojure.core/next search_space__16897))
                        result__16898))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16895)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 3)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)
              input__15599_nth_2__
              (clojure.core/nth input__15599 2)]
             (clojure.core/case
              input__15599_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__15599_nth_1__)
               (clojure.core/loop
                [search_space__16900
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__15599_nth_1__)]
                (if
                 (clojure.core/seq search_space__16900)
                 (clojure.core/let
                  [input__15599_nth_1___parts__
                   (clojure.core/first search_space__16900)
                   result__16901
                   (clojure.core/let
                    [input__15599_nth_1___l__
                     (clojure.core/nth input__15599_nth_1___parts__ 0)
                     input__15599_nth_1___r__
                     (clojure.core/nth input__15599_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__15599_nth_1___l__)]
                     (clojure.core/let
                      [input__15599_nth_1___r___l__
                       (clojure.core/subvec
                        input__15599_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__15599_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__15599_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__15599_nth_1___r___r__
                         (clojure.core/subvec
                          input__15599_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__15599_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__15599_nth_1___r___l__
                           0)
                          input__15599_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__15599_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__15599_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__15686
                            (clojure.core/namespace
                             input__15599_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__15686]
                            (clojure.core/let
                             [X__15688
                              (clojure.core/name
                               input__15599_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__15688)
                              (clojure.core/let
                               [ret__15689
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__15688)]
                               (if
                                (clojure.core/some? ret__15689)
                                (if
                                 (clojure.core/vector? ret__15689)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__15689)
                                   2)
                                  (clojure.core/let
                                   [ret__15689_nth_1__
                                    (clojure.core/nth ret__15689 1)]
                                   (clojure.core/let
                                    [?n ret__15689_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__15599_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__15599_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__9580__auto__
                                        (def__15677
                                         input__15599_nth_2__
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
                                              (CATA__FN__15664
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__15664
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
                                                  (CATA__FN__15664
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__10883__auto__
                                                       (CATA__FN__15664
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
                                                      (CATA__FN__15664
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
                   (meander.runtime.zeta/fail? result__16901)
                   (recur (clojure.core/next search_space__16900))
                   result__16901))
                 (state__16863)))
               (state__16863))
              (state__16863)))
            (state__16863))
           (state__16863))))
        (state__16863
         []
         (clojure.core/letfn
          [(def__15734
            [arg__15766 ?ns]
            (clojure.core/letfn
             [(state__16903
               []
               (clojure.core/let
                [x__15767 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__15767)
                 (clojure.core/let [?env arg__15766] [?env ?ns])
                 (state__16904))))
              (state__16904
               []
               (if
                (clojure.core/map? arg__15766)
                (clojure.core/let
                 [VAL__15768 (.valAt arg__15766 :aliases)]
                 (if
                  (clojure.core/map? VAL__15768)
                  (clojure.core/let
                   [X__15770 (clojure.core/set VAL__15768)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15770))
                    (clojure.core/loop
                     [search_space__16905 (clojure.core/seq X__15770)]
                     (if
                      (clojure.core/seq search_space__16905)
                      (clojure.core/let
                       [elem__15771
                        (clojure.core/first search_space__16905)
                        result__16906
                        (clojure.core/let
                         [elem__15771_nth_0__
                          (clojure.core/nth elem__15771 0)
                          elem__15771_nth_1__
                          (clojure.core/nth elem__15771 1)]
                         (if
                          (clojure.core/symbol? elem__15771_nth_0__)
                          (clojure.core/let
                           [X__15773
                            (clojure.core/name elem__15771_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__15773)
                            (if
                             (clojure.core/symbol? elem__15771_nth_1__)
                             (clojure.core/let
                              [X__15775
                               (clojure.core/name elem__15771_nth_1__)]
                              (clojure.core/case
                               X__15775
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__15766]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16906)
                        (recur (clojure.core/next search_space__16905))
                        result__16906))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16903)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 3)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)
              input__15599_nth_2__
              (clojure.core/nth input__15599 2)]
             (clojure.core/case
              input__15599_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__15599_nth_1__)
               (clojure.core/loop
                [search_space__16908
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__15599_nth_1__)]
                (if
                 (clojure.core/seq search_space__16908)
                 (clojure.core/let
                  [input__15599_nth_1___parts__
                   (clojure.core/first search_space__16908)
                   result__16909
                   (clojure.core/let
                    [input__15599_nth_1___l__
                     (clojure.core/nth input__15599_nth_1___parts__ 0)
                     input__15599_nth_1___r__
                     (clojure.core/nth input__15599_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__15599_nth_1___l__)]
                     (clojure.core/let
                      [input__15599_nth_1___r___l__
                       (clojure.core/subvec
                        input__15599_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__15599_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__15599_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__15599_nth_1___r___r__
                         (clojure.core/subvec
                          input__15599_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__15599_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__15599_nth_1___r___l__
                           0)
                          input__15599_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__15599_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__15599_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__15743
                            (clojure.core/namespace
                             input__15599_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__15743]
                            (clojure.core/let
                             [X__15745
                              (clojure.core/name
                               input__15599_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__15745)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__15745)
                               (clojure.core/let
                                [?pattern
                                 input__15599_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__15599_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__9580__auto__
                                   (def__15734
                                    input__15599_nth_2__
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
                                         (CATA__FN__15664
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__15664
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
                                             (CATA__FN__15664
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__10883__auto__
                                                 (CATA__FN__15664
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
                                                 (CATA__FN__15664
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
                   (meander.runtime.zeta/fail? result__16909)
                   (recur (clojure.core/next search_space__16908))
                   result__16909))
                 (state__16864)))
               (state__16864))
              (state__16864)))
            (state__16864))
           (state__16864))))
        (state__16864
         []
         (if
          (clojure.core/vector? input__15599)
          (clojure.core/letfn
           [(state__16911
             []
             (if
              (clojure.core/= (clojure.core/count input__15599) 3)
              (clojure.core/let
               [input__15599_nth_0__
                (clojure.core/nth input__15599 0)
                input__15599_nth_1__
                (clojure.core/nth input__15599 1)
                input__15599_nth_2__
                (clojure.core/nth input__15599 2)]
               (clojure.core/case
                input__15599_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__15599_nth_1__)
                 (clojure.core/letfn
                  [(state__16914
                    []
                    (clojure.core/let
                     [n__15796
                      (clojure.core/count input__15599_nth_1__)
                      m__15797
                      (clojure.core/max 0 (clojure.core/- n__15796 2))
                      input__15599_nth_1___l__
                      (clojure.core/subvec
                       input__15599_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__15599_nth_1__)
                        m__15797))
                      input__15599_nth_1___r__
                      (clojure.core/subvec
                       input__15599_nth_1__
                       m__15797)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__15599_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__15599_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__15599_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__15599_nth_1___r___nth_0__
                          (clojure.core/nth input__15599_nth_1___r__ 0)
                          input__15599_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__15599_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__15599_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__15801
                            (clojure.core/namespace
                             input__15599_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__15801]
                            (clojure.core/let
                             [X__15803
                              (clojure.core/name
                               input__15599_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__15803)
                              (clojure.core/let
                               [ret__15804
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__15803)]
                               (if
                                (clojure.core/some? ret__15804)
                                (clojure.core/let
                                 [?name ret__15804]
                                 (clojure.core/let
                                  [?pattern
                                   input__15599_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__15599_nth_2__)
                                   (clojure.core/let
                                    [VAL__15788
                                     (.valAt
                                      input__15599_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__15788)
                                     (clojure.core/let
                                      [X__15790
                                       (clojure.core/set VAL__15788)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__15790))
                                       (clojure.core/loop
                                        [search_space__16918
                                         (clojure.core/seq X__15790)]
                                        (if
                                         (clojure.core/seq
                                          search_space__16918)
                                         (clojure.core/let
                                          [elem__15791
                                           (clojure.core/first
                                            search_space__16918)
                                           result__16919
                                           (clojure.core/let
                                            [elem__15791_nth_0__
                                             (clojure.core/nth
                                              elem__15791
                                              0)
                                             elem__15791_nth_1__
                                             (clojure.core/nth
                                              elem__15791
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__15791_nth_0__)
                                             (clojure.core/let
                                              [X__15793
                                               (clojure.core/name
                                                elem__15791_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__15793)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__15791_nth_1__)
                                                (clojure.core/let
                                                 [X__15795
                                                  (clojure.core/name
                                                   elem__15791_nth_1__)]
                                                 (clojure.core/case
                                                  X__15795
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__15599_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__10883__auto__
                                                        (CATA__FN__15664
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
                                            result__16919)
                                           (recur
                                            (clojure.core/next
                                             search_space__16918))
                                           result__16919))
                                         (state__16915)))
                                       (state__16915)))
                                     (state__16915)))
                                   (state__16915))))
                                (state__16915)))
                              (state__16915)))))
                          (state__16915)))
                        (state__16915)))
                      (state__16915))))
                   (state__16915
                    []
                    (clojure.core/loop
                     [search_space__16921
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__15599_nth_1__)]
                     (if
                      (clojure.core/seq search_space__16921)
                      (clojure.core/let
                       [input__15599_nth_1___parts__
                        (clojure.core/first search_space__16921)
                        result__16922
                        (clojure.core/let
                         [input__15599_nth_1___l__
                          (clojure.core/nth
                           input__15599_nth_1___parts__
                           0)
                          input__15599_nth_1___r__
                          (clojure.core/nth
                           input__15599_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__15599_nth_1___l__)]
                          (clojure.core/let
                           [input__15599_nth_1___r___l__
                            (clojure.core/subvec
                             input__15599_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__15599_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__15599_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__15599_nth_1___r___r__
                              (clojure.core/subvec
                               input__15599_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__15599_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__15599_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__15599_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__15664
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__15664
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
                                         (CATA__FN__15664
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
                        (meander.runtime.zeta/fail? result__16922)
                        (recur (clojure.core/next search_space__16921))
                        result__16922))
                      (state__16916))))
                   (state__16916
                    []
                    (clojure.core/let
                     [input__15599_nth_1___l__
                      (clojure.core/subvec
                       input__15599_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__15599_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__15599_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__15599_nth_1___r__
                        (clojure.core/subvec input__15599_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__15599_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__15599_nth_1___r__]
                         (clojure.core/let
                          [?env input__15599_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__10883__auto__
                              (CATA__FN__15664
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
                        (state__16917)))
                      (state__16917))))
                   (state__16917
                    []
                    (clojure.core/loop
                     [search_space__16924
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__15599_nth_1__)]
                     (if
                      (clojure.core/seq search_space__16924)
                      (clojure.core/let
                       [input__15599_nth_1___parts__
                        (clojure.core/first search_space__16924)
                        result__16925
                        (clojure.core/let
                         [input__15599_nth_1___l__
                          (clojure.core/nth
                           input__15599_nth_1___parts__
                           0)
                          input__15599_nth_1___r__
                          (clojure.core/nth
                           input__15599_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__9744__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__15599_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__15821]
                              (clojure.core/let
                               [input__15821_nth_0__
                                (clojure.core/nth input__15821 0)]
                               (clojure.core/letfn
                                [(save__15822
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__16928
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__15821_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__15821_nth_0__)
                                 (clojure.core/let
                                  [X__15824
                                   (clojure.core/namespace
                                    input__15821_nth_0__)]
                                  (clojure.core/case
                                   X__15824
                                   (nil)
                                   (clojure.core/let
                                    [X__15826
                                     (clojure.core/name
                                      input__15821_nth_0__)]
                                    (if
                                     (clojure.core/string? X__15826)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__15826)
                                      (save__15822)
                                      (f__16928))
                                     (f__16928)))
                                   (f__16928)))
                                 (f__16928)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__15599_nth_1___r___l__
                                (clojure.core/subvec
                                 input__15599_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__15599_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__15599_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__15599_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__15599_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__15599_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__15599_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__15599_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__15664
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__15664
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
                                             (CATA__FN__15664
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
                        (meander.runtime.zeta/fail? result__16925)
                        (recur (clojure.core/next search_space__16924))
                        result__16925))
                      (state__16912))))]
                  (state__16914))
                 (state__16912))
                (state__16912)))
              (state__16912)))
            (state__16912
             []
             (if
              (clojure.core/= (clojure.core/count input__15599) 4)
              (clojure.core/let
               [input__15599_nth_0__
                (clojure.core/nth input__15599 0)
                input__15599_nth_1__
                (clojure.core/nth input__15599 1)
                input__15599_nth_2__
                (clojure.core/nth input__15599 2)]
               (clojure.core/letfn
                [(state__16929
                  []
                  (clojure.core/let
                   [input__15599_nth_3__
                    (clojure.core/nth input__15599 3)]
                   (clojure.core/case
                    input__15599_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__16931
                       []
                       (if
                        (clojure.core/map? input__15599_nth_1__)
                        (clojure.core/let
                         [VAL__15830
                          (.valAt input__15599_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__15830
                          (:cat)
                          (clojure.core/let
                           [VAL__15831
                            (.valAt input__15599_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__15831)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__15831)
                              1)
                             (clojure.core/let
                              [VAL__15831_nth_0__
                               (clojure.core/nth VAL__15831 0)]
                              (if
                               (clojure.core/map? VAL__15831_nth_0__)
                               (clojure.core/let
                                [VAL__15836
                                 (.valAt VAL__15831_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__15836
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__15831_nth_0__]
                                  (clojure.core/let
                                   [VAL__15832
                                    (.valAt
                                     input__15599_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__15832)
                                    (clojure.core/let
                                     [VAL__15833
                                      (.valAt VAL__15832 :tag)]
                                     (clojure.core/case
                                      VAL__15833
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__15599_nth_2__]
                                       (clojure.core/let
                                        [?env input__15599_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__10883__auto__
                                            (CATA__FN__15664
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
                                      (state__16932)))
                                    (state__16932))))
                                 (state__16932)))
                               (state__16932)))
                             (state__16932))
                            (state__16932)))
                          (state__16932)))
                        (state__16932)))
                      (state__16932
                       []
                       (clojure.core/let
                        [?pattern input__15599_nth_1__]
                        (clojure.core/let
                         [?next input__15599_nth_2__]
                         (if
                          (clojure.core/map? input__15599_nth_3__)
                          (clojure.core/let
                           [VAL__15839
                            (.valAt input__15599_nth_3__ :context)]
                           (clojure.core/case
                            VAL__15839
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
                            (state__16930)))
                          (state__16930)))))]
                     (state__16931))
                    (state__16930))))
                 (state__16930
                  []
                  (clojure.core/case
                   input__15599_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__15599_nth_1__]
                    (clojure.core/let
                     [?next input__15599_nth_2__]
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
                   (state__16913)))]
                (state__16929)))
              (state__16913)))
            (state__16913
             []
             (if
              (clojure.core/= (clojure.core/count input__15599) 3)
              (clojure.core/let
               [input__15599_nth_0__
                (clojure.core/nth input__15599 0)
                input__15599_nth_1__
                (clojure.core/nth input__15599 1)
                input__15599_nth_2__
                (clojure.core/nth input__15599 2)]
               (clojure.core/case
                input__15599_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__15599_nth_1__)
                 (clojure.core/let
                  [input__15599_nth_1___l__
                   (clojure.core/subvec
                    input__15599_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__15599_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__15599_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__15599_nth_1___r__
                     (clojure.core/subvec input__15599_nth_1__ 1)]
                    (clojure.core/let
                     [input__15599_nth_1___l___nth_0__
                      (clojure.core/nth input__15599_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__15599_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__15847
                        (clojure.core/namespace
                         input__15599_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__15847
                        (nil)
                        (clojure.core/let
                         [X__15849
                          (clojure.core/name
                           input__15599_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__15849)
                          (clojure.core/let
                           [ret__15850
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__15849)]
                           (if
                            (clojure.core/some? ret__15850)
                            (if
                             (clojure.core/vector? ret__15850)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__15850)
                               2)
                              (clojure.core/let
                               [ret__15850_nth_1__
                                (clojure.core/nth ret__15850 1)]
                               (clojure.core/let
                                [?n ret__15850_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__15599_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__15599_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__15599_nth_2__]
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
                              (state__16865))
                             (state__16865))
                            (state__16865)))
                          (state__16865)))
                        (state__16865)))
                      (state__16865))))
                   (state__16865)))
                 (state__16865))
                (state__16865)))
              (state__16865)))]
           (state__16911))
          (state__16865)))
        (state__16865
         []
         (clojure.core/letfn
          [(def__15853
            [arg__15877]
            (clojure.core/letfn
             [(state__16933
               []
               (clojure.core/let
                [x__15878 :string-plus]
                (clojure.core/let
                 [?tag x__15878]
                 (if
                  (clojure.core/map? arg__15877)
                  (clojure.core/let
                   [VAL__15879 (.valAt arg__15877 :context)]
                   (clojure.core/case
                    VAL__15879
                    (:string)
                    (clojure.core/let [?env arg__15877] [?tag ?env])
                    (state__16934)))
                  (state__16934)))))
              (state__16934
               []
               (clojure.core/let
                [x__15880 :plus]
                (clojure.core/let
                 [?tag x__15880]
                 (clojure.core/let [?env arg__15877] [?tag ?env]))))]
             (state__16933)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 3)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)
              input__15599_nth_2__
              (clojure.core/nth input__15599 2)]
             (clojure.core/case
              input__15599_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__15599_nth_1__)
               (clojure.core/loop
                [search_space__16935
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__15599_nth_1__)]
                (if
                 (clojure.core/seq search_space__16935)
                 (clojure.core/let
                  [input__15599_nth_1___parts__
                   (clojure.core/first search_space__16935)
                   result__16936
                   (clojure.core/let
                    [input__15599_nth_1___l__
                     (clojure.core/nth input__15599_nth_1___parts__ 0)
                     input__15599_nth_1___r__
                     (clojure.core/nth input__15599_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9744__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__15599_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__15870]
                         (clojure.core/let
                          [input__15870_nth_0__
                           (clojure.core/nth input__15870 0)]
                          (clojure.core/letfn
                           [(save__15871
                             []
                             (meander.runtime.zeta/fail))
                            (f__16939
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__15870_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__15870_nth_0__)
                            (clojure.core/let
                             [X__15873
                              (clojure.core/namespace
                               input__15870_nth_0__)]
                             (clojure.core/case
                              X__15873
                              (nil)
                              (clojure.core/let
                               [X__15875
                                (clojure.core/name
                                 input__15870_nth_0__)]
                               (if
                                (clojure.core/string? X__15875)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__15875)
                                 (save__15871)
                                 (f__16939))
                                (f__16939)))
                              (f__16939)))
                            (f__16939)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__15599_nth_1___r___l__
                           (clojure.core/subvec
                            input__15599_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__15599_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__15599_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__15599_nth_1___r___r__
                             (clojure.core/subvec
                              input__15599_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__15599_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__15599_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__15599_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__15864
                                (clojure.core/namespace
                                 input__15599_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__15864
                                (nil)
                                (clojure.core/let
                                 [X__15866
                                  (clojure.core/name
                                   input__15599_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__15866)
                                  (clojure.core/let
                                   [ret__15867
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__15866)]
                                   (if
                                    (clojure.core/some? ret__15867)
                                    (if
                                     (clojure.core/vector? ret__15867)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__15867)
                                       2)
                                      (clojure.core/let
                                       [ret__15867_nth_1__
                                        (clojure.core/nth
                                         ret__15867
                                         1)]
                                       (clojure.core/let
                                        [?n ret__15867_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__15599_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9580__auto__
                                           (def__15853
                                            input__15599_nth_2__)]
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
                                                  (CATA__FN__15664
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
                                                  (CATA__FN__15664
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
                   (meander.runtime.zeta/fail? result__16936)
                   (recur (clojure.core/next search_space__16935))
                   result__16936))
                 (state__16866)))
               (state__16866))
              (state__16866)))
            (state__16866))
           (state__16866))))
        (state__16866
         []
         (if
          (clojure.core/vector? input__15599)
          (if
           (clojure.core/= (clojure.core/count input__15599) 3)
           (clojure.core/let
            [input__15599_nth_0__
             (clojure.core/nth input__15599 0)
             input__15599_nth_1__
             (clojure.core/nth input__15599 1)
             input__15599_nth_2__
             (clojure.core/nth input__15599 2)]
            (clojure.core/case
             input__15599_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__15599_nth_1__)
              (clojure.core/let
               [input__15599_nth_1___l__
                (clojure.core/subvec
                 input__15599_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__15599_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__15599_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_1___r__
                  (clojure.core/subvec input__15599_nth_1__ 1)]
                 (clojure.core/let
                  [input__15599_nth_1___l___nth_0__
                   (clojure.core/nth input__15599_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__15898
                     (clojure.core/namespace
                      input__15599_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__15898
                     (nil)
                     (clojure.core/let
                      [X__15900
                       (clojure.core/name
                        input__15599_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__15900)
                       (clojure.core/let
                        [ret__15901
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__15900)]
                        (if
                         (clojure.core/some? ret__15901)
                         (if
                          (clojure.core/vector? ret__15901)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__15901)
                            2)
                           (clojure.core/let
                            [ret__15901_nth_1__
                             (clojure.core/nth ret__15901 1)]
                            (clojure.core/let
                             [?n ret__15901_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__15599_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__15599_nth_1___r__]
                               (clojure.core/let
                                [?env input__15599_nth_2__]
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
                           (state__16867))
                          (state__16867))
                         (state__16867)))
                       (state__16867)))
                     (state__16867)))
                   (state__16867))))
                (state__16867)))
              (state__16867))
             (state__16867)))
           (state__16867))
          (state__16867)))
        (state__16867
         []
         (clojure.core/letfn
          [(def__15904
            [arg__15928]
            (clojure.core/letfn
             [(state__16940
               []
               (clojure.core/let
                [x__15929 :string-logical-plus]
                (clojure.core/let
                 [?tag x__15929]
                 (if
                  (clojure.core/map? arg__15928)
                  (clojure.core/let
                   [VAL__15930 (.valAt arg__15928 :context)]
                   (clojure.core/case
                    VAL__15930
                    (:string)
                    (clojure.core/let [?env arg__15928] [?tag ?env])
                    (state__16941)))
                  (state__16941)))))
              (state__16941
               []
               (clojure.core/let
                [x__15931 :logical-plus]
                (clojure.core/let
                 [?tag x__15931]
                 (clojure.core/let [?env arg__15928] [?tag ?env]))))]
             (state__16940)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 3)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)
              input__15599_nth_2__
              (clojure.core/nth input__15599 2)]
             (clojure.core/case
              input__15599_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__15599_nth_1__)
               (clojure.core/loop
                [search_space__16942
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__15599_nth_1__)]
                (if
                 (clojure.core/seq search_space__16942)
                 (clojure.core/let
                  [input__15599_nth_1___parts__
                   (clojure.core/first search_space__16942)
                   result__16943
                   (clojure.core/let
                    [input__15599_nth_1___l__
                     (clojure.core/nth input__15599_nth_1___parts__ 0)
                     input__15599_nth_1___r__
                     (clojure.core/nth input__15599_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9744__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__15599_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__15921]
                         (clojure.core/let
                          [input__15921_nth_0__
                           (clojure.core/nth input__15921 0)]
                          (clojure.core/letfn
                           [(save__15922
                             []
                             (meander.runtime.zeta/fail))
                            (f__16946
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__15921_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__15921_nth_0__)
                            (clojure.core/let
                             [X__15924
                              (clojure.core/namespace
                               input__15921_nth_0__)]
                             (clojure.core/case
                              X__15924
                              (nil)
                              (clojure.core/let
                               [X__15926
                                (clojure.core/name
                                 input__15921_nth_0__)]
                               (if
                                (clojure.core/string? X__15926)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__15926)
                                 (save__15922)
                                 (f__16946))
                                (f__16946)))
                              (f__16946)))
                            (f__16946)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__15599_nth_1___r___l__
                           (clojure.core/subvec
                            input__15599_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__15599_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__15599_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__15599_nth_1___r___r__
                             (clojure.core/subvec
                              input__15599_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__15599_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__15599_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__15599_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__15915
                                (clojure.core/namespace
                                 input__15599_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__15915
                                (nil)
                                (clojure.core/let
                                 [X__15917
                                  (clojure.core/name
                                   input__15599_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__15917)
                                  (clojure.core/let
                                   [ret__15918
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__15917)]
                                   (if
                                    (clojure.core/some? ret__15918)
                                    (if
                                     (clojure.core/vector? ret__15918)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__15918)
                                       2)
                                      (clojure.core/let
                                       [ret__15918_nth_1__
                                        (clojure.core/nth
                                         ret__15918
                                         1)]
                                       (clojure.core/let
                                        [?n ret__15918_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__15599_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9580__auto__
                                           (def__15904
                                            input__15599_nth_2__)]
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
                                                  (CATA__FN__15664
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
                                                  (CATA__FN__15664
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
                   (meander.runtime.zeta/fail? result__16943)
                   (recur (clojure.core/next search_space__16942))
                   result__16943))
                 (state__16868)))
               (state__16868))
              (state__16868)))
            (state__16868))
           (state__16868))))
        (state__16868
         []
         (if
          (clojure.core/vector? input__15599)
          (if
           (clojure.core/= (clojure.core/count input__15599) 3)
           (clojure.core/let
            [input__15599_nth_0__
             (clojure.core/nth input__15599 0)
             input__15599_nth_1__
             (clojure.core/nth input__15599 1)
             input__15599_nth_2__
             (clojure.core/nth input__15599 2)]
            (clojure.core/case
             input__15599_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__15599_nth_1__)
              (clojure.core/let
               [input__15599_nth_1___l__
                (clojure.core/subvec
                 input__15599_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__15599_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__15599_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_1___r__
                  (clojure.core/subvec input__15599_nth_1__ 1)]
                 (clojure.core/let
                  [input__15599_nth_1___l___nth_0__
                   (clojure.core/nth input__15599_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__15949
                     (clojure.core/namespace
                      input__15599_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__15949
                     (nil)
                     (clojure.core/let
                      [X__15951
                       (clojure.core/name
                        input__15599_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__15951)
                       (clojure.core/let
                        [ret__15952
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__15951)]
                        (if
                         (clojure.core/some? ret__15952)
                         (if
                          (clojure.core/vector? ret__15952)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__15952)
                            2)
                           (clojure.core/let
                            [ret__15952_nth_1__
                             (clojure.core/nth ret__15952 1)]
                            (clojure.core/let
                             [?n ret__15952_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__15599_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__15599_nth_1___r__]
                               (clojure.core/let
                                [?env input__15599_nth_2__]
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
                           (state__16869))
                          (state__16869))
                         (state__16869)))
                       (state__16869)))
                     (state__16869)))
                   (state__16869))))
                (state__16869)))
              (state__16869))
             (state__16869)))
           (state__16869))
          (state__16869)))
        (state__16869
         []
         (clojure.core/letfn
          [(def__15955
            [arg__15979]
            (clojure.core/letfn
             [(state__16947
               []
               (clojure.core/let
                [x__15980 :string-memory-plus]
                (clojure.core/let
                 [?tag x__15980]
                 (if
                  (clojure.core/map? arg__15979)
                  (clojure.core/let
                   [VAL__15981 (.valAt arg__15979 :context)]
                   (clojure.core/case
                    VAL__15981
                    (:string)
                    (clojure.core/let [?env arg__15979] [?tag ?env])
                    (state__16948)))
                  (state__16948)))))
              (state__16948
               []
               (clojure.core/let
                [x__15982 :memory-plus]
                (clojure.core/let
                 [?tag x__15982]
                 (clojure.core/let [?env arg__15979] [?tag ?env]))))]
             (state__16947)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 3)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)
              input__15599_nth_2__
              (clojure.core/nth input__15599 2)]
             (clojure.core/case
              input__15599_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__15599_nth_1__)
               (clojure.core/loop
                [search_space__16949
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__15599_nth_1__)]
                (if
                 (clojure.core/seq search_space__16949)
                 (clojure.core/let
                  [input__15599_nth_1___parts__
                   (clojure.core/first search_space__16949)
                   result__16950
                   (clojure.core/let
                    [input__15599_nth_1___l__
                     (clojure.core/nth input__15599_nth_1___parts__ 0)
                     input__15599_nth_1___r__
                     (clojure.core/nth input__15599_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9744__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__15599_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__15972]
                         (clojure.core/let
                          [input__15972_nth_0__
                           (clojure.core/nth input__15972 0)]
                          (clojure.core/letfn
                           [(save__15973
                             []
                             (meander.runtime.zeta/fail))
                            (f__16953
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__15972_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__15972_nth_0__)
                            (clojure.core/let
                             [X__15975
                              (clojure.core/namespace
                               input__15972_nth_0__)]
                             (clojure.core/case
                              X__15975
                              (nil)
                              (clojure.core/let
                               [X__15977
                                (clojure.core/name
                                 input__15972_nth_0__)]
                               (if
                                (clojure.core/string? X__15977)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__15977)
                                 (save__15973)
                                 (f__16953))
                                (f__16953)))
                              (f__16953)))
                            (f__16953)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__15599_nth_1___r___l__
                           (clojure.core/subvec
                            input__15599_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__15599_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__15599_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__15599_nth_1___r___r__
                             (clojure.core/subvec
                              input__15599_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__15599_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__15599_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__15599_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__15966
                                (clojure.core/namespace
                                 input__15599_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__15966
                                (nil)
                                (clojure.core/let
                                 [X__15968
                                  (clojure.core/name
                                   input__15599_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__15968)
                                  (clojure.core/let
                                   [ret__15969
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__15968)]
                                   (if
                                    (clojure.core/some? ret__15969)
                                    (if
                                     (clojure.core/vector? ret__15969)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__15969)
                                       2)
                                      (clojure.core/let
                                       [ret__15969_nth_1__
                                        (clojure.core/nth
                                         ret__15969
                                         1)]
                                       (clojure.core/let
                                        [?n ret__15969_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__15599_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9580__auto__
                                           (def__15955
                                            input__15599_nth_2__)]
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
                                                  (CATA__FN__15664
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
                                                  (CATA__FN__15664
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
                   (meander.runtime.zeta/fail? result__16950)
                   (recur (clojure.core/next search_space__16949))
                   result__16950))
                 (state__16870)))
               (state__16870))
              (state__16870)))
            (state__16870))
           (state__16870))))
        (state__16870
         []
         (if
          (clojure.core/vector? input__15599)
          (clojure.core/letfn
           [(state__16954
             []
             (if
              (clojure.core/= (clojure.core/count input__15599) 3)
              (clojure.core/let
               [input__15599_nth_0__
                (clojure.core/nth input__15599 0)
                input__15599_nth_1__
                (clojure.core/nth input__15599 1)
                input__15599_nth_2__
                (clojure.core/nth input__15599 2)]
               (clojure.core/case
                input__15599_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__15599_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__15599_nth_1__)]
                  (clojure.core/let
                   [?env input__15599_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__10883__auto__
                        (CATA__FN__15664
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__15665 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__15665
                               (clojure.core/let
                                [CATA_RESULT__10883__auto__
                                 (CATA__FN__15664
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
                              return__15665))))
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
                 (state__16955))
                (state__16955)))
              (state__16955)))
            (state__16955
             []
             (if
              (clojure.core/= (clojure.core/count input__15599) 4)
              (clojure.core/let
               [input__15599_nth_0__
                (clojure.core/nth input__15599 0)
                input__15599_nth_1__
                (clojure.core/nth input__15599 1)
                input__15599_nth_2__
                (clojure.core/nth input__15599 2)]
               (clojure.core/letfn
                [(state__16957
                  []
                  (clojure.core/let
                   [input__15599_nth_3__
                    (clojure.core/nth input__15599 3)]
                   (clojure.core/case
                    input__15599_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__15599_nth_1__)
                     (clojure.core/letfn
                      [(state__16964
                        []
                        (clojure.core/case
                         input__15599_nth_1__
                         ([])
                         (clojure.core/let
                          [?next input__15599_nth_2__]
                          (clojure.core/let
                           [?env input__15599_nth_3__]
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
                         (state__16965)))
                       (state__16965
                        []
                        (clojure.core/loop
                         [search_space__16966
                          (meander.runtime.zeta/epsilon-partitions
                           2
                           input__15599_nth_1__)]
                         (if
                          (clojure.core/seq search_space__16966)
                          (clojure.core/let
                           [input__15599_nth_1___parts__
                            (clojure.core/first search_space__16966)
                            result__16967
                            (clojure.core/let
                             [input__15599_nth_1___l__
                              (clojure.core/nth
                               input__15599_nth_1___parts__
                               0)
                              input__15599_nth_1___r__
                              (clojure.core/nth
                               input__15599_nth_1___parts__
                               1)]
                             (clojure.core/letfn
                              [(state__16969
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__9744__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__15599_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__16008]
                                     (clojure.core/let
                                      [input__16008_nth_0__
                                       (clojure.core/nth
                                        input__16008
                                        0)]
                                      (clojure.core/letfn
                                       [(save__16009
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__16972
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__16008_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__16008_nth_0__)
                                        (clojure.core/let
                                         [VAL__16010
                                          (.valAt
                                           input__16008_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__16010
                                          (:group)
                                          (save__16009)
                                          (f__16972)))
                                        (f__16972)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__15599_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__15599_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__15599_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__15599_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__15599_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__15599_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__15599_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__15599_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__15599_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__16007
                                            (.valAt
                                             input__15599_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__16007
                                            (:group)
                                            (clojure.core/let
                                             [?group
                                              input__15599_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__15599_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__15599_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__15599_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10883__auto__
                                                     (CATA__FN__15664
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__10883__auto__
                                                         (CATA__FN__15664
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
                                                         (CATA__FN__15664
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__10883__auto__
                                                             (CATA__FN__15664
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
                                            (state__16970)))
                                          (state__16970))))
                                       (state__16970)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__9744__auto__)
                                   (state__16970)
                                   ret__9744__auto__))))
                               (state__16970
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__9744__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__15599_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__16019]
                                     (clojure.core/let
                                      [input__16019_nth_0__
                                       (clojure.core/nth
                                        input__16019
                                        0)]
                                      (clojure.core/letfn
                                       [(save__16020
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__16974
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__16019_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__16019_nth_0__)
                                        (clojure.core/let
                                         [VAL__16021
                                          (.valAt
                                           input__16019_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__16021
                                          (:star)
                                          (save__16020)
                                          (f__16974)))
                                        (f__16974)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__15599_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__15599_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__15599_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__15599_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__15599_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__15599_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__15599_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__15599_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__15599_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__16018
                                            (.valAt
                                             input__15599_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__16018
                                            (:star)
                                            (clojure.core/let
                                             [?group
                                              input__15599_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__15599_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__15599_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__15599_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10883__auto__
                                                     (CATA__FN__15664
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__10883__auto__
                                                         (CATA__FN__15664
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
                                                         (CATA__FN__15664
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__10883__auto__
                                                             (CATA__FN__15664
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
                              (state__16969)))]
                           (if
                            (meander.runtime.zeta/fail? result__16967)
                            (recur
                             (clojure.core/next search_space__16966))
                            result__16967))
                          (state__16958))))]
                      (state__16964))
                     (state__16958))
                    (state__16958))))
                 (state__16958
                  []
                  (clojure.core/case
                   input__15599_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__15599_nth_1__)
                    (clojure.core/let
                     [input__15599_nth_1___l__
                      (clojure.core/subvec
                       input__15599_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__15599_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__15599_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__15599_nth_1___r__
                        (clojure.core/subvec input__15599_nth_1__ 1)]
                       (clojure.core/let
                        [input__15599_nth_1___l___nth_0__
                         (clojure.core/nth input__15599_nth_1___l__ 0)]
                        (if
                         (clojure.core/map?
                          input__15599_nth_1___l___nth_0__)
                         (clojure.core/let
                          [VAL__16030
                           (.valAt
                            input__15599_nth_1___l___nth_0__
                            :tag)]
                          (clojure.core/case
                           VAL__16030
                           (:literal)
                           (clojure.core/let
                            [VAL__16031
                             (.valAt
                              input__15599_nth_1___l___nth_0__
                              :type)]
                            (if
                             (clojure.core/let
                              [x__8640__auto__ VAL__16031]
                              (clojure.core/case
                               x__8640__auto__
                               (:string :char)
                               true
                               false))
                             (clojure.core/let
                              [VAL__16032
                               (.valAt
                                input__15599_nth_1___l___nth_0__
                                :form)]
                              (clojure.core/let
                               [!forms []]
                               (clojure.core/let
                                [!forms
                                 (clojure.core/conj !forms VAL__16032)]
                                (clojure.core/loop
                                 [i__9717__auto__
                                  0
                                  coll__16975
                                  input__15599_nth_1___r__
                                  [!forms]
                                  [!forms]]
                                 (clojure.core/let
                                  [input__16033
                                   (clojure.core/subvec
                                    coll__16975
                                    0
                                    (clojure.core/min
                                     (clojure.core/count coll__16975)
                                     1))]
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count input__16033)
                                    1)
                                   (clojure.core/let
                                    [result__9718__auto__
                                     (clojure.core/let
                                      [input__16033_nth_0__
                                       (clojure.core/nth
                                        input__16033
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__16033_nth_0__)
                                       (clojure.core/let
                                        [VAL__16034
                                         (.valAt
                                          input__16033_nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__16034
                                         (:literal)
                                         (clojure.core/let
                                          [VAL__16035
                                           (.valAt
                                            input__16033_nth_0__
                                            :type)]
                                          (if
                                           (clojure.core/let
                                            [x__8640__auto__
                                             VAL__16035]
                                            (clojure.core/case
                                             x__8640__auto__
                                             (:string :char)
                                             true
                                             false))
                                           (clojure.core/let
                                            [VAL__16036
                                             (.valAt
                                              input__16033_nth_0__
                                              :form)]
                                            (clojure.core/let
                                             [!forms
                                              (clojure.core/conj
                                               !forms
                                               VAL__16036)]
                                             [!forms]))
                                           (meander.runtime.zeta/fail)))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      result__9718__auto__)
                                     (state__16959)
                                     (recur
                                      (clojure.core/inc
                                       i__9717__auto__)
                                      (clojure.core/subvec
                                       coll__16975
                                       1)
                                      result__9718__auto__)))
                                   (if
                                    (clojure.core/or
                                     (clojure.core/seq coll__16975)
                                     (clojure.core/<
                                      i__9717__auto__
                                      0))
                                    (state__16959)
                                    (if
                                     (clojure.core/map?
                                      input__15599_nth_2__)
                                     (clojure.core/let
                                      [VAL__16025
                                       (.valAt
                                        input__15599_nth_2__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__16025
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
                                       (state__16959)))
                                     (state__16959)))))))))
                             (state__16959)))
                           (state__16959)))
                         (state__16959))))
                      (state__16959)))
                    (state__16959))
                   (state__16959)))
                 (state__16959
                  []
                  (clojure.core/let
                   [input__15599_nth_3__
                    (clojure.core/nth input__15599 3)]
                   (clojure.core/case
                    input__15599_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__16976
                       []
                       (if
                        (clojure.core/vector? input__15599_nth_1__)
                        (clojure.core/let
                         [input__15599_nth_1___l__
                          (clojure.core/subvec
                           input__15599_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__15599_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__15599_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__15599_nth_1___r__
                            (clojure.core/subvec
                             input__15599_nth_1__
                             1)]
                           (clojure.core/let
                            [input__15599_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__15599_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__15599_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__16857
                               (.valAt
                                input__15599_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__16857
                               (:literal)
                               (clojure.core/letfn
                                [(state__16978
                                  []
                                  (clojure.core/let
                                   [VAL__16043
                                    (.valAt
                                     input__15599_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__16043
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__15599_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__15599_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__15599_nth_2__]
                                       (clojure.core/let
                                        [?env input__15599_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__10883__auto__
                                            (CATA__FN__15664
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__10883__auto__
                                                (CATA__FN__15664
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
                                    (state__16979))))
                                 (state__16979
                                  []
                                  (clojure.core/let
                                   [VAL__16053
                                    (.valAt
                                     input__15599_nth_1___l___nth_0__
                                     :form)]
                                   (clojure.core/let
                                    [!forms []]
                                    (clojure.core/let
                                     [!forms
                                      (clojure.core/conj
                                       !forms
                                       VAL__16053)]
                                     (clojure.core/loop
                                      [i__9717__auto__
                                       0
                                       coll__16980
                                       input__15599_nth_1___r__
                                       [!forms]
                                       [!forms]]
                                      (clojure.core/let
                                       [input__16054
                                        (clojure.core/subvec
                                         coll__16980
                                         0
                                         (clojure.core/min
                                          (clojure.core/count
                                           coll__16980)
                                          1))]
                                       (if
                                        (clojure.core/=
                                         (clojure.core/count
                                          input__16054)
                                         1)
                                        (clojure.core/let
                                         [result__9718__auto__
                                          (clojure.core/let
                                           [input__16054_nth_0__
                                            (clojure.core/nth
                                             input__16054
                                             0)]
                                           (if
                                            (clojure.core/map?
                                             input__16054_nth_0__)
                                            (clojure.core/let
                                             [VAL__16055
                                              (.valAt
                                               input__16054_nth_0__
                                               :tag)]
                                             (clojure.core/case
                                              VAL__16055
                                              (:literal)
                                              (clojure.core/let
                                               [VAL__16056
                                                (.valAt
                                                 input__16054_nth_0__
                                                 :form)]
                                               (clojure.core/let
                                                [!forms
                                                 (clojure.core/conj
                                                  !forms
                                                  VAL__16056)]
                                                [!forms]))
                                              (meander.runtime.zeta/fail)))
                                            (meander.runtime.zeta/fail)))]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           result__9718__auto__)
                                          (state__16977)
                                          (recur
                                           (clojure.core/inc
                                            i__9717__auto__)
                                           (clojure.core/subvec
                                            coll__16980
                                            1)
                                           result__9718__auto__)))
                                        (if
                                         (clojure.core/or
                                          (clojure.core/seq
                                           coll__16980)
                                          (clojure.core/<
                                           i__9717__auto__
                                           0))
                                         (state__16977)
                                         (if
                                          (clojure.core/map?
                                           input__15599_nth_2__)
                                          (clojure.core/let
                                           [VAL__16046
                                            (.valAt
                                             input__15599_nth_2__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__16046
                                            (:empty)
                                            (if
                                             (clojure.core/map?
                                              input__15599_nth_3__)
                                             (clojure.core/let
                                              [VAL__16047
                                               (.valAt
                                                input__15599_nth_3__
                                                :context)]
                                              (clojure.core/let
                                               [?context VAL__16047]
                                               (clojure.core/let
                                                [?env
                                                 input__15599_nth_3__]
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
                                             (state__16977))
                                            (state__16977)))
                                          (state__16977))))))))))]
                                (state__16978))
                               (state__16977)))
                             (state__16977))))
                          (state__16977)))
                        (state__16977)))
                      (state__16977
                       []
                       (clojure.core/let
                        [?sequence input__15599_nth_1__]
                        (clojure.core/let
                         [?next input__15599_nth_2__]
                         (if
                          (clojure.core/map? input__15599_nth_3__)
                          (clojure.core/let
                           [VAL__16060
                            (.valAt input__15599_nth_3__ :context)]
                           (clojure.core/case
                            VAL__16060
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
                            (state__16960)))
                          (state__16960)))))]
                     (state__16976))
                    (state__16960))))
                 (state__16960
                  []
                  (clojure.core/case
                   input__15599_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__15599_nth_1__]
                    (clojure.core/let
                     [?next input__15599_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__11823__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11823__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11823__auto__))))))
                   (state__16961)))
                 (state__16961
                  []
                  (clojure.core/let
                   [input__15599_nth_3__
                    (clojure.core/nth input__15599 3)]
                   (clojure.core/case
                    input__15599_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__15599_nth_1__)
                     (clojure.core/let
                      [VAL__16855 (.valAt input__15599_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__16855
                       (:cat)
                       (clojure.core/let
                        [VAL__16066
                         (.valAt input__15599_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__16066]
                         (clojure.core/let
                          [VAL__16067
                           (.valAt input__15599_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__16067)
                           (clojure.core/let
                            [VAL__16068 (.valAt VAL__16067 :tag)]
                            (clojure.core/case
                             VAL__16068
                             (:empty)
                             (clojure.core/let
                              [?right input__15599_nth_2__]
                              (clojure.core/let
                               [?env input__15599_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__10883__auto__
                                   (CATA__FN__15664
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
                             (state__16962)))
                           (state__16962)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__16072
                         (.valAt input__15599_nth_1__ :type)]
                        (clojure.core/case
                         VAL__16072
                         (:string)
                         (clojure.core/let
                          [VAL__16073
                           (.valAt input__15599_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__16073]
                           (if
                            (clojure.core/map? input__15599_nth_2__)
                            (clojure.core/let
                             [VAL__16074
                              (.valAt input__15599_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__16074
                              (:string-join)
                              (clojure.core/let
                               [VAL__16075
                                (.valAt input__15599_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__16075)
                                (clojure.core/let
                                 [VAL__16076 (.valAt VAL__16075 :tag)]
                                 (clojure.core/case
                                  VAL__16076
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__16077
                                    (.valAt VAL__16075 :type)]
                                   (clojure.core/case
                                    VAL__16077
                                    (:string)
                                    (clojure.core/let
                                     [VAL__16078
                                      (.valAt VAL__16075 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__16078]
                                      (clojure.core/let
                                       [VAL__16079
                                        (.valAt
                                         input__15599_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__16079]
                                        (if
                                         (clojure.core/map?
                                          input__15599_nth_3__)
                                         (clojure.core/let
                                          [VAL__16080
                                           (.valAt
                                            input__15599_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__16080
                                           (:string)
                                           (clojure.core/let
                                            [?env input__15599_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__10883__auto__
                                                (CATA__FN__15664
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
                                           (state__16962)))
                                         (state__16962))))))
                                    (state__16962)))
                                  (state__16962)))
                                (state__16962)))
                              (state__16962)))
                            (state__16962))))
                         (state__16962)))
                       (state__16962)))
                     (state__16962))
                    (state__16962))))
                 (state__16962
                  []
                  (clojure.core/case
                   input__15599_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__15599_nth_1__)
                    (clojure.core/let
                     [VAL__16856 (.valAt input__15599_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__16856
                      (:cat)
                      (clojure.core/let
                       [?ast input__15599_nth_1__]
                       (if
                        (clojure.core/map? input__15599_nth_2__)
                        (clojure.core/let
                         [VAL__16084
                          (.valAt input__15599_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__16084
                          (:cat)
                          (clojure.core/let
                           [VAL__16085
                            (.valAt input__15599_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__16085]
                            (clojure.core/let
                             [VAL__16086
                              (.valAt input__15599_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__16086]
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
                          (state__16963)))
                        (state__16963)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__15599_nth_1__]
                       (if
                        (clojure.core/map? input__15599_nth_2__)
                        (clojure.core/let
                         [VAL__16090
                          (.valAt input__15599_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__16090
                          (:string-cat)
                          (clojure.core/let
                           [VAL__16091
                            (.valAt input__15599_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__16091]
                            (clojure.core/let
                             [VAL__16092
                              (.valAt input__15599_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__16092]
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
                          (state__16963)))
                        (state__16963)))
                      (state__16963)))
                    (state__16963))
                   (state__16963)))
                 (state__16963
                  []
                  (clojure.core/let
                   [input__15599_nth_3__
                    (clojure.core/nth input__15599 3)]
                   (clojure.core/case
                    input__15599_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__16981
                       []
                       (if
                        (clojure.core/map? input__15599_nth_1__)
                        (clojure.core/let
                         [VAL__16860
                          (.valAt input__15599_nth_1__ :next)
                          VAL__16859
                          (.valAt input__15599_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__16859
                          (:string-cat)
                          (clojure.core/let
                           [VAL__16096
                            (.valAt input__15599_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__16096]
                            (if
                             (clojure.core/map? VAL__16860)
                             (clojure.core/let
                              [VAL__16098 (.valAt VAL__16860 :tag)]
                              (clojure.core/case
                               VAL__16098
                               (:empty)
                               (clojure.core/let
                                [?right input__15599_nth_2__]
                                (clojure.core/let
                                 [?env input__15599_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__15664
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
                               (state__16982)))
                             (state__16982))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__16102
                            (.valAt input__15599_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__16102]
                            (if
                             (clojure.core/map? VAL__16860)
                             (clojure.core/let
                              [VAL__16104 (.valAt VAL__16860 :tag)]
                              (clojure.core/case
                               VAL__16104
                               (:empty)
                               (clojure.core/let
                                [?right input__15599_nth_2__]
                                (if
                                 (clojure.core/map?
                                  input__15599_nth_3__)
                                 (clojure.core/let
                                  [VAL__16105
                                   (.valAt
                                    input__15599_nth_3__
                                    :context)]
                                  (clojure.core/case
                                   VAL__16105
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
                                   (state__16982)))
                                 (state__16982)))
                               (state__16982)))
                             (state__16982))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__16109
                            (.valAt input__15599_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__16109]
                            (clojure.core/let
                             [VAL__16110
                              (.valAt input__15599_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__16110]
                              (clojure.core/let
                               [?right-2 input__15599_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__15599_nth_3__)
                                (clojure.core/let
                                 [VAL__16111
                                  (.valAt
                                   input__15599_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__16111
                                  (:string)
                                  (clojure.core/let
                                   [?env input__15599_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__15664
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
                                  (state__16982)))
                                (state__16982)))))))
                          (state__16982)))
                        (state__16982)))
                      (state__16982
                       []
                       (clojure.core/let
                        [?left input__15599_nth_1__]
                        (if
                         (clojure.core/map? input__15599_nth_2__)
                         (clojure.core/let
                          [VAL__16114
                           (.valAt input__15599_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__16114
                           (:empty)
                           (clojure.core/let
                            [?env input__15599_nth_3__]
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
                           (state__16983)))
                         (state__16983))))
                      (state__16983
                       []
                       (if
                        (clojure.core/map? input__15599_nth_1__)
                        (clojure.core/let
                         [VAL__16858
                          (.valAt input__15599_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__16858
                          (:empty)
                          (clojure.core/let
                           [?right input__15599_nth_2__]
                           (clojure.core/let
                            [?env input__15599_nth_3__]
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
                           [VAL__16121
                            (.valAt input__15599_nth_1__ :next)]
                           (if
                            (clojure.core/map? VAL__16121)
                            (clojure.core/let
                             [VAL__16122 (.valAt VAL__16121 :tag)]
                             (clojure.core/case
                              VAL__16122
                              (:empty)
                              (clojure.core/let
                               [?left input__15599_nth_1__]
                               (clojure.core/let
                                [?right input__15599_nth_2__]
                                (clojure.core/let
                                 [?env input__15599_nth_3__]
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
                              (state__16984)))
                            (state__16984)))
                          (state__16984)))
                        (state__16984)))
                      (state__16984
                       []
                       (clojure.core/let
                        [?left input__15599_nth_1__]
                        (clojure.core/let
                         [?right input__15599_nth_2__]
                         (clojure.core/letfn
                          [(state__16985
                            []
                            (if
                             (clojure.core/map? input__15599_nth_3__)
                             (clojure.core/let
                              [VAL__16125
                               (.valAt input__15599_nth_3__ :context)]
                              (clojure.core/case
                               VAL__16125
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
                               (state__16986)))
                             (state__16986)))
                           (state__16986
                            []
                            (clojure.core/let
                             [?env input__15599_nth_3__]
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
                          (state__16985)))))]
                     (state__16981))
                    (state__16956))))]
                (state__16957)))
              (state__16956)))
            (state__16956
             []
             (if
              (clojure.core/= (clojure.core/count input__15599) 3)
              (clojure.core/let
               [input__15599_nth_0__
                (clojure.core/nth input__15599 0)
                input__15599_nth_1__
                (clojure.core/nth input__15599 1)
                input__15599_nth_2__
                (clojure.core/nth input__15599 2)]
               (clojure.core/case
                input__15599_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__15599_nth_1__)
                 (clojure.core/let
                  [VAL__16130
                   (.valAt input__15599_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__16130]
                   (clojure.core/let
                    [X__16132
                     ((clojure.core/fn
                       [m__8647__auto__]
                       (clojure.core/dissoc
                        m__8647__auto__
                        :meander.zeta/as))
                      input__15599_nth_1__)]
                    (clojure.core/let
                     [?rest X__16132]
                     (clojure.core/letfn
                      [(save__16133 [] (state__16871))
                       (f__16987
                        []
                        (clojure.core/let
                         [?env input__15599_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__10883__auto__
                              (CATA__FN__15664 [?pattern ?env])]
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
                              (CATA__FN__15664 [?rest ?env])]
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
                       (clojure.core/= ?rest input__15599_nth_1__)
                       (save__16133)
                       (f__16987)))))))
                 (state__16871))
                (state__16871)))
              (state__16871)))]
           (state__16954))
          (state__16871)))
        (state__16871
         []
         (clojure.core/letfn
          [(def__16136
            [arg__16169 ?ns]
            (clojure.core/letfn
             [(state__16988
               []
               (clojure.core/let
                [x__16170 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__16170)
                 (clojure.core/let [?env arg__16169] [?env ?ns])
                 (state__16989))))
              (state__16989
               []
               (if
                (clojure.core/map? arg__16169)
                (clojure.core/let
                 [VAL__16171 (.valAt arg__16169 :aliases)]
                 (if
                  (clojure.core/map? VAL__16171)
                  (clojure.core/let
                   [X__16173 (clojure.core/set VAL__16171)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16173))
                    (clojure.core/loop
                     [search_space__16990 (clojure.core/seq X__16173)]
                     (if
                      (clojure.core/seq search_space__16990)
                      (clojure.core/let
                       [elem__16174
                        (clojure.core/first search_space__16990)
                        result__16991
                        (clojure.core/let
                         [elem__16174_nth_0__
                          (clojure.core/nth elem__16174 0)
                          elem__16174_nth_1__
                          (clojure.core/nth elem__16174 1)]
                         (if
                          (clojure.core/symbol? elem__16174_nth_0__)
                          (clojure.core/let
                           [X__16176
                            (clojure.core/name elem__16174_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__16176)
                            (if
                             (clojure.core/symbol? elem__16174_nth_1__)
                             (clojure.core/let
                              [X__16178
                               (clojure.core/name elem__16174_nth_1__)]
                              (clojure.core/case
                               X__16178
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__16169]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16991)
                        (recur (clojure.core/next search_space__16990))
                        result__16991))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16988)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 3)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)
              input__15599_nth_2__
              (clojure.core/nth input__15599 2)]
             (clojure.core/case
              input__15599_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__15599_nth_1__)
               (clojure.core/let
                [X__16141 (clojure.core/set input__15599_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__16141))
                 (clojure.core/loop
                  [search_space__16993 (clojure.core/seq X__16141)]
                  (if
                   (clojure.core/seq search_space__16993)
                   (clojure.core/let
                    [elem__16142
                     (clojure.core/first search_space__16993)
                     result__16994
                     (clojure.core/let
                      [elem__16142_nth_0__
                       (clojure.core/nth elem__16142 0)
                       elem__16142_nth_1__
                       (clojure.core/nth elem__16142 1)]
                      (clojure.core/let
                       [*m__15627 elem__16142_nth_0__]
                       (if
                        (clojure.core/symbol? elem__16142_nth_0__)
                        (clojure.core/let
                         [X__16144
                          (clojure.core/namespace elem__16142_nth_0__)]
                         (clojure.core/let
                          [?ns X__16144]
                          (clojure.core/let
                           [X__16146
                            (clojure.core/name elem__16142_nth_0__)]
                           (if
                            (clojure.core/string? X__16146)
                            (if
                             (clojure.core/re-matches #"&.*" X__16146)
                             (clojure.core/let
                              [?pattern elem__16142_nth_1__]
                              (clojure.core/let
                               [X__16148
                                ((clojure.core/fn
                                  [m__8647__auto__]
                                  (clojure.core/dissoc
                                   m__8647__auto__
                                   *m__15627))
                                 input__15599_nth_1__)]
                               (clojure.core/let
                                [?rest X__16148]
                                (clojure.core/let
                                 [x__9580__auto__
                                  (def__16136
                                   input__15599_nth_2__
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
                                        (CATA__FN__15664
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
                                        (CATA__FN__15664
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
                     (meander.runtime.zeta/fail? result__16994)
                     (recur (clojure.core/next search_space__16993))
                     result__16994))
                   (state__16872)))
                 (state__16872)))
               (state__16872))
              (state__16872)))
            (state__16872))
           (state__16872))))
        (state__16872
         []
         (if
          (clojure.core/vector? input__15599)
          (clojure.core/letfn
           [(state__16996
             []
             (if
              (clojure.core/= (clojure.core/count input__15599) 3)
              (clojure.core/let
               [input__15599_nth_0__
                (clojure.core/nth input__15599 0)
                input__15599_nth_1__
                (clojure.core/nth input__15599 1)
                input__15599_nth_2__
                (clojure.core/nth input__15599 2)]
               (clojure.core/case
                input__15599_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__16998
                   []
                   (if
                    (clojure.core/map? input__15599_nth_1__)
                    (clojure.core/let
                     [X__16192 (clojure.core/set input__15599_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__16192))
                      (clojure.core/loop
                       [search_space__17000
                        (clojure.core/seq X__16192)]
                       (if
                        (clojure.core/seq search_space__17000)
                        (clojure.core/let
                         [elem__16193
                          (clojure.core/first search_space__17000)
                          result__17001
                          (clojure.core/let
                           [elem__16193_nth_0__
                            (clojure.core/nth elem__16193 0)
                            elem__16193_nth_1__
                            (clojure.core/nth elem__16193 1)]
                           (clojure.core/let
                            [?key-pattern elem__16193_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__16193_nth_1__]
                             (clojure.core/let
                              [X__16195
                               ((clojure.core/fn
                                 [m__8647__auto__]
                                 (clojure.core/dissoc
                                  m__8647__auto__
                                  ?key-pattern))
                                input__15599_nth_1__)]
                              (clojure.core/let
                               [?rest X__16195]
                               (clojure.core/let
                                [?env input__15599_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__15664
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
                                     (CATA__FN__15664
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
                                     (CATA__FN__15664
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
                          (meander.runtime.zeta/fail? result__17001)
                          (recur
                           (clojure.core/next search_space__17000))
                          result__17001))
                        (state__16999)))
                      (state__16999)))
                    (state__16999)))
                  (state__16999
                   []
                   (if
                    (clojure.core/map? input__15599_nth_1__)
                    (clojure.core/let
                     [?env input__15599_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__11823__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11823__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11823__auto__)))))
                    (state__16997)))]
                 (state__16998))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__17003
                   []
                   (if
                    (clojure.core/vector? input__15599_nth_1__)
                    (clojure.core/case
                     input__15599_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__15599_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__11823__auto__
                        (if
                         (meander.runtime.zeta/fail? e__11823__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11823__auto__)))))
                     (state__17004))
                    (state__17004)))
                  (state__17004
                   []
                   (if
                    (clojure.core/vector? input__15599_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__15599_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__15599_nth_2__]
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
                     (state__17005))
                    (state__17005)))
                  (state__17005
                   []
                   (if
                    (clojure.core/vector? input__15599_nth_1__)
                    (clojure.core/let
                     [input__15599_nth_1___l__
                      (clojure.core/subvec
                       input__15599_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__15599_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__15599_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__15599_nth_1___r__
                        (clojure.core/subvec input__15599_nth_1__ 2)]
                       (clojure.core/let
                        [input__15599_nth_1___l___nth_0__
                         (clojure.core/nth input__15599_nth_1___l__ 0)
                         input__15599_nth_1___l___nth_1__
                         (clojure.core/nth input__15599_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__15599_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__16209
                           (clojure.core/namespace
                            input__15599_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__16211
                            (clojure.core/name
                             input__15599_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__16211)
                            (if
                             (clojure.core/re-matches #"%.+" X__16211)
                             (clojure.core/let
                              [?symbol
                               input__15599_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__15599_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__15599_nth_1___r__]
                                (clojure.core/let
                                 [?env input__15599_nth_2__]
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
                                         (CATA__FN__15664
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
                                       (CATA__FN__15664
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
                             (state__17006))
                            (state__17006))))
                         (state__17006))))
                      (state__17006)))
                    (state__17006)))
                  (state__17006
                   []
                   (if
                    (clojure.core/vector? input__15599_nth_1__)
                    (clojure.core/let
                     [input__15599_nth_1___l__
                      (clojure.core/subvec
                       input__15599_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__15599_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__15599_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__15599_nth_1___r__
                        (clojure.core/subvec input__15599_nth_1__ 2)]
                       (clojure.core/let
                        [input__15599_nth_1___l___nth_0__
                         (clojure.core/nth input__15599_nth_1___l__ 0)
                         input__15599_nth_1___l___nth_1__
                         (clojure.core/nth input__15599_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__15599_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__15599_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__15599_nth_1___r__]
                           (clojure.core/let
                            [?env input__15599_nth_2__]
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
                      (state__16997)))
                    (state__16997)))]
                 (state__17003))
                (state__16997)))
              (state__16997)))
            (state__16997
             []
             (if
              (clojure.core/= (clojure.core/count input__15599) 2)
              (clojure.core/let
               [input__15599_nth_0__
                (clojure.core/nth input__15599 0)
                input__15599_nth_1__
                (clojure.core/nth input__15599 1)]
               (if
                (clojure.core/vector? input__15599_nth_0__)
                (clojure.core/let
                 [?sequence input__15599_nth_0__]
                 (clojure.core/let
                  [?form input__15599_nth_0__]
                  (clojure.core/let
                   [?env input__15599_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__10883__auto__
                        (CATA__FN__15664
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
                (state__16873)))
              (state__16873)))]
           (state__16996))
          (state__16873)))
        (state__16873
         []
         (clojure.core/letfn
          [(def__16221
            [arg__16244 ?__15600]
            (clojure.core/letfn
             [(state__17007
               []
               (clojure.core/let
                [x__16245 "meander.zeta"]
                (if
                 (clojure.core/= ?__15600 x__16245)
                 [?__15600]
                 (state__17008))))
              (state__17008
               []
               (if
                (clojure.core/map? arg__16244)
                (clojure.core/let
                 [VAL__16246 (.valAt arg__16244 :aliases)]
                 (if
                  (clojure.core/map? VAL__16246)
                  (clojure.core/let
                   [X__16248 (clojure.core/set VAL__16246)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16248))
                    (clojure.core/loop
                     [search_space__17009 (clojure.core/seq X__16248)]
                     (if
                      (clojure.core/seq search_space__17009)
                      (clojure.core/let
                       [elem__16249
                        (clojure.core/first search_space__17009)
                        result__17010
                        (clojure.core/let
                         [elem__16249_nth_0__
                          (clojure.core/nth elem__16249 0)
                          elem__16249_nth_1__
                          (clojure.core/nth elem__16249 1)]
                         (if
                          (clojure.core/symbol? elem__16249_nth_0__)
                          (clojure.core/let
                           [X__16251
                            (clojure.core/name elem__16249_nth_0__)]
                           (if
                            (clojure.core/= ?__15600 X__16251)
                            (if
                             (clojure.core/symbol? elem__16249_nth_1__)
                             (clojure.core/let
                              [X__16253
                               (clojure.core/name elem__16249_nth_1__)]
                              (clojure.core/case
                               X__16253
                               ("meander.zeta")
                               [?__15600]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17010)
                        (recur (clojure.core/next search_space__17009))
                        result__17010))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17007)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16231
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15600 X__16231]
                     (clojure.core/let
                      [X__16233
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16233
                       ("*")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16221 input__15599_nth_1__ ?__15600)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16874)
                         (clojure.core/let
                          [[?__15600] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (clojure.core/let
                               [input__15599_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__15599_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__15599_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__15599_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__15599_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__15599_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__15599_nth_0__]
                                   (clojure.core/let
                                    [?env input__15599_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__15664
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
                                (state__16874)))
                              (state__16874)))
                            (state__16874))
                           (state__16874)))))
                       (state__16874)))))
                   (state__16874))))
                (state__16874)))
              (state__16874)))
            (state__16874))
           (state__16874))))
        (state__16874
         []
         (clojure.core/letfn
          [(def__16255
            [arg__16278 ?__15601]
            (clojure.core/letfn
             [(state__17012
               []
               (clojure.core/let
                [x__16279 "meander.zeta"]
                (if
                 (clojure.core/= ?__15601 x__16279)
                 [?__15601]
                 (state__17013))))
              (state__17013
               []
               (if
                (clojure.core/map? arg__16278)
                (clojure.core/let
                 [VAL__16280 (.valAt arg__16278 :aliases)]
                 (if
                  (clojure.core/map? VAL__16280)
                  (clojure.core/let
                   [X__16282 (clojure.core/set VAL__16280)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16282))
                    (clojure.core/loop
                     [search_space__17014 (clojure.core/seq X__16282)]
                     (if
                      (clojure.core/seq search_space__17014)
                      (clojure.core/let
                       [elem__16283
                        (clojure.core/first search_space__17014)
                        result__17015
                        (clojure.core/let
                         [elem__16283_nth_0__
                          (clojure.core/nth elem__16283 0)
                          elem__16283_nth_1__
                          (clojure.core/nth elem__16283 1)]
                         (if
                          (clojure.core/symbol? elem__16283_nth_0__)
                          (clojure.core/let
                           [X__16285
                            (clojure.core/name elem__16283_nth_0__)]
                           (if
                            (clojure.core/= ?__15601 X__16285)
                            (if
                             (clojure.core/symbol? elem__16283_nth_1__)
                             (clojure.core/let
                              [X__16287
                               (clojure.core/name elem__16283_nth_1__)]
                              (clojure.core/case
                               X__16287
                               ("meander.zeta")
                               [?__15601]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17015)
                        (recur (clojure.core/next search_space__17014))
                        result__17015))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17012)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16265
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15601 X__16265]
                     (clojure.core/let
                      [X__16267
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16267
                       ("<>")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16255 input__15599_nth_1__ ?__15601)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16875)
                         (clojure.core/let
                          [[?__15601] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (clojure.core/let
                               [input__15599_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__15599_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__15599_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__15599_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__15599_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__15599_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__15599_nth_0__]
                                   (clojure.core/let
                                    [?env input__15599_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__15664
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
                                (state__16875)))
                              (state__16875)))
                            (state__16875))
                           (state__16875)))))
                       (state__16875)))))
                   (state__16875))))
                (state__16875)))
              (state__16875)))
            (state__16875))
           (state__16875))))
        (state__16875
         []
         (clojure.core/letfn
          [(def__16289
            [arg__16312 ?__15602]
            (clojure.core/letfn
             [(state__17017
               []
               (clojure.core/let
                [x__16313 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__15602 x__16313)
                 [?__15602]
                 (state__17018))))
              (state__17018
               []
               (if
                (clojure.core/map? arg__16312)
                (clojure.core/let
                 [VAL__16314 (.valAt arg__16312 :aliases)]
                 (if
                  (clojure.core/map? VAL__16314)
                  (clojure.core/let
                   [X__16316 (clojure.core/set VAL__16314)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16316))
                    (clojure.core/loop
                     [search_space__17019 (clojure.core/seq X__16316)]
                     (if
                      (clojure.core/seq search_space__17019)
                      (clojure.core/let
                       [elem__16317
                        (clojure.core/first search_space__17019)
                        result__17020
                        (clojure.core/let
                         [elem__16317_nth_0__
                          (clojure.core/nth elem__16317 0)
                          elem__16317_nth_1__
                          (clojure.core/nth elem__16317 1)]
                         (if
                          (clojure.core/symbol? elem__16317_nth_0__)
                          (clojure.core/let
                           [X__16319
                            (clojure.core/name elem__16317_nth_0__)]
                           (if
                            (clojure.core/= ?__15602 X__16319)
                            (if
                             (clojure.core/symbol? elem__16317_nth_1__)
                             (clojure.core/let
                              [X__16321
                               (clojure.core/name elem__16317_nth_1__)]
                              (clojure.core/case
                               X__16321
                               ("meander.math.zeta")
                               [?__15602]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17020)
                        (recur (clojure.core/next search_space__17019))
                        result__17020))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17017)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16299
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15602 X__16299]
                     (clojure.core/let
                      [X__16301
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16301
                       ("+")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16289 input__15599_nth_1__ ?__15602)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16876)
                         (clojure.core/let
                          [[?__15602] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__15599_nth_0__)
                                3)
                               (clojure.core/let
                                [input__15599_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  1)
                                 input__15599_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__15599_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__15599_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__15599_nth_0__]
                                   (clojure.core/let
                                    [?env input__15599_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/+,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__15664 [?a ?env])]
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
                                         (CATA__FN__15664 [?b ?env])]
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
                               (state__16876))
                              (state__16876)))
                            (state__16876))
                           (state__16876)))))
                       (state__16876)))))
                   (state__16876))))
                (state__16876)))
              (state__16876)))
            (state__16876))
           (state__16876))))
        (state__16876
         []
         (clojure.core/letfn
          [(def__16323
            [arg__16346 ?__15603]
            (clojure.core/letfn
             [(state__17022
               []
               (clojure.core/let
                [x__16347 "meander.zeta"]
                (if
                 (clojure.core/= ?__15603 x__16347)
                 [?__15603]
                 (state__17023))))
              (state__17023
               []
               (if
                (clojure.core/map? arg__16346)
                (clojure.core/let
                 [VAL__16348 (.valAt arg__16346 :aliases)]
                 (if
                  (clojure.core/map? VAL__16348)
                  (clojure.core/let
                   [X__16350 (clojure.core/set VAL__16348)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16350))
                    (clojure.core/loop
                     [search_space__17024 (clojure.core/seq X__16350)]
                     (if
                      (clojure.core/seq search_space__17024)
                      (clojure.core/let
                       [elem__16351
                        (clojure.core/first search_space__17024)
                        result__17025
                        (clojure.core/let
                         [elem__16351_nth_0__
                          (clojure.core/nth elem__16351 0)
                          elem__16351_nth_1__
                          (clojure.core/nth elem__16351 1)]
                         (if
                          (clojure.core/symbol? elem__16351_nth_0__)
                          (clojure.core/let
                           [X__16353
                            (clojure.core/name elem__16351_nth_0__)]
                           (if
                            (clojure.core/= ?__15603 X__16353)
                            (if
                             (clojure.core/symbol? elem__16351_nth_1__)
                             (clojure.core/let
                              [X__16355
                               (clojure.core/name elem__16351_nth_1__)]
                              (clojure.core/case
                               X__16355
                               ("meander.zeta")
                               [?__15603]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17025)
                        (recur (clojure.core/next search_space__17024))
                        result__17025))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17022)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16333
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15603 X__16333]
                     (clojure.core/let
                      [X__16335
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16335
                       ("with")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16323 input__15599_nth_1__ ?__15603)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16877)
                         (clojure.core/let
                          [[?__15603] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__15599_nth_0__)
                                3)
                               (clojure.core/let
                                [input__15599_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  1)
                                 input__15599_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__15599_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__15599_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__15599_nth_0__]
                                   (clojure.core/let
                                    [?env input__15599_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__10883__auto__
                                          (CATA__FN__15664
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
                                         (CATA__FN__15664
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
                               (state__16877))
                              (state__16877)))
                            (state__16877))
                           (state__16877)))))
                       (state__16877)))))
                   (state__16877))))
                (state__16877)))
              (state__16877)))
            (state__16877))
           (state__16877))))
        (state__16877
         []
         (clojure.core/letfn
          [(def__16357
            [arg__16380 ?__15604]
            (clojure.core/letfn
             [(state__17027
               []
               (clojure.core/let
                [x__16381 "meander.zeta"]
                (if
                 (clojure.core/= ?__15604 x__16381)
                 [?__15604]
                 (state__17028))))
              (state__17028
               []
               (if
                (clojure.core/map? arg__16380)
                (clojure.core/let
                 [VAL__16382 (.valAt arg__16380 :aliases)]
                 (if
                  (clojure.core/map? VAL__16382)
                  (clojure.core/let
                   [X__16384 (clojure.core/set VAL__16382)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16384))
                    (clojure.core/loop
                     [search_space__17029 (clojure.core/seq X__16384)]
                     (if
                      (clojure.core/seq search_space__17029)
                      (clojure.core/let
                       [elem__16385
                        (clojure.core/first search_space__17029)
                        result__17030
                        (clojure.core/let
                         [elem__16385_nth_0__
                          (clojure.core/nth elem__16385 0)
                          elem__16385_nth_1__
                          (clojure.core/nth elem__16385 1)]
                         (if
                          (clojure.core/symbol? elem__16385_nth_0__)
                          (clojure.core/let
                           [X__16387
                            (clojure.core/name elem__16385_nth_0__)]
                           (if
                            (clojure.core/= ?__15604 X__16387)
                            (if
                             (clojure.core/symbol? elem__16385_nth_1__)
                             (clojure.core/let
                              [X__16389
                               (clojure.core/name elem__16385_nth_1__)]
                              (clojure.core/case
                               X__16389
                               ("meander.zeta")
                               [?__15604]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17030)
                        (recur (clojure.core/next search_space__17029))
                        result__17030))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17027)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16367
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15604 X__16367]
                     (clojure.core/let
                      [X__16369
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16369
                       ("apply")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16357 input__15599_nth_1__ ?__15604)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16878)
                         (clojure.core/let
                          [[?__15604] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__15599_nth_0__)
                                3)
                               (clojure.core/let
                                [input__15599_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  1)
                                 input__15599_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__15599_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__15599_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__15599_nth_0__]
                                   (clojure.core/let
                                    [?env input__15599_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__15664
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
                               (state__16878))
                              (state__16878)))
                            (state__16878))
                           (state__16878)))))
                       (state__16878)))))
                   (state__16878))))
                (state__16878)))
              (state__16878)))
            (state__16878))
           (state__16878))))
        (state__16878
         []
         (clojure.core/letfn
          [(def__16391
            [arg__16414 ?__15605]
            (clojure.core/letfn
             [(state__17032
               []
               (clojure.core/let
                [x__16415 "meander.zeta"]
                (if
                 (clojure.core/= ?__15605 x__16415)
                 [?__15605]
                 (state__17033))))
              (state__17033
               []
               (if
                (clojure.core/map? arg__16414)
                (clojure.core/let
                 [VAL__16416 (.valAt arg__16414 :aliases)]
                 (if
                  (clojure.core/map? VAL__16416)
                  (clojure.core/let
                   [X__16418 (clojure.core/set VAL__16416)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16418))
                    (clojure.core/loop
                     [search_space__17034 (clojure.core/seq X__16418)]
                     (if
                      (clojure.core/seq search_space__17034)
                      (clojure.core/let
                       [elem__16419
                        (clojure.core/first search_space__17034)
                        result__17035
                        (clojure.core/let
                         [elem__16419_nth_0__
                          (clojure.core/nth elem__16419 0)
                          elem__16419_nth_1__
                          (clojure.core/nth elem__16419 1)]
                         (if
                          (clojure.core/symbol? elem__16419_nth_0__)
                          (clojure.core/let
                           [X__16421
                            (clojure.core/name elem__16419_nth_0__)]
                           (if
                            (clojure.core/= ?__15605 X__16421)
                            (if
                             (clojure.core/symbol? elem__16419_nth_1__)
                             (clojure.core/let
                              [X__16423
                               (clojure.core/name elem__16419_nth_1__)]
                              (clojure.core/case
                               X__16423
                               ("meander.zeta")
                               [?__15605]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17035)
                        (recur (clojure.core/next search_space__17034))
                        result__17035))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17032)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16401
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15605 X__16401]
                     (clojure.core/let
                      [X__16403
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16403
                       ("and")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16391 input__15599_nth_1__ ?__15605)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16879)
                         (clojure.core/let
                          [[?__15605] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__15599_nth_0__)
                                3)
                               (clojure.core/let
                                [input__15599_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  1)
                                 input__15599_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__15599_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__15599_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__15599_nth_0__]
                                   (clojure.core/let
                                    [?env input__15599_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__15664
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
                                         (CATA__FN__15664
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
                               (state__16879))
                              (state__16879)))
                            (state__16879))
                           (state__16879)))))
                       (state__16879)))))
                   (state__16879))))
                (state__16879)))
              (state__16879)))
            (state__16879))
           (state__16879))))
        (state__16879
         []
         (clojure.core/letfn
          [(def__16425
            [arg__16448 ?__15606]
            (clojure.core/letfn
             [(state__17037
               []
               (clojure.core/let
                [x__16449 "meander.zeta"]
                (if
                 (clojure.core/= ?__15606 x__16449)
                 [?__15606]
                 (state__17038))))
              (state__17038
               []
               (if
                (clojure.core/map? arg__16448)
                (clojure.core/let
                 [VAL__16450 (.valAt arg__16448 :aliases)]
                 (if
                  (clojure.core/map? VAL__16450)
                  (clojure.core/let
                   [X__16452 (clojure.core/set VAL__16450)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16452))
                    (clojure.core/loop
                     [search_space__17039 (clojure.core/seq X__16452)]
                     (if
                      (clojure.core/seq search_space__17039)
                      (clojure.core/let
                       [elem__16453
                        (clojure.core/first search_space__17039)
                        result__17040
                        (clojure.core/let
                         [elem__16453_nth_0__
                          (clojure.core/nth elem__16453 0)
                          elem__16453_nth_1__
                          (clojure.core/nth elem__16453 1)]
                         (if
                          (clojure.core/symbol? elem__16453_nth_0__)
                          (clojure.core/let
                           [X__16455
                            (clojure.core/name elem__16453_nth_0__)]
                           (if
                            (clojure.core/= ?__15606 X__16455)
                            (if
                             (clojure.core/symbol? elem__16453_nth_1__)
                             (clojure.core/let
                              [X__16457
                               (clojure.core/name elem__16453_nth_1__)]
                              (clojure.core/case
                               X__16457
                               ("meander.zeta")
                               [?__15606]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17040)
                        (recur (clojure.core/next search_space__17039))
                        result__17040))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17037)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16435
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15606 X__16435]
                     (clojure.core/let
                      [X__16437
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16437
                       ("cata")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16425 input__15599_nth_1__ ?__15606)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16880)
                         (clojure.core/let
                          [[?__15606] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__15599_nth_0__)
                                2)
                               (clojure.core/let
                                [input__15599_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__15599_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__15599_nth_0__]
                                  (clojure.core/let
                                   [?env input__15599_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__15664
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
                               (state__16880))
                              (state__16880)))
                            (state__16880))
                           (state__16880)))))
                       (state__16880)))))
                   (state__16880))))
                (state__16880)))
              (state__16880)))
            (state__16880))
           (state__16880))))
        (state__16880
         []
         (clojure.core/letfn
          [(def__16459
            [arg__16482 ?__15607]
            (clojure.core/letfn
             [(state__17042
               []
               (clojure.core/let
                [x__16483 "meander.zeta"]
                (if
                 (clojure.core/= ?__15607 x__16483)
                 [?__15607]
                 (state__17043))))
              (state__17043
               []
               (if
                (clojure.core/map? arg__16482)
                (clojure.core/let
                 [VAL__16484 (.valAt arg__16482 :aliases)]
                 (if
                  (clojure.core/map? VAL__16484)
                  (clojure.core/let
                   [X__16486 (clojure.core/set VAL__16484)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16486))
                    (clojure.core/loop
                     [search_space__17044 (clojure.core/seq X__16486)]
                     (if
                      (clojure.core/seq search_space__17044)
                      (clojure.core/let
                       [elem__16487
                        (clojure.core/first search_space__17044)
                        result__17045
                        (clojure.core/let
                         [elem__16487_nth_0__
                          (clojure.core/nth elem__16487 0)
                          elem__16487_nth_1__
                          (clojure.core/nth elem__16487 1)]
                         (if
                          (clojure.core/symbol? elem__16487_nth_0__)
                          (clojure.core/let
                           [X__16489
                            (clojure.core/name elem__16487_nth_0__)]
                           (if
                            (clojure.core/= ?__15607 X__16489)
                            (if
                             (clojure.core/symbol? elem__16487_nth_1__)
                             (clojure.core/let
                              [X__16491
                               (clojure.core/name elem__16487_nth_1__)]
                              (clojure.core/case
                               X__16491
                               ("meander.zeta")
                               [?__15607]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17045)
                        (recur (clojure.core/next search_space__17044))
                        result__17045))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17042)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16469
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15607 X__16469]
                     (clojure.core/let
                      [X__16471
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16471
                       ("fold")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16459 input__15599_nth_1__ ?__15607)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16881)
                         (clojure.core/let
                          [[?__15607] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__15599_nth_0__)
                                4)
                               (clojure.core/let
                                [input__15599_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  1)
                                 input__15599_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  2)
                                 input__15599_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__15599_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__15599_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__15599_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__15599_nth_0__]
                                    (clojure.core/let
                                     [?env input__15599_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__15664
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__15664
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
                                             (CATA__FN__15664
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
                               (state__16881))
                              (state__16881)))
                            (state__16881))
                           (state__16881)))))
                       (state__16881)))))
                   (state__16881))))
                (state__16881)))
              (state__16881)))
            (state__16881))
           (state__16881))))
        (state__16881
         []
         (if
          (clojure.core/vector? input__15599)
          (if
           (clojure.core/= (clojure.core/count input__15599) 5)
           (clojure.core/let
            [input__15599_nth_0__
             (clojure.core/nth input__15599 0)
             input__15599_nth_1__
             (clojure.core/nth input__15599 1)
             input__15599_nth_2__
             (clojure.core/nth input__15599 2)
             input__15599_nth_3__
             (clojure.core/nth input__15599 3)
             input__15599_nth_4__
             (clojure.core/nth input__15599 4)]
            (clojure.core/case
             input__15599_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__15599_nth_1__)
              (clojure.core/let
               [VAL__16494 (.valAt input__15599_nth_1__ :tag)]
               (clojure.core/case
                VAL__16494
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__15599_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__15599_nth_2__]
                  (clojure.core/let
                   [?fold-function input__15599_nth_3__]
                   (clojure.core/let
                    [?form input__15599_nth_4__]
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
                (state__16882)))
              (state__16882))
             (state__16882)))
           (state__16882))
          (state__16882)))
        (state__16882
         []
         (clojure.core/letfn
          [(def__16496
            [arg__16519 ?__15608]
            (clojure.core/letfn
             [(state__17047
               []
               (clojure.core/let
                [x__16520 "meander.zeta"]
                (if
                 (clojure.core/= ?__15608 x__16520)
                 [?__15608]
                 (state__17048))))
              (state__17048
               []
               (if
                (clojure.core/map? arg__16519)
                (clojure.core/let
                 [VAL__16521 (.valAt arg__16519 :aliases)]
                 (if
                  (clojure.core/map? VAL__16521)
                  (clojure.core/let
                   [X__16523 (clojure.core/set VAL__16521)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16523))
                    (clojure.core/loop
                     [search_space__17049 (clojure.core/seq X__16523)]
                     (if
                      (clojure.core/seq search_space__17049)
                      (clojure.core/let
                       [elem__16524
                        (clojure.core/first search_space__17049)
                        result__17050
                        (clojure.core/let
                         [elem__16524_nth_0__
                          (clojure.core/nth elem__16524 0)
                          elem__16524_nth_1__
                          (clojure.core/nth elem__16524 1)]
                         (if
                          (clojure.core/symbol? elem__16524_nth_0__)
                          (clojure.core/let
                           [X__16526
                            (clojure.core/name elem__16524_nth_0__)]
                           (if
                            (clojure.core/= ?__15608 X__16526)
                            (if
                             (clojure.core/symbol? elem__16524_nth_1__)
                             (clojure.core/let
                              [X__16528
                               (clojure.core/name elem__16524_nth_1__)]
                              (clojure.core/case
                               X__16528
                               ("meander.zeta")
                               [?__15608]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17050)
                        (recur (clojure.core/next search_space__17049))
                        result__17050))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17047)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16506
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15608 X__16506]
                     (clojure.core/let
                      [X__16508
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16508
                       ("let")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16496 input__15599_nth_1__ ?__15608)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16883)
                         (clojure.core/let
                          [[?__15608] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__15599_nth_0__)
                                3)
                               (clojure.core/let
                                [input__15599_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  0)
                                 input__15599_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  1)
                                 input__15599_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__15599_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__15599_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next input__15599_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__15599_nth_0__]
                                    (clojure.core/let
                                     [?env input__15599_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__10883__auto__
                                          (CATA__FN__15664
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
                                          (CATA__FN__15664
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
                               (state__16883))
                              (state__16883)))
                            (state__16883))
                           (state__16883)))))
                       (state__16883)))))
                   (state__16883))))
                (state__16883)))
              (state__16883)))
            (state__16883))
           (state__16883))))
        (state__16883
         []
         (clojure.core/letfn
          [(def__16530
            [arg__16553 ?__15609]
            (clojure.core/letfn
             [(state__17052
               []
               (clojure.core/let
                [x__16554 "meander.zeta"]
                (if
                 (clojure.core/= ?__15609 x__16554)
                 [?__15609]
                 (state__17053))))
              (state__17053
               []
               (if
                (clojure.core/map? arg__16553)
                (clojure.core/let
                 [VAL__16555 (.valAt arg__16553 :aliases)]
                 (if
                  (clojure.core/map? VAL__16555)
                  (clojure.core/let
                   [X__16557 (clojure.core/set VAL__16555)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16557))
                    (clojure.core/loop
                     [search_space__17054 (clojure.core/seq X__16557)]
                     (if
                      (clojure.core/seq search_space__17054)
                      (clojure.core/let
                       [elem__16558
                        (clojure.core/first search_space__17054)
                        result__17055
                        (clojure.core/let
                         [elem__16558_nth_0__
                          (clojure.core/nth elem__16558 0)
                          elem__16558_nth_1__
                          (clojure.core/nth elem__16558 1)]
                         (if
                          (clojure.core/symbol? elem__16558_nth_0__)
                          (clojure.core/let
                           [X__16560
                            (clojure.core/name elem__16558_nth_0__)]
                           (if
                            (clojure.core/= ?__15609 X__16560)
                            (if
                             (clojure.core/symbol? elem__16558_nth_1__)
                             (clojure.core/let
                              [X__16562
                               (clojure.core/name elem__16558_nth_1__)]
                              (clojure.core/case
                               X__16562
                               ("meander.zeta")
                               [?__15609]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17055)
                        (recur (clojure.core/next search_space__17054))
                        result__17055))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17052)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16540
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15609 X__16540]
                     (clojure.core/let
                      [X__16542
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16542
                       ("not")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16530 input__15599_nth_1__ ?__15609)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16884)
                         (clojure.core/let
                          [[?__15609] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__15599_nth_0__)
                                2)
                               (clojure.core/let
                                [input__15599_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__15599_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__15599_nth_0__]
                                  (clojure.core/let
                                   [?env input__15599_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__15664
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
                               (state__16884))
                              (state__16884)))
                            (state__16884))
                           (state__16884)))))
                       (state__16884)))))
                   (state__16884))))
                (state__16884)))
              (state__16884)))
            (state__16884))
           (state__16884))))
        (state__16884
         []
         (clojure.core/letfn
          [(def__16564
            [arg__16587 ?__15610]
            (clojure.core/letfn
             [(state__17057
               []
               (clojure.core/let
                [x__16588 "meander.zeta"]
                (if
                 (clojure.core/= ?__15610 x__16588)
                 [?__15610]
                 (state__17058))))
              (state__17058
               []
               (if
                (clojure.core/map? arg__16587)
                (clojure.core/let
                 [VAL__16589 (.valAt arg__16587 :aliases)]
                 (if
                  (clojure.core/map? VAL__16589)
                  (clojure.core/let
                   [X__16591 (clojure.core/set VAL__16589)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16591))
                    (clojure.core/loop
                     [search_space__17059 (clojure.core/seq X__16591)]
                     (if
                      (clojure.core/seq search_space__17059)
                      (clojure.core/let
                       [elem__16592
                        (clojure.core/first search_space__17059)
                        result__17060
                        (clojure.core/let
                         [elem__16592_nth_0__
                          (clojure.core/nth elem__16592 0)
                          elem__16592_nth_1__
                          (clojure.core/nth elem__16592 1)]
                         (if
                          (clojure.core/symbol? elem__16592_nth_0__)
                          (clojure.core/let
                           [X__16594
                            (clojure.core/name elem__16592_nth_0__)]
                           (if
                            (clojure.core/= ?__15610 X__16594)
                            (if
                             (clojure.core/symbol? elem__16592_nth_1__)
                             (clojure.core/let
                              [X__16596
                               (clojure.core/name elem__16592_nth_1__)]
                              (clojure.core/case
                               X__16596
                               ("meander.zeta")
                               [?__15610]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17060)
                        (recur (clojure.core/next search_space__17059))
                        result__17060))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17057)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16574
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15610 X__16574]
                     (clojure.core/let
                      [X__16576
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16576
                       ("or")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16564 input__15599_nth_1__ ?__15610)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16885)
                         (clojure.core/let
                          [[?__15610] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__15599_nth_0__)
                                3)
                               (clojure.core/let
                                [input__15599_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  1)
                                 input__15599_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__15599_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__15599_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__15599_nth_0__]
                                   (clojure.core/let
                                    [?env input__15599_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__15664
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
                                         (CATA__FN__15664
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
                               (state__16885))
                              (state__16885)))
                            (state__16885))
                           (state__16885)))))
                       (state__16885)))))
                   (state__16885))))
                (state__16885)))
              (state__16885)))
            (state__16885))
           (state__16885))))
        (state__16885
         []
         (clojure.core/letfn
          [(def__16598
            [arg__16621 ?__15611]
            (clojure.core/letfn
             [(state__17062
               []
               (clojure.core/let
                [x__16622 "meander.zeta"]
                (if
                 (clojure.core/= ?__15611 x__16622)
                 [?__15611]
                 (state__17063))))
              (state__17063
               []
               (if
                (clojure.core/map? arg__16621)
                (clojure.core/let
                 [VAL__16623 (.valAt arg__16621 :aliases)]
                 (if
                  (clojure.core/map? VAL__16623)
                  (clojure.core/let
                   [X__16625 (clojure.core/set VAL__16623)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16625))
                    (clojure.core/loop
                     [search_space__17064 (clojure.core/seq X__16625)]
                     (if
                      (clojure.core/seq search_space__17064)
                      (clojure.core/let
                       [elem__16626
                        (clojure.core/first search_space__17064)
                        result__17065
                        (clojure.core/let
                         [elem__16626_nth_0__
                          (clojure.core/nth elem__16626 0)
                          elem__16626_nth_1__
                          (clojure.core/nth elem__16626 1)]
                         (if
                          (clojure.core/symbol? elem__16626_nth_0__)
                          (clojure.core/let
                           [X__16628
                            (clojure.core/name elem__16626_nth_0__)]
                           (if
                            (clojure.core/= ?__15611 X__16628)
                            (if
                             (clojure.core/symbol? elem__16626_nth_1__)
                             (clojure.core/let
                              [X__16630
                               (clojure.core/name elem__16626_nth_1__)]
                              (clojure.core/case
                               X__16630
                               ("meander.zeta")
                               [?__15611]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17065)
                        (recur (clojure.core/next search_space__17064))
                        result__17065))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17062)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16608
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15611 X__16608]
                     (clojure.core/let
                      [X__16610
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16610
                       ("re")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16598 input__15599_nth_1__ ?__15611)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16886)
                         (clojure.core/let
                          [[?__15611] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__15599_nth_0__)
                                2)
                               (clojure.core/let
                                [input__15599_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__15599_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__15599_nth_0__]
                                  (clojure.core/let
                                   [?env input__15599_nth_1__]
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
                               (state__16886))
                              (state__16886)))
                            (state__16886))
                           (state__16886)))))
                       (state__16886)))))
                   (state__16886))))
                (state__16886)))
              (state__16886)))
            (state__16886))
           (state__16886))))
        (state__16886
         []
         (clojure.core/letfn
          [(def__16632
            [arg__16655 ?__15612]
            (clojure.core/letfn
             [(state__17067
               []
               (clojure.core/let
                [x__16656 "meander.zeta"]
                (if
                 (clojure.core/= ?__15612 x__16656)
                 [?__15612]
                 (state__17068))))
              (state__17068
               []
               (if
                (clojure.core/map? arg__16655)
                (clojure.core/let
                 [VAL__16657 (.valAt arg__16655 :aliases)]
                 (if
                  (clojure.core/map? VAL__16657)
                  (clojure.core/let
                   [X__16659 (clojure.core/set VAL__16657)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16659))
                    (clojure.core/loop
                     [search_space__17069 (clojure.core/seq X__16659)]
                     (if
                      (clojure.core/seq search_space__17069)
                      (clojure.core/let
                       [elem__16660
                        (clojure.core/first search_space__17069)
                        result__17070
                        (clojure.core/let
                         [elem__16660_nth_0__
                          (clojure.core/nth elem__16660 0)
                          elem__16660_nth_1__
                          (clojure.core/nth elem__16660 1)]
                         (if
                          (clojure.core/symbol? elem__16660_nth_0__)
                          (clojure.core/let
                           [X__16662
                            (clojure.core/name elem__16660_nth_0__)]
                           (if
                            (clojure.core/= ?__15612 X__16662)
                            (if
                             (clojure.core/symbol? elem__16660_nth_1__)
                             (clojure.core/let
                              [X__16664
                               (clojure.core/name elem__16660_nth_1__)]
                              (clojure.core/case
                               X__16664
                               ("meander.zeta")
                               [?__15612]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17070)
                        (recur (clojure.core/next search_space__17069))
                        result__17070))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17067)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16642
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15612 X__16642]
                     (clojure.core/let
                      [X__16644
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16644
                       ("re")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16632 input__15599_nth_1__ ?__15612)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16887)
                         (clojure.core/let
                          [[?__15612] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__15599_nth_0__)
                                3)
                               (clojure.core/let
                                [input__15599_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  1)
                                 input__15599_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__15599_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__15599_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__15599_nth_0__]
                                   (clojure.core/let
                                    [?env input__15599_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__15664
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
                               (state__16887))
                              (state__16887)))
                            (state__16887))
                           (state__16887)))))
                       (state__16887)))))
                   (state__16887))))
                (state__16887)))
              (state__16887)))
            (state__16887))
           (state__16887))))
        (state__16887
         []
         (clojure.core/letfn
          [(def__16666
            [arg__16689 ?__15613]
            (clojure.core/letfn
             [(state__17072
               []
               (clojure.core/let
                [x__16690 "meander.zeta"]
                (if
                 (clojure.core/= ?__15613 x__16690)
                 [?__15613]
                 (state__17073))))
              (state__17073
               []
               (if
                (clojure.core/map? arg__16689)
                (clojure.core/let
                 [VAL__16691 (.valAt arg__16689 :aliases)]
                 (if
                  (clojure.core/map? VAL__16691)
                  (clojure.core/let
                   [X__16693 (clojure.core/set VAL__16691)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16693))
                    (clojure.core/loop
                     [search_space__17074 (clojure.core/seq X__16693)]
                     (if
                      (clojure.core/seq search_space__17074)
                      (clojure.core/let
                       [elem__16694
                        (clojure.core/first search_space__17074)
                        result__17075
                        (clojure.core/let
                         [elem__16694_nth_0__
                          (clojure.core/nth elem__16694 0)
                          elem__16694_nth_1__
                          (clojure.core/nth elem__16694 1)]
                         (if
                          (clojure.core/symbol? elem__16694_nth_0__)
                          (clojure.core/let
                           [X__16696
                            (clojure.core/name elem__16694_nth_0__)]
                           (if
                            (clojure.core/= ?__15613 X__16696)
                            (if
                             (clojure.core/symbol? elem__16694_nth_1__)
                             (clojure.core/let
                              [X__16698
                               (clojure.core/name elem__16694_nth_1__)]
                              (clojure.core/case
                               X__16698
                               ("meander.zeta")
                               [?__15613]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17075)
                        (recur (clojure.core/next search_space__17074))
                        result__17075))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17072)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16676
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15613 X__16676]
                     (clojure.core/let
                      [X__16678
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16678
                       ("string")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16666 input__15599_nth_1__ ?__15613)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16888)
                         (clojure.core/let
                          [[?__15613] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (clojure.core/let
                               [input__15599_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__15599_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__15599_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__15599_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__15599_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__15599_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__15599_nth_0__]
                                   (clojure.core/let
                                    [?env input__15599_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__15664
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
                                (state__16888)))
                              (state__16888)))
                            (state__16888))
                           (state__16888)))))
                       (state__16888)))))
                   (state__16888))))
                (state__16888)))
              (state__16888)))
            (state__16888))
           (state__16888))))
        (state__16888
         []
         (clojure.core/letfn
          [(def__16700
            [arg__16723 ?__15614]
            (clojure.core/letfn
             [(state__17077
               []
               (clojure.core/let
                [x__16724 "meander.zeta"]
                (if
                 (clojure.core/= ?__15614 x__16724)
                 [?__15614]
                 (state__17078))))
              (state__17078
               []
               (if
                (clojure.core/map? arg__16723)
                (clojure.core/let
                 [VAL__16725 (.valAt arg__16723 :aliases)]
                 (if
                  (clojure.core/map? VAL__16725)
                  (clojure.core/let
                   [X__16727 (clojure.core/set VAL__16725)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16727))
                    (clojure.core/loop
                     [search_space__17079 (clojure.core/seq X__16727)]
                     (if
                      (clojure.core/seq search_space__17079)
                      (clojure.core/let
                       [elem__16728
                        (clojure.core/first search_space__17079)
                        result__17080
                        (clojure.core/let
                         [elem__16728_nth_0__
                          (clojure.core/nth elem__16728 0)
                          elem__16728_nth_1__
                          (clojure.core/nth elem__16728 1)]
                         (if
                          (clojure.core/symbol? elem__16728_nth_0__)
                          (clojure.core/let
                           [X__16730
                            (clojure.core/name elem__16728_nth_0__)]
                           (if
                            (clojure.core/= ?__15614 X__16730)
                            (if
                             (clojure.core/symbol? elem__16728_nth_1__)
                             (clojure.core/let
                              [X__16732
                               (clojure.core/name elem__16728_nth_1__)]
                              (clojure.core/case
                               X__16732
                               ("meander.zeta")
                               [?__15614]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17080)
                        (recur (clojure.core/next search_space__17079))
                        result__17080))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17077)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16710
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15614 X__16710]
                     (clojure.core/let
                      [X__16712
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16712
                       ("symbol")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16700 input__15599_nth_1__ ?__15614)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16889)
                         (clojure.core/let
                          [[?__15614] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__15599_nth_0__)
                                2)
                               (clojure.core/let
                                [input__15599_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__15599_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__15599_nth_0__]
                                  (clojure.core/let
                                   [?env input__15599_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__15664 [?name ?env])]
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
                               (state__16889))
                              (state__16889)))
                            (state__16889))
                           (state__16889)))))
                       (state__16889)))))
                   (state__16889))))
                (state__16889)))
              (state__16889)))
            (state__16889))
           (state__16889))))
        (state__16889
         []
         (clojure.core/letfn
          [(def__16734
            [arg__16757 ?__15615]
            (clojure.core/letfn
             [(state__17082
               []
               (clojure.core/let
                [x__16758 "meander.zeta"]
                (if
                 (clojure.core/= ?__15615 x__16758)
                 [?__15615]
                 (state__17083))))
              (state__17083
               []
               (if
                (clojure.core/map? arg__16757)
                (clojure.core/let
                 [VAL__16759 (.valAt arg__16757 :aliases)]
                 (if
                  (clojure.core/map? VAL__16759)
                  (clojure.core/let
                   [X__16761 (clojure.core/set VAL__16759)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16761))
                    (clojure.core/loop
                     [search_space__17084 (clojure.core/seq X__16761)]
                     (if
                      (clojure.core/seq search_space__17084)
                      (clojure.core/let
                       [elem__16762
                        (clojure.core/first search_space__17084)
                        result__17085
                        (clojure.core/let
                         [elem__16762_nth_0__
                          (clojure.core/nth elem__16762 0)
                          elem__16762_nth_1__
                          (clojure.core/nth elem__16762 1)]
                         (if
                          (clojure.core/symbol? elem__16762_nth_0__)
                          (clojure.core/let
                           [X__16764
                            (clojure.core/name elem__16762_nth_0__)]
                           (if
                            (clojure.core/= ?__15615 X__16764)
                            (if
                             (clojure.core/symbol? elem__16762_nth_1__)
                             (clojure.core/let
                              [X__16766
                               (clojure.core/name elem__16762_nth_1__)]
                              (clojure.core/case
                               X__16766
                               ("meander.zeta")
                               [?__15615]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17085)
                        (recur (clojure.core/next search_space__17084))
                        result__17085))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17082)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16744
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15615 X__16744]
                     (clojure.core/let
                      [X__16746
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16746
                       ("symbol")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16734 input__15599_nth_1__ ?__15615)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16890)
                         (clojure.core/let
                          [[?__15615] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__15599_nth_0__)
                                3)
                               (clojure.core/let
                                [input__15599_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  1)
                                 input__15599_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__15599_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__15599_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__15599_nth_0__]
                                   (clojure.core/let
                                    [?env input__15599_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__15664
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
                                         (CATA__FN__15664
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
                               (state__16890))
                              (state__16890)))
                            (state__16890))
                           (state__16890)))))
                       (state__16890)))))
                   (state__16890))))
                (state__16890)))
              (state__16890)))
            (state__16890))
           (state__16890))))
        (state__16890
         []
         (clojure.core/letfn
          [(def__16768
            [arg__16791 ?__15616]
            (clojure.core/letfn
             [(state__17087
               []
               (clojure.core/let
                [x__16792 "meander.zeta"]
                (if
                 (clojure.core/= ?__15616 x__16792)
                 [?__15616]
                 (state__17088))))
              (state__17088
               []
               (if
                (clojure.core/map? arg__16791)
                (clojure.core/let
                 [VAL__16793 (.valAt arg__16791 :aliases)]
                 (if
                  (clojure.core/map? VAL__16793)
                  (clojure.core/let
                   [X__16795 (clojure.core/set VAL__16793)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16795))
                    (clojure.core/loop
                     [search_space__17089 (clojure.core/seq X__16795)]
                     (if
                      (clojure.core/seq search_space__17089)
                      (clojure.core/let
                       [elem__16796
                        (clojure.core/first search_space__17089)
                        result__17090
                        (clojure.core/let
                         [elem__16796_nth_0__
                          (clojure.core/nth elem__16796 0)
                          elem__16796_nth_1__
                          (clojure.core/nth elem__16796 1)]
                         (if
                          (clojure.core/symbol? elem__16796_nth_0__)
                          (clojure.core/let
                           [X__16798
                            (clojure.core/name elem__16796_nth_0__)]
                           (if
                            (clojure.core/= ?__15616 X__16798)
                            (if
                             (clojure.core/symbol? elem__16796_nth_1__)
                             (clojure.core/let
                              [X__16800
                               (clojure.core/name elem__16796_nth_1__)]
                              (clojure.core/case
                               X__16800
                               ("meander.zeta")
                               [?__15616]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__17090)
                        (recur (clojure.core/next search_space__17089))
                        result__17090))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__17087)))]
          (if
           (clojure.core/vector? input__15599)
           (if
            (clojure.core/= (clojure.core/count input__15599) 2)
            (clojure.core/let
             [input__15599_nth_0__
              (clojure.core/nth input__15599 0)
              input__15599_nth_1__
              (clojure.core/nth input__15599 1)]
             (if
              (clojure.core/seq? input__15599_nth_0__)
              (clojure.core/let
               [input__15599_nth_0___l__
                (clojure.core/take 1 input__15599_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__15599_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__15599_nth_0___r__
                  (clojure.core/drop 1 input__15599_nth_0__)]
                 (clojure.core/let
                  [input__15599_nth_0___l___nth_0__
                   (clojure.core/nth input__15599_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__15599_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16778
                     (clojure.core/namespace
                      input__15599_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__15616 X__16778]
                     (clojure.core/let
                      [X__16780
                       (clojure.core/name
                        input__15599_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16780
                       ("symbol")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__16768 input__15599_nth_1__ ?__15616)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__16891)
                         (clojure.core/let
                          [[?__15616] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__15599)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__15599)
                             2)
                            (clojure.core/let
                             [input__15599_nth_0__
                              (clojure.core/nth input__15599 0)
                              input__15599_nth_1__
                              (clojure.core/nth input__15599 1)]
                             (if
                              (clojure.core/seq? input__15599_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__15599_nth_0__)
                                5)
                               (clojure.core/let
                                [input__15599_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  1)
                                 input__15599_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  2)
                                 input__15599_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  3)
                                 input__15599_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__15599_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__15599_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__15599_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name input__15599_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__15599_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__15599_nth_0__]
                                     (clojure.core/let
                                      [?env input__15599_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__10883__auto__
                                           (CATA__FN__15664
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
                                           (CATA__FN__15664
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
                                           (CATA__FN__15664
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
                                 (state__16891)))
                               (state__16891))
                              (state__16891)))
                            (state__16891))
                           (state__16891)))))
                       (state__16891)))))
                   (state__16891))))
                (state__16891)))
              (state__16891)))
            (state__16891))
           (state__16891))))
        (state__16891
         []
         (if
          (clojure.core/vector? input__15599)
          (if
           (clojure.core/= (clojure.core/count input__15599) 2)
           (clojure.core/let
            [input__15599_nth_0__ (clojure.core/nth input__15599 0)]
            (clojure.core/letfn
             [(state__17092
               []
               (clojure.core/let
                [input__15599_nth_1__
                 (clojure.core/nth input__15599 1)]
                (clojure.core/letfn
                 [(state__17097
                   []
                   (if
                    (clojure.core/seq? input__15599_nth_0__)
                    (clojure.core/let
                     [?sequence input__15599_nth_0__]
                     (clojure.core/let
                      [?env input__15599_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__10883__auto__
                           (CATA__FN__15664
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
                    (state__17098)))
                  (state__17098
                   []
                   (if
                    (clojure.core/map? input__15599_nth_0__)
                    (clojure.core/let
                     [?map input__15599_nth_0__]
                     (clojure.core/let
                      [?env input__15599_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__10883__auto__
                           (CATA__FN__15664
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
                    (state__17093)))]
                 (state__17097))))
              (state__17093
               []
               (if
                (clojure.core/symbol? input__15599_nth_0__)
                (clojure.core/let
                 [X__16810
                  (clojure.core/namespace input__15599_nth_0__)]
                 (clojure.core/let
                  [X__16812 (clojure.core/name input__15599_nth_0__)]
                  (if
                   (clojure.core/string? X__16812)
                   (clojure.core/letfn
                    [(state__17099
                      []
                      (clojure.core/let
                       [ret__16813
                        (clojure.core/re-matches #"_.*" X__16812)]
                       (if
                        (clojure.core/some? ret__16813)
                        (clojure.core/let
                         [?name ret__16813]
                         (clojure.core/let
                          [?symbol input__15599_nth_0__]
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
                        (state__17100))))
                     (state__17100
                      []
                      (clojure.core/let
                       [ret__16820
                        (clojure.core/re-matches #".+#" X__16812)]
                       (if
                        (clojure.core/some? ret__16820)
                        (clojure.core/let
                         [?name ret__16820]
                         (clojure.core/let
                          [?symbol input__15599_nth_0__]
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
                        (state__17101))))
                     (state__17101
                      []
                      (clojure.core/let
                       [ret__16827
                        (clojure.core/re-matches #"%.+" X__16812)]
                       (if
                        (clojure.core/some? ret__16827)
                        (clojure.core/let
                         [?name ret__16827]
                         (clojure.core/let
                          [?symbol input__15599_nth_0__]
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
                        (state__17102))))
                     (state__17102
                      []
                      (clojure.core/let
                       [ret__16834
                        (clojure.core/re-matches #"\*.+" X__16812)]
                       (if
                        (clojure.core/some? ret__16834)
                        (clojure.core/let
                         [?name ret__16834]
                         (clojure.core/let
                          [?symbol input__15599_nth_0__]
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
                        (state__17103))))
                     (state__17103
                      []
                      (clojure.core/let
                       [ret__16841
                        (clojure.core/re-matches #"\!.+" X__16812)]
                       (if
                        (clojure.core/some? ret__16841)
                        (clojure.core/let
                         [?name ret__16841]
                         (clojure.core/let
                          [?symbol input__15599_nth_0__]
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
                        (state__17104))))
                     (state__17104
                      []
                      (clojure.core/let
                       [ret__16848
                        (clojure.core/re-matches #"\?.+" X__16812)]
                       (if
                        (clojure.core/some? ret__16848)
                        (clojure.core/let
                         [?name ret__16848]
                         (clojure.core/let
                          [?symbol input__15599_nth_0__]
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
                        (state__17094))))]
                    (state__17099))
                   (state__17094))))
                (state__17094)))
              (state__17094
               []
               (if
                (string? input__15599_nth_0__)
                (clojure.core/let
                 [?x input__15599_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__11823__auto__
                   (if
                    (meander.runtime.zeta/fail? e__11823__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__11823__auto__)))))
                (state__17095)))
              (state__17095
               []
               (if
                (char? input__15599_nth_0__)
                (clojure.core/let
                 [?x input__15599_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__11823__auto__
                   (if
                    (meander.runtime.zeta/fail? e__11823__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__11823__auto__)))))
                (state__17096)))
              (state__17096
               []
               (clojure.core/let
                [?x input__15599_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__11823__auto__
                  (if
                   (meander.runtime.zeta/fail? e__11823__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__11823__auto__))))))]
             (state__17092)))
           (state__16892))
          (state__16892)))
        (state__16892
         []
         (clojure.core/let
          [?x input__15599]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__11823__auto__
            (if
             (meander.runtime.zeta/fail? e__11823__auto__)
             (meander.runtime.zeta/fail)
             (throw e__11823__auto__))))))]
       (state__16861)))]
    (clojure.core/let
     [x__9580__auto__ (CATA__FN__15664 input__15599)]
     (if
      (meander.runtime.zeta/fail? x__9580__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__15666] x__9580__auto__]
       CATA_RETURN__15666))))]
  (if
   (meander.runtime.zeta/fail? ret__9760__auto__)
   nil
   ret__9760__auto__)))
