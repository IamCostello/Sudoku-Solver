package com.company.Controller;

import java.io.File;
import java.io.RandomAccessFile;

public class SudokuFileReader {
    int[][] sudokuBoard = new int[9][9];

    //TODO make function that saves solution state to file

    public int[][] readFile(String filename){
        try{
            File file = new File("puzzles",filename+".txt");
            filename = file.getAbsolutePath();
            RandomAccessFile randomAccessFile = new RandomAccessFile(filename,"r");
            String key = randomAccessFile.readLine();
            int keyPointer = 0;
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    //TODO decide on alternative
                    sudokuBoard[i][j] = Character.getNumericValue(key.charAt(keyPointer));
                    keyPointer++;
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return sudokuBoard;
    }
}
