/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tictactoeai.AILogic;

import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import com.mycompany.tictactoeai.Methods.MinimumMaximum;
import com.mycompany.tictactoeai.Methods.ArrayList;

/**
 *
 * @author Jaakko
 */

/**
 * Class is the MinMax part of the AI 
 */
public class MinMaxAI {
    public GameStatus status;
    public MinimumMaximum minmax;
    public int bestV;
    public GameStatus bestStatus;
    public char originalStart;
    public char opponentOriginalStart;
    private Heuristic heuristic;
    private int depthWhereToCalculateHeuristic;
    private boolean HashMapUsed;
    
    public MinMaxAI(GameStatus status) {
        heuristic = new Heuristic(status.board);
        this.status = status;
        depthStop();
        if (status.currentPlayer == 'O') {
            opponentOriginalStart = 'X';
        } else {
            opponentOriginalStart = 'O';
        }
        this.minmax = new MinimumMaximum();
    }
    public MinMaxAI(GameStatus status, boolean HashMapUsed) {
        this.HashMapUsed = HashMapUsed;
        
        heuristic = new Heuristic(status.board);
        this.status = status;
        depthStop();
        if (status.currentPlayer == 'O') {
            opponentOriginalStart = 'X';
        } else {
            opponentOriginalStart = 'O';
        }
        this.minmax = new MinimumMaximum();
    }
    /**
    * Method tells how to deep to check (how many boards are checked) before estimating the board value using heuristics
    *  
    */
    public void depthStop() {
        if (status.board.boardSize >= 10) {
            if (status.board.boardSize <= 20) {
                depthWhereToCalculateHeuristic = 7;  
            }
            if (status.board.boardSize <= 25) {
                depthWhereToCalculateHeuristic = 6;  
            } else if (status.board.boardSize <= 36) {
                depthWhereToCalculateHeuristic = 5; 
            } else if (status.board.boardSize <= 54) {
                depthWhereToCalculateHeuristic = 4;              
            
            } else if (status.board.boardSize <= 100) {
                depthWhereToCalculateHeuristic = 3;              
            }
            else {
                depthWhereToCalculateHeuristic = 2;  
            }       
        } else {
            depthWhereToCalculateHeuristic = 9999;  
        }
       
    }
    
    /**
    * Method returns the heuristic value of the best possible move 
    * 
    * @return int heuristic value
    */
    public int alphaBetaValue(GameStatus status) {
        this.status = status;   
        this.originalStart = status.currentPlayer;
        this.opponentOriginalStart = status.otherPlayer();
        int returnVal = maxValue(this.status, -2000000000, 2000000000, 0);
        return returnVal;
    }
    /**
    * Method returns the best next move 
    * 
     *@param status 
    * @return GameStatus status of the best move
    */
    public GameStatus alphaBetaBoard(GameStatus status) {
        this.status = status;
        this.originalStart = status.currentPlayer;
        this.opponentOriginalStart = status.otherPlayer();
        bestV = -2100000001;
        maxValue(this.status, -2000000000, 2000000000, 0);
        return bestStatus;
    }
    /**
    * Max part of the min max algorithm
    * 
    * 
    * @param status GameStatus that is being checked 
    * @param alpha int alpha value of the min-max 
    * @param beta int beta value of the min-max 
    * @param depth tells how deep is the recursion
    * @return int of the max value
    */
    public int maxValue(GameStatus status, int alpha, int beta, int depth) {
        this.status = status;
        char statusValue = status.checkAll();
        if (statusValue == 'n') {
            return 0;
        }
        if (statusValue == originalStart) {
            return 1000000100 - depth;
        } else if (statusValue == opponentOriginalStart) {
            return -1000000100 + depth;
        }
        if (depth == depthWhereToCalculateHeuristic) {
            return heuristic.evaluate(status, originalStart, opponentOriginalStart, HashMapUsed);
        }
        int v = -2100000000;
        for (GameStatus nodeChild: generateBoards().getAll()) {
            v = minmax.max(v, minValue(nodeChild, alpha, beta, depth + 1));
            alpha = minmax.max(alpha, v);
            if (depth == 0 && v > bestV) {
                this.bestStatus = nodeChild.copyGameStatus();
                bestV = v;
            }
            if (alpha  >= beta) {
                return v;                
            }
        }
        return v;

    }
    /**
    * Min part of the min max algorithm
    * 
    * 
    * @param status GameStatus that is being checked 
    * @param alpha int alpha value of the min-max 
    * @param beta int beta value of the min-max 
    * @param depth tells how deep is the recursion
    * int of the min value
    */
    public int minValue(GameStatus status, int alpha, int beta, int depth) {
        this.status = status;
        char statusValue = status.checkAll();
        if (statusValue == 'n') {            
            return 0;
        }
        if (statusValue == originalStart) {
            return 1000000100 - depth;
        } else if (statusValue == opponentOriginalStart) {
            return -1000000100 + depth;
        }        
        if (depth == depthWhereToCalculateHeuristic) {
            return heuristic.evaluate(status, originalStart, opponentOriginalStart, HashMapUsed);
        }
        int v = 2100000000;
        for (GameStatus nodeChild: generateBoards().getAll()) {
            v = minmax.min(v, maxValue(nodeChild, alpha, beta, depth + 1));
            beta = minmax.min(beta, v);
            if (alpha  >= beta) {
                return v;
            }
        }

        return v;        
        
    }
    /*
    * Methods generates all legal boards possible and returns an ArrayList of them
    * @return ArrayList that contains all legal possible moves
    */
    public ArrayList generateBoards() {
        ArrayList children = new ArrayList();
        Board board = status.board;
        for (int i = 0; i < board.gameBoard.length; i++) {
            for (int j = 0; j < board.gameBoard[0].length; j++) {
                if ((board.gameBoard[i][j] == '_')) {
                    board.gameBoard[i][j] = status.currentPlayer;
                    children.add(new GameStatus(this.status.board.copyBoard(), status.otherPlayer()));
                    board.gameBoard[i][j] = '_';
                }
            }
        }

        return children;
    }

}

    
