
package client.model.helper;

import org.joml.Vector3f;

/**
 * Attributes contains the common properties of map objects.
 * @author zoli-
 * @author yzsolt
 */
public class Attributes {

    private int radius;
    private Vector3f color;

    /**
     * Attributes contains the common properties of map objects.
     * @param radius Radius of the MapObject.
     * @param color Color of the MapObject
     */
    public Attributes(int radius, Vector3f color) {
        this.radius = radius;
        this.color = color;
    }

    /**
     * Return radius of the MapObject
     * @return the radius
     */
    public int getRadius() {
        return this.radius;
    }
    
    /**
     * Return color of the MapObject
     * @return the color in vector form
     */
    public Vector3f getColor() {
        return this.color;
    }
    
    /**
     * Set radius of the MapObject
     * @param radius the radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    /**
     * Set color of the MapObject
     * @param color the color in vector form
     */
    public void setColor(Vector3f color) {
        this.color = color;
    }
    
}
