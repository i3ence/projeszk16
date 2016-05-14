package server.model.object;

import java.awt.Color;
import server.model.Map;

/**
 *
 * @author zoli-
 */
public class Food extends MapObject {

    /**
     * Sets the attribute of the food.
     * 
     * @param x The x coordinate of the food's position.
     * @param y The y coordinate of the food's position.
     * @param radius The radius of the food.
     * @param mass The mass of the food.
     * @param map The map instance.
     */
    public Food(float x, float y, int radius, int mass, Map map) {
        super(x, y, radius, mass, map, Color.ORANGE);
    }
    
    /**
     * Removes this food instance from the map.
     */
    public void gotEaten() {
        this.map.removeFood(this);
    }
    
}
