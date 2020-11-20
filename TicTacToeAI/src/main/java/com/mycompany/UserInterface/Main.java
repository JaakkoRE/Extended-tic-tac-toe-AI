/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.UserInterface;

import com.mycompany.UserInterface.GUI;
import com.mycompany.tictactoeai.AILogic.Heuristic;
import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import com.mycompany.tictactoeai.AILogic.MinMaxAI;
import com.mycompany.tictactoeai.Methods.ArrayList;
import com.mycompany.tictactoeai.Methods.HashMap;

/**
 *
 * @author Jaakko
 */
public class Main {
    public static void main(String[] args) {
       
        GUI u = new GUI();
        UI u2 = new UI();
        u2.UILogicStart();
      //  MinMaxAI ai = new MinMaxAI(new GameStatus(new Board(2,2,1)));
      //  System.out.println(ai.alphaBetaValue());
        
    }

}
