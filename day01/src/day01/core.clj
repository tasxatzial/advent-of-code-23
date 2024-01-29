(ns day01.core
  (:gen-class))

; --------------------------
; common

(def input-file "resources\\input.txt")

(defn parse-file
  "Reads and parses the input file into a vector of strings."
  []
  (-> input-file
      slurp
      clojure.string/split-lines))

(def memoized-input-file->calibration-lines (memoize parse-file))

; --------------------------
; problem 1

(defn p1_get-first-value
  "Given a line, it returns the value of the first digit."
  [calibration-line]
  (Integer/parseInt (re-find #"\d" calibration-line)))

(defn p1_compute-calibration-value
  "Given a line, it returns the calibration value."
  [calibration-line]
  (let [first-val (p1_get-first-value calibration-line)
        last-val (p1_get-first-value (clojure.string/reverse calibration-line))]
    (+ (* 10 first-val) last-val)))

; --------------------------
; problem 2

(def digit->value
  "A map from a digit to its numerical value."
  (zipmap ["1" "2" "3" "4" "5" "6" "7" "8" "9" "one" "two" "three" "four" "five" "six" "seven" "eight" "nine"]
          (take 18 (cycle (range 1 10)))))

(def number-pattern
  (re-pattern "(?=(one|two|three|four|five|six|seven|eight|nine|\\d))."))

(defn extract-values
  "Given a line, it returns all its numbers and as a seq."
  [calibration-line]
  (->> calibration-line
       (re-seq number-pattern)
       (map (comp digit->value second))))

(defn p2_compute-calibration-value
  "Given a line, it returns the calibration value."
  [calibration-line]
  (let [values (extract-values calibration-line)]
    (if (= 1 (count values))
      (+ (* 10 (first values)) (first values))
      (+ (* 10 (first values)) (last values)))))

; --------------------------
; results

(defn day01
  [extract-calibration-value]
  (->> (memoized-input-file->calibration-lines)
       (map extract-calibration-value)
       (reduce +)))

(defn day01-1
  []
  (day01 p1_compute-calibration-value))

(defn day01-2
  []
  (day01 p2_compute-calibration-value))

(defn -main
  []
  (println (day01-1))
  (println (day01-2)))
