(defproject meander "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :profiles {:dev {:dependencies [[org.clojure/math.combinatorics "0.1.4"]
                                  [org.clojure/tools.nrepl "0.2.10"]
                                  [org.clojure/test.check "0.10.0-alpha2"]
                                  [com.taoensso/tufte "1.1.2"]
                                  [fipp "0.6.10"]]
                   :source-paths ["src" "dev"]}})
