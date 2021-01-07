(ns Day6(:require [clojure.set :as set]))

(defn stringToCharSet [inputString]
      (set (char-array inputString))
      )

(defn parseLine [parsedReplies lineToParse]
       (cons (set/union (first parsedReplies) (stringToCharSet lineToParse)) (rest parsedReplies))
      )

(defn readAnswers [parsedReplies unparsedReplies]
      (if (empty? unparsedReplies) (reduce + (map count parsedReplies))
                                   (if (not (clojure.string/blank? (first unparsedReplies)))
                                                               (readAnswers (parseLine parsedReplies (first unparsedReplies)) (rest unparsedReplies))
                                                               (readAnswers (cons (empty set) parsedReplies) (rest unparsedReplies))
                                                               )
                                   )
      )

(def input (line-seq (clojure.java.io/reader "../input/Day6.in")))

(println (readAnswers (list (empty set)) input))