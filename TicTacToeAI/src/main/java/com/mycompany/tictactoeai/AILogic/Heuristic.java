/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tictactoeai.AILogic;

import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import com.mycompany.tictactoeai.Methods.AbsoluteValue;

/**
 *
 * @author Jaakko
 */

/**
 * Class is used for getting heuristic value of the current board
 */
public class Heuristic {
    public int[][] heuristicBoard;
    public boolean[][] heuristicVerticalTested;
    public boolean[][] heuristicHorizontalTested;
    public boolean[][] heuristicDiagonal1Tested;
    public boolean[][] heuristicDiagonal2Tested;
    public boolean[][] heuristicSameSymbolNear;

    public int vcl;

    public Heuristic(Board board) {
        heuristicBaseSetUp(board.sizex, board.sizey);
        this.vcl = board.vcl;
    }
    /**
    * Method sets value to the board to give base values of locations based on coordinates. 
    * @param xLength tells the setup boards one sides length
    * @param yLength tells the setup boards other sides length
    */ 
    public void heuristicBaseSetUp(int xLength, int yLength) {
        AbsoluteValue intAbs = new AbsoluteValue();
        heuristicBoard = new int[xLength][yLength];
        heuristicVerticalTested = new boolean[xLength][yLength];
        heuristicHorizontalTested = new boolean[xLength][yLength];
        heuristicDiagonal1Tested = new boolean[xLength][yLength];
        heuristicDiagonal2Tested = new boolean[xLength][yLength];
        heuristicSameSymbolNear = new boolean[xLength][yLength];   
        
        for (int i = 0; i < heuristicBoard.length; i++) {
            for (int j = 0; j < heuristicBoard[0].length; j++) {
                if (i == 0 && j == 0 || i == 0 && j == heuristicBoard[0].length - 1 || i == heuristicBoard.length - 1 && j == 0 || i == heuristicBoard.length - 1 && j == heuristicBoard[0].length - 1) {
                    heuristicBoard[i][j] = (heuristicBoard[0].length + heuristicBoard.length) / 2;
                } else {
                    heuristicBoard[i][j] = (heuristicBoard.length - intAbs.absoluteValueOfInt(heuristicBoard.length / 2 - i) + heuristicBoard[0].length - intAbs.absoluteValueOfInt(heuristicBoard[0].length / 2 - j));
                }
                
            }
        }
        
    }
    /**
    * Method evaluates the current boards heuristic value
    * @param status is the current game's status
    * @param currentSymbol is the current player's symbol
    * @param opponentSymbol is the current player's opponent's symbol
    * @return int of the current board value
    */ 
    public int evaluate(GameStatus status, char currentSymbol, char opponentSymbol) {
        Board board = status.board;
        int heuristic = 0;
        char[][] gameBoard = board.gameBoard;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                heuristic = evaluateHeuristic(gameBoard, i, j, currentSymbol, opponentSymbol);
                if (heuristic >= 90000000) {
                    return heuristic;
                }
                if (heuristic <= -90000000) {
                    return heuristic;
                }
            }
        }
    
        return heuristic;
    }
    /**
    * Method approximates heuristic value to be added based on one square
    * @param gameBoard is the current game's board
    * @param i x coordinate
    * @param j y coordinate
    * @param currentSymbol is the current player's symbol
    * @param opponentSymbol is the current player's opponent's symbol
    * @return int of the current board value at location value
    */
    public int evaluateHeuristic(char[][] gameBoard, int i, int j, char currentSymbol, char opponentSymbol) {
        int heuristic = 0;
        if (gameBoard[i][j] == currentSymbol) {
            heuristic += heuristicBoard[i][j];
            if (!heuristicVerticalTested[i][j]) {
                heuristic += heuristicVertical(gameBoard, i, j, currentSymbol);
            }
            if (!heuristicHorizontalTested[i][j]) {
                heuristic += heuristicHorizontal(gameBoard, i, j, currentSymbol);
            }
            if (!heuristicDiagonal1Tested[i][j]) {
                heuristic += heuristicDiagonal1(gameBoard, i, j, currentSymbol);
            }
            if (!heuristicDiagonal2Tested[i][j]) {
                heuristic += heuristicDiagonal2(gameBoard, i, j, currentSymbol);
            }
        }
        if (gameBoard[i][j] == opponentSymbol) {
            heuristic -= heuristicBoard[i][j];
            if (!heuristicVerticalTested[i][j]) {
                heuristic -= heuristicVertical(gameBoard, i, j, opponentSymbol);
            }
            if (!heuristicHorizontalTested[i][j]) {
                heuristic -= heuristicHorizontal(gameBoard, i, j, opponentSymbol);
            }
            if (!heuristicDiagonal1Tested[i][j]) {
                heuristic -= heuristicDiagonal1(gameBoard, i, j, opponentSymbol);
            }
            if (!heuristicDiagonal2Tested[i][j]) {
                heuristic -= heuristicDiagonal2(gameBoard, i, j, opponentSymbol);
            }
        }
        return heuristic;
    }
   
    public int heuristicSameSymbolNearSameSymbol(char[][] gameBoard, int x, int y, char sideToBeChecked) {
        return 0;
    }
     /**
    * Method approximates heuristic value to be added based on how many of the same symbols are vertically next to each other
    * @param gameBoard is the current game's board
    * @param x x coordinate
    * @param y y coordinate
    * @param sideToBeChecked is the players symbol whose value at coordinate is checked
    * @return int of the current board value at location based on how many of the same symbols are vertically next to each other
    */
    public int heuristicVertical(char[][] gameBoard, int x, int y, char sideToBeChecked) {
        int length = 1;
        int i = x - 1;
        while (i >= 0 && gameBoard[i][y] == sideToBeChecked) {
            length++;
            heuristicVerticalTested[i][y] = true;
            i--;
        }
        i = x + 1;
        while (i < gameBoard.length && gameBoard[i][y] == sideToBeChecked) {
            length++;
            heuristicVerticalTested[i][y] = true;
            i++;
        }
        if (length >= vcl) {
            return 100000000;
        } else  if (length > 1) {
            return length * length * gameBoard.length * gameBoard[0].length;
        } else {
            return 0;
        }
    }
     /**
    * Method approximates heuristic value to be added based on how many of the same symbols are horizontally next to each other
    * @param gameBoard is the current game's board
    * @param x x coordinate
    * @param y y coordinate
    * @param sideToBeChecked is the players symbol whose value at coordinate is checked
    * @return int of the current board value at location based on how many of the same symbols are horizontally next to each other
    */
    public int heuristicHorizontal(char[][] gameBoard, int x, int y, char sideToBeChecked) {
        int length = 1;
        int j = y - 1;
        while (j >= 0 && gameBoard[x][j] == sideToBeChecked) {
            length++;
            heuristicHorizontalTested[x][j] = true;
            j--;
        }
        j = y + 1;
        while (j < gameBoard[0].length && gameBoard[x][j] == sideToBeChecked) {
            length++;
            heuristicHorizontalTested[x][j] = true;
            j++;
        }
        if (length >= vcl) {
            return 100000000;
        } else {
            return length * length * gameBoard.length * gameBoard[0].length;
        }
    }
    /**
    * Method approximates heuristic value to be added based on how many of the same symbols are diagonally next to each other
    * @param gameBoard is the current game's board
    * @param x x coordinate
    * @param y y coordinate
    * @param sideToBeChecked is the players symbol whose value at coordinate is checked
    * @return int of the current board value at location based on how many of the same symbols are diagonally next to each other
    */
    public int heuristicDiagonal1(char[][] gameBoard, int x, int y, char sideToBeChecked) {
        int length = 1;
        int i = x - 1;
        int j = y + 1;
        while (i >= 0 && j < gameBoard[0].length && gameBoard[i][j] == sideToBeChecked) {
            length++;
            heuristicDiagonal1Tested[i][j] = true;
            i--;
            j++;
        }
        i = x + 1;
        j = y - 1;
        while (i < gameBoard.length && j >= 0 && gameBoard[i][j] == sideToBeChecked) {
            length++;
            heuristicDiagonal1Tested[i][j] = true;
            i++;
            j--;
        }
        if (length >= vcl) {
            return 100000000;
        } else {
            return length * length * gameBoard.length * gameBoard[0].length;
        }
    }
     /**
    * Method approximates heuristic value to be added based on how many of the same symbols are in the other diagonal direction next to each other
    * @param gameBoard is the current game's board
    * @param x x coordinate
    * @param y y coordinate
    * @param sideToBeChecked is the players symbol whose value at coordinate is checked
    * @return int of the current board value at location based on how many of the same symbols are in the other diagonal direction next to each other
    */
    public int heuristicDiagonal2(char[][] gameBoard, int x, int y, char sideToBeChecked) {
        int length = 1;
        int i = x - 1;
        int j = y - 1;
        while (i >= 0 && j >= 0 && gameBoard[i][j] == sideToBeChecked) {
            length++;
            heuristicDiagonal2Tested[i][j] = true;
            i--;
            j--;
        }
        i = x + 1;
        j = y + 1;
        while (i < gameBoard.length && j < gameBoard[0].length && gameBoard[i][j] == sideToBeChecked) {
            length++;
            heuristicDiagonal2Tested[i][j] = true;
            i++;
            j++;
        }
        if (length >= vcl) {
            return 100000000;
        } else {
            return length * length * gameBoard.length * gameBoard[0].length;
        }
    }
}
