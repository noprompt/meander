# Meander

Meander is a Clojure data transformation library which combines higher order functional programming with concepts from [term rewriting](https://en.wikipedia.org/wiki/Rewriting). It does so with extensible syntactic pattern matching, syntactic pattern substitution, and a suite of combinators (also known as "strategies") that run the gamut from purely functional to purely declarative.


## Table of Contents

* [Pattern Matching](#pattern-matching)
* [Pattern Substituton](#pattern-matching)
* [Strategy Combinators](#strategy-combinators)
* [Rewriting](#rewriting)


## Pattern Matching

* [Operators](#operators)
  * [`match`](#match)
  * [`match*`](#match-star)
* [Pattern Syntax](#pattern-syntax)
  * [Literals](#literals)
  * [Variables](#variables)
    * [Logic Variables](#logic-variables)
    * [Memory Variables](#memory-variables)
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
  * [Escaping](#escaping)
    * [Unquote](#unquote)
    * [Unquote Splicing](#unquote-splicing)


### Operators

#### `match`

The `match` operator provides traditional pattern matching.


#### `match*`

The `match*` operator is an extended version `match` which returns a sequence of all action values which satisfy their pattern counterparts. Map patterns with variable keys, set patterns with variable subpatterns, or two side-by-side zero or more subsequence patterns, are all examples of patterns which may have multiple matches for a given value. `match*` will find all such matches and, unlike `match`, will not throw when a pattern match could not be made. In essence, `match*` allows you to _query_ arbitrary data.


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

Pattern variables bind symbols to the values they match making them available for use in pattern actions, substitutions, and even within patterns. There are two types of pattern variables: logic variables and memory variables. 

#### Logic Variables

Logic variables are variables which express an equivalent, but not necessarily identical, value everywhere within a pattern. They are represented by an unqualified symbol prefixed with the `?` character.

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

Memory variables are variables which "remember" or collect values during pattern matching. They are represented by an unqualified symbol prefixed with the `!` character. Because they collect multiple values it is idiomatic to employ a plural naming convention e.g. `!xs` or `!people`.


To collect values from a 4-tuple such that we collet the first and last elements in one container and the middle elements in another we would write the folowing.

```clj
[!xs !ys !ys !xs]
```

This pattern will match a value like 

```clj
[:red :green :yellow :blue]
```

and bind `!xs` to `[:red :blue]` and `!ys` to `[:green :yellow]`.


### Operators

### `guard`

`(guard expr)` matches whenenver `expr` true.

Example:

```clj
(match 42
  (guard true) :okay)
;; => :okay
```

### `let`

`(let pat expr)` matches when `pat` matches the result of evaluating `expr`. This allowpattern matching on an arbitrary expression.

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

```clj
(match 42
  (or 43 42 41)
  true)
;; => true
```

Note that unbound variables _must_ be shared by `pat₀` through `patₙ`.

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
