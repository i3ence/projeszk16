package common.model;

import java.awt.Color;
import java.io.Serializable;
import org.joml.Vector2f;

/**
 * Simple Serializable Object to be sent through the socket.
 * This should be easily converted from the server data to the GL Renderer.
 * @author zsiga
 */
public abstract class MapObject implements Serializable {
    
    // so that it can be transferred without moving to new project
    private static final long serialVersionUID = 1L;
    
    protected Vector2f position;
    
    protected int radius;
    protected int mass;
    protected Color color;
    protected String name;
    protected int id;
    
    /**
     * Sets the map instance and the attributes of a map object.
     * @param position
     */
    public MapObject(Vector2f position) {
        this.id = -1;
        this.position = position;
        this.radius = -1;
        this.mass = -1;
        this.color = Color.BLACK;
    }
    
    /**
     * Sets the map instance and the attributes of the object.
     * 
     * @param position The object's position.
     * @param id The unique ID of the object.
     * @param radius The radius of the object.
     * @param mass The mass of the object.
     * @param color The color of the object.
     */
    public MapObject(Vector2f position, int id, int radius, int mass, Color color) {
        this(position);
        this.id = id;
        this.radius = radius;
        this.mass = mass;
        this.color = color;
    }

    /**
     * Sets the map instance and the attributes of the object.
     * 
     * @param position Position of the object.
     * @param radius The radius of the object.
     * @param mass The mass of the object.
     * @param color The color of the object.
     * @param name name of player.
     * @param id id of player.
     */
    public MapObject(Vector2f position, int id, int radius, int mass, Color color, String name) {
        this(position, id, radius, mass, color);
        this.name = name;
    }
    
    /**
     * Copy data from an other instance.
     * @param other The other MapObject.
     */
    public void copyDataFrom(MapObject other) {
        this.position.x = other.position.x;
        this.position.y = other.position.y;
        this.radius = other.radius;
        this.mass = other.mass;
        this.color = other.color;
        this.name = other.name;
        this.id = other.id;
    }

    /**
     * Returns the ID of the Map Object.
     * @return ID of the Map Object.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the position of the Map Object.
     * @return Position of the Map Object.
     */
    public Vector2f getPosition() {
        return position;
    }
    
    /**
     * Set position of the Map Object.
     * @param x position on X axis.
     * @param y position on Y axis.
     */
    public void setPostition(float x, float y) {
        this.position = new Vector2f(x, y);
    }

    /**
     * Returns the radius of the Map Object.
     * @return Radius of the Map Object.
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Returns the mass of the Map Object.
     * @return Mass of the Map Object.
     */
    public int getMass() {
        return mass;
    }

    /**
     * Returns the color of the Map Object.
     * @return Color of the Map Object.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the name of the Map Object.
     * @return Name of the Map Object.
     */
    public String getName() {
        return name;
    }
    
    @Override
    public boolean equals(Object other) {
        
        if (other instanceof MapObject) {
            
            MapObject simpleMapObject = (MapObject)other;
            
            if (this.id < 0 || simpleMapObject.id < 0) {
                System.out.println("!!!!!!! NO ID");
            }
            
            return id == simpleMapObject.id;
            
        }
        
        return false;
        
    }

    @Override
    public int hashCode() {
        return id;
    }
    
}
