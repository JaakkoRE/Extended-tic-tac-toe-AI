/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.UserInterface;

import com.mycompany.tictactoeai.AILogic.AIVSAI;
import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import com.mycompany.tictactoeai.AILogic.MinMaxAI;
import java.util.Scanner;

/**
 *
 * @author Jaakko
 */

public class UI {
    public GameStatus status;
    Scanner scanner = new Scanner(System.in);
    
    public UI() {
        
    }
 /**
 * Method takes first the 3 values of the board and then moves with . in between. For example 3 enter 3 enter 3 enter 2.2 enter
 */
 //  work in progress
    public void UILogicStart() {
        try {
            System.out.println("set board height: ");
            int xLength = Integer.parseInt(scanner.nextLine()); 
            System.out.println("set board length: ");
            int yLength = Integer.parseInt(scanner.nextLine()); 
            System.out.println("set victory row length: ");
            int vcl = Integer.parseInt(scanner.nextLine()); 
            this.status = new GameStatus(new Board(xLength, yLength, vcl));
            System.out.println("Type 's' if you want to play vs AI or 'a' if u want ai to play vs itself: ");
            String AIOrSolo = scanner.nextLine(); 
            if (AIOrSolo.equals("s")) {
                UILogicPlayerVSAI();
            }
            if (AIOrSolo.equals("a")) {
                UILogicAIVSAI();
            }
        } catch (Exception e) {
            System.out.println("Bad input (restart)");
            UILogicStart();
        }
    }
    
    public void UILogicPlayerVSAI() {
        System.out.println("if you want board positions, type 'p'");
        System.out.println("move in format (length.height):");
        MinMaxAI ai = new MinMaxAI(this.status);
        this.status = ai.alphaBetaBoard(this.status);
        System.out.println(this.status.board);
        while (!this.status.isBoardFull()) {
            while (true) {
                char rightCords = 'e';
                String nextMove = scanner.nextLine(); 
                if (nextMove.equals("p")) {
                    this.status.board.boardPositions();
                } else {
                    
                    String[] split = nextMove.split("\\.");
                    int[] cord = new int[2];
                    cord[0] = Integer.parseInt(split[0]);
                    cord[1] = Integer.parseInt(split[1]); 
            
                    rightCords = this.status.setBoardValue(cord[0], cord[1]);
                }
                if (rightCords != 'e') {
                    break;
                }
                if (!nextMove.equals("p")) {
                    System.out.println("invalid position");
                }
            }
            System.out.println(this.status.board.toString());
            char result = status.checkAll();
            if (!(result == 0)) {
                System.out.println(result);
                break;
            } 
            
            this.status = ai.alphaBetaBoard(this.status);
            System.out.println(this.status.board.toString());
            result = status.checkAll();
            if (!(result == 0)) {
                System.out.println(result);
                break;
            }
            System.out.println(status.checkAll());
            
        }
    }
    public void UILogicAIVSAI() {
        System.out.println("Use HashMap for board value heuristics? y/n");
        String hashMapOrNo = scanner.nextLine(); 
        boolean hashMapBool = false;
        if (hashMapOrNo.equals("y")) {
            hashMapBool = true;
        }        
        AIVSAI AIVersus = new AIVSAI();
        AIVersus.AIVSAIPlay(status, hashMapBool, true);
    }
    
    

}
