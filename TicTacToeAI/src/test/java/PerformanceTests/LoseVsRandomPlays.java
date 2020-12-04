/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PerformanceTests;

import com.mycompany.tictactoeai.AILogic.MinMaxAI;
import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import com.mycompany.tictactoeai.Methods.ArrayList;
import java.util.Random;
import org.junit.Test;

/**
 *
 * @author Jaakko
 */
public class LoseVsRandomPlays {
    // takes a while
     @Test 
    public void randomTest() {
        Random r = new Random();
        GameStatus test = new GameStatus (new Board(6, 6, 4), 'O');
        testRandomly(test);
        
        test = new GameStatus (new Board(6, 6, 3), 'O');
        testRandomly(test);
        
        test = new GameStatus (new Board(8, 8, 3), 'O');
        testRandomly(test);
        
        test = new GameStatus (new Board(13, 13, 6), 'O');
        testRandomly(test);
        
        test = new GameStatus (new Board(17, 17, 4), 'O');
        testRandomly(test);
      
    }
    public void testRandomly(GameStatus test) {
        Random r = new Random();
        MinMaxAI ai = new MinMaxAI(test);
        for (int i = 0; i < 1000; i++) {
            char result = 'e';
            while (true) {
                test = ai.alphaBetaBoard(test);
                result = test.checkAll();
                if (result != 0) {
                    break;
                }
                ai = new MinMaxAI(test);
                ArrayList list = ai.generateBoards();
                test = list.getValueIndex(r.nextInt(list.getLength()));
                result = test.checkAll();
                if (result != 0) {
                    break;
                }
            }
            if (result == 'X') {
                System.out.println("AI played poorly");
                System.out.println(test.board.toString());
                break;
            }
        }
    }
}
