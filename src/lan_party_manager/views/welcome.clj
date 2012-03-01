(ns lan-party-manager.views.welcome
  (:require [lan-party-manager.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to lan-party-manager"]))
