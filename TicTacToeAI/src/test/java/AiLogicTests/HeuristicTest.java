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
    private GameStatus gameStatus;
    private GameStatus gameStatus2;
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
    }
     @Test
    public void heuristicBaseTest() {
        assertEquals(heuristic.heuristicBoard[0][0],3);
        assertEquals(heuristic.heuristicBoard[0][1],5); 
        assertEquals(heuristic.heuristicBoard[1][0],5); 
        assertEquals(heuristic.heuristicBoard[1][1],6);
        assertEquals(heuristic.heuristicBoard[2][0],3); 
        
        assertEquals(heuristic2.heuristicBoard[0][0],2);
        assertEquals(heuristic2.heuristicBoard[0][1],2); 
        assertEquals(heuristic2.heuristicBoard[1][0],2); 
        assertEquals(heuristic2.heuristicBoard[1][1],2);                
    }
     @Test
    public void differentDirectionHeuristicsTest() {       
        assertEquals(heuristic.heuristicDiagonal1(gameStatus.board.gameBoard, 0, 0, 'O'),9);
        
        assertEquals(heuristic.heuristicDiagonal2(gameStatus.board.gameBoard, 0, 0, 'O'),9);
        
        assertEquals(heuristic.heuristicVertical(gameStatus.board.gameBoard, 0, 0, 'O'),0);
        
        assertEquals(heuristic.heuristicHorizontal(gameStatus.board.gameBoard, 0, 0, 'O'),36);
    }
     @Test
    public void evaluateHeuristicTest() {       
        assertEquals(heuristic.evaluate(gameStatus, 'O', 'X'),0);
        assertEquals(heuristic.evaluate(gameStatus, 'X', 'O'),0);
        this.gameStatus.setBoardValue(2, 2);
        assertEquals(heuristic.evaluate(gameStatus, 'O', 'X'),30);
        assertEquals(heuristic.evaluate(gameStatus, 'X', 'O'),-30);
        
        assertEquals(heuristic2.evaluate(gameStatus2, 'O', 'X'),100000010);
        assertEquals(gameStatus2.vcl,2);
        assertEquals(heuristic2.evaluate(gameStatus2, 'X', 'O'),-100000010);   
        
    }
}
