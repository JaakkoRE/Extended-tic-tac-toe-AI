/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tictactoeai.AILogic;

import com.mycompany.tictactoeai.BoardLogic.GameStatus;

/**
 *
 * @author Jaakko
 */
public class AIVSAI {

    public void AIVSAIPlay(GameStatus status, boolean hashMapBool, boolean print) {
        long Start = System.nanoTime();
        MinMaxAI ai = new MinMaxAI(status, hashMapBool);
        while (!status.isBoardFull()) {
            status = ai.alphaBetaBoard(status);
            if (print) {
                System.out.println(status.board.toString());
            }
            char result = status.checkAll();
            if (!(result == 0)) {
                System.out.println(result);
                break;
            } 
            
            status = ai.alphaBetaBoard(status);
            if (print) {
                System.out.println(status.board.toString());
            }
            result = status.checkAll();
            if (!(result == 0)) {
                System.out.println(result);
                break;
            }
        }
        long end = System.nanoTime();
        System.out.println("Time to calculate: " + ((end - Start) / 1e9) + " s");


    }
}
