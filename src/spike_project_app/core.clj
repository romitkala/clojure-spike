(ns spike-project-app.core
  (:require [clojure-csv.core :as csv])
  (:gen-class))

(defn parse-row [header row]
  (let [v (first (csv/parse-csv row))]
    (zipmap header v)
    )
  )

(defn take-csv
  "Takes file name and reads data."
  [fname]
  (with-open [file (java.io.BufferedReader.
                 (java.io.FileReader. fname))]
    (let [header (first (csv/parse-csv (first (line-seq file))))]
      (doall (map #(parse-row header %)  (line-seq file)))
      )
    ))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (println "Hello, World!")
  (take-csv "data-files/Order_Sample.csv"))