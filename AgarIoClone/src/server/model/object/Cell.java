/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.object;

/**
 *
 * @author zoli-
 */
public class Cell extends MapObject {

    public double getIntersectionWithOtherObject(MapObject object) {
        double x = (double)object.getCoords().getX();
        double y = (double)object.getCoords().getY();
        double r = (double)object.getAttributes().getRadius();
        double R = (double)this.attr.getRadius();
        double d = Math.sqrt((x - this.coords.getX()) * (x - this.coords.getX()) + (y - this.coords.getY()) * (y - this.coords.getY()));
        
        if (R < r) {
            // swap
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

}
