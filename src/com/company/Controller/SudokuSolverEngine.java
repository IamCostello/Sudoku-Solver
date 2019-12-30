package com.company.Controller;

import com.company.Strategies.BactrakStrategy;
import com.company.Strategies.ISudokuStrategy;
import com.company.Strategies.MarkUpStrategy;
import com.company.Strategies.NakedPairStrategy;

import java.time.chrono.IsoChronology;
import java.util.LinkedList;
import java.util.List;
import java.util.prefs.BackingStoreException;

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

        String currentState = sudokuBoardStateManager.generateState(sudokuBoard);
        String nextState = sudokuBoardStateManager.generateState(strategies.get(0).solve(sudokuBoard));

        //use other strategies to tighten decision tree for backtracking or solve puzzle
        while(!sudokuBoardStateManager.isSolved(sudokuBoard) && !currentState.equals(nextState)){
            currentState = nextState;
            for(var strategy : strategies){
                nextState = sudokuBoardStateManager.generateState(strategy.solve(sudokuBoard));
            }
        }

        //only use backtracking after eliminating possible cell values
        if(!sudokuBoardStateManager.isSolved(sudokuBoard)){
            BactrakStrategy bactrakStrategy = new BactrakStrategy(sudokuMapper);
            bactrakStrategy.solve(sudokuBoard);
        }

        return sudokuBoardStateManager.isSolved(sudokuBoard);
    }
}