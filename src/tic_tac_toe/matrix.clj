(ns tic-tac-toe.matrix)

(defn transpose [matrix]
  (apply mapv vector matrix))

(defn left-diagonal [matrix]
  (apply vector (map #(get-in matrix [% %] ) (range (count matrix)))))

(defn right-diagonal [matrix]
  (let [length (count matrix)]
    (apply vector (map #(get-in matrix [% (- length 1 %)] ) (range length)))))

(defn diagonals [matrix]
  [(left-diagonal matrix)
   (right-diagonal matrix)])
