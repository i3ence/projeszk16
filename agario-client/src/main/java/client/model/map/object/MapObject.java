
package client.model.map.object;

import client.model.map.Map;
import client.model.map.helper.Attributes;

import org.joml.Vector2i;

/**
 *
 * @author zoli-
 */
public abstract class MapObject {
    
    protected Map map;
    protected Vector2i position;
    protected Attributes attributes;
    
     public MapObject(Map map, Vector2i position, Attributes attributes) {
        this.map = map;
        this.position = position;
        this.attributes = attributes;
     }
    
    public Vector2i getPosition(){
        return this.position;
    }
    
    public Attributes getAttributes(){
        return this.attributes;
    }

}
