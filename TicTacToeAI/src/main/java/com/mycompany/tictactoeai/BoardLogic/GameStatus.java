/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tictactoeai.BoardLogic;

/**
 *
 * @author Jaakko
 */

/**
 * Class stores the status of the game, the board and gives operations on checking the status of the game
 */
public class GameStatus {
    public Board board;
    public char[][] gameBoard;
    public int vcl;
    public char currentPlayer;
    
    public GameStatus(Board board, char currentPlayer) {
        this.board = board;
        this.gameBoard = board.getGameBoard();
        this.vcl = board.getVcl();
        this.currentPlayer = currentPlayer;
    }
    public GameStatus(Board board) {
        this.board = board;
        this.gameBoard = board.getGameBoard();
        this.vcl = board.getVcl();
        this.currentPlayer = 'O';
    }
    public GameStatus copyGameStatus() {
        return new GameStatus(board.copyBoard(), currentPlayer);
    }
    /**
    * Method returns the other player than the current player 
    * @return the other player
    */ 
    public char otherPlayer() {
        if (this.currentPlayer == 'O') {
            return 'X';
        } else {
            return 'O';
        }
    }
    /**
    * Method sets value to the board according to the coordinates and the current user
    * @param x sets the x coordinates
    * @param y sets the y coordinates
    * @return the status of the game
    */ 
    public char setBoardValue(int x, int y) {
        boolean BoolCouldChangeValue = this.board.changeBoardValue(x, y, currentPlayer);
        if (BoolCouldChangeValue) {
            if (currentPlayer == 'O') {
                currentPlayer = 'X';
            } else {
                currentPlayer = 'O';
            } 
            this.gameBoard = board.getGameBoard();
        } else {
            return 'e';
        }
        return victoryCheck(x, y);
    }
    /**
    * Method checks if the board is filled, which means that there are no empty '_' chars
    * @return true if board is full else false
    */ 
    public boolean isBoardFull() {
        if (board.boardSize == board.boardfulfillment) {
            return true;
        }
        return false;
//System.out.println(board.boardSize + " ja " + board.boardfulfillment);
//        this.gameBoard = board.getGameBoard();
//        for (int i = 0; i < gameBoard.length; i++) {
//            for (int j = 0; j < gameBoard[0].length; j++) {
//                if ((gameBoard[i][j] == '_')) {
//                    return false;
//                }
//            }
//        }
//        return true;
    }
    /**
    * Method checks all points of the board and tells the status of the game
    * @return the status of the game
    */ 
    public char checkAll() {
       
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                if (!(victoryCheck(i, j) == 0)) {
                    return victoryCheck(i, j);
                }
            }
        }
        if (isBoardFull()) {
            return 'n';
        }
        return 0;
    }
    /**
    * Method checks points from x,y fullfill the victory condition
    * @return returns 0 if no victory, else the victors symbol
    */ 
    public char victoryCheck(int x, int y) {
        this.gameBoard = board.getGameBoard();
        char sideToBeChecked = gameBoard[x][y]; 
        if (sideToBeChecked == '_') {
            return 0;
        }
        char returnValue = horizontalVictoryCheck(x, y, sideToBeChecked);
        if (!(returnValue == 0)) {
            return returnValue;
        }
        returnValue = verticalVictoryCheck(x, y, sideToBeChecked);
        if (!(returnValue == 0)) {
            return returnValue;
        }
        returnValue = diagonalVictoryCheck1(x, y, sideToBeChecked);
        if (!(returnValue == 0)) {
            return returnValue;
        }
        returnValue = diagonalVictoryCheck2(x, y, sideToBeChecked);
        if (!(returnValue == 0)) {
            return returnValue;
        }
        return 0;
    }
    /**
    * Method checks the horizontal victory condition from the point
    * @return returns n if no victory, else the victors symbol
    */ 
    public char horizontalVictoryCheck(int x, int y, char sideToBeChecked) {
        int length = 1;
        int i = x - 1;
        while (i >= 0 && gameBoard[i][y] == sideToBeChecked) {
            length++;
            i--;
        }
        i = x + 1;
        while (i < gameBoard.length && gameBoard[i][y] == sideToBeChecked) {
            length++;
            i++;
        }
        if (length >= vcl) {
            return sideToBeChecked;
        } else {
            return 0;
        }
    }
    /**
    * Method checks the vertical victory condition from the point
    * @return returns n if no victory, else the victors symbol
    */ 
    public char verticalVictoryCheck(int x, int y,  char sideToBeChecked) {
        int length = 1;
        int j = y - 1;
        while (j >= 0 && gameBoard[x][j] == sideToBeChecked) {
            length++;
            j--;
        }
        j = y + 1;
        while (j < gameBoard[0].length && gameBoard[x][j] == sideToBeChecked) {
            length++;
            j++;
        }
        if (length >= vcl) {
            return sideToBeChecked;
        } else {
            return 0;
        }
    }
     
    /**
    * Method checks the first diagonal victory condition from the point
    * @return returns n if no victory, else the victors symbol
    */ 
    public char diagonalVictoryCheck1(int x, int y,  char sideToBeChecked) {
        int length = 1;
        int i = x - 1;
        int j = y + 1;
        while (i >= 0 && j < gameBoard[0].length && gameBoard[i][j] == sideToBeChecked) {
            length++;
            i--;
            j++;
        }
        i = x + 1;
        j = y - 1;
        while (i < gameBoard.length && j >= 0 && gameBoard[i][j] == sideToBeChecked) {
            length++;
            i++;
            j--;
        }
        if (length >= vcl) {
            return sideToBeChecked;
        } else {
            return 0;
        }
    }
    
    /**
    * Method checks the second diagonal victory condition from the point
    * @return returns n if no victory, else the victors symbol
    */ 
    public char diagonalVictoryCheck2(int x, int y,  char sideToBeChecked) {
        int length = 1;
        int i = x - 1;
        int j = y - 1;
        while (i >= 0 && j >= 0 && gameBoard[i][j] == sideToBeChecked) {
            length++;
            i--;
            j--;
        }
        i = x + 1;
        j = y + 1;
        while (i < gameBoard.length && j < gameBoard[0].length && gameBoard[i][j] == sideToBeChecked) {
            length++;
            i++;
            j++;
        }
        if (length >= vcl) {
            return sideToBeChecked;
        } else {
            return 0;
        }
    }
}
