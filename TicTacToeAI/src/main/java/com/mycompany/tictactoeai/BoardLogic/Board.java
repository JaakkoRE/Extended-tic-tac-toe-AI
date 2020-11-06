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
 * Class is storing and giving basic operations of the gameboard of tic-tac-toe
 */
public class Board {
    public char[][] gameBoard;
    public int vcl;
    public int sizex;
    public int sizey;
    public Board(int sizeX, int sizeY, int victoryConditionLength) {
        if (sizeX <= 1) {
            sizeX = 1;
        }
        if (sizeY <= 1) {
            sizeY = 1;
        }
        this.sizex = sizeX;
        this.sizey = sizeY;
        if (victoryConditionLength > sizeX && victoryConditionLength > sizeY) {
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
    
    /**
    * Method changes an empty slot in the tic-tac-toe board to players symbol
    * 
    * @param x int that gives the x coordinates
    * @param y int that gives the y coordinates
    * @param symbol char that tells which symbol will be put in the slot
    * 
    * @return boolean that confirms that the change was succesful or not
    */
    public boolean changeBoardValue(int x, int y, char symbol) {
        if (x >= 0 && x < gameBoard.length && y >= 0 && y < gameBoard[0].length) {
            if (gameBoard[x][y] == ('_')) {
                this.gameBoard[x][y] = symbol;
                return true;
            }
        }
        return false;
    }
    /**
    * Method sets an empty board
    * 
    */
    public void setUpBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                gameBoard[i][j] = '_';
            }
        }
    }
    /**
    * Method returns the board as an two dimensional list
    * 
    * @return char[][] char list with all symbols in the board
    */
    public char[][] getGameBoard() {
        return gameBoard;
    }
    /**
    * Method returns victory length condition
    * 
    * @return returns int that is the victory length condition
    */
    public int getVcl() {
        return vcl;
    }
    /**
    * Method returns a copy of the current board
    * 
    * @return Board that is the copy of the current board
    */
    public Board copyBoard() {
        Board returnBoard = new Board(sizex, sizey, vcl);
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                returnBoard.gameBoard[i][j] = gameBoard[i][j];
            }
        }
        return returnBoard;
    }
    /**
    * Method returns a string of the board
    * 
    * @return String which displays all of the symbols in the board
    */
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
