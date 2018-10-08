(defproject meander/meander.alpha "0.0.0-SNAPSHOT"
  :description "Data transformation library combining higher order functional programming with concepts from term rewriting."
  :url "https://github.com/noprompt/meander"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :profiles {:dev {:dependencies [[org.clojure/tools.nrepl "0.2.10"]
                                  [org.clojure/test.check "0.10.0-alpha2"]]
                   :source-paths ["src"]}})
