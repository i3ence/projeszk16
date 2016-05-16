
package client.model.object;

import client.Util;
import client.model.Map;
import client.model.helper.Attributes;
import common.model.SimpleThorn;

import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 *
 * @author zoli-
 * @author yzsolt
 */
public class Thorn extends MapObject {
    
    private static final Vector3f COLOR = new Vector3f(1.f, 0.1f, 0.1f);

    public Thorn(Map map, Vector2f position, int radius) {
        super(map, position, new Attributes(radius, COLOR));
    }

    public Thorn(Vector2f position, Attributes attributes) {
        super(position, attributes);
    }

    public static Thorn fromSimpleThorn(SimpleThorn simpleThorn) {
        Vector2f position = new Vector2f(simpleThorn.getX(), simpleThorn.getY());
        Attributes attributes = new Attributes(simpleThorn.getRadius(), Util.convertColor(simpleThorn.getColor()));
        return new Thorn(position, attributes);
    }
    
}
