(ns warmup.web
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.server.standalone :refer [serve]]
            [compojure.core :refer [defroutes routes ANY wrap-routes]]
            [compojure.route :as route]
            [glimpse.views :as glimpse]
            [glimpse.middleware :refer [wrap-glimpse]]
            [warmup.routes :refer [warmup-routes]])
  (:gen-class))

(defroutes app-routes
  (route/resources "/")
  (ANY "*" [:as {uri :uri}]
       (when-let [resp (glimpse/not-found uri)]
         (glimpse/render resp)))
  (route/not-found "Not Found"))

(def application (-> (routes warmup-routes app-routes)
                     (wrap-routes wrap-glimpse)
                     (wrap-defaults site-defaults)))

(defn -main []
  (let [port (Integer. (or (System/getenv "PORT") 8080))]
   (serve application {:port port})))
