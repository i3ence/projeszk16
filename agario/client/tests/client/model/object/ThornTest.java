/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model.object;

import common.model.Thorn;
import java.awt.Color;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hegym
 */
public class ThornTest {
    
    public ThornTest() {
    }

    /**
     * Test of fromSimpleThorn method, of class Thorn.
     */
    @Test
    public void testFromSimpleThorn() {
        System.out.println("fromSimpleThorn");
        Thorn sThorn = new Thorn(0.2f, 0.5f, 5, 5);
        Thorn result = Thorn.fromSimpleThorn(sThorn);
        assertEquals(new Vector2f(0.2f, 0.5f), result.getPosition());
        assertEquals(5, result.getAttributes().getRadius());
        assertEquals(new Vector3f(Color.green.getRed(), Color.green.getGreen(), Color.green.getBlue()), result.getAttributes().getColor());
    }
    
}
