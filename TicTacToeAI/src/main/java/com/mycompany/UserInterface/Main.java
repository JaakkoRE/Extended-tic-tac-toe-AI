/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.UserInterface;

import com.mycompany.UserInterface.GUI;
import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import com.mycompany.tictactoeai.AILogic.MinMaxAI;

/**
 *
 * @author Jaakko
 */
public class Main {
    public static void main(String[] args) {
        GameStatus status = new GameStatus(new Board(3,3,3),'O');
        status.board.changeBoardValue(0, 0, 'X');
        status.board.changeBoardValue(0, 1, 'O');
        status.board.changeBoardValue(0, 2, 'X');
        status.board.changeBoardValue(1, 0, 'X');
        status.board.changeBoardValue(1, 1, 'X');
        status.board.changeBoardValue(1, 2, 'O');
        status.board.changeBoardValue(2, 0, 'O');
        status.board.changeBoardValue(2, 1, 'X');
        status.board.changeBoardValue(2, 2, 'O');
        System.out.println(status.board.toString());
        GUI u = new GUI();
        UI u2 = new UI();
        u2.UILogic();
      //  MinMaxAI ai = new MinMaxAI(new GameStatus(new Board(2,2,1)));
      //  System.out.println(ai.alphaBetaValue());
        
    }

}
