/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model.object;

import client.Util;
import client.model.helper.Attributes;
import common.model.Food;
import java.awt.Color;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hegym
 */
public class FoodTest {
    
    public FoodTest() {
    }

    /**
     * Test of fromSimpleFood method, of class Food.
     */
    @Test
    public void testFromSimpleFood() {
        System.out.println("fromSimpleFood");
        Food sFood = new Food(0.2f, 0.5f, 5, 5);
        Food result = Food.fromSimpleFood(sFood);
        assertEquals(new Vector2f(0.2f, 0.5f), result.getPosition());
        assertEquals(5, result.getAttributes().getRadius());
        assertEquals(Util.convertColor(Color.ORANGE), result.getAttributes().getColor());
    }
    
}
