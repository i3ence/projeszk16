package server.model.object;

/**
 *
 * @author zoli-
 */
import java.awt.Color;
import server.model.Map;
import server.model.helper.Attributes;
import server.model.helper.Coords;
import common.model.SimpleMapObject;

public abstract class MapObject {

    protected Coords coords;
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
        this.coords = new Coords(x, y);
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
        this.coords = new Coords(x, y);
        this.attr = new Attributes(radius, mass, color);
        this.map = map;
    }

    /**
     * Returns the coordinates of the object.
     * 
     * @return The coordinates object.
     */
    public Coords getCoords() {
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
     * Checks if the given coordinates is within the given area to the objects center.
     * 
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param distanceFromTheEdge The given area the checking is made agains.
     * @return True if the given x,y coordinate is within the given distanceFromTheEdge according to the center of the object, false otherwise.
     */
    public boolean isCoordsWithninGivenArea(float x, float y, int distanceFromTheEdge) {
        double distance = Math.sqrt((x - this.coords.getX()) * (x - this.coords.getX()) + (y - this.coords.getY()) * (y - this.coords.getY()));
       
        return distance < (this.attr.getRadius() + distanceFromTheEdge);
    }

}
