(ns
 meander.compiled.parse.zeta
 (:require [meander.runtime.zeta] [meander.util.zeta]))
(clojure.core/defn
 parse
 [input__14513]
 (let*
  [R__16748
   (clojure.core/letfn
    [(C__14591
      [input__14513]
      (clojure.core/letfn
       [(state__16391
         []
         (if
          (clojure.core/vector? input__14513)
          (clojure.core/letfn
           [(state__16430
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (clojure.core/case
                 input__14513_nth_1__
                 ([])
                 (clojure.core/let
                  [?env input__14513_nth_2__]
                  [{:tag :empty}])
                 (state__16431))
                (state__16431)))
              (state__16431)))
            (state__16431
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/let
                  [N__14707
                   (clojure.core/count input__14513_nth_1__)
                   N__14708
                   (clojure.core/max 0 (clojure.core/- N__14707 2))
                   input__14513_nth_1___L__
                   (clojure.core/subvec
                    input__14513_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__14513_nth_1__)
                     N__14708))
                   input__14513_nth_1___R__
                   (clojure.core/subvec input__14513_nth_1__ N__14708)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14513_nth_1___R__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__14513_nth_1___L__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14513_nth_1___R__)
                      2)
                     (clojure.core/let
                      [input__14513_nth_1___R___nth_0__
                       (clojure.core/nth input__14513_nth_1___R__ 0)
                       input__14513_nth_1___R___nth_1__
                       (clojure.core/nth input__14513_nth_1___R__ 1)]
                      (clojure.core/case
                       input__14513_nth_1___R___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__14513_nth_1___R___nth_1__]
                        (clojure.core/let
                         [?env input__14513_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [R__14592 (C__14591 [?pattern ?env])]
                              (if
                               (meander.runtime.zeta/fail? R__14592)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth R__14592 0))),
                             :next
                             (clojure.core/let
                              [R__14593
                               (C__14591
                                ['meander.dev.parse.zeta/parse-sequential
                                 (clojure.core/into
                                  []
                                  (clojure.core/vec
                                   (clojure.core/iterator-seq
                                    !xs__counter)))
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail? R__14593)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth R__14593 0)))})]
                          (catch
                           java.lang.Exception
                           e__10399__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10399__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10399__auto__))))))
                       (state__16392)))
                     (state__16392)))
                   (state__16392)))
                 (state__16392))
                (state__16392)))
              (state__16392)))]
           (state__16430))
          (state__16392)))
        (state__16392
         []
         (clojure.core/letfn
          [(D__14713
            [T__14756 ?ns]
            (clojure.core/letfn
             [(state__16432
               []
               (clojure.core/let
                [T__14757 "meander.zeta"]
                (if
                 (clojure.core/= ?ns T__14757)
                 (clojure.core/let [?env T__14756] [?env ?ns])
                 (state__16433))))
              (state__16433
               []
               (if
                (clojure.core/map? T__14756)
                (clojure.core/let
                 [T__14758 (.valAt T__14756 :aliases)]
                 (if
                  (clojure.core/map? T__14758)
                  (clojure.core/loop
                   [S__16434
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__14758
                     1)]
                   (if
                    (clojure.core/seq S__16434)
                    (clojure.core/let
                     [T__14761
                      (clojure.core/first S__16434)
                      R__16435
                      (clojure.core/let
                       [T__14762
                        (clojure.core/nth T__14761 0)
                        T__14764
                        (clojure.core/nth
                         (clojure.core/nth T__14762 0)
                         0)
                        T__14765
                        (.valAt T__14758 T__14764)
                        T__14763
                        (clojure.core/nth T__14761 1)]
                       (if
                        (clojure.core/symbol? T__14764)
                        (clojure.core/let
                         [X__14767 (clojure.core/name T__14764)]
                         (if
                          (clojure.core/= ?ns X__14767)
                          (if
                           (clojure.core/symbol? T__14765)
                           (clojure.core/let
                            [X__14769 (clojure.core/name T__14765)]
                            (clojure.core/case
                             X__14769
                             ("meander.zeta")
                             (clojure.core/let
                              [T__14759
                               (clojure.core/dissoc T__14756 :aliases)]
                              (clojure.core/let
                               [?env T__14756]
                               [?env ?ns]))
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16435)
                      (recur (clojure.core/next S__16434))
                      R__16435))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16432)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 3)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)
              input__14513_nth_2__
              (clojure.core/nth input__14513 2)]
             (clojure.core/case
              input__14513_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14513_nth_1__)
               (clojure.core/loop
                [S__16437
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14513_nth_1__)]
                (if
                 (clojure.core/seq S__16437)
                 (clojure.core/let
                  [input__14513_nth_1___parts__
                   (clojure.core/first S__16437)
                   R__16438
                   (clojure.core/let
                    [input__14513_nth_1___L__
                     (clojure.core/nth input__14513_nth_1___parts__ 0)
                     input__14513_nth_1___R__
                     (clojure.core/nth input__14513_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__14513_nth_1___L__)]
                     (clojure.core/let
                      [input__14513_nth_1___R___L__
                       (clojure.core/subvec
                        input__14513_nth_1___R__
                        0
                        (clojure.core/min
                         (clojure.core/count input__14513_nth_1___R__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__14513_nth_1___R___L__)
                        2)
                       (clojure.core/let
                        [input__14513_nth_1___R___R__
                         (clojure.core/subvec
                          input__14513_nth_1___R__
                          2)]
                        (clojure.core/let
                         [input__14513_nth_1___R___L___nth_0__
                          (clojure.core/nth
                           input__14513_nth_1___R___L__
                           0)
                          input__14513_nth_1___R___L___nth_1__
                          (clojure.core/nth
                           input__14513_nth_1___R___L__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__14513_nth_1___R___L___nth_0__)
                          (clojure.core/let
                           [X__14722
                            (clojure.core/namespace
                             input__14513_nth_1___R___L___nth_0__)]
                           (clojure.core/let
                            [?ns X__14722]
                            (clojure.core/let
                             [X__14724
                              (clojure.core/name
                               input__14513_nth_1___R___L___nth_0__)]
                             (if
                              (clojure.core/string? X__14724)
                              (clojure.core/let
                               [R__14725
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__14724)]
                               (if
                                (clojure.core/some? R__14725)
                                (if
                                 (clojure.core/vector? R__14725)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count R__14725)
                                   2)
                                  (clojure.core/let
                                   [R__14725_nth_1__
                                    (clojure.core/nth R__14725 1)]
                                   (clojure.core/let
                                    [?n R__14725_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__14513_nth_1___R___L___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__14513_nth_1___R___R__]
                                      (clojure.core/let
                                       [R__16440
                                        (D__14713
                                         input__14513_nth_2__
                                         ?ns)]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         R__16440)
                                        (meander.runtime.zeta/fail)
                                        (clojure.core/let
                                         [[?env ?ns] R__16440]
                                         (try
                                          [(clojure.core/let
                                            [!init__counter
                                             (meander.runtime.zeta/iterator
                                              !init)]
                                            (clojure.core/let
                                             [R__14598
                                              (C__14591
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [R__14594
                                                  (C__14591
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !init__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   R__14594)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   R__14594
                                                   0)))
                                                (clojure.core/let
                                                 [R__14597
                                                  (C__14591
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [R__14595
                                                       (C__14591
                                                        [?pattern
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        R__14595)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        R__14595
                                                        0)))}
                                                    (clojure.core/let
                                                     [R__14596
                                                      (C__14591
                                                       ['meander.dev.parse.zeta/parse-sequential
                                                        (clojure.core/into
                                                         []
                                                         ?rest)
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       R__14596)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       R__14596
                                                       0)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   R__14597)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   R__14597
                                                   0)))
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               R__14598)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               R__14598
                                               0))))]
                                          (catch
                                           java.lang.Exception
                                           e__10399__auto__
                                           (if
                                            (meander.runtime.zeta/fail?
                                             e__10399__auto__)
                                            (meander.runtime.zeta/fail)
                                            (throw
                                             e__10399__auto__)))))))))))
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? R__16438)
                   (recur (clojure.core/next S__16437))
                   R__16438))
                 (state__16393)))
               (state__16393))
              (state__16393)))
            (state__16393))
           (state__16393))))
        (state__16393
         []
         (clojure.core/letfn
          [(D__14786
            [T__14826 ?ns]
            (clojure.core/letfn
             [(state__16441
               []
               (clojure.core/let
                [T__14827 "meander.zeta"]
                (if
                 (clojure.core/= ?ns T__14827)
                 (clojure.core/let [?env T__14826] [?env ?ns])
                 (state__16442))))
              (state__16442
               []
               (if
                (clojure.core/map? T__14826)
                (clojure.core/let
                 [T__14828 (.valAt T__14826 :aliases)]
                 (if
                  (clojure.core/map? T__14828)
                  (clojure.core/loop
                   [S__16443
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__14828
                     1)]
                   (if
                    (clojure.core/seq S__16443)
                    (clojure.core/let
                     [T__14831
                      (clojure.core/first S__16443)
                      R__16444
                      (clojure.core/let
                       [T__14832
                        (clojure.core/nth T__14831 0)
                        T__14834
                        (clojure.core/nth
                         (clojure.core/nth T__14832 0)
                         0)
                        T__14835
                        (.valAt T__14828 T__14834)
                        T__14833
                        (clojure.core/nth T__14831 1)]
                       (if
                        (clojure.core/symbol? T__14834)
                        (clojure.core/let
                         [X__14837 (clojure.core/name T__14834)]
                         (if
                          (clojure.core/= ?ns X__14837)
                          (if
                           (clojure.core/symbol? T__14835)
                           (clojure.core/let
                            [X__14839 (clojure.core/name T__14835)]
                            (clojure.core/case
                             X__14839
                             ("meander.zeta")
                             (clojure.core/let
                              [T__14829
                               (clojure.core/dissoc T__14826 :aliases)]
                              (clojure.core/let
                               [?env T__14826]
                               [?env ?ns]))
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16444)
                      (recur (clojure.core/next S__16443))
                      R__16444))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16441)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 3)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)
              input__14513_nth_2__
              (clojure.core/nth input__14513 2)]
             (clojure.core/case
              input__14513_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14513_nth_1__)
               (clojure.core/loop
                [S__16446
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14513_nth_1__)]
                (if
                 (clojure.core/seq S__16446)
                 (clojure.core/let
                  [input__14513_nth_1___parts__
                   (clojure.core/first S__16446)
                   R__16447
                   (clojure.core/let
                    [input__14513_nth_1___L__
                     (clojure.core/nth input__14513_nth_1___parts__ 0)
                     input__14513_nth_1___R__
                     (clojure.core/nth input__14513_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__14513_nth_1___L__)]
                     (clojure.core/let
                      [input__14513_nth_1___R___L__
                       (clojure.core/subvec
                        input__14513_nth_1___R__
                        0
                        (clojure.core/min
                         (clojure.core/count input__14513_nth_1___R__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__14513_nth_1___R___L__)
                        2)
                       (clojure.core/let
                        [input__14513_nth_1___R___R__
                         (clojure.core/subvec
                          input__14513_nth_1___R__
                          2)]
                        (clojure.core/let
                         [input__14513_nth_1___R___L___nth_0__
                          (clojure.core/nth
                           input__14513_nth_1___R___L__
                           0)
                          input__14513_nth_1___R___L___nth_1__
                          (clojure.core/nth
                           input__14513_nth_1___R___L__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__14513_nth_1___R___L___nth_0__)
                          (clojure.core/let
                           [X__14795
                            (clojure.core/namespace
                             input__14513_nth_1___R___L___nth_0__)]
                           (clojure.core/let
                            [?ns X__14795]
                            (clojure.core/let
                             [X__14797
                              (clojure.core/name
                               input__14513_nth_1___R___L___nth_0__)]
                             (if
                              (clojure.core/string? X__14797)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__14797)
                               (clojure.core/let
                                [?pattern
                                 input__14513_nth_1___R___L___nth_1__]
                                (clojure.core/let
                                 [?rest input__14513_nth_1___R___R__]
                                 (clojure.core/let
                                  [R__16449
                                   (D__14786 input__14513_nth_2__ ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    R__16449)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] R__16449]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [R__14603
                                         (C__14591
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [R__14599
                                             (C__14591
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              R__14599)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              R__14599
                                              0)))
                                           (clojure.core/let
                                            [R__14602
                                             (C__14591
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [R__14600
                                                 (C__14591
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  R__14600)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  R__14600
                                                  0)))
                                               (clojure.core/let
                                                [R__14601
                                                 (C__14591
                                                  ['meander.dev.parse.zeta/parse-sequential
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  R__14601)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  R__14601
                                                  0)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              R__14602)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              R__14602
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14603)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14603
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10399__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10399__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10399__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? R__16447)
                   (recur (clojure.core/next S__16446))
                   R__16447))
                 (state__16394)))
               (state__16394))
              (state__16394)))
            (state__16394))
           (state__16394))))
        (state__16394
         []
         (if
          (clojure.core/vector? input__14513)
          (clojure.core/letfn
           [(state__16450
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/let
                  [N__14868
                   (clojure.core/count input__14513_nth_1__)
                   N__14869
                   (clojure.core/max 0 (clojure.core/- N__14868 2))
                   input__14513_nth_1___L__
                   (clojure.core/subvec
                    input__14513_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__14513_nth_1__)
                     N__14869))
                   input__14513_nth_1___R__
                   (clojure.core/subvec input__14513_nth_1__ N__14869)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14513_nth_1___R__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__14513_nth_1___L__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14513_nth_1___R__)
                      2)
                     (clojure.core/let
                      [input__14513_nth_1___R___nth_0__
                       (clojure.core/nth input__14513_nth_1___R__ 0)
                       input__14513_nth_1___R___nth_1__
                       (clojure.core/nth input__14513_nth_1___R__ 1)]
                      (if
                       (clojure.core/symbol?
                        input__14513_nth_1___R___nth_0__)
                       (clojure.core/let
                        [X__14873
                         (clojure.core/namespace
                          input__14513_nth_1___R___nth_0__)]
                        (clojure.core/let
                         [?ns X__14873]
                         (clojure.core/let
                          [X__14875
                           (clojure.core/name
                            input__14513_nth_1___R___nth_0__)]
                          (if
                           (clojure.core/string? X__14875)
                           (clojure.core/let
                            [R__14876
                             (clojure.core/re-matches #"&.*" X__14875)]
                            (if
                             (clojure.core/some? R__14876)
                             (clojure.core/let
                              [?name R__14876]
                              (clojure.core/let
                               [?pattern
                                input__14513_nth_1___R___nth_1__]
                               (if
                                (clojure.core/map?
                                 input__14513_nth_2__)
                                (clojure.core/let
                                 [T__14856
                                  (.valAt
                                   input__14513_nth_2__
                                   :aliases)]
                                 (if
                                  (clojure.core/map? T__14856)
                                  (clojure.core/loop
                                   [S__16458
                                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                                     T__14856
                                     1)]
                                   (if
                                    (clojure.core/seq S__16458)
                                    (clojure.core/let
                                     [T__14859
                                      (clojure.core/first S__16458)
                                      R__16459
                                      (clojure.core/let
                                       [T__14860
                                        (clojure.core/nth T__14859 0)
                                        T__14862
                                        (clojure.core/nth
                                         (clojure.core/nth T__14860 0)
                                         0)
                                        T__14863
                                        (.valAt T__14856 T__14862)
                                        T__14861
                                        (clojure.core/nth T__14859 1)]
                                       (if
                                        (clojure.core/symbol? T__14862)
                                        (clojure.core/let
                                         [X__14865
                                          (clojure.core/name T__14862)]
                                         (if
                                          (clojure.core/= ?ns X__14865)
                                          (if
                                           (clojure.core/symbol?
                                            T__14863)
                                           (clojure.core/let
                                            [X__14867
                                             (clojure.core/name
                                              T__14863)]
                                            (clojure.core/case
                                             X__14867
                                             ("meander.zeta")
                                             (clojure.core/let
                                              [T__14857
                                               (clojure.core/dissoc
                                                input__14513_nth_2__
                                                :aliases)]
                                              (clojure.core/let
                                               [?env
                                                input__14513_nth_2__]
                                               (try
                                                [(clojure.core/let
                                                  [!xs__counter
                                                   (meander.runtime.zeta/iterator
                                                    !xs)]
                                                  (clojure.core/let
                                                   [R__14604
                                                    (C__14591
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
                                                     R__14604)
                                                    (throw
                                                     (meander.runtime.zeta/fail))
                                                    (clojure.core/nth
                                                     R__14604
                                                     0))))]
                                                (catch
                                                 java.lang.Exception
                                                 e__10399__auto__
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   e__10399__auto__)
                                                  (meander.runtime.zeta/fail)
                                                  (throw
                                                   e__10399__auto__))))))
                                             (meander.runtime.zeta/fail)))
                                           (meander.runtime.zeta/fail))
                                          (meander.runtime.zeta/fail)))
                                        (meander.runtime.zeta/fail)))]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       R__16459)
                                      (recur
                                       (clojure.core/next S__16458))
                                      R__16459))
                                    (state__16451)))
                                  (state__16451)))
                                (state__16451))))
                             (state__16451)))
                           (state__16451)))))
                       (state__16451)))
                     (state__16451)))
                   (state__16451)))
                 (state__16451))
                (state__16451)))
              (state__16451)))
            (state__16451
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/loop
                  [S__16461
                   (meander.runtime.zeta/epsilon-partitions
                    2
                    input__14513_nth_1__)]
                  (if
                   (clojure.core/seq S__16461)
                   (clojure.core/let
                    [input__14513_nth_1___parts__
                     (clojure.core/first S__16461)
                     R__16462
                     (clojure.core/let
                      [input__14513_nth_1___L__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        0)
                       input__14513_nth_1___R__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__14513_nth_1___L__)]
                       (clojure.core/let
                        [input__14513_nth_1___R___L__
                         (clojure.core/subvec
                          input__14513_nth_1___R__
                          0
                          (clojure.core/min
                           (clojure.core/count
                            input__14513_nth_1___R__)
                           1))]
                        (if
                         (clojure.core/=
                          (clojure.core/count
                           input__14513_nth_1___R___L__)
                          1)
                         (clojure.core/let
                          [input__14513_nth_1___R___R__
                           (clojure.core/subvec
                            input__14513_nth_1___R__
                            1)]
                          (if
                           (clojure.core/=
                            input__14513_nth_1___R___L__
                            ['.])
                           (clojure.core/let
                            [?rest input__14513_nth_1___R___R__]
                            (clojure.core/let
                             [?env input__14513_nth_2__]
                             (try
                              [(clojure.core/let
                                [!xs__counter
                                 (meander.runtime.zeta/iterator !xs)]
                                (clojure.core/let
                                 [R__14607
                                  (C__14591
                                   ['meander.dev.parse.zeta/make-join
                                    (clojure.core/let
                                     [R__14605
                                      (C__14591
                                       ['meander.dev.parse.zeta/parse-sequential
                                        (clojure.core/into
                                         []
                                         (clojure.core/vec
                                          (clojure.core/iterator-seq
                                           !xs__counter)))
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       R__14605)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth R__14605 0)))
                                    (clojure.core/let
                                     [R__14606
                                      (C__14591
                                       ['meander.dev.parse.zeta/parse-sequential
                                        ?rest
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       R__14606)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth R__14606 0)))
                                    ?env])]
                                 (if
                                  (meander.runtime.zeta/fail? R__14607)
                                  (throw (meander.runtime.zeta/fail))
                                  (clojure.core/nth R__14607 0))))]
                              (catch
                               java.lang.Exception
                               e__10399__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10399__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10399__auto__))))))
                           (meander.runtime.zeta/fail)))
                         (meander.runtime.zeta/fail)))))]
                    (if
                     (meander.runtime.zeta/fail? R__16462)
                     (recur (clojure.core/next S__16461))
                     R__16462))
                   (state__16452)))
                 (state__16452))
                (state__16452)))
              (state__16452)))
            (state__16452
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/let
                  [input__14513_nth_1___L__
                   (clojure.core/subvec
                    input__14513_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__14513_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14513_nth_1___L__)
                    1)
                   (clojure.core/let
                    [input__14513_nth_1___R__
                     (clojure.core/subvec input__14513_nth_1__ 1)]
                    (if
                     (clojure.core/= input__14513_nth_1___L__ ['...])
                     (clojure.core/let
                      [?rest input__14513_nth_1___R__]
                      (clojure.core/let
                       [?env input__14513_nth_2__]
                       (try
                        [(clojure.core/let
                          [R__14608
                           (C__14591
                            ['meander.dev.parse.zeta/parse-sequential
                             ?rest
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail? R__14608)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth R__14608 0)))]
                        (catch
                         java.lang.Exception
                         e__10399__auto__
                         (if
                          (meander.runtime.zeta/fail? e__10399__auto__)
                          (meander.runtime.zeta/fail)
                          (throw e__10399__auto__))))))
                     (state__16453)))
                   (state__16453)))
                 (state__16453))
                (state__16453)))
              (state__16453)))
            (state__16453
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/loop
                  [S__16464
                   (meander.runtime.zeta/epsilon-partitions
                    2
                    input__14513_nth_1__)]
                  (if
                   (clojure.core/seq S__16464)
                   (clojure.core/let
                    [input__14513_nth_1___parts__
                     (clojure.core/first S__16464)
                     R__16465
                     (clojure.core/let
                      [input__14513_nth_1___L__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        0)
                       input__14513_nth_1___R__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs []]
                       (clojure.core/let
                        [R__16469
                         (meander.runtime.zeta/epsilon-run-star-1
                          input__14513_nth_1___L__
                          [!xs]
                          (clojure.core/fn
                           [[!xs] input__14893]
                           (clojure.core/let
                            [input__14893_nth_0__
                             (clojure.core/nth input__14893 0)]
                            (clojure.core/let
                             [R__16468
                              (if
                               (clojure.core/symbol?
                                input__14893_nth_0__)
                               (clojure.core/let
                                [X__14895
                                 (clojure.core/namespace
                                  input__14893_nth_0__)]
                                (clojure.core/case
                                 X__14895
                                 (nil)
                                 (clojure.core/let
                                  [X__14897
                                   (clojure.core/name
                                    input__14893_nth_0__)]
                                  (if
                                   (clojure.core/string? X__14897)
                                   (if
                                    (clojure.core/re-matches
                                     #"\.\.(?:\.|\d+)"
                                     X__14897)
                                    true
                                    (meander.runtime.zeta/fail))
                                   (meander.runtime.zeta/fail)))
                                 (meander.runtime.zeta/fail)))
                               (meander.runtime.zeta/fail))]
                             (if
                              (meander.runtime.zeta/fail? R__16468)
                              (clojure.core/let
                               [!xs
                                (clojure.core/conj
                                 !xs
                                 input__14893_nth_0__)]
                               [!xs])
                              (meander.runtime.zeta/fail)))))
                          (clojure.core/fn
                           [[!xs]]
                           (clojure.core/let
                            [input__14513_nth_1___R___L__
                             (clojure.core/subvec
                              input__14513_nth_1___R__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__14513_nth_1___R__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__14513_nth_1___R___L__)
                              1)
                             (clojure.core/let
                              [input__14513_nth_1___R___R__
                               (clojure.core/subvec
                                input__14513_nth_1___R__
                                1)]
                              (if
                               (clojure.core/=
                                input__14513_nth_1___R___L__
                                ['...])
                               (clojure.core/let
                                [?rest input__14513_nth_1___R___R__]
                                (clojure.core/let
                                 [?env input__14513_nth_2__]
                                 (try
                                  [(clojure.core/let
                                    [!xs__counter
                                     (meander.runtime.zeta/iterator
                                      !xs)]
                                    (clojure.core/let
                                     [R__14611
                                      (C__14591
                                       ['meander.dev.parse.zeta/make-star
                                        (clojure.core/let
                                         [R__14609
                                          (C__14591
                                           ['meander.dev.parse.zeta/parse-sequential
                                            (clojure.core/into
                                             []
                                             (clojure.core/vec
                                              (clojure.core/iterator-seq
                                               !xs__counter)))
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           R__14609)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           R__14609
                                           0)))
                                        (clojure.core/let
                                         [R__14610
                                          (C__14591
                                           ['meander.dev.parse.zeta/parse-sequential
                                            ?rest
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           R__14610)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           R__14610
                                           0)))
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       R__14611)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth R__14611 0))))]
                                  (catch
                                   java.lang.Exception
                                   e__10399__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__10399__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__10399__auto__))))))
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail)))))]
                        (if
                         (meander.runtime.zeta/fail? R__16469)
                         (meander.runtime.zeta/fail)
                         R__16469))))]
                    (if
                     (meander.runtime.zeta/fail? R__16465)
                     (recur (clojure.core/next S__16464))
                     R__16465))
                   (state__16454)))
                 (state__16454))
                (state__16454)))
              (state__16454)))
            (state__16454
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-star)
                (if
                 (clojure.core/map? input__14513_nth_1__)
                 (clojure.core/let
                  [T__14903
                   (.valAt input__14513_nth_1__ :next)
                   T__14902
                   (.valAt input__14513_nth_1__ :sequence)
                   T__14901
                   (.valAt input__14513_nth_1__ :tag)]
                  (clojure.core/case
                   T__14901
                   (:cat)
                   (if
                    (clojure.core/vector? T__14902)
                    (if
                     (clojure.core/= (clojure.core/count T__14902) 1)
                     (clojure.core/let
                      [T__14902_nth_0__ (clojure.core/nth T__14902 0)]
                      (if
                       (clojure.core/map? T__14902_nth_0__)
                       (clojure.core/let
                        [T__14909 (.valAt T__14902_nth_0__ :tag)]
                        (clojure.core/case
                         T__14909
                         (:memory-variable)
                         (clojure.core/let
                          [T__14910
                           (clojure.core/dissoc T__14902_nth_0__ :tag)]
                          (clojure.core/let
                           [?memory-variable T__14902_nth_0__]
                           (if
                            (clojure.core/map? T__14903)
                            (clojure.core/let
                             [T__14905 (.valAt T__14903 :tag)]
                             (clojure.core/case
                              T__14905
                              (:empty)
                              (clojure.core/let
                               [T__14906
                                (clojure.core/dissoc T__14903 :tag)]
                               (clojure.core/let
                                [T__14904
                                 (clojure.core/dissoc
                                  input__14513_nth_1__
                                  :tag
                                  :sequence
                                  :next)]
                                (clojure.core/let
                                 [?next input__14513_nth_2__]
                                 (clojure.core/let
                                  [?env input__14513_nth_3__]
                                  (try
                                   [(clojure.core/let
                                     [R__14612
                                      (C__14591
                                       ['meander.dev.parse.zeta/make-join
                                        {:tag :into,
                                         :memory-variable
                                         ?memory-variable}
                                        ?next
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       R__14612)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth R__14612 0)))]
                                   (catch
                                    java.lang.Exception
                                    e__10399__auto__
                                    (if
                                     (meander.runtime.zeta/fail?
                                      e__10399__auto__)
                                     (meander.runtime.zeta/fail)
                                     (throw e__10399__auto__))))))))
                              (state__16455)))
                            (state__16455))))
                         (state__16455)))
                       (state__16455)))
                     (state__16455))
                    (state__16455))
                   (state__16455)))
                 (state__16455))
                (state__16455)))
              (state__16455)))
            (state__16455
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-star)
                (clojure.core/let
                 [?pattern input__14513_nth_1__]
                 (clojure.core/let
                  [?next input__14513_nth_2__]
                  (if
                   (clojure.core/map? input__14513_nth_3__)
                   (clojure.core/let
                    [T__14913 (.valAt input__14513_nth_3__ :context)]
                    (clojure.core/case
                     T__14913
                     (:string)
                     (clojure.core/let
                      [T__14914
                       (clojure.core/dissoc
                        input__14513_nth_3__
                        :context)]
                      [{:tag :string-star,
                        :greedy? false,
                        :pattern ?pattern,
                        :next ?next}])
                     (state__16456)))
                   (state__16456))))
                (state__16456)))
              (state__16456)))
            (state__16456
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-star)
                (clojure.core/let
                 [?pattern input__14513_nth_1__]
                 (clojure.core/let
                  [?next input__14513_nth_2__]
                  [{:tag :star,
                    :greedy? false,
                    :pattern ?pattern,
                    :next ?next}]))
                (state__16457)))
              (state__16457)))
            (state__16457
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/let
                  [input__14513_nth_1___L__
                   (clojure.core/subvec
                    input__14513_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__14513_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14513_nth_1___L__)
                    1)
                   (clojure.core/let
                    [input__14513_nth_1___R__
                     (clojure.core/subvec input__14513_nth_1__ 1)]
                    (clojure.core/let
                     [input__14513_nth_1___L___nth_0__
                      (clojure.core/nth input__14513_nth_1___L__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__14513_nth_1___L___nth_0__)
                      (clojure.core/let
                       [X__14922
                        (clojure.core/namespace
                         input__14513_nth_1___L___nth_0__)]
                       (clojure.core/case
                        X__14922
                        (nil)
                        (clojure.core/let
                         [X__14924
                          (clojure.core/name
                           input__14513_nth_1___L___nth_0__)]
                         (if
                          (clojure.core/string? X__14924)
                          (clojure.core/let
                           [R__14925
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__14924)]
                           (if
                            (clojure.core/some? R__14925)
                            (if
                             (clojure.core/vector? R__14925)
                             (if
                              (clojure.core/=
                               (clojure.core/count R__14925)
                               2)
                              (clojure.core/let
                               [R__14925_nth_1__
                                (clojure.core/nth R__14925 1)]
                               (clojure.core/let
                                [?n R__14925_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__14513_nth_1___L___nth_0__]
                                 (clojure.core/let
                                  [?rest input__14513_nth_1___R__]
                                  (clojure.core/let
                                   [?env input__14513_nth_2__]
                                   [{:tag :syntax-error,
                                     :message
                                     "The n or more operator ..N must be preceeded by at least one pattern"}])))))
                              (state__16395))
                             (state__16395))
                            (state__16395)))
                          (state__16395)))
                        (state__16395)))
                      (state__16395))))
                   (state__16395)))
                 (state__16395))
                (state__16395)))
              (state__16395)))]
           (state__16450))
          (state__16395)))
        (state__16395
         []
         (clojure.core/letfn
          [(D__14928
            [T__14951]
            (clojure.core/letfn
             [(state__16470
               []
               (clojure.core/let
                [T__14952 :string-plus]
                (clojure.core/let
                 [?tag T__14952]
                 (if
                  (clojure.core/map? T__14951)
                  (clojure.core/let
                   [T__14953 (.valAt T__14951 :context)]
                   (clojure.core/case
                    T__14953
                    (:string)
                    (clojure.core/let
                     [T__14954 (clojure.core/dissoc T__14951 :context)]
                     (clojure.core/let [?env T__14951] [?tag ?env]))
                    (state__16471)))
                  (state__16471)))))
              (state__16471
               []
               (clojure.core/let
                [T__14955 :plus]
                (clojure.core/let
                 [?tag T__14955]
                 (clojure.core/let [?env T__14951] [?tag ?env]))))]
             (state__16470)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 3)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)
              input__14513_nth_2__
              (clojure.core/nth input__14513 2)]
             (clojure.core/case
              input__14513_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14513_nth_1__)
               (clojure.core/loop
                [S__16472
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14513_nth_1__)]
                (if
                 (clojure.core/seq S__16472)
                 (clojure.core/let
                  [input__14513_nth_1___parts__
                   (clojure.core/first S__16472)
                   R__16473
                   (clojure.core/let
                    [input__14513_nth_1___L__
                     (clojure.core/nth input__14513_nth_1___parts__ 0)
                     input__14513_nth_1___R__
                     (clojure.core/nth input__14513_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [R__16478
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__14513_nth_1___L__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__14945]
                         (clojure.core/let
                          [input__14945_nth_0__
                           (clojure.core/nth input__14945 0)]
                          (clojure.core/let
                           [R__16476
                            (if
                             (clojure.core/symbol?
                              input__14945_nth_0__)
                             (clojure.core/let
                              [X__14947
                               (clojure.core/namespace
                                input__14945_nth_0__)]
                              (clojure.core/case
                               X__14947
                               (nil)
                               (clojure.core/let
                                [X__14949
                                 (clojure.core/name
                                  input__14945_nth_0__)]
                                (if
                                 (clojure.core/string? X__14949)
                                 (if
                                  (clojure.core/re-matches
                                   #"\.\.(?:\.|\d+)"
                                   X__14949)
                                  true
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail)))
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))]
                           (if
                            (meander.runtime.zeta/fail? R__16476)
                            (clojure.core/let
                             [!xs
                              (clojure.core/conj
                               !xs
                               input__14945_nth_0__)]
                             [!xs])
                            (meander.runtime.zeta/fail)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__14513_nth_1___R___L__
                           (clojure.core/subvec
                            input__14513_nth_1___R__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__14513_nth_1___R__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__14513_nth_1___R___L__)
                            1)
                           (clojure.core/let
                            [input__14513_nth_1___R___R__
                             (clojure.core/subvec
                              input__14513_nth_1___R__
                              1)]
                            (clojure.core/let
                             [input__14513_nth_1___R___L___nth_0__
                              (clojure.core/nth
                               input__14513_nth_1___R___L__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__14513_nth_1___R___L___nth_0__)
                              (clojure.core/let
                               [X__14939
                                (clojure.core/namespace
                                 input__14513_nth_1___R___L___nth_0__)]
                               (clojure.core/case
                                X__14939
                                (nil)
                                (clojure.core/let
                                 [X__14941
                                  (clojure.core/name
                                   input__14513_nth_1___R___L___nth_0__)]
                                 (if
                                  (clojure.core/string? X__14941)
                                  (clojure.core/let
                                   [R__14942
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__14941)]
                                   (if
                                    (clojure.core/some? R__14942)
                                    (if
                                     (clojure.core/vector? R__14942)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count R__14942)
                                       2)
                                      (clojure.core/let
                                       [R__14942_nth_1__
                                        (clojure.core/nth R__14942 1)]
                                       (clojure.core/let
                                        [?n R__14942_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__14513_nth_1___R___R__]
                                         (clojure.core/let
                                          [R__16477
                                           (D__14928
                                            input__14513_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            R__16477)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env] R__16477]
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
                                                 [R__14613
                                                  (C__14591
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   R__14613)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   R__14613
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [R__14614
                                                  (C__14591
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   R__14614)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   R__14614
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__10399__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10399__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10399__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? R__16478)
                       (meander.runtime.zeta/fail)
                       R__16478))))]
                  (if
                   (meander.runtime.zeta/fail? R__16473)
                   (recur (clojure.core/next S__16472))
                   R__16473))
                 (state__16396)))
               (state__16396))
              (state__16396)))
            (state__16396))
           (state__16396))))
        (state__16396
         []
         (if
          (clojure.core/vector? input__14513)
          (if
           (clojure.core/= (clojure.core/count input__14513) 3)
           (clojure.core/let
            [input__14513_nth_0__
             (clojure.core/nth input__14513 0)
             input__14513_nth_1__
             (clojure.core/nth input__14513 1)
             input__14513_nth_2__
             (clojure.core/nth input__14513 2)]
            (clojure.core/case
             input__14513_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__14513_nth_1__)
              (clojure.core/let
               [input__14513_nth_1___L__
                (clojure.core/subvec
                 input__14513_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__14513_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__14513_nth_1___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_1___R__
                  (clojure.core/subvec input__14513_nth_1__ 1)]
                 (clojure.core/let
                  [input__14513_nth_1___L___nth_0__
                   (clojure.core/nth input__14513_nth_1___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_1___L___nth_0__)
                   (clojure.core/let
                    [X__14976
                     (clojure.core/namespace
                      input__14513_nth_1___L___nth_0__)]
                    (clojure.core/case
                     X__14976
                     (nil)
                     (clojure.core/let
                      [X__14978
                       (clojure.core/name
                        input__14513_nth_1___L___nth_0__)]
                      (if
                       (clojure.core/string? X__14978)
                       (clojure.core/let
                        [R__14979
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__14978)]
                        (if
                         (clojure.core/some? R__14979)
                         (if
                          (clojure.core/vector? R__14979)
                          (if
                           (clojure.core/=
                            (clojure.core/count R__14979)
                            2)
                           (clojure.core/let
                            [R__14979_nth_1__
                             (clojure.core/nth R__14979 1)]
                            (clojure.core/let
                             [?n R__14979_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__14513_nth_1___L___nth_0__]
                              (clojure.core/let
                               [?rest input__14513_nth_1___R__]
                               (clojure.core/let
                                [?env input__14513_nth_2__]
                                [{:tag :syntax-error,
                                  :message
                                  "The ?n or more operator ..?n must be preceeded by at least one pattern"}])))))
                           (state__16397))
                          (state__16397))
                         (state__16397)))
                       (state__16397)))
                     (state__16397)))
                   (state__16397))))
                (state__16397)))
              (state__16397))
             (state__16397)))
           (state__16397))
          (state__16397)))
        (state__16397
         []
         (clojure.core/letfn
          [(D__14982
            [T__15005]
            (clojure.core/letfn
             [(state__16479
               []
               (clojure.core/let
                [T__15006 :string-logical-plus]
                (clojure.core/let
                 [?tag T__15006]
                 (if
                  (clojure.core/map? T__15005)
                  (clojure.core/let
                   [T__15007 (.valAt T__15005 :context)]
                   (clojure.core/case
                    T__15007
                    (:string)
                    (clojure.core/let
                     [T__15008 (clojure.core/dissoc T__15005 :context)]
                     (clojure.core/let [?env T__15005] [?tag ?env]))
                    (state__16480)))
                  (state__16480)))))
              (state__16480
               []
               (clojure.core/let
                [T__15009 :logical-plus]
                (clojure.core/let
                 [?tag T__15009]
                 (clojure.core/let [?env T__15005] [?tag ?env]))))]
             (state__16479)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 3)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)
              input__14513_nth_2__
              (clojure.core/nth input__14513 2)]
             (clojure.core/case
              input__14513_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14513_nth_1__)
               (clojure.core/loop
                [S__16481
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14513_nth_1__)]
                (if
                 (clojure.core/seq S__16481)
                 (clojure.core/let
                  [input__14513_nth_1___parts__
                   (clojure.core/first S__16481)
                   R__16482
                   (clojure.core/let
                    [input__14513_nth_1___L__
                     (clojure.core/nth input__14513_nth_1___parts__ 0)
                     input__14513_nth_1___R__
                     (clojure.core/nth input__14513_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [R__16487
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__14513_nth_1___L__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__14999]
                         (clojure.core/let
                          [input__14999_nth_0__
                           (clojure.core/nth input__14999 0)]
                          (clojure.core/let
                           [R__16485
                            (if
                             (clojure.core/symbol?
                              input__14999_nth_0__)
                             (clojure.core/let
                              [X__15001
                               (clojure.core/namespace
                                input__14999_nth_0__)]
                              (clojure.core/case
                               X__15001
                               (nil)
                               (clojure.core/let
                                [X__15003
                                 (clojure.core/name
                                  input__14999_nth_0__)]
                                (if
                                 (clojure.core/string? X__15003)
                                 (if
                                  (clojure.core/re-matches
                                   #"\.\.(?:\.|\d+)"
                                   X__15003)
                                  true
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail)))
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))]
                           (if
                            (meander.runtime.zeta/fail? R__16485)
                            (clojure.core/let
                             [!xs
                              (clojure.core/conj
                               !xs
                               input__14999_nth_0__)]
                             [!xs])
                            (meander.runtime.zeta/fail)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__14513_nth_1___R___L__
                           (clojure.core/subvec
                            input__14513_nth_1___R__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__14513_nth_1___R__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__14513_nth_1___R___L__)
                            1)
                           (clojure.core/let
                            [input__14513_nth_1___R___R__
                             (clojure.core/subvec
                              input__14513_nth_1___R__
                              1)]
                            (clojure.core/let
                             [input__14513_nth_1___R___L___nth_0__
                              (clojure.core/nth
                               input__14513_nth_1___R___L__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__14513_nth_1___R___L___nth_0__)
                              (clojure.core/let
                               [X__14993
                                (clojure.core/namespace
                                 input__14513_nth_1___R___L___nth_0__)]
                               (clojure.core/case
                                X__14993
                                (nil)
                                (clojure.core/let
                                 [X__14995
                                  (clojure.core/name
                                   input__14513_nth_1___R___L___nth_0__)]
                                 (if
                                  (clojure.core/string? X__14995)
                                  (clojure.core/let
                                   [R__14996
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__14995)]
                                   (if
                                    (clojure.core/some? R__14996)
                                    (if
                                     (clojure.core/vector? R__14996)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count R__14996)
                                       2)
                                      (clojure.core/let
                                       [R__14996_nth_1__
                                        (clojure.core/nth R__14996 1)]
                                       (clojure.core/let
                                        [?n R__14996_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__14513_nth_1___R___R__]
                                         (clojure.core/let
                                          [R__16486
                                           (D__14982
                                            input__14513_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            R__16486)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env] R__16486]
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
                                                 [R__14615
                                                  (C__14591
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   R__14615)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   R__14615
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [R__14616
                                                  (C__14591
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   R__14616)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   R__14616
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__10399__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10399__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10399__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? R__16487)
                       (meander.runtime.zeta/fail)
                       R__16487))))]
                  (if
                   (meander.runtime.zeta/fail? R__16482)
                   (recur (clojure.core/next S__16481))
                   R__16482))
                 (state__16398)))
               (state__16398))
              (state__16398)))
            (state__16398))
           (state__16398))))
        (state__16398
         []
         (if
          (clojure.core/vector? input__14513)
          (if
           (clojure.core/= (clojure.core/count input__14513) 3)
           (clojure.core/let
            [input__14513_nth_0__
             (clojure.core/nth input__14513 0)
             input__14513_nth_1__
             (clojure.core/nth input__14513 1)
             input__14513_nth_2__
             (clojure.core/nth input__14513 2)]
            (clojure.core/case
             input__14513_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__14513_nth_1__)
              (clojure.core/let
               [input__14513_nth_1___L__
                (clojure.core/subvec
                 input__14513_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__14513_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__14513_nth_1___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_1___R__
                  (clojure.core/subvec input__14513_nth_1__ 1)]
                 (clojure.core/let
                  [input__14513_nth_1___L___nth_0__
                   (clojure.core/nth input__14513_nth_1___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_1___L___nth_0__)
                   (clojure.core/let
                    [X__15030
                     (clojure.core/namespace
                      input__14513_nth_1___L___nth_0__)]
                    (clojure.core/case
                     X__15030
                     (nil)
                     (clojure.core/let
                      [X__15032
                       (clojure.core/name
                        input__14513_nth_1___L___nth_0__)]
                      (if
                       (clojure.core/string? X__15032)
                       (clojure.core/let
                        [R__15033
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__15032)]
                        (if
                         (clojure.core/some? R__15033)
                         (if
                          (clojure.core/vector? R__15033)
                          (if
                           (clojure.core/=
                            (clojure.core/count R__15033)
                            2)
                           (clojure.core/let
                            [R__15033_nth_1__
                             (clojure.core/nth R__15033 1)]
                            (clojure.core/let
                             [?n R__15033_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__14513_nth_1___L___nth_0__]
                              (clojure.core/let
                               [?rest input__14513_nth_1___R__]
                               (clojure.core/let
                                [?env input__14513_nth_2__]
                                [{:tag :syntax-error,
                                  :message
                                  "The operator ..!n must be preceeded by at least one pattern"}])))))
                           (state__16399))
                          (state__16399))
                         (state__16399)))
                       (state__16399)))
                     (state__16399)))
                   (state__16399))))
                (state__16399)))
              (state__16399))
             (state__16399)))
           (state__16399))
          (state__16399)))
        (state__16399
         []
         (clojure.core/letfn
          [(D__15036
            [T__15059]
            (clojure.core/letfn
             [(state__16488
               []
               (clojure.core/let
                [T__15060 :string-memory-plus]
                (clojure.core/let
                 [?tag T__15060]
                 (if
                  (clojure.core/map? T__15059)
                  (clojure.core/let
                   [T__15061 (.valAt T__15059 :context)]
                   (clojure.core/case
                    T__15061
                    (:string)
                    (clojure.core/let
                     [T__15062 (clojure.core/dissoc T__15059 :context)]
                     (clojure.core/let [?env T__15059] [?tag ?env]))
                    (state__16489)))
                  (state__16489)))))
              (state__16489
               []
               (clojure.core/let
                [T__15063 :memory-plus]
                (clojure.core/let
                 [?tag T__15063]
                 (clojure.core/let [?env T__15059] [?tag ?env]))))]
             (state__16488)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 3)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)
              input__14513_nth_2__
              (clojure.core/nth input__14513 2)]
             (clojure.core/case
              input__14513_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14513_nth_1__)
               (clojure.core/loop
                [S__16490
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14513_nth_1__)]
                (if
                 (clojure.core/seq S__16490)
                 (clojure.core/let
                  [input__14513_nth_1___parts__
                   (clojure.core/first S__16490)
                   R__16491
                   (clojure.core/let
                    [input__14513_nth_1___L__
                     (clojure.core/nth input__14513_nth_1___parts__ 0)
                     input__14513_nth_1___R__
                     (clojure.core/nth input__14513_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [R__16496
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__14513_nth_1___L__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__15053]
                         (clojure.core/let
                          [input__15053_nth_0__
                           (clojure.core/nth input__15053 0)]
                          (clojure.core/let
                           [R__16494
                            (if
                             (clojure.core/symbol?
                              input__15053_nth_0__)
                             (clojure.core/let
                              [X__15055
                               (clojure.core/namespace
                                input__15053_nth_0__)]
                              (clojure.core/case
                               X__15055
                               (nil)
                               (clojure.core/let
                                [X__15057
                                 (clojure.core/name
                                  input__15053_nth_0__)]
                                (if
                                 (clojure.core/string? X__15057)
                                 (if
                                  (clojure.core/re-matches
                                   #"\.\.(?:\.|\d+)"
                                   X__15057)
                                  true
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail)))
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))]
                           (if
                            (meander.runtime.zeta/fail? R__16494)
                            (clojure.core/let
                             [!xs
                              (clojure.core/conj
                               !xs
                               input__15053_nth_0__)]
                             [!xs])
                            (meander.runtime.zeta/fail)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__14513_nth_1___R___L__
                           (clojure.core/subvec
                            input__14513_nth_1___R__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__14513_nth_1___R__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__14513_nth_1___R___L__)
                            1)
                           (clojure.core/let
                            [input__14513_nth_1___R___R__
                             (clojure.core/subvec
                              input__14513_nth_1___R__
                              1)]
                            (clojure.core/let
                             [input__14513_nth_1___R___L___nth_0__
                              (clojure.core/nth
                               input__14513_nth_1___R___L__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__14513_nth_1___R___L___nth_0__)
                              (clojure.core/let
                               [X__15047
                                (clojure.core/namespace
                                 input__14513_nth_1___R___L___nth_0__)]
                               (clojure.core/case
                                X__15047
                                (nil)
                                (clojure.core/let
                                 [X__15049
                                  (clojure.core/name
                                   input__14513_nth_1___R___L___nth_0__)]
                                 (if
                                  (clojure.core/string? X__15049)
                                  (clojure.core/let
                                   [R__15050
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__15049)]
                                   (if
                                    (clojure.core/some? R__15050)
                                    (if
                                     (clojure.core/vector? R__15050)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count R__15050)
                                       2)
                                      (clojure.core/let
                                       [R__15050_nth_1__
                                        (clojure.core/nth R__15050 1)]
                                       (clojure.core/let
                                        [?n R__15050_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__14513_nth_1___R___R__]
                                         (clojure.core/let
                                          [R__16495
                                           (D__15036
                                            input__14513_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            R__16495)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env] R__16495]
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
                                                 [R__14617
                                                  (C__14591
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   R__14617)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   R__14617
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [R__14618
                                                  (C__14591
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   R__14618)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   R__14618
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__10399__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10399__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10399__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? R__16496)
                       (meander.runtime.zeta/fail)
                       R__16496))))]
                  (if
                   (meander.runtime.zeta/fail? R__16491)
                   (recur (clojure.core/next S__16490))
                   R__16491))
                 (state__16400)))
               (state__16400))
              (state__16400)))
            (state__16400))
           (state__16400))))
        (state__16400
         []
         (if
          (clojure.core/vector? input__14513)
          (clojure.core/letfn
           [(state__16497
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__14513_nth_1__)]
                  (clojure.core/let
                   [?env input__14513_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [R__14621
                        (C__14591
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__14620 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__14620
                               (clojure.core/let
                                [R__14619
                                 (C__14591
                                  [(if
                                    (.hasNext !xs__counter)
                                    (.next !xs__counter))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail? R__14619)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth R__14619 0)))))
                             (clojure.core/persistent!
                              return__14620))))
                          {:tag :empty}
                          ?env])]
                       (if
                        (meander.runtime.zeta/fail? R__14621)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth R__14621 0))))]
                    (catch
                     java.lang.Exception
                     e__10399__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10399__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10399__auto__))))))
                 (state__16498))
                (state__16498)))
              (state__16498)))
            (state__16498
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-cat)
                (clojure.core/case
                 input__14513_nth_1__
                 ([])
                 (clojure.core/let
                  [?next input__14513_nth_2__]
                  (clojure.core/let
                   [?env input__14513_nth_3__]
                   [?next]))
                 (state__16499))
                (state__16499)))
              (state__16499)))
            (state__16499
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-cat)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/loop
                  [S__16519
                   (meander.runtime.zeta/epsilon-partitions
                    2
                    input__14513_nth_1__)]
                  (if
                   (clojure.core/seq S__16519)
                   (clojure.core/let
                    [input__14513_nth_1___parts__
                     (clojure.core/first S__16519)
                     R__16520
                     (clojure.core/let
                      [input__14513_nth_1___L__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        0)
                       input__14513_nth_1___R__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs []]
                       (clojure.core/let
                        [R__16524
                         (meander.runtime.zeta/epsilon-run-star-1
                          input__14513_nth_1___L__
                          [!xs]
                          (clojure.core/fn
                           [[!xs] input__15093]
                           (clojure.core/let
                            [input__15093_nth_0__
                             (clojure.core/nth input__15093 0)]
                            (clojure.core/let
                             [R__16523
                              (if
                               (clojure.core/map? input__15093_nth_0__)
                               (clojure.core/let
                                [T__15094
                                 (.valAt input__15093_nth_0__ :tag)]
                                (clojure.core/case
                                 T__15094
                                 (:group)
                                 (clojure.core/let
                                  [T__15095
                                   (clojure.core/dissoc
                                    input__15093_nth_0__
                                    :tag)]
                                  true)
                                 (meander.runtime.zeta/fail)))
                               (meander.runtime.zeta/fail))]
                             (if
                              (meander.runtime.zeta/fail? R__16523)
                              (clojure.core/let
                               [!xs
                                (clojure.core/conj
                                 !xs
                                 input__15093_nth_0__)]
                               [!xs])
                              (meander.runtime.zeta/fail)))))
                          (clojure.core/fn
                           [[!xs]]
                           (clojure.core/let
                            [input__14513_nth_1___R___L__
                             (clojure.core/subvec
                              input__14513_nth_1___R__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__14513_nth_1___R__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__14513_nth_1___R___L__)
                              1)
                             (clojure.core/let
                              [input__14513_nth_1___R___R__
                               (clojure.core/subvec
                                input__14513_nth_1___R__
                                1)]
                              (clojure.core/let
                               [input__14513_nth_1___R___L___nth_0__
                                (clojure.core/nth
                                 input__14513_nth_1___R___L__
                                 0)]
                               (if
                                (clojure.core/map?
                                 input__14513_nth_1___R___L___nth_0__)
                                (clojure.core/let
                                 [T__15091
                                  (.valAt
                                   input__14513_nth_1___R___L___nth_0__
                                   :tag)]
                                 (clojure.core/case
                                  T__15091
                                  (:group)
                                  (clojure.core/let
                                   [T__15092
                                    (clojure.core/dissoc
                                     input__14513_nth_1___R___L___nth_0__
                                     :tag)]
                                   (clojure.core/let
                                    [?group
                                     input__14513_nth_1___R___L___nth_0__]
                                    (clojure.core/let
                                     [?rest
                                      input__14513_nth_1___R___R__]
                                     (clojure.core/let
                                      [?next input__14513_nth_2__]
                                      (clojure.core/let
                                       [?env input__14513_nth_3__]
                                       (try
                                        [(clojure.core/let
                                          [!xs__counter
                                           (meander.runtime.zeta/iterator
                                            !xs)]
                                          (clojure.core/let
                                           [R__14625
                                            (C__14591
                                             ['meander.dev.parse.zeta/make-join
                                              (clojure.core/let
                                               [R__14622
                                                (C__14591
                                                 ['meander.dev.parse.zeta/make-cat
                                                  (clojure.core/into
                                                   []
                                                   (clojure.core/vec
                                                    (clojure.core/iterator-seq
                                                     !xs__counter)))
                                                  {:tag :empty}
                                                  ?env])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 R__14622)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 R__14622
                                                 0)))
                                              (clojure.core/let
                                               [R__14624
                                                (C__14591
                                                 ['meander.dev.parse.zeta/make-join
                                                  ?group
                                                  (clojure.core/let
                                                   [R__14623
                                                    (C__14591
                                                     ['meander.dev.parse.zeta/make-cat
                                                      ?rest
                                                      ?next
                                                      ?env])]
                                                   (if
                                                    (meander.runtime.zeta/fail?
                                                     R__14623)
                                                    (throw
                                                     (meander.runtime.zeta/fail))
                                                    (clojure.core/nth
                                                     R__14623
                                                     0)))
                                                  ?env])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 R__14624)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 R__14624
                                                 0)))
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             R__14625)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             R__14625
                                             0))))]
                                        (catch
                                         java.lang.Exception
                                         e__10399__auto__
                                         (if
                                          (meander.runtime.zeta/fail?
                                           e__10399__auto__)
                                          (meander.runtime.zeta/fail)
                                          (throw
                                           e__10399__auto__)))))))))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail))))
                             (meander.runtime.zeta/fail)))))]
                        (if
                         (meander.runtime.zeta/fail? R__16524)
                         (meander.runtime.zeta/fail)
                         R__16524))))]
                    (if
                     (meander.runtime.zeta/fail? R__16520)
                     (recur (clojure.core/next S__16519))
                     R__16520))
                   (state__16500)))
                 (state__16500))
                (state__16500)))
              (state__16500)))
            (state__16500
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-cat)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/loop
                  [S__16525
                   (meander.runtime.zeta/epsilon-partitions
                    2
                    input__14513_nth_1__)]
                  (if
                   (clojure.core/seq S__16525)
                   (clojure.core/let
                    [input__14513_nth_1___parts__
                     (clojure.core/first S__16525)
                     R__16526
                     (clojure.core/let
                      [input__14513_nth_1___L__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        0)
                       input__14513_nth_1___R__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs []]
                       (clojure.core/let
                        [R__16530
                         (meander.runtime.zeta/epsilon-run-star-1
                          input__14513_nth_1___L__
                          [!xs]
                          (clojure.core/fn
                           [[!xs] input__15105]
                           (clojure.core/let
                            [input__15105_nth_0__
                             (clojure.core/nth input__15105 0)]
                            (clojure.core/let
                             [R__16529
                              (if
                               (clojure.core/map? input__15105_nth_0__)
                               (clojure.core/let
                                [T__15106
                                 (.valAt input__15105_nth_0__ :tag)]
                                (clojure.core/case
                                 T__15106
                                 (:star)
                                 (clojure.core/let
                                  [T__15107
                                   (clojure.core/dissoc
                                    input__15105_nth_0__
                                    :tag)]
                                  true)
                                 (meander.runtime.zeta/fail)))
                               (meander.runtime.zeta/fail))]
                             (if
                              (meander.runtime.zeta/fail? R__16529)
                              (clojure.core/let
                               [!xs
                                (clojure.core/conj
                                 !xs
                                 input__15105_nth_0__)]
                               [!xs])
                              (meander.runtime.zeta/fail)))))
                          (clojure.core/fn
                           [[!xs]]
                           (clojure.core/let
                            [input__14513_nth_1___R___L__
                             (clojure.core/subvec
                              input__14513_nth_1___R__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__14513_nth_1___R__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__14513_nth_1___R___L__)
                              1)
                             (clojure.core/let
                              [input__14513_nth_1___R___R__
                               (clojure.core/subvec
                                input__14513_nth_1___R__
                                1)]
                              (clojure.core/let
                               [input__14513_nth_1___R___L___nth_0__
                                (clojure.core/nth
                                 input__14513_nth_1___R___L__
                                 0)]
                               (if
                                (clojure.core/map?
                                 input__14513_nth_1___R___L___nth_0__)
                                (clojure.core/let
                                 [T__15103
                                  (.valAt
                                   input__14513_nth_1___R___L___nth_0__
                                   :tag)]
                                 (clojure.core/case
                                  T__15103
                                  (:star)
                                  (clojure.core/let
                                   [T__15104
                                    (clojure.core/dissoc
                                     input__14513_nth_1___R___L___nth_0__
                                     :tag)]
                                   (clojure.core/let
                                    [?star
                                     input__14513_nth_1___R___L___nth_0__]
                                    (clojure.core/let
                                     [?rest
                                      input__14513_nth_1___R___R__]
                                     (clojure.core/let
                                      [?next input__14513_nth_2__]
                                      (clojure.core/let
                                       [?env input__14513_nth_3__]
                                       (try
                                        [(clojure.core/let
                                          [!xs__counter
                                           (meander.runtime.zeta/iterator
                                            !xs)]
                                          (clojure.core/let
                                           [R__14629
                                            (C__14591
                                             ['meander.dev.parse.zeta/make-join
                                              (clojure.core/let
                                               [R__14626
                                                (C__14591
                                                 ['meander.dev.parse.zeta/make-cat
                                                  (clojure.core/into
                                                   []
                                                   (clojure.core/vec
                                                    (clojure.core/iterator-seq
                                                     !xs__counter)))
                                                  {:tag :empty}
                                                  ?env])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 R__14626)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 R__14626
                                                 0)))
                                              (clojure.core/let
                                               [R__14628
                                                (C__14591
                                                 ['meander.dev.parse.zeta/make-join
                                                  ?star
                                                  (clojure.core/let
                                                   [R__14627
                                                    (C__14591
                                                     ['meander.dev.parse.zeta/make-cat
                                                      ?rest
                                                      ?next
                                                      ?env])]
                                                   (if
                                                    (meander.runtime.zeta/fail?
                                                     R__14627)
                                                    (throw
                                                     (meander.runtime.zeta/fail))
                                                    (clojure.core/nth
                                                     R__14627
                                                     0)))
                                                  ?env])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 R__14628)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 R__14628
                                                 0)))
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             R__14629)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             R__14629
                                             0))))]
                                        (catch
                                         java.lang.Exception
                                         e__10399__auto__
                                         (if
                                          (meander.runtime.zeta/fail?
                                           e__10399__auto__)
                                          (meander.runtime.zeta/fail)
                                          (throw
                                           e__10399__auto__)))))))))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail))))
                             (meander.runtime.zeta/fail)))))]
                        (if
                         (meander.runtime.zeta/fail? R__16530)
                         (meander.runtime.zeta/fail)
                         R__16530))))]
                    (if
                     (meander.runtime.zeta/fail? R__16526)
                     (recur (clojure.core/next S__16525))
                     R__16526))
                   (state__16501)))
                 (state__16501))
                (state__16501)))
              (state__16501)))
            (state__16501
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-cat)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/loop
                  [S__16531
                   (meander.runtime.zeta/epsilon-partitions
                    2
                    input__14513_nth_1__)]
                  (if
                   (clojure.core/seq S__16531)
                   (clojure.core/let
                    [input__14513_nth_1___parts__
                     (clojure.core/first S__16531)
                     R__16532
                     (clojure.core/let
                      [input__14513_nth_1___L__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        0)
                       input__14513_nth_1___R__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [input__14513_nth_1___L___L__
                        (clojure.core/subvec
                         input__14513_nth_1___L__
                         0
                         (clojure.core/min
                          (clojure.core/count input__14513_nth_1___L__)
                          1))]
                       (if
                        (clojure.core/=
                         (clojure.core/count
                          input__14513_nth_1___L___L__)
                         1)
                        (clojure.core/let
                         [input__14513_nth_1___L___R__
                          (clojure.core/subvec
                           input__14513_nth_1___L__
                           1)]
                         (clojure.core/let
                          [input__14513_nth_1___L___L___nth_0__
                           (clojure.core/nth
                            input__14513_nth_1___L___L__
                            0)]
                          (clojure.core/let
                           [R__16537
                            (if
                             (clojure.core/map?
                              input__14513_nth_1___L___L___nth_0__)
                             (clojure.core/let
                              [T__15115
                               (.valAt
                                input__14513_nth_1___L___L___nth_0__
                                :tag)]
                              (clojure.core/case
                               T__15115
                               (:reference)
                               (clojure.core/let
                                [T__15116
                                 (clojure.core/dissoc
                                  input__14513_nth_1___L___L___nth_0__
                                  :tag)]
                                true)
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))]
                           (if
                            (meander.runtime.zeta/fail? R__16537)
                            (clojure.core/let
                             [!xs []]
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__14513_nth_1___L___L___nth_0__)]
                              (clojure.core/let
                               [R__16536
                                (meander.runtime.zeta/epsilon-run-star-1
                                 input__14513_nth_1___L___R__
                                 [!xs]
                                 (clojure.core/fn
                                  [[!xs] input__15121]
                                  (clojure.core/let
                                   [input__15121_nth_0__
                                    (clojure.core/nth input__15121 0)]
                                   (clojure.core/let
                                    [R__16535
                                     (if
                                      (clojure.core/map?
                                       input__15121_nth_0__)
                                      (clojure.core/let
                                       [T__15122
                                        (.valAt
                                         input__15121_nth_0__
                                         :tag)]
                                       (clojure.core/case
                                        T__15122
                                        (:reference)
                                        (clojure.core/let
                                         [T__15123
                                          (clojure.core/dissoc
                                           input__15121_nth_0__
                                           :tag)]
                                         true)
                                        (meander.runtime.zeta/fail)))
                                      (meander.runtime.zeta/fail))]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      R__16535)
                                     (clojure.core/let
                                      [!xs
                                       (clojure.core/conj
                                        !xs
                                        input__15121_nth_0__)]
                                      [!xs])
                                     (meander.runtime.zeta/fail)))))
                                 (clojure.core/fn
                                  [[!xs]]
                                  (clojure.core/let
                                   [input__14513_nth_1___R___L__
                                    (clojure.core/subvec
                                     input__14513_nth_1___R__
                                     0
                                     (clojure.core/min
                                      (clojure.core/count
                                       input__14513_nth_1___R__)
                                      1))]
                                   (if
                                    (clojure.core/=
                                     (clojure.core/count
                                      input__14513_nth_1___R___L__)
                                     1)
                                    (clojure.core/let
                                     [input__14513_nth_1___R___R__
                                      (clojure.core/subvec
                                       input__14513_nth_1___R__
                                       1)]
                                     (clojure.core/let
                                      [input__14513_nth_1___R___L___nth_0__
                                       (clojure.core/nth
                                        input__14513_nth_1___R___L__
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__14513_nth_1___R___L___nth_0__)
                                       (clojure.core/let
                                        [T__15119
                                         (.valAt
                                          input__14513_nth_1___R___L___nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         T__15119
                                         (:reference)
                                         (clojure.core/let
                                          [T__15120
                                           (clojure.core/dissoc
                                            input__14513_nth_1___R___L___nth_0__
                                            :tag)]
                                          (clojure.core/let
                                           [?reference
                                            input__14513_nth_1___R___L___nth_0__]
                                           (clojure.core/let
                                            [?rest
                                             input__14513_nth_1___R___R__]
                                            (clojure.core/let
                                             [?next
                                              input__14513_nth_2__]
                                             (clojure.core/let
                                              [?env
                                               input__14513_nth_3__]
                                              (try
                                               [(clojure.core/let
                                                 [!xs__counter
                                                  (meander.runtime.zeta/iterator
                                                   !xs)]
                                                 (clojure.core/let
                                                  [R__14634
                                                   (C__14591
                                                    ['meander.dev.parse.zeta/make-join
                                                     (clojure.core/let
                                                      [R__14630
                                                       (C__14591
                                                        ['meander.dev.parse.zeta/make-cat
                                                         (clojure.core/into
                                                          []
                                                          (clojure.core/vec
                                                           (clojure.core/iterator-seq
                                                            !xs__counter)))
                                                         {:tag :empty}
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        R__14630)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        R__14630
                                                        0)))
                                                     (clojure.core/let
                                                      [R__14633
                                                       (C__14591
                                                        ['meander.dev.parse.zeta/make-join
                                                         (clojure.core/let
                                                          [R__14631
                                                           (C__14591
                                                            ['meander.dev.parse.zeta/make-cat
                                                             [?reference]
                                                             {:tag
                                                              :empty}
                                                             ?env])]
                                                          (if
                                                           (meander.runtime.zeta/fail?
                                                            R__14631)
                                                           (throw
                                                            (meander.runtime.zeta/fail))
                                                           (clojure.core/nth
                                                            R__14631
                                                            0)))
                                                         (clojure.core/let
                                                          [R__14632
                                                           (C__14591
                                                            ['meander.dev.parse.zeta/make-cat
                                                             ?rest
                                                             ?next
                                                             ?env])]
                                                          (if
                                                           (meander.runtime.zeta/fail?
                                                            R__14632)
                                                           (throw
                                                            (meander.runtime.zeta/fail))
                                                           (clojure.core/nth
                                                            R__14632
                                                            0)))
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        R__14633)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        R__14633
                                                        0)))
                                                     ?env])]
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    R__14634)
                                                   (throw
                                                    (meander.runtime.zeta/fail))
                                                   (clojure.core/nth
                                                    R__14634
                                                    0))))]
                                               (catch
                                                java.lang.Exception
                                                e__10399__auto__
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  e__10399__auto__)
                                                 (meander.runtime.zeta/fail)
                                                 (throw
                                                  e__10399__auto__)))))))))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail))))
                                    (meander.runtime.zeta/fail)))))]
                               (if
                                (meander.runtime.zeta/fail? R__16536)
                                (meander.runtime.zeta/fail)
                                R__16536))))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? R__16532)
                     (recur (clojure.core/next S__16531))
                     R__16532))
                   (state__16502)))
                 (state__16502))
                (state__16502)))
              (state__16502)))
            (state__16502
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-cat)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/loop
                  [S__16538
                   (meander.runtime.zeta/epsilon-partitions
                    2
                    input__14513_nth_1__)]
                  (if
                   (clojure.core/seq S__16538)
                   (clojure.core/let
                    [input__14513_nth_1___parts__
                     (clojure.core/first S__16538)
                     R__16539
                     (clojure.core/let
                      [input__14513_nth_1___L__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        0)
                       input__14513_nth_1___R__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [input__14513_nth_1___L___L__
                        (clojure.core/subvec
                         input__14513_nth_1___L__
                         0
                         (clojure.core/min
                          (clojure.core/count input__14513_nth_1___L__)
                          1))]
                       (if
                        (clojure.core/=
                         (clojure.core/count
                          input__14513_nth_1___L___L__)
                         1)
                        (clojure.core/let
                         [input__14513_nth_1___L___R__
                          (clojure.core/subvec
                           input__14513_nth_1___L__
                           1)]
                         (clojure.core/let
                          [input__14513_nth_1___L___L___nth_0__
                           (clojure.core/nth
                            input__14513_nth_1___L___L__
                            0)]
                          (if
                           (clojure.core/map?
                            input__14513_nth_1___L___L___nth_0__)
                           (clojure.core/let
                            [T__15133
                             (.valAt
                              input__14513_nth_1___L___L___nth_0__
                              :form)
                             T__15132
                             (.valAt
                              input__14513_nth_1___L___L___nth_0__
                              :type)
                             T__15131
                             (.valAt
                              input__14513_nth_1___L___L___nth_0__
                              :tag)]
                            (clojure.core/case
                             T__15131
                             (:literal)
                             (clojure.core/case
                              T__15132
                              (:string)
                              (clojure.core/let
                               [!forms []]
                               (clojure.core/let
                                [!forms
                                 (clojure.core/conj !forms T__15133)]
                                (clojure.core/let
                                 [T__15134
                                  (clojure.core/dissoc
                                   input__14513_nth_1___L___L___nth_0__
                                   :tag
                                   :type
                                   :form)]
                                 (clojure.core/loop
                                  [N__16543
                                   0
                                   coll__16541
                                   input__14513_nth_1___L___R__
                                   [!forms]
                                   [!forms]]
                                  (clojure.core/let
                                   [input__15135
                                    (clojure.core/subvec
                                     coll__16541
                                     0
                                     (clojure.core/min
                                      (clojure.core/count coll__16541)
                                      1))]
                                   (if
                                    (clojure.core/=
                                     (clojure.core/count input__15135)
                                     1)
                                    (clojure.core/let
                                     [R__16542
                                      (clojure.core/let
                                       [input__15135_nth_0__
                                        (clojure.core/nth
                                         input__15135
                                         0)]
                                       (if
                                        (clojure.core/map?
                                         input__15135_nth_0__)
                                        (clojure.core/let
                                         [T__15138
                                          (.valAt
                                           input__15135_nth_0__
                                           :form)
                                          T__15137
                                          (.valAt
                                           input__15135_nth_0__
                                           :type)
                                          T__15136
                                          (.valAt
                                           input__15135_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          T__15136
                                          (:literal)
                                          (clojure.core/case
                                           T__15137
                                           (:string)
                                           (clojure.core/let
                                            [!forms
                                             (clojure.core/conj
                                              !forms
                                              T__15138)]
                                            (clojure.core/let
                                             [T__15139
                                              (clojure.core/dissoc
                                               input__15135_nth_0__
                                               :tag
                                               :type
                                               :form)]
                                             [!forms]))
                                           (meander.runtime.zeta/fail))
                                          (meander.runtime.zeta/fail)))
                                        (meander.runtime.zeta/fail)))]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       R__16542)
                                      (meander.runtime.zeta/fail)
                                      (recur
                                       (clojure.core/inc N__16543)
                                       (clojure.core/subvec
                                        coll__16541
                                        1)
                                       R__16542)))
                                    (if
                                     (clojure.core/or
                                      (clojure.core/seq coll__16541)
                                      (clojure.core/< N__16543 1))
                                     (meander.runtime.zeta/fail)
                                     (clojure.core/let
                                      [?rest input__14513_nth_1___R__]
                                      (clojure.core/let
                                       [?next input__14513_nth_2__]
                                       (clojure.core/let
                                        [?env input__14513_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [!forms__counter
                                            (meander.runtime.zeta/iterator
                                             !forms)]
                                           (clojure.core/let
                                            [R__14635
                                             (C__14591
                                              ['meander.dev.parse.zeta/make-cat
                                               (clojure.core/into
                                                []
                                                (clojure.core/concat
                                                 (clojure.core/list
                                                  {:tag :literal,
                                                   :type :string,
                                                   :form
                                                   (clojure.string/join
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !forms__counter))))})
                                                 ?rest))
                                               ?next
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              R__14635)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              R__14635
                                              0))))]
                                         (catch
                                          java.lang.Exception
                                          e__10399__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__10399__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__10399__auto__))))))))))))))
                              (meander.runtime.zeta/fail))
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? R__16539)
                     (recur (clojure.core/next S__16538))
                     R__16539))
                   (state__16503)))
                 (state__16503))
                (state__16503)))
              (state__16503)))
            (state__16503
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-cat)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/let
                  [input__14513_nth_1___L__
                   (clojure.core/subvec
                    input__14513_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__14513_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14513_nth_1___L__)
                    1)
                   (clojure.core/let
                    [input__14513_nth_1___R__
                     (clojure.core/subvec input__14513_nth_1__ 1)]
                    (clojure.core/let
                     [input__14513_nth_1___L___nth_0__
                      (clojure.core/nth input__14513_nth_1___L__ 0)]
                     (if
                      (clojure.core/map?
                       input__14513_nth_1___L___nth_0__)
                      (clojure.core/let
                       [T__15146
                        (.valAt input__14513_nth_1___L___nth_0__ :type)
                        T__15145
                        (.valAt input__14513_nth_1___L___nth_0__ :tag)]
                       (clojure.core/case
                        T__15145
                        (:literal)
                        (clojure.core/case
                         T__15146
                         (:string)
                         (clojure.core/let
                          [T__15147
                           (clojure.core/dissoc
                            input__14513_nth_1___L___nth_0__
                            :tag
                            :type)]
                          (clojure.core/let
                           [?ast input__14513_nth_1___L___nth_0__]
                           (clojure.core/let
                            [?rest input__14513_nth_1___R__]
                            (clojure.core/let
                             [?next input__14513_nth_2__]
                             (clojure.core/let
                              [?env input__14513_nth_3__]
                              (try
                               [(clojure.core/let
                                 [R__14637
                                  (C__14591
                                   ['meander.dev.parse.zeta/make-join
                                    ?ast
                                    (clojure.core/let
                                     [R__14636
                                      (C__14591
                                       ['meander.dev.parse.zeta/make-cat
                                        ?rest
                                        ?next
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       R__14636)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth R__14636 0)))
                                    ?env])]
                                 (if
                                  (meander.runtime.zeta/fail? R__14637)
                                  (throw (meander.runtime.zeta/fail))
                                  (clojure.core/nth R__14637 0)))]
                               (catch
                                java.lang.Exception
                                e__10399__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__10399__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__10399__auto__)))))))))
                         (state__16504))
                        (state__16504)))
                      (state__16504))))
                   (state__16504)))
                 (state__16504))
                (state__16504)))
              (state__16504)))
            (state__16504
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-cat)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/let
                  [input__14513_nth_1___L__
                   (clojure.core/subvec
                    input__14513_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__14513_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14513_nth_1___L__)
                    1)
                   (clojure.core/let
                    [input__14513_nth_1___R__
                     (clojure.core/subvec input__14513_nth_1__ 1)]
                    (clojure.core/let
                     [input__14513_nth_1___L___nth_0__
                      (clojure.core/nth input__14513_nth_1___L__ 0)]
                     (clojure.core/let
                      [?x input__14513_nth_1___L___nth_0__]
                      (clojure.core/let
                       [?sequence input__14513_nth_1___R__]
                       (clojure.core/let
                        [?next input__14513_nth_2__]
                        (if
                         (clojure.core/map? input__14513_nth_3__)
                         (clojure.core/let
                          [T__15150
                           (.valAt input__14513_nth_3__ :context)]
                          (clojure.core/case
                           T__15150
                           (:string)
                           (clojure.core/let
                            [T__15151
                             (clojure.core/dissoc
                              input__14513_nth_3__
                              :context)]
                            (clojure.core/let
                             [?env input__14513_nth_3__]
                             (try
                              [(clojure.core/let
                                [R__14639
                                 (C__14591
                                  ['meander.dev.parse.zeta/make-join
                                   ?x
                                   (clojure.core/let
                                    [R__14638
                                     (C__14591
                                      ['meander.dev.parse.zeta/make-cat
                                       ?sequence
                                       ?next
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      R__14638)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth R__14638 0)))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail? R__14639)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth R__14639 0)))]
                              (catch
                               java.lang.Exception
                               e__10399__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10399__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10399__auto__))))))
                           (state__16505)))
                         (state__16505)))))))
                   (state__16505)))
                 (state__16505))
                (state__16505)))
              (state__16505)))
            (state__16505
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-cat)
                (clojure.core/let
                 [?sequence input__14513_nth_1__]
                 (clojure.core/let
                  [?next input__14513_nth_2__]
                  [{:tag :cat, :sequence ?sequence, :next ?next}]))
                (state__16506)))
              (state__16506)))
            (state__16506
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-join)
                (if
                 (clojure.core/map? input__14513_nth_1__)
                 (clojure.core/let
                  [T__15159
                   (.valAt input__14513_nth_1__ :next)
                   T__15158
                   (.valAt input__14513_nth_1__ :tag)]
                  (clojure.core/case
                   T__15158
                   (:star)
                   (if
                    (clojure.core/map? T__15159)
                    (clojure.core/let
                     [T__15161 (.valAt T__15159 :tag)]
                     (clojure.core/case
                      T__15161
                      (:empty)
                      (clojure.core/let
                       [T__15162 (clojure.core/dissoc T__15159 :tag)]
                       (clojure.core/let
                        [T__15160
                         (clojure.core/dissoc
                          input__14513_nth_1__
                          :tag
                          :next)]
                        (clojure.core/let
                         [?left input__14513_nth_1__]
                         (clojure.core/let
                          [?right input__14513_nth_2__]
                          (clojure.core/let
                           [?env input__14513_nth_3__]
                           [(clojure.core/let
                             [form__9462__auto__
                              {:tag :star, :next ?right}]
                             (clojure.core/merge
                              (clojure.core/into {} ?left)
                              form__9462__auto__))])))))
                      (state__16507)))
                    (state__16507))
                   (state__16507)))
                 (state__16507))
                (state__16507)))
              (state__16507)))
            (state__16507
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-join)
                (clojure.core/let
                 [?left input__14513_nth_1__]
                 (if
                  (clojure.core/map? input__14513_nth_2__)
                  (clojure.core/let
                   [T__15165 (.valAt input__14513_nth_2__ :tag)]
                   (clojure.core/case
                    T__15165
                    (:empty)
                    (clojure.core/let
                     [T__15166
                      (clojure.core/dissoc input__14513_nth_2__ :tag)]
                     (clojure.core/let
                      [?env input__14513_nth_3__]
                      [?left]))
                    (state__16508)))
                  (state__16508)))
                (state__16508)))
              (state__16508)))
            (state__16508
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-join)
                (if
                 (clojure.core/map? input__14513_nth_1__)
                 (clojure.core/let
                  [T__15169 (.valAt input__14513_nth_1__ :tag)]
                  (clojure.core/case
                   T__15169
                   (:empty)
                   (clojure.core/let
                    [T__15170
                     (clojure.core/dissoc input__14513_nth_1__ :tag)]
                    (clojure.core/let
                     [?right input__14513_nth_2__]
                     (clojure.core/let
                      [?env input__14513_nth_3__]
                      [?right])))
                   (state__16509)))
                 (state__16509))
                (state__16509)))
              (state__16509)))
            (state__16509
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-join)
                (if
                 (clojure.core/map? input__14513_nth_1__)
                 (clojure.core/let
                  [T__15175
                   (.valAt input__14513_nth_1__ :next)
                   T__15174
                   (.valAt input__14513_nth_1__ :sequence)
                   T__15173
                   (.valAt input__14513_nth_1__ :tag)]
                  (clojure.core/case
                   T__15173
                   (:cat)
                   (clojure.core/let
                    [?sequence T__15174]
                    (if
                     (clojure.core/map? T__15175)
                     (clojure.core/let
                      [T__15177 (.valAt T__15175 :tag)]
                      (clojure.core/case
                       T__15177
                       (:empty)
                       (clojure.core/let
                        [T__15178 (clojure.core/dissoc T__15175 :tag)]
                        (clojure.core/let
                         [T__15176
                          (clojure.core/dissoc
                           input__14513_nth_1__
                           :tag
                           :sequence
                           :next)]
                         (clojure.core/let
                          [?right input__14513_nth_2__]
                          (clojure.core/let
                           [?env input__14513_nth_3__]
                           (try
                            [(clojure.core/let
                              [R__14640
                               (C__14591
                                ['meander.dev.parse.zeta/make-cat
                                 ?sequence
                                 ?right
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail? R__14640)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth R__14640 0)))]
                            (catch
                             java.lang.Exception
                             e__10399__auto__
                             (if
                              (meander.runtime.zeta/fail?
                               e__10399__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__10399__auto__))))))))
                       (state__16510)))
                     (state__16510)))
                   (state__16510)))
                 (state__16510))
                (state__16510)))
              (state__16510)))
            (state__16510
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-join)
                (if
                 (clojure.core/map? input__14513_nth_1__)
                 (clojure.core/let
                  [T__15181 (.valAt input__14513_nth_1__ :tag)]
                  (clojure.core/case
                   T__15181
                   (:cat)
                   (clojure.core/let
                    [T__15182
                     (clojure.core/dissoc input__14513_nth_1__ :tag)]
                    (clojure.core/let
                     [?ast input__14513_nth_1__]
                     (if
                      (clojure.core/map? input__14513_nth_2__)
                      (clojure.core/let
                       [T__15185
                        (.valAt input__14513_nth_2__ :next)
                        T__15184
                        (.valAt input__14513_nth_2__ :sequence)
                        T__15183
                        (.valAt input__14513_nth_2__ :tag)]
                       (clojure.core/case
                        T__15183
                        (:cat)
                        (clojure.core/let
                         [?sequence T__15184]
                         (clojure.core/let
                          [?next T__15185]
                          (clojure.core/let
                           [T__15186
                            (clojure.core/dissoc
                             input__14513_nth_2__
                             :tag
                             :sequence
                             :next)]
                           [{:tag :cat,
                             :sequence
                             (clojure.core/into
                              []
                              (clojure.core/concat
                               (clojure.core/list ?ast)
                               ?sequence)),
                             :next ?next}])))
                        (state__16511)))
                      (state__16511))))
                   (state__16511)))
                 (state__16511))
                (state__16511)))
              (state__16511)))
            (state__16511
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-join)
                (if
                 (clojure.core/map? input__14513_nth_1__)
                 (clojure.core/let
                  [T__15191
                   (.valAt input__14513_nth_1__ :form)
                   T__15190
                   (.valAt input__14513_nth_1__ :type)
                   T__15189
                   (.valAt input__14513_nth_1__ :tag)]
                  (clojure.core/case
                   T__15189
                   (:literal)
                   (clojure.core/case
                    T__15190
                    (:string)
                    (clojure.core/let
                     [?form T__15191]
                     (clojure.core/let
                      [T__15192
                       (clojure.core/dissoc
                        input__14513_nth_1__
                        :tag
                        :type
                        :form)]
                      (clojure.core/let
                       [?right input__14513_nth_2__]
                       [{:tag :string-prefix,
                         :form ?form,
                         :next ?right}])))
                    (state__16512))
                   (state__16512)))
                 (state__16512))
                (state__16512)))
              (state__16512)))
            (state__16512
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-join)
                (clojure.core/let
                 [?left input__14513_nth_1__]
                 (if
                  (clojure.core/map? input__14513_nth_2__)
                  (clojure.core/let
                   [T__15197
                    (.valAt input__14513_nth_2__ :form)
                    T__15196
                    (.valAt input__14513_nth_2__ :type)
                    T__15195
                    (.valAt input__14513_nth_2__ :tag)]
                   (clojure.core/case
                    T__15195
                    (:literal)
                    (clojure.core/case
                     T__15196
                     (:string)
                     (clojure.core/let
                      [?form T__15197]
                      (clojure.core/let
                       [T__15198
                        (clojure.core/dissoc
                         input__14513_nth_2__
                         :tag
                         :type
                         :form)]
                       [{:tag :string-suffix,
                         :form ?form,
                         :next ?left}]))
                     (state__16513))
                    (state__16513)))
                  (state__16513)))
                (state__16513)))
              (state__16513)))
            (state__16513
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-join)
                (if
                 (clojure.core/map? input__14513_nth_1__)
                 (clojure.core/let
                  [T__15203
                   (.valAt input__14513_nth_1__ :next)
                   T__15202
                   (.valAt input__14513_nth_1__ :pattern)
                   T__15201
                   (.valAt input__14513_nth_1__ :tag)]
                  (clojure.core/case
                   T__15201
                   (:string-star)
                   (clojure.core/let
                    [?pattern T__15202]
                    (if
                     (clojure.core/map? T__15203)
                     (clojure.core/let
                      [T__15207 (.valAt T__15203 :tag)]
                      (clojure.core/case
                       T__15207
                       (:empty)
                       (clojure.core/let
                        [T__15208 (clojure.core/dissoc T__15203 :tag)]
                        (clojure.core/let
                         [T__15204
                          (clojure.core/dissoc
                           input__14513_nth_1__
                           :tag
                           :pattern
                           :next)]
                         (clojure.core/let
                          [?right input__14513_nth_2__]
                          (if
                           (clojure.core/map? input__14513_nth_3__)
                           (clojure.core/let
                            [T__15205
                             (.valAt input__14513_nth_3__ :context)]
                            (clojure.core/case
                             T__15205
                             (:string)
                             (clojure.core/let
                              [T__15206
                               (clojure.core/dissoc
                                input__14513_nth_3__
                                :context)]
                              [{:tag :string-star,
                                :pattern ?pattern,
                                :next ?right}])
                             (state__16514)))
                           (state__16514)))))
                       (state__16514)))
                     (state__16514)))
                   (state__16514)))
                 (state__16514))
                (state__16514)))
              (state__16514)))
            (state__16514
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-join)
                (clojure.core/let
                 [?left input__14513_nth_1__]
                 (if
                  (clojure.core/map? input__14513_nth_2__)
                  (clojure.core/let
                   [T__15213
                    (.valAt input__14513_nth_2__ :next)
                    T__15212
                    (.valAt input__14513_nth_2__ :form)
                    T__15211
                    (.valAt input__14513_nth_2__ :tag)]
                   (clojure.core/case
                    T__15211
                    (:string-prefix)
                    (clojure.core/let
                     [?form T__15212]
                     (clojure.core/let
                      [?right T__15213]
                      (clojure.core/let
                       [T__15214
                        (clojure.core/dissoc
                         input__14513_nth_2__
                         :tag
                         :form
                         :next)]
                       (if
                        (clojure.core/map? input__14513_nth_3__)
                        (clojure.core/let
                         [T__15215
                          (.valAt input__14513_nth_3__ :context)]
                         (clojure.core/case
                          T__15215
                          (:string)
                          (clojure.core/let
                           [T__15216
                            (clojure.core/dissoc
                             input__14513_nth_3__
                             :context)]
                           (clojure.core/let
                            [?env input__14513_nth_3__]
                            [{:tag :string-infix,
                              :form ?form,
                              :left ?left,
                              :right ?right}]))
                          (state__16515)))
                        (state__16515)))))
                    (state__16515)))
                  (state__16515)))
                (state__16515)))
              (state__16515)))
            (state__16515
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-join)
                (if
                 (clojure.core/map? input__14513_nth_1__)
                 (clojure.core/let
                  [T__15221
                   (.valAt input__14513_nth_1__ :right)
                   T__15220
                   (.valAt input__14513_nth_1__ :left)
                   T__15219
                   (.valAt input__14513_nth_1__ :tag)]
                  (clojure.core/case
                   T__15219
                   (:string-join)
                   (clojure.core/let
                    [?left T__15220]
                    (clojure.core/let
                     [?right-1 T__15221]
                     (clojure.core/let
                      [T__15222
                       (clojure.core/dissoc
                        input__14513_nth_1__
                        :tag
                        :left
                        :right)]
                      (clojure.core/let
                       [?right-2 input__14513_nth_2__]
                       (if
                        (clojure.core/map? input__14513_nth_3__)
                        (clojure.core/let
                         [T__15223
                          (.valAt input__14513_nth_3__ :context)]
                         (clojure.core/case
                          T__15223
                          (:string)
                          (clojure.core/let
                           [T__15224
                            (clojure.core/dissoc
                             input__14513_nth_3__
                             :context)]
                           (clojure.core/let
                            [?env input__14513_nth_3__]
                            (try
                             [{:tag :string-join,
                               :left ?left,
                               :right
                               (clojure.core/let
                                [R__14641
                                 (C__14591
                                  ['meander.dev.parse.zeta/make-join
                                   ?right-1
                                   ?right-2
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail? R__14641)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth R__14641 0)))}]
                             (catch
                              java.lang.Exception
                              e__10399__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10399__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10399__auto__))))))
                          (state__16516)))
                        (state__16516))))))
                   (state__16516)))
                 (state__16516))
                (state__16516)))
              (state__16516)))
            (state__16516
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-join)
                (clojure.core/let
                 [?left input__14513_nth_1__]
                 (clojure.core/let
                  [?right input__14513_nth_2__]
                  (if
                   (clojure.core/map? input__14513_nth_3__)
                   (clojure.core/let
                    [T__15227 (.valAt input__14513_nth_3__ :context)]
                    (clojure.core/case
                     T__15227
                     (:string)
                     (clojure.core/let
                      [T__15228
                       (clojure.core/dissoc
                        input__14513_nth_3__
                        :context)]
                      [{:tag :string-join,
                        :left ?left,
                        :right ?right}])
                     (state__16517)))
                   (state__16517))))
                (state__16517)))
              (state__16517)))
            (state__16517
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-join)
                (clojure.core/let
                 [?left input__14513_nth_1__]
                 (clojure.core/let
                  [?right input__14513_nth_2__]
                  (clojure.core/let
                   [?env input__14513_nth_3__]
                   [{:tag :join, :left ?left, :right ?right}])))
                (state__16518)))
              (state__16518)))
            (state__16518
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__14513_nth_1__)
                 (clojure.core/let
                  [T__15233
                   (.valAt input__14513_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern T__15233]
                   (clojure.core/let
                    [T__15234
                     (clojure.core/dissoc
                      input__14513_nth_1__
                      :meander.zeta/as)]
                    (clojure.core/let
                     [?rest T__15234]
                     (clojure.core/let
                      [R__16544
                       (if
                        (clojure.core/= ?rest input__14513_nth_1__)
                        true
                        (meander.runtime.zeta/fail))]
                      (if
                       (meander.runtime.zeta/fail? R__16544)
                       (clojure.core/let
                        [?env input__14513_nth_2__]
                        (try
                         [{:tag :as,
                           :pattern
                           (clojure.core/let
                            [R__14642 (C__14591 [?pattern ?env])]
                            (if
                             (meander.runtime.zeta/fail? R__14642)
                             (throw (meander.runtime.zeta/fail))
                             (clojure.core/nth R__14642 0))),
                           :next
                           (clojure.core/let
                            [R__14643 (C__14591 [?rest ?env])]
                            (if
                             (meander.runtime.zeta/fail? R__14643)
                             (throw (meander.runtime.zeta/fail))
                             (clojure.core/nth R__14643 0)))}]
                         (catch
                          java.lang.Exception
                          e__10399__auto__
                          (if
                           (meander.runtime.zeta/fail?
                            e__10399__auto__)
                           (meander.runtime.zeta/fail)
                           (throw e__10399__auto__)))))
                       (state__16401)))))))
                 (state__16401))
                (state__16401)))
              (state__16401)))]
           (state__16497))
          (state__16401)))
        (state__16401
         []
         (clojure.core/letfn
          [(D__15237
            [T__15279 ?ns]
            (clojure.core/letfn
             [(state__16545
               []
               (clojure.core/let
                [T__15280 "meander.zeta"]
                (if
                 (clojure.core/= ?ns T__15280)
                 (clojure.core/let [?env T__15279] [?env ?ns])
                 (state__16546))))
              (state__16546
               []
               (if
                (clojure.core/map? T__15279)
                (clojure.core/let
                 [T__15281 (.valAt T__15279 :aliases)]
                 (if
                  (clojure.core/map? T__15281)
                  (clojure.core/loop
                   [S__16547
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__15281
                     1)]
                   (if
                    (clojure.core/seq S__16547)
                    (clojure.core/let
                     [T__15284
                      (clojure.core/first S__16547)
                      R__16548
                      (clojure.core/let
                       [T__15285
                        (clojure.core/nth T__15284 0)
                        T__15287
                        (clojure.core/nth
                         (clojure.core/nth T__15285 0)
                         0)
                        T__15288
                        (.valAt T__15281 T__15287)
                        T__15286
                        (clojure.core/nth T__15284 1)]
                       (if
                        (clojure.core/symbol? T__15287)
                        (clojure.core/let
                         [X__15290 (clojure.core/name T__15287)]
                         (if
                          (clojure.core/= ?ns X__15290)
                          (if
                           (clojure.core/symbol? T__15288)
                           (clojure.core/let
                            [X__15292 (clojure.core/name T__15288)]
                            (clojure.core/case
                             X__15292
                             ("meander.zeta")
                             (clojure.core/let
                              [T__15282
                               (clojure.core/dissoc T__15279 :aliases)]
                              (clojure.core/let
                               [?env T__15279]
                               [?env ?ns]))
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16548)
                      (recur (clojure.core/next S__16547))
                      R__16548))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16545)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 3)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)
              input__14513_nth_2__
              (clojure.core/nth input__14513 2)]
             (clojure.core/case
              input__14513_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__14513_nth_1__)
               (clojure.core/loop
                [S__16550
                 (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                  input__14513_nth_1__
                  1)]
                (if
                 (clojure.core/seq S__16550)
                 (clojure.core/let
                  [T__15242
                   (clojure.core/first S__16550)
                   R__16551
                   (clojure.core/let
                    [T__15243
                     (clojure.core/nth T__15242 0)
                     T__15245
                     (clojure.core/nth (clojure.core/nth T__15243 0) 0)
                     T__15246
                     (.valAt input__14513_nth_1__ T__15245)
                     T__15244
                     (clojure.core/nth T__15242 1)]
                    (if
                     (clojure.core/symbol? T__15245)
                     (clojure.core/let
                      [X__15248 (clojure.core/namespace T__15245)]
                      (clojure.core/let
                       [?ns X__15248]
                       (clojure.core/let
                        [X__15250 (clojure.core/name T__15245)]
                        (if
                         (clojure.core/string? X__15250)
                         (if
                          (clojure.core/re-matches #"&.*" X__15250)
                          (clojure.core/let
                           [?pattern T__15246]
                           (clojure.core/let
                            [?rest T__15244]
                            (clojure.core/let
                             [R__16553
                              (D__15237 input__14513_nth_2__ ?ns)]
                             (if
                              (meander.runtime.zeta/fail? R__16553)
                              (meander.runtime.zeta/fail)
                              (clojure.core/let
                               [[?env ?ns] R__16553]
                               (try
                                [{:tag :rest-map,
                                  :pattern
                                  (clojure.core/let
                                   [R__14644
                                    (C__14591 [?pattern ?env])]
                                   (if
                                    (meander.runtime.zeta/fail?
                                     R__14644)
                                    (throw (meander.runtime.zeta/fail))
                                    (clojure.core/nth R__14644 0))),
                                  :next
                                  (clojure.core/let
                                   [R__14645
                                    (C__14591
                                     ['meander.dev.parse.zeta/parse-entries
                                      ?rest
                                      ?env])]
                                   (if
                                    (meander.runtime.zeta/fail?
                                     R__14645)
                                    (throw (meander.runtime.zeta/fail))
                                    (clojure.core/nth R__14645 0)))}]
                                (catch
                                 java.lang.Exception
                                 e__10399__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__10399__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__10399__auto__)))))))))
                          (meander.runtime.zeta/fail))
                         (meander.runtime.zeta/fail)))))
                     (meander.runtime.zeta/fail)))]
                  (if
                   (meander.runtime.zeta/fail? R__16551)
                   (recur (clojure.core/next S__16550))
                   R__16551))
                 (state__16402)))
               (state__16402))
              (state__16402)))
            (state__16402))
           (state__16402))))
        (state__16402
         []
         (if
          (clojure.core/vector? input__14513)
          (clojure.core/letfn
           [(state__16554
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__14513_nth_1__)
                 (clojure.core/loop
                  [S__16561
                   (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                    input__14513_nth_1__
                    1)]
                  (if
                   (clojure.core/seq S__16561)
                   (clojure.core/let
                    [T__15310
                     (clojure.core/first S__16561)
                     R__16562
                     (clojure.core/let
                      [T__15311
                       (clojure.core/nth T__15310 0)
                       T__15313
                       (clojure.core/nth
                        (clojure.core/nth T__15311 0)
                        0)
                       T__15314
                       (.valAt input__14513_nth_1__ T__15313)
                       T__15312
                       (clojure.core/nth T__15310 1)]
                      (clojure.core/let
                       [?key-pattern T__15313]
                       (clojure.core/let
                        [?val-pattern T__15314]
                        (clojure.core/let
                         [?rest T__15312]
                         (clojure.core/let
                          [?env input__14513_nth_2__]
                          (try
                           [{:tag :entry,
                             :key-pattern
                             (clojure.core/let
                              [R__14646 (C__14591 [?key-pattern ?env])]
                              (if
                               (meander.runtime.zeta/fail? R__14646)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth R__14646 0))),
                             :val-pattern
                             (clojure.core/let
                              [R__14647 (C__14591 [?val-pattern ?env])]
                              (if
                               (meander.runtime.zeta/fail? R__14647)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth R__14647 0))),
                             :next
                             (clojure.core/let
                              [R__14648
                               (C__14591
                                ['meander.dev.parse.zeta/parse-entries
                                 ?rest
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail? R__14648)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth R__14648 0)))}]
                           (catch
                            java.lang.Exception
                            e__10399__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10399__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10399__auto__)))))))))]
                    (if
                     (meander.runtime.zeta/fail? R__16562)
                     (recur (clojure.core/next S__16561))
                     R__16562))
                   (state__16555)))
                 (state__16555))
                (state__16555)))
              (state__16555)))
            (state__16555
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__14513_nth_1__)
                 (clojure.core/let
                  [?env input__14513_nth_2__]
                  [{:tag :some-map}])
                 (state__16556))
                (state__16556)))
              (state__16556)))
            (state__16556
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/case
                 input__14513_nth_1__
                 ([])
                 (clojure.core/let [?env input__14513_nth_2__] [[]])
                 (state__16557))
                (state__16557)))
              (state__16557)))
            (state__16557
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/parse-with-bindings)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (if
                  (clojure.core/=
                   (clojure.core/count input__14513_nth_1__)
                   1)
                  (clojure.core/let
                   [?env input__14513_nth_2__]
                   [[{:tag :error,
                      :message
                      "meander.zeta/with expects an even number of bindings"}]])
                  (state__16558))
                 (state__16558))
                (state__16558)))
              (state__16558)))
            (state__16558
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/parse-with-bindings)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/let
                  [input__14513_nth_1___L__
                   (clojure.core/subvec
                    input__14513_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__14513_nth_1__)
                     2))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14513_nth_1___L__)
                    2)
                   (clojure.core/let
                    [input__14513_nth_1___R__
                     (clojure.core/subvec input__14513_nth_1__ 2)]
                    (clojure.core/let
                     [input__14513_nth_1___L___nth_0__
                      (clojure.core/nth input__14513_nth_1___L__ 0)
                      input__14513_nth_1___L___nth_1__
                      (clojure.core/nth input__14513_nth_1___L__ 1)]
                     (if
                      (clojure.core/symbol?
                       input__14513_nth_1___L___nth_0__)
                      (clojure.core/let
                       [X__15328
                        (clojure.core/namespace
                         input__14513_nth_1___L___nth_0__)]
                       (clojure.core/let
                        [X__15330
                         (clojure.core/name
                          input__14513_nth_1___L___nth_0__)]
                        (if
                         (clojure.core/string? X__15330)
                         (if
                          (clojure.core/re-matches #"%.+" X__15330)
                          (clojure.core/let
                           [?symbol input__14513_nth_1___L___nth_0__]
                           (clojure.core/let
                            [?pattern input__14513_nth_1___L___nth_1__]
                            (clojure.core/let
                             [?rest input__14513_nth_1___R__]
                             (clojure.core/let
                              [?env input__14513_nth_2__]
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
                                     [R__14649
                                      (C__14591 [?pattern ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       R__14649)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth R__14649 0)))})
                                  (clojure.core/let
                                   [R__14650
                                    (C__14591
                                     ['meander.dev.parse.zeta/parse-with-bindings
                                      ?rest
                                      ?env])]
                                   (if
                                    (meander.runtime.zeta/fail?
                                     R__14650)
                                    (throw (meander.runtime.zeta/fail))
                                    (clojure.core/nth R__14650 0)))))]
                               (catch
                                java.lang.Exception
                                e__10399__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__10399__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__10399__auto__))))))))
                          (state__16559))
                         (state__16559))))
                      (state__16559))))
                   (state__16559)))
                 (state__16559))
                (state__16559)))
              (state__16559)))
            (state__16559
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/parse-with-bindings)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/let
                  [input__14513_nth_1___L__
                   (clojure.core/subvec
                    input__14513_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__14513_nth_1__)
                     2))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14513_nth_1___L__)
                    2)
                   (clojure.core/let
                    [input__14513_nth_1___R__
                     (clojure.core/subvec input__14513_nth_1__ 2)]
                    (clojure.core/let
                     [input__14513_nth_1___L___nth_0__
                      (clojure.core/nth input__14513_nth_1___L__ 0)
                      input__14513_nth_1___L___nth_1__
                      (clojure.core/nth input__14513_nth_1___L__ 1)]
                     (clojure.core/let
                      [?x input__14513_nth_1___L___nth_0__]
                      (clojure.core/let
                       [?pattern input__14513_nth_1___L___nth_1__]
                       (clojure.core/let
                        [?rest input__14513_nth_1___R__]
                        (clojure.core/let
                         [?env input__14513_nth_2__]
                         [[{:tag :error,
                            :message
                            "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]))))))
                   (state__16560)))
                 (state__16560))
                (state__16560)))
              (state__16560)))
            (state__16560
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 2)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)]
               (if
                (clojure.core/vector? input__14513_nth_0__)
                (clojure.core/let
                 [?sequence input__14513_nth_0__]
                 (clojure.core/let
                  [?form input__14513_nth_0__]
                  (clojure.core/let
                   [?env input__14513_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [R__14651
                        (C__14591
                         ['meander.dev.parse.zeta/parse-sequential
                          ?sequence
                          (clojure.core/let
                           [form__9462__auto__ {:context :vector}]
                           (clojure.core/merge
                            (clojure.core/into {} ?env)
                            form__9462__auto__))])]
                       (if
                        (meander.runtime.zeta/fail? R__14651)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth R__14651 0))),
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__10399__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10399__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10399__auto__)))))))
                (state__16403)))
              (state__16403)))]
           (state__16554))
          (state__16403)))
        (state__16403
         []
         (clojure.core/letfn
          [(D__15340
            [T__15367 ?__14515]
            (clojure.core/letfn
             [(state__16564
               []
               (clojure.core/let
                [T__15368 "clojure.core"]
                (if
                 (clojure.core/= ?__14515 T__15368)
                 [?__14515]
                 (state__16565))))
              (state__16565
               []
               (if
                (clojure.core/map? T__15367)
                (clojure.core/let
                 [T__15369 (.valAt T__15367 :aliases)]
                 (if
                  (clojure.core/map? T__15369)
                  (clojure.core/loop
                   [S__16566
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__15369
                     1)]
                   (if
                    (clojure.core/seq S__16566)
                    (clojure.core/let
                     [T__15372
                      (clojure.core/first S__16566)
                      R__16567
                      (clojure.core/let
                       [T__15373
                        (clojure.core/nth T__15372 0)
                        T__15375
                        (clojure.core/nth
                         (clojure.core/nth T__15373 0)
                         0)
                        T__15376
                        (.valAt T__15369 T__15375)
                        T__15374
                        (clojure.core/nth T__15372 1)]
                       (if
                        (clojure.core/symbol? T__15375)
                        (clojure.core/let
                         [X__15378 (clojure.core/name T__15375)]
                         (if
                          (clojure.core/= ?__14515 X__15378)
                          (if
                           (clojure.core/symbol? T__15376)
                           (clojure.core/let
                            [X__15380 (clojure.core/name T__15376)]
                            (clojure.core/case
                             X__15380
                             ("clojure.core")
                             (clojure.core/let
                              [T__15370
                               (clojure.core/dissoc T__15367 :aliases)]
                              [?__14515])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16567)
                      (recur (clojure.core/next S__16566))
                      R__16567))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16564)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__15350
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14515 X__15350]
                     (clojure.core/let
                      [X__15352
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__15352
                       ("unquote")
                       (clojure.core/let
                        [R__16569
                         (D__15340 input__14513_nth_1__ ?__14515)]
                        (if
                         (meander.runtime.zeta/fail? R__16569)
                         (state__16404)
                         (clojure.core/let
                          [[?__14515] R__16569]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 3
                                 input__14513_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14513_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?x input__14513_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14513_nth_0__]
                                  (clojure.core/let
                                   [?env input__14513_nth_1__]
                                   [{:tag :host-expression,
                                     :expression ?x,
                                     :form ?form}]))))
                               (state__16404))
                              (state__16404)))
                            (state__16404))
                           (state__16404)))))
                       (state__16404)))))
                   (state__16404))))
                (state__16404)))
              (state__16404)))
            (state__16404))
           (state__16404))))
        (state__16404
         []
         (clojure.core/letfn
          [(D__15382
            [T__15409 ?__14516]
            (clojure.core/letfn
             [(state__16570
               []
               (clojure.core/let
                [T__15410 "meander.zeta"]
                (if
                 (clojure.core/= ?__14516 T__15410)
                 [?__14516]
                 (state__16571))))
              (state__16571
               []
               (if
                (clojure.core/map? T__15409)
                (clojure.core/let
                 [T__15411 (.valAt T__15409 :aliases)]
                 (if
                  (clojure.core/map? T__15411)
                  (clojure.core/loop
                   [S__16572
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__15411
                     1)]
                   (if
                    (clojure.core/seq S__16572)
                    (clojure.core/let
                     [T__15414
                      (clojure.core/first S__16572)
                      R__16573
                      (clojure.core/let
                       [T__15415
                        (clojure.core/nth T__15414 0)
                        T__15417
                        (clojure.core/nth
                         (clojure.core/nth T__15415 0)
                         0)
                        T__15418
                        (.valAt T__15411 T__15417)
                        T__15416
                        (clojure.core/nth T__15414 1)]
                       (if
                        (clojure.core/symbol? T__15417)
                        (clojure.core/let
                         [X__15420 (clojure.core/name T__15417)]
                         (if
                          (clojure.core/= ?__14516 X__15420)
                          (if
                           (clojure.core/symbol? T__15418)
                           (clojure.core/let
                            [X__15422 (clojure.core/name T__15418)]
                            (clojure.core/case
                             X__15422
                             ("meander.zeta")
                             (clojure.core/let
                              [T__15412
                               (clojure.core/dissoc T__15409 :aliases)]
                              [?__14516])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16573)
                      (recur (clojure.core/next S__16572))
                      R__16573))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16570)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__15392
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14516 X__15392]
                     (clojure.core/let
                      [X__15394
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__15394
                       ("*")
                       (clojure.core/let
                        [R__16575
                         (D__15382 input__14513_nth_1__ ?__14516)]
                        (if
                         (meander.runtime.zeta/fail? R__16575)
                         (state__16405)
                         (clojure.core/let
                          [[?__14516] R__16575]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (clojure.core/let
                               [input__14513_nth_0___L__
                                (clojure.core/take
                                 1
                                 input__14513_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  2
                                  input__14513_nth_0___L__)
                                 1)
                                (clojure.core/let
                                 [input__14513_nth_0___R__
                                  (clojure.core/drop
                                   1
                                   input__14513_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__14513_nth_0___R__]
                                  (clojure.core/let
                                   [?form input__14513_nth_0__]
                                   (clojure.core/let
                                    [?env input__14513_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [R__14652
                                         (C__14591
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14652)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14652
                                          0))),
                                       :next {:tag :empty}}]
                                     (catch
                                      java.lang.Exception
                                      e__10399__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10399__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10399__auto__))))))))
                                (state__16405)))
                              (state__16405)))
                            (state__16405))
                           (state__16405)))))
                       (state__16405)))))
                   (state__16405))))
                (state__16405)))
              (state__16405)))
            (state__16405))
           (state__16405))))
        (state__16405
         []
         (clojure.core/letfn
          [(D__15424
            [T__15451 ?__14517]
            (clojure.core/letfn
             [(state__16576
               []
               (clojure.core/let
                [T__15452 "meander.zeta"]
                (if
                 (clojure.core/= ?__14517 T__15452)
                 [?__14517]
                 (state__16577))))
              (state__16577
               []
               (if
                (clojure.core/map? T__15451)
                (clojure.core/let
                 [T__15453 (.valAt T__15451 :aliases)]
                 (if
                  (clojure.core/map? T__15453)
                  (clojure.core/loop
                   [S__16578
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__15453
                     1)]
                   (if
                    (clojure.core/seq S__16578)
                    (clojure.core/let
                     [T__15456
                      (clojure.core/first S__16578)
                      R__16579
                      (clojure.core/let
                       [T__15457
                        (clojure.core/nth T__15456 0)
                        T__15459
                        (clojure.core/nth
                         (clojure.core/nth T__15457 0)
                         0)
                        T__15460
                        (.valAt T__15453 T__15459)
                        T__15458
                        (clojure.core/nth T__15456 1)]
                       (if
                        (clojure.core/symbol? T__15459)
                        (clojure.core/let
                         [X__15462 (clojure.core/name T__15459)]
                         (if
                          (clojure.core/= ?__14517 X__15462)
                          (if
                           (clojure.core/symbol? T__15460)
                           (clojure.core/let
                            [X__15464 (clojure.core/name T__15460)]
                            (clojure.core/case
                             X__15464
                             ("meander.zeta")
                             (clojure.core/let
                              [T__15454
                               (clojure.core/dissoc T__15451 :aliases)]
                              [?__14517])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16579)
                      (recur (clojure.core/next S__16578))
                      R__16579))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16576)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__15434
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14517 X__15434]
                     (clojure.core/let
                      [X__15436
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__15436
                       ("<>")
                       (clojure.core/let
                        [R__16581
                         (D__15424 input__14513_nth_1__ ?__14517)]
                        (if
                         (meander.runtime.zeta/fail? R__16581)
                         (state__16406)
                         (clojure.core/let
                          [[?__14517] R__16581]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (clojure.core/let
                               [input__14513_nth_0___L__
                                (clojure.core/take
                                 1
                                 input__14513_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  2
                                  input__14513_nth_0___L__)
                                 1)
                                (clojure.core/let
                                 [input__14513_nth_0___R__
                                  (clojure.core/drop
                                   1
                                   input__14513_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__14513_nth_0___R__]
                                  (clojure.core/let
                                   [?form input__14513_nth_0__]
                                   (clojure.core/let
                                    [?env input__14513_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [R__14653
                                         (C__14591
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14653)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14653
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__10399__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10399__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10399__auto__))))))))
                                (state__16406)))
                              (state__16406)))
                            (state__16406))
                           (state__16406)))))
                       (state__16406)))))
                   (state__16406))))
                (state__16406)))
              (state__16406)))
            (state__16406))
           (state__16406))))
        (state__16406
         []
         (clojure.core/letfn
          [(D__15466
            [T__15493 ?__14518]
            (clojure.core/letfn
             [(state__16582
               []
               (clojure.core/let
                [T__15494 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__14518 T__15494)
                 [?__14518]
                 (state__16583))))
              (state__16583
               []
               (if
                (clojure.core/map? T__15493)
                (clojure.core/let
                 [T__15495 (.valAt T__15493 :aliases)]
                 (if
                  (clojure.core/map? T__15495)
                  (clojure.core/loop
                   [S__16584
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__15495
                     1)]
                   (if
                    (clojure.core/seq S__16584)
                    (clojure.core/let
                     [T__15498
                      (clojure.core/first S__16584)
                      R__16585
                      (clojure.core/let
                       [T__15499
                        (clojure.core/nth T__15498 0)
                        T__15501
                        (clojure.core/nth
                         (clojure.core/nth T__15499 0)
                         0)
                        T__15502
                        (.valAt T__15495 T__15501)
                        T__15500
                        (clojure.core/nth T__15498 1)]
                       (if
                        (clojure.core/symbol? T__15501)
                        (clojure.core/let
                         [X__15504 (clojure.core/name T__15501)]
                         (if
                          (clojure.core/= ?__14518 X__15504)
                          (if
                           (clojure.core/symbol? T__15502)
                           (clojure.core/let
                            [X__15506 (clojure.core/name T__15502)]
                            (clojure.core/case
                             X__15506
                             ("meander.math.zeta")
                             (clojure.core/let
                              [T__15496
                               (clojure.core/dissoc T__15493 :aliases)]
                              [?__14518])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16585)
                      (recur (clojure.core/next S__16584))
                      R__16585))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16582)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__15476
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14518 X__15476]
                     (clojure.core/let
                      [X__15478
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__15478
                       ("+")
                       (clojure.core/let
                        [R__16587
                         (D__15466 input__14513_nth_1__ ?__14518)]
                        (if
                         (meander.runtime.zeta/fail? R__16587)
                         (state__16407)
                         (clojure.core/let
                          [[?__14518] R__16587]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 4
                                 input__14513_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14513_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  1)
                                 input__14513_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__14513_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__14513_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14513_nth_0__]
                                   (clojure.core/let
                                    [?env input__14513_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/+,
                                       :left
                                       (clojure.core/let
                                        [R__14654 (C__14591 [?a ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14654)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14654
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [R__14655 (C__14591 [?b ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14655)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14655
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__10399__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10399__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10399__auto__)))))))))
                               (state__16407))
                              (state__16407)))
                            (state__16407))
                           (state__16407)))))
                       (state__16407)))))
                   (state__16407))))
                (state__16407)))
              (state__16407)))
            (state__16407))
           (state__16407))))
        (state__16407
         []
         (clojure.core/letfn
          [(D__15508
            [T__15535 ?__14519]
            (clojure.core/letfn
             [(state__16588
               []
               (clojure.core/let
                [T__15536 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__14519 T__15536)
                 [?__14519]
                 (state__16589))))
              (state__16589
               []
               (if
                (clojure.core/map? T__15535)
                (clojure.core/let
                 [T__15537 (.valAt T__15535 :aliases)]
                 (if
                  (clojure.core/map? T__15537)
                  (clojure.core/loop
                   [S__16590
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__15537
                     1)]
                   (if
                    (clojure.core/seq S__16590)
                    (clojure.core/let
                     [T__15540
                      (clojure.core/first S__16590)
                      R__16591
                      (clojure.core/let
                       [T__15541
                        (clojure.core/nth T__15540 0)
                        T__15543
                        (clojure.core/nth
                         (clojure.core/nth T__15541 0)
                         0)
                        T__15544
                        (.valAt T__15537 T__15543)
                        T__15542
                        (clojure.core/nth T__15540 1)]
                       (if
                        (clojure.core/symbol? T__15543)
                        (clojure.core/let
                         [X__15546 (clojure.core/name T__15543)]
                         (if
                          (clojure.core/= ?__14519 X__15546)
                          (if
                           (clojure.core/symbol? T__15544)
                           (clojure.core/let
                            [X__15548 (clojure.core/name T__15544)]
                            (clojure.core/case
                             X__15548
                             ("meander.math.zeta")
                             (clojure.core/let
                              [T__15538
                               (clojure.core/dissoc T__15535 :aliases)]
                              [?__14519])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16591)
                      (recur (clojure.core/next S__16590))
                      R__16591))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16588)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__15518
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14519 X__15518]
                     (clojure.core/let
                      [X__15520
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__15520
                       ("-")
                       (clojure.core/let
                        [R__16593
                         (D__15508 input__14513_nth_1__ ?__14519)]
                        (if
                         (meander.runtime.zeta/fail? R__16593)
                         (state__16408)
                         (clojure.core/let
                          [[?__14519] R__16593]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 4
                                 input__14513_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14513_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  1)
                                 input__14513_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__14513_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__14513_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14513_nth_0__]
                                   (clojure.core/let
                                    [?env input__14513_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/-,
                                       :left
                                       (clojure.core/let
                                        [R__14656 (C__14591 [?a ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14656)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14656
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [R__14657 (C__14591 [?b ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14657)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14657
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__10399__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10399__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10399__auto__)))))))))
                               (state__16408))
                              (state__16408)))
                            (state__16408))
                           (state__16408)))))
                       (state__16408)))))
                   (state__16408))))
                (state__16408)))
              (state__16408)))
            (state__16408))
           (state__16408))))
        (state__16408
         []
         (clojure.core/letfn
          [(D__15550
            [T__15577 ?__14520]
            (clojure.core/letfn
             [(state__16594
               []
               (clojure.core/let
                [T__15578 "meander.zeta"]
                (if
                 (clojure.core/= ?__14520 T__15578)
                 [?__14520]
                 (state__16595))))
              (state__16595
               []
               (if
                (clojure.core/map? T__15577)
                (clojure.core/let
                 [T__15579 (.valAt T__15577 :aliases)]
                 (if
                  (clojure.core/map? T__15579)
                  (clojure.core/loop
                   [S__16596
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__15579
                     1)]
                   (if
                    (clojure.core/seq S__16596)
                    (clojure.core/let
                     [T__15582
                      (clojure.core/first S__16596)
                      R__16597
                      (clojure.core/let
                       [T__15583
                        (clojure.core/nth T__15582 0)
                        T__15585
                        (clojure.core/nth
                         (clojure.core/nth T__15583 0)
                         0)
                        T__15586
                        (.valAt T__15579 T__15585)
                        T__15584
                        (clojure.core/nth T__15582 1)]
                       (if
                        (clojure.core/symbol? T__15585)
                        (clojure.core/let
                         [X__15588 (clojure.core/name T__15585)]
                         (if
                          (clojure.core/= ?__14520 X__15588)
                          (if
                           (clojure.core/symbol? T__15586)
                           (clojure.core/let
                            [X__15590 (clojure.core/name T__15586)]
                            (clojure.core/case
                             X__15590
                             ("meander.zeta")
                             (clojure.core/let
                              [T__15580
                               (clojure.core/dissoc T__15577 :aliases)]
                              [?__14520])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16597)
                      (recur (clojure.core/next S__16596))
                      R__16597))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16594)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__15560
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14520 X__15560]
                     (clojure.core/let
                      [X__15562
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__15562
                       ("with")
                       (clojure.core/let
                        [R__16599
                         (D__15550 input__14513_nth_1__ ?__14520)]
                        (if
                         (meander.runtime.zeta/fail? R__16599)
                         (state__16409)
                         (clojure.core/let
                          [[?__14520] R__16599]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 4
                                 input__14513_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14513_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  1)
                                 input__14513_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__14513_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__14513_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14513_nth_0__]
                                   (clojure.core/let
                                    [?env input__14513_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [R__14658
                                          (C__14591
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?bindings
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           R__14658)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           R__14658
                                           0)))},
                                       :body
                                       (clojure.core/let
                                        [R__14659
                                         (C__14591 [?body ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14659)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14659
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10399__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10399__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10399__auto__)))))))))
                               (state__16409))
                              (state__16409)))
                            (state__16409))
                           (state__16409)))))
                       (state__16409)))))
                   (state__16409))))
                (state__16409)))
              (state__16409)))
            (state__16409))
           (state__16409))))
        (state__16409
         []
         (clojure.core/letfn
          [(D__15592
            [T__15619 ?__14521]
            (clojure.core/letfn
             [(state__16600
               []
               (clojure.core/let
                [T__15620 "meander.zeta"]
                (if
                 (clojure.core/= ?__14521 T__15620)
                 [?__14521]
                 (state__16601))))
              (state__16601
               []
               (if
                (clojure.core/map? T__15619)
                (clojure.core/let
                 [T__15621 (.valAt T__15619 :aliases)]
                 (if
                  (clojure.core/map? T__15621)
                  (clojure.core/loop
                   [S__16602
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__15621
                     1)]
                   (if
                    (clojure.core/seq S__16602)
                    (clojure.core/let
                     [T__15624
                      (clojure.core/first S__16602)
                      R__16603
                      (clojure.core/let
                       [T__15625
                        (clojure.core/nth T__15624 0)
                        T__15627
                        (clojure.core/nth
                         (clojure.core/nth T__15625 0)
                         0)
                        T__15628
                        (.valAt T__15621 T__15627)
                        T__15626
                        (clojure.core/nth T__15624 1)]
                       (if
                        (clojure.core/symbol? T__15627)
                        (clojure.core/let
                         [X__15630 (clojure.core/name T__15627)]
                         (if
                          (clojure.core/= ?__14521 X__15630)
                          (if
                           (clojure.core/symbol? T__15628)
                           (clojure.core/let
                            [X__15632 (clojure.core/name T__15628)]
                            (clojure.core/case
                             X__15632
                             ("meander.zeta")
                             (clojure.core/let
                              [T__15622
                               (clojure.core/dissoc T__15619 :aliases)]
                              [?__14521])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16603)
                      (recur (clojure.core/next S__16602))
                      R__16603))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16600)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__15602
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14521 X__15602]
                     (clojure.core/let
                      [X__15604
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__15604
                       ("apply")
                       (clojure.core/let
                        [R__16605
                         (D__15592 input__14513_nth_1__ ?__14521)]
                        (if
                         (meander.runtime.zeta/fail? R__16605)
                         (state__16410)
                         (clojure.core/let
                          [[?__14521] R__16605]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 4
                                 input__14513_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14513_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  1)
                                 input__14513_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__14513_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__14513_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14513_nth_0__]
                                   (clojure.core/let
                                    [?env input__14513_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [R__14660
                                         (C__14591 [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14660)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14660
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10399__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10399__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10399__auto__)))))))))
                               (state__16410))
                              (state__16410)))
                            (state__16410))
                           (state__16410)))))
                       (state__16410)))))
                   (state__16410))))
                (state__16410)))
              (state__16410)))
            (state__16410))
           (state__16410))))
        (state__16410
         []
         (clojure.core/letfn
          [(D__15634
            [T__15663 ?__14522]
            (clojure.core/letfn
             [(state__16606
               []
               (clojure.core/let
                [T__15664 "meander.zeta"]
                (if
                 (clojure.core/= ?__14522 T__15664)
                 [?__14522]
                 (state__16607))))
              (state__16607
               []
               (if
                (clojure.core/map? T__15663)
                (clojure.core/let
                 [T__15665 (.valAt T__15663 :aliases)]
                 (if
                  (clojure.core/map? T__15665)
                  (clojure.core/loop
                   [S__16608
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__15665
                     1)]
                   (if
                    (clojure.core/seq S__16608)
                    (clojure.core/let
                     [T__15668
                      (clojure.core/first S__16608)
                      R__16609
                      (clojure.core/let
                       [T__15669
                        (clojure.core/nth T__15668 0)
                        T__15671
                        (clojure.core/nth
                         (clojure.core/nth T__15669 0)
                         0)
                        T__15672
                        (.valAt T__15665 T__15671)
                        T__15670
                        (clojure.core/nth T__15668 1)]
                       (if
                        (clojure.core/symbol? T__15671)
                        (clojure.core/let
                         [X__15674 (clojure.core/name T__15671)]
                         (if
                          (clojure.core/= ?__14522 X__15674)
                          (if
                           (clojure.core/symbol? T__15672)
                           (clojure.core/let
                            [X__15676 (clojure.core/name T__15672)]
                            (clojure.core/case
                             X__15676
                             ("meander.zeta")
                             (clojure.core/let
                              [T__15666
                               (clojure.core/dissoc T__15663 :aliases)]
                              [?__14522])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16609)
                      (recur (clojure.core/next S__16608))
                      R__16609))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16606)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__15646
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14522 X__15646]
                     (clojure.core/let
                      [X__15648
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__15648
                       ("and")
                       (clojure.core/let
                        [R__16611
                         (D__15634 input__14513_nth_1__ ?__14522)]
                        (if
                         (meander.runtime.zeta/fail? R__16611)
                         (state__16411)
                         (clojure.core/let
                          [[?__14522] R__16611]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (clojure.core/let
                               [input__14513_nth_0___L__
                                (clojure.core/take
                                 1
                                 input__14513_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  2
                                  input__14513_nth_0___L__)
                                 1)
                                (clojure.core/let
                                 [input__14513_nth_0___R__
                                  (clojure.core/drop
                                   1
                                   input__14513_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__14513_nth_0___R__)]
                                  (clojure.core/let
                                   [?form input__14513_nth_0__]
                                   (clojure.core/let
                                    [?env input__14513_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [R__14663
                                         (C__14591
                                          ['meander.dev.parse.zeta/make-and
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__14662
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__14662
                                                (clojure.core/let
                                                 [R__14661
                                                  (C__14591
                                                   [(if
                                                     (.hasNext
                                                      !forms__counter)
                                                     (.next
                                                      !forms__counter))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   R__14661)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   R__14661
                                                   0)))))
                                              (clojure.core/persistent!
                                               return__14662))))
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14663)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14663
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10399__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10399__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10399__auto__))))))))
                                (state__16411)))
                              (state__16411)))
                            (state__16411))
                           (state__16411)))))
                       (state__16411)))))
                   (state__16411))))
                (state__16411)))
              (state__16411)))
            (state__16411))
           (state__16411))))
        (state__16411
         []
         (if
          (clojure.core/vector? input__14513)
          (clojure.core/letfn
           [(state__16612
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-and)
                (clojure.core/case
                 input__14513_nth_1__
                 ([])
                 (clojure.core/let
                  [?form input__14513_nth_2__]
                  [{:tag :error,
                    :message
                    "meander.zeta/and requires 1 or more arguments",
                    :form ?form}])
                 (state__16613))
                (state__16613)))
              (state__16613)))
            (state__16613
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-and)
                (clojure.core/case
                 input__14513_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__14513_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14513_nth_1__)
                    1)
                   (clojure.core/let
                    [input__14513_nth_1___nth_0__
                     (clojure.core/nth input__14513_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__14513_nth_1___nth_0__]
                     [?ast-a]))
                   (state__16614))
                  (state__16614))
                 (state__16614))
                (state__16614)))
              (state__16614)))
            (state__16614
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-and)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (if
                  (clojure.core/=
                   (clojure.core/count input__14513_nth_1__)
                   1)
                  (clojure.core/let
                   [input__14513_nth_1___nth_0__
                    (clojure.core/nth input__14513_nth_1__ 0)]
                   (clojure.core/let
                    [?ast-a input__14513_nth_1___nth_0__]
                    (clojure.core/let
                     [?form input__14513_nth_2__]
                     (try
                      [(clojure.core/let
                        [R__14664
                         (C__14591
                          ['meander.dev.parse.zeta/make-and
                           [?ast-a {:tag :pass}]
                           ?form])]
                        (if
                         (meander.runtime.zeta/fail? R__14664)
                         (throw (meander.runtime.zeta/fail))
                         (clojure.core/nth R__14664 0)))]
                      (catch
                       java.lang.Exception
                       e__10399__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10399__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10399__auto__)))))))
                  (state__16615))
                 (state__16615))
                (state__16615)))
              (state__16615)))
            (state__16615
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-and)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (if
                  (clojure.core/=
                   (clojure.core/count input__14513_nth_1__)
                   2)
                  (clojure.core/let
                   [input__14513_nth_1___nth_0__
                    (clojure.core/nth input__14513_nth_1__ 0)
                    input__14513_nth_1___nth_1__
                    (clojure.core/nth input__14513_nth_1__ 1)]
                   (clojure.core/let
                    [?ast-a input__14513_nth_1___nth_0__]
                    (clojure.core/let
                     [?ast-b input__14513_nth_1___nth_1__]
                     (clojure.core/let
                      [?form input__14513_nth_2__]
                      [{:tag :and,
                        :left ?ast-a,
                        :right ?ast-b,
                        :form ?form}]))))
                  (state__16616))
                 (state__16616))
                (state__16616)))
              (state__16616)))
            (state__16616
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-and)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/loop
                  [S__16618
                   (meander.runtime.zeta/epsilon-partitions
                    2
                    input__14513_nth_1__)]
                  (if
                   (clojure.core/seq S__16618)
                   (clojure.core/let
                    [input__14513_nth_1___parts__
                     (clojure.core/first S__16618)
                     R__16619
                     (clojure.core/let
                      [input__14513_nth_1___L__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        0)
                       input__14513_nth_1___R__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!asts-1 []]
                       (clojure.core/let
                        [R__16624
                         (meander.runtime.zeta/epsilon-run-star-1
                          input__14513_nth_1___L__
                          [!asts-1]
                          (clojure.core/fn
                           [[!asts-1] input__15703]
                           (clojure.core/let
                            [input__15703_nth_0__
                             (clojure.core/nth input__15703 0)]
                            (clojure.core/let
                             [!asts-1
                              (clojure.core/conj
                               !asts-1
                               input__15703_nth_0__)]
                             [!asts-1])))
                          (clojure.core/fn
                           [[!asts-1]]
                           (clojure.core/let
                            [X__15696
                             (clojure.core/count
                              input__14513_nth_1___L__)]
                            (clojure.core/let
                             [?n X__15696]
                             (clojure.core/let
                              [X__15700
                               (clojure.core/count
                                input__14513_nth_1___R__)]
                              (if
                               (clojure.core/= ?n X__15700)
                               (clojure.core/let
                                [!asts-2 []]
                                (clojure.core/let
                                 [R__16623
                                  (meander.runtime.zeta/epsilon-run-star-1
                                   input__14513_nth_1___R__
                                   [!asts-2 !asts-1]
                                   (clojure.core/fn
                                    [[!asts-2 !asts-1] input__15701]
                                    (clojure.core/let
                                     [input__15701_nth_0__
                                      (clojure.core/nth
                                       input__15701
                                       0)]
                                     (clojure.core/let
                                      [!asts-2
                                       (clojure.core/conj
                                        !asts-2
                                        input__15701_nth_0__)]
                                      [!asts-2 !asts-1])))
                                   (clojure.core/fn
                                    [[!asts-2 !asts-1]]
                                    (clojure.core/let
                                     [?form input__14513_nth_2__]
                                     (try
                                      [(clojure.core/let
                                        [!asts-1__counter
                                         (meander.runtime.zeta/iterator
                                          !asts-1)
                                         !asts-2__counter
                                         (meander.runtime.zeta/iterator
                                          !asts-2)]
                                        (clojure.core/let
                                         [R__14667
                                          (C__14591
                                           ['meander.dev.parse.zeta/make-and
                                            [(clojure.core/let
                                              [R__14665
                                               (C__14591
                                                ['meander.dev.parse.zeta/make-and
                                                 (clojure.core/into
                                                  []
                                                  (clojure.core/vec
                                                   (clojure.core/iterator-seq
                                                    !asts-1__counter)))
                                                 nil])]
                                              (if
                                               (meander.runtime.zeta/fail?
                                                R__14665)
                                               (throw
                                                (meander.runtime.zeta/fail))
                                               (clojure.core/nth
                                                R__14665
                                                0)))
                                             (clojure.core/let
                                              [R__14666
                                               (C__14591
                                                ['meander.dev.parse.zeta/make-and
                                                 (clojure.core/into
                                                  []
                                                  (clojure.core/vec
                                                   (clojure.core/iterator-seq
                                                    !asts-2__counter)))
                                                 nil])]
                                              (if
                                               (meander.runtime.zeta/fail?
                                                R__14666)
                                               (throw
                                                (meander.runtime.zeta/fail))
                                               (clojure.core/nth
                                                R__14666
                                                0)))]
                                            ?form])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           R__14667)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           R__14667
                                           0))))]
                                      (catch
                                       java.lang.Exception
                                       e__10399__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10399__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw e__10399__auto__)))))))]
                                 (if
                                  (meander.runtime.zeta/fail? R__16623)
                                  (meander.runtime.zeta/fail)
                                  R__16623)))
                               (meander.runtime.zeta/fail)))))))]
                        (if
                         (meander.runtime.zeta/fail? R__16624)
                         (meander.runtime.zeta/fail)
                         R__16624))))]
                    (if
                     (meander.runtime.zeta/fail? R__16619)
                     (recur (clojure.core/next S__16618))
                     R__16619))
                   (state__16617)))
                 (state__16617))
                (state__16617)))
              (state__16617)))
            (state__16617
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-and)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/loop
                  [S__16625
                   (meander.runtime.zeta/epsilon-partitions
                    2
                    input__14513_nth_1__)]
                  (if
                   (clojure.core/seq S__16625)
                   (clojure.core/let
                    [input__14513_nth_1___parts__
                     (clojure.core/first S__16625)
                     R__16626
                     (clojure.core/let
                      [input__14513_nth_1___L__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        0)
                       input__14513_nth_1___R__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!asts-1 []]
                       (clojure.core/let
                        [R__16631
                         (meander.runtime.zeta/epsilon-run-star-1
                          input__14513_nth_1___L__
                          [!asts-1]
                          (clojure.core/fn
                           [[!asts-1] input__15719]
                           (clojure.core/let
                            [input__15719_nth_0__
                             (clojure.core/nth input__15719 0)]
                            (clojure.core/let
                             [!asts-1
                              (clojure.core/conj
                               !asts-1
                               input__15719_nth_0__)]
                             [!asts-1])))
                          (clojure.core/fn
                           [[!asts-1]]
                           (clojure.core/let
                            [X__15710
                             (clojure.core/count
                              input__14513_nth_1___L__)]
                            (clojure.core/let
                             [?n X__15710]
                             (clojure.core/let
                              [input__14513_nth_1___R___L__
                               (clojure.core/subvec
                                input__14513_nth_1___R__
                                0
                                (clojure.core/min
                                 (clojure.core/count
                                  input__14513_nth_1___R__)
                                 1))]
                              (if
                               (clojure.core/=
                                (clojure.core/count
                                 input__14513_nth_1___R___L__)
                                1)
                               (clojure.core/let
                                [input__14513_nth_1___R___R__
                                 (clojure.core/subvec
                                  input__14513_nth_1___R__
                                  1)]
                                (clojure.core/let
                                 [input__14513_nth_1___R___L___nth_0__
                                  (clojure.core/nth
                                   input__14513_nth_1___R___L__
                                   0)]
                                 (clojure.core/let
                                  [?ast
                                   input__14513_nth_1___R___L___nth_0__]
                                  (clojure.core/let
                                   [X__15716
                                    (clojure.core/count
                                     input__14513_nth_1___R___R__)]
                                   (if
                                    (clojure.core/= ?n X__15716)
                                    (clojure.core/let
                                     [!asts-2 []]
                                     (clojure.core/let
                                      [R__16630
                                       (meander.runtime.zeta/epsilon-run-star-1
                                        input__14513_nth_1___R___R__
                                        [!asts-2 !asts-1]
                                        (clojure.core/fn
                                         [[!asts-2 !asts-1]
                                          input__15717]
                                         (clojure.core/let
                                          [input__15717_nth_0__
                                           (clojure.core/nth
                                            input__15717
                                            0)]
                                          (clojure.core/let
                                           [!asts-2
                                            (clojure.core/conj
                                             !asts-2
                                             input__15717_nth_0__)]
                                           [!asts-2 !asts-1])))
                                        (clojure.core/fn
                                         [[!asts-2 !asts-1]]
                                         (clojure.core/let
                                          [?form input__14513_nth_2__]
                                          (try
                                           [(clojure.core/let
                                             [!asts-1__counter
                                              (meander.runtime.zeta/iterator
                                               !asts-1)
                                              !asts-2__counter
                                              (meander.runtime.zeta/iterator
                                               !asts-2)]
                                             (clojure.core/let
                                              [R__14670
                                               (C__14591
                                                ['meander.dev.parse.zeta/make-and
                                                 [(clojure.core/let
                                                   [R__14668
                                                    (C__14591
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
                                                     R__14668)
                                                    (throw
                                                     (meander.runtime.zeta/fail))
                                                    (clojure.core/nth
                                                     R__14668
                                                     0)))
                                                  (clojure.core/let
                                                   [R__14669
                                                    (C__14591
                                                     ['meander.dev.parse.zeta/make-and
                                                      (clojure.core/into
                                                       []
                                                       (clojure.core/vec
                                                        (clojure.core/iterator-seq
                                                         !asts-2__counter)))
                                                      nil])]
                                                   (if
                                                    (meander.runtime.zeta/fail?
                                                     R__14669)
                                                    (throw
                                                     (meander.runtime.zeta/fail))
                                                    (clojure.core/nth
                                                     R__14669
                                                     0)))]
                                                 ?form])]
                                              (if
                                               (meander.runtime.zeta/fail?
                                                R__14670)
                                               (throw
                                                (meander.runtime.zeta/fail))
                                               (clojure.core/nth
                                                R__14670
                                                0))))]
                                           (catch
                                            java.lang.Exception
                                            e__10399__auto__
                                            (if
                                             (meander.runtime.zeta/fail?
                                              e__10399__auto__)
                                             (meander.runtime.zeta/fail)
                                             (throw
                                              e__10399__auto__)))))))]
                                      (if
                                       (meander.runtime.zeta/fail?
                                        R__16630)
                                       (meander.runtime.zeta/fail)
                                       R__16630)))
                                    (meander.runtime.zeta/fail))))))
                               (meander.runtime.zeta/fail)))))))]
                        (if
                         (meander.runtime.zeta/fail? R__16631)
                         (meander.runtime.zeta/fail)
                         R__16631))))]
                    (if
                     (meander.runtime.zeta/fail? R__16626)
                     (recur (clojure.core/next S__16625))
                     R__16626))
                   (state__16412)))
                 (state__16412))
                (state__16412)))
              (state__16412)))]
           (state__16612))
          (state__16412)))
        (state__16412
         []
         (clojure.core/letfn
          [(D__15722
            [T__15749 ?__14523]
            (clojure.core/letfn
             [(state__16632
               []
               (clojure.core/let
                [T__15750 "meander.zeta"]
                (if
                 (clojure.core/= ?__14523 T__15750)
                 [?__14523]
                 (state__16633))))
              (state__16633
               []
               (if
                (clojure.core/map? T__15749)
                (clojure.core/let
                 [T__15751 (.valAt T__15749 :aliases)]
                 (if
                  (clojure.core/map? T__15751)
                  (clojure.core/loop
                   [S__16634
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__15751
                     1)]
                   (if
                    (clojure.core/seq S__16634)
                    (clojure.core/let
                     [T__15754
                      (clojure.core/first S__16634)
                      R__16635
                      (clojure.core/let
                       [T__15755
                        (clojure.core/nth T__15754 0)
                        T__15757
                        (clojure.core/nth
                         (clojure.core/nth T__15755 0)
                         0)
                        T__15758
                        (.valAt T__15751 T__15757)
                        T__15756
                        (clojure.core/nth T__15754 1)]
                       (if
                        (clojure.core/symbol? T__15757)
                        (clojure.core/let
                         [X__15760 (clojure.core/name T__15757)]
                         (if
                          (clojure.core/= ?__14523 X__15760)
                          (if
                           (clojure.core/symbol? T__15758)
                           (clojure.core/let
                            [X__15762 (clojure.core/name T__15758)]
                            (clojure.core/case
                             X__15762
                             ("meander.zeta")
                             (clojure.core/let
                              [T__15752
                               (clojure.core/dissoc T__15749 :aliases)]
                              [?__14523])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16635)
                      (recur (clojure.core/next S__16634))
                      R__16635))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16632)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__15732
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14523 X__15732]
                     (clojure.core/let
                      [X__15734
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__15734
                       ("cata")
                       (clojure.core/let
                        [R__16637
                         (D__15722 input__14513_nth_1__ ?__14523)]
                        (if
                         (meander.runtime.zeta/fail? R__16637)
                         (state__16413)
                         (clojure.core/let
                          [[?__14523] R__16637]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 3
                                 input__14513_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14513_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__14513_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14513_nth_0__]
                                  (clojure.core/let
                                   [?env input__14513_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [R__14671
                                        (C__14591 [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         R__14671)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         R__14671
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10399__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10399__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10399__auto__))))))))
                               (state__16413))
                              (state__16413)))
                            (state__16413))
                           (state__16413)))))
                       (state__16413)))))
                   (state__16413))))
                (state__16413)))
              (state__16413)))
            (state__16413))
           (state__16413))))
        (state__16413
         []
         (clojure.core/letfn
          [(D__15764
            [T__15791 ?__14524]
            (clojure.core/letfn
             [(state__16638
               []
               (clojure.core/let
                [T__15792 "meander.zeta"]
                (if
                 (clojure.core/= ?__14524 T__15792)
                 [?__14524]
                 (state__16639))))
              (state__16639
               []
               (if
                (clojure.core/map? T__15791)
                (clojure.core/let
                 [T__15793 (.valAt T__15791 :aliases)]
                 (if
                  (clojure.core/map? T__15793)
                  (clojure.core/loop
                   [S__16640
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__15793
                     1)]
                   (if
                    (clojure.core/seq S__16640)
                    (clojure.core/let
                     [T__15796
                      (clojure.core/first S__16640)
                      R__16641
                      (clojure.core/let
                       [T__15797
                        (clojure.core/nth T__15796 0)
                        T__15799
                        (clojure.core/nth
                         (clojure.core/nth T__15797 0)
                         0)
                        T__15800
                        (.valAt T__15793 T__15799)
                        T__15798
                        (clojure.core/nth T__15796 1)]
                       (if
                        (clojure.core/symbol? T__15799)
                        (clojure.core/let
                         [X__15802 (clojure.core/name T__15799)]
                         (if
                          (clojure.core/= ?__14524 X__15802)
                          (if
                           (clojure.core/symbol? T__15800)
                           (clojure.core/let
                            [X__15804 (clojure.core/name T__15800)]
                            (clojure.core/case
                             X__15804
                             ("meander.zeta")
                             (clojure.core/let
                              [T__15794
                               (clojure.core/dissoc T__15791 :aliases)]
                              [?__14524])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16641)
                      (recur (clojure.core/next S__16640))
                      R__16641))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16638)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__15774
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14524 X__15774]
                     (clojure.core/let
                      [X__15776
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__15776
                       ("fold")
                       (clojure.core/let
                        [R__16643
                         (D__15764 input__14513_nth_1__ ?__14524)]
                        (if
                         (meander.runtime.zeta/fail? R__16643)
                         (state__16414)
                         (clojure.core/let
                          [[?__14524] R__16643]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 5
                                 input__14513_nth_0__)
                                4)
                               (clojure.core/let
                                [input__14513_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  1)
                                 input__14513_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  2)
                                 input__14513_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__14513_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__14513_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__14513_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__14513_nth_0__]
                                    (clojure.core/let
                                     [?env input__14513_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [R__14674
                                         (C__14591
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [R__14672
                                             (C__14591
                                              [?mutable-variable
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              R__14672)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              R__14672
                                              0)))
                                           (clojure.core/let
                                            [R__14673
                                             (C__14591
                                              [?initial-value ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              R__14673)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              R__14673
                                              0)))
                                           ?fold-function
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14674)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14674
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__10399__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10399__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__10399__auto__))))))))))
                               (state__16414))
                              (state__16414)))
                            (state__16414))
                           (state__16414)))))
                       (state__16414)))))
                   (state__16414))))
                (state__16414)))
              (state__16414)))
            (state__16414))
           (state__16414))))
        (state__16414
         []
         (if
          (clojure.core/vector? input__14513)
          (if
           (clojure.core/= (clojure.core/count input__14513) 5)
           (clojure.core/let
            [input__14513_nth_0__
             (clojure.core/nth input__14513 0)
             input__14513_nth_1__
             (clojure.core/nth input__14513 1)
             input__14513_nth_2__
             (clojure.core/nth input__14513 2)
             input__14513_nth_3__
             (clojure.core/nth input__14513 3)
             input__14513_nth_4__
             (clojure.core/nth input__14513 4)]
            (clojure.core/case
             input__14513_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__14513_nth_1__)
              (clojure.core/let
               [T__15807 (.valAt input__14513_nth_1__ :tag)]
               (clojure.core/case
                T__15807
                (:mutable-variable)
                (clojure.core/let
                 [T__15808
                  (clojure.core/dissoc input__14513_nth_1__ :tag)]
                 (clojure.core/let
                  [?variable-ast input__14513_nth_1__]
                  (clojure.core/let
                   [?initial-value-ast input__14513_nth_2__]
                   (clojure.core/let
                    [?fold-function input__14513_nth_3__]
                    (clojure.core/let
                     [?form input__14513_nth_4__]
                     [{:tag :fold,
                       :variable ?variable-ast,
                       :initial-value ?initial-value-ast,
                       :fold-function
                       {:tag :host-expression, :form ?fold-function},
                       :form ?form}])))))
                (state__16415)))
              (state__16415))
             (state__16415)))
           (state__16415))
          (state__16415)))
        (state__16415
         []
         (clojure.core/letfn
          [(D__15810
            [T__15837 ?__14525]
            (clojure.core/letfn
             [(state__16644
               []
               (clojure.core/let
                [T__15838 "meander.zeta"]
                (if
                 (clojure.core/= ?__14525 T__15838)
                 [?__14525]
                 (state__16645))))
              (state__16645
               []
               (if
                (clojure.core/map? T__15837)
                (clojure.core/let
                 [T__15839 (.valAt T__15837 :aliases)]
                 (if
                  (clojure.core/map? T__15839)
                  (clojure.core/loop
                   [S__16646
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__15839
                     1)]
                   (if
                    (clojure.core/seq S__16646)
                    (clojure.core/let
                     [T__15842
                      (clojure.core/first S__16646)
                      R__16647
                      (clojure.core/let
                       [T__15843
                        (clojure.core/nth T__15842 0)
                        T__15845
                        (clojure.core/nth
                         (clojure.core/nth T__15843 0)
                         0)
                        T__15846
                        (.valAt T__15839 T__15845)
                        T__15844
                        (clojure.core/nth T__15842 1)]
                       (if
                        (clojure.core/symbol? T__15845)
                        (clojure.core/let
                         [X__15848 (clojure.core/name T__15845)]
                         (if
                          (clojure.core/= ?__14525 X__15848)
                          (if
                           (clojure.core/symbol? T__15846)
                           (clojure.core/let
                            [X__15850 (clojure.core/name T__15846)]
                            (clojure.core/case
                             X__15850
                             ("meander.zeta")
                             (clojure.core/let
                              [T__15840
                               (clojure.core/dissoc T__15837 :aliases)]
                              [?__14525])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16647)
                      (recur (clojure.core/next S__16646))
                      R__16647))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16644)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__15820
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14525 X__15820]
                     (clojure.core/let
                      [X__15822
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__15822
                       ("keyword")
                       (clojure.core/let
                        [R__16649
                         (D__15810 input__14513_nth_1__ ?__14525)]
                        (if
                         (meander.runtime.zeta/fail? R__16649)
                         (state__16416)
                         (clojure.core/let
                          [[?__14525] R__16649]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (clojure.core/let
                               [input__14513_nth_0___L__
                                (clojure.core/take
                                 1
                                 input__14513_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  2
                                  input__14513_nth_0___L__)
                                 1)
                                (clojure.core/let
                                 [input__14513_nth_0___R__
                                  (clojure.core/drop
                                   1
                                   input__14513_nth_0__)]
                                 (clojure.core/let
                                  [?keyword-args
                                   input__14513_nth_0___R__]
                                  (clojure.core/let
                                   [?form input__14513_nth_0__]
                                   (clojure.core/let
                                    [?env input__14513_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [R__14675
                                        (C__14591
                                         ['meander.dev.parse.zeta/make-keyword
                                          (clojure.core/into
                                           []
                                           ?keyword-args)
                                          ?form
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         R__14675)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         R__14675
                                         0)))]
                                     (catch
                                      java.lang.Exception
                                      e__10399__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10399__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10399__auto__))))))))
                                (state__16416)))
                              (state__16416)))
                            (state__16416))
                           (state__16416)))))
                       (state__16416)))))
                   (state__16416))))
                (state__16416)))
              (state__16416)))
            (state__16416))
           (state__16416))))
        (state__16416
         []
         (if
          (clojure.core/vector? input__14513)
          (clojure.core/letfn
           [(state__16650
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-keyword)
                (clojure.core/case
                 input__14513_nth_1__
                 ([])
                 (clojure.core/let
                  [?form input__14513_nth_2__]
                  [{:tag :keyword,
                    :namespace {:tag :wildcard},
                    :name {:tag :wildcard},
                    :form ?form}])
                 (state__16651))
                (state__16651)))
              (state__16651)))
            (state__16651
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-keyword)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (if
                  (clojure.core/=
                   (clojure.core/count input__14513_nth_1__)
                   1)
                  (clojure.core/let
                   [input__14513_nth_1___nth_0__
                    (clojure.core/nth input__14513_nth_1__ 0)]
                   (clojure.core/let
                    [?name input__14513_nth_1___nth_0__]
                    (clojure.core/let
                     [?form input__14513_nth_2__]
                     (clojure.core/let
                      [?env input__14513_nth_3__]
                      (try
                       [{:tag :keyword,
                         :namespace {:tag :wildcard},
                         :name
                         (clojure.core/let
                          [R__14676 (C__14591 [?name ?env])]
                          (if
                           (meander.runtime.zeta/fail? R__14676)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth R__14676 0))),
                         :form ?form}]
                       (catch
                        java.lang.Exception
                        e__10399__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10399__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10399__auto__))))))))
                  (state__16652))
                 (state__16652))
                (state__16652)))
              (state__16652)))
            (state__16652
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-keyword)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (if
                  (clojure.core/=
                   (clojure.core/count input__14513_nth_1__)
                   2)
                  (clojure.core/let
                   [input__14513_nth_1___nth_0__
                    (clojure.core/nth input__14513_nth_1__ 0)
                    input__14513_nth_1___nth_1__
                    (clojure.core/nth input__14513_nth_1__ 1)]
                   (clojure.core/let
                    [?namespace input__14513_nth_1___nth_0__]
                    (clojure.core/let
                     [?name input__14513_nth_1___nth_1__]
                     (clojure.core/let
                      [?form input__14513_nth_2__]
                      (clojure.core/let
                       [?env input__14513_nth_3__]
                       (try
                        [{:tag :keyword,
                          :namespace
                          (clojure.core/let
                           [R__14677 (C__14591 [?namespace ?env])]
                           (if
                            (meander.runtime.zeta/fail? R__14677)
                            (throw (meander.runtime.zeta/fail))
                            (clojure.core/nth R__14677 0))),
                          :name
                          (clojure.core/let
                           [R__14678 (C__14591 [?name ?env])]
                           (if
                            (meander.runtime.zeta/fail? R__14678)
                            (throw (meander.runtime.zeta/fail))
                            (clojure.core/nth R__14678 0))),
                          :form ?form}]
                        (catch
                         java.lang.Exception
                         e__10399__auto__
                         (if
                          (meander.runtime.zeta/fail? e__10399__auto__)
                          (meander.runtime.zeta/fail)
                          (throw e__10399__auto__)))))))))
                  (state__16417))
                 (state__16417))
                (state__16417)))
              (state__16417)))]
           (state__16650))
          (state__16417)))
        (state__16417
         []
         (clojure.core/letfn
          [(D__15862
            [T__15889 ?__14526]
            (clojure.core/letfn
             [(state__16653
               []
               (clojure.core/let
                [T__15890 "meander.zeta"]
                (if
                 (clojure.core/= ?__14526 T__15890)
                 [?__14526]
                 (state__16654))))
              (state__16654
               []
               (if
                (clojure.core/map? T__15889)
                (clojure.core/let
                 [T__15891 (.valAt T__15889 :aliases)]
                 (if
                  (clojure.core/map? T__15891)
                  (clojure.core/loop
                   [S__16655
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__15891
                     1)]
                   (if
                    (clojure.core/seq S__16655)
                    (clojure.core/let
                     [T__15894
                      (clojure.core/first S__16655)
                      R__16656
                      (clojure.core/let
                       [T__15895
                        (clojure.core/nth T__15894 0)
                        T__15897
                        (clojure.core/nth
                         (clojure.core/nth T__15895 0)
                         0)
                        T__15898
                        (.valAt T__15891 T__15897)
                        T__15896
                        (clojure.core/nth T__15894 1)]
                       (if
                        (clojure.core/symbol? T__15897)
                        (clojure.core/let
                         [X__15900 (clojure.core/name T__15897)]
                         (if
                          (clojure.core/= ?__14526 X__15900)
                          (if
                           (clojure.core/symbol? T__15898)
                           (clojure.core/let
                            [X__15902 (clojure.core/name T__15898)]
                            (clojure.core/case
                             X__15902
                             ("meander.zeta")
                             (clojure.core/let
                              [T__15892
                               (clojure.core/dissoc T__15889 :aliases)]
                              [?__14526])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16656)
                      (recur (clojure.core/next S__16655))
                      R__16656))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16653)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__15872
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14526 X__15872]
                     (clojure.core/let
                      [X__15874
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__15874
                       ("let")
                       (clojure.core/let
                        [R__16658
                         (D__15862 input__14513_nth_1__ ?__14526)]
                        (if
                         (meander.runtime.zeta/fail? R__16658)
                         (state__16418)
                         (clojure.core/let
                          [[?__14526] R__16658]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 4
                                 input__14513_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14513_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  1)
                                 input__14513_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__14513_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__14513_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14513_nth_0__]
                                   (clojure.core/let
                                    [?env input__14513_nth_1__]
                                    (try
                                     [{:tag :let,
                                       :pattern
                                       (clojure.core/let
                                        [R__14679
                                         (C__14591 [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14679)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14679
                                          0))),
                                       :expression
                                       {:tag :host-expression,
                                        :form ?expression},
                                       :next {:tag :pass}}]
                                     (catch
                                      java.lang.Exception
                                      e__10399__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10399__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10399__auto__)))))))))
                               (state__16418))
                              (state__16418)))
                            (state__16418))
                           (state__16418)))))
                       (state__16418)))))
                   (state__16418))))
                (state__16418)))
              (state__16418)))
            (state__16418))
           (state__16418))))
        (state__16418
         []
         (clojure.core/letfn
          [(D__15904
            [T__15931 ?__14527]
            (clojure.core/letfn
             [(state__16659
               []
               (clojure.core/let
                [T__15932 "meander.zeta"]
                (if
                 (clojure.core/= ?__14527 T__15932)
                 [?__14527]
                 (state__16660))))
              (state__16660
               []
               (if
                (clojure.core/map? T__15931)
                (clojure.core/let
                 [T__15933 (.valAt T__15931 :aliases)]
                 (if
                  (clojure.core/map? T__15933)
                  (clojure.core/loop
                   [S__16661
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__15933
                     1)]
                   (if
                    (clojure.core/seq S__16661)
                    (clojure.core/let
                     [T__15936
                      (clojure.core/first S__16661)
                      R__16662
                      (clojure.core/let
                       [T__15937
                        (clojure.core/nth T__15936 0)
                        T__15939
                        (clojure.core/nth
                         (clojure.core/nth T__15937 0)
                         0)
                        T__15940
                        (.valAt T__15933 T__15939)
                        T__15938
                        (clojure.core/nth T__15936 1)]
                       (if
                        (clojure.core/symbol? T__15939)
                        (clojure.core/let
                         [X__15942 (clojure.core/name T__15939)]
                         (if
                          (clojure.core/= ?__14527 X__15942)
                          (if
                           (clojure.core/symbol? T__15940)
                           (clojure.core/let
                            [X__15944 (clojure.core/name T__15940)]
                            (clojure.core/case
                             X__15944
                             ("meander.zeta")
                             (clojure.core/let
                              [T__15934
                               (clojure.core/dissoc T__15931 :aliases)]
                              [?__14527])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16662)
                      (recur (clojure.core/next S__16661))
                      R__16662))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16659)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__15914
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14527 X__15914]
                     (clojure.core/let
                      [X__15916
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__15916
                       ("let")
                       (clojure.core/let
                        [R__16664
                         (D__15904 input__14513_nth_1__ ?__14527)]
                        (if
                         (meander.runtime.zeta/fail? R__16664)
                         (state__16419)
                         (clojure.core/let
                          [[?__14527] R__16664]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 5
                                 input__14513_nth_0__)
                                4)
                               (clojure.core/let
                                [input__14513_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  1)
                                 input__14513_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  2)
                                 input__14513_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?pattern
                                  input__14513_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__14513_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?next input__14513_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__14513_nth_0__]
                                    (clojure.core/let
                                     [?env input__14513_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [R__14680
                                          (C__14591 [?pattern ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           R__14680)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           R__14680
                                           0))),
                                        :expression
                                        {:tag :host-expression,
                                         :form ?expression},
                                        :next
                                        (clojure.core/let
                                         [R__14681
                                          (C__14591 [?next ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           R__14681)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           R__14681
                                           0)))}]
                                      (catch
                                       java.lang.Exception
                                       e__10399__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10399__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__10399__auto__))))))))))
                               (state__16419))
                              (state__16419)))
                            (state__16419))
                           (state__16419)))))
                       (state__16419)))))
                   (state__16419))))
                (state__16419)))
              (state__16419)))
            (state__16419))
           (state__16419))))
        (state__16419
         []
         (clojure.core/letfn
          [(D__15946
            [T__15973 ?__14528]
            (clojure.core/letfn
             [(state__16665
               []
               (clojure.core/let
                [T__15974 "meander.zeta"]
                (if
                 (clojure.core/= ?__14528 T__15974)
                 [?__14528]
                 (state__16666))))
              (state__16666
               []
               (if
                (clojure.core/map? T__15973)
                (clojure.core/let
                 [T__15975 (.valAt T__15973 :aliases)]
                 (if
                  (clojure.core/map? T__15975)
                  (clojure.core/loop
                   [S__16667
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__15975
                     1)]
                   (if
                    (clojure.core/seq S__16667)
                    (clojure.core/let
                     [T__15978
                      (clojure.core/first S__16667)
                      R__16668
                      (clojure.core/let
                       [T__15979
                        (clojure.core/nth T__15978 0)
                        T__15981
                        (clojure.core/nth
                         (clojure.core/nth T__15979 0)
                         0)
                        T__15982
                        (.valAt T__15975 T__15981)
                        T__15980
                        (clojure.core/nth T__15978 1)]
                       (if
                        (clojure.core/symbol? T__15981)
                        (clojure.core/let
                         [X__15984 (clojure.core/name T__15981)]
                         (if
                          (clojure.core/= ?__14528 X__15984)
                          (if
                           (clojure.core/symbol? T__15982)
                           (clojure.core/let
                            [X__15986 (clojure.core/name T__15982)]
                            (clojure.core/case
                             X__15986
                             ("meander.zeta")
                             (clojure.core/let
                              [T__15976
                               (clojure.core/dissoc T__15973 :aliases)]
                              [?__14528])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16668)
                      (recur (clojure.core/next S__16667))
                      R__16668))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16665)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__15956
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14528 X__15956]
                     (clojure.core/let
                      [X__15958
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__15958
                       ("not")
                       (clojure.core/let
                        [R__16670
                         (D__15946 input__14513_nth_1__ ?__14528)]
                        (if
                         (meander.runtime.zeta/fail? R__16670)
                         (state__16420)
                         (clojure.core/let
                          [[?__14528] R__16670]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 3
                                 input__14513_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14513_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__14513_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14513_nth_0__]
                                  (clojure.core/let
                                   [?env input__14513_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [R__14682
                                        (C__14591 [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         R__14682)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         R__14682
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10399__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10399__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10399__auto__))))))))
                               (state__16420))
                              (state__16420)))
                            (state__16420))
                           (state__16420)))))
                       (state__16420)))))
                   (state__16420))))
                (state__16420)))
              (state__16420)))
            (state__16420))
           (state__16420))))
        (state__16420
         []
         (clojure.core/letfn
          [(D__15988
            [T__16017 ?__14529]
            (clojure.core/letfn
             [(state__16671
               []
               (clojure.core/let
                [T__16018 "meander.zeta"]
                (if
                 (clojure.core/= ?__14529 T__16018)
                 [?__14529]
                 (state__16672))))
              (state__16672
               []
               (if
                (clojure.core/map? T__16017)
                (clojure.core/let
                 [T__16019 (.valAt T__16017 :aliases)]
                 (if
                  (clojure.core/map? T__16019)
                  (clojure.core/loop
                   [S__16673
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__16019
                     1)]
                   (if
                    (clojure.core/seq S__16673)
                    (clojure.core/let
                     [T__16022
                      (clojure.core/first S__16673)
                      R__16674
                      (clojure.core/let
                       [T__16023
                        (clojure.core/nth T__16022 0)
                        T__16025
                        (clojure.core/nth
                         (clojure.core/nth T__16023 0)
                         0)
                        T__16026
                        (.valAt T__16019 T__16025)
                        T__16024
                        (clojure.core/nth T__16022 1)]
                       (if
                        (clojure.core/symbol? T__16025)
                        (clojure.core/let
                         [X__16028 (clojure.core/name T__16025)]
                         (if
                          (clojure.core/= ?__14529 X__16028)
                          (if
                           (clojure.core/symbol? T__16026)
                           (clojure.core/let
                            [X__16030 (clojure.core/name T__16026)]
                            (clojure.core/case
                             X__16030
                             ("meander.zeta")
                             (clojure.core/let
                              [T__16020
                               (clojure.core/dissoc T__16017 :aliases)]
                              [?__14529])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16674)
                      (recur (clojure.core/next S__16673))
                      R__16674))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16671)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__16000
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14529 X__16000]
                     (clojure.core/let
                      [X__16002
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__16002
                       ("or")
                       (clojure.core/let
                        [R__16676
                         (D__15988 input__14513_nth_1__ ?__14529)]
                        (if
                         (meander.runtime.zeta/fail? R__16676)
                         (state__16421)
                         (clojure.core/let
                          [[?__14529] R__16676]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (clojure.core/let
                               [input__14513_nth_0___L__
                                (clojure.core/take
                                 1
                                 input__14513_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  2
                                  input__14513_nth_0___L__)
                                 1)
                                (clojure.core/let
                                 [input__14513_nth_0___R__
                                  (clojure.core/drop
                                   1
                                   input__14513_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__14513_nth_0___R__)]
                                  (clojure.core/let
                                   [?form input__14513_nth_0__]
                                   (clojure.core/let
                                    [?env input__14513_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [R__14685
                                         (C__14591
                                          ['meander.dev.parse.zeta/make-or
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__14684
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__14684
                                                (clojure.core/let
                                                 [R__14683
                                                  (C__14591
                                                   [(if
                                                     (.hasNext
                                                      !forms__counter)
                                                     (.next
                                                      !forms__counter))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   R__14683)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   R__14683
                                                   0)))))
                                              (clojure.core/persistent!
                                               return__14684))))
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14685)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14685
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10399__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10399__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10399__auto__))))))))
                                (state__16421)))
                              (state__16421)))
                            (state__16421))
                           (state__16421)))))
                       (state__16421)))))
                   (state__16421))))
                (state__16421)))
              (state__16421)))
            (state__16421))
           (state__16421))))
        (state__16421
         []
         (if
          (clojure.core/vector? input__14513)
          (clojure.core/letfn
           [(state__16677
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-or)
                (clojure.core/case
                 input__14513_nth_1__
                 ([])
                 (clojure.core/let
                  [?form input__14513_nth_2__]
                  [{:tag :error,
                    :message
                    "meander.zeta/or requires 1 or more arguments",
                    :form ?form}])
                 (state__16678))
                (state__16678)))
              (state__16678)))
            (state__16678
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-or)
                (clojure.core/case
                 input__14513_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__14513_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14513_nth_1__)
                    1)
                   (clojure.core/let
                    [input__14513_nth_1___nth_0__
                     (clojure.core/nth input__14513_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__14513_nth_1___nth_0__]
                     [?ast-a]))
                   (state__16679))
                  (state__16679))
                 (state__16679))
                (state__16679)))
              (state__16679)))
            (state__16679
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-or)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (if
                  (clojure.core/=
                   (clojure.core/count input__14513_nth_1__)
                   1)
                  (clojure.core/let
                   [input__14513_nth_1___nth_0__
                    (clojure.core/nth input__14513_nth_1__ 0)]
                   (clojure.core/let
                    [?ast-a input__14513_nth_1___nth_0__]
                    (clojure.core/let
                     [?form input__14513_nth_2__]
                     (try
                      [(clojure.core/let
                        [R__14686
                         (C__14591
                          ['meander.dev.parse.zeta/make-or
                           [?ast-a {:tag :pass}]
                           ?form])]
                        (if
                         (meander.runtime.zeta/fail? R__14686)
                         (throw (meander.runtime.zeta/fail))
                         (clojure.core/nth R__14686 0)))]
                      (catch
                       java.lang.Exception
                       e__10399__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10399__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10399__auto__)))))))
                  (state__16680))
                 (state__16680))
                (state__16680)))
              (state__16680)))
            (state__16680
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-or)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (if
                  (clojure.core/=
                   (clojure.core/count input__14513_nth_1__)
                   2)
                  (clojure.core/let
                   [input__14513_nth_1___nth_0__
                    (clojure.core/nth input__14513_nth_1__ 0)
                    input__14513_nth_1___nth_1__
                    (clojure.core/nth input__14513_nth_1__ 1)]
                   (clojure.core/let
                    [?ast-a input__14513_nth_1___nth_0__]
                    (clojure.core/let
                     [?ast-b input__14513_nth_1___nth_1__]
                     (clojure.core/let
                      [?form input__14513_nth_2__]
                      [{:tag :or,
                        :left ?ast-a,
                        :right ?ast-b,
                        :form ?form}]))))
                  (state__16681))
                 (state__16681))
                (state__16681)))
              (state__16681)))
            (state__16681
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-or)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/loop
                  [S__16683
                   (meander.runtime.zeta/epsilon-partitions
                    2
                    input__14513_nth_1__)]
                  (if
                   (clojure.core/seq S__16683)
                   (clojure.core/let
                    [input__14513_nth_1___parts__
                     (clojure.core/first S__16683)
                     R__16684
                     (clojure.core/let
                      [input__14513_nth_1___L__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        0)
                       input__14513_nth_1___R__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!asts-1 []]
                       (clojure.core/let
                        [R__16689
                         (meander.runtime.zeta/epsilon-run-star-1
                          input__14513_nth_1___L__
                          [!asts-1]
                          (clojure.core/fn
                           [[!asts-1] input__16057]
                           (clojure.core/let
                            [input__16057_nth_0__
                             (clojure.core/nth input__16057 0)]
                            (clojure.core/let
                             [!asts-1
                              (clojure.core/conj
                               !asts-1
                               input__16057_nth_0__)]
                             [!asts-1])))
                          (clojure.core/fn
                           [[!asts-1]]
                           (clojure.core/let
                            [X__16050
                             (clojure.core/count
                              input__14513_nth_1___L__)]
                            (clojure.core/let
                             [?n X__16050]
                             (clojure.core/let
                              [X__16054
                               (clojure.core/count
                                input__14513_nth_1___R__)]
                              (if
                               (clojure.core/= ?n X__16054)
                               (clojure.core/let
                                [!asts-2 []]
                                (clojure.core/let
                                 [R__16688
                                  (meander.runtime.zeta/epsilon-run-star-1
                                   input__14513_nth_1___R__
                                   [!asts-2 !asts-1]
                                   (clojure.core/fn
                                    [[!asts-2 !asts-1] input__16055]
                                    (clojure.core/let
                                     [input__16055_nth_0__
                                      (clojure.core/nth
                                       input__16055
                                       0)]
                                     (clojure.core/let
                                      [!asts-2
                                       (clojure.core/conj
                                        !asts-2
                                        input__16055_nth_0__)]
                                      [!asts-2 !asts-1])))
                                   (clojure.core/fn
                                    [[!asts-2 !asts-1]]
                                    (clojure.core/let
                                     [?form input__14513_nth_2__]
                                     (try
                                      [(clojure.core/let
                                        [!asts-1__counter
                                         (meander.runtime.zeta/iterator
                                          !asts-1)
                                         !asts-2__counter
                                         (meander.runtime.zeta/iterator
                                          !asts-2)]
                                        (clojure.core/let
                                         [R__14689
                                          (C__14591
                                           ['meander.dev.parse.zeta/make-or
                                            [(clojure.core/let
                                              [R__14687
                                               (C__14591
                                                ['meander.dev.parse.zeta/make-or
                                                 (clojure.core/into
                                                  []
                                                  (clojure.core/vec
                                                   (clojure.core/iterator-seq
                                                    !asts-1__counter)))
                                                 nil])]
                                              (if
                                               (meander.runtime.zeta/fail?
                                                R__14687)
                                               (throw
                                                (meander.runtime.zeta/fail))
                                               (clojure.core/nth
                                                R__14687
                                                0)))
                                             (clojure.core/let
                                              [R__14688
                                               (C__14591
                                                ['meander.dev.parse.zeta/make-or
                                                 (clojure.core/into
                                                  []
                                                  (clojure.core/vec
                                                   (clojure.core/iterator-seq
                                                    !asts-2__counter)))
                                                 nil])]
                                              (if
                                               (meander.runtime.zeta/fail?
                                                R__14688)
                                               (throw
                                                (meander.runtime.zeta/fail))
                                               (clojure.core/nth
                                                R__14688
                                                0)))]
                                            ?form])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           R__14689)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           R__14689
                                           0))))]
                                      (catch
                                       java.lang.Exception
                                       e__10399__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10399__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw e__10399__auto__)))))))]
                                 (if
                                  (meander.runtime.zeta/fail? R__16688)
                                  (meander.runtime.zeta/fail)
                                  R__16688)))
                               (meander.runtime.zeta/fail)))))))]
                        (if
                         (meander.runtime.zeta/fail? R__16689)
                         (meander.runtime.zeta/fail)
                         R__16689))))]
                    (if
                     (meander.runtime.zeta/fail? R__16684)
                     (recur (clojure.core/next S__16683))
                     R__16684))
                   (state__16682)))
                 (state__16682))
                (state__16682)))
              (state__16682)))
            (state__16682
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 3)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-or)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (clojure.core/loop
                  [S__16690
                   (meander.runtime.zeta/epsilon-partitions
                    2
                    input__14513_nth_1__)]
                  (if
                   (clojure.core/seq S__16690)
                   (clojure.core/let
                    [input__14513_nth_1___parts__
                     (clojure.core/first S__16690)
                     R__16691
                     (clojure.core/let
                      [input__14513_nth_1___L__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        0)
                       input__14513_nth_1___R__
                       (clojure.core/nth
                        input__14513_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!asts-1 []]
                       (clojure.core/let
                        [R__16696
                         (meander.runtime.zeta/epsilon-run-star-1
                          input__14513_nth_1___L__
                          [!asts-1]
                          (clojure.core/fn
                           [[!asts-1] input__16073]
                           (clojure.core/let
                            [input__16073_nth_0__
                             (clojure.core/nth input__16073 0)]
                            (clojure.core/let
                             [!asts-1
                              (clojure.core/conj
                               !asts-1
                               input__16073_nth_0__)]
                             [!asts-1])))
                          (clojure.core/fn
                           [[!asts-1]]
                           (clojure.core/let
                            [X__16064
                             (clojure.core/count
                              input__14513_nth_1___L__)]
                            (clojure.core/let
                             [?n X__16064]
                             (clojure.core/let
                              [input__14513_nth_1___R___L__
                               (clojure.core/subvec
                                input__14513_nth_1___R__
                                0
                                (clojure.core/min
                                 (clojure.core/count
                                  input__14513_nth_1___R__)
                                 1))]
                              (if
                               (clojure.core/=
                                (clojure.core/count
                                 input__14513_nth_1___R___L__)
                                1)
                               (clojure.core/let
                                [input__14513_nth_1___R___R__
                                 (clojure.core/subvec
                                  input__14513_nth_1___R__
                                  1)]
                                (clojure.core/let
                                 [input__14513_nth_1___R___L___nth_0__
                                  (clojure.core/nth
                                   input__14513_nth_1___R___L__
                                   0)]
                                 (clojure.core/let
                                  [?ast
                                   input__14513_nth_1___R___L___nth_0__]
                                  (clojure.core/let
                                   [X__16070
                                    (clojure.core/count
                                     input__14513_nth_1___R___R__)]
                                   (if
                                    (clojure.core/= ?n X__16070)
                                    (clojure.core/let
                                     [!asts-2 []]
                                     (clojure.core/let
                                      [R__16695
                                       (meander.runtime.zeta/epsilon-run-star-1
                                        input__14513_nth_1___R___R__
                                        [!asts-2 !asts-1]
                                        (clojure.core/fn
                                         [[!asts-2 !asts-1]
                                          input__16071]
                                         (clojure.core/let
                                          [input__16071_nth_0__
                                           (clojure.core/nth
                                            input__16071
                                            0)]
                                          (clojure.core/let
                                           [!asts-2
                                            (clojure.core/conj
                                             !asts-2
                                             input__16071_nth_0__)]
                                           [!asts-2 !asts-1])))
                                        (clojure.core/fn
                                         [[!asts-2 !asts-1]]
                                         (clojure.core/let
                                          [?form input__14513_nth_2__]
                                          (try
                                           [(clojure.core/let
                                             [!asts-1__counter
                                              (meander.runtime.zeta/iterator
                                               !asts-1)
                                              !asts-2__counter
                                              (meander.runtime.zeta/iterator
                                               !asts-2)]
                                             (clojure.core/let
                                              [R__14692
                                               (C__14591
                                                ['meander.dev.parse.zeta/make-or
                                                 [(clojure.core/let
                                                   [R__14690
                                                    (C__14591
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
                                                     R__14690)
                                                    (throw
                                                     (meander.runtime.zeta/fail))
                                                    (clojure.core/nth
                                                     R__14690
                                                     0)))
                                                  (clojure.core/let
                                                   [R__14691
                                                    (C__14591
                                                     ['meander.dev.parse.zeta/make-or
                                                      (clojure.core/into
                                                       []
                                                       (clojure.core/vec
                                                        (clojure.core/iterator-seq
                                                         !asts-2__counter)))
                                                      nil])]
                                                   (if
                                                    (meander.runtime.zeta/fail?
                                                     R__14691)
                                                    (throw
                                                     (meander.runtime.zeta/fail))
                                                    (clojure.core/nth
                                                     R__14691
                                                     0)))]
                                                 ?form])]
                                              (if
                                               (meander.runtime.zeta/fail?
                                                R__14692)
                                               (throw
                                                (meander.runtime.zeta/fail))
                                               (clojure.core/nth
                                                R__14692
                                                0))))]
                                           (catch
                                            java.lang.Exception
                                            e__10399__auto__
                                            (if
                                             (meander.runtime.zeta/fail?
                                              e__10399__auto__)
                                             (meander.runtime.zeta/fail)
                                             (throw
                                              e__10399__auto__)))))))]
                                      (if
                                       (meander.runtime.zeta/fail?
                                        R__16695)
                                       (meander.runtime.zeta/fail)
                                       R__16695)))
                                    (meander.runtime.zeta/fail))))))
                               (meander.runtime.zeta/fail)))))))]
                        (if
                         (meander.runtime.zeta/fail? R__16696)
                         (meander.runtime.zeta/fail)
                         R__16696))))]
                    (if
                     (meander.runtime.zeta/fail? R__16691)
                     (recur (clojure.core/next S__16690))
                     R__16691))
                   (state__16422)))
                 (state__16422))
                (state__16422)))
              (state__16422)))]
           (state__16677))
          (state__16422)))
        (state__16422
         []
         (clojure.core/letfn
          [(D__16076
            [T__16103 ?__14530]
            (clojure.core/letfn
             [(state__16697
               []
               (clojure.core/let
                [T__16104 "meander.zeta"]
                (if
                 (clojure.core/= ?__14530 T__16104)
                 [?__14530]
                 (state__16698))))
              (state__16698
               []
               (if
                (clojure.core/map? T__16103)
                (clojure.core/let
                 [T__16105 (.valAt T__16103 :aliases)]
                 (if
                  (clojure.core/map? T__16105)
                  (clojure.core/loop
                   [S__16699
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__16105
                     1)]
                   (if
                    (clojure.core/seq S__16699)
                    (clojure.core/let
                     [T__16108
                      (clojure.core/first S__16699)
                      R__16700
                      (clojure.core/let
                       [T__16109
                        (clojure.core/nth T__16108 0)
                        T__16111
                        (clojure.core/nth
                         (clojure.core/nth T__16109 0)
                         0)
                        T__16112
                        (.valAt T__16105 T__16111)
                        T__16110
                        (clojure.core/nth T__16108 1)]
                       (if
                        (clojure.core/symbol? T__16111)
                        (clojure.core/let
                         [X__16114 (clojure.core/name T__16111)]
                         (if
                          (clojure.core/= ?__14530 X__16114)
                          (if
                           (clojure.core/symbol? T__16112)
                           (clojure.core/let
                            [X__16116 (clojure.core/name T__16112)]
                            (clojure.core/case
                             X__16116
                             ("meander.zeta")
                             (clojure.core/let
                              [T__16106
                               (clojure.core/dissoc T__16103 :aliases)]
                              [?__14530])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16700)
                      (recur (clojure.core/next S__16699))
                      R__16700))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16697)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__16086
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14530 X__16086]
                     (clojure.core/let
                      [X__16088
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__16088
                       ("pred")
                       (clojure.core/let
                        [R__16702
                         (D__16076 input__14513_nth_1__ ?__14530)]
                        (if
                         (meander.runtime.zeta/fail? R__16702)
                         (state__16423)
                         (clojure.core/let
                          [[?__14530] R__16702]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 3
                                 input__14513_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14513_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?expression
                                  input__14513_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14513_nth_0__]
                                  (clojure.core/let
                                   [?env input__14513_nth_1__]
                                   [{:tag :pred,
                                     :expression
                                     {:tag :host-expression,
                                      :form ?expression},
                                     :pattern {:tag :wildcard},
                                     :form ?form}]))))
                               (state__16423))
                              (state__16423)))
                            (state__16423))
                           (state__16423)))))
                       (state__16423)))))
                   (state__16423))))
                (state__16423)))
              (state__16423)))
            (state__16423))
           (state__16423))))
        (state__16423
         []
         (clojure.core/letfn
          [(D__16118
            [T__16145 ?__14531]
            (clojure.core/letfn
             [(state__16703
               []
               (clojure.core/let
                [T__16146 "meander.zeta"]
                (if
                 (clojure.core/= ?__14531 T__16146)
                 [?__14531]
                 (state__16704))))
              (state__16704
               []
               (if
                (clojure.core/map? T__16145)
                (clojure.core/let
                 [T__16147 (.valAt T__16145 :aliases)]
                 (if
                  (clojure.core/map? T__16147)
                  (clojure.core/loop
                   [S__16705
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__16147
                     1)]
                   (if
                    (clojure.core/seq S__16705)
                    (clojure.core/let
                     [T__16150
                      (clojure.core/first S__16705)
                      R__16706
                      (clojure.core/let
                       [T__16151
                        (clojure.core/nth T__16150 0)
                        T__16153
                        (clojure.core/nth
                         (clojure.core/nth T__16151 0)
                         0)
                        T__16154
                        (.valAt T__16147 T__16153)
                        T__16152
                        (clojure.core/nth T__16150 1)]
                       (if
                        (clojure.core/symbol? T__16153)
                        (clojure.core/let
                         [X__16156 (clojure.core/name T__16153)]
                         (if
                          (clojure.core/= ?__14531 X__16156)
                          (if
                           (clojure.core/symbol? T__16154)
                           (clojure.core/let
                            [X__16158 (clojure.core/name T__16154)]
                            (clojure.core/case
                             X__16158
                             ("meander.zeta")
                             (clojure.core/let
                              [T__16148
                               (clojure.core/dissoc T__16145 :aliases)]
                              [?__14531])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16706)
                      (recur (clojure.core/next S__16705))
                      R__16706))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16703)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__16128
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14531 X__16128]
                     (clojure.core/let
                      [X__16130
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__16130
                       ("pred")
                       (clojure.core/let
                        [R__16708
                         (D__16118 input__14513_nth_1__ ?__14531)]
                        (if
                         (meander.runtime.zeta/fail? R__16708)
                         (state__16424)
                         (clojure.core/let
                          [[?__14531] R__16708]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 4
                                 input__14513_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14513_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  1)
                                 input__14513_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?expression
                                  input__14513_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__14513_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14513_nth_0__]
                                   (clojure.core/let
                                    [?env input__14513_nth_1__]
                                    (try
                                     [{:tag :pred,
                                       :expression
                                       {:tag :host-expression,
                                        :form ?expression},
                                       :pattern
                                       (clojure.core/let
                                        [R__14693
                                         (C__14591 [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14693)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14693
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10399__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10399__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10399__auto__)))))))))
                               (state__16424))
                              (state__16424)))
                            (state__16424))
                           (state__16424)))))
                       (state__16424)))))
                   (state__16424))))
                (state__16424)))
              (state__16424)))
            (state__16424))
           (state__16424))))
        (state__16424
         []
         (clojure.core/letfn
          [(D__16160
            [T__16187 ?__14532]
            (clojure.core/letfn
             [(state__16709
               []
               (clojure.core/let
                [T__16188 "meander.zeta"]
                (if
                 (clojure.core/= ?__14532 T__16188)
                 [?__14532]
                 (state__16710))))
              (state__16710
               []
               (if
                (clojure.core/map? T__16187)
                (clojure.core/let
                 [T__16189 (.valAt T__16187 :aliases)]
                 (if
                  (clojure.core/map? T__16189)
                  (clojure.core/loop
                   [S__16711
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__16189
                     1)]
                   (if
                    (clojure.core/seq S__16711)
                    (clojure.core/let
                     [T__16192
                      (clojure.core/first S__16711)
                      R__16712
                      (clojure.core/let
                       [T__16193
                        (clojure.core/nth T__16192 0)
                        T__16195
                        (clojure.core/nth
                         (clojure.core/nth T__16193 0)
                         0)
                        T__16196
                        (.valAt T__16189 T__16195)
                        T__16194
                        (clojure.core/nth T__16192 1)]
                       (if
                        (clojure.core/symbol? T__16195)
                        (clojure.core/let
                         [X__16198 (clojure.core/name T__16195)]
                         (if
                          (clojure.core/= ?__14532 X__16198)
                          (if
                           (clojure.core/symbol? T__16196)
                           (clojure.core/let
                            [X__16200 (clojure.core/name T__16196)]
                            (clojure.core/case
                             X__16200
                             ("meander.zeta")
                             (clojure.core/let
                              [T__16190
                               (clojure.core/dissoc T__16187 :aliases)]
                              [?__14532])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16712)
                      (recur (clojure.core/next S__16711))
                      R__16712))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16709)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__16170
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14532 X__16170]
                     (clojure.core/let
                      [X__16172
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__16172
                       ("re")
                       (clojure.core/let
                        [R__16714
                         (D__16160 input__14513_nth_1__ ?__14532)]
                        (if
                         (meander.runtime.zeta/fail? R__16714)
                         (state__16425)
                         (clojure.core/let
                          [[?__14532] R__16714]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 3
                                 input__14513_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14513_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__14513_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14513_nth_0__]
                                  (clojure.core/let
                                   [?env input__14513_nth_1__]
                                   [{:tag :regex,
                                     :regex ?regex,
                                     :form ?form}]))))
                               (state__16425))
                              (state__16425)))
                            (state__16425))
                           (state__16425)))))
                       (state__16425)))))
                   (state__16425))))
                (state__16425)))
              (state__16425)))
            (state__16425))
           (state__16425))))
        (state__16425
         []
         (clojure.core/letfn
          [(D__16202
            [T__16229 ?__14533]
            (clojure.core/letfn
             [(state__16715
               []
               (clojure.core/let
                [T__16230 "meander.zeta"]
                (if
                 (clojure.core/= ?__14533 T__16230)
                 [?__14533]
                 (state__16716))))
              (state__16716
               []
               (if
                (clojure.core/map? T__16229)
                (clojure.core/let
                 [T__16231 (.valAt T__16229 :aliases)]
                 (if
                  (clojure.core/map? T__16231)
                  (clojure.core/loop
                   [S__16717
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__16231
                     1)]
                   (if
                    (clojure.core/seq S__16717)
                    (clojure.core/let
                     [T__16234
                      (clojure.core/first S__16717)
                      R__16718
                      (clojure.core/let
                       [T__16235
                        (clojure.core/nth T__16234 0)
                        T__16237
                        (clojure.core/nth
                         (clojure.core/nth T__16235 0)
                         0)
                        T__16238
                        (.valAt T__16231 T__16237)
                        T__16236
                        (clojure.core/nth T__16234 1)]
                       (if
                        (clojure.core/symbol? T__16237)
                        (clojure.core/let
                         [X__16240 (clojure.core/name T__16237)]
                         (if
                          (clojure.core/= ?__14533 X__16240)
                          (if
                           (clojure.core/symbol? T__16238)
                           (clojure.core/let
                            [X__16242 (clojure.core/name T__16238)]
                            (clojure.core/case
                             X__16242
                             ("meander.zeta")
                             (clojure.core/let
                              [T__16232
                               (clojure.core/dissoc T__16229 :aliases)]
                              [?__14533])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16718)
                      (recur (clojure.core/next S__16717))
                      R__16718))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16715)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__16212
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14533 X__16212]
                     (clojure.core/let
                      [X__16214
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__16214
                       ("re")
                       (clojure.core/let
                        [R__16720
                         (D__16202 input__14513_nth_1__ ?__14533)]
                        (if
                         (meander.runtime.zeta/fail? R__16720)
                         (state__16426)
                         (clojure.core/let
                          [[?__14533] R__16720]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 4
                                 input__14513_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14513_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  1)
                                 input__14513_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14513_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__14513_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__14513_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14513_nth_0__]
                                   (clojure.core/let
                                    [?env input__14513_nth_1__]
                                    (try
                                     [{:tag :regex-with-capture,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [R__14694
                                         (C__14591 [?capture ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14694)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14694
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10399__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10399__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10399__auto__)))))))))
                               (state__16426))
                              (state__16426)))
                            (state__16426))
                           (state__16426)))))
                       (state__16426)))))
                   (state__16426))))
                (state__16426)))
              (state__16426)))
            (state__16426))
           (state__16426))))
        (state__16426
         []
         (clojure.core/letfn
          [(D__16244
            [T__16271 ?__14534]
            (clojure.core/letfn
             [(state__16721
               []
               (clojure.core/let
                [T__16272 "meander.zeta"]
                (if
                 (clojure.core/= ?__14534 T__16272)
                 [?__14534]
                 (state__16722))))
              (state__16722
               []
               (if
                (clojure.core/map? T__16271)
                (clojure.core/let
                 [T__16273 (.valAt T__16271 :aliases)]
                 (if
                  (clojure.core/map? T__16273)
                  (clojure.core/loop
                   [S__16723
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__16273
                     1)]
                   (if
                    (clojure.core/seq S__16723)
                    (clojure.core/let
                     [T__16276
                      (clojure.core/first S__16723)
                      R__16724
                      (clojure.core/let
                       [T__16277
                        (clojure.core/nth T__16276 0)
                        T__16279
                        (clojure.core/nth
                         (clojure.core/nth T__16277 0)
                         0)
                        T__16280
                        (.valAt T__16273 T__16279)
                        T__16278
                        (clojure.core/nth T__16276 1)]
                       (if
                        (clojure.core/symbol? T__16279)
                        (clojure.core/let
                         [X__16282 (clojure.core/name T__16279)]
                         (if
                          (clojure.core/= ?__14534 X__16282)
                          (if
                           (clojure.core/symbol? T__16280)
                           (clojure.core/let
                            [X__16284 (clojure.core/name T__16280)]
                            (clojure.core/case
                             X__16284
                             ("meander.zeta")
                             (clojure.core/let
                              [T__16274
                               (clojure.core/dissoc T__16271 :aliases)]
                              [?__14534])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16724)
                      (recur (clojure.core/next S__16723))
                      R__16724))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16721)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__16254
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14534 X__16254]
                     (clojure.core/let
                      [X__16256
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__16256
                       ("str")
                       (clojure.core/let
                        [R__16726
                         (D__16244 input__14513_nth_1__ ?__14534)]
                        (if
                         (meander.runtime.zeta/fail? R__16726)
                         (state__16427)
                         (clojure.core/let
                          [[?__14534] R__16726]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (clojure.core/let
                               [input__14513_nth_0___L__
                                (clojure.core/take
                                 1
                                 input__14513_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  2
                                  input__14513_nth_0___L__)
                                 1)
                                (clojure.core/let
                                 [input__14513_nth_0___R__
                                  (clojure.core/drop
                                   1
                                   input__14513_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__14513_nth_0___R__]
                                  (clojure.core/let
                                   [?form input__14513_nth_0__]
                                   (clojure.core/let
                                    [?env input__14513_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [R__14695
                                         (C__14591
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           (clojure.core/let
                                            [form__9462__auto__
                                             {:context :string}]
                                            (clojure.core/merge
                                             (clojure.core/into
                                              {}
                                              ?env)
                                             form__9462__auto__))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          R__14695)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          R__14695
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10399__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10399__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10399__auto__))))))))
                                (state__16427)))
                              (state__16427)))
                            (state__16427))
                           (state__16427)))))
                       (state__16427)))))
                   (state__16427))))
                (state__16427)))
              (state__16427)))
            (state__16427))
           (state__16427))))
        (state__16427
         []
         (clojure.core/letfn
          [(D__16286
            [T__16313 ?__14535]
            (clojure.core/letfn
             [(state__16727
               []
               (clojure.core/let
                [T__16314 "meander.zeta"]
                (if
                 (clojure.core/= ?__14535 T__16314)
                 [?__14535]
                 (state__16728))))
              (state__16728
               []
               (if
                (clojure.core/map? T__16313)
                (clojure.core/let
                 [T__16315 (.valAt T__16313 :aliases)]
                 (if
                  (clojure.core/map? T__16315)
                  (clojure.core/loop
                   [S__16729
                    (meander.match.runtime.epsilon/map-k-permutations-with-unselected
                     T__16315
                     1)]
                   (if
                    (clojure.core/seq S__16729)
                    (clojure.core/let
                     [T__16318
                      (clojure.core/first S__16729)
                      R__16730
                      (clojure.core/let
                       [T__16319
                        (clojure.core/nth T__16318 0)
                        T__16321
                        (clojure.core/nth
                         (clojure.core/nth T__16319 0)
                         0)
                        T__16322
                        (.valAt T__16315 T__16321)
                        T__16320
                        (clojure.core/nth T__16318 1)]
                       (if
                        (clojure.core/symbol? T__16321)
                        (clojure.core/let
                         [X__16324 (clojure.core/name T__16321)]
                         (if
                          (clojure.core/= ?__14535 X__16324)
                          (if
                           (clojure.core/symbol? T__16322)
                           (clojure.core/let
                            [X__16326 (clojure.core/name T__16322)]
                            (clojure.core/case
                             X__16326
                             ("meander.zeta")
                             (clojure.core/let
                              [T__16316
                               (clojure.core/dissoc T__16313 :aliases)]
                              [?__14535])
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail)))]
                     (if
                      (meander.runtime.zeta/fail? R__16730)
                      (recur (clojure.core/next S__16729))
                      R__16730))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16727)))]
          (if
           (clojure.core/vector? input__14513)
           (if
            (clojure.core/= (clojure.core/count input__14513) 2)
            (clojure.core/let
             [input__14513_nth_0__
              (clojure.core/nth input__14513 0)
              input__14513_nth_1__
              (clojure.core/nth input__14513 1)]
             (if
              (clojure.core/seq? input__14513_nth_0__)
              (clojure.core/let
               [input__14513_nth_0___L__
                (clojure.core/take 1 input__14513_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  2
                  input__14513_nth_0___L__)
                 1)
                (clojure.core/let
                 [input__14513_nth_0___R__
                  (clojure.core/drop 1 input__14513_nth_0__)]
                 (clojure.core/let
                  [input__14513_nth_0___L___nth_0__
                   (clojure.core/nth input__14513_nth_0___L__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14513_nth_0___L___nth_0__)
                   (clojure.core/let
                    [X__16296
                     (clojure.core/namespace
                      input__14513_nth_0___L___nth_0__)]
                    (clojure.core/let
                     [?__14535 X__16296]
                     (clojure.core/let
                      [X__16298
                       (clojure.core/name
                        input__14513_nth_0___L___nth_0__)]
                      (clojure.core/case
                       X__16298
                       ("symbol")
                       (clojure.core/let
                        [R__16732
                         (D__16286 input__14513_nth_1__ ?__14535)]
                        (if
                         (meander.runtime.zeta/fail? R__16732)
                         (state__16428)
                         (clojure.core/let
                          [[?__14535] R__16732]
                          (if
                           (clojure.core/vector? input__14513)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14513)
                             2)
                            (clojure.core/let
                             [input__14513_nth_0__
                              (clojure.core/nth input__14513 0)
                              input__14513_nth_1__
                              (clojure.core/nth input__14513 1)]
                             (if
                              (clojure.core/seq? input__14513_nth_0__)
                              (clojure.core/let
                               [input__14513_nth_0___L__
                                (clojure.core/take
                                 1
                                 input__14513_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  2
                                  input__14513_nth_0___L__)
                                 1)
                                (clojure.core/let
                                 [input__14513_nth_0___R__
                                  (clojure.core/drop
                                   1
                                   input__14513_nth_0__)]
                                 (clojure.core/let
                                  [?symbol-args
                                   input__14513_nth_0___R__]
                                  (clojure.core/let
                                   [?form input__14513_nth_0__]
                                   (clojure.core/let
                                    [?env input__14513_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [R__14696
                                        (C__14591
                                         ['meander.dev.parse.zeta/make-symbol
                                          (clojure.core/into
                                           []
                                           ?symbol-args)
                                          ?form
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         R__14696)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         R__14696
                                         0)))]
                                     (catch
                                      java.lang.Exception
                                      e__10399__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10399__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10399__auto__))))))))
                                (state__16428)))
                              (state__16428)))
                            (state__16428))
                           (state__16428)))))
                       (state__16428)))))
                   (state__16428))))
                (state__16428)))
              (state__16428)))
            (state__16428))
           (state__16428))))
        (state__16428
         []
         (if
          (clojure.core/vector? input__14513)
          (clojure.core/letfn
           [(state__16733
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-symbol)
                (clojure.core/case
                 input__14513_nth_1__
                 ([])
                 (clojure.core/let
                  [?form input__14513_nth_2__]
                  [{:tag :symbol,
                    :namespace {:tag :wildcard},
                    :name {:tag :wildcard},
                    :form ?form}])
                 (state__16734))
                (state__16734)))
              (state__16734)))
            (state__16734
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-symbol)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (if
                  (clojure.core/=
                   (clojure.core/count input__14513_nth_1__)
                   1)
                  (clojure.core/let
                   [input__14513_nth_1___nth_0__
                    (clojure.core/nth input__14513_nth_1__ 0)]
                   (clojure.core/let
                    [?name input__14513_nth_1___nth_0__]
                    (clojure.core/let
                     [?form input__14513_nth_2__]
                     (clojure.core/let
                      [?env input__14513_nth_3__]
                      (try
                       [{:tag :symbol,
                         :namespace {:tag :wildcard},
                         :name
                         (clojure.core/let
                          [R__14697 (C__14591 [?name ?env])]
                          (if
                           (meander.runtime.zeta/fail? R__14697)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth R__14697 0))),
                         :form ?form}]
                       (catch
                        java.lang.Exception
                        e__10399__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10399__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10399__auto__))))))))
                  (state__16735))
                 (state__16735))
                (state__16735)))
              (state__16735)))
            (state__16735
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 4)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)
                input__14513_nth_2__
                (clojure.core/nth input__14513 2)
                input__14513_nth_3__
                (clojure.core/nth input__14513 3)]
               (clojure.core/case
                input__14513_nth_0__
                (meander.dev.parse.zeta/make-symbol)
                (if
                 (clojure.core/vector? input__14513_nth_1__)
                 (if
                  (clojure.core/=
                   (clojure.core/count input__14513_nth_1__)
                   2)
                  (clojure.core/let
                   [input__14513_nth_1___nth_0__
                    (clojure.core/nth input__14513_nth_1__ 0)
                    input__14513_nth_1___nth_1__
                    (clojure.core/nth input__14513_nth_1__ 1)]
                   (clojure.core/let
                    [?namespace input__14513_nth_1___nth_0__]
                    (clojure.core/let
                     [?name input__14513_nth_1___nth_1__]
                     (clojure.core/let
                      [?form input__14513_nth_2__]
                      (clojure.core/let
                       [?env input__14513_nth_3__]
                       (try
                        [{:tag :symbol,
                          :namespace
                          (clojure.core/let
                           [R__14698 (C__14591 [?namespace ?env])]
                           (if
                            (meander.runtime.zeta/fail? R__14698)
                            (throw (meander.runtime.zeta/fail))
                            (clojure.core/nth R__14698 0))),
                          :name
                          (clojure.core/let
                           [R__14699 (C__14591 [?name ?env])]
                           (if
                            (meander.runtime.zeta/fail? R__14699)
                            (throw (meander.runtime.zeta/fail))
                            (clojure.core/nth R__14699 0))),
                          :form ?form}]
                        (catch
                         java.lang.Exception
                         e__10399__auto__
                         (if
                          (meander.runtime.zeta/fail? e__10399__auto__)
                          (meander.runtime.zeta/fail)
                          (throw e__10399__auto__)))))))))
                  (state__16736))
                 (state__16736))
                (state__16736)))
              (state__16736)))
            (state__16736
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 2)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)]
               (if
                (clojure.core/seq? input__14513_nth_0__)
                (clojure.core/let
                 [?sequence input__14513_nth_0__]
                 (clojure.core/let
                  [?env input__14513_nth_1__]
                  (try
                   [{:tag :seq,
                     :next
                     (clojure.core/let
                      [R__14700
                       (C__14591
                        ['meander.dev.parse.zeta/parse-sequential
                         (clojure.core/into [] ?sequence)
                         (clojure.core/let
                          [form__9462__auto__ {:context :seq}]
                          (clojure.core/merge
                           (clojure.core/into {} ?env)
                           form__9462__auto__))])]
                      (if
                       (meander.runtime.zeta/fail? R__14700)
                       (throw (meander.runtime.zeta/fail))
                       (clojure.core/nth R__14700 0))),
                     :form ?sequence}]
                   (catch
                    java.lang.Exception
                    e__10399__auto__
                    (if
                     (meander.runtime.zeta/fail? e__10399__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__10399__auto__))))))
                (state__16737)))
              (state__16737)))
            (state__16737
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 2)
              (clojure.core/let
               [input__14513_nth_0__
                (clojure.core/nth input__14513 0)
                input__14513_nth_1__
                (clojure.core/nth input__14513 1)]
               (if
                (clojure.core/map? input__14513_nth_0__)
                (clojure.core/let
                 [?map input__14513_nth_0__]
                 (clojure.core/let
                  [?env input__14513_nth_1__]
                  (try
                   [{:tag :map,
                     :next
                     (clojure.core/let
                      [R__14701
                       (C__14591
                        ['meander.dev.parse.zeta/parse-entries
                         ?map
                         ?env])]
                      (if
                       (meander.runtime.zeta/fail? R__14701)
                       (throw (meander.runtime.zeta/fail))
                       (clojure.core/nth R__14701 0))),
                     :form ?map}]
                   (catch
                    java.lang.Exception
                    e__10399__auto__
                    (if
                     (meander.runtime.zeta/fail? e__10399__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__10399__auto__))))))
                (state__16738)))
              (state__16738)))
            (state__16738
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 2)
              (clojure.core/let
               [input__14513_nth_0__ (clojure.core/nth input__14513 0)]
               (if
                (clojure.core/symbol? input__14513_nth_0__)
                (clojure.core/let
                 [X__16346
                  (clojure.core/namespace input__14513_nth_0__)]
                 (clojure.core/let
                  [X__16348 (clojure.core/name input__14513_nth_0__)]
                  (if
                   (clojure.core/string? X__16348)
                   (clojure.core/let
                    [R__16349
                     (clojure.core/re-matches #"_.*" X__16348)]
                    (if
                     (clojure.core/some? R__16349)
                     (clojure.core/let
                      [?name R__16349]
                      (clojure.core/let
                       [?symbol input__14513_nth_0__]
                       [{:tag :wildcard,
                         :name ?name,
                         :form ?symbol,
                         :symbol ?symbol}]))
                     (state__16739)))
                   (state__16739))))
                (state__16739)))
              (state__16739)))
            (state__16739
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 2)
              (clojure.core/let
               [input__14513_nth_0__ (clojure.core/nth input__14513 0)]
               (if
                (clojure.core/symbol? input__14513_nth_0__)
                (clojure.core/let
                 [X__16353
                  (clojure.core/namespace input__14513_nth_0__)]
                 (clojure.core/let
                  [X__16355 (clojure.core/name input__14513_nth_0__)]
                  (if
                   (clojure.core/string? X__16355)
                   (clojure.core/let
                    [R__16356
                     (clojure.core/re-matches #".+#" X__16355)]
                    (if
                     (clojure.core/some? R__16356)
                     (clojure.core/let
                      [?name R__16356]
                      (clojure.core/let
                       [?symbol input__14513_nth_0__]
                       [{:tag :random-symbol,
                         :name ?name,
                         :form ?symbol,
                         :symbol ?symbol}]))
                     (state__16740)))
                   (state__16740))))
                (state__16740)))
              (state__16740)))
            (state__16740
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 2)
              (clojure.core/let
               [input__14513_nth_0__ (clojure.core/nth input__14513 0)]
               (if
                (clojure.core/symbol? input__14513_nth_0__)
                (clojure.core/let
                 [X__16360
                  (clojure.core/namespace input__14513_nth_0__)]
                 (clojure.core/let
                  [X__16362 (clojure.core/name input__14513_nth_0__)]
                  (if
                   (clojure.core/string? X__16362)
                   (clojure.core/let
                    [R__16363
                     (clojure.core/re-matches #"%.+" X__16362)]
                    (if
                     (clojure.core/some? R__16363)
                     (clojure.core/let
                      [?name R__16363]
                      (clojure.core/let
                       [?symbol input__14513_nth_0__]
                       [{:tag :reference,
                         :name ?name,
                         :symbol ?symbol}]))
                     (state__16741)))
                   (state__16741))))
                (state__16741)))
              (state__16741)))
            (state__16741
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 2)
              (clojure.core/let
               [input__14513_nth_0__ (clojure.core/nth input__14513 0)]
               (if
                (clojure.core/symbol? input__14513_nth_0__)
                (clojure.core/let
                 [X__16367
                  (clojure.core/namespace input__14513_nth_0__)]
                 (clojure.core/let
                  [X__16369 (clojure.core/name input__14513_nth_0__)]
                  (if
                   (clojure.core/string? X__16369)
                   (clojure.core/let
                    [R__16370
                     (clojure.core/re-matches #"\*.+" X__16369)]
                    (if
                     (clojure.core/some? R__16370)
                     (clojure.core/let
                      [?name R__16370]
                      (clojure.core/let
                       [?symbol input__14513_nth_0__]
                       [{:tag :mutable-variable,
                         :name ?name,
                         :symbol ?symbol}]))
                     (state__16742)))
                   (state__16742))))
                (state__16742)))
              (state__16742)))
            (state__16742
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 2)
              (clojure.core/let
               [input__14513_nth_0__ (clojure.core/nth input__14513 0)]
               (if
                (clojure.core/symbol? input__14513_nth_0__)
                (clojure.core/let
                 [X__16374
                  (clojure.core/namespace input__14513_nth_0__)]
                 (clojure.core/let
                  [X__16376 (clojure.core/name input__14513_nth_0__)]
                  (if
                   (clojure.core/string? X__16376)
                   (clojure.core/let
                    [R__16377
                     (clojure.core/re-matches #"\!.+" X__16376)]
                    (if
                     (clojure.core/some? R__16377)
                     (clojure.core/let
                      [?name R__16377]
                      (clojure.core/let
                       [?symbol input__14513_nth_0__]
                       [{:tag :memory-variable,
                         :name ?name,
                         :symbol ?symbol}]))
                     (state__16743)))
                   (state__16743))))
                (state__16743)))
              (state__16743)))
            (state__16743
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 2)
              (clojure.core/let
               [input__14513_nth_0__ (clojure.core/nth input__14513 0)]
               (if
                (clojure.core/symbol? input__14513_nth_0__)
                (clojure.core/let
                 [X__16381
                  (clojure.core/namespace input__14513_nth_0__)]
                 (clojure.core/let
                  [X__16383 (clojure.core/name input__14513_nth_0__)]
                  (if
                   (clojure.core/string? X__16383)
                   (clojure.core/let
                    [R__16384
                     (clojure.core/re-matches #"\?.+" X__16383)]
                    (if
                     (clojure.core/some? R__16384)
                     (clojure.core/let
                      [?name R__16384]
                      (clojure.core/let
                       [?symbol input__14513_nth_0__]
                       [{:tag :logic-variable,
                         :name ?name,
                         :symbol ?symbol}]))
                     (state__16744)))
                   (state__16744))))
                (state__16744)))
              (state__16744)))
            (state__16744
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 2)
              (clojure.core/let
               [input__14513_nth_0__ (clojure.core/nth input__14513 0)]
               (if
                (string? input__14513_nth_0__)
                (clojure.core/let
                 [?x input__14513_nth_0__]
                 [{:tag :literal, :type :string, :form ?x}])
                (state__16745)))
              (state__16745)))
            (state__16745
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 2)
              (clojure.core/let
               [input__14513_nth_0__ (clojure.core/nth input__14513 0)]
               (if
                (char? input__14513_nth_0__)
                (clojure.core/let
                 [?x input__14513_nth_0__]
                 [{:tag :literal, :type :char, :form ?x}])
                (state__16746)))
              (state__16746)))
            (state__16746
             []
             (if
              (clojure.core/= (clojure.core/count input__14513) 2)
              (clojure.core/let
               [input__14513_nth_0__ (clojure.core/nth input__14513 0)]
               (clojure.core/let
                [?x input__14513_nth_0__]
                [{:tag :literal, :form ?x}]))
              (state__16429)))]
           (state__16733))
          (state__16429)))
        (state__16429
         []
         (clojure.core/let
          [?x input__14513]
          [{:tag :mistake, :x ?x}]))]
       (state__16391)))]
    (clojure.core/let
     [R__16747 (C__14591 input__14513)]
     (if
      (meander.runtime.zeta/fail? R__16747)
      (meander.runtime.zeta/fail)
      (clojure.core/let [[R__14702] R__16747] R__14702))))]
  (if (meander.runtime.zeta/fail? R__16748) nil R__16748)))
