/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PerformanceTests;

import com.mycompany.tictactoeai.AILogic.MinMaxAI;
import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import java.util.Random;
import org.junit.Test;


/**
 *
 * @author Jaakko
 */

//since 0 starts first, O always has equal or one more piece on the board so X should never win.
public class XLosesTests {
    // takes a while
     @Test
    public void doesXWin() {
        Random r = new Random(1337);
        GameStatus test1 = new GameStatus(new Board(3,3,3),'O');
        GameStatus test2 = new GameStatus(new Board(6,6,3),'O');
        GameStatus test3 = new GameStatus(new Board(7,7,5),'O');
        GameStatus test4 = new GameStatus(new Board(9,9,5),'O');
        GameStatus test5 = new GameStatus(new Board(11,11,4),'O');
        GameStatus test6 = new GameStatus(new Board(15,15,9),'O');
        GameStatus test7 = new GameStatus(new Board(15,15,8),'O');
        GameStatus test8 = new GameStatus(new Board(9,9,6),'O');
        GameStatus test9 = new GameStatus(new Board(8,8,4),'O');
        testIfXWins(test1);
        testIfXWins(test2);
        testIfXWins(test3);
        testIfXWins(test4);
        testIfXWins(test5);
        testIfXWins(test6);
        testIfXWins(test7);
        testIfXWins(test8);
        testIfXWins(test9);
        System.out.println("extra tests");
        for (int i = 0; i <= 25; i++) {
            GameStatus testRepeated = new GameStatus(new Board(r.nextInt(9)+2,r.nextInt(9)+2,r.nextInt(4)+2),'O');
            testIfXWins(testRepeated);
        }
    }
    public void testIfXWins(GameStatus test) {
        MinMaxAI ai = new MinMaxAI(test);
            char result = 'e';
            while (true) {
                test = ai.alphaBetaBoard(test);
                result = test.checkAll();
                if (result != 0) {
                    break;
                }
            }
            if (result == 'X') {
                System.out.println("AI played poorly");
                System.out.println(test.board.toString());
            }
    }
}