package boardLogicTests;


import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
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
public class GameStatusTest {
    private GameStatus status;
    public GameStatusTest() {
        
    }
    
     @Before
    public void setUp() {
        Board board = new Board(2,2,2);
        status = new GameStatus(board, 'O');
    }
     @Test
    public void otherPlayerTest() {
        assertEquals(status.otherPlayer(),'X');
        GameStatus statusTest = new GameStatus(new Board(2,2,2), 'X');
        assertEquals(statusTest.otherPlayer(),'O');

    }
     @Test
    public void isBoardFullTest() {
        assertEquals(status.board.boardSize,4);
        assertEquals(status.board.boardfulfillment,0);
        assertEquals(status.isBoardFull(),false);
        status.board.changeBoardValue(0, 0, 'X');
        assertEquals(status.isBoardFull(),false);
        status.board.changeBoardValue(0, 1, 'O');
        assertEquals(status.isBoardFull(),false);
        status.board.changeBoardValue(1, 0, 'X');
        assertEquals(status.isBoardFull(),false);
        status.board.changeBoardValue(1, 1, 'X');
        assertEquals(status.isBoardFull(),true);
    }
    
     @Test
    public void setBoardValue() {
        assertEquals(status.setBoardValue(-1, 0),'e');
        assertEquals(status.setBoardValue(0, 3),'e');
        assertEquals(status.setBoardValue(0, 1),0);           
        status.board.changeBoardValue(1, 1, 'X');
        assertEquals(status.setBoardValue(1, 1 ),'e');
        assertEquals(status.setBoardValue(1, 0 ),'X');
    }
      @Test
    public void allCheckTest() {
        assertEquals(status.checkAll(),0);
        status.board.changeBoardValue(0, 0, 'O');
        assertEquals(status.checkAll(),0);
        status.board.changeBoardValue(0, 1, 'O');
        assertEquals(status.checkAll(),'O');
        status.board.changeBoardValue(1, 0, 'O');
        status.board.changeBoardValue(1, 1, 'O');
        assertEquals(status.checkAll(),'O');
        GameStatus testBoard = new GameStatus(new Board(3,3,3),'O');
        testBoard.board.changeBoardValue(0, 0, 'X');
        testBoard.board.changeBoardValue(0, 1, 'O');
        testBoard.board.changeBoardValue(0, 2, 'X');
        testBoard.board.changeBoardValue(1, 0, 'X');
        testBoard.board.changeBoardValue(1, 1, 'X');
        testBoard.board.changeBoardValue(1, 2, 'O');
        testBoard.board.changeBoardValue(2, 0, 'O');
        testBoard.board.changeBoardValue(2, 1, 'X');
        testBoard.board.changeBoardValue(2, 2, 'O');
        assertEquals(testBoard.checkAll(),'n');
    }
     @Test
    public void victoryCheckTest() {
        assertEquals(status.victoryCheck(0, 0),0);
        status.board.changeBoardValue(0, 0, 'O');
        assertEquals(status.victoryCheck(0, 0),0);
        status.board.changeBoardValue(0, 1, 'O');
        assertEquals(status.victoryCheck(0, 0),'O');

    }
        @Test
    public void horizontalVictoryTest() {
        assertEquals(status.horizontalVictoryCheck(0, 0, 'X'),0);
        status.board.changeBoardValue(0, 0, 'X');
        assertEquals(status.horizontalVictoryCheck(0, 0, 'X'),0);
        status.board.changeBoardValue(1, 0, 'X');
        assertEquals(status.horizontalVictoryCheck(0, 0, 'X'),'X');
        assertEquals(status.horizontalVictoryCheck(1, 0, 'X'),'X');
        assertEquals(status.horizontalVictoryCheck(1, 0, 'O'),0);
        
        GameStatus gameStatusTest = new GameStatus(new Board(3,3,3),'O');
        gameStatusTest.board.changeBoardValue(0, 0, 'O');
        gameStatusTest.board.changeBoardValue(0, 2, 'X');
        gameStatusTest.board.changeBoardValue(1, 0, 'O');
        gameStatusTest.board.changeBoardValue(1, 1, 'X');
        gameStatusTest.board.changeBoardValue(2, 1, 'O');
        assertEquals(gameStatusTest.horizontalVictoryCheck(0, 1, 'O'),0);
        
        GameStatus gameStatusTest2 = new GameStatus(new Board(3,3,3),'O');
        gameStatusTest2.board.changeBoardValue(0, 0, 'O');
        gameStatusTest2.board.changeBoardValue(0, 2, 'X');
        gameStatusTest2.board.changeBoardValue(2, 0, 'X');
        gameStatusTest2.board.changeBoardValue(1, 2, 'X');
        gameStatusTest2.board.changeBoardValue(1, 0, 'O');
        gameStatusTest2.board.changeBoardValue(1, 1, 'O');
        assertEquals(gameStatusTest2.horizontalVictoryCheck(2, 1, 'O'),0);
    }
     @Test
    public void verticalVictoryTest() {
        assertEquals(status.verticalVictoryCheck(0, 0, 'O'),0);
        status.board.changeBoardValue(0, 0, 'O');
        assertEquals(status.verticalVictoryCheck(0, 0, 'O'),0);
        status.board.changeBoardValue(0, 1, 'O');
        assertEquals(status.verticalVictoryCheck(0, 0, 'O'),'O');
        assertEquals(status.verticalVictoryCheck(0, 1, 'O'),'O');
        assertEquals(status.verticalVictoryCheck(0, 1, 'X'),0);
        
        GameStatus gameStatusTest = new GameStatus(new Board(3,3,3),'O');
        gameStatusTest.board.changeBoardValue(0, 0, 'O');
        gameStatusTest.board.changeBoardValue(0, 2, 'X');
        gameStatusTest.board.changeBoardValue(1, 0, 'O');
        gameStatusTest.board.changeBoardValue(1, 1, 'X');
        gameStatusTest.board.changeBoardValue(2, 1, 'O');
        assertEquals(gameStatusTest.verticalVictoryCheck(0, 1, 'O'),0);
        
        GameStatus gameStatusTest2 = new GameStatus(new Board(3,3,3),'O');
        gameStatusTest2.board.changeBoardValue(0, 0, 'O');
        gameStatusTest2.board.changeBoardValue(0, 2, 'X');
        gameStatusTest2.board.changeBoardValue(2, 0, 'X');
        gameStatusTest2.board.changeBoardValue(1, 2, 'X');
        gameStatusTest2.board.changeBoardValue(1, 0, 'O');
        gameStatusTest2.board.changeBoardValue(1, 1, 'O');
        assertEquals(gameStatusTest2.verticalVictoryCheck(2, 1, 'O'),0);
    }
     @Test
    public void diagonalVictoryTest() {
        assertEquals(status.diagonalVictoryCheck1(0, 0, 'X'),0);
        status.board.changeBoardValue(1, 0, 'X');
        assertEquals(status.diagonalVictoryCheck1(0, 0, 'X'),0);
        status.board.changeBoardValue(0, 1, 'X');
        assertEquals(status.diagonalVictoryCheck1(0, 1, 'X'),'X');
        assertEquals(status.diagonalVictoryCheck1(1, 1, 'X'),0);
        assertEquals(status.diagonalVictoryCheck1(1, 0, 'X'),'X');
        assertEquals(status.diagonalVictoryCheck1(1, 0, 'O'),0);
        
        GameStatus gameStatusTest = new GameStatus(new Board(3,3,3),'O');
        gameStatusTest.board.changeBoardValue(0, 0, 'O');
        gameStatusTest.board.changeBoardValue(0, 2, 'X');
        gameStatusTest.board.changeBoardValue(1, 0, 'O');
        gameStatusTest.board.changeBoardValue(1, 1, 'X');
        gameStatusTest.board.changeBoardValue(2, 1, 'O');
        assertEquals(gameStatusTest.diagonalVictoryCheck1(0, 1, 'O'),0);
        
        GameStatus gameStatusTest2 = new GameStatus(new Board(3,3,3),'O');
        gameStatusTest2.board.changeBoardValue(0, 0, 'O');
        gameStatusTest2.board.changeBoardValue(0, 2, 'X');
        gameStatusTest2.board.changeBoardValue(2, 0, 'X');
        gameStatusTest2.board.changeBoardValue(1, 2, 'X');
        gameStatusTest2.board.changeBoardValue(1, 0, 'O');
        gameStatusTest2.board.changeBoardValue(1, 1, 'O');
        assertEquals(gameStatusTest2.diagonalVictoryCheck1(2, 1, 'O'),0);
        
        GameStatus gameStatusTest3 = new GameStatus(new Board(3,3,3),'X');
        gameStatusTest3.board.changeBoardValue(1, 1, 'X');
        gameStatusTest3.board.changeBoardValue(2, 0, 'X');
        assertEquals(gameStatusTest3.diagonalVictoryCheck1(0, 2, 'X'),'X');
    }
     @Test    
    public void diagonal2VictoryTest() {
        assertEquals(status.diagonalVictoryCheck2(0, 0, 'X'),0);
        status.board.changeBoardValue(1, 1, 'X');
        assertEquals(status.diagonalVictoryCheck2(1, 1, 'X'),0);
        status.board.changeBoardValue(0, 0, 'X');
        assertEquals(status.diagonalVictoryCheck2(1, 1, 'X'),'X');
        assertEquals(status.diagonalVictoryCheck2(1, 0, 'X'),0);
        assertEquals(status.diagonalVictoryCheck2(0, 0, 'X'),'X');
        assertEquals(status.diagonalVictoryCheck2(1, 0, 'O'),0);
        
        GameStatus gameStatusTest = new GameStatus(new Board(3,3,3),'O');
        gameStatusTest.board.changeBoardValue(0, 0, 'O');
        gameStatusTest.board.changeBoardValue(0, 2, 'X');
        gameStatusTest.board.changeBoardValue(1, 0, 'O');
        gameStatusTest.board.changeBoardValue(1, 1, 'X');
        gameStatusTest.board.changeBoardValue(2, 1, 'O');
        assertEquals(gameStatusTest.diagonalVictoryCheck2(0, 1, 'O'),0);
        
        GameStatus gameStatusTest2 = new GameStatus(new Board(3,3,3),'O');
        gameStatusTest2.board.changeBoardValue(0, 0, 'O');
        gameStatusTest2.board.changeBoardValue(0, 2, 'X');
        gameStatusTest2.board.changeBoardValue(2, 0, 'X');
        gameStatusTest2.board.changeBoardValue(1, 2, 'X');
        gameStatusTest2.board.changeBoardValue(1, 0, 'O');
        gameStatusTest2.board.changeBoardValue(1, 1, 'O');
        assertEquals(gameStatusTest2.diagonalVictoryCheck2(2, 1, 'O'),0);

        GameStatus gameStatusTest3 = new GameStatus(new Board(3,3,3),'X');
        gameStatusTest3.board.changeBoardValue(1, 1, 'X');
        gameStatusTest3.board.changeBoardValue(2, 0, 'X');
        assertEquals(gameStatusTest3.diagonalVictoryCheck2(0, 2, 'X'),0);
    }    
         @Test    
    public void copyGameStatusTest() {
       assertEquals(status.copyGameStatus().board.toString(),status.board.toString());
       assertEquals(status.copyGameStatus().currentPlayer,status.currentPlayer);
       assertEquals(status.copyGameStatus().vcl,status.vcl);
       status.setBoardValue(1, 1);
       assertEquals(status.copyGameStatus().board.toString(),status.board.toString());
       assertEquals(status.copyGameStatus().currentPlayer,status.currentPlayer);
       assertEquals(status.copyGameStatus().vcl,status.vcl);

    }
}
