(ns lan-party-manager.models.party
  (:use somnium.congomongo))

(def lan-party-required-keys #{:name :games :date :players})

(def game-required-keys #{:name :votes})

(defn validate [obj reqs]
  (when-let [ks (keys obj)] (every? reqs ks)))

(defn id->str [obj]
  (update-in obj [:_id] str))

(defn all-lans []
  (map id->str (fetch :lans)))

(defn by-id [id]
  (id->str (fetch-by-id :lans (object-id id))))

(defn all-proposed-games []
  (->> (fetch :lans :only [:games]) (map :games) (apply concat) (map id->str) set))

(def votemaps :votemaps)

(defn upvote-game
  "increment the value in the votemap"
  [lan-id game]
  (fetch-and-modify votemaps {:lan (object-id lan-id)} {:$inc {(str "map." game) 1}}))

(defn create-vote-map
  "create a new vote map for the given lan"
  [{lan-id :_id}]
  (insert! votemaps {:lan lan-id :map {}}))

(defn save-lan [{:keys [name games players] :as party}]
  (when-not (validate party lan-party-required-keys)
    (throw (Exception.
            (format "Validation failed. Provide all fields (%s) needed for a lan party" lan-party-required-keys))))
  (-> (insert! :lans party)
      create-vote-map))