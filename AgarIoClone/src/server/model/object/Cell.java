/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.object;

import server.controller.network.communication.ResponseInterface;
import java.awt.Color;
import server.model.Map;

/**
 *
 * @author zoli-
 */
public class Cell extends MapObject {

    private final int maxSpeed;
    private int status;
    private float movingAngle;//the angle sent by client
    private float movingSpeedRatio;//the normal vector sent by client, these values must be stored because these are used between client requests
    private String name;
    
    public Cell(float x, float y, int radius, int mass, Map map, Color color, String name, int maxSpeed) {
        super(x, y, radius, mass, map, color);
        this.name = name;
        this.maxSpeed = maxSpeed;
    }

    //Need to test if works fine
    public double getIntersectionWithOtherObject(MapObject object) {
        double x = (double) object.getCoords().getX();
        double y = (double) object.getCoords().getY();
        double r = (double) object.getAttributes().getRadius();
        double R = (double) this.attr.getRadius();
        double d = Math.sqrt((x - this.coords.getX()) * (x - this.coords.getX()) + (y - this.coords.getY()) * (y - this.coords.getY()));

        if (R < r) {
            double tmp = R;
            R = r;
            r = R;
        }
        Double part1 = r * r * Math.acos((d * d + r * r - R * R) / (2 * d * r));
        Double part2 = R * R * Math.acos((d * d + R * R - r * r) / (2 * d * R));
        Double part3 = 0.5 * Math.sqrt((-d + r + R) * (d + r - R) * (d - r + R) * (d + r + R));

        return (part1 + part2 - part3);
    }

    public boolean checkCollisionWithFood(Food food) {
        float x = food.getCoords().getX();
        float y = food.getCoords().getY();
        float r = food.getAttributes().getRadius();
        return ((x - this.coords.getX()) * (x - this.coords.getX()) + (y - this.coords.getY()) * (y - this.coords.getY())) < ((r + this.attr.getRadius()) * (r + this.attr.getRadius()));
    }

    public void move() {
        if (this.status == ResponseInterface.STATUS_PLAYING) {
            //Calculate new position
            float newX = 0;
            float newY = 0;

            this.coords.setX(newX);
            this.coords.setY(newY);
        }
    }

    public void setMovingSpeedRatio(float ratio) {
        this.movingSpeedRatio = ratio;
    }

    public void setMovingAngle(float angle) {
        this.movingAngle = angle;
    }

    public float getMovingSpeedRation() {
        return this.movingSpeedRatio;
    }

    public float getMovingAngle() {
        return this.movingAngle;
    }

    public void eatFood(Food food) {
        this.attr.setMass(this.attr.getMass() + 1);
        food.gotEaten();
    }

    public void eatCell(Cell cell) {
        this.attr.increaseMassWith((int)cell.getAttributes().getMass() / 1.5);
        cell.gotEaten();
    }

    private void gotEaten() {
        this.status = ResponseInterface.STATUS_DEAD;
    }
    
    public void setStatus (int status) {
        this.status = status;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }  

    public int getMaxSpeed() {
        return this.maxSpeed;
    }
    
    public void decreaseCellWithPercent(int percent) {
        int decreaseMassWith = (this.attr.getMass() / 100) * percent;
        this.attr.decreaseMassWith(decreaseMassWith);
    }

}
