(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__196443]
 (let*
  [ret__14429__auto__
   (clojure.core/letfn
    [(CATA__FN__196503
      [input__196443]
      (clojure.core/letfn
       [(state__197602
         []
         (if
          (clojure.core/vector? input__196443)
          (if
           (clojure.core/= (clojure.core/count input__196443) 3)
           (clojure.core/let
            [input__196443_nth_0__
             (clojure.core/nth input__196443 0)
             input__196443_nth_1__
             (clojure.core/nth input__196443 1)
             input__196443_nth_2__
             (clojure.core/nth input__196443 2)]
            (if
             (clojure.core/let
              [x__13309__auto__ input__196443_nth_0__]
              (clojure.core/case
               x__13309__auto__
               (meander.dev.parse.zeta/parse-seq
                meander.dev.parse.zeta/parse-string)
               true
               false))
             (clojure.core/letfn
              [(state__197628
                []
                (if
                 (clojure.core/vector? input__196443_nth_1__)
                 (clojure.core/case
                  input__196443_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__196443_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__16492__auto__
                     (if
                      (meander.runtime.zeta/fail? e__16492__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__16492__auto__)))))
                  (state__197629))
                 (state__197629)))
               (state__197629
                []
                (clojure.core/let
                 [?rule-name input__196443_nth_0__]
                 (if
                  (clojure.core/vector? input__196443_nth_1__)
                  (clojure.core/let
                   [n__196511
                    (clojure.core/count input__196443_nth_1__)
                    m__196512
                    (clojure.core/max 0 (clojure.core/- n__196511 2))
                    input__196443_nth_1___l__
                    (clojure.core/subvec
                     input__196443_nth_1__
                     0
                     (clojure.core/min
                      (clojure.core/count input__196443_nth_1__)
                      m__196512))
                    input__196443_nth_1___r__
                    (clojure.core/subvec
                     input__196443_nth_1__
                     m__196512)]
                   (if
                    (clojure.core/=
                     (clojure.core/count input__196443_nth_1___r__)
                     2)
                    (clojure.core/let
                     [!xs (clojure.core/vec input__196443_nth_1___l__)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__196443_nth_1___r__)
                       2)
                      (clojure.core/let
                       [input__196443_nth_1___r___nth_0__
                        (clojure.core/nth input__196443_nth_1___r__ 0)
                        input__196443_nth_1___r___nth_1__
                        (clojure.core/nth input__196443_nth_1___r__ 1)]
                       (clojure.core/case
                        input__196443_nth_1___r___nth_0__
                        (:meander.zeta/as)
                        (clojure.core/let
                         [?pattern input__196443_nth_1___r___nth_1__]
                         (clojure.core/let
                          [?env input__196443_nth_2__]
                          (try
                           [(clojure.core/let
                             [!xs__counter
                              (meander.runtime.zeta/iterator !xs)]
                             {:tag :as,
                              :pattern
                              (clojure.core/let
                               [CATA_RESULT__15552__auto__
                                (CATA__FN__196503 [?pattern ?env])]
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
                                (CATA__FN__196503
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
                        (state__197603)))
                      (state__197603)))
                    (state__197603)))
                  (state__197603))))]
              (state__197628))
             (state__197603)))
           (state__197603))
          (state__197603)))
        (state__197603
         []
         (clojure.core/letfn
          [(def__196517
            [arg__196552 ?ns]
            (clojure.core/letfn
             [(state__197630
               []
               (clojure.core/let
                [x__196553 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__196553)
                 (clojure.core/let [?env arg__196552] [?env ?ns])
                 (state__197631))))
              (state__197631
               []
               (if
                (clojure.core/map? arg__196552)
                (clojure.core/let
                 [VAL__196554 (.valAt arg__196552 :aliases)]
                 (if
                  (clojure.core/map? VAL__196554)
                  (clojure.core/let
                   [X__196556 (clojure.core/set VAL__196554)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__196556))
                    (clojure.core/loop
                     [search_space__197632
                      (clojure.core/seq X__196556)]
                     (if
                      (clojure.core/seq search_space__197632)
                      (clojure.core/let
                       [elem__196557
                        (clojure.core/first search_space__197632)
                        result__197633
                        (clojure.core/let
                         [elem__196557_nth_0__
                          (clojure.core/nth elem__196557 0)
                          elem__196557_nth_1__
                          (clojure.core/nth elem__196557 1)]
                         (if
                          (clojure.core/symbol? elem__196557_nth_0__)
                          (clojure.core/let
                           [X__196559
                            (clojure.core/name elem__196557_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__196559)
                            (if
                             (clojure.core/symbol?
                              elem__196557_nth_1__)
                             (clojure.core/let
                              [X__196561
                               (clojure.core/name
                                elem__196557_nth_1__)]
                              (clojure.core/case
                               X__196561
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__196552]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197633)
                        (recur
                         (clojure.core/next search_space__197632))
                        result__197633))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197630)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 3)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)
              input__196443_nth_2__
              (clojure.core/nth input__196443 2)]
             (if
              (clojure.core/let
               [x__13309__auto__ input__196443_nth_0__]
               (clojure.core/case
                x__13309__auto__
                (meander.dev.parse.zeta/parse-seq
                 meander.dev.parse.zeta/parse-string)
                true
                false))
              (clojure.core/let
               [?rule-name input__196443_nth_0__]
               (if
                (clojure.core/vector? input__196443_nth_1__)
                (clojure.core/loop
                 [search_space__197635
                  (meander.match.runtime.epsilon/partitions
                   2
                   input__196443_nth_1__)]
                 (if
                  (clojure.core/seq search_space__197635)
                  (clojure.core/let
                   [input__196443_nth_1___parts__
                    (clojure.core/first search_space__197635)
                    result__197636
                    (clojure.core/let
                     [input__196443_nth_1___l__
                      (clojure.core/nth
                       input__196443_nth_1___parts__
                       0)
                      input__196443_nth_1___r__
                      (clojure.core/nth
                       input__196443_nth_1___parts__
                       1)]
                     (clojure.core/let
                      [!init
                       (clojure.core/vec input__196443_nth_1___l__)]
                      (clojure.core/let
                       [input__196443_nth_1___r___l__
                        (clojure.core/subvec
                         input__196443_nth_1___r__
                         0
                         (clojure.core/min
                          (clojure.core/count
                           input__196443_nth_1___r__)
                          2))]
                       (if
                        (clojure.core/=
                         (clojure.core/count
                          input__196443_nth_1___r___l__)
                         2)
                        (clojure.core/let
                         [input__196443_nth_1___r___r__
                          (clojure.core/subvec
                           input__196443_nth_1___r__
                           2)]
                         (clojure.core/let
                          [input__196443_nth_1___r___l___nth_0__
                           (clojure.core/nth
                            input__196443_nth_1___r___l__
                            0)
                           input__196443_nth_1___r___l___nth_1__
                           (clojure.core/nth
                            input__196443_nth_1___r___l__
                            1)]
                          (if
                           (clojure.core/symbol?
                            input__196443_nth_1___r___l___nth_0__)
                           (clojure.core/let
                            [X__196526
                             (clojure.core/namespace
                              input__196443_nth_1___r___l___nth_0__)]
                            (clojure.core/let
                             [?ns X__196526]
                             (clojure.core/let
                              [X__196528
                               (clojure.core/name
                                input__196443_nth_1___r___l___nth_0__)]
                              (if
                               (clojure.core/string? X__196528)
                               (clojure.core/let
                                [ret__196529
                                 (clojure.core/re-matches
                                  #"&(\d+)"
                                  X__196528)]
                                (if
                                 (clojure.core/some? ret__196529)
                                 (if
                                  (clojure.core/vector? ret__196529)
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count ret__196529)
                                    2)
                                   (clojure.core/let
                                    [ret__196529_nth_1__
                                     (clojure.core/nth ret__196529 1)]
                                    (clojure.core/let
                                     [?n ret__196529_nth_1__]
                                     (clojure.core/let
                                      [?pattern
                                       input__196443_nth_1___r___l___nth_1__]
                                      (clojure.core/let
                                       [?rest
                                        input__196443_nth_1___r___r__]
                                       (clojure.core/let
                                        [x__14249__auto__
                                         (def__196517
                                          input__196443_nth_2__
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
                                               (CATA__FN__196503
                                                ['meander.dev.parse.zeta/join-args
                                                 (clojure.core/let
                                                  [CATA_RESULT__15552__auto__
                                                   (CATA__FN__196503
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
                                                   (CATA__FN__196503
                                                    ['meander.dev.parse.zeta/join-args
                                                     {:tag :slice,
                                                      :size
                                                      (Integer. ?n),
                                                      :pattern
                                                      (clojure.core/let
                                                       [CATA_RESULT__15552__auto__
                                                        (CATA__FN__196503
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
                                                       (CATA__FN__196503
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
                    (meander.runtime.zeta/fail? result__197636)
                    (recur (clojure.core/next search_space__197635))
                    result__197636))
                  (state__197604)))
                (state__197604)))
              (state__197604)))
            (state__197604))
           (state__197604))))
        (state__197604
         []
         (clojure.core/letfn
          [(def__196574
            [arg__196606 ?ns]
            (clojure.core/letfn
             [(state__197638
               []
               (clojure.core/let
                [x__196607 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__196607)
                 (clojure.core/let [?env arg__196606] [?env ?ns])
                 (state__197639))))
              (state__197639
               []
               (if
                (clojure.core/map? arg__196606)
                (clojure.core/let
                 [VAL__196608 (.valAt arg__196606 :aliases)]
                 (if
                  (clojure.core/map? VAL__196608)
                  (clojure.core/let
                   [X__196610 (clojure.core/set VAL__196608)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__196610))
                    (clojure.core/loop
                     [search_space__197640
                      (clojure.core/seq X__196610)]
                     (if
                      (clojure.core/seq search_space__197640)
                      (clojure.core/let
                       [elem__196611
                        (clojure.core/first search_space__197640)
                        result__197641
                        (clojure.core/let
                         [elem__196611_nth_0__
                          (clojure.core/nth elem__196611 0)
                          elem__196611_nth_1__
                          (clojure.core/nth elem__196611 1)]
                         (if
                          (clojure.core/symbol? elem__196611_nth_0__)
                          (clojure.core/let
                           [X__196613
                            (clojure.core/name elem__196611_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__196613)
                            (if
                             (clojure.core/symbol?
                              elem__196611_nth_1__)
                             (clojure.core/let
                              [X__196615
                               (clojure.core/name
                                elem__196611_nth_1__)]
                              (clojure.core/case
                               X__196615
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__196606]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197641)
                        (recur
                         (clojure.core/next search_space__197640))
                        result__197641))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197638)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 3)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)
              input__196443_nth_2__
              (clojure.core/nth input__196443 2)]
             (if
              (clojure.core/=
               input__196443_nth_0__
               'meander.dev.parse.zeta/parse-seq)
              (if
               (clojure.core/vector? input__196443_nth_1__)
               (clojure.core/loop
                [search_space__197643
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__196443_nth_1__)]
                (if
                 (clojure.core/seq search_space__197643)
                 (clojure.core/let
                  [input__196443_nth_1___parts__
                   (clojure.core/first search_space__197643)
                   result__197644
                   (clojure.core/let
                    [input__196443_nth_1___l__
                     (clojure.core/nth input__196443_nth_1___parts__ 0)
                     input__196443_nth_1___r__
                     (clojure.core/nth
                      input__196443_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__196443_nth_1___l__)]
                     (clojure.core/let
                      [input__196443_nth_1___r___l__
                       (clojure.core/subvec
                        input__196443_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__196443_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__196443_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__196443_nth_1___r___r__
                         (clojure.core/subvec
                          input__196443_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__196443_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__196443_nth_1___r___l__
                           0)
                          input__196443_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__196443_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__196443_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__196583
                            (clojure.core/namespace
                             input__196443_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__196583]
                            (clojure.core/let
                             [X__196585
                              (clojure.core/name
                               input__196443_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__196585)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__196585)
                               (clojure.core/let
                                [?pattern
                                 input__196443_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__196443_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__14249__auto__
                                   (def__196574
                                    input__196443_nth_2__
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
                                         (CATA__FN__196503
                                          ['meander.dev.parse.zeta/join-args
                                           (clojure.core/let
                                            [CATA_RESULT__15552__auto__
                                             (CATA__FN__196503
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
                                             (CATA__FN__196503
                                              ['meander.dev.parse.zeta/join-args
                                               (clojure.core/let
                                                [CATA_RESULT__15552__auto__
                                                 (CATA__FN__196503
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
                                                 (CATA__FN__196503
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
                   (meander.runtime.zeta/fail? result__197644)
                   (recur (clojure.core/next search_space__197643))
                   result__197644))
                 (state__197605)))
               (state__197605))
              (state__197605)))
            (state__197605))
           (state__197605))))
        (state__197605
         []
         (clojure.core/letfn
          [(def__196628
            [arg__196660 ?ns]
            (clojure.core/letfn
             [(state__197646
               []
               (clojure.core/let
                [x__196661 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__196661)
                 (clojure.core/let [?env arg__196660] [?env ?ns])
                 (state__197647))))
              (state__197647
               []
               (if
                (clojure.core/map? arg__196660)
                (clojure.core/let
                 [VAL__196662 (.valAt arg__196660 :aliases)]
                 (if
                  (clojure.core/map? VAL__196662)
                  (clojure.core/let
                   [X__196664 (clojure.core/set VAL__196662)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__196664))
                    (clojure.core/loop
                     [search_space__197648
                      (clojure.core/seq X__196664)]
                     (if
                      (clojure.core/seq search_space__197648)
                      (clojure.core/let
                       [elem__196665
                        (clojure.core/first search_space__197648)
                        result__197649
                        (clojure.core/let
                         [elem__196665_nth_0__
                          (clojure.core/nth elem__196665 0)
                          elem__196665_nth_1__
                          (clojure.core/nth elem__196665 1)]
                         (if
                          (clojure.core/symbol? elem__196665_nth_0__)
                          (clojure.core/let
                           [X__196667
                            (clojure.core/name elem__196665_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__196667)
                            (if
                             (clojure.core/symbol?
                              elem__196665_nth_1__)
                             (clojure.core/let
                              [X__196669
                               (clojure.core/name
                                elem__196665_nth_1__)]
                              (clojure.core/case
                               X__196669
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__196660]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197649)
                        (recur
                         (clojure.core/next search_space__197648))
                        result__197649))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197646)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 3)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)
              input__196443_nth_2__
              (clojure.core/nth input__196443 2)]
             (if
              (clojure.core/=
               input__196443_nth_0__
               'meander.dev.parse.zeta/parse-string)
              (if
               (clojure.core/vector? input__196443_nth_1__)
               (clojure.core/loop
                [search_space__197651
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__196443_nth_1__)]
                (if
                 (clojure.core/seq search_space__197651)
                 (clojure.core/let
                  [input__196443_nth_1___parts__
                   (clojure.core/first search_space__197651)
                   result__197652
                   (clojure.core/let
                    [input__196443_nth_1___l__
                     (clojure.core/nth input__196443_nth_1___parts__ 0)
                     input__196443_nth_1___r__
                     (clojure.core/nth
                      input__196443_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__196443_nth_1___l__)]
                     (clojure.core/let
                      [input__196443_nth_1___r___l__
                       (clojure.core/subvec
                        input__196443_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__196443_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__196443_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__196443_nth_1___r___r__
                         (clojure.core/subvec
                          input__196443_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__196443_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__196443_nth_1___r___l__
                           0)
                          input__196443_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__196443_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__196443_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__196637
                            (clojure.core/namespace
                             input__196443_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__196637]
                            (clojure.core/let
                             [X__196639
                              (clojure.core/name
                               input__196443_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__196639)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__196639)
                               (clojure.core/let
                                [?pattern
                                 input__196443_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__196443_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__14249__auto__
                                   (def__196628
                                    input__196443_nth_2__
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
                                         (CATA__FN__196503
                                          ['meander.dev.parse.zeta/string-join-args
                                           (clojure.core/let
                                            [CATA_RESULT__15552__auto__
                                             (CATA__FN__196503
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
                                             (CATA__FN__196503
                                              ['meander.dev.parse.zeta/string-join-args
                                               (clojure.core/let
                                                [CATA_RESULT__15552__auto__
                                                 (CATA__FN__196503
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
                                                 (CATA__FN__196503
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
                   (meander.runtime.zeta/fail? result__197652)
                   (recur (clojure.core/next search_space__197651))
                   result__197652))
                 (state__197606)))
               (state__197606))
              (state__197606)))
            (state__197606))
           (state__197606))))
        (state__197606
         []
         (if
          (clojure.core/vector? input__196443)
          (if
           (clojure.core/= (clojure.core/count input__196443) 3)
           (clojure.core/let
            [input__196443_nth_0__
             (clojure.core/nth input__196443 0)
             input__196443_nth_1__
             (clojure.core/nth input__196443 1)
             input__196443_nth_2__
             (clojure.core/nth input__196443 2)]
            (clojure.core/letfn
             [(state__197654
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/parse-seq)
                (if
                 (clojure.core/vector? input__196443_nth_1__)
                 (clojure.core/let
                  [n__196690
                   (clojure.core/count input__196443_nth_1__)
                   m__196691
                   (clojure.core/max 0 (clojure.core/- n__196690 2))
                   input__196443_nth_1___l__
                   (clojure.core/subvec
                    input__196443_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__196443_nth_1__)
                     m__196691))
                   input__196443_nth_1___r__
                   (clojure.core/subvec
                    input__196443_nth_1__
                    m__196691)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__196443_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__196443_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__196443_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__196443_nth_1___r___nth_0__
                       (clojure.core/nth input__196443_nth_1___r__ 0)
                       input__196443_nth_1___r___nth_1__
                       (clojure.core/nth input__196443_nth_1___r__ 1)]
                      (if
                       (clojure.core/symbol?
                        input__196443_nth_1___r___nth_0__)
                       (clojure.core/let
                        [X__196695
                         (clojure.core/namespace
                          input__196443_nth_1___r___nth_0__)]
                        (clojure.core/let
                         [?ns X__196695]
                         (clojure.core/let
                          [X__196697
                           (clojure.core/name
                            input__196443_nth_1___r___nth_0__)]
                          (if
                           (clojure.core/string? X__196697)
                           (clojure.core/let
                            [ret__196698
                             (clojure.core/re-matches
                              #"&.*"
                              X__196697)]
                            (if
                             (clojure.core/some? ret__196698)
                             (clojure.core/let
                              [?name ret__196698]
                              (clojure.core/let
                               [?pattern
                                input__196443_nth_1___r___nth_1__]
                               (if
                                (clojure.core/map?
                                 input__196443_nth_2__)
                                (clojure.core/let
                                 [VAL__196682
                                  (.valAt
                                   input__196443_nth_2__
                                   :aliases)]
                                 (if
                                  (clojure.core/map? VAL__196682)
                                  (clojure.core/let
                                   [X__196684
                                    (clojure.core/set VAL__196682)]
                                   (if
                                    (clojure.core/<=
                                     1
                                     (clojure.core/count X__196684))
                                    (clojure.core/loop
                                     [search_space__197659
                                      (clojure.core/seq X__196684)]
                                     (if
                                      (clojure.core/seq
                                       search_space__197659)
                                      (clojure.core/let
                                       [elem__196685
                                        (clojure.core/first
                                         search_space__197659)
                                        result__197660
                                        (clojure.core/let
                                         [elem__196685_nth_0__
                                          (clojure.core/nth
                                           elem__196685
                                           0)
                                          elem__196685_nth_1__
                                          (clojure.core/nth
                                           elem__196685
                                           1)]
                                         (if
                                          (clojure.core/symbol?
                                           elem__196685_nth_0__)
                                          (clojure.core/let
                                           [X__196687
                                            (clojure.core/name
                                             elem__196685_nth_0__)]
                                           (if
                                            (clojure.core/=
                                             ?ns
                                             X__196687)
                                            (if
                                             (clojure.core/symbol?
                                              elem__196685_nth_1__)
                                             (clojure.core/let
                                              [X__196689
                                               (clojure.core/name
                                                elem__196685_nth_1__)]
                                              (clojure.core/case
                                               X__196689
                                               ("meander.zeta")
                                               (clojure.core/let
                                                [?env
                                                 input__196443_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15552__auto__
                                                     (CATA__FN__196503
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
                                         result__197660)
                                        (recur
                                         (clojure.core/next
                                          search_space__197659))
                                        result__197660))
                                      (state__197655)))
                                    (state__197655)))
                                  (state__197655)))
                                (state__197655))))
                             (state__197655)))
                           (state__197655)))))
                       (state__197655)))
                     (state__197655)))
                   (state__197655)))
                 (state__197655))
                (state__197655)))
              (state__197655
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__196443_nth_1__)
                 (clojure.core/let
                  [n__196709
                   (clojure.core/count input__196443_nth_1__)
                   m__196710
                   (clojure.core/max 0 (clojure.core/- n__196709 2))
                   input__196443_nth_1___l__
                   (clojure.core/subvec
                    input__196443_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__196443_nth_1__)
                     m__196710))
                   input__196443_nth_1___r__
                   (clojure.core/subvec
                    input__196443_nth_1__
                    m__196710)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__196443_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__196443_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__196443_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__196443_nth_1___r___nth_0__
                       (clojure.core/nth input__196443_nth_1___r__ 0)
                       input__196443_nth_1___r___nth_1__
                       (clojure.core/nth input__196443_nth_1___r__ 1)]
                      (if
                       (clojure.core/symbol?
                        input__196443_nth_1___r___nth_0__)
                       (clojure.core/let
                        [X__196714
                         (clojure.core/namespace
                          input__196443_nth_1___r___nth_0__)]
                        (clojure.core/let
                         [?ns X__196714]
                         (clojure.core/let
                          [X__196716
                           (clojure.core/name
                            input__196443_nth_1___r___nth_0__)]
                          (if
                           (clojure.core/string? X__196716)
                           (clojure.core/let
                            [ret__196717
                             (clojure.core/re-matches
                              #"&.*"
                              X__196716)]
                            (if
                             (clojure.core/some? ret__196717)
                             (clojure.core/let
                              [?name ret__196717]
                              (clojure.core/let
                               [?pattern
                                input__196443_nth_1___r___nth_1__]
                               (if
                                (clojure.core/map?
                                 input__196443_nth_2__)
                                (clojure.core/let
                                 [VAL__196701
                                  (.valAt
                                   input__196443_nth_2__
                                   :aliases)]
                                 (if
                                  (clojure.core/map? VAL__196701)
                                  (clojure.core/let
                                   [X__196703
                                    (clojure.core/set VAL__196701)]
                                   (if
                                    (clojure.core/<=
                                     1
                                     (clojure.core/count X__196703))
                                    (clojure.core/loop
                                     [search_space__197662
                                      (clojure.core/seq X__196703)]
                                     (if
                                      (clojure.core/seq
                                       search_space__197662)
                                      (clojure.core/let
                                       [elem__196704
                                        (clojure.core/first
                                         search_space__197662)
                                        result__197663
                                        (clojure.core/let
                                         [elem__196704_nth_0__
                                          (clojure.core/nth
                                           elem__196704
                                           0)
                                          elem__196704_nth_1__
                                          (clojure.core/nth
                                           elem__196704
                                           1)]
                                         (if
                                          (clojure.core/symbol?
                                           elem__196704_nth_0__)
                                          (clojure.core/let
                                           [X__196706
                                            (clojure.core/name
                                             elem__196704_nth_0__)]
                                           (if
                                            (clojure.core/=
                                             ?ns
                                             X__196706)
                                            (if
                                             (clojure.core/symbol?
                                              elem__196704_nth_1__)
                                             (clojure.core/let
                                              [X__196708
                                               (clojure.core/name
                                                elem__196704_nth_1__)]
                                              (clojure.core/case
                                               X__196708
                                               ("meander.zeta")
                                               (clojure.core/let
                                                [?env
                                                 input__196443_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15552__auto__
                                                     (CATA__FN__196503
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
                                         result__197663)
                                        (recur
                                         (clojure.core/next
                                          search_space__197662))
                                        result__197663))
                                      (state__197656)))
                                    (state__197656)))
                                  (state__197656)))
                                (state__197656))))
                             (state__197656)))
                           (state__197656)))))
                       (state__197656)))
                     (state__197656)))
                   (state__197656)))
                 (state__197656))
                (state__197656)))
              (state__197656
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/parse-seq)
                (if
                 (clojure.core/vector? input__196443_nth_1__)
                 (clojure.core/loop
                  [search_space__197665
                   (meander.match.runtime.epsilon/partitions
                    2
                    input__196443_nth_1__)]
                  (if
                   (clojure.core/seq search_space__197665)
                   (clojure.core/let
                    [input__196443_nth_1___parts__
                     (clojure.core/first search_space__197665)
                     result__197666
                     (clojure.core/let
                      [input__196443_nth_1___l__
                       (clojure.core/nth
                        input__196443_nth_1___parts__
                        0)
                       input__196443_nth_1___r__
                       (clojure.core/nth
                        input__196443_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__196443_nth_1___l__)]
                       (clojure.core/let
                        [input__196443_nth_1___r___l__
                         (clojure.core/subvec
                          input__196443_nth_1___r__
                          0
                          (clojure.core/min
                           (clojure.core/count
                            input__196443_nth_1___r__)
                           1))]
                        (if
                         (clojure.core/=
                          (clojure.core/count
                           input__196443_nth_1___r___l__)
                          1)
                         (clojure.core/let
                          [input__196443_nth_1___r___r__
                           (clojure.core/subvec
                            input__196443_nth_1___r__
                            1)]
                          (if
                           (clojure.core/=
                            input__196443_nth_1___r___l__
                            ['.])
                           (clojure.core/let
                            [?rest input__196443_nth_1___r___r__]
                            (clojure.core/let
                             [?env input__196443_nth_2__]
                             (try
                              [(clojure.core/let
                                [!xs__counter
                                 (meander.runtime.zeta/iterator !xs)]
                                (clojure.core/let
                                 [CATA_RESULT__15552__auto__
                                  (CATA__FN__196503
                                   ['meander.dev.parse.zeta/join-args
                                    (clojure.core/let
                                     [CATA_RESULT__15552__auto__
                                      (CATA__FN__196503
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
                                      (CATA__FN__196503
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
                     (meander.runtime.zeta/fail? result__197666)
                     (recur (clojure.core/next search_space__197665))
                     result__197666))
                   (state__197657)))
                 (state__197657))
                (state__197657)))
              (state__197657
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__196443_nth_1__)
                 (clojure.core/loop
                  [search_space__197668
                   (meander.match.runtime.epsilon/partitions
                    2
                    input__196443_nth_1__)]
                  (if
                   (clojure.core/seq search_space__197668)
                   (clojure.core/let
                    [input__196443_nth_1___parts__
                     (clojure.core/first search_space__197668)
                     result__197669
                     (clojure.core/let
                      [input__196443_nth_1___l__
                       (clojure.core/nth
                        input__196443_nth_1___parts__
                        0)
                       input__196443_nth_1___r__
                       (clojure.core/nth
                        input__196443_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__196443_nth_1___l__)]
                       (clojure.core/let
                        [input__196443_nth_1___r___l__
                         (clojure.core/subvec
                          input__196443_nth_1___r__
                          0
                          (clojure.core/min
                           (clojure.core/count
                            input__196443_nth_1___r__)
                           1))]
                        (if
                         (clojure.core/=
                          (clojure.core/count
                           input__196443_nth_1___r___l__)
                          1)
                         (clojure.core/let
                          [input__196443_nth_1___r___r__
                           (clojure.core/subvec
                            input__196443_nth_1___r__
                            1)]
                          (if
                           (clojure.core/=
                            input__196443_nth_1___r___l__
                            ['.])
                           (clojure.core/let
                            [?rest input__196443_nth_1___r___r__]
                            (clojure.core/let
                             [?env input__196443_nth_2__]
                             (try
                              [(clojure.core/let
                                [!xs__counter
                                 (meander.runtime.zeta/iterator !xs)]
                                (clojure.core/let
                                 [CATA_RESULT__15552__auto__
                                  (CATA__FN__196503
                                   ['meander.dev.parse.zeta/string-join-args
                                    (clojure.core/let
                                     [CATA_RESULT__15552__auto__
                                      (CATA__FN__196503
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
                                      (CATA__FN__196503
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
                     (meander.runtime.zeta/fail? result__197669)
                     (recur (clojure.core/next search_space__197668))
                     result__197669))
                   (state__197658)))
                 (state__197658))
                (state__197658)))
              (state__197658
               []
               (if
                (clojure.core/let
                 [x__13309__auto__ input__196443_nth_0__]
                 (clojure.core/case
                  x__13309__auto__
                  (meander.dev.parse.zeta/parse-seq
                   meander.dev.parse.zeta/parse-string)
                  true
                  false))
                (clojure.core/let
                 [?rule-name input__196443_nth_0__]
                 (if
                  (clojure.core/vector? input__196443_nth_1__)
                  (clojure.core/let
                   [input__196443_nth_1___l__
                    (clojure.core/subvec
                     input__196443_nth_1__
                     0
                     (clojure.core/min
                      (clojure.core/count input__196443_nth_1__)
                      1))]
                   (if
                    (clojure.core/=
                     (clojure.core/count input__196443_nth_1___l__)
                     1)
                    (clojure.core/let
                     [input__196443_nth_1___r__
                      (clojure.core/subvec input__196443_nth_1__ 1)]
                     (if
                      (clojure.core/= input__196443_nth_1___l__ ['...])
                      (clojure.core/let
                       [?rest input__196443_nth_1___r__]
                       (clojure.core/let
                        [?env input__196443_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__15552__auto__
                            (CATA__FN__196503 [?rule-name ?rest ?env])]
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
                      (state__197607)))
                    (state__197607)))
                  (state__197607)))
                (state__197607)))]
             (state__197654)))
           (state__197607))
          (state__197607)))
        (state__197607
         []
         (clojure.core/letfn
          [(def__196734
            [arg__196751]
            (clojure.core/letfn
             [(state__197671
               []
               (if
                (clojure.core/=
                 arg__196751
                 'meander.dev.parse.zeta/parse-string)
                (clojure.core/let
                 [?rule-name arg__196751]
                 (clojure.core/let
                  [x__196752 'meander.dev.parse.zeta/string-star-args]
                  (clojure.core/let
                   [?star-name x__196752]
                   [?star-name ?rule-name])))
                (state__197672)))
              (state__197672
               []
               (if
                (clojure.core/=
                 arg__196751
                 'meander.dev.parse.zeta/parse-seq)
                (clojure.core/let
                 [?rule-name arg__196751]
                 (clojure.core/let
                  [x__196753 'meander.dev.parse.zeta/star-args]
                  (clojure.core/let
                   [?star-name x__196753]
                   [?star-name ?rule-name])))
                (meander.runtime.zeta/fail)))]
             (state__197671)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 3)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)
              input__196443_nth_2__
              (clojure.core/nth input__196443 2)]
             (clojure.core/let
              [x__14249__auto__ (def__196734 input__196443_nth_0__)]
              (if
               (meander.runtime.zeta/fail? x__14249__auto__)
               (state__197608)
               (clojure.core/let
                [[?star-name ?rule-name] x__14249__auto__]
                (if
                 (clojure.core/vector? input__196443_nth_1__)
                 (clojure.core/loop
                  [search_space__197673
                   (meander.match.runtime.epsilon/partitions
                    2
                    input__196443_nth_1__)]
                  (if
                   (clojure.core/seq search_space__197673)
                   (clojure.core/let
                    [input__196443_nth_1___parts__
                     (clojure.core/first search_space__197673)
                     result__197674
                     (clojure.core/let
                      [input__196443_nth_1___l__
                       (clojure.core/nth
                        input__196443_nth_1___parts__
                        0)
                       input__196443_nth_1___r__
                       (clojure.core/nth
                        input__196443_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs []]
                       (clojure.core/let
                        [ret__14413__auto__
                         (meander.runtime.zeta/epsilon-run-star-1
                          input__196443_nth_1___l__
                          [!xs]
                          (clojure.core/fn
                           [[!xs] input__196744]
                           (clojure.core/let
                            [input__196744_nth_0__
                             (clojure.core/nth input__196744 0)]
                            (clojure.core/letfn
                             [(save__196745
                               []
                               (meander.runtime.zeta/fail))
                              (f__197677
                               []
                               (clojure.core/let
                                [!xs
                                 (clojure.core/conj
                                  !xs
                                  input__196744_nth_0__)]
                                [!xs]))]
                             (if
                              (clojure.core/symbol?
                               input__196744_nth_0__)
                              (clojure.core/let
                               [X__196747
                                (clojure.core/namespace
                                 input__196744_nth_0__)]
                               (clojure.core/case
                                X__196747
                                (nil)
                                (clojure.core/let
                                 [X__196749
                                  (clojure.core/name
                                   input__196744_nth_0__)]
                                 (if
                                  (clojure.core/string? X__196749)
                                  (if
                                   (clojure.core/re-matches
                                    #"\.\.(?:\.|\d+)"
                                    X__196749)
                                   (save__196745)
                                   (f__197677))
                                  (f__197677)))
                                (f__197677)))
                              (f__197677)))))
                          (clojure.core/fn
                           [[!xs]]
                           (clojure.core/let
                            [input__196443_nth_1___r___l__
                             (clojure.core/subvec
                              input__196443_nth_1___r__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__196443_nth_1___r__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__196443_nth_1___r___l__)
                              1)
                             (clojure.core/let
                              [input__196443_nth_1___r___r__
                               (clojure.core/subvec
                                input__196443_nth_1___r__
                                1)]
                              (if
                               (clojure.core/=
                                input__196443_nth_1___r___l__
                                ['...])
                               (clojure.core/let
                                [?rest input__196443_nth_1___r___r__]
                                (clojure.core/let
                                 [?env input__196443_nth_2__]
                                 (try
                                  [(clojure.core/let
                                    [!xs__counter
                                     (meander.runtime.zeta/iterator
                                      !xs)]
                                    (clojure.core/let
                                     [CATA_RESULT__15552__auto__
                                      (CATA__FN__196503
                                       [?star-name
                                        (clojure.core/let
                                         [CATA_RESULT__15552__auto__
                                          (CATA__FN__196503
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
                                          (CATA__FN__196503
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
                     (meander.runtime.zeta/fail? result__197674)
                     (recur (clojure.core/next search_space__197673))
                     result__197674))
                   (state__197608)))
                 (state__197608))))))
            (state__197608))
           (state__197608))))
        (state__197608
         []
         (if
          (clojure.core/vector? input__196443)
          (if
           (clojure.core/= (clojure.core/count input__196443) 3)
           (clojure.core/let
            [input__196443_nth_0__
             (clojure.core/nth input__196443 0)
             input__196443_nth_1__
             (clojure.core/nth input__196443 1)
             input__196443_nth_2__
             (clojure.core/nth input__196443 2)]
            (clojure.core/letfn
             [(state__197678
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/star-args)
                (if
                 (clojure.core/map? input__196443_nth_1__)
                 (clojure.core/let
                  [VAL__196765 (.valAt input__196443_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__196765
                   (:cat)
                   (clojure.core/let
                    [VAL__196766
                     (.valAt input__196443_nth_1__ :sequence)]
                    (if
                     (clojure.core/vector? VAL__196766)
                     (if
                      (clojure.core/=
                       (clojure.core/count VAL__196766)
                       1)
                      (clojure.core/let
                       [VAL__196766_nth_0__
                        (clojure.core/nth VAL__196766 0)]
                       (if
                        (clojure.core/map? VAL__196766_nth_0__)
                        (clojure.core/let
                         [VAL__196771
                          (.valAt VAL__196766_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__196771
                          (:memory-variable)
                          (clojure.core/let
                           [?memory-variable VAL__196766_nth_0__]
                           (clojure.core/let
                            [VAL__196767
                             (.valAt input__196443_nth_1__ :next)]
                            (if
                             (clojure.core/map? VAL__196767)
                             (clojure.core/let
                              [VAL__196768 (.valAt VAL__196767 :tag)]
                              (clojure.core/case
                               VAL__196768
                               (:empty)
                               (clojure.core/let
                                [?next input__196443_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [CATA_RESULT__15552__auto__
                                    (CATA__FN__196503
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
                               (state__197679)))
                             (state__197679))))
                          (state__197679)))
                        (state__197679)))
                      (state__197679))
                     (state__197679)))
                   (state__197679)))
                 (state__197679))
                (state__197679)))
              (state__197679
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/star-args)
                (clojure.core/let
                 [?pattern input__196443_nth_1__]
                 (clojure.core/let
                  [?next input__196443_nth_2__]
                  (try
                   [{:tag :star, :pattern ?pattern, :next ?next}]
                   (catch
                    java.lang.Exception
                    e__16492__auto__
                    (if
                     (meander.runtime.zeta/fail? e__16492__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__16492__auto__))))))
                (state__197680)))
              (state__197680
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/string-star-args)
                (if
                 (clojure.core/map? input__196443_nth_1__)
                 (clojure.core/let
                  [VAL__196776 (.valAt input__196443_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__196776
                   (:string-cat)
                   (clojure.core/let
                    [VAL__196777
                     (.valAt input__196443_nth_1__ :sequence)]
                    (if
                     (clojure.core/vector? VAL__196777)
                     (if
                      (clojure.core/=
                       (clojure.core/count VAL__196777)
                       1)
                      (clojure.core/let
                       [VAL__196777_nth_0__
                        (clojure.core/nth VAL__196777 0)]
                       (if
                        (clojure.core/map? VAL__196777_nth_0__)
                        (clojure.core/let
                         [VAL__196782
                          (.valAt VAL__196777_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__196782
                          (:memory-variable)
                          (clojure.core/let
                           [?memory-variable VAL__196777_nth_0__]
                           (clojure.core/let
                            [VAL__196778
                             (.valAt input__196443_nth_1__ :next)]
                            (if
                             (clojure.core/map? VAL__196778)
                             (clojure.core/let
                              [VAL__196779 (.valAt VAL__196778 :tag)]
                              (clojure.core/case
                               VAL__196779
                               (:empty)
                               (clojure.core/let
                                [?next input__196443_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [CATA_RESULT__15552__auto__
                                    (CATA__FN__196503
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
                               (state__197681)))
                             (state__197681))))
                          (state__197681)))
                        (state__197681)))
                      (state__197681))
                     (state__197681)))
                   (state__197681)))
                 (state__197681))
                (state__197681)))
              (state__197681
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/string-star-args)
                (clojure.core/let
                 [?pattern input__196443_nth_1__]
                 (clojure.core/let
                  [?next input__196443_nth_2__]
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
                (state__197682)))
              (state__197682
               []
               (if
                (clojure.core/let
                 [x__13309__auto__ input__196443_nth_0__]
                 (clojure.core/case
                  x__13309__auto__
                  (meander.dev.parse.zeta/parse-seq
                   meander.dev.parse.zeta/parse-string)
                  true
                  false))
                (clojure.core/letfn
                 [(state__197703
                   []
                   (if
                    (clojure.core/vector? input__196443_nth_1__)
                    (clojure.core/let
                     [input__196443_nth_1___l__
                      (clojure.core/subvec
                       input__196443_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__196443_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__196443_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__196443_nth_1___r__
                        (clojure.core/subvec input__196443_nth_1__ 1)]
                       (clojure.core/let
                        [input__196443_nth_1___l___nth_0__
                         (clojure.core/nth
                          input__196443_nth_1___l__
                          0)]
                        (if
                         (clojure.core/symbol?
                          input__196443_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__196790
                           (clojure.core/namespace
                            input__196443_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__196790
                           (nil)
                           (clojure.core/let
                            [X__196792
                             (clojure.core/name
                              input__196443_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__196792)
                             (clojure.core/let
                              [ret__196793
                               (clojure.core/re-matches
                                #"\.\.(\d+)"
                                X__196792)]
                              (if
                               (clojure.core/some? ret__196793)
                               (if
                                (clojure.core/vector? ret__196793)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__196793)
                                  2)
                                 (clojure.core/let
                                  [ret__196793_nth_1__
                                   (clojure.core/nth ret__196793 1)]
                                  (clojure.core/let
                                   [?n ret__196793_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__196443_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__196443_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__196443_nth_2__]
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
                                 (state__197704))
                                (state__197704))
                               (state__197704)))
                             (state__197704)))
                           (state__197704)))
                         (state__197704))))
                      (state__197704)))
                    (state__197704)))
                  (state__197704
                   []
                   (clojure.core/let
                    [?rule-name input__196443_nth_0__]
                    (if
                     (clojure.core/vector? input__196443_nth_1__)
                     (clojure.core/loop
                      [search_space__197709
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__196443_nth_1__)]
                      (if
                       (clojure.core/seq search_space__197709)
                       (clojure.core/let
                        [input__196443_nth_1___parts__
                         (clojure.core/first search_space__197709)
                         result__197710
                         (clojure.core/let
                          [input__196443_nth_1___l__
                           (clojure.core/nth
                            input__196443_nth_1___parts__
                            0)
                           input__196443_nth_1___r__
                           (clojure.core/nth
                            input__196443_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__14413__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__196443_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__196809]
                               (clojure.core/let
                                [input__196809_nth_0__
                                 (clojure.core/nth input__196809 0)]
                                (clojure.core/letfn
                                 [(save__196810
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__197713
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__196809_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__196809_nth_0__)
                                  (clojure.core/let
                                   [X__196812
                                    (clojure.core/namespace
                                     input__196809_nth_0__)]
                                   (clojure.core/case
                                    X__196812
                                    (nil)
                                    (clojure.core/let
                                     [X__196814
                                      (clojure.core/name
                                       input__196809_nth_0__)]
                                     (if
                                      (clojure.core/string? X__196814)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__196814)
                                       (save__196810)
                                       (f__197713))
                                      (f__197713)))
                                    (f__197713)))
                                  (f__197713)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__196443_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__196443_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__196443_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__196443_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__196443_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__196443_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__196443_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__196443_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__196443_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__196803
                                      (clojure.core/namespace
                                       input__196443_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__196803
                                      (nil)
                                      (clojure.core/let
                                       [X__196805
                                        (clojure.core/name
                                         input__196443_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string?
                                         X__196805)
                                        (clojure.core/let
                                         [ret__196806
                                          (clojure.core/re-matches
                                           #"\.\.(\d+)"
                                           X__196805)]
                                         (if
                                          (clojure.core/some?
                                           ret__196806)
                                          (if
                                           (clojure.core/vector?
                                            ret__196806)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__196806)
                                             2)
                                            (clojure.core/let
                                             [ret__196806_nth_1__
                                              (clojure.core/nth
                                               ret__196806
                                               1)]
                                             (clojure.core/let
                                              [?n ret__196806_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__196443_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__196443_nth_2__]
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
                                                      (CATA__FN__196503
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
                                                      (CATA__FN__196503
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
                         (meander.runtime.zeta/fail? result__197710)
                         (recur
                          (clojure.core/next search_space__197709))
                         result__197710))
                       (state__197705)))
                     (state__197705))))
                  (state__197705
                   []
                   (if
                    (clojure.core/vector? input__196443_nth_1__)
                    (clojure.core/let
                     [input__196443_nth_1___l__
                      (clojure.core/subvec
                       input__196443_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__196443_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__196443_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__196443_nth_1___r__
                        (clojure.core/subvec input__196443_nth_1__ 1)]
                       (clojure.core/let
                        [input__196443_nth_1___l___nth_0__
                         (clojure.core/nth
                          input__196443_nth_1___l__
                          0)]
                        (if
                         (clojure.core/symbol?
                          input__196443_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__196821
                           (clojure.core/namespace
                            input__196443_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__196821
                           (nil)
                           (clojure.core/let
                            [X__196823
                             (clojure.core/name
                              input__196443_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__196823)
                             (clojure.core/let
                              [ret__196824
                               (clojure.core/re-matches
                                #"\.\.(\?.+)"
                                X__196823)]
                              (if
                               (clojure.core/some? ret__196824)
                               (if
                                (clojure.core/vector? ret__196824)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__196824)
                                  2)
                                 (clojure.core/let
                                  [ret__196824_nth_1__
                                   (clojure.core/nth ret__196824 1)]
                                  (clojure.core/let
                                   [?n ret__196824_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__196443_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__196443_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__196443_nth_2__]
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
                                 (state__197706))
                                (state__197706))
                               (state__197706)))
                             (state__197706)))
                           (state__197706)))
                         (state__197706))))
                      (state__197706)))
                    (state__197706)))
                  (state__197706
                   []
                   (clojure.core/let
                    [?rule-name input__196443_nth_0__]
                    (if
                     (clojure.core/vector? input__196443_nth_1__)
                     (clojure.core/loop
                      [search_space__197714
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__196443_nth_1__)]
                      (if
                       (clojure.core/seq search_space__197714)
                       (clojure.core/let
                        [input__196443_nth_1___parts__
                         (clojure.core/first search_space__197714)
                         result__197715
                         (clojure.core/let
                          [input__196443_nth_1___l__
                           (clojure.core/nth
                            input__196443_nth_1___parts__
                            0)
                           input__196443_nth_1___r__
                           (clojure.core/nth
                            input__196443_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__14413__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__196443_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__196840]
                               (clojure.core/let
                                [input__196840_nth_0__
                                 (clojure.core/nth input__196840 0)]
                                (clojure.core/letfn
                                 [(save__196841
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__197718
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__196840_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__196840_nth_0__)
                                  (clojure.core/let
                                   [X__196843
                                    (clojure.core/namespace
                                     input__196840_nth_0__)]
                                   (clojure.core/case
                                    X__196843
                                    (nil)
                                    (clojure.core/let
                                     [X__196845
                                      (clojure.core/name
                                       input__196840_nth_0__)]
                                     (if
                                      (clojure.core/string? X__196845)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__196845)
                                       (save__196841)
                                       (f__197718))
                                      (f__197718)))
                                    (f__197718)))
                                  (f__197718)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__196443_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__196443_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__196443_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__196443_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__196443_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__196443_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__196443_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__196443_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__196443_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__196834
                                      (clojure.core/namespace
                                       input__196443_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__196834
                                      (nil)
                                      (clojure.core/let
                                       [X__196836
                                        (clojure.core/name
                                         input__196443_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string?
                                         X__196836)
                                        (clojure.core/let
                                         [ret__196837
                                          (clojure.core/re-matches
                                           #"\.\.(\?.+)"
                                           X__196836)]
                                         (if
                                          (clojure.core/some?
                                           ret__196837)
                                          (if
                                           (clojure.core/vector?
                                            ret__196837)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__196837)
                                             2)
                                            (clojure.core/let
                                             [ret__196837_nth_1__
                                              (clojure.core/nth
                                               ret__196837
                                               1)]
                                             (clojure.core/let
                                              [?n ret__196837_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__196443_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__196443_nth_2__]
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
                                                      (CATA__FN__196503
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
                                                      (CATA__FN__196503
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
                         (meander.runtime.zeta/fail? result__197715)
                         (recur
                          (clojure.core/next search_space__197714))
                         result__197715))
                       (state__197707)))
                     (state__197707))))
                  (state__197707
                   []
                   (if
                    (clojure.core/vector? input__196443_nth_1__)
                    (clojure.core/let
                     [input__196443_nth_1___l__
                      (clojure.core/subvec
                       input__196443_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__196443_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__196443_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__196443_nth_1___r__
                        (clojure.core/subvec input__196443_nth_1__ 1)]
                       (clojure.core/let
                        [input__196443_nth_1___l___nth_0__
                         (clojure.core/nth
                          input__196443_nth_1___l__
                          0)]
                        (if
                         (clojure.core/symbol?
                          input__196443_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__196852
                           (clojure.core/namespace
                            input__196443_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__196852
                           (nil)
                           (clojure.core/let
                            [X__196854
                             (clojure.core/name
                              input__196443_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__196854)
                             (clojure.core/let
                              [ret__196855
                               (clojure.core/re-matches
                                #"\.\.(!.+)"
                                X__196854)]
                              (if
                               (clojure.core/some? ret__196855)
                               (if
                                (clojure.core/vector? ret__196855)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__196855)
                                  2)
                                 (clojure.core/let
                                  [ret__196855_nth_1__
                                   (clojure.core/nth ret__196855 1)]
                                  (clojure.core/let
                                   [?n ret__196855_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__196443_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__196443_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__196443_nth_2__]
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
                                 (state__197708))
                                (state__197708))
                               (state__197708)))
                             (state__197708)))
                           (state__197708)))
                         (state__197708))))
                      (state__197708)))
                    (state__197708)))
                  (state__197708
                   []
                   (clojure.core/let
                    [?rule-name input__196443_nth_0__]
                    (if
                     (clojure.core/vector? input__196443_nth_1__)
                     (clojure.core/loop
                      [search_space__197719
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__196443_nth_1__)]
                      (if
                       (clojure.core/seq search_space__197719)
                       (clojure.core/let
                        [input__196443_nth_1___parts__
                         (clojure.core/first search_space__197719)
                         result__197720
                         (clojure.core/let
                          [input__196443_nth_1___l__
                           (clojure.core/nth
                            input__196443_nth_1___parts__
                            0)
                           input__196443_nth_1___r__
                           (clojure.core/nth
                            input__196443_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__14413__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__196443_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__196871]
                               (clojure.core/let
                                [input__196871_nth_0__
                                 (clojure.core/nth input__196871 0)]
                                (clojure.core/letfn
                                 [(save__196872
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__197723
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__196871_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__196871_nth_0__)
                                  (clojure.core/let
                                   [X__196874
                                    (clojure.core/namespace
                                     input__196871_nth_0__)]
                                   (clojure.core/case
                                    X__196874
                                    (nil)
                                    (clojure.core/let
                                     [X__196876
                                      (clojure.core/name
                                       input__196871_nth_0__)]
                                     (if
                                      (clojure.core/string? X__196876)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__196876)
                                       (save__196872)
                                       (f__197723))
                                      (f__197723)))
                                    (f__197723)))
                                  (f__197723)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__196443_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__196443_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__196443_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__196443_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__196443_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__196443_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__196443_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__196443_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__196443_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__196865
                                      (clojure.core/namespace
                                       input__196443_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__196865
                                      (nil)
                                      (clojure.core/let
                                       [X__196867
                                        (clojure.core/name
                                         input__196443_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string?
                                         X__196867)
                                        (clojure.core/let
                                         [ret__196868
                                          (clojure.core/re-matches
                                           #"\.\.(\!.+)"
                                           X__196867)]
                                         (if
                                          (clojure.core/some?
                                           ret__196868)
                                          (if
                                           (clojure.core/vector?
                                            ret__196868)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__196868)
                                             2)
                                            (clojure.core/let
                                             [ret__196868_nth_1__
                                              (clojure.core/nth
                                               ret__196868
                                               1)]
                                             (clojure.core/let
                                              [?n ret__196868_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__196443_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__196443_nth_2__]
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
                                                      (CATA__FN__196503
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
                                                      (CATA__FN__196503
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
                         (meander.runtime.zeta/fail? result__197720)
                         (recur
                          (clojure.core/next search_space__197719))
                         result__197720))
                       (state__197683)))
                     (state__197683))))]
                 (state__197703))
                (state__197683)))
              (state__197683
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/parse-seq)
                (if
                 (clojure.core/vector? input__196443_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__196443_nth_1__)]
                  (clojure.core/let
                   [?env input__196443_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__15552__auto__
                        (CATA__FN__196503
                         ['meander.dev.parse.zeta/cat-args
                          (clojure.core/let
                           [return__196504 (clojure.core/transient [])]
                           (clojure.core/while
                            (clojure.core/and (.hasNext !xs__counter))
                            (clojure.core/conj!
                             return__196504
                             (clojure.core/let
                              [CATA_RESULT__15552__auto__
                               (CATA__FN__196503
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
                           (clojure.core/persistent! return__196504))
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
                 (state__197684))
                (state__197684)))
              (state__197684
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__196443_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__196443_nth_1__)]
                  (clojure.core/let
                   [?env input__196443_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__15552__auto__
                        (CATA__FN__196503
                         ['meander.dev.parse.zeta/string-cat-args
                          (clojure.core/let
                           [return__196505 (clojure.core/transient [])]
                           (clojure.core/while
                            (clojure.core/and (.hasNext !xs__counter))
                            (clojure.core/conj!
                             return__196505
                             (clojure.core/let
                              [CATA_RESULT__15552__auto__
                               (CATA__FN__196503
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
                           (clojure.core/persistent! return__196505))
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
                 (state__197685))
                (state__197685)))
              (state__197685
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/cat-args)
                (if
                 (clojure.core/vector? input__196443_nth_1__)
                 (clojure.core/let
                  [!forms []]
                  (clojure.core/let
                   [ret__14413__auto__
                    (meander.runtime.zeta/epsilon-run-star-1
                     input__196443_nth_1__
                     [!forms]
                     (clojure.core/fn
                      [[!forms] input__196891]
                      (clojure.core/let
                       [input__196891_nth_0__
                        (clojure.core/nth input__196891 0)]
                       (if
                        (clojure.core/map? input__196891_nth_0__)
                        (clojure.core/let
                         [VAL__196892
                          (.valAt input__196891_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__196892
                          (:literal)
                          (clojure.core/let
                           [VAL__196893
                            (.valAt input__196891_nth_0__ :form)]
                           (clojure.core/let
                            [!forms
                             (clojure.core/conj !forms VAL__196893)]
                            [!forms]))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail))))
                     (clojure.core/fn
                      [[!forms]]
                      (if
                       (clojure.core/map? input__196443_nth_2__)
                       (clojure.core/let
                        [VAL__196888
                         (.valAt input__196443_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__196888
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
                         (state__197686)))
                       (state__197686))))]
                   (if
                    (meander.runtime.zeta/fail? ret__14413__auto__)
                    (state__197686)
                    ret__14413__auto__)))
                 (state__197686))
                (state__197686)))
              (state__197686
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/cat-args)
                (clojure.core/let
                 [?sequence input__196443_nth_1__]
                 (clojure.core/let
                  [?next input__196443_nth_2__]
                  (try
                   [{:tag :cat, :sequence ?sequence, :next ?next}]
                   (catch
                    java.lang.Exception
                    e__16492__auto__
                    (if
                     (meander.runtime.zeta/fail? e__16492__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__16492__auto__))))))
                (state__197687)))
              (state__197687
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (if
                 (clojure.core/map? input__196443_nth_1__)
                 (clojure.core/let
                  [VAL__196899 (.valAt input__196443_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__196899
                   (:cat)
                   (clojure.core/let
                    [VAL__196900
                     (.valAt input__196443_nth_1__ :sequence)]
                    (clojure.core/let
                     [?sequence VAL__196900]
                     (clojure.core/let
                      [VAL__196901
                       (.valAt input__196443_nth_1__ :next)]
                      (if
                       (clojure.core/map? VAL__196901)
                       (clojure.core/let
                        [VAL__196902 (.valAt VAL__196901 :tag)]
                        (clojure.core/case
                         VAL__196902
                         (:empty)
                         (clojure.core/let
                          [?right input__196443_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__15552__auto__
                              (CATA__FN__196503
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
                         (state__197688)))
                       (state__197688)))))
                   (state__197688)))
                 (state__197688))
                (state__197688)))
              (state__197688
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (clojure.core/let
                 [?left input__196443_nth_1__]
                 (if
                  (clojure.core/map? input__196443_nth_2__)
                  (clojure.core/let
                   [VAL__196905 (.valAt input__196443_nth_2__ :tag)]
                   (clojure.core/case
                    VAL__196905
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
                    (state__197689)))
                  (state__197689)))
                (state__197689)))
              (state__197689
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (if
                 (clojure.core/map? input__196443_nth_1__)
                 (clojure.core/let
                  [VAL__196908 (.valAt input__196443_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__196908
                   (:empty)
                   (clojure.core/let
                    [?right input__196443_nth_2__]
                    (try
                     [?right]
                     (catch
                      java.lang.Exception
                      e__16492__auto__
                      (if
                       (meander.runtime.zeta/fail? e__16492__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__16492__auto__)))))
                   (state__197690)))
                 (state__197690))
                (state__197690)))
              (state__197690
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (clojure.core/let
                 [?left input__196443_nth_1__]
                 (clojure.core/let
                  [?right input__196443_nth_2__]
                  (try
                   [{:tag :join, :left ?left, :right ?right}]
                   (catch
                    java.lang.Exception
                    e__16492__auto__
                    (if
                     (meander.runtime.zeta/fail? e__16492__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__16492__auto__))))))
                (state__197691)))
              (state__197691
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (if
                 (clojure.core/vector? input__196443_nth_1__)
                 (clojure.core/let
                  [!forms []]
                  (clojure.core/let
                   [ret__14413__auto__
                    (meander.runtime.zeta/epsilon-run-star-1
                     input__196443_nth_1__
                     [!forms]
                     (clojure.core/fn
                      [[!forms] input__196916]
                      (clojure.core/let
                       [input__196916_nth_0__
                        (clojure.core/nth input__196916 0)]
                       (if
                        (clojure.core/map? input__196916_nth_0__)
                        (clojure.core/let
                         [VAL__196917
                          (.valAt input__196916_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__196917
                          (:literal)
                          (clojure.core/let
                           [VAL__196918
                            (.valAt input__196916_nth_0__ :type)]
                           (if
                            (clojure.core/let
                             [x__13309__auto__ VAL__196918]
                             (clojure.core/case
                              x__13309__auto__
                              (:string :char)
                              true
                              false))
                            (clojure.core/let
                             [VAL__196919
                              (.valAt input__196916_nth_0__ :form)]
                             (clojure.core/let
                              [!forms
                               (clojure.core/conj !forms VAL__196919)]
                              [!forms]))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail))))
                     (clojure.core/fn
                      [[!forms]]
                      (if
                       (clojure.core/map? input__196443_nth_2__)
                       (clojure.core/let
                        [VAL__196913
                         (.valAt input__196443_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__196913
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
                         (state__197692)))
                       (state__197692))))]
                   (if
                    (meander.runtime.zeta/fail? ret__14413__auto__)
                    (state__197692)
                    ret__14413__auto__)))
                 (state__197692))
                (state__197692)))
              (state__197692
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (if
                 (clojure.core/vector? input__196443_nth_1__)
                 (clojure.core/let
                  [input__196443_nth_1___l__
                   (clojure.core/subvec
                    input__196443_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__196443_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__196443_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__196443_nth_1___r__
                     (clojure.core/subvec input__196443_nth_1__ 1)]
                    (clojure.core/let
                     [input__196443_nth_1___l___nth_0__
                      (clojure.core/nth input__196443_nth_1___l__ 0)]
                     (if
                      (clojure.core/map?
                       input__196443_nth_1___l___nth_0__)
                      (clojure.core/let
                       [VAL__196925
                        (.valAt
                         input__196443_nth_1___l___nth_0__
                         :tag)]
                       (clojure.core/case
                        VAL__196925
                        (:literal)
                        (clojure.core/let
                         [VAL__196926
                          (.valAt
                           input__196443_nth_1___l___nth_0__
                           :type)]
                         (clojure.core/case
                          VAL__196926
                          (:string)
                          (clojure.core/let
                           [?ast input__196443_nth_1___l___nth_0__]
                           (clojure.core/let
                            [?rest input__196443_nth_1___r__]
                            (clojure.core/let
                             [?next input__196443_nth_2__]
                             (try
                              [(clojure.core/let
                                [CATA_RESULT__15552__auto__
                                 (CATA__FN__196503
                                  ['meander.dev.parse.zeta/string-join-args
                                   ?ast
                                   (clojure.core/let
                                    [CATA_RESULT__15552__auto__
                                     (CATA__FN__196503
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
                          (state__197693)))
                        (state__197693)))
                      (state__197693))))
                   (state__197693)))
                 (state__197693))
                (state__197693)))
              (state__197693
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (clojure.core/let
                 [?sequence input__196443_nth_1__]
                 (clojure.core/let
                  [?next input__196443_nth_2__]
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
                (state__197694)))
              (state__197694
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__196443_nth_1__)
                 (clojure.core/let
                  [VAL__196931 (.valAt input__196443_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__196931
                   (:literal)
                   (clojure.core/let
                    [VAL__196932 (.valAt input__196443_nth_1__ :type)]
                    (clojure.core/case
                     VAL__196932
                     (:string)
                     (clojure.core/let
                      [VAL__196933
                       (.valAt input__196443_nth_1__ :form)]
                      (clojure.core/let
                       [?form-1 VAL__196933]
                       (if
                        (clojure.core/map? input__196443_nth_2__)
                        (clojure.core/let
                         [VAL__196934
                          (.valAt input__196443_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__196934
                          (:string-join)
                          (clojure.core/let
                           [VAL__196935
                            (.valAt input__196443_nth_2__ :left)]
                           (if
                            (clojure.core/map? VAL__196935)
                            (clojure.core/let
                             [VAL__196936 (.valAt VAL__196935 :tag)]
                             (clojure.core/case
                              VAL__196936
                              (:literal)
                              (clojure.core/let
                               [VAL__196937 (.valAt VAL__196935 :type)]
                               (clojure.core/case
                                VAL__196937
                                (:string)
                                (clojure.core/let
                                 [VAL__196938
                                  (.valAt VAL__196935 :form)]
                                 (clojure.core/let
                                  [?form-2 VAL__196938]
                                  (clojure.core/let
                                   [VAL__196939
                                    (.valAt
                                     input__196443_nth_2__
                                     :right)]
                                   (clojure.core/let
                                    [?right VAL__196939]
                                    (try
                                     [(clojure.core/let
                                       [CATA_RESULT__15552__auto__
                                        (CATA__FN__196503
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
                                (state__197695)))
                              (state__197695)))
                            (state__197695)))
                          (state__197695)))
                        (state__197695))))
                     (state__197695)))
                   (state__197695)))
                 (state__197695))
                (state__197695)))
              (state__197695
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__196443_nth_1__)
                 (clojure.core/let
                  [VAL__196942 (.valAt input__196443_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__196942
                   (:literal)
                   (clojure.core/let
                    [VAL__196943 (.valAt input__196443_nth_1__ :type)]
                    (clojure.core/case
                     VAL__196943
                     (:string)
                     (clojure.core/let
                      [?ast input__196443_nth_1__]
                      (if
                       (clojure.core/map? input__196443_nth_2__)
                       (clojure.core/let
                        [VAL__196944
                         (.valAt input__196443_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__196944
                         (:string-cat)
                         (clojure.core/let
                          [VAL__196945
                           (.valAt input__196443_nth_2__ :sequence)]
                          (clojure.core/let
                           [?sequence VAL__196945]
                           (clojure.core/let
                            [VAL__196946
                             (.valAt input__196443_nth_2__ :next)]
                            (clojure.core/let
                             [?next VAL__196946]
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
                         (state__197696)))
                       (state__197696)))
                     (state__197696)))
                   (state__197696)))
                 (state__197696))
                (state__197696)))
              (state__197696
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__196443_nth_1__)
                 (clojure.core/let
                  [VAL__196949 (.valAt input__196443_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__196949
                   (:string-cat)
                   (clojure.core/let
                    [VAL__196950
                     (.valAt input__196443_nth_1__ :sequence)]
                    (clojure.core/let
                     [?sequence VAL__196950]
                     (clojure.core/let
                      [VAL__196951
                       (.valAt input__196443_nth_1__ :next)]
                      (if
                       (clojure.core/map? VAL__196951)
                       (clojure.core/let
                        [VAL__196952 (.valAt VAL__196951 :tag)]
                        (clojure.core/case
                         VAL__196952
                         (:empty)
                         (clojure.core/let
                          [?right input__196443_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__15552__auto__
                              (CATA__FN__196503
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
                         (state__197697)))
                       (state__197697)))))
                   (state__197697)))
                 (state__197697))
                (state__197697)))
              (state__197697
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__196443_nth_1__)
                 (clojure.core/let
                  [VAL__196955 (.valAt input__196443_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__196955
                   (:string-star)
                   (clojure.core/let
                    [VAL__196956
                     (.valAt input__196443_nth_1__ :pattern)]
                    (clojure.core/let
                     [?pattern VAL__196956]
                     (clojure.core/let
                      [VAL__196957
                       (.valAt input__196443_nth_1__ :next)]
                      (if
                       (clojure.core/map? VAL__196957)
                       (clojure.core/let
                        [VAL__196958 (.valAt VAL__196957 :tag)]
                        (clojure.core/case
                         VAL__196958
                         (:empty)
                         (clojure.core/let
                          [?right input__196443_nth_2__]
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
                         (state__197698)))
                       (state__197698)))))
                   (state__197698)))
                 (state__197698))
                (state__197698)))
              (state__197698
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__196443_nth_1__)
                 (clojure.core/let
                  [VAL__196961 (.valAt input__196443_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__196961
                   (:string-join)
                   (clojure.core/let
                    [VAL__196962 (.valAt input__196443_nth_1__ :left)]
                    (clojure.core/let
                     [?left VAL__196962]
                     (clojure.core/let
                      [VAL__196963
                       (.valAt input__196443_nth_1__ :right)]
                      (clojure.core/let
                       [?right-1 VAL__196963]
                       (clojure.core/let
                        [?right-2 input__196443_nth_2__]
                        (try
                         [{:tag :string-join,
                           :left ?left,
                           :right
                           (clojure.core/let
                            [CATA_RESULT__15552__auto__
                             (CATA__FN__196503
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
                   (state__197699)))
                 (state__197699))
                (state__197699)))
              (state__197699
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (clojure.core/let
                 [?left input__196443_nth_1__]
                 (if
                  (clojure.core/map? input__196443_nth_2__)
                  (clojure.core/let
                   [VAL__196966 (.valAt input__196443_nth_2__ :tag)]
                   (clojure.core/case
                    VAL__196966
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
                    (state__197700)))
                  (state__197700)))
                (state__197700)))
              (state__197700
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__196443_nth_1__)
                 (clojure.core/let
                  [VAL__196969 (.valAt input__196443_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__196969
                   (:empty)
                   (clojure.core/let
                    [?right input__196443_nth_2__]
                    (try
                     [?right]
                     (catch
                      java.lang.Exception
                      e__16492__auto__
                      (if
                       (meander.runtime.zeta/fail? e__16492__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__16492__auto__)))))
                   (state__197701)))
                 (state__197701))
                (state__197701)))
              (state__197701
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (clojure.core/let
                 [?left input__196443_nth_1__]
                 (clojure.core/let
                  [?right input__196443_nth_2__]
                  (try
                   [{:tag :string-join, :left ?left, :right ?right}]
                   (catch
                    java.lang.Exception
                    e__16492__auto__
                    (if
                     (meander.runtime.zeta/fail? e__16492__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__16492__auto__))))))
                (state__197702)))
              (state__197702
               []
               (if
                (clojure.core/=
                 input__196443_nth_0__
                 'meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__196443_nth_1__)
                 (clojure.core/let
                  [VAL__196974
                   (.valAt input__196443_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__196974]
                   (clojure.core/let
                    [X__196976
                     ((clojure.core/fn
                       [m__13316__auto__]
                       (clojure.core/dissoc
                        m__13316__auto__
                        :meander.zeta/as))
                      input__196443_nth_1__)]
                    (clojure.core/let
                     [?rest X__196976]
                     (clojure.core/letfn
                      [(save__196977 [] (state__197609))
                       (f__197726
                        []
                        (clojure.core/let
                         [?env input__196443_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__15552__auto__
                              (CATA__FN__196503 [?pattern ?env])]
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
                              (CATA__FN__196503 [?rest ?env])]
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
                       (clojure.core/= ?rest input__196443_nth_1__)
                       (save__196977)
                       (f__197726)))))))
                 (state__197609))
                (state__197609)))]
             (state__197678)))
           (state__197609))
          (state__197609)))
        (state__197609
         []
         (clojure.core/letfn
          [(def__196980
            [arg__197013 ?ns]
            (clojure.core/letfn
             [(state__197727
               []
               (clojure.core/let
                [x__197014 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__197014)
                 (clojure.core/let [?env arg__197013] [?env ?ns])
                 (state__197728))))
              (state__197728
               []
               (if
                (clojure.core/map? arg__197013)
                (clojure.core/let
                 [VAL__197015 (.valAt arg__197013 :aliases)]
                 (if
                  (clojure.core/map? VAL__197015)
                  (clojure.core/let
                   [X__197017 (clojure.core/set VAL__197015)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__197017))
                    (clojure.core/loop
                     [search_space__197729
                      (clojure.core/seq X__197017)]
                     (if
                      (clojure.core/seq search_space__197729)
                      (clojure.core/let
                       [elem__197018
                        (clojure.core/first search_space__197729)
                        result__197730
                        (clojure.core/let
                         [elem__197018_nth_0__
                          (clojure.core/nth elem__197018 0)
                          elem__197018_nth_1__
                          (clojure.core/nth elem__197018 1)]
                         (if
                          (clojure.core/symbol? elem__197018_nth_0__)
                          (clojure.core/let
                           [X__197020
                            (clojure.core/name elem__197018_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__197020)
                            (if
                             (clojure.core/symbol?
                              elem__197018_nth_1__)
                             (clojure.core/let
                              [X__197022
                               (clojure.core/name
                                elem__197018_nth_1__)]
                              (clojure.core/case
                               X__197022
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__197013]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197730)
                        (recur
                         (clojure.core/next search_space__197729))
                        result__197730))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197727)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 3)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)
              input__196443_nth_2__
              (clojure.core/nth input__196443 2)]
             (if
              (clojure.core/=
               input__196443_nth_0__
               'meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__196443_nth_1__)
               (clojure.core/let
                [X__196985 (clojure.core/set input__196443_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__196985))
                 (clojure.core/loop
                  [search_space__197732 (clojure.core/seq X__196985)]
                  (if
                   (clojure.core/seq search_space__197732)
                   (clojure.core/let
                    [elem__196986
                     (clojure.core/first search_space__197732)
                     result__197733
                     (clojure.core/let
                      [elem__196986_nth_0__
                       (clojure.core/nth elem__196986 0)
                       elem__196986_nth_1__
                       (clojure.core/nth elem__196986 1)]
                      (clojure.core/let
                       [*m__196472 elem__196986_nth_0__]
                       (if
                        (clojure.core/symbol? elem__196986_nth_0__)
                        (clojure.core/let
                         [X__196988
                          (clojure.core/namespace
                           elem__196986_nth_0__)]
                         (clojure.core/let
                          [?ns X__196988]
                          (clojure.core/let
                           [X__196990
                            (clojure.core/name elem__196986_nth_0__)]
                           (if
                            (clojure.core/string? X__196990)
                            (if
                             (clojure.core/re-matches #"&.*" X__196990)
                             (clojure.core/let
                              [?pattern elem__196986_nth_1__]
                              (clojure.core/let
                               [X__196992
                                ((clojure.core/fn
                                  [m__13316__auto__]
                                  (clojure.core/dissoc
                                   m__13316__auto__
                                   *m__196472))
                                 input__196443_nth_1__)]
                               (clojure.core/let
                                [?rest X__196992]
                                (clojure.core/let
                                 [x__14249__auto__
                                  (def__196980
                                   input__196443_nth_2__
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
                                        (CATA__FN__196503
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
                                        (CATA__FN__196503
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
                     (meander.runtime.zeta/fail? result__197733)
                     (recur (clojure.core/next search_space__197732))
                     result__197733))
                   (state__197610)))
                 (state__197610)))
               (state__197610))
              (state__197610)))
            (state__197610))
           (state__197610))))
        (state__197610
         []
         (if
          (clojure.core/vector? input__196443)
          (clojure.core/letfn
           [(state__197735
             []
             (if
              (clojure.core/= (clojure.core/count input__196443) 3)
              (clojure.core/let
               [input__196443_nth_0__
                (clojure.core/nth input__196443 0)
                input__196443_nth_1__
                (clojure.core/nth input__196443 1)
                input__196443_nth_2__
                (clojure.core/nth input__196443 2)]
               (clojure.core/letfn
                [(state__197738
                  []
                  (if
                   (clojure.core/=
                    input__196443_nth_0__
                    'meander.dev.parse.zeta/parse-entries)
                   (if
                    (clojure.core/map? input__196443_nth_1__)
                    (clojure.core/let
                     [X__197036
                      (clojure.core/set input__196443_nth_1__)]
                     (if
                      (clojure.core/<=
                       1
                       (clojure.core/count X__197036))
                      (clojure.core/loop
                       [search_space__197744
                        (clojure.core/seq X__197036)]
                       (if
                        (clojure.core/seq search_space__197744)
                        (clojure.core/let
                         [elem__197037
                          (clojure.core/first search_space__197744)
                          result__197745
                          (clojure.core/let
                           [elem__197037_nth_0__
                            (clojure.core/nth elem__197037 0)
                            elem__197037_nth_1__
                            (clojure.core/nth elem__197037 1)]
                           (clojure.core/let
                            [?key-pattern elem__197037_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__197037_nth_1__]
                             (clojure.core/let
                              [X__197039
                               ((clojure.core/fn
                                 [m__13316__auto__]
                                 (clojure.core/dissoc
                                  m__13316__auto__
                                  ?key-pattern))
                                input__196443_nth_1__)]
                              (clojure.core/let
                               [?rest X__197039]
                               (clojure.core/let
                                [?env input__196443_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__15552__auto__
                                     (CATA__FN__196503
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
                                     (CATA__FN__196503
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
                                     (CATA__FN__196503
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
                          (meander.runtime.zeta/fail? result__197745)
                          (recur
                           (clojure.core/next search_space__197744))
                          result__197745))
                        (state__197739)))
                      (state__197739)))
                    (state__197739))
                   (state__197739)))
                 (state__197739
                  []
                  (if
                   (clojure.core/=
                    input__196443_nth_0__
                    'meander.dev.parse.zeta/parse-entries)
                   (if
                    (clojure.core/map? input__196443_nth_1__)
                    (clojure.core/let
                     [?env input__196443_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__16492__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16492__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16492__auto__)))))
                    (state__197740))
                   (state__197740)))
                 (state__197740
                  []
                  (if
                   (clojure.core/=
                    input__196443_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__196443_nth_1__)
                    (clojure.core/case
                     input__196443_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__196443_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__16492__auto__
                        (if
                         (meander.runtime.zeta/fail? e__16492__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__16492__auto__)))))
                     (state__197741))
                    (state__197741))
                   (state__197741)))
                 (state__197741
                  []
                  (if
                   (clojure.core/=
                    input__196443_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__196443_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__196443_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__196443_nth_2__]
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
                     (state__197742))
                    (state__197742))
                   (state__197742)))
                 (state__197742
                  []
                  (if
                   (clojure.core/=
                    input__196443_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__196443_nth_1__)
                    (clojure.core/let
                     [input__196443_nth_1___l__
                      (clojure.core/subvec
                       input__196443_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__196443_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__196443_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__196443_nth_1___r__
                        (clojure.core/subvec input__196443_nth_1__ 2)]
                       (clojure.core/let
                        [input__196443_nth_1___l___nth_0__
                         (clojure.core/nth input__196443_nth_1___l__ 0)
                         input__196443_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__196443_nth_1___l__
                          1)]
                        (if
                         (clojure.core/symbol?
                          input__196443_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__197053
                           (clojure.core/namespace
                            input__196443_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__197055
                            (clojure.core/name
                             input__196443_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__197055)
                            (if
                             (clojure.core/re-matches #"%.+" X__197055)
                             (clojure.core/let
                              [?symbol
                               input__196443_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__196443_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__196443_nth_1___r__]
                                (clojure.core/let
                                 [?env input__196443_nth_2__]
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
                                         (CATA__FN__196503
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
                                       (CATA__FN__196503
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
                             (state__197743))
                            (state__197743))))
                         (state__197743))))
                      (state__197743)))
                    (state__197743))
                   (state__197743)))
                 (state__197743
                  []
                  (if
                   (clojure.core/=
                    input__196443_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__196443_nth_1__)
                    (clojure.core/let
                     [input__196443_nth_1___l__
                      (clojure.core/subvec
                       input__196443_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__196443_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__196443_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__196443_nth_1___r__
                        (clojure.core/subvec input__196443_nth_1__ 2)]
                       (clojure.core/let
                        [input__196443_nth_1___l___nth_0__
                         (clojure.core/nth input__196443_nth_1___l__ 0)
                         input__196443_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__196443_nth_1___l__
                          1)]
                        (clojure.core/let
                         [?x input__196443_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__196443_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__196443_nth_1___r__]
                           (clojure.core/let
                            [?env input__196443_nth_2__]
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
                      (state__197736)))
                    (state__197736))
                   (state__197736)))]
                (state__197738)))
              (state__197736)))
            (state__197736
             []
             (if
              (clojure.core/= (clojure.core/count input__196443) 2)
              (clojure.core/let
               [input__196443_nth_0__
                (clojure.core/nth input__196443 0)
                input__196443_nth_1__
                (clojure.core/nth input__196443 1)]
               (if
                (clojure.core/vector? input__196443_nth_0__)
                (clojure.core/let
                 [?sequence input__196443_nth_0__]
                 (clojure.core/let
                  [?env input__196443_nth_1__]
                  (try
                   [(clojure.core/let
                     [CATA_RESULT__15552__auto__
                      (CATA__FN__196503
                       ['meander.dev.parse.zeta/vector-args
                        (clojure.core/let
                         [CATA_RESULT__15552__auto__
                          (CATA__FN__196503
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
                (state__197737)))
              (state__197737)))
            (state__197737
             []
             (if
              (clojure.core/= (clojure.core/count input__196443) 3)
              (clojure.core/let
               [input__196443_nth_0__
                (clojure.core/nth input__196443 0)
                input__196443_nth_1__
                (clojure.core/nth input__196443 1)]
               (clojure.core/letfn
                [(state__197747
                  []
                  (if
                   (clojure.core/=
                    input__196443_nth_0__
                    'meander.dev.parse.zeta/vector-args)
                   (if
                    (clojure.core/map? input__196443_nth_1__)
                    (clojure.core/let
                     [VAL__197066 (.valAt input__196443_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__197066
                      (:literal)
                      (clojure.core/let
                       [?literal input__196443_nth_1__]
                       (try
                        [?literal]
                        (catch
                         java.lang.Exception
                         e__16492__auto__
                         (if
                          (meander.runtime.zeta/fail? e__16492__auto__)
                          (meander.runtime.zeta/fail)
                          (throw e__16492__auto__)))))
                      (state__197748)))
                    (state__197748))
                   (state__197748)))
                 (state__197748
                  []
                  (clojure.core/let
                   [input__196443_nth_2__
                    (clojure.core/nth input__196443 2)]
                   (if
                    (clojure.core/=
                     input__196443_nth_0__
                     'meander.dev.parse.zeta/vector-args)
                    (clojure.core/let
                     [?next input__196443_nth_1__]
                     (clojure.core/let
                      [?sequence input__196443_nth_2__]
                      (try
                       [{:tag :vector, :next ?next, :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__16492__auto__
                        (if
                         (meander.runtime.zeta/fail? e__16492__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__16492__auto__))))))
                    (state__197611))))]
                (state__197747)))
              (state__197611)))]
           (state__197735))
          (state__197611)))
        (state__197611
         []
         (clojure.core/letfn
          [(def__197070
            [arg__197093 ?__196445]
            (clojure.core/letfn
             [(state__197749
               []
               (clojure.core/let
                [x__197094 "meander.zeta"]
                (if
                 (clojure.core/= ?__196445 x__197094)
                 [?__196445]
                 (state__197750))))
              (state__197750
               []
               (if
                (clojure.core/map? arg__197093)
                (clojure.core/let
                 [VAL__197095 (.valAt arg__197093 :aliases)]
                 (if
                  (clojure.core/map? VAL__197095)
                  (clojure.core/let
                   [X__197097 (clojure.core/set VAL__197095)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__197097))
                    (clojure.core/loop
                     [search_space__197751
                      (clojure.core/seq X__197097)]
                     (if
                      (clojure.core/seq search_space__197751)
                      (clojure.core/let
                       [elem__197098
                        (clojure.core/first search_space__197751)
                        result__197752
                        (clojure.core/let
                         [elem__197098_nth_0__
                          (clojure.core/nth elem__197098 0)
                          elem__197098_nth_1__
                          (clojure.core/nth elem__197098 1)]
                         (if
                          (clojure.core/symbol? elem__197098_nth_0__)
                          (clojure.core/let
                           [X__197100
                            (clojure.core/name elem__197098_nth_0__)]
                           (if
                            (clojure.core/= ?__196445 X__197100)
                            (if
                             (clojure.core/symbol?
                              elem__197098_nth_1__)
                             (clojure.core/let
                              [X__197102
                               (clojure.core/name
                                elem__197098_nth_1__)]
                              (clojure.core/case
                               X__197102
                               ("meander.zeta")
                               [?__196445]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197752)
                        (recur
                         (clojure.core/next search_space__197751))
                        result__197752))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197749)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 2)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)]
             (if
              (clojure.core/seq? input__196443_nth_0__)
              (clojure.core/let
               [input__196443_nth_0___l__
                (clojure.core/take 1 input__196443_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__196443_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__196443_nth_0___r__
                  (clojure.core/drop 1 input__196443_nth_0__)]
                 (clojure.core/let
                  [input__196443_nth_0___l___nth_0__
                   (clojure.core/nth input__196443_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__196443_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__197080
                     (clojure.core/namespace
                      input__196443_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__196445 X__197080]
                     (clojure.core/let
                      [X__197082
                       (clojure.core/name
                        input__196443_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__197082
                       ("with")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__197070 input__196443_nth_1__ ?__196445)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__197612)
                         (clojure.core/let
                          [[?__196445] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__196443)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__196443)
                             2)
                            (clojure.core/let
                             [input__196443_nth_0__
                              (clojure.core/nth input__196443 0)
                              input__196443_nth_1__
                              (clojure.core/nth input__196443 1)]
                             (if
                              (clojure.core/seq? input__196443_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__196443_nth_0__)
                                3)
                               (clojure.core/let
                                [input__196443_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  1)
                                 input__196443_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__196443_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__196443_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__196443_nth_0__]
                                   (clojure.core/let
                                    [?env input__196443_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__15552__auto__
                                          (CATA__FN__196503
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
                                         (CATA__FN__196503
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
                               (state__197612))
                              (state__197612)))
                            (state__197612))
                           (state__197612)))))
                       (state__197612)))))
                   (state__197612))))
                (state__197612)))
              (state__197612)))
            (state__197612))
           (state__197612))))
        (state__197612
         []
         (clojure.core/letfn
          [(def__197104
            [arg__197127 ?__196446]
            (clojure.core/letfn
             [(state__197754
               []
               (clojure.core/let
                [x__197128 "meander.zeta"]
                (if
                 (clojure.core/= ?__196446 x__197128)
                 [?__196446]
                 (state__197755))))
              (state__197755
               []
               (if
                (clojure.core/map? arg__197127)
                (clojure.core/let
                 [VAL__197129 (.valAt arg__197127 :aliases)]
                 (if
                  (clojure.core/map? VAL__197129)
                  (clojure.core/let
                   [X__197131 (clojure.core/set VAL__197129)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__197131))
                    (clojure.core/loop
                     [search_space__197756
                      (clojure.core/seq X__197131)]
                     (if
                      (clojure.core/seq search_space__197756)
                      (clojure.core/let
                       [elem__197132
                        (clojure.core/first search_space__197756)
                        result__197757
                        (clojure.core/let
                         [elem__197132_nth_0__
                          (clojure.core/nth elem__197132 0)
                          elem__197132_nth_1__
                          (clojure.core/nth elem__197132 1)]
                         (if
                          (clojure.core/symbol? elem__197132_nth_0__)
                          (clojure.core/let
                           [X__197134
                            (clojure.core/name elem__197132_nth_0__)]
                           (if
                            (clojure.core/= ?__196446 X__197134)
                            (if
                             (clojure.core/symbol?
                              elem__197132_nth_1__)
                             (clojure.core/let
                              [X__197136
                               (clojure.core/name
                                elem__197132_nth_1__)]
                              (clojure.core/case
                               X__197136
                               ("meander.zeta")
                               [?__196446]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197757)
                        (recur
                         (clojure.core/next search_space__197756))
                        result__197757))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197754)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 2)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)]
             (if
              (clojure.core/seq? input__196443_nth_0__)
              (clojure.core/let
               [input__196443_nth_0___l__
                (clojure.core/take 1 input__196443_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__196443_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__196443_nth_0___r__
                  (clojure.core/drop 1 input__196443_nth_0__)]
                 (clojure.core/let
                  [input__196443_nth_0___l___nth_0__
                   (clojure.core/nth input__196443_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__196443_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__197114
                     (clojure.core/namespace
                      input__196443_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__196446 X__197114]
                     (clojure.core/let
                      [X__197116
                       (clojure.core/name
                        input__196443_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__197116
                       ("apply")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__197104 input__196443_nth_1__ ?__196446)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__197613)
                         (clojure.core/let
                          [[?__196446] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__196443)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__196443)
                             2)
                            (clojure.core/let
                             [input__196443_nth_0__
                              (clojure.core/nth input__196443 0)
                              input__196443_nth_1__
                              (clojure.core/nth input__196443 1)]
                             (if
                              (clojure.core/seq? input__196443_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__196443_nth_0__)
                                3)
                               (clojure.core/let
                                [input__196443_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  1)
                                 input__196443_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__196443_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__196443_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__196443_nth_0__]
                                   (clojure.core/let
                                    [?env input__196443_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__196503
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
                               (state__197613))
                              (state__197613)))
                            (state__197613))
                           (state__197613)))))
                       (state__197613)))))
                   (state__197613))))
                (state__197613)))
              (state__197613)))
            (state__197613))
           (state__197613))))
        (state__197613
         []
         (clojure.core/letfn
          [(def__197138
            [arg__197161 ?__196447]
            (clojure.core/letfn
             [(state__197759
               []
               (clojure.core/let
                [x__197162 "meander.zeta"]
                (if
                 (clojure.core/= ?__196447 x__197162)
                 [?__196447]
                 (state__197760))))
              (state__197760
               []
               (if
                (clojure.core/map? arg__197161)
                (clojure.core/let
                 [VAL__197163 (.valAt arg__197161 :aliases)]
                 (if
                  (clojure.core/map? VAL__197163)
                  (clojure.core/let
                   [X__197165 (clojure.core/set VAL__197163)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__197165))
                    (clojure.core/loop
                     [search_space__197761
                      (clojure.core/seq X__197165)]
                     (if
                      (clojure.core/seq search_space__197761)
                      (clojure.core/let
                       [elem__197166
                        (clojure.core/first search_space__197761)
                        result__197762
                        (clojure.core/let
                         [elem__197166_nth_0__
                          (clojure.core/nth elem__197166 0)
                          elem__197166_nth_1__
                          (clojure.core/nth elem__197166 1)]
                         (if
                          (clojure.core/symbol? elem__197166_nth_0__)
                          (clojure.core/let
                           [X__197168
                            (clojure.core/name elem__197166_nth_0__)]
                           (if
                            (clojure.core/= ?__196447 X__197168)
                            (if
                             (clojure.core/symbol?
                              elem__197166_nth_1__)
                             (clojure.core/let
                              [X__197170
                               (clojure.core/name
                                elem__197166_nth_1__)]
                              (clojure.core/case
                               X__197170
                               ("meander.zeta")
                               [?__196447]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197762)
                        (recur
                         (clojure.core/next search_space__197761))
                        result__197762))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197759)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 2)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)]
             (if
              (clojure.core/seq? input__196443_nth_0__)
              (clojure.core/let
               [input__196443_nth_0___l__
                (clojure.core/take 1 input__196443_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__196443_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__196443_nth_0___r__
                  (clojure.core/drop 1 input__196443_nth_0__)]
                 (clojure.core/let
                  [input__196443_nth_0___l___nth_0__
                   (clojure.core/nth input__196443_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__196443_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__197148
                     (clojure.core/namespace
                      input__196443_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__196447 X__197148]
                     (clojure.core/let
                      [X__197150
                       (clojure.core/name
                        input__196443_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__197150
                       ("and")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__197138 input__196443_nth_1__ ?__196447)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__197614)
                         (clojure.core/let
                          [[?__196447] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__196443)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__196443)
                             2)
                            (clojure.core/let
                             [input__196443_nth_0__
                              (clojure.core/nth input__196443 0)
                              input__196443_nth_1__
                              (clojure.core/nth input__196443 1)]
                             (if
                              (clojure.core/seq? input__196443_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__196443_nth_0__)
                                3)
                               (clojure.core/let
                                [input__196443_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  1)
                                 input__196443_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__196443_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right
                                   input__196443_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__196443_nth_0__]
                                   (clojure.core/let
                                    [?env input__196443_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__196503
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
                                         (CATA__FN__196503
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
                               (state__197614))
                              (state__197614)))
                            (state__197614))
                           (state__197614)))))
                       (state__197614)))))
                   (state__197614))))
                (state__197614)))
              (state__197614)))
            (state__197614))
           (state__197614))))
        (state__197614
         []
         (clojure.core/letfn
          [(def__197172
            [arg__197195 ?__196448]
            (clojure.core/letfn
             [(state__197764
               []
               (clojure.core/let
                [x__197196 "meander.zeta"]
                (if
                 (clojure.core/= ?__196448 x__197196)
                 [?__196448]
                 (state__197765))))
              (state__197765
               []
               (if
                (clojure.core/map? arg__197195)
                (clojure.core/let
                 [VAL__197197 (.valAt arg__197195 :aliases)]
                 (if
                  (clojure.core/map? VAL__197197)
                  (clojure.core/let
                   [X__197199 (clojure.core/set VAL__197197)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__197199))
                    (clojure.core/loop
                     [search_space__197766
                      (clojure.core/seq X__197199)]
                     (if
                      (clojure.core/seq search_space__197766)
                      (clojure.core/let
                       [elem__197200
                        (clojure.core/first search_space__197766)
                        result__197767
                        (clojure.core/let
                         [elem__197200_nth_0__
                          (clojure.core/nth elem__197200 0)
                          elem__197200_nth_1__
                          (clojure.core/nth elem__197200 1)]
                         (if
                          (clojure.core/symbol? elem__197200_nth_0__)
                          (clojure.core/let
                           [X__197202
                            (clojure.core/name elem__197200_nth_0__)]
                           (if
                            (clojure.core/= ?__196448 X__197202)
                            (if
                             (clojure.core/symbol?
                              elem__197200_nth_1__)
                             (clojure.core/let
                              [X__197204
                               (clojure.core/name
                                elem__197200_nth_1__)]
                              (clojure.core/case
                               X__197204
                               ("meander.zeta")
                               [?__196448]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197767)
                        (recur
                         (clojure.core/next search_space__197766))
                        result__197767))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197764)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 2)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)]
             (if
              (clojure.core/seq? input__196443_nth_0__)
              (clojure.core/let
               [input__196443_nth_0___l__
                (clojure.core/take 1 input__196443_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__196443_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__196443_nth_0___r__
                  (clojure.core/drop 1 input__196443_nth_0__)]
                 (clojure.core/let
                  [input__196443_nth_0___l___nth_0__
                   (clojure.core/nth input__196443_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__196443_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__197182
                     (clojure.core/namespace
                      input__196443_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__196448 X__197182]
                     (clojure.core/let
                      [X__197184
                       (clojure.core/name
                        input__196443_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__197184
                       ("cata")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__197172 input__196443_nth_1__ ?__196448)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__197615)
                         (clojure.core/let
                          [[?__196448] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__196443)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__196443)
                             2)
                            (clojure.core/let
                             [input__196443_nth_0__
                              (clojure.core/nth input__196443 0)
                              input__196443_nth_1__
                              (clojure.core/nth input__196443 1)]
                             (if
                              (clojure.core/seq? input__196443_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__196443_nth_0__)
                                2)
                               (clojure.core/let
                                [input__196443_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__196443_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__196443_nth_0__]
                                  (clojure.core/let
                                   [?env input__196443_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__15552__auto__
                                        (CATA__FN__196503
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
                               (state__197615))
                              (state__197615)))
                            (state__197615))
                           (state__197615)))))
                       (state__197615)))))
                   (state__197615))))
                (state__197615)))
              (state__197615)))
            (state__197615))
           (state__197615))))
        (state__197615
         []
         (clojure.core/letfn
          [(def__197206
            [arg__197229 ?__196449]
            (clojure.core/letfn
             [(state__197769
               []
               (clojure.core/let
                [x__197230 "meander.zeta"]
                (if
                 (clojure.core/= ?__196449 x__197230)
                 [?__196449]
                 (state__197770))))
              (state__197770
               []
               (if
                (clojure.core/map? arg__197229)
                (clojure.core/let
                 [VAL__197231 (.valAt arg__197229 :aliases)]
                 (if
                  (clojure.core/map? VAL__197231)
                  (clojure.core/let
                   [X__197233 (clojure.core/set VAL__197231)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__197233))
                    (clojure.core/loop
                     [search_space__197771
                      (clojure.core/seq X__197233)]
                     (if
                      (clojure.core/seq search_space__197771)
                      (clojure.core/let
                       [elem__197234
                        (clojure.core/first search_space__197771)
                        result__197772
                        (clojure.core/let
                         [elem__197234_nth_0__
                          (clojure.core/nth elem__197234 0)
                          elem__197234_nth_1__
                          (clojure.core/nth elem__197234 1)]
                         (if
                          (clojure.core/symbol? elem__197234_nth_0__)
                          (clojure.core/let
                           [X__197236
                            (clojure.core/name elem__197234_nth_0__)]
                           (if
                            (clojure.core/= ?__196449 X__197236)
                            (if
                             (clojure.core/symbol?
                              elem__197234_nth_1__)
                             (clojure.core/let
                              [X__197238
                               (clojure.core/name
                                elem__197234_nth_1__)]
                              (clojure.core/case
                               X__197238
                               ("meander.zeta")
                               [?__196449]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197772)
                        (recur
                         (clojure.core/next search_space__197771))
                        result__197772))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197769)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 2)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)]
             (if
              (clojure.core/seq? input__196443_nth_0__)
              (clojure.core/let
               [input__196443_nth_0___l__
                (clojure.core/take 1 input__196443_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__196443_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__196443_nth_0___r__
                  (clojure.core/drop 1 input__196443_nth_0__)]
                 (clojure.core/let
                  [input__196443_nth_0___l___nth_0__
                   (clojure.core/nth input__196443_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__196443_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__197216
                     (clojure.core/namespace
                      input__196443_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__196449 X__197216]
                     (clojure.core/let
                      [X__197218
                       (clojure.core/name
                        input__196443_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__197218
                       ("fold")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__197206 input__196443_nth_1__ ?__196449)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__197616)
                         (clojure.core/let
                          [[?__196449] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__196443)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__196443)
                             2)
                            (clojure.core/let
                             [input__196443_nth_0__
                              (clojure.core/nth input__196443 0)
                              input__196443_nth_1__
                              (clojure.core/nth input__196443 1)]
                             (if
                              (clojure.core/seq? input__196443_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__196443_nth_0__)
                                4)
                               (clojure.core/let
                                [input__196443_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  1)
                                 input__196443_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  2)
                                 input__196443_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__196443_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__196443_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__196443_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__196443_nth_0__]
                                    (clojure.core/let
                                     [?env input__196443_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__196503
                                          ['meander.dev.parse.zeta/fold-args
                                           (clojure.core/let
                                            [CATA_RESULT__15552__auto__
                                             (CATA__FN__196503
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
                                             (CATA__FN__196503
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
                               (state__197616))
                              (state__197616)))
                            (state__197616))
                           (state__197616)))))
                       (state__197616)))))
                   (state__197616))))
                (state__197616)))
              (state__197616)))
            (state__197616))
           (state__197616))))
        (state__197616
         []
         (if
          (clojure.core/vector? input__196443)
          (if
           (clojure.core/= (clojure.core/count input__196443) 5)
           (clojure.core/let
            [input__196443_nth_0__
             (clojure.core/nth input__196443 0)
             input__196443_nth_1__
             (clojure.core/nth input__196443 1)
             input__196443_nth_2__
             (clojure.core/nth input__196443 2)
             input__196443_nth_3__
             (clojure.core/nth input__196443 3)
             input__196443_nth_4__
             (clojure.core/nth input__196443 4)]
            (if
             (clojure.core/=
              input__196443_nth_0__
              'meander.dev.parse.zeta/fold-args)
             (if
              (clojure.core/map? input__196443_nth_1__)
              (clojure.core/let
               [VAL__197241 (.valAt input__196443_nth_1__ :tag)]
               (clojure.core/case
                VAL__197241
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__196443_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__196443_nth_2__]
                  (clojure.core/let
                   [?fold-function input__196443_nth_3__]
                   (clojure.core/let
                    [?form input__196443_nth_4__]
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
                (state__197617)))
              (state__197617))
             (state__197617)))
           (state__197617))
          (state__197617)))
        (state__197617
         []
         (clojure.core/letfn
          [(def__197243
            [arg__197266 ?__196450]
            (clojure.core/letfn
             [(state__197774
               []
               (clojure.core/let
                [x__197267 "meander.zeta"]
                (if
                 (clojure.core/= ?__196450 x__197267)
                 [?__196450]
                 (state__197775))))
              (state__197775
               []
               (if
                (clojure.core/map? arg__197266)
                (clojure.core/let
                 [VAL__197268 (.valAt arg__197266 :aliases)]
                 (if
                  (clojure.core/map? VAL__197268)
                  (clojure.core/let
                   [X__197270 (clojure.core/set VAL__197268)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__197270))
                    (clojure.core/loop
                     [search_space__197776
                      (clojure.core/seq X__197270)]
                     (if
                      (clojure.core/seq search_space__197776)
                      (clojure.core/let
                       [elem__197271
                        (clojure.core/first search_space__197776)
                        result__197777
                        (clojure.core/let
                         [elem__197271_nth_0__
                          (clojure.core/nth elem__197271 0)
                          elem__197271_nth_1__
                          (clojure.core/nth elem__197271 1)]
                         (if
                          (clojure.core/symbol? elem__197271_nth_0__)
                          (clojure.core/let
                           [X__197273
                            (clojure.core/name elem__197271_nth_0__)]
                           (if
                            (clojure.core/= ?__196450 X__197273)
                            (if
                             (clojure.core/symbol?
                              elem__197271_nth_1__)
                             (clojure.core/let
                              [X__197275
                               (clojure.core/name
                                elem__197271_nth_1__)]
                              (clojure.core/case
                               X__197275
                               ("meander.zeta")
                               [?__196450]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197777)
                        (recur
                         (clojure.core/next search_space__197776))
                        result__197777))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197774)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 2)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)]
             (if
              (clojure.core/seq? input__196443_nth_0__)
              (clojure.core/let
               [input__196443_nth_0___l__
                (clojure.core/take 1 input__196443_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__196443_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__196443_nth_0___r__
                  (clojure.core/drop 1 input__196443_nth_0__)]
                 (clojure.core/let
                  [input__196443_nth_0___l___nth_0__
                   (clojure.core/nth input__196443_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__196443_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__197253
                     (clojure.core/namespace
                      input__196443_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__196450 X__197253]
                     (clojure.core/let
                      [X__197255
                       (clojure.core/name
                        input__196443_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__197255
                       ("let")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__197243 input__196443_nth_1__ ?__196450)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__197618)
                         (clojure.core/let
                          [[?__196450] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__196443)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__196443)
                             2)
                            (clojure.core/let
                             [input__196443_nth_0__
                              (clojure.core/nth input__196443 0)
                              input__196443_nth_1__
                              (clojure.core/nth input__196443 1)]
                             (if
                              (clojure.core/seq? input__196443_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__196443_nth_0__)
                                3)
                               (clojure.core/let
                                [input__196443_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  0)
                                 input__196443_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  1)
                                 input__196443_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__196443_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__196443_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next
                                    input__196443_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__196443_nth_0__]
                                    (clojure.core/let
                                     [?env input__196443_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__15552__auto__
                                          (CATA__FN__196503
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
                                          (CATA__FN__196503
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
                               (state__197618))
                              (state__197618)))
                            (state__197618))
                           (state__197618)))))
                       (state__197618)))))
                   (state__197618))))
                (state__197618)))
              (state__197618)))
            (state__197618))
           (state__197618))))
        (state__197618
         []
         (clojure.core/letfn
          [(def__197277
            [arg__197300 ?__196451]
            (clojure.core/letfn
             [(state__197779
               []
               (clojure.core/let
                [x__197301 "meander.zeta"]
                (if
                 (clojure.core/= ?__196451 x__197301)
                 [?__196451]
                 (state__197780))))
              (state__197780
               []
               (if
                (clojure.core/map? arg__197300)
                (clojure.core/let
                 [VAL__197302 (.valAt arg__197300 :aliases)]
                 (if
                  (clojure.core/map? VAL__197302)
                  (clojure.core/let
                   [X__197304 (clojure.core/set VAL__197302)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__197304))
                    (clojure.core/loop
                     [search_space__197781
                      (clojure.core/seq X__197304)]
                     (if
                      (clojure.core/seq search_space__197781)
                      (clojure.core/let
                       [elem__197305
                        (clojure.core/first search_space__197781)
                        result__197782
                        (clojure.core/let
                         [elem__197305_nth_0__
                          (clojure.core/nth elem__197305 0)
                          elem__197305_nth_1__
                          (clojure.core/nth elem__197305 1)]
                         (if
                          (clojure.core/symbol? elem__197305_nth_0__)
                          (clojure.core/let
                           [X__197307
                            (clojure.core/name elem__197305_nth_0__)]
                           (if
                            (clojure.core/= ?__196451 X__197307)
                            (if
                             (clojure.core/symbol?
                              elem__197305_nth_1__)
                             (clojure.core/let
                              [X__197309
                               (clojure.core/name
                                elem__197305_nth_1__)]
                              (clojure.core/case
                               X__197309
                               ("meander.zeta")
                               [?__196451]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197782)
                        (recur
                         (clojure.core/next search_space__197781))
                        result__197782))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197779)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 2)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)]
             (if
              (clojure.core/seq? input__196443_nth_0__)
              (clojure.core/let
               [input__196443_nth_0___l__
                (clojure.core/take 1 input__196443_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__196443_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__196443_nth_0___r__
                  (clojure.core/drop 1 input__196443_nth_0__)]
                 (clojure.core/let
                  [input__196443_nth_0___l___nth_0__
                   (clojure.core/nth input__196443_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__196443_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__197287
                     (clojure.core/namespace
                      input__196443_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__196451 X__197287]
                     (clojure.core/let
                      [X__197289
                       (clojure.core/name
                        input__196443_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__197289
                       ("not")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__197277 input__196443_nth_1__ ?__196451)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__197619)
                         (clojure.core/let
                          [[?__196451] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__196443)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__196443)
                             2)
                            (clojure.core/let
                             [input__196443_nth_0__
                              (clojure.core/nth input__196443 0)
                              input__196443_nth_1__
                              (clojure.core/nth input__196443 1)]
                             (if
                              (clojure.core/seq? input__196443_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__196443_nth_0__)
                                2)
                               (clojure.core/let
                                [input__196443_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__196443_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__196443_nth_0__]
                                  (clojure.core/let
                                   [?env input__196443_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__15552__auto__
                                        (CATA__FN__196503
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
                               (state__197619))
                              (state__197619)))
                            (state__197619))
                           (state__197619)))))
                       (state__197619)))))
                   (state__197619))))
                (state__197619)))
              (state__197619)))
            (state__197619))
           (state__197619))))
        (state__197619
         []
         (clojure.core/letfn
          [(def__197311
            [arg__197334 ?__196452]
            (clojure.core/letfn
             [(state__197784
               []
               (clojure.core/let
                [x__197335 "meander.zeta"]
                (if
                 (clojure.core/= ?__196452 x__197335)
                 [?__196452]
                 (state__197785))))
              (state__197785
               []
               (if
                (clojure.core/map? arg__197334)
                (clojure.core/let
                 [VAL__197336 (.valAt arg__197334 :aliases)]
                 (if
                  (clojure.core/map? VAL__197336)
                  (clojure.core/let
                   [X__197338 (clojure.core/set VAL__197336)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__197338))
                    (clojure.core/loop
                     [search_space__197786
                      (clojure.core/seq X__197338)]
                     (if
                      (clojure.core/seq search_space__197786)
                      (clojure.core/let
                       [elem__197339
                        (clojure.core/first search_space__197786)
                        result__197787
                        (clojure.core/let
                         [elem__197339_nth_0__
                          (clojure.core/nth elem__197339 0)
                          elem__197339_nth_1__
                          (clojure.core/nth elem__197339 1)]
                         (if
                          (clojure.core/symbol? elem__197339_nth_0__)
                          (clojure.core/let
                           [X__197341
                            (clojure.core/name elem__197339_nth_0__)]
                           (if
                            (clojure.core/= ?__196452 X__197341)
                            (if
                             (clojure.core/symbol?
                              elem__197339_nth_1__)
                             (clojure.core/let
                              [X__197343
                               (clojure.core/name
                                elem__197339_nth_1__)]
                              (clojure.core/case
                               X__197343
                               ("meander.zeta")
                               [?__196452]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197787)
                        (recur
                         (clojure.core/next search_space__197786))
                        result__197787))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197784)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 2)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)]
             (if
              (clojure.core/seq? input__196443_nth_0__)
              (clojure.core/let
               [input__196443_nth_0___l__
                (clojure.core/take 1 input__196443_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__196443_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__196443_nth_0___r__
                  (clojure.core/drop 1 input__196443_nth_0__)]
                 (clojure.core/let
                  [input__196443_nth_0___l___nth_0__
                   (clojure.core/nth input__196443_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__196443_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__197321
                     (clojure.core/namespace
                      input__196443_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__196452 X__197321]
                     (clojure.core/let
                      [X__197323
                       (clojure.core/name
                        input__196443_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__197323
                       ("or")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__197311 input__196443_nth_1__ ?__196452)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__197620)
                         (clojure.core/let
                          [[?__196452] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__196443)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__196443)
                             2)
                            (clojure.core/let
                             [input__196443_nth_0__
                              (clojure.core/nth input__196443 0)
                              input__196443_nth_1__
                              (clojure.core/nth input__196443 1)]
                             (if
                              (clojure.core/seq? input__196443_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__196443_nth_0__)
                                3)
                               (clojure.core/let
                                [input__196443_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  1)
                                 input__196443_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__196443_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right
                                   input__196443_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__196443_nth_0__]
                                   (clojure.core/let
                                    [?env input__196443_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__196503
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
                                         (CATA__FN__196503
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
                               (state__197620))
                              (state__197620)))
                            (state__197620))
                           (state__197620)))))
                       (state__197620)))))
                   (state__197620))))
                (state__197620)))
              (state__197620)))
            (state__197620))
           (state__197620))))
        (state__197620
         []
         (clojure.core/letfn
          [(def__197345
            [arg__197368 ?__196453]
            (clojure.core/letfn
             [(state__197789
               []
               (clojure.core/let
                [x__197369 "meander.zeta"]
                (if
                 (clojure.core/= ?__196453 x__197369)
                 [?__196453]
                 (state__197790))))
              (state__197790
               []
               (if
                (clojure.core/map? arg__197368)
                (clojure.core/let
                 [VAL__197370 (.valAt arg__197368 :aliases)]
                 (if
                  (clojure.core/map? VAL__197370)
                  (clojure.core/let
                   [X__197372 (clojure.core/set VAL__197370)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__197372))
                    (clojure.core/loop
                     [search_space__197791
                      (clojure.core/seq X__197372)]
                     (if
                      (clojure.core/seq search_space__197791)
                      (clojure.core/let
                       [elem__197373
                        (clojure.core/first search_space__197791)
                        result__197792
                        (clojure.core/let
                         [elem__197373_nth_0__
                          (clojure.core/nth elem__197373 0)
                          elem__197373_nth_1__
                          (clojure.core/nth elem__197373 1)]
                         (if
                          (clojure.core/symbol? elem__197373_nth_0__)
                          (clojure.core/let
                           [X__197375
                            (clojure.core/name elem__197373_nth_0__)]
                           (if
                            (clojure.core/= ?__196453 X__197375)
                            (if
                             (clojure.core/symbol?
                              elem__197373_nth_1__)
                             (clojure.core/let
                              [X__197377
                               (clojure.core/name
                                elem__197373_nth_1__)]
                              (clojure.core/case
                               X__197377
                               ("meander.zeta")
                               [?__196453]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197792)
                        (recur
                         (clojure.core/next search_space__197791))
                        result__197792))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197789)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 2)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)]
             (if
              (clojure.core/seq? input__196443_nth_0__)
              (clojure.core/let
               [input__196443_nth_0___l__
                (clojure.core/take 1 input__196443_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__196443_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__196443_nth_0___r__
                  (clojure.core/drop 1 input__196443_nth_0__)]
                 (clojure.core/let
                  [input__196443_nth_0___l___nth_0__
                   (clojure.core/nth input__196443_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__196443_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__197355
                     (clojure.core/namespace
                      input__196443_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__196453 X__197355]
                     (clojure.core/let
                      [X__197357
                       (clojure.core/name
                        input__196443_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__197357
                       ("re")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__197345 input__196443_nth_1__ ?__196453)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__197621)
                         (clojure.core/let
                          [[?__196453] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__196443)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__196443)
                             2)
                            (clojure.core/let
                             [input__196443_nth_0__
                              (clojure.core/nth input__196443 0)
                              input__196443_nth_1__
                              (clojure.core/nth input__196443 1)]
                             (if
                              (clojure.core/seq? input__196443_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__196443_nth_0__)
                                2)
                               (clojure.core/let
                                [input__196443_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__196443_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__196443_nth_0__]
                                  (clojure.core/let
                                   [?env input__196443_nth_1__]
                                   (try
                                    [{:tag :regex,
                                      :regex ?regex,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__16492__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__16492__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__16492__auto__))))))))
                               (state__197621))
                              (state__197621)))
                            (state__197621))
                           (state__197621)))))
                       (state__197621)))))
                   (state__197621))))
                (state__197621)))
              (state__197621)))
            (state__197621))
           (state__197621))))
        (state__197621
         []
         (clojure.core/letfn
          [(def__197379
            [arg__197402 ?__196454]
            (clojure.core/letfn
             [(state__197794
               []
               (clojure.core/let
                [x__197403 "meander.zeta"]
                (if
                 (clojure.core/= ?__196454 x__197403)
                 [?__196454]
                 (state__197795))))
              (state__197795
               []
               (if
                (clojure.core/map? arg__197402)
                (clojure.core/let
                 [VAL__197404 (.valAt arg__197402 :aliases)]
                 (if
                  (clojure.core/map? VAL__197404)
                  (clojure.core/let
                   [X__197406 (clojure.core/set VAL__197404)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__197406))
                    (clojure.core/loop
                     [search_space__197796
                      (clojure.core/seq X__197406)]
                     (if
                      (clojure.core/seq search_space__197796)
                      (clojure.core/let
                       [elem__197407
                        (clojure.core/first search_space__197796)
                        result__197797
                        (clojure.core/let
                         [elem__197407_nth_0__
                          (clojure.core/nth elem__197407 0)
                          elem__197407_nth_1__
                          (clojure.core/nth elem__197407 1)]
                         (if
                          (clojure.core/symbol? elem__197407_nth_0__)
                          (clojure.core/let
                           [X__197409
                            (clojure.core/name elem__197407_nth_0__)]
                           (if
                            (clojure.core/= ?__196454 X__197409)
                            (if
                             (clojure.core/symbol?
                              elem__197407_nth_1__)
                             (clojure.core/let
                              [X__197411
                               (clojure.core/name
                                elem__197407_nth_1__)]
                              (clojure.core/case
                               X__197411
                               ("meander.zeta")
                               [?__196454]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197797)
                        (recur
                         (clojure.core/next search_space__197796))
                        result__197797))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197794)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 2)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)]
             (if
              (clojure.core/seq? input__196443_nth_0__)
              (clojure.core/let
               [input__196443_nth_0___l__
                (clojure.core/take 1 input__196443_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__196443_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__196443_nth_0___r__
                  (clojure.core/drop 1 input__196443_nth_0__)]
                 (clojure.core/let
                  [input__196443_nth_0___l___nth_0__
                   (clojure.core/nth input__196443_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__196443_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__197389
                     (clojure.core/namespace
                      input__196443_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__196454 X__197389]
                     (clojure.core/let
                      [X__197391
                       (clojure.core/name
                        input__196443_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__197391
                       ("re")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__197379 input__196443_nth_1__ ?__196454)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__197622)
                         (clojure.core/let
                          [[?__196454] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__196443)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__196443)
                             2)
                            (clojure.core/let
                             [input__196443_nth_0__
                              (clojure.core/nth input__196443 0)
                              input__196443_nth_1__
                              (clojure.core/nth input__196443 1)]
                             (if
                              (clojure.core/seq? input__196443_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__196443_nth_0__)
                                3)
                               (clojure.core/let
                                [input__196443_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  1)
                                 input__196443_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__196443_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__196443_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__196443_nth_0__]
                                   (clojure.core/let
                                    [?env input__196443_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__196503
                                          [?capture ?env])]
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
                               (state__197622))
                              (state__197622)))
                            (state__197622))
                           (state__197622)))))
                       (state__197622)))))
                   (state__197622))))
                (state__197622)))
              (state__197622)))
            (state__197622))
           (state__197622))))
        (state__197622
         []
         (clojure.core/letfn
          [(def__197413
            [arg__197436 ?__196455]
            (clojure.core/letfn
             [(state__197799
               []
               (clojure.core/let
                [x__197437 "meander.zeta"]
                (if
                 (clojure.core/= ?__196455 x__197437)
                 [?__196455]
                 (state__197800))))
              (state__197800
               []
               (if
                (clojure.core/map? arg__197436)
                (clojure.core/let
                 [VAL__197438 (.valAt arg__197436 :aliases)]
                 (if
                  (clojure.core/map? VAL__197438)
                  (clojure.core/let
                   [X__197440 (clojure.core/set VAL__197438)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__197440))
                    (clojure.core/loop
                     [search_space__197801
                      (clojure.core/seq X__197440)]
                     (if
                      (clojure.core/seq search_space__197801)
                      (clojure.core/let
                       [elem__197441
                        (clojure.core/first search_space__197801)
                        result__197802
                        (clojure.core/let
                         [elem__197441_nth_0__
                          (clojure.core/nth elem__197441 0)
                          elem__197441_nth_1__
                          (clojure.core/nth elem__197441 1)]
                         (if
                          (clojure.core/symbol? elem__197441_nth_0__)
                          (clojure.core/let
                           [X__197443
                            (clojure.core/name elem__197441_nth_0__)]
                           (if
                            (clojure.core/= ?__196455 X__197443)
                            (if
                             (clojure.core/symbol?
                              elem__197441_nth_1__)
                             (clojure.core/let
                              [X__197445
                               (clojure.core/name
                                elem__197441_nth_1__)]
                              (clojure.core/case
                               X__197445
                               ("meander.zeta")
                               [?__196455]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197802)
                        (recur
                         (clojure.core/next search_space__197801))
                        result__197802))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197799)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 2)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)]
             (if
              (clojure.core/seq? input__196443_nth_0__)
              (clojure.core/let
               [input__196443_nth_0___l__
                (clojure.core/take 1 input__196443_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__196443_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__196443_nth_0___r__
                  (clojure.core/drop 1 input__196443_nth_0__)]
                 (clojure.core/let
                  [input__196443_nth_0___l___nth_0__
                   (clojure.core/nth input__196443_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__196443_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__197423
                     (clojure.core/namespace
                      input__196443_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__196455 X__197423]
                     (clojure.core/let
                      [X__197425
                       (clojure.core/name
                        input__196443_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__197425
                       ("string")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__197413 input__196443_nth_1__ ?__196455)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__197623)
                         (clojure.core/let
                          [[?__196455] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__196443)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__196443)
                             2)
                            (clojure.core/let
                             [input__196443_nth_0__
                              (clojure.core/nth input__196443 0)
                              input__196443_nth_1__
                              (clojure.core/nth input__196443 1)]
                             (if
                              (clojure.core/seq? input__196443_nth_0__)
                              (clojure.core/let
                               [input__196443_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__196443_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__196443_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__196443_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__196443_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__196443_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__196443_nth_0__]
                                   (clojure.core/let
                                    [?env input__196443_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__196503
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
                                (state__197623)))
                              (state__197623)))
                            (state__197623))
                           (state__197623)))))
                       (state__197623)))))
                   (state__197623))))
                (state__197623)))
              (state__197623)))
            (state__197623))
           (state__197623))))
        (state__197623
         []
         (clojure.core/letfn
          [(def__197447
            [arg__197470 ?__196456]
            (clojure.core/letfn
             [(state__197804
               []
               (clojure.core/let
                [x__197471 "meander.zeta"]
                (if
                 (clojure.core/= ?__196456 x__197471)
                 [?__196456]
                 (state__197805))))
              (state__197805
               []
               (if
                (clojure.core/map? arg__197470)
                (clojure.core/let
                 [VAL__197472 (.valAt arg__197470 :aliases)]
                 (if
                  (clojure.core/map? VAL__197472)
                  (clojure.core/let
                   [X__197474 (clojure.core/set VAL__197472)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__197474))
                    (clojure.core/loop
                     [search_space__197806
                      (clojure.core/seq X__197474)]
                     (if
                      (clojure.core/seq search_space__197806)
                      (clojure.core/let
                       [elem__197475
                        (clojure.core/first search_space__197806)
                        result__197807
                        (clojure.core/let
                         [elem__197475_nth_0__
                          (clojure.core/nth elem__197475 0)
                          elem__197475_nth_1__
                          (clojure.core/nth elem__197475 1)]
                         (if
                          (clojure.core/symbol? elem__197475_nth_0__)
                          (clojure.core/let
                           [X__197477
                            (clojure.core/name elem__197475_nth_0__)]
                           (if
                            (clojure.core/= ?__196456 X__197477)
                            (if
                             (clojure.core/symbol?
                              elem__197475_nth_1__)
                             (clojure.core/let
                              [X__197479
                               (clojure.core/name
                                elem__197475_nth_1__)]
                              (clojure.core/case
                               X__197479
                               ("meander.zeta")
                               [?__196456]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197807)
                        (recur
                         (clojure.core/next search_space__197806))
                        result__197807))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197804)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 2)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)]
             (if
              (clojure.core/seq? input__196443_nth_0__)
              (clojure.core/let
               [input__196443_nth_0___l__
                (clojure.core/take 1 input__196443_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__196443_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__196443_nth_0___r__
                  (clojure.core/drop 1 input__196443_nth_0__)]
                 (clojure.core/let
                  [input__196443_nth_0___l___nth_0__
                   (clojure.core/nth input__196443_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__196443_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__197457
                     (clojure.core/namespace
                      input__196443_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__196456 X__197457]
                     (clojure.core/let
                      [X__197459
                       (clojure.core/name
                        input__196443_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__197459
                       ("symbol")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__197447 input__196443_nth_1__ ?__196456)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__197624)
                         (clojure.core/let
                          [[?__196456] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__196443)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__196443)
                             2)
                            (clojure.core/let
                             [input__196443_nth_0__
                              (clojure.core/nth input__196443 0)
                              input__196443_nth_1__
                              (clojure.core/nth input__196443 1)]
                             (if
                              (clojure.core/seq? input__196443_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__196443_nth_0__)
                                2)
                               (clojure.core/let
                                [input__196443_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__196443_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__196443_nth_0__]
                                  (clojure.core/let
                                   [?env input__196443_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__15552__auto__
                                        (CATA__FN__196503
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
                               (state__197624))
                              (state__197624)))
                            (state__197624))
                           (state__197624)))))
                       (state__197624)))))
                   (state__197624))))
                (state__197624)))
              (state__197624)))
            (state__197624))
           (state__197624))))
        (state__197624
         []
         (clojure.core/letfn
          [(def__197481
            [arg__197504 ?__196457]
            (clojure.core/letfn
             [(state__197809
               []
               (clojure.core/let
                [x__197505 "meander.zeta"]
                (if
                 (clojure.core/= ?__196457 x__197505)
                 [?__196457]
                 (state__197810))))
              (state__197810
               []
               (if
                (clojure.core/map? arg__197504)
                (clojure.core/let
                 [VAL__197506 (.valAt arg__197504 :aliases)]
                 (if
                  (clojure.core/map? VAL__197506)
                  (clojure.core/let
                   [X__197508 (clojure.core/set VAL__197506)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__197508))
                    (clojure.core/loop
                     [search_space__197811
                      (clojure.core/seq X__197508)]
                     (if
                      (clojure.core/seq search_space__197811)
                      (clojure.core/let
                       [elem__197509
                        (clojure.core/first search_space__197811)
                        result__197812
                        (clojure.core/let
                         [elem__197509_nth_0__
                          (clojure.core/nth elem__197509 0)
                          elem__197509_nth_1__
                          (clojure.core/nth elem__197509 1)]
                         (if
                          (clojure.core/symbol? elem__197509_nth_0__)
                          (clojure.core/let
                           [X__197511
                            (clojure.core/name elem__197509_nth_0__)]
                           (if
                            (clojure.core/= ?__196457 X__197511)
                            (if
                             (clojure.core/symbol?
                              elem__197509_nth_1__)
                             (clojure.core/let
                              [X__197513
                               (clojure.core/name
                                elem__197509_nth_1__)]
                              (clojure.core/case
                               X__197513
                               ("meander.zeta")
                               [?__196457]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197812)
                        (recur
                         (clojure.core/next search_space__197811))
                        result__197812))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197809)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 2)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)]
             (if
              (clojure.core/seq? input__196443_nth_0__)
              (clojure.core/let
               [input__196443_nth_0___l__
                (clojure.core/take 1 input__196443_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__196443_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__196443_nth_0___r__
                  (clojure.core/drop 1 input__196443_nth_0__)]
                 (clojure.core/let
                  [input__196443_nth_0___l___nth_0__
                   (clojure.core/nth input__196443_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__196443_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__197491
                     (clojure.core/namespace
                      input__196443_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__196457 X__197491]
                     (clojure.core/let
                      [X__197493
                       (clojure.core/name
                        input__196443_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__197493
                       ("symbol")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__197481 input__196443_nth_1__ ?__196457)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__197625)
                         (clojure.core/let
                          [[?__196457] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__196443)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__196443)
                             2)
                            (clojure.core/let
                             [input__196443_nth_0__
                              (clojure.core/nth input__196443 0)
                              input__196443_nth_1__
                              (clojure.core/nth input__196443 1)]
                             (if
                              (clojure.core/seq? input__196443_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__196443_nth_0__)
                                3)
                               (clojure.core/let
                                [input__196443_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  1)
                                 input__196443_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__196443_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__196443_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__196443_nth_0__]
                                   (clojure.core/let
                                    [?env input__196443_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__15552__auto__
                                         (CATA__FN__196503
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
                                         (CATA__FN__196503
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
                               (state__197625))
                              (state__197625)))
                            (state__197625))
                           (state__197625)))))
                       (state__197625)))))
                   (state__197625))))
                (state__197625)))
              (state__197625)))
            (state__197625))
           (state__197625))))
        (state__197625
         []
         (clojure.core/letfn
          [(def__197515
            [arg__197538 ?__196458]
            (clojure.core/letfn
             [(state__197814
               []
               (clojure.core/let
                [x__197539 "meander.zeta"]
                (if
                 (clojure.core/= ?__196458 x__197539)
                 [?__196458]
                 (state__197815))))
              (state__197815
               []
               (if
                (clojure.core/map? arg__197538)
                (clojure.core/let
                 [VAL__197540 (.valAt arg__197538 :aliases)]
                 (if
                  (clojure.core/map? VAL__197540)
                  (clojure.core/let
                   [X__197542 (clojure.core/set VAL__197540)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__197542))
                    (clojure.core/loop
                     [search_space__197816
                      (clojure.core/seq X__197542)]
                     (if
                      (clojure.core/seq search_space__197816)
                      (clojure.core/let
                       [elem__197543
                        (clojure.core/first search_space__197816)
                        result__197817
                        (clojure.core/let
                         [elem__197543_nth_0__
                          (clojure.core/nth elem__197543 0)
                          elem__197543_nth_1__
                          (clojure.core/nth elem__197543 1)]
                         (if
                          (clojure.core/symbol? elem__197543_nth_0__)
                          (clojure.core/let
                           [X__197545
                            (clojure.core/name elem__197543_nth_0__)]
                           (if
                            (clojure.core/= ?__196458 X__197545)
                            (if
                             (clojure.core/symbol?
                              elem__197543_nth_1__)
                             (clojure.core/let
                              [X__197547
                               (clojure.core/name
                                elem__197543_nth_1__)]
                              (clojure.core/case
                               X__197547
                               ("meander.zeta")
                               [?__196458]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__197817)
                        (recur
                         (clojure.core/next search_space__197816))
                        result__197817))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__197814)))]
          (if
           (clojure.core/vector? input__196443)
           (if
            (clojure.core/= (clojure.core/count input__196443) 2)
            (clojure.core/let
             [input__196443_nth_0__
              (clojure.core/nth input__196443 0)
              input__196443_nth_1__
              (clojure.core/nth input__196443 1)]
             (if
              (clojure.core/seq? input__196443_nth_0__)
              (clojure.core/let
               [input__196443_nth_0___l__
                (clojure.core/take 1 input__196443_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__196443_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__196443_nth_0___r__
                  (clojure.core/drop 1 input__196443_nth_0__)]
                 (clojure.core/let
                  [input__196443_nth_0___l___nth_0__
                   (clojure.core/nth input__196443_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__196443_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__197525
                     (clojure.core/namespace
                      input__196443_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__196458 X__197525]
                     (clojure.core/let
                      [X__197527
                       (clojure.core/name
                        input__196443_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__197527
                       ("symbol")
                       (clojure.core/let
                        [x__14249__auto__
                         (def__197515 input__196443_nth_1__ ?__196458)]
                        (if
                         (meander.runtime.zeta/fail? x__14249__auto__)
                         (state__197626)
                         (clojure.core/let
                          [[?__196458] x__14249__auto__]
                          (if
                           (clojure.core/vector? input__196443)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__196443)
                             2)
                            (clojure.core/let
                             [input__196443_nth_0__
                              (clojure.core/nth input__196443 0)
                              input__196443_nth_1__
                              (clojure.core/nth input__196443 1)]
                             (if
                              (clojure.core/seq? input__196443_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__196443_nth_0__)
                                5)
                               (clojure.core/let
                                [input__196443_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  1)
                                 input__196443_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  2)
                                 input__196443_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  3)
                                 input__196443_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__196443_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__196443_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__196443_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name
                                    input__196443_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__196443_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__196443_nth_0__]
                                     (clojure.core/let
                                      [?env input__196443_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__15552__auto__
                                           (CATA__FN__196503
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
                                           (CATA__FN__196503
                                            [?namespace ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__15552__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__15552__auto__
                                            0))),
                                         :as-pattern
                                         (clojure.core/let
                                          [CATA_RESULT__15552__auto__
                                           (CATA__FN__196503
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
                                         (throw
                                          e__16492__auto__)))))))))
                                 (state__197626)))
                               (state__197626))
                              (state__197626)))
                            (state__197626))
                           (state__197626)))))
                       (state__197626)))))
                   (state__197626))))
                (state__197626)))
              (state__197626)))
            (state__197626))
           (state__197626))))
        (state__197626
         []
         (if
          (clojure.core/vector? input__196443)
          (if
           (clojure.core/= (clojure.core/count input__196443) 2)
           (clojure.core/let
            [input__196443_nth_0__ (clojure.core/nth input__196443 0)]
            (clojure.core/letfn
             [(state__197819
               []
               (clojure.core/let
                [input__196443_nth_1__
                 (clojure.core/nth input__196443 1)]
                (clojure.core/letfn
                 [(state__197824
                   []
                   (if
                    (clojure.core/seq? input__196443_nth_0__)
                    (clojure.core/let
                     [?sequence input__196443_nth_0__]
                     (clojure.core/let
                      [?env input__196443_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__15552__auto__
                           (CATA__FN__196503
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
                    (state__197825)))
                  (state__197825
                   []
                   (if
                    (clojure.core/map? input__196443_nth_0__)
                    (clojure.core/let
                     [?map input__196443_nth_0__]
                     (clojure.core/let
                      [?env input__196443_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__15552__auto__
                           (CATA__FN__196503
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
                    (state__197820)))]
                 (state__197824))))
              (state__197820
               []
               (if
                (clojure.core/symbol? input__196443_nth_0__)
                (clojure.core/let
                 [X__197557
                  (clojure.core/namespace input__196443_nth_0__)]
                 (clojure.core/let
                  [X__197559 (clojure.core/name input__196443_nth_0__)]
                  (if
                   (clojure.core/string? X__197559)
                   (clojure.core/letfn
                    [(state__197826
                      []
                      (clojure.core/let
                       [ret__197560
                        (clojure.core/re-matches #"_.*" X__197559)]
                       (if
                        (clojure.core/some? ret__197560)
                        (clojure.core/let
                         [?name ret__197560]
                         (clojure.core/let
                          [?symbol input__196443_nth_0__]
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
                        (state__197827))))
                     (state__197827
                      []
                      (clojure.core/let
                       [ret__197567
                        (clojure.core/re-matches #".+#" X__197559)]
                       (if
                        (clojure.core/some? ret__197567)
                        (clojure.core/let
                         [?name ret__197567]
                         (clojure.core/let
                          [?symbol input__196443_nth_0__]
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
                        (state__197828))))
                     (state__197828
                      []
                      (clojure.core/let
                       [ret__197574
                        (clojure.core/re-matches #"%.+" X__197559)]
                       (if
                        (clojure.core/some? ret__197574)
                        (clojure.core/let
                         [?name ret__197574]
                         (clojure.core/let
                          [?symbol input__196443_nth_0__]
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
                        (state__197829))))
                     (state__197829
                      []
                      (clojure.core/let
                       [ret__197581
                        (clojure.core/re-matches #"\*.+" X__197559)]
                       (if
                        (clojure.core/some? ret__197581)
                        (clojure.core/let
                         [?name ret__197581]
                         (clojure.core/let
                          [?symbol input__196443_nth_0__]
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
                        (state__197830))))
                     (state__197830
                      []
                      (clojure.core/let
                       [ret__197588
                        (clojure.core/re-matches #"\!.+" X__197559)]
                       (if
                        (clojure.core/some? ret__197588)
                        (clojure.core/let
                         [?name ret__197588]
                         (clojure.core/let
                          [?symbol input__196443_nth_0__]
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
                        (state__197831))))
                     (state__197831
                      []
                      (clojure.core/let
                       [ret__197595
                        (clojure.core/re-matches #"\?.+" X__197559)]
                       (if
                        (clojure.core/some? ret__197595)
                        (clojure.core/let
                         [?name ret__197595]
                         (clojure.core/let
                          [?symbol input__196443_nth_0__]
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
                        (state__197821))))]
                    (state__197826))
                   (state__197821))))
                (state__197821)))
              (state__197821
               []
               (if
                (string? input__196443_nth_0__)
                (clojure.core/let
                 [?x input__196443_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__16492__auto__
                   (if
                    (meander.runtime.zeta/fail? e__16492__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__16492__auto__)))))
                (state__197822)))
              (state__197822
               []
               (if
                (char? input__196443_nth_0__)
                (clojure.core/let
                 [?x input__196443_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__16492__auto__
                   (if
                    (meander.runtime.zeta/fail? e__16492__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__16492__auto__)))))
                (state__197823)))
              (state__197823
               []
               (clojure.core/let
                [?x input__196443_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__16492__auto__
                  (if
                   (meander.runtime.zeta/fail? e__16492__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__16492__auto__))))))]
             (state__197819)))
           (state__197627))
          (state__197627)))
        (state__197627
         []
         (clojure.core/let
          [?x input__196443]
          (try
           [?x]
           (catch
            java.lang.Exception
            e__16492__auto__
            (if
             (meander.runtime.zeta/fail? e__16492__auto__)
             (meander.runtime.zeta/fail)
             (throw e__16492__auto__))))))]
       (state__197602)))]
    (clojure.core/let
     [x__14249__auto__ (CATA__FN__196503 input__196443)]
     (if
      (meander.runtime.zeta/fail? x__14249__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__196506] x__14249__auto__]
       CATA_RETURN__196506))))]
  (if
   (meander.runtime.zeta/fail? ret__14429__auto__)
   nil
   ret__14429__auto__)))
