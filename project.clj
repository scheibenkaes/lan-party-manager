(defproject lan-party-manager "0.1.0-SNAPSHOT"
            :description "Manage upcoming LAN party events"
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [noir "1.2.1"]
                           [korma "0.2.1"]
                           [org.xerial/sqlite-jdbc "3.7.2"]
                           [lobos "1.0.0-SNAPSHOT"]]
            :dev-dependencies [[lein-vim "1.0.2-SNAPSHOT"]]
            :main lan-party-manager.server)

