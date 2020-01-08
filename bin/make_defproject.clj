(ns make-defproject
  (:require
   [clojure.java.shell :as shell]
   [clojure.string :as string]
   [meander.epsilon :as m]
   [meander.strategy.epsilon :as m*]))


(defn git-branch-name
  "Attempts to get the current branch name via the shell."
  []
  (m/match (shell/sh "git" "rev-parse" "--abbrev-ref" "HEAD")
    {:exit 0, :out ?out}
    [:okay (string/trim ?out)]

    ?result
    [:error (ex-info "Unable to compute branch name"  ?result)]))

(def git-commit-count-start
  "Starting SHA to count commits from."
  "6c9083a9e75a3ac451edd3514030d6baa7e04a9c")

(defn git-branch-commit-count
  "Attempts to get the current number of commits on the current branch
  via the shell."
  []
  (m/match (shell/sh "git" "rev-list" (str git-commit-count-start "...") "--count")
    {:exit 0, :out ?out}
    [:okay (string/trim ?out)]

    ?result
    [:error (ex-info "Unable to compute commit count" ?result)]))

(defn -main
  "Creates and writes the project.clj file for this project."
  [& args]
  (m/match [(git-branch-name) (git-branch-commit-count)]
    [[:error ?error] _]
    (throw ?error)

    [_ [:error ?error]]
    (throw ?error)

    [[:okay ?branch-name] [:okay ?branch-commit-count]]
    (let [?project-name (symbol "meander" ?branch-name)
          ?project-version (format "0.0.%s" ?branch-commit-count)
          deps-edn (read-string (slurp "deps.edn"))]
      ((m*/pipe
        (m*/tuple (m*/match
                    {:paths ?paths}
                    ?paths)
                  (m*/search
                    {:deps {?dep {:mvn/version ?version}}}
                    [?dep ?version]))
        (m*/rewrite
         [?paths ?deps]
         (defproject ?project-name ?project-version
           :description "A library that enables transparent data manipulation through pattern matching, substitution, and rewriting."
           :url "https://github.com/noprompt/meander"
           :license {:name "MIT"
                     :url "https://opensource.org/licenses/MIT"}
           :paths ?paths
           :dependencies ?deps))
        (fn [project-file]
          (spit "project.clj" project-file)))
       deps-edn)
      (System/exit 0))))

(comment
  (-main)
  ;; => Writes something like
  (defproject meander/beta "0.0.496"
    :description "A library that enables transparent data manipulation through pattern matching, substitution, and rewriting."
    :url "https://github.com/noprompt/meander"
    :license {:name "MIT",
              :url "https://opensource.org/licenses/MIT"}
    :paths ["src"]
    :dependencies ([org.clojure/test.check "0.10.0-alpha3"]
                   [org.clojure/clojurescript "1.10.439"]
                   [org.clojure/clojure "1.9.0"])))
