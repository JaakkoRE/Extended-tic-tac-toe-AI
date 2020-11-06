/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methodTests;

import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.Methods.MinimumMaximum;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jaakko
 */
public class MinMaxTests {
    private MinimumMaximum minmax;
     @Before
    public void setUp() {
        minmax = new MinimumMaximum();
    }
     @Test
    public void maxTest() {
        assertEquals(minmax.max(3,5),5);  
        assertEquals(minmax.max(5,3),5);  
        assertEquals(minmax.max(3,3),3);  
        assertEquals(minmax.max(-1,-2),-1);
        assertEquals(minmax.max(-2,-2),-2);
    }
     @Test
    public void minTest() {
        assertEquals(minmax.min(3,5),3);  
        assertEquals(minmax.min(5,3),3);  
        assertEquals(minmax.min(3,3),3);  
        assertEquals(minmax.min(-1,-2),-2);
        assertEquals(minmax.min(-2,-2),-2);
    }
    
}
