(ns duct.compiler.cljs.shadow-test
  (:require [clojure.test :refer [deftest is]]
            [clojure.java.io :as io]
            [duct.compiler.cljs.shadow :as shadow]
            [integrant.core :as ig]))

(deftest release-test
  (try
    (ig/init-key
     ::shadow/release
     {:build
      {:target :browser
       :output-dir "target/cljs/js"
       :asset-path "/js"
       :modules {:main {:entries ['duct.compiler.cljs.client-test]}}}})
    (is (.exists (io/file "target/cljs/js/main.js")))
    (finally
      (let [f (io/file "target/cljs/js/main.js")]
        (when (.exists f)
          (.delete f))))))
