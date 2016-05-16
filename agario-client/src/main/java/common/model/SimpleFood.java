package common.model;

import java.awt.Color;

/**
 *
 * @author zsiga
 */
public class SimpleFood extends SimpleMapObject{
    
    /**
     * Sets the attribute of the food.
     * 
     * @param x The x coordinate of the food's position.
     * @param y The y coordinate of the food's position.
     * @param radius The radius of the food.
     * @param mass The mass of the food.
     * @param map The map instance.
     */
    public SimpleFood(float x, float y, int radius, int mass) {
        super(x, y, radius, mass, Color.ORANGE);
    }
    
}
