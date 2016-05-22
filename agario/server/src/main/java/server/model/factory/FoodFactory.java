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
    private int limit;
    
    private void spawnFoodAtRandomPosition() throws InterruptedException {
        
        float x, y;
        float [] coords = this.map.getRandomCoordsWithEmptyRadiusOf(2);
        x = coords[0];
        y = coords[1];
        Food food = new Food(this.map, x, y, 3, 1);
        this.map.addFood(food);
        
    }

    /**
     * Sets the map instance and the count of how many ticks should a new food be created after (10 ticks).
     * 
     * @param map The map, on which food will be placed.
     */
    public FoodFactory(Map map) {
        this.map = map;
        this.spawnCountDivider = 10;
        this.tick = 0;
        this.limit = map.getSize() / 2;
    }

    /**
     * Sets the map instance and count of how many ticks should a new food be created after.
     * 
     * @param map The map instance.
     * @param limit Maximal amount of food on the map.
     * @param divider The tick count for new food creation.
     */
    public FoodFactory(Map map, int limit, int divider) {
        this(map);
        this.spawnCountDivider = divider;
        this.limit = limit;
    }
    
    public void fillMapToLimit() throws InterruptedException {
        
        while (this.map.foods.size() < this.limit) {
            this.spawnFoodAtRandomPosition();
        }
        
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
    public void spawn() throws InterruptedException {
        if (this.tick % this.spawnCountDivider == 0 && this.map.foods.size() < this.limit) {
            this.spawnFoodAtRandomPosition();
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
