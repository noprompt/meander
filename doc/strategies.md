# Strategies

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

## Rewriting Overview

Rewriting, also known as term rewriting or program transformation, is a programming paradigm based on the idea of replacing one term with another.

A _term_ is simply some [valid expression](https://en.wikipedia.org/wiki/Well-formed_formula) in a given language. In Clojure these are objects which can be expressed in Clojure syntax.

In a term rewriting system a replacement, formally known as a _reduction_, is described by a rule, or _identity_, which expresses an equivalence relation between two terms.
In mathematics this relationship is often expressed with the `=` sign.
To make this concept clear let's consider two properties of multiplication: the distributive and commutative properties.

The distributive property of multiplication is defined as

```
a × (b + c) = (a × b) + (a × c)
```

The commutative property for multiplication is defined as

```
a × b = b × a
```

Putting multiplication aside for moment and considering only the symbols involved on both sides of the `=`, we can view these identities as a description of how to _rewrite_ the term on the left as the term on the right.
Indeed, the term _rewrite_ is commonly used in mathematics text to express this concept. Let's evaluate the expression

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

But how did we know we were finished?
Couldn't we continue to apply the commutative rule infinitely?
We could!
It turns out _termination_ is a problem term rewriting systems must grapple with and there are many approaches.
One of the simplest is to place the burden of termination on the user.
As programmers, we're already accustomed to this problem; we want a `loop` to stop at a certain point etc.
In the term rewriting world this is achieved with _strategies_ and _strategy combinators_.

## Strategy Combinators

A _strategy_ is a function of one argument, a term `t`, and returns the term rewritten `t*`.
A _strategy combinator_ is a function which accepts, as arguments, one or more _strategies_ and returns a _strategy_.

Meander's strategy combinators can be found in the `meander.strategy.epsilon` namespace.

```
(require '[meander.strategy.epsilon :as r])
```

The alias `r` stands for "rewrite" and will be used throughout the following examples.

Before diving into the combinators themselves it's important to understand how combinators fail.
When a combinator fails to transform `t` into `t*` it returns a special value: `meander.strategy.epsilon/*fail*` which is printed as `#meander.epsilon/fail[]`.
This value is at the heart of strategy control flow.
You can detect this value in your with `meander.strategy.epsilon/fail?`, however, you should rarely need to reach for this function outside of combinators.

## Basic Combinators

### `fail`

Strategy which always fails.

```clj
(r/fail 10)
;; =>
#meander.epsilon/fail[]
```

### `build`

Strategy combinator which takes a value returns a strategy which always returns that value.
Like `clojure.core/constantly` but the returned function takes only one argument.

```clj
(let [s (r/build "shoe")]
  (s "horse"))
;; =>
"shoe"
```

### `pipe`

Strategy combinator which takes two (or more) strategies `p` and `q` and returns a strategy which applies `p` to `t` and then `q` if and only if `p` is successful. Fails if either `p` or `q` fails.


```clj
(let [s (r/pipe inc str)]
  (s 10))
;; =>
"11"
```

```clj
(let [s (r/pipe inc r/fail)]
  (s 10))
;; =>
#meander.epsilon/fail[]
```

Note: `pipe` actually takes zero or more strategies as arguments and has behavior analogous to `and` e.g. `((pipe) t)` and `((pipe s) t)` is the equivalent to `(identity t)` and `(s t)` respectively.

### `choice`

Strategy which takes two (or more) strategies `p` and `q` and returns a strategy which attempts to apply `p` to `t` or `q` to `t` whichever succeeds first.
Fails if all provided strategies fail.
Choices are applied deterministically from left to right.

```clj example
(let [s1 (r/pipe inc r/fail)
      s2 (r/pipe inc str)
      s (r/choice s1 s2)]
  (s 10))
;; =>
"11"
```

### `pred`

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

## Traversal Combinators

### `one`

The `one` combinator is a traversal combinator which applies a strategy `s` to one child of a term `t`.
If there is no child term for which `s` succeeds then `(one s)` fails.

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
#meander.epsilon/fail[]
```

### `some`

The `some` combinator is a traversal combinator which applies a strategy `s` to one child of a term `t`.
If there is no child term for which `s` succeeds then `(some s)` fails.

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
#meander.epsilon/fail[]
```

### `all`

The `all` combinator is a traversal combinator which applies a strategy `s` to every child of a term `t`.
If there is one child term for which `s` fails then `(all s)` fails.

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
#meander.epsilon/fail[]
```

## Matching Combinators

### `match`

The `match` strategy is built on top of `meander.match.epsilon/match`.
It succeeds whenever some term `t` is successfully matched.

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
#meander.epsilon/fail[]
```

### `find`

The `find` strategy is built on top of `meander.match.epsilon/find`.

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

Like the macro it is built on top of, the `find` strategy will always succeed unless it explicitly returns `meander.match.epsilon/*fail*`.

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
#meander.epsilon/fail[]
```

#### `rewrite`

The `rewrite` strategy is built on top of `meander.match.epsilon/find` and `meander.substitute.epsilon/substitute` and has the same form as `find`, `match`, and `search`, however, a substitution is performed instead of executing code.
This allows for purely symbolic data transformation and is an incredibly powerful tool for syntactic and structural manipulations.

```clj example
;; The commutative rule for multiplication.
(let [comm (rewrite
            ;; Left side
            (* ?a (+ ?b ?c))
            ;; Right side
            (+ (* ?a ?b)
               (* ?a ?c)))]
  (comm '(* (+ w x)
            (+ y z))))
;; =>
(+ (* (+ w x) y)
   (* (+ w x) z))
```

```clj example
(let [s (rewrite
         (let* [!bs !vs ..1]
           . !body ...)
         (let* [!bs !vs]
           (let* [!bs !vs ...]
             . !body ...)))]
    (s '(let* [b1 :v1, b2 :v2, b3 :v3]
          (vector b1 b2 b3))))
;; =>
(let* [b1 :v1]
 (let* [b2 :v2
        b3 :v3]
   (vector b1 b2 b3)))
```