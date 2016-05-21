
package client.model.object;

import client.model.Map;
import client.model.helper.Attributes;

import org.joml.Vector2f;

/**
 * An abstract class that represents all the objects that can be found on the map.
 * To be extended by different Map Objects (Food, Cell, Thorn)
 * @author zoli-
 * @author yzsolt
 */
public abstract class MapObject {
    
    protected Map map;
    protected Vector2f position;
    protected Attributes attributes;
    
    /**
     * Sets the attributes of a map object.
     * @param map The map assigned to the object.
     * @param position The position of the object.
     * @param attributes The attributes of the object.
     */
    public MapObject(Map map, Vector2f position, Attributes attributes) {
        this.map = map;
        this.position = position;
        this.attributes = attributes;
     }

    /**
     * Sets the attributes of a map object without assigning its map.
     * @param position The position of the object.
     * @param attributes The attributes of the object.
     */
    public MapObject(Vector2f position, Attributes attributes) {
        this.position = position;
        this.attributes = attributes;
    }
     
    /**
     * Returns the position of the map object.
     * @return position of the map object.
     */
    public Vector2f getPosition(){
        return this.position;
    }
    
    /**
     * Returns the attributes of the map object.
     * @return attributes of the map object.
     */
    public Attributes getAttributes(){
        return this.attributes;
    }

}
