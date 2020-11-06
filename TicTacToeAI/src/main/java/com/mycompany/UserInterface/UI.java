/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.UserInterface;

import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import java.util.Scanner;

/**
 *
 * @author Jaakko
 */

public class UI {
    public GameStatus status;
    Scanner scanner = new Scanner(System.in);

    public UI() {
        int xLength = Integer.parseInt(scanner.nextLine()); 
        int yLength = Integer.parseInt(scanner.nextLine()); 
        int vcl = Integer.parseInt(scanner.nextLine()); 
        this.status = new GameStatus(new Board(xLength, yLength, vcl));
        System.out.println("move:");
    }
    public void UILogic() {
        while (!this.status.isBoardFull()) {
            String nextMove = scanner.nextLine(); 
            String[] split = nextMove.split("\\.");
            int[] cord = new int[2];
            cord[0] = Integer.parseInt(split[0]);
            cord[1] = Integer.parseInt(split[1]); 
            char result = this.status.setBoardValue(cord[0], cord[1]);
            System.out.println(this.status.board.toString());
            if (!(result == 0)) {
                System.out.println(result);
                break;
            }
            
        }
    }
}
