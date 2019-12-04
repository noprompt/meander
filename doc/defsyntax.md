# Syntax Extensions


Meanders pattern syntax can be extended with new "special" forms called _operators_ via the `defsyntax` macro located in the `meander.epsilon` namespace.


```clj
(require '[meander.epsilon :as m]
```

## Overview

`defsyntax` is similar to `defmacro` in that it defines a function which will be called with unevaluated arguments.
Functions defined by `defsyntax` are invoked when patterns are parsed e.g. during the macro expansion of `m/match`, `m/subst`, etc.
Like `defmacro`, `defsyntax` binds the special variables `&form` and `&env`, but unlike `defmacro` are often essential.
`&form` is semantically equivalent to the `&form` bound by `defmacro`; its the form of the invocation of the operator i.e. `(operator-name ?x ?y)`.
On the other hand, `&env` is only similar to the `&env` of `defmacro` in that it is a `map?` and contains contextual data.

## Tutorial

To build some motivation for `defsyntax` consider the following.

```clj
(m/match :foo/bar
  (m/pred ident? (m/app namespace ?ns) (m/app name ?name))
  [?ns ?name]
  
  _
  nil))
;; => ["foo" "bar"]
```

This example captures a common scenario in Clojure: given an `ident?`, we'd like to get its `namespace` and `name`.
However, it is also often the case to then dispatch on these values.
We could tighten up the original pattern by matching the result `namespace`, etc.


```clj
(m/match :foo/bar
  (m/pred ident? (m/app namespace "clojure.core") (m/app name ?name))
  ?name
  
  _
  nil))
;; => nil
```

But if we had many cases this approach could become inconvenient.

Lets see how `defsyntax` can help us.

```clj
(m/defsyntax ident [ns-pattern name-pattern]
  `(m/pred ident? (m/app namespace ~ns-pattern) (m/app name ~name-pattern)))
;; => #'user/ident
```

Next, lets apply our extension to our original scenario.

```clj
(m/match :foo/bar
  (ident ?ns ?name)
  [?ns ?name]

  _
  nil)
;; => ["foo" "bar"]
```

Success!

How about our `clojure.core` case?

```clj
(m/match `inc
  (ident "clojure.core" ?name)
  ?name

  _
  nil)
;; => "inc"
```

Check!

What if we're interested in `ident?`s without namespace?

```
(m/match 'inc
  (ident nil ?name)
  ?name)
;; => "inc"
```

Check!

Does it work with `search`?

```clj
(m/search [`inc `m/scan `dec]
  (m/scan (ident _ ?name))
  ?name)
;; => ("inc" "scan" "dec")
```

Yes!

We've made ourselves a nice extension that allows us to conviently match `ident?`s in a variety of ways.

But what happens if we try to use our extension with `subst`?

```clj
(let [?namespace "clojure.core"
      ?name "inc"]
  (m/subst (ident ?namespace ?name)))
;; =>
;; 1. Unhandled java.lang.ClassCastException
;;    class java.lang.String cannot be cast to class clojure.lang.Named
;;    (java.lang.String is in module java.base of loader 'bootstrap';
;;    clojure.lang.Named is in unnamed module of loader 'app')
```

Uh, oh.
Whats going on here?

To figure it out, lets consider the expansion of our operator.

When the pattern

```clj
(ident ?namespace ?name)
```

is expanded it becomes the pattern

```clj
(m/pred clojure.core/ident? (m/app namespace ?namespace) (m/app name ?name))
```

per our definition.
Since `m/pred` is not a substitution operator it is treated as a list of substitutions

```clj
(list (m/subst m/pred) (m/subst clojure.core/ident?) ,,,)
```

The first two elements of the list will substitute as symbols.

But what about `(m/app namespace ?namespace)`?
`m/app` _is_ a substitution operator.
It applies the function to the result of the applying substitution to its arguments.
To cut to the chase, in this case that means

```clj
(namespace ?namespace)
;; ==
(namespace "clojure.core")
```

If we try invoking that last form we will see the same error which was reported before, the `java.lang.ClassCastException`.
So how do we fix this?
The answer lies in `&env`.

`&env` contains data which allows us to identify what kind of syntax our extension applies to e.g. whether we are matching or subsiting.
The syntax is matching on the left side of `m/match`, `m/search`, `m/find`, `m/rewrite`, and `m/rewrites`; and substituting in `m/subst` and on the right side of `m/rewrite` and `m/rewrites`.
We can use the functions `m/match-syntax?` and `m/subst-syntax?` to detect which syntax is in use.
Lets update our previous extension.

```clj
(m/defsyntax ident [ns-pattern name-pattern]
  (if (m/match-syntax? &env)
    `(m/pred ident? (m/app namespace ~ns-pattern) (m/app name ~name-pattern))
    &form))
;; #'user/ident
```

And try our substitution again.

```clj
(let [?namespace "clojure.core"
      ?name "inc"]
  (m/subst (ident ?namespace ?name)))
;; => (ident "clojure.core" "inc")
```

It works!
Now we can use our extension safely in either context.
Note that we also used `&form` to return the original form in the subsitution case.
Many of the extensions defined in the `meander.epsilon` namespace do a similar thing.
If you're curious, take a look at the definition of `m/keyword` or `m/symbol` to see how you might implement a substitution extension.
