/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.object;

import common.model.Cell;
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
public class CellTest {
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
     * Test of getIntersectionWithOtherObject method, of class Cell.
     */
    @Test
    public void testGetIntersectionWithOtherObject() {
        Cell cell1 = new Cell(5.0f, 5.0f, 4, 4, map, Color.red, "Cell1", 10);
        Cell cell2 = new Cell(5.0f, 5.0f, 4, 4, map, Color.red, "Cell2", 10);
        assertEquals(100f, cell1.getIntersectionWithOtherObject(cell2), 0.01f);
    }

    /**
     * Test of checkCollisionWithFood method, of class Cell.
     */
    @Test
    public void testCheckCollisionWithFoodTrue() {
        Cell cell = new Cell(5.0f, 5.0f, 4, 4, map, Color.red, "Cell1", 10);
        Food food = new Food(9.4f, 5.0f, 1, 1, map);
        assertEquals(true, cell.checkCollisionWithFood(food));
    }
    
    /**
     * Test of checkCollisionWithFood method, of class Cell.
     */
    @Test
    public void testCheckCollisionWithFoodFalse() {
        Cell cell = new Cell(5.0f, 5.0f, 2, 2, map, Color.red, "Cell1", 10);
        Food food = new Food(15.0f, 5.0f, 1, 1, map);
        assertEquals(false, cell.checkCollisionWithFood(food));
    }

    /**
     * Test of eatFood method, of class Cell.
     */
    @Test
    public void testEatFood() {
        Cell cell = new Cell(5.0f, 5.0f, 10, 10, map, Color.red, "Cell1", 10);
        Food food = new Food(15.0f, 5.0f, 6, 6, map);
        cell.eatFood(food);
        assertEquals(11, cell.getAttributes().getMass());
    }

    /**
     * Test of eatCell method, of class Cell.
     */
    @Test
    public void testEatCell() {
        Cell cell1 = new Cell(5.0f, 5.0f, 10, 10, map, Color.red, "Cell1", 10);
        Cell cell2 = new Cell(5.0f, 5.0f, 6, 6, map, Color.red, "Cell2", 10);
        cell1.eatCell(cell2);
        assertEquals(13, cell1.getAttributes().getMass());
        assertEquals(2, cell2.getStatus());
        assertEquals(6, cell2.getAttributes().getMass());
        assertEquals(6, cell2.getAttributes().getRadius());
        cell2.eatCell(cell1);
        assertEquals(12, cell2.getAttributes().getMass());
        assertEquals(2, cell1.getStatus());
        assertEquals(10, cell1.getAttributes().getMass());
        assertEquals(10, cell1.getAttributes().getRadius());
    }

    /**
     * Test of decreaseCellWithPercent method, of class Cell.
     */
    @Test
    public void testDecreaseCellWithPercent() {
        Cell cell = new Cell(5.0f, 5.0f, 10, 10, map, Color.red, "Cell1", 10);
        cell.decreaseCellWithPercent(80);
        assertEquals(2, cell.getAttributes().getMass());
    }

    /**
     * Test of simplify method, of class Cell.
     */
    @Test
    public void testSimplify() {
        Cell cell = new Cell(5.0f, 5.0f, 10, 10, map, Color.red, "Cell1", 10);
        Cell sCell = cell.simplify(0);
        assertEquals(0, sCell.getId());
        assertEquals("Cell1", sCell.getName());
        assertEquals(10, sCell.getMass());
        assertEquals(28, sCell.getRadius());
        assertEquals(Color.red, sCell.getColor());
    }
    
}
