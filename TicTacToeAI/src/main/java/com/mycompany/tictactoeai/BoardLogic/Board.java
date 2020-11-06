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
public class Board {
    public char[][] gameBoard;
    public int vcl;
    public Board(int sizeX, int sizeY, int victoryConditionLength) {
        if (sizeX <= 1) {
            sizeX = 1;
        }
        if (sizeY <= 1) {
            sizeY = 1;
        }
        if (victoryConditionLength < sizeX && victoryConditionLength < sizeY) {
            if (sizeX <= sizeY) {
                victoryConditionLength = sizeY;
            } else {
                victoryConditionLength = sizeX;
            }
        }           
        this.gameBoard = new char[sizeX][sizeY];
        this.vcl = victoryConditionLength;
        setUpBoard();
    }

    public void setUpBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                gameBoard[i][j] = '_';
            }
        }
    }
    public char[][] getGameBoard() {
        return gameBoard;
    }

    public int getVcl() {
        return vcl;
    }

    @Override
    public String toString() {
        String boardToString = "";
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                boardToString += gameBoard[i][j] + " ";
            }
            boardToString += "\n";
        }
        return boardToString;
    }
}
