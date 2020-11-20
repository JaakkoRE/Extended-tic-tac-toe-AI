package AiLogicTests;

import com.mycompany.tictactoeai.AILogic.MinMaxAI;
import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import com.mycompany.tictactoeai.Methods.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jaakko
 */


public class AiLogicTests {
     @Before
    public void setUp() {
        
    }
     @Test
    public void minMaxAIBoardTestWithSmallBoards() {
        GameStatus statusTest = new GameStatus(new Board(1,1,1),'O');
        MinMaxAI ai = new MinMaxAI(statusTest, false);
        GameStatus statusCheck = ai.alphaBetaBoard(statusTest);
        statusTest.setBoardValue(0, 0);
        assertEquals(ai.bestStatus.board.toString(),statusCheck.board.toString()); 
        assertEquals(-1000000100,ai.alphaBetaValue(statusTest));
        
        
        statusTest = new GameStatus(new Board(3,2,3),'O');
        statusTest.setBoardValue(0, 0);
        statusTest.setBoardValue(0, 1);
        statusTest.setBoardValue(1, 0);
        statusTest.setBoardValue(1, 1);
        ai = new MinMaxAI(statusTest);
        GameStatus statusChecker = statusTest.copyGameStatus();
        statusChecker.setBoardValue(2, 0);
        assertEquals(statusChecker.board.toString(),ai.alphaBetaBoard(statusTest).board.toString());
        assertEquals(1000000099,ai.alphaBetaValue(statusTest));
        
        statusTest = new GameStatus(new Board(3,3,3),'O');
        statusTest.setBoardValue(0, 1);
        statusTest.setBoardValue(1, 1);
        statusTest.setBoardValue(0, 0);
        statusTest.setBoardValue(1, 0);
        statusTest.setBoardValue(2, 2);
        ai = new MinMaxAI(statusTest);
        statusChecker = statusTest.copyGameStatus();
        statusChecker.setBoardValue(1, 2);
        assertEquals(statusChecker.board.toString(),ai.alphaBetaBoard(statusTest).board.toString()); 
        assertEquals(1000000099,ai.alphaBetaValue(statusTest));
    }
      @Test
    public void maxValueTest() {
        GameStatus statusTest = new GameStatus(new Board(1,1,1),'O');
        MinMaxAI ai = new MinMaxAI(statusTest);
        // initizialization
        ai.alphaBetaValue(statusTest);
        assertEquals(ai.maxValue(statusTest, -2000000000, 2000000000, 0),1000000099);
        
        statusTest = new GameStatus(new Board(3,3,3),'O');
        ai = new MinMaxAI(statusTest);
        // initizialization
        ai.alphaBetaValue(statusTest);
        assertEquals(ai.maxValue(statusTest, -2000000000, 2000000000, 0),0);
    }
    
      @Test
    public void minValueTest() {
        GameStatus statusTest = new GameStatus(new Board(1,1,1),'O');
        MinMaxAI ai = new MinMaxAI(statusTest);
        // initizialization
        ai.alphaBetaValue(statusTest);
        assertEquals(ai.maxValue(statusTest, -2000000000, 2000000000, 0),1000000099);
        
        statusTest = new GameStatus(new Board(3,3,3),'O');
        ai = new MinMaxAI(statusTest);
        // initizialization
        ai.alphaBetaValue(statusTest);
        assertEquals(ai.maxValue(statusTest, -2000000000, 2000000000, 0),0);
    }
     @Test
    public void generateChildrenBoards() {
        GameStatus statusTest = new GameStatus(new Board(2,2,2),'O');
        MinMaxAI ai = new MinMaxAI(statusTest);
        ArrayList testList = ai.generateBoards();
        GameStatus statusTest1 = statusTest.copyGameStatus();
        statusTest1.setBoardValue(0, 0);
        GameStatus statusTest2 = statusTest.copyGameStatus();
        statusTest2.setBoardValue(0, 1);
        GameStatus statusTest3 = statusTest.copyGameStatus();
        statusTest3.setBoardValue(1, 0);
        GameStatus statusTest4 = statusTest.copyGameStatus();
        statusTest4.setBoardValue(1, 1);
        assertEquals(testList.getValueIndex(0).board.toString(),statusTest1.board.toString());
        assertEquals(testList.getValueIndex(1).board.toString(),statusTest2.board.toString()); 
        assertEquals(testList.getValueIndex(2).board.toString(),statusTest3.board.toString()); 
        assertEquals(testList.getValueIndex(3).board.toString(),statusTest4.board.toString()); 
        
        statusTest.setBoardValue(0, 0);
        ai = new MinMaxAI(statusTest);
        testList = ai.generateBoards();
        statusTest1 = statusTest.copyGameStatus();
        statusTest1.setBoardValue(0, 1);
        statusTest2 = statusTest.copyGameStatus();
        statusTest2.setBoardValue(1, 0);
        statusTest3 = statusTest.copyGameStatus();
        statusTest3.setBoardValue(1, 1);
        assertEquals(testList.getValueIndex(0).board.toString(),statusTest1.board.toString());
        assertEquals(testList.getValueIndex(1).board.toString(),statusTest2.board.toString()); 
        assertEquals(testList.getValueIndex(2).board.toString(),statusTest3.board.toString()); 
    }
    
}
