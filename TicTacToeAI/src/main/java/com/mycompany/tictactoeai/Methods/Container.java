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
public class Container {
/**
 * Class is meant to contain hashmaps key and value;
 */
    public Board key;
    public int value;
    
    public Container(Board key, int value) {
        this.key = key;
        this.value = value;
    }
}


