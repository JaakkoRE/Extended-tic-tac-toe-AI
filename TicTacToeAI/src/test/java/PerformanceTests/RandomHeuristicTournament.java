/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PerformanceTests;

import com.mycompany.tictactoeai.AILogic.MinMaxAI;
import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import org.junit.Test;

/**
 *
 * @author Jaakko
 */

//tests if there are better combinations
// default values are 1 1 2 1 since that is the current configuration
public class RandomHeuristicTournament {
    
    public MinMaxAI topContender;
    public MinMaxAI topContender2;
    public boolean change;
    
 
    // @Test
    public void testBoard1() {
        System.out.println("test 1");
        GameStatus testStatus = new GameStatus(new Board(10,10,5),'O');
        topContender = new MinMaxAI(testStatus);
        change = false;
        int i = 0;
        while (i < 25) {
            i++;
            System.out.println("round " + i);
            testIfXWins(new GameStatus(new Board(10,10,5),'O'), topContender);
            if (change == true) {
                i = 0;
                 change = false;
            }
        }
        System.out.println("Best: ");
        System.out.println(topContender.heuristic.Val1 + " " + topContender.heuristic.Val2 + " " + topContender.heuristic.Val3 + " " + topContender.heuristic.Val4);         
    }
     @Test
    public void testBoard2() {
        System.out.println("test 2");
        GameStatus testStatus = new GameStatus(new Board(8,8,4),'O');
        topContender = new MinMaxAI(testStatus);
        change = false;
        int i = 0;
        while (i < 20) {
            i++;
            System.out.println("round " + i);
            testIfXWins(new GameStatus(new Board(8,8,4),'O'), topContender);
            if (change == true) {
                i = 0;
                change = false;
            }
        }
        System.out.println("Best: ");
        System.out.println(topContender.heuristic.Val1 + " " + topContender.heuristic.Val2 + " " + topContender.heuristic.Val3 + " " + topContender.heuristic.Val4);

    }
     @Test
    public void testBoard3() {
        System.out.println("test 3");
        GameStatus testStatus = new GameStatus(new Board(7,7,4),'O');
        topContender = new MinMaxAI(testStatus);
        change = false;
        int i = 0;
        while (i < 20) {
            i++;
            System.out.println("round " + i);
            testIfXWins(new GameStatus(new Board(7,7,4),'O'), topContender);
            if (change == true) {
                i = 0;
                change = false;
            }
        }
        System.out.println("Best: ");
        System.out.println(topContender.heuristic.Val1 + " " + topContender.heuristic.Val2 + " " + topContender.heuristic.Val3 + " " + topContender.heuristic.Val4);
    }
    public void testIfXWins(GameStatus test, MinMaxAI ai2) {
        MinMaxAI ai = new MinMaxAI(test);
        ai.heuristic.randomiseValues();
            char result = 'e';
            while (true) {
                test = ai.alphaBetaBoard(test);
                result = test.checkAll();
                if (result != 0) {
                    break;
                }
                test = ai2.alphaBetaBoard(test);
                result = test.checkAll();
                if (result != 0) {
                    break;
                }
                
        }
            if (result == 'X') {
                topContender = ai;
                System.out.println("changed");
                System.out.println(topContender.heuristic.Val1 + " " + topContender.heuristic.Val2 + " " + topContender.heuristic.Val3 + " " + topContender.heuristic.Val4);
                change = true;
            }
    }
}
