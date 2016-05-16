package common.model;

import java.awt.Color;
import server.model.Map;
import server.model.object.Cell;

/**
 *
 * @author zsiga
 */
public class SimpleCell extends SimpleMapObject{
    
    public SimpleCell(float x, float y, int radius, int mass, Color color) {
        super(x, y, radius, mass, color);
    }
    
}
