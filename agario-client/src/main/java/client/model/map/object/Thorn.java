
package client.model.map.object;

import client.model.map.Map;
import client.model.map.helper.Attributes;

import org.joml.Vector2i;
import org.joml.Vector3f;

/**
 *
 * @author zoli-
 * @author yzsolt
 */
public class Thorn extends MapObject {
    
    private static final Vector3f COLOR = new Vector3f(1.f, 0.1f, 0.1f);

    public Thorn(Map map, Vector2i position, int radius) {
        super(map, position, new Attributes(radius, COLOR));
    }
    
}
