(ns warmup.twitter
  (:require [camel-snake-kebab.core :refer :all]
            [camel-snake-kebab.extras :refer [transform-keys]]
            [environ.core :refer [env]]
            [twitter.api.restful :as rest]
            [twitter.api.search :refer [search]]
            [twitter.callbacks.handlers :refer :all]
            [twitter.oauth :refer [make-oauth-creds]])
  (:import [twitter.callbacks.protocols SyncSingleCallback]))

(def callback (SyncSingleCallback. response-return-body
                                   response-throw-error
                                   exception-rethrow))

(def credentials (make-oauth-creds (env :consumer-key)
                                   (env :consumer-secret)
                                   (env :access-token)
                                   (env :access-token-secret)))

(defn format-tweet [tweet]
  (-> (transform-keys ->kebab-case-keyword tweet)
      (assoc-in [:user :profile-image :src] (get-in tweet [:user :profile_image_url_https]))
      (assoc-in [:link :href] (str "/tweets/show/" (:id_str tweet)))
      (assoc-in [:twitter-link :href] (str "https://twitter.com/statuses/" (:id_str tweet)))))

(defn get-tweets [hashtag]
  (:statuses (search :oauth-creds credentials
                     :params {:q hashtag}
                     :callbacks callback)))

(defn get-tweet [id]
  (rest/statuses-show-id :oauth-creds credentials
                         :params {:id id}
                         :callbacks callback))
