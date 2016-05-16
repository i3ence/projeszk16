package common.model;

import java.awt.Color;
import server.model.Map;

/**
 *
 * @author zsiga
 */
public class SimpleThorn extends SimpleMapObject{
    /**
     * Sets the map instance and the attributes of the thorn.
     * 
     * @param x The x position of the thorn.
     * @param y The y position of the thorn.
     * @param radius The radius of the thorn.
     * @param mass The mass of the thorn.
     * @param map The map instance.
     */
    public SimpleThorn(float x, float y, int radius, int mass) {
        super(x, y, radius, mass, Color.GREEN);
    }
}
