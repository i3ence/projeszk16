package common.model;

import java.awt.Color;
import java.io.Serializable;

/**
 * Simple Serializable Object to be sent through the socket.
 * This should be easily converted from the server data to the GL Renderer.
 * @author zsiga
 */
public abstract class SimpleMapObject implements Serializable{
    
    // so that it can be transferred without moving to new project
    private static final long serialVersionUID = 1L;
    //    protected Map map;
    // convert to Vector2f in GL
    protected float x;
    protected float y;
    
    protected int radius;
    protected int mass;
    protected Color color;
    protected String name;
    protected int id;
    
    /**
     * Sets the map instance and the attributes of a simplified map object.
     * 
     * @param x The x coordinate of the object's position.
     * @param y The x coordinate of the object's position.
     */
    public SimpleMapObject(float x, float y) {
        this.id = 0;
        this.x = x;
        this.y = y;
        this.radius = 1;
        this.mass = 2;
        this.color = Color.ORANGE;
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
    public SimpleMapObject(float x, float y, int radius, int mass, Color color) {
        this.id = 0;
        this.x = x;
        this.y = y;
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
    public SimpleMapObject(int id, String name, float x, float y, int radius, int mass, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.mass = mass;
        this.color = color;
        this.name = name;
        this.id = id;
    }
    
    

    public int getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
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
    
}
