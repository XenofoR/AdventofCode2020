(ns Day4
    (:require [clojure.set :as set]))



(defn checkPassport [passportSet]
      (empty? (set/difference #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"} passportSet))
      )

(defn keyvalSplit [s]
      (map #(nth  (.split %1 ":") 0) (.split s " "))
      )

(defn checkBlank [input validList index]
      (if (> (count input) 1) (
                                (println (count input) index)
                                      (if (not (clojure.string/blank? (nth input index)))

                                            ((checkBlank input validList (inc index)))
                                            (
                                                  (def passportSplit (split-at index input)) ;end of passport, split at blank line
                                                  (def passport (set(reduce concat (map #(keyvalSplit %1) (nth passportSplit 0))))) ;concat rows into set of keys, for step 1 we only need the keys
                                                   (checkBlank (rest(nth passportSplit 1)) (conj validList (checkPassport passport)) 0) ;continue with end of seq from index 0
                                                  )
                                            )


                                      )
                               (println (count (filter true? validList))) ;done, print the true values
                                     )
      )

(def input (line-seq (clojure.java.io/reader "../input/Day4.in")))


(checkBlank input (list false) 0)

