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
    
    public MinMaxAI(GameStatus status) {
        bestV = -99999999;
        this.status = status;
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
    * 
    * @return int heuristic value
    */
    public int alphaBetaValue(GameStatus status) {
        this.status = status;   
        this.originalStart = status.currentPlayer;
        if (status.currentPlayer == 'O') {
            opponentOriginalStart = 'X';
        } else {
            opponentOriginalStart = 'O';
        }
        int returnVal = maxValue(this.status, -1, 1, 0);
        return returnVal;
    }
    /**
    * Method returns the best next move
    * 
    * 
     *@param status 
    * @return GameStatus status of the best move
    */
    public GameStatus alphaBetaBoard(GameStatus status) {
        this.status = status;
        this.originalStart = status.currentPlayer;
        bestV = -99999999;
        maxValue(this.status, -1, 1, 0);
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
    private int maxValue(GameStatus status, int alpha, int beta, int depth) {
        this.status = status;
        char statusValue = status.checkAll();
        if (statusValue == 'n') {
            return 0;
        }
        if (statusValue == originalStart) {
            return 1000;
        } else if (statusValue == opponentOriginalStart) {
            return -1000;
        }
//            System.out.println("joi");
//            System.out.println(node.toString());
//            System.out.println(node.value(originalStart));
//            System.out.println(node.isEndState() +" ja " + node.won('o')); 
        
        int v = -9999999;
        for (GameStatus nodeChild: generateBoards().getAll()) {
            v = minmax.max(v, minValue(nodeChild, alpha, beta, depth + 1));
            alpha = minmax.max(alpha, v);
            if (alpha  >= beta) {

                return v;
                
            }
        }

        return v;
//    if end_state(node): return value(node)
//    v = -Inf
//    for each child in node.children():
//       v = max(v, min_value(child, alpha, beta))
//       alpha = max(alpha, v)
//       if alpha >= beta: return v
//    return v
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
    private int minValue(GameStatus status, int alpha, int beta, int depth) {

        this.status = status;
        char statusValue = status.checkAll();
        if (statusValue == 'O') {
            if (depth == 1) {
                this.bestStatus = status.copyGameStatus();
                bestV = 1000;
            }
            return 1000;
        } else if (statusValue == 'X') {
            if (depth == 1 && bestV > -1000) {
                this.bestStatus = status.copyGameStatus();
            }
            bestV = -1000;
            return -1000;
        }
        if (statusValue == 'n') {
            if (depth == 1 && bestV < 0) {
                this.bestStatus = status.copyGameStatus();
                bestV = 0;
            }
            return 0;
        }
        int v = 9999999;
        for (GameStatus nodeChild: generateBoards().getAll()) {
            v = minmax.min(v, maxValue(nodeChild, alpha, beta, depth + 1));
            beta = minmax.min(beta, v);
            if (alpha  >= beta) {

                if (depth == 1 && v > bestV) {
                    this.bestStatus = status.copyGameStatus();
                    bestV = v;
                }
                return v;
            }
        }

        if (depth == 1 && v > bestV) {
            this.bestStatus = status.copyGameStatus();
            bestV = v;
        }
        return v;        
        
    }
    /*
    * Methods generates all legal boards possible and returns an ArrayList of them
    * @return ArrayList that contains legal possible moves
    */
    private ArrayList generateBoards() {
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

    
