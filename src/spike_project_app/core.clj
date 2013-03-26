(ns spike-project-app.core
  (:require [clojure-csv.core :as csv])
  (:use [clojure.java.io])
  (:gen-class))

(defn- parse-row [header row]
  (let [v (first (csv/parse-csv row))]
    (zipmap header v)
    )
  )

(defn- construct-line
  [value]
  (let [ordrTyp (format "%10s" (get value "Name"))
        fndPrvId (format "%10s" (get value "Email"))
        ]
    (str ordrTyp fndPrvId "\n")
    )
  )

(defn- write-line [values]
  (with-open [wrtr (writer "data-files/Order_ABA.dat")]
    (doseq [item values] (.write wrtr (construct-line item)))
    )
  )

(defn- take-csv
  [fname]
  (with-open [file (java.io.BufferedReader.
                 (java.io.FileReader. fname))]
    (let [header (first (csv/parse-csv (first (line-seq file))))]
      (let [values (doall (map #(parse-row header %)  (line-seq file)))]
        (write-line values)
        )
      )
    ))

(defn -main
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (take-csv "data-files/Order_Sample.csv"))