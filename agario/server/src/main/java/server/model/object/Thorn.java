package server.model.object;

import java.awt.Color;
import server.model.Map;

/**
 * A thorn, that reduces the cell size on collision.
 * 
 * @author zoli-
 */
public class Thorn extends MapObject{

    /**
     * Sets the map instance and the attributes of the thorn.
     * 
     * @param x The x position of the thorn.
     * @param y The y position of the thorn.
     * @param radius The radius of the thorn.
     * @param mass The mass of the thorn.
     * @param map The map instance.
     */
    public Thorn(Map map, float x, float y, int radius, int mass) {
        super(map, x, y, radius, mass, Color.GREEN);
    }

    /**
     * Wraps thorn to a serializable object that can be sent to the client for rendering purposes.
     * @return The Simplified Thorn object based on this Thorn.
     */
    public common.model.Thorn simplify() {
        return new common.model.Thorn(position, id, radius, mass);
    }
}
