(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__57548]
 (let*
  [ret__8106__auto__
   (clojure.core/letfn
    [(CATA__FN__57598
      [input__57548]
      (clojure.core/letfn
       [(state__58586
         []
         (if
          (clojure.core/vector? input__57548)
          (if
           (clojure.core/= (clojure.core/count input__57548) 3)
           (clojure.core/let
            [input__57548_nth_0__
             (clojure.core/nth input__57548 0)
             input__57548_nth_1__
             (clojure.core/nth input__57548 1)
             input__57548_nth_2__
             (clojure.core/nth input__57548 2)]
            (clojure.core/case
             input__57548_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__57548_nth_1__)
              (clojure.core/letfn
               [(state__58609
                 []
                 (clojure.core/case
                  input__57548_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__57548_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__10169__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10169__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10169__auto__)))))
                  (state__58610)))
                (state__58610
                 []
                 (clojure.core/let
                  [n__57605
                   (clojure.core/count input__57548_nth_1__)
                   m__57606
                   (clojure.core/max 0 (clojure.core/- n__57605 2))
                   input__57548_nth_1___l__
                   (clojure.core/subvec
                    input__57548_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__57548_nth_1__)
                     m__57606))
                   input__57548_nth_1___r__
                   (clojure.core/subvec input__57548_nth_1__ m__57606)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__57548_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__57548_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__57548_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__57548_nth_1___r___nth_0__
                       (clojure.core/nth input__57548_nth_1___r__ 0)
                       input__57548_nth_1___r___nth_1__
                       (clojure.core/nth input__57548_nth_1___r__ 1)]
                      (clojure.core/case
                       input__57548_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__57548_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__57548_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__9229__auto__
                               (CATA__FN__57598 [?pattern ?env])]
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
                               (CATA__FN__57598
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
                       (state__58587)))
                     (state__58587)))
                   (state__58587))))]
               (state__58609))
              (state__58587))
             (state__58587)))
           (state__58587))
          (state__58587)))
        (state__58587
         []
         (clojure.core/letfn
          [(def__57611
            [arg__57646 ?ns]
            (clojure.core/letfn
             [(state__58611
               []
               (clojure.core/let
                [x__57647 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__57647)
                 (clojure.core/let [?env arg__57646] [?env ?ns])
                 (state__58612))))
              (state__58612
               []
               (if
                (clojure.core/map? arg__57646)
                (clojure.core/let
                 [VAL__57648 (.valAt arg__57646 :aliases)]
                 (if
                  (clojure.core/map? VAL__57648)
                  (clojure.core/let
                   [X__57650 (clojure.core/set VAL__57648)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__57650))
                    (clojure.core/loop
                     [search_space__58613 (clojure.core/seq X__57650)]
                     (if
                      (clojure.core/seq search_space__58613)
                      (clojure.core/let
                       [elem__57651
                        (clojure.core/first search_space__58613)
                        result__58614
                        (clojure.core/let
                         [elem__57651_nth_0__
                          (clojure.core/nth elem__57651 0)
                          elem__57651_nth_1__
                          (clojure.core/nth elem__57651 1)]
                         (if
                          (clojure.core/symbol? elem__57651_nth_0__)
                          (clojure.core/let
                           [X__57653
                            (clojure.core/name elem__57651_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__57653)
                            (if
                             (clojure.core/symbol? elem__57651_nth_1__)
                             (clojure.core/let
                              [X__57655
                               (clojure.core/name elem__57651_nth_1__)]
                              (clojure.core/case
                               X__57655
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__57646]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58614)
                        (recur (clojure.core/next search_space__58613))
                        result__58614))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58611)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 3)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)
              input__57548_nth_2__
              (clojure.core/nth input__57548 2)]
             (clojure.core/case
              input__57548_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__57548_nth_1__)
               (clojure.core/loop
                [search_space__58616
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__57548_nth_1__)]
                (if
                 (clojure.core/seq search_space__58616)
                 (clojure.core/let
                  [input__57548_nth_1___parts__
                   (clojure.core/first search_space__58616)
                   result__58617
                   (clojure.core/let
                    [input__57548_nth_1___l__
                     (clojure.core/nth input__57548_nth_1___parts__ 0)
                     input__57548_nth_1___r__
                     (clojure.core/nth input__57548_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__57548_nth_1___l__)]
                     (clojure.core/let
                      [input__57548_nth_1___r___l__
                       (clojure.core/subvec
                        input__57548_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__57548_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__57548_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__57548_nth_1___r___r__
                         (clojure.core/subvec
                          input__57548_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__57548_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__57548_nth_1___r___l__
                           0)
                          input__57548_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__57548_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__57548_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__57620
                            (clojure.core/namespace
                             input__57548_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__57620]
                            (clojure.core/let
                             [X__57622
                              (clojure.core/name
                               input__57548_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__57622)
                              (clojure.core/let
                               [ret__57623
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__57622)]
                               (if
                                (clojure.core/some? ret__57623)
                                (if
                                 (clojure.core/vector? ret__57623)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__57623)
                                   2)
                                  (clojure.core/let
                                   [ret__57623_nth_1__
                                    (clojure.core/nth ret__57623 1)]
                                   (clojure.core/let
                                    [?n ret__57623_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__57548_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__57548_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__7926__auto__
                                        (def__57611
                                         input__57548_nth_2__
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
                                              (CATA__FN__57598
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__9229__auto__
                                                  (CATA__FN__57598
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
                                                  (CATA__FN__57598
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__9229__auto__
                                                       (CATA__FN__57598
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
                                                      (CATA__FN__57598
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
                   (meander.runtime.zeta/fail? result__58617)
                   (recur (clojure.core/next search_space__58616))
                   result__58617))
                 (state__58588)))
               (state__58588))
              (state__58588)))
            (state__58588))
           (state__58588))))
        (state__58588
         []
         (clojure.core/letfn
          [(def__57668
            [arg__57700 ?ns]
            (clojure.core/letfn
             [(state__58619
               []
               (clojure.core/let
                [x__57701 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__57701)
                 (clojure.core/let [?env arg__57700] [?env ?ns])
                 (state__58620))))
              (state__58620
               []
               (if
                (clojure.core/map? arg__57700)
                (clojure.core/let
                 [VAL__57702 (.valAt arg__57700 :aliases)]
                 (if
                  (clojure.core/map? VAL__57702)
                  (clojure.core/let
                   [X__57704 (clojure.core/set VAL__57702)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__57704))
                    (clojure.core/loop
                     [search_space__58621 (clojure.core/seq X__57704)]
                     (if
                      (clojure.core/seq search_space__58621)
                      (clojure.core/let
                       [elem__57705
                        (clojure.core/first search_space__58621)
                        result__58622
                        (clojure.core/let
                         [elem__57705_nth_0__
                          (clojure.core/nth elem__57705 0)
                          elem__57705_nth_1__
                          (clojure.core/nth elem__57705 1)]
                         (if
                          (clojure.core/symbol? elem__57705_nth_0__)
                          (clojure.core/let
                           [X__57707
                            (clojure.core/name elem__57705_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__57707)
                            (if
                             (clojure.core/symbol? elem__57705_nth_1__)
                             (clojure.core/let
                              [X__57709
                               (clojure.core/name elem__57705_nth_1__)]
                              (clojure.core/case
                               X__57709
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__57700]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58622)
                        (recur (clojure.core/next search_space__58621))
                        result__58622))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58619)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 3)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)
              input__57548_nth_2__
              (clojure.core/nth input__57548 2)]
             (clojure.core/case
              input__57548_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__57548_nth_1__)
               (clojure.core/loop
                [search_space__58624
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__57548_nth_1__)]
                (if
                 (clojure.core/seq search_space__58624)
                 (clojure.core/let
                  [input__57548_nth_1___parts__
                   (clojure.core/first search_space__58624)
                   result__58625
                   (clojure.core/let
                    [input__57548_nth_1___l__
                     (clojure.core/nth input__57548_nth_1___parts__ 0)
                     input__57548_nth_1___r__
                     (clojure.core/nth input__57548_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__57548_nth_1___l__)]
                     (clojure.core/let
                      [input__57548_nth_1___r___l__
                       (clojure.core/subvec
                        input__57548_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__57548_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__57548_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__57548_nth_1___r___r__
                         (clojure.core/subvec
                          input__57548_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__57548_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__57548_nth_1___r___l__
                           0)
                          input__57548_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__57548_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__57548_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__57677
                            (clojure.core/namespace
                             input__57548_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__57677]
                            (clojure.core/let
                             [X__57679
                              (clojure.core/name
                               input__57548_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__57679)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__57679)
                               (clojure.core/let
                                [?pattern
                                 input__57548_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__57548_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__7926__auto__
                                   (def__57668
                                    input__57548_nth_2__
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
                                         (CATA__FN__57598
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__57598
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
                                             (CATA__FN__57598
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__9229__auto__
                                                 (CATA__FN__57598
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
                                                 (CATA__FN__57598
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
                   (meander.runtime.zeta/fail? result__58625)
                   (recur (clojure.core/next search_space__58624))
                   result__58625))
                 (state__58589)))
               (state__58589))
              (state__58589)))
            (state__58589))
           (state__58589))))
        (state__58589
         []
         (if
          (clojure.core/vector? input__57548)
          (clojure.core/letfn
           [(state__58627
             []
             (if
              (clojure.core/= (clojure.core/count input__57548) 3)
              (clojure.core/let
               [input__57548_nth_0__
                (clojure.core/nth input__57548 0)
                input__57548_nth_1__
                (clojure.core/nth input__57548 1)
                input__57548_nth_2__
                (clojure.core/nth input__57548 2)]
               (clojure.core/case
                input__57548_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__57548_nth_1__)
                 (clojure.core/letfn
                  [(state__58632
                    []
                    (clojure.core/let
                     [n__57730
                      (clojure.core/count input__57548_nth_1__)
                      m__57731
                      (clojure.core/max 0 (clojure.core/- n__57730 2))
                      input__57548_nth_1___l__
                      (clojure.core/subvec
                       input__57548_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__57548_nth_1__)
                        m__57731))
                      input__57548_nth_1___r__
                      (clojure.core/subvec
                       input__57548_nth_1__
                       m__57731)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__57548_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__57548_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__57548_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__57548_nth_1___r___nth_0__
                          (clojure.core/nth input__57548_nth_1___r__ 0)
                          input__57548_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__57548_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__57548_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__57735
                            (clojure.core/namespace
                             input__57548_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__57735]
                            (clojure.core/let
                             [X__57737
                              (clojure.core/name
                               input__57548_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__57737)
                              (clojure.core/let
                               [ret__57738
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__57737)]
                               (if
                                (clojure.core/some? ret__57738)
                                (clojure.core/let
                                 [?name ret__57738]
                                 (clojure.core/let
                                  [?pattern
                                   input__57548_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__57548_nth_2__)
                                   (clojure.core/let
                                    [VAL__57722
                                     (.valAt
                                      input__57548_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__57722)
                                     (clojure.core/let
                                      [X__57724
                                       (clojure.core/set VAL__57722)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__57724))
                                       (clojure.core/loop
                                        [search_space__58636
                                         (clojure.core/seq X__57724)]
                                        (if
                                         (clojure.core/seq
                                          search_space__58636)
                                         (clojure.core/let
                                          [elem__57725
                                           (clojure.core/first
                                            search_space__58636)
                                           result__58637
                                           (clojure.core/let
                                            [elem__57725_nth_0__
                                             (clojure.core/nth
                                              elem__57725
                                              0)
                                             elem__57725_nth_1__
                                             (clojure.core/nth
                                              elem__57725
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__57725_nth_0__)
                                             (clojure.core/let
                                              [X__57727
                                               (clojure.core/name
                                                elem__57725_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__57727)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__57725_nth_1__)
                                                (clojure.core/let
                                                 [X__57729
                                                  (clojure.core/name
                                                   elem__57725_nth_1__)]
                                                 (clojure.core/case
                                                  X__57729
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__57548_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__9229__auto__
                                                        (CATA__FN__57598
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
                                            result__58637)
                                           (recur
                                            (clojure.core/next
                                             search_space__58636))
                                           result__58637))
                                         (state__58633)))
                                       (state__58633)))
                                     (state__58633)))
                                   (state__58633))))
                                (state__58633)))
                              (state__58633)))))
                          (state__58633)))
                        (state__58633)))
                      (state__58633))))
                   (state__58633
                    []
                    (clojure.core/loop
                     [search_space__58639
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__57548_nth_1__)]
                     (if
                      (clojure.core/seq search_space__58639)
                      (clojure.core/let
                       [input__57548_nth_1___parts__
                        (clojure.core/first search_space__58639)
                        result__58640
                        (clojure.core/let
                         [input__57548_nth_1___l__
                          (clojure.core/nth
                           input__57548_nth_1___parts__
                           0)
                          input__57548_nth_1___r__
                          (clojure.core/nth
                           input__57548_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__57548_nth_1___l__)]
                          (clojure.core/let
                           [input__57548_nth_1___r___l__
                            (clojure.core/subvec
                             input__57548_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__57548_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__57548_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__57548_nth_1___r___r__
                              (clojure.core/subvec
                               input__57548_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__57548_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__57548_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__57548_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__9229__auto__
                                     (CATA__FN__57598
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__57598
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
                                         (CATA__FN__57598
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
                        (meander.runtime.zeta/fail? result__58640)
                        (recur (clojure.core/next search_space__58639))
                        result__58640))
                      (state__58634))))
                   (state__58634
                    []
                    (clojure.core/let
                     [input__57548_nth_1___l__
                      (clojure.core/subvec
                       input__57548_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__57548_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__57548_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__57548_nth_1___r__
                        (clojure.core/subvec input__57548_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__57548_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__57548_nth_1___r__]
                         (clojure.core/let
                          [?env input__57548_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__9229__auto__
                              (CATA__FN__57598
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
                        (state__58635)))
                      (state__58635))))
                   (state__58635
                    []
                    (clojure.core/loop
                     [search_space__58642
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__57548_nth_1__)]
                     (if
                      (clojure.core/seq search_space__58642)
                      (clojure.core/let
                       [input__57548_nth_1___parts__
                        (clojure.core/first search_space__58642)
                        result__58643
                        (clojure.core/let
                         [input__57548_nth_1___l__
                          (clojure.core/nth
                           input__57548_nth_1___parts__
                           0)
                          input__57548_nth_1___r__
                          (clojure.core/nth
                           input__57548_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__57548_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__57755]
                              (clojure.core/let
                               [input__57755_nth_0__
                                (clojure.core/nth input__57755 0)]
                               (clojure.core/letfn
                                [(save__57756
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__58646
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__57755_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__57755_nth_0__)
                                 (clojure.core/let
                                  [X__57758
                                   (clojure.core/namespace
                                    input__57755_nth_0__)]
                                  (clojure.core/case
                                   X__57758
                                   (nil)
                                   (clojure.core/let
                                    [X__57760
                                     (clojure.core/name
                                      input__57755_nth_0__)]
                                    (if
                                     (clojure.core/string? X__57760)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__57760)
                                      (save__57756)
                                      (f__58646))
                                     (f__58646)))
                                   (f__58646)))
                                 (f__58646)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__57548_nth_1___r___l__
                                (clojure.core/subvec
                                 input__57548_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__57548_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__57548_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__57548_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__57548_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__57548_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__57548_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__57548_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__57598
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__57598
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
                                             (CATA__FN__57598
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
                        (meander.runtime.zeta/fail? result__58643)
                        (recur (clojure.core/next search_space__58642))
                        result__58643))
                      (state__58628))))]
                  (state__58632))
                 (state__58628))
                (state__58628)))
              (state__58628)))
            (state__58628
             []
             (if
              (clojure.core/= (clojure.core/count input__57548) 4)
              (clojure.core/let
               [input__57548_nth_0__
                (clojure.core/nth input__57548 0)
                input__57548_nth_1__
                (clojure.core/nth input__57548 1)
                input__57548_nth_2__
                (clojure.core/nth input__57548 2)]
               (clojure.core/letfn
                [(state__58647
                  []
                  (clojure.core/let
                   [input__57548_nth_3__
                    (clojure.core/nth input__57548 3)]
                   (clojure.core/case
                    input__57548_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__58649
                       []
                       (if
                        (clojure.core/map? input__57548_nth_1__)
                        (clojure.core/let
                         [VAL__57764
                          (.valAt input__57548_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__57764
                          (:cat)
                          (clojure.core/let
                           [VAL__57765
                            (.valAt input__57548_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__57765)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__57765)
                              1)
                             (clojure.core/let
                              [VAL__57765_nth_0__
                               (clojure.core/nth VAL__57765 0)]
                              (if
                               (clojure.core/map? VAL__57765_nth_0__)
                               (clojure.core/let
                                [VAL__57770
                                 (.valAt VAL__57765_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__57770
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__57765_nth_0__]
                                  (clojure.core/let
                                   [VAL__57766
                                    (.valAt
                                     input__57548_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__57766)
                                    (clojure.core/let
                                     [VAL__57767
                                      (.valAt VAL__57766 :tag)]
                                     (clojure.core/case
                                      VAL__57767
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__57548_nth_2__]
                                       (clojure.core/let
                                        [?env input__57548_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9229__auto__
                                            (CATA__FN__57598
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
                                      (state__58650)))
                                    (state__58650))))
                                 (state__58650)))
                               (state__58650)))
                             (state__58650))
                            (state__58650)))
                          (state__58650)))
                        (state__58650)))
                      (state__58650
                       []
                       (clojure.core/let
                        [?pattern input__57548_nth_1__]
                        (clojure.core/let
                         [?next input__57548_nth_2__]
                         (if
                          (clojure.core/map? input__57548_nth_3__)
                          (clojure.core/let
                           [VAL__57773
                            (.valAt input__57548_nth_3__ :context)]
                           (clojure.core/case
                            VAL__57773
                            (:string)
                            (try
                             [{:tag :string-star,
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
                            (state__58648)))
                          (state__58648)))))]
                     (state__58649))
                    (state__58648))))
                 (state__58648
                  []
                  (clojure.core/case
                   input__57548_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__57548_nth_1__]
                    (clojure.core/let
                     [?next input__57548_nth_2__]
                     (try
                      [{:tag :star, :pattern ?pattern, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__))))))
                   (state__58629)))]
                (state__58647)))
              (state__58629)))
            (state__58629
             []
             (if
              (clojure.core/= (clojure.core/count input__57548) 3)
              (clojure.core/let
               [input__57548_nth_0__
                (clojure.core/nth input__57548 0)
                input__57548_nth_1__
                (clojure.core/nth input__57548 1)
                input__57548_nth_2__
                (clojure.core/nth input__57548 2)]
               (clojure.core/case
                input__57548_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__57548_nth_1__)
                 (clojure.core/letfn
                  [(state__58651
                    []
                    (clojure.core/let
                     [input__57548_nth_1___l__
                      (clojure.core/subvec
                       input__57548_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__57548_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__57548_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__57548_nth_1___r__
                        (clojure.core/subvec input__57548_nth_1__ 1)]
                       (clojure.core/let
                        [input__57548_nth_1___l___nth_0__
                         (clojure.core/nth input__57548_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__57548_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__57781
                           (clojure.core/namespace
                            input__57548_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__57781
                           (nil)
                           (clojure.core/let
                            [X__57783
                             (clojure.core/name
                              input__57548_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__57783)
                             (clojure.core/let
                              [ret__57784
                               (clojure.core/re-matches
                                #"\.\.(\d+)"
                                X__57783)]
                              (if
                               (clojure.core/some? ret__57784)
                               (if
                                (clojure.core/vector? ret__57784)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__57784)
                                  2)
                                 (clojure.core/let
                                  [ret__57784_nth_1__
                                   (clojure.core/nth ret__57784 1)]
                                  (clojure.core/let
                                   [?n ret__57784_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__57548_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__57548_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__57548_nth_2__]
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
                                 (state__58652))
                                (state__58652))
                               (state__58652)))
                             (state__58652)))
                           (state__58652)))
                         (state__58652))))
                      (state__58652))))
                   (state__58652
                    []
                    (clojure.core/loop
                     [search_space__58658
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__57548_nth_1__)]
                     (if
                      (clojure.core/seq search_space__58658)
                      (clojure.core/let
                       [input__57548_nth_1___parts__
                        (clojure.core/first search_space__58658)
                        result__58659
                        (clojure.core/let
                         [input__57548_nth_1___l__
                          (clojure.core/nth
                           input__57548_nth_1___parts__
                           0)
                          input__57548_nth_1___r__
                          (clojure.core/nth
                           input__57548_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__57548_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__57800]
                              (clojure.core/let
                               [input__57800_nth_0__
                                (clojure.core/nth input__57800 0)]
                               (clojure.core/letfn
                                [(save__57801
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__58662
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__57800_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__57800_nth_0__)
                                 (clojure.core/let
                                  [X__57803
                                   (clojure.core/namespace
                                    input__57800_nth_0__)]
                                  (clojure.core/case
                                   X__57803
                                   (nil)
                                   (clojure.core/let
                                    [X__57805
                                     (clojure.core/name
                                      input__57800_nth_0__)]
                                    (if
                                     (clojure.core/string? X__57805)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__57805)
                                      (save__57801)
                                      (f__58662))
                                     (f__58662)))
                                   (f__58662)))
                                 (f__58662)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__57548_nth_1___r___l__
                                (clojure.core/subvec
                                 input__57548_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__57548_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__57548_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__57548_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__57548_nth_1___r__
                                   1)]
                                 (clojure.core/let
                                  [input__57548_nth_1___r___l___nth_0__
                                   (clojure.core/nth
                                    input__57548_nth_1___r___l__
                                    0)]
                                  (if
                                   (clojure.core/symbol?
                                    input__57548_nth_1___r___l___nth_0__)
                                   (clojure.core/let
                                    [X__57794
                                     (clojure.core/namespace
                                      input__57548_nth_1___r___l___nth_0__)]
                                    (clojure.core/case
                                     X__57794
                                     (nil)
                                     (clojure.core/let
                                      [X__57796
                                       (clojure.core/name
                                        input__57548_nth_1___r___l___nth_0__)]
                                      (if
                                       (clojure.core/string? X__57796)
                                       (clojure.core/let
                                        [ret__57797
                                         (clojure.core/re-matches
                                          #"\.\.(\d+)"
                                          X__57796)]
                                        (if
                                         (clojure.core/some?
                                          ret__57797)
                                         (if
                                          (clojure.core/vector?
                                           ret__57797)
                                          (if
                                           (clojure.core/=
                                            (clojure.core/count
                                             ret__57797)
                                            2)
                                           (clojure.core/let
                                            [ret__57797_nth_1__
                                             (clojure.core/nth
                                              ret__57797
                                              1)]
                                            (clojure.core/let
                                             [?n ret__57797_nth_1__]
                                             (clojure.core/let
                                              [?rest
                                               input__57548_nth_1___r___r__]
                                              (clojure.core/let
                                               [?env
                                                input__57548_nth_2__]
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
                                                     (CATA__FN__57598
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
                                                     (CATA__FN__57598
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
                             ret__8090__auto__)
                            (meander.runtime.zeta/fail)
                            ret__8090__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__58659)
                        (recur (clojure.core/next search_space__58658))
                        result__58659))
                      (state__58653))))
                   (state__58653
                    []
                    (clojure.core/let
                     [input__57548_nth_1___l__
                      (clojure.core/subvec
                       input__57548_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__57548_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__57548_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__57548_nth_1___r__
                        (clojure.core/subvec input__57548_nth_1__ 1)]
                       (clojure.core/let
                        [input__57548_nth_1___l___nth_0__
                         (clojure.core/nth input__57548_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__57548_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__57812
                           (clojure.core/namespace
                            input__57548_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__57812
                           (nil)
                           (clojure.core/let
                            [X__57814
                             (clojure.core/name
                              input__57548_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__57814)
                             (clojure.core/let
                              [ret__57815
                               (clojure.core/re-matches
                                #"\.\.(\?.+)"
                                X__57814)]
                              (if
                               (clojure.core/some? ret__57815)
                               (if
                                (clojure.core/vector? ret__57815)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__57815)
                                  2)
                                 (clojure.core/let
                                  [ret__57815_nth_1__
                                   (clojure.core/nth ret__57815 1)]
                                  (clojure.core/let
                                   [?n ret__57815_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__57548_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__57548_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__57548_nth_2__]
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
                                         (throw
                                          e__10169__auto__)))))))))
                                 (state__58654))
                                (state__58654))
                               (state__58654)))
                             (state__58654)))
                           (state__58654)))
                         (state__58654))))
                      (state__58654))))
                   (state__58654
                    []
                    (clojure.core/loop
                     [search_space__58663
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__57548_nth_1__)]
                     (if
                      (clojure.core/seq search_space__58663)
                      (clojure.core/let
                       [input__57548_nth_1___parts__
                        (clojure.core/first search_space__58663)
                        result__58664
                        (clojure.core/let
                         [input__57548_nth_1___l__
                          (clojure.core/nth
                           input__57548_nth_1___parts__
                           0)
                          input__57548_nth_1___r__
                          (clojure.core/nth
                           input__57548_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__57548_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__57831]
                              (clojure.core/let
                               [input__57831_nth_0__
                                (clojure.core/nth input__57831 0)]
                               (clojure.core/letfn
                                [(save__57832
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__58667
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__57831_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__57831_nth_0__)
                                 (clojure.core/let
                                  [X__57834
                                   (clojure.core/namespace
                                    input__57831_nth_0__)]
                                  (clojure.core/case
                                   X__57834
                                   (nil)
                                   (clojure.core/let
                                    [X__57836
                                     (clojure.core/name
                                      input__57831_nth_0__)]
                                    (if
                                     (clojure.core/string? X__57836)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__57836)
                                      (save__57832)
                                      (f__58667))
                                     (f__58667)))
                                   (f__58667)))
                                 (f__58667)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__57548_nth_1___r___l__
                                (clojure.core/subvec
                                 input__57548_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__57548_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__57548_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__57548_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__57548_nth_1___r__
                                   1)]
                                 (clojure.core/let
                                  [input__57548_nth_1___r___l___nth_0__
                                   (clojure.core/nth
                                    input__57548_nth_1___r___l__
                                    0)]
                                  (if
                                   (clojure.core/symbol?
                                    input__57548_nth_1___r___l___nth_0__)
                                   (clojure.core/let
                                    [X__57825
                                     (clojure.core/namespace
                                      input__57548_nth_1___r___l___nth_0__)]
                                    (clojure.core/case
                                     X__57825
                                     (nil)
                                     (clojure.core/let
                                      [X__57827
                                       (clojure.core/name
                                        input__57548_nth_1___r___l___nth_0__)]
                                      (if
                                       (clojure.core/string? X__57827)
                                       (clojure.core/let
                                        [ret__57828
                                         (clojure.core/re-matches
                                          #"\.\.(\?.+)"
                                          X__57827)]
                                        (if
                                         (clojure.core/some?
                                          ret__57828)
                                         (if
                                          (clojure.core/vector?
                                           ret__57828)
                                          (if
                                           (clojure.core/=
                                            (clojure.core/count
                                             ret__57828)
                                            2)
                                           (clojure.core/let
                                            [ret__57828_nth_1__
                                             (clojure.core/nth
                                              ret__57828
                                              1)]
                                            (clojure.core/let
                                             [?n ret__57828_nth_1__]
                                             (clojure.core/let
                                              [?rest
                                               input__57548_nth_1___r___r__]
                                              (clojure.core/let
                                               [?env
                                                input__57548_nth_2__]
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
                                                    [CATA_RESULT__9229__auto__
                                                     (CATA__FN__57598
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
                                                     (CATA__FN__57598
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
                             ret__8090__auto__)
                            (meander.runtime.zeta/fail)
                            ret__8090__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__58664)
                        (recur (clojure.core/next search_space__58663))
                        result__58664))
                      (state__58655))))
                   (state__58655
                    []
                    (clojure.core/let
                     [input__57548_nth_1___l__
                      (clojure.core/subvec
                       input__57548_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__57548_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__57548_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__57548_nth_1___r__
                        (clojure.core/subvec input__57548_nth_1__ 1)]
                       (clojure.core/let
                        [input__57548_nth_1___l___nth_0__
                         (clojure.core/nth input__57548_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__57548_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__57843
                           (clojure.core/namespace
                            input__57548_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__57843
                           (nil)
                           (clojure.core/let
                            [X__57845
                             (clojure.core/name
                              input__57548_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__57845)
                             (clojure.core/let
                              [ret__57846
                               (clojure.core/re-matches
                                #"\.\.(!.+)"
                                X__57845)]
                              (if
                               (clojure.core/some? ret__57846)
                               (if
                                (clojure.core/vector? ret__57846)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__57846)
                                  2)
                                 (clojure.core/let
                                  [ret__57846_nth_1__
                                   (clojure.core/nth ret__57846 1)]
                                  (clojure.core/let
                                   [?n ret__57846_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__57548_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__57548_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__57548_nth_2__]
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
                                         (throw
                                          e__10169__auto__)))))))))
                                 (state__58656))
                                (state__58656))
                               (state__58656)))
                             (state__58656)))
                           (state__58656)))
                         (state__58656))))
                      (state__58656))))
                   (state__58656
                    []
                    (clojure.core/loop
                     [search_space__58668
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__57548_nth_1__)]
                     (if
                      (clojure.core/seq search_space__58668)
                      (clojure.core/let
                       [input__57548_nth_1___parts__
                        (clojure.core/first search_space__58668)
                        result__58669
                        (clojure.core/let
                         [input__57548_nth_1___l__
                          (clojure.core/nth
                           input__57548_nth_1___parts__
                           0)
                          input__57548_nth_1___r__
                          (clojure.core/nth
                           input__57548_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__57548_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__57862]
                              (clojure.core/let
                               [input__57862_nth_0__
                                (clojure.core/nth input__57862 0)]
                               (clojure.core/letfn
                                [(save__57863
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__58672
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__57862_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__57862_nth_0__)
                                 (clojure.core/let
                                  [X__57865
                                   (clojure.core/namespace
                                    input__57862_nth_0__)]
                                  (clojure.core/case
                                   X__57865
                                   (nil)
                                   (clojure.core/let
                                    [X__57867
                                     (clojure.core/name
                                      input__57862_nth_0__)]
                                    (if
                                     (clojure.core/string? X__57867)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__57867)
                                      (save__57863)
                                      (f__58672))
                                     (f__58672)))
                                   (f__58672)))
                                 (f__58672)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__57548_nth_1___r___l__
                                (clojure.core/subvec
                                 input__57548_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__57548_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__57548_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__57548_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__57548_nth_1___r__
                                   1)]
                                 (clojure.core/let
                                  [input__57548_nth_1___r___l___nth_0__
                                   (clojure.core/nth
                                    input__57548_nth_1___r___l__
                                    0)]
                                  (if
                                   (clojure.core/symbol?
                                    input__57548_nth_1___r___l___nth_0__)
                                   (clojure.core/let
                                    [X__57856
                                     (clojure.core/namespace
                                      input__57548_nth_1___r___l___nth_0__)]
                                    (clojure.core/case
                                     X__57856
                                     (nil)
                                     (clojure.core/let
                                      [X__57858
                                       (clojure.core/name
                                        input__57548_nth_1___r___l___nth_0__)]
                                      (if
                                       (clojure.core/string? X__57858)
                                       (clojure.core/let
                                        [ret__57859
                                         (clojure.core/re-matches
                                          #"\.\.(\!.+)"
                                          X__57858)]
                                        (if
                                         (clojure.core/some?
                                          ret__57859)
                                         (if
                                          (clojure.core/vector?
                                           ret__57859)
                                          (if
                                           (clojure.core/=
                                            (clojure.core/count
                                             ret__57859)
                                            2)
                                           (clojure.core/let
                                            [ret__57859_nth_1__
                                             (clojure.core/nth
                                              ret__57859
                                              1)]
                                            (clojure.core/let
                                             [?n ret__57859_nth_1__]
                                             (clojure.core/let
                                              [?rest
                                               input__57548_nth_1___r___r__]
                                              (clojure.core/let
                                               [?env
                                                input__57548_nth_2__]
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
                                                    [CATA_RESULT__9229__auto__
                                                     (CATA__FN__57598
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
                                                     (CATA__FN__57598
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
                             ret__8090__auto__)
                            (meander.runtime.zeta/fail)
                            ret__8090__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__58669)
                        (recur (clojure.core/next search_space__58668))
                        result__58669))
                      (state__58657))))
                   (state__58657
                    []
                    (clojure.core/let
                     [!xs (clojure.core/vec input__57548_nth_1__)]
                     (clojure.core/let
                      [?env input__57548_nth_2__]
                      (try
                       [(clojure.core/let
                         [!xs__counter
                          (meander.runtime.zeta/iterator !xs)]
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__57598
                            ['meander.dev.parse.zeta/make-cat
                             (clojure.core/into
                              []
                              (clojure.core/loop
                               [return__57599
                                (clojure.core/transient [])]
                               (if
                                (clojure.core/and
                                 (.hasNext !xs__counter))
                                (recur
                                 (clojure.core/conj!
                                  return__57599
                                  (clojure.core/let
                                   [CATA_RESULT__9229__auto__
                                    (CATA__FN__57598
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
                                 return__57599))))
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
                         (throw e__10169__auto__)))))))]
                  (state__58651))
                 (state__58630))
                (state__58630)))
              (state__58630)))
            (state__58630
             []
             (if
              (clojure.core/= (clojure.core/count input__57548) 4)
              (clojure.core/let
               [input__57548_nth_0__
                (clojure.core/nth input__57548 0)
                input__57548_nth_1__
                (clojure.core/nth input__57548 1)
                input__57548_nth_2__
                (clojure.core/nth input__57548 2)]
               (clojure.core/letfn
                [(state__58673
                  []
                  (clojure.core/case
                   input__57548_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__57548_nth_1__)
                    (clojure.core/let
                     [!forms []]
                     (clojure.core/let
                      [ret__8090__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__57548_nth_1__
                        [!forms]
                        (clojure.core/fn
                         [[!forms] input__57878]
                         (clojure.core/let
                          [input__57878_nth_0__
                           (clojure.core/nth input__57878 0)]
                          (if
                           (clojure.core/map? input__57878_nth_0__)
                           (clojure.core/let
                            [VAL__57879
                             (.valAt input__57878_nth_0__ :tag)]
                            (clojure.core/case
                             VAL__57879
                             (:literal)
                             (clojure.core/let
                              [VAL__57880
                               (.valAt input__57878_nth_0__ :type)]
                              (if
                               (clojure.core/let
                                [x__6986__auto__ VAL__57880]
                                (clojure.core/case
                                 x__6986__auto__
                                 (:string :char)
                                 true
                                 false))
                               (clojure.core/let
                                [VAL__57881
                                 (.valAt input__57878_nth_0__ :form)]
                                (clojure.core/let
                                 [!forms
                                  (clojure.core/conj
                                   !forms
                                   VAL__57881)]
                                 [!forms]))
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))))
                        (clojure.core/fn
                         [[!forms]]
                         (if
                          (clojure.core/map? input__57548_nth_2__)
                          (clojure.core/let
                           [VAL__57875
                            (.valAt input__57548_nth_2__ :tag)]
                           (clojure.core/case
                            VAL__57875
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
                            (state__58674)))
                          (state__58674))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8090__auto__)
                       (state__58674)
                       ret__8090__auto__)))
                    (state__58674))
                   (state__58674)))
                 (state__58674
                  []
                  (clojure.core/let
                   [input__57548_nth_3__
                    (clojure.core/nth input__57548 3)]
                   (clojure.core/case
                    input__57548_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__57548_nth_1__)
                     (clojure.core/letfn
                      [(state__58678
                        []
                        (clojure.core/let
                         [input__57548_nth_1___l__
                          (clojure.core/subvec
                           input__57548_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__57548_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__57548_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__57548_nth_1___r__
                            (clojure.core/subvec
                             input__57548_nth_1__
                             1)]
                           (clojure.core/let
                            [input__57548_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__57548_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__57548_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__57887
                               (.valAt
                                input__57548_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__57887
                               (:literal)
                               (clojure.core/let
                                [VAL__57888
                                 (.valAt
                                  input__57548_nth_1___l___nth_0__
                                  :type)]
                                (clojure.core/case
                                 VAL__57888
                                 (:string)
                                 (clojure.core/let
                                  [?ast
                                   input__57548_nth_1___l___nth_0__]
                                  (clojure.core/let
                                   [?rest input__57548_nth_1___r__]
                                   (clojure.core/let
                                    [?next input__57548_nth_2__]
                                    (clojure.core/let
                                     [?env input__57548_nth_3__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__57598
                                          ['meander.dev.parse.zeta/make-join
                                           ?ast
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__57598
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
                                        (throw e__10169__auto__))))))))
                                 (state__58679)))
                               (state__58679)))
                             (state__58679))))
                          (state__58679))))
                       (state__58679
                        []
                        (clojure.core/let
                         [!forms []]
                         (clojure.core/let
                          [ret__8090__auto__
                           (meander.runtime.zeta/epsilon-run-star-1
                            input__57548_nth_1__
                            [!forms]
                            (clojure.core/fn
                             [[!forms] input__57894]
                             (clojure.core/let
                              [input__57894_nth_0__
                               (clojure.core/nth input__57894 0)]
                              (if
                               (clojure.core/map? input__57894_nth_0__)
                               (clojure.core/let
                                [VAL__57895
                                 (.valAt input__57894_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__57895
                                 (:literal)
                                 (clojure.core/let
                                  [VAL__57896
                                   (.valAt input__57894_nth_0__ :form)]
                                  (clojure.core/let
                                   [!forms
                                    (clojure.core/conj
                                     !forms
                                     VAL__57896)]
                                   [!forms]))
                                 (meander.runtime.zeta/fail)))
                               (meander.runtime.zeta/fail))))
                            (clojure.core/fn
                             [[!forms]]
                             (if
                              (clojure.core/map? input__57548_nth_2__)
                              (clojure.core/let
                               [VAL__57891
                                (.valAt input__57548_nth_2__ :tag)]
                               (clojure.core/case
                                VAL__57891
                                (:empty)
                                (clojure.core/let
                                 [?env input__57548_nth_3__]
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
                                (state__58675)))
                              (state__58675))))]
                          (if
                           (meander.runtime.zeta/fail?
                            ret__8090__auto__)
                           (state__58675)
                           ret__8090__auto__))))]
                      (state__58678))
                     (state__58675))
                    (state__58675))))
                 (state__58675
                  []
                  (clojure.core/case
                   input__57548_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__57548_nth_1__]
                    (clojure.core/let
                     [?next input__57548_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__))))))
                   (state__58676)))
                 (state__58676
                  []
                  (clojure.core/let
                   [input__57548_nth_3__
                    (clojure.core/nth input__57548 3)]
                   (clojure.core/case
                    input__57548_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__58681
                       []
                       (if
                        (clojure.core/map? input__57548_nth_1__)
                        (clojure.core/let
                         [VAL__57902
                          (.valAt input__57548_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__57902
                          (:cat)
                          (clojure.core/let
                           [VAL__57903
                            (.valAt input__57548_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__57903]
                            (clojure.core/let
                             [VAL__57904
                              (.valAt input__57548_nth_1__ :next)]
                             (if
                              (clojure.core/map? VAL__57904)
                              (clojure.core/let
                               [VAL__57905 (.valAt VAL__57904 :tag)]
                               (clojure.core/case
                                VAL__57905
                                (:empty)
                                (clojure.core/let
                                 [?right input__57548_nth_2__]
                                 (clojure.core/let
                                  [?env input__57548_nth_3__]
                                  (try
                                   [(clojure.core/let
                                     [CATA_RESULT__9229__auto__
                                      (CATA__FN__57598
                                       ['meander.dev.parse.zeta/make-cat
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
                                (state__58682)))
                              (state__58682)))))
                          (state__58682)))
                        (state__58682)))
                      (state__58682
                       []
                       (clojure.core/let
                        [?left input__57548_nth_1__]
                        (if
                         (clojure.core/map? input__57548_nth_2__)
                         (clojure.core/let
                          [VAL__57908
                           (.valAt input__57548_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__57908
                           (:empty)
                           (clojure.core/let
                            [?env input__57548_nth_3__]
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
                           (state__58683)))
                         (state__58683))))
                      (state__58683
                       []
                       (if
                        (clojure.core/map? input__57548_nth_1__)
                        (clojure.core/let
                         [VAL__57911
                          (.valAt input__57548_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__57911
                          (:empty)
                          (clojure.core/let
                           [?right input__57548_nth_2__]
                           (clojure.core/let
                            [?env input__57548_nth_3__]
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
                          (state__58684)))
                        (state__58684)))
                      (state__58684
                       []
                       (clojure.core/let
                        [?left input__57548_nth_1__]
                        (clojure.core/let
                         [?right input__57548_nth_2__]
                         (clojure.core/letfn
                          [(state__58686
                            []
                            (if
                             (clojure.core/map? input__57548_nth_3__)
                             (clojure.core/let
                              [VAL__57914
                               (.valAt input__57548_nth_3__ :context)]
                              (clojure.core/case
                               VAL__57914
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
                               (state__58687)))
                             (state__58687)))
                           (state__58687
                            []
                            (clojure.core/let
                             [?env input__57548_nth_3__]
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
                          (state__58686)))))
                      (state__58685
                       []
                       (if
                        (clojure.core/map? input__57548_nth_1__)
                        (clojure.core/let
                         [VAL__58585
                          (.valAt input__57548_nth_1__ :type)
                          VAL__58584
                          (.valAt input__57548_nth_1__ :tag)]
                         (clojure.core/letfn
                          [(state__58688
                            []
                            (clojure.core/case
                             VAL__58584
                             (:literal)
                             (clojure.core/case
                              VAL__58585
                              (:string)
                              (clojure.core/let
                               [VAL__57921
                                (.valAt input__57548_nth_1__ :form)]
                               (clojure.core/let
                                [?form-1 VAL__57921]
                                (if
                                 (clojure.core/map?
                                  input__57548_nth_2__)
                                 (clojure.core/let
                                  [VAL__57922
                                   (.valAt input__57548_nth_2__ :tag)]
                                  (clojure.core/case
                                   VAL__57922
                                   (:string-join)
                                   (clojure.core/let
                                    [VAL__57923
                                     (.valAt
                                      input__57548_nth_2__
                                      :left)]
                                    (if
                                     (clojure.core/map? VAL__57923)
                                     (clojure.core/let
                                      [VAL__57924
                                       (.valAt VAL__57923 :tag)]
                                      (clojure.core/case
                                       VAL__57924
                                       (:literal)
                                       (clojure.core/let
                                        [VAL__57925
                                         (.valAt VAL__57923 :type)]
                                        (clojure.core/case
                                         VAL__57925
                                         (:string)
                                         (clojure.core/let
                                          [VAL__57926
                                           (.valAt VAL__57923 :form)]
                                          (clojure.core/let
                                           [?form-2 VAL__57926]
                                           (clojure.core/let
                                            [VAL__57927
                                             (.valAt
                                              input__57548_nth_2__
                                              :right)]
                                            (clojure.core/let
                                             [?right VAL__57927]
                                             (if
                                              (clojure.core/map?
                                               input__57548_nth_3__)
                                              (clojure.core/let
                                               [VAL__57928
                                                (.valAt
                                                 input__57548_nth_3__
                                                 :context)]
                                               (clojure.core/case
                                                VAL__57928
                                                (:string)
                                                (clojure.core/let
                                                 [?env
                                                  input__57548_nth_3__]
                                                 (try
                                                  [(clojure.core/let
                                                    [CATA_RESULT__9229__auto__
                                                     (CATA__FN__57598
                                                      ['meander.dev.parse.zeta/make-join
                                                       {:tag :literal,
                                                        :type :string,
                                                        :form
                                                        ((clojure.core/partial
                                                          clojure.core/apply
                                                          str)
                                                         [?form-1
                                                          ?form-2])}
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
                                                (state__58689)))
                                              (state__58689))))))
                                         (state__58689)))
                                       (state__58689)))
                                     (state__58689)))
                                   (state__58689)))
                                 (state__58689))))
                              (state__58689))
                             (state__58689)))
                           (state__58689
                            []
                            (clojure.core/let
                             [VAL__58583
                              (.valAt input__57548_nth_1__ :next)]
                             (clojure.core/case
                              VAL__58584
                              (:literal)
                              (clojure.core/case
                               VAL__58585
                               (:string)
                               (clojure.core/let
                                [?ast input__57548_nth_1__]
                                (if
                                 (clojure.core/map?
                                  input__57548_nth_2__)
                                 (clojure.core/let
                                  [VAL__57933
                                   (.valAt input__57548_nth_2__ :tag)]
                                  (clojure.core/case
                                   VAL__57933
                                   (:string-cat)
                                   (clojure.core/let
                                    [VAL__57934
                                     (.valAt
                                      input__57548_nth_2__
                                      :sequence)]
                                    (clojure.core/let
                                     [?sequence VAL__57934]
                                     (clojure.core/let
                                      [VAL__57935
                                       (.valAt
                                        input__57548_nth_2__
                                        :next)]
                                      (clojure.core/let
                                       [?next VAL__57935]
                                       (if
                                        (clojure.core/map?
                                         input__57548_nth_3__)
                                        (clojure.core/let
                                         [VAL__57936
                                          (.valAt
                                           input__57548_nth_3__
                                           :context)]
                                         (clojure.core/case
                                          VAL__57936
                                          (:string)
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
                                             (throw
                                              e__10169__auto__))))
                                          (state__58631)))
                                        (state__58631))))))
                                   (state__58631)))
                                 (state__58631)))
                               (state__58631))
                              (:string-cat)
                              (clojure.core/let
                               [VAL__57940
                                (.valAt
                                 input__57548_nth_1__
                                 :sequence)]
                               (clojure.core/let
                                [?sequence VAL__57940]
                                (if
                                 (clojure.core/map? VAL__58583)
                                 (clojure.core/let
                                  [VAL__57942 (.valAt VAL__58583 :tag)]
                                  (clojure.core/case
                                   VAL__57942
                                   (:empty)
                                   (clojure.core/let
                                    [?right input__57548_nth_2__]
                                    (clojure.core/let
                                     [?env input__57548_nth_3__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__57598
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
                                   (state__58631)))
                                 (state__58631))))
                              (:string-star)
                              (clojure.core/let
                               [VAL__57946
                                (.valAt input__57548_nth_1__ :pattern)]
                               (clojure.core/let
                                [?pattern VAL__57946]
                                (if
                                 (clojure.core/map? VAL__58583)
                                 (clojure.core/let
                                  [VAL__57948 (.valAt VAL__58583 :tag)]
                                  (clojure.core/case
                                   VAL__57948
                                   (:empty)
                                   (clojure.core/let
                                    [?right input__57548_nth_2__]
                                    (if
                                     (clojure.core/map?
                                      input__57548_nth_3__)
                                     (clojure.core/let
                                      [VAL__57949
                                       (.valAt
                                        input__57548_nth_3__
                                        :context)]
                                      (clojure.core/case
                                       VAL__57949
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
                                       (state__58631)))
                                     (state__58631)))
                                   (state__58631)))
                                 (state__58631))))
                              (:string-join)
                              (clojure.core/let
                               [VAL__57953
                                (.valAt input__57548_nth_1__ :left)]
                               (clojure.core/let
                                [?left VAL__57953]
                                (clojure.core/let
                                 [VAL__57954
                                  (.valAt input__57548_nth_1__ :right)]
                                 (clojure.core/let
                                  [?right-1 VAL__57954]
                                  (clojure.core/let
                                   [?right-2 input__57548_nth_2__]
                                   (if
                                    (clojure.core/map?
                                     input__57548_nth_3__)
                                    (clojure.core/let
                                     [VAL__57955
                                      (.valAt
                                       input__57548_nth_3__
                                       :context)]
                                     (clojure.core/case
                                      VAL__57955
                                      (:string)
                                      (clojure.core/let
                                       [?env input__57548_nth_3__]
                                       (try
                                        [{:tag :string-join,
                                          :left ?left,
                                          :right
                                          (clojure.core/let
                                           [CATA_RESULT__9229__auto__
                                            (CATA__FN__57598
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
                                      (state__58631)))
                                    (state__58631)))))))
                              (state__58631))))]
                          (state__58688)))
                        (state__58631)))]
                     (state__58681))
                    (state__58631))))]
                (state__58673)))
              (state__58631)))
            (state__58631
             []
             (if
              (clojure.core/= (clojure.core/count input__57548) 3)
              (clojure.core/let
               [input__57548_nth_0__
                (clojure.core/nth input__57548 0)
                input__57548_nth_1__
                (clojure.core/nth input__57548 1)
                input__57548_nth_2__
                (clojure.core/nth input__57548 2)]
               (clojure.core/case
                input__57548_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__57548_nth_1__)
                 (clojure.core/let
                  [VAL__57958
                   (.valAt input__57548_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__57958]
                   (clojure.core/let
                    [X__57960
                     ((clojure.core/fn
                       [m__6993__auto__]
                       (clojure.core/dissoc
                        m__6993__auto__
                        :meander.zeta/as))
                      input__57548_nth_1__)]
                    (clojure.core/let
                     [?rest X__57960]
                     (clojure.core/letfn
                      [(save__57961 [] (state__58590))
                       (f__58690
                        []
                        (clojure.core/let
                         [?env input__57548_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__9229__auto__
                              (CATA__FN__57598 [?pattern ?env])]
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
                              (CATA__FN__57598 [?rest ?env])]
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
                       (clojure.core/= ?rest input__57548_nth_1__)
                       (save__57961)
                       (f__58690)))))))
                 (state__58590))
                (state__58590)))
              (state__58590)))]
           (state__58627))
          (state__58590)))
        (state__58590
         []
         (clojure.core/letfn
          [(def__57964
            [arg__57997 ?ns]
            (clojure.core/letfn
             [(state__58691
               []
               (clojure.core/let
                [x__57998 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__57998)
                 (clojure.core/let [?env arg__57997] [?env ?ns])
                 (state__58692))))
              (state__58692
               []
               (if
                (clojure.core/map? arg__57997)
                (clojure.core/let
                 [VAL__57999 (.valAt arg__57997 :aliases)]
                 (if
                  (clojure.core/map? VAL__57999)
                  (clojure.core/let
                   [X__58001 (clojure.core/set VAL__57999)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__58001))
                    (clojure.core/loop
                     [search_space__58693 (clojure.core/seq X__58001)]
                     (if
                      (clojure.core/seq search_space__58693)
                      (clojure.core/let
                       [elem__58002
                        (clojure.core/first search_space__58693)
                        result__58694
                        (clojure.core/let
                         [elem__58002_nth_0__
                          (clojure.core/nth elem__58002 0)
                          elem__58002_nth_1__
                          (clojure.core/nth elem__58002 1)]
                         (if
                          (clojure.core/symbol? elem__58002_nth_0__)
                          (clojure.core/let
                           [X__58004
                            (clojure.core/name elem__58002_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__58004)
                            (if
                             (clojure.core/symbol? elem__58002_nth_1__)
                             (clojure.core/let
                              [X__58006
                               (clojure.core/name elem__58002_nth_1__)]
                              (clojure.core/case
                               X__58006
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__57997]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58694)
                        (recur (clojure.core/next search_space__58693))
                        result__58694))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58691)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 3)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)
              input__57548_nth_2__
              (clojure.core/nth input__57548 2)]
             (clojure.core/case
              input__57548_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__57548_nth_1__)
               (clojure.core/let
                [X__57969 (clojure.core/set input__57548_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__57969))
                 (clojure.core/loop
                  [search_space__58696 (clojure.core/seq X__57969)]
                  (if
                   (clojure.core/seq search_space__58696)
                   (clojure.core/let
                    [elem__57970
                     (clojure.core/first search_space__58696)
                     result__58697
                     (clojure.core/let
                      [elem__57970_nth_0__
                       (clojure.core/nth elem__57970 0)
                       elem__57970_nth_1__
                       (clojure.core/nth elem__57970 1)]
                      (clojure.core/let
                       [*m__57567 elem__57970_nth_0__]
                       (if
                        (clojure.core/symbol? elem__57970_nth_0__)
                        (clojure.core/let
                         [X__57972
                          (clojure.core/namespace elem__57970_nth_0__)]
                         (clojure.core/let
                          [?ns X__57972]
                          (clojure.core/let
                           [X__57974
                            (clojure.core/name elem__57970_nth_0__)]
                           (if
                            (clojure.core/string? X__57974)
                            (if
                             (clojure.core/re-matches #"&.*" X__57974)
                             (clojure.core/let
                              [?pattern elem__57970_nth_1__]
                              (clojure.core/let
                               [X__57976
                                ((clojure.core/fn
                                  [m__6993__auto__]
                                  (clojure.core/dissoc
                                   m__6993__auto__
                                   *m__57567))
                                 input__57548_nth_1__)]
                               (clojure.core/let
                                [?rest X__57976]
                                (clojure.core/let
                                 [x__7926__auto__
                                  (def__57964
                                   input__57548_nth_2__
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
                                        (CATA__FN__57598
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
                                        (CATA__FN__57598
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
                     (meander.runtime.zeta/fail? result__58697)
                     (recur (clojure.core/next search_space__58696))
                     result__58697))
                   (state__58591)))
                 (state__58591)))
               (state__58591))
              (state__58591)))
            (state__58591))
           (state__58591))))
        (state__58591
         []
         (if
          (clojure.core/vector? input__57548)
          (clojure.core/letfn
           [(state__58699
             []
             (if
              (clojure.core/= (clojure.core/count input__57548) 3)
              (clojure.core/let
               [input__57548_nth_0__
                (clojure.core/nth input__57548 0)
                input__57548_nth_1__
                (clojure.core/nth input__57548 1)
                input__57548_nth_2__
                (clojure.core/nth input__57548 2)]
               (clojure.core/case
                input__57548_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__58701
                   []
                   (if
                    (clojure.core/map? input__57548_nth_1__)
                    (clojure.core/let
                     [X__58020 (clojure.core/set input__57548_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__58020))
                      (clojure.core/loop
                       [search_space__58703
                        (clojure.core/seq X__58020)]
                       (if
                        (clojure.core/seq search_space__58703)
                        (clojure.core/let
                         [elem__58021
                          (clojure.core/first search_space__58703)
                          result__58704
                          (clojure.core/let
                           [elem__58021_nth_0__
                            (clojure.core/nth elem__58021 0)
                            elem__58021_nth_1__
                            (clojure.core/nth elem__58021 1)]
                           (clojure.core/let
                            [?key-pattern elem__58021_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__58021_nth_1__]
                             (clojure.core/let
                              [X__58023
                               ((clojure.core/fn
                                 [m__6993__auto__]
                                 (clojure.core/dissoc
                                  m__6993__auto__
                                  ?key-pattern))
                                input__57548_nth_1__)]
                              (clojure.core/let
                               [?rest X__58023]
                               (clojure.core/let
                                [?env input__57548_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9229__auto__
                                     (CATA__FN__57598
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
                                     (CATA__FN__57598
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
                                     (CATA__FN__57598
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
                          (meander.runtime.zeta/fail? result__58704)
                          (recur
                           (clojure.core/next search_space__58703))
                          result__58704))
                        (state__58702)))
                      (state__58702)))
                    (state__58702)))
                  (state__58702
                   []
                   (if
                    (clojure.core/map? input__57548_nth_1__)
                    (clojure.core/let
                     [?env input__57548_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__)))))
                    (state__58700)))]
                 (state__58701))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__58706
                   []
                   (if
                    (clojure.core/vector? input__57548_nth_1__)
                    (clojure.core/case
                     input__57548_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__57548_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__10169__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10169__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10169__auto__)))))
                     (state__58707))
                    (state__58707)))
                  (state__58707
                   []
                   (if
                    (clojure.core/vector? input__57548_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__57548_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__57548_nth_2__]
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
                     (state__58708))
                    (state__58708)))
                  (state__58708
                   []
                   (if
                    (clojure.core/vector? input__57548_nth_1__)
                    (clojure.core/let
                     [input__57548_nth_1___l__
                      (clojure.core/subvec
                       input__57548_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__57548_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__57548_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__57548_nth_1___r__
                        (clojure.core/subvec input__57548_nth_1__ 2)]
                       (clojure.core/let
                        [input__57548_nth_1___l___nth_0__
                         (clojure.core/nth input__57548_nth_1___l__ 0)
                         input__57548_nth_1___l___nth_1__
                         (clojure.core/nth input__57548_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__57548_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__58037
                           (clojure.core/namespace
                            input__57548_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__58039
                            (clojure.core/name
                             input__57548_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__58039)
                            (if
                             (clojure.core/re-matches #"%.+" X__58039)
                             (clojure.core/let
                              [?symbol
                               input__57548_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__57548_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__57548_nth_1___r__]
                                (clojure.core/let
                                 [?env input__57548_nth_2__]
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
                                         (CATA__FN__57598
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
                                       (CATA__FN__57598
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
                             (state__58709))
                            (state__58709))))
                         (state__58709))))
                      (state__58709)))
                    (state__58709)))
                  (state__58709
                   []
                   (if
                    (clojure.core/vector? input__57548_nth_1__)
                    (clojure.core/let
                     [input__57548_nth_1___l__
                      (clojure.core/subvec
                       input__57548_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__57548_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__57548_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__57548_nth_1___r__
                        (clojure.core/subvec input__57548_nth_1__ 2)]
                       (clojure.core/let
                        [input__57548_nth_1___l___nth_0__
                         (clojure.core/nth input__57548_nth_1___l__ 0)
                         input__57548_nth_1___l___nth_1__
                         (clojure.core/nth input__57548_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__57548_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__57548_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__57548_nth_1___r__]
                           (clojure.core/let
                            [?env input__57548_nth_2__]
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
                      (state__58700)))
                    (state__58700)))]
                 (state__58706))
                (state__58700)))
              (state__58700)))
            (state__58700
             []
             (if
              (clojure.core/= (clojure.core/count input__57548) 2)
              (clojure.core/let
               [input__57548_nth_0__
                (clojure.core/nth input__57548 0)
                input__57548_nth_1__
                (clojure.core/nth input__57548 1)]
               (if
                (clojure.core/vector? input__57548_nth_0__)
                (clojure.core/let
                 [?sequence input__57548_nth_0__]
                 (clojure.core/let
                  [?form input__57548_nth_0__]
                  (clojure.core/let
                   [?env input__57548_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__9229__auto__
                        (CATA__FN__57598
                         ['meander.dev.parse.zeta/parse-sequential
                          ?sequence
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
                      (meander.runtime.zeta/fail? e__10169__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10169__auto__)))))))
                (state__58592)))
              (state__58592)))]
           (state__58699))
          (state__58592)))
        (state__58592
         []
         (clojure.core/letfn
          [(def__58049
            [arg__58072 ?__57549]
            (clojure.core/letfn
             [(state__58710
               []
               (clojure.core/let
                [x__58073 "meander.zeta"]
                (if
                 (clojure.core/= ?__57549 x__58073)
                 [?__57549]
                 (state__58711))))
              (state__58711
               []
               (if
                (clojure.core/map? arg__58072)
                (clojure.core/let
                 [VAL__58074 (.valAt arg__58072 :aliases)]
                 (if
                  (clojure.core/map? VAL__58074)
                  (clojure.core/let
                   [X__58076 (clojure.core/set VAL__58074)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__58076))
                    (clojure.core/loop
                     [search_space__58712 (clojure.core/seq X__58076)]
                     (if
                      (clojure.core/seq search_space__58712)
                      (clojure.core/let
                       [elem__58077
                        (clojure.core/first search_space__58712)
                        result__58713
                        (clojure.core/let
                         [elem__58077_nth_0__
                          (clojure.core/nth elem__58077 0)
                          elem__58077_nth_1__
                          (clojure.core/nth elem__58077 1)]
                         (if
                          (clojure.core/symbol? elem__58077_nth_0__)
                          (clojure.core/let
                           [X__58079
                            (clojure.core/name elem__58077_nth_0__)]
                           (if
                            (clojure.core/= ?__57549 X__58079)
                            (if
                             (clojure.core/symbol? elem__58077_nth_1__)
                             (clojure.core/let
                              [X__58081
                               (clojure.core/name elem__58077_nth_1__)]
                              (clojure.core/case
                               X__58081
                               ("meander.zeta")
                               [?__57549]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58713)
                        (recur (clojure.core/next search_space__58712))
                        result__58713))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58710)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 2)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)]
             (if
              (clojure.core/seq? input__57548_nth_0__)
              (clojure.core/let
               [input__57548_nth_0___l__
                (clojure.core/take 1 input__57548_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__57548_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__57548_nth_0___r__
                  (clojure.core/drop 1 input__57548_nth_0__)]
                 (clojure.core/let
                  [input__57548_nth_0___l___nth_0__
                   (clojure.core/nth input__57548_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__57548_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__58059
                     (clojure.core/namespace
                      input__57548_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__57549 X__58059]
                     (clojure.core/let
                      [X__58061
                       (clojure.core/name
                        input__57548_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__58061
                       ("with")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__58049 input__57548_nth_1__ ?__57549)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__58593)
                         (clojure.core/let
                          [[?__57549] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__57548)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__57548)
                             2)
                            (clojure.core/let
                             [input__57548_nth_0__
                              (clojure.core/nth input__57548 0)
                              input__57548_nth_1__
                              (clojure.core/nth input__57548 1)]
                             (if
                              (clojure.core/seq? input__57548_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__57548_nth_0__)
                                3)
                               (clojure.core/let
                                [input__57548_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  1)
                                 input__57548_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__57548_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__57548_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__57548_nth_0__]
                                   (clojure.core/let
                                    [?env input__57548_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__57598
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
                                         (CATA__FN__57598
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
                               (state__58593))
                              (state__58593)))
                            (state__58593))
                           (state__58593)))))
                       (state__58593)))))
                   (state__58593))))
                (state__58593)))
              (state__58593)))
            (state__58593))
           (state__58593))))
        (state__58593
         []
         (clojure.core/letfn
          [(def__58083
            [arg__58106 ?__57550]
            (clojure.core/letfn
             [(state__58715
               []
               (clojure.core/let
                [x__58107 "meander.zeta"]
                (if
                 (clojure.core/= ?__57550 x__58107)
                 [?__57550]
                 (state__58716))))
              (state__58716
               []
               (if
                (clojure.core/map? arg__58106)
                (clojure.core/let
                 [VAL__58108 (.valAt arg__58106 :aliases)]
                 (if
                  (clojure.core/map? VAL__58108)
                  (clojure.core/let
                   [X__58110 (clojure.core/set VAL__58108)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__58110))
                    (clojure.core/loop
                     [search_space__58717 (clojure.core/seq X__58110)]
                     (if
                      (clojure.core/seq search_space__58717)
                      (clojure.core/let
                       [elem__58111
                        (clojure.core/first search_space__58717)
                        result__58718
                        (clojure.core/let
                         [elem__58111_nth_0__
                          (clojure.core/nth elem__58111 0)
                          elem__58111_nth_1__
                          (clojure.core/nth elem__58111 1)]
                         (if
                          (clojure.core/symbol? elem__58111_nth_0__)
                          (clojure.core/let
                           [X__58113
                            (clojure.core/name elem__58111_nth_0__)]
                           (if
                            (clojure.core/= ?__57550 X__58113)
                            (if
                             (clojure.core/symbol? elem__58111_nth_1__)
                             (clojure.core/let
                              [X__58115
                               (clojure.core/name elem__58111_nth_1__)]
                              (clojure.core/case
                               X__58115
                               ("meander.zeta")
                               [?__57550]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58718)
                        (recur (clojure.core/next search_space__58717))
                        result__58718))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58715)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 2)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)]
             (if
              (clojure.core/seq? input__57548_nth_0__)
              (clojure.core/let
               [input__57548_nth_0___l__
                (clojure.core/take 1 input__57548_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__57548_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__57548_nth_0___r__
                  (clojure.core/drop 1 input__57548_nth_0__)]
                 (clojure.core/let
                  [input__57548_nth_0___l___nth_0__
                   (clojure.core/nth input__57548_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__57548_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__58093
                     (clojure.core/namespace
                      input__57548_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__57550 X__58093]
                     (clojure.core/let
                      [X__58095
                       (clojure.core/name
                        input__57548_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__58095
                       ("apply")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__58083 input__57548_nth_1__ ?__57550)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__58594)
                         (clojure.core/let
                          [[?__57550] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__57548)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__57548)
                             2)
                            (clojure.core/let
                             [input__57548_nth_0__
                              (clojure.core/nth input__57548 0)
                              input__57548_nth_1__
                              (clojure.core/nth input__57548 1)]
                             (if
                              (clojure.core/seq? input__57548_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__57548_nth_0__)
                                3)
                               (clojure.core/let
                                [input__57548_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  1)
                                 input__57548_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__57548_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__57548_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__57548_nth_0__]
                                   (clojure.core/let
                                    [?env input__57548_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__57598
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
                               (state__58594))
                              (state__58594)))
                            (state__58594))
                           (state__58594)))))
                       (state__58594)))))
                   (state__58594))))
                (state__58594)))
              (state__58594)))
            (state__58594))
           (state__58594))))
        (state__58594
         []
         (clojure.core/letfn
          [(def__58117
            [arg__58140 ?__57551]
            (clojure.core/letfn
             [(state__58720
               []
               (clojure.core/let
                [x__58141 "meander.zeta"]
                (if
                 (clojure.core/= ?__57551 x__58141)
                 [?__57551]
                 (state__58721))))
              (state__58721
               []
               (if
                (clojure.core/map? arg__58140)
                (clojure.core/let
                 [VAL__58142 (.valAt arg__58140 :aliases)]
                 (if
                  (clojure.core/map? VAL__58142)
                  (clojure.core/let
                   [X__58144 (clojure.core/set VAL__58142)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__58144))
                    (clojure.core/loop
                     [search_space__58722 (clojure.core/seq X__58144)]
                     (if
                      (clojure.core/seq search_space__58722)
                      (clojure.core/let
                       [elem__58145
                        (clojure.core/first search_space__58722)
                        result__58723
                        (clojure.core/let
                         [elem__58145_nth_0__
                          (clojure.core/nth elem__58145 0)
                          elem__58145_nth_1__
                          (clojure.core/nth elem__58145 1)]
                         (if
                          (clojure.core/symbol? elem__58145_nth_0__)
                          (clojure.core/let
                           [X__58147
                            (clojure.core/name elem__58145_nth_0__)]
                           (if
                            (clojure.core/= ?__57551 X__58147)
                            (if
                             (clojure.core/symbol? elem__58145_nth_1__)
                             (clojure.core/let
                              [X__58149
                               (clojure.core/name elem__58145_nth_1__)]
                              (clojure.core/case
                               X__58149
                               ("meander.zeta")
                               [?__57551]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58723)
                        (recur (clojure.core/next search_space__58722))
                        result__58723))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58720)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 2)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)]
             (if
              (clojure.core/seq? input__57548_nth_0__)
              (clojure.core/let
               [input__57548_nth_0___l__
                (clojure.core/take 1 input__57548_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__57548_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__57548_nth_0___r__
                  (clojure.core/drop 1 input__57548_nth_0__)]
                 (clojure.core/let
                  [input__57548_nth_0___l___nth_0__
                   (clojure.core/nth input__57548_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__57548_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__58127
                     (clojure.core/namespace
                      input__57548_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__57551 X__58127]
                     (clojure.core/let
                      [X__58129
                       (clojure.core/name
                        input__57548_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__58129
                       ("and")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__58117 input__57548_nth_1__ ?__57551)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__58595)
                         (clojure.core/let
                          [[?__57551] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__57548)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__57548)
                             2)
                            (clojure.core/let
                             [input__57548_nth_0__
                              (clojure.core/nth input__57548 0)
                              input__57548_nth_1__
                              (clojure.core/nth input__57548 1)]
                             (if
                              (clojure.core/seq? input__57548_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__57548_nth_0__)
                                3)
                               (clojure.core/let
                                [input__57548_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  1)
                                 input__57548_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__57548_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__57548_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__57548_nth_0__]
                                   (clojure.core/let
                                    [?env input__57548_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__57598
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
                                         (CATA__FN__57598
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
                               (state__58595))
                              (state__58595)))
                            (state__58595))
                           (state__58595)))))
                       (state__58595)))))
                   (state__58595))))
                (state__58595)))
              (state__58595)))
            (state__58595))
           (state__58595))))
        (state__58595
         []
         (clojure.core/letfn
          [(def__58151
            [arg__58174 ?__57552]
            (clojure.core/letfn
             [(state__58725
               []
               (clojure.core/let
                [x__58175 "meander.zeta"]
                (if
                 (clojure.core/= ?__57552 x__58175)
                 [?__57552]
                 (state__58726))))
              (state__58726
               []
               (if
                (clojure.core/map? arg__58174)
                (clojure.core/let
                 [VAL__58176 (.valAt arg__58174 :aliases)]
                 (if
                  (clojure.core/map? VAL__58176)
                  (clojure.core/let
                   [X__58178 (clojure.core/set VAL__58176)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__58178))
                    (clojure.core/loop
                     [search_space__58727 (clojure.core/seq X__58178)]
                     (if
                      (clojure.core/seq search_space__58727)
                      (clojure.core/let
                       [elem__58179
                        (clojure.core/first search_space__58727)
                        result__58728
                        (clojure.core/let
                         [elem__58179_nth_0__
                          (clojure.core/nth elem__58179 0)
                          elem__58179_nth_1__
                          (clojure.core/nth elem__58179 1)]
                         (if
                          (clojure.core/symbol? elem__58179_nth_0__)
                          (clojure.core/let
                           [X__58181
                            (clojure.core/name elem__58179_nth_0__)]
                           (if
                            (clojure.core/= ?__57552 X__58181)
                            (if
                             (clojure.core/symbol? elem__58179_nth_1__)
                             (clojure.core/let
                              [X__58183
                               (clojure.core/name elem__58179_nth_1__)]
                              (clojure.core/case
                               X__58183
                               ("meander.zeta")
                               [?__57552]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58728)
                        (recur (clojure.core/next search_space__58727))
                        result__58728))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58725)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 2)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)]
             (if
              (clojure.core/seq? input__57548_nth_0__)
              (clojure.core/let
               [input__57548_nth_0___l__
                (clojure.core/take 1 input__57548_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__57548_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__57548_nth_0___r__
                  (clojure.core/drop 1 input__57548_nth_0__)]
                 (clojure.core/let
                  [input__57548_nth_0___l___nth_0__
                   (clojure.core/nth input__57548_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__57548_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__58161
                     (clojure.core/namespace
                      input__57548_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__57552 X__58161]
                     (clojure.core/let
                      [X__58163
                       (clojure.core/name
                        input__57548_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__58163
                       ("cata")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__58151 input__57548_nth_1__ ?__57552)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__58596)
                         (clojure.core/let
                          [[?__57552] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__57548)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__57548)
                             2)
                            (clojure.core/let
                             [input__57548_nth_0__
                              (clojure.core/nth input__57548 0)
                              input__57548_nth_1__
                              (clojure.core/nth input__57548 1)]
                             (if
                              (clojure.core/seq? input__57548_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__57548_nth_0__)
                                2)
                               (clojure.core/let
                                [input__57548_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__57548_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__57548_nth_0__]
                                  (clojure.core/let
                                   [?env input__57548_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__57598
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
                               (state__58596))
                              (state__58596)))
                            (state__58596))
                           (state__58596)))))
                       (state__58596)))))
                   (state__58596))))
                (state__58596)))
              (state__58596)))
            (state__58596))
           (state__58596))))
        (state__58596
         []
         (clojure.core/letfn
          [(def__58185
            [arg__58208 ?__57553]
            (clojure.core/letfn
             [(state__58730
               []
               (clojure.core/let
                [x__58209 "meander.zeta"]
                (if
                 (clojure.core/= ?__57553 x__58209)
                 [?__57553]
                 (state__58731))))
              (state__58731
               []
               (if
                (clojure.core/map? arg__58208)
                (clojure.core/let
                 [VAL__58210 (.valAt arg__58208 :aliases)]
                 (if
                  (clojure.core/map? VAL__58210)
                  (clojure.core/let
                   [X__58212 (clojure.core/set VAL__58210)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__58212))
                    (clojure.core/loop
                     [search_space__58732 (clojure.core/seq X__58212)]
                     (if
                      (clojure.core/seq search_space__58732)
                      (clojure.core/let
                       [elem__58213
                        (clojure.core/first search_space__58732)
                        result__58733
                        (clojure.core/let
                         [elem__58213_nth_0__
                          (clojure.core/nth elem__58213 0)
                          elem__58213_nth_1__
                          (clojure.core/nth elem__58213 1)]
                         (if
                          (clojure.core/symbol? elem__58213_nth_0__)
                          (clojure.core/let
                           [X__58215
                            (clojure.core/name elem__58213_nth_0__)]
                           (if
                            (clojure.core/= ?__57553 X__58215)
                            (if
                             (clojure.core/symbol? elem__58213_nth_1__)
                             (clojure.core/let
                              [X__58217
                               (clojure.core/name elem__58213_nth_1__)]
                              (clojure.core/case
                               X__58217
                               ("meander.zeta")
                               [?__57553]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58733)
                        (recur (clojure.core/next search_space__58732))
                        result__58733))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58730)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 2)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)]
             (if
              (clojure.core/seq? input__57548_nth_0__)
              (clojure.core/let
               [input__57548_nth_0___l__
                (clojure.core/take 1 input__57548_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__57548_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__57548_nth_0___r__
                  (clojure.core/drop 1 input__57548_nth_0__)]
                 (clojure.core/let
                  [input__57548_nth_0___l___nth_0__
                   (clojure.core/nth input__57548_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__57548_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__58195
                     (clojure.core/namespace
                      input__57548_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__57553 X__58195]
                     (clojure.core/let
                      [X__58197
                       (clojure.core/name
                        input__57548_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__58197
                       ("fold")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__58185 input__57548_nth_1__ ?__57553)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__58597)
                         (clojure.core/let
                          [[?__57553] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__57548)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__57548)
                             2)
                            (clojure.core/let
                             [input__57548_nth_0__
                              (clojure.core/nth input__57548 0)
                              input__57548_nth_1__
                              (clojure.core/nth input__57548 1)]
                             (if
                              (clojure.core/seq? input__57548_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__57548_nth_0__)
                                4)
                               (clojure.core/let
                                [input__57548_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  1)
                                 input__57548_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  2)
                                 input__57548_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__57548_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__57548_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__57548_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__57548_nth_0__]
                                    (clojure.core/let
                                     [?env input__57548_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__57598
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__57598
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
                                             (CATA__FN__57598
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
                               (state__58597))
                              (state__58597)))
                            (state__58597))
                           (state__58597)))))
                       (state__58597)))))
                   (state__58597))))
                (state__58597)))
              (state__58597)))
            (state__58597))
           (state__58597))))
        (state__58597
         []
         (if
          (clojure.core/vector? input__57548)
          (if
           (clojure.core/= (clojure.core/count input__57548) 5)
           (clojure.core/let
            [input__57548_nth_0__
             (clojure.core/nth input__57548 0)
             input__57548_nth_1__
             (clojure.core/nth input__57548 1)
             input__57548_nth_2__
             (clojure.core/nth input__57548 2)
             input__57548_nth_3__
             (clojure.core/nth input__57548 3)
             input__57548_nth_4__
             (clojure.core/nth input__57548 4)]
            (clojure.core/case
             input__57548_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__57548_nth_1__)
              (clojure.core/let
               [VAL__58220 (.valAt input__57548_nth_1__ :tag)]
               (clojure.core/case
                VAL__58220
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__57548_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__57548_nth_2__]
                  (clojure.core/let
                   [?fold-function input__57548_nth_3__]
                   (clojure.core/let
                    [?form input__57548_nth_4__]
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
                (state__58598)))
              (state__58598))
             (state__58598)))
           (state__58598))
          (state__58598)))
        (state__58598
         []
         (clojure.core/letfn
          [(def__58222
            [arg__58245 ?__57554]
            (clojure.core/letfn
             [(state__58735
               []
               (clojure.core/let
                [x__58246 "meander.zeta"]
                (if
                 (clojure.core/= ?__57554 x__58246)
                 [?__57554]
                 (state__58736))))
              (state__58736
               []
               (if
                (clojure.core/map? arg__58245)
                (clojure.core/let
                 [VAL__58247 (.valAt arg__58245 :aliases)]
                 (if
                  (clojure.core/map? VAL__58247)
                  (clojure.core/let
                   [X__58249 (clojure.core/set VAL__58247)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__58249))
                    (clojure.core/loop
                     [search_space__58737 (clojure.core/seq X__58249)]
                     (if
                      (clojure.core/seq search_space__58737)
                      (clojure.core/let
                       [elem__58250
                        (clojure.core/first search_space__58737)
                        result__58738
                        (clojure.core/let
                         [elem__58250_nth_0__
                          (clojure.core/nth elem__58250 0)
                          elem__58250_nth_1__
                          (clojure.core/nth elem__58250 1)]
                         (if
                          (clojure.core/symbol? elem__58250_nth_0__)
                          (clojure.core/let
                           [X__58252
                            (clojure.core/name elem__58250_nth_0__)]
                           (if
                            (clojure.core/= ?__57554 X__58252)
                            (if
                             (clojure.core/symbol? elem__58250_nth_1__)
                             (clojure.core/let
                              [X__58254
                               (clojure.core/name elem__58250_nth_1__)]
                              (clojure.core/case
                               X__58254
                               ("meander.zeta")
                               [?__57554]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58738)
                        (recur (clojure.core/next search_space__58737))
                        result__58738))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58735)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 2)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)]
             (if
              (clojure.core/seq? input__57548_nth_0__)
              (clojure.core/let
               [input__57548_nth_0___l__
                (clojure.core/take 1 input__57548_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__57548_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__57548_nth_0___r__
                  (clojure.core/drop 1 input__57548_nth_0__)]
                 (clojure.core/let
                  [input__57548_nth_0___l___nth_0__
                   (clojure.core/nth input__57548_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__57548_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__58232
                     (clojure.core/namespace
                      input__57548_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__57554 X__58232]
                     (clojure.core/let
                      [X__58234
                       (clojure.core/name
                        input__57548_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__58234
                       ("let")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__58222 input__57548_nth_1__ ?__57554)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__58599)
                         (clojure.core/let
                          [[?__57554] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__57548)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__57548)
                             2)
                            (clojure.core/let
                             [input__57548_nth_0__
                              (clojure.core/nth input__57548 0)
                              input__57548_nth_1__
                              (clojure.core/nth input__57548 1)]
                             (if
                              (clojure.core/seq? input__57548_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__57548_nth_0__)
                                3)
                               (clojure.core/let
                                [input__57548_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  0)
                                 input__57548_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  1)
                                 input__57548_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__57548_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__57548_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next input__57548_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__57548_nth_0__]
                                    (clojure.core/let
                                     [?env input__57548_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__57598
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
                                          (CATA__FN__57598
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
                               (state__58599))
                              (state__58599)))
                            (state__58599))
                           (state__58599)))))
                       (state__58599)))))
                   (state__58599))))
                (state__58599)))
              (state__58599)))
            (state__58599))
           (state__58599))))
        (state__58599
         []
         (clojure.core/letfn
          [(def__58256
            [arg__58279 ?__57555]
            (clojure.core/letfn
             [(state__58740
               []
               (clojure.core/let
                [x__58280 "meander.zeta"]
                (if
                 (clojure.core/= ?__57555 x__58280)
                 [?__57555]
                 (state__58741))))
              (state__58741
               []
               (if
                (clojure.core/map? arg__58279)
                (clojure.core/let
                 [VAL__58281 (.valAt arg__58279 :aliases)]
                 (if
                  (clojure.core/map? VAL__58281)
                  (clojure.core/let
                   [X__58283 (clojure.core/set VAL__58281)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__58283))
                    (clojure.core/loop
                     [search_space__58742 (clojure.core/seq X__58283)]
                     (if
                      (clojure.core/seq search_space__58742)
                      (clojure.core/let
                       [elem__58284
                        (clojure.core/first search_space__58742)
                        result__58743
                        (clojure.core/let
                         [elem__58284_nth_0__
                          (clojure.core/nth elem__58284 0)
                          elem__58284_nth_1__
                          (clojure.core/nth elem__58284 1)]
                         (if
                          (clojure.core/symbol? elem__58284_nth_0__)
                          (clojure.core/let
                           [X__58286
                            (clojure.core/name elem__58284_nth_0__)]
                           (if
                            (clojure.core/= ?__57555 X__58286)
                            (if
                             (clojure.core/symbol? elem__58284_nth_1__)
                             (clojure.core/let
                              [X__58288
                               (clojure.core/name elem__58284_nth_1__)]
                              (clojure.core/case
                               X__58288
                               ("meander.zeta")
                               [?__57555]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58743)
                        (recur (clojure.core/next search_space__58742))
                        result__58743))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58740)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 2)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)]
             (if
              (clojure.core/seq? input__57548_nth_0__)
              (clojure.core/let
               [input__57548_nth_0___l__
                (clojure.core/take 1 input__57548_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__57548_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__57548_nth_0___r__
                  (clojure.core/drop 1 input__57548_nth_0__)]
                 (clojure.core/let
                  [input__57548_nth_0___l___nth_0__
                   (clojure.core/nth input__57548_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__57548_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__58266
                     (clojure.core/namespace
                      input__57548_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__57555 X__58266]
                     (clojure.core/let
                      [X__58268
                       (clojure.core/name
                        input__57548_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__58268
                       ("not")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__58256 input__57548_nth_1__ ?__57555)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__58600)
                         (clojure.core/let
                          [[?__57555] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__57548)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__57548)
                             2)
                            (clojure.core/let
                             [input__57548_nth_0__
                              (clojure.core/nth input__57548 0)
                              input__57548_nth_1__
                              (clojure.core/nth input__57548 1)]
                             (if
                              (clojure.core/seq? input__57548_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__57548_nth_0__)
                                2)
                               (clojure.core/let
                                [input__57548_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__57548_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__57548_nth_0__]
                                  (clojure.core/let
                                   [?env input__57548_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__57598
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
                               (state__58600))
                              (state__58600)))
                            (state__58600))
                           (state__58600)))))
                       (state__58600)))))
                   (state__58600))))
                (state__58600)))
              (state__58600)))
            (state__58600))
           (state__58600))))
        (state__58600
         []
         (clojure.core/letfn
          [(def__58290
            [arg__58313 ?__57556]
            (clojure.core/letfn
             [(state__58745
               []
               (clojure.core/let
                [x__58314 "meander.zeta"]
                (if
                 (clojure.core/= ?__57556 x__58314)
                 [?__57556]
                 (state__58746))))
              (state__58746
               []
               (if
                (clojure.core/map? arg__58313)
                (clojure.core/let
                 [VAL__58315 (.valAt arg__58313 :aliases)]
                 (if
                  (clojure.core/map? VAL__58315)
                  (clojure.core/let
                   [X__58317 (clojure.core/set VAL__58315)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__58317))
                    (clojure.core/loop
                     [search_space__58747 (clojure.core/seq X__58317)]
                     (if
                      (clojure.core/seq search_space__58747)
                      (clojure.core/let
                       [elem__58318
                        (clojure.core/first search_space__58747)
                        result__58748
                        (clojure.core/let
                         [elem__58318_nth_0__
                          (clojure.core/nth elem__58318 0)
                          elem__58318_nth_1__
                          (clojure.core/nth elem__58318 1)]
                         (if
                          (clojure.core/symbol? elem__58318_nth_0__)
                          (clojure.core/let
                           [X__58320
                            (clojure.core/name elem__58318_nth_0__)]
                           (if
                            (clojure.core/= ?__57556 X__58320)
                            (if
                             (clojure.core/symbol? elem__58318_nth_1__)
                             (clojure.core/let
                              [X__58322
                               (clojure.core/name elem__58318_nth_1__)]
                              (clojure.core/case
                               X__58322
                               ("meander.zeta")
                               [?__57556]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58748)
                        (recur (clojure.core/next search_space__58747))
                        result__58748))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58745)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 2)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)]
             (if
              (clojure.core/seq? input__57548_nth_0__)
              (clojure.core/let
               [input__57548_nth_0___l__
                (clojure.core/take 1 input__57548_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__57548_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__57548_nth_0___r__
                  (clojure.core/drop 1 input__57548_nth_0__)]
                 (clojure.core/let
                  [input__57548_nth_0___l___nth_0__
                   (clojure.core/nth input__57548_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__57548_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__58300
                     (clojure.core/namespace
                      input__57548_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__57556 X__58300]
                     (clojure.core/let
                      [X__58302
                       (clojure.core/name
                        input__57548_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__58302
                       ("or")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__58290 input__57548_nth_1__ ?__57556)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__58601)
                         (clojure.core/let
                          [[?__57556] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__57548)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__57548)
                             2)
                            (clojure.core/let
                             [input__57548_nth_0__
                              (clojure.core/nth input__57548 0)
                              input__57548_nth_1__
                              (clojure.core/nth input__57548 1)]
                             (if
                              (clojure.core/seq? input__57548_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__57548_nth_0__)
                                3)
                               (clojure.core/let
                                [input__57548_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  1)
                                 input__57548_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__57548_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__57548_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__57548_nth_0__]
                                   (clojure.core/let
                                    [?env input__57548_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__57598
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
                                         (CATA__FN__57598
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
                               (state__58601))
                              (state__58601)))
                            (state__58601))
                           (state__58601)))))
                       (state__58601)))))
                   (state__58601))))
                (state__58601)))
              (state__58601)))
            (state__58601))
           (state__58601))))
        (state__58601
         []
         (clojure.core/letfn
          [(def__58324
            [arg__58347 ?__57557]
            (clojure.core/letfn
             [(state__58750
               []
               (clojure.core/let
                [x__58348 "meander.zeta"]
                (if
                 (clojure.core/= ?__57557 x__58348)
                 [?__57557]
                 (state__58751))))
              (state__58751
               []
               (if
                (clojure.core/map? arg__58347)
                (clojure.core/let
                 [VAL__58349 (.valAt arg__58347 :aliases)]
                 (if
                  (clojure.core/map? VAL__58349)
                  (clojure.core/let
                   [X__58351 (clojure.core/set VAL__58349)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__58351))
                    (clojure.core/loop
                     [search_space__58752 (clojure.core/seq X__58351)]
                     (if
                      (clojure.core/seq search_space__58752)
                      (clojure.core/let
                       [elem__58352
                        (clojure.core/first search_space__58752)
                        result__58753
                        (clojure.core/let
                         [elem__58352_nth_0__
                          (clojure.core/nth elem__58352 0)
                          elem__58352_nth_1__
                          (clojure.core/nth elem__58352 1)]
                         (if
                          (clojure.core/symbol? elem__58352_nth_0__)
                          (clojure.core/let
                           [X__58354
                            (clojure.core/name elem__58352_nth_0__)]
                           (if
                            (clojure.core/= ?__57557 X__58354)
                            (if
                             (clojure.core/symbol? elem__58352_nth_1__)
                             (clojure.core/let
                              [X__58356
                               (clojure.core/name elem__58352_nth_1__)]
                              (clojure.core/case
                               X__58356
                               ("meander.zeta")
                               [?__57557]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58753)
                        (recur (clojure.core/next search_space__58752))
                        result__58753))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58750)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 2)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)]
             (if
              (clojure.core/seq? input__57548_nth_0__)
              (clojure.core/let
               [input__57548_nth_0___l__
                (clojure.core/take 1 input__57548_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__57548_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__57548_nth_0___r__
                  (clojure.core/drop 1 input__57548_nth_0__)]
                 (clojure.core/let
                  [input__57548_nth_0___l___nth_0__
                   (clojure.core/nth input__57548_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__57548_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__58334
                     (clojure.core/namespace
                      input__57548_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__57557 X__58334]
                     (clojure.core/let
                      [X__58336
                       (clojure.core/name
                        input__57548_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__58336
                       ("re")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__58324 input__57548_nth_1__ ?__57557)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__58602)
                         (clojure.core/let
                          [[?__57557] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__57548)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__57548)
                             2)
                            (clojure.core/let
                             [input__57548_nth_0__
                              (clojure.core/nth input__57548 0)
                              input__57548_nth_1__
                              (clojure.core/nth input__57548 1)]
                             (if
                              (clojure.core/seq? input__57548_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__57548_nth_0__)
                                2)
                               (clojure.core/let
                                [input__57548_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__57548_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__57548_nth_0__]
                                  (clojure.core/let
                                   [?env input__57548_nth_1__]
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
                               (state__58602))
                              (state__58602)))
                            (state__58602))
                           (state__58602)))))
                       (state__58602)))))
                   (state__58602))))
                (state__58602)))
              (state__58602)))
            (state__58602))
           (state__58602))))
        (state__58602
         []
         (clojure.core/letfn
          [(def__58358
            [arg__58381 ?__57558]
            (clojure.core/letfn
             [(state__58755
               []
               (clojure.core/let
                [x__58382 "meander.zeta"]
                (if
                 (clojure.core/= ?__57558 x__58382)
                 [?__57558]
                 (state__58756))))
              (state__58756
               []
               (if
                (clojure.core/map? arg__58381)
                (clojure.core/let
                 [VAL__58383 (.valAt arg__58381 :aliases)]
                 (if
                  (clojure.core/map? VAL__58383)
                  (clojure.core/let
                   [X__58385 (clojure.core/set VAL__58383)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__58385))
                    (clojure.core/loop
                     [search_space__58757 (clojure.core/seq X__58385)]
                     (if
                      (clojure.core/seq search_space__58757)
                      (clojure.core/let
                       [elem__58386
                        (clojure.core/first search_space__58757)
                        result__58758
                        (clojure.core/let
                         [elem__58386_nth_0__
                          (clojure.core/nth elem__58386 0)
                          elem__58386_nth_1__
                          (clojure.core/nth elem__58386 1)]
                         (if
                          (clojure.core/symbol? elem__58386_nth_0__)
                          (clojure.core/let
                           [X__58388
                            (clojure.core/name elem__58386_nth_0__)]
                           (if
                            (clojure.core/= ?__57558 X__58388)
                            (if
                             (clojure.core/symbol? elem__58386_nth_1__)
                             (clojure.core/let
                              [X__58390
                               (clojure.core/name elem__58386_nth_1__)]
                              (clojure.core/case
                               X__58390
                               ("meander.zeta")
                               [?__57558]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58758)
                        (recur (clojure.core/next search_space__58757))
                        result__58758))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58755)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 2)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)]
             (if
              (clojure.core/seq? input__57548_nth_0__)
              (clojure.core/let
               [input__57548_nth_0___l__
                (clojure.core/take 1 input__57548_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__57548_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__57548_nth_0___r__
                  (clojure.core/drop 1 input__57548_nth_0__)]
                 (clojure.core/let
                  [input__57548_nth_0___l___nth_0__
                   (clojure.core/nth input__57548_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__57548_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__58368
                     (clojure.core/namespace
                      input__57548_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__57558 X__58368]
                     (clojure.core/let
                      [X__58370
                       (clojure.core/name
                        input__57548_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__58370
                       ("re")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__58358 input__57548_nth_1__ ?__57558)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__58603)
                         (clojure.core/let
                          [[?__57558] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__57548)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__57548)
                             2)
                            (clojure.core/let
                             [input__57548_nth_0__
                              (clojure.core/nth input__57548 0)
                              input__57548_nth_1__
                              (clojure.core/nth input__57548 1)]
                             (if
                              (clojure.core/seq? input__57548_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__57548_nth_0__)
                                3)
                               (clojure.core/let
                                [input__57548_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  1)
                                 input__57548_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__57548_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__57548_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__57548_nth_0__]
                                   (clojure.core/let
                                    [?env input__57548_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__57598
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
                               (state__58603))
                              (state__58603)))
                            (state__58603))
                           (state__58603)))))
                       (state__58603)))))
                   (state__58603))))
                (state__58603)))
              (state__58603)))
            (state__58603))
           (state__58603))))
        (state__58603
         []
         (clojure.core/letfn
          [(def__58392
            [arg__58415 ?__57559]
            (clojure.core/letfn
             [(state__58760
               []
               (clojure.core/let
                [x__58416 "meander.zeta"]
                (if
                 (clojure.core/= ?__57559 x__58416)
                 [?__57559]
                 (state__58761))))
              (state__58761
               []
               (if
                (clojure.core/map? arg__58415)
                (clojure.core/let
                 [VAL__58417 (.valAt arg__58415 :aliases)]
                 (if
                  (clojure.core/map? VAL__58417)
                  (clojure.core/let
                   [X__58419 (clojure.core/set VAL__58417)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__58419))
                    (clojure.core/loop
                     [search_space__58762 (clojure.core/seq X__58419)]
                     (if
                      (clojure.core/seq search_space__58762)
                      (clojure.core/let
                       [elem__58420
                        (clojure.core/first search_space__58762)
                        result__58763
                        (clojure.core/let
                         [elem__58420_nth_0__
                          (clojure.core/nth elem__58420 0)
                          elem__58420_nth_1__
                          (clojure.core/nth elem__58420 1)]
                         (if
                          (clojure.core/symbol? elem__58420_nth_0__)
                          (clojure.core/let
                           [X__58422
                            (clojure.core/name elem__58420_nth_0__)]
                           (if
                            (clojure.core/= ?__57559 X__58422)
                            (if
                             (clojure.core/symbol? elem__58420_nth_1__)
                             (clojure.core/let
                              [X__58424
                               (clojure.core/name elem__58420_nth_1__)]
                              (clojure.core/case
                               X__58424
                               ("meander.zeta")
                               [?__57559]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58763)
                        (recur (clojure.core/next search_space__58762))
                        result__58763))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58760)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 2)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)]
             (if
              (clojure.core/seq? input__57548_nth_0__)
              (clojure.core/let
               [input__57548_nth_0___l__
                (clojure.core/take 1 input__57548_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__57548_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__57548_nth_0___r__
                  (clojure.core/drop 1 input__57548_nth_0__)]
                 (clojure.core/let
                  [input__57548_nth_0___l___nth_0__
                   (clojure.core/nth input__57548_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__57548_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__58402
                     (clojure.core/namespace
                      input__57548_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__57559 X__58402]
                     (clojure.core/let
                      [X__58404
                       (clojure.core/name
                        input__57548_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__58404
                       ("string")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__58392 input__57548_nth_1__ ?__57559)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__58604)
                         (clojure.core/let
                          [[?__57559] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__57548)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__57548)
                             2)
                            (clojure.core/let
                             [input__57548_nth_0__
                              (clojure.core/nth input__57548 0)
                              input__57548_nth_1__
                              (clojure.core/nth input__57548 1)]
                             (if
                              (clojure.core/seq? input__57548_nth_0__)
                              (clojure.core/let
                               [input__57548_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__57548_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__57548_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__57548_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__57548_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__57548_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__57548_nth_0__]
                                   (clojure.core/let
                                    [?env input__57548_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__57598
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
                                (state__58604)))
                              (state__58604)))
                            (state__58604))
                           (state__58604)))))
                       (state__58604)))))
                   (state__58604))))
                (state__58604)))
              (state__58604)))
            (state__58604))
           (state__58604))))
        (state__58604
         []
         (clojure.core/letfn
          [(def__58426
            [arg__58449 ?__57560]
            (clojure.core/letfn
             [(state__58765
               []
               (clojure.core/let
                [x__58450 "meander.zeta"]
                (if
                 (clojure.core/= ?__57560 x__58450)
                 [?__57560]
                 (state__58766))))
              (state__58766
               []
               (if
                (clojure.core/map? arg__58449)
                (clojure.core/let
                 [VAL__58451 (.valAt arg__58449 :aliases)]
                 (if
                  (clojure.core/map? VAL__58451)
                  (clojure.core/let
                   [X__58453 (clojure.core/set VAL__58451)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__58453))
                    (clojure.core/loop
                     [search_space__58767 (clojure.core/seq X__58453)]
                     (if
                      (clojure.core/seq search_space__58767)
                      (clojure.core/let
                       [elem__58454
                        (clojure.core/first search_space__58767)
                        result__58768
                        (clojure.core/let
                         [elem__58454_nth_0__
                          (clojure.core/nth elem__58454 0)
                          elem__58454_nth_1__
                          (clojure.core/nth elem__58454 1)]
                         (if
                          (clojure.core/symbol? elem__58454_nth_0__)
                          (clojure.core/let
                           [X__58456
                            (clojure.core/name elem__58454_nth_0__)]
                           (if
                            (clojure.core/= ?__57560 X__58456)
                            (if
                             (clojure.core/symbol? elem__58454_nth_1__)
                             (clojure.core/let
                              [X__58458
                               (clojure.core/name elem__58454_nth_1__)]
                              (clojure.core/case
                               X__58458
                               ("meander.zeta")
                               [?__57560]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58768)
                        (recur (clojure.core/next search_space__58767))
                        result__58768))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58765)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 2)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)]
             (if
              (clojure.core/seq? input__57548_nth_0__)
              (clojure.core/let
               [input__57548_nth_0___l__
                (clojure.core/take 1 input__57548_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__57548_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__57548_nth_0___r__
                  (clojure.core/drop 1 input__57548_nth_0__)]
                 (clojure.core/let
                  [input__57548_nth_0___l___nth_0__
                   (clojure.core/nth input__57548_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__57548_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__58436
                     (clojure.core/namespace
                      input__57548_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__57560 X__58436]
                     (clojure.core/let
                      [X__58438
                       (clojure.core/name
                        input__57548_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__58438
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__58426 input__57548_nth_1__ ?__57560)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__58605)
                         (clojure.core/let
                          [[?__57560] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__57548)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__57548)
                             2)
                            (clojure.core/let
                             [input__57548_nth_0__
                              (clojure.core/nth input__57548 0)
                              input__57548_nth_1__
                              (clojure.core/nth input__57548 1)]
                             (if
                              (clojure.core/seq? input__57548_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__57548_nth_0__)
                                2)
                               (clojure.core/let
                                [input__57548_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__57548_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__57548_nth_0__]
                                  (clojure.core/let
                                   [?env input__57548_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__57598 [?name ?env])]
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
                               (state__58605))
                              (state__58605)))
                            (state__58605))
                           (state__58605)))))
                       (state__58605)))))
                   (state__58605))))
                (state__58605)))
              (state__58605)))
            (state__58605))
           (state__58605))))
        (state__58605
         []
         (clojure.core/letfn
          [(def__58460
            [arg__58483 ?__57561]
            (clojure.core/letfn
             [(state__58770
               []
               (clojure.core/let
                [x__58484 "meander.zeta"]
                (if
                 (clojure.core/= ?__57561 x__58484)
                 [?__57561]
                 (state__58771))))
              (state__58771
               []
               (if
                (clojure.core/map? arg__58483)
                (clojure.core/let
                 [VAL__58485 (.valAt arg__58483 :aliases)]
                 (if
                  (clojure.core/map? VAL__58485)
                  (clojure.core/let
                   [X__58487 (clojure.core/set VAL__58485)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__58487))
                    (clojure.core/loop
                     [search_space__58772 (clojure.core/seq X__58487)]
                     (if
                      (clojure.core/seq search_space__58772)
                      (clojure.core/let
                       [elem__58488
                        (clojure.core/first search_space__58772)
                        result__58773
                        (clojure.core/let
                         [elem__58488_nth_0__
                          (clojure.core/nth elem__58488 0)
                          elem__58488_nth_1__
                          (clojure.core/nth elem__58488 1)]
                         (if
                          (clojure.core/symbol? elem__58488_nth_0__)
                          (clojure.core/let
                           [X__58490
                            (clojure.core/name elem__58488_nth_0__)]
                           (if
                            (clojure.core/= ?__57561 X__58490)
                            (if
                             (clojure.core/symbol? elem__58488_nth_1__)
                             (clojure.core/let
                              [X__58492
                               (clojure.core/name elem__58488_nth_1__)]
                              (clojure.core/case
                               X__58492
                               ("meander.zeta")
                               [?__57561]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58773)
                        (recur (clojure.core/next search_space__58772))
                        result__58773))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58770)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 2)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)]
             (if
              (clojure.core/seq? input__57548_nth_0__)
              (clojure.core/let
               [input__57548_nth_0___l__
                (clojure.core/take 1 input__57548_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__57548_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__57548_nth_0___r__
                  (clojure.core/drop 1 input__57548_nth_0__)]
                 (clojure.core/let
                  [input__57548_nth_0___l___nth_0__
                   (clojure.core/nth input__57548_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__57548_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__58470
                     (clojure.core/namespace
                      input__57548_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__57561 X__58470]
                     (clojure.core/let
                      [X__58472
                       (clojure.core/name
                        input__57548_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__58472
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__58460 input__57548_nth_1__ ?__57561)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__58606)
                         (clojure.core/let
                          [[?__57561] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__57548)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__57548)
                             2)
                            (clojure.core/let
                             [input__57548_nth_0__
                              (clojure.core/nth input__57548 0)
                              input__57548_nth_1__
                              (clojure.core/nth input__57548 1)]
                             (if
                              (clojure.core/seq? input__57548_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__57548_nth_0__)
                                3)
                               (clojure.core/let
                                [input__57548_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  1)
                                 input__57548_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__57548_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__57548_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__57548_nth_0__]
                                   (clojure.core/let
                                    [?env input__57548_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__57598
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
                                         (CATA__FN__57598
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
                               (state__58606))
                              (state__58606)))
                            (state__58606))
                           (state__58606)))))
                       (state__58606)))))
                   (state__58606))))
                (state__58606)))
              (state__58606)))
            (state__58606))
           (state__58606))))
        (state__58606
         []
         (clojure.core/letfn
          [(def__58494
            [arg__58517 ?__57562]
            (clojure.core/letfn
             [(state__58775
               []
               (clojure.core/let
                [x__58518 "meander.zeta"]
                (if
                 (clojure.core/= ?__57562 x__58518)
                 [?__57562]
                 (state__58776))))
              (state__58776
               []
               (if
                (clojure.core/map? arg__58517)
                (clojure.core/let
                 [VAL__58519 (.valAt arg__58517 :aliases)]
                 (if
                  (clojure.core/map? VAL__58519)
                  (clojure.core/let
                   [X__58521 (clojure.core/set VAL__58519)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__58521))
                    (clojure.core/loop
                     [search_space__58777 (clojure.core/seq X__58521)]
                     (if
                      (clojure.core/seq search_space__58777)
                      (clojure.core/let
                       [elem__58522
                        (clojure.core/first search_space__58777)
                        result__58778
                        (clojure.core/let
                         [elem__58522_nth_0__
                          (clojure.core/nth elem__58522 0)
                          elem__58522_nth_1__
                          (clojure.core/nth elem__58522 1)]
                         (if
                          (clojure.core/symbol? elem__58522_nth_0__)
                          (clojure.core/let
                           [X__58524
                            (clojure.core/name elem__58522_nth_0__)]
                           (if
                            (clojure.core/= ?__57562 X__58524)
                            (if
                             (clojure.core/symbol? elem__58522_nth_1__)
                             (clojure.core/let
                              [X__58526
                               (clojure.core/name elem__58522_nth_1__)]
                              (clojure.core/case
                               X__58526
                               ("meander.zeta")
                               [?__57562]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__58778)
                        (recur (clojure.core/next search_space__58777))
                        result__58778))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__58775)))]
          (if
           (clojure.core/vector? input__57548)
           (if
            (clojure.core/= (clojure.core/count input__57548) 2)
            (clojure.core/let
             [input__57548_nth_0__
              (clojure.core/nth input__57548 0)
              input__57548_nth_1__
              (clojure.core/nth input__57548 1)]
             (if
              (clojure.core/seq? input__57548_nth_0__)
              (clojure.core/let
               [input__57548_nth_0___l__
                (clojure.core/take 1 input__57548_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__57548_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__57548_nth_0___r__
                  (clojure.core/drop 1 input__57548_nth_0__)]
                 (clojure.core/let
                  [input__57548_nth_0___l___nth_0__
                   (clojure.core/nth input__57548_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__57548_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__58504
                     (clojure.core/namespace
                      input__57548_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__57562 X__58504]
                     (clojure.core/let
                      [X__58506
                       (clojure.core/name
                        input__57548_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__58506
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__58494 input__57548_nth_1__ ?__57562)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__58607)
                         (clojure.core/let
                          [[?__57562] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__57548)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__57548)
                             2)
                            (clojure.core/let
                             [input__57548_nth_0__
                              (clojure.core/nth input__57548 0)
                              input__57548_nth_1__
                              (clojure.core/nth input__57548 1)]
                             (if
                              (clojure.core/seq? input__57548_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__57548_nth_0__)
                                5)
                               (clojure.core/let
                                [input__57548_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  1)
                                 input__57548_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  2)
                                 input__57548_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  3)
                                 input__57548_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__57548_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__57548_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__57548_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name input__57548_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__57548_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__57548_nth_0__]
                                     (clojure.core/let
                                      [?env input__57548_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__9229__auto__
                                           (CATA__FN__57598
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
                                           (CATA__FN__57598
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
                                           (CATA__FN__57598
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
                                 (state__58607)))
                               (state__58607))
                              (state__58607)))
                            (state__58607))
                           (state__58607)))))
                       (state__58607)))))
                   (state__58607))))
                (state__58607)))
              (state__58607)))
            (state__58607))
           (state__58607))))
        (state__58607
         []
         (if
          (clojure.core/vector? input__57548)
          (if
           (clojure.core/= (clojure.core/count input__57548) 2)
           (clojure.core/let
            [input__57548_nth_0__ (clojure.core/nth input__57548 0)]
            (clojure.core/letfn
             [(state__58780
               []
               (clojure.core/let
                [input__57548_nth_1__
                 (clojure.core/nth input__57548 1)]
                (clojure.core/letfn
                 [(state__58785
                   []
                   (if
                    (clojure.core/seq? input__57548_nth_0__)
                    (clojure.core/let
                     [?sequence input__57548_nth_0__]
                     (clojure.core/let
                      [?env input__57548_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__57598
                            ['meander.dev.parse.zeta/parse-sequential
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
                    (state__58786)))
                  (state__58786
                   []
                   (if
                    (clojure.core/map? input__57548_nth_0__)
                    (clojure.core/let
                     [?map input__57548_nth_0__]
                     (clojure.core/let
                      [?env input__57548_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__57598
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
                    (state__58781)))]
                 (state__58785))))
              (state__58781
               []
               (if
                (clojure.core/symbol? input__57548_nth_0__)
                (clojure.core/let
                 [X__58536
                  (clojure.core/namespace input__57548_nth_0__)]
                 (clojure.core/let
                  [X__58538 (clojure.core/name input__57548_nth_0__)]
                  (if
                   (clojure.core/string? X__58538)
                   (clojure.core/letfn
                    [(state__58787
                      []
                      (clojure.core/let
                       [ret__58539
                        (clojure.core/re-matches #"_.*" X__58538)]
                       (if
                        (clojure.core/some? ret__58539)
                        (clojure.core/let
                         [?name ret__58539]
                         (clojure.core/let
                          [?symbol input__57548_nth_0__]
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
                        (state__58788))))
                     (state__58788
                      []
                      (clojure.core/let
                       [ret__58546
                        (clojure.core/re-matches #".+#" X__58538)]
                       (if
                        (clojure.core/some? ret__58546)
                        (clojure.core/let
                         [?name ret__58546]
                         (clojure.core/let
                          [?symbol input__57548_nth_0__]
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
                        (state__58789))))
                     (state__58789
                      []
                      (clojure.core/let
                       [ret__58553
                        (clojure.core/re-matches #"%.+" X__58538)]
                       (if
                        (clojure.core/some? ret__58553)
                        (clojure.core/let
                         [?name ret__58553]
                         (clojure.core/let
                          [?symbol input__57548_nth_0__]
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
                        (state__58790))))
                     (state__58790
                      []
                      (clojure.core/let
                       [ret__58560
                        (clojure.core/re-matches #"\*.+" X__58538)]
                       (if
                        (clojure.core/some? ret__58560)
                        (clojure.core/let
                         [?name ret__58560]
                         (clojure.core/let
                          [?symbol input__57548_nth_0__]
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
                        (state__58791))))
                     (state__58791
                      []
                      (clojure.core/let
                       [ret__58567
                        (clojure.core/re-matches #"\!.+" X__58538)]
                       (if
                        (clojure.core/some? ret__58567)
                        (clojure.core/let
                         [?name ret__58567]
                         (clojure.core/let
                          [?symbol input__57548_nth_0__]
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
                        (state__58792))))
                     (state__58792
                      []
                      (clojure.core/let
                       [ret__58574
                        (clojure.core/re-matches #"\?.+" X__58538)]
                       (if
                        (clojure.core/some? ret__58574)
                        (clojure.core/let
                         [?name ret__58574]
                         (clojure.core/let
                          [?symbol input__57548_nth_0__]
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
                        (state__58782))))]
                    (state__58787))
                   (state__58782))))
                (state__58782)))
              (state__58782
               []
               (if
                (string? input__57548_nth_0__)
                (clojure.core/let
                 [?x input__57548_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10169__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10169__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10169__auto__)))))
                (state__58783)))
              (state__58783
               []
               (if
                (char? input__57548_nth_0__)
                (clojure.core/let
                 [?x input__57548_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10169__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10169__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10169__auto__)))))
                (state__58784)))
              (state__58784
               []
               (clojure.core/let
                [?x input__57548_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__10169__auto__
                  (if
                   (meander.runtime.zeta/fail? e__10169__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__10169__auto__))))))]
             (state__58780)))
           (state__58608))
          (state__58608)))
        (state__58608
         []
         (clojure.core/let
          [?x input__57548]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__10169__auto__
            (if
             (meander.runtime.zeta/fail? e__10169__auto__)
             (meander.runtime.zeta/fail)
             (throw e__10169__auto__))))))]
       (state__58586)))]
    (clojure.core/let
     [x__7926__auto__ (CATA__FN__57598 input__57548)]
     (if
      (meander.runtime.zeta/fail? x__7926__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__57600] x__7926__auto__]
       CATA_RETURN__57600))))]
  (if
   (meander.runtime.zeta/fail? ret__8106__auto__)
   nil
   ret__8106__auto__)))
