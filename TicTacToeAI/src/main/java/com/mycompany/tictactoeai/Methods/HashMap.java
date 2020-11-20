/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tictactoeai.Methods;

import com.mycompany.tictactoeai.BoardLogic.Board;

/**
 *
 * @author Jaakko
 */

/**
 * Class is meant to be HashMap where keys are boards and values are heuristic value of the key board.
 */
public class HashMap {

    private ArrayListContainer[] recordList;
    
    public HashMap() {		
        this.recordList = new ArrayListContainer[10000000];
        for (int i = 0; i < recordList.length; i++) {
            recordList[i] = new ArrayListContainer();
        }
                
    }
    /**
    * Method adds board with value to hashmap
    * 
    * @param key board that is the key of the hashmap
    * @param value is int of the heuristic value of the board
    */
    public void put(Board key, int value) {
        boolean itemExists = false;
        int index = key.hashCode();
        ArrayListContainer bucket = recordList[index];
        for (int i = 0; i < bucket.getLength(); i++) {
            Container cont = bucket.getValueIndex(i);
            if (cont.key.equals(key)) {
                cont.value = value;
                itemExists = true;
                break;
            }
        }
        if (!itemExists) {
            bucket.add(new Container(key, value));
        }
    }
	
    public int get(Board key) {
        int index = key.hashCode();
        ArrayListContainer bucket = recordList[index];
        for (int i = 0; i < bucket.getLength(); i++) {
            Container cont = bucket.getValueIndex(i);
            if (cont.key.equals(key)) {
                return cont.value;
            }
        }
        // if there is no Board value. Just a random negative int.
        return -7777;
    }   
}
