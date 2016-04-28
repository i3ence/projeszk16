/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;

import server.model.object.*;
import server.model.factory.*;
import java.util.*;

/**
 *
 * @author zoli-
 */
public class Map {
    
    List<Food> foods;
    List<Thorn> thorns;
    List<Cell> cells;
    FoodFactory foodFactory;
    ThornFactory thornFactory;
    
    public Map() {
        this.foods = new ArrayList<Food>();
        this.thorns = new ArrayList<Thorn>();
        this.cells = new ArrayList<Cell>();
        this.foodFactory = new FoodFactory(this, 5);
        this.thornFactory = new ThornFactory(this);
        
        for (int i = 0; i < 10; i++) {
            this.thornFactory.spawn();
        }
    }

    public void tick() {
        this.foodFactory.spawn();
    }

    public void addFood(Food food) {
        this.foods.add(food);
    }
    
    public void removeFood(Food food) {
        this.foods.remove(food);
    }

    public void addThorn(Thorn thorn) {
        this.thorns.add(thorn);
    }
    
    public void removeThorn(Thorn thorn) {
        this.thorns.remove(thorn);
    }
    
    public void addCell(Cell cell) {
        this.cells.add(cell);
    }
    
    public void removeCell(Cell cell) {
        this.cells.remove(cell);
    }
    
    public void setFoodFactory(FoodFactory foodFactory) {
        this.foodFactory = foodFactory;
    }
    
    public FoodFactory getFoodFactory() {
        return this.foodFactory;
    }
    
    public void setThornFactory(ThornFactory thornFactory) {
        this.thornFactory = thornFactory;
    }
    
    public ThornFactory getThornFactory() {
        return this.thornFactory;
    }

    public boolean isEmptySpace(float x, float y, int radius) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
