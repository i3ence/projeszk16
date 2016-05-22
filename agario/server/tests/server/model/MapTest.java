/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import org.joml.Vector2f;
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
    int port = 0;
    
    /**
     * Runs before each test.
     */
    @Before
    public void beforeTests() throws IOException {
        core = new Core(port);
        map = new Map(core);
        map.thorns.clear();
        map.foods.clear();
        port++;
    }
    
    /**
     * Runs after each test.
     */
    @After
    public void afterTests() throws IOException {
        map = null;
        //core.closeServer();
        core = null;
    }

    /**
     * Test of checkCollisions method, of class Map with Thorns
     */
    @Test
    public void testCollisionsThorn() {
        Thorn thorn = new Thorn(map, 5.5f, 5.5f, 30, 30);
        map.thorns.add(thorn);
        Cell cell = new Cell(map, 5.5f, 5.5f, 10, 10, Color.red, "Cell", 10);
        map.cells.put(0, cell);
        map.checkCollisions();
        assertEquals(5, map.cells.get(0).getMass());
    }
    
    /**
     * Test of checkCollisions method, of class Map with Foods
     */
    @Test
    public void testCollisionsFood() {
        Food food = new Food(map, 5.5f, 5.5f, 10, 20);
        map.foods.add(food);
        Cell cell = new Cell(map, 5.5f, 5.5f, 20, 50, Color.red, "Cell", 10);
        map.cells.put(0, cell);
        map.checkCollisions();
        assertEquals(51, map.cells.get(0).getMass());
    }
    
    /**
     * Test of checkCollisions method, of class Map with Cells
     */
    @Test
    public void testCollisionsCell() {
        Cell cell1 = new Cell(map, 5.5f, 5.5f, 20, 20, Color.red, "Cell1", 10);
        map.cells.put(0, cell1);
        Cell cell2 = new Cell(map, 5.5f, 5.5f, 20, 60, Color.red, "Cell2", 10);
        map.cells.put(1, cell2);
        map.checkCollisions();
        assertEquals(70, map.cells.get(1).getMass());
        assertEquals(20, map.cells.get(0).getMass());
    }
    
    /**
     * Test of updateCell method, of class Map.
     */
    @Test
    public void testUpdateCell() {
        Cell cell = new Cell(map, 5.5f, 5.5f, 20, 20, Color.red, "Cell", 10);
        map.cells.put(0, cell);
        map.updateCell(0, 5.2f, 2.f);
        assertEquals(5.2f, map.cells.get(0).getMovingAngle(), 0.1f);
    }

    /**
     * Test of reAnimateCell method, of class Map.
     */
    @Test
    public void testReAnimateCell() {
        Cell cell = new Cell(map, 5.5f, 5.5f, 20, 20, Color.red, "Cell", 10);
        map.cells.put(0, cell);
        map.reAnimateCell(0, "NewName");
        assertEquals("NewName", map.cells.get(0).getName());
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
        Food food = new Food(map, 2.5f, 2.5f, 1, 1);
        map.foods.add(food);
        boolean result = map.isEmptySpace(3.0f, 3.0f, 2);
        assertEquals(false, result);
    }

    /**
     * Test of createSimpleMapObjects method, of class Map.
     */
    @Test
    public void testCreateSimpleMapObjects() {        
        Food food = new Food(map, 2.0f, 2.0f, 12, 20);
        common.model.Food sFood = new common.model.Food(new Vector2f(2.0f, 2.0f), 1, 12, 20);
        map.foods.add(food);
        
        Cell cell = new Cell(map, 5.5f, 5.5f, 20, 50, Color.red, "Cell", 10);
        common.model.Cell sCell = new common.model.Cell(new Vector2f(5.5f, 5.5f), 1, 20, 50, Color.red, "Cell");
        map.cells.put(0, cell);
        
        List<common.model.MapObject> result = map.createMapObjects();
        
        for (Object o : result) {
            if (o instanceof common.model.Food)
                assertEquals(12, ((common.model.Food) o).getRadius());
            if (o instanceof common.model.Cell)
                assertEquals("Cell", ((common.model.Cell) o).getName());
        }
    }
}
