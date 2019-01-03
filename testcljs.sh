#!/bin/sh
clj -C:test -m cljs.main -re nashorn -i test/meander/strategy/alpha_test.cljc
clj -C:test -m cljs.main -re nashorn -i test/meander/match/alpha_test.cljc
