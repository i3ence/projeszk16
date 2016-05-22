package server.model.object;

import common.Util;
import java.awt.Color;
import server.model.Map;
import java.util.logging.Logger;

/**
 * A cell that represents a player in the game.
 *
 * @author zoli-
 */
public class Cell extends MapObject {

    private final int maxSpeed, starterMass, starterRadius;
    private int status;
    private float movingAngle;//the angle sent by client
    private float movementMultiplier;
    private static int MAX_RADIUS = 100;

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
    public Cell(Map map, float x, float y, int radius, int mass, Color color, String name, int maxSpeed) {
        super(map, x, y, (radius + (int)Math.sqrt(mass) * 6), mass, color);
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.starterMass = mass;
        this.starterRadius = (radius + (int)Math.sqrt(mass) * 6);
        this.movementMultiplier = 0;
    }

    /**
     * If the cell is alive then calculates the cell's new position according to
     * the angle, the maxSpeed and the mass and sets the coordinates to the new one.
     */
    public void move() {
            float divider;
            if (this.getMass() < 20) {
                divider = 0;
            } else if (this.getMass() > 1000) {
                divider = 1;
            } else {
                divider = (this.getMass()) / 1000;
            }
            float distance = this.maxSpeed * (1 - divider) + 1;
            distance *= this.movementMultiplier;
            
            float cosineOfAngle = (float)Math.cos(this.movingAngle);
            float sineOfAngle = (float)Math.sin(this.movingAngle);

            float newX = this.position.x + cosineOfAngle * distance;
            float newY = this.position.y + sineOfAngle * distance;

            float mapSize = (float)map.getSize();
            
            this.position.x = Util.clampWithRadius(newX, 0, mapSize, radius);
            this.position.y = Util.clampWithRadius(newY, 0, mapSize, radius);
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
     * 
     * @param movementMultiplier is between 0 and 1
     */
    public void setMovementMultiplier(float movementMultiplier) {
        this.movementMultiplier = movementMultiplier;
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
        this.mass++;
        this.calculateAndSetRadius();
        food.gotEaten();
    }

    /**
     * Increase mass of cell by half of the cell passed as parameter.
     * Then triggers kill event (gotEaten) of passed cell.
     *
     * @param cell The cell to be eaten.
     */
    public void eatCell(Cell cell) throws InterruptedException {
        this.increaseMassWith((int) cell.getMass() * 0.5);
        this.calculateAndSetRadius();
        cell.gotEaten();
    }

    /**
     * Sets the status of the cell to DEAD and the mass to the starting mass.
     */
    private void gotEaten() throws InterruptedException {
        //this.status = SimpleResponse.STATUS_DEAD;
        this.mass = this.starterMass;
        this.radius = this.starterRadius;
        float [] coords = this.map.getRandomCoordsWithEmptyRadiusOf(10);
        this.setPostition(coords[0], coords[1]);
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
    
    public void setColor(Color color) {
        this.color = color;
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
     * If current mass is less than provided parameter, then it is set to 1.
     * Otherwise decrease mass with given parameter.
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
     * Increases the mass with the amount passed in parameter.
     * 
     * @param increaseMassWith The amount to be added to the mass.
     */
    public void increaseMassWith(double increaseMassWith) {
        this.mass += increaseMassWith;
    }

    /**
     * Decreases the mass of the cell with the given percentage.
     *
     * @param percent The percentage the mass has to be decreased with.
     */
    public void decreaseCellWithPercent(int percent) {
        float decreaseMassWith = ((float)this.mass / 100) * percent;
        this.decreaseMassWith((int)decreaseMassWith);
        this.calculateAndSetRadius();
    }
    
    /**
     * Calculates and sets the radius of the cell according to its mass.
     */
    private void calculateAndSetRadius() {
        if (this.radius < MAX_RADIUS) {
            int newRadius = this.starterRadius + (int)Math.sqrt(this.mass) * 6;
            if (newRadius > MAX_RADIUS) {
                this.radius = MAX_RADIUS;
            } else {
                this.radius = newRadius;
            }
        } 
    }
    
    /**
     * Wraps cell to a serializable object that can be sent to the client for rendering purposes.
     * @return The Simplified Cell object based on this Cell.
     */
    public common.model.Cell simplify() {
        return new common.model.Cell(this.position, this.id, getRadius(), getMass(), getColor(), this.name);
    }

}
