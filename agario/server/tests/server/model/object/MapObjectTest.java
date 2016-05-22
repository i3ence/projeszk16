/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.object;

import java.awt.Color;
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
    int port = 0;
    
    /**
     * Runs before each test.
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    @Before
    public void beforeTests() throws IOException, InterruptedException {
        core = new Core(port);
        map = new Map(core);
        map.thorns.clear();
        map.foods.clear();
        port++;
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
     * Test of getIntersectionWithOtherObject method, of class MapObject, with two objects on each other.
     */
    @Test
    public void testGetIntersectionWithOtherObjectCovering() {
        Cell cell1 = new Cell(map, 5.0f, 5.0f, 4, 4, Color.red, "Cell1", 10);
        Cell cell2 = new Cell(map, 5.0f, 5.0f, 4, 4, Color.red, "Cell2", 10);
        assertEquals(100f, cell1.getIntersectionWithOtherObject(cell2), 0.01f);
    }
    
    /**
     * Test of getIntersectionWithOtherObject method, of class MapObject, with two touching objects.
     */
    @Test
    public void testGetIntersectionWithOtherObjectIntersecting() {
        Cell cell1 = new Cell(map, 5.0f, 5.0f, 4, 4, Color.red, "Cell1", 10);
        Cell cell2 = new Cell(map, 6.5f, 5.0f, 4, 4, Color.red, "Cell2", 10);
        assertEquals(94.03f, cell1.getIntersectionWithOtherObject(cell2), 0.01f);
    }
    
    /**
     * Test of getIntersectionWithOtherObject method, of class MapObject, with two not touching objects.
     */
    @Test
    public void testGetIntersectionWithOtherObjectDistinct() {
        Cell cell1 = new Cell(map, 0.0f, 0.0f, 4, 4, Color.red, "Cell1", 10);
        Cell cell2 = new Cell(map, 25.0f, 25.0f, 4, 4, Color.red, "Cell2", 10);
        assertEquals(0f, cell1.getIntersectionWithOtherObject(cell2), 0.01f);
    }
    
    /**
     * Test of isCoordsWithninGivenArea method, of class MapObject.
     */
    @Test
    public void testIsCoordsNotWithninGivenArea() {
        Food food = new Food(map, 5.0f, 5.0f, 3, 5);
        assertEquals(false, food.isCoordsWithinGivenArea(12.f, 12.f, 3));
    }
    
    /**
     * Test of isCoordsWithninGivenArea method, of class MapObject.
     */
    @Test
    public void testIsCoordsWithninGivenArea() {
        Food food = new Food(map, 5.0f, 5.0f, 10, 20);
        assertEquals(true, food.isCoordsWithinGivenArea(10.f, 8.f, 10));
    }
    
    /**
     * Test if CollidesWith method, of class MapObject correctly returns true.
     */
    @Test
    public void testCollidesWith() {
        Cell cell1 = new Cell(map, 0.0f, 0.0f, 4, 4, Color.red, "Cell1", 10);
        Cell cell2 = new Cell(map, 1.0f, 0.0f, 4, 4, Color.red, "Cell2", 10);
        assertEquals(true, cell1.collidesWith(cell2));
    }
    
    /**
     * Test if CollidesWith method, of class MapObject correctly returns false.
     */
    @Test
    public void testNotCollidesWith() {
        Cell cell1 = new Cell(map, 0.0f, 0.0f, 4, 4, Color.red, "Cell1", 10);
        Cell cell2 = new Cell(map, 25.0f, 25.0f, 4, 4, Color.red, "Cell2", 10);
        assertEquals(false, cell1.collidesWith(cell2));
    }
}
