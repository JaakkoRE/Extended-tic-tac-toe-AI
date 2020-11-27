/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AiLogicTests;

import com.mycompany.tictactoeai.AILogic.DepthStop;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jaakko
 */
public class DepthStopTests {
    public DepthStop depthStop;
     @Before
    public void setUp() {
      depthStop = new DepthStop();
    }
     @Test
    public void depthStopStartTest() {
         assertEquals(depthStop.depthStopStart(8),9999);
         assertEquals(depthStop.depthStopStart(10000),1);
         assertEquals(depthStop.depthStopStart(20),7);
         assertEquals(depthStop.depthStopStart(25),6);
         assertEquals(depthStop.depthStopStart(36),5);
         assertEquals(depthStop.depthStopStart(54),4);
         assertEquals(depthStop.depthStopStart(54),4);
         assertEquals(depthStop.depthStopStart(100),3);
         assertEquals(depthStop.depthStopStart(150),2);
         assertEquals(depthStop.depthStopStart(324),2);
         assertEquals(depthStop.depthStopStart(325),1);         
    }
     @Test
    public void depthStopCorrectionTest() {
        assertEquals(depthStop.depthStopCorrection(100, 0, 5),5);
        assertEquals(depthStop.depthStopCorrection(100, 0, 1),2);
        assertEquals(depthStop.depthStopCorrection(18, 8, 10),10);
        assertEquals(depthStop.depthStopCorrection(18, 8, 3),7);
        assertEquals(depthStop.depthStopCorrection(60, 47, 3),6);
        assertEquals(depthStop.depthStopCorrection(60, 45, 3),5);
        assertEquals(depthStop.depthStopCorrection(60, 37, 3),4);
        assertEquals(depthStop.depthStopCorrection(60, 0, 3),3);
        assertEquals(depthStop.depthStopCorrection(60, 10, 3),3);
        assertEquals(depthStop.depthStopCorrection(600, 47, 0),1);
    }
}
