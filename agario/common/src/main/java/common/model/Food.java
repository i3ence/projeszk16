package common.model;

import java.awt.Color;
import org.joml.Vector2f;

/**
* A food representation to be serialized.
 * @author zsiga
 */
public class Food extends MapObject {
    
    /**
     * Sets the attribute of the food.
     * 
     * @param position Position of the food.
     * @param id ID of the food.
     * @param radius The radius of the food.
     * @param mass The mass of the food.
     */
    public Food(Vector2f position, int id, int radius, int mass) {
        super(position, id, radius, mass, Color.ORANGE);
    }
    
}
