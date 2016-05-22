package common.model;

import java.awt.Color;
import org.joml.Vector2f;

/**
 *
 * @author zsiga
 */
public class Thorn extends MapObject{
    /**
     * Sets the map instance and the attributes of the thorn.
     * 
     * @param position The position of the thorn.
     * @param id ID of the thorn.
     * @param radius The radius of the thorn.
     * @param mass The mass of the thorn.
     */
    public Thorn(Vector2f position, int id, int radius, int mass) {
        super(position, id, radius, mass, Color.GREEN);
    }
}
