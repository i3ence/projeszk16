/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model.object;

import client.Util;
import common.model.SimpleCell;
import java.awt.Color;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hegym
 */
public class CellTest {
    
    public CellTest() {
    }

    /**
     * Test of fromSimpleCell method, of class Cell.
     */
    @Test
    public void testFromSimpleCell() {
        System.out.println("fromSimpleCell");
        SimpleCell sCell = new SimpleCell(54321, "TestCell2", 0.2f, 0.2f, 2, 5, Color.yellow);
        Cell result = Cell.fromSimpleCell(sCell);
        assertEquals(54321, result.getId());
        assertEquals("TestCell2", result.getName());
        assertEquals(5, result.getMass());
        assertEquals(2, result.attributes.getRadius());
        assertEquals(Util.convertColor(Color.yellow), result.attributes.getColor());
    }
    
}
