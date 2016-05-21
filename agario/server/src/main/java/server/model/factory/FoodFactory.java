package server.model.factory;

import server.model.Map;
import server.model.object.Food;

/**
 * Generates food on the assigned map.
 * 
 * @author zoli-
 */
public class FoodFactory {

    private int spawnCountDivider;
    private int tick;
    private Map map;

    /**
     * Sets the map instance and the count of how many ticks should a new food be created after (10 ticks).
     * 
     * @param map The map, on which food will be placed.
     */
    public FoodFactory(Map map) {
        this.map = map;
        this.spawnCountDivider = 10;
        this.tick = 0;
    }

    /**
     * Sets the map instance and count of how many ticks should a new food be created after.
     * 
     * @param map The map instance.
     * @param divider The tick count for new food creation.
     */
    public FoodFactory(Map map, int divider) {
        this.map = map;
        this.spawnCountDivider = divider;
        this.tick = 0;
    }

    /**
     * Sets the divider of how many ticks should a new food be created after.
     * 
     * @param divider The tick count for new food creation.
     */
    public void setSpawnCountDivider(int divider) {
        this.spawnCountDivider = divider;
    }

    /**
     * Returns the spawnCountDivider.
     * 
     * @return The spawnCountDivider.
     */
    public int getSpawnCountDivider() {
        return this.spawnCountDivider;
    }

    /**
     * Creates a new food object at random coordinates of the map if ticks since the last food creation are equal to the spawnCountDivider.
     */
    public void spawn() {
        if (this.tick % this.spawnCountDivider == 0) {
            float x, y;
            float [] coords = this.map.getRandomCoordsWithEmptyRadiusOf(2);
            x = coords[0];
            y = coords[1];
            Food food = new Food(x, y, 1, 1, this.map);
            this.map.addFood(food);
        }
        this.tick++;
    }

    /**
     * Sets the map instance.
     * 
     * @param map The map instance.
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Returns the map instance.
     * 
     * @return The map instance.
     */
    public Map getMap() {
        return this.map;
    }

}
