/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AiLogicTests;

import com.mycompany.tictactoeai.AILogic.Heuristic;
import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jaakko
 */
public class HeuristicTest {
    private Heuristic heuristic;
    private Heuristic heuristic2;
    private Heuristic heuristic3;
    private GameStatus gameStatus;
    private GameStatus gameStatus2;
    private GameStatus gameStatus3;

     @Before
    public void setUp() {
        this.heuristic = new Heuristic(new Board(3,3,3));
        this.heuristic2 = new Heuristic(new Board(2,2,2));
        this.gameStatus = new GameStatus(new Board(3,3,3),'O');
        this.gameStatus2 = new GameStatus(new Board(2,2,2),'O');
        this.gameStatus.setBoardValue(0, 0);
        this.gameStatus.setBoardValue(1, 1);
        this.gameStatus.setBoardValue(0, 1);
        this.gameStatus.setBoardValue(1, 2);
        
        this.gameStatus2.setBoardValue(0, 0);
        this.gameStatus2.setBoardValue(1, 1);
        this.gameStatus2.setBoardValue(0, 1);
        
        this.heuristic3 = new Heuristic(new Board(4,4,4));
        this.gameStatus3 = new GameStatus(new Board(4,4,4),'O');
        this.gameStatus3.setBoardValue(0, 0);
        this.gameStatus3.setBoardValue(1, 1);
        this.gameStatus3.setBoardValue(0, 1);
        this.gameStatus3.setBoardValue(1, 2);
        this.gameStatus3.setBoardValue(2, 3);
        this.gameStatus3.setBoardValue(3, 3);       
    }
     @Test
    public void theHeuristicTest() {
        GameStatus statusTest = new GameStatus(new Board(5,4,3),'O');
        this.heuristic = new Heuristic(new Board(5,4,3));
        statusTest.setBoardValue(0, 1);
        statusTest.setBoardValue(0, 0);
        statusTest.setBoardValue(0, 2);
        statusTest.setBoardValue(1, 0);
        statusTest.setBoardValue(0, 3);
        assertEquals(heuristic.evaluate(statusTest, 'O', 'X',false),174);   
        
        assertEquals(heuristic3.evaluate(gameStatus3, 'O', 'X',false),-114); 
        this.gameStatus3.setBoardValue(1, 3);
        assertEquals(heuristic3.evaluate(gameStatus3, 'O', 'X',false),44); 
        
        assertEquals(heuristic2.evaluate(gameStatus2, 'O', 'X',false),91); 
    }
     @Test 
     public void heuristicSymbolNearTest() {
        assertEquals(heuristic.heuristicSameSymbolNearSameSymbol(gameStatus.board.gameBoard,0,0,'O',0),12);
        assertEquals(heuristic2.heuristicSameSymbolNearSameSymbol(gameStatus2.board.gameBoard,1,1,'X',0),0);
           
        GameStatus statusTest = new GameStatus(new Board(5,4,3),'O');
        this.heuristic = new Heuristic(new Board(5,4,3));
        statusTest.setBoardValue(0, 1);
        statusTest.setBoardValue(0, 0);
        statusTest.setBoardValue(0, 2);
        statusTest.setBoardValue(1, 0);
        statusTest.setBoardValue(0, 3);
        statusTest.setBoardValue(1, 3);
        statusTest.setBoardValue(2, 3);
        statusTest.setBoardValue(3, 3);
        statusTest.setBoardValue(4, 1);
        statusTest.setBoardValue(2, 1);
        statusTest.setBoardValue(4, 2);
        statusTest.setBoardValue(1, 1);
        statusTest.setBoardValue(1, 2);
        statusTest.setBoardValue(1, 0);
        assertEquals(heuristic.heuristicSameSymbolNearSameSymbol(statusTest.board.gameBoard,0,0,'X',0),100);
        this.heuristic = new Heuristic(new Board(5,4,3));
        assertEquals(heuristic.heuristicSameSymbolNearSameSymbol(statusTest.board.gameBoard,2,3,'O',0),250);
        this.heuristic = new Heuristic(new Board(5,4,3));
        assertEquals(heuristic.heuristicSameSymbolNearSameSymbol(statusTest.board.gameBoard,4,1,'O',0),30);
        this.heuristic = new Heuristic(new Board(5,4,3));
        assertEquals(heuristic.heuristicSameSymbolNearSameSymbol(statusTest.board.gameBoard,1,1,'X',0),100);
        this.heuristic = new Heuristic(new Board(5,4,3));
        assertEquals(heuristic.heuristicSameSymbolNearSameSymbol(statusTest.board.gameBoard,4,3,'p',0),0);
        assertEquals(heuristic.heuristicSameSymbolNearSameSymbol(statusTest.board.gameBoard,0,3,'p',0),0);
        assertEquals(heuristic.heuristicSameSymbolNearSameSymbol(statusTest.board.gameBoard,4,0,'p',0),0);
     }
     @Test
    public void heuristicBaseTest() {
        assertEquals(heuristic.heuristicBoard[0][0],1);
        assertEquals(heuristic.heuristicBoard[0][1],5); 
        assertEquals(heuristic.heuristicBoard[1][0],5); 
        assertEquals(heuristic.heuristicBoard[1][1],12);
        assertEquals(heuristic.heuristicBoard[2][0],1); 
        
        assertEquals(heuristic2.heuristicBoard[0][0],1);
        assertEquals(heuristic2.heuristicBoard[0][1],1); 
        assertEquals(heuristic2.heuristicBoard[1][0],1); 
        assertEquals(heuristic2.heuristicBoard[1][1],1);                
    }
     @Test
    public void differentDirectionHeuristicsTest() {       
        assertEquals(heuristic.heuristicDiagonal1(gameStatus.board.gameBoard, 0, 0, 'O'),1);
        
        assertEquals(heuristic.heuristicDiagonal2(gameStatus.board.gameBoard, 0, 0, 'O'),1);
        
        assertEquals(heuristic.heuristicVertical(gameStatus.board.gameBoard, 0, 0, 'O'),1);
        
        assertEquals(heuristic.heuristicHorizontal(gameStatus.board.gameBoard, 0, 0, 'O'),2);
    }
     @Test
    public void realLengthTest() {
        assertEquals(heuristic.lenghtThatIsReturned(1, 2, 2, 'O'),2);
        assertEquals(heuristic.lenghtThatIsReturned(1, 2, 0, 'O'),1);
        assertEquals(heuristic.lenghtThatIsReturned(1, 0, 2, 'O'),1);
        assertEquals(heuristic.lenghtThatIsReturned(2, 0, 2, 'O'),4);
        assertEquals(heuristic.lenghtThatIsReturned(1, 0, 0, 'O'),0);
        assertEquals(heuristic.lenghtThatIsReturned(2, 2, 2, 'X'),4);
        assertEquals(heuristic.lenghtThatIsReturned(2, 0, 2, 'X'),4);
        assertEquals(heuristic.lenghtThatIsReturned(1, 2, 0, 'X'),1);
    }
     @Test
    public void evaluateHeuristicTest() {       
        assertEquals(heuristic.evaluate(gameStatus, 'O', 'X',false),-31);
        assertEquals(heuristic.evaluate(gameStatus, 'X', 'O',false),27);
        this.gameStatus.setBoardValue(2, 2);
        assertEquals(heuristic.evaluate(gameStatus, 'O', 'X',false),0);
        assertEquals(heuristic.evaluate(gameStatus, 'X', 'O',false),-4);
        
        assertEquals(heuristic2.evaluate(gameStatus2, 'O', 'X',false),91);
        assertEquals(gameStatus2.vcl,2);
        assertEquals(heuristic2.evaluate(gameStatus2, 'X', 'O',false),-52);   
        
       
    }
}
