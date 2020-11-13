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
public class AbsoluteValue {
    /**
    * Method returns the absolute value
    * 
    * @param i given int 
    * 
    * @return int absolute value of i
    */
    public int absoluteValueOfInt(int i) {
        if (i < 0) {
            return -1 * i;
        } else {
            return i;
        }
    }
}
