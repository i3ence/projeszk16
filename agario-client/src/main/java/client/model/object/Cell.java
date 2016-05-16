
package client.model.object;

import client.Util;
import client.model.Map;
import client.model.helper.Attributes;
import common.model.SimpleCell;
import common.model.SimpleMapObject;

import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 *
 * @author zoli-
 * @author yzsolt
 */
public class Cell extends MapObject {
    
    private static final int MAX_SPEED = 3;

    private int id;
    private String name;
    private int mass;
    
    public Cell(Map map, Vector2f position, int radius, int id, String name, int mass) {
        
        // TODO: calculate some random color
        
        super(map, position, new Attributes(radius, new Vector3f(0.2f, 0.6f, 0.5f)));
        
        this.id = id;
        this.name = name;
        this.mass = mass;

    }

    public Cell(int id, String name, Vector2f position, Attributes attributes) {
        super(position, attributes);
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getMass() {
        return this.mass;
    }
    
    public void move(Vector2f movement) {
        
        float speed = movement.length();
        
        if (speed > MAX_SPEED) {
            
            float ratio = MAX_SPEED / speed;
            
            movement.mul(ratio);
            
        }
        
        position.add(movement);
        
        int radius = this.attributes.getRadius();
        
        position.x = Util.clamp(position.x, 0 + radius, map.getSize() - radius);
        position.y = Util.clamp(position.y, 0 + radius, map.getSize() - radius);
        
    }
    
        
    public static Cell fromSimpleCell(SimpleCell simpleCell) {
        int id = simpleCell.getId();
        String name = simpleCell.getName();
        Vector2f position = new Vector2f(simpleCell.getX(), simpleCell.getY());
        Attributes attributes = new Attributes(simpleCell.getRadius(), Util.convertColor(simpleCell.getColor()));
        
        return new Cell(id, name, position, attributes);
    }
    
}
