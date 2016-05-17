/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import client.Util;
import client.model.helper.Attributes;
import client.model.object.Cell;
import client.model.object.Food;
import common.model.SimpleCell;
import common.model.SimpleFood;
import common.model.SimpleMapObject;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.joml.Vector2f;
import org.joml.Vector3f;
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
     * Test of updateWithSimpleMapObjects method, of class Map.
     */
    @Test
    public void testUpdateWithSimpleMapObjects() {
        System.out.println("updateWithSimpleMapObjects");
        Map instance = new Map(5);
        
        Cell initial = new Cell(12345, "TestCell", new Vector2f(0.5f, 0.5f), 2, new Attributes(5, new Vector3f(0.5f, 0.5f, 0.5f)));
        instance.objects.add(initial);
        
        SimpleFood sFood = new SimpleFood(0.5f, 0.5f, 5, 5);
        SimpleCell sCell = new SimpleCell(54321, "TestCell2", 0.2f, 0.2f, 5, 5, Color.yellow);
        
        List<SimpleMapObject> simpleObjects = new ArrayList<>();
        simpleObjects.add(sFood);
        simpleObjects.add(sCell);
        
        instance.updateWithSimpleMapObjects(simpleObjects);
        
        assertEquals(new Vector2f(0.5f, 0.5f), instance.objects.get(0).getPosition());
        assertEquals(5, instance.objects.get(0).getAttributes().getRadius());
        assertEquals(Util.convertColor(Color.ORANGE), instance.objects.get(0).getAttributes().getColor());
        
        assertEquals(54321, ((Cell)instance.objects.get(1)).getId());
        assertEquals("TestCell2", ((Cell)instance.objects.get(1)).getName());
        assertEquals(5, ((Cell)instance.objects.get(1)).getMass());
        assertEquals(5, instance.objects.get(1).getAttributes().getRadius());
        assertEquals(Util.convertColor(Color.yellow), instance.objects.get(1).getAttributes().getColor());
    }
    
}
