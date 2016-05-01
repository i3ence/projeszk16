
package client.model.map.object;

import client.model.map.Map;
import org.joml.Vector2i;
import org.joml.Vector3f;

import client.model.map.helper.Attributes;

/**
 *
 * @author zoli-
 * @author yzsolt
 */
public class Cell extends MapObject {

    private int id;
    private String name;
    private int mass;
    
    public Cell(Map map, Vector2i position, int radius, int id, String name, int mass) {
        
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
    
}
