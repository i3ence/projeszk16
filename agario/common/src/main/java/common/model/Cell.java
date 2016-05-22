package common.model;

import java.awt.Color;

/**
 *
 * @author zsiga
 */
public class Cell extends MapObject{
    
    public Cell(int id, String name, float x, float y, int radius, int mass, Color color) {
        super(id, name, x, y, radius, mass, color);
    }
    
}
