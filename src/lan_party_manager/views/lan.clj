(ns lan-party-manager.views.lan
  (:use [lan-party-manager.views.common :only [layout]])
  (:require [lan-party-manager.models.party :as party])
  (:require [noir.response :as response])
  (:use [noir.core :only [defpage]]))

(defn layout-lans [ls]
  (map (fn [{:keys [doodle name _id proposed-games]}]
         [:div [:h3 [:a {:href  (str "/lans/" _id)} (str name)]]]) ls))

(defpage "/lans/:id" {:keys [id]}
  (response/json (party/by-id id)))

(defpage "/lans" []
  (response/json (party/all-lans)))
