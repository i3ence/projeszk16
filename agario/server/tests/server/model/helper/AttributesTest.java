/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.helper;

import java.awt.Color;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hegym
 */
public class AttributesTest {
    
    /**
     * Test of decreaseMassWith method, of class Attributes with small number
     */
    @Test
    public void testDecreaseMassWithSmallNum() {
        System.out.println("decreaseMassWith");
        Attributes instance = new Attributes(10, 12, Color.red);
        instance.decreaseMassWith(10);
        assertEquals(2, instance.getMass());
    }
    
    /**
     * Test of decreaseMassWith method, of class Attributes with large number
     */
    @Test
    public void testDecreaseMassWithLargeNum() {
        System.out.println("decreaseMassWith");
        Attributes instance = new Attributes(10, 12, Color.red);
        instance.decreaseMassWith(20);
        assertEquals(1, instance.getMass());
    }

    /**
     * Test of increaseMassWith method, of class Attributes.
     */
    @Test
    public void testIncreaseMassWith() {
        System.out.println("increaseMassWith");
        Attributes instance = new Attributes(10, 12, Color.red);
        instance.increaseMassWith(6);
        assertEquals(18, instance.getMass());
    }
    
}
