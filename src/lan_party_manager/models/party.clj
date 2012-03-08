(ns lan-party-manager.models.party
  (:use somnium.congomongo))

(def lan-party-required-keys #{:name})

(defstruct lan-party
  :name
  :doodle
  :proposed-games)

(defn id->str [obj]
  (update-in obj [:_id] str))

(defn all-lans []
  (map id->str (fetch :lans)))

(defn by-id [id]
  (id->str (fetch-by-id :lans (object-id id))))

(defn all-proposed-games []
  (->> (fetch :lans :only [:proposed-games]) (map :proposed-games) (apply concat) (map id->str) set))

(defn save-lan [{:keys [name proposed-games players] :as party}]
  ; TODO validate
  (insert! :lans party))