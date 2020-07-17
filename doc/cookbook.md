Please add your own tips and tricks! You can edit this file from Github by clicking then pencil icon in the top right of the file view.

---


## Common Patterns

### Capture Variable From Pattern Match
- How to capture a variable from a pattern match? Use `:as`
    ```clojure
    (def a_opprmdefblk [(ophirPrmDef :inBoneTrk ctidChannel #{:EArg-In})
                        (ophirPrmDef :inoutBoneTrk ctidChannel #{:EArg-In :EArg-Out})
                        (ophirPrmDef :outBoneTrk ctidChannel #{:EArg-Out})])
    (m/search
     a_opprmdefblk
     [(m/or {:argFlags #{:EArg-Out} :as !argOut}
            {:argFlags #{:EArg-In} :as !argIn})
      ...]
     {:opmirArgIn !argIn
      :opmirArgOut !argOut})
    ```


### Sequence Transformation

- Desired Result
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
  - Input: a tokenized string
  - Make sure tokens match order pattern (`nstoken xseg {xseg}` )
  - Transform each of the tokens based on the token
    ```clojure
    nstoken =>
      case "obj": :objstore
      default: (keyword nstoken)
    ```

- **Normal Clojure**
  ```clojure
  (defn initOppath-clj [axpath]
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

- **Meander**   
    - **Naive attempt**: 
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
        ```

    - **Second Attempt:** Better but a nitpick is the functional transformation is on the pattern matching clause where conceptually feels like it should go in the generation part
        ```clojure
        (defn initOppath-m2 [axpath]
              (m/match (str/split axpath #"[/:]")
                (m/with [%segattr (m/pred #(= (first %1) \@)    (m/app #(->OppathSeg :seg-attr %1) !seg))
                         %segobj  (m/pred #(not= (first %1) \@) (m/app #(->OppathSeg :seg-chld %1) !seg))]
                        [(m/re #"obj|oppas|dc" ?ns)
                         . (m/or %segobj %segattr) ...])
                {:ns (keyword ?ns) :xsegs !seg}))        
        ```

    - **Cleaner Solution** Use a helper to construct the xseg:

        ```clojure
        (defn make-xseg [val]
          (m/rewrite val
            (m/re #"@.*" ?val)
            {:kind :seg-attr :val ?val}

            (m/re #"[^@].*" ?val)
            {:kind :seg-chld :val ?val}

            ?val
            {:kind :unknown :val ?val}))


        (m/rewrite ["oppas" "obj1" "@attr1" "@attr2" "obj2"]
          [(m/re #"obj|oppas|dc" ?ns) . !segs ...]
          {:ns (m/keyword ?ns)
           :xsegs [(m/app make-xseg !segs) ...]})
        ;; =>
        {:ns :oppas,
         :xsegs
         [{:kind :seg-chld, :val "obj1"}
          {:kind :seg-attr, :val "@attr1"}
          {:kind :seg-attr, :val "@attr2"}
          {:kind :seg-chld, :val "obj2"}]}
        ```

    - **Concise Using Recursion**: The second uses `m/cata` on the left or right side:
        - Left side
            ```clojure
            (m/rewrite ["oppas" "obj1" "@attr1" "@attr2" "obj2"]
              [(m/re #"obj|oppas|dc" ?ns) . (m/cata !segs) ...]
              {:ns (m/keyword ?ns)
               :xsegs [!segs ...]}

              (m/re #"@.*" ?val)
              {:kind :seg-attr :val ?val}

              (m/re #"[^@].*" ?val)
              {:kind :seg-chld :val ?val}

              ?val
              {:kind :unknown :val ?val})
            ```

        - Right side
            ```clojure
            (m/rewrite ["oppas" "obj1" "@attr1" "@attr2" "obj2"]
              [(m/re #"obj|oppas|dc" ?ns) . !segs ...]
              {:ns (m/keyword ?ns)
               :xsegs [(m/cata !segs) ...]}

              (m/re #"@.*" ?val)
              {:kind :seg-attr :val ?val}

              (m/re #"[^@].*" ?val)
              {:kind :seg-chld :val ?val}

              ?val
              {:kind :unknown :val ?val})
            ```

    - **Final Solution:** Cata on the right side can be used to construct a value to be recursively rewritten. It’s the dual of the left.
        ```clojure
        (m/rewrite ["oppas" "obj1" "@attr1" "@attr2" "obj2"]
          [(m/re #"obj|oppas|dc" ?ns) . !segs ...]
          {:ns (m/keyword ?ns)
           :xsegs [(m/cata ($EXAMPLE !segs)) ...]}

          ($EXAMPLE (m/re #"@.*" ?val))
          {:kind :seg-attr :val ?val}

          ($EXAMPLE (m/re #"[^@].*" ?val))

          {:kind :seg-chld :val ?val}

          ($EXAMPLE ?val)
          {:kind :unknown :val ?val})
        ;; =>
        {:ns :oppas,
         :xsegs
         [{:kind :seg-chld, :val "obj1"}
          {:kind :seg-attr, :val "@attr1"}
          {:kind :seg-attr, :val "@attr2"}
          {:kind :seg-chld, :val "obj2"}]}
        ```

### Split stream based on filter and project   (1-to-many)
- Pseudo code:
    ```clojure
    filter(
      (predA? x) => (projA x) :as !projAseq
      (predB? x) => (projB x) :as !projBseq
    )
    ```

- Clojure Code
    ```clojure        
    ;; Test Data
    (def arglist [{:name :inBoneTrk    :argFlags #{:EArg-In}}
                  {:name :inoutBoneTrk :argFlags #{:EArg-In :EArg-Out}}
                  {:name :outBoneTrk   :argFlags #{:EArg-Out}}])
    ;; Using match
    (m/match
     arglist
      [(m/or {:argFlags #{:EArg-Out} :as !argOut}
             {:argFlags #{:EArg-In} :as !argIn})
       ...]
      {:opmirArgIn !argIn
       :opmirArgOut !argOut})
    ;; =>
    {:opmirArgIn  [{:name     :inBoneTrk
                    :argFlags #{:EArg-In}}]
     :opmirArgOut [{:name     :inoutBoneTrk
                    :argFlags #{:EArg-Out :EArg-In}} 
                   {:name     :outBoneTrk
                    :argFlags #{:EArg-Out}}]}

    ```

- Now let's use m/search to see the difference
    ```clojure
    (m/search
     arglist
     [(m/or {:argFlags #{:EArg-Out} :as !argOut}
            {:argFlags #{:EArg-In} :as !argIn})
      ...]
     {:opmirArgIn !argIn
      :opmirArgOut !argOut})
    ;; =>
    ({:opmirArgIn [{:name :inBoneTrk, :argFlags #{:EArg-In}}]
      :opmirArgOut [{:name :inoutBoneTrk, :argFlags #{:EArg-Out :EArg-In}} 
                    {:name :outBoneTrk, :argFlags #{:EArg-Out}}]}
     {:opmirArgIn [{:name :inBoneTrk, :argFlags #{:EArg-In}} 
                   {:name :inoutBoneTrk, :argFlags #{:EArg-Out :EArg-In}}]
      :opmirArgOut [{:name :outBoneTrk, :argFlags #{:EArg-Out}}]})
    ```

- Now let's look using m/scan
    ```clojure
    (m/search
     arglist
     (m/scan {:argFlags #{:EArg-In} :as ?argIn})
     ?argIn)
    ;; =>
    ({:name     :inBoneTrk
      :argFlags #{:EArg-In}} 
     {:name     :inoutBoneTrk
      :argFlags #{:EArg-Out :EArg-In}})
    ```

- Now let's look at m/scan with a memory variable
    ```clojure
    (m/search
     arglist
     (m/scan {:argFlags #{:EArg-In} :as !argIn})
     !argIn)
    ;; =>
    ([{:name     :inBoneTrk
       :argFlags #{:EArg-In}}]
     [{:name     :inoutBoneTrk
       :argFlags #{:EArg-Out :EArg-In}}])
    ```

---

## TODO  
- How to do EBNF like production rules.  Ex: 
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

