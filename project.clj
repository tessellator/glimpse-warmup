(defproject warmup "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring-server "0.4.0"]
                 [compojure "1.4.0"]
                 [glimpse "0.1.0"]
                 [twitter-api "0.7.8"]
                 [camel-snake-kebab "0.3.2"]
                 [environ "1.0.1"]]

    :aliases {"prototype" ["with-profile" "dev" "run" "-m" "warmup.dev" "prototype"]
              "production" ["with-profile" "dev" "run"]}

  :main ^:skip-aot warmup.web
  :uberjar-name "warmup-standalone.jar"

  :profiles {:uberjar {:aot :all}
             :production {:ring {:open-browser? false
                                 :stacktraces? false
                                 :auto-reload? false}}
             :dev {:dependencies [[ring/ring-devel "1.4.0"]]
                   :source-paths ["dev"]}}

  :repl-options {:init-ns warmup.dev
                 :init (glimpse/set-mode! :prototype)})
