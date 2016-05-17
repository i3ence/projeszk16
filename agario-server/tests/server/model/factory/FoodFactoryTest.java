/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.factory;

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
public class FoodFactoryTest {
    Core core;
    Map map;
    
    /**
     * Runs before each test.
     */
    @Before
    public void beforeTests() throws IOException {
        core = new Core(12345);
        map = new Map(core);
    }
    
    /**
     * Runs after each test.
     */
    @After
    public void afterTests() throws IOException {
        map = null;
        core.closeServer();
        core = null;
    }
    
    /**
     * Test of spawn method, of class FoodFactory.
     */
    @Test
    public void testSpawn() {
        FoodFactory ff = new FoodFactory(map, 3);
        ff.spawn();
        ff.spawn();
        ff.spawn();
        ff.spawn();
        assertEquals(2, map.foods.size());
    }
}