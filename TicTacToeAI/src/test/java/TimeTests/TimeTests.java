package TimeTests;

import com.mycompany.UserInterface.UI;
import com.mycompany.tictactoeai.AILogic.AIVSAI;
import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
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
public class TimeTests {
     @Test
    public void TestLogicAiSmallTest() {
        GameStatus status;
        AIVSAI AIVersus = new AIVSAI();
        System.out.println("Board 5,5,5 without HashMap utilization" );
        status = new GameStatus(new Board(5, 5, 5));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        System.out.println("Board 5,5,5 with HashMap utilization" );
        status = new GameStatus(new Board(5, 5, 5));
        AIVersus.AIVSAIPlay(status, true, false);
        System.out.println("");
        
        System.out.println("Board 5,5,4 without HashMap utilization" );
        status = new GameStatus(new Board(5, 5, 4));
        AIVersus.AIVSAIPlay(status, true, false);
        System.out.println("");
        System.out.println("Board 5,5,4 with HashMap utilization" );
        status = new GameStatus(new Board(5, 5, 4));
        AIVersus.AIVSAIPlay(status, true, false);
        System.out.println("");
        
        System.out.println("Board 3,3,3");
        status = new GameStatus(new Board(3, 3, 3));
        AIVersus.AIVSAIPlay(status, true, false);
        System.out.println("");
       
        System.out.println("Board 10,10,9 without HashMap utilization" );
        status = new GameStatus(new Board(10,10,9));
        AIVersus.AIVSAIPlay(status, true, false);
        System.out.println("");
        System.out.println("Board 10,10,9 with HashMap utilization" );
        status = new GameStatus(new Board(10,10,9));
        AIVersus.AIVSAIPlay(status, true, false);
        System.out.println("");
    }
     @Test
    public void TestLogicAiBigTest() {
        GameStatus status;
        System.out.println("Board 3,3,3");
        status = new GameStatus(new Board(3, 3, 3));
        AIVSAI AIVersus = new AIVSAI();
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 5,5,5 without HashMap utilization" );
        status = new GameStatus(new Board(5, 5, 5));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        System.out.println("Board 5,5,5 with HashMap utilization" );
        status = new GameStatus(new Board(5, 5, 5));
        AIVersus.AIVSAIPlay(status, true, false);
        System.out.println("");
        
        System.out.println("Board 5,5,4 without HashMap utilization" );
        status = new GameStatus(new Board(5, 5, 4));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        System.out.println("Board 5,5,4 with HashMap utilization" );
        status = new GameStatus(new Board(5, 5, 4));
        AIVersus.AIVSAIPlay(status, true, false);
        System.out.println("");
        
        System.out.println("Board 5,5,3 without HashMap utilization" );
        status = new GameStatus(new Board(5, 5, 3));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        System.out.println("Board 5,5,3 with HashMap utilization" );
        status = new GameStatus(new Board(5, 5, 3));
        AIVersus.AIVSAIPlay(status, true, false);
        System.out.println("");
        
       
        System.out.println("Board 6,5,5 without HashMap utilization" );
        status = new GameStatus(new Board(6,5,5));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 6,6,5 without HashMap utilization" );
        status = new GameStatus(new Board(6,6,5));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 6,6,6 without HashMap utilization" );
        status = new GameStatus(new Board(6,6,6));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 7,6,6 without HashMap utilization" );
        status = new GameStatus(new Board(7,6,6));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 7,7,6 without HashMap utilization" );
        status = new GameStatus(new Board(7,6,6));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        System.out.println("Board 7,7,6 with HashMap utilization" );
        status = new GameStatus(new Board(7,6,6));
        AIVersus.AIVSAIPlay(status, true, false);
        System.out.println("");
        
        System.out.println("Board 7,7,7 without HashMap utilization" );
        status = new GameStatus(new Board(7,7,7));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        System.out.println("Board 7,7,7 with HashMap utilization" );
        status = new GameStatus(new Board(7,7,7));
        AIVersus.AIVSAIPlay(status, true, false);
        System.out.println("");
        
        System.out.println("Board 8,8,7 without HashMap utilization" );
        status = new GameStatus(new Board(7,7,7));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 9,9,7 without HashMap utilization" );
        status = new GameStatus(new Board(7,7,7));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 10,10,7 without HashMap utilization" );
        status = new GameStatus(new Board(7,7,7));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 11,11,7 without HashMap utilization" );
        status = new GameStatus(new Board(7,7,7));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 12,12,7 without HashMap utilization" );
        status = new GameStatus(new Board(7,7,7));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");

        
        System.out.println("Board 13,13,7 without HashMap utilization" );
        status = new GameStatus(new Board(7,7,7));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");

        System.out.println("toiset");
        
        System.out.println("Board 5,5,5 without HashMap utilization" );
        status = new GameStatus(new Board(5,5,5));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 6,6,6 without HashMap utilization" );
        status = new GameStatus(new Board(6,6,6));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 7,7,7 without HashMap utilization" );
        status = new GameStatus(new Board(7,7,7));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 8,8,8 without HashMap utilization" );
        status = new GameStatus(new Board(8,8,8));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 9,9,9 without HashMap utilization" );
        status = new GameStatus(new Board(9,9,9));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 10,10,10 without HashMap utilization" );
        status = new GameStatus(new Board(10,10,10));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 11,11,11 without HashMap utilization" );
        status = new GameStatus(new Board(11,11,11));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 12,12,12 without HashMap utilization" );
        status = new GameStatus(new Board(12,12,12));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 13,13,13 without HashMap utilization" );
        status = new GameStatus(new Board(13,13,13));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 14,14,14 without HashMap utilization" );
        status = new GameStatus(new Board(14,14,14));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 15,15,15 without HashMap utilization" );
        status = new GameStatus(new Board(15,15,15));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 16,16,16 without HashMap utilization" );
        status = new GameStatus(new Board(16,16,16));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 17,17,17 without HashMap utilization" );
        status = new GameStatus(new Board(17,17,17));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 18,18,18 without HashMap utilization" );
        status = new GameStatus(new Board(18,18,18));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
        
        System.out.println("Board 19,19,19 without HashMap utilization" );
        status = new GameStatus(new Board(19,19,19));
        AIVersus.AIVSAIPlay(status, false, false);
        System.out.println("");
    }
}
