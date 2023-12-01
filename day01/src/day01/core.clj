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

; --------------------------
; problem 1

(defn get-first-num
  [calibration-line]
  (Integer/parseInt (re-find #"\d" calibration-line)))

(defn extract-calibration-value
  [calibration-line]
  (let [first-num (get-first-num calibration-line)
        last-num (get-first-num (clojure.string/reverse calibration-line))]
    (+ (* 10 first-num) last-num)))

(defn -main
  []
  (println 1))
