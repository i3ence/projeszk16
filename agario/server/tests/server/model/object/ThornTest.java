/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.object;

import common.model.SimpleThorn;
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
public class ThornTest {
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
     * Test of simplify method, of class Thorn.
     */
    @Test
    public void testSimplify() {
        Thorn food = new Thorn(5.5f, 5.5f, 10, 20, map);
        SimpleThorn sThorn = food.simplify();
        assertEquals(5.5f, sThorn.getX(), 0.01f);
        assertEquals(20, sThorn.getMass());
        assertEquals(10, sThorn.getRadius());
    }
    
}
