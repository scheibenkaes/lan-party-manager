(ns lan-party-manager.server
  (:require [noir.server :as server]))

(server/load-views "src/lan_party_manager/views/")

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode mode
                        :ns 'lan-party-manager})))

