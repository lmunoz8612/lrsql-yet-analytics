(ns lrsql.postgres.main
  (:require [com.stuartsierra.component :as component]
            [lrsql.system :as system]
            [lrsql.postgres.record :as pr])
  (:gen-class))

(def postgres-backend (pr/map->PostgresBackend {}))

(defn run-test-postgres
  "Run a Postgres-backed LRSQL instance based on the `:test-postgres`
   config profile. For use with `clojure -X:db-postgres`."
  [_] ; Need to pass in a map for -X
  (component/start (system/system postgres-backend :test-postgres)))

(defn -main [& _args]
  (-> (system/system postgres-backend :prod-postgres)
      component/start))
