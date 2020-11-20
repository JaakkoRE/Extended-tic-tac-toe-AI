/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tictactoeai.Methods;


/**
 *
 * @author Jaakko
 */
public class ArrayListContainer {

    


    private Container [] list;
    private int index;
    private int size;
    public ArrayListContainer() {
        this.list = new Container[0];
        this.index = 0;
        this.size = 0;
    }
    
    /**
    * Method adds Containers with key (hash) and int value to ArrayList
    * Used in hashMap method
    * 
    * @param container Container that is added to the ArrayList
    */
    public void add(Container container) {
        if (index >= size) {
            increaseSize();
        }
        list[index] = container;
        index++;

    }
    
    /**
    * Method gets GameStatus from ArrayList
    * 
    * @param ind is index that is used to get ArrayList value from that index
    * 
    * @return GameStatus 
    */
    public Container getValueIndex(int ind) {
        
        return list[ind];
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
    * @return Container[] list
    */
    public Container[] getAll() {
        Container[] newList = new Container[index];
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
            this.list = new Container[size];
        } else {
            Container[] newList = new Container[2 * size];
            for (int i = 0; i < size; i++) {
                newList[i] = list[i];
            }
            size = 2 * size;
            this.list = newList;
        }
        
    }
     /**
    * Method removes value from index
    *  @param ind index
    */
    public void removeIndex(int ind) {
        if (ind > index) {
            return;
        }
        Container[] newList = new Container[index];
        for (int i = 0; i < ind; i++) {
            newList[i] = list[i];
        }
        for (int i = ind + 1; i < index; i++) {
            newList[i - 1] = list[i];
        }
        index--;
        list = newList;
    }
}


