#!/usr/bin/env bash

echo 'Running Clojure tests'
clojure -A:test

echo 'Running ClojureScript tests'
clojure -A:cljs-test
