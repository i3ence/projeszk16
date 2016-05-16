
package client.model.object;

import client.Util;
import client.model.Map;
import client.model.helper.Attributes;
import common.model.SimpleFood;

import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 *
 * @author zoli-
 * @author yzsolt
 */
public class Food extends MapObject {

    private static final Vector3f COLOR = new Vector3f(0.1f, 1.f, 0.1f);
    
    public Food(Map map, Vector2f position, int radius) {
        super(position, new Attributes(radius, COLOR));
    }

    public Food(Vector2f position, Attributes attributes) {
        super(position, attributes);
    }
    
    public static Food fromSimpleFood(SimpleFood simpleFood) {
        Vector2f position = new Vector2f(simpleFood.getX(), simpleFood.getY());
        Attributes attributes = new Attributes(simpleFood.getRadius(), Util.convertColor(simpleFood.getColor()));
        return new Food(position, attributes);
    }
    
}
