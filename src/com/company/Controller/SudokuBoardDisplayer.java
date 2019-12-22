package com.company.Controller;

public class SudokuBoardDisplayer {
    public void display(String title, int[][] sudokuBoard){
        if(!title.equals(""))
            System.out.println(title);
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print(sudokuBoard[i][j]);
            }
            System.out.println();
        }
    }
}
