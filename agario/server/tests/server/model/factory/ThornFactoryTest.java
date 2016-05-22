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
public class ThornFactoryTest {
    Core core;
    Map map;
    
    /**
     * Runs before each test.
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    @Before
    public void beforeTests() throws IOException, InterruptedException {
        core = new Core(100);
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
     * Test of spawn method, of class ThornFactory.
     */
    @Test
    public void testSpawn() {
        // Map construcot already has a thorn factory
        assertEquals(10, map.thorns.size());
    }
}
