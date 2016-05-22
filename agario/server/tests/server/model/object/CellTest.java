/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.object;

//import server.model.object.Cell;
import java.awt.Color;
import java.io.IOException;
import org.joml.Vector2f;
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
     * Test of eatFood method, of class Cell.
     */
    @Test
    public void testEatFood() {
        Cell cell = new Cell(map, 5.0f, 5.0f, 10, 10, Color.red, "Cell1", 10);
        Food food = new Food(map, 15.0f, 5.0f, 6, 6);
        cell.eatFood(food);
        assertEquals(11, cell.getMass());
    }

    /**
     * Test of eatCell method, of class Cell.
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testEatCell() throws InterruptedException {
        Cell cell1 = new Cell(map, 5.0f, 5.0f, 10, 10, Color.red, "Cell1", 10);
        Cell cell2 = new Cell(map, 5.0f, 5.0f, 6, 6, Color.red, "Cell2", 10);
        cell1.eatCell(cell2);
        assertEquals(13, cell1.getMass());
        assertEquals(6, cell2.getMass());
        assertEquals(18, cell2.getRadius());
        cell2.eatCell(cell1);
        assertEquals(12, cell2.getMass());
        assertEquals(10, cell1.getMass());
        assertEquals(28, cell1.getRadius());
    }

    /**
     * Test of decreaseCellWithPercent method, of class Cell.
     */
    @Test
    public void testDecreaseCellWithPercent() {
        Cell cell = new Cell(map, 5.0f, 5.0f, 10, 10, Color.red, "Cell1", 10);
        cell.decreaseCellWithPercent(80);
        assertEquals(2, cell.getMass());
    }

    /**
     * Test of simplify method, of class Cell.
     */
    @Test
    public void testSimplify() {
        Cell cell = new Cell(map, 5.0f, 5.0f, 10, 10, Color.red, "Cell1", 10);
        common.model.Cell sCell = cell.simplify();
        assertEquals("Cell1", sCell.getName());
        assertEquals(10, sCell.getMass());
        assertEquals(28, sCell.getRadius());
        assertEquals(Color.red, sCell.getColor());
        assertEquals(new Vector2f(5.f, 5.f), sCell.getPosition());
    }
    
}
