# Pattern Matching

* [Macros](#Macros)
  * [`match`](#match)
  * [`search`](#search)
  * [`find`](#find)
* [Pattern Syntax](#pattern-syntax)
  * [Literals](#literals)
  * [ClojureScript Literals](#clojurescript-literals)
  * [Variables](#variables)
    * [Logic Variables](#logic-variables)
    * [Memory Variables](#memory-variables)
    * [Any Variables](#any-variables)
    * [Mutable Variables](#mutable-variables)
  * [Operators](#operators)
    * [`and`](#and)
    * [`or`](#or)
    * [`pred`](#pred)
    * [`guard`](#guard)
    * [`app`](#app)
    * [`let`](#let)
    * [`not`](#not)
    * [`scan`](#scan)
    * [`$`](#subtree-search-)
    * [`with`](#with)
  * [Subsequences](#subsequences)
    * [Zero or More](#zero-or-more)
    * [N or More](#n-or-more)
    * [Repeating with Variables](#repeating-with-variables)
    * [Partition](#partition)
    * [Rest](#rest)
  * [Escaping](#escaping)
    * [Unquote](#unquote)

## Macros

The primary macros for pattern matching and searching are available in `meander.epsilon`.

```clj
(require '[meander.epsilon :as m])
```

### `match`

The `match` macro provides traditional pattern matching.
It takes an expression to "match" followed by a series of pattern/action clauses.

```clj
(m/match x  ;; 1
  pattern   ;; 2
  action    ;; 3
  ,,,)
```

1. `x` is the expression.
2. `pattern` is the pattern to match against the expression.
    Patterns have special [syntax](#pattern-syntax) that is important to understand.
3. `action` is the action expression to be evaluated if `pattern` matches successfully. Certain patterns can bind variables and, if a match is successful, will be available to the `action` expression.

A pair of `pattern` and `action` is called a `clause`.

```clj
(m/match [1 2 1]
  [1 2]
  :first-action

  [1 2 1]
  :second-action)
;; =>
:second-action

(m/match []
  [1 2]
  :first-action

  [1 2 1]
  :second-action)
;; #error {:cause "non exhaustive pattern match" }
```

Like `clojure.core/case`, if no patterns match an exception will be thrown.

### `search`

The `search` macro is an extended version of `match` which returns a sequence of all action values which satisfy their pattern counterparts.
Map patterns with variable keys, set patterns with variable subpatterns, or two side-by-side zero or more subsequence patterns, are all examples of patterns which may have multiple matches for a given value. `search` will find all such matches and, unlike `match`, will not throw when a pattern match could not be made.
In essence, `search` allows you to _query_ arbitrary data.

```clj
;; Find all pairs of an odd number followed by an even number in the
;; collection.
(m/search [1 1 2 2 3 4 5]
  [_ ... (m/pred odd? ?a) (m/pred even? ?b) . _ ...]
  [?a ?b])
;; =>
([1 2] [3 4])
```

### `find`

The `find` macro is similar to `search`, however, returns only the first search result.
If it cannot be found, `find` returns `nil`.

```clj
;; Find the first pair of an odd number followed by an even number in
;; the collection.
(m/find [1 1 2 2 3 4 5]
  [_ ... (m/pred odd? ?a) (m/pred even? ?b) . _ ...]
  [?a ?b])
;; =>
[1 2]
```

## Pattern Syntax

### Literals

The simplest patterns to express are literal patterns.
Literal patterns are

1. scalar data types such as numbers, strings, booleans, keywords;
2. quoted or unquoted symbols that are not considered special by Meander, such as variables and operators;
3. and lists and vectors composed of literals and do not contain maps, sets, or subsequence operators.

Notice that maps and sets are not included in the above list, we'll come back to that in a moment.

Literal patterns match _exactly_ themselves. For example, the literal pattern

```clj
(fn [] "foo")
```

matches a list where the first element is the symbol `fn`, the second is the empty vector, and the third is the string `"foo"`.

List and vector patterns may also qualify as literal patterns if they contain no map or set patterns.


```clj
([1] [2] [3])
```

is a literal pattern, however,

```clj
[{:foo 1} #{:bar :baz} {:quux 3}]
```

is not.
This is because map and set patterns express _submap_ and _subset_ patterns respectively.
The pattern

```clj
{:foo 1}
```

expresses the value being matched is a map containing the key `:foo` with value `1`. 
That means that there may be more keys. For example the above pattern would match the following list: `{:foo 1 :bar 2}`.

The pattern

```clj
#{:foo :bar}
```

expresses the value being matched is a set containing the values `:foo` and `:bar`.
Again, this does not mean that these are the only elements in the set.

### ClojureScript Literals

In ClojureScript it is possible to pattern match on JavaScript `Array`s and `Object` using the `#js []` and `#js {}` literal syntaxes respectively.

```clj
(m/match #js [1 2 1]
  #js [?x ?y ?x]
  ?x)
;; => 1
```

```clj
(m/match js/process
  #js {:version ?version, :platform ?platform}
  [?version ?platform])
;; =>
["v11.4.0" "darwin"]
```

`#js {}` pattern matching is _very_ liberal and matches against _any_ non `nil` equivalent JavaScript object.

## Variables

Pattern variables are variables which may or may not bind symbols to the values they match.
In the case of variables which bind, the bindings are made available for use in pattern actions, substitutions, and even within patterns.
There are two types of pattern variables which bind, _logic variables_ and _memory variables_, and one type of variable which does not, the so-called _any variable_ also known as a wild card.

### Logic Variables

_Logic variables_ are variables which express an equivalent, but not necessarily identical, value everywhere within a pattern.
They are represented by a simple symbol prefixed with the `?` character.

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

since the second occurrence of `?x` is not equal to `1`.

Note that a logic variable in place of a map's value might have a surprising result:

```clj
(m/match {:a 1}
  {:b ?b}
  ?b)
;; =>
nil
```

One might expect this pattern to fail, however, because it is common for map keys to be optional, Meander takes the position that map patterns should accommodate these situations.

```clj
(doseq [person [{:name "John Doe" :title "MD"}
                {:name "Mike Foe"}]]
  (m/match person
    {:name ?name :title ?title}
    (println (str ?name (when ?title (str ", " ?title))))))
;; =>
John Doe, MD
Mike Foe
nil
```

If you wish to match a key's value to a non-`nil` value you can use the [`some`](#some) pattern operator.

```clj
(m/match {:name "Mike Foe"}
  {:name (m/some ?name)
   :title (m/some ?title)}
  [?name ?title])
;; =>
Execution error (ExceptionInfo) at user/eval4134$fail (REPL:1).
non exhaustive pattern match
```

Pattern operators are discussed [below](#operators).

### Memory Variables

_Memory variables_ are variables which "remember" or collect values during pattern matching.
They are represented by a simple symbol prefixed with the `!` character.
Because they collect multiple values it is idiomatic to employ a plural naming convention e.g. `!xs` or `!people`.

To collect values from a 4-tuple such that we collect the first and last elements in one container and the middle elements in another we would write the following.

```clj
[!xs !ys !ys !xs]
```

This pattern will match a value like

```clj
[:red :green :yellow :blue]
```

and bind `!xs` to `[:red :blue]` and `!ys` to `[:green :yellow]`.


### Any Variables

_Any variables_ are variables which match anything but do not bind the values they match.
They are represented as simple symbols prefixed with the `_` character e.g. `_`, `_first-name`, and so on.
Any variables commonly appear in the last clause of a `match` expression as a catch-all when all other patterns fail to match.

### Mutable Variables

_Mutable variables_ are variables which, like _any variables_, will match anything but, unlike _any variables_ will bind the values they match.
They are represented as simple symbols prefixed with the `*` character e.g. `*scratch`.
Mutable variables were introduced as a primitive in order to derive specific features cleanly.

Matching the pattern

```clj
[*m *m]
```

against

```clj
[1 2]
```

would first bind `*m` to `1`, and then ultimately to `2`.


## Operators

### `guard`

```clj
(m/guard expr)
```

matches whenever `expr` is truthy.


```clj
(m/match :anything
  (m/guard (= 1 1)) :okay)
;; => :okay
```

### `pred`

`(m/pred pred-fn pat-0 ,,, pat-n)` matches whenever `pred-fn` applied to the current value being matched returns a truthy value and all of `pat-0` through `pat-n` match.

```clj
(m/match 42
  (m/pred even?)
  :okay)
;; => :okay
```

```clj
(m/match [42 43]
  [(m/pred even? ?x) (m/pred odd?)]
  ?x)
;; => 42
```

### `app`

```clj
(m/app fn-expr pat-0 ,,, pat-n)
```

matches whenever `fn-expr` applied to the current value being matched matches `pat-0` through `pat-n`.

```clj
(m/match 42
  (m/app inc (m/pred odd? ?x))
  :even

  _
  :odd)
;; =>
:even

(m/match (list 1 2 3)
  (m/and (m/app first ?x) (m/app rest ?xs))
  {:x ?x, :xs ?xs})
;; =>
{:x 1, :xs (2 3)}
```

### `let`

```clj
(meander.epsilon/let [pattern clojure-expr ,,,])

(meander.epsilon/let [pattern clojure-expr ,,,]
  target-pattern)
```

matches when all of the `pattern`s matches the result of evaluating their corresponding `clojure-expr`.
This allows pattern matching on an arbitrary expression. 
Optionally, `target-pattern` may be provided to pattern match on the current pattern matching target.

```clj
(m/match :not-a-pair
  (m/or [?x ?y]
        (m/let [?x 1, ?y 2]))
  [?x ?y])
;; => [1 2]
```

### `not`

```clj
(meander.epsilon/not pattern)
```

is the negation of a pattern.
It will match anything that does not match `pattern`.

```clj
(m/match 12
  (m/not 42)
  :yep)
;; => :yep

(m/match 42
  (m/not 42)
  :yep

  _
  :nope)
;; => :nope
```

### `and`

```clj
(meander.epsilon/and pat-0 ,,, pat-n)
```

matches when all of `pat-0` through `pat-n` match.

```clj
(m/match 42
  (m/and ?x (m/guard (even? ?x)))
  ?x)
;; => 42
```

### `or`

```clj
(meander.epsilon/or pat-0 ,,, pat-n)
```

matches when any one of `pat-0` through `pat-n` match.

```clj
(m/match 42
  (m/or 43 42 41)
  true)
;; =>
true
```

Note that unbound variables _must_ be shared by `pat-0` through `pat-n`.

```clj
(m/match [1 2 3]
  (m/or [?x ?y]
        [?x ?y ?z])
  [?x ?y])
;; Every pattern of an or pattern must have references to the same
;; unbound variables.
;; {:pat (m/or [?x ?y] [?x ?y ?z]),
;;  :env #{},
;;  :problems [{:pat [?x ?y], :absent #{?z}}]}
```

### `scan`

```clj
(meander.epsilon/scan pattern)
```

searches a sequence for elements that match `pattern`.

```clj
(m/search [1 2 3]
  (m/scan ?x)
  ?x)
;; =>
(1 2 3)
```

```clj
(m/search {:x 1 :y 2 :z 3}
  (m/scan [?a ?b])
  {?b ?a})
;; => ({1 :x} {2 :y} {3 :z})
```

### Subtree search `$`

```clj
(meander.epsilon/$ pattern)
(meander.epsilon/$ context pattern)
```

recursively searches for any subtree that matches `pattern`.

```clj
(m/search [[1] 2 [[3 4] 5]]
  (m/$ [?a ?b])
  [?a ?b])
;; => ([[3 4] 5] [3 4])
```

You can optionally specify a `?context` variable that,
when called with an argument, returns the toplevel collection with the
matched variable replaced with the argument.

```clj
(m/search [[1] 2 [[3 4] 5]]
          (m/$ ?context [?a ?b])
          (?context (str "a:" ?a ", b:" ?b)))
;; => ([[1] 2 "a:[3 4], b:5"] [[1] 2 ["a:3, b:4" 5]])
```

### `with`

The `with` pattern operator enables patterns to be bound much like `clojure.core/let`.

```clj
(m/with [%ref1 pat1 
         ,,,
         %refn patn]
  pat)
```

These pattern bindings are called "references" and are named with simple symbols prefixed by the `%` character.
References may be specified in any order and may also be recursive.
In essence, the `with` operator allows for novel and powerful feature: the ad-hoc construction and matching of recursive grammars.

```clj
(def hiccup
  [:div
   [:p {"foo" "bar"}
    [:strong "Foo"]
    [:em {"baz" "quux"} "Bar"
     [:u "Baz"]]]
   [:ul
    [:li "Beef"]
    [:li "Lamb"]
    [:li "Pork"]
    [:li "Chicken"]]])

;; meander.epsilon/find
(m/find hiccup
  (m/with [%h1 [!tags {:as !attrs} . %hiccup ...]
           %h2 [!tags . %hiccup ...]
           %h3 !xs
           %hiccup (m/or %h1 %h2 %h3)]
    %hiccup)
  [!tags !attrs !xs])
;; =>
[[:div :p :strong :em :u :ul :li :li :li :li]
 [{"foo" "bar"} {"baz" "quux"}]
 ["Foo" "Bar" "Baz" "Beef" "Lamb" "Pork" "Chicken"]]
```

In the example above, `with` is used to (naively) describe and match [hiccup](https://github.com/weavejester/hiccup).
Notice that references `%h1`, `%h2`, and `%hiccup` refer to each other in their definitions.
The "body" of the `with` form says we wish to match `%hiccup` against the current value being matched, in this case `hiccup`.
When the match executes it does so recursively and, as we can see, binds and collects all the values specified.

## Subsequences

When matching subsequences it is often useful to express the notions of _zero or more_ and _n or more_ things. The postfix operators `...` or `..n` respectively provide this utility.

### Zero or more

The `...` postfix operator matches the _subsequence_ of patterns to its left (up to the first `.` or start of the collection) zero or more times.

```clj
(m/match [1 2 1 2 2 3]
  [1 2 ... ?x ?y]
  [?x ?y])
;; =>
[2 3]
```

```clj
(m/match [:A :A :A :B :A :C :A :D]
  [:A !xs ...]
  !xs)
;; =>
[:A :B :C :D]
```

Note that multiple unbounded sequences are allowed in a pattern only for `search` and `find`, `match` will throw an exception since the result is non-determinitic.

```clj
(m/search [1 1 2 3]
  [1 ... !rest ... ]
  !rest)
;; =>
([1 1 2 3] [1 2 3] [2 3])

(m/match [1 1 2 3]
  [1 ... !rest ... ]
  !rest)
;; =>
Syntax error macroexpanding mm/match at (REPL:1:1).
A variable length subsequence pattern may not be followed by another variable length subsequence pattern.
```

### N or more

The `..n` postfix operator matches the _subsequence_ of patterns to its left (up to the first `.` or start of the collection) _n_ or more times where _n_ is a positive natural number.

```clj
(m/match [1 1 1 2 3]
  [1 ..3 ?x ?y]
  [?x ?y])
;; =>
[2 3]
```

```clj
(m/match [1 2 3]
  [1 ..3 ?x ?y]
  [:okay [?x ?y]]
  _
  [:fail])
;; =>
[:fail]

(m/match [1 1 1 2 3]
  [1 ..3 ?x ?y]
  [:okay [?x ?y]]
  _
  [:fail])
;; =>
[:okay [2 3]]
```

### Repeating With Variables

In addition to repeating n or more times you can control or capture repeating using logic or memory variables. 
First let's look at logic variables.

```clj
(m/match [:a :b :c]
  [!xs ..?n]
  [!xs ?n])
;; =>
[[:a :b :c] 3]

(m/match [2 :one :two]
  [?n . !xs ..?n]
  [?n !xs])
;; =>
[2 [:one :two]]

(m/match [2 :one :two :three]
  [?n . !xs ..?n]
  [?n !xs])
  
;; Doesn't match because there are 3 elements, not two.
;; =>
;; non exhaustive pattern match
```

Instead of logic variables, we can capture multiple repeats with memory variables. 
For example:


```clj
(m/match [[1 2 3] [4 5]]
  [[!xs ..!n] [!ys ..!n]]
  [!xs !ys !n])
;; =>
[[1 2 3] [4 5] [3 2]]

;; We can use this to help us with nested groups.
;; By default meander might have some unexpected behavior when capturing
;; nested things in memory variables.

(m/rewrite [:a [1 2 3] :b [4 5]]
  [!k [!x ...] ...]
  [!k [!x ...] ...])
  
;; => [:a [1 2 3 4 5]]

;; We can fix this by capturing the number of times to repeat things.

(m/rewrite [:a [1 2 3] :b [4 5]]
  [!k [!x ..!n] ..!m]
  [!k [!x ..!n] ..!m])

;; => 

[:a [1 2 3] :b [4 5]]
```

### Partition

The `.` operator, read as "partition", partitions the collection into two parts: left and right.
This operator is used primarily to delimit the start of a variable length subsequence.
It is important to note that both `...` and `..n` act as partition operators as well.

```clj
(m/match [3 4 5 6 7 8]
  [3 4 . !xs !ys ...]
  [!xs !ys])
;; =>
[[5 7] [6 8]]
```

Had the pattern `[3 4 . !xs !ys ...]` in this example been written as `[3 4 !xs !ys ...]` the match would have failed.
This is because the latter pattern represents a subsequence of 4 elements beginning with the sequence `3 4`.

```clj
(m/search [3 0 0 3 1 1 3 2 2]
  [_ ... 3 . !ys ...]
  {:!ys !ys})
;; =>
({:!ys [0 0 3 1 1 3 2 2]}
 {:!ys [1 1 3 2 2]}
 {:!ys [2 2]})
```

This example demonstrates how `search` finds solutions for patterns which have sequential patterns which contain variable length subsequences on both sides of a partition.
The pattern 

```clj
[_ ... 3 . !ys ...]
```

says find every subsequence in the vector being matched after _any_ occurrence of a `3`.

### Rest

We can use the `&` operator to match of the rest of sequences, vectors, maps and sets.

```clj
(m/match [1 2 3]
  [1 & ?xs]
  ?xs)
;; =>
[2 3]

(m/match {:a 1 :b 2}
  {:a 1 & ?rest}
  ?rest)
;; =>
{:b 2}

(m/match #{1 2 3}
  #{^& ?rest}
  ?rest)
;; => #{1 3 2}
```

We can also use `&` with substitution to build up sequences, vectors, maps and sets.

```clj
(m/rewrite '(1 2 3)
  ?xs
  [& ?xs])
;; =>
[1 2 3]

(m/rewrite [:a 1 :b 2]
  [!ks !vs ...]
  {& ([!ks !vs] ...)})
;; =>
{:a 1 :b 2}

(m/rewrite [1 2 3]
  ?xs
  #{^& ?xs})
;; =>
#{1 3 2}
```

## Escaping

### `unquote`

In some cases you may want to "parameterize" a pattern by referencing an external value.
This can be done using Clojure's `unquote` operator (`unquote-splicing` is currently not implemented).

```clj
(def x 2)

(defn match-my-map [m]
  (m/match m
    {:x ~x :y ?y}
    [:okay ?y]

    _
    [:fail]))

(match-my-map {:x 1 :y 3})
;; =>
[:fail]

(match-my-map {:x 2 :y 3})
;;=>
[:okay 3]
```

```clj
;; The first two elements summed together equals the third.
(let [f (fn [z]
          (m/match z
            [?x ?y ~(+ (nth z 0) (nth z 1))]
            :yes
            _
            :no))]
  [(f [1 2 3])
   (f [2 1 4])
   (f [1 3 4])])
;; =>
[:yes :no :yes]
```
