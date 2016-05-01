
package client.model.map.object;

import client.model.map.Map;
import client.model.map.helper.Attributes;

import org.joml.Vector2i;
import org.joml.Vector3f;

/**
 *
 * @author zoli-
 */
public class Food extends MapObject {

    private static final Vector3f COLOR = new Vector3f(0.1f, 1.f, 0.1f);
    
    public Food(Map map, Vector2i position, int radius) {
        
        super(map, position, new Attributes(radius, COLOR));
        
    }
    
}
