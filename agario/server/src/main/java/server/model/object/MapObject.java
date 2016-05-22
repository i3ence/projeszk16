package server.model.object;

/**
 * An abstract class that represents all the objects that can be found on the
 * map. To be extended by different Map Objects (Food, Cell, Thorn)
 *
 * @author zoli-
 */
import java.awt.Color;
import org.joml.Vector2f;
import server.model.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class MapObject extends common.model.MapObject {
private final Logger logger;
    private static int idCounter = 0;

    public static int getUniqueId() {
        return idCounter++;
    }

    protected final Map map;

    /**
     * Sets the map instance and the attributes of the object.
     *
     * @param x The x coordinate of the object's position.
     * @param y The x coordinate of the object's position.
     * @param radius The radius of the object.
     * @param mass The mass of the object.
     * @param map The map instance.
     * @param color The color of the object.
     */
    public MapObject(Map map, float x, float y, int radius, int mass, Color color) {
        super(new Vector2f(x, y), getUniqueId(), radius, mass, color);this.logger = Logger.getLogger(Cell.class.getName());
        this.map = map;
    }

    /**
     * Calculates the intersection percentage of this object against the given
     * MapObject.
     *
     * @param object The MapObject which the intersection is checked against.
     * @return The percentage of the intersection.
     */
    public double getIntersectionWithOtherObject(MapObject object) {
        double x = (double) object.getPosition().x;
        double y = (double) object.getPosition().y;
        double r = (double) object.getRadius();
        double R = (double) this.getRadius();
        double d = Math.sqrt((x - this.position.x) * (x - this.position.x) + (y - this.position.y) * (y - this.position.y));

        if (this instanceof Cell) {
            logger.log(Level.SEVERE, "distance {0}", d);
        }
         
        if ((d + R) < r) {
            return 100;
        }

//        if (R < r) {
//            double tmp = R;
//            R = r;
//            r = tmp;
//        }
        double part1 = r * r * Math.acos((d * d + r * r - R * R) / (2 * d * r));
        double part2 = R * R * Math.acos((d * d + R * R - r * r) / (2 * d * R));
        double part3 = 0.5 * Math.sqrt((-d + r + R) * (d + r - R) * (d - r + R) * (d + r + R));

        return (part1 + part2 - part3) / ((R * R * Math.PI) / 100);
    }

    /**
     * Checks if the given coordinates are within a certain radius from the
     * object's edge.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param distanceFromTheEdge The given area that the check is made against.
     * @return True if the given x,y coordinates are within the given
     * distanceFromTheEdge according to the center of the object, false
     * otherwise.
     */
    public boolean isCoordsWithinGivenArea(float x, float y, int distanceFromTheEdge) {
        double distance = Math.sqrt((x - this.position.x) * (x - this.position.x) + (y - this.position.y) * (y - this.position.y));

        return distance < (this.radius + distanceFromTheEdge);
    }

    public boolean collidesWith(MapObject other) {

        Vector2f difference = new Vector2f();
        Vector2f.sub(other.position, position, difference);

        return difference.length() < (this.radius + other.radius);

    }

}
