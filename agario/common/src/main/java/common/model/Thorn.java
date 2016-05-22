package common.model;

import java.awt.Color;

/**
 *
 * @author zsiga
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
    public Thorn(float x, float y, int radius, int mass) {
        super(x, y, radius, mass, Color.GREEN);
    }
}
