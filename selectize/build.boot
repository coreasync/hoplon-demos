#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.3.1"

(set-env!
  :dependencies (read-string (slurp "../deps.edn"))
  :src-paths    #{"src"}
  :out-path     "resources/public")

(add-sync! (get-env :out-path) #{"resources/assets"})

(require '[tailrecursion.hoplon.boot      :refer :all]
         '[tailrecursion.boot.core        :as    boot]
         '[tailrecursion.boot.task.ring   :refer [dev-server]]
         '[tailrecursion.boot.task.notify :refer [hear]])

(deftask development
  "Build foop for development."
  []
  (comp (watch) (hear) (hoplon {:prerender false}) (dev-server)))

(deftask production
  "Build foop for production."
  []
  (hoplon {:optimizations :advanced}))
