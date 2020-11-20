/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methodTests;

import com.mycompany.tictactoeai.BoardLogic.Board;
import com.mycompany.tictactoeai.BoardLogic.GameStatus;
import com.mycompany.tictactoeai.Methods.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jaakko
 */
public class ArrayListTests {
    private GameStatus status1;
    private GameStatus status2;
    private GameStatus status3;
    private GameStatus status4;
    private GameStatus status5;
    private ArrayList arrayList;
     @Before
    public void setUp() {
        status1 = new GameStatus(new Board(3,3,3),'O');
        status2 = new GameStatus(new Board(3,3,3),'X');
        status3 = new GameStatus(new Board(2,2,2),'O');
        status4 = new GameStatus(new Board(4,2,3),'X');
        status5 = new GameStatus(new Board(5,1,1),'O');
        this.arrayList = new ArrayList();
        
        
    }
     @Test
    public void getValueIndexTest() {
        arrayList.add(status1);
        assertEquals(arrayList.getValueIndex(0),status1); 
        arrayList.add(status2);
        assertEquals(arrayList.getValueIndex(1),status2);  
        arrayList.add(status3);
        assertEquals(arrayList.getValueIndex(2),status3);  
        assertEquals(arrayList.getValueIndex(1),status2);
        assertEquals(arrayList.getValueIndex(0),status1);
    }
     @Test
    public void getAllTest() {
        arrayList.add(status1);
        GameStatus[] statuses = arrayList.getAll();
        assertEquals(statuses[0],status1); 
        arrayList.add(status2);
        statuses = arrayList.getAll();
        assertEquals(statuses[1],status2);  
        arrayList.add(status3);
        statuses = arrayList.getAll();
        assertEquals(statuses[2],status3);  
    }
     @Test
    public void getLength() {
        assertEquals(arrayList.getLength(),0);  
        arrayList.add(status1);
        assertEquals(arrayList.getLength(),1);  
        arrayList.add(status2);
        assertEquals(arrayList.getLength(),2); 
        arrayList.add(status3);
        assertEquals(arrayList.getLength(),3);
        arrayList.add(status4);
        assertEquals(arrayList.getLength(),4); 
        arrayList.add(status5);
        assertEquals(arrayList.getLength(),5); 
    }
}
