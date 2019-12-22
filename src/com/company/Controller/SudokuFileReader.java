package com.company.Controller;

import java.io.RandomAccessFile;

public class SudokuFileReader {
    int[][] sudokuBoard = new int[9][9];

    public int[][] readFile(String fileName){
        try{
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName+".txt","r");
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    sudokuBoard[i][j] = randomAccessFile.readInt();
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return sudokuBoard;
    }
}
