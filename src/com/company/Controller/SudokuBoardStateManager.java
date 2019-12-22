package com.company.Controller;

public class SudokuBoardStateManager {
    public String generateState(int[][] sudokuBoard){
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < sudokuBoard.length; i++) {
            for (int j = 0; j < sudokuBoard[i].length; j++) {
                key.append(sudokuBoard[i][j]);
            }
        }
        return key.toString();
    }

    public boolean isSolved(int[][] sudokuBoard){
        for (int i = 0; i < sudokuBoard.length; i++) {
            for (int j = 0; j < sudokuBoard[i].length; j++) {
                if (sudokuBoard[i][j] == 0 || String.valueOf(sudokuBoard[i][j]).length() > 1)
                    return false;
            }
        }
        return true;
    }
}
