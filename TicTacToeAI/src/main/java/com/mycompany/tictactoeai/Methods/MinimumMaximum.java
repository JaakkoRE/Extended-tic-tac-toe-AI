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
/**
 * Class is meant for giving minimum and maximum value
 */
public class MinimumMaximum {
    /**
    * Method returns the minimum of two values´
    * 
    * @param x other int
    * @param y other int
    * 
    * @return the minimum
    */
    public int min(int x, int y) {
        if (y < x) {
            return y;
        } else {
            return x;
        }
    }
    /**
    * Method returns the maximum of two values´
    * 
    * @param x other int
    * @param y other int
    * 
    * @return the maximum
    */
    public int max(int x, int y) {
        if (y > x) {
            return y;
        } else {
            return x;
        }
    }
}
