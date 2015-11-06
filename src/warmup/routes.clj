(ns warmup.routes
    (:require [compojure.core :refer :all]
              [glimpse.views :as glimpse]
              [warmup.twitter :as twitter]))

(defroutes warmup-routes
  (GET "/" [] {:tweet (map twitter/format-tweet (twitter/get-tweets "#programming"))})
  (GET "/tweets/show/:id" [id] {:tweet (twitter/format-tweet (twitter/get-tweet id))}))
