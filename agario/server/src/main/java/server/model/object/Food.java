package server.model.object;

import java.awt.Color;
import server.model.Map;

/**
 * A Food that can be eaten by Cells
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
    public Food(Map map, float x, float y, int radius, int mass) {
        super(map, x, y, radius, mass, Color.ORANGE);
    }
    
    /**
     * Removes this food instance from the map.
     */
    public void gotEaten() {
        this.map.removeFood(this);
    }
        
    /**
     * Wraps food to a serializable object that can be sent to the client for rendering purposes.
     * @return The Simplified Food object based on this Food.
     */
    public common.model.Food simplify() {
        return new common.model.Food(position, id, radius, mass);
    }

}
