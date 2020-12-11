/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tictactoeai.AILogic;

/**
 *
 * @author Jaakko
 */

/**
 * Class tells the minmax ai how deep the recursion can go (endless recursions on larger boards take too long to process).
 */
public class DepthStop {
    /**
    * Method tells how to deep to check (how many boards are checked) before estimating the board value using heuristics
    *  @param boardSize size of the board
    * @return depth
    */
    public int depthStopStart(int boardSize) {
        int depthWhereToCalculateHeuristic = 9999;
        if (boardSize >= 10) {
            if (boardSize <= 20) {
                depthWhereToCalculateHeuristic = 7;  
            } else if (boardSize <= 25) {
                depthWhereToCalculateHeuristic = 6;  
            } else if (boardSize <= 30) {
                depthWhereToCalculateHeuristic = 5; 
            } else if (boardSize <= 60) {
                depthWhereToCalculateHeuristic = 4;              
            
            } else if (boardSize <= 121) {
                depthWhereToCalculateHeuristic = 3;  // decent            
            } else if (boardSize <= 324) {
                depthWhereToCalculateHeuristic = 2;  // pretty bad
            } else {
                depthWhereToCalculateHeuristic = 1; // very imprecise, practically useless
            }       
        } 
        return depthWhereToCalculateHeuristic;
    }  
    /**
    * Method corrects the algorithm on the depth. As boardsize gets smaller the deeper the recursion can go before significant slow down. 
    *  @param  size boards size
    *  @param fullfillment how full the board is
    *  @param oldDepthWhereToCalculateHeuristic old depth that is being corrected
    * 
    *  @return depth
    */
    public int depthStopCorrection(int size, int fullfillment, int oldDepthWhereToCalculateHeuristic) {
       //   System.out.print(oldDepthWhereToCalculateHeuristic + " hö ");
        int depthWhereToCalculateHeuristic = 9999;
        if (size - fullfillment >= 9) {
            if (size - fullfillment <= 11) {
                depthWhereToCalculateHeuristic = 7;  
            } else if (size - fullfillment <= 14) {
                depthWhereToCalculateHeuristic = 6;  
            } else if (size - fullfillment <= 16) {
                depthWhereToCalculateHeuristic = 5; 
            } else if (size - fullfillment <= 25) {
                depthWhereToCalculateHeuristic = 4;              
            
            } else if (size - fullfillment <= 90) {
                depthWhereToCalculateHeuristic = 3;              
            } else if (size - fullfillment <= 250) {
                depthWhereToCalculateHeuristic = 2;  
            } else {
                depthWhereToCalculateHeuristic = 1;
            }       
        }
      //  System.out.println(depthWhereToCalculateHeuristic + " ha");
        if (depthWhereToCalculateHeuristic >= oldDepthWhereToCalculateHeuristic) {
            return depthWhereToCalculateHeuristic;
        }
        
        return oldDepthWhereToCalculateHeuristic;
    }
}