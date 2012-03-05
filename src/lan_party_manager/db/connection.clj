(ns lan-party-manager.db.connection
  [:use somnium.congomongo])

(def connection (make-connection "lan_manager"))

(defn init! []
  (set-connection! connection))

