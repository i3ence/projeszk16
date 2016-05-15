package server.model.object;

import server.controller.network.communication.ResponseInterface;
import java.awt.Color;
import server.model.Map;

/**
 *
 * @author zoli-
 */
public class Cell extends MapObject {

    private final int maxSpeed, starterMass;
    private int status;
    private float movingAngle;//the angle sent by client
    private float movingSpeedRatio;//the normal vector sent by client, these values must be stored because these are used between client requests
    private String name;

    /**
     * Sets the attributes of the cell.
     *
     * @param x The x coordinate of the cell's position.
     * @param y The y coordinate of the cell's position.
     * @param radius The radius of the cell.
     * @param mass The mass of the cell.
     * @param map The map instance.
     * @param color The color of the cell.
     * @param name The name of the cell which is sent by the player.
     * @param maxSpeed The max speed of the cell.
     */
    public Cell(float x, float y, int radius, int mass, Map map, Color color, String name, int maxSpeed) {
        super(x, y, radius, mass, map, color);
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.starterMass = mass;
    }

    /**
     * Calculates the intersection percentage of the cell against the given
     * MapObject.
     *
     * @param object The MapObject which the itersection is checking against.
     * @return The percentage of the intersection.
     */
    public double getIntersectionWithOtherObject(MapObject object) {
        double x = (double) object.getCoords().getX();
        double y = (double) object.getCoords().getY();
        double r = (double) object.getAttributes().getRadius();
        double R = (double) this.attr.getRadius();
        double d = Math.sqrt((x - this.coords.getX()) * (x - this.coords.getX()) + (y - this.coords.getY()) * (y - this.coords.getY()));

        if (R < r) {
            double tmp = R;
            R = r;
            r = tmp;
        }
        double part1 = r * r * Math.acos((d * d + r * r - R * R) / (2 * d * r));
        double part2 = R * R * Math.acos((d * d + R * R - r * r) / (2 * d * R));
        double part3 = 0.5 * Math.sqrt((-d + r + R) * (d + r - R) * (d - r + R) * (d + r + R));

        return (part1 + part2 - part3) / ((R * R * Math.PI) / 100);
    }

    /**
     * Checks if the cell is collisioned with the given food object.
     *
     * @param food The food object the collision is checking against.
     * @return True if collision happened, false otherwise.
     */
    public boolean checkCollisionWithFood(Food food) {
        float x = food.getCoords().getX();
        float y = food.getCoords().getY();
        float r = food.getAttributes().getRadius();
        return ((x - this.coords.getX()) * (x - this.coords.getX()) + (y - this.coords.getY()) * (y - this.coords.getY())) < ((r + this.attr.getRadius()) * (r + this.attr.getRadius()));
    }

    /**
     * If the cell is alive then calculates the cell's new position according to
     * the angle, the maxSpeed and the mass and sets the coordinates to the new one.
     */
    public void move() {
        if (this.status == ResponseInterface.STATUS_PLAYING) {
            double divider;
            if (this.attr.getMass() < 20) {
                divider = 0;
            } else if (this.attr.getMass() > 1000) {
                divider = 1;
            } else {
                divider = (this.attr.getMass()) / 1000;
            }
            double distance = this.maxSpeed * (1 - divider) + 1;
            
            double cosineOfAngle = Math.cos(this.movingAngle);
            double sineOfAngle = Math.sin(this.movingAngle);

            double newX = this.coords.getX() + cosineOfAngle * distance;
            double newY = this.coords.getY() + sineOfAngle * distance;

            this.coords.setX((float)newX);
            this.coords.setY((float)newY);
        }
    }

    /**
     * Sets the moving speed ratio.
     *
     * @param ratio The moving speed ratio.
     */
    public void setMovingSpeedRatio(float ratio) {
        this.movingSpeedRatio = ratio;
    }

    /**
     * Sets the moving angle.
     *
     * @param angle The moving angle.
     */
    public void setMovingAngle(float angle) {
        this.movingAngle = angle;
    }

    /**
     * Returns the moving speed ratio.
     *
     * @return The moving speed ratio.
     */
    public float getMovingSpeedRation() {
        return this.movingSpeedRatio;
    }

    /**
     * Returns the moving angle.
     *
     * @return The moving angle.
     */
    public float getMovingAngle() {
        return this.movingAngle;
    }

    /**
     * Increases the cells mass and triggers the food object to be eaten.
     *
     * @param food The food object to be eaten.
     */
    public void eatFood(Food food) {
        this.attr.setMass(this.attr.getMass() + 1);
        food.gotEaten();
    }

    /**
     * Increases the cell's mass with the half mass of the given cell then
     * triggers the given cell to be eaten.
     *
     * @param cell The cell to be eaten.
     */
    public void eatCell(Cell cell) {
        this.attr.increaseMassWith((int) cell.getAttributes().getMass() / 1.5);
        cell.gotEaten();
    }

    /**
     * Sets the status of the cell to DEAD and the mass to the starter mass.
     */
    private void gotEaten() {
        this.status = ResponseInterface.STATUS_DEAD;
        this.attr.setMass(this.starterMass);
    }

    /**
     * Sets the status of the cell.
     *
     * @param status The new status.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Returns the status of the cell.
     *
     * @return The current status.
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Sets the name of the cell.
     *
     * @param name The name the player gives.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the current name of the cell.
     *
     * @return The name of the cell.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the max speed of the cell.
     *
     * @return The max speed of the cell.
     */
    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    /**
     * Decreases the cell's mass with the given percentage.
     *
     * @param percent The percentage the mass has to be decreased with.
     */
    public void decreaseCellWithPercent(int percent) {
        int decreaseMassWith = (this.attr.getMass() / 100) * percent;
        this.attr.decreaseMassWith(decreaseMassWith);
    }

}
