(ns lan-party-manager.db.entities
  (:use korma.core))

(declare game lan-party participant participants-at-party games-at-party)

(defentity lan-party
  (fields :date :doodle :name)
  (has-many games-at-party)
  (has-many participants-at-party))

(defentity game
  (fields :name))

(defentity participant
  (fields :name :email))

(defentity games-at-party
  (has-one game)
  (has-one lan-party))

(defentity participants-at-party
  (has-one game)
  (has-one lan-party))

