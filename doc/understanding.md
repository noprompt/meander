#  Understanding Meander's Pattern Matching Macros

Meander has a few different macros that allow different capabilities for pattern matching. At first glance it might not be obvious which function you should use, in this article we will explore the differences between them and talk about when to use what.

## [**Match**]()

`meander.epsilon/match` is the bread and butter of Meander. `match` is what you might think about as standard 	pattern matching. It works in a similar way to match in core.match. Let's look at a few examples.

```clojure
(require '[meander.epsilon :as m])

(m/match [1 2 3]
  [_ ?y _] ?y)
;; => 2

(m/match [1 2 1]
  [?x ?y ?x] {:x ?x :y ?y}
  _ :no-match)
;; => {:x 1 :y 2}

(m/match {:my-stuff [1 2 3 4 5]}
  {:my-stuff [!xs ...]}
  (map inc !xs))
;; => [2 3 4 5 6]
```

`match` takes some input, and then pairs of a pattern to match on, called the left-hand-side (lhs), and the output if that pattern matches, called the right-hand-side (rhs). When there are multiple patterns, Meander checks them in a top down fashion. One other thing to note, that will only make complete sense later, is that the rhs of a match is just normal Clojure code. If our pattern matches, that code will be called as we can see in the last example above. Another important feature about  `match` is that it returns one result. Once `match` has matched on some data, it will stop and return it. This is often what we want, but some times we want to have multiple results, to do this we can use `meader.epsilon/search`.

## Search

Search is the first Meander feature you will encounter that goes beyond the way you typically think of pattern matching. Let's start with a simple example to see how search works.

```clojure
(m/search [1 2 1 3 1 5]
  [_ ... 1 ?x . _ ...] ?x)
;; => (2 3 5)
```

Here we are looking for a vector and finding all the 1s in that vector and assigning whatever appears after them to the variable `?x`.  There is some tricky syntax going on here, so let's explain it. First we have `_ ...` which is a wildcard match that will match anything followed by a zero or more repeat operator. After that we have `1 ?x` which just says find a one and store whatever is after it in the variable `?x`. Finally we have are last part `. _ ...`. What we are trying to say is that we there can be in other number of elements in the vector that can have any value. The wildcard repeat says this, but we need to tell the repeat where to stop. We use `.` to do that.

All of that is a bit of a mouthful and can get a little hard to read, so we've given this pattern a name `m/scan`. Here is the same thing using `scan`.

```clojure
(m/search [1 2 1 3 1 5]
  (m/scan 1 ?x) ?x)
;; => (2 3 5)
```

Using `scan` in a search is one of the ways we can get more than one result. You can even scan multiple collections of things and find relationships between them. Here we are scanning through all the people and then scanning through addresses to find those that match.

```clojure
(m/search {:people [{:id 1 :name "Bob"} {:id 2 :name "Alice"}]
           :addresses [{:type :business :person-id 1 :info ""}
                       {:type :other :person-id 1 :info ""}
                       {:type :business :person-id 2 :info ""}
                       {:type :vacation :person-id 2 :info ""}]}

  {:people (m/scan {:name ?name :id ?id})
   :addresses (m/scan {:person-id ?id :as ?address})}

  {:name ?name
   :address ?address})

;; =>

({:name "Bob", :address {:type :business, :person-id 1, :info ""}}
 {:name "Bob", :address {:type :other, :person-id 1, :info ""}}
 {:name "Alice", :address {:type :business, :person-id 2, :info ""}}
 {:name "Alice", :address {:type :vacation, :person-id 2, :info ""}})
```

Now of course, if we were doing this often, we'd probably want to store things in a better format. But, being able to do express this easily with meander is a very nice feature to have.

## Find

If instead of finding all matches of something, we only cared about finding one match we could use `find`. Perhaps we just want to find the first person with the name Bob. Then we could write this match.

```clojure
(m/find [{:name "Alice" :id 1} {:name "Bob" :id 2} {:name "Bob" :id 3}]
  (m/scan {:name "Bob" :as ?bobs-info})
  ?bobs-info)

;; =>

{:name "Bob", :id 2}
```

One thing to note is that just like match, the right hand sides of search and find are just normal Clojure code. To see why I am highlighting this fact, let's look at `rewrite`.

## Rewrite

So what makes rewrite different from match? Well, on our rhs we can use some of Meander's operators through a process called substitution.

```clojure
(m/rewrite {:xs [1 2 3 4 5]
            :ys [6 7 8 9 10]}
  {:xs [!xs ...]
   :ys [!ys ...]}
  [!xs ... . !ys ...])

;; =>
[1 2 3 4 5 6 7 8 9 10]
```

As you can see here we are able to use `...` and `.` on the rhs of our match. In general things on the rhs work the way you'd assume. In this case, we can extract all the values out of our memory variables by using `...`, allowing us to combine `!xs` and `!ys` together. Rewrite uses Meander [substitution](./substitution) on the rhs. So, we can't just directly run Clojure code, but we have a lot more flexibility in how we manipulate things.

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

;; =>

[{:name "entity1", :status :complete, :value 100}
 {:name "entity1", :status :complete, :value 300}
 {:name "entity1", :status :complete, :value 700}
 {:name "entity1", :status :complete, :value 1000}
 {:name "entity1", :status :complete, :value 10000}]
```

Here you can see how easy it is to use meander to unnest some values. This sort of thing is often needed when trying to build charts/graphs and can be complicated when trying to do the manipulations manually. Rewrite can come in handy in so many different ways. It can easily be used for generating html via hiccup or SQL via honeySQL.

## Rewrites

Rewrites is like rewrite, but with searches ability to return multiple values. Here is a fairly nonsensical example, but one that shows the sorts of things that are possible using rewrites.

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

;; =>

([:div
  [:h3 "entity1"]
  [:strong "complete" - 100]
  [:ul [:li 1000] [:li 10000]]]
 [:div
  [:h3 "entity1"]
  [:strong "complete" - 300]
  [:ul [:li 1000] [:li 10000]]]
 [:div
  [:h3 "entity1"]
  [:strong "complete" - 700]
  [:ul [:li 1000] [:li 10000]]])
```

## Conclusion

There are a few more things that exist in meander. But these are the 5 top level functions that you really need to know about in order to get most of your work done. Hopefully this overview helped you understand when to use which macro. If there are anything you are confused about feel free to file an issue or a PR with your suggestions/questions.
