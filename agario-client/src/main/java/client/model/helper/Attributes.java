
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

    public Attributes(int radius, Vector3f color) {
        this.radius = radius;
        this.color = color;
    }

    public int getRadius() {
        return this.radius;
    }
    
    public Vector3f getColor() {
        return this.color;
    }
    
    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    public void setColor(Vector3f color) {
        this.color = color;
    }
    
}
