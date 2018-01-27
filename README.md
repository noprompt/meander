# meander

A wandering river.

## Pattern syntax

```
Compound := (Atom ...)
          | [Atom ...]
          | {Atom Atom ...}
          | #{Atom ...}

Atom := Variable
      | SplicingVariable
      | ClojureScalar

Variable := ~a where a ∈ simple-symbol?
SplicingVariable := ~@as where as ∈ simple-symbol?
ClojureScalar  := Any Clojure scalar value e.g. strings, numbers
```

### Example patterns

```clj
inc
```

will match the simple symbol `inc`.

```clj
clojure/walk
```

will match the qualified symbol `clojure/walk`.


```
(inc x)
```

will match the list `'(inc x)`

```clj
~x
```

will match anything.

```clj
(inc ~x)
```

will match forms like

```
(inc 1)
;; x ↦ 1

(inc "cheese")
;; x ↦ "cheese"
```


```clj
(~x ~y ~x)
```

will match forms like

```clj
(1 2 1)
;; ~x ↦ 1
;; ~y ↦ 2

("foo" "bar" "foo")
;; ~x ↦ "foo"
;; ~y ↦ "bar"
```

Note `~x` is the same throughout which means forms like

```clj
(1 2 3)
```

will not match because `~x` cannot be `1` _and_ `3`.

```clj
(~@xs)
```

will match forms like

```clj
()
;; xs ↦ ()

(foo)
;; xs ↦ (foo)

(foo bar ,,,)
;; xs ↦ (foo bar ,,)
```

```
(~x ~@xs)
```

will match forms like

```clj
(foo bar)
;; x ↦ foo
;; xs ↦ (bar)

(foo bar ,,,)
;; x ↦ foo
;; xs ↦ (bar ,,)
```

---

```
(~@xs ~@ys)
```

will match forms ike

```
(foo bar)
;; xs ↦ (), ys ↦ (foo bar)
;; xs ↦ (foo), ys ↦ (bar)
;; xs ↦ (foo bar), ys ↦ ()
```

Note there are multiple solutions


## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
