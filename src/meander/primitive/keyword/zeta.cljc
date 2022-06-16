(ns meander.primitive.keyword.zeta)

(defrecord KeywordAny [])

(defrecord KeywordUnqualified [name])

(defrecord KeywordQualified [ns name])

(def
  ^{:arglists '([])}
  any #'->KeywordAny)

(def
  ^{:arglists '([name])}
  unqualified #'->KeywordUnqualified)

(def
  ^{:arglists '([ns name])}
  qualified #'->KeywordQualified)
