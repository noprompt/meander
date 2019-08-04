(ns make-defproject
  (:require
   [clojure.java.shell :as shell]
   [clojure.string :as string]
   [meander.match.delta :as r.match :include-macros true]
   [meander.strategy.delta :as r]
   [meander.syntax.delta :as r.syntax :include-macros true]))


(defn git-branch-name
  "Attempts to get the current branch name via the shell."
  []
  (r.match/match (shell/sh "git" "rev-parse" "--abbrev-ref" "HEAD")
    {:exit 0, :out ?out}
    [:okay (string/trim ?out)]

    ?result
    [:error (ex-info "Unable to compute branch name"  ?result)]))

(def git-commit-count-start
  "Starting SHA to count commits from."
  "d9a5cc3d2a26bc5552c7bd0631847872071fbd5b")

(defn git-branch-commit-count
  "Attempts to get the current number of commits on the current branch
  via the shell."
  []
  (r.match/match (shell/sh "git" "rev-list" (str git-commit-count-start "...") "--count")
    {:exit 0, :out ?out}
    [:okay (string/trim ?out)]

    ?result
    [:error (ex-info "Unable to compute commit count" ?result)]))

(defn -main
  "Creates and writes the project.clj file for this project."
  [& args]
  (r.match/match [(git-branch-name) (git-branch-commit-count)]
    [[:error ?error] _]
    (throw ?error)

    [_ [:error ?error]]
    (throw ?error)

    [[:okay ?branch-name] [:okay ?branch-commit-count]]
    (let [?project-name (symbol "meander" ?branch-name)
          ?project-version (format "0.0.%s" ?branch-commit-count)
          deps-edn (read-string (slurp "deps.edn"))]
      ((r/pipe
        (r/tuple (r/match
                   {:paths ?paths}
                   ?paths)
                 (r/search
                   {:deps {?dep {:mvn/version ?version}}}
                   [?dep ?version]))
        (r/rewrite
         [?paths ?deps]
         (defproject ?project-name ?project-version
           :description "Data transformation library combining higher order functional programming with concepts from term rewriting."
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
    :description "Data transformation library combining higher order functional programming with concepts from term rewriting."
    :url "https://github.com/noprompt/meander"
    :license {:name "MIT",
              :url "https://opensource.org/licenses/MIT"}
    :paths ["src"]
    :dependencies ([org.clojure/test.check "0.10.0-alpha3"]
                   [org.clojure/clojurescript "1.10.439"]
                   [org.clojure/clojure "1.9.0"])))
