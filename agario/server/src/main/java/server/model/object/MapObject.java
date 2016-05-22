package server.model.object;

/**
 * An abstract class that represents all the objects that can be found on the map.
 * To be extended by different Map Objects (Food, Cell, Thorn)
 * 
 * @author zoli-
 */
import java.awt.Color;
import org.joml.Vector2f;
import server.model.Map;
import server.model.helper.Attributes;

public abstract class MapObject {

    protected Vector2f coords;
    protected Attributes attr;
    protected final Map map;

    /**
     * Sets the map instance and the attributes of the object.
     * 
     * @param x The x coordinate of the object's position.
     * @param y The x coordinate of the object's position.
     * @param map The map instance.
     */
    public MapObject(float x, float y, Map map) {
        this.coords = new Vector2f(x, y);
        this.attr = new Attributes(1, 2, Color.ORANGE);
        this.map = map;
    }

    /**
     * Sets the map instance and the attributes of the object.
     * 
     * @param x The x coordinate of the object's position.
     * @param y The x coordinate of the object's position.
     * @param radius The radius of the object.
     * @param mass The mass of the object.
     * @param map The map instance.    
     * @param color The color of the object.
     */
    public MapObject(float x, float y, int radius, int mass, Map map, Color color) {
        this.coords = new Vector2f(x, y);
        this.attr = new Attributes(radius, mass, color);
        this.map = map;
    }

    /**
     * Returns the coordinates of the object.
     * 
     * @return The coordinates object.
     */
    public Vector2f getCoords() {
        return this.coords;
    }

    /**
     * Returns the attributes of the object.
     * 
     * @return The attributes object.
     */
    public Attributes getAttributes() {
        return this.attr;
    }

    /**
     * Checks if the given coordinates are within a certain radius from the object's edge.
     * 
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param distanceFromTheEdge The given area that the check is made against.
     * @return True if the given x,y coordinates are within the given distanceFromTheEdge according to the center of the object, false otherwise.
     */
    public boolean isCoordsWithinGivenArea(float x, float y, int distanceFromTheEdge) {
        double distance = Math.sqrt((x - this.coords.x) * (x - this.coords.x) + (y - this.coords.y) * (y - this.coords.y));
       
        return distance < (this.attr.getRadius() + distanceFromTheEdge);
    }
    
    public boolean collidesWith(MapObject other) {
        
        Vector2f difference = new Vector2f();
        Vector2f.sub(other.coords, coords, difference);
        
        return difference.length() < (this.attr.getRadius() + other.attr.getRadius());
        
    }

}
