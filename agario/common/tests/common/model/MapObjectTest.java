/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.model;

import java.awt.Color;
import org.joml.Vector2f;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hegym
 */
public class MapObjectTest {
    /**
     * Test of equals method, of class MapObject.
     */
    @Test
    public void testEquals() {
        Cell cell1 = new Cell(new Vector2f(0.1f, 0.1f), 3, 10, 10, Color.pink, "TestCell");
        Cell cell2 = new Cell(new Vector2f(0.1f, 0.1f), 3, 10, 10, Color.pink, "TestCell");
        Cell cell3 = new Cell(new Vector2f(0.1f, 0.1f), 2, 10, 10, Color.pink, "TestCell");
        
        assertEquals(cell1, cell2);
        assertEquals(false, cell1.equals(cell3));
    }
    
}
