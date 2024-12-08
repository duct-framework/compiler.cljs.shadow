(ns duct.compiler.cljs.shadow
  (:require [integrant.core :as ig]
            [shadow.cljs.devtools.api :as api]
            [shadow.cljs.devtools.config :as config]
            [shadow.cljs.devtools.server.common :as common]
            [shadow.cljs.devtools.server.runtime :as runtime]
            [shadow.runtime.services :as rt]))

(defn- make-runtime [config]
  (-> {::api/started (System/currentTimeMillis), :config config}
      (rt/init (common/get-system-config config))
      (rt/start-all)))

(defmacro ^:private with-runtime [runtime & body]
  `(let [runtime# ~runtime]
     (try
       (runtime/set-instance! runtime#)
       (do ~@body)
       (finally
         (runtime/reset-instance!)
         (rt/stop-all runtime#)))))

(defn- normalize-config [config]
  (-> (merge config/default-config config config)
      (dissoc :build)
      (assoc-in [:builds :cljs] (:build config))
      (config/normalize)))

(defmethod ig/init-key ::release [_ config]
  (let [config (normalize-config config)]
    (with-runtime (make-runtime config)
      (api/release* (-> config :builds :cljs) {}))))

(defmethod ig/init-key ::watch [_ _options]
  (prn :worker-started))

(defmethod ig/halt-key! ::watch [_ _worker]
  (prn :worker-stopped))
