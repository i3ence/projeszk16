/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.object;

import java.io.IOException;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import server.controller.Core;
import server.model.Map;

/**
 *
 * @author hegym
 */
public class FoodTest {
    Core core;
    Map map;
    
    /**
     * Runs before each test.
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    @Before
    public void beforeTests() throws IOException, InterruptedException {
        core = new Core(102);
        map = new Map(core);
    }
    
    /**
     * Runs after each test.
     * @throws java.io.IOException
     */
    @After
    public void afterTests() throws IOException {
        map = null;
        core = null;
    }
    
    /**
     * Test of simplify method, of class Cell.
     */
    @Test
    public void testSimplify() {
        Food food = new Food(map, 5.5f, 5.5f, 10, 20);
        common.model.Food sFood = food.simplify();
        assertEquals(5.5f, sFood.getPosition().x, 0.01f);
        assertEquals(20, sFood.getMass());
        assertEquals(10, sFood.getRadius());
    }
    
}
