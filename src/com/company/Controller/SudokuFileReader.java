package com.company.Controller;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SudokuFileReader {
    int[][] sudokuBoard = new int[9][9];

    public int[][] readFile(String filename){
        try{
            File file = new File("puzzles",filename+".txt");
            filename = file.getAbsolutePath();
            RandomAccessFile randomAccessFile = new RandomAccessFile(filename,"r");
            String key = randomAccessFile.readLine();
            int keyPointer = 0;
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    sudokuBoard[i][j] = Character.getNumericValue(key.charAt(keyPointer));
                    keyPointer++;
                }
            }
        }
        catch (Exception ex){
            System.out.println("Selected puzzle does not exist");
        }

        return sudokuBoard;
    }

    public List<String> listPuzzles(){
        List<String> puzzles = new LinkedList<>();

        try{
            File file = new File("puzzles","");
            Stream<Path> walk = Files.walk(Paths.get(file.getAbsolutePath()));
            puzzles = walk.filter(Files::isRegularFile).map(x -> x.toString().substring(x.toString().indexOf("puzzle-"),x.toString().length()-4)).collect(Collectors.toList());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return puzzles;
    }
}
