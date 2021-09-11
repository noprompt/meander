#  Understanding Meander's Pattern Matching Macros

*Note: The examples in this documentation assume that `meander.epsilon` has been aliased to `m`, either via `(require '[meander.epsilon :as m])` or a similar `:require` clause in an `ns` form.*

Meander provides five pattern matching macros that supply different functionality for specific circumstances:

  * [`match`](#match) --
     Finds a single match of the input against the list of patterns.
     Throws an exception if nothing matches.
  * [`search`](#search) --
    Finds potentially multiple matches of the input against the list of patterns.
    Returns a sequence of matches or `nil` if nothing matches.
  * [`find`](#find) --
    Like `search` but only returns the first match or `nil`.
  * [`rewrites`](#rewrites-and-rewrite) --
    Like `search`, returns a sequence of values or `nil`, but using Meander's substitution operations on the right-hand-side (see [below](#rewrites-and-rewrite)).
  * [`rewrite`](#rewrites-and-rewrite) --
    As `find` is to `search`, so `rewrite` is to `rewrites`.
    Returns the first match and uses Meander's substitution operations on the right-hand-side.


The following sections describe each of these in greater detail.

Before we dive in, however, let's define some concepts that appear throughout the descriptions:

  * *input* --
    The data item that you want to match.
    This is referred to as `x` in the Clojure documentation strings for `match`, `search`, `find`, `rewrites`, and `rewrite`.
    In the examples, below, we typically match against a literal value, but the *input* can also be the value of a variable.
  * *clauses* --
    Each of the macros matches the *input* against a set of *clauses*.
    Each *clause* includes a pair of items, the left-hand-side (LHS) and the right-hand-side (RHS).
    The LHS is called the *pattern* and the RHS is called the *expression*.
    If the *input* matches the *pattern* then Meander evaluates the corresponding *expression* and returns the result.
    Depending on the matching macro that is being used, this may happen multiple times.
  * *pattern* --
    The *pattern* is composed of literal data and Meander's matching operators (described in more detail in [Operator Overview](./operator-overview.md)).
  * *expression* --
    If the *input* matches the *pattern* then Meander evaluates the corresponding *expression* and returns the result.
    In the `match`, `search`, and `find` macros, the *expression* is arbitrary Clojure code that is evaluated normally.
    In the `rewrites` and `rewrite` macros, the expression uses Meander's substitution operations instead of evaluating arbitrary Clojure code (described in more detail in [Substitution](./substitution.md)).

The general form of a Meander matching macro is:

```clojure
(m/matching-macro input
    pattern1 expression1   ; clause 1
    pattern2 expression2   ; clause 2
    ...)
```

Note that while the input value can be a variable, the patterns and expressions of each clause must be literals since the macros must have access to them to compile the appropriate data transformation code.
Since `match`, `search` and `find` support arbitrary Clojure code in the RHS expression, however, it's easy to call out to helper functions.

## `match`

`meander.epsilon/match` is what you might think of as "standard" pattern matching.
In simple cases, its behavior is similar to what you would find in `clojure.core.match/match`.
However, `meander.epsilon/match` provide more powerful operators and pattern variable binding that `clojure.core.match/match` just can't, well, match.
(Pardon the pun.)

Let's start by matching a simple value against a set of other values.

```clojure
(require '[meander.epsilon :as m])

(m/match 2                           ; input value = 2
; pattern (LHS)   expression (RHS)
  1               :one               ; clause 1
  2               :two               ; clause 2
  3               :three)            ; clause 3
;; => :two
```

Here, the input is `2`.
Meander tries to match this value against the LHS pattern of each clause.
It finds a match in clause 2.
Meander then evaluates the expression of clause 2 (the RHS of clause 2).
The expression can be any arbitrary Clojure code, but in this case it's simply the Clojure keyword `:two`.
Since `match` only finds a single match, it then returns.

```clojure
(let [x 2]
  (m/match x
    1 (+ 1 x)
    2 (+ 2 x)))
;; => 4
```

As we said previously, the input can be the value of a variable and the expression can be any legal Clojure expression.

Meander can match against various types of Clojure data structures, too.

```clojure
(m/match [1 2 3]
  [1 2 3] :yes)
;; => :yes
```

Here, we're matching against a vector.
As you'll see when we review the [operators](./operator-overview.md), we can also match against all the other Clojure datatypes (lists, maps, sets, etc.).

Meander also supports *logic variables* that will be set to the value of all or a portion of the input value that matches the rest of the pattern.
We'll learn more about these in [Operator Overview](./operator-overview.md), but we'll introduce the basics here to help us with more sophisticated examples.
A logic variable is simply a symbol that begins with a leading question mark.

```clojure
(m/match [1 2 3]
  [1 ?y 3] ?y)
;; => 2
```

Here, the logic variable is named `?y`.
The pattern says to match against any vector where the first element is `1` and the third element is `3`, and to bind the value of `?y` to the second element.
Note that this pattern will match *many* different vectors: `[1 10 3]`, `[1 22 3]`, `[1 :forty-two 3]`, etc.
Meander simply makes sure that the input is a three-element vector with the first element equal to `1` and the third element equal to `3`.

In this case, we then return the logic variable `?y` as the value of the expression.

If we don't care about pieces of the input, we can use a wildcard, written as an underscore (`_`), and it will match anything in that position.

```clojure
(m/match [1 2 3]
  [_ ?y _] ?y)
;; => 2
```

Here, we use `_` to specify that we don't care about the first or third elements of the vector.
This will match every three element vector and capture the second element in `?y`.

We can also use logic variables to match against the same value at multiple places in the input.
If you've ever programmed in Prolog, you'll be quite at home with this behavior, known as unification.

```clojure
(m/match [1 2 1]
  [?x ?y ?x] [?x ?y])
;; => [1 2]
```

In this case, we've used `?x` in two places.
The first time that `?x` is encountered in the pattern, it will be set to the value of the input at that point.
Subsequently, if `?x` shows up in the pattern again it will already be bound to a value, so Meander will compare `?x` and the input value at that point and ensure that they match (in this case `1`).
If they match, then Meander continues matching this clause.
If they do not match, then Meander moves on.
So, this pattern will match any three element vector where the first and third elements are the *same value*.
You'll see later on that this allows us to do very sophisticated data transformations that are similar to joins in the database world.

If multiple patterns match the input, `match` will only match the first of these.

```clojure
(m/match 2
  2 :two-1
  2 :two-2)
;; => :two-1
```

If you want to match multiple clauses, see [`search`](#search).

If the input doesn't match any of the clauses, `match` throws an exception.

```clojure
(m/match 10
    1 :one
    2 :two
    3 :three)
;; => throws clojure.lang.ExceptionInfo
```

If you don't want the exception, you can use a wildcard to match any input.

```clojure
(m/match 10
  1 :one
  2 :two
  3 :three
  _ :no-match)
;; => :no-match
```

Note that this behavior is similar to Clojure's `case` macro which performs simple constant matching, allows for a default expression, and throws an exception if nothing matches.

Remember that Meander considers the clauses in order.

```clojure
(m/match 1
  _  :no-match
  1 :one
  2 :two
  3 :three)
;; => :no-match
```

In this case, the wildcard matches anything and because `match` stops after finding the first match, it doesn't consider the clause with `1` on the LHS.


## `search`

While `match` will return a single result, the expression corresponding to the first matching clause, or throw an exception, `search` will return a sequence of results corresponding to multiple possible matches (either a list or a lazy seq).
Meander's `search` goes *far* beyond what can be done with other pattern matching libraries such as `clojure.core.match`.

For instance, let's match multiple clauses.

```clojure
(m/search 2
  1 :nope        ; clause 1
  2 :yes         ; clause 2
  3 :not-yet     ; clause 3
  2 :yes-again)  ; clause 4
;; => (:yes :yes-again)
```

In this case, Meander finds a match with clause 2 and then clause 4 and so returns a Clojure seq containing both results.
As with `match`, each of the RHS expressions are arbitrary Clojure code.

```clojure
(m/search [1 2 3]
  [1 ?x _] ?x
  [_ 2 ?x] ?x
  _ :whatever)
;; => (2 3 :whatever)
```

In addition to showing `search` matching against multiple clauses, this example also shows that a logic variable is *local to a clause*.
When `search` matches the input against the first clause, `?x` is bound to `2`.
When `search` comes to the second clause, a *different* logic variable, also named `?x` is then bound to `3`.

If `search` doesn't find any match, it returns `nil`.
(Note that there is some discussion about this. It's probably more appropriate for `search` to return `()` (the empty list) instead of `nil` when there are no matches. This behavior might change in future versions of Meander, so code should be written defensively. If you want to test whether `search` found any matches, for instance, you should probably use something like `empty?` rather than testing for equality with `nil`. In particular, `some?` is troubling since it returns `false` for `nil` but `true` for the empty list.)

```clojure
(m/search :not-going-to-match
  1 :one
  2 :two)
;; => nil
```

Now that you've seen that `search` can match against different clauses, here's an example that shows `search` matching against the pattern of a single clause multiple times.

```clojure
(m/search [1 2 1 3 1 5]
  [_ ... 1 ?x . _ ...] ?x)
;; => (2 3 5)
```

While this pattern looks complex, when we break it down you'll see that it's actually pretty simple.
In this case, we are looking for a vector, finding all the 1s in that vector, and assigning whatever appears after a 1 to the variable `?x`.
Syntactically, the `_ ...` is a wildcard match of any number of elements that are *not* a 1 (since the 1 comes next).
As described previously, `_` matches anything, and `...` says to repeat the match any number of times.
If you're familiar with Clojure regular expressions for string matching, `_ ...` is effectively the equivalent of `#".*"`.
Next, we have `1 ?x`.
This says to match a `1` followed by anything and to bind `?x` to the item following the `1`.
Finally, we have `. _ ...`.
As before, the `_ ...` portion matches anything else in the vector.
The leading `.` says to start the wildcard matching following the item bound to `?x` (it limits the scope of `...` to just the `_` instead of everything that came before it).

In this case, `?x` is bound to three items, one at a time, and the RHS expression is invoked once for each binding.
This is important, so let's say it again.
The logic variable `?x` is *not* bound to `(2 3 5)`, but rather once to `2` and then the RHS expression is evaluated, then to `3` and the RHS is evaluated a second time, and finally bound to `5` and the RHS expression is evaluated a third and final time.

If you want to see this explicitly, you can add a side effect to the expression.

```clojure
(m/search [1 2 1 3 1 5]
  [_ ... 1 ?x . _ ...] (do (println "Invoked" ?x)
                           ?x))
;; => (2 3 5)
;; and prints:
;; Invoked 2
;; Invoked 3
;; Invoked 5
```

All of that wildcarding can be intimidating, so Meander has given this pattern a simpler name: `m/scan`.
Here is the same thing using `scan`.

```clojure
(m/search [1 2 1 3 1 5]
  (m/scan 1 ?x) ?x)
;; => (2 3 5)
```

You can even scan multiple collections of things and find relationships between them using a logic variable in two or more positions.

```clojure
(m/search {:people [{:id 1 :name "Bob"} {:id 2 :name "Alice"}]    ; the input
           :addresses [{:type :business :person-id 1 :info ""}
                       {:type :other :person-id 1 :info ""}
                       {:type :business :person-id 2 :info ""}
                       {:type :vacation :person-id 2 :info ""}]}

  {:people (m/scan {:name ?name :id ?id})                         ; clause 1 pattern
   :addresses (m/scan {:person-id ?id :as ?address})}

  {:name ?name                                                    ; clause 1 expression
   :address ?address})

;; => ({:name "Bob", :address {:type :business, :person-id 1, :info ""}}
;;     {:name "Bob", :address {:type :other, :person-id 1, :info ""}}
;;     {:name "Alice", :address {:type :business, :person-id 2, :info ""}}
;;     {:name "Alice", :address {:type :vacation, :person-id 2, :info ""}})
```

Here, the first clause pattern will match a Clojure map containing *at least* two keys, `:people` and `:addresses` (there may be other keys present; see [Operator Overview](./operator-overview.md)).
The first `scan` will run through each item of the `:people` vector, matching each item to another map with `:name` and `:id` keys, binding the `?name` and `?id` logic variables to each value, in turn.
The second `scan` will run through each item of the `:addresses` vector, confirming that each element is a map with a `:person-id` key.
Since the same logic variable, `?id`, is used in this second scan, it will only match maps that have a `:person-id` value that corresponds to the current binding of `?id`.
This is similar to what you might have in a database join (e.g., SQL or Datalog).
If `?id` matches in the second `scan`, Meander binds the whole map item to `?address` and then invokes the RHS expression.
If `?id` doesn't match, then `scan` continues the search.

The RHS expression simply creates a new Clojure map with the `?name` and `?address` variables.
In the end, `search` returns a sequence containing four items.

From this relatively simple example, you can see that Meander is extremely powerful, giving you sophisticated tools to deconstruct your input data, perform internal joins, and then restructure your data any way you want.
Rather than getting caught up in a lot of complex code that tries to do this manually, Meander reduces the problem to one of just matching the input against a pattern, binding interesting data to variables, and then creating a return value using the corresponding RHS expression.

It's important to note that `search` may return results in a different order than you expect given the way you wrote the pattern.
For instance, consider the difference in behavior between these two examples.

```clojure
(m/search {:a [1 2] :z [:x :y]}
  {:a (m/scan ?a) :z (m/scan ?z)}
  [?a ?z])
;; => ([1 :x] [1 :y] [2 :x] [2 :y])

(m/search {:a [1 2] :b 2 :c 3 :d 4 :e 5 :f 6 :g 7 :h 8 :i 9 :j 10 :z [:x :y]}
  {:a (m/scan ?a) :b 2 :c 3 :d 4 :e 5 :f 6 :g 7 :h 8 :i 9 :j 10 :z (m/scan ?z)}
  [?a ?z])
;; => ([1 :x] [2 :x] [1 :y] [2 :y])
```

What's going on?

The `search` macro constructs its matching code in the order that Clojure stores the map items.
The difference in ordering is caused because Clojure doesn't guarantee the ordering of keys in maps.
While you may write the keys in a map literal in a certain order, Clojure won't necessarily preserve that order when it actually constructs the map.

```clojure
{:a 1 :z 26}
;; => {:a 1, :z 26}

(class {:a 1 :z 26})
;; => clojure.lang.PersistentArrayMap

{:a 1 :b 2 :c 3 :d 4 :e 5 :f 6 :g 7 :h 8 :i 9 :j 10 :z 26}
;; => {:e 5, :z 26, :g 7, :c 3, :j 10, :h 8, :b 2, :d 4, :f 6, :i 9, :a 1}

(class {:a 1 :b 2 :c 3 :d 4 :e 5 :f 6 :g 7 :h 8 :i 9 :j 10 :z 26})
;; => clojure.lang.PersistentHashMap
```

In the first case, the map is small and Clojure stores the values in a `clojure.lang.PersistentArrayMap`.
This preserves the order of the keys; the map entries are stored in an array in the order they appear in the program text.
As the size of the map grows (currently when the element count is nine or more in Clojure 1.10.1), Clojure stores the data in a `clojure.lang.PersistentHashMap` so it can perfom key lookups more efficiently.
But if you iterate through the keys in a `clojure.lang.PersistentHashMap` you don't necessarily get them in the order that you wrote them in the literal data.
Instead, they'll be ordered by the hash code of each of the keys.
So, this change of the ordering of the keys in the *pattern*, not the input, causes `search` to construct its search code using the `:z` `scan` first.
In general, this should not cause you a problem.
You get the same results in any case, but the order can be different than you expect, so don't rely on it.


## `find`

Sometimes, we want to be able to search for a match but we don't care about returning all the matches.
Instead we just need to know whether there's at least one match.
We could do this using Clojure functions:

```clojure
(first (m/search [1 2 1 3 1 5]
         (m/scan 1 ?x) ?x))
;; => 2
```

If the `search` is expensive and returns a lot of values, however, this may require more computation than we want and reduce performace, even when `search` returns a lazy seq.
Instead, we can use `find`.

Semantically, `find` is similar to `(first (m/search ...))`, but it terminates the search immediately after finding the first element and thus has better performance.

For example, perhaps we just want to find the first person with the name Bob.

```clojure
(m/find [{:name "Alice" :id 1} {:name "Bob" :id 2} {:name "Bob" :id 3}]
  (m/scan {:name "Bob" :as ?bobs-info})
  ?bobs-info)

;; => {:name "Bob", :id 2}
```

As with `match` and `search`, the RHS of `find` is just normal Clojure code.
But that's about to change.
Let's take a look at `rewrite` and `rewrites`.

## `rewrites` and `rewrite`

As you've seen, `match`, `search`, and `find` give us powerful pattern matching and data processing tools.
They also give us complete flexibility to transform our data using arbitrary Clojure code in the RHS expression.
Arbitrary Clojure code is arbitrarily powerful, but it can also get messy.

`rewrites` and `rewrite` are just like `search` and `find`, respectively, but instead of Clojure code in the RHS expression, they use Meander *substitutions*.

A substitution is basically the inverse of a pattern used to match the input.
Where a pattern deconstructs the input value, a substitution *constructs* a data value by filling in pieces of it using Meander variables.
Because of this, you can often achieve the same result as arbitrary Clojure code with a more declarative style and sometimes *dramatically* fewer lines of code.

We'll show an example shortly, but first we need to introduce Meander's memory variables.

```clojure
(m/match [1 2 3]
  [!xs ...]
  !xs)
;; => [1 2 3]
```

Memory variables are prefixed with an exclamation point and accumulate values into a vector every time they match.
Here, we're using the `match` macro, but memory variables work with `match`, `search`, `find`, `rewrites`, and `rewrite`.
The `!xs ...` construction says to match a series of values and to accumulate them into `!xs`.
Every time `!xs` matches another value, the value is accumulated into the `!xs` vector.
Because we're using `match`, the RHS expression is arbitrary Clojure code, which simply returns the value of `!xs`: `[1 2 3]`.

Now, let's take a look at another example using `rewrite`.

```clojure
(m/rewrite {:xs [1 2 3 4 5]     ; input
            :ys [6 7 8 9 10]}
  {:xs [!xs ...]                ; LHS pattern
   :ys [!ys ...]}
  [!xs ... . !ys ...])          ; RHS substitution

;; => [1 2 3 4 5 6 7 8 9 10]
```

In this case, we use memory variables named `!xs` and `!ys` and the zero-or-more repetition operator, `...`, to collect the values of the two vectors in the input map.
If we had used the `find` macro instead of `rewrite`, we might have created the output vector using `(vec (concat !xs !ys))` as the RHS expression.
Instead, we are using the `rewrite` macro and so we specify a substitution.
Here, the substitution is going to create a vector and the `!xs ...` and `!ys ...` portions specify that we're to take values out of the memory variables to populate the elements of the vector.
The `.` operator separates the two individual substitutions and limits the scope of the `...` operator.

Instead of inserting the values of `!xs` and `!ys` after each other, what if we needed to interleave them?
Meander makes that easy.

```clojure
(m/rewrite {:xs [1 2 3 4 5]
            :ys [6 7 8 9 10]}
  {:xs [!xs ...]
   :ys [!ys ...]}
  [!xs !ys ...])

;; => [1 6 2 7 3 8 4 9 5 10]
```

Simply remove the `... .` between `!xs` and `!ys`. Now, the remaining "..." operator applies to both memory variables as a pair.

Now, let's take a look at a more complex example.

```clojure
(m/rewrite {:name "entity1"
            :status :complete
            :history [{:value 100} {:value 300} {:value 700}]
            :future [{:value 1000} {:value 10000}]}
  {:name ?name
   :status ?status
   :history [{:value !values} ...]
   :future [{:value !values} ...]}
  [{:name ?name
    :status ?status
    :value !values} ...])

;; => [{:name "entity1", :status :complete, :value 100}
;;     {:name "entity1", :status :complete, :value 300}
;;     {:name "entity1", :status :complete, :value 700}
;;     {:name "entity1", :status :complete, :value 1000}
;;     {:name "entity1", :status :complete, :value 10000}]
```
This uses a combination of logic variables and memory variables.
The RHS expression generates a vector of maps.
The zero-or-more repetition operator, `...`, is used to generate mutiple maps.
Each time a map is generated, logic variables contribute the same values to the map, but memory variables distribute a new value in the same order that values were added to the memory variable originally.

In the next example, you can see how easy it is to use Meander to destructure a complex data value and then generate a Hiccup data structure which can then be used to generate HTML. This can be very useful in web applications.

```clojure
(m/rewrites {:name "entity1"
             :status :complete
             :history [{:value 100} {:value 300} {:value 700}]
             :future [{:value 1000} {:value 10000}]}
  {:name ?name
   :status ?status
   :history (m/scan {:value ?value})
   :future [{:value !values} ...]}
  [:div
   [:h3 ?name]
   [:strong (m/app name ?status) - ?value]
   [:ul .
    [:li !values] ...]])

;; => ([:div
;;      [:h3 "entity1"]
;;      [:strong "complete" - 100]
;;      [:ul [:li 1000] [:li 10000]]]
;;     [:div
;;      [:h3 "entity1"]
;;      [:strong "complete" - 300]
;;      [:ul [:li 1000] [:li 10000]]]
;;     [:div
;;      [:h3 "entity1"]
;;      [:strong "complete" - 700]
;;      [:ul [:li 1000] [:li 10000]]])
```

This is simply a data-to-data transformation.
Meander extracts data values in the LHS pattern and then reconstructs a new data value in the RHS substitution.

There is a lot more you can do with Meander's substitution capabilities. You can read about them in [Substitution](./substition.md).


## Conclusion

Meander provides five pattern matching macros that supply different functionality for specific circumstances:

  * `match` -- Finds a single match of the input against the list of patterns.
  * `search` -- Finds potentially multiple matches of the input against the list of patterns.
  * `find` -- Like `search` but only returns the first match.
  * `rewrites` -- Like `search`, potentially returning multiple values, but using Meander's substitution operations on the right-hand-side.
  * `rewrite` -- As `find` is to `search`, so `rewrite` is to `rewrites`. Returns the first match and uses Meander's substitution operations on the right-hand-side.

Meander makes it easy to create sophisticated data transformations with a minimum of coding.
Instead, the programmer specifies a pattern to deconstruct the input and then either a Clojure expression (`match`, `search`, and `find`) or a Meander substitution (`rewrites` and `rewrite`) to create one or more output values.
