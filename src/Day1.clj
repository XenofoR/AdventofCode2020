(ns Day1)
(defn getVectorFromFile [fileName]
(with-open [reader (clojure.java.io/reader fileName)]

(reduce conj () (line-seq reader))))

(def input  (map #(Integer/parseInt %) (getVectorFromFile "../input/Day1.in")))


(defn checkForSum [l]
(def head (first l))
(def remaining (- 2020 head))                               ; we are looking for the numbers that sum give 2020
(def tail (rest l))
(def wanted (drop-while #(not= remaining %)  tail))
(if (not-empty wanted) (* head (first wanted)) (checkForSum tail))
)

(println (checkForSum input))
