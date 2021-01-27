(ns someapp.controllers.ss
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [someapp.views.ss :as view]
            [someapp.models.s :as model]))

(defn index []
  (view/index (model/all)))

(defn create
  [s]
  (when-not (or (str/blank? s)
                (> (count s) 512))
    (model/create s))
  (ring/redirect "/"))

(defroutes routes
  (GET  "/" [] (index))
  (POST "/" [s] (create s)))
