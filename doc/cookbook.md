Please add your own tips and tricks! You can edit this file from Github by clicking then pencil icon in the top right of the file view.

## Reuse subpatterns in other patterns

```clojure
(m/defsyntax ending-with [end]
  ['_ '... end])

(m/rewrite
  [1 2 3 4 5]
  (ending-with ?x)
  ?x)
;;=> 5

(m/rewrite
  [:a :b [1 2 3]]
  [:a :b (ending-with ?x)]
  ?x)
;;=> 3
```

## Tokenize a sequence

You can use `..!n` as a subsequence grouping facility, and `with` to define a recursive pattern.

```clojure
(m/rewrite
  [1 2 3 0 4 5 6 0 7 8 0 9]
  (m/with [%split (m/or [!xs ..!n 0 & %split]
                        [!xs ..!n])]
    %split)
  [[!xs ..!n] ...])
```

## Get all keys and values from a map

```clojure
(m/match
  {1 2 3 4 5 6}
  {& (m/seqable [!ks !vs] ...)}
  [!ks !vs])
```

## Webscrape HTML

Use a library like `hickory` to parse the HTML into data structures, then you can match either the DOM or hiccup.
`$` is a convenient way to search for matches in sub-trees.

```clojure
(m/search (fetch-as-hiccup company-directory-page)
  (m/$ [:div {:class "directory-tables"}
        . _ ...
        [:h3 _ ?department & _]])
  ?department)
```

## Recursion, reduction, and aggregation

Patterns can call themselves with the `m/cata` operator.
This is like recursion.
You can leverage self recursion to accumulate a result.

```clojure
(m/rewrite [() '(1 2 3)] ;; Initial state
  ;; Intermediate step with recursion
  [?current (?head & ?tail)]
  (m/cata [(?head & ?current) ?tail])

  ;; Done
  [?current ()]
  ?current)
;; => (3 2 1)
```

## Use the same value from a memory variable twice

When you have a match pattern that contains a memory varible `!n` and a substitution pattern where you want to make use of the variable in multiple ways, you can't do that directly because `[!n !n]` would take 2 different values out of `!n` instead of the same value twice. However, you can easily create two names for the same value in the search pattern with `(m/and !n !n2)` which will match a single value, but create 2 memory variables.

```clojure
(m/rewrite [[:a 1] [:b 2] [:c 3]]
           [[!k (m/and !n !n2)] ...]
           [[!k !n (m/app str !n2)] ...])
```
