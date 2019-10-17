(ns hiccup
  (:require [meander.epsilon :as m]
            [meander.strategy.epsilon :as m*]))

;; Interpreter (function) version
;; ---------------------------------------------------------------------

(defn str* [xs]
  (apply str xs))

(defn html [hiccup]
  (m/rewrite hiccup
    ;; Borrow :<> from Reagent.
    [:<> . (m/cata !content) ...]
    (m/app str* (!content ...))

    ;; Void tags.
    (m/with [%attributes {(m/keyword !attr) !val & (m/or %attributes _)}]
      [(m/keyword (m/re #"area|base|br|col|embed|hr|img|input|link|meta|param|source|track|wbr" ?tag))
       & (m/or [%attributes . _ ...]
               [_ ...])])
    (m/app str* ("<" ?tag . " " !attr "=\"" !val "\"" ... "/>"))

    ;; Normal tags.
    (m/with [%content (m/cata !content)
             %attributes {(m/keyword !attr) !val & (m/or %attributes _)}]
      [(m/keyword ?tag)
       & (m/or [%attributes . %content ...]
               [%content ...])])
    (m/app str* ("<" ?tag . " " !attr "=\"" !val "\"" ... ">" . !content ... "</" ?tag ">"))

    ;; Sequences.
    ((m/cata !content) ...)
    (m/app str* (!content ...))

    ;; Everythign else.
    ?x
    (m/app str ?x)))

(def tachyons-banner-basic-hiccup
  "https://tachyons.io/components/banners/basic/index.html"
  [:html
   [:head
    [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1"}]
    [:link {:rel "stylesheet"
            :href "https://unpkg.com/tachyons/css/tachyons.min.css"}]]
   [:body {:class "w-100 sans-serif bg-white pt5"}
    [:article {:class "mw7 center ph3 ph5-ns tc br2 pv5 bg-washed-green dark-green mb5"}
     [:h1 {:class "fw6 f3 f2-ns lh-title mt0 mb3"}
      "This is a tagline. For x."]
     [:h2 {:class "fw2 f4 lh-copy mt0 mb3"}
      "This will change things. And we want you to be involved. This text needs to"
      "be longer for testing sake."]
     [:p {:class "fw1 f5 mt0 mb3"}
      "Sign up for beta access or learn more about x."]
     [:div
      [:a {:class  "f6 br-pill bg-dark-green no-underline washed-green ba b--dark-green grow pv2 ph3 dib mr3"
           :href "#"}
       "Sign Up"]
      [:a {:class  "f6 br-pill dark-green no-underline ba grow pv2 ph3 dib"
           :href "#"}
       "Learn More"]]]]])
  
(comment
  (def tachyons-banner-basic-html
    (html tachyons-banner-basic-hiccup))

  tachyons-banner-basic-html
  ;; =>
  "<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/><link rel=\"stylesheet\" href=\"https://unpkg.com/tachyons/css/tachyons.min.css\"/></head><body class=\"w-100 sans-serif bg-white pt5\"><article class=\"mw7 center ph3 ph5-ns tc br2 pv5 bg-washed-green dark-green mb5\"><h1 class=\"fw6 f3 f2-ns lh-title mt0 mb3\">This is a tagline. For x.</h1><h2 class=\"fw2 f4 lh-copy mt0 mb3\">This will change things. And we want you to be involved. This text needs tobe longer for testing sake.</h2><p class=\"fw1 f5 mt0 mb3\">Sign up for beta access or learn more about x.</p><div><a href=\"#\" class=\"f6 br-pill bg-dark-green no-underline washed-green ba b--dark-green grow pv2 ph3 dib mr3\">Sign Up</a><a href=\"#\" class=\"f6 br-pill dark-green no-underline ba grow pv2 ph3 dib\">Learn More</a></div></article></body></html>"

  (spit "tachyons-banner-basic.html" tachyons-banner-basic-html))

;; Compiler (macro) version
;; ---------------------------------------------------------------------

(defn stringifiable? [x]
  (or (string? x)
      (number? x)
      (keyword? x)))

(defn flatten-str-form [str-form]
  (m/rewrite str-form
    ;; (clojure.core/str) == "".
    (`str)
    ""

    ;; Flatten nested calls to `clojure.core/str` recursively.
    (`str . (m/or (`str . (m/cata (m/or "" !args)) ...)
                  (m/cata (m/or "" !args))) ...)
    (`str . !args ...)

    ;; Stop.
    ('quote & _ :as ?form)
    ?form

    ;; Apply to lists.
    ((m/cata !xs) ...)
    (!xs ...)

    ;; Apply to vectors.
    [(m/cata !xs) ...]
    [!xs ...]

    ;; Apply to vectors.
    (m/and {} (m/gather [(m/cata !k) (m/cata !v)]))
    {& [[!k !v] ...]}

    ;; Apply to sets.
    (m/and #{} (m/gather (m/cata !x)))
    #{^& [!x ...]}

    ;; Stop.
    ?x
    ?x))

(defn interpret-str-form* [str-form]
  (m/rewrite str-form
    ;; (clojure.core/str 1) == "1"
    ;; (clojure.core/str "foo") == "foo"
    (`str (m/pred stringifiable? ?string))
    (m/app str ?string)

    ;; (clojure.core/str "foo" "bar") == "foo bar"
    (`str . !init ... (m/pred stringifiable? ?string-1) (m/pred stringifiable? ?string-2) & ?rest)
    (`str . !init ... (m/app str ?string-1 ?string-2) & ?rest)

    ;; (clojure.core/str x [,,,] ,,,) == (clojure.core/str x "[" ,,, "]" ,,,)
    (`str . (m/and (m/not [& _]) (m/cata !ys)) ... [(m/cata !xs) ...] & ?tail)
    (`str . !ys ... "[" . !xs ... "]" & ?tail)

    ;; (clojure.core/str x {,,,} ,,,) == (clojure.core/str x "{" ,,, "}" ,,,)
    (`str . (m/and (m/not {}) (m/cata !ys)) ... (m/and {} (m/gather [(m/cata !k) (m/cata !v)])) & ?tail)
    (`str . !ys ... "{" . !k !v ... "}" & ?tail)

    ;; (clojure.core/str x #{,,,} ,,,) == (clojure.core/str x "#{" ,,, "}" ,,,)
    (`str . (m/and (m/not #{}) (m/cata !ys)) ... (m/and #{} (m/gather (m/cata !x))) & ?tail)
    (`str . !ys ... "#{" . !x ... "}" & ?tail)

    ;; (clojure.core/str x "foo" "bar" y ,,,) == (clojure.core/str x "foobar" y ,,,)
    (`str . !init ... (m/pred stringifiable? !string) ..1 & ?tail)
    (`str . !init ... (m/app str* [!string ...]) & ?tail)

    ;; Stop.
    ('quote & _ :as ?form)
    ?form

    ;; Apply to lists.
    ((m/cata !xs) ...)
    (!xs ...)

    ;; Apply to vectors.
    [(m/cata !xs) ...]
    [!xs ...]

    ;; Apply to vectors.
    (m/and {} (m/gather [(m/cata !k) (m/cata !v)]))
    {& [[!k !v] ...]}

    ;; Apply to sets.
    (m/and #{} (m/gather (m/cata !k)))
    #{^& [!k ...]}

    ;; Stop.
    ?x
    ?x))

(def ^{:arglists '([str-form])}
  interpret-str-form
  (m*/pipe #'flatten-str-form (m*/until = #'interpret-str-form*)))

(defmacro html* [hiccup]
  (interpret-str-form
   (m/rewrite hiccup
     ;; Borrow :<> from Reagent.
     [:<> . (m/cata !content) ...]
     (`list !content ...)

     ;; Void tags.
     (m/with [%attributes {(m/keyword !attr) !val & (m/or %attributes _)}]
       [(m/keyword (m/re #"area|base|br|col|embed|hr|img|input|link|meta|param|source|track|wbr" ?tag))
        & (m/or [%attributes . _ ...]
                [_ ...])])
     (`str "<" ?tag . " " !attr "=\"" !val "\"" ... "/>")

     ;; Normal tags.
     (m/with [%content (m/cata !content)
              %attributes {(m/keyword !attr) !val & (m/or %attributes _)}]
       [(m/keyword ?tag)
        & (m/or [%attributes . %content ...]
                [%content ...])])
     (`str . "<" ?tag . " " !attr "=\"" !val "\"" ... ">" . !content ... "</" ?tag ">")

     (m/pred string? ?x)
     ?x

     ;; Everything else.
     ?x
     (`html ?x))))

(comment
  (interpret-str-form `(str (get #{(str 1 2 3) (str "a" ["b" "c"] "e")} (str "1" (str "23")))))
  ;; =>
  (clojure.core/str (clojure.core/get #{"123" "a[bc]e"} "123"))

  (macroexpand
   (html*
    [:html
     [:head
      [:meta {:name "viewport"
              :content "width=device-width, initial-scale=1"}]
      [:link {:rel "stylesheet"
              :href "https://unpkg.com/tachyons/css/tachyons.min.css"}]]
     [:body {:class "w-100 sans-serif bg-white pt5"}
      [:article {:class "mw7 center ph3 ph5-ns tc br2 pv5 bg-washed-green dark-green mb5"}
       [:h1 {:class "fw6 f3 f2-ns lh-title mt0 mb3"}
        "This is a tagline. For x."]
       [:h2 {:class "fw2 f4 lh-copy mt0 mb3"}
        "This will change things. And we want you to be involved. This text needs to"
        "be longer for testing sake."]
       [:p {:class "fw1 f5 mt0 mb3"}
        "Sign up for beta access or learn more about x."]
       [:div
        [:a {:class  "f6 br-pill bg-dark-green no-underline washed-green ba b--dark-green grow pv2 ph3 dib mr3"
             :href "#"}
         "Sign Up"]
        [:a {:class  "f6 br-pill dark-green no-underline ba grow pv2 ph3 dib"
             :href "#"}
         "Learn More"]]]]]))
  ;; =>
  "<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/><link rel=\"stylesheet\" href=\"https://unpkg.com/tachyons/css/tachyons.min.css\"/></head><body class=\"w-100 sans-serif bg-white pt5\"><article class=\"mw7 center ph3 ph5-ns tc br2 pv5 bg-washed-green dark-green mb5\"><h1 class=\"fw6 f3 f2-ns lh-title mt0 mb3\">This is a tagline. For x.</h1><h2 class=\"fw2 f4 lh-copy mt0 mb3\">This will change things. And we want you to be involved. This text needs tobe longer for testing sake.</h2><p class=\"fw1 f5 mt0 mb3\">Sign up for beta access or learn more about x.</p><div><a href=\"#\" class=\"f6 br-pill bg-dark-green no-underline washed-green ba b--dark-green grow pv2 ph3 dib mr3\">Sign Up</a><a href=\"#\" class=\"f6 br-pill dark-green no-underline ba grow pv2 ph3 dib\">Learn More</a></div></article></body></html>")
