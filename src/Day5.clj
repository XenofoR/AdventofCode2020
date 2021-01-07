(ns Day5)

(defn binaryPartition [x y]
      (/ (- x  y) 2)
      )

(defn stringToCharVector [inputString]
      (seq (char-array inputString))
      )

(def input (map stringToCharVector (line-seq (clojure.java.io/reader "../input/Day5.in"))))


(defn getRow [boardingChars currentRow currentColumn]
      (if (not= nil (first boardingChars)) (do
          (def lowerRow (nth currentRow 0))
          (def upperRow (nth currentRow 1))
          (def lowerColumn (nth currentColumn 0))
          (def upperColumn (nth currentColumn 1))
          (case (first boardingChars)
                \F (getRow (rest boardingChars) (vector lowerRow (- (- upperRow (binaryPartition upperRow lowerRow) ) 0.5) ) currentColumn)
                \B (getRow (rest boardingChars) (vector (+ (+ (binaryPartition upperRow lowerRow) lowerRow) 0.5) upperRow  ) currentColumn)
                \L (getRow (rest boardingChars) currentRow (vector lowerColumn (- (- upperColumn (binaryPartition upperColumn lowerColumn)) 0.5)))
                \R (getRow (rest boardingChars) currentRow (vector (+ (+ lowerColumn (binaryPartition upperColumn lowerColumn)) 0.5) upperColumn))
          )

          ) (do
             (+ (* (nth currentRow 0) 8) (nth currentColumn 0))
             ))

      )
(println (apply max (map #(getRow %1 (vector 0 127) (vector 0 7)) input)))

;(def row (getRow ["F" "B" "F" "B" "B" "F" "F" "R" "L" "R"] (vector 0 127) (vector 0 7)))

;(println row)


