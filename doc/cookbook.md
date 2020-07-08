Please add your own tips and tricks! You can edit this file from Github by clicking then pencil icon in the top right of the file view.

---


## Common Patterns

### Sequence Transformation

- Example
  ```clojure
  ;EBNF
  ns <= "obj" | "oppas" | "dc"
  segattr <= ["/"] "@" alphanumeric
  segobj  <= ["/"] alphanumeric
  xpath   <= ns (segattr|segobj) {(segattr|segobj)}

  ; Input
  "obj:/myobj/mychild/@myattrib"
  ;; Result =>
  {:ns :obj,
  :xsegs
  ({:segkind :seg-chld, :segpath ""}
    {:segkind :seg-chld, :segpath "myobj"}
    {:segkind :seg-chld, :segpath "mychild"}
    {:segkind :seg-attr, :segpath "@myattrib"})}
  ```

- What this shows:
  - Make sure tokens match order pattern (`nstoken xseg {xseg}` )
  - Transform each of the tokens based on the token
    ```clojure
    nstoken =>
      case "obj": :objstore
      default: (keyword nstoken)
    ```
  - ***QUESTION**:* want to be able to express this as production rules in meander where we only specify the special cases and uses a default otherwise for *valid* patterns (e.g. nstoken can't be "fakeNs")

- **Old way**
  ```clojure
  (defn initPath-clj [axpath]
    (let [nsandpath (str/split axpath #"[:]" 2)
          nsstr (first nsandpath)
          pathtokens (->
                      nsandpath
                      (nth 1)
                      (str/split #"[/]"))]
      {:ns (case nsstr
            "op"    :op
            "obj"   :obj
            "oppas" :oppas)
      :xsegs (map
              #(if (= (first %1) \@)
                  (->OppathSeg :seg-attr %1)
                  (->OppathSeg :seg-chld %1))
              pathtokens)}))
  ```

- **Meander hotness**   
  - ***QUESTION:  Is there a better way? I don't like that the functional transformation on the pattern matching clause where conceptually feels like it should go in the generation part of the clause***
    ```clojure
    (defn initOppath-m1 [axpath]
      (let [axptokens (str/split axpath #"[/:]")]
        {:ns (m/match (first axptokens)
               (m/and ?ns (m/or "op" "obj" "oppas"))
               (keyword ?ns))
         :xsegs (map
                 #(if (= (first %1) \@)
                    (->OppathSeg :seg-attr %1)
                    (->OppathSeg :seg-chld %1))
                 (rest axptokens))}))


    (defn initOppath-m2 [axpath]
      (m/match (str/split axpath #"[/:]")
        (m/with [%segattr (m/pred #(= (first %1) \@)    (m/app #(->OppathSeg :seg-attr %1) !seg))
                 %segobj  (m/pred #(not= (first %1) \@) (m/app #(->OppathSeg :seg-chld %1) !seg))]
                [(m/re #"obj|oppas|dc" ?ns)
                 . (m/or %segobj %segattr) ...])
        {:ns (keyword ?ns) :xsegs !seg}))
    ```

### Transform
- ***QUESTION:*** How to do EBNF like production rules.  Ex: 
    ```clojure
    token ::= (:arg-in|:arg-out) ?argname
    pseudocode-result:: (str (emit-in ?arg-attr)|emit-out :arg-attr) ?argname)
    
    ```
  



---

## Reuse subpatterns in other patterns

```clojure
(m/defsyntax ending-with [end]
  ['_ '... end])

(m/rewrite
  [1 2 3 4 5]
  (ending-with ?x)
  ?x)
;;=> 5

(m/rewrite
  [:a :b [1 2 3]]
  [:a :b (ending-with ?x)]
  ?x)
;;=> 3
```

## Self referential patterns

```clojure
(m/match {:pair [2 [3 [4 5]]]}
 (m/with [%pair [!as (m/or %pair !bs)]]
   {:pair %pair})
 [!as !bs])
;; => [[2 3 4] [5]]
```

## Tokenize a sequence (partitioning)

You can use `..!n` as a subsequence grouping facility, and `with` to define a recursive pattern.

```clojure
(m/rewrite [1 2 3 0 4 5 6 0 7 8 0 9]
  (m/with [%split (m/or [!xs ..!n 0 & %split]
                        [!xs ..!n])]
    %split)
  [[!xs ..!n] ...])
;; => [[1 2 3] [4 5 6] [7 8] [9]]
```

## Multiple variable length sub-sequences

You can use `m/cata` to recursively apply the same pattern for identifying a separator and subsequent values.
Here we group odd numbers after even numbers together.

```clojure
(m/rewrite [2 3 5 4 3 2]
  [] [] ; The base case for no values left
  [(m/pred even? ?x) . (m/pred odd? !ys) ... & ?more]
  [[?x [!ys ...]] & (m/cata ?more)])
;; => [[2 [3 5]] [4 [3]] [2 []]]
```

## Get all keys and values from a map

```clojure
(m/match {1 2 3 4 5 6}
  {& (m/seqable [!ks !vs] ...)}
  [!ks !vs])
;; => [[1 3 5] [2 4 6]]
```

## Webscrape HTML

Use a library like `hickory` to parse the HTML into data structures, then you can match either the DOM or hiccup.
`$` is a convenient way to search for matches in sub-trees.

```clojure
(m/search (fetch-as-hiccup company-directory-page)
  (m/$ [:div {:class "directory-tables"}
        . _ ...
        [:h3 _ ?department & _]])
  ?department)
```

## Recursion, reduction, and aggregation

Patterns can call themselves with the `m/cata` operator.
This is like recursion.
You can leverage self recursion to accumulate a result.

```clojure
(m/rewrite [() '(1 2 3)] ;; Initial state
  ;; Intermediate step with recursion
  [?current (?head & ?tail)]
  (m/cata [(?head & ?current) ?tail])

  ;; Done
  [?current ()]
  ?current)
;; => (3 2 1)
```

## Use the same value from a memory variable twice

When you have a match pattern that contains a memory varible `!n` and a substitution pattern where you want to make use of the variable in multiple ways, you can't do that directly because `[!n !n]` would take 2 different values out of `!n` instead of the same value twice. However, you can easily create two names for the same value in the search pattern with `(m/and !n !n2)` which will match a single value, but create 2 memory variables.

```clojure
(me/rewrite [[:a 1] [:b 2] [:c 3]]
  [[!k (me/and !n !n2)] ...]
  [[!k !n (me/app str !n2)] ...])
;; => [[:a 1 "1"] [:b 2 "2"] [:c 3 "3"]]
```

## Replace all occurrences

You can use `meander.strategy.epsilon/top-down` or `bottom-up` to find and replace.
```clojure
(def p
  (s/top-down
    (s/match
      (m/pred string? ?s) (keyword ?s)
      ?x ?x)))
(p [1 ["a" 2] "b" 3 "c"])
;; => [1 [:a 2] :b 3 :c]
```

## Optional values

Say you want to match a number that may be followed by a string, and then a keyword:

```clojure
[1 "this is fine" :foo]
[1 :foo]
```

Zeta will include a regex style `?` operator.

Prior to zeta there are 3 ways to handle optional values:

a) Write separate patterns:
```clojure
(m/match [1 "this is fine" :foo]
  [(m/pred number? ?n) (m/pred string? ?s) ?k]
  "first case!"
  [(m/pred number? ?n) (m/pred keyword? ?k)]
  "second case!")
;; => "first case!"
```

b) Use recursion:
```clojure
(m/match [1 "this is fine" :foo]
  [(m/pred number? ?n) & (m/with [%tail [(m/pred keyword? ?k)]]
                           (m/or [(m/pred string? ?s) & %tail]
                                 (m/and (m/let [?s nil])
                                        %tail)))]
  [?n ?s ?k])
;; => [1 "this is fine" :foo]
```

c) Constrain a memory variable to length <= 1:
```clojure
(m/match [1 "this is fine" :foo]
  (m/and
    [(m/pred number? ?n) . (m/pred string? !s) ..?sn (m/pred keyword? ?k)]
    (m/guard (<= ?sn 1)))
  [?n (first !s) ?k])
;; => [1 "this is fine" :foo]
```

