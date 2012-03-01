(ns lan-party-manager.db.connection
  (:use korma.db))

(def db {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "database.db"})

(defdb sqlite-db
  db)