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

import com.mycompany.tictactoeai.BoardLogic.Board;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class boardTest {
    private Board board;
    public boardTest() {

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
