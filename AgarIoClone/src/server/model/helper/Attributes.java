package server.model.helper;

import java.awt.Color;

public class Attributes {

    private int radius;
    private int maxSpeed;//replace to cell
    private int mass;
    private Color color;

    public Attributes(int radius, int maxSpeed, int mass, Color color) {
        this.radius = radius;
        this.maxSpeed = maxSpeed;
        this.mass = mass;
        this.color = color;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }


    public int getRadius() {
        return this.radius;
    }

    public int getMass() {
        return this.mass;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }
    
    public void setColor (Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return this.color;
    }
    
}
