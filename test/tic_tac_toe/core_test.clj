(ns tic-tac-toe.core-test
  (:require [clojure.test :refer :all]
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

(deftest should-return-true
  (let [board [["X" "X" "X"] ["." "." "."] ["." "." "."]]]
    (is (winner? board cross))))
