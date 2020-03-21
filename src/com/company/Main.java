package com.company;

import com.company.Controller.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            SudokuMapper sudokuMapper = new SudokuMapper();
            SudokuBoardStateManager sudokuBoardStateManager = new SudokuBoardStateManager();
            SudokuSolverEngine sudokuSolverEngine = new SudokuSolverEngine(sudokuBoardStateManager, sudokuMapper);
            SudokuFileReader sudokuFileReader = new SudokuFileReader();
            SudokuBoardDisplayer sudokuBoardDisplayer = new SudokuBoardDisplayer();
            Scanner scanner = new Scanner(System.in);

            List<String> puzzles = sudokuFileReader.listPuzzles();
            System.out.println("AVAILABLE PUZZLES");
            puzzles.forEach(System.out::println);
            System.out.println("Enter the file name conaining sudoku puzzle: ");
            String filename = scanner.nextLine();

            int[][] sudokuBoard = sudokuFileReader.readFile(filename);
            sudokuBoardDisplayer.display(filename, sudokuBoard);

            long startTimer = System.currentTimeMillis();
            boolean isSolved = sudokuSolverEngine.solve(sudokuBoard);
            sudokuBoardDisplayer.display("Final state", sudokuBoard);
            System.out.println(isSolved ? ("Succes!"+" Puzzle solved in "+(System.currentTimeMillis()-startTimer)+" millisecond") : "Failure");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
