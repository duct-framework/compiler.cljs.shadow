(ns duct.compiler.cljs.shadow-cljs
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :duct.compiler.cljs/shadow-cljs [_ _options]
  (prn :compiled))
