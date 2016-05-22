/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import common.model.Cell;
import common.model.Food;
import common.model.MapObject;
import common.model.Thorn;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.joml.Vector2f;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hegym
 */
public class MapTest {
    
    public MapTest() {
    }

    /**
     * Test of updateObjects method, of class Map.
     */
    @Test
    public void testUpdateObjects() {
        Map instance = new Map(5);
        
        Cell initial1 = new Cell(new Vector2f(0.1f, 0.1f), 3, 10, 10, Color.pink, "TestCell");
        Food initial2 = new Food(new Vector2f(0.1f, 0.1f), 12, 5, 5);
        Thorn initial3 = new Thorn(new Vector2f(0.3f, 0.3f), 15, 5, 5);
        instance.objects.add(initial1);
        instance.objects.add(initial2);
        instance.objects.add(initial3);
        
        Cell cell = new Cell(new Vector2f(0.2f, 0.5f), 3, 5, 5, Color.yellow, "TestCell");
        Food food = new Food(new Vector2f(0.2f, 0.2f), 12, 2, 2);
        
        List<MapObject> simpleObjects = new ArrayList<>();
        simpleObjects.add(food);
        simpleObjects.add(cell);
        
        instance.updateObjects(simpleObjects);
        
        assertEquals(2, instance.objects.size());
        
        assertEquals(new Vector2f(0.2f, 0.5f), instance.objects.get(0).getPosition());
        assertEquals(5, instance.objects.get(0).getRadius());
        assertEquals(5, instance.objects.get(0).getMass());
        assertEquals(3, instance.objects.get(0).getId());
        assertEquals(Color.yellow, instance.objects.get(0).getColor());
        assertEquals("TestCell", instance.objects.get(0).getName());
        
        assertEquals(new Vector2f(0.2f, 0.2f), instance.objects.get(1).getPosition());
        assertEquals(2, instance.objects.get(1).getRadius());
        assertEquals(2, instance.objects.get(1).getMass());
        assertEquals(12, instance.objects.get(1).getId());
    }
    
}
