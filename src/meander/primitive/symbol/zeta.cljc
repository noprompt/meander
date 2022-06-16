(ns meander.primitive.symbol.zeta)

(defrecord SymbolAny [])

(defrecord SymbolUnqualified [name])

(defrecord SymbolQualified [ns name])

(def
  ^{:arglists '([])}
  any #'->SymbolAny)

(def
  ^{:arglists '([name])}
  unqualified #'->SymbolUnqualified)

(def
  ^{:arglists '([ns name])}
  qualified #'->SymbolQualified)
