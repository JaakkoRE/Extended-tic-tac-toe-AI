/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methodTests;

import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.Methods.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jaakko
 */
public class HashMapTests {
    private HashMap hashMap;
    private Board testBoard1;
    private Board testBoard2;
    private Board testBoard3;
    private Board testBoard4;
    private Board testBoard5;
    private Board testBoard6;
    private Board testBoard7;

     @Before
    public void setUp() {
        hashMap = new HashMap();
        
        testBoard1 = new Board(3,2,1);
        
        testBoard2 = new Board(3,2,2);
        
        testBoard3 = new Board(5,3,2);
        testBoard3.changeBoardValue(4, 2, 'O');
        
        testBoard4 = new Board(5,3,2);
        testBoard4.changeBoardValue(4, 2, 'X');
        
        testBoard5 = new Board(5,5,2);
        testBoard5.changeBoardValue(4, 2, 'X');
        testBoard5.changeBoardValue(4, 1, 'X');
        testBoard5.changeBoardValue(4, 4, 'X');
        
        testBoard6 = new Board(15,15,2);
        testBoard6.changeBoardValue(4, 2, 'X');
        
        testBoard7 = new Board(1,1,1);
        
    }
     @Test
    public void hashMapTests() {
        hashMap.put(testBoard1, 5);
        assertEquals(hashMap.get(testBoard1),5);
        hashMap.put(testBoard2, 2);
        hashMap.put(testBoard3, 500);
        hashMap.put(testBoard4, 0);
        hashMap.put(testBoard5, -5);
        hashMap.put(testBoard6, -1000);
        
        assertEquals(hashMap.get(testBoard1),5);
        assertEquals(hashMap.get(testBoard2),2);
        assertEquals(hashMap.get(testBoard3),500);
        assertEquals(hashMap.get(testBoard4),0);
        assertEquals(hashMap.get(testBoard5),-5);
        assertEquals(hashMap.get(testBoard6),-1000);
        
        hashMap.put(testBoard1, 10);
        assertEquals(hashMap.get(testBoard1),10);
        hashMap.put(new Board(3,2,2), 7);
        assertEquals(hashMap.get(testBoard1),10);
        assertEquals(hashMap.get(testBoard7),-7777);


    }
    
}
