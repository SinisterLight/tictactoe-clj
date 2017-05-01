(ns tic-tac-toe.core-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe.matrix :as matrix]
            [tic-tac-toe.core :refer :all]))

(deftest define-empty-board
  (testing "empty board"
    (is (= [["." "." "."] ["." "." "."] ["." "." "."]]
           empty-board))))

(deftest define-cross
  (is (= "X" cross)))

(deftest define-zero
  (is (= "O" zero)))

(deftest define-empty-square
  (is (= "." empty-square)))

(deftest board-to-string
  (is (= ". | . | .\n---------\n. | . | .\n---------\n. | . | ." (board-as-string empty-board))))

(deftest mark-board-with-cross
  (is (= [["." "." "."] ["." "X" "."] ["." "." "."]]
         (mark-board empty-board cross [1 1]))))

(deftest marked-should-return-true-when-marked
  (is (true? (marked? [["." "." "."] ["." "X" "."] ["." "." "."]] [1 1]))))

(deftest marked-should-return-false-when-not-marked
  (is (false? (marked? [["." "." "."] ["." "." "."] ["." "." "."]] [1 1]))))

(deftest should-fail-to-mark-if-marked
  (let [board (mark-board empty-board cross [0 0])]
    (is (thrown? AssertionError (mark-board board cross [0 0])))))

(deftest should-return-true-for-a-row-winner
  (let [board [["X" "X" "X"] ["." "." "."] ["." "." "."]]]
    (is (true? (winner? board cross)))))

(deftest should-return-false-for-no-winner
  (let [board [["X" "X" "O"] ["." "." "."] ["." "." "."]]]
    (is (false? (winner? board cross)))))

(deftest should-return-true-for-a-column-winner
  (let [board [["X" "." "O"] ["X" "." "."] ["X" "." "."]]]
    (is (true? (winner? board cross)))))

(deftest should-return-transpose-of-board
  (let [board [["X" "." "O"] ["X" "." "."] ["X" "." "."]]]
    (is (= [["X" "X" "X"] ["." "." "."] ["O" "." "."]] (matrix/transpose board)))))

(deftest should-return-true-for-a-diagonal-winner
  (let [board [["X" "." "O"] ["." "X" "."] ["X" "." "X"]]]
    (is (true? (winner? board cross)))))

(deftest should-return-true-for-a-transposed-diagonal-winner
  (let [board [["O" "." "X"] ["." "X" "."] ["X" "." "O"]]]
    (is (true? (winner? board cross)))))

(deftest should-return-true-when-moves-left
  (let [board [["O" "X" "X"] ["X" "X" "X"] ["X" "." "O"]]]
    (is (true? (moves-left? board)))))

(deftest should-return-false-when-no-moves-left
  (let [board [["O" "X" "X"] ["X" "X" "X"] ["X" "O" "O"]]]
    (is (false? (moves-left? board)))))
