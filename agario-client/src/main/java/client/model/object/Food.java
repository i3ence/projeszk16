
package client.model.object;

import client.Util;
import client.model.Map;
import client.model.helper.Attributes;
import common.model.SimpleFood;

import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * A Food that can be eaten by Cells
 * @author zoli-
 * @author yzsolt
 */
public class Food extends MapObject {

    private static final Vector3f COLOR = new Vector3f(0.1f, 1.f, 0.1f);
    
    /**
     * Sets the attributes of the food.
     * @param map The map instance.
     * @param position The position of the food.
     * @param radius The radius of the food.
     */
    public Food(Map map, Vector2f position, int radius) {
        super(position, new Attributes(radius, COLOR));
    }

    /**
     * Sets the attributes of the food.
     * @param position The position of the food.
     * @param attributes The attributes of the food.
     */
    public Food(Vector2f position, Attributes attributes) {
        super(position, attributes);
    }
    
    /**
     * Unwraps response Simple Food object to be used by GL renderer.
     * @param simpleFood Food object 
     * @return A client-side implementation of Food
     */
    public static Food fromSimpleFood(SimpleFood simpleFood) {
        Vector2f position = new Vector2f(simpleFood.getX(), simpleFood.getY());
        Attributes attributes = new Attributes(simpleFood.getRadius(), Util.convertColor(simpleFood.getColor()));
        return new Food(position, attributes);
    }
    
}
