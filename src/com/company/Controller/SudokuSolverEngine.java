package com.company.Controller;

import com.company.Strategies.BactrakStrategy;
import com.company.Strategies.ISudokuStrategy;
import com.company.Strategies.MarkUpStrategy;
import com.company.Strategies.NakedPairStrategy;

import java.time.chrono.IsoChronology;
import java.util.LinkedList;
import java.util.List;

public class SudokuSolverEngine {
    private SudokuBoardStateManager sudokuBoardStateManager;
    private SudokuMapper sudokuMapper;

    public SudokuSolverEngine(SudokuBoardStateManager sudokuBoardStateManager, SudokuMapper sudokuMapper){
        this.sudokuBoardStateManager = sudokuBoardStateManager;
        this.sudokuMapper = sudokuMapper;
    }

    public boolean solve(int[][] sudokuBoard){
        List<ISudokuStrategy> strategies = new LinkedList<>();
        strategies.add(new MarkUpStrategy(sudokuMapper));
        strategies.add(new NakedPairStrategy(sudokuMapper));
        strategies.add(new BactrakStrategy(sudokuMapper));

        String currentState = sudokuBoardStateManager.generateState(sudokuBoard);
        String nextState = sudokuBoardStateManager.generateState(strategies.get(0).solve(sudokuBoard));

        //TODO 2 basic algos if solution not found use backtracking (eliminate nodes)

        while(!sudokuBoardStateManager.isSolved(sudokuBoard) && !currentState.equals(nextState)){
            currentState = nextState;
            for(var strategy : strategies){
                nextState = sudokuBoardStateManager.generateState(strategy.solve(sudokuBoard));
            }
        }

        return sudokuBoardStateManager.isSolved(sudokuBoard);
    }
}