package server.model.helper;

import java.awt.Color;

public class Attributes {

    private int radius;
    private int mass;
    private Color color;

    public Attributes(int radius, int mass, Color color) {
        this.radius = radius;
        this.mass = mass;
        this.color = color;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getRadius() {
        return this.radius;
    }

    public int getMass() {
        return this.mass;
    }
    
    public void setColor (Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return this.color;
    }

    public void decreaseMassWith(int decreaseMassWith) {
        if (decreaseMassWith >= this.mass) {
            this.mass = 1;
        } else {
            this.mass -= decreaseMassWith;
        }
    }

    public void increaseMassWith(double d) {
        this.mass += d;
    }
    
}
