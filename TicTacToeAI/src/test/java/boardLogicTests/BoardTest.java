package boardLogicTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jaakko
 */

import com.mycompany.tictactoeai.AILogic.MinMaxAI;
import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {
    private Board board;
    public BoardTest() {

    }
    
     @Before
    public void setUp() {
        board = new Board(3,3,3);
    }
         @Test
    public void isBoardValueChangeWorking() {
        assertEquals(board.changeBoardValue(-1, 0, 'O'),false);
        assertEquals(board.changeBoardValue(0, 3, 'O'),false);
        assertEquals(board.changeBoardValue(2, 2, 'O'),true);
        assertEquals(board.changeBoardValue(0, 0, 'X'),true);
        board.changeBoardValue(1, 1, 'X');
        assertEquals(board.changeBoardValue(1, 1, 'X'),false);
    }
     @Test
    public void isBoardToStringWorking() {
        assertEquals(board.toString(),"_ _ _ \n_ _ _ \n_ _ _ \n");
        board.changeBoardValue(1, 1, 'X');
        assertEquals(board.toString(),"_ _ _ \n_ X _ \n_ _ _ \n");
        board.changeBoardValue(0, 0, 'O');
        assertEquals(board.toString(),"O _ _ \n_ X _ \n_ _ _ \n");
    }
     @Test
    public void isBoardSetUpWorking() {    
        board.changeBoardValue(1, 1, 'X');
        board.setUpBoard();
        assertEquals(board.toString(),"_ _ _ \n_ _ _ \n_ _ _ \n");
    }
     @Test
    public void isBoardCopyWorking() {    
        Board testBoard = board.copyBoard();
        assertEquals(testBoard.toString(),board.toString());
        assertEquals(testBoard.sizex,board.sizex);
        assertEquals(testBoard.sizey,board.sizey);
        assertEquals(testBoard.vcl,board.vcl);
    }
     @Test
    public void getterTest() {
        assertEquals(board.sizex, 3);
        assertEquals(board.sizey, 3);
        assertEquals(board.vcl, 3);
    }
     @Test
     public void hashTest() {
        assertEquals(board.hashCode(), 9140526);
        
        Board testBoard1 = new Board(3,2,1);
        Board testBoard2 = new Board(3,2,2);
        assertEquals(testBoard1.hashCode(), testBoard2.hashCode());
        
        Board testBoard3 = new Board(10,10,5);
        assertEquals(testBoard3.hashCode(), 4969531);
        GameStatus status = new GameStatus(new Board(15,15,5),'O');
        MinMaxAI forTests = new MinMaxAI(status);
        int[] hashValues = new int[225];
        int index = 0;
        for (GameStatus statusTestsForHash: forTests.generateBoards().getAll()) {
            hashValues[index] = statusTestsForHash.board.hashCode();
            index++;
        }
        //tests that hashes are diverse enough
        for (int i = 0; i < 224; i++) {
            int hashValue = hashValues[i];
            for (int j = i+1; j < 225; j++) {
                if (hashValue == hashValues[j]) {
                    assertEquals("at point " + i, " and " + j + " are same");
                }
            }
        }
     }
     @Test
    public void differentBoardTests() {
        Board testBoard = new Board(-1,2,2);
        assertEquals(testBoard.sizex, 1);
        assertEquals(testBoard.sizey, 2);
        assertEquals(testBoard.vcl, 2);
        
        testBoard = new Board(2,-1,2);
        assertEquals(testBoard.sizex, 2);
        assertEquals(testBoard.sizey, 1);
        assertEquals(testBoard.vcl, 2);
        
        testBoard = new Board(1,2,3);
        assertEquals(testBoard.sizex, 1);
        assertEquals(testBoard.sizey, 2);
        assertEquals(testBoard.vcl, 2);
        
        testBoard = new Board(2,1,3);
        assertEquals(testBoard.sizex, 2);
        assertEquals(testBoard.sizey, 1);
        assertEquals(testBoard.vcl, 2);
        
        testBoard = new Board(4,5,3);
        assertEquals(testBoard.sizex, 4);
        assertEquals(testBoard.sizey, 5);
        assertEquals(testBoard.vcl, 3);
    }

}
