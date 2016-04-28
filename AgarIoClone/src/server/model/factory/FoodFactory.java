/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.factory;

import server.model.Map;
import server.model.object.Food;
import java.util.Random;

/**
 *
 * @author zoli-
 */
public class FoodFactory {

    private int spawnCountDivider;
    private int tick;
    private Map map;

    public FoodFactory(Map map) {
        this.map = map;
        this.spawnCountDivider = 10;
        this.tick = 0;
    }

    public FoodFactory(Map map, int divider) {
        this.map = map;
        this.spawnCountDivider = divider;
        this.tick = 0;
    }

    public void setSpawnCountDivider(int divider) {
        this.spawnCountDivider = divider;
    }

    public int getSpawnCountDivider() {
        return this.spawnCountDivider;
    }

    public void spawn() {
        if (this.tick % this.spawnCountDivider == 0) {
            Random rand = new Random();
            float x;
            float y;
            do {
                x = rand.nextFloat();
                y = rand.nextFloat();
            } while (this.map.isEmptySpace(x, y, 1));
            Food food = new Food(x, y, 1, 0, 1, this.map);
            this.map.addFood(food);
        }
        this.tick++;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return this.map;
    }

}
