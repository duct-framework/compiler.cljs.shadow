(ns duct.compiler.cljs.shadow
  (:require [integrant.core :as ig]))

(defmethod ig/init-key ::release [_ _options]
  (prn :compiled))

(defmethod ig/init-key ::watch [_ _options]
  (prn :worker-started))

(defmethod ig/halt-key! ::watch [_ _worker]
  (prn :worker-stopped))
