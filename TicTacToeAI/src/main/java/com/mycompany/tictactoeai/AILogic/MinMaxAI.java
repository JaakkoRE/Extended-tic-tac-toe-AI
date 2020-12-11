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
    public DepthStop depthStop;
    public Heuristic heuristic;
    private int depthWhereToCalculateHeuristic;
    private boolean hashMapused;
    
    public MinMaxAI(GameStatus status) {
        heuristic = new Heuristic(status.board);
        this.status = status;
        
        this.depthStop = new DepthStop();
        this.depthWhereToCalculateHeuristic = this.depthStop.depthStopStart(status.board.boardSize);
        
        if (status.currentPlayer == 'O') {
            opponentOriginalStart = 'X';
        } else {
            opponentOriginalStart = 'O';
        }
        this.minmax = new MinimumMaximum();
    }
    public MinMaxAI(GameStatus status, boolean hashMapused) {
        this.hashMapused = hashMapused;
       
        heuristic = new Heuristic(status.board);
        this.status = status;
        
        this.depthStop = new DepthStop();
        this.depthWhereToCalculateHeuristic = this.depthStop.depthStopStart(status.board.boardSize);
        
        if (status.currentPlayer == 'O') {
            opponentOriginalStart = 'X';
        } else {
            opponentOriginalStart = 'O';
        }
        this.minmax = new MinimumMaximum();
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
        this.depthWhereToCalculateHeuristic = depthStop.depthStopCorrection(status.board.boardSize, status.board.boardfulfillment, depthWhereToCalculateHeuristic);
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
        this.depthWhereToCalculateHeuristic = depthStop.depthStopCorrection(status.board.boardSize, status.board.boardfulfillment, depthWhereToCalculateHeuristic);
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
            return 1000000100 - depth; //better to win faster
        } else if (statusValue == opponentOriginalStart) {
            return -1000000100 + depth; //better to lose faster
        }
        if (depth == depthWhereToCalculateHeuristic) {
            return heuristic.evaluate(status, originalStart, opponentOriginalStart, hashMapused);
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
            return 1000000100 - depth; // better to win faster
        } else if (statusValue == opponentOriginalStart) {
            return -1000000100 + depth; // better to lose slower
        }        
        if (depth == depthWhereToCalculateHeuristic) {
            return heuristic.evaluate(status, originalStart, opponentOriginalStart, hashMapused);
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
    /**
    * Methods generates all legal boards possible and returns an ArrayList of them
    * @return ArrayList that contains all legal possible moves
    */
    
    public ArrayList generateBoards() {
        ArrayList children = new ArrayList();
        Board board = status.board;
        for (int i = 0; i < board.gameBoard.length; i++) {
            for (int j = 0; j < board.gameBoard[0].length; j++) {
                if (board.boardfulfillment <= 3 && board.gameBoard[i][j] == '_') { // if the board is nearly empty you shouldn't try to limit options
                    GameStatus copy = status.copyGameStatus();
                    copy.setBoardValue(i, j);
                    children.add(copy);                    
                } else if (board.gameBoard[i][j] == '_' && nearSymbol(board, i, j)) { // options that are far from anything are likely useless
                    GameStatus copy = status.copyGameStatus();
                    copy.setBoardValue(i, j);
                    children.add(copy);
                }
            }
        }

        return children;
    }
    /**
    * Methods returns true if the board position is near symbol 'O' or 'X'
    * @param board the board that is checked
    * @param i coordinate
    * @param j the other coordinate
    * @return boolean
    */
    public boolean nearSymbol(Board board, int i, int j) {
        for (int ind = 1; ind <= 2; ind++) {
            if (i + ind < board.gameBoard.length && board.gameBoard[i + ind][j] != '_') {
                return true;
            }
            if (i - ind >= 0 && board.gameBoard[i - ind][j] != '_') {
                return true;
            }
            if (j + ind < board.gameBoard[0].length && board.gameBoard[i][j + ind] != '_') {
                return true;
            }
            if (j - ind >= 0 && board.gameBoard[i][j - ind] != '_') {
                return true;
            }
            
            
            if (i + ind < board.gameBoard.length && j + ind < board.gameBoard[0].length && board.gameBoard[i + ind][j + ind] != '_') {
                return true;
            }
            if (i - ind >= 0 && j - ind >= 0 && board.gameBoard[i - ind][j - ind] != '_') {
                return true;
            }            
            if (i + ind < board.gameBoard.length && j - ind >= 0 && board.gameBoard[i + ind][j - ind] != '_') {
                return true;
            }
            if (i - ind >= 0 && j + ind < board.gameBoard[0].length && board.gameBoard[i - ind][j + ind] != '_') {
                return true;
            }
        }
        if (i - 2 >= 0 && j - 1 >= 0 && board.gameBoard[i - 2][j - 1] != '_') {
            return true;
        }
        if (i - 2 >= 0 && j + 1 < board.gameBoard[0].length && board.gameBoard[i - 2][j + 1] != '_') {
            return true;
        }
        if (i - 1 >= 0 && j - 2 >= 0 && board.gameBoard[i - 1][j - 2] != '_') {
            return true;
        }
        if (i - 1 >= 0 && j + 2 < board.gameBoard[0].length && board.gameBoard[i - 1][j + 2] != '_') {
            return true;
        }
        
        if (i + 2 < board.gameBoard.length && j - 1 >= 0 && board.gameBoard[i + 2][j - 1] != '_') {
            return true;
        }
        if (i + 2 < board.gameBoard.length && j + 1 < board.gameBoard[0].length && board.gameBoard[i + 2][j + 1] != '_') {
            return true;
        }
        if (i + 1 < board.gameBoard.length && j - 2 >= 0 && board.gameBoard[i + 1][j - 2] != '_') {
            return true;
        }
        if (i + 1 < board.gameBoard.length && j + 2 < board.gameBoard[0].length && board.gameBoard[i + 1][j + 2] != '_') {
            return true;
        }
        
        return false;
    }

}

    
