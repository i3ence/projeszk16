package common.model;

import java.awt.Color;
import org.joml.Vector2f;

/**
 *
 * @author zsiga
 */
public class Cell extends MapObject {
    
    public Cell(Vector2f position, int id, int radius, int mass, Color color, String name) {
        super(position, id, radius, mass, color, name);
    }
    
}
