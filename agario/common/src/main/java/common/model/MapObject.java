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
     * Sets the map instance and the attributes of a map object.
     * 
     * @param x The x coordinate of the object's position.
     * @param y The x coordinate of the object's position.
     */
    public MapObject(float x, float y) {
        this(new Vector2f(x, y));
    }
    
    /**
     * Sets the map instance and the attributes of the object.
     * 
     * @param x The x coordinate of the object's position.
     * @param y The x coordinate of the object's position.
     * @param radius The radius of the object.
     * @param mass The mass of the object.
     * @param color The color of the object.
     */
    public MapObject(float x, float y, int radius, int mass, Color color) {
        this(x, y);
        this.radius = radius;
        this.mass = mass;
        this.color = color;
    }

    /**
     * Sets the map instance and the attributes of the object.
     * 
     * @param x The x coordinate of the object's position.
     * @param y The x coordinate of the object's position.
     * @param radius The radius of the object.
     * @param mass The mass of the object.
     * @param color The color of the object.
     * @param name name of player.
     * @param id id of player.
     */
    public MapObject(int id, String name, float x, float y, int radius, int mass, Color color) {
        this(x, y, radius, mass, color);
        this.name = name;
        this.id = id;
    }
    
    public void copyDataFrom(MapObject other) {
        this.position = other.position;
        this.radius = other.radius;
        this.mass = other.mass;
        this.color = other.color;
        this.name = other.name;
        this.id = other.id;
    }

    public int getId() {
        return id;
    }

    public Vector2f getPosition() {
        return position;
    }

    public int getRadius() {
        return radius;
    }

    public int getMass() {
        return mass;
    }

    public Color getColor() {
        return color;
    }

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
