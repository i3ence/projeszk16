
package client.model.object;

import client.model.Map;
import client.model.helper.Attributes;

import org.joml.Vector2f;

/**
 *
 * @author zoli-
 * @author yzsolt
 */
public abstract class MapObject {
    
    protected Map map;
    protected Vector2f position;
    protected Attributes attributes;
    
     public MapObject(Map map, Vector2f position, Attributes attributes) {
        this.map = map;
        this.position = position;
        this.attributes = attributes;
     }
    
    public Vector2f getPosition(){
        return this.position;
    }
    
    public Attributes getAttributes(){
        return this.attributes;
    }

}
