(ns meander.match.epsilon-test
  (:require
   [clojure.test :as t]
   [meander.match.epsilon :as m.match :include-macros true]
   [meander.match.syntax.epsilon :as m.match.syntax :include-macros true])
  #?(:cljs
     (:require-macros [meander.match.epsilon-test :refer [match-compile]])))

(defmacro match-compile
  {:style/indent 1}
  [target & clauses]
  `(let [analysis# (m.match/analyze-match-args '[~target ~@clauses] '~(meta &form))
         matrix# (get analysis# :matrix)]
     (m.match/compile '~'[TARGET] matrix#)))

;; ;; Seq compilation
;; ;; ---------------------------------------------------------------------

;; (t/deftest seq-compilation-empty-seq-test
;;   (t/testing "no literal seq inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :check-seq
;;                                :then {:op :branch
;;                                       :arms [{:op :pass
;;                                               :then {:op :branch
;;                                                      :arms [{:op :check-equal
;;                                                              :target-1 {:op :eval :form TARGET}
;;                                                              :then {:value true :op :return}
;;                                                              :target-2 {:op :eval :form []}}
;;                                                             {:op :fail}]}}]}
;;                                :target {:op :eval :form TARGET}}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              ^{:meander.epsilon/infer-literal-seq false}
;;              (match-compile x () true))))

;;   (t/testing "literal seq inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :check-lit
;;                                :value {:op :eval :form ()}
;;                                :then {:value true :op :return}
;;                                :target {:op :eval :form TARGET}}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              (match-compile x () true)))))

;; (t/deftest seq-compilation-empty-seq-with-rest-as-wildcard-test
;;   (t/is (= '{:op :branch
;;              :arms [{:op :branch
;;                      :arms [{:op :check-seq
;;                              :then {:op :branch
;;                                     :arms [{:op :pass
;;                                             :then {:op :branch
;;                                                    :arms [{:op :branch
;;                                                            :arms [{:value true :op :return}
;;                                                                   {:op :fail}]}
;;                                                           {:op :fail}]}}]}
;;                              :target {:op :eval :form TARGET}}
;;                             {:op :fail}]}
;;                     {:op :fail}]}
;;            (match-compile x (& _) true))))

;; (t/deftest seq-compilation-empty-seq-with-rest-as-logic-variable-test
;;   (t/testing "with literal seq inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :check-seq
;;                                :then {:op :branch
;;                                       :arms [{:op :pass
;;                                               :then {:op :branch
;;                                                      :arms [{:op :branch
;;                                                              :arms [{:op :branch
;;                                                                      :arms [{:op :lvr-bind
;;                                                                              :symbol ?1
;;                                                                              :then {:value true :op :return}
;;                                                                              :target {:op :eval :form TARGET}}
;;                                                                             {:op :fail}]}
;;                                                                     {:op :fail}]}
;;                                                             {:op :fail}]}}]}
;;                                :target {:op :eval :form TARGET}}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              (match-compile x (& ?1) true))))

;;   (t/testing "without literal seq inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :check-seq
;;                                :then {:op :branch
;;                                       :arms [{:op :pass
;;                                               :then {:op :branch
;;                                                      :arms [{:op :branch
;;                                                              :arms [{:op :branch
;;                                                                      :arms [{:op :lvr-bind
;;                                                                              :symbol ?1
;;                                                                              :then {:value true :op :return}
;;                                                                              :target {:op :eval :form TARGET}}
;;                                                                             {:op :fail}]}
;;                                                                     {:op :fail}]}
;;                                                             {:op :fail}]}}]}
;;                                :target {:op :eval :form TARGET}}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              ^{:meander.epsilon/infer-literal-seq false}
;;              (match-compile x (& ?1) true)))))

;; (t/deftest seq-compilation-empty-seq-with-as-test
;;   (t/testing "without literal seq inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :branch
;;                                :arms [{:op :check-seq
;;                                        :then {:op :branch
;;                                               :arms [{:op :pass
;;                                                       :then {:op :branch
;;                                                              :arms [{:op :check-equal
;;                                                                      :target-1 {:op :eval :form TARGET}
;;                                                                      :then {:op :branch
;;                                                                             :arms [{:op :lvr-bind
;;                                                                                     :symbol ?1
;;                                                                                     :then {:value true :op :return}
;;                                                                                     :target {:op :eval :form TARGET}}
;;                                                                                    {:op :fail}]}
;;                                                                      :target-2 {:op :eval :form []}}
;;                                                                     {:op :fail}]}}]}
;;                                        :target {:op :eval :form TARGET}}
;;                                       {:op :fail}]}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              ^{:meander.epsilon/infer-literal-seq false}
;;              (match-compile x (:as ?1) true))))

;;   (t/testing "with literal seq inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :branch
;;                                :arms [{:op :check-lit
;;                                        :value {:op :eval :form ()}
;;                                        :then {:op :branch
;;                                               :arms [{:op :lvr-bind
;;                                                       :symbol ?1
;;                                                       :then {:value true :op :return}
;;                                                       :target {:op :eval :form TARGET}}
;;                                                      {:op :fail}]}
;;                                        :target {:op :eval :form TARGET}}
;;                                       {:op :fail}]}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              (match-compile x (:as ?1) true)))))



;; (t/deftest seq-compilation-unit-seq-test
;;   (t/is (= '{:op :branch
;;              :arms [{:op :branch
;;                      :arms [{:op :check-seq
;;                              :then {:op :branch
;;                                     :arms [{:op :pass
;;                                             :then {:op :branch
;;                                                    :arms [{:op :check-bounds
;;                                                            :kind :seq
;;                                                            :length 1
;;                                                            :then
;;                                                            {:op :branch
;;                                                             :arms [{:op :check-equal
;;                                                                     :target-1 {:op :eval :form TARGET}
;;                                                                     :then {:value true :op :return}
;;                                                                     :target-2 {:op :eval :form [1]}}
;;                                                                    {:op :fail}]}
;;                                                            :target {:op :eval :form TARGET}}
;;                                                           {:op :fail}]}}]}
;;                              :target {:op :eval :form TARGET}}
;;                             {:op :fail}]}
;;                     {:op :fail}]}
;;            ^{:meander.epsilon/infer-literal-seq false}
;;            (match-compile x (1) true)))

;;   (t/testing "with literal seq inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :check-lit
;;                                :value {:op :eval :form (1)}
;;                                :then {:value true :op :return}
;;                                :target {:op :eval :form TARGET}}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              (match-compile x (1) true)))))

;; (t/deftest seq-compilation-unit-seq-with-rest-as-wildcard-test
;;   (t/testing "with literal seq inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :check-seq
;;                                :then {:op :branch
;;                                       :arms [{:op :pass
;;                                               :then {:op :branch
;;                                                      :arms [{:symbol TARGET_l__
;;                                                              :value {:op :take
;;                                                                      :n 1
;;                                                                      :kind :seq
;;                                                                      :target {:op :eval :form TARGET}}
;;                                                              :op :bind
;;                                                              :then {:op :check-bounds
;;                                                                     :kind :seq
;;                                                                     :length 1
;;                                                                     :then {:op :bind
;;                                                                            :symbol TARGET_r__
;;                                                                            :value {:op :drop
;;                                                                                    :n 1
;;                                                                                    :kind :seq
;;                                                                                    :target {:op :eval :form TARGET}}
;;                                                                            :then {:op :branch
;;                                                                                   :arms [{:op :check-equal
;;                                                                                           :target-1 {:op :eval :form TARGET_l__}

;;                                                                                           :target-2 {:op :eval :form [1]}
;;                                                                                           :then {:op :branch
;;                                                                                                  :arms [{:op :return
;;                                                                                                          :value true}
;;                                                                                                         {:op :fail}]}}
;;                                                                                          {:op :fail}]}}
;;                                                                     :target {:op :eval :form TARGET_l__}}}
;;                                                             {:op :fail}]}}]}
;;                                :target {:op :eval :form TARGET}}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              ^{:meander.epsilon/infer-literal-seq false}
;;              (match-compile x (1 & _) true))))

;;   (t/testing "with literal seq inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :check-seq
;;                                :then {:op :branch
;;                                       :arms [{:op :pass
;;                                               :then {:op :branch
;;                                                      :arms [{:symbol TARGET_l__
;;                                                              :value {:op :take
;;                                                                      :n 1
;;                                                                      :kind :seq
;;                                                                      :target {:op :eval :form TARGET}}
;;                                                              :op :bind
;;                                                              :then {:op :check-bounds
;;                                                                     :kind :seq
;;                                                                     :length 1
;;                                                                     :then {:symbol TARGET_r__
;;                                                                            :value {:op :drop
;;                                                                                    :n 1
;;                                                                                    :kind :seq
;;                                                                                    :target {:op :eval :form TARGET}}
;;                                                                            :op :bind
;;                                                                            :then {:op :branch
;;                                                                                   :arms
;;                                                                                   [{:op :check-equal
;;                                                                                     :target-1 {:op :eval :form TARGET_l__}
;;                                                                                     :target-2 {:op :eval :form [1]}
;;                                                                                     :then {:op :branch
;;                                                                                            :arms [{:value true :op :return}
;;                                                                                                   {:op :fail}]}}
;;                                                                                    {:op :fail}]}}
;;                                                                     :target {:op :eval :form TARGET_l__}}}
;;                                                             {:op :fail}]}}]}
;;                                :target {:op :eval :form TARGET}}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              (match-compile x (1 & _) true)))))


;; ;; Vector compilation
;; ;; ---------------------------------------------------------------------

;; (t/deftest vector-compilation-empty-vector-test
;;   (t/testing "no literal vector inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :check-vector
;;                                :then {:op :branch
;;                                       :arms [{:op :pass
;;                                               :then {:op :branch
;;                                                      :arms [{:op :check-equal
;;                                                              :target-1 {:op :eval :form TARGET}
;;                                                              :then {:value true :op :return}
;;                                                              :target-2 {:op :eval :form []}}
;;                                                             {:op :fail}]}}]}
;;                                :target {:op :eval :form TARGET}}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              ^{:meander.epsilon/infer-literal-vector false}
;;              (match-compile x [] true))))

;;   (t/testing "literal vector inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :check-lit
;;                                :value {:op :eval :form ()}
;;                                :then {:value true :op :return}
;;                                :target {:op :eval :form TARGET}}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              (match-compile x [] true)))))

;; (t/deftest vector-compilation-empty-vector-with-rest-as-wildcard-test
;;   (t/is (= '{:op :branch
;;              :arms [{:op :branch
;;                      :arms [{:op :check-vector
;;                              :then {:op :branch
;;                                     :arms [{:op :pass
;;                                             :then {:op :branch
;;                                                    :arms [{:op :branch
;;                                                            :arms [{:value true :op :return}
;;                                                                   {:op :fail}]}
;;                                                           {:op :fail}]}}]}
;;                              :target {:op :eval :form TARGET}}
;;                             {:op :fail}]}
;;                     {:op :fail}]}
;;            (match-compile x [& _] true))))

;; (t/deftest vector-compilation-empty-vector-with-rest-as-logic-variable-test
;;   (t/testing "with literal vector inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :check-vector
;;                                :then {:op :branch
;;                                       :arms [{:op :pass
;;                                               :then {:op :branch
;;                                                      :arms [{:op :branch
;;                                                              :arms [{:op :branch
;;                                                                      :arms [{:op :lvr-bind
;;                                                                              :symbol ?1
;;                                                                              :then {:value true :op :return}
;;                                                                              :target {:op :eval :form TARGET}}
;;                                                                             {:op :fail}]}
;;                                                                     {:op :fail}]}
;;                                                             {:op :fail}]}}]}
;;                                :target {:op :eval :form TARGET}}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              (match-compile x [& ?1] true))))

;;   (t/testing "without literal vector inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :check-vector
;;                                :then {:op :branch
;;                                       :arms [{:op :pass
;;                                               :then {:op :branch
;;                                                      :arms [{:op :branch
;;                                                              :arms [{:op :branch
;;                                                                      :arms [{:op :lvr-bind
;;                                                                              :symbol ?1
;;                                                                              :then {:value true :op :return}
;;                                                                              :target {:op :eval :form TARGET}}
;;                                                                             {:op :fail}]}
;;                                                                     {:op :fail}]}
;;                                                             {:op :fail}]}}]}
;;                                :target {:op :eval :form TARGET}}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              ^{:meander.epsilon/infer-literal-vector false}
;;              (match-compile x [& ?1] true)))))

;; (t/deftest vector-compilation-empty-vector-with-as-test
;;   (t/testing "without literal vector inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :branch
;;                                :arms [{:op :check-vector
;;                                        :then {:op :branch
;;                                               :arms [{:op :pass
;;                                                       :then {:op :branch
;;                                                              :arms [{:op :check-equal
;;                                                                      :target-1 {:op :eval :form TARGET}
;;                                                                      :then {:op :branch
;;                                                                             :arms [{:op :lvr-bind
;;                                                                                     :symbol ?1
;;                                                                                     :then {:value true :op :return}
;;                                                                                     :target {:op :eval :form TARGET}}
;;                                                                                    {:op :fail}]}
;;                                                                      :target-2 {:op :eval :form []}}
;;                                                                     {:op :fail}]}}]}
;;                                        :target {:op :eval :form TARGET}}
;;                                       {:op :fail}]}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              ^{:meander.epsilon/infer-literal-vector false}
;;              (match-compile x [:as ?1] true))))

;;   (t/testing "with literal vector inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :branch
;;                                :arms [{:op :check-lit
;;                                        :value {:op :eval :form []}
;;                                        :then {:op :branch
;;                                               :arms [{:op :lvr-bind
;;                                                       :symbol ?1
;;                                                       :then {:value true :op :return}
;;                                                       :target {:op :eval :form TARGET}}
;;                                                      {:op :fail}]}
;;                                        :target {:op :eval :form TARGET}}
;;                                       {:op :fail}]}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              (match-compile x [:as ?1] true)))))

;; (t/deftest vector-compilation-unit-vector-test
;;   (t/is (= '{:op :branch
;;              :arms [{:op :branch
;;                      :arms [{:op :check-vector
;;                              :then {:op :branch
;;                                     :arms [{:op :pass
;;                                             :then {:op :branch
;;                                                    :arms [{:op :check-bounds
;;                                                            :kind :vector
;;                                                            :length 1
;;                                                            :then
;;                                                            {:op :branch
;;                                                             :arms [{:op :check-equal
;;                                                                     :target-1 {:op :eval :form TARGET}
;;                                                                     :then {:value true :op :return}
;;                                                                     :target-2 {:op :eval :form [1]}}
;;                                                                    {:op :fail}]}
;;                                                            :target {:op :eval :form TARGET}}
;;                                                           {:op :fail}]}}]}
;;                              :target {:op :eval :form TARGET}}
;;                             {:op :fail}]}
;;                     {:op :fail}]}
;;            ^{:meander.epsilon/infer-literal-vector false}
;;            (match-compile x [1] true)))

;;   (t/testing "with literal vector inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :check-lit
;;                                :value {:op :eval :form [1]}
;;                                :then {:value true :op :return}
;;                                :target {:op :eval :form TARGET}}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              (match-compile x [1] true)))))

;; (t/deftest vector-compilation-unit-vector-with-rest-as-wildcard-test
;;   (t/testing "with literal vector inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :check-vector
;;                                :then {:op :branch
;;                                       :arms [{:op :pass
;;                                               :then {:op :branch
;;                                                      :arms [{:symbol TARGET_l__
;;                                                              :value {:op :take
;;                                                                      :n 1
;;                                                                      :kind :vector
;;                                                                      :target {:op :eval :form TARGET}}
;;                                                              :op :bind
;;                                                              :then {:op :check-bounds
;;                                                                     :kind :vector
;;                                                                     :length 1
;;                                                                     :then {:op :bind
;;                                                                            :symbol TARGET_r__
;;                                                                            :value {:op :drop
;;                                                                                    :n 1
;;                                                                                    :kind :vector
;;                                                                                    :target {:op :eval :form TARGET}}
;;                                                                            :then {:op :branch
;;                                                                                   :arms [{:op :check-equal
;;                                                                                           :target-1 {:op :eval :form TARGET_l__}

;;                                                                                           :target-2 {:op :eval :form [1]}
;;                                                                                           :then {:op :branch
;;                                                                                                  :arms [{:op :return
;;                                                                                                          :value true}
;;                                                                                                         {:op :fail}]}}
;;                                                                                          {:op :fail}]}}
;;                                                                     :target {:op :eval :form TARGET_l__}}}
;;                                                             {:op :fail}]}}]}
;;                                :target {:op :eval :form TARGET}}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              ^{:meander.epsilon/infer-literal-vector false}
;;              (match-compile x [1 & _] true))))

;;   (t/testing "with literal vector inference"
;;     (t/is (= '{:op :branch
;;                :arms [{:op :branch
;;                        :arms [{:op :check-vector
;;                                :then {:op :branch
;;                                       :arms [{:op :pass
;;                                               :then {:op :branch
;;                                                      :arms [{:symbol TARGET_l__
;;                                                              :value {:op :take
;;                                                                      :n 1
;;                                                                      :kind :vector
;;                                                                      :target {:op :eval :form TARGET}}
;;                                                              :op :bind
;;                                                              :then {:op :check-bounds
;;                                                                     :kind :vector
;;                                                                     :length 1
;;                                                                     :then {:symbol TARGET_r__
;;                                                                            :value {:op :drop
;;                                                                                    :n 1
;;                                                                                    :kind :vector
;;                                                                                    :target {:op :eval :form TARGET}}
;;                                                                            :op :bind
;;                                                                            :then {:op :branch
;;                                                                                   :arms
;;                                                                                   [{:op :check-equal
;;                                                                                     :target-1 {:op :eval :form TARGET_l__}
;;                                                                                     :target-2 {:op :eval :form [1]}
;;                                                                                     :then {:op :branch
;;                                                                                            :arms [{:value true :op :return}
;;                                                                                                   {:op :fail}]}}
;;                                                                                    {:op :fail}]}}
;;                                                                     :target {:op :eval :form TARGET_l__}}}
;;                                                             {:op :fail}]}}]}
;;                                :target {:op :eval :form TARGET}}
;;                               {:op :fail}]}
;;                       {:op :fail}]}
;;              (match-compile x [1 & _] true)))))


;; ---------------------------------------------------------------------
;; match macro tests


;; (t/deftest map-can-have-variable-bound-lvr-keys
;;   (r.match/match {:k1 "v1"
;;                   :k2 {"v1" "v2"}}
;;     {:k1 ?x
;;      :k2 {?x ?y}}
;;     (t/is (and (= ?x "v1")
;;                (= ?y "v2")))

;;     _
;;     false))


;; (t/deftest matching-and-searching-maps
;;   (t/are [x] (if (coll? x)
;;                (every? true? x)
;;                (true? x))
;;     (r/match {:k1 :k2
;;               :ks {:k2 {:valid true}}}
;;       {:k1 ?k
;;        :ks {?k {:valid ?valid}}}
;;       ?valid)

;;     (r/match {:k1 :k2
;;               :ks {:k2 {:valid true}}}
;;       {:ks {?k {:valid ?valid}}
;;        :k1 ?k}
;;       ?valid)

;;     (r/search {:k1 :k2
;;                :ks {:k2 {:valid true}}}
;;       {:k1 ?k
;;        :ks {?k {:valid ?valid}}}
;;       ?valid)

;;     (r/search {1 2
;;                3 4
;;                :ks {1 {:parent nil}
;;                     2 {:parent 1}
;;                     3 {:parent nil}
;;                     4 {:parent 3}}}
;;       {?k1 ?k2
;;        :ks {?k2 {:parent ?k1}}}
;;       (= 1 (- ?k2 ?k1)))

;;     (r/match [:a {:a :b}]
;;       [?a {?a ?b}]
;;       (= ?b :b))

;;     (r/match {:a :b :b 4 :c 5}
;;       {:a ?b ?b (r/pred number?)}
;;       (= ?b :b))

;;     (r/search {2 :a 3 :b}
;;       (r/with [%x (r/pred even? ?k)]
;;         {%x ?v})
;;       (= [2 :a] [?k ?v]))

;;     (r/search {:a :b :b 4 :c 5}
;;       {:a ?b ?b (r/pred number?)}
;;       (= ?b :b))

;;     (r/search {:a :b :b 4 :c 5}
;;       {(r/pred keyword?) ?b ?b (r/pred number?)}
;;       (= ?b :b))

;;     (r/search {:a :b
;;                :b 4
;;                :c :d
;;                :d 6
;;                :e 5}
;;       {(r/pred keyword?) ?b
;;        ?b (r/pred number? (r/pred even? ?num))}
;;       (boolean (and (#{:b :d} ?b)
;;                     (#{4 6} ?num))))

;;     (r/search {1 {2 [1 2 3 0]}
;;                3 {[1 2 3 0] 4}
;;                5 {[1 2 3 0] 6}}
;;       {?k1 {?k2 (r/scan 0)}
;;        ?k3 {(r/scan 0) ?k4}}
;;       (= -2 (- ?k4 ?k3 ?k2 ?k1)))))

;; (t/deftest vector-literals-check-type
;;   (t/is (= :ok (r/match [:literal]
;;                  [:literal] :ok)))
;;   (t/is (= :fail (r/match (list :literal)
;;                    [:literal] :ok
;;                    _ :fail))))

;; (t/deftest seq-literals-check-type
;;   (t/is (= :ok (r/match (list :literal)
;;                  (:literal) :ok)))
;;   (t/is (= :fail (r/match [:literal]
;;                    (literal) :ok
;;                    _ :fail))))

#_
;; TODO: This should pass because ?x and ?y can be matched before
;; attempting to match the subsequence.
(t/deftest zero-or-more-may-have-lvrs-if-lvrs-can-be-bound
  (t/is  (= [1 2]
            (r.match/match [[1 2 1 2] 1 2]
              [[?x ?y ...] ?x ?y]
              [?x ?y]))))

#_
;; TODO: This should pass because there is only one possible solution.
(t/deftest sequences-of-zero-or-more
  (t/is (r.match/match [1 2 3 4 3 4]
          [1 2 ... 3 4 ...]
          true

          _
          false)))
