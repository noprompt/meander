# Meander

Meander is a Clojure data transformation library which combines higher order functional programming with concepts from [term rewriting](https://en.wikipedia.org/wiki/Rewriting) and [logic programming](https://en.wikipedia.org/wiki/Logic_programming). It does so with extensible syntactic [pattern matching](https://en.wikipedia.org/wiki/Pattern_matching), syntactic [pattern substitution](https://en.wikipedia.org/wiki/Substitution_(logic)), and a suite of combinators known as _strategies_ that run the gamut from purely functional to purely declarative.


## Contents

* [Pattern Matching](#pattern-matching)
* [Pattern Substituton](#pattern-matching)
* [Rewriting](#rewriting)
  * [Strategy Combinators](#strategy-combinators)


## Pattern Matching

* [Operators](#operators)
  * [`match`](#match)
  * [`search`](#search-star)
* [Pattern Syntax](#pattern-syntax)
  * [Literals](#literals)
  * [Variables](#variables)
    * [Logic Variables](#logic-variables)
    * [Memory Variables](#memory-variables)
    * [Any Variables](#any-variables)
  * [Operators](#operators)
    * [`and`](#and)
    * [`or`](#or)
    * [`pred`](#pred)
    * [`guard`](#guard)
    * [`app`](#app)
    * [`let`](#let)
  * [Subsequences](#subsequences)
    * [Zero or More](#zero-or-more)
    * [N or More](#n-or-more)
    * [Partition](#partition)
  * [Escaping](#escaping)
    * [Unquote](#unquote)
    * [Unquote Splicing](#unquote-splicing)


### Operators

The primary operators for pattern matching and searching are available in `meander.match.alpha`.


#### `match`

The `match` operator provides traditional pattern matching.


#### `search`

The `search` operator is an extended version `match` which returns a sequence of all action values which satisfy their pattern counterparts. Map patterns with variable keys, set patterns with variable subpatterns, or two side-by-side zero or more subsequence patterns, are all examples of patterns which may have multiple matches for a given value. `search` will find all such matches and, unlike `match`, will not throw when a pattern match could not be made. In essence, `search` allows you to _query_ arbitrary data.


### Pattern Syntax

#### Literals

The simplest patterns to express are literal patterns. Literal patterns are patterns which are either quoted with `'` or are not variables, pattern operators, or pattern subsequences.

For example, the pattern 

```clj
[1 ?x 3]
```

contains the literals `1` and `3`. And the pattern

```clj
('or ?x "foo")
```

contains the literals `or` and `"foo"`.

#### Variables

Pattern variables are variables which may or may not bind symbols to the values they match. In the case of variables which bind, the bindings are made available for use in pattern actions, substitutions, and even within patterns. There are two types of pattern variables which bind, _logic variables_ and _memory variables_, and one type of variable which does not, the so-called _any variable_ also known as a wild card.

#### Logic Variables

_Logic variables_ are variables which express an equivalent, but not necessarily identical, value everywhere within a pattern. They are represented by an unqualified symbol prefixed with the `?` character.

To express any 2-tuple composed of equivalent elements we would write the following.

```clj
[?x ?x]
```

This pattern will match a value like 

```clj
[1 1]
``` 

and bind `?x` to `1` but will not match a value like 

```clj
[1 2]
```

since the second occurence of `?x` is not equal to `1`.


#### Memory Mariables

_Memory variables_ are variables which "remember" or collect values during pattern matching. They are represented by an unqualified symbol prefixed with the `!` character. Because they collect multiple values it is idiomatic to employ a plural naming convention e.g. `!xs` or `!people`.


To collect values from a 4-tuple such that we collet the first and last elements in one container and the middle elements in another we would write the folowing.

```clj
[!xs !ys !ys !xs]
```

This pattern will match a value like 

```clj
[:red :green :yellow :blue]
```

and bind `!xs` to `[:red :blue]` and `!ys` to `[:green :yellow]`.


#### Any Variables

_Any variables_ are variables which match anything but do not bind the values they match. They are represented as simple symbols prefixed with the `_` character e.g. `_`, `_first-name`, and so on. Any variables commonly appear in the last clause of a `match` expression as a catch-all when all other patterns fail to match.


### Operators

### `guard`

`(guard expr)` matches whenenver `expr` true.

Example:

```clj
(match 42
  (guard true) :okay)
;; => :okay
```

### `pred`

`(pred pred-fn)` matches whenenver `pred-fn` applied to the current value being matchedreturns a truthy value.

Example:

```clj
(match 42
  (pred even?) :okay)
;; => :okay
```

### `let`

`(let pat expr)` matches when `pat` matches the result of evaluating `expr`. This allows pattern matching on an arbitrary expression.

Example:

```clj
(match 42
  (or [?x ?y] (let [?x ?y] [1 2]))
  [?x ?y])
;; => [1 2]
```

#### `and`

`(and pat₀ ,,, patₙ)` matches when all of `pat₀` through `patₙ` match.

Example:

```clj
(match 42
  (and ?x (guard (even? ?x)))
  ?x)
;; => 42
```

#### `or`

`(or pat₀ ,,, patₙ)` matches when any one of `pat₀` through `patₙ` match.

Example:

```clj
(match 42
  (or 43 42 41)
  true)
;; => true
```

Note that unbound variables _must_ be shared by `pat₀` through `patₙ`.

Example:

```clj
(match [1 2 3]
  (or [?x ?y]
      [?x ?y ?z])
  [?x ?y])
;; Every pattern of an or pattern must have references to the same
;; unbound variables.
;; {:pat (or [?x ?y] [?x ?y ?z]),
;;  :env #{},
;;  :problems [{:pat [?x ?y], :absent #{?z}}]}
```

### Subsequences

When matching subsequences it is often useful to express the notions of _zero or more_ or _n or more_ things. The postfix operators `...` or `..n` respectively provide this utility.

#### Zero or more

The `...` postfix operator matches the _subsequence_ of patterns to it's left (up to the first `.` or start of the collection) zero or more times.

Example:

```clj
(match [1 2 1 2 2 3]
  [1 2 ... ?x ?y]
  [?x ?y])
;; => [2 3]
```
  
#### N or more

The `..n` postfix operator matches the _subsequence_ of patterns to it's left (up to the first `.` or start of the collection) _n_ or more times where _n_ is a positive natural number.

Example:

```clj
(match [1 1 1 2 3]
  [1 ..3 ?x ?y]
  [?x ?y])
;; => [2 3]
```
  
```clj
(match [1 2 3]
  [1 ..3 ?x ?y]
  [:okay [?x ?y]]

  _
  [:fail])
;; => [:fail]
```

#### Partition

The `.` operator, read as "partition", partitions the collection into two parts: left and right. This operator is use primarily to delimit the start of a variable length subsequence. It is important to note that both `...` and `..n` act as partition operators as well.

Example:

```clj
(match [3 4 5 6 7 8] 
  [3 4 . !xs !ys ...]
  [!xs !ys])
;; => [[5 7] [6 8]]
```

Had the pattern `[3 4 . !xs !ys ...]` in this example been written as `[3 4 !xs !ys ...]` the match would have failed. This is because the latter pattern represents a subsequence of 4 elements beginning with the sequence `3 4`.

Example:

```clj
(search [3 0 0 3 1 1 3 2 2] 
  [_ ... 3 . !ys ...]
  {:!ys !ys})
;; =>
({:!ys [0 0 3 1 1 3 2 2]}
 {:!ys [1 1 3 2 2]}
 {:!ys [2 2]})
```

This example demonstrates how `search` finds solutions for patterns which have sequential patterns which contain variable length subsequences on both sides of a partition. The pattern `[_ ... 3 . !ys ...]` says find every subsequence in the vector being matched after _any_ occurence of a `3`.

## Pattern Substitution

Pattern substitution can be thought of as the inverse to pattern matching. While pattern matching binds values by deconstructing an object, pattern substitution uses existing bindings to _construct_ an object.

The `substitute` operator is available from the `meander.substitute.alpha` namespace and utilizes the same syntax as `match` and `search` (with a few exceptions). On it's own it is unlikely to be of much use, however, it is a necessary part of building syntactic _rewrite rules_.

Because rewriting is a central theme it's worthwhile to understand substitution semantics.

### Logic variables

Logic variables have semantically equivalent behavior to the unquote operator.

```clj
(let [?x 1]
  (substitute (+ ?x ~?x)))
;; =>
(+ 1 1)
```

### Memory variables

Memory variables disperse their values throughout a substitution. Each occurence disperse one value from the collection into the expression.

```clj
(let [!xs [1 2 3]]
  (substitute (!xs !xs !xs)))
;; =>
(1 2 3)
```

This works similarly for subsequence patterns: values are dispersed until one of the memory variables is exhausted.

```clj
(let [!bs '[x y]
      !vs [1 2 3]
      !body '[(println x) (println y) (+ x y)]]
  (substitute (let* [!bs !vs ...] . !body ...)))
;; =>
(let* [x 1 y 2] (println x) (println y) (+ x y))
```

When an expression has memory variable occurences which exceed the number of available elements in it's collection `nil` is dispersed after it is exhausted.

```clj
(let [!xs [1]]
  (substitute (!xs !xs !xs)))
;; =>
(1 nil nil)
```

`nil` is also dispersed in [n or more](#n-or-more) patterns up to `n`.

```clj
(let [!xs [1]
      !ys [:A]]
  (substitute (!xs !ys ..2)))
;; =>
(1 :A nil nil)
```
