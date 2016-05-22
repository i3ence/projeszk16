package common.model;

import java.awt.Color;
import org.joml.Vector2f;

/**
 *
 * @author zsiga
 */
public class Food extends MapObject {
    
    /**
     * Sets the attribute of the food.
     * 
     * @param x The x coordinate of the food's position.
     * @param y The y coordinate of the food's position.
     * @param radius The radius of the food.
     * @param mass The mass of the food.
     */
    public Food(Vector2f position, int id, int radius, int mass) {
        super(position, id, radius, mass, Color.ORANGE);
    }
    
}
