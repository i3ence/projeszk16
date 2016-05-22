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
public class MapObjectTest {
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
     * Test of isCoordsWithninGivenArea method, of class MapObject.
     */
    @Test
    public void testIsCoordsNotWithninGivenArea() {
        Food food = new Food(5.0f, 5.0f, 3, 5, map);
        assertEquals(false, food.isCoordsWithinGivenArea(12.f, 12.f, 3));
    }
    
    /**
     * Test of isCoordsWithninGivenArea method, of class MapObject.
     */
    @Test
    public void testIsCoordsWithninGivenArea() {
        Food food = new Food(5.0f, 5.0f, 10, 20, map);
        assertEquals(true, food.isCoordsWithinGivenArea(10.f, 8.f, 10));
    }
    
}
