(ns day01.core
  (:gen-class))

; --------------------------
; common

(def input-file "resources\\input.txt")

(defn input-file->calibration-lines
  []
  (-> input-file
      slurp
      clojure.string/split-lines))

(def memoized-input-file->calibration-lines (memoize input-file->calibration-lines))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
