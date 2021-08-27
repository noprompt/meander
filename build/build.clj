(ns build
  (:require [clojure.tools.build.api :as b]
            [clojure.java.shell :as shell]
            [clojure.string :as string]
            [meander.epsilon :as m]
            [deps-deploy.deps-deploy :as d]))

(defn git-branch-name
  "Attempts to get the current branch name via the shell."
  []
  (m/match (shell/sh "git" "rev-parse" "--abbrev-ref" "HEAD")
           {:exit 0, :out ?out}
           (string/trim ?out)

           ?result
           (throw (ex-info "Unable to compute branch name" ?result))))

(def git-commit-count-start
  "Starting SHA to count commits from."
  "6c9083a9e75a3ac451edd3514030d6baa7e04a9c")

(defn git-branch-commit-count
  "Attempts to get the current number of commits on the current branch
  via the shell."
  []
  (m/match (shell/sh "git" "rev-list" (str git-commit-count-start "...") "--count")
           {:exit 0, :out ?out}
           (string/trim ?out)

           ?result
           (throw (ex-info "Unable to compute commit count" ?result))))

(def lib (symbol "meander" (git-branch-name)))
(def basis (b/create-basis {:project "deps.edn"}))
(def version (format "0.0.%s" (git-branch-commit-count)))
(def class-dir "target/classes")
(def jar-file (format "target/%s-%s.jar" (git-branch-name) version))

(defn clean [_]
  (b/delete {:path "target"}))

(defn jar [_]
  (b/write-pom {:class-dir class-dir
                :lib       lib
                :version   version
                :basis     basis
                :src-dirs  ["src"]})
  (b/copy-dir {:src-dirs   ["src"]
               :target-dir class-dir})
  (b/jar {:class-dir class-dir
          :jar-file  jar-file}))

(defn deploy [args]
  (-> args
      (assoc  :artifact jar-file
              :pom-file (str class-dir "/META-INF/maven/noprompt/meander/pom.xml"))
      (d/deploy)))

(defn clojars [args]
  (-> {:installer :remote :sign-releases? true}
      (merge args)
      (deploy)))

(defn install [args]
  (-> {:installer :local}
      (merge args)
      (deploy)))