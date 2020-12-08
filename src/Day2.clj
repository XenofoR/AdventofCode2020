(ns Day2)


; Used a map first, until I realized that password policies can repeat :-/
(defn getMapFromFile [fileName]
      (
        with-open [reader (clojure.java.io/reader fileName)]
                  (def split_vector (doall (map #(clojure.string/split %1 #":") (line-seq reader))))
                  (println split_vector)
                  (apply hash-map (reduce concat split_vector))
                  )
      )

 (defn splitGet [string index regex]
   (get (clojure.string/split string regex) index)
   )

(defn checkPassword [inline]
      (def criteria (clojure.string/split inline #":"))
  (def min_count (Integer/parseInt (splitGet (get criteria 0)  0 #"-")))
  (def max_count (Integer/parseInt (splitGet (splitGet (get criteria 0) 1 #"-") 0 #" "))) ;Ugly to split again and again, bit I am tired x.x
  (def character (splitGet (splitGet (get criteria 0) 1 #"-") 1 #" "))
      (and (<= min_count (count (re-seq (re-pattern character) (get criteria 1)))) (>= max_count (count (re-seq (re-pattern character) (get criteria 1))))) ; totally readable... totally
      )

(def input (line-seq (clojure.java.io/reader "../input/Day2.in")))


(println (count (filter true? (map #(checkPassword %) input))))