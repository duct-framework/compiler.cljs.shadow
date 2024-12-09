(ns duct.compiler.cljs.shadow-test
  (:require [clojure.test :refer [deftest is]]
            [clojure.java.io :as io]
            [duct.compiler.cljs.shadow :as shadow]
            [integrant.core :as ig]))

(def client-test
  'duct.compiler.cljs.client-test)

(deftest release-test
  (try
    (ig/init-key
     ::shadow/release
     {:build
      {:target :browser
       :output-dir "target/cljs/js"
       :asset-path "/js"
       :modules {:main {:entries [client-test]}}}})
    (is (.exists (io/file "target/cljs/js/main.js")))
    (finally
      (let [f (io/file "target/cljs/js/main.js")]
        (when (.exists f)
          (.delete f))))))

(deftest server-test
  (try
    (let [out    (io/file "target/cljs/js/main.js")
          config {:build
                  {:target :browser
                   :output-dir "target/cljs/js"
                   :asset-path "/js"
                   :modules {:main {:entries [client-test]}}}}
          inst   (ig/init-key ::shadow/server config)]
      (Thread/sleep 1000)
      (is (.exists out))
      (ig/suspend-key! ::shadow/server inst)
      (.delete out)
      (is (not (.exists out)))
      (let [inst (ig/resume-key ::shadow/server config config inst)]
        (Thread/sleep 1000)
        (is (.exists out))
        (ig/halt-key! ::shadow/server inst)))
    (finally
      (doseq [f (reverse (file-seq (io/file "target/cljs")))]
        (.delete f)))))
