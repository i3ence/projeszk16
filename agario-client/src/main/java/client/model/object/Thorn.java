
package client.model.object;

import client.Util;
import client.model.Map;
import client.model.helper.Attributes;
import common.model.SimpleThorn;

import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * A thorn, that reduces the cell size on collision.
 * @author zoli-
 * @author yzsolt
 */
public class Thorn extends MapObject {
    
    private static final Vector3f COLOR = new Vector3f(1.f, 0.1f, 0.1f);

    /**
     * Sets the attributes of the Thorn.
     * @param map The map instance.
     * @param position The position of the Thorn.
     * @param radius The radius of the Thorn.
     */
    public Thorn(Map map, Vector2f position, int radius) {
        super(map, position, new Attributes(radius, COLOR));
    }

    /**
     * Sets the attributes of the Thorn.
     * @param position The position of the Thorn.
     * @param attributes The attributes of the Thorn.
     */
    public Thorn(Vector2f position, Attributes attributes) {
        super(position, attributes);
    }

    /**
     * Unwraps response Simple Thorn object to be used by GL renderer.
     * @param simpleThorn Thorn object 
     * @return A client-side implementation of Thorn
     */
    public static Thorn fromSimpleThorn(SimpleThorn simpleThorn) {
        Vector2f position = new Vector2f(simpleThorn.getX(), simpleThorn.getY());
        Attributes attributes = new Attributes(simpleThorn.getRadius(), Util.convertColor(simpleThorn.getColor()));
        return new Thorn(position, attributes);
    }
    
}
