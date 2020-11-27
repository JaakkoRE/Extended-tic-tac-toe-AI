/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tictactoeai.AILogic;

import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import com.mycompany.tictactoeai.Methods.AbsoluteValue;
import com.mycompany.tictactoeai.Methods.HashMap;

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
    public HashMap heuristicValuesMap;
    public int xLength;
    public int yLength;

    public int vcl;

    public Heuristic(Board board) {
        this.heuristicValuesMap = new HashMap();
        this.xLength = board.sizex;
        this.yLength = board.sizey;
        this.vcl = board.vcl;
        heuristicBaseSetUp();
        
        heuristicVerticalTested = new boolean[xLength][yLength];
        heuristicHorizontalTested = new boolean[xLength][yLength];
        heuristicDiagonal1Tested = new boolean[xLength][yLength];
        heuristicDiagonal2Tested = new boolean[xLength][yLength];
        heuristicSameSymbolNear = new boolean[xLength][yLength];   
    }
    /**
    * Method sets value to the board to give base values of locations based on coordinates. 
    */ 
    public void heuristicBaseSetUp() {
        AbsoluteValue intAbs = new AbsoluteValue();
        heuristicBoard = new int[xLength][yLength];        
        for (int i = 0; i < heuristicBoard.length; i++) {
            for (int j = 0; j < heuristicBoard[0].length; j++) {
                if (i == 0 && j == 0 || i == 0 && j == heuristicBoard[0].length - 1 || i == heuristicBoard.length - 1 && j == 0 || i == heuristicBoard.length - 1 && j == heuristicBoard[0].length - 1) {
                    //corners are bad
                    heuristicBoard[i][j] = (heuristicBoard[0].length + heuristicBoard.length) / 2;
                } else {
                    //the closer to the middle, the better.
                    heuristicBoard[i][j] = (heuristicBoard.length - intAbs.absoluteValueOfInt(heuristicBoard.length / 2 - i) + heuristicBoard[0].length - intAbs.absoluteValueOfInt(heuristicBoard[0].length / 2 - j));
                   
                }
            }
        }
        
    }
    /**
    * Method evaluates the current boards heuristic value
    * @param status is the current game's status
    * @param useHashMap tells if the heuristic values should be safed for later use in case the board has already been calculated (generally slows performance wip).
    * @param currentSymbol is the current player's symbol
    * @param opponentSymbol is the current player's opponent's symbol
    * @return int of the current board value
    */ 
    public int evaluate(GameStatus status, char currentSymbol, char opponentSymbol, boolean useHashMap) {
        Board board = status.board;
        //-7777 random int from HashMap
        if (useHashMap && heuristicValuesMap.get(board) != -7777) {
            return heuristicValuesMap.get(board);
        }
        int heuristic = 0;
        char[][] gameBoard = board.gameBoard;
        heuristicVerticalTested = new boolean[xLength][yLength];
        heuristicHorizontalTested = new boolean[xLength][yLength];
        heuristicDiagonal1Tested = new boolean[xLength][yLength];
        heuristicDiagonal2Tested = new boolean[xLength][yLength];
        heuristicSameSymbolNear = new boolean[xLength][yLength];   
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                heuristic += evaluateHeuristic(gameBoard, i, j, currentSymbol, opponentSymbol);
                //if heuristic is so strong (win or lose guaranteed) no need to calculate further
                //But this shouldn't happen since victory is checked with victorycheck with every board
                if (heuristic >= 90000000) {
                    return 1000000000;
                }
                if (heuristic <= -90000000) {
                    return -1000000000;
                }
            }
        }
        if (useHashMap) {
            heuristicValuesMap.put(board, heuristic);
        }
        return heuristic;
    }
    /**
    * Method approximates heuristic value to be added based on one square. Uses the other heuristic methods to determine value.
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
            heuristic += heuricticDirections(gameBoard, i, j, currentSymbol);
            heuristic += heuristicHowManyWaysOfWinning(gameBoard, i, j);
            if (!heuristicSameSymbolNear[i][j]) {
                heuristic += heuristicSameSymbolNearSameSymbol(gameBoard, i, j, currentSymbol, 0);
            }
        }
        if (gameBoard[i][j] == opponentSymbol) {
            heuristic -= heuristicBoard[i][j];
            heuristic -= heuricticDirections(gameBoard, i, j, opponentSymbol);
            heuristic -= heuristicHowManyWaysOfWinning(gameBoard, i, j);
            if (!heuristicSameSymbolNear[i][j]) {
                heuristic -= heuristicSameSymbolNearSameSymbol(gameBoard, i, j, opponentSymbol, 0);
            }
        }
        return heuristic;
    }
    /**
    * Method gives points when there are big clusters of the same symbol even if the symbols aren't in long lines.
    * @param gameBoard is the current game's board
    * @param x x coordinate
    * @param y y coordinate
    * @param heuristic value that is send forwards
    * @param sideToBeChecked is the current symbol that is checked
    * @return int of the current board value at location value
    */
    public int heuristicSameSymbolNearSameSymbol(char[][] gameBoard, int x, int y, char sideToBeChecked, int heuristic) {
        if (x - 1 >= 0 && gameBoard[x - 1][y] == sideToBeChecked && !heuristicSameSymbolNear[x - 1][y]) {
            heuristicSameSymbolNear[x - 1][y] = true;
            heuristic += heuristicSameSymbolNearSameSymbol(gameBoard, x - 1, y, sideToBeChecked, heuristic + gameBoard.length * gameBoard[0].length);
        }
        if (x + 1 < gameBoard.length && gameBoard[x + 1][y] == sideToBeChecked && !heuristicSameSymbolNear[x + 1][y]) {
            heuristicSameSymbolNear[x + 1][y] = true;
            heuristic += heuristicSameSymbolNearSameSymbol(gameBoard, x + 1, y, sideToBeChecked, heuristic + gameBoard.length * gameBoard[0].length);
        }
        
        if (y - 1 >= 0 && gameBoard[x][y - 1] == sideToBeChecked && !heuristicSameSymbolNear[x][y - 1]) {
            heuristicSameSymbolNear[x][y - 1] = true;
            heuristic += heuristicSameSymbolNearSameSymbol(gameBoard, x, y - 1, sideToBeChecked, heuristic + gameBoard.length * gameBoard[0].length);
        }
        if (y + 1 < gameBoard[0].length && gameBoard[x][y + 1] == sideToBeChecked && !heuristicSameSymbolNear[x][y + 1]) {
            heuristicSameSymbolNear[x][y + 1] = true;
            heuristic += heuristicSameSymbolNearSameSymbol(gameBoard, x, y + 1, sideToBeChecked, heuristic + gameBoard.length * gameBoard[0].length);
        }
        
        if (x - 1 >= 0 && y - 1 >= 0 && gameBoard[x - 1][y - 1] == sideToBeChecked && !heuristicSameSymbolNear[x - 1][y - 1]) {
            heuristicSameSymbolNear[x - 1][y - 1] = true;
            heuristic += heuristicSameSymbolNearSameSymbol(gameBoard, x - 1, y - 1, sideToBeChecked, heuristic + gameBoard.length * gameBoard[0].length);
        }
        if (x + 1 < gameBoard.length && y + 1 < gameBoard[0].length && gameBoard[x + 1][y + 1] == sideToBeChecked && !heuristicSameSymbolNear[x + 1][y + 1]) {
            heuristicSameSymbolNear[x + 1][y + 1] = true;
            heuristic += heuristicSameSymbolNearSameSymbol(gameBoard, x + 1, y + 1, sideToBeChecked, heuristic + gameBoard.length * gameBoard[0].length);
        }
        
        if (x - 1 >= 0 && y + 1 < gameBoard[0].length && gameBoard[x - 1][y + 1] == sideToBeChecked && !heuristicSameSymbolNear[x - 1][y + 1]) {
            heuristicSameSymbolNear[x - 1][y + 1] = true;
            heuristic += heuristicSameSymbolNearSameSymbol(gameBoard, x - 1, y + 1, sideToBeChecked, heuristic + gameBoard.length * gameBoard[0].length);
        }
        if (x + 1 < gameBoard.length && y - 1 >= 0 && gameBoard[x + 1][y - 1] == sideToBeChecked && !heuristicSameSymbolNear[x + 1][y - 1]) {
            heuristicSameSymbolNear[x + 1][y - 1] = true;
            heuristic += heuristicSameSymbolNearSameSymbol(gameBoard, x + 1, y - 1, sideToBeChecked, heuristic + gameBoard.length * gameBoard[0].length);
        }
        return heuristic;
    }
    /**
    * Method gives points if there is possibility of creating a winning line from the point from a direction. More directions, more points.
    * @param gameBoard is the current game's board
    * @param x x coordinate
    * @param y y coordinate
    * @return int of the current board value at location value
    */
    public int heuristicHowManyWaysOfWinning(char[][] gameBoard, int x, int y) {
        int waysOfWinning = 0;
        if (x - 1 >= 0 && gameBoard[x - 1][y] == '_') {
            if (heuristicVertical(gameBoard, x - 1, y, '_') >= vcl) {
                waysOfWinning++;
            }
        }
        if (x + 1 < gameBoard.length && gameBoard[x + 1][y] == '_') {
            if (heuristicVertical(gameBoard, x + 1, y, '_') >= vcl) {
                waysOfWinning++;
            }        
        }       
        if (y - 1 >= 0 && gameBoard[x][y - 1] == '_') {
            if (heuristicHorizontal(gameBoard, x, y - 1, '_') >= vcl) {
                waysOfWinning++;
            } 
        }
        if (y + 1 < gameBoard[0].length && gameBoard[x][y + 1] == '_') {
            if (heuristicHorizontal(gameBoard, x, y + 1, '_') >= vcl) {
                waysOfWinning++;
            }
        }
        if (x - 1 >= 0 && y + 1 < gameBoard[0].length && gameBoard[x - 1][y + 1] == '_') {
            if (heuristicDiagonal1(gameBoard, x - 1, y + 1, '_') >= vcl) {
                waysOfWinning++;
            }
        }
        if (x + 1 < gameBoard.length && y - 1 >= 0 && gameBoard[x + 1][y - 1] == '_') {
            if (heuristicDiagonal1(gameBoard, x + 1, y - 1, '_') >= vcl) {
                waysOfWinning++;
            }
        }        
        if (x - 1 >= 0 && y - 1 >= 0 && gameBoard[x - 1][y - 1] == '_') {
            if (heuristicDiagonal2(gameBoard, x - 1, y - 1, '_') >= vcl) {
                waysOfWinning++;
            }        
        }
        if (x + 1 < gameBoard.length && y + 1 < gameBoard[0].length && gameBoard[x + 1][y + 1] == '_') {
            if (heuristicDiagonal2(gameBoard, x + 1, y + 1, '_') >= vcl) {
                waysOfWinning++;
            }
        }       
        
        return waysOfWinning * vcl * vcl;
    }
    /**
    * Method gives points when there are long straight lines of the same symbol. 
    * @param gameBoard is the current game's board
    * @param x x coordinate
    * @param y y coordinate
    * @param sideToBeChecked is the current symbol that is checked
    * @return int of the current board value at location value
    */
    public int heuricticDirections(char[][] gameBoard, int x, int y, char sideToBeChecked) {
        int heuristic = 0;
        int length;
        if (!heuristicVerticalTested[x][y]) {
            length = heuristicVertical(gameBoard, x, y, sideToBeChecked);
            if (length >= vcl) {
                return 100000000;
            } else {
                heuristic += length * length * length * gameBoard.length * gameBoard[0].length;
            } 
        }
        if (!heuristicHorizontalTested[x][y]) {
            length = heuristicHorizontal(gameBoard, x, y, sideToBeChecked);
            if (length >= vcl) {
                return 100000000;
            } else {
                heuristic += length * length * gameBoard.length * gameBoard[0].length / vcl;
            }
        }
        if (!heuristicDiagonal1Tested[x][y]) {
            length = heuristicDiagonal1(gameBoard, x, y, sideToBeChecked);
            if (length >= vcl) {
                return 100000000;
            } else {
                heuristic += length * length * gameBoard.length * gameBoard[0].length / vcl;
            }
        }
        if (!heuristicDiagonal2Tested[x][y]) {
            length = heuristicDiagonal2(gameBoard, x, y, sideToBeChecked);
            if (length >= vcl) {
                return 100000000;
            } else {
                heuristic += length * length * gameBoard.length * gameBoard[0].length / vcl;
            }
        }
        return heuristic;
    }
    
     /**
    * Method tells the length of the same symbol based on how many of the same symbols are vertically next to each other
    * @param gameBoard is the current game's board
    * @param x x coordinate
    * @param y y coordinate
    * @param sideToBeChecked is the players symbol whose value at coordinate is checked
    * @return int of the vertical length of the same symbol.
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
        return length;
    }
    /**
    * Method tells the length of the same symbol based on how many of the same symbols are horizontally next to each other
    * @param gameBoard is the current game's board
    * @param x x coordinate
    * @param y y coordinate
    * @param sideToBeChecked is the players symbol whose value at coordinate is checked
    * @return int of the horizontal length of the same symbol.
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
        return length;
    }
    /**
    * Method tells the length of the same symbol based on how many of the same symbols are diagonally in one direction next to each other
    * @param gameBoard is the current game's board
    * @param x x coordinate
    * @param y y coordinate
    * @param sideToBeChecked is the players symbol whose value at coordinate is checked
    * @return int of the diagonal length of the same symbol.
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
        return length;
    }
    /**
    * Method tells the length of the same symbol based on how many of the same symbols are diagonally in the other direction next to each other
    * @param gameBoard is the current game's board
    * @param x x coordinate
    * @param y y coordinate
    * @param sideToBeChecked is the players symbol whose value at coordinate is checked
    * @return int of the diagonal length of the same symbol.
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
        return length;
    }


}
