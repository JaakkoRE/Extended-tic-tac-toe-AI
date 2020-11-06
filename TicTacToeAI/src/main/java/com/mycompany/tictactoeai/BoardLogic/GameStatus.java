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
public class GameStatus {
    public Board board;
    public char[][] gameBoard;
    public int vcl;
    public char currentPlayer;
    public GameStatus(Board board) {
        this.board = board;
        this.gameBoard = board.getGameBoard();
        this.vcl = board.getVcl();
        this.currentPlayer = 'O';
    }
    
    public char setBoardValue(int x, int y) {
        if (x >= 0 && x < gameBoard.length && y >= 0 && y < gameBoard[0].length) {
            if (gameBoard[x][y] == ('_')) {
                gameBoard[x][y] = currentPlayer;
                if (currentPlayer == 'O') {
                    currentPlayer = 'X';
                } else {
                    currentPlayer = 'O';
                }
            }
        }
        return victoryCheck(x, y);
    }
    public boolean isBoardFull() {
        this.gameBoard = board.getGameBoard();
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                if ((gameBoard[i][j] == '_')) {
                    return false;
                }
            }
        }
        return true;
    }
    public char victoryCheck(int x, int y) {
        this.gameBoard = board.getGameBoard();
        char sideToBeChecked = gameBoard[x][y]; 
        char returnValue = horizontalVictoryCheck(x, y, sideToBeChecked);
        if (!(returnValue == 'n')) {
            return returnValue;
        }
        returnValue = verticalVictoryCheck(x, y, sideToBeChecked);
        if (!(returnValue == 'n')) {
            return returnValue;
        }
        returnValue = diagonalVictoryCheck1(x, y, sideToBeChecked);
        if (!(returnValue == 'n')) {
            return returnValue;
        }
        returnValue = diagonalVictoryCheck2(x, y, sideToBeChecked);
        if (!(returnValue == 'n')) {
            return returnValue;
        }
        System.out.println(returnValue);
        return 0;
    }
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
            return 'n';
        }
    }
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
            return 'n';
        }
    }
     
    public char diagonalVictoryCheck1(int x, int y,  char sideToBeChecked) {
        int length = 1;
        int i = x - 1;
        int j = y + 1;
        while (i >= 0 && j < gameBoard[0].length && gameBoard[i][j] == sideToBeChecked) {
            length++;
            i--;
            y++;
        }
        i = x + 1;
        j = y - 1;
        while (i < gameBoard.length && j >= 0 && gameBoard[i][j] == sideToBeChecked) {
            length++;
            i++;
            y--;
        }
        if (length >= vcl) {
            return sideToBeChecked;
        } else {
            return 'n';
        }
    }
    
    public char diagonalVictoryCheck2(int x, int y,  char sideToBeChecked) {
        int length = 1;
        int i = x - 1;
        int j = y - 1;
        while (i >= 0 && j >= 0 && gameBoard[i][j] == sideToBeChecked) {
            length++;
            i--;
            y--;
        }
        i = x + 1;
        j = y + 1;
        while (i < gameBoard.length && j < gameBoard[0].length && gameBoard[i][j] == sideToBeChecked) {
            length++;
            i++;
            y++;
        }
        if (length >= vcl) {
            return sideToBeChecked;
        } else {
            return 'n';
        }
    }
}
