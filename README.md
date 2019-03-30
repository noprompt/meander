# Meander

Meander is a Clojure/ClojureScript data transformation library which combines higher order functional programming with concepts from [term rewriting](https://en.wikipedia.org/wiki/Rewriting) and [logic programming](https://en.wikipedia.org/wiki/Logic_programming). It does so with extensible syntactic [pattern matching](https://en.wikipedia.org/wiki/Pattern_matching), syntactic [pattern substitution](https://en.wikipedia.org/wiki/Substitution_(logic)), and a suite of combinators known as _strategies_ that run the gamut from purely functional to purely declarative.


[![Clojars Project](https://img.shields.io/clojars/v/meander/gamma.svg)](https://clojars.org/meander/gamma)


## Contents

* [Pattern Matching](#pattern-matching)
* [Pattern Substituton](#pattern-matching)
* [Rewriting](#rewriting)

## Pattern Matching

* [Operators](#operators)
  * [`match`](#match)
  * [`search`](#search-star)
  * [`find`](#find)
* [Pattern Syntax](#pattern-syntax)
  * [Literals](#literals)
  * [ClojureScript Literals](#clojurescript-literals)
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
    * [`scan`](#scan)
    * [`$`](#$)
  * [Subsequences](#subsequences)
    * [Zero or More](#zero-or-more)
    * [N or More](#n-or-more)
    * [Partition](#partition)
  * [Escaping](#escaping)
    * [Unquote](#unquote)
    * [Unquote Splicing](#unquote-splicing)


### Operators

The primary operators for pattern matching and searching are available in `meander.match.gamma`.


#### `match`

The `match` operator provides traditional pattern matching. It takes an expression to "match" followed by a series of pattern/action clauses.

```clj
(match x  ;; 1
  pattern ;; 2
  action  ;; 3
  ,,,)
```

1. `x` is the expression.
2. `pattern` is the pattern to match against the expression. Patterns have special [syntax](#pattern-syntax) that is important to understand.
3. `action` is the action expression to be evaluated if `pattern` matches successfully. Certain patterns can bind variables and, if a match is successful, will be available to the `action` expression.

Like `clojure.core/case`, if no patterns match an exception will be
thrown.

Example:

```clj
(match [1 2 1]
  ;; Pair of equivalent objects.
  [?a ?a]
  ?a
  
  ;; Triple where the first and last element are equal.
  [?a ?b ?a]
  ?a)
;; =>
1
```

#### `search`

The `search` operator is an extended version `match` which returns a sequence of all action values which satisfy their pattern counterparts. Map patterns with variable keys, set patterns with variable subpatterns, or two side-by-side zero or more subsequence patterns, are all examples of patterns which may have multiple matches for a given value. `search` will find all such matches and, unlike `match`, will not throw when a pattern match could not be made. In essence, `search` allows you to _query_ arbitrary data.

Example:

```clj
;; Find all pairs of an odd number followed by an even number in the
;; collection.
(search [1 1 2 2 3 4 5]
  [_ ... (pred odd? ?a) (pred even? ?b) . _ ...]
  [?a ?b])
;; =>
([1 2] [3 4])
```

#### `find`

The `find` operator is similar to `search`, however, returns only the first search result. If it cannot be found, `find` returns `nil`.

Example:

```clj
;; Find the first pair of an odd number followed by an even number in
;; the collection.
(find [1 1 2 2 3 4 5]
  [_ ... (pred odd? ?a) (pred even? ?b) . _ ...]
  [?a ?b])
;; =>
[1 2]
```

### Pattern Syntax

#### Literals

The simplest patterns to express are literal patterns. Literal patterns are patterns which are either quoted with `'` or are not variables, pattern operators, or pattern subsequences. They match themselves.

For example, the pattern 

```clj
[1 ?x 3]
```

contains the literals `1` and `3`. And the pattern

```clj
(fn ?args "foo")
```

contains the literals `fn` and `"foo"`.

List and vector patterns may also qualify as literal patterns if they contain no map or set patterns.


```clj
([1] [2] [3])
```

is a literal pattern, however,

```clj
[{:foo 1} #{:bar :baz} {:quux 3}]
```

is not. This is because map and set patterns express submap and subset patterns respectively. The pattern

```clj
{:foo 1}
```

expresses the value being matched is a map containing the key `:foo` with value `1`. The pattern

```clj
#{:foo :bar}
```

expresses the value being matched is a set containing the values `:foo` and `:bar`.

#### ClojureScript Literals

In ClojureScript it is possible to pattern match on JavaScript `Array`s and `Object` using the `#js []` and `#js {}` literal syntaxes respectively.

```clj
(match #js [1 2 1]
  #js [?x ?y ?x]
  ?x)
;; => 1
```

```clj
(match js/process
  #js {:version ?version, :platform ?platform}
  [?version ?platform])
;; =>
["v11.4.0" "darwin"]
```

`#js {}` pattern matching is _very_ liberal and matches against _any_ non `nil` equivalent JavaScript object.

#### Variables

Pattern variables are variables which may or may not bind symbols to the values they match. In the case of variables which bind, the bindings are made available for use in pattern actions, substitutions, and even within patterns. There are two types of pattern variables which bind, _logic variables_ and _memory variables_, and one type of variable which does not, the so-called _any variable_ also known as a wild card.

#### Logic Variables

_Logic variables_ are variables which express an equivalent, but not necessarily identical, value everywhere within a pattern. They are represented by a simple symbol prefixed with the `?` character.

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


#### Memory Variables

_Memory variables_ are variables which "remember" or collect values during pattern matching. They are represented by a simple symbol prefixed with the `!` character. Because they collect multiple values it is idiomatic to employ a plural naming convention e.g. `!xs` or `!people`.


To collect values from a 4-tuple such that we collect the first and last elements in one container and the middle elements in another we would write the following.

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

#### `guard`

`(guard expr)` matches whenenver `expr` true.

Example:

```clj
(match 42
  (guard true) :okay)
;; => :okay
```

#### `pred`

`(pred pred-fn pat-0 ,,, pat-n)` matches whenenver `pred-fn` applied to the current value being matched returns a truthy value and all of `pat-0` through `pat-n` match.

Example:

```clj
(match 42
  (pred even?) 
  :okay)
;; => :okay
```

```clj
(match [42 43]
  [(pred even? ?x) (pred odd?)]
  ?x)
;; => 42
```

#### `app`

`(app fn-expr pat-0 ,,, pat-n)` matches whenever `fn-expr` applied to the current value being matched matches `pat-0` through `pat-n`.

```clj
(r.match/match 42
  (app inc (pred odd? ?x))
  :even

  _
  :odd)
;; =>
:even

(r.match/match (list 1 2 3)
  (and (app first ?x) (app rest ?xs))
  {'?x ?x, '?xs ?xs})
;; =>
{'?x 1, '?xs (2 3)}
```

#### `let`

`(let pat expr)` matches when `pat` matches the result of evaluating `expr`. This allows pattern matching on an arbitrary expression.

Example:

```clj
(match 42
  (or [?x ?y] (let [?x ?y] [1 2]))
  [?x ?y])
;; => [1 2]
```

#### `and`

`(and pat-0 ,,, pat-n)` matches when all of `pat-0` through `pat-n` match.

Example:

```clj
(match 42
  (and ?x (guard (even? ?x)))
  ?x)
;; => 42
```

#### `or`

`(or pat-0 ,,, pat-n)` matches when any one of `pat-0` through `pat-n` match.

Example:

```clj
(match 42
  (or 43 42 41)
  true)
;; => true
```

Note that unbound variables _must_ be shared by `pat-0` through `pat-n`.

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

#### `scan`

`(scan pat)` searches a sequence for elements that match `pat`.

```clj
(search [1 2 3]
  (scan ?x)
  ?x)
;; => (1 2 3)
```

```clj
(search {:x 1 :y 2 :z 3}
  (scan [?a ?b])
  {?b ?a})
;; => ({1 :x} {2 :y} {3 :z})
```

#### `$`

`($ pat)` recursively searches all nested sequences for elements that match
`pat`

```clj
(search [[1] 2 [[3 4] 5]]
  ($ [?a ?b])
  [?a ?b])
;; => ([[3 4] 5] [3 4])
```

Additionally, you can optionally specify a `context` variable that, when called
with an argument, returns the toplevel collection with all matched variables
replaced with the argument.

```clj
(search [[1] 2 [[3 4] 5]]
  ($ ?context [?a ?b])
  (?c [9])
;; => ([[1] 2 [9]] [[1] 2 [[9] 5]])
```

### Subsequences

When matching subsequences it is often useful to express the notions of _zero or more_ and _n or more_ things. The postfix operators `...` or `..n` respectively provide this utility.

#### Zero or more

The `...` postfix operator matches the _subsequence_ of patterns to it's left (up to the first `.` or start of the collection) zero or more times.

Example:

```clj
(match [1 2 1 2 2 3]
  [1 2 ... ?x ?y]
  [?x ?y])
;; =>
[2 3]
```

```clj
(match [:A :A :A :B :A :C :A :D]
  [:A !xs ...]
  !xs)
;; =>
[:A :B :C :D]
```
  
#### N or more

The `..n` postfix operator matches the _subsequence_ of patterns to it's left (up to the first `.` or start of the collection) _n_ or more times where _n_ is a positive natural number.

Example:

```clj
(match [1 1 1 2 3]
  [1 ..3 ?x ?y]
  [?x ?y])
;; =>
[2 3]
```
  
```clj
(match [1 2 3]
  [1 ..3 ?x ?y]
  [:okay [?x ?y]]

  _
  [:fail])
;; => 
[:fail]
```

#### Partition

The `.` operator, read as "partition", partitions the collection into two parts: left and right. This operator is use primarily to delimit the start of a variable length subsequence. It is important to note that both `...` and `..n` act as partition operators as well.

Example:

```clj
(match [3 4 5 6 7 8] 
  [3 4 . !xs !ys ...]
  [!xs !ys])
;; =>
[[5 7] [6 8]]
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

### Escaping

In some cases you may want to "parameterize" a pattern by referencing an external value. This can be done using Clojure's `unquote` operator (`unquote-splicing` is currently not implemented).

##### `unquote`

Example:

```clj
(let [f (fn [x]
          (fn [z]
            (match z
              {:x ~x, :y ?y}
              [:okay ?y]
              _
              [:fail])))
      g (f 1)]
  [(g {:x 1 :y 2})
   (g {:x 2 :y 2})])
;; =>
[[:okay 2] [:fail]]
```

```clj
;; The first two elements summed together equals the third.
(let [f (fn [z]
          (match z
            [?x ?y ~(+ ?x ?y)]
            :yes
            _
            :no))]
  [(f [1 2 3])
   (f [2 1 4])
   (f [1 3 4])])
;; =>
[:yes :no :yes]
```


## Pattern Substitution

Pattern substitution can be thought of as the inverse to pattern matching. While pattern matching binds values by deconstructing an object, pattern substitution uses existing bindings to _construct_ an object.

The `substitute` operator is available from the `meander.substitute.gamma` namespace and utilizes the same syntax as `match` and `search` (with a few exceptions). On it's own it is unlikely to be of much use, however, it is a necessary part of building syntactic _rewrite rules_.

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

Memory variables disperse their values throughout a substitution. Each occurence disperses one value from the collection into the expression.

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

## Rewriting

* [Rewriting Overview](#rewriting-overview)
* [Strategy Combinators](#strategy-combinators)
* [Basic Combinators](#basic-combinators)
  * [`fail`](#fail)
  * [`build`](#build)
  * [`pipe`](#pipe)
  * [`choice`](#choice)
  * [`pred`](#choice)
* [Traversal Combinators](#traversal-combinators)
  * [`one`](#one)
  * [`some`](#some)
  * [`all`](#all)
* [Matching Combinators](#traversal-combinators)
  * [`match`](#match)
  * [`find`](#find)
  * [`rewrite`](#rewrite)
  
### Rewriting Overview

Rewriting, also known as term rewriting or program transformation, is a programming paradigm based on the idea of replacing one term with another.

A _term_ is simply some [valid expression](https://en.wikipedia.org/wiki/Well-formed_formula) in a given language. In Clojure these are objects which can be expressed in Clojure syntax.

In a term rewriting system a replacement, formally known as a _reduction_, is described by a rule, or _identity_, which expresses an equivalence relation between two terms. In mathematics this relationship is often expressed with the `=` sign. To make this concept clear let's consider two properties of multiplication: the distributive and commutative properties.

The distributive property of multiplication is defined as

```
a × (b + c) = (a × b) + (a × c)
```

The commutative property for multiplication is defined as

```
a × b = b × a
```

Putting multiplication aside for moment and considering only the symbols involved on both sides of the `=`, we can view these identities as a description of how to _rewrite_ the term on the left as the term on the right. Indeed, the term _rewrite_ is commonly used in mathematics text to express this concept. Let's evaluate the expression

```
(w + x) × (y + z)
```

with these rules.

By the distributed property we have 

```
((w + x) × y) + ((w + x) × z)
```

with `a = (w + x)`, `b = y`, and `c = z`.

Next we'll apply the commutative property twice with `a = (w + x)` and `b = y`,

```
(y × (w + x)) + ((w + x) × z)
```

and then with `a = (w + x)` and `b = z`.

```
(y × (w + x)) + (z × (w + x))
```

Finally we can apply the distributive property two more times with `a = y` and `b = (w + x)`,

```
((y × w) + (y × x)) + ((z × (w + x))
```

and then with `a = z` and `b = (w + x)`.

```
((y × w) + (y × x)) + ((z × w) + (z × x))
```

We've now rewritten our original expression by applying the rewrite rules. This is the fundamental concept of term rewriting.

But how did we know we were finished? Couldn't we continue to apply the commutative rule infinitely? We could! It turns out _termination_ is a problem term rewriting systems must grapple with and there are many approaches. One of the simplest is to place the burden of termination on the user. As programmers, we're already accustomed to this problem; we want a `loop` to stop at a certain point etc. In the term rewriting world this is achieved with _strategies_ and _strategy combinators_.

### Strategy Combinators

A _strategy_ is a function of one argument, a term `t`, and returns the term rewritten `t*`. A _strategy combinator_ is a function which accepts, as arguments, one or more _strategies_ and returns a _strategy_.

Meander's strategy combinators can be found in the `meander.strategy.gamma` namespace.

```
(require '[meander.strategy.gamma :as r])
```

The alias `r` stands for "rewrite" and will be used throughout the following examples.

Before diving into the combinators themselves it's important to understand how combinators fail. When a combinator fails to transform `t` into `t*` it returns a special value: `meander.strategy.gamma/*fail*` which is printed as `#meander.gamma/fail[]`. This value is at the heart of strategy control flow. You can detect this value in your with `meander.strategy.gamma/fail?`, however, you should rarely need to reach for this function outside of combinators.

### Basic Combinators

#### `fail`

Strategy which always fails.

```clj
(r/fail 10)
;; =>
#meander.gamma/fail[]
```

#### `build`

Strategy combinator which takes a value returns a strategy which always returns that value. Like `clojure.core/constantly` but the returned function takes only one argument.

```clj
(let [s (r/build "shoe")]
  (s "horse"))
;; =>
"shoe"
```

#### `pipe`

Strategy combinator which takes two (or more) strategies`p` and `q` and returns a strategy which applies `p` to `t` and then `q` if and only if `p` is successful. Fails if either `p` or `q` fails.


```clj
(let [s (r/pipe inc str)]
  (s 10))
;; =>
"11"
```

```clj
(let [s (r/pipe inc fail)]
  (s 10))
;; =>
#meander.gamma/fail[]
```

Note: `pipe` actually takes zero or more strategies as arguments and has behavior analogous to `and` e.g. `((pipe) t)` and `((pipe s) t)` is the equivalent to `(identity t)` and `(s t)` respectively.

#### `choice`

Strategy which takes two (or more) strategies `p` and `q` and returns a strategy which attempts to apply `p` to `t` or `q` to `t` whichever succeeds first. Fails if all provided strategies fail. Choices are applied deterministically from left to right.

```clj example
(let [s1 (r/pipe inc fail)
      s2 (r/pipe inc str)
      s (r/choice s1 s2)]
  (s 10))
;; =>
"10"
```

#### `pred`

The strategy `(pred pred-fn)` succeeds returning `t` if the result of applying pred-fn to `t`is truthy.


```clj example
(let [s (r/pred even?)]
  (s 2))
;; =>
2
```

```clj example
(let [s (r/pipe (r/pred even?) inc)]
  (s 2))
;; =>
3
```

### Traversal Combinators

#### `one`

The `one` combinator is a traversal combinator which applies a strategy `s` to one child of a term `t`. If there is no child term for which `s` succeeds then `(one s)` fails.

```clj example
(let [s (fn [x]
          (if (number? x)
            (inc x)
            r/*fail*))
      one-s (r/one s)]
  (one-s ["a" 2 "b" 3]))
;; => 
["a" 3 "b" 3]
```

```clj example
(let [s (fn [x]
          (if (number? x)
            (inc x)
            r/*fail*))
      one-s (r/one s)]
  (s ["a" "b" "c"]))
;; => 
#meander.gamma/fail[]
```

#### `some`

The `some` combinator is a traversal combinator which applies a strategy `s` to at least child of a term `t`. If there is no child term for which `s` succeeds then `(some s)` fails.

```clj example
(let [s (fn [x]
          (if (number? x)
            (inc x)
            r/*fail*))
      some-s (r/some s)]
  (some-s ["a" 2 "b" 3]))
;; => 
["a" 3 "b" 4]
```

```clj example
(let [s (fn [x]
          (if (number? x)
            (inc x)
            r/*fail*))
      some-s (r/some s)]
  (some-s ["a" "b" "c"]))
;; => 
#meander.gamma/fail[]
```

#### `all`

The `all` combinator is a traversal combinator which applies a strategy `s` to every child of a term `t`. If there one child term for which `s` fails then `(all s)` fails.

```clj example
(let [s (fn [x]
          (if (number? x)
            (inc x)
            r/*fail*))
      all-s (r/all s)]
  (all-s [1 2 3]))
;; => 
[2 3 4]
```

```clj example
(let [s (fn [x]
          (if (number? x)
            (inc x)
            r/*fail*))
      all-s (r/all s)]
  (all-s [1 2 "c"]))
;; => 
#meander.gamma/fail[]
```

### Matching Combinators

#### `match`

The `match` strategy is built on top of `meander.match.gamma/match`. It succeeds whenever some term `t` is successfully matched.

```clj example
(let [s (r/match
          [:foo ?bar ?baz]
          {:bar ?bar, :baz ?baz})]
  (s [:foo 1 2]))
;; =>
{:bar 1, :baz 2}
```

```clj example
(let [s (r/match
          [:foo ?bar ?baz]
          {:bar ?bar, :baz ?baz})]
  (s [:baz 1 2]))
;; =>
#meander.gamma/fail[]
```

#### `find`

The `find` strategy is built on top of `meander.match.gamma/find`. 

```clj example
(let [s (r/find
          {:ns ?ns
           :namespaces {?ns ?syms}}
          ?syms)]
  (s '{:ns b.core
       :namespaces {a.core [a aa aaa]
                    b.core [b bb bbb]}}))
;; =>
[b bb bbb]
```

Like the macro it is built on top of, the `find` strategy will always succeed unless it explicitly returns `meander.match.gamma/*fail*`.

```clj example
(let [s (r/find
          {:ns ?ns
           :namespaces {?ns ?syms}}
          ?syms)]
  (s '{:ns c.core
       :namespaces {a.core [a aa aaa]
                    b.core [b bb bbb]}}))
;; =>
nil
```

```clj example
(let [s (r/find
          {:ns ?ns
           :namespaces {?ns ?syms}}
          ?syms

          _
          r/*fail*)]
  (s '{:ns c.core
       :namespaces {a.core [a aa aaa]
                    b.core [b bb bbb]}}))
;; =>
#meander.gamma/fail[]
```
