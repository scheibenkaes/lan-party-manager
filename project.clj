(defproject lan-party-manager "0.1.0-SNAPSHOT"
            :description "Manage upcoming LAN party events"
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [org.clojure/data.json "0.1.3"]
                           [noir "1.2.1"]
                           [congomongo "0.1.8"]
                           [enlive "1.0.0"]]
            :dev-dependencies [[lein-vim "1.0.2-SNAPSHOT"]]
            :main lan-party-manager.server)

