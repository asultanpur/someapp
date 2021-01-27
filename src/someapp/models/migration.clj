(ns someapp.models.migration
  (:require [clojure.java.jdbc :as sql]
            [someapp.models.s :as s]))

(defn migrated? []
  (-> (sql/query s/spec
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='ss'")])
      first :count pos?))

(defn migrate []
  (when (not (migrated?))
    (print "Creating database structure...") (flush)
    (sql/db-do-commands s/spec
                        (sql/create-table-ddl
                         :ss
                         [:id :serial "PRIMARY KEY"]
                         [:body :varchar "NOT NULL"]
                         [:created_at :timestamp
                          "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
    (println " done")))

