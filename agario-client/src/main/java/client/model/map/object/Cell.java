
package client.model.map.object;

import client.Util;
import client.model.map.Map;
import client.model.map.helper.Attributes;

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
    
}
