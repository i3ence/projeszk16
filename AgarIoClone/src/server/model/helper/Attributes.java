package server.model.helper;

import java.awt.Color;

public class Attributes {

    private int radius;
    private int mass;
    private Color color;

    /**
     * Sets the attributes of this object.
     * 
     * @param radius The radius of the owner MapObject.
     * @param mass The mass of the owner MapObject.
     * @param color The color of the owner MapObject.
     */
    public Attributes(int radius, int mass, Color color) {
        this.radius = radius;
        this.mass = mass;
        this.color = color;
    }

    /**
     * Sets the radius.
     * 
     * @param radius The radius.
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Sets the mass.
     * 
     * @param mass The mass.
     */
    public void setMass(int mass) {
        this.mass = mass;
    }

    /**
     * Returns the radius.
     * 
     * @return The radius.
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Returns the mass.
     * 
     * @return The mass.
     */
    public int getMass() {
        return this.mass;
    }
   
    /**
     * Sets the color.
     * 
     * @param color The color.
     */
    public void setColor (Color color) {
        this.color = color;
    }
    
    /**
     * Returns the color.
     * 
     * @return The color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * If the mass is less the decreaseMassWith then sets to 1, decrease mass with decreaseMassWith otherwise.
     * 
     * @param decreaseMassWith The amount the mass will be subtracted with.
     */
    public void decreaseMassWith(int decreaseMassWith) {
        if (decreaseMassWith >= this.mass) {
            this.mass = 1;
        } else {
            this.mass -= decreaseMassWith;
        }
    }

    /**
     * Increases the mass with the amount of increaseMassWith.
     * 
     * @param increaseMassWith The amount to be added to the mass.
     */
    public void increaseMassWith(double increaseMassWith) {
        this.mass += increaseMassWith;
    }
    
}
