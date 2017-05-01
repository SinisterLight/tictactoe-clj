(ns tic-tac-toe.core
  (:require [tic-tac-toe.matrix :as matrix])
  (:gen-class))

(def empty-board
  [["." "." "."]
   ["." "." "."]
   ["." "." "."]])

(def cross "X")
(def zero "O")
(def empty-square ".")
(def line-separater "\n---------\n")

(defn board-as-string [board]
  (clojure.string/join line-separater (map #(clojure.string/join " | " %) board)))

(defn print-board [board]
  (print (board-as-string board)))

(defn unmarked? [board [x y]]
  (= empty-square (get-in board [x y])))

(defn marked? [board [x y]]
  (not (unmarked? board [x y])))

(defn mark-board [board mark [x y]]
  {:pre [(unmarked? board [x y])]}
  (assoc board y (assoc (get board y) x mark)))

(defn diagonal-winner? [board mark]
  (= true (some true? (map #(every? #{mark} %) (matrix/diagonals board)))))

(defn row-winner? [board mark]
  (= true (some true? (map #(every? #{mark} %) board))))

(defn winner? [board mark]
  (or (row-winner? board mark)
      (row-winner? (matrix/transpose board) mark)
      (diagonal-winner? board mark)
      (diagonal-winner? (matrix/transpose board) mark)))
