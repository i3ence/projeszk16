package server.model.object;

import java.awt.Color;
import server.model.Map;
import common.model.SimpleThorn;

/**
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
    public Thorn(float x, float y, int radius, int mass, Map map) {
        super(x, y, radius, mass, map, Color.RED);
    }

    public SimpleThorn simplify() {
        return new SimpleThorn(this.coords.getX(), this.coords.getY(), this.attr.getRadius(), this.attr.getMass());
    }
}
