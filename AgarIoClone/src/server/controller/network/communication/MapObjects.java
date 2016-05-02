/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controller.network.communication;

import java.util.List;
import server.model.object.Cell;
import server.model.object.Food;
import server.model.object.Thorn;

/**
 *
 * @author zoli-
 */
public class MapObjects {

    private final List<Food> foods;
    private final List<Thorn> thorns;
    private final java.util.Map<Integer, Cell> cells;
    
    public MapObjects(List<Food> foods, List<Thorn> thorns, java.util.Map<Integer, Cell> cells) {
        this.foods = foods;
        this.thorns = thorns;
        this.cells = cells;
    }
    
    public java.util.Map<Integer, Cell> getCells() {
        return this.cells;
    }
    
    public List<Food> getFoods() {
        return this.foods;
    }
    
    public List<Thorn> getThorns() {
        return this.thorns;
    }   
}
