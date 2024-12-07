(ns duct.compiler.cljs.shadow
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :duct.compiler.cljs/shadow [_ _options]
  (prn :compiled))
