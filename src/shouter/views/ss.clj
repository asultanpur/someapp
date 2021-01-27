(ns someapp.views.ss
  (:require [someapp.views.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]
            [ring.util.anti-forgery :as anti-forgery]))

(defn s-form []
  [:div {:id "s-form" :class "sixteen columns alpha omega"}
   (form/form-to [:post "/"]
                 (anti-forgery/anti-forgery-field)
                 (form/label "s" "SAY SOMETHING")
                 (form/text-area "s")
                 (form/submit-button "SAY"))])

(defn display-ss [ss]
  [:div {:class "ss sixteen columns alpha omega"}
   (map
    (fn [s] [:h2 {:class "s"} (h (:body s))])
    ss)])

(defn index [ss]
  (layout/common "SOMEAPP"
                 (s-form)
                 [:div {:class "clear"}]
                 (display-ss ss)))
