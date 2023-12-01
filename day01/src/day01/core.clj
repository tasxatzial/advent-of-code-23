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

(defn p1_get-first-num
  [calibration-line]
  (Integer/parseInt (re-find #"\d" calibration-line)))

(defn p1_extract-calibration-value
  [calibration-line]
  (let [first-num (p1_get-first-num calibration-line)
        last-num (p1_get-first-num (clojure.string/reverse calibration-line))]
    (+ (* 10 first-num) last-num)))

; --------------------------
; problem 2

(def digit-str->vals
  (zipmap ["1" "2" "3" "4" "5" "6" "7" "8" "9" "one" "two" "three" "four" "five" "six" "seven" "eight" "nine"]
          (take 18 (cycle (range 1 10)))))

(def digits-pattern
  (re-pattern "(?=(one|two|three|four|five|six|seven|eight|nine|\\d))."))

(defn p2_extract-calibration-value
  [calibration-line]
  (let [digits (map digit-str->vals (map second (re-seq digits-pattern calibration-line)))]
    (if (= 1 (count digits))
      (+ (* 10 (first digits)) (first digits))
      (+ (* 10 (first digits)) (last digits)))))

; --------------------------
; results

(defn day01-1
  []
  (->> (memoized-input-file->calibration-lines)
       (map p1_extract-calibration-value)
       (reduce +)))

(defn day01-2
  []
  (->> (memoized-input-file->calibration-lines)
       (map p2_extract-calibration-value)
       (reduce +)))

(defn -main
  []
  (println (day01-1))
  (println (day01-2)))
