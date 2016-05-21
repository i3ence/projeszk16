/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;

import common.model.SimpleCell;
import common.model.SimpleFood;
import common.model.SimpleMapObject;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import server.controller.Core;
import server.model.object.Cell;
import server.model.object.Food;
import server.model.object.Thorn;

/**
 *
 * @author hegym
 */
public class MapTest {
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
     * Test of checkCollisions method, of class Map with Thorns
     * @throws java.lang.InterruptedException
     */
    @Test
    public synchronized void testCollisionsThorn() throws InterruptedException {
        wait(100);
        Thorn thorn = map.thorns.get(0);
        Cell cell = new Cell(thorn.getCoords().getX(), thorn.getCoords().getY(), 20, 50, map, Color.red, "Cell", 10);
        map.cells.put(0, cell);
        map.checkCollisions();
        assertEquals(25, map.cells.get(0).getAttributes().getMass());
    }
    
    /**
     * Test of checkCollisions method, of class Map with Foods
     */
    @Test
    public void testCollisionsFood() {
        Food food = new Food(5.5f, 5.5f, 10, 20, map);
        map.foods.add(food);
        Cell cell = new Cell(5.5f, 5.5f, 20, 50, map, Color.red, "Cell", 10);
        map.cells.put(0, cell);
        map.checkCollisions();
        assertEquals(51, map.cells.get(0).getAttributes().getMass());
    }
    
    /**
     * Test of checkCollisions method, of class Map with Cells
     */
    @Test
    public void testCollisionsCell() {
        Cell cell1 = new Cell(5.5f, 5.5f, 20, 20, map, Color.red, "Cell1", 10);
        map.cells.put(0, cell1);
        Cell cell2 = new Cell(5.5f, 5.5f, 20, 60, map, Color.red, "Cell2", 10);
        map.cells.put(1, cell2);
        map.checkCollisions();
        assertEquals(70, map.cells.get(1).getAttributes().getMass());
        assertEquals(2, map.cells.get(0).getStatus());
    }
    
    /**
     * Test of updateCell method, of class Map.
     */
    @Test
    public void testUpdateCell() {
        Cell cell = new Cell(5.5f, 5.5f, 20, 20, map, Color.red, "Cell", 10);
        map.cells.put(0, cell);
        map.updateCell(0, 5.2f);
        assertEquals(5.2f, map.cells.get(0).getMovingAngle(), 0.1f);
    }

    /**
     * Test of addCell method, of class Map.
     */
    @Test
    public void testAddCell() {
        map.addCell(234, "Added");
        assertEquals("Added", map.cells.get(234).getName());
        assertEquals(23, map.cells.get(234).getAttributes().getRadius());
    }

    /**
     * Test of reAnimateCell method, of class Map.
     */
    @Test
    public void testReAnimateCell() {
        Cell cell = new Cell(5.5f, 5.5f, 20, 20, map, Color.red, "Cell", 10);
        cell.setStatus(2);
        map.cells.put(0, cell);
        map.reAnimateCell(0, "NewName");
        assertEquals("NewName", map.cells.get(0).getName());
        assertEquals(0, map.cells.get(0).getStatus());
    }

    /**
     * Test of isEmptySpace method, of class Map with empty map
     */
    @Test
    public void testIsEmptySpace() {
        boolean result = map.isEmptySpace(2.0f, 2.0f, 1);
        assertEquals(true, result);
    }
    
    /**
     * Test of isEmptySpace method, of class Map with non-empty map
     */
    @Test
    public void testIsEmptySpaceFalse() {
        Food food = new Food(2.5f, 2.5f, 1, 1, map);
        map.foods.add(food);
        boolean result = map.isEmptySpace(3.0f, 3.0f, 2);
        assertEquals(false, result);
    }

    /**
     * Test of createSimpleMapObjects method, of class Map.
     */
    @Test
    public void testCreateSimpleMapObjects() {        
        Food food = new Food(2.0f, 2.0f, 12, 20, map);
        SimpleFood sFood = new SimpleFood(2.0f, 2.0f, 12, 20);
        map.foods.add(food);
        
        Cell cell = new Cell(5.5f, 5.5f, 20, 50, map, Color.red, "Cell", 10);
        //SimpleCell sCell = new SimpleCell(0, "Cell", 5.5f, 5.5f, 20, 50, Color.red);
        map.cells.put(0, cell);
        
        List<? super SimpleMapObject> result = map.createSimpleMapObjects();
        
        for (Object o : result) {
            if (o instanceof SimpleFood)
                assertEquals(12, ((SimpleFood) o).getRadius());
            if (o instanceof SimpleCell)
                assertEquals("Cell", ((SimpleCell) o).getName());
        }
    }
}
