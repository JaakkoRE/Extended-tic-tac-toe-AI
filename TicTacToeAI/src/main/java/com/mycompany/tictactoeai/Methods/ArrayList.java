/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tictactoeai.Methods;

import com.mycompany.tictactoeai.BoardLogic.GameStatus;

/**
 *
 * @author Jaakko
 */
/**
 * Class is meant for being an ArrayList like for different GameStatuses
 */
public class ArrayList {
    private GameStatus [] list;
    private int index;
    private int size;
    public ArrayList() {
        this.list = new GameStatus[0];
        this.index = 0;
        this.size = 0;
    }
    
    /**
    * Method adds GameStatus to ArrayList
    * 
    * @param gameStatus GameStatus that is added to the ArrayList
    */
    public void add(GameStatus gameStatus) {
        if (index >= size) {
            increaseSize();
        }
        list[index] = gameStatus;
        index++;

    }
    
    /**
    * Method gets GameStatus from ArrayList
    * 
    * @param i is index that is used to get ArrayList value from that index
    * 
    * @return GameStatus 
    */
    public GameStatus getValueIndex(int i) {
        return list[i];
    }
    
    /**
    * Method returns the length of the ArrayList
    *   
    * @return int length of ArrayList 
    */
    public int getLength() {
        return index;
    }
    /**
    * Method returns the ArrayList as list
    *   
    * @return GameStatus[]
    */
    public GameStatus[] getAll() {
        GameStatus[] newList = new GameStatus[index];
        for (int i = 0; i < index; i++) {
            newList[i] = list[i];
        }
        return newList;
    }
     /**
    * Method increases the size of ArrayList dynamically
    *  
    */
    private void increaseSize() {
        if (size == 0) {
            size = 1;
            this.list = new GameStatus[size];
        } else {
            GameStatus[] newList = new GameStatus[2 * size];
            for (int i = 0; i < size; i++) {
                newList[i] = list[i];
            }
            size = 2 * size;
            this.list = newList;
        }
        
    }
}
