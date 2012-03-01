(ns lan-party-manager.db.migrations
  (:require [lobos.core :as lobos] 
            [lobos.connectivity :as lobos-connectivity]            
            [lobos.schema :as s]
            [lobos.migration :as mig])
  (:use [lan-party-manager.db.connection :only [db]]))

(mig/defmigration create-lan-party
  (up (lobos/create (s/table :lan_party
                             (s/integer :id :primary-key :auto-inc)
                             (s/date :date)
                             (s/text :doodle))))
  (down (lobos/drop (s/table :lan_party))))

(mig/defmigration create-game
  (up (lobos/create (s/table :game
                             (s/integer :id :primary-key :auto-inc)
                             (s/text :name))))
  (down (lobos/drop (s/table :game))))

(mig/defmigration create-participant
  (up (lobos/create (s/table :participant
                             (s/integer :id :primary-key :auto-inc)
                             (s/text :name :not-null)
                             (s/text :email))))
  (down (lobos/drop (s/table :participant))))

(mig/defmigration create-participants-at-party
  (up (lobos/create (s/table :participants_at_party
                             (s/integer :id :primary-key :auto-inc)
                             (s/integer :participant_id [:refer :participant :id])
                             (s/integer :game_id [:refer :game :id]))))
  (down (lobos/drop (s/table :participants_at_party))))

(mig/defmigration create-games-at-party
  (up (lobos/create (s/table :games_at_party
                             (s/integer :id :primary-key :auto-inc)
                             (s/integer :lan_party_id [:refer :lan_party :id])
                             (s/integer :game_id [:refer :game :id]))))
  (down (lobos/drop (s/table :games_at_party))))
