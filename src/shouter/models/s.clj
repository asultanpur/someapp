(ns someapp.models.s
  (:require [clojure.java.jdbc :as sql]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/someapp"))

(defn all []
  (into [] (sql/query spec ["select * from ss order by id desc limit 128"])))

(defn create [s]
  (sql/insert! spec :shouts [:body] [s]))
