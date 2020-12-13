(ns Day3)

(defn stringToCharVector [inputString]
      (vec (char-array inputString))
      )

(defn wrapColumn [index shift]
      (def newIndex (+ index shift))
      (if (< 30 newIndex) (- newIndex 31) newIndex)
      )

(defn checkTree [row index]
      (if (= \# (nth row index)) 1 0)
      )

(defn getPath [path row rowJump column columnShift trees]
      (if (<= (count path) row)
        trees
        (getPath path (+ row rowJump) rowJump (wrapColumn column columnShift) columnShift (+ trees (checkTree (nth path row) column)))
        )
      )

(def input (map stringToCharVector (line-seq (clojure.java.io/reader "../input/Day3.in"))))

(def treeCount (getPath input 0 1 0 1 0))

(def treeCount2 (getPath input 0 1 0 3 0))

(def treeCount3 (getPath input 0 1 0 5 0))

(def treeCount4 (getPath input 0 1 0 7 0))

(def treeCount5 (getPath input 0 2 0 1 0))

(println (* treeCount treeCount2 treeCount3 treeCount4 treeCount5))