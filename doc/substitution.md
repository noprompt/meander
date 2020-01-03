# Substitution

Substitution can be thought of as the inverse to pattern
matching. While pattern matching binds values by deconstructing an
object, substitution uses existing bindings to _construct_ an object.
When used in conjunction with pattern matching it is an incredibly
powerful tool for data transformation.  

Since Meander's rewriting macros involve substitution it is
important to understand how substitution works.

To get started with subsitution, require the `meander.epsilon`
namespace.

```clj
(require '[meander.epsilon :as m])
```

`meander.epsilon/subst` is the substitution macro. The `subst`
macro has a simple signature taking one argument: a pattern. With a
few exceptions, the syntax for `subst` patterns is the same as
`match`, `find`, and `search` patterns.


## Literals

Literal values evaluate to themselves.

```clj
(m/subst 1)
;; =>
1

(m/subst (S (S 0)))
;; =>
(S (S 0))
```

### Logic variables

Logic variables have semantically equivalent behavior to the unquote operator.

```clj
(let [?x 0]
  (m/subst [?x ~?x]))
;; =>
[0 0]
```

### Memory variables

Memory variables disperse their values throughout a substitution. Each
occurrence disperses one value from the collection.

```clj
(let [!xs [1 2 3]]
  (m/subst (!xs !xs !xs)))
;; =>
(1 2 3)
```

For subsequence patterns, values are dispersed until one of the memory
variables is exhausted.

```clj
(let [!bs ['x 'y]
      !vs [1 2 3]]]
  (m/subst [!bs !vs ...]))
;; =>
[x 1 y 2]
```

When an expression has memory variable occurrences which exceed the
number of available elements in its collection `nil` is dispersed
after it is exhausted.

```clj
(let [!xs [1]]
  (m/subst (!xs !xs !xs)))
;; =>
(1 nil nil)
```

`nil` is also dispersed in n or more patterns up to `n`.

```clj
(let [!xs ['A]
      !ys [:B]]
  (m/subst (!xs !ys ..2)))
;; =>
(A :B nil nil)
```