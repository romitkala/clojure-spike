(ns spike-project-app.core
  (:require [clojure-csv.core :as csv])
  (:gen-class))

(defn parse-row [row]
    (let [v (first (csv/parse-csv row))]
      (zipmap [:OrdrTyp :DstId :FndPrvId :SwtchRef :OrdrRef :FndIdType :FndId :FndNm :PrdtCd :Unit :OrdrAmt :OrdrCcy :HldgsRedRate :Rndg :AcctId :SubAcctId :AgtCode :Grp1Or2Units :ReqdSttlmCcy :ReqdPrcCcy :ChrgTp :ChrgRt :ComssnTp :ComssnRt :PctgComssnWvd :SbLgOrdrRef :SbLgFndIdType :SbLgFndId :SbLgFndNm :SbLgPrdtCd :SbLgPctgOfTtlRedAmt :SbLgAcctId :SbLgSubAcctId :SbLgAgtCode :SbLgReqdSttlmCcy :SbLgReqdPrcCcy :SbLgChrgTp :SbLgChrgRt :SbLgComssnTp :SbLgComssnRt :SbLgPctgComssnWvd] v)))

;; (defn parse-row [row]
;;     (let [v (first (csv/parse-csv row))]
;;       (zipmap [:OrdrTyp :DstId :FndPrvId :SwtchRef :OrdrRef :FndIdType :FndId :FndNm :PrdtCd :Unit :OrdrAmt :OrdrCcy :HldgsRedRate :Rndg :AcctId :SubAcctId :AgtCode :Grp1Or2Units :ReqdSttlmCcy :ReqdPrcCcy :ChrgTp :ChrgRt :ComssnTp :ComssnRt :PctgComssnWvd :SbLgOrdrRef :SbLgFndIdType :SbLgFndId :SbLgFndNm :SbLgPrdtCd :SbLgPctgOfTtlRedAmt :SbLgAcctId :SbLgSubAcctId :SbLgAgtCode :SbLgReqdSttlmCcy :SbLgReqdPrcCcy :SbLgChrgTp :SbLgChrgRt :SbLgComssnTp :SbLgComssnRt :SbLgPctgComssnWvd] v)))


;; (defn- take-csv
;;   "Takes file name and reads data."
;;   [fname]
;;   (with-open [file (java.io.BufferedReader.
;;                  (java.io.FileReader. fname))]
;;     ;; (doall (map (comp first csv/parse-csv) (line-seq file)))
;;     ;; (csv/parse-csv (slurp file))
;;     (doseq [line (line-seq file)]
;;       (println (parse-row line))
;;       )
;;     ))

(defn take-csv
  "Takes file name and reads data."
  [fname]
  (with-open [file (java.io.BufferedReader.
                 (java.io.FileReader. fname))]
    ;; (doall (map (comp first csv/parse-csv) (line-seq file)))
    ;; (csv/parse-csv (slurp file))
    (doseq [line (line-seq file)]
      (println (parse-row line))
      )
    ))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (println "Hello, World!")
  (take-csv "data-files/Order_Sample.csv"))