(ns lan-party-manager.models.party
  (:use [clojure.string :only [trim]])
  (:use [clojure.set :only [superset?]])
  (:use somnium.congomongo))

(def lan-party-required-keys #{:name :games :date :players})

(def game-required-keys #{:name :votes})

(defn validate [obj reqs]
  (when-let [ks (set (keys obj))]
    (superset? ks lan-party-required-keys)))

(defn id->str [obj]
  (update-in obj [:_id] str))

(defn all-lans []
  (map id->str (fetch :lans)))

(defn by-id [id]
  (id->str (fetch-by-id :lans (object-id id))))

(def votemaps :votemaps)

(defn upvote-game
  "increment the value in the votemap"
  [lan-id game]
  (fetch-and-modify votemaps {:lan (object-id lan-id)} {:$inc {(str "map." game) 1}} :as :json))

(defn create-vote-map
  "create a new vote map for the given lan"
  [{lan-id :_id}]
  (insert! votemaps {:lan lan-id :map {}}))

(defn get-vote-map [game-id]
  (let [g (-> (fetch-by-id :lans (object-id game-id)) :games)
        m* (into {} (for [g* g] [g* 0]))
        with-votes  (->> (fetch-one votemaps :where {:lan (object-id game-id)}) :map)]
    (sort-by second > (into [] (merge m* with-votes)))))

(defn save-lan [{:keys [name games players] :as party}]
  (when-not (validate party lan-party-required-keys)
    (throw (Exception.
            (format "Validation failed. Provide all fields (%s) needed for a lan party" lan-party-required-keys))))
  (-> (insert! :lans party)
      create-vote-map))

(defn add-game [lan game]
  (when (and lan (not-empty (trim game)) (>= (count game) 3))
    (when-not (-> (fetch-one :lans :where {:_id (object-id lan)}) :games set (contains? game))
      (fetch-and-modify :lans {:_id (object-id lan)} {:$push {:games game}}))))