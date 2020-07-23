# Meander<sup>ε</sup>

Meander is a Clojure/ClojureScript library that empowers you to write transparent data transformation code that allows you to plainly see the input and output of these transformations.

The latest version of the library can be found at the following link.

[![Clojars Project](https://img.shields.io/clojars/v/meander/epsilon.svg)](https://clojars.org/meander/epsilon)

## What can Meander do?

```clojure
(require '[meander.epsilon :as m])


(defn favorite-food-info [foods-by-name user]
  (m/match {:user user
            :foods-by-name foods-by-name}
    {:user
     {:name ?name
      :favorite-food {:name ?food}}
     :foods-by-name {?food {:popularity ?popularity
                            :calories ?calories}}}
    {:name ?name
     :favorite {:food ?food
                :popularity ?popularity
                :calories ?calories}}))
```

Meander's `match` macro allows us to pattern match on a data structure and return the answer that matches our pattern.
We use logic variables (symbols that start with `?`) to extract values from our input and return them in our output.
Logic variables also let us join across values.
In this example, we do that using the `?food` variable to lookup our users favorite foods in the `foods-by-name` collection.
Here is an example of running this match with some test data `foods-by-name`.

```clojure
(def foods-by-name
  {:nachos {:popularity :high
            :calories :lots}
   :smoothie {:popularity :high
              :calories :less}})

(favorite-food-info foods-by-name
  {:name :alice
   :favorite-food {:name :nachos}})
;; =>
{:name :alice
 :favorite {:food :nachos
            :popularity :high
            :calories :lots}}
```

### Finding More than One Answer

What if instead of a user having one favorite food, they had many?
And suppose we wanted to return the information for all of them.
The `search` macro performs exactly this job.

```clojure
(defn favorite-foods-info [foods-by-name user]
  (m/search {:user user
             :foods-by-name foods-by-name}
    {:user
     {:name ?name
      :favorite-foods (m/scan {:name ?food})}
     :foods-by-name {?food {:popularity ?popularity
                            :calories ?calories}}}
    {:name ?name
     :favorite {:food ?food
                :popularity ?popularity
                :calories ?calories}}))
```

There is actually very little that is different here.
Some names have been pluralized, line 2 changed to use `search` instead of `match`, and on line 6 added we're using the `scan` *pattern matching operator*.
That is all we need to find all of a users favorite foods and look up the information about them.
Lets try it with our test data from the previous example.

```clojure
(favorite-foods-info foods-by-name
 {:name :alice
  :favorite-foods [{:name :nachos}
                   {:name :smoothie}]})
;; =>
({:name :alice
  :favorite {:food :nachos
             :popularity :high
             :calories :lots}}
 {:name :alice
  :favorite {:food :smoothie
             :popularity :high
             :calories :less}})
```


### Remembering Values

Let's shift gears. What if a user has all sorts of different foods scattered through out their information and we want to collect them all?
Here we can use *memory variables*.

```clojure
(defn grab-all-foods [user]
  (m/find user
    {:favorite-foods [{:name !foods} ...]
     :special-food !food
     :recipes [{:title !foods} ...]
     :meal-plan {:breakfast [{:food !foods} ...]
                 :lunch [{:food !foods} ...]
                 :dinner [{:food !foods} ...]}}
    !foods))
```

This code example is a little contrived, but it does immediately show you how you can grab values from all sorts of places in your data structure.
This combination of a `!memory-variable` and the *zero or more* postfix operator `...` is a fairly common one.
Using them together allows you to gather up many values.
If you want to ensure that a certain number of elements exist you can also use the *n or more* postfix operator i.e. `..2`.

### Conditional Matches

Stepping away from food examples, we can see a few of Meanders more traditional pattern matching abilities.
Imagine that we have coordinates that can either include be `[x y]` or `[x y z]` and we want a pattern to that extracts `y`.

```clojure
(def point [1 2])

(m/find point
  [?x ?y] ?y
  [?x ?y ?z] ?y)
;; => 2
```

Here we used `find` to check against multiple patterns.
Meander checks this in a top to bottom ordering.
One thing to note is that since we didn't use `?x` or `?z` we could have replaced them with a wildcard match, a simple symbol which starts with `_`.
The above pattern accomplishes the task, but imagine that for some reason people keep passing things that aren't numbers into our match, so we want to restrict our matches to only numbers.

```clojure
(m/find point
  [(m/pred number?) (m/pred number? ?y)]
  ?y

  [(m/pred number?) (m/pred number? ?y) (m/pred number?)]
  ?y)
```

This ensures things that aren't number fail to match, but is a little verbose.
Honestly, that isn't a problem.
Length is not the measure of good code, clarity is.
But just to see how Meander allows us to build our own extensions let's look at how we can shorten things up.

```clojure
(m/defsyntax number
  ([] `(number _#))
  ([pattern]
    (if (m/match-syntax? &env)
      `(m/pred number? ~pattern)
      &form)))

(m/find point
  [(number) (number ?y)]
  ?y

  [(number) (number ?y) (number)]
  ?y)
```

Here we use `defsyntax` to essentially build our own macros for Meander.

To learn more check out the [Syntax Extensions](doc/defsyntax.md) article.

### Gaining Control

Sometimes we have a multistep process where we want to transform nested data in place.
This is an area that Meander continues to explore, but one powerful way of solving this problem is using Meander's strategies.

```clojure
(require '[meander.strategy.epsilon :as m*])

(def eliminate-zeros
  (m*/rewrite
   (+ ?x 0) ?x
   (+ 0 ?x) ?x))

(def eliminate-all-zeros
  (m*/bottom-up
   (m*/attempt eliminate-zeros)))

(eliminate-all-zeros '(+ (+ 0 (+ 0 (+ 3 (+ 2 0)))) 0))
;; => (+ 3 2)
```

Using our strategies we can make rewrite rules and then say how they ought to be applied.
Here we use the attempt strategy, which just says if the match fails, then return whatever was passed in.
And the bottom up strategies which applies our match to the most deep value, and replaces values that match all the way up the tree.
To learn more checkout how to apply meander [strategies](doc/strategies.md)

## Documentation

* [Concepts and API documentation](https://cljdoc.org/d/meander/epsilon/) covers the concepts, operators and primitives of Meander.
* [Cookbook](doc/cookbook.md) has commonly encountered situations and solution snippets. Contributions welcome.
* [Examples](examples/) contains larger examples.
* [Tests](test/meander) are a good way to see the detailed behaviors of primitives.

### Blog Posts

* [Meander: The answer to map fatigue](http://timothypratley.blogspot.com/2019/01/meander-answer-to-map-fatigue.html)
* [Meander for Practical Data Transformation](https://jimmyhmiller.github.io/meander-practical)
* [Introduction to Term Rewriting with Meander](https://jimmyhmiller.github.io/meander-rewriting)
* [Building Meander in Meander](https://jimmyhmiller.github.io/building-meander-in-meander)

### Talks

* [Strangeloop 2019](https://www.youtube.com/watch?v=9fhnJpCgtUw)

## Community

If you have any questions don't hesitate to ask them in [Clojurians Slack #meander](https://app.slack.com/client/T03RZGPFR/CFFTD7R6Z) or file an issue for this project in Github.
We are happy to help.

## Project Status

Meander is a young, active, and ambitious project.
Unless there is a reason to surrender, the project will continue to be regularly improved.

### Release schedule

Prompt releases can be expected when bugs are fixed or there are significant performance enchancements.

### Versioning semantics

This project uses an unorthodox method of versioning in that *any* change that could break compatibility with the current `meander/artifactID` must occur at a new `meander/artifactID`.
For instance, a change in syntax could cause matches to now fail.
In this case we do not want a new version of the software i.e. we do not wish to go from `0.0.N` to `1.N.N` etc.
Instead we create a new `meander/artifactID` update the namespaces accordingly and continue to progress from there.
This might sound strange at first but this method allows the project to progress in a way that is much more free than a traditional approach with semantic versioning with the following advantages:

1. A version of a `meander/artifactID` will always be safe to uprade.
1. When a new `meander/artifactID` is created you can depend on both without conflict when or if you decide to transition.
1. It promotes project growth because it gives the library the ability grow and change without the fear of breaking users.

Like anything, there are drawbacks to this approach, however, as the project matures it should stabilize and be more like a "regular" project.
