
package client.model.object;

import client.model.Map;
import client.model.helper.Attributes;

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
        
        super(map, position, new Attributes(radius, COLOR));
        
    }
    
}
