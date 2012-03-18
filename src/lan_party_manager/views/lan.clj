(ns lan-party-manager.views.lan
  (:use [lan-party-manager.views.common :only [layout]])
  (:require [lan-party-manager.models.party :as party])
  (:require [noir.response :as response])
  (:use [net.cgrand.enlive-html :only [deftemplate]])
  (:use [noir.core :only [defpage]]))

(defn layout-lans [ls]
  (map (fn [{:keys [doodle name _id proposed-games]}]
         [:div [:h3 [:a {:href  (str "/lans/" _id)} (str name)]]]) ls))

(defpage [:post "/addgame/:lan"] {:keys [lan game]}
  (-> 
   (if (party/add-game lan game)
     "Neues Spiel, neues Glück"
     "No way Jose")
   response/json))

(defpage "/votemap/:id" {id :id}
  (response/json (party/get-vote-map id)))

(defpage [:post "/upvote/:lan/:game"] {:keys [lan game]}
  (response/json (party/upvote-game lan game)))

(defpage "/lans/:id" {:keys [id]}
  (response/json (party/by-id id)))

(defpage "/lans" []
  (response/json (party/all-lans)))

(deftemplate index-template "public/index.html" []
  )

(defpage "/" []
  (index-template))
